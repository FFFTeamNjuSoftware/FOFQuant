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
import javafx.scene.chart.*;
import javafx.scene.control.*;
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
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/2.
 */

/**
 * 资产配置-基金走势
 */
public class AssetAllocationController implements Initializable {
    @FXML
    private TableView<ProfitRateInfo4Code> solidTable;
    @FXML
    private TableView<ProfitRateInfo4Code> profitTable;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private AnchorPane basicPane;
    @FXML
    private ComboBox<TimeType> timeTypeBox;
    @FXML
    private ComboBox<UnitType> unitTypeBox;
    @FXML
    private ComboBox<ChartType> chartTypeBox;

    private FOFBaseInfoLogic baseLogic;

    private MarketLogic marketLogic;

    private UnitType unitType = UnitType.WEEK;
    private TimeType timeType = TimeType.ONE_MONTH;
    private ChartType chartType = ChartType.NET_WORTH_PERFORMANCE_FQ;
    private final String greenFill = "-fx-text-fill:#9ac94a;";
    private final String redFill = "-fx-text-fill:#eb494d;";

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
    public String[] names = {"权益类基金", "权益类基金基准", "沪深300指数"};

    /**
     * 储存当前选择的基金信息
     */
    private ProfitRateInfo4Code tempValue;

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
        lineChart.setTitle("基金净值表现");
        initLineChart(null);
    }


    public void initTable(TableView table) {
        ObservableList<TableColumn> columns = table.getColumns();
        for (TableColumn column : columns) {
            String id = column.getId();
            column.setCellValueFactory(
                    new PropertyValueFactory<ProfitRateInfo4Code, String>(id));
            setColumnColor(column);
        }
        profitTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> initLineChart(newValue));
        solidTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> initLineChart(newValue));

    }


    public void initLineChart(ProfitRateInfo4Code value) {
        if (value != null) {
            lineChart.getData().clear();
            this.tempValue = value;
            List<ProfitChartInfo> list = null;
            try {
                list = marketLogic.getFundProfitInfoChart(value.getCode(), unitType, timeType, chartType);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
            if (list != null) {
                for (int i = 0; i < names.length; i++) {
                    String name = names[i];
                    XYChart.Series series = new XYChart.Series();
                    series.setName(name);
                    for (ProfitChartInfo chartInfo : list) {
                        series.getData().add(new XYChart.Data(chartInfo.date, chartInfo.values[i]));
                    }
                    lineChart.getData().add(series);
                }
                NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
                yAxis.setForceZeroInRange(false);
            }
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

    private void addBoxListener(ComboBox box) {
        box.setOnAction((e) -> {
            if (timeTypeBox.getValue() != null) {
                this.timeType = timeTypeBox.getValue();
            }
            if (chartTypeBox.getValue() != null) {
                this.chartType = chartTypeBox.getValue();
            }
            if (unitTypeBox.getValue() != null) {
                this.unitType = unitTypeBox.getValue();
            }
            initLineChart(tempValue);
        });
    }

    private void setColumnColor(TableColumn<ProfitRateInfo4Code, String> c) {
        c.setCellFactory(column -> {
            return new TableCell<ProfitRateInfo4Code, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(null);
                    setText(empty ? "" : getItem().toString());
                    if (!isEmpty()) {
                        if (item.contains("%")) {
                            item = item.substring(0, item.length() - 1);
                        }
                        Double t = Double.parseDouble(item);
                        if (t > 0) {
                            c.setStyle(redFill);
                        } else if (t < 0) {
                            c.setStyle(greenFill);
                        }
                    }
                }
            };
        });
    }
}