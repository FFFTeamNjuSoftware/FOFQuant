package ui.userUI.riskControlUI;

import RMIModule.BLInterfaces;
import beans.PriceInfo;
import beans.WarnLog;
import bl.MarketLogic;
import bl.fof.FOFBaseInfoLogic;
import bl.fof.WarnLogLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import ui.util.LineChartGenerator;
import util.FOFUtilInfo;
import util.UnitType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/9.
 */
public class RiskControlPanelController implements Initializable {
    @FXML
    private AnchorPane fofPane;
    @FXML
    private AnchorPane marketPane;
    @FXML
    private TableView<WarnLog> riskTable;
    @FXML
    private TableColumn<WarnLog, String> riskColumn;
    @FXML
    private TableColumn<WarnLog, Number> profitRateColumn;
    @FXML
    private TableColumn<WarnLog, Number> profitColumn;
    @FXML
    private TableColumn<WarnLog, String> dateColumn;
    @FXML
    private LineChart<String, Number> marketChart;
    @FXML
    private LineChart<String, Number> fofChart;
    private WarnLogLogic warnLogic;
    private MarketLogic marketLogic;
    private FOFBaseInfoLogic fofBaseLogic;

    private static final String fundType = "上证基金指数";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warnLogic = BLInterfaces.getWarnLogLogic();
        marketLogic = BLInterfaces.getMarketLogic();
        fofBaseLogic = BLInterfaces.getFofBaseInfoLogic();
        initTable();
        String code = FOFUtilInfo.performanceBaseInfo.get(fundType);
        List<PriceInfo> marketData = null;
        List<PriceInfo> fofData = null;
        try {
            marketData = marketLogic.getPriceInfo(code,UnitType.MONTH);
            fofData = fofBaseLogic.getFOFPriceInfos();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        initChart(marketData,marketChart,"历史收盘价折线图","上证指数历史首盘点位与设定的阈值线",marketPane);
        initChart(fofData,fofChart,"历史收益率折线图","Fof组合",fofPane);
    }

    public void initTable() {
        List<WarnLog> list = null;
        try {
            list = warnLogic.getWarnLogs();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (list != null) {
            riskTable.setItems(FXCollections.observableArrayList(list));
            riskColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().warnInfo));
            profitRateColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                    cellData.getValue().totalProfit));
            profitColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                    cellData.getValue().netWorth));
            dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                    cellData.getValue().date));
        }
    }

    public void initChart(List<PriceInfo> list,LineChart lineChart,String title,String seriesName,AnchorPane pane) {
        if (list != null) {
            lineChart.setTitle(title);
            XYChart.Series series = new XYChart.Series();
            for (PriceInfo info : list) {
                if(title.equals("历史收盘价折线图")) {
                    series.getData().add(new XYChart.Data(info.date, info.rise));
                }else{
                    series.getData().add(new XYChart.Data(info.date, info.total_netWorth));
                }
            }
            series.setName(seriesName);
            lineChart.getData().add(series);
            LineChartGenerator generator = new LineChartGenerator();
            generator.simpleSetupHover(lineChart, pane);
            NumberAxis yAxis = (NumberAxis) lineChart.getYAxis();
            yAxis.setForceZeroInRange(false);
        }
    }
}
