package ui.mangerUI.userManagerUI;

import RMIModule.BLInterfaces;
import beans.UserManageInfo;
import bl.UserLogic;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import starter.MainUI;
import util.UserType;

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
    private TableColumn<UserManageInfo, String> userNameColumn,userTypeColumn,nameColumn,genderColumn,passwordColumn;
   @FXML
    private Button modifyBtn,deleteBtn;
    @FXML
    private TableView<UserManageInfo> table;
    @FXML
    private TextField userNameField,nameField,passwordField;
    @FXML
    private ChoiceBox userTypeChoBox,genderChoBox,choiceBox;
    @FXML
    private Label tipLabel;
    private int selectedIndex;
    private String userType,genderType;
    private String[] userTypes = {"normal", "manager"};
    private String[] genderTypes = {"male", "female"};
    private String[] choiceTypes ={"类型","性别","全部"};

    private BLInterfaces blInterfaces = new BLInterfaces();
    private UserLogic userLogic ;
    private List<UserManageInfo> userManageInfoList = new ArrayList<UserManageInfo>();
    private UserManageInfo userManageInfo = new UserManageInfo();
    private UserManageInfo updateUserManageInfo = new UserManageInfo();
    private userManagerUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        userLogic= blInterfaces.getUserLogic();
        initButton();
        init();
    }
    private void init(){
        tipLabel.setText(null);

        try {
            userManageInfoList=userLogic.getAllUser();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        table.setItems(FXCollections.observableArrayList(userManageInfoList));
        table.setRowFactory(new Callback<TableView<UserManageInfo>, TableRow<UserManageInfo>>() {
            @Override
            public TableRow<UserManageInfo> call(TableView<UserManageInfo> table) {
                // TODO Auto-generated method stub

                return new TableRowControl(table);
            }
        });
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


        userTypeChoBox.setValue("normal");
        genderChoBox.setValue("male");
        choiceBox.setValue("全部");
        userTypeChoBox.setItems(FXCollections.observableArrayList(userTypes));
        genderChoBox.setItems(FXCollections.observableArrayList(genderTypes));
        choiceBox.setItems(FXCollections.observableArrayList(choiceTypes));

    }

    public void initButton(){

        genderChoBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldv,newv)->{
            genderType=genderTypes[newv.intValue()];
            System.out.println("the selected is?: " + genderType);
        });

        userTypeChoBox.getSelectionModel().selectedIndexProperty().addListener((ov,oldv,newv)->{
            userType=userTypes[newv.intValue()];
            System.out.println("the selected userType is?: " + userType);
        });

    }

    @FXML
    private void ensureDelete(){
        updateUserManageInfo = new UserManageInfo();
        getSelectedInfo();
        try {
            userLogic.deleteUser(updateUserManageInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ensureModify(){
        getSelectedInfo();
        try {
            userLogic.updateUserInfo(updateUserManageInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addNewUser(){
        userManageInfo.name=nameField.getText();
        userManageInfo.username = userNameField.getText();
        userManageInfo.password = passwordField.getText();

        userManageInfo.gender=genderType;

        if (userType.equalsIgnoreCase("manager"))
            userManageInfo.userType = UserType.MANAGER;
        else if (userType.equalsIgnoreCase("normal"))
            userManageInfo.userType = UserType.NORMAL;

        try {
            userLogic.addUser(userManageInfo);
           // tipLabel.setText("已添加！");
            init();
            MainUI.getInstance().displaySuccessPane();
        } catch (RemoteException e) {
            e.printStackTrace();
            tipLabel.setText("RemoteException！");
        } catch (ObjectExistedException e) {
            e.printStackTrace();
            tipLabel.setText("该用户已存在！");
        }

    }

    @FXML
    private void deleteAllSelected(){
        try {
            userLogic.deleteUser(updateUserManageInfo);
            init();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void showAddList(){
        userNameField.clear();
        nameField.clear();
        passwordField.clear();
    }

    @FXML
    private void updateAllUser(){
            init();
    }

    public void getSelectedInfo(){
        table.getSelectionModel().selectedIndexProperty().addListener((ov,oldv,newv)->{
            System.out.println("......selected....id..."+selectedIndex);
            selectedIndex =newv.intValue();
            updateUserManageInfo.name  =nameColumn.getCellData(selectedIndex);
            updateUserManageInfo.password = passwordColumn.getCellData(selectedIndex);
            updateUserManageInfo.username = userNameColumn.getCellData(selectedIndex);
            updateUserManageInfo.gender = genderColumn.getCellData(selectedIndex);

            if(userTypeColumn.getCellData(selectedIndex).equals("管理员")){
                updateUserManageInfo.userType=UserType.MANAGER;
            }else if(userTypeColumn.getCellData(selectedIndex).equals("普通用户")){
                updateUserManageInfo.userType=UserType.NORMAL;
            }else{
                System.out.println("......get user type fail......");
                updateUserManageInfo.userType=UserType.MANAGER;
            }
        });
    }

    public class TableRowControl<T> extends TableRow<T> {

        public TableRowControl(TableView<T> tableView) {
            super();
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton().equals(MouseButton.PRIMARY)
                            && event.getClickCount() == 2
                            && TableRowControl.this.getIndex() < table.getItems().size()) {
                        //doSomething
                        System.out.println("double click");
                        userNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        userTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        genderColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                        passwordColumn.setCellFactory(TextFieldTableCell.forTableColumn());

                    }
                    if (event.getButton().equals(MouseButton.PRIMARY)
                            && event.getClickCount() == 1
                            && TableRowControl.this.getIndex() < table.getItems().size()) {
                    System.out.println("......update info......"+selectedIndex);
                    System.out.println("......======......"+nameColumn.getCellData(selectedIndex));
                    selectedIndex = userManagerUIController.TableRowControl.this.getIndex();
                    updateUserManageInfo.name  =nameColumn.getCellData(selectedIndex);
                    updateUserManageInfo.password = passwordColumn.getCellData(selectedIndex);
                    updateUserManageInfo.username = userNameColumn.getCellData(selectedIndex);
                    updateUserManageInfo.gender = genderColumn.getCellData(selectedIndex);

                    if(userTypeColumn.getCellData(selectedIndex).equals("管理员")){
                        updateUserManageInfo.userType=UserType.MANAGER;
                    }else if(userTypeColumn.getCellData(selectedIndex).equals("普通用户")){
                        updateUserManageInfo.userType=UserType.NORMAL;
                    }else{
                        System.out.println("......get user type fail......");
                        updateUserManageInfo.userType=UserType.MANAGER;
                    }
                }}
            });
        }
    }
}
