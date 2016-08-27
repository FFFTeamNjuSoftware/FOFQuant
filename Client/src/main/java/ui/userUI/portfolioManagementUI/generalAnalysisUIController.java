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
        tabPanel.getChildrenUnmodifiable().addAll(tab1Btn, tab2Btn, tab3Btn, tab4Btn,tab5Btn, tab6Btn);

        tabPanel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
        @Override
        public void changed(ObservableValue<? extends Tab> arg0, Tab arg1, Tab mostRecentlySelectedTab){
            if (mostRecentlySelectedTab.equals(tab1Btn)){

                System.out.println("...tab1Btn...");
            }
            if (mostRecentlySelectedTab.equals(tab2Btn)){

                System.out.println("...tab2Btn...");
            }
            if (mostRecentlySelectedTab.equals(tab3Btn)){

                System.out.println("...tab3Btn...");
            }
            if (mostRecentlySelectedTab.equals(tab4Btn)){

                System.out.println("...tab4Btn...");
            }
            if (mostRecentlySelectedTab.equals(tab5Btn)){

                System.out.println("...tab5Btn...");
            }
            if (mostRecentlySelectedTab.equals(tab6Btn)){

                System.out.println("...tab6Btn...");
            }
        }
    });

}

}
