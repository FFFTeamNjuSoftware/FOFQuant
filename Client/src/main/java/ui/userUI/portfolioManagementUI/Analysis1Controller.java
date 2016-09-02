package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FundInFOFQuickInfo;
import beans.FundQuickInfo;
import beans.PerformanceAttribution;
import beans.ProfitChartInfo;
import bl.MarketLogic;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ui.util.InitHelper;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import static util.FOFUtilInfo.performanceBaseInfo;

/**
 * Created by OptimusPrime on 2016/8/29.
 */
public class Analysis1Controller implements Initializable {
	@FXML
	private TableView netWorthTable;
	@FXML
	private Label redLineLb, blueLineLb, greenLineLb, displayNameLb;
	@FXML
	private LineChart netWorthChart;
	@FXML
	private TableColumn<FundInFOFQuickInfo, String> fundCodeCm, fundNameCm, timeCm, fundTypeCm,
			predictRiseCm,predictRiseValueCm,floatProfitCm,floatProfitRatioCm,
			totalProfitCm,totalProfitRatioCm,dayProfitCm,finishedProfitCm;
	@FXML
	private TableColumn<FundInFOFQuickInfo, Number>
			predictNetValueCm,holdNumCm, costCm, holdValueCm, newestWeightCm;
	@FXML
	private ComboBox<String> gradeCb;
	@FXML
	private ComboBox<ChartType> chartCb1;
	@FXML
	private ComboBox<UnitType> chartCb2;
	@FXML
	private ComboBox<TimeType> chartCb3;
	@FXML
	private NumberAxis numAxis;
	@FXML
	private CategoryAxis categoryAxis;
	private Analysis1Controller analysis1Controller;

	private List<FundInFOFQuickInfo> fundInFOFQuickInfoList;
	private List<ProfitChartInfo> profitChartInfoList;

	private FOFRealTimeMonitorLogic fofRealTimeMonitorLogic;
	private MarketLogic marketLogic;

	private UnitType[] unitTypes = {UnitType.DAY, UnitType.WEEK, UnitType.MONTH, UnitType.QUARTER,UnitType.YEAR};
	private TimeType[] timeTypes = {TimeType.ONE_MONTH, TimeType.THREE_MONTH, TimeType.SIX_MONTH, TimeType.ONE_YEAR, TimeType.FIVE_YEAR, TimeType.SIN_THIS_YEAR, TimeType.SINCE_ESTABLISH};
	private ChartType[] chartTypes = {ChartType.NET_WORTH_PERFORMANCE_FQ, ChartType.NET_WORTH_PERFORMANCE_UNIT, ChartType.NET_WORTH_PERFORMANCE_TOTAL};
	private String selectFundcode=null;

	private String greenFill = "-fx-text-fill:#9ac94a;";
	private String redFill = "-fx-text-fill:#eb494d;";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.analysis1Controller = this;
		marketLogic = BLInterfaces.getMarketLogic();
		fofRealTimeMonitorLogic = BLInterfaces.getFofRealTimeMonitorLogic();
		initComboboxes();
		initTable();
//		initNetWorthChart("000001");
	}

	public void initComboboxes() {
//		gradeCombobox init
//		gradeCb.setValue("沪深300");
		gradeCb.setItems(FXCollections.observableArrayList(InitHelper.referType));

		gradeCb.getSelectionModel().selectFirst();
		gradeCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue!=null&&oldValue!=null){
					if (!oldValue.equals(newValue)) {
						getFundInFOFQuickinfo();
						initTable();
					}
				}
			}

		});
//		chartCb1 init
//		chartCb1.setValue(ChartType.NET_WORTH_PERFORMANCE_FQ);
		chartCb1.setItems(FXCollections.observableArrayList(chartTypes));
		chartCb1.getSelectionModel().selectFirst();
		chartCb1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ChartType>() {

			@Override
			public void changed(ObservableValue<? extends ChartType> observable, ChartType oldValue, ChartType newValue) {
				if (!newValue.equals(oldValue)) {
					initNetWorthChart(selectFundcode);
				}
			}
		});

//		chartCb2 init
//		chartCb2.setValue(UnitType.MONTH);
		chartCb2.setItems(FXCollections.observableArrayList(unitTypes));
		chartCb2.getSelectionModel().selectFirst();
		chartCb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UnitType>() {
			@Override
			public void changed(ObservableValue<? extends UnitType> observable, UnitType oldValue, UnitType newValue) {
				if (!oldValue.equals(newValue)) {
					initNetWorthChart(selectFundcode);
				}
			}
		});

