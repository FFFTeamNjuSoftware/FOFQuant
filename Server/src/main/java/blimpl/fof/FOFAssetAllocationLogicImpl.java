package blimpl.fof;

import beans.AssetItem;
import beans.PositionChange;
import bl.fof.FOFAssetAllocationLogic;
import blimpl.Converter;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import exception.ObjectNotFoundException;
import util.FOFUtilInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
    String fof_code;

    private FOFAssetAllocationLogicImpl() throws RemoteException {
        fofDataService = DataServiceController.getFOFDataService();
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
    public List<AssetItem> getFOFAssetAllocation() {
        try {
            return fofDataService.getFOFAssetAllocation(fof_code).stream().map
                    (Converter::convertFOFAssetAllocation).collect(Collectors.toList());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Map<String, Double> getWeights() {
        return null;
    }
}
