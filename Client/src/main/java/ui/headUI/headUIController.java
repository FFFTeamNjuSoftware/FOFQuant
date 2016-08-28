package ui.headUI;

import RMIModule.BLInterfaces;
import beans.CodeName;
import bl.BaseInfoLogic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.IOHelper;

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
    private Button minBtn, exitBtn, fullBtn, searchBtn;
    @FXML
    private TextField searchTextField;
    private BLInterfaces blInterfaces;
    private BaseInfoLogic baseInfoLogic;
    private List<CodeName> searchList;
    private headUIController instance;
    private String searchID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        buttonInit();
        handleSearch();
    }


    public void buttonInit() {
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
    public void toFullScreen() {

    }

    @FXML
    public void toExitScreen() {
        MainUI.getPrimaryStage().close();
    }

    @FXML
    public void toMinScreen() {
        MainUI.getPrimaryStage().setIconified(true);
    }


    public void handleSearch() {
        baseInfoLogic = blInterfaces.getBaseInfoLogic();
        searchTextField.setOnKeyReleased((e) -> {
            try {
                searchList = baseInfoLogic.fuzzySearch(e.getText());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            if (searchList != null) {
                AnchorPane pane = MainUI.getInstance().getPanel();
                ObservableList<String> list = FXCollections.<String>observableArrayList();
                for(CodeName codeName: searchList){
                    list.add(codeName.name+":"+codeName.code);
                }
                ListView<String> listView = new ListView<>(list);
                listView.setOrientation(Orientation.VERTICAL);
                listView.setPrefSize(170, 200);
                listView.setLayoutX(40);
                listView.setLayoutY(0);
                //listView.getStylesheets().add("Client/src/main/resources/css/stageView.css");
                pane.getChildren().add(listView);
                listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> ov,
                                        final String oldvalue, final String newvalue) {
                        String[] strs = newvalue.toString().split(":");
                        IOHelper.writeName(strs[1]);
                        MainUI.getInstance().changeScene("user_guidePanel","marketPanel");
                    }
                });

            }
        });


    }

    @FXML
    public void toSearchFund() {
        String id = searchTextField.getText();


    }

}
