package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.InvestStyleAnalyse;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/9/2.
 */
public class fofAnalysis72Controller  implements Initializable {
    private BLInterfaces blInterfaces = new BLInterfaces();
    private ProfitFeatureLogic profitFeatureLogic  ;
    private FOFBaseInfoLogic baseInfoLogic;
    private InvestStyleAnalyse InvestStyleAnalyse = new InvestStyleAnalyse();
    private List<InvestStyleAnalyse> investStyleAnalyseList1 = new ArrayList<InvestStyleAnalyse>();
    private List<InvestStyleAnalyse> investStyleAnalyseList2 = new ArrayList<InvestStyleAnalyse>();
    private Map<String,List<String>> mapList;
    private List<String> data1List = new ArrayList<String>();
    private List<String> data2List = new ArrayList<String>();

    @FXML
    private TableView table,table1;
    @FXML
    private TableColumn<InvestStyleAnalyse,String> codeColumn,nameColumn,styleColumn,typeColumn,coColumn,
            codeColumn1,nameColumn1,styleColumn1,typeColumn1,coColumn1;
    @FXML
    private TableColumn<InvestStyleAnalyse,Number> yearColumn,winColumn,clearColumn,tenStockColumn,threeColumn,
                                            fiveColumn,tenIndustrySColumn,
            yearColumn1,winColumn1,clearColumn1,tenStockColumn1,threeColumn1,
            fiveColumn1,tenIndustrySColumn1;

    private fofAnalysis72Controller instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance=this;
        profitFeatureLogic = blInterfaces.getProfitFeatureLogic();
        baseInfoLogic = blInterfaces.getFofBaseInfoLogic();
        try {
            mapList=baseInfoLogic.getFundCodeInFOF();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        initTable();
    }

    private void initTable(){
        //投资风格分析
        data1List = mapList.get("000011");
        data2List = mapList.get("000012");

        try {
            for(String code:data1List) {
                InvestStyleAnalyse = profitFeatureLogic.getInvestStyleAnalyse(code);
                investStyleAnalyseList1.add(InvestStyleAnalyse);
            }
            for(String code1:data2List){
                System.out.println();
                InvestStyleAnalyse = profitFeatureLogic.getInvestStyleAnalyse(code1);
                investStyleAnalyseList2.add(InvestStyleAnalyse);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

            table1.setItems(FXCollections.observableArrayList(investStyleAnalyseList1));

            table.setItems(FXCollections.observableArrayList(investStyleAnalyseList2));


        codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().code));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().name));
        styleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().investStyle));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().investType));
        coColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().manageCompany));
        yearColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().aveHoldTime));
        winColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().holdProfitRate));
        clearColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().holdNetWorthRate));
        tenStockColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topTenStockRate));
        threeColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topThreeIndustryRate));
        fiveColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topFiveIndustryRate));
        tenIndustrySColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topTenIndustryRate));

        codeColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().code));
        nameColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().name));
        styleColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().investStyle));
        typeColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().investType));
        coColumn1.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().manageCompany));
        yearColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().aveHoldTime));
        winColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().holdProfitRate));
        clearColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().holdNetWorthRate));
        tenStockColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topTenStockRate));
        threeColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topThreeIndustryRate));
        fiveColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topFiveIndustryRate));
        tenIndustrySColumn1.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().topTenIndustryRate));

    }
}
