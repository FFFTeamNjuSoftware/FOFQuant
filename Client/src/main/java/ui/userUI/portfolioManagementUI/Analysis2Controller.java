package ui.userUI.portfolioManagementUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/28.
 */
public class Analysis2Controller implements Initializable {
	@FXML
	private ComboBox<Date> startCb,endCb;
	@FXML
	private Label startDateLb,endDateLb,combinationLb1,combinationLb2;
	@FXML
	private ImageView combinationImage1,combinationImage2;
//	@FXML
//	private TableColum<>
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
