package stubs.logic;

import beans.CodeName;
import beans.FundInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BaseInfoLogicStub  extends UnicastRemoteObject implements BaseInfoLogic{
    public BaseInfoLogicStub() throws RemoteException{

    }

    @Override
    public List<String> getFundCodes() throws RemoteException {
        List<String>  fundCodeList= new ArrayList<String>();
        fundCodeList.add("001547");
        fundCodeList.add("001714");
        return fundCodeList;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException {
        List<CodeName>  codeNameList= new ArrayList<CodeName>();
        CodeName codeName=new CodeName();
        codeName.setCode("001547");
        codeName.setName("兴业聚惠A");
        codeNameList.add(codeName);
        return codeNameList;
    }

    @Override
    public FundInfo getFundBaseInfo() throws RemoteException, ObjectNotFoundException {
        FundInfo fundInfo=new FundInfo();
        fundInfo.compare_base="兴业基金";
        fundInfo.establish_date="20150703";
        fundInfo.establish_scale=10000.0;
        fundInfo.full_name="兴业聚惠灵活配置混合A";
        fundInfo.fund_type="混合型";
        fundInfo.invest_strategy="低风险";
        fundInfo.invest_type="混合";
        fundInfo.invest_target="保值";
        fundInfo.manage_fee=0.01;
        fundInfo.risk_feature="高";
        fundInfo.scale=2000.0;
        fundInfo.simple_name="兴业聚惠A";
        return fundInfo;
    }
}
