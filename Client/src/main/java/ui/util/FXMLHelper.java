package ui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tj on 2016/8/17.
 */
public class FXMLHelper {
    public static AnchorPane loadPanel(String fxml) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainUI.class.getClassLoader().getResource(fxml+".fxml"));
        AnchorPane result = null;
        try {
            result = loader.load();
        } catch (IOException e) {
            System.out.println(fxml + "加载失败");
            e.printStackTrace();
        }
        return result;
    }
}
