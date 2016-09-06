package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.InvestStyleAnalyse;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.util.FXMLHelper;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/9/2.
 */
public class performanceEvaluationController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private ImageView image1,image2;
    @FXML
    private Button btn1,btn2;

    private performanceEvaluationController instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initStep();
        AnchorPane pane = FXMLHelper.loadPanel("fofAnalysis71");
        panel.getChildren().addAll(pane);
    }

    private void initStep(){
        Button[] buttons= new Button[]{btn1,btn2};
        ImageView[] images = new ImageView[]{image1,image2};
        for ( int i =0; i < buttons.length; i++) {
            int j = i;
            buttons[i].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                images[j].setVisible(true);
                for (int s = 0; s < buttons.length; s++) {
                    if (s != j) {
                        images[s].setVisible(false);
                    }
                }
                String panelName = "fofAnalysis7" + (j + 1);
                AnchorPane pane = FXMLHelper.loadPanel(panelName);
                panel.getChildren().addAll(pane);
            });
        }
    }

}
