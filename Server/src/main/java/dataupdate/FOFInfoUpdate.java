package dataupdate;

import blimpl.LogicUtil;
import dataservice.FOFDataService;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.*;
import exception.NotAllowedException;
import exception.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import startup.HibernateBoot;
import util.FOFUtilInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/9/2.
 */
public class FOFInfoUpdate {
    private FOFDataService fofDataService;
    private MarketDataService marketDataService;
    private String fof_code;
    private List<FofHoldInfoEntity> newFOFHoldInfos;
    private FofHistoryInfoEntity newFOFHistoryInfo;
    private NetWorthEntity netWorthEntity;
    private FofInfoEntity fofInfoEntity;
    private NetWorthEntity lastFOFNetWorth;
    FofHistoryInfoEntity lastHistoryInfo;
    List<FofHoldInfoEntity> lastHoldInfos;
    List<FofEstablishInfoEntity> fofEstablishInfoEntities;

    private double currentCost;
    private double cashValue;

    private String date;

    public FOFInfoUpdate() throws ObjectNotFoundException {
        fof_code = FOFUtilInfo.FOF_CODE;
        fofDataService = DataServiceController.getFOFDataService();
        marketDataService = DataServiceController.getMarketDataService();
    }

    public void init(String date) throws ObjectNotFoundException {
        this.date = date;
        newFOFHoldInfos = new ArrayList<>();
        newFOFHistoryInfo = new FofHistoryInfoEntity();
        netWorthEntity = new NetWorthEntity();
        fofInfoEntity = fofDataService.getFofInfoEntity(fof_code);
        fofEstablishInfoEntities = fofDataService
                .getFofEstablishInfo(fof_code);
        fofDataService.getFofEstablishInfo(fof_code);
        lastFOFNetWorth = marketDataService.getNewestNetWorth(fof_code);
        lastHoldInfos = fofDataService.getNewestFofHoldInfos(fof_code);
        lastHistoryInfo = fofDataService.getNewestHistoryInfo(fof_code);
        cashValue = lastHistoryInfo.getCashValue();
        currentCost = fofInfoEntity.getCurrentCost();
    }

