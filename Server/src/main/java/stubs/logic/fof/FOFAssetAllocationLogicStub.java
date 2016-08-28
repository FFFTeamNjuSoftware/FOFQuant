package stubs.logic.fof;

import beans.AssetItem;
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
        item.holdValue = 10023.12;
        item.ratio = 10.0;
        item.totalProfit = 5.2;
        item.totalProfitRate = 30.2;
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
