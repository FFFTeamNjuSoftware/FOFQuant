package ui.headUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import starter.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class headUIController implements Initializable {

    @FXML
    private Button minBtn,exitBtn,fullBtn;
    private  headUIController instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        buttonInit();
    }


    public void buttonInit(){
        minBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
        });

        minBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
        });

        minBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: transparent;");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.6");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.6");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: transparent;");
        });

        fullBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            fullBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.6");
        });

        fullBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            fullBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.6");
        });

        fullBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            fullBtn.setStyle("-fx-background-color: transparent;");
        });
    }

    @FXML
    public void  toFullScreen(){

    }
    @FXML
    public void  toExitScreen(){
        Main.getPrimaryStage().close();
    }
    @FXML
    public void  toMinScreen(){
        Main.getPrimaryStage().setIconified(true);
    }


}
