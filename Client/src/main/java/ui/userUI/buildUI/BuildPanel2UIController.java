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
public class BuildPanel2UIController implements Initializable {
	private BuildPanel2UIController buildPanel2UIController;
	@FXML
	private ImageView nextBt2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel2UIController=this;
		initBuildFanel2();
	}
	private void initBuildFanel2(){
		ImageView[] imageViews = {nextBt2};
		InitHelper.beautifyImageViews(imageViews);
	}
	@FXML
	public void nextBt2Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel3");
	}


}
