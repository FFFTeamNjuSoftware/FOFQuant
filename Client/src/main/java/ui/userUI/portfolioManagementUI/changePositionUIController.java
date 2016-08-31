package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.TimeType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/29.
 */
public class changePositionUIController implements Initializable {
    private changePositionUIController instance;
    private BLInterfaces blInterfaces = new BLInterfaces();
    private FOFProfitAnalyseLogic profitAnalyseLogic;
    private FOFProfitAnalyse profitAnalyse_three, profitAnalyse_half, profitAnalyse_year,profitAnalyse_establish;
    @FXML
    private TableView table;
    //@FXML
   // private TableColumn<> opDateColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTable();
    }

    public void initTable(){

    }

}
