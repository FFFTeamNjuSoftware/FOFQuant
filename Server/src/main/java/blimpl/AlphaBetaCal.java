package blimpl;

import beans.ConstParameter;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import exception.NotInitialedExcepiton;
import exception.ObjectNotFoundException;
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

    public double[] getAlphaBeta() {
        try {
            MarketLogic marketLogic = BLController.getMarketLogic();
            BaseInfoLogic baseInfoLogic = BLController.getBaseInfoLogic();
            ConstParameter constParameter = baseInfoLogic.getConstaParameteer();
            List<Double> price = marketLogic.getPriceInfo(code, UnitType.DAY).stream().map(e -> e
                    .rise / 100).collect(Collectors.toList());
            List<Double> basePrice = marketLogic.getPriceInfo("I000011", UnitType.DAY).stream().map(e
                    -> e.rise / 100).collect(Collectors.toList());
            int size = basePrice.size();
            basePrice = basePrice.subList(size - price.size(), size);
//            System.out.println(price.size() + "," + basePrice.size());
            Object[] objs = MatlabBoot.getCalculateTool().singleIndexModule(2, TypeConverter
                    .convertList(basePrice), TypeConverter.convertList(price), constParameter
                    .noRiskProfit / 100);
            double[] re = new double[2];
            MWNumericArray array = (MWNumericArray) objs[0];
            MWNumericArray array1 = (MWNumericArray) objs[1];
            re[0] = array.getDouble(1);
            re[1] = array1.getDouble(1);
            return re;
        } catch (NotInitialedExcepiton e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {

        }
        return null;
    }
}
