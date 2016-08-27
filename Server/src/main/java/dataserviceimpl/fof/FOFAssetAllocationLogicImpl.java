package dataserviceimpl.fof;

import beans.AssetItem;
import bl.fof.FOFAssetAllocationLogic;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFAssetAllocationLogicImpl implements FOFAssetAllocationLogic {
    @Override
    public List<String> getAllSupportDate() throws RemoteException {
        return null;
    }

    @Override
    public void setDate(String date) {

    }

    @Override
    public List<AssetItem> getFOFAssetAllocation() {
        return null;
    }

    @Override
    public Map<String, Double> getWeights() {
        return null;
    }
}
