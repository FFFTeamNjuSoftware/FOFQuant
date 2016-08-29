package stubs.logic;

import beans.CodeName;
import beans.ConstParameter;
import beans.FundInfo;
import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import com.google.gson.Gson;
import exception.ObjectNotFoundException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class BaseInfoLogicStub extends UnicastRemoteObject implements BaseInfoLogic {
    public BaseInfoLogicStub() throws RemoteException {

    }

    @Override
    public List<String> getFundCodes() throws RemoteException {
        List<String> fundCodeList = new ArrayList<String>();
        fundCodeList.add("001547");
        fundCodeList.add("001714");
        return fundCodeList;
    }

    @Override
    public List<CodeName> fuzzySearch(String keyword) throws RemoteException {
        List<CodeName> codeNameList = new ArrayList<CodeName>();
        CodeName codeName = new CodeName();
        codeName.code = "001547";
        codeName.name = "兴业聚惠A";
        codeNameList.add(codeName);
        return codeNameList;
    }

    @Override
    public FundInfo getFundBaseInfo(String code) throws RemoteException, ObjectNotFoundException {
        FundInfo fundInfo = new FundInfo();
        fundInfo.compare_base = "兴业基金";
        fundInfo.establish_date = "20150703";
        fundInfo.establish_scale = 10000.0;
        fundInfo.full_name = "兴业聚惠灵活配置混合A";
        fundInfo.fund_type = "混合型";
        fundInfo.invest_strategy = "低风险";
        fundInfo.invest_type = "混合";
        fundInfo.invest_target = "保值";
        fundInfo.manage_fee = 0.01;
        fundInfo.risk_feature = "高";
        fundInfo.scale = 2000.0;
        fundInfo.simple_name = "兴业聚惠A";
        return fundInfo;
    }

    @Override
    public List<ArrayList<String>> getRankSectorType() throws RemoteException {
        return null;
    }


    @Override
    public List<FundQuickInfo> getFundQuickInfo(String type) throws RemoteException {
        FundQuickInfo quickInfo1 = new Gson().fromJson("{\"code\":\"960000\"," +
                "\"simple_name\":\"汇丰大盘H\",\"current_netWorth\":0.996,\"daily_rise\":-0" +
                ".39000000000000146,\"oneMonth\":5.866277493858152,\"threeMonth\":15" +
                ".639142071013756,\"halfYear\":19.206876017803154,\"oneYear\":-0" +
                ".3432342264725885,\"threeYear\":-0.3432342264725885,\"fiveYear\":-0" +
                ".3432342264725885,\"sinceEstablish\":-0.3432342264725885,\"yearRate\":3" +
                ".1420253164556122}", FundQuickInfo.class);
        FundQuickInfo quickInfo2 = new Gson().fromJson("{\"code\":\"740101\"," +
                "\"simple_name\":\"长安300非周期\",\"current_netWorth\":1.332,\"daily_rise\":-0" +
                ".21999999999999797,\"oneMonth\":2.773275896500982,\"threeMonth\":12" +
                ".973845588794685,\"halfYear\":12.664593959081882,\"oneYear\":-13" +
                ".743672379197424,\"threeYear\":37.79746864867626,\"fiveYear\":42.84216424513683," +
                "\"sinceEstablish\":42.84216424513683,\"yearRate\":12.888588957055108}",
                FundQuickInfo.class);
        FundQuickInfo quickInfo3 = new Gson().fromJson("{\"code\":\"700002\"," +
                "\"simple_name\":\"平安深证300\",\"current_netWorth\":1.564,\"daily_rise\":-0" +
                ".060000000000004494,\"oneMonth\":2.9129333155772708,\"threeMonth\":15" +
                ".527550511186261,\"halfYear\":11.452745804007236,\"oneYear\":-6.118105911667005," +
                "\"threeYear\":58.552659694511135,\"fiveYear\":68.53309185955898," +
                "\"sinceEstablish\":68.53309185955898,\"yearRate\":15.511304347826075}",
                FundQuickInfo.class);
        return Arrays.asList(quickInfo1, quickInfo2, quickInfo3);
    }

    @Override
    public ConstParameter getConstaParameteer() throws RemoteException {
        return null;
    }
}
