package blimpl.fof;

import beans.WarnLog;
import bl.fof.WarnLogLogic;
import blimpl.Converter;
import dataservice.FOFDataService;
import dataserviceimpl.DataServiceController;
import util.FOFUtilInfo;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/9/9.
 */
public class WarnLogLogicImpl implements WarnLogLogic {
    private String fof_code;
    private FOFDataService fofDataService;


    private WarnLogLogicImpl() throws RemoteException {
        fof_code = FOFUtilInfo.FOF_CODE;
        fofDataService = DataServiceController.getFOFDataService();
    }

    private static WarnLogLogic instance;

    public static WarnLogLogic getInstance() {
        if (instance == null)
            try {
                instance = new WarnLogLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public List<WarnLog> getWarnLogs() throws RemoteException {
        return fofDataService.getWarnLogs().stream().map(Converter::convertWarnLogEntity).collect
                (Collectors.toList());
    }

}
