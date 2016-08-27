package ui.userUI.buildUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.util.InitHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel4UIController implements Initializable {
	private BuildPanel4UIController buildPanel4UIController;
	@FXML
	private ImageView nextBt4;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel4UIController=this;
		initBuildPanel4();
	}
	private void initBuildPanel4(){
		ImageView[] imageViews = {nextBt4};
		InitHelper.beautifyImageViews(imageViews);
	}
	@FXML
	public void nextBt4Click() {

	}
}
