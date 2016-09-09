package bl.fof;

import beans.ConstParameter;
import beans.WarnLog;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Daniel on 2016/9/9.
 */
public interface WarnLogLogic extends Remote{
    List<WarnLog> getWarnLogs() throws RemoteException;


}
