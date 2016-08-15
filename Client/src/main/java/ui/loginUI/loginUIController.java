package ui.loginUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import starter.Main;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/14.
 */
public class loginUIController  implements Initializable {

    @FXML
    private Button minBtn,exitBtn;

    private static loginUIController instance;

    public static loginUIController getInstance() {
        System.out.println("here is the instance of loginUIController ");
        return instance == null ? (instance = new loginUIController()) : instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        init();
    }
    public void init(){

    }

    @FXML
    public void toLogin(){

    }

    @FXML
    public void toMinScreen(){
        System.out.println("min");
        Main.getPrimaryStage().setIconified(true);
    }
    @FXML
    public void toExitScreen(){
        System.out.println("Exit");
        Main.getPrimaryStage().close();

    }
}
