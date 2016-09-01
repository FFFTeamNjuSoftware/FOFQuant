package blimpl.fof;

import CalculateTool.CalculateTool;
import beans.ConstParameter;
import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import blimpl.BLController;
import blimpl.CalculateDataHandler;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import exception.NotInitialedException;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import matlabtool.TypeConverter;
import startup.MatlabBoot;
import util.FOFUtilInfo;
import util.IndexCodeInfo;
import util.TimeType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/26.
 */
public class FOFProfitAnalyseLogicImpl extends UnicastRemoteObject implements FOFProfitAnalyseLogic {
    private String startDate;
    private String endDate;
    private String baseCode;
    private String fof_code;

    private FOFProfitAnalyseLogicImpl() throws RemoteException {
        fof_code = FOFUtilInfo.FOF_CODE;

    }

    private static FOFProfitAnalyseLogic instance;

    public static FOFProfitAnalyseLogic getInstance() {
        if (instance == null)
            try {
                instance = new FOFProfitAnalyseLogicImpl();
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
        this.baseCode = indexCode;
        if (!IndexCodeInfo.INDEX_CODES.contains(indexCode.substring(1))) {
            throw new ObjectNotFoundException("indexCode:" + indexCode + " not found");
        }
    }

    @Override
    public FOFProfitAnalyse getFOFProfitAnalyse(TimeType timeType) throws RemoteException {
        CalculateDataHandler calculateDataHandler = new CalculateDataHandler(fof_code);
        calculateDataHandler.setBaseCode(baseCode);
        if (timeType == TimeType.SELECT_PERIOD) {
            calculateDataHandler.setDate(startDate, endDate);
        } else {
            calculateDataHandler.setTimeType(timeType);
        }
        try {
            Map<String, List<Double>> infos = calculateDataHandler.getDatasByRise();
            List<Double> fof_info = infos.get(fof_code);
            List<Double> base_info = infos.get(baseCode);
            double total_profit = 0, releted_total_profit = 0, max_rise = Double.MIN_VALUE,
                    min_rise = Double.MAX_VALUE;
            int sequence_num = 0, max_rise_sequence_num = 0, max_drop_sequence_num = 0,
                    last_min_rise_index = 0, last_max_rise_index = 0, max_rise_recover_num = 0,
                    max_drop_recover_num = 0;
            double sign = fof_info.get(0);

            for (int i = 0; i < fof_info.size(); i++) {
                double fofRise = fof_info.get(i);
                double baseRise = base_info.get(i);
                total_profit += fofRise;
                releted_total_profit += fofRise - baseRise;
                max_rise = (fofRise > max_rise) ? fofRise : max_rise;
                min_rise = (fofRise < min_rise) ? fofRise : min_rise;
                if (fofRise >= 0) {
                    if (sign >= 0) {
                        sequence_num++;
                        sign = fofRise;
                    } else {
                        int tem_sequence = i - 1 - last_min_rise_index;
                        max_drop_recover_num = (tem_sequence > max_drop_recover_num)
                                ? tem_sequence : max_drop_recover_num;
                        last_min_rise_index = i - 1;
                        max_drop_sequence_num = (sequence_num > max_drop_sequence_num)
                                ? sequence_num : max_drop_sequence_num;

                    }
                } else {
                    if (sign < 0) {
                        sequence_num++;
                        sign = fofRise;
                    } else {
                        int tem_sequence = i - 1 - last_max_rise_index;
                        max_rise_recover_num = (tem_sequence > max_rise_recover_num)
                                ? tem_sequence : max_rise_recover_num;
                        last_max_rise_index = i - 1;
                        max_rise_sequence_num = (sequence_num > max_rise_sequence_num)
                                ? sequence_num : max_rise_sequence_num;
                    }
                }
            }
            int i = fof_info.size() - 1;
            while (true) {
                if (fof_info.get(i) > 0) {
                    int tem_sequence = i - last_max_rise_index;
                    max_rise_recover_num = (tem_sequence > max_rise_recover_num)
                            ? tem_sequence : max_rise_recover_num;
                    max_rise_sequence_num = (sequence_num > max_rise_sequence_num)
                            ? sequence_num : max_rise_sequence_num;
                    break;
                } else if (fof_info.get(i) < 0) {
                    int tem_sequence = i - last_min_rise_index;
                    max_drop_recover_num = (tem_sequence > max_drop_recover_num)
                            ? tem_sequence : max_drop_recover_num;
                    max_drop_sequence_num = (sequence_num > max_drop_sequence_num)
                            ? sequence_num : max_drop_sequence_num;
                    break;
                }
                i--;
            }
            FOFProfitAnalyse fofProfitAnalyse = new FOFProfitAnalyse();
            fofProfitAnalyse.totalProfit = total_profit;
            fofProfitAnalyse.relatedTotalProfit = releted_total_profit;
            fofProfitAnalyse.maxRise = max_rise;
            fofProfitAnalyse.minRise = min_rise;
            fofProfitAnalyse.maxRiseDays = max_rise_sequence_num;
            fofProfitAnalyse.minRiseDays = max_drop_sequence_num;
            fofProfitAnalyse.maxRiseRecoverDays = max_rise_recover_num;
            fofProfitAnalyse.minRiseRecoverDays = max_drop_recover_num;
            fofProfitAnalyse.yearProfitRate = total_profit / fof_info.size() * 252;
            fofProfitAnalyse.yearRelatedProfitRate = releted_total_profit / fof_info.size() * 252;

            CalculateTool calculateTool = MatlabBoot.getCalculateTool();
            MWNumericArray fof_info_mwn = TypeConverter.convertList(fof_info);
            MWNumericArray base_info_mwn = TypeConverter.convertList(base_info);
            ConstParameter constParameter = BLController.getBaseInfoLogic().getConstaParameteer();
            double[] alphaBeta = TypeConverter.getDoubleResults(calculateTool.singleIndexModule(2, base_info_mwn,
                    fof_info_mwn, constParameter.noRiskProfit), 2);
            fofProfitAnalyse.alpha = alphaBeta[0];
            fofProfitAnalyse.beta = alphaBeta[1];
            fofProfitAnalyse.treynor = TypeConverter.getSingleDoubleResult(calculateTool.calTreynor(1,
                    fof_info_mwn, base_info_mwn));
            fofProfitAnalyse.sharpe = TypeConverter.getSingleDoubleResult(calculateTool.calSharpe
                    (1, base_info_mwn, constParameter.noRiskProfit));
            fofProfitAnalyse.yearWaveRate = TypeConverter.getSingleDoubleResult(calculateTool
                    .yearWaveRate(1, fof_info_mwn));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (NotInitialedException e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        }
        return null;
    }
}
