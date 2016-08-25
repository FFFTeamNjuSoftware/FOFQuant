package starter;

import RMIModule.BLInterfaces;
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

/**
 * Created by tj on 2016/8/17.
 */
public class MainUI extends Application {
	private final Delta dragDelta = new Delta();
	private static Stage primaryStage;
	private static Scene primaryScene;
	private AnchorPane loginPanel;
	private BLInterfaces blInterfaces;
	private static MainUI instance;
	private static HBox hbox;
	private static VBox vbox;
	private final double normalWidth = 1366;
	private final double normalHeight=768;

	public static double sizeRatio;

	public static MainUI getInstance() {
		return instance;
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	public static Scene getPrimaryScene() {
		return primaryScene;
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;
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
		System.out.println("width:"+theWidth+"height:"+primaryScreenBounds.getHeight());
		sizeRatio =  theWidth/ normalWidth;


		primaryStage.setHeight(618 * sizeRatio);
		primaryStage.setWidth(1000 * sizeRatio);
		primaryStage.setTitle("FoFQuant");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);

		primaryScene = new Scene(loginPanel);
		addDraggableNode(loginPanel);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
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

	public static void enterLoginPanel() {
		AnchorPane pane = FXMLHelper.loadPanel("loginPanel");
		MainUI.primaryScene = new Scene(pane);
		MainUI mainui = new MainUI();
		mainui.addDraggableNode(pane);
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
}
