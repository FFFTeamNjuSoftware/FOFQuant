package ui.userUI.adjustParameterUI;

import RMIModule.BLInterfaces;
import beans.ConstParameter;
import bl.BaseInfoLogic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import starter.MainUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Created by tj on 2016/8/18.
 */
public class adjustParameterUIController implements Initializable {

    @FXML
    private TextField stable_field,lowRisk_field,maxBack_field,noRisk_field,highRisk_field,fof_field;
    @FXML
    private ComboBox win_box,has_box;
    private String[] hasTypes = new String[]{"30","60", "90","180"};
    private String[] winTypes = new String[]{"60","90","180","360"};

    private BLInterfaces blInterfaces = new BLInterfaces();
    private BaseInfoLogic baseInfo;
    private ConstParameter constParameter = new ConstParameter();
    private ConstParameter newConstParameter = new ConstParameter();

    private adjustParameterUIController instance;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		instance=this;
        baseInfo = blInterfaces.getBaseInfoLogic();
        try {
            init();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void init() throws RemoteException {
        constParameter =  baseInfo.getConstaParameteer();
        stable_field.setPromptText(constParameter.stableIndex+"");
        lowRisk_field.setPromptText(constParameter.lowRiskIndex+"");
        maxBack_field.setPromptText(constParameter.maxRetreatRatio+"");
        noRisk_field.setPromptText(constParameter.noRiskProfit+"");
        highRisk_field.setPromptText(constParameter.highRiskIndex+"");
        fof_field.setPromptText(constParameter.stopLossValue+"");

        stable_field.setText(constParameter.stableIndex+"");
        lowRisk_field.setText(constParameter.lowRiskIndex+"");
        maxBack_field.setText(constParameter.maxRetreatRatio+"");
        noRisk_field.setText(constParameter.noRiskProfit+"");
        highRisk_field.setText(constParameter.highRiskIndex+"");
        fof_field.setText(constParameter.stopLossValue+"");
        win_box.setValue(constParameter.windowTime);
        has_box.setValue(constParameter.holdTime);
        win_box.setItems(FXCollections.observableArrayList(winTypes));
        has_box.setItems(FXCollections.observableArrayList(hasTypes));

        win_box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("the selected win is: " + winTypes[newValue.intValue()]);
                newConstParameter.windowTime = Double.parseDouble(winTypes[newValue.intValue()]);
            }
        });

        has_box.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("the selected hold is: " + hasTypes[newValue.intValue()]);
                newConstParameter.holdTime = Double.parseDouble( hasTypes[newValue.intValue()]);
            }
        });

    }
    @FXML
    private void toUpdateParameter(){
        newConstParameter.highRiskIndex = Double.parseDouble(highRisk_field.getText());
        newConstParameter.lowRiskIndex =  Double.parseDouble(lowRisk_field.getText());
        newConstParameter.noRiskProfit =   Double.parseDouble(noRisk_field.getText());
        newConstParameter.stopLossValue = Double.parseDouble(fof_field.getText());
        newConstParameter.stableIndex =  Double.parseDouble(stable_field.getText());
        newConstParameter.maxRetreatRatio = Double.parseDouble(maxBack_field.getText());
        // win_box,has_box

        try {
            baseInfo.updateConstParameter(newConstParameter);
            MainUI.getInstance().displaySuccessPane();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}