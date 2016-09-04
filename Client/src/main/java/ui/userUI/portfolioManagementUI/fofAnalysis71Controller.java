package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

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
    private TreeTableView treeTable;
    @FXML
    private TreeTableColumn<RiskProfitIndex,String> codeColumn,nameColumn,typeColumn,coColumn;
    @FXML
    private TreeTableColumn<RiskProfitIndex,Number> alphaColumn,betaColumn,sharpColumn,treynorColumn,jensenColumn,
                                        aveBenefitColumn,aveRiskColumn,benefitSColumn,yearSColumn;

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
        System.out.println("....71..");
        initTable();
    }

    public void initTable(){
        final TreeItem<String> childNode1 = new TreeItem<>("权益类");
        final TreeItem<String> childNode2 = new TreeItem<>("固定收益类");

        //Creating the root element
 //       final TreeItem<String> root = new TreeItem<>();
        childNode1.setExpanded(true);
        childNode2.setExpanded(true);
        //Adding tree items to the root
  //      root.getChildren().setAll(childNode1, childNode2);

        //风险收益指数
        data1List = mapList.get("000011");
        data2List = mapList.get("000012");

        try {
            for(String code:data1List){
                riskProfitIndex = profitFeatureLogic.getRiskProfitIndex(code);
                riskProfitIndexList1.add(riskProfitIndex);
            }
            for(String code:data2List){
                riskProfitIndex = profitFeatureLogic.getRiskProfitIndex(code);
                riskProfitIndexList2.add(riskProfitIndex);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        riskProfitIndexList1.stream().forEach((RiskProfitIndex) -> {
            childNode1.getChildren().add(new TreeItem<>());
        });

        riskProfitIndexList2.stream().forEach((RiskProfitIndex) -> {
            childNode2.getChildren().add(new TreeItem<>());
        });

        codeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().code)
        );
        nameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().name)
        );
        typeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().investType)
        );
        coColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().manageCompany)
        );
        alphaColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().alpha)
        );
        betaColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().beta)
        );
        sharpColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().sharpe)
        );
        treynorColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().treynor)
        );
        jensenColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().jensen)
        );
        aveBenefitColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().aveProfit)
        );
        aveRiskColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().aveRiskProfit)
        );
        benefitSColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().profitSd)
        );
        yearSColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<RiskProfitIndex, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().yearWaveRate)
        );

    }
}
