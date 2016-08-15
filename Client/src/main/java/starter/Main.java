package starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by QiHan on 2016/8/14.
 */
public class Main  extends Application {

    private static Main instance;
    private static Stage primaryStage;
    private static Scene primaryScene;
    private static AnchorPane loginPanel;


    public static Main getInstance() {
        return instance;
    }

    public static Scene getFactoryScene(Parent parent) {
        /**
         * change cursor
         */
        Scene ans=new Scene(parent);
        return ans;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.primaryStage = primaryStage
        ;  String fileName = "loginPanel.fxml";
        System.out.println(fileName);
        System.out.println(new Main().getClass().getResource("loginPanel.fxml"));
        System.out.println(new Main().getClass().getClassLoader().getResource("loginPanel.fxml"));

        loginPanel= FXMLLoader.load(getClass().getClassLoader().getResource("loginPanel.fxml"));
        System.out.println("??????))))");

        primaryStage.setHeight(618);
        primaryStage.setWidth(1000);
        primaryStage.setTitle("FoFQuant");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.isResizable();
        primaryStage.setScene(getFactoryScene(loginPanel));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
