package RMIModule;

import org.dom4j.DocumentException;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class StartUp {
    public static void main(String[] args) {
        try {
            BLInterfaces.netStart();
            System.out.println(BLInterfaces.getRiskFeature().getBeta("600000"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
