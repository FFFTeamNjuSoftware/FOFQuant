package ui.userUI.marketUI;

import RMIModule.BLInterfaces;
import beans.*;
import bl.BaseInfoLogic;
import bl.InvestmentPortfolioLogic;
import bl.MarketLogic;
import bl.ProfitFeatureLogic;
import exception.ObjectNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.userUI.allFundUI.allFundUIController;
import ui.util.BarChartGenerator;
import ui.util.LineChartGenerator;
import ui.util.PieChartGenerator;
import util.ChartType;
import util.HoldingType;
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

    private MarketLogic marketLogic;
    private InvestmentPortfolioLogic investmentLogic;
    private String codeNum = "001547";
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
    private Label rateChart;
    @FXML
    private Label millionWaveChart;
    @FXML
    private Label allocation;
    @FXML
    private Label heavyStock;
    @FXML
    private Label heavyBound;
    @FXML
    private Label industry;
    @FXML
    private AnchorPane profitPane;
    @FXML
    private AnchorPane profitChartPane;
    @FXML
    private AnchorPane assetAllocationPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.codeNum = allFundUIController.getFundId();
        marketLogic = BLInterfaces.getMarketLogic();
        initButton();
        initBaseInfo();
        initProfitFeature();
        initProfitRate();
        initProfitChart(ChartType.RATE_CHART);
        assetAllocation();
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
            invest_strategy.setText(fundInfo.manage_fee+"");
            invest_type.setText(fundInfo.invest_type);
            compare_base.setText(fundInfo.compare_base);
            scale.setText(fundInfo.scale + "");
            code.setText(codeNum);
        }
    }

    /**
     * 效绩评估
     */
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

    /**
     * 收益率指标
     */
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

    /**
     * 万元波动图和折价/溢价率
     *
     * @param chartType
     */
    public void initProfitChart(ChartType chartType) {
        String[] names = {"基金", "基金指数", "沪深300指数"};
        LineChartGenerator generator = new LineChartGenerator(profitChartPane, names);
        List<ProfitChartInfo> list = null;
        try {
            list = marketLogic.getFundProfitInfoChart(codeNum, UnitType.MONTH, TimeType.SINCE_ESTABLISH, chartType);
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

        for (int i = 0; i < list.size(); i++) {
            ProfitChartInfo info = list.get(i);
            for (int j = 0; j < 3; j++) {
                nums[j][i] = info.values[j];
            }
        }
        generator.setData(year, nums);
    }

    public void initButton() {
        rateChart.setOnMouseClicked((e) -> {
            profitChartPane.getChildren().clear();
            initProfitChart(ChartType.RATE_CHART);
        });
        millionWaveChart.setOnMouseClicked((e) -> {
            profitChartPane.getChildren().clear();
            initProfitChart(ChartType.MILLION_WAVE_CHART);
        });
        allocation.setOnMouseClicked((e) -> {
            assetAllocationPane.getChildren().clear();
            assetAllocation();
        });
        heavyStock.setOnMouseClicked((e) -> {
            assetAllocationPane.getChildren().clear();
            setHeavy(HoldingType.STOCK);
        });
        heavyBound.setOnMouseClicked((e) -> {
            assetAllocationPane.getChildren().clear();
            setHeavy(HoldingType.BOND);
        });
        industry.setOnMouseClicked((e) -> {
            assetAllocationPane.getChildren().clear();
            setHeavy(HoldingType.INDUSTRY);
        });
    }

    /**
     * 资产配置的分布图
     */
    public void assetAllocation() {
        investmentLogic = BLInterfaces.getInvestmentPortfolioLogic();
        List<AssetAllocation> list = null;
        try {
            list = investmentLogic.getAssetAllocation(codeNum);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            AssetAllocation asset = list.get(0);
            ObservableList<PieChart.Data> datas = FXCollections.observableArrayList();
            datas.add(new PieChart.Data("股票", asset.stock_value));
            datas.add(new PieChart.Data("债券", asset.bond_value));
            datas.add(new PieChart.Data("现金", asset.cash_value));
            datas.add(new PieChart.Data("其他资产", asset.other_value));
            new PieChartGenerator(assetAllocationPane, datas);
        }

    }

    /**
     * 重仓股票重仓债券
     */
    public void setHeavy(HoldingType type) {
        List<HoldingInfo> list = null;
        try {
            list = investmentLogic.getHoldingInfos(codeNum, type);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
            HoldingInfo info = list.get(0);
            List<HoldingUnit> units = info.items;
            for (HoldingUnit unit : units) {
                dataSeries.getData().add(new XYChart.Data(unit.name, unit.ratio));
            }
            new BarChartGenerator(assetAllocationPane,"股票名称","占比",dataSeries);
        }
    }
}