    /**
     * 更新FOF信息
     */
    public void updateFOFinfo() throws NotAllowedException {
        try {
            double totalValue = lastHistoryInfo.getCashValue();

            /**
             * 更新FOFholdInfo
             */
            for (FofHoldInfoEntity entity : lastHoldInfos) {
                FofEstablishInfoEntity establishInfoEntity = LogicUtil.getFofEstablishByFundCode
                        (fofEstablishInfoEntities, entity.getFundId());
                FofHoldInfoEntity fofHoldInfoEntity = new FofHoldInfoEntity();
                NetWorthEntity netWorthEntity = LogicUtil.getNetWorthByDate(marketDataService.getNetWorth(entity
                        .getFundId()), date);
                fofHoldInfoEntity.setDate(date);
                fofHoldInfoEntity.setFofId(fof_code);
                fofHoldInfoEntity.setFundId(entity.getFundId());
                fofHoldInfoEntity.setHoldNum(entity.getHoldNum());
                fofHoldInfoEntity.setFinishedProfit(entity.getFinishedProfit());
                fofHoldInfoEntity.setNetWorth(netWorthEntity.getUnitWorth());
                fofHoldInfoEntity.setHoldValue(netWorthEntity.getUnitWorth() * entity.getHoldNum());
                cashValue += entity.getHoldValue() * (1 + netWorthEntity.getDailyRise() / 100)
                        - fofHoldInfoEntity.getHoldValue();
                fofHoldInfoEntity.setDayProfit(entity.getHoldValue() * netWorthEntity
                        .getDailyRise() / 100);
                fofHoldInfoEntity.setFloatProfit((netWorthEntity.getUnitWorth()
                        - establishInfoEntity.getBuyPrice()) * fofHoldInfoEntity.getHoldNum());
                fofHoldInfoEntity.setFloatProfitRatio((netWorthEntity.getUnitWorth()
                        - establishInfoEntity.getBuyPrice()) * fofHoldInfoEntity.getHoldNum()
                        / establishInfoEntity.getCost() * 100);
                fofHoldInfoEntity.setTotalProfit(entity.getTotalProfit()
                        + fofHoldInfoEntity.getDayProfit());
                fofHoldInfoEntity.setTotalProfitRatio(((entity.getTotalProfitRatio() / 100 + 1) *
                        (netWorthEntity.getDailyRise() / 100 + 1) - 1) * 100);
                totalValue += fofHoldInfoEntity.getHoldValue();
                newFOFHoldInfos.add(fofHoldInfoEntity);
            }

            totalValue += cashValue;
            newFOFHistoryInfo.setTotalValue(totalValue);


            for (FofHoldInfoEntity entity : newFOFHoldInfos) {
                entity.setRatio(entity.getHoldValue() / totalValue * 100);
            }
            // FOFHoldInfo更新完成
            // 处理调仓请求
//            consumePositionChangeResuqest();

            upDateHistoryInfo();
            upDateFOFQuickInfo();

            saveInfos();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理仓位变化信息
     */
    private void consumePositionChangeResuqest() throws NotAllowedException {
        List<PositionChangeEntity> positionChangeRequest = null;
        try {
            positionChangeRequest = fofDataService.getPositionChangeRequest(fof_code);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return;
        }

        for (PositionChangeEntity positionChangeEntity : positionChangeRequest) {
            try {
                FofHoldInfoEntity entity = LogicUtil.getFofHoldInfoByFundCode(newFOFHoldInfos,
                        positionChangeEntity.getFundCode());
                //买入
                if (positionChangeEntity.getBuyNum() != 0) {
                    double totalFee = getTotalFee(positionChangeEntity);
                    if (cashValue < totalFee)
                        throw new NotAllowedException("现金不够购买基金");
                    cashValue -= totalFee;
                    handlePositionChange(entity, positionChangeEntity, true);
                }
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                addNewFund(positionChangeEntity);
            }
        }
    }

    /**
     * 更新基金历史信息
     */
    private void upDateHistoryInfo() {
        double dailyProfit = newFOFHistoryInfo.getTotalValue() - lastHistoryInfo.getTotalValue();
        double dailyProfitRate = dailyProfit / lastHistoryInfo.getTotalValue();
        double totalProfit = lastHistoryInfo.getTotalProfit() + dailyProfit;
        double totalProfitRate = (1 + lastHistoryInfo.getTotalProfitRate() / 100) * (1 +
                dailyProfitRate) - 1;
        newFOFHistoryInfo.setDate(date);
        newFOFHistoryInfo.setFofId(lastHistoryInfo.getFofId());
        newFOFHistoryInfo.setScale(lastHistoryInfo.getScale());
        newFOFHistoryInfo.setTotalProfit(totalProfit);
        newFOFHistoryInfo.setTotalProfitRate(totalProfitRate * 100);
        newFOFHistoryInfo.setDailyProfit(dailyProfit);
        newFOFHistoryInfo.setDailyProfitRate(dailyProfitRate * 100);
        newFOFHistoryInfo.setCashValue(cashValue);
    }

    /**
     * 更新基金信息
     */
    private void upDateFOFQuickInfo() {
        fofInfoEntity.setCurrentCost(currentCost);
        fofInfoEntity.setTotalProfit(newFOFHistoryInfo.getTotalProfitRate());
        fofInfoEntity.setNetAsset(newFOFHistoryInfo.getTotalValue());
        double oldNetWorth = fofInfoEntity.getNetWorth();
        double netWorth = fofInfoEntity.getNetAsset() / fofInfoEntity.getScale();
        fofInfoEntity.setNetWorth(netWorth);
        double riseValue = (netWorth - oldNetWorth);
        double rise = riseValue / oldNetWorth;

        netWorthEntity.setDate(date);
        netWorthEntity.setCode(FOFUtilInfo.FOF_CODE);
        netWorthEntity.setUnitWorth(netWorth);
        netWorthEntity.setDailyRise(rise * 100);
        netWorthEntity.setTotalWorth(lastFOFNetWorth.getTotalWorth() + riseValue);
    }

    public void addNewFund(PositionChangeEntity entity) {

    }

    private void saveInfos() {
        Session se = HibernateBoot.openSession();
        Transaction transaction = se.beginTransaction();
        se.saveOrUpdate(newFOFHistoryInfo);
        se.saveOrUpdate(netWorthEntity);
        se.saveOrUpdate(fofInfoEntity);
        newFOFHoldInfos.forEach(se::saveOrUpdate);
        transaction.commit();
        se.close();
    }



    /**
     * 得到申购总费用
     *
     * @param positionChangeEntity
     * @return
     */
    public double getTotalFee(PositionChangeEntity positionChangeEntity) {
        return positionChangeEntity.getBuyNum() * positionChangeEntity.getBuyPrice();
    }

    /**
     * 处理调整请求
     * 主要有调整持有量、持有市值、浮动盈亏、浮动盈亏率、已实现盈亏
     *
     * @param holdInfo
     * @param positionChange
     * @param isBuy
     */
    private void handlePositionChange(FofHoldInfoEntity holdInfo, PositionChangeEntity
            positionChange, boolean isBuy) {
        if (isBuy) {
            holdInfo.setHoldNum(holdInfo.getHoldNum() + positionChange.getBuyNum());
            holdInfo.setHoldValue(holdInfo.getHoldNum() + positionChange.getBuyNum() * positionChange
                    .getBuyPrice());
        }
    }

    public static void main(String[] args) throws Exception {
        List<NetWorthEntity> entitys = DataServiceController.getMarketDataService().getNetWorth
                ("000122", "2016-08-31", LocalDate.now().toString());
        FOFInfoUpdate fofInfoUpdate = new FOFInfoUpdate();
        for (NetWorthEntity entity : entitys) {
            System.out.println(entity.getDate());
            fofInfoUpdate.init(entity.getDate());
            fofInfoUpdate.updateFOFinfo();
        }
        HibernateBoot.closeConnection();
    }

}
