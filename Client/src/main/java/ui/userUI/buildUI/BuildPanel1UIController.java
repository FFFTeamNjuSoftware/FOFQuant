package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import beans.RiskParameters;
import bl.fof.FOFGenerateLogic;
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
import util.StrategyType;

import java.net.URL;
import java.rmi.RemoteException;
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
	private Label field,high_breakeven,high_risk,medium_breakeven,medium_risk,low_breakeven,low_risk;
	@FXML
	private RadioButton CPPINode,levelNode,customNode;
	@FXML
	private GridPane customPane,highPane,mediumPane,lowPane;
	private ToggleGroup group = new ToggleGroup();
	@FXML
	private TextField assetField;
	private BLInterfaces blInterfaces = new BLInterfaces();
	private FOFGenerateLogic generateLogic;

	private String[] riskType = {"2","2.5","3","3.5","4"};
	private double risk=2;
    /**
     *	0表示未选，1为高，2为中，3为低,4为自选
     */
    private RiskParameters[]  riskLevel = new RiskParameters[]{RiskParameters.HIGH_RISK,RiskParameters.MIDDLE_RISK,RiskParameters.LOW_RISK};
	private int pressedButton=0;
	private BuildPanel1UIController buildPanel1UIController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel1UIController=this;
		generateLogic = blInterfaces.getFofGenerateLogic();
		initBuildPanel1();
	}
	private void initBuildPanel1(){
		init();
		ImageView[] imageViews = {nextBt1};
		InitHelper.beautifyImageViews(imageViews);
		ImageView[] imageViews2 = {highRiskBt,mediumRiskBt,lowRiskBt};
		InitHelper.beautifyImageViews2(imageViews2);
	}

	private void init(){

		high_breakeven.setText("保本额度："+RiskParameters.HIGH_RISK.breakEvenValue);
		high_risk.setText("风险乘数："+RiskParameters.HIGH_RISK.breakEvenValue);
		medium_breakeven.setText("保本额度："+RiskParameters.MIDDLE_RISK.breakEvenValue);
		medium_risk.setText("风险乘数："+RiskParameters.MIDDLE_RISK.breakEvenValue);
		low_breakeven.setText("保本额度："+RiskParameters.LOW_RISK.breakEvenValue);
		low_risk.setText("风险乘数："+RiskParameters.LOW_RISK.breakEvenValue);

		combobox.setValue(2);
		combobox.setItems(FXCollections.observableArrayList(riskType));
		combobox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				risk = Double.parseDouble(riskType[newValue.intValue()]);
				System.out.println("the selected risk is: " + risk);
			}
		});
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


		customNode.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if(customNode.isSelected()){
				pressedButton=0;
				customPane.setVisible(false);
			}else{
				pressedButton=4;
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
	public void nextBt1Click() throws RemoteException {
        if(pressedButton!=0) {
        	//总资产
			generateLogic.setTotalAsset(Double.parseDouble(assetField.getText()));
			//策略选择
			if(CPPINode.isSelected()){
						generateLogic.setStrategyType(StrategyType.CPPI);
			}else if(levelNode.isSelected()){
						generateLogic.setStrategyType(StrategyType.FUND_RISKY_PARITY);
			}
			//风险系数
        	if(pressedButton!=4){
				generateLogic.setRiskLevel(riskLevel[pressedButton-1]);
			}else if(pressedButton==4){
				generateLogic.setRiskLevel(RiskParameters.getRiskParameters(risk,Double.parseDouble(field.getText())));
			}
        	MainUI.getInstance().changeScene("user_guidePanel", "buildPanel2");
        }else{
      //      warningText.setVisible(true);
			MainUI.getInstance().addInfoPanel("请您先选择风险等级!");
        }
	}

}
