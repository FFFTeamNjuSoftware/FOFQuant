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
public class BuildHomeUIController implements Initializable {
	@FXML
	private ImageView buildFundBt;
	private BuildHomeUIController buildHomeUIController;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildHomeUIController = this;
		initBuildFund();
	}

	private void initBuildFund() {
		ImageView[] imageViews = {buildFundBt};
		InitHelper.beautifyImageViews(imageViews);
	}

	@FXML
	public void buildFundBtClick() {
		MainUI.getInstance().changeScene("user_guidePanel", "buildPanel1");
	}
}

