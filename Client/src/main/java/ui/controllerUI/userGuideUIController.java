package ui.controllerUI;

import RMIModule.BLInterfaces;
import bl.fof.FOFBaseInfoLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import starter.MainUI;
import ui.util.IOHelper;

import java.net.URL;
import java.rmi.RemoteException;
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
    private BLInterfaces blInterfaces = new BLInterfaces();
    private FOFBaseInfoLogic baseInfoLogic;
    private MainUI mainUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        baseInfoLogic = blInterfaces.getFofBaseInfoLogic();
        instance = this;
        initButtons();
        initNormalUser();
    }

    public void initNormalUser() {
        userNameLabel.setText(IOHelper.readName());
    }

    public void initButtons() {
        Button[] buttons = new Button[]{combinationBtn, marketBtn, riskBtn, warning_logBtn, logoutBtn};
//		combinationBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
        for (int k = 0; k < buttons.length; k++) {
            if (k != MainUI.getInstance().s) {
                buttons[k].setStyle("-fx-background-color: transparent;");
            } else {
                buttons[k].setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
            }
        }
        for (final int[] i = {0}; i[0] < buttons.length; i[0]++) {
            int j = i[0];
            buttons[i[0]].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                buttons[j].setStyle("-fx-background-color: #AFE1FE; -fx-opacity:0.3");
            });
            buttons[i[0]].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                MainUI.getInstance().s = j;
                for (int k = 0; k < buttons.length; k++) {
                    if (k != MainUI.getInstance().s) {
                        buttons[k].setStyle("-fx-background-color: transparent;");
                    } else {
                        buttons[k].setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
                    }
                }
            });
            buttons[i[0]].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                for (int k = 0; k < buttons.length; k++) {
                    if (MainUI.getInstance().s == k) {
                        buttons[k].setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
                    } else {
                        buttons[k].setStyle("-fx-background-color: transparent;");
                    }
                }
            });

        }
        mainUI = MainUI.getInstance();
        combinationBtn.setOnAction((e) -> {
            try {
                if (baseInfoLogic.hasGeneratedFofCombination()) {
                    mainUI.changeScene("user_guidePanel", "analyseHomePanel");
                } else {
                    mainUI.changeScene("user_guidePanel", "buildHomePanel");
                }
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        marketBtn.setOnAction((e) -> {
            mainUI.changeScene("user_guidePanel", "allFundPanel");
        });

        warning_logBtn.setOnAction((e) -> {
            mainUI.changeScene("user_guidePanel", "adjustParameterPanel");
        });
        riskBtn.setOnAction((e) -> {
            mainUI.changeScene("user_guidePanel","riskControlPanel");
        });
    }


    @FXML
    public void user_logout() {
        MainUI.getInstance().enterLoginPanel();
    }


}
