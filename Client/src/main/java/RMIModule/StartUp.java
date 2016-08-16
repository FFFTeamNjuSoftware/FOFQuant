package RMIModule;

import exception.ObjectNotFoundException;
import org.dom4j.DocumentException;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class StartUp {
    public static void main(String[] args) {
        try {
            BLInterfaces.netStart();
            try {
                BLInterfaces.getUserLogic().loginIn("Buffett", "123456");
                System.out.println("success");
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                System.out.println("wrong");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
