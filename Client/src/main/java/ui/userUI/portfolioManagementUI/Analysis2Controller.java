package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import beans.FOFQuickInfo;
import beans.FundInFOFQuickInfo;
import bl.fof.FOFBaseInfoLogic;
import bl.fof.FOFProfitAnalyseLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
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
 * 盈亏分析
 */
public class Analysis2Controller implements Initializable {
	@FXML
	private ComboBox<LocalDate> startCb, endCb;
	@FXML
	private ComboBox<String> gradeCb;
	@FXML
	private Label startDateLb, endDateLb;
	private Analysis2Controller analysis2Controller;
	private FOFProfitAnalyseLogic profitAnalyseLogic;
	private FOFBaseInfoLogic fofBaseInfoLogic;
	private FOFProfitAnalyse profitAnalyse_three, profitAnalyse_half, profitAnalyse_year, profitAnalyse_establish;
	@FXML
	private TableView table1, table2;
	@FXML
	private TableColumn table1column1, table1column2, table1column3, table1column4, table2column1, table2column2, table2column3, table2column4;
	private FOFQuickInfo fofQuickInfo;
	private LocalDate oldStartDate = LocalDate.now().plusDays(1), oldEndDate = LocalDate.now().plusDays(1);
	private String oldGrade = "";


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.analysis2Controller = this;
		this.profitAnalyseLogic = BLInterfaces.getFofProfitAnalyseLogic();
		this.fofBaseInfoLogic = BLInterfaces.getFofBaseInfoLogic();


//		table1.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
//			@Override
//			public void handle(ScrollEvent event) {
//				if (event.getDeltaX() != 0) {
//					event.consume();
//				}
//			}
//		});
//		table2.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
//			@Override
//			public void handle(ScrollEvent event) {
//				if (event.getDeltaX() != 0) {
//					event.consume();
//				}
//			}
//		});
		initComboboxes();
		initTable();
	}

	public void initComboboxes() {
//		this gradeCombobox
		gradeCb.setItems(FXCollections.observableArrayList(InitHelper.referType));
		gradeCb.getSelectionModel().selectFirst();
		gradeCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!oldValue.equals(newValue)) {
					try {
						profitAnalyseLogic.setProformanceBase(performanceBaseInfo.get(newValue));
						initTable();
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ObjectNotFoundException e) {
						e.printStackTrace();
					}
				}
			}

		});
//		init startCombobox
		try {
			fofQuickInfo = fofBaseInfoLogic.getFOFQuickInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		LocalDate nowDate = LocalDate.now();
		LocalDate nowLocalDate = LocalDate.parse(fofQuickInfo.establish_date);
		LocalDate startTempDate = nowLocalDate;
		List<LocalDate> startDateList = new ArrayList<LocalDate>();
		do {
			startDateList.add(startTempDate);
			startTempDate = startTempDate.plusDays(1);
		} while (startTempDate.isBefore(nowDate));
		startCb.setItems(FXCollections.observableArrayList(startDateList));
		startCb.getSelectionModel().selectFirst();
		startCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				try {
					profitAnalyseLogic.setStartDate(newValue.toString());
					initTable();
				} catch (ParameterException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});


