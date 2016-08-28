package ui.userUI.buildUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starter.MainUI;
import ui.util.InitHelper;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel3UIController implements Initializable {
	private BuildPanel3UIController buildPanel3UIController;
	@FXML
	private ImageView nextBt3;
	@FXML
	private ImageView waiting3;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel3UIController=this;
		initPanel3();
		scheduleTask3();
	}
	//	10s后执行
	private void scheduleTask3(){ Runnable runnable = new Runnable() {
		public void run() {
			// task to run goes here
			waiting3.setVisible(false);

		}
	};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
		service.schedule(runnable,10, TimeUnit.SECONDS);
	}
	private void initPanel3(){
		ImageView[] imageViews = {nextBt3};
		InitHelper.beautifyImageViews(imageViews);
	}
	@FXML
	public void nextBt3Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel4");
	}

}
