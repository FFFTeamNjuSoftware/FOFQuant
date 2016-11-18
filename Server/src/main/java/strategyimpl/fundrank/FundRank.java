package strategyimpl.fundrank;

import CalculateTool.CalculateTool;
import blimpl.CalculateDataHandler;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import dataservice.BaseInfoDataService;
import dataserviceimpl.DataServiceController;
import entities.ConstParameterEntity;
import entities.FundInfosEntity;
import exception.ObjectNotFoundException;
import matlabtool.TypeConverter;
import util.SectorType;
import util.TimeType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/11/15.
 */
public class FundRank {

    String[] sectorTypes = new String[]{SectorType.STOCK_TYPE, SectorType.BOND_TYPE, SectorType
            .MONEY_MARKET_TYPE};

    Map<String, List<String>> fundPool;

    double[][] totalWeight = {{0.7, 0.3, 0, 0}, {0.6, 0.1, 0.3, 0}, {0.5, 0.1, 0.4, 0}},
            profitWeight = {{0.3, 0.4, 0.3}, {0.8, 0, 0.2}, {1.0, 0, 0}},
            riskWeight = {{0.4, 0.4, 0.2}, {0.5, 0.5, 0}, {0.8, 0.2, 0}},
            stableWeight = {{0, 0}, {0.7, 0.3}, {0.9, 0.1}};

    String baseCode = "I000300";

    TimeType calTimeType = TimeType.THREE_YEAR;

    private static final int UNIT = 5;


