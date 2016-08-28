package ui.userUI.portfolioManagementUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/26.
 */
public class analyseHomeUIController implements Initializable {
    private analyseHomeUIController analyseHomeUIController;
    private generalAnalysisUIController  generalAnalysisUIController =new generalAnalysisUIController();
    private MainUI mainUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyseHomeUIController=this;
        mainUI = MainUI.getInstance();
    }

    @FXML
    public void toMonitorPanel(){
     //   FXMLLoader fxmlLoader=new FXMLLoader(MainUI.class.getResource("generalAnalysisPanel.fxml"));
    //    generalAnalysisUIController=fxmlLoader.getController();
        generalAnalysisUIController.setTab(0);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toAppraisalPanel(){
        generalAnalysisUIController.setTab(1);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toChangePositionPanel(){
        generalAnalysisUIController.setTab(2);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toBreakevenPanel(){
        generalAnalysisUIController.setTab(3);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }

    @FXML
    public void toAttributionPanel(){
        generalAnalysisUIController.setTab(4);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toAssetAllocationPanel(){
        generalAnalysisUIController.setTab(5);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toReturnStatsPanel(){
        generalAnalysisUIController.setTab(6);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
}
