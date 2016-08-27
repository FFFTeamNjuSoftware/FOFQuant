package ui.headUI;

import RMIModule.BLInterfaces;
import beans.CodeName;
import bl.BaseInfoLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import starter.MainUI;

import java.awt.event.KeyAdapter;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class headUIController implements Initializable {

    @FXML
    private Button minBtn,exitBtn,fullBtn,searchBtn;
    @FXML
    private TextField searchTextField;
    private BLInterfaces  blInterfaces;
    private BaseInfoLogic baseInfoLogic;
    private List<CodeName> searchList;
    private  headUIController instance;
    private String searchID;
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
        MainUI.getPrimaryStage().close();
    }
    @FXML
    public void  toMinScreen(){
        MainUI.getPrimaryStage().setIconified(true);
    }


    public void search(){
        baseInfoLogic= blInterfaces.getBaseInfoLogic();

        searchTextField.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {

            try {
                searchList =baseInfoLogic.fuzzySearch(e.getCharacter());

                ////
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

        });

    }
    @FXML
    public void toSearchFund(){
        String id =searchTextField.getText();
        System.out.println("......search result......"+id);

    }

}
