package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.ProfitRateInfo;
import beans.ProfitRateInfo4Code;
import bl.MarketLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/2.
 */
public class AssetAllocationController implements Initializable {
    @FXML
    private Label combinationTab;
    @FXML
    private Label fundTrendTab;
    @FXML
    private AnchorPane basicPane;
    @FXML
    private TableView solidTable;
    @FXML
    private TableView profitTable;

    private FOFBaseInfoLogic baseLogic;

    private MarketLogic marketLogic;
    /**
     * 权益类
     */
    private ObservableList<ProfitRateInfo4Code> profitDatas =
            FXCollections.observableArrayList();
    /**
     * 固定收益类
     */
    private ObservableList<ProfitRateInfo4Code> solidDatas =
            FXCollections.observableArrayList();

    public static String profitFundKey = "000011";
    public static String solidProfitKey = "000012";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baseLogic = BLInterfaces.getFofBaseInfoLogic();
        marketLogic = BLInterfaces.getMarketLogic();
        generateTableInfo(profitFundKey);
        generateTableInfo(solidProfitKey);
        initTable(solidTable);
        initTable(profitTable);
        solidTable.setItems(solidDatas);
        profitTable.setItems(profitDatas);
    }


    public void initTable(TableView table) {
        ObservableList<TableColumn> columns = table.getColumns();
        for (TableColumn column : columns) {
            String id = column.getId();
            column.setCellValueFactory(
                    new PropertyValueFactory<ProfitRateInfo4Code, String>(id));
        }
    }

    /**
     * 把profitRateInfo加上code和name
     */
    private void generateTableInfo(String key) {
        List<String> list = null;
        try {
            list = baseLogic.getFundCodeInFOF().get(key);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (String code : list) {
                ProfitRateInfo info = null;
                try {
                    info = marketLogic.getProfitRateInfo(code);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (ObjectNotFoundException e) {
                    e.printStackTrace();
                }
                if (info != null) {
                    ProfitRateInfo4Code info4Code = new ProfitRateInfo4Code(info, code, "");
                    if (key.equals(solidProfitKey)) {
                        profitDatas.add(info4Code);
                    } else {
                        solidDatas.add(info4Code);
                    }
                }
            }
        }
    }
}