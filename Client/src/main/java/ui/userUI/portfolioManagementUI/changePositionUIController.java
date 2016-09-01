package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import beans.PositionChange;
import bl.fof.FOFAssetAllocationLogic;
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
    private FOFAssetAllocationLogic assetAllocationLogic;
    private List<PositionChange> positionChangeList;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<PositionChange,String> opDateColumn,changeDateColumn,idColumn,nameColumn;
    @FXML
    private TableColumn<PositionChange,Number> inNumColumn,inPriceColumn,outNumColumn,outPriceColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        assetAllocationLogic = blInterfaces.getFofAssetAllocationLogic();
        initTable();
    }

    public void initTable(){
        try {
            positionChangeList= assetAllocationLogic.getFOFPositionChanges();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        table.setItems(FXCollections.observableArrayList(positionChangeList));

        opDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().changeDate));
        changeDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().changeTime));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().fundCode));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().fundName));

        inNumColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().buyNum));
        inPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                        cellData.getValue().buyPrice));
        outNumColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().saleNum));
        outPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().salePrice));
    }

}
