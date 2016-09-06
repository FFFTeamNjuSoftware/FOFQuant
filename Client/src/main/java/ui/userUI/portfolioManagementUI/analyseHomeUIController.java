package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFHistoryInfo;
import bl.fof.FOFBaseInfoLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import starter.MainUI;
import ui.util.MultipleAxesLineChart;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

/**
 * Created by QiHan on 2016/8/26.
 */
public class analyseHomeUIController implements Initializable {
	public static final String GENERAL_ANALYSIS_PANEL = "generalAnalysisPanel";
	private analyseHomeUIController analyseHomeUIController;
	private generalAnalysisUIController generalAnalysisUIController = new generalAnalysisUIController();
	@FXML
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
	@FXML
	private ImageView Img1, Img2, Img3, Img4, Img5, Img6, Img7;

	private MainUI mainUI;
	@FXML
	private AnchorPane areaChartPanel;
	@FXML
	private BorderPane chartBorderPanel;

	@FXML
	private NumberAxis numAxis, numAxis1;
	@FXML
	private CategoryAxis categoryAxis, categoryAxis1;
	@FXML
	private AreaChart areaChart, areaChart1;
	@FXML
	private Label redLineLb, blueLineLb;
	private List<FOFHistoryInfo> fofHistoryInfoList;
	private FOFBaseInfoLogic fofBaseInfoLogic;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		analyseHomeUIController = this;
		fofBaseInfoLogic = BLInterfaces.getFofBaseInfoLogic();
		//   FXMLLoader fxmlLoader=new FXMLLoader(generalAnalysisUIController.class.getResource("generalAnalysisPanel.fxml"));
		//     generalAnalysisUIController=fxmlLoader.getController();
		mainUI = MainUI.getInstance();
		initButton();
		initAreaChart();
	}

	private void initButton() {
		Button[] buttons = new Button[]{btn1, btn2, btn3, btn4, btn5, btn6, btn7};
		ImageView[] imgs = new ImageView[]{Img1, Img2, Img3, Img4, Img5, Img6, Img7};
		for (int i = 0; i < buttons.length; i++) {
			int j = i;
			buttons[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				imgs[j].setImage(new Image(this.getClass().getResourceAsStream("/images/homepageButtonEnter" + (j + 1) + ".png")));
			});
			buttons[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				imgs[j].setImage(new Image(this.getClass().getResourceAsStream("/images/homepageButton" + (j + 1) + ".png")));
			});
		}
	}

	@FXML
	public void toMonitorPanel() {
		mainUI.setIndex(0);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toBreakevenPanel() {
		mainUI.setIndex(1);
		// generalAnalysisUIController.setK(1);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toAttributionPanel() {
		mainUI.setIndex(2);
		//  generalAnalysisUIController.setK(2);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toReturnStatsPanel() {
		mainUI.setIndex(3);
		//  generalAnalysisUIController.setK(3);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toAssetAllocationPanel() {
		mainUI.setIndex(4);
		//   generalAnalysisUIController.setK(4);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toChangePositionPanel() {
		mainUI.setIndex(5);
		//  generalAnalysisUIController.setK(5);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	@FXML
	public void toAppraisalPanel() {
		mainUI.setIndex(6);
		//  generalAnalysisUIController.setK(6);
		mainUI.changeScene("user_guidePanel", "generalAnalysisPanel");
	}

	public void initAreaChart() {
		if (areaChart.getData() != null) {
			areaChart.getData().clear();
		}
		if (fofHistoryInfoList == null) {
			getFOFHistoryInfo();
		}
		if (fofHistoryInfoList != null) {
			XYChart.Series series1 = new XYChart.Series();
			XYChart.Series series2 = new XYChart.Series();
			series1.setName("总资产");
			series2.setName("总回报");

			for (int i = 1; i < 10; i++) {
				series1.getData().add(new XYChart.Data(fofHistoryInfoList.get(i).date, fofHistoryInfoList.get(i).totalValue));
				series2.getData().add(new XYChart.Data(fofHistoryInfoList.get(i).date, fofHistoryInfoList.get(i).totalProfitRate));
			}
//			for(int i=1;i<10;i++){
//				series1.getData().add(new XYChart.Data("2016-08-0"+i, 10000.0+i));
//				series2.getData().add(new XYChart.Data("2016-08-0"+i, 10.0+i));
//			}

			areaChart.setTitleSide(Side.TOP);
			areaChart.setCreateSymbols(false);
			areaChart.setAlternativeColumnFillVisible(false);
			areaChart.setLegendVisible(false);

			areaChart1.setTitleSide(Side.TOP);
			areaChart1.setCreateSymbols(false);
			areaChart1.setAlternativeColumnFillVisible(false);
			areaChart1.setLegendVisible(false);

			categoryAxis.setTickLabelGap(10);
			categoryAxis.isGapStartAndEnd();
			categoryAxis.setTickMarkVisible(true);
			categoryAxis.setTickLabelRotation(0.5);
			categoryAxis.setTickLabelsVisible(true);

			categoryAxis1 = categoryAxis;
			categoryAxis1.setTickLabelGap(10);
			categoryAxis1.isGapStartAndEnd();
			categoryAxis1.setTickMarkVisible(true);
			categoryAxis1.setTickLabelRotation(0.5);
			categoryAxis1.setTickLabelsVisible(true);

			numAxis.setTickUnit(1);
			numAxis.setForceZeroInRange(false);

			numAxis1.setTickUnit(1);
			numAxis1.setForceZeroInRange(false);

			areaChart.getData().add(0, series1);
			areaChart.getStylesheets().add("/css/areaChartView.css");
//			areaChart.setStyle("-fx-color:#ff0fff");
			areaChart1.getData().add(1, series2);
			areaChart.getStylesheets().add("/css/areaChartView.css");
//			areaChart.setStyle("-fx-color:#ffff0f");
		}

	}

	public void getFOFHistoryInfo() {
		try {
			fofHistoryInfoList = fofBaseInfoLogic.getFOFHistoryInfo();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
