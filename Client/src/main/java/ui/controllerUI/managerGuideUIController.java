package ui.controllerUI;

/**
 * Created by QiHan on 2016/8/16.
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import starter.Main;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Created by QiHan on 2016/8/16.
 */
public class managerGuideUIController implements Initializable {
    @FXML
    private Label managerNameLabel;
    @FXML
    private Button userManagerBtn,modifyBtn,system_logBtn;

    private static managerGuideUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initButtons();
    }

    public void initManagerUser(String userName){
        managerNameLabel = new Label();
        managerNameLabel.setText(userName);
    }


    public void initButtons(){
        Button[] buttons = new Button[]{userManagerBtn,modifyBtn,system_logBtn};
        for(int i=0;i<buttons.length;i++) {
            int j=i;
            System.out.println(buttons[j].getId());
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
    public void user_logout()  {
        Main.enterLoginPanel();
    }



}