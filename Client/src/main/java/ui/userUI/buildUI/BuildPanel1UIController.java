package ui.userUI.buildUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import starter.MainUI;
import ui.util.InitHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel1UIController implements Initializable {
	@FXML
	private ImageView highRiskBt;
	@FXML
	private ImageView mediumRiskBt;
	@FXML
	private ImageView lowRiskBt;
	@FXML
	private ImageView nextBt1;
    @FXML
    private Label warningText;
	@FXML
	private ComboBox combobox;
	@FXML
	private Slider slider;
	@FXML
	private Label field;
	@FXML
	private RadioButton CPPINode,levelNode,customNode;
	@FXML
	private GridPane customPane,highPane,mediumPane,lowPane;
	private ToggleGroup group = new ToggleGroup();
	@FXML
	private TextField assetField;

	private String[] riskType = {"2","2.5","3","3.5","4"};
    /**
     *	0表示未选，1为高，2为中，3为低,4为自选
     */
	private int pressedButton=0;
	private BuildPanel1UIController buildPanel1UIController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel1UIController=this;
		initBuilPanel1();
	}
	private void initBuilPanel1(){
		init();
		ImageView[] imageViews = {nextBt1};
		InitHelper.beautifyImageViews(imageViews);
		ImageView[] imageViews2 = {highRiskBt,mediumRiskBt,lowRiskBt};
		InitHelper.beautifyImageViews2(imageViews2);
	}

	private void init(){
		combobox.setValue(2);
		combobox.setItems(FXCollections.observableArrayList(riskType));

		slider.setMin(0);
		slider.setMax(1);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(0.50);
		slider.setMinorTickCount(10);
		slider.setBlockIncrement(20);

		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				field.setText(String.format("%.2f", new_val));
			}
		});
		CPPINode.setToggleGroup(group);
		CPPINode.setSelected(true);
		levelNode.setToggleGroup(group);
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
			public void changed(ObservableValue<? extends Toggle> ov,
							   Toggle old_toggle, Toggle new_toggle) {
				System.out.println("......new......"+new_toggle+"......"+ov.getValue());
			}
		});
		customNode.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if(customNode.isSelected()){
				pressedButton=0;
				customPane.setVisible(false);
			}else{
				pressedButton=3;
				lowPane.setVisible(false);
				mediumPane.setVisible(false);
				highPane.setVisible(false);
				customPane.setVisible(true);
			}
		});

		assetField.setStyle("-fx-text-fill: #EB494D;");
	}

	@FXML
	public void highRiskBtClick(){
        pressedButton=1;
		lowPane.setVisible(false);
		mediumPane.setVisible(false);
		highPane.setVisible(true);
        warningText.setVisible(false);
	}
	@FXML
	public void mediumRiskBtClick(){
        pressedButton=2;
		lowPane.setVisible(false);
		mediumPane.setVisible(true);
		highPane.setVisible(false);
        warningText.setVisible(false);
	}
	@FXML
	public void lowRiskBtClick(){
        pressedButton=3;
		lowPane.setVisible(true);
		mediumPane.setVisible(false);
		highPane.setVisible(false);
        warningText.setVisible(false);
	}

	@FXML
	public void nextBt1Click(){
        if(pressedButton!=0) {
            MainUI.getInstance().changeScene("user_guidePanel", "buildPanel2");
        }else{
            warningText.setVisible(true);
        }
	}

}
