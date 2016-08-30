package strategyimpl;

import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundRankEntity;
import exception.ObjectNotFoundException;
import strategy.FundDeployStrategy;
import util.SectorType;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by Seven on 16/8/30.
 */
public class FundDeployStrategyImpl implements FundDeployStrategy {
    private BaseInfoDataService baseInfoDataService;
    private BaseInfoLogic baseInfoLogic;
    private MarketLogic marketLogic;
    private String startDate="2013-12-31";
    private String endDate="2015-12-31";

    public FundDeployStrategyImpl(){
            baseInfoDataService=DataServiceController.getBaseInfoDataService();
            baseInfoLogic=BLController.getBaseInfoLogic();
            marketLogic=BLController.getMarketLogic();
    }

    @Override
    public double[][] getFundsProfit(List<String> funds, String startDate, String endDate) {
        return new double[0][];
    }

    @Override
    public double[][] getFundsFee(List<String> funds) {
        return new double[0][];
    }

    @Override
    public List getWRpturn(List<String> funds, String startDate, String endDate) {
        return null;
    }

    @Override
    public List DefaultFundDeploy() throws RemoteException{
        //获得系统中所有固定收益类基金的排名
        try {
            List<FundQuickInfo> fundQuickInfos=baseInfoLogic.getFundQuickInfo(SectorType.FIX_PROFIT_TYPE);
            HashMap<String,Double> fixProfitRank=new HashMap<>();
            FundRankEntity fundRankEntity;
            for (FundQuickInfo fundQuickInfo:fundQuickInfos) {
                String code="";
                try {
                    code = fundQuickInfo.code;
                    fundRankEntity = baseInfoDataService.getFundRankInfo(code);
                    Double rank = fundRankEntity.getRank();
                    if (rank != null) {
                        fixProfitRank.put(code, rank);
                    }
                } catch (ObjectNotFoundException e) {
                    System.out.println(code+"no rank info");
                }
            }
            List<String> sortedCodes=this.sort(fixProfitRank);
            for(String sortedCode:sortedCodes){
                System.out.println(sortedCode);
            }


//            //读取系统中前N只固定收益型股票的2013.12-2015.12收盘价数据,每只基金对应的费率
//            List<String> sortedCodes=this.sort(fixProfitRank);
//                //N=2,可调
//                int N=0;
//                if(sortedCodes.size()>2){N=2;}else{N=sortedCodes.size();}
//
//                Map<String,List<Double>> codePrices=new HashMap<>();
//                Map<String,List<Double>> codeFee=new HashMap<>();
//                int size=marketLogic.getPriceInfo(sortedCodes.get(0),UnitType.DAY,startDate,endDate).size();
//                int i=0;
//                while(i<N){
//                    String code=sortedCodes.get(i);
//                    List<PriceInfo> priceInfos=marketLogic.getPriceInfo(code, UnitType.DAY,startDate,endDate);
//                    if(priceInfos.size()<size){
//                        size=priceInfos.size();
//                    }
//                    //<基金,收盘价序列>
//                    List<Double> prices=new ArrayList<>();
//                    for(int j=0;j<priceInfos.size();j++){
//                        prices.add(priceInfos.get(j).price);
//                    }
//                    codePrices.put(code,prices);
//                    //<基金,费率序列>
//                    List<Double> fees=new ArrayList<>();
//                    FundInfosEntity fundInfos=baseInfoDataService.getFundInfo(code);
//                    Double manageFee=fundInfos.getManageFee();
//                    Double trusteeFee=fundInfos.getTrusteeFee();
//                    Double saleServiceFee=fundInfos.getSaleServiceFee();
//                    if(manageFee!=null) {fees.add(manageFee);}else{fees.add(0.0);}
//                    if(trusteeFee!=null){fees.add(trusteeFee);}else{fees.add(0.0);}
//                    if(saleServiceFee!=null){fees.add(saleServiceFee);}else{fees.add(0.0);}
//                    codeFee.put(code,fees);
//
//                    i++;
//                }
//                System.out.println(codePrices.keySet());
//                //将收盘价数据写入txt文件
//                File priceFile=new File("fundDeployN2.txt");
//                if(!priceFile.exists()){
//                    priceFile.createNewFile();
//                }
//                File feeFile=new File("fundFeeN2");
//                if(!feeFile.exists()){
//                    feeFile.createNewFile();
//                }
//
//                FileWriter priceFileWriter=new FileWriter(priceFile.getName());
//                BufferedWriter bufferedWriter=new BufferedWriter(priceFileWriter);
//
//                FileWriter feeFileWriter=new FileWriter(feeFile.getName());
//                BufferedWriter bufferedWriter1=new BufferedWriter(feeFileWriter);
//
//                for(int day=0;day<size;day++) {
//                    for (String fundcode:codePrices.keySet()) {
//                        double close=codePrices.get(fundcode).get(day);
//                        bufferedWriter.write(close+" ");
//                        if (day==0){
//                            for(i=0;i<3;i++){
//                                double fee=codeFee.get(fundcode).get(i);
//                                bufferedWriter1.write(fee+" ");
//                            }
//                            bufferedWriter1.write("\n");
//                        }
//                    }
//                    bufferedWriter.write("\n");
//                }
//                bufferedWriter.close();
//                bufferedWriter1.close();

        } catch (ObjectNotFoundException e) {
            System.out.println("NO SUCH KIND OF FUNDS");
//        } catch (ParameterException e) {
//            System.out.println("FORMAT WRONG");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List CustomizedFundDeploy(List<String> funds) {
        return null;
    }

    //排序
    private List<String> sort(HashMap<String,Double> index){
        List<Map.Entry<String,Double>> fundCodes=new ArrayList<>(index.entrySet());
        //按照升序排序
        Collections.sort(fundCodes, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//                if(o2.getValue()>o1.getValue()){
//                    return -1;
//                }else if (o2.getValue()==o1.getValue()){
//                    return 0;
//                }else{
//                    return 1;
//                }
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<String> codesAfterSort=new ArrayList<>();
        for(int i=0;i<fundCodes.size();i++) {
            codesAfterSort.add(fundCodes.get(i).getKey());
        }
        return codesAfterSort;
    }

}
