package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.InvestStyleAnalyse;
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
public class fofAnalysis72Controller  implements Initializable {
    private BLInterfaces blInterfaces = new BLInterfaces();
    private ProfitFeatureLogic profitFeatureLogic  ;
    private FOFBaseInfoLogic baseInfoLogic;
    private beans.InvestStyleAnalyse InvestStyleAnalyse = new InvestStyleAnalyse();
    private List<InvestStyleAnalyse> investStyleAnalyseList1 = new ArrayList<InvestStyleAnalyse>();
    private List<InvestStyleAnalyse> investStyleAnalyseList2 = new ArrayList<InvestStyleAnalyse>();
    private Map<String,List<String>> mapList;
    private List<String> data1List = new ArrayList<String>();
    private List<String> data2List = new ArrayList<String>();

    private fofAnalysis72Controller instance;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance=this;
        initTable();
    }

    public void initTable(){
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
    }
}
