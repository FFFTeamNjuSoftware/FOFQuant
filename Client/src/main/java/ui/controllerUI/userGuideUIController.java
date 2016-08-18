package ui.controllerUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import starter.Main;
import ui.util.IOHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class userGuideUIController implements Initializable {
    @FXML
    private Label userNameLabel, managerNameLabel;
    @FXML
    private Button combinationBtn, marketBtn, riskBtn, warning_logBtn, logoutBtn;

    private userGuideUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initButtons();
        initNormalUser();
    }

    public void initNormalUser() {
        userNameLabel.setText(IOHelper.readName());
    }


    public void initButtons() {
        Button[] buttons = new Button[]{combinationBtn, marketBtn, riskBtn, warning_logBtn, logoutBtn};
        for (int i = 0; i < buttons.length; i++) {
            int j = i;
            buttons[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                buttons[j].setStyle("-fx-background-color: #AFE1FE; -fx-opacity:0.3");
            });

            buttons[i].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                buttons[j].setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
            });

//Removing the shadow when the mouse cursor is off
            buttons[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                buttons[j].setStyle("-fx-background-color: transparent;");
            });
        }
    }


    @FXML
    public void user_logout() {
        Main.enterLoginPanel();
    }


}
