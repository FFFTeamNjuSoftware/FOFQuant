package strategyimpl;

import beans.FundQuickInfo;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundInfosEntity;
import entities.FundRankEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import strategy.FundDeployStrategy;
import util.SectorType;
import util.UnitType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    private String startDate = "2013-01-01";
    private String endDate = "2015-12-31";

    public FundDeployStrategyImpl() {
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
        baseInfoLogic = BLController.getBaseInfoLogic();
        marketLogic = BLController.getMarketLogic();
    }


    @Override
    public List getWRpturn(List<String> funds, String startDate, String endDate) {
        return null;
    }


    @Override
    public List DefaultFundDeploy() throws RemoteException {
        //获得系统中所有固定收益类基金的排名
        List<FundQuickInfo> fundQuickInfos;
        try {
            fundQuickInfos = baseInfoLogic.getFundQuickInfo(SectorType.FIX_PROFIT_TYPE);
            HashMap<String, Double> fixProfitRank = new HashMap<>();
            FundRankEntity fundRankEntity;
            for (FundQuickInfo fundQuickInfo : fundQuickInfos) {
                String code = "";
                try {
                    code = fundQuickInfo.code;
                    fundRankEntity = baseInfoDataService.getFundRankInfo(code);
                    Double grade = fundRankEntity.getGrade();
                    if (grade != null) {
                        fixProfitRank.put(code, grade);
                    }
                } catch (ObjectNotFoundException e) {
                    continue;
                }
            }
            //排序
            List<String> sortedCodes = this.sort(fixProfitRank);
            int[] windows = {90, 180, 360};
            int[] holds = {30, 60, 90};
            List<Double> sharpes=new ArrayList<>();
//            for (int window : windows) {
//                for (int hold : holds) {
                    for (int N = 2; N <= 6; N++) {
//                        System.out.println(N+"begin");
                        double sharpe=this.calSharpe(sortedCodes, N, 360, 30);

                       // FundDeployEntity fundDeployEntity=new FundDeployEntity();

                        sharpes.add(sharpe);
                    }
//                }
//            }
            //对sharpe数组进行排序
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List CustomizedFundDeploy(List<String> funds) {
        return null;
    }

    @Override
    public double calSharpe(List<String> sortedCodes, int N, int window, int hold) throws RemoteException {
        //读取系统中前N只固定收益型股票的2013.12-2015.12收盘价数据,每只基金对应的费率
        if (sortedCodes.size() < N) {
            N = sortedCodes.size();
        }
        Map<String, List<Double>> codePrices = this.getCodePrices(sortedCodes, N);
        Map<String, List<Double>> codeFee = this.getCodeFee(sortedCodes, N);

        //算出最少包含的天数
        int length=0;
        for(String codePrice:codePrices.keySet()){
            if(length==0) {
                length = codePrices.get(codePrice).size();
            }else{
                if (length>codePrices.get(codePrice).size()){
                    length=codePrices.get(codePrice).size();
                }
            }
        }

        this.writeToTXT(codePrices,codeFee,N,window,hold,length);
        //根据codePrices和codeFee生成dataPrice和dataFee矩阵
        //调用小类matlab策略
        //策略返回w矩阵和rpturn数组
        //rpturn数组计算出对应的Sharpe比率
        List<String> codes=new ArrayList<>();
        int adjust=(int)Math.floor((length-window)/hold);
        double[][] w=new double[adjust][N];
        for (String code:codes){
            Map<String,Double> propotion=new HashMap<>();

        }
        double sharpe=0.0;

        return sharpe;
    }

    public Map<String, List<Double>> getCodePrices(List<String> funds, int N) throws RemoteException {
        Map<String, List<Double>> codePrices = new HashMap<>();
        try {
            int i = 0;
            while (i < N) {
                String code = funds.get(i);
                List<PriceInfo> priceInfos = marketLogic.getPriceInfo(code, UnitType.DAY, startDate, endDate);
                //<基金,收盘价序列>
                List<Double> prices = new ArrayList<>();
                for (int j = 0; j < priceInfos.size(); j++) {
//                    System.out.print(priceInfos.get(j).date+"  ");
                    prices.add(priceInfos.get(j).price);
                }
                codePrices.put(code, prices);
                System.out.println(code);
                i++;
            }

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        return codePrices;
    }

    public Map<String, List<Double>> getCodeFee(List<String> funds, int N) throws RemoteException {
        Map<String, List<Double>> codeFee = new HashMap<>();
        int size = 0;
        try {
            int i = 0;
            while (i < N) {
                String code = funds.get(i);
                //<基金,费率序列>
                List<Double> fees = new ArrayList<>();
                FundInfosEntity fundInfos = baseInfoDataService.getFundInfo(code);
                Double manageFee = fundInfos.getManageFee();
                Double trusteeFee = fundInfos.getTrusteeFee();
                Double saleServiceFee = fundInfos.getSaleServiceFee();
                if (manageFee != null) {
                    fees.add(manageFee);
                } else {
                    fees.add(0.0);
                }
                if (trusteeFee != null) {
                    fees.add(trusteeFee);
                } else {
                    fees.add(0.0);
                }
                if (saleServiceFee != null) {
                    fees.add(saleServiceFee);
                } else {
                    fees.add(0.0);
                }
                codeFee.put(code,fees);
                i++;
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return codeFee;
    }

    private void writeToTXT(Map<String,List<Double>> codePrices,Map<String, List<Double>> codeFee,int N,int window,int hold,int length) {
        String name1 = "riskyParityN" + N + "Price.txt";
        String name2 = "riskyParityN" + N + "Fee.txt";
        //将收盘价数据写入txt文件
        File priceFile = new File(name1);
        File feeFile = new File(name2);
        try {
            if (!priceFile.exists()) {
                priceFile.createNewFile();
            }
            if (!feeFile.exists()) {
                feeFile.createNewFile();
            }

            FileWriter priceFileWriter = new FileWriter(priceFile.getName());
            BufferedWriter bufferedWriter = new BufferedWriter(priceFileWriter);

            FileWriter feeFileWriter = new FileWriter(feeFile.getName());
            BufferedWriter bufferedWriter1 = new BufferedWriter(feeFileWriter);

            for (int day = 0; day < length; day++) {
                for (String fundcode : codePrices.keySet()) {
                    double close = codePrices.get(fundcode).get(day);
                    if(day==0) {
                        System.out.println(close+"  "+fundcode);
                    }
                    bufferedWriter.write(close + " ");
                    if (day == 0) {
                        for (int i = 0; i < 3; i++) {
                            double fee = codeFee.get(fundcode).get(i);
                            bufferedWriter1.write(fee + " ");
                        }
                        bufferedWriter1.write("\n");
                    }
                }
                bufferedWriter.write("\n");
            }
            bufferedWriter.close();
            bufferedWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //排序
    private List<String> sort(Map<String,Double> index){
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
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        List<String> codesAfterSort=new ArrayList<>();
        System.out.println("Sorting codes!");
        for(int i=0;i<fundCodes.size();i++) {
            codesAfterSort.add(fundCodes.get(i).getKey());
        }
        System.out.println("End Sorting");
        return codesAfterSort;
    }

}
