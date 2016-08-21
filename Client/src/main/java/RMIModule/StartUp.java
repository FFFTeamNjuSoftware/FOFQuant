package RMIModule;

import com.google.gson.Gson;
import exception.AuthorityException;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import org.dom4j.DocumentException;
import util.SectorType;
import util.UnitType;

import java.rmi.RemoteException;

/**
 * Created by Daniel on 2016/8/15.
 */
public class StartUp {
    public static void main(String[] args) {
        try {
            BLInterfaces.netStart();
            try {
                BLInterfaces.getUserLogic().loginIn("yyf", "123456");
                BLInterfaces.getBaseInfoLogic().getFundQuickInfo(SectorType.STOCK_TYPE).stream()
                        .forEach(e -> System.out.println(new Gson().toJson(e)));
                BLInterfaces.getMarketLogic().getPriceInfo("000001", UnitType.WEEK, 16).stream()
                        .forEach(e -> System.out.println(new Gson().toJson(e)));
                System.out.println("success");
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
                System.out.println("wrong");
            } catch (AuthorityException e) {
                e.printStackTrace();
            } catch (ParameterException e) {

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
