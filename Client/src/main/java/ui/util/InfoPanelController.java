package ui.util;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/7.
 */
public class InfoPanelController implements Initializable {
    @FXML
    private Label closeTab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeTab.setOnMouseClicked((e) -> {
            MainUI.getInstance().removeInfoPanel();
        });
    }
}
