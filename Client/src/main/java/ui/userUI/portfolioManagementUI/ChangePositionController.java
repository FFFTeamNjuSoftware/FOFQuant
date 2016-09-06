package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import bl.fof.FOFAssetAllocationLogic;
import bl.fof.FOFBaseInfoLogic;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import starter.Main;
import starter.MainUI;
import ui.util.DisplayType;

import javax.swing.text.Document;
import java.net.URL;
import java.rmi.RemoteException;
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
	private TableColumn<DisplayType, String> nameCm1, nameCm2;
	@FXML
	private TableColumn sliderCm1, sliderCm2;
	@FXML
	private TableColumn<DisplayType,String> ratioCm1, ratioCm2;

	private ChangePositionController changePositionCotroller;
	private FOFBaseInfoLogic fofBaseInfoLogic;
	private FOFAssetAllocationLogic fofAssetAllocationLogic;
	private Map<String, Map<String, Double>> weightMap;
	private Map<String, Double> displayMap1, displayMap2;
	private List<DisplayType> displayTypeList1;

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
			Pane tableHeader = (Pane) changeTable1.lookup(".tableheaderrow");
			if (tableHeader != null) {
				System.out.println("get TableHeader successed!");
				tableHeader.setMaxHeight(0);
				tableHeader.setMinHeight(0);
				tableHeader.setPrefHeight(0);
				tableHeader.setVisible(false);
			} else {
				System.out.println("get TableHeader failed!");
			}
			changeTable1.autosize();
			changeTable1.setStyle("-fx-cell-size:70.0");
			changeTable1.setStyle("-fx-font-size:16");
			System.out.println("The list size:" + displayTypeList1.size());
			changeTable1.setItems(FXCollections.observableList(displayTypeList1));
			changeTable1.setRowFactory(new Callback<TableView, TableRow>() {

				@Override
				public TableRow call(TableView param) {
					return new TableRowControl(changeTable1);
				}
			});
			nameCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			sliderCm1
					.setCellValueFactory(new PropertyValueFactory<Document, Number>(
							"select"));
			sliderCm1
					.setCellFactory(new Callback<TableColumn<Document, Number>, TableCell<Document, Number>>() {
						@Override
						public TableCell<Document, Number> call(
								TableColumn<Document, Number> param) {
							final SliderTableCell<Document, Number> cell = new SliderTableCell<>();
							final Slider slider = (Slider) cell
									.getGraphic();

							slider.setMin(0);
							slider.setMax(100);

							slider.setValue(40);

							slider.setShowTickLabels(false);
							slider.setShowTickMarks(false);
							slider.setMajorTickUnit(50);
							slider.setMinorTickCount(10);
							slider.setBlockIncrement(0.01);
							slider.setStyle("-fx-control-inner-background: #FFFFFF");
//							slider.setStyle("-fx-background-color: #FFFFFF");
//							slider.setStyle("-fx-foreground-color:#ffffff");
							slider.valueProperty().addListener(new ChangeListener<Number>() {
								public void changed(ObservableValue<? extends Number> ov,
								                    Number old_val, Number new_val) {
//									sepiaEffect.setLevel(new_val.doubleValue());
//									sepiaValue.setText(String.format("%.2f", new_val));
								}
							});

							return cell;
						}
					});
			ratioCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getValue()+"%"));
//			ratioCm1
//					.setCellValueFactory(new PropertyValueFactory<Document, Number>(
//							"select"));
//			ratioCm1
//					.setCellFactory(new Callback<TableColumn<Document, String>, TableCell<Document, String>>() {
//						@Override
//						public TableCell<Document, String> call(
//								TableColumn<Document, String> param) {
//							final TextFieldTableCell<Document, String> cell = new TextFieldTableCell<>();
//							final TextField field = (TextField) cell
//									.getGraphic();
//
//							field.setStyle("-fx-control-inner-background: #FFFFFF");
////							field.setStyle("-fx-background-color: #FFFFFF");
////							field.setStyle("-fx-foreground-color:#000000");
//							field.setPrefHeight(30.0);
//							field.setPrefWidth(66.0);
//							field.setFont(new Font(16));
//							field.setText("50%");
//							field.textProperty().addListener((observable, oldValue, newValue) -> {
//								if(!newValue.equals(oldValue)){
//									System.out.println("textfield changed from " + oldValue + " to " + newValue);
//								}
//							});
//
//							int selectedIndex=cell.getTableRow().getIndex();
//
//							return cell;
//						}
//					});

//			ratioCm1.setCellValueFactory(cellData -> new SimpleStringProperty(
//					cellData.getValue().getValue() + "%"));

			ratioCm1.setEditable(true);
		}
	}


	public void initButton() {

	}


	private void getWeightMap() {
		try {
			weightMap = fofBaseInfoLogic.getNewestWeight();
			displayMap1 = weightMap.get("000011");
			displayMap2 = weightMap.get("000012");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (displayMap1 != null) {
			displayTypeList1 = new ArrayList<DisplayType>();
			for (Map.Entry<String, Double> entry : displayMap1.entrySet()) {
				DisplayType temp = new DisplayType(entry.getKey(), entry.getValue());
				displayTypeList1.add(temp);
//				System.out.println(entry.getKey() + "/" + entry.getValue());
			}
		}
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

	public class SliderTableCell<S, T> extends TableCell<S, T> {
		private final Slider slider;
		private ObservableValue<T> ov;

		public SliderTableCell() {
			this.slider = new Slider();
			slider.applyCss();
			slider.layout();

			Label label = new Label();
			label.textProperty().bind(slider.valueProperty().asString("%.2f"));

			MainUI.getPrimaryStage().show();
//							MainUI.getPrimaryScene().set;
			StackPane thumb = (StackPane) slider.lookup(".thumb");
			if(thumb!=null){
//								thumb.getChildren().clear();
				thumb.getChildren().add(label);
			}else{
				System.out.println("The thumb is null!");
			}

			setAlignment(Pos.CENTER);
			setGraphic(slider);
		}

		@Override
		protected void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(slider);

				int selectIndex=getTableRow().getIndex();
				refreshCell(selectIndex);
				changeTable1.refresh();
				if (ov instanceof BooleanProperty) {
//					radio.getProperties().unbindBidirectional(
//							(BooleanProperty) ov);
				}
				ov = getTableColumn().getCellObservableValue(getIndex());
				if (ov instanceof BooleanProperty) {
//					radio.selectedProperty()
//							.bindBidirectional((BooleanProperty) ov);
				}
			}
		}
		private void refreshCell(int selectIndex) {
			DisplayType displayType=displayTypeList1.get(selectIndex);
			displayType.setValue(slider.getValue());
		}
	}

	public class TextFieldTableCell<S, T> extends TableCell<S, T> {
		private final TextField text;
		private ObservableValue<T> ov;

		public TextFieldTableCell() {
			this.text = new TextField();
			setAlignment(Pos.CENTER);
			setGraphic(text);
		}

		@Override
		protected void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(text);


				if (ov instanceof BooleanProperty) {
//					radio.getProperties().unbindBidirectional(
//							(BooleanProperty) ov);
				}
				ov = getTableColumn().getCellObservableValue(getIndex());
				if (ov instanceof BooleanProperty) {
//					radio.selectedProperty()
//							.bindBidirectional((BooleanProperty) ov);
				}
			}
		}

	}
}
