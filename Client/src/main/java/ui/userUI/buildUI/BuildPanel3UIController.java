package ui.userUI.buildUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starter.MainUI;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel3UIController implements Initializable {
	private BuildPanel3UIController buildPanel3UIController;
	@FXML
	private ImageView nextBt3;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel3UIController=this;
		initPanel3();
	}
	private void initPanel3(){
		ImageView[] imageViews = {nextBt3};
		for (int i=0;i < imageViews.length; i++) {
			ImageView tempImage=imageViews[i];
			tempImage.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
			});

			tempImage.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
			});

			tempImage.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: transparent;");
			});
		}
	}
	@FXML
	public void nextBt3Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel4");
	}
}
