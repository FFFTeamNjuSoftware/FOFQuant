package startup;

import RMIModule.NetInitial;
import org.dom4j.DocumentException;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class Main {
    public static void main(String []args){
        try {
            NetInitial.initial();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
