package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import bl.fof.FOFGenerateLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.InitHelper;
import ui.util.PieChartGenerator;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
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
	private ImageView nextBt3;
	@FXML
	private ImageView waiting3;
	@FXML
	private Label profitFund;
	@FXML
	private Label solidProfitFund;
	@FXML
	private AnchorPane chartPane;
	private FOFGenerateLogic logic;
	//根据stub瞎写的
	public static String profitKey = "000011";
	public static String solidKey = "000012";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel2UIController = this;
		logic = BLInterfaces.getFofGenerateLogic();
		initPanel3();
		scheduleTask3();
	}

	//	10s后执行
	private void scheduleTask3() {
		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				waiting3.setVisible(false);

			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
//		service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
		service.schedule(runnable, 10, TimeUnit.SECONDS);
	}

	private void initPanel3() {
		ImageView[] imageViews = {nextBt3};
		InitHelper.beautifyImageViews(imageViews);
		Map<String, Double> map = null;
		try {
			map = logic.getLargeClassConfiguration();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (map != null) {
			profitFund.setText(map.get(profitKey) + "%");
			solidProfitFund.setText(map.get(solidKey) + "%");
			ObservableList<PieChart.Data> datas = FXCollections.observableArrayList();
			datas.add(new PieChart.Data("权益类基金", map.get(profitKey)));
			datas.add(new PieChart.Data("固定收益类基金", map.get(solidKey)));
			new PieChartGenerator(chartPane, datas);
		}
	}

	@FXML
	public void nextBt3Click() {
		MainUI.getInstance().changeScene("user_guidePanel", "buildPanel3");
	}

}
