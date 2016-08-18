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
        return null;
    }

    @Override
    public List<ProfitChartInfo> getMillionWaveChart(String code, UnitType type, TimeType
            timeType, ChartType chartType) throws RemoteException, ObjectNotFoundException {
        return null;
    }
}
