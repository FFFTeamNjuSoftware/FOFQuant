package ui.userUI.portfolioManagementUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import starter.MainUI;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/26.
 */
public class analyseHomeUIController implements Initializable {
    private analyseHomeUIController analyseHomeUIController;
    private MainUI mainUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyseHomeUIController=this;
        mainUI = MainUI.getInstance();
    }


    @FXML
    public void toGeneralAnalysisiPanel(){
        mainUI.changeScene("user_guidePanel","buildHomePanel");
    }
    @FXML
    public void toPerformancePanel(){

    }
    @FXML
    public void toAppraisalPanel(){

    }
    @FXML
    public void toAttributionPanel(){

    }
}
