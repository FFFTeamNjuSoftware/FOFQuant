package ui.userUI.portfolioManagementUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import starter.MainUI;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/9/14.
 */
public class WarningPanelController implements Initializable {
	@FXML
	private ImageView warningExitBt;
	private WarningPanelController warningPanelController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.warningPanelController=this;
	}
	@FXML
	private void warningExitClick(){
		MainUI.getInstance().removeWarningPane();
	}
}
