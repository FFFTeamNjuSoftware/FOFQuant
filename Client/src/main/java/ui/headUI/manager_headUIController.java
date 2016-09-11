package ui.headUI;

import RMIModule.BLInterfaces;
import beans.CodeName;
import bl.BaseInfoLogic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.IOHelper;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class manager_headUIController implements Initializable {

    @FXML
    private Button minBtn, exitBtn;
    private manager_headUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        buttonInit();
    }


    private void buttonInit() {
        minBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
        });

        minBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
        });

        minBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: transparent;");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.6");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.6");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: transparent;");
        });
    }

    @FXML
    public void toExitScreen() {
        MainUI.getPrimaryStage().close();
        System.exit(0);
    }

    @FXML
    public void toMinScreen() {
        MainUI.getPrimaryStage().setIconified(true);
    }


}
