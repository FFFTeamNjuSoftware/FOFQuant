package ui.userUI.portfolioManagementUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.FXMLHelper;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/27.
 */
public class generalAnalysisUIController implements Initializable {
    @FXML
    private Button tab1Btn,tab2Btn,tab3Btn,tab4Btn,tab5Btn,tab6Btn,tab7Btn;
    @FXML
    private ImageView tab1Img,tab2Img,tab3Img,tab4Img,tab5Img,tab6Img,tab7Img;
    @FXML
    private AnchorPane contentPanel;
    private generalAnalysisUIController instance;
    private MainUI mainUI;
    private int k;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance=this;
        mainUI = MainUI.getInstance();
        k=MainUI.getIndex();
        setTab();
        initButton();
    }

    public  void setTab(){
        ImageView[] images = new ImageView[]{tab1Img,tab2Img,tab3Img,tab4Img,tab5Img,tab6Img,tab7Img};

        for(int i=0;i<images.length;i++){
            if(i==k){
                images[i].setVisible(true);
            }else{
                images[i].setVisible(false);
            }
         }
         String panelName="fofAnalysis"+(k+1);
         AnchorPane pane = FXMLHelper.loadPanel(panelName);
        if(contentPanel.getChildren()!=null){
            contentPanel.getChildren().addAll(pane);
        }else{
            System.out.println("The contentPanel children is Null!");
        }
    }

    public void initButton(){
        Button[] buttons= new Button[]{tab1Btn,tab2Btn,tab3Btn,tab4Btn,tab5Btn,tab6Btn,tab7Btn};
        ImageView[] images = new ImageView[]{tab1Img,tab2Img,tab3Img,tab4Img,tab5Img,tab6Img,tab7Img};
        int p=0;
        for ( int i =0; i < buttons.length; i++) {
            int j = i;
//            buttons[i].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
//                images[j].setVisible(true);
//            });
            buttons[i].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
                images[j].setVisible(true);
                for(int s=0;s<buttons.length;s++){
                    if(s!=j){
                        images[s].setVisible(false);
                    }
                }
                String panelName="fofAnalysis"+(j+1);
                AnchorPane pane = FXMLHelper.loadPanel(panelName);
                contentPanel.getChildren().addAll(pane);
            });
//            buttons[i].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
//                if(j!=k) {
//                    images[j].setVisible(false);
//                }
//            });

        }


    }

    @FXML
    private void toAnalyseHomePanel() {
        mainUI.changeScene("user_guidePanel","analyseHomePanel");
    }
}
