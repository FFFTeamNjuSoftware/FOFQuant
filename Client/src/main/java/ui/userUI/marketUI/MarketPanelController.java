package ui.userUI.marketUI;

import RMIModule.BLInterfaces;
import beans.FundInfo;
import beans.ProfitChartInfo;
import beans.ProfitRateInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import bl.ProfitFeatureLogic;
import exception.ObjectNotFoundException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.util.LineChartGenerator;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.lang.reflect.Field;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tj on 2016/8/18.
 */
public class MarketPanelController implements Initializable {
    private String codeNum = "001547";
    private MarketLogic marketLogic;
    private String greenFill = "-fx-text-fill:#9ac94a;";
    private String redFill = "-fx-text-fill:#eb494d;";
    @FXML
    private Label establish_date;
    @FXML
    private Label establish_scale;
    @FXML
    private Label invest_strategy;
    @FXML
    private Label invest_type;
    @FXML
    private Label compare_base;
    @FXML
    private Label scale;
    @FXML
    private Label code;
    @FXML
    private Label riskProfitRate;
    @FXML
    private Label ensembleAveProfitRate;
    @FXML
    private Label aveProfitRate;
    @FXML
    private Label alpha;
    @FXML
    private AnchorPane profitPane;
    @FXML
    private AnchorPane profitChartPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        marketLogic = BLInterfaces.getMarketLogic();
//        initBaseInfo();
//        initProfitFeature();
//        initProfitRate();
//        initProfitChart();
    }

    public void initBaseInfo() {
        BaseInfoLogic baseInfoLogic = BLInterfaces.getBaseInfoLogic();
        FundInfo fundInfo = null;
        try {
            fundInfo = baseInfoLogic.getFundBaseInfo(codeNum);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (fundInfo != null) {
            establish_date.setText(fundInfo.establish_date);
            establish_scale.setText(fundInfo.establish_scale + "");
            invest_strategy.setText(fundInfo.invest_strategy);
            invest_type.setText(fundInfo.invest_type);
            compare_base.setText(fundInfo.compare_base);
            scale.setText(fundInfo.scale + "");
            code.setText(codeNum);
        }
    }

    public void initProfitFeature() {
        ProfitFeatureLogic pflogic = BLInterfaces.getProfitFeatureLogic();
        try {
            riskProfitRate.setText(pflogic.riskProfitRate(codeNum) + "");
            ensembleAveProfitRate.setText(pflogic.getEnsembleAveProfitRate(codeNum) + "");
            aveProfitRate.setText(pflogic.aveProfitRate(codeNum) + "");
            alpha.setText(pflogic.getAlpha(codeNum) + "");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void initProfitRate() {
        ProfitRateInfo profitRate = null;
        try {
            profitRate = marketLogic.getProfitRateInfo(codeNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (profitRate != null) {
            ObservableList<Node> labels = profitPane.getChildren();
            for (Node node : labels) {
                Label label = (Label) node;
                String id = label.getId();
                try {
                    Field field = profitRate.getClass().getDeclaredField(id);
                    field.setAccessible(true);
                    Double temp = field.getDouble(profitRate) * 100;
                    label.setText(temp + "%");
                    if (temp >= 0) {
                        label.setStyle(redFill);
                    } else {
                        label.setStyle(greenFill);
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void initProfitChart() {
        String[] names = {"基金", "基金指数", "沪深300指数"};
        LineChartGenerator generator = new LineChartGenerator(profitChartPane, names);
        List<ProfitChartInfo> list = null;
        try {
            list = marketLogic.getFundProfitInfoChart(codeNum, UnitType.MONTH, TimeType.SINCE_ESTABLISH, ChartType.MILLION_WAVE_CHART);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        String[] year = new String[list.size()];
        double[][] nums = new double[3][list.size()];

        for (int i = 0; i < list.size(); i++) {
            ProfitChartInfo info = list.get(i);
            year[i] = info.date;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < list.size(); j++) {
                ProfitChartInfo info = list.get(j);
                nums[i][j] = info.values[j];
            }
        }

        generator.setData(year, nums);
    }
}
