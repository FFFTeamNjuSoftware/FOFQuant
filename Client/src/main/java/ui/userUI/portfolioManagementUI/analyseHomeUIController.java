package ui.userUI.portfolioManagementUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    @FXML
    private ImageView img1,img2,img3,img4,img5,img6,img7;

    private MainUI mainUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyseHomeUIController=this;
        mainUI = MainUI.getInstance();
        initButton();
    }

    @FXML
    public void toMonitorPanel(){
     //   FXMLLoader fxmlLoader=new FXMLLoader(MainUI.class.getResource("generalAnalysisPanel.fxml"));
    //    generalAnalysisUIController=fxmlLoader.getController();
        generalAnalysisUIController.setTab(0);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }

    private void initButton(){
        Button[] buttons = new Button[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7};
        ImageView[] imgs = new ImageView[]{img1,img2,img3,img4,img5,img6,img7};
        for (int i=0;i<buttons.length;i++){
            int j=i;
            buttons[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
         //       imgs[j].setImage(new Image("../resources/images/homepageButtonEnter"+(j+1)+".png"));
            });
            buttons[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
          //      imgs[j].setImage(new Image("../images/homepageButton"+(j+1)+".png"));
            });
        }
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
        mainUI.changeScene("user_guidePanel","fofAnalysis2");
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
