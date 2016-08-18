package starter;

import RMIModule.BLInterfaces;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.dom4j.DocumentException;
import ui.util.FXMLHelper;

/**
 * Created by tj on 2016/8/17.
 */
public class MainUI extends Application {
    private Stage primaryStage;
    private Scene primaryScene;
    private AnchorPane loginPanel;
    private BLInterfaces blInterfaces;
    private static MainUI instance;

    public static MainUI getInstance() {
        return instance;
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
        primaryStage.setHeight(618);
        primaryStage.setWidth(1000);
        primaryStage.setTitle("FoFQuant");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);

        primaryScene = new Scene(loginPanel);

        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void changeScene(String name) {
        AnchorPane pane = FXMLHelper.loadPanel(name);
        primaryScene = new Scene(pane);
        primaryStage.setScene(primaryScene);
    }
}
