package ui.userUI.portfolioManagementUI;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/2.
 */
public class AssetAllocationController implements Initializable {
    @FXML
    private Label combinationTab;
    @FXML
    private Label fundTrendTab;
    @FXML
    private AnchorPane basicPane;
    @FXML
    private TableView solidProfitTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTab() {

    }

    public void initTable() {
        ObservableList<TableColumn> columns = solidProfitTable.getColumns();
        for (TableColumn column : columns) {
            String id = column.getId();

        }
    }
}