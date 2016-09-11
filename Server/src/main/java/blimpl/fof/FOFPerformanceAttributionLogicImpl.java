package blimpl.fof;

import beans.PerformanceAttribution;
import bl.fof.FOFPerformanceAttributionLogic;
import dataservice.BaseInfoDataService;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import entities.FofHoldInfoEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.FOFUtilInfo;
import util.NumberOpe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFPerformanceAttributionLogicImpl extends UnicastRemoteObject implements
        FOFPerformanceAttributionLogic {
    private String startDate;
    private String endDate;
    private String baseCode;
    private String fof_code;
    private FOFDataService fofDataService;
    private BaseInfoDataService baseInfoDataService;

    private FOFPerformanceAttributionLogicImpl() throws RemoteException {
        fofDataService = DataServiceController.getFOFDataService();
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
        fof_code = FOFUtilInfo.FOF_CODE;
    }

    private static FOFPerformanceAttributionLogic instance;

    public static FOFPerformanceAttributionLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFPerformanceAttributionLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public void setStartDate(String startDate) throws ParameterException, RemoteException {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(String endDate) throws ParameterException, RemoteException {
        this.endDate = endDate;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {
        this.baseCode = indexCode;
    }

    @Override
    public List<PerformanceAttribution> getPerformanceAttribution() throws RemoteException {
        try {
            List<FofHoldInfoEntity> entities = fofDataService.getFofHoldInfos(fof_code, startDate, endDate);
            Map<String, List<FofHoldInfoEntity>> infoByCode = entities.stream().collect(Collectors
                    .groupingBy(e -> e.getFundId()));
            Set<String> allCodes = infoByCode.keySet();
            List<PerformanceAttribution> performanceAttributions = new ArrayList<>();
            for (String code : allCodes) {
                PerformanceAttribution performanceAttribution = new PerformanceAttribution();
                performanceAttribution.fundCode = code;
                performanceAttribution.fundName = baseInfoDataService.getFundInfo(code)
                        .getSimpleName();
                List<FofHoldInfoEntity> fofHoldInfoEntities = infoByCode.get(code);
                Collections.sort(fofHoldInfoEntities, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
                FofHoldInfoEntity firstHold = fofHoldInfoEntities.get(0);
                FofHoldInfoEntity endHold = fofHoldInfoEntities.get(fofHoldInfoEntities.size() - 1);
                performanceAttribution.beginingHoldNum = firstHold.getHoldNum();
                performanceAttribution.beginingPerValue = firstHold.getNetWorth();
                performanceAttribution.beginingTotalValue = firstHold.getHoldValue();
                performanceAttribution.beginingHoldRatio = firstHold.getRatio();
                performanceAttribution.endingHoldNum = endHold.getHoldNum();
                performanceAttribution.endingPerValue = endHold.getNetWorth();
                performanceAttribution.endingHoldRatio = endHold.getRatio();
                performanceAttribution.endingTotalValue = endHold.getHoldValue();
                performanceAttribution.periodProfit = endHold.getTotalProfit() - firstHold
                        .getTotalProfit();
                performanceAttribution.periodProfitRate = endHold.getTotalProfitRatio() - firstHold
                        .getTotalProfitRatio();
                performanceAttribution.periodProfitFinishProfit = endHold.getFinishedProfit()
                        - firstHold.getFinishedProfit();
                //// TODO: 2016/9/2 unitProfit
                performanceAttribution.unitProfit = 0;
                handleResult(performanceAttribution);
                performanceAttributions.add(performanceAttribution);
            }
            return performanceAttributions;
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void handleResult(PerformanceAttribution performanceAttribution) {
        performanceAttribution.beginingHoldNum = performanceAttribution.beginingHoldNum / FOFUtilInfo
                .NUM_UNIT;
        performanceAttribution.beginingTotalValue = performanceAttribution
                .beginingTotalValue / FOFUtilInfo.VALUE_UNIT;
        performanceAttribution.endingHoldNum = performanceAttribution.endingHoldNum / FOFUtilInfo
                .NUM_UNIT;
        performanceAttribution.endingTotalValue = performanceAttribution
                .endingTotalValue / FOFUtilInfo.VALUE_UNIT;
        performanceAttribution.periodProfit = performanceAttribution.periodProfit / FOFUtilInfo
                .VALUE_UNIT;
        NumberOpe.controlDecimal(performanceAttribution, 4);
    }
}
