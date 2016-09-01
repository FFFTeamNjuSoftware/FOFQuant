package blimpl.fof;

import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import beans.ProfitStatisticsInfoOne;
import beans.ProfitStatisticsInfoTwo;
import bl.fof.FOFProfitStatisticsLogic;
import blimpl.CalculateDataHandler;
import blimpl.LogicUtil;
import com.google.gson.Gson;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.FOFUtilInfo;
import util.IndexCodeInfo;
import util.NumberOpe;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitStatisticsLogicImpl extends UnicastRemoteObject implements
        FOFProfitStatisticsLogic {
    private String startDate;
    private String endDate;
    private String baseCode;
    private UnitType unitType;
    private String code;

    private FOFProfitStatisticsLogicImpl() throws RemoteException {
        code = FOFUtilInfo.FOF_CODE;
        unitType = UnitType.DAY;
        baseCode = "I000300";
    }

    private static FOFProfitStatisticsLogic instance;

    public static FOFProfitStatisticsLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFProfitStatisticsLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public void setStartDate(String startDate) throws ParameterException, RemoteException {
        this.startDate = startDate;
    }

    @Override
    public void setEndDate(String endDate) throws ParameterException, RemoteException {
        this.endDate = endDate;
    }

    @Override
    public void setProformanceBase(String indexCode) throws RemoteException, ObjectNotFoundException {
        if (!IndexCodeInfo.INDEX_CODES.contains(indexCode.substring(1)))
            throw new ObjectNotFoundException("indexCode " + indexCode + " notFound");
        this.baseCode = indexCode;
    }

    @Override
    public void setUnitType(UnitType unitType) throws RemoteException {
        this.unitType = unitType;
    }

    @Override
    public ProfitStatisticsInfo getProfitStatisticsInfo() throws RemoteException {
        CalculateDataHandler calculateDataHandler = new CalculateDataHandler(code);
        calculateDataHandler.setDate(startDate, endDate).setUnitType(unitType).setBaseCode(baseCode);
        try {
            Map<String, List<PriceInfo>> priciInfos = calculateDataHandler.getDatasByPriceInfo();
            List<PriceInfo> fofPriceInfos = priciInfos.get(code);
            List<PriceInfo> basePriceInfos = priciInfos.get(baseCode);
            List<PriceInfo> relatedPriceInfos = new ArrayList<>();
            for (int i = 0; i < fofPriceInfos.size(); i++) {
                PriceInfo priceInfo = new PriceInfo();
                priceInfo.date = fofPriceInfos.get(i).date;
                priceInfo.rise = fofPriceInfos.get(i).rise - basePriceInfos.get(i).rise;
                priceInfo.price = fofPriceInfos.get(i).rise;
                priceInfo.total_netWorth = basePriceInfos.get(i).rise;
                relatedPriceInfos.add(priceInfo);
            }
            List<Double> rise_data, drop_data;
            rise_data = new ArrayList<>();
            drop_data = new ArrayList<>();
            double total_rise = 0, total_drop = 0, total = 0;
            int max_rise_sequence = 0, max_drop_sequence = 0, max_zero_sequence = 0;
            int rise_sequence_num = 0, drop_sequence_num = 0, zero_sequence_num = 0;
            int num = 0;
            int sign = getSign(relatedPriceInfos.get(0).rise);
            for (PriceInfo priceInfo : relatedPriceInfos) {
                double rise = priceInfo.rise / 100;
                total += rise;
                int currentSign = getSign(rise);
                switch (currentSign) {
                    case 1:
                        total_rise += rise;
                        rise_data.add(rise);
                        if (sign > 0) {
                            num++;
                        } else {
                            if (sign < 0) {
                                max_drop_sequence = num > max_drop_sequence ? num :
                                        max_drop_sequence;
                                drop_sequence_num++;
                            } else {
                                max_zero_sequence = num > max_zero_sequence ? num : max_zero_sequence;
                                zero_sequence_num++;
                            }
                            num = 1;
                            sign = 1;
                        }
                        break;
                    case -1:
                        total_drop += rise;
                        drop_data.add(rise);
                        if (sign < 0) {
                            num++;
                        } else {
                            if (sign == 0) {
                                max_zero_sequence = num > max_zero_sequence ? num : max_zero_sequence;
                                zero_sequence_num++;
                            } else {
                                max_rise_sequence = num > max_rise_sequence ? num :
                                        max_rise_sequence;
                                rise_sequence_num++;
                            }
                            num = 1;
                            sign = -1;
                        }
                        break;
                    case 0:
                        if (sign == 0) {
                            num++;
                        } else {
                            if (sign > 0) {
                                max_rise_sequence = num > max_rise_sequence ? num :
                                        max_rise_sequence;
                                rise_sequence_num++;
                            } else {
                                max_drop_sequence = num > max_drop_sequence ? num : max_drop_sequence;
                                drop_sequence_num++;
                            }
                            num = 1;
                            sign = 0;
                        }
                        break;
                }
            }


            ProfitStatisticsInfoOne periodNum = new ProfitStatisticsInfoOne();
            periodNum.relatedRise = rise_data.size();
            periodNum.reletedDrop = drop_data.size();
            periodNum.total = relatedPriceInfos.size();
            periodNum.zeroRise = periodNum.total - periodNum.relatedRise - periodNum.reletedDrop;
            ProfitStatisticsInfoOne percent = new ProfitStatisticsInfoOne();
            percent.reletedDrop = periodNum.reletedDrop / periodNum.total * 100;
            percent.relatedRise = periodNum.relatedRise / periodNum.total * 100;
            percent.zeroRise = periodNum.zeroRise / periodNum.total * 100;
            percent.total = 100;
            ProfitStatisticsInfoOne ave = new ProfitStatisticsInfoOne();
            ave.relatedRise = total_rise / periodNum.relatedRise * 100;
            ave.reletedDrop = total_drop / periodNum.reletedDrop * 100;
            ave.total = total / periodNum.total * 100;
            ave.zeroRise = 0;
            ProfitStatisticsInfoOne sd = new ProfitStatisticsInfoOne();
            sd.relatedRise = LogicUtil.standardDeviation(rise_data, ave.relatedRise / 100) * 100;
            sd.reletedDrop = LogicUtil.standardDeviation(drop_data, ave.reletedDrop / 100) * 100;
            sd.total = LogicUtil.standardDeviation(relatedPriceInfos.stream().map(e -> e.rise)
                    .collect(Collectors.toList()), ave.total / 100) * 100;
            sd.zeroRise = 0;
            ProfitStatisticsInfoOne max_sequence = new ProfitStatisticsInfoOne();
            max_sequence.relatedRise = max_rise_sequence;
            max_sequence.reletedDrop = max_drop_sequence;
            max_sequence.zeroRise = max_zero_sequence;
            max_sequence.total = 0;
            ProfitStatisticsInfoOne ave_sequence = new ProfitStatisticsInfoOne();
            ave_sequence.relatedRise = periodNum.relatedRise / (rise_sequence_num + 0.00001);
            ave_sequence.reletedDrop = periodNum.reletedDrop / (drop_sequence_num + 0.00001);
            ave_sequence.zeroRise = periodNum.zeroRise / (zero_sequence_num + 0.00001);
            ave_sequence.total = 0;
            Collections.sort(relatedPriceInfos, (e1, e2) -> (e1.rise < e2.rise ? 1 :
                    (e1.rise > e2.rise ? -1 : 0)));
            ProfitStatisticsInfoTwo[] topRise = new ProfitStatisticsInfoTwo[3];
            ProfitStatisticsInfoTwo[] topDrop = new ProfitStatisticsInfoTwo[3];
            for (int i = 0; i < 3; i++) {
                topRise[i] = new ProfitStatisticsInfoTwo();
                topDrop[i] = new ProfitStatisticsInfoTwo();
                PriceInfo risePriceInfo = relatedPriceInfos.get(i);
                PriceInfo dropPriceInfo = relatedPriceInfos.get(relatedPriceInfos.size()
                        - i - 1);
                topRise[i].relatedProfit = risePriceInfo.rise;
                topRise[i].happenDate = risePriceInfo.date;
                topRise[i].combinationProfit = risePriceInfo.price;
                topRise[i].baseProfit = risePriceInfo.total_netWorth;
                topDrop[i].relatedProfit = dropPriceInfo.rise;
                topDrop[i].happenDate = dropPriceInfo.date;
                topDrop[i].combinationProfit = dropPriceInfo.price;
                topDrop[i].baseProfit = dropPriceInfo.total_netWorth;
            }

            ProfitStatisticsInfo profitStatisticsInfo = new ProfitStatisticsInfo();
            profitStatisticsInfo.periodNum = periodNum;
            profitStatisticsInfo.percentage = percent;
            profitStatisticsInfo.average = ave;
            profitStatisticsInfo.standardDeviation = sd;
            profitStatisticsInfo.aveSequence = ave_sequence;
            profitStatisticsInfo.maxSequence = max_sequence;
            profitStatisticsInfo.oneRise = topRise[0];
            profitStatisticsInfo.twoRise = topRise[1];
            profitStatisticsInfo.threeRise = topRise[2];
            profitStatisticsInfo.oneDrop = topDrop[0];
            profitStatisticsInfo.twoDrop = topDrop[1];
            profitStatisticsInfo.threeDrop = topDrop[2];
            NumberOpe.controlDecimal(profitStatisticsInfo, 2, ProfitStatisticsInfoOne.class,
                    ProfitStatisticsInfoTwo.class);
            return profitStatisticsInfo;

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private int getSign(double rise) {
        return rise > 0 ? 1 : (rise < 0 ? -1 : 0);
    }


    @Override
    public List<PriceInfo> getPriceInfo() throws RemoteException {
        CalculateDataHandler calculateDataHandler = new CalculateDataHandler(code);
        calculateDataHandler.setDate(startDate, endDate).setUnitType(unitType).setBaseCode(baseCode);
        try {
            Map<String, List<PriceInfo>> priceInfos = calculateDataHandler.getDatasByPriceInfo();
            return priceInfos.get(code);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
