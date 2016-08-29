package ui.userUI.portfolioManagementUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import starter.MainUI;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/27.
 */
public class generalAnalysisUIController implements Initializable {
    @FXML
    private TabPane tabPanel;
    @FXML
    private Button tab1Btn,tab2Btn,tab3Btn,tab4Btn,tab5Btn,tab6Btn,tab7Btn;
    @FXML
    private ImageView tab1Img,tab2Img,tab3Img,tab4Img,tab5Img,tab6Img,tab7Img;

    private generalAnalysisUIController instance;
    private MainUI mainUI;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance=this;
        mainUI = MainUI.getInstance();
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
    public  void setTab(int k){
        ImageView[] images = new ImageView[]{tab1Img,tab2Img,tab3Img,tab4Img,tab5Img,tab6Img,tab7Img};

        for(int i=0;i<images.length;i++){
            System.out.println("......"+i+"......");
            if(i==k){
                System.out.println("......visible......");
                images[i].setVisible(true);
            }else{
                System.out.println("......not visible......");
                images[i].setVisible(false);
            }
         }
    }


    @FXML
    private void toAnalyseHomePanel() {
        mainUI.changeScene("user_guidePanel","analyseHomePanel");
    }
}
