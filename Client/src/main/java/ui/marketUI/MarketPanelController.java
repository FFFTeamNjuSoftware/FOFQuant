package ui.marketUI;

import RMIModule.BLInterfaces;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import javafx.fxml.Initializable;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Created by tj on 2016/8/18.
 */
public class MarketPanelController implements Initializable {
    private BaseInfoLogic baseInfoLogic;
    private MarketLogic marketLogic;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baseInfoLogic = BLInterfaces.getBaseInfoLogic();
        marketLogic = BLInterfaces.getMarketLogic();
        try {
            System.out.println(baseInfoLogic.getFundCodes());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
