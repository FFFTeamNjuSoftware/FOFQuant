package blimpl.fof;

import beans.FundInFOFQuickInfo;
import beans.FundRealTimeInfo;
import bl.fof.FOFRealTimeMonitorLogic;
import blimpl.LogicUtil;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import dataupdate.FundRealTimeInfoGetter;
import entities.FofEstablishInfoEntity;
import entities.FofHistoryInfoEntity;
import entities.FofHoldInfoEntity;
import entities.FofInfoEntity;
import exception.ObjectNotFoundException;
import util.FOFUtilInfo;
import util.NumberOpe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFRealTimeMonitorLogicImpl extends UnicastRemoteObject implements FOFRealTimeMonitorLogic {
    private String baseCode;
    private String fof_code;
    private FOFDataService fofDataService;


    private FOFRealTimeMonitorLogicImpl() throws RemoteException {
        baseCode = "I000300";
        fof_code = FOFUtilInfo.FOF_CODE;
        fofDataService = DataServiceController.getFOFDataService();
    }

    private static FOFRealTimeMonitorLogic instance;

    public static FOFRealTimeMonitorLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFRealTimeMonitorLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public List<FundInFOFQuickInfo> getFundInFOFQuickinfo() throws RemoteException {
        try {
            List<FofHoldInfoEntity> holdInfos = fofDataService.getNewestFofHoldInfos(fof_code);
            FundRealTimeInfoGetter fundRealTimeInfoGetter = new FundRealTimeInfoGetter();
            FofHistoryInfoEntity fofHistoryInfoEntity = fofDataService.getNewestHistoryInfo(fof_code);
            FofInfoEntity fofInfoEntity = fofDataService.getFofInfoEntity(fof_code);
            List<FofEstablishInfoEntity> fofEstablishInfoEntities = fofDataService.getFofEstablishInfo
                    (fof_code);
            double total_value = fofHistoryInfoEntity.getTotalValue();
            List<FundInFOFQuickInfo> result = new ArrayList<>();
            for (FofHoldInfoEntity holdInfo : holdInfos) {
                FundRealTimeInfo fundRealTimeInfo = fundRealTimeInfoGetter.getFundRealTimeInfo
                        (holdInfo.getFundId());
                FofEstablishInfoEntity fofEstablishInfoEntity = LogicUtil
                        .getFofEstablishByFundCode(fofEstablishInfoEntities, holdInfo.getFundId());
                double rise = fundRealTimeInfo.gszzl / 100;
                boolean isSameDay = holdInfo.getDate().equals(fundRealTimeInfo.gztime.split(" ")[0]);
                FundInFOFQuickInfo fundInFOFQuickInfo = new FundInFOFQuickInfo();
                fundInFOFQuickInfo.fundCode = holdInfo.getFundId();
                fundInFOFQuickInfo.fundName = fundRealTimeInfo.name;
                fundInFOFQuickInfo.predictNetValue = fundRealTimeInfo.gsz;
                fundInFOFQuickInfo.time = fundRealTimeInfo.gztime;
                fundInFOFQuickInfo.predictRise = fundRealTimeInfo.gszzl;
                fundInFOFQuickInfo.holdNum = holdInfo.getHoldNum();
                fundInFOFQuickInfo.holdValue = fundRealTimeInfo.gsz * holdInfo.getHoldNum();
                fundInFOFQuickInfo.dayProfit = (fundRealTimeInfo.gsz - fundRealTimeInfo.dwjz) * holdInfo
                        .getHoldNum() - fofEstablishInfoEntity.getOtherFee();
                fundInFOFQuickInfo.totalProfit = isSameDay ? holdInfo.getTotalProfit() : holdInfo
                        .getTotalProfit() + fundInFOFQuickInfo.dayProfit;
                fundInFOFQuickInfo.totalProfitRatio = ((1 + holdInfo.getTotalProfitRatio() / 100) *
                        (1 + fundInFOFQuickInfo.predictRise / 100) - 1) * 100;
                fundInFOFQuickInfo.predictRiseValue = fundRealTimeInfo.dwjz * rise;
                fundInFOFQuickInfo.finishedProfit = holdInfo.getFinishedProfit();
                fundInFOFQuickInfo.floatProfit = holdInfo.getFloatProfit() + fundInFOFQuickInfo.dayProfit;
                fundInFOFQuickInfo.floatProfitRatio = fundInFOFQuickInfo
                        .floatProfit / fofInfoEntity.getCurrentCost() * 100;
                if (!isSameDay)
                    total_value += fundInFOFQuickInfo.dayProfit;
                result.add(fundInFOFQuickInfo);
            }
            for (FundInFOFQuickInfo fundInFOFQuickInfo : result) {
                fundInFOFQuickInfo.newestWeight = fundInFOFQuickInfo.holdValue / total_value * 100;
                formatResult(fundInFOFQuickInfo);
            }
            return result;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {
        this.baseCode = indexCode;
    }

    private void formatResult(FundInFOFQuickInfo fundInFOFQuickInfo) {
        fundInFOFQuickInfo.cost = fundInFOFQuickInfo.cost / FOFUtilInfo.VALUE_UNIT;
        fundInFOFQuickInfo.dayProfit = fundInFOFQuickInfo.dayProfit / FOFUtilInfo.PROFIT_UNIT;
        fundInFOFQuickInfo.floatProfit = fundInFOFQuickInfo.floatProfit / FOFUtilInfo.PROFIT_UNIT;
        fundInFOFQuickInfo.finishedProfit = fundInFOFQuickInfo.finishedProfit / FOFUtilInfo
                .PROFIT_UNIT;
        fundInFOFQuickInfo.holdNum = fundInFOFQuickInfo.holdNum / FOFUtilInfo.NUM_UNIT;
        fundInFOFQuickInfo.holdValue = fundInFOFQuickInfo.holdValue / FOFUtilInfo.VALUE_UNIT;
        fundInFOFQuickInfo.totalProfit = fundInFOFQuickInfo.totalProfit / FOFUtilInfo.VALUE_UNIT;
        NumberOpe.controlDecimal(fundInFOFQuickInfo, 3);
    }

}
