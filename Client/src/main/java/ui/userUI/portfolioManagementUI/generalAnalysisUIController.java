package ui.userUI.portfolioManagementUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/27.
 */
public class generalAnalysisUIController implements Initializable {
    @FXML
    private TabPane tabPanel;
    @FXML
    private Button tab1Btn,tab2Btn,tab3Btn,tab4Btn,tab5Btn,tab6Btn;
    private generalAnalysisUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance=this;
    }

    public void tabInit(){
      //  tabPanel.getTabs().addAll(tab1Btn, tab2Btn, tab3Btn, tab4Btn,tab5Btn, tab6Btn);

        tabPanel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
        @Override
        public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab mostRecentlySelectedTab){
//            if (mostRecentlySelectedTab.equals(dayChatTab)){
//                calendarPanel.setOpacity(1);
//                toLabel.setDisable(false);
//                dateChoiceLabel.setDisable(false);
//                minTime.setDisable(false);
//                maxTime.setDisable(false);
//                okBtn.setDisable(false);
//                System.out.println("daytab");
//            }
//            if (mostRecentlySelectedTab.equals(weekChatTab)){
//                calendarPanel.setOpacity(0);
//                toLabel.setDisable(true);
//                dateChoiceLabel.setDisable(true);
//                minTime.setDisable(true);
//                maxTime.setDisable(true);
//                okBtn.setDisable(true);
//                System.out.println("weektab");
//            }
//            if (mostRecentlySelectedTab.equals(monthChatTab)){
//                calendarPanel.setOpacity(0);
//                toLabel.setDisable(true);
//                dateChoiceLabel.setDisable(true);
//                minTime.setDisable(true);
//                maxTime.setDisable(true);
//                okBtn.setDisable(true);
//                System.out.println("monthtab");
//            }
        }
    });

}

}
