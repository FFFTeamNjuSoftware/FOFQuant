package blimpl.fof;

import beans.AssetItem;
import beans.PositionChange;
import bl.fof.FOFAssetAllocationLogic;
import blimpl.Converter;
import dataservice.BaseInfoDataService;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import entities.FofHoldInfoEntity;
import entities.FofInfoEntity;
import entities.FundQuickInfosEntity;
import entities.PositionChangeEntity;
import exception.ObjectNotFoundException;
import util.FOFUtilInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFAssetAllocationLogicImpl extends UnicastRemoteObject implements
        FOFAssetAllocationLogic {
    FOFDataService fofDataService;
    BaseInfoDataService baseInfoDataService;
    String fof_code;

    private FOFAssetAllocationLogicImpl() throws RemoteException {
        fofDataService = DataServiceController.getFOFDataService();
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
        fof_code = FOFUtilInfo.FOF_CODE;
    }

    private static FOFAssetAllocationLogic instance;

    public static FOFAssetAllocationLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFAssetAllocationLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public List<String> getAllSupportDate() throws RemoteException {
        return null;
    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public List<PositionChange> getFOFPositionChanges() throws RemoteException {
        try {
            return fofDataService.getPositionChange(fof_code).stream().map
                    (Converter::convertPositionChange).collect(Collectors.toList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<AssetItem> getFOFAssetAllocation() throws RemoteException {
        try {
            return fofDataService.getFOFAssetAllocation(fof_code).stream().map
                    (Converter::convertFOFAssetAllocation).collect(Collectors.toList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, Double> getWeights() throws RemoteException {
        return null;
    }

    @Override
    public void changePosition(Map<String, Double> newWeight) throws RemoteException {
        try {
            List<FofHoldInfoEntity> fofHoldInfoEntities = fofDataService.getNewestFofHoldInfos
                    (fof_code);
            FofInfoEntity fofInfoEntity = fofDataService.getFofInfoEntity(fof_code);
            double totalValue = fofInfoEntity.getNetAsset();
            List<PositionChangeEntity> positionChangeEntities = new ArrayList<>();
            for (FofHoldInfoEntity fofHoldInfoEntity : fofHoldInfoEntities) {
                String code = fofHoldInfoEntity.getFundId();
                if (newWeight.get(code) == null)
                    continue;
                FundQuickInfosEntity fundQuickInfosEntity = baseInfoDataService.getFundQuickInfo(code);
                double price = fundQuickInfosEntity.getNetWorth();
                double changeWeight = newWeight.get(code) - fofHoldInfoEntity.getRatio() / 100;
                int changeNum = (int) (totalValue * changeWeight / price);
                if (changeNum == 0)
                    continue;
                PositionChangeEntity positionChangeEntity = new PositionChangeEntity();
                positionChangeEntity.setIsHandle(0);
                positionChangeEntity.setChangeDate(LocalDate.now().toString());
                positionChangeEntity.setChangeTime(LocalTime.now().toString());
                positionChangeEntity.setFofCode(fof_code);
                if (changeNum > 0) {
                    positionChangeEntity.setBuyNum(changeNum + 0.0);
                    positionChangeEntity.setBuyPrice(price);
                    positionChangeEntity.setSaleNum(0.0);
                    positionChangeEntity.setSalePrice(0.0);
                } else {
                    positionChangeEntity.setSaleNum(-changeNum + 0.0);
                    positionChangeEntity.setSalePrice(price);
                    positionChangeEntity.setBuyNum(0.0);
                    positionChangeEntity.setBuyPrice(0.0);
                }
                positionChangeEntity.setFundCode(code);
                positionChangeEntity.setFundName(fundQuickInfosEntity.getSimpleName());
                positionChangeEntities.add(positionChangeEntity);
            }
            fofDataService.savePositionChangeEntity(positionChangeEntities);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
