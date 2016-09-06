package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import beans.ProfitRateInfo4Code;
import bl.MarketLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import ui.util.LineChartGenerator;
import util.ChartType;
import util.FOFUtilInfo;
import util.TimeType;
import util.UnitType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/2.
 */

/**
 * 资产配置-基金走势
 */
public class AssetAllocationController implements Initializable {
    @FXML
    private TableView solidTable;
    @FXML
    private TableView profitTable;
    @FXML
    private AnchorPane chartPane;
    @FXML
    private ComboBox<TimeType> timeTypeBox;
    @FXML
    private ComboBox<UnitType> unitTypeBox;
    @FXML
    private ComboBox<ChartType> chartTypeBox;

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
    public static final String[] names = {"权益类基金", "权益类基金基准", "沪深300指数"};

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
        chartTypeBox.getItems().addAll(ChartType.values());
        unitTypeBox.getItems().addAll(UnitType.values());
        timeTypeBox.getItems().addAll(TimeType.values());
        addBoxListener(timeTypeBox);
        addBoxListener(unitTypeBox);
        addBoxListener(chartTypeBox);
        initLineChart(UnitType.WEEK, TimeType.ONE_MONTH, ChartType.NET_WORTH_PERFORMANCE_FQ);
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

    public void initLineChart(UnitType unitType, TimeType timeType, ChartType chartType) {
        chartPane.getChildren().clear();
        LineChartGenerator generator = new LineChartGenerator(chartPane, names);
        List<ProfitChartInfo> list = null;
        try {
            list = marketLogic.getFundProfitInfoChart(profitFundKey, unitType, timeType, chartType);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (list != null) {
            String[] year = new String[list.size()];
            double[][] nums = new double[3][list.size()];

            for (int i = 0; i < list.size(); i++) {
                ProfitChartInfo info = list.get(i);
                year[i] = info.date;
            }

            for (int i = 0; i < list.size(); i++) {
                ProfitChartInfo info = list.get(i);
                for (int j = 0; j < 3; j++) {
                    nums[j][i] = info.values[j];
                }
            }
            generator.setData(year, nums);
        }
    }

    public void addBoxListener(ComboBox box) {
        box.setOnAction((e) -> {
            refreshBox();
        });
    }

    public void refreshBox() {
        TimeType tt = timeTypeBox.getValue();
        ChartType ct = chartTypeBox.getValue();
        UnitType ut = unitTypeBox.getValue();
        if (tt != null && ct != null && ut != null) {
            initLineChart(ut, tt, ct);
        }
    }

}