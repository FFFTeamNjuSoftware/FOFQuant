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
    public static final String GENERAL_ANALYSIS_PANEL = "generalAnalysisPanel";
    private analyseHomeUIController analyseHomeUIController;
    private generalAnalysisUIController generalAnalysisUIController= new generalAnalysisUIController();
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
        mainUI.setIndex(0);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toBreakevenPanel(){
        mainUI.setIndex(1);
        // generalAnalysisUIController.setK(1);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }

    @FXML
    public void toAttributionPanel(){
        mainUI.setIndex(2);
        //  generalAnalysisUIController.setK(2);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toReturnStatsPanel(){
        mainUI.setIndex(3);
        //  generalAnalysisUIController.setK(3);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toAssetAllocationPanel(){
        mainUI.setIndex(4);
        //   generalAnalysisUIController.setK(4);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }

    @FXML
    public void toChangePositionPanel(){
        mainUI.setIndex(5);
        //  generalAnalysisUIController.setK(5);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }
    @FXML
    public void toAppraisalPanel(){
        mainUI.setIndex(6);
        //  generalAnalysisUIController.setK(6);
        mainUI.changeScene("user_guidePanel","generalAnalysisPanel");
    }

}
