package strategyimpl;

import beans.FundDeploy;
import beans.FundQuickInfo;
import beans.PriceInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import blimpl.BLController;
import blimpl.Converter;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.FundDeployEntity;
import entities.FundInfosEntity;
import entities.FundRankEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import strategy.FundDeployStrategy;
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
    private String start = "2013-01-01";
    private String end = "2015-12-31";
    private int[] windows = {90, 180, 360};
    private int[] holds = {30, 60, 90};

    public FundDeployStrategyImpl() {
        baseInfoDataService = DataServiceController.getBaseInfoDataService();
        baseInfoLogic = BLController.getBaseInfoLogic();
        marketLogic = BLController.getMarketLogic();
    }

    @Override
    public Map<String, List<Double>> getCodePrices(List<String> funds, int N,String startDate,String endDate) throws RemoteException {
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

    @Override
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


    @Override
    public FundDeploy calSharpe(List<String> sortedCodes, int N, int window, int hold, String startDate, String endDate) throws RemoteException {
        //读取系统中前N只固定收益型股票的2013.12-2015.12收盘价数据,每只基金对应的费率
        if (sortedCodes.size() < N) {
            N = sortedCodes.size();
        }
        Map<String, List<Double>> codePrices = this.getCodePrices(sortedCodes, N, startDate, endDate);
        Map<String, List<Double>> codeFee = this.getCodeFee(sortedCodes, N);

        //算出最少包含的天数
        int length = 0;
        for (String codePrice : codePrices.keySet()) {
            if (length == 0) {
                length = codePrices.get(codePrice).size();
            } else {
                if (length > codePrices.get(codePrice).size()) {
                    length = codePrices.get(codePrice).size();
                }
            }
        }
        //        this.writeToTXT(codePrices,codeFee,N,window,hold,length);
        double[][] price=new double[length][N];
        double[][] fee=new double[N][3];
        List<String> codes=new ArrayList<>();
        int index=0;
        for(String code:codePrices.keySet()){
            for(int i=0;i<length;i++) {
                price[index][i] = codePrices.get(code).get(i);
            }
            for(int j=0;j<3;j++){
                fee[index][j]=codeFee.get(code).get(j);
            }
            codes.add(code);
            index++;
        }
        //根据prices,fee,N,window,hold生成dataPrice和dataFee矩阵
        //调用小类matlab策略
        //策略返回w矩阵,rpturn数组,Sharpe比率
        //rpturn数组计算出对应的Sharpe比率
        MWNumericArray prices=new MWNumericArray(price, MWClassID.DOUBLE);
        MWNumericArray fees=new MWNumericArray(fee,MWClassID.DOUBLE);
        Object[] objs=new Object[3];
//        Object[] objs= MatlabBoot.getCalculateTool().(3,prices,fees,N,window,hold);

        double[][] w=(double[][])((MWNumericArray)objs[0]).toDoubleArray();
        double[] rpturn = (double[])((MWNumericArray)objs[1]).toDoubleArray();
        double sharpe = (double)objs[2];
        List<Map<String, Double>> proportions = new ArrayList<>();

        for (int i = 0; i < rpturn.length; i++){
            Map<String, Double> proportion = new HashMap<>();
            for (int j=0;j<N;j++) {
                //w中每一行存储每一只基金对应的权重
                double p = w[i][j];
                proportion.put(sortedCodes.get(j),p);
            }
            proportions.add(proportion);
        }
        FundDeployEntity fundDeployEntity=new FundDeployEntity(proportions,N,rpturn,window,hold,sharpe);

        return Converter.convertFundDeployEntity(fundDeployEntity);
    }

    @Override
    public FundDeploy CustomizedFundDeploy(List<String> funds,String startDate,String endDate) throws RemoteException{
        List<FundDeploy> fundDeploys=new ArrayList<>();
        for (int window : windows) {
            for (int hold : holds) {
                for (int N = 2; N <= 6; N++) {
                     FundDeploy fundDeploy=this.calSharpe(funds,N,window,hold,startDate,endDate);
                     fundDeploys.add(fundDeploy);
                }
            }
        }
        //选出夏普比率值最高的组合
        FundDeploy result=fundDeploys.get(0);
        double sharpe=0;
        for(FundDeploy fundDeploy:fundDeploys){
              if(fundDeploy.sharpe>sharpe){
                  sharpe=fundDeploy.sharpe;
                  result=fundDeploy;
              }
        }
        return result;
    }

    @Override
    public FundDeploy DefaultFundDeploy(String sectorType) throws RemoteException {
        //获得系统中所有固定收益类基金的排名
        List<FundQuickInfo> fundQuickInfos;
        FundDeploy fundDeploy=new FundDeploy();
        try {
            fundQuickInfos = baseInfoLogic.getFundQuickInfo(sectorType);
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
            fundDeploy=this.CustomizedFundDeploy(sortedCodes,start,end);
        } catch (ObjectNotFoundException e) {
            System.out.println("系统无法进行小类配置");
        }
        return fundDeploy;
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
