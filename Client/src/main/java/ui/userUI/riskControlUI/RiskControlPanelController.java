package ui.userUI.riskControlUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/9/9.
 */
public class RiskControlPanelController implements Initializable {
    @FXML
    private TableView riskTable;
    @FXML
    private AnchorPane historyPricePane;
    @FXML
    private AnchorPane historyProfitPane;
    @FXML
    private TableColumn riskColumn;
    @FXML
    private TableColumn profitRateColumn;
    @FXML
    private TableColumn profitColumn;
    @FXML
    private TableColumn dateColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
