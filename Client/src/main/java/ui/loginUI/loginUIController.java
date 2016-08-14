package ui.loginUI;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/14.
 */
public class loginUIController  implements Initializable {

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
}
