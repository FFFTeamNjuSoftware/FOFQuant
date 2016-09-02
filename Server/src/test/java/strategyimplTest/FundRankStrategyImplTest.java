package strategyimplTest;

import beans.FundQuickInfo;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import org.junit.Before;
import org.junit.Test;
import startup.HibernateBoot;
import strategy.FundRankStrategy;
import strategyimpl.FundRankStrategyImpl;
import util.SectorType;
import util.TimeType;
import util.UnitType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/8/24.
 */
public class FundRankStrategyImplTest {
    FundRankStrategy fundRankStrategy;
    MarketLogic marketLogic;
    private BaseInfoLogic baseInfoLogic;

    @Before
    public void before() throws Exception{
        HibernateBoot.init();
        fundRankStrategy=new FundRankStrategyImpl();
        marketLogic= BLController.getMarketLogic();
        baseInfoLogic=BLController.getBaseInfoLogic();
    }

    @Test
    public void getFundReturnRate() throws Exception {
        List<PriceInfo> priceInfoList=marketLogic.getPriceInfo("180033", UnitType.MONTH,"2013-08-26","2016-08-26");

        double returnRate=fundRankStrategy.getFundReturnRate(priceInfoList,36,TimeType.ONE_YEAR);
        System.out.println(returnRate);
    }

    @Test
    public void getFundNoRiskRate() throws Exception {
        double noRiskRate=fundRankStrategy.getFundNoRiskRate(12);
        System.out.println(noRiskRate);
    }

    @Test
    public void getFundProfit() throws Exception {
        List<PriceInfo> priceInfoList=marketLogic.getPriceInfo("180033", UnitType.MONTH,"2016-01-01","2016-08-26");
        double profit=fundRankStrategy.getFundProfit(priceInfoList,8, TimeType.ONE_YEAR);
        System.out.println(profit);
    }

    @Test
    public void getMRAR() throws Exception {
        double mrar=fundRankStrategy.getMRAR("000001",TimeType.THREE_YEAR,"2015-12-31");
        System.out.println(mrar);
    }

    @Test
    public void refreshFundRank() throws Exception {
        this.getFundRankByDate();

        File file=new File("qdii.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter=new FileWriter(file.getName());
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        List<FundQuickInfo> fundQuickInfos=baseInfoLogic.getFundQuickInfo(SectorType.QDII_TYPE);
        for(FundQuickInfo fundQuickInfo:fundQuickInfos){
            String data=fundQuickInfo.code;
            bufferedWriter.write(data+"\n");
        };
        bufferedWriter.close();

    }

    @Test
    public void getFundRankByDate() throws Exception{
        Map<String,ArrayList<Double>> maps=fundRankStrategy.getFundRankByDate(TimeType.THREE_YEAR,
                "2016-08-26");
        File file=new File("rank.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter=new FileWriter(file.getName());
        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
        for(String str:maps.keySet() ){
            String data=str+","+maps.get(str).get(1) +","+maps.get(str).get(2)+"\n";
            bufferedWriter.write(data);
        };
        bufferedWriter.close();


    }
}