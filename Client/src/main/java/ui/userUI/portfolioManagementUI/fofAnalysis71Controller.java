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
import javafx.scene.layout.Background;
import ui.util.FXMLHelper;

import java.io.WriteAbortedException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/9/2.
 */
public class fofAnalysis71Controller implements Initializable {
    private BLInterfaces blInterfaces = new BLInterfaces();
    private ProfitFeatureLogic profitFeatureLogic  ;
    private FOFBaseInfoLogic baseInfoLogic;
    private RiskProfitIndex riskProfitIndex = new RiskProfitIndex();
    private List<RiskProfitIndex> riskProfitIndexList1 = new ArrayList<RiskProfitIndex>();
    private List<RiskProfitIndex> riskProfitIndexList2 = new ArrayList<RiskProfitIndex>();
    private Map<String,List<String>> mapList;
    private List<String> data1List = new ArrayList<String>();
    private List<String> data2List = new ArrayList<String>();
    @FXML
    private AnchorPane panel;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<RiskProfitIndex,String> codeColumn,nameColumn,typeColumn,coColumn;
    @FXML
    private TableColumn<RiskProfitIndex,Number> alphaColumn,betaColumn,sharpColumn,treynorColumn,jensenColumn,
                                        aveBenefitColumn,aveRiskColumn,benefitSColumn,yearSColumn;
    private String[] types = new String[]{"权益类", "固定收益类"};
    private TitledPane[] tps = new TitledPane[types.length];

    private fofAnalysis71Controller instance;
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
        initBack();
    }

    private void initBack(){
        TableView table1 = initTable(0);
        riskProfitIndex = new RiskProfitIndex();
        table.getItems().clear();
          TableView table2 = initTable(1);
        TableView[] tables = new TableView[]{table1,table2};
     //   TableView[] tables = new TableView[]{initTable(0),initTable(1)};
        final Accordion accordion =new Accordion ();

        for (int i = 0; i <types.length; i++) {
            tps[i] = new TitledPane(types[i],tables[i]);
        }
        accordion.getPanes().addAll(tps);
        accordion.setLayoutX(30);
        accordion.setLayoutY(20);
        accordion.setPrefHeight(400);
        accordion.setPrefWidth(865);
        accordion.setStyle("-fx-background-color: transparent;");
        accordion.setExpandedPane(tps[0]);
        panel.getChildren().addAll(accordion);
    }
    private TableView initTable(int i){
        //风险收益指数
        data1List = mapList.get("000011");
        data2List = mapList.get("000012");

        try {
            for(String code:data1List){
                System.out.println("...code1..."+code+"......");
                riskProfitIndex = new RiskProfitIndex();
                riskProfitIndex = profitFeatureLogic.getRiskProfitIndex(code);
                riskProfitIndexList1.add(riskProfitIndex);
            }
            for(String code:data2List){
                System.out.println("...code2..."+code+"......");
                riskProfitIndex = new RiskProfitIndex();
                riskProfitIndex = profitFeatureLogic.getRiskProfitIndex(code);
                riskProfitIndexList2.add(riskProfitIndex);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if(i==0) {
            table.getItems().clear();
            table.setItems(FXCollections.observableArrayList(riskProfitIndexList1));
        }else if(i==1){
            table.getItems().clear();
            table.setItems(FXCollections.observableArrayList(riskProfitIndexList2));
        }
        codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().code));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().name));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().investType));
        coColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().manageCompany));
        alphaColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().alpha));
        betaColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().beta));
        sharpColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().sharpe));
        treynorColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().treynor));
        jensenColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().jensen));
        aveBenefitColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().aveProfit));
        aveRiskColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().aveRiskProfit));
        benefitSColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().profitSd));
        yearSColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().yearWaveRate));
        return table;
    }
}
