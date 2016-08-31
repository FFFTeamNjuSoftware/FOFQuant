package blimpl.fof;

import beans.AssetItem;
import beans.PositionChange;
import bl.fof.FOFAssetAllocationLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFAssetAllocationLogicImpl extends UnicastRemoteObject implements
        FOFAssetAllocationLogic {
    private FOFAssetAllocationLogicImpl() throws RemoteException {

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
        return null;
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