    /**
     * 获得总的基金池
     * 筛选掉成立时间短和规模小的（成立时间两年以内，资产规模小于2亿（1亿？）的筛选掉）
     *
     * @return 以sectorType作为键，即按基金分类返回基金代码
     */
    public Map<String, List<String>> getTotalFundPool() {
        Map<String, List<String>> re = new HashMap<>();
        BaseInfoDataService baseInfoDataService = DataServiceController.getBaseInfoDataService();
        try {
            List<String> qdii = baseInfoDataService.getSectorCodes(SectorType.QDII_TYPE);
            for (String str : sectorTypes) {
                List<String> tem = baseInfoDataService.getSectorCodes(str);
                tem.removeAll(qdii);
                re.put(str,
                        tem.stream().filter(new Predicate<String>() {
                            @Override
                            public boolean test(String s) {
                                try {
                                    FundInfosEntity fundInfosEntity = baseInfoDataService.getFundInfo(s);
                                    LocalDate localDate = LocalDate.now();
                                    localDate = localDate.minusYears(3);
                                    String date = localDate.toString();
                                    if (fundInfosEntity.getScale() == null || fundInfosEntity
                                            .getScale() <= 2 || fundInfosEntity.getEstablishDate()
                                            == null || fundInfosEntity.getEstablishDate()
                                            .compareTo(date) > 0 || fundInfosEntity.getSimpleName()
                                            .contains("B") || fundInfosEntity.getSimpleName()
                                            .contains("C")) {
                                        return false;
                                    }
                                    return true;
                                } catch (ObjectNotFoundException e) {
                                    e.printStackTrace();
                                }
                                return false;
                            }
                        }).collect(Collectors.toList()));
            }
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        fundPool = re;
        return re;
    }


    public List<FundIndexInfo> getFundRank(List<String> codes, String sectorType) {
        Map<String, Double> re = new HashMap<>();
        Map<String, List<Double>> fundRiseInfos = new HashMap<>();
        Map<String, List<Double>> baseRiseInfos = new HashMap<>();
        int index = getIndexBySectorType(sectorType);
        if (index == -1)
            return null;

        CalculateTool calculateTool = null;
        ConstParameterEntity constParameterEntity = DataServiceController.getBaseInfoDataService()
                .getConstParameter();
        try {
            calculateTool = new CalculateTool();
        } catch (MWException e) {
            e.printStackTrace();
        }

        /**
         * 获取数据
         */
        System.out.println("开始获取数据");
        for (String code : codes) {
            CalculateDataHandler cal = new CalculateDataHandler(code);
            cal.setTimeType(calTimeType);
            cal.setBaseCode(baseCode);
            try {
                Map<String, List<Double>> info = cal.getDatasByRise();
                fundRiseInfos.put(code, info.get(code));
                baseRiseInfos.put(code, info.get(baseCode));
            } catch (ObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("数据获取结束");

        /**
         * ----------------------计算盈利能力-----------------------
         */
        double pw = totalWeight[index][0];
        List<FundIndexInfo> totalProfit = new ArrayList<>(), sharpe = new ArrayList<>(), jensen = new ArrayList<>();
        for (String code : codes) {
            double p = fundRiseInfos.get(code).stream().reduce((x, y) -> (1 + x) * (1 + y) - 1).get();
            if (code.equals("070005"))
                System.out.println(p);
            totalProfit.add(new FundIndexInfo(code, p));
        }
        addScore(re, totalProfit, profitWeight[index][0], pw, 1);
        System.out.println("profit over----start sharpe");

        if (profitWeight[index][1] != 0) {
            try {
                for (String code : codes) {
                    MWNumericArray fof_info_mwn = TypeConverter.convertList(fundRiseInfos.get(code));
                    double s = TypeConverter.getSingleDoubleResult(calculateTool.calSharpe(1,
                            fof_info_mwn, constParameterEntity.getNoRiskProfit() / 100, 1.0));
                    sharpe.add(new FundIndexInfo(code, s));
                }
                addScore(re, sharpe, profitWeight[index][1], pw, 1);
            } catch (MWException e) {
                e.printStackTrace();
            }
        }

        System.out.println("sharpe over----start jensen");

        if (profitWeight[index][2] != 0) {
            try {
                for (String code : codes) {
                    MWNumericArray fof_info_mwn = TypeConverter.convertList(fundRiseInfos.get(code));
                    MWNumericArray base_info_mwn = TypeConverter.convertList(baseRiseInfos.get(code));
                    double j = TypeConverter.getSingleDoubleResult(calculateTool.calJensen
                            (1, fof_info_mwn, base_info_mwn, constParameterEntity
                                    .getNoRiskProfit() / 100, 1.0));
                    jensen.add(new FundIndexInfo(code, j));
                }
                addScore(re, jensen, profitWeight[index][2], pw, 1);
            } catch (MWException e) {
                e.printStackTrace();
            }
        }
        /**
         * ----------------------------盈利能力计算结束-------------------------------
         */


        /**
         * ----------------------------计算抗风险能力--------------------------------
         */
        double rw = totalWeight[index][1];
        List<FundIndexInfo> vRatio = new ArrayList<>(), averageV = new ArrayList<>(), maxRecu = new
                ArrayList<>();
        for (String code : codes) {
            int vSize = fundRiseInfos.get(code).size();
            double fr = 0;
            double total = 0;
            for (double d : fundRiseInfos.get(code)) {
                if (d < 0) {
                    fr++;
                    total += d;
                }
            }
            vRatio.add(new FundIndexInfo(code, fr / vSize));
            averageV.add(new FundIndexInfo(code, total / vSize));
        }
        addScore(re, vRatio, riskWeight[index][0], rw, -1);
        addScore(re, averageV, riskWeight[index][1], rw, -1);
        if (riskWeight[index][2] != 0) {
            for (String code : codes) {
                maxRecu.add(new FundIndexInfo(code, maxRe(fundRiseInfos.get(code))));
            }
            addScore(re, maxRecu, riskWeight[index][2], rw, -1);
        }
        /**
         * --------------------------------抗风险能力计算结束----------------------------
         */

        /**
         * --------------------------------计算业绩稳定性-------------------------------
         */
        double sw = totalWeight[index][2];
        List<FundIndexInfo> hurstHolder = new ArrayList<>(), profitWinRate = new ArrayList<>();
        if (stableWeight[index][0] != 0) {
            for (String code : codes) {
                double h = hurstHolder(UNIT, fundRiseInfos.get(code));
                hurstHolder.add(new FundIndexInfo(code, h));
            }
            addScore(re, hurstHolder, stableWeight[index][0], sw, 1);
        }

        if (stableWeight[index][1] != 0) {
            Map<String, List<Double>> monthInfo = new HashMap<>();
            int monthNum = 3 * 12;
            for (String code : codes) {
                List<Double> info = fundRiseInfos.get(code);
                int unit = info.size() / monthNum;
                List<Double> m = new ArrayList<>();
                for (int i = 0; i < monthNum; i++) {
                    m.add(info.subList(i * unit, (i + 1) * unit).stream().mapToDouble(e -> e).sum
                            ());
                }
                monthInfo.put(code, m);
            }
            double[] aveRise = new double[monthNum];
            for (int i = 0; i < monthNum; i++) {
                aveRise[i] = 0;
            }
            for (String code : codes) {
                for (int i = 0; i < monthNum; i++) {
                    aveRise[i] += monthInfo.get(code).get(i) / monthNum;
                }
            }
            for (String code : codes) {
                int count = 0;
                for (int i = 0; i < monthNum; i++) {
                    if (monthInfo.get(code).get(i) > aveRise[i])
                        count++;
                }
                profitWinRate.add(new FundIndexInfo(code, count));
            }
            addScore(re, profitWinRate, stableWeight[index][1], sw, 1);
        }

        List<FundIndexInfo> fundIndexInfos = new ArrayList<>();
        for (String str : re.keySet()) {
            fundIndexInfos.add(new FundIndexInfo(str, re.get(str)));
        }
        fundIndexInfos.sort(new FundComparator());
        return fundIndexInfos;
    }

    private void addScore(Map<String, Double> scoreMap, List<FundIndexInfo> fundIndexInfos, double
            w1, double w2, int order) {
        fundIndexInfos.sort(new FundComparator(order));
        int size = fundIndexInfos.size();
        for (int i = 0; i < size; i++) {
            FundIndexInfo fundIndexInfo = fundIndexInfos.get(i);
            double v = scoreMap.get(fundIndexInfo.fundCode) == null ? 0 : scoreMap.get(fundIndexInfo
                    .fundCode);
            scoreMap.put(fundIndexInfo.fundCode, v + (size - i) / ((double) size) * w1 * w2);
        }
    }

    private double maxRe(List<Double> rises) {
        double maxRe = 0, temRe = 1;
        for (double d : rises) {
            if (d > 0) {
                temRe = 1 - temRe;
                maxRe = Math.max(maxRe, temRe);
                temRe = 1;
            } else {
                temRe *= (1 + d);
            }
        }
        return maxRe;
    }

    private double hurstHolder(int unit, List<Double> infos) {
        int size = infos.size();
//        List<List<Double>> totalUnitInfos = new ArrayList<>();
//        int sizeNum = size / unit;
//        for (int i = 0; i < sizeNum; i++) {
//            totalUnitInfos.add(infos.subList(unit * i, unit * (i + 1)));
//        }

        double ave = infos.stream().mapToDouble(e -> e).sum() / size;
        double[] zt = new double[size];
        for (int i = 0; i < size; i++) {
            zt[i] = infos.get(i) - ave;
        }
        double max = 0, min = Double.MAX_VALUE, tem = 0;
        for (double d : zt) {
            tem += d;
            max = Math.max(tem, max);
            min = Math.min(tem, min);
        }
        double standar = 0;
        for (int i = 0; i < size; i++) {
            standar += zt[i] * zt[i];
        }
        standar = standar / (size - 1);
        standar = Math.sqrt(standar);
        double h = 1 / Math.log(size);
        h *= Math.sqrt((max - min) / standar);
        return h;
    }

    private int getIndexBySectorType(String sectorType) {
        switch (sectorType) {
            case SectorType.STOCK_TYPE:
                return 0;
            case SectorType.BOND_TYPE:
                return 1;
            case SectorType.MONEY_MARKET_TYPE:
                return 2;
        }
        return -1;
    }

    void outPut(String filePath, String sectorType) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            List<FundIndexInfo> fundIndexInfos = getFundRank(fundPool.get(sectorType), sectorType);
            String format = "%-4d%-10s%-5.2f\n";
            for (int i = 0; i < fundIndexInfos.size(); i++) {
                bufferedWriter.write(String.format(format, i + 1, fundIndexInfos.get(i).fundCode,
                        fundIndexInfos.get(i).value * 100));
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void outPut() {
        getTotalFundPool();
        outPut("D:/股票型.txt", SectorType.STOCK_TYPE);
        outPut("D:/债券型.txt", SectorType.BOND_TYPE);
        outPut("D:/货币市场型.txt", SectorType.MONEY_MARKET_TYPE);
    }


    public static void main(String[] args) throws Exception {
        FundRank fundRank = new FundRank();
        System.out.println(fundRank.getTotalFundPool().get(SectorType.STOCK_TYPE).size());
        fundRank.getTotalFundPool().get(SectorType.STOCK_TYPE).forEach(System.out::println);
//        CalculateDataHandler calculateDataHandler = new CalculateDataHandler("164509");
//        double h=fundRank.hurstHolder(1,calculateDataHandler.getDatasByRise().get("164509"));
//        System.out.println(h);
//        fundRank.outPut();
    }
}
