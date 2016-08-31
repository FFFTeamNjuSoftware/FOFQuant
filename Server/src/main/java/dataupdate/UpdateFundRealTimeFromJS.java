package dataupdate;

import beans.FundRealTimeInfo;
import blimpl.fof.FundRealTimeInfoGetter;
import entities.FundRealtimeInfoEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Daniel on 2016/8/31.
 */
public class UpdateFundRealTimeFromJS {
    /**
     * 最大线程数量
     */
    private final int maxThreadNum = 40;
    private int currentThreadNum;
    private Condition hasFreeTreadNum;
    private Lock lock;
    private FundRealTimeInfoGetter fundRealTimeInfoGetter;


    public UpdateFundRealTimeFromJS() {
        currentThreadNum = 0;
        lock = new ReentrantLock();
        hasFreeTreadNum = lock.newCondition();
        fundRealTimeInfoGetter = new FundRealTimeInfoGetter();
    }

    public void updateFundRealTimeInfo(String code) {
        lock.lock();
        while (currentThreadNum >= maxThreadNum) {
            try {
                hasFreeTreadNum.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        UpdateFundRealTimeRunnable updateFundRealTimeRunnable = new UpdateFundRealTimeRunnable(code);
        Thread thread = new Thread(updateFundRealTimeRunnable);
        thread.start();
        lock.unlock();

    }

    class UpdateFundRealTimeRunnable implements Runnable {
        String code;

        UpdateFundRealTimeRunnable(String code) {
            this.code = code;
        }

        @Override
        public void run() {
            Session se = null;
            try {
                se = HibernateBoot.openSession();
                Transaction transaction = se.beginTransaction();
                FundRealTimeInfo fundRealTimeInfo = fundRealTimeInfoGetter.getFundRealTimeInfo(code);
                FundRealtimeInfoEntity entity = new FundRealtimeInfoEntity();
                entity.setCode(fundRealTimeInfo.fundcode);
                String[] tems = fundRealTimeInfo.gztime.split(" ");
                entity.setDate(tems[0]);
                entity.setTime(tems[1]);
                entity.setGsz(fundRealTimeInfo.gsz);
                entity.setGsrise(fundRealTimeInfo.gszzl);
                se.saveOrUpdate(entity);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.lock();
                currentThreadNum--;
                if (currentThreadNum < maxThreadNum)
                    hasFreeTreadNum.signalAll();
                lock.unlock();
                se.close();
            }
        }
    }
}
