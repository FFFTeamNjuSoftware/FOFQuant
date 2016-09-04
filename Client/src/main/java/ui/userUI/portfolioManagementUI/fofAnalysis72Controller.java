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
    private AnchorPane panel;
    @FXML
    private TableView table;
    @FXML
    private TableColumn<InvestStyleAnalyse,String> codeColumn,nameColumn,styleColumn,typeColumn,coColumn;
    @FXML
    private TableColumn<InvestStyleAnalyse,Number> yearColumn,winColumn,clearColumn,tenStockColumn,threeColumn,
                                            fiveColumn,tenIndustrySColumn;
    private String[] types = new String[]{"权益类", "固定收益类"};
    private TitledPane[] tps = new TitledPane[types.length];
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
        initBack();
    }
    private void initBack(){
        TableView table1 = initTable(0);
        InvestStyleAnalyse = new InvestStyleAnalyse();
        table.getItems().clear();
     //   TableView table2 = initTable(1);
        TableView[] tables = new TableView[]{table1,null};
        final Accordion accordion =new Accordion ();

        for (int i = 0; i <types.length; i++) {
            tps[i] = new TitledPane(types[i],tables[i]);
        }
        accordion.getPanes().addAll(tps);
        accordion.setLayoutX(30);
        accordion.setLayoutY(20);
        accordion.setMaxHeight(400);
        accordion.setPrefWidth(865);
        accordion.setStyle("-fx-background-color: transparent;");
        accordion.setExpandedPane(tps[0]);
        panel.getChildren().addAll(accordion);
    }

    private TableView initTable(int i){
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
        if(i==0) {
            table.setItems(FXCollections.observableArrayList(investStyleAnalyseList1));
        }else if(i==1){
            table.setItems(FXCollections.observableArrayList(investStyleAnalyseList2));
        }

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
        return table;
    }
}
