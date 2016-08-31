package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FundInFOFQuickInfo;
import beans.ProfitChartInfo;
import bl.MarketLogic;
import bl.fof.FOFRealTimeMonitorLogic;
import exception.ObjectNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.util.InitHelper;
import util.TimeType;
import util.UnitType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static util.FOFUtilInfo.performanceBaseInfo;

/**
 * Created by OptimusPrime on 2016/8/29.
 */
public class Analysis1Controller implements Initializable {
	@FXML
	private TableView netWorthTable;
	@FXML
	private Label redLineLb,blueLineLb,greenLineLb,displayNameLb;
	@FXML
	private LineChart netWorthChart;
	@FXML
	private TableColumn<FundInFOFQuickInfo,String> categoryCm,codeCm,abbreviationCm;
	@FXML
	private TableColumn<FundInFOFQuickInfo,Number> latestPriceCm,changeCm,quoteChangeCm,positionNumCm,positionValueCm,latestWeightCm,nowBreakevenCm,floatCm;
	@FXML
	private ComboBox<String> gradeCb,chartCb1;
	@FXML
	private ComboBox<UnitType> chartCb2;
	@FXML
	private ComboBox<TimeType> chartCb3;
	@FXML
	private NumberAxis numberAxis;
	@FXML
	private CategoryAxis categoryAxis;
	private Analysis1Controller analysis1Controller;

	private List<FundInFOFQuickInfo> fundInFOFQuickInfoList;
	private List<ProfitChartInfo> profitChartInfoList;

	private FOFRealTimeMonitorLogic fofRealTimeMonitorLogic;
	private MarketLogic marketLogic;

	private UnitType[] unitTypes={UnitType.DAY,UnitType.WEEK,UnitType.MONTH,UnitType.QUARTER,UnitType.QUARTER,UnitType.YEAR};
	private TimeType[] timeTypes={TimeType.ONE_MONTH,TimeType.THREE_MONTH,TimeType.SIS_MONTH,TimeType.ONE_YEAR,TimeType.FIVE_YEAR,TimeType.SIN_THIS_YEAR,TimeType.SINCE_ESTABLISH};
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.analysis1Controller=this;
		marketLogic=BLInterfaces.getMarketLogic();
		fofRealTimeMonitorLogic= BLInterfaces.getFofRealTimeMonitorLogic();
	}

	public void initNetWorthChart(){

	}
	public void initComboboxes(){
//		gradeCombobox init
		gradeCb.setItems(FXCollections.observableArrayList(InitHelper.referType));
		gradeCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!oldValue.equals(newValue)) {
				}
			}

		});
		gradeCb.getSelectionModel().selectFirst();
//		chartCb1 init

//		chartCb2 init
		chartCb2.setItems(FXCollections.observableArrayList(unitTypes));
		chartCb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UnitType>() {
			@Override
			public void changed(ObservableValue<? extends UnitType> observable, UnitType oldValue, UnitType newValue) {
				if(!oldValue.equals(newValue)){

				}
			}
		});
		chartCb2.getSelectionModel().selectFirst();

//		chartCb3 init
		chartCb3.setItems(FXCollections.observableArrayList(timeTypes));
		chartCb3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TimeType>() {

			@Override
			public void changed(ObservableValue<? extends TimeType> observable, TimeType oldValue, TimeType newValue) {

			}
		});
		chartCb3.getSelectionModel().selectFirst();

	}

	public void initLabel(){}

	public void initNetWorthTable(){

	}
}
