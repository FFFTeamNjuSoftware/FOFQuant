package dataupdate;

/**
 * Created by Daniel on 2016/9/12.
 */

import beans.PriceInfo;
import blimpl.CalculateDataHandler;
import dataserviceimpl.DataServiceController;
import entities.ConstParameterEntity;
import entities.WarnLogEntity;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import util.FOFUtilInfo;
import util.TimeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 更新风险信息
 */
public class UpdateWarnLog {
    List<PriceInfo> fof_profit_info, index_profit_info;
    String fof_code = FOFUtilInfo.FOF_CODE, base_code = "I000001";
    List<WarnLogEntity> warnLogEntities = new ArrayList<>();
    ConstParameterEntity constParameter;
    int maxRetreatWindowNum = 60, aveWindowNum = 60;
    static final String RETREAT_INFO_PATTERN = "最大回撤率达%s",
            INDEX_AVE_INFO_PATTERN = "上证指数突破60日均线，指数点数：%s";

    /**
     * 读取数据
     */
    private void readData() {
        constParameter = DataServiceController.getBaseInfoDataService().getConstParameter();
        CalculateDataHandler calculateDataHandler = new CalculateDataHandler(FOFUtilInfo.FOF_CODE);
        calculateDataHandler.setBaseCode(base_code).setTimeType(TimeType.SINCE_ESTABLISH);
        try {
            Map<String, List<PriceInfo>> re = calculateDataHandler.getDatasByPriceInfo();
            fof_profit_info = re.get(fof_code);
            index_profit_info = re.get(base_code);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        readData();
        double totalProfit = 0;
        for (int i = 0; i < fof_profit_info.size(); i++) {
            totalProfit = (totalProfit + 1) * (1 + fof_profit_info.get(i).rise / 100) - 1;
            updateIndexAve(i, totalProfit);
            updateMaxRetreat(i, totalProfit);
        }
        Session se = HibernateBoot.openSession();
        Transaction transaction = se.beginTransaction();
        for (WarnLogEntity warnLog : warnLogEntities) {
            se.save(warnLog);
        }
        transaction.commit();
        se.close();
        HibernateBoot.closeConnection();
    }

    private void updateMaxRetreat(int index, double totalProfit) {
        if (index >= maxRetreatWindowNum) {
            double retreatValue = 1;
            for (int i = index - maxRetreatWindowNum; i <= index; i++) {
                retreatValue *= (1 + fof_profit_info.get(i).rise / 100);
            }
            retreatValue -= 1;
            if (retreatValue < 0 && -retreatValue > constParameter.getMaxRetreatRatio() / 100) {
                addMaxRetreatLog(retreatValue, fof_profit_info.get(index).price, totalProfit,
                        fof_profit_info.get(index).date);
            }
        }
    }

    private void updateIndexAve(int index, double totalProfit) {
        if (index >= aveWindowNum + 1) {
            double ave1 = 0, ave2 = 0, ave3 = 0;
            int startIndex = index - aveWindowNum - 1;
            for (int i = startIndex; i <= index; i++) {
                PriceInfo priceInfo = index_profit_info.get(i);
                if (i <= index - 2) {
                    ave1 += priceInfo.price;
                }
                if (startIndex + 1 <= i && i <= index - 1) {
                    ave2 += priceInfo.price;
                }
                if (startIndex + 2 <= i && i <= index) {
                    ave3 += priceInfo.price;
                }
            }
            ave1 /= aveWindowNum;
            ave2 /= aveWindowNum;
            ave3 /= aveWindowNum;
            if (index_profit_info.get(index - 2).price < ave1 && index_profit_info.get(index - 1).price
                    < ave2 && index_profit_info.get(index).price < ave3) {
                addIndexAveLog(index_profit_info.get(index).price, fof_profit_info.get(index).price,
                        totalProfit, fof_profit_info.get(index).date);
            }
        }
    }

    private void addMaxRetreatLog(double retreatValue, double netValue, double totalProfit, String
            date) {
        WarnLogEntity warnLogEntity = new WarnLogEntity();
        warnLogEntity.setNetWorth(netValue);
        warnLogEntity.setDate(date);
        warnLogEntity.setTotalProfit(totalProfit);
        warnLogEntity.setWarnInfo(String.format(RETREAT_INFO_PATTERN, retreatValue));
        warnLogEntities.add(warnLogEntity);
    }

    private void addIndexAveLog(double price, double netValue, double totalProfit, String date) {
        WarnLogEntity warnLogEntity = new WarnLogEntity();
        warnLogEntity.setDate(date);
        warnLogEntity.setNetWorth(netValue);
        warnLogEntity.setTotalProfit(totalProfit);
        warnLogEntity.setWarnInfo(String.format(INDEX_AVE_INFO_PATTERN, price));
        warnLogEntities.add(warnLogEntity);
    }

    public static void main(String[] args) {
        UpdateWarnLog updateWarnLog = new UpdateWarnLog();
        updateWarnLog.update();
    }

}