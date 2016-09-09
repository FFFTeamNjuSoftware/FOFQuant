package dataupdate;

import beans.FundJSNetWorth;
import com.google.gson.Gson;
import entities.NetWorthEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import util.HttpTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by st0001 on 2016/8/30.
 */
public class UpdateNetWorthFromJS {
    private final String url = "http://fund.eastmoney.com/f10/F10DataApi" +
            ".aspx?type=lsjz&code=%s&page=1&per=100000&sdate=%s&edate=%s&rt=%s";
    private final String previous = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
    /**
     * 最大线程数量
     */
    private final int maxThreadNum = 40;
    private int currentThreadNum;
    private int leaveThreadNum;
    private Condition hasFreeTreadNum;
    private Lock lock, finishOpe;
    /**
     * 结束操作
     */
    private Runnable runnable;
    /**
     * 一个类只能start一次
     */
    private boolean dead = false;

    List<Thread> threads;


    public UpdateNetWorthFromJS() {
        currentThreadNum = 0;
        lock = new ReentrantLock();
        finishOpe = new ReentrantLock();
        hasFreeTreadNum = lock.newCondition();
        leaveThreadNum = 0;
        threads = new ArrayList<>();
    }

    public void updateNetWorth(String code, String startDate) {
        if (!dead) {
            UpdateClass updateClass = new UpdateClass(code, startDate);
            Thread newThread = new Thread(updateClass);
            threads.add(newThread);
            leaveThreadNum++;
        } else {
            System.out.println("class is dead!!");
        }
    }

    public void startUpdate() {
        dead = true;
        while (leaveThreadNum > 0) {
            lock.lock();
            while (currentThreadNum >= maxThreadNum) {
                try {
                    hasFreeTreadNum.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            threads.get(0).start();
            threads.remove(0);
            leaveThreadNum--;
            currentThreadNum++;
            lock.unlock();
        }
        finishOperation();
    }

    public void setFinishOperation(Runnable runnable) {
        this.runnable = runnable;
    }

    private void finishOperation() {
        finishOpe.lock();
        if (runnable != null) {
            new Thread(runnable).start();
            runnable = null;
        }
        finishOpe.unlock();
    }

    class UpdateClass implements Runnable {
        String code;
        String startDate;

        UpdateClass(String code, String startDate) {
            this.code = code;
            this.startDate = startDate;
        }

        @Override
        public void run() {
            Session se = null;
            try {
                se = HibernateBoot.openSession();
                String url_str = String.format(url, code, startDate, "", Math.random());
                HttpTool httpTool = new HttpTool();
                String content = httpTool.getURLContent(url_str, "gb2312");
                AnalyseFundJSResult analyseFundJSResult = new AnalyseFundJSResultImpl();
                FundJSNetWorth fundJSNetWorth = new Gson().fromJson(analyseFundJSResult.getJSONContent
                        (content), FundJSNetWorth.class);
                Document document = DocumentHelper.parseText(previous + fundJSNetWorth.content);
                Element rootElement = document.getRootElement();
                List<Element> head = rootElement.element("thead").element("tr").elements("th");
                boolean isMoneyMarket = !head.get(1).getText().equals("单位净值");
                Element body = rootElement.element("tbody");
                List<Element> li = body.elements("tr");
                Transaction tra = se.beginTransaction();
                for (Element element : li) {
                    List<Element> tds = element.elements("td");
                    if (tds.size() == 1) {
                        System.out.println("code " + code + " has no data");
                        return;
                    }
                    String date = tds.get(0).getText();
                    if (date.length() == 0)
                        continue;
                    double field1 = new Double(tds.get(1).getText());
                    NetWorthEntity netWorthEntity = new NetWorthEntity();
                    netWorthEntity.setCode(code);
                    netWorthEntity.setDate(date);
                    netWorthEntity.setUnitWorth(field1);
                    String field2_str = tds.get(2).getText();
                    String field3_str = tds.get(3).getText();
                    if (isMoneyMarket) {
                        if (field2_str.length() == 0)
                            continue;
                        double field2 = new Double(field2_str.substring(0, field2_str.length() - 1));
                        netWorthEntity.setDailyRise(field1 / 100);
                        netWorthEntity.setTotalWorth(field2);
                    } else {
                        if (field3_str.length() == 0)
                            continue;
                        double field2 = new Double(field2_str);
                        double field3 = new Double(field3_str.substring(0, field3_str.length() - 1));
                        netWorthEntity.setTotalWorth(field2);
                        netWorthEntity.setDailyRise(field3);
                    }
                    se.saveOrUpdate(netWorthEntity);
                }
                tra.commit();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            } finally {
                lock.lock();
                currentThreadNum--;
                if (currentThreadNum < maxThreadNum)
                    hasFreeTreadNum.signalAll();
                if (currentThreadNum == 0 && leaveThreadNum == 0)
                    finishOperation();
                System.out.println("leave thred:" + leaveThreadNum);
                lock.unlock();
                se.close();
            }

        }
    }
}
