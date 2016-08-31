package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import beans.FOFQuickInfo;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ScrollEvent;
import ui.util.InitHelper;
import util.CalendarOperate;
import util.TimeType;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static util.FOFUtilInfo.performanceBaseInfo;

/**
 * Created by OptimusPrime on 2016/8/28.
 */
public class Analysis2Controller implements Initializable {
	@FXML
	private ComboBox<Date> startCb, endCb;
	@FXML
	private ComboBox<String> gradeCb;
	@FXML
	private Label startDateLb, endDateLb;
	private Analysis2Controller analysis2Controller;
	private FOFProfitAnalyseLogic profitAnalyseLogic;
	private FOFProfitAnalyse profitAnalyse_three, profitAnalyse_half, profitAnalyse_year, profitAnalyse_establish;
	@FXML
	private TableView table1, table2;
	@FXML
	private TableColumn table1column1, table1column2, table1column3, table1column4, table2column1, table2column2, table2column3, table2column4;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.analysis2Controller = this;
		this.profitAnalyseLogic = BLInterfaces.getFofProfitAnalyseLogic();
		table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table1.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (event.getDeltaX() != 0) {
					event.consume();
				}
			}
		});
		table2.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (event.getDeltaX() != 0) {
					event.consume();
				}
			}
		});
		initComboboxes();
		initTable();
	}

	public void initComboboxes() {
//		this gradeCombobox
		gradeCb.setItems(FXCollections.observableArrayList(InitHelper.referType));
		gradeCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!oldValue.equals(newValue)) {
					String indexCode = performanceBaseInfo.get(newValue);
					try {
						profitAnalyseLogic.setProformanceBase(indexCode);
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ObjectNotFoundException e) {
						e.printStackTrace();
					}
					initTable();
				}
			}

		});
		gradeCb.getSelectionModel().selectFirst();


		LocalDateTime nowLocalDate = LocalDateTime.now();
//		init startCombobox
		LocalDateTime startTempDate = nowLocalDate.minusDays(90);
		List<Date> startDateList = new ArrayList<Date>();
		int i = 0;
		do {
			startDateList.add(Date.from(startTempDate.atZone(ZoneId.systemDefault()).toInstant()));
			startTempDate = startTempDate.plusDays(1);
			i++;
		} while (i < 30);
		startCb.setItems(FXCollections.observableArrayList(startDateList));
		startCb.getSelectionModel().selectFirst();
		startCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Date>() {
			@Override
			public void changed(ObservableValue<? extends Date> observable, Date oldValue, Date newValue) {
				if (!oldValue.toString().equals(newValue.toString())) {
					try {
						profitAnalyseLogic.setStartDate(newValue.toString());
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ParameterException e) {
						e.printStackTrace();
					}
					initTable();
				}
			}
		});

