package Test;

/**
 * Created by OptimusPrime on 2016/9/6.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestScene extends Application {

	@Override
	public void start(Stage primaryStage) {
		Slider slider = new Slider();
		StackPane root = new StackPane(slider);
		StackPane root1=new StackPane(root);
		root.setPadding(new Insets(20));


		Scene scene = new Scene(root1);
		slider.applyCss();
		slider.layout();
		Pane thumb = (Pane) slider.lookup(".thumb");
		Label label = new Label();
		label.textProperty().bind(slider.valueProperty().asString("%.2f"));

		if(thumb!=null){
			System.out.println("get thumb successed!");
			thumb.getChildren().add(label);
		}else{
			System.out.println("get thumb failed!");
		}

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
