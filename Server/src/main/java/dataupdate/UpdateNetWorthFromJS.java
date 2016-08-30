package dataupdate;

import beans.FundJSNetWorth;
import blimpl.AnalyseFundJSResult;
import blimpl.AnalyseFundJSResultImpl;
import com.google.gson.Gson;
import entities.NetWorthEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import util.HttpTool;

import java.io.IOException;
import java.util.List;

/**
 * Created by st0001 on 2016/8/30.
 */
public class UpdateNetWorthFromJS {
    private final String url = "http://fund.eastmoney.com/f10/F10DataApi" +
            ".aspx?type=lsjz&code=%s&page=1&per=100000&sdate=%s&edate=%s&rt=%s";
    private final String previous = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

    public void updateNetWorth(String code, String startDate) {
        UpdateClass updateClass = new UpdateClass(code, startDate);
        Thread newThread = new Thread(updateClass);
        newThread.setDaemon(true);
        newThread.start();
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
            try {
                String url_str = String.format(url, code, startDate, "", Math.random());
                HttpTool httpTool = new HttpTool();
                String content = httpTool.getURLContent(url_str, "gb2312");
                AnalyseFundJSResult analyseFundJSResult = new AnalyseFundJSResultImpl();
                System.out.println(analyseFundJSResult.getJSONContent(content));
                FundJSNetWorth fundJSNetWorth = new Gson().fromJson(analyseFundJSResult.getJSONContent
                        (content), FundJSNetWorth.class);
                SAXReader reader = new SAXReader();
                Document document = DocumentHelper.parseText(previous + fundJSNetWorth.content);
                Element rootElement = document.getRootElement();
                List<Element> head = rootElement.element("thead").element("tr").elements("th");
                for (Element ele : head) {
                    System.out.print(ele.getText() + "\t");
                }
                System.out.println();
                Element body = rootElement.element("tbody");
                List<Element> li = body.elements("tr");
                for (Element element : li) {
                    List<Element> li2 = element.elements("td");
                    for (Element ele : li2) {
                        System.out.print(ele.getText() + "\t");
                    }
                    System.out.println();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }

        }
    }
}
