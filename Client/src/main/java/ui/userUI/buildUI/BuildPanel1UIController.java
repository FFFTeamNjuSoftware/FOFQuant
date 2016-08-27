package ui.userUI.buildUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

	}
	@FXML
	public void mediumRiskBtClick(){

	}
	@FXML
	public void lowRiskBtClick(){

	}

	@FXML
	public void nextBt1Click(){
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel2");
	}

}
