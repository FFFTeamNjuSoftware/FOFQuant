package ui.loginUI;

import beans.UserInfo;
import bl.UserLogic;
import exception.ObjectNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import starter.Main;
import util.UserType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/14.
 */
public class loginUIController  implements Initializable {

    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button minBtn,exitBtn,loginBtn;
    String name=null;


    private static loginUIController instance;

    private UserLogic userLogic ;
    private UserInfo userInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        init();
    }
    public void init(){
//        userNameField.setCaretColor(Color.WHITE);
   //     userNameField.setForeground(Color.WHITE);
        userNameField.setPromptText("用户名");
        passwordField.setPromptText("密码");
        buttonInit();
    }

    @FXML
    public void toLogin(){
        String userName = userNameField.getText();
        String password = passwordField.getText();
        System.out.println(userName+"..."+password);

        try {
            userInfo = userLogic.loginIn(userName,password);
            if(userInfo.userType== UserType.MANAGER){
                //进入管理员界面
                System.out.println("......manager......");
                name = userName;
                Main.enterInitPanel(1,name);
            }else if(userInfo.userType== UserType.NORMAL){
                //进入普通用户界面
                name = userName;
                System.out.println("......normal......");
                Main.enterInitPanel(0,name);
            }else{
                System.out.println("......login fail......");
            }
            ///////////////////////////////////////////////
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("......RemoteException......");
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            System.out.println("......ObjectNotFoundException......");
        }catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("......NullPointerException......");
        }


    }

    public void buttonInit(){
        minBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
        });

        minBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
        });

//Removing the shadow when the mouse cursor is off
        minBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            minBtn.setStyle("-fx-background-color: transparent;");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
        });

        exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            exitBtn.setStyle("-fx-background-color: transparent;");
        });

        loginBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            loginBtn.setStyle("-fx-background-color: #87CCF3;");
        });

        loginBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            loginBtn.setStyle("-fx-background-color: #1F77B9;");
        });

        loginBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            loginBtn.setStyle("-fx-background-color: #23a3f3;");
        });
    }

    @FXML
    public void toMinScreen(){
        Main.getPrimaryStage().setIconified(true);
    }

    @FXML
    public void toExitScreen(){
        Main.getPrimaryStage().close();
    }
}
