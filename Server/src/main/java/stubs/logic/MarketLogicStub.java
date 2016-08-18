package stubs.logic;

import beans.PriceInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import bl.MarketLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class MarketLogicStub extends UnicastRemoteObject implements MarketLogic {
    public MarketLogicStub() throws RemoteException {

    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException, ObjectNotFoundException {
        List<PriceInfo> priceInfoList = new ArrayList<PriceInfo>();
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.date = "20160101";
        priceInfo.price = 8.8;
        priceInfoList.add(priceInfo);
        return priceInfoList;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws RemoteException, ObjectNotFoundException, ParameterException {
        List<PriceInfo> priceInfoList = new ArrayList<PriceInfo>();
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.date = "20160101";
        priceInfo.price = 8.8;
        priceInfoList.add(priceInfo);
        return priceInfoList;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate) throws RemoteException, ObjectNotFoundException, ParameterException {
        List<PriceInfo> priceInfoList = new ArrayList<PriceInfo>();
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.date = "20160101";
        priceInfo.price = 8.8;
        priceInfoList.add(priceInfo);
        return priceInfoList;
    }

    @Override
    public ProfitRateInfo getProfitRateInfo(String code, UnitType type, TimeType timeType)
            throws RemoteException, ObjectNotFoundException {
        ProfitRateInfo profitRateInfo=new ProfitRateInfo();
        switch (timeType){
            case ONE_MONTH:
                profitRateInfo.nearOneMonth=0.7;
                break;
            case THREE_MONTH:
                profitRateInfo.nearThreeMonth=0.5;
                break;
            case ONE_YEAR:
                profitRateInfo.nearOneYear=0.3;
                break;
            case THREE_YEAR:
                profitRateInfo.nearThreeYear=0.2;
                break;
            case SINCE_ESTABLISH:
                profitRateInfo.sinceEstablish=0.1;
                break;
        }
        return profitRateInfo;
    }

    @Override
    public List<ProfitChartInfo> getFundProfitInfoChart(String code, UnitType type, TimeType
            timeType, ChartType chartType) throws RemoteException, ObjectNotFoundException {
        List<ProfitChartInfo> profitChartInfoList=new ArrayList<>();
        ProfitChartInfo profitChartInfo=new ProfitChartInfo();
        profitChartInfo.date="20160101";
        switch (chartType) {
            case MILLION_WAVE_CHART:
                profitChartInfo.values=new double[3];
                profitChartInfo.values[0]=1;
                profitChartInfo.values[1]=8.8;
                profitChartInfo.values[2]=8.8;
                break;
            case RATE_CHART:
                profitChartInfo.values=new double[3];
                profitChartInfo.values[0]=1;
                profitChartInfo.values[1]=8.8;
                profitChartInfo.values[2]=8.8;
                break;
        }
        profitChartInfoList.add(profitChartInfo);
        return profitChartInfoList;
    }
}
