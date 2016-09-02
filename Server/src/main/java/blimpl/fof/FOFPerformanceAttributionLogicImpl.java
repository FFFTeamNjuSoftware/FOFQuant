package blimpl.fof;

import beans.PerformanceAttribution;
import bl.fof.FOFPerformanceAttributionLogic;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import entities.FofHoldInfoEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    private FOFPerformanceAttributionLogicImpl() throws RemoteException {
        fofDataService = DataServiceController.getFOFDataService();
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
            List<String> allCodes = new ArrayList<>();
            Map<String, List<FofHoldInfoEntity>> codeInfo = entities.stream().collect(Collectors
                    .groupingBy(e -> e.getFundId()));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
