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
public class BuildPanel2UIController implements Initializable {
	private BuildPanel2UIController buildPanel2UIController;
	@FXML
	private ImageView nextBt2;
	@FXML
	private ImageView waiting2;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel2UIController=this;
		initBuildFanel2();
		scheduleTask2();
	}
	private void initBuildFanel2(){
		ImageView[] imageViews = {nextBt2};
		InitHelper.beautifyImageViews(imageViews);
	}
//	10s后执行
	private void scheduleTask2(){ Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				waiting2.setVisible(false);

			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
		service.schedule(runnable,10,TimeUnit.SECONDS);
	}
	@FXML
	public void nextBt2Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel3");
	}

}
