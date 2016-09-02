package stubs.logic.fof;

import beans.AssetItem;
import beans.PositionChange;
import bl.fof.FOFAssetAllocationLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/28.
 */
public class FOFAssetAllocationLogicStub extends UnicastRemoteObject implements FOFAssetAllocationLogic {
    public FOFAssetAllocationLogicStub() throws RemoteException {

    }


    @Override
    public List<PositionChange> getFOFPositionChanges() throws RemoteException {
        PositionChange positionChange = new PositionChange();
        positionChange.changeDate = "2016-03-01";
        positionChange.buyNum = 0;
        positionChange.buyPrice = 0;
        positionChange.changeTime = "15:02:01";
        positionChange.fundCode = "000006";
        positionChange.fundName = "测试";
        positionChange.saleNum = 1000;
        positionChange.salePrice = 1.2;
        return Arrays.asList(positionChange);
    }

    @Override
    public List<String> getAllSupportDate() throws RemoteException {
        return Arrays.asList("2016-06-30", "2016-03-30", "2015-12-30");
    }

    @Override
    public void setDate(String date) throws RemoteException {

    }

    @Override
    public List<AssetItem> getFOFAssetAllocation() throws RemoteException {
        AssetItem item = new AssetItem();
        item.code = "000001";
        item.name = "测试";
        item.endHoldNum = 1564;
        item.endNetWorth = 10.5;
        item.endHoldValue = 10023.12;
        item.endHoldRatio = 10.0;
        item.periodProfit = 1235.0;
        item.periodFloatProfit = 1023.4;
        item.unitProfit = 10.2;
        item.periodProfitRate = 5.2;
        return Arrays.asList(item);
    }

    @Override
    public Map<String, Double> getWeights() throws RemoteException {
        Map<String, Double> weights = new HashMap<>();
        weights.put("股票型基金", 20.2);
        weights.put("债券型基金", 50.6);
        weights.put("其他", 29.2);
        return null;
    }
}
