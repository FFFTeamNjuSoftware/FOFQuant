package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.InvestStyleAnalyse;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
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
    private TreeTableView treeTable;
    @FXML
    private TreeTableColumn<InvestStyleAnalyse,String> codeColumn,nameColumn,styleColumn,typeColumn,coColumn;
    @FXML
    private TreeTableColumn<InvestStyleAnalyse,Number> yearColumn,winColumn,clearColumn,tenStockColumn,threeColumn,
                                            fiveColumn,tenIndustrySColumn;

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
        System.out.println("....72..");
        initTable();
    }

    public void initTable(){
        //"权益类""固定收益类"
        final TreeItem<String> childNode1 = new TreeItem<>("权益类");
        final TreeItem<String> childNode2 = new TreeItem<>("固定收益类");

        childNode1.setExpanded(true);
        childNode2.setExpanded(true);
        //Adding tree items to the root

        //投资风格分析
        data1List = mapList.get("000011");
        data2List = mapList.get("000012");

        try {
            for(String code:data1List) {
                InvestStyleAnalyse = profitFeatureLogic.getInvestStyleAnalyse(code);
                investStyleAnalyseList1.add(InvestStyleAnalyse);
            }
            for(String code:data2List){
                InvestStyleAnalyse = profitFeatureLogic.getInvestStyleAnalyse(code);
                investStyleAnalyseList2.add(InvestStyleAnalyse);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        investStyleAnalyseList1.stream().forEach((InvestStyleAnalyse) -> {
            childNode1.getChildren().add(new TreeItem<>());
        });
        investStyleAnalyseList2.stream().forEach((InvestStyleAnalyse) -> {
            childNode2.getChildren().add(new TreeItem<>());
        });
        codeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().code)
        );
        nameColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().name)
        );
        styleColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().investStyle)
        );
        typeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().investType)
        );
        coColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().manageCompany)
        );
        yearColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().aveHoldTime)
        );
        winColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().holdProfitRate)
        );
        clearColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().holdNetWorthRate)
        );
        tenStockColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().topTenStockRate)
        );
        threeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().topThreeIndustryRate)
        );
        fiveColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().topFiveIndustryRate)
        );
        tenIndustrySColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<InvestStyleAnalyse, Number> param) ->
                        new ReadOnlyDoubleWrapper(param.getValue().getValue().topTenIndustryRate)
        );


    }
}