//		init endCombobox
		List<LocalDate> endDateList = startDateList;
		Collections.reverse(endDateList);

		endCb.setItems(FXCollections.observableArrayList(endDateList));
		endCb.getSelectionModel().selectFirst();
		endCb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				try {
					profitAnalyseLogic.setEndDate(newValue.toString());
					initTable();
				} catch (ParameterException e) {
					e.printStackTrace();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void initTable() {
		if (table1.getItems() != null) {
			table1.getItems().clear();
		}
		if (table2.getItems() != null) {
			table2.getItems().clear();
		}
		if (!oldGrade.equals(gradeCb.getValue())) {
			try {
				profitAnalyseLogic.setProformanceBase(performanceBaseInfo.get(gradeCb.getValue()));
				oldGrade = gradeCb.getValue();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (ObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (!startCb.getValue().isEqual(oldStartDate)) {
			try {
				profitAnalyseLogic.setStartDate(startCb.getValue().toString());
				oldStartDate = startCb.getValue();
			} catch (ParameterException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (!endCb.getValue().isEqual(oldEndDate)) {
			try {
				profitAnalyseLogic.setEndDate(endCb.getValue().toString());
				oldEndDate = endCb.getValue();
			} catch (ParameterException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		try {
			profitAnalyse_three = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.THREE_MONTH);
			profitAnalyse_half = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIX_MONTH);
			profitAnalyse_year = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIN_THIS_YEAR);
			profitAnalyse_establish = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SINCE_ESTABLISH);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("profitAnalyse_three.test:" + profitAnalyse_three.alpha + "--" + LocalDateTime.now());
		table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table2.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		table1.setItems(FXCollections.observableArrayList(
				new TableData(
						profitAnalyse_three.totalProfit + "%", profitAnalyse_half.totalProfit + "%", profitAnalyse_year.totalProfit + "%", profitAnalyse_establish.totalProfit + "%"),
				new TableData(
						profitAnalyse_three.relatedTotalProfit + "%", profitAnalyse_half.relatedTotalProfit + "%", profitAnalyse_year.relatedTotalProfit + "%", profitAnalyse_establish.relatedTotalProfit + "%"),
				new TableData(
						profitAnalyse_three.maxRise + "%", profitAnalyse_half.maxRise + "%", profitAnalyse_year.maxRise + "%", profitAnalyse_establish.maxRise + "%"),
				new TableData(
						profitAnalyse_three.maxRiseDays + 0.0 + "", profitAnalyse_half.maxRiseDays + 0.0 + "", profitAnalyse_year.maxRiseDays + 0.0 + "", profitAnalyse_establish.maxRiseDays + 0.0 + ""),
				new TableData(
						profitAnalyse_three.maxRiseRecoverDays + 0.0 + "", profitAnalyse_half.maxRiseRecoverDays + 0.0 + "", profitAnalyse_year.maxRiseRecoverDays + 0.0 + "", profitAnalyse_establish.maxRiseRecoverDays + 0.0 + ""),
				new TableData(
						profitAnalyse_three.minRise + "%", profitAnalyse_half.minRise + "%", profitAnalyse_year.minRise + "%", profitAnalyse_establish.minRise + "%"),
				new TableData(
						profitAnalyse_three.minRiseDays + 0.0 + "", profitAnalyse_half.minRiseDays + 0.0 + "", profitAnalyse_year.minRiseDays + 0.0 + "", profitAnalyse_establish.minRiseDays + 0.0 + ""),
				new TableData(
						profitAnalyse_three.minRiseRecoverDays + 0.0 + "", profitAnalyse_half.minRiseRecoverDays + 0.0 + "", profitAnalyse_year.minRiseRecoverDays + 0.0 + "", profitAnalyse_establish.minRiseRecoverDays + 0.0 + ""),
				new TableData(
						profitAnalyse_three.yearProfitRate + "%", profitAnalyse_half.yearProfitRate + "%", profitAnalyse_year.yearProfitRate + "%", profitAnalyse_establish.yearProfitRate + "%"),
				new TableData(
						profitAnalyse_three.yearRelatedProfitRate + "%", profitAnalyse_half.yearRelatedProfitRate + "%", profitAnalyse_year.yearRelatedProfitRate + "%", profitAnalyse_establish.yearRelatedProfitRate + "%"),
				new TableData(
						profitAnalyse_three.downsideRisk + "", profitAnalyse_half.downsideRisk + "", profitAnalyse_year.downsideRisk + "", profitAnalyse_establish.downsideRisk + "")));

		table1column1.setCellValueFactory(new PropertyValueFactory<TableData, String>("one"));
		table1column2.setCellValueFactory(new PropertyValueFactory<TableData, String>("two"));
		table1column3.setCellValueFactory(new PropertyValueFactory<TableData, String>("three"));
		table1column4.setCellValueFactory(new PropertyValueFactory<TableData, String>("four"));

		table2.setItems(FXCollections.observableArrayList(
				new TableData(
						profitAnalyse_three.yearWaveRate + "%", profitAnalyse_half.yearWaveRate + "%", profitAnalyse_year.yearWaveRate + "%", profitAnalyse_establish.yearWaveRate + "%"),
				new TableData(
						profitAnalyse_three.trackingError + "", profitAnalyse_half.trackingError + "", profitAnalyse_year.trackingError + "", profitAnalyse_establish.trackingError + ""),
				new TableData(
						profitAnalyse_three.correlationCoefficent + "", profitAnalyse_half.correlationCoefficent + "", profitAnalyse_year.correlationCoefficent + "", profitAnalyse_establish.correlationCoefficent + ""),
				new TableData(
						profitAnalyse_three.alpha + "", profitAnalyse_half.alpha + "", profitAnalyse_year.alpha + "", profitAnalyse_establish.alpha + ""),
				new TableData(
						profitAnalyse_three.beta + "", profitAnalyse_half.beta + "", profitAnalyse_year.beta + "", profitAnalyse_establish.beta + ""),
				new TableData(
						profitAnalyse_three.sharpe + "", profitAnalyse_half.sharpe + "", profitAnalyse_year.sharpe + "", profitAnalyse_establish.sharpe + ""),
				new TableData(
						profitAnalyse_three.treynor + "", profitAnalyse_half.treynor + "", profitAnalyse_year.treynor + "", profitAnalyse_establish.treynor + ""),
				new TableData(
						profitAnalyse_three.Jensen + "", profitAnalyse_half.Jensen + "", profitAnalyse_year.Jensen + "", profitAnalyse_establish.Jensen + ""),
				new TableData(
						profitAnalyse_three.R2 + "", profitAnalyse_half.R2 + "", profitAnalyse_year.R2 + "", profitAnalyse_establish.R2 + ""),
				new TableData(
						profitAnalyse_three.semiVariance + "", profitAnalyse_half.semiVariance + "", profitAnalyse_year.semiVariance + "", profitAnalyse_establish.semiVariance + ""),
				new TableData(
						profitAnalyse_three.sortino + "", profitAnalyse_half.sortino + "", profitAnalyse_year.sortino + "", profitAnalyse_establish.sortino + "")));

		table2column1.setCellValueFactory(new PropertyValueFactory<TableData, String>("one"));
		table2column2.setCellValueFactory(new PropertyValueFactory<TableData, String>("two"));
		table2column3.setCellValueFactory(new PropertyValueFactory<TableData, String>("three"));
		table2column4.setCellValueFactory(new PropertyValueFactory<TableData, String>("four"));
		TableColumn[] columns = new TableColumn[]{table1column1, table1column2, table1column3, table1column4, table2column1, table2column2, table2column3, table2column4};
		setColumnSortable(columns, false);

	}

	private void setColumnSortable(TableColumn[] columns, boolean t) {
		for (int i = 0; i < columns.length; i++) {
			columns[i].setSortable(t);
		}
	}

	public static class TableData {
		SimpleStringProperty one, two, three, four;

		public TableData(String one, String two, String three, String four) {
			this.one = new SimpleStringProperty(one);
			this.two = new SimpleStringProperty(two);
			this.three = new SimpleStringProperty(three);
			this.four = new SimpleStringProperty(four);
		}

		public String getOne() {
			return one.get();
		}

		public SimpleStringProperty oneProperty() {
			return one;
		}

		public void setOne(String one) {
			this.one.set(one);
		}

		public String getTwo() {
			return two.get();
		}

		public SimpleStringProperty twoProperty() {
			return two;
		}

		public void setTwo(String two) {
			this.two.set(two);
		}

		public String getThree() {
			return three.get();
		}

		public SimpleStringProperty threeProperty() {
			return three;
		}

		public void setThree(String three) {
			this.three.set(three);
		}

		public String getFour() {
			return four.get();
		}

		public SimpleStringProperty fourProperty() {
			return four;
		}

		public void setFour(String four) {
			this.four.set(four);
		}
	}


}
