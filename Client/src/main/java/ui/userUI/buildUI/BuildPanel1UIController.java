package ui.userUI.buildUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    /**
     *	0表示未选，1为高，2为中，3为低
     */
	private int pressedButton=0;
	private BuildPanel1UIController buildPanel1UIController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel1UIController=this;
		initBuilPanel1();
	}
	private void initBuilPanel1(){
		ImageView[] imageViews = {nextBt1};
		InitHelper.beautifyImageViews(imageViews);
		ImageView[] imageViews2 = {highRiskBt,mediumRiskBt,lowRiskBt};
		InitHelper.beautifyImageViews2(imageViews2);
	}

	@FXML
	public void highRiskBtClick(){
        pressedButton=1;
        warningText.setVisible(false);
	}
	@FXML
	public void mediumRiskBtClick(){
        pressedButton=2;
        warningText.setVisible(false);
	}
	@FXML
	public void lowRiskBtClick(){
        pressedButton=3;
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
