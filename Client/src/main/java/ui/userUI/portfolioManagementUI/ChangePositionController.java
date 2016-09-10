package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFQuickInfo;
import beans.FundInfo;
import bl.BaseInfoLogic;
import bl.fof.FOFAssetAllocationLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import starter.MainUI;
import ui.userUI.allFundUI.allFundUIController;
import ui.util.DisplayType;
import ui.util.IOHelper;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by OptimusPrime on 2016/9/4.
 * 仓位调整
 */
public class ChangePositionController implements Initializable {
	@FXML
	private ImageView completedBt;
	@FXML
	private ImageView backImage;
	@FXML
	private TableView changeTable1, changeTable2;
	@FXML
	private TableColumn<DisplayType, String> codeCm1, codeCm2, nameCm1, nameCm2;
	@FXML
	private TableColumn sliderCm1, sliderCm2;
	@FXML
	private TableColumn<DisplayType, String> ratioCm1, ratioCm2;
	@FXML
	private Slider slider1,slider2;
	@FXML
	private Label ratioLb1,ratioLb2;

	private ChangePositionController changePositionCotroller;
	private FOFBaseInfoLogic fofBaseInfoLogic;
	private BaseInfoLogic baseInfoLogic;
	private FOFAssetAllocationLogic fofAssetAllocationLogic;
	private Map<String, Map<String, Double>> weightMap;
	private List<DisplayType> displayTypeList1,displayTypeList2;
	private Map<String,Double> displayMap1,displayMap2,allDisplayMap;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.changePositionCotroller = this;
		this.baseInfoLogic=BLInterfaces.getBaseInfoLogic();
		this.fofBaseInfoLogic = BLInterfaces.getFofBaseInfoLogic();
		this.fofAssetAllocationLogic = BLInterfaces.getFofAssetAllocationLogic();
		initButton();
		initTable();
		initOthers();
	}

	private void initOthers() {
		slider1.setMin(0);
		slider1.setMax(1);
		slider1.setShowTickLabels(false);
		slider1.setShowTickMarks(false);
		slider1.setMajorTickUnit(50);
		slider1.setMinorTickCount(100);
		slider1.setBlockIncrement(0.0001);
		slider1.setDisable(true);
		ratioLb1.textProperty().bindBidirectional(slider1.valueProperty(),new DecimalFormat("#.##%"));

		slider2.setMin(0);
		slider2.setMax(1);
		slider2.setShowTickLabels(false);
		slider2.setShowTickMarks(false);
		slider2.setMajorTickUnit(50);
		slider2.setMinorTickCount(100);
		slider2.setBlockIncrement(0.0001);
		slider2.setDisable(true);
		ratioLb2.textProperty().bindBidirectional(slider2.valueProperty(),new DecimalFormat("#.##%"));

	}
	public void refreshSlider1(){
		double sum=0;
		List<DisplayType> columList=changeTable1.getItems();
		for(int i=0;i<columList.size();i++){
			double temp=columList.get(i).getValue();
			sum+=temp;
		}
		DecimalFormat df=new DecimalFormat("#.####");
		slider1.setValue(Double.parseDouble(df.format(sum/100)));
	}
	public void refreshSlider2(){
		double sum=0;
		List<DisplayType> columList=changeTable2.getItems();
		for(int i=0;i<columList.size();i++){
			double temp=columList.get(i).getValue();
			sum+=temp;
		}
		DecimalFormat df=new DecimalFormat("#.####");
		slider2.setValue(Double.parseDouble(df.format(sum/100)));
	}

	public void initTable() {
		if (displayTypeList1 == null) {
			getWeightMap();
		}
		if (displayTypeList1 != null) {
			Pane tableHeader = (Pane) changeTable1.lookup(".tableHeaderRow");
			if (tableHeader != null) {
				tableHeader.setMaxHeight(0);
				tableHeader.setMinHeight(0);
				tableHeader.setPrefHeight(0);
				tableHeader.setVisible(false);
			} else {
			}
			changeTable1.autosize();
			changeTable1.setStyle("-fx-cell-size:70.0");
			changeTable1.setStyle("-fx-font-size:16");
			changeTable1.getStyleClass().add("tableview-header-hidden");
			changeTable1.setItems(FXCollections.observableList(displayTypeList1));
			changeTable1.setRowFactory(new Callback<TableView, TableRow>() {

				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(changeTable1);
				}
			});
			codeCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			setColumnOrange(codeCm1);
			codeCm1.setSortable(false);
			nameCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getName()));
			setColumnOrange(nameCm1);
			nameCm1.setSortable(false);
			sliderCm1
					.setCellFactory(new Callback<TableColumn<DisplayType, Number>, TableCell<DisplayType, Number>>() {
						@Override
						public TableCell<DisplayType, Number> call(
								TableColumn<DisplayType, Number> param) {
							final SliderTableCell<DisplayType, Number> cell = new SliderTableCell<>(true);
							return cell;
						}

					});
			sliderCm1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DisplayType, Number>,
					ObservableValue<Number>>() {
				@Override
				public ObservableValue<Number> call(TableColumn.CellDataFeatures<DisplayType, Number> param) {
					return param.getValue().valueProperty();
				}
			});
			sliderCm1.setSortable(false);
			ratioCm1.setCellValueFactory(cellData -> {
				DisplayType data = cellData.getValue();
				return Bindings.createStringBinding(() -> {
					try {
						double ratio = data.getValue();
						DecimalFormat df = new DecimalFormat("#.##");
						String dx = df.format(ratio);
						return dx + "%";
					} catch (NumberFormatException nfe) {
						return "";
					}
				}, data.valueProperty());
			});
			ratioCm1.setSortable(false);
		}
		if (displayTypeList2 != null) {
			Pane tableHeader = (Pane) changeTable1.lookup(".tableHeaderRow");
			if (tableHeader != null) {
				tableHeader.setMaxHeight(0);
				tableHeader.setMinHeight(0);
				tableHeader.setPrefHeight(0);
				tableHeader.setVisible(false);
			} else {
			}
			changeTable2.autosize();
			changeTable2.setStyle("-fx-cell-size:70.0");
			changeTable2.setStyle("-fx-font-size:16");
			changeTable2.getStyleClass().add("tableview-header-hidden");
			changeTable2.setItems(FXCollections.observableList(displayTypeList2));
			changeTable2.setRowFactory(new Callback<TableView, TableRow>() {

				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(changeTable2);
				}
			});
			codeCm2.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			setColumnOrange(codeCm2);
			codeCm2.setSortable(false);
			nameCm2.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getName()));
			setColumnOrange(nameCm2);
			nameCm2.setSortable(false);
			sliderCm2
					.setCellFactory(new Callback<TableColumn<DisplayType, Number>, TableCell<DisplayType, Number>>() {
						@Override
						public TableCell<DisplayType, Number> call(
								TableColumn<DisplayType, Number> param) {
							final SliderTableCell<DisplayType, Number> cell = new SliderTableCell<>(false);
							return cell;
						}

					});
			sliderCm2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DisplayType, Number>,
					ObservableValue<Number>>() {
				@Override
				public ObservableValue<Number> call(TableColumn.CellDataFeatures<DisplayType, Number> param) {
					return param.getValue().valueProperty();
				}
			});
			sliderCm2.setSortable(false);
			ratioCm2.setCellValueFactory(cellData -> {
				DisplayType data = cellData.getValue();
				return Bindings.createStringBinding(() -> {
					try {
						double ratio = data.getValue();
						DecimalFormat df = new DecimalFormat("#.##");
						String dx = df.format(ratio);
						return dx + "%";
					} catch (NumberFormatException nfe) {
						return "";
					}
				}, data.valueProperty());
			});
			ratioCm2.setSortable(false);
		}
	}


	public void initButton() {
		completedBt.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
					Map<String,Double> setMap=new HashMap<String,Double>();
					List<DisplayType> columList=changeTable1.getItems();
					for(int i=0;i<columList.size();i++){
						String tempCode=columList.get(i).getKey();
						double tempValue=columList.get(i).getValue();
						double tempSource=allDisplayMap.get(tempCode);
						if(!(Math.abs(tempSource-tempValue)<0.0001)){
							setMap.put(tempCode,tempValue);
						}
					}
					if(setMap.size()>0){
						try {
							fofAssetAllocationLogic.changePosition(setMap);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					MainUI.getInstance().changeScene("user_guidePanel","analyseHomePanel");
				}

			}
		});
	}


	private void getWeightMap() {
		displayMap1=null;
		displayMap2=null;
		try {
			weightMap = fofBaseInfoLogic.getNewestWeight();
			displayMap1 = weightMap.get("000011");
			displayMap2 = weightMap.get("000012");

			allDisplayMap=weightMap.get("000011");
			allDisplayMap.putAll(displayMap2);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if(displayMap1!=null){
			displayTypeList1=getDisplayTypeList(displayMap1);
		}
		if(displayMap2!=null){
			displayTypeList2=getDisplayTypeList(displayMap2);
		}

	}
	private void setColumnOrange(TableColumn<DisplayType,String> p){
		p.setCellFactory(new Callback<TableColumn<DisplayType, String>, TableCell<DisplayType, String>>() {
			@Override
			public TableCell<DisplayType, String> call(TableColumn<DisplayType, String> param) {
				return new TableCell<DisplayType, String>(){
					@Override
					public void updateItem(String item,boolean empty){
						super.updateItem(item, empty);
						if (!isEmpty()) {
							this.setTextFill(new Color(252/255.0,242/255.0,70/255.0,1));
							setText(item);
						}
					}
				};
			}
		});
	}

	public List<DisplayType> getDisplayTypeList(Map<String, Double> map) {
		List<DisplayType> list=new ArrayList<DisplayType>();
		if (map != null) {
			list = new ArrayList<DisplayType>();
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				FundInfo info = null;
				if(entry.getKey()!=null&&baseInfoLogic!=null) try {
					System.out.println("The fundcode is:" + entry.getKey());
					info = baseInfoLogic.getFundBaseInfo(entry.getKey());
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
				}
				DisplayType temp = null;
				if (info != null) {
					System.out.println("fundinfo is not null!:"+info.simple_name);
					temp = new DisplayType(entry.getKey(), entry.getValue(), info.simple_name);
				} else {
					temp = new DisplayType(entry.getKey(), entry.getValue(), "");
				}
				if (temp != null) {
					list.add(temp);
				}
			}
		}
		return list;
	}

	public class TableRowControl<T> extends TableRow<T> {

		public TableRowControl(TableView<T> tableView) {
			super();
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {

					}
					if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
					}

				}
			});
		}
	}


	public class SliderTableCell<T, S> extends TableCell<T, S> {
		private final Slider slider;
		private ObservableValue<S> ov;
		/**
		 * true is table1,false is table2
		 */
		private boolean type;

		public SliderTableCell(boolean type) {
			this.type=type;
			this.slider = new Slider();
			slider.applyCss();
			slider.layout();
			slider.setMin(0);
			slider.setMax(100);

			slider.setShowTickLabels(false);
			slider.setShowTickMarks(false);
			slider.setMajorTickUnit(50);
			slider.setMinorTickCount(10);
			slider.setBlockIncrement(0.01);
			slider.setStyle("-fx-control-inner-background: #FFFFFF");
			Label label = new Label();
			label.textProperty().bind(slider.valueProperty().asString("%.2f"));

			setAlignment(Pos.CENTER);
		}

		@Override
		protected void updateItem(S item, boolean empty) {
			if (empty) {
				setText(null);
			} else {
				ov = getTableColumn().getCellObservableValue(getIndex());
//				System.out.println("++" + getIndex() + "----" + getTableColumn().getCellObservableValue(0) + "+++" + ov);
				if (ov instanceof DoubleProperty) {
					slider.setValue(((DoubleProperty) ov).getValue());
					slider.valueProperty()
							.bindBidirectional((DoubleProperty) ov);
					if(type){
						refreshSlider1();
					}else{
						refreshSlider2();
					}

				}
				setGraphic(slider);
			}
		}

	}


}
