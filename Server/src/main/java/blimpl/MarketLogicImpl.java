package blimpl;

import beans.PriceInfo;
import bl.MarketLogic;
import dataservice.MarketDataService;
import dataserviceimpl.DataServiceController;
import entities.NetWorthEntity;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import util.CalendarOperate;
import util.DateConstraint;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Daniel on 2016/8/15.
 */
public class MarketLogicImpl extends UnicastRemoteObject implements MarketLogic {

    private static MarketLogic instance;
    private MarketDataService marketDataService;

    private MarketLogicImpl() throws RemoteException {
        marketDataService = DataServiceController.getMarketDataService();
    }

    public static MarketLogic getInstance() {
        if (instance == null)
            try {
                instance = new MarketLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type) throws RemoteException, ObjectNotFoundException {
        List<NetWorthEntity> entities = marketDataService.getNetWorth(code);
        List<PriceInfo> infos = new ArrayList<>();
        DateConstraint constraint = DateConstrainGenerator.getDateConstrainByUnitType(type);
        for (int i = 0; i < entities.size(); i++) {
            if (i == entities.size() - 1)
                break;
            if (constraint.value(CalendarOperate.getCalendarByString(entities.get(i).getDate()
                    .toString()), CalendarOperate.getCalendarByString(entities.get(i + 1).getDate()
                    .toString())))
                infos.add(Converter.convertPriceInfo(entities.get(i)));
        }
        return infos;
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, int counts) throws RemoteException, ObjectNotFoundException, ParameterException {
        List<PriceInfo> infos = getPriceInfo(code, type);
        int size = infos.size();
        if (counts > size)
            throw new ParameterException("required counts:" + counts + " is larger than true info " +
                    "counts:" + size);
        return infos.subList(size - counts, size);
    }

    @Override
    public List<PriceInfo> getPriceInfo(String code, UnitType type, String startDate, String endDate) throws RemoteException, ObjectNotFoundException, ParameterException {
        if (startDate.compareTo(endDate) > 0)
            throw new ParameterException("starDate:" + startDate + " is behind endDate:" + endDate);
        List<PriceInfo> infos = getPriceInfo(code, type);
        Collections.sort(infos, (e1, e2) -> e1.date.compareTo(e2.date));
        int startIndex = 0, endIndex = infos.size()-1;
        while (startDate.compareTo(infos.get(startIndex).date) > 0)
            startIndex++;
        while (endDate.compareTo(infos.get(endIndex).date) < 0)
            endIndex--;
        endIndex++;
        return infos.subList(startIndex, endIndex);
    }
}
