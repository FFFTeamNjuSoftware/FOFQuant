package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFQuickInfo;
import bl.fof.FOFAssetAllocationLogic;
import bl.fof.FOFBaseInfoLogic;
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
import javafx.util.Callback;
import starter.MainUI;
import ui.util.DisplayType;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/9/4.
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

	private ChangePositionController changePositionCotroller;
	private FOFBaseInfoLogic fofBaseInfoLogic;
	private FOFAssetAllocationLogic fofAssetAllocationLogic;
	private Map<String, Map<String, Double>> weightMap;
	private List<DisplayType> displayTypeList1,displayTypeList2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.changePositionCotroller = this;
		this.fofBaseInfoLogic = BLInterfaces.getFofBaseInfoLogic();
		this.fofAssetAllocationLogic = BLInterfaces.getFofAssetAllocationLogic();
		initButton();
		initTable();
	}

	public void initTable() {
		if (displayTypeList1 == null) {
			getWeightMap();
		}
		if (displayTypeList1 != null) {
			Pane tableHeader = (Pane) changeTable1.lookup(".tableHeaderRow");
			if (tableHeader != null) {
//				System.out.println("get TableHeader successed!");
				tableHeader.setMaxHeight(0);
				tableHeader.setMinHeight(0);
				tableHeader.setPrefHeight(0);
				tableHeader.setVisible(false);
			} else {
//				System.out.println("get TableHeader failed!");
			}
			changeTable1.autosize();
			changeTable1.setStyle("-fx-cell-size:70.0");
			changeTable1.setStyle("-fx-font-size:16");
//			System.out.println("The list size:" + displayTypeList1.size());
			changeTable1.setItems(FXCollections.observableList(displayTypeList1));
			changeTable1.setRowFactory(new Callback<TableView, TableRow>() {

				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(changeTable1);
				}
			});
			codeCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			nameCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getName()));
			sliderCm1
					.setCellFactory(new Callback<TableColumn<DisplayType, Number>, TableCell<DisplayType, Number>>() {
						@Override
						public TableCell<DisplayType, Number> call(
								TableColumn<DisplayType, Number> param) {
							final SliderTableCell<DisplayType, Number> cell = new SliderTableCell<>();
//							final Slider slider = (Slider) cell
//									.getGraphic();
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
//			ratioCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
//					cellData.getValue().getValue() + "%"));
//			ratioCm1.setEditable(true);
		}
		if (displayTypeList2 != null) {
			Pane tableHeader = (Pane) changeTable1.lookup(".tableHeaderRow");
			if (tableHeader != null) {
//				System.out.println("get TableHeader2 successed!");
				tableHeader.setMaxHeight(0);
				tableHeader.setMinHeight(0);
				tableHeader.setPrefHeight(0);
				tableHeader.setVisible(false);
			} else {
//				System.out.println("get TableHeader2 failed!");
			}
			changeTable2.autosize();
			changeTable2.setStyle("-fx-cell-size:70.0");
			changeTable2.setStyle("-fx-font-size:16");
//			System.out.println("The list size:" + displayTypeList2.size());
			changeTable2.setItems(FXCollections.observableList(displayTypeList2));
			changeTable2.setRowFactory(new Callback<TableView, TableRow>() {

				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(changeTable2);
				}
			});
			codeCm2.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			nameCm2.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getName()));
			sliderCm2
					.setCellFactory(new Callback<TableColumn<DisplayType, Number>, TableCell<DisplayType, Number>>() {
						@Override
						public TableCell<DisplayType, Number> call(
								TableColumn<DisplayType, Number> param) {
							final SliderTableCell<DisplayType, Number> cell = new SliderTableCell<>();
//							final Slider slider = (Slider) cell
//									.getGraphic();
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
//			ratioCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
//					cellData.getValue().getValue() + "%"));
//			ratioCm1.setEditable(true);
		}
	}


	public void initButton() {

	}


	private void getWeightMap() {
		Map<String,Double> displayMap1=null;
		Map<String,Double> displayMap2=null;
		try {
			weightMap = fofBaseInfoLogic.getNewestWeight();
			displayMap1 = weightMap.get("000011");
			displayMap2 = weightMap.get("000012");
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

	public List<DisplayType> getDisplayTypeList(Map<String, Double> map) {
		List<DisplayType> list=new ArrayList<DisplayType>();
		if (map != null) {
			list = new ArrayList<DisplayType>();
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				FOFQuickInfo info = null;
//				System.out.println("---"+entry.getKey()+"+++"+(fofBaseInfoLogic==null));
				try {
					 info = fofBaseInfoLogic.getFOFQuickInfo(entry.getKey());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				DisplayType temp = null;
				if (info != null) {
					temp = new DisplayType(entry.getKey(), entry.getValue(), info.name);
				} else {
					temp = new DisplayType(entry.getKey(), entry.getValue(), "");
				}
				if (temp != null) {
					list.add(temp);
				}
//				System.out.println(entry.getKey() + "/" + entry.getValue());
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

		public SliderTableCell() {
			this.slider = new Slider();
			slider.applyCss();
			slider.layout();
			slider.setMin(0);
			slider.setMax(100);

//			slider.setValue(40);

			slider.setShowTickLabels(false);
			slider.setShowTickMarks(false);
			slider.setMajorTickUnit(50);
			slider.setMinorTickCount(10);
			slider.setBlockIncrement(0.01);
			slider.setStyle("-fx-control-inner-background: #FFFFFF");
//							slider.setStyle("-fx-background-color: #FFFFFF");
//							slider.setStyle("-fx-foreground-color:#ffffff");
//			slider.valueProperty().addListener(new ChangeListener<Number>() {
//				public void changed(ObservableValue<? extends Number> ov,
//				                    Number old_val, Number new_val) {
////									sepiaEffect.setLevel(new_val.doubleValue());
////									sepiaValue.setText(String.format("%.2f", new_val));
//				}
//			});
			Label label = new Label();
			label.textProperty().bind(slider.valueProperty().asString("%.2f"));

			MainUI.getPrimaryStage().show();
//							MainUI.getPrimaryScene().set;
			StackPane thumb = (StackPane) slider.lookup("Thumb");
			if (thumb != null) {
//								thumb.getChildren().clear();
				thumb.getChildren().add(label);
			} else {
//				System.out.println("--------The thumb is null!");
			}

			setAlignment(Pos.CENTER);
		}

		@Override
		protected void updateItem(S item, boolean empty) {
//			super.updateItem(item, empty);
//			System.out.println("use updateItem function!" + item + " " + empty);
			if (empty) {
				setText(null);
//				setGraphic(null);
			} else {
//				int selectIndex = getTableRow().getIndex();
//				refreshCell(selectIndex);
//				changeTable1.refresh();
				ov = getTableColumn().getCellObservableValue(getIndex());
//				System.out.println("++" + getIndex() + "----" + getTableColumn().getCellObservableValue(0) + "+++" + ov);
				if (ov instanceof DoubleProperty) {
					slider.setValue(((DoubleProperty) ov).getValue());
					slider.valueProperty()
							.bindBidirectional((DoubleProperty) ov);

				}
				setGraphic(slider);
			}
		}

		private void refreshCell(int selectIndex) {
			ui.util.DisplayType displayType = displayTypeList1.get(selectIndex);
			displayType.setValue(slider.getValue());
		}
	}

}
