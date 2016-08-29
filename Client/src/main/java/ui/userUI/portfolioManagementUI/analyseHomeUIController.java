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
    private generalAnalysisUIController  generalAnalysisUIController =new  generalAnalysisUIController();

    @FXML
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    @FXML
    private ImageView Img1,Img2,Img3,Img4,Img5,Img6,Img7;

    private MainUI mainUI;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analyseHomeUIController=this;
     //   FXMLLoader fxmlLoader=new FXMLLoader(generalAnalysisUIController.class.getResource("generalAnalysisPanel.fxml"));
     //     generalAnalysisUIController=fxmlLoader.getController();
        mainUI = MainUI.getInstance();
        initButton();
    }

    private void initButton(){
        Button[] buttons = new Button[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7};
        ImageView[] imgs = new ImageView[]{Img1,Img2,Img3,Img4,Img5,Img6,Img7};
        for (int i=0;i<buttons.length;i++){
            int j=i;
            buttons[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
               imgs[j].setImage(new Image(this.getClass().getResourceAsStream("/images/homepageButtonEnter"+(j+1)+".png")));
            });
            buttons[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                imgs[j].setImage(new Image(this.getClass().getResourceAsStream("/images/homepageButton"+(j+1)+".png")));
            });
        }
    }
    @FXML
    public void toMonitorPanel(){
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
