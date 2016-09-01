package blimpl;

import beans.ConstParameter;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import exception.NotInitialedException;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import matlabtool.TypeConverter;
import startup.MatlabBoot;
import util.UnitType;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Daniel on 2016/8/29.
 */
public class AlphaBetaCal {
    private String code;

    public AlphaBetaCal(String code) {
        this.code = code;
    }

    public static double[] getAlphaBeta(List<Double> base, List<Double> data) {
        try {
            BaseInfoLogic baseInfoLogic = BLController.getBaseInfoLogic();
            ConstParameter constParameter = baseInfoLogic.getConstaParameteer();
            Object[] objs = MatlabBoot.getCalculateTool().singleIndexModule(2, TypeConverter
                    .convertList(base), TypeConverter.convertList(data), constParameter
                    .noRiskProfit / 100, 1.0);
            double[] re = new double[2];
            System.out.println("————————————————————");
            for(Object obj:objs)
                System.out.println(obj);
            System.out.println("————————————————————");
            MWNumericArray array = (MWNumericArray) objs[0];
            MWNumericArray array1 = (MWNumericArray) objs[1];
            re[0] = array.getDouble(1);
            re[1] = array1.getDouble(1);
            System.out.println(re[0] + "," + re[1]);
            return re;
        } catch (NotInitialedException e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }


    public double[] getAlphaBeta() {
        try {
            MarketLogic marketLogic = BLController.getMarketLogic();
            List<Double> price = marketLogic.getPriceInfo(code, UnitType.DAY, 252).stream()
                    .map(e -> e.rise / 100).collect(Collectors.toList());
            List<Double> basePrice = marketLogic.getPriceInfo("I000011", UnitType.DAY, 252).stream
                    ().map(e -> e.rise / 100).collect(Collectors.toList());
            return getAlphaBeta(basePrice, price);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {

        } catch (ParameterException e) {

        }
        return null;
    }
}