//		init endCombobox
		List<Date> endDateList = new ArrayList<Date>();
		LocalDateTime endTempDate = nowLocalDate;
		int j = 0;
		do {
			endDateList.add(Date.from(endTempDate.atZone(ZoneId.systemDefault()).toInstant()));
			endTempDate = endTempDate.minusDays(1);
			j++;
		} while (j < 30);
		endCb.setItems(FXCollections.observableArrayList(endDateList));
		endCb.getSelectionModel().selectFirst();
		endCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Date>() {


			@Override
			public void changed(ObservableValue<? extends Date> observable, Date oldValue, Date newValue) {
				if (!oldValue.toString().equals(newValue.toString())) {
					try {
						profitAnalyseLogic.setEndDate(newValue.toString());
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ParameterException e) {
						e.printStackTrace();
					}
					initTable();
				}
			}
		});
	}

	@FXML
	public void combinationLb1Click() {

	}

	@FXML
	public void combinationLb2Click() {

	}

	public void initTable() {

		try {
			profitAnalyse_three = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.THREE_MONTH);
			profitAnalyse_half = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIS_MONTH);
			profitAnalyse_year = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIN_THIS_YEAR);
			profitAnalyse_establish = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SINCE_ESTABLISH);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		table1.setItems(FXCollections.observableArrayList(new changePositionUIController.TableData(
				profitAnalyse_three.totalProfit, profitAnalyse_half.totalProfit, profitAnalyse_year.totalProfit, profitAnalyse_establish.totalProfit), new changePositionUIController.TableData(
				profitAnalyse_three.relatedTotalProfit, profitAnalyse_half.relatedTotalProfit, profitAnalyse_year.relatedTotalProfit, profitAnalyse_establish.relatedTotalProfit), new changePositionUIController.TableData(
				profitAnalyse_three.maxRise, profitAnalyse_half.maxRise, profitAnalyse_year.maxRise, profitAnalyse_establish.maxRise), new changePositionUIController.TableData(
				profitAnalyse_three.maxRiseDays + 0.0, profitAnalyse_half.maxRiseDays + 0.0, profitAnalyse_year.maxRiseDays + 0.0, profitAnalyse_establish.maxRiseDays + 0.0), new changePositionUIController.TableData(
				profitAnalyse_three.maxRiseRecoverDays + 0.0, profitAnalyse_half.maxRiseRecoverDays + 0.0, profitAnalyse_year.maxRiseRecoverDays + 0.0, profitAnalyse_establish.maxRiseRecoverDays + 0.0), new changePositionUIController.TableData(
				profitAnalyse_three.minRise, profitAnalyse_half.minRise, profitAnalyse_year.minRise, profitAnalyse_establish.minRise), new changePositionUIController.TableData(
				profitAnalyse_three.minRiseDays + 0.0, profitAnalyse_half.minRiseDays + 0.0, profitAnalyse_year.minRiseDays + 0.0, profitAnalyse_establish.minRiseDays + 0.0), new changePositionUIController.TableData(
				profitAnalyse_three.minRiseRecoverDays + 0.0, profitAnalyse_half.minRiseRecoverDays + 0.0, profitAnalyse_year.minRiseRecoverDays + 0.0, profitAnalyse_establish.minRiseRecoverDays + 0.0), new changePositionUIController.TableData(
				profitAnalyse_three.yearProfitRate, profitAnalyse_half.yearProfitRate, profitAnalyse_year.yearProfitRate, profitAnalyse_establish.yearProfitRate), new changePositionUIController.TableData(
				profitAnalyse_three.yearRelatedProfitRate, profitAnalyse_half.yearRelatedProfitRate, profitAnalyse_year.yearRelatedProfitRate, profitAnalyse_establish.yearRelatedProfitRate), new changePositionUIController.TableData(
				profitAnalyse_three.downsideRisk, profitAnalyse_half.downsideRisk, profitAnalyse_year.downsideRisk, profitAnalyse_establish.downsideRisk)));

		table1column1.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("one"));
		table1column2.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("two"));
		table1column3.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("three"));
		table1column4.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("four"));

		table2.setItems(FXCollections.observableArrayList(new changePositionUIController.TableData(
				profitAnalyse_three.yearWaveRate, profitAnalyse_half.yearWaveRate, profitAnalyse_year.yearWaveRate, profitAnalyse_establish.yearWaveRate), new changePositionUIController.TableData(
				profitAnalyse_three.trackingError, profitAnalyse_half.trackingError, profitAnalyse_year.trackingError, profitAnalyse_establish.trackingError), new changePositionUIController.TableData(
				profitAnalyse_three.correlationCoefficent, profitAnalyse_half.correlationCoefficent, profitAnalyse_year.correlationCoefficent, profitAnalyse_establish.correlationCoefficent), new changePositionUIController.TableData(
				profitAnalyse_three.alpha, profitAnalyse_half.alpha, profitAnalyse_year.alpha, profitAnalyse_establish.alpha), new changePositionUIController.TableData(
				profitAnalyse_three.beta, profitAnalyse_half.beta, profitAnalyse_year.beta, profitAnalyse_establish.beta), new changePositionUIController.TableData(
				profitAnalyse_three.sharpe, profitAnalyse_half.sharpe, profitAnalyse_year.sharpe, profitAnalyse_establish.sharpe), new changePositionUIController.TableData(
				profitAnalyse_three.treynor, profitAnalyse_half.treynor, profitAnalyse_year.treynor, profitAnalyse_establish.treynor), new changePositionUIController.TableData(
				profitAnalyse_three.Jensen, profitAnalyse_half.Jensen, profitAnalyse_year.Jensen, profitAnalyse_establish.Jensen), new changePositionUIController.TableData(
				profitAnalyse_three.R2, profitAnalyse_half.R2, profitAnalyse_year.R2, profitAnalyse_establish.R2), new changePositionUIController.TableData(
				profitAnalyse_three.semiVariance, profitAnalyse_half.semiVariance, profitAnalyse_year.semiVariance, profitAnalyse_establish.semiVariance), new changePositionUIController.TableData(
				profitAnalyse_three.sortino, profitAnalyse_half.sortino, profitAnalyse_year.sortino, profitAnalyse_establish.sortino)));

		table2column1.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("one"));
		table2column2.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("two"));
		table2column3.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("three"));
		table2column4.setCellValueFactory(new PropertyValueFactory<changePositionUIController.TableData, Double>("four"));


	}

	public static class TableData {
		SimpleDoubleProperty one, two, three, four;

		public TableData(Double one, Double two, Double three, Double four) {
			this.one = new SimpleDoubleProperty(one);
			this.two = new SimpleDoubleProperty(two);
			this.three = new SimpleDoubleProperty(three);
			this.four = new SimpleDoubleProperty(four);
		}

		public Double getOne() {
			return one.get();
		}

		public void setOne(Double one) {
			this.one.set(one);
		}

		public Double getThree() {
			return three.get();
		}

		public void setThree(Double three) {
			this.three.set(three);
		}

		public Double getTwo() {
			return two.get();
		}

		public void setTwo(Double two) {
			this.two.set(two);
		}

		public Double getFour() {
			return two.get();
		}

		public void setFour(Double four) {
			this.two.set(four);
		}

	}


}
