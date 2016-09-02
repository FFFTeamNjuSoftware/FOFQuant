package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.RiskProfitIndex;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.fxml.Initializable;

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
        initTable();
    }

    public void initTable(){
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

    }
}
