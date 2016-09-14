package ui.mangerUI.systemBlogUI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/16.
 */
public class systemLogUIController implements Initializable {

    @FXML
    private TableView table;
    @FXML
    private TableColumn nameColumn,operationColumn,dateColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       initData();
    }
    private void init(){

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<Log,String>("name")
        );
        operationColumn.setCellValueFactory(
                new PropertyValueFactory<Log,String>("operation")
        );
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<Log,String>("date")
        );


    }
    private void initData(){
        final ObservableList<Log> data= FXCollections.observableArrayList(new Log("yyf","获取所有用户信息","2016/9/5 7:56"),
                new Log("yyf","修改用户txy密码,旧密码:123456，新密码:65432","2016/9/5 8:01"),
                new Log("txy","登录","016/9/5 8:02") ,
                new Log("txy","注销登录","2016/9/5 8:02"),
                new Log("tj","登录","2016/9/5 8:03"),
                new Log("tj","创建FOF组合","2016/9/5 8:05"),
                new Log("tj","查看所有基金行情信息","2016/9/5 8:05"),
                new Log("tj","查看000001(华夏大盘）基金信息","2016/9/5 8:06"),
                new Log("tj","进行仓位调整","2016/9/5 8:07"),
                new Log("tj","查看FOF组合实时监控信息","2016/9/5 8:09"),
                new Log("yyf","查看组合管理，资产配置、基金走势、回报统计、业绩归因、盈亏分析、实时监控","2016/9/4 7:56"),
                new Log("yyf","创建组合“我的组合1”","2016/9/4 8:01"),
                new Log("txy","登录","2016/9/4 8:02"),
                new Log("txy","注销登录","2016/9/4 8:02"),
                new Log("tj","登录","2016/9/4 8:03"),
                new Log("tj","创建FOF组合","2016/9/4 8:05"),
                new Log("tj","查看所有基金行情信息","2016/9/4 8:05"),
                new Log("tj","查看000001(华夏大盘）基金信息","2016/9/4 8:06"),
                new Log("tj","进行仓位调整","2016/9/4 8:07"),
                new Log("tj","查看FOF组合实时监控信息","2016/9/3 8:09"),
                new Log("yyf","获取所有用户信息","2016/9/3 7:56"),
                new Log("yyf","查看基金信息","2016/9/3 8:01"),
                new Log("txy","登录","2016/9/3 8:02"),
                new Log("txy","注销登录","2016/9/3 8:02"),
                new Log("tj","登录","2016/9/3 8:03"),
                new Log("tj","t创建FOF组合","2016/9/3 8:05"),
                new Log("tj","查看所有基金行情信息","2016/9/5 8:05"),
                new Log("tj","查看000001(华夏大盘）基金信息","2016/9/5 8:06"),
                new Log("tj","进行仓位调整","2016/9/5 8:07"),
                new Log("tj","查看FOF组合实时监控信息","2016/9/5 8:09") );
        init();
        table.setItems(data);
    }
    public static class Log{
        private final SimpleStringProperty name;
        private final SimpleStringProperty operation;
        private final SimpleStringProperty date;

        private Log(String name,String operation,String date){
            this.name = new SimpleStringProperty(name);
            this.operation = new SimpleStringProperty(operation);
            this.date = new SimpleStringProperty(date);
        }


        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getOperation() {
            return operation.get();
        }

        public SimpleStringProperty operationProperty() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation.set(operation);
        }

        public String getDate() {
            return date.get();
        }

        public SimpleStringProperty dateProperty() {
            return date;
        }

        public void setDate(String date) {
            this.date.set(date);
        }
    }

}
