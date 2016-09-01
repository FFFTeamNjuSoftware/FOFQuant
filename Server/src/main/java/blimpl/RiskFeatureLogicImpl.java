package blimpl;

import beans.ConstParameter;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import bl.RiskFeatureLogic;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import matlabtool.TypeConverter;
import startup.MatlabBoot;
import util.UnitType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/15.
 */
public class RiskFeatureLogicImpl extends UnicastRemoteObject implements RiskFeatureLogic {
    private static RiskFeatureLogic instance;
    private MarketLogic marketLogic;
    private BaseInfoLogic baseInfoLogic;

    private RiskFeatureLogicImpl() throws RemoteException {
        marketLogic = BLController.getMarketLogic();
        baseInfoLogic = BLController.getBaseInfoLogic();
    }

    public static RiskFeatureLogic getInstance() {
        if (instance == null)
            try {
                instance = new RiskFeatureLogicImpl();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        return instance;
    }

    @Override
    public double getStandardDeviation(String code) throws RemoteException {
        try {
            MWNumericArray array = getFundRise(code);
            Object[] objs = MatlabBoot.getCalculateTool().starndarDeviation(1, array);
            return ((MWNumericArray) objs[0]).getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getBeta(String code) throws RemoteException {
        AlphaBetaCal alphaBetaCal = new AlphaBetaCal(code);
        double[] re = alphaBetaCal.getAlphaBeta();
        if (re == null) {
            return 0;
        }
        return re[1];
    }

    @Override
    public double getJansen(String code) throws RemoteException {
        try {
            MWNumericArray base = getBasePrice("I000300");
            MWNumericArray fund = getFundRise(code);
            ConstParameter constParameter = baseInfoLogic.getConstaParameteer();
            Object[] objs = MatlabBoot.getCalculateTool().calJensen(1, fund, base, constParameter
                    .noRiskProfit / 100);
            return ((MWNumericArray) objs[0]).getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getTreynor(String code) throws RemoteException {
        try {
            MWNumericArray base = getBasePrice("I000300");
            MWNumericArray fund = getFundRise(code);
            ConstParameter constParameter = baseInfoLogic.getConstaParameteer();
            Object[] objs = MatlabBoot.getCalculateTool().calTreynor(1, fund, base, constParameter
                    .noRiskProfit / 100);
            return ((MWNumericArray) objs[0]).getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getSharpe(String code) throws RemoteException {
        try {
            MWNumericArray fund = getFundRise(code);
            ConstParameter constParameter = baseInfoLogic.getConstaParameteer();
            Object[] objs = MatlabBoot.getCalculateTool().calSharpe(1, fund, constParameter
                    .noRiskProfit / 100);
            return ((MWNumericArray) objs[0]).getDouble(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private MWNumericArray getFundRise(String code) throws Exception {
        return TypeConverter.convertList(marketLogic.getPriceInfo(code, UnitType.DAY, 252).stream()
                .map(e -> e.rise / 100).collect(Collectors.toList()));
    }

    private MWNumericArray getBasePrice(String code) throws Exception {
        return TypeConverter.convertList(marketLogic.getPriceInfo(code, UnitType.DAY, 252)
                .stream().map(e -> e.rise / 100).collect(Collectors.toList()));
    }
}