//		chartCb3 init
//		chartCb3.setValue(TimeType.ONE_YEAR);
		chartCb3.setItems(FXCollections.observableArrayList(timeTypes));
		chartCb3.getSelectionModel().selectFirst();
		chartCb3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TimeType>() {

			@Override
			public void changed(ObservableValue<? extends TimeType> observable, TimeType oldValue, TimeType newValue) {
				if (!oldValue.equals(newValue)) {
					initNetWorthChart(selectFundcode);
				}
			}
		});
	}

	public void getFundProfitInfoChart(String code) {
		try {
			profitChartInfoList = marketLogic.getFundProfitInfoChart(code, chartCb2.getValue(), chartCb3.getValue(), chartCb1.getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void getFundInFOFQuickinfo() {
		try {
			System.out.println("The gradeCb value:"+gradeCb.getValue());
			fofRealTimeMonitorLogic.setProformanceBase(performanceBaseInfo.get(gradeCb.getValue()));
			fundInFOFQuickInfoList = fofRealTimeMonitorLogic.getFundInFOFQuickinfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		if(fundInFOFQuickInfoList==null){
			System.out.println("get data from server failed!");
		}else{
			System.out.println("fundInfoFOFQuickInfolist Size:"+fundInFOFQuickInfoList.size());
		}
	}

	public void initTable() {
		if (fundInFOFQuickInfoList == null) {
			getFundInFOFQuickinfo();
		} else {
			netWorthTable.setItems(FXCollections.observableArrayList(fundInFOFQuickInfoList));
			netWorthTable.setRowFactory(new Callback<TableView, TableRow>() {
				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(netWorthTable);
				}
			});
			fundCodeCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().fundCode));
			fundNameCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().fundName));
			timeCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().time));
			fundTypeCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().fundType));
			predictRiseValueCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().predictRiseValue+""));
			predictRiseCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().predictRise+"%"));
			predictNetValueCm.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().predictNetValue));
			holdNumCm.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().holdNum));
			costCm.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().cost));
			holdValueCm.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().holdValue));
			newestWeightCm.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().newestWeight));
			dayProfitCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().dayProfit+""));
			floatProfitCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().floatProfit+""));
			floatProfitRatioCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().floatProfitRatio+"%"));
			totalProfitCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().totalProfit+""));
			totalProfitRatioCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().totalProfitRatio+"%"));
			finishedProfitCm.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().finishedProfit+""));
		}
//		TableColumn<FundInFOFQuickInfo,String>[] cs={predictRiseCm,predictRiseValueCm,floatProfitCm,floatProfitRatioCm,totalProfitCm,totalProfitRatioCm,dayProfitCm,finishedProfitCm};
		setColumnColor(predictRiseValueCm);
		setColumnColor(predictRiseCm);
		setColumnColor(floatProfitRatioCm);
		setColumnColor(floatProfitCm);
		setColumnColor(totalProfitRatioCm);
		setColumnColor(totalProfitCm);
		setColumnColor(dayProfitCm);
		setColumnColor(finishedProfitCm);

	}



	private void setColumnColor(TableColumn<FundInFOFQuickInfo, String> c) {
//		for(int i=0;i<cs.length;i++){
//			TableColumn<FundQuickInfo,String> c=cs[i];
			c.setCellFactory(column -> {
				return new TableCell<FundInFOFQuickInfo, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						setGraphic(null);
						setText(empty ? "" : getItem().toString());
						if (!isEmpty()) {
							item = item.split("%")[0];
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
//		}

	}
	public void initNetWorthChart(String fundCode) {
		if(fundCode!=null){
			if(netWorthChart.getData()!=null){
				netWorthChart.getData().clear();
			}
			getFundProfitInfoChart(fundCode);
			XYChart.Series series1 = new XYChart.Series();
			XYChart.Series series2=new XYChart.Series();
			XYChart.Series series3=new XYChart.Series();
			series1.setName("基金");
			series2.setName("基金指数");
			series3.setName("沪深300指数");
			if(profitChartInfoList!=null){
				for (int i = 0; i < profitChartInfoList.size(); i++) {
					series1.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[0]));
					series2.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[1]));
					series3.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[2]));
				}
			}
			netWorthChart.setTitleSide(Side.TOP);
			netWorthChart.setCreateSymbols(false);
			netWorthChart.setAlternativeColumnFillVisible(false);
			netWorthChart.setLegendVisible(false);

			categoryAxis.setTickLabelGap(10);
			categoryAxis.isGapStartAndEnd();
			categoryAxis.setTickMarkVisible(true);
			categoryAxis.setTickLabelRotation(0.5);
			categoryAxis.setTickLabelsVisible(true);
//        categoryAxis.setTickLength(10);

			numAxis.setTickUnit(1);
			numAxis.setForceZeroInRange(false);

			System.out.println();

			netWorthChart.getData().add(0, series1);
			netWorthChart.getData().add(1, series2);
			netWorthChart.getData().add(2, series3);

		}
	}

	public class TableRowControl<T> extends TableRow<T> {

		public TableRowControl(TableView<T> tableView) {
			super();
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
						netWorthChart.getData().clear();
						int selectedIndex=TableRowControl.this.getIndex();
						String fundCode=fundCodeCm.getCellData(selectedIndex);
						String fundName=fundNameCm.getCellData(selectedIndex);
						displayNameLb.setText(fundName+" ( "+fundCode+" ) ");
						redLineLb.setText(fundName);
						initNetWorthChart(fundCode);

//						selectedIndex = TableRowControl.this.getIndex();
//						fundID = codeColumn.getCellData(selectedIndex);
//						fundName = full_nameColumn.getCellData(selectedIndex);
//						fullNameLabel.setText(fundName);
//						fundIDLabel.setText(fundID);
//						lineChart1.getData().clear();
//						lineChart2.getData().clear();
//						initChart1(fundID);
//						initChart2(fundID);
					}
					if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
//						selectedIndex = TableRowControl.this.getIndex();
//						fundId = codeColumn.getCellData(selectedIndex);
//						IOHelper.writeName(fundId);
//						MainUI.getInstance().changeScene("user_guidePanel", "marketPanel");
					}

				}
			});
		}
	}
}
