package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import bl.fof.FOFGenerateLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starter.MainUI;
import ui.util.InitHelper;
import ui.util.PieChartGenerator;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
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
	@FXML
	private Label label1,label2;
	@FXML
	private TableView build3Table1,build3Table2;
	@FXML
	private TableColumn column1,column2,column3,column4;
	private BLInterfaces blInterfaces = new BLInterfaces();
	private FOFGenerateLogic generateLogic;
	private ProfitFeatureLogic profitFeatureLogic  ;
	private FOFBaseInfoLogic baseInfoLogic;
	private Map<String, Map<String, Double>> map =null;
	private Map<String,Double> mapList;
	public static String profitKey = "000011";
	public static String solidKey = "000012";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel3UIController=this;
		generateLogic = blInterfaces.getFofGenerateLogic();
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

		try {
			map=generateLogic.getSmallClassConfiguration();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (map != null) {
			label1.setText(map.get(profitKey) + "%");
			label2.setText(map.get(solidKey) + "%");
		}

	}
	@FXML
	public void nextBt3Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel4");
	}

}
