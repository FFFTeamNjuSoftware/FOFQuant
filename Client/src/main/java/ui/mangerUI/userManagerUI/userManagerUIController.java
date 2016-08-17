package ui.mangerUI.userManagerUI;

import RMIModule.BLInterfaces;
import beans.UserManageInfo;
import bl.UserLogic;
import exception.ObjectExistedException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import util.UserType;

import javax.swing.text.Document;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class userManagerUIController  implements Initializable {

    @FXML
    private TableColumn<UserManageInfo, Boolean> selectColumn;
    @FXML
    private TableColumn<UserManageInfo, String> userTypeColumn;
    @FXML
    private TableColumn<UserManageInfo, String> userNameColumn;
    @FXML
    private TableColumn<UserManageInfo, String> nameColumn;
    @FXML
    private TableColumn<UserManageInfo, String> genderColumn;
    @FXML
    private TableColumn<UserManageInfo, String> passwordColumn;
    private Button[] setBtn = new Button[2];
    private Button modifyBtn,deleteBtn;
    @FXML
    private TableColumn<Button[], Button> settingColumn;
    @FXML
    private TableView<UserManageInfo> table;
    @FXML
    private TextField userNameField,nameField,passwordField;
    @FXML
    private ChoiceBox userTypeChoBox,genderChoBox,choiceBox;
    @FXML
    private Label tipLabel;
    private String userType,genderType;
    private String[] userTypes = {"normal", "manager"};
    private String[] genderTypes = {"male", "female"};
    private String[] choiceTypes ={"类型","性别","全部"};

    private BLInterfaces blInterfaces = new BLInterfaces();
    private UserLogic userLogic ;
    private List<UserManageInfo> userManageInfoList = new ArrayList<UserManageInfo>();
    private UserManageInfo userManageInfo = new UserManageInfo();
    private userManagerUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        userLogic= blInterfaces.getUserLogic();
        initButton();
        init();
    }
    public void init(){
        tipLabel.setText(null);

        try {
            userManageInfoList=userLogic.getAllUser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        table.setItems(FXCollections.observableArrayList(userManageInfoList));
        System.out.println(userManageInfoList.get(0).name+"......"+userManageInfoList.get(1).name);

        selectColumn.setCellValueFactory(new PropertyValueFactory<UserManageInfo, Boolean>("select"));
        selectColumn.setCellFactory(new Callback<TableColumn<UserManageInfo, Boolean>, TableCell<UserManageInfo, Boolean>>() {
            @Override
            public TableCell<UserManageInfo, Boolean> call(
                    TableColumn<UserManageInfo, Boolean> param) {
                CheckBoxTableCell<UserManageInfo, Boolean> cell = new CheckBoxTableCell<>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });


        userTypeColumn.setCellFactory(new Callback<TableColumn<UserManageInfo, String>, TableCell<UserManageInfo, String>>() {
            @Override
            public TableCell<UserManageInfo, String> call(TableColumn<UserManageInfo, String> arg0) {
                return new TableCell<UserManageInfo, String>() {
                    ObservableValue ov1;
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (this.getIndex()<userManageInfoList.size()-1&&this.getIndex()>=0) {
                            if (userManageInfoList.get(this.getIndex()).userType.equals(UserType.MANAGER)) {
                                this.setText("管理员");
                            } else if (userManageInfoList.get(this.getIndex()).userType.equals(UserType.NORMAL))
                                this.setText("普通用户");
                        }
                    }
                };
            }
        });

        userNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().username));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().name));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().gender));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().password));
        settingColumn
                .setCellValueFactory(new PropertyValueFactory< Button[],  Button>(
                        "设置"));
//        settingColumn
//                .setCellFactory(new Callback<TableColumn< Button[], Button>, TableCell< Button[],Button>>() {
//                    @Override
//                    public TableCell< Button[], Button> call(
//                            TableColumn< Button[], Button> param) {
//                        return new ChartTableCell();
//                    }
//                });
        userTypeChoBox.setValue("normal");
        genderChoBox.setValue("male");
        choiceBox.setValue("全部");
        userTypeChoBox.setItems(FXCollections.observableArrayList(userTypes));
        genderChoBox.setItems(FXCollections.observableArrayList(genderTypes));
        choiceBox.setItems(FXCollections.observableArrayList(choiceTypes));

    }

    public void initButton(){
        modifyBtn = new Button("修改");
        deleteBtn = new Button("删除");
        modifyBtn = setBtn[0];
        deleteBtn = setBtn[1];
    }


    @FXML
    public void addNewUser(){
        userManageInfo.name=nameField.getText();
        userManageInfo.username = userNameField.getText();
        userManageInfo.password = passwordField.getText();



        genderChoBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                genderType = t1;
                System.out.println("the selected is?: " + t1);
            }
        });

        userManageInfo.gender=genderType;

        userTypeChoBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldv,newv)->{
            userType=userTypes[newv.intValue()];
        });

        if (userType.equalsIgnoreCase("manager"))
            userManageInfo.userType = UserType.MANAGER;
        else if (userType.equalsIgnoreCase("normal"))
            userManageInfo.userType = UserType.NORMAL;

        try {
            userLogic.addUser(userManageInfo);
            tipLabel.setText("已添加！");
        } catch (RemoteException e) {
            e.printStackTrace();
            tipLabel.setText("RemoteException！");
        } catch (ObjectExistedException e) {
            e.printStackTrace();
            tipLabel.setText("该用户已存在！");
        }

    }
    @FXML
    public void deleteAllSelected(){

    }
    @FXML
    public void showAddList(){

    }
}
