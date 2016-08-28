package starter;

import RMIModule.BLInterfaces;
import beans.FundQuickInfo;
import beans.PriceInfo;
import beans.ProfitChartInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.dom4j.DocumentException;
import ui.util.FXMLHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by tj on 2016/8/17.
 */
public class MainUI extends Application {
	public MainUI() {
	}

	private final Delta dragDelta = new Delta();
	private static Stage primaryStage;
	private static Scene primaryScene;
	private AnchorPane loginPanel;

	private BLInterfaces blInterfaces;

	private static HBox hbox;
	private static VBox vbox;
	private final double normalWidth = 1366;
	private final double normalHeight = 768;

	private BaseInfoLogic baseInfoLogic;

	public static double sizeRatio;
	public static   HashMap<String,List<FundQuickInfo>> fundInfoMap=new HashMap<String,List<FundQuickInfo>>();
	public static   HashMap<String,List<PriceInfo>> priceInfoMap =new HashMap<String,List<PriceInfo>>();
	public static  HashMap<String,List<ProfitChartInfo>> profitChartInfoMap = new HashMap<String,List<ProfitChartInfo>>();
	public static MainUI getInstance() {
		return MainUIHandler.instance;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Scene getPrimaryScene() {
		return primaryScene;
	}


	private static class MainUIHandler{
		private static MainUI instance=new MainUI();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			blInterfaces.netStart();
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.print("......net fail......+\n");
		}
		this.primaryStage = primaryStage;
		loginPanel = FXMLHelper.loadPanel("loginPanel");

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
		double theWidth = primaryScreenBounds.getWidth();
		System.out.println("width:" + theWidth + "height:" + primaryScreenBounds.getHeight());
		sizeRatio = theWidth / normalWidth;


//		primaryStage.setHeight(618 * sizeRatio);
//		primaryStage.setWidth(1000 * sizeRatio);
		primaryStage.setHeight(618);
		primaryStage.setWidth(1000);
		primaryStage.setTitle("FoFQuant");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);

		primaryScene = new Scene(loginPanel);
		addDraggableNode(loginPanel);
		primaryStage.setScene(primaryScene);
		primaryStage.show();

		getFundDataThread();

	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * drag
	 */

	class Delta {
		double x, y;
	}

	protected void addDraggableNode(final Node node) {

		node.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					dragDelta.x = me.getSceneX();
					dragDelta.y = me.getSceneY();
				}
			}
		});

		node.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (me.getButton() != MouseButton.MIDDLE) {
					node.getScene().getWindow().setX(me.getScreenX() - dragDelta.x);
					node.getScene().getWindow().setY(me.getScreenY() - dragDelta.y);
				}
			}
		});
	}

	public void enterLoginPanel() {
		AnchorPane pane = FXMLHelper.loadPanel("loginPanel");
		MainUI.primaryScene = new Scene(pane);
		addDraggableNode(pane);
		MainUI.primaryStage.setScene(primaryScene);

	}

	public void changeScene(String guideName, String mainStageName) {
		vbox = new VBox();
		hbox = new HBox();
		AnchorPane headPane = FXMLHelper.loadPanel("headPanel");
		AnchorPane guidePane = FXMLHelper.loadPanel(guideName);
		AnchorPane mainStagePane = FXMLHelper.loadPanel(mainStageName);
		vbox.getChildren().addAll(headPane, mainStagePane);
		hbox.getChildren().addAll(guidePane, vbox);
		primaryScene = new Scene(hbox);
		addDraggableNode(hbox);
		primaryStage.setScene(primaryScene);
	}
	public void getFundDataThread() {
		Runnable getFundData = new Runnable() {
			@Override
			public synchronized void run() {
				String sectorID="000001";
				List<FundQuickInfo> fundQuickInfoList=null;
				long tempTime= Calendar.getInstance().getTimeInMillis();
				if(!MainUI.fundInfoMap.containsKey(sectorID)){
					try {
						fundQuickInfoList = BLInterfaces.getBaseInfoLogic().getFundQuickInfo(sectorID);
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (ObjectNotFoundException e) {
						e.printStackTrace();
					}
					System.out.println("---get "+sectorID+" fundinfo from server:"+(Calendar.getInstance().getTimeInMillis()-tempTime));
					MainUI.fundInfoMap.put(sectorID,fundQuickInfoList);
				}else{
					fundQuickInfoList=MainUI.fundInfoMap.get(sectorID);
					System.out.println("get "+sectorID+" fundinfo from map:"+(Calendar.getInstance().getTimeInMillis()-tempTime));
				}
			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.schedule(getFundData, 0, TimeUnit.SECONDS);
	}


}
