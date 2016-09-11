package starter;

import RMIModule.BLInterfaces;
import beans.FundQuickInfo;
import beans.PriceInfo;
import beans.ProfitChartInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.dom4j.DocumentException;
import ui.headUI.headUIController;
import ui.util.FXMLHelper;

import java.io.IOException;
import java.rmi.RemoteException;
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
    private AnchorPane mainPanel;
    private AnchorPane headPanel;
    private AnchorPane guidePanel;
    private AnchorPane infoPane;
    private AnchorPane rootPane;
    private BLInterfaces blInterfaces;
    private headUIController headController;

    private static HBox hbox;
    private static VBox vbox;
    private final double normalWidth = 1366;
    private final double normalHeight = 768;
    private BaseInfoLogic baseInfoLogic;
    public static int index = 0;

    public static double sizeRatio;
    public static int s = -1;
    public static HashMap<String, List<FundQuickInfo>> fundInfoMap = new HashMap<String, List<FundQuickInfo>>();
    public static HashMap<String, List<PriceInfo>> priceInfoMap = new HashMap<String, List<PriceInfo>>();
    public static HashMap<String, List<ProfitChartInfo>> profitChartInfoMap = new HashMap<String, List<ProfitChartInfo>>();


    public static MainUI getInstance() {
        return MainUIHandler.instance;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static class MainUIHandler {
        private static MainUI instance = new MainUI();
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
        mainPanel = FXMLHelper.loadPanel("loginPanel");

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
        rootPane = new AnchorPane();
        rootPane.getChildren().add(mainPanel);
        primaryScene = new Scene(rootPane);
        addDraggableNode(rootPane);
        primaryStage.setScene(primaryScene);
        primaryStage.show();

        getFundDataThread();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setIndex(int i) {
        index = i;
    }

    public static int getIndex() {
        return index;
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
        rootPane = new AnchorPane();
        mainPanel = FXMLHelper.loadPanel("loginPanel");
        rootPane.getChildren().add(mainPanel);
        MainUI.primaryScene = new Scene(rootPane);
        addDraggableNode(rootPane);
        MainUI.primaryStage.setScene(primaryScene);

    }

    public void changeScene(String guideName, String mainStageName) {
        vbox = new VBox();
        hbox = new HBox();
  //      headPanel = FXMLHelper.loadPanel("headPanel");
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("/headPanel.fxml"));
            headPanel = (AnchorPane)fxmlLoader.load();
            headController =fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(guideName.equals("manager_guidePanel")){
            headController.buttonChange(0);
        }else if(guideName.equals("user_guidePanel")){
            headController.buttonChange(1);
        }else {
            System.out.println("......登录失败......");
        }
        guidePanel = FXMLHelper.loadPanel(guideName);
        mainPanel = FXMLHelper.loadPanel(mainStageName);
        vbox.getChildren().addAll(headPanel, mainPanel);
        hbox.getChildren().addAll(guidePanel, vbox);
        rootPane = new AnchorPane();
        rootPane.getChildren().add(hbox);
        primaryScene = new Scene(rootPane);
        addDraggableNode(rootPane);
        primaryStage.setScene(primaryScene);
    }

    public void addInfoPanel(String info) {
        mainPanel.setDisable(true);
        if (guidePanel != null) {
            guidePanel.setDisable(true);
        }
        if (headPanel != null) {
            headPanel.setDisable(true);
        }
        infoPane = FXMLHelper.loadPanel("infoPanel");
        infoPane.setLayoutX(400);
        infoPane.setLayoutY(200);
        rootPane.getChildren().add(infoPane);
        Label label = new Label(info);
        label.setLayoutX(2);
        label.setLayoutY(infoPane.getPrefHeight() / 2);
        label.setFont(new Font(18));
        label.setStyle("-fx-text-fill:white");
        infoPane.getChildren().add(label);
    }

    public void removeInfoPanel() {
        mainPanel.setDisable(false);
        if (guidePanel != null) {
            guidePanel.setDisable(false);
        }
        if (headPanel != null) {
            headPanel.setDisable(false);
        }
        rootPane.getChildren().remove(infoPane);
    }

    public void getFundDataThread() {
        Runnable getFundData = new Runnable() {
            @Override
            public synchronized void run() {
                String sectorID = "000001";
                List<FundQuickInfo> fundQuickInfoList = null;
                long tempTime = Calendar.getInstance().getTimeInMillis();
                if (!MainUI.fundInfoMap.containsKey(sectorID)) {
                    try {
                        fundQuickInfoList = BLInterfaces.getBaseInfoLogic().getFundQuickInfo(sectorID);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    } catch (ObjectNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("---get " + sectorID + " fundinfo from server:" + (Calendar.getInstance().getTimeInMillis() - tempTime));
                    MainUI.fundInfoMap.put(sectorID, fundQuickInfoList);
                } else {
                    fundQuickInfoList = MainUI.fundInfoMap.get(sectorID);
                    System.out.println("get " + sectorID + " fundinfo from map:" + (Calendar.getInstance().getTimeInMillis() - tempTime));
                }
            }
        };
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        service.schedule(getFundData, 0, TimeUnit.SECONDS);
    }

    public AnchorPane getMainPanel() {
        return mainPanel;
    }

    public void displaySuccessPane(){
        AnchorPane successPane = FXMLHelper.loadPanel("operationSuccessPane");
        rootPane.getChildren().add(successPane);
        successPane.setLayoutX(70);
        successPane.setLayoutY(584);
        FadeTransition ft = new FadeTransition(Duration.millis(3000), successPane);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setAutoReverse(true);
        ft.play();
    }
}
