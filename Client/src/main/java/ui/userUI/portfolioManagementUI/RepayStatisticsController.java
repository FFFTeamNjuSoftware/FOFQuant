package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.PriceInfo;
import beans.ProfitStatisticsInfo;
import bl.fof.FOFProfitStatisticsLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.BarChartGenerator;
import util.FOFUtilInfo;
import util.UnitType;

import java.lang.reflect.Field;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/8/29.
 */

/**
 * 回报统计
 */
public class RepayStatisticsController implements Initializable {
    @FXML
    Group infoOneGroup;
    @FXML
    Group infoTwoGroup;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    ComboBox<String> statisticsPeriod;
    @FXML
    ComboBox<String> repayPeriod;
    @FXML
    AnchorPane barChartPane;

    private FOFProfitStatisticsLogic logic;

    private ProfitStatisticsInfo profitInfo;

    private final String[] unitTypes = {"日", "周", "月", "季度", "年"};
    private final String[] percentLabels = {"percentage", "average", "standardDeviation",
            "threeRise", "threeDrop", "oneDrop", "twoRise", "oneRise", "twoDrop", ""};

    private String sDate = "2016-01-01";
    private String eDate = "2016-09-10";
    private UnitType unitType = UnitType.DAY;
    private String principle = "上证基金指数";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = BLInterfaces.getFofProfitStatisticsLogic();
        try {
            logic.setStartDate(sDate);
            logic.setEndDate(eDate);
            logic.setUnitType(unitType);
            logic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get(principle));
            profitInfo = logic.getProfitStatisticsInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        setDate();
        initComboBox();
        refresh();
    }

    /**
     * 上半部分的数据展示
     *
     * @param infoGroup
     */
    public void initBasicInfo(Group infoGroup) {
        try {
            profitInfo = logic.getProfitStatisticsInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (profitInfo != null) {
            ObservableList<Node> groups = infoGroup.getChildren();
            for (Node group : groups) {
                String groupId = group.getId();
                ObservableList<Node> labels = ((Group) group).getChildren();
                try {
                    Field field = profitInfo.getClass().getDeclaredField(groupId);
                    field.setAccessible(true);
                    Object object = field.get(profitInfo);
                    for (Node label : labels) {
                        String labelId = label.getId();
                        Label lab = (Label) label;
                        Field field1 = object.getClass().getDeclaredField(labelId);
                        field1.setAccessible(true);
                        Object num = field1.get(object);
                        if (isContained(groupId)) {
                            lab.setText(num.toString() + "%");
                        }else{
                            lab.setText(num.toString() + "");
                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setDate() {
        startDate.setOnAction((e) -> {
            LocalDate date = startDate.getValue();
            try {
                if (judgeDate()) {
                    this.sDate = date.toString();
                    logic.setStartDate(sDate);
                    refresh();
                }
            } catch (ParameterException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        endDate.setOnAction((e) -> {
            LocalDate date = endDate.getValue();
            try {
                if (judgeDate()) {
                    this.eDate = date.toString();
                    logic.setEndDate(eDate);
                    refresh();
                }
            } catch (ParameterException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void initChart() {
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        List<PriceInfo> list = null;
        try {
            list = logic.getPriceInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (PriceInfo info : list) {
                String simpleDate = info.date.substring(5);
                dataSeries.getData().add(new XYChart.Data(simpleDate, info.rise));
            }
        }
        BarChartGenerator g = new BarChartGenerator(barChartPane, "", "回报（%）", dataSeries);
        g.setTitle("回报统计");
    }

    public void initComboBox() {
        repayPeriod.getItems().addAll("沪深300", "上证综指", "上证基金指数");
        repayPeriod.setOnAction((e) -> {
            String str = repayPeriod.getValue();
            if (str != null) {
                this.principle = FOFUtilInfo.performanceBaseInfo.get(str);
                try {
                    logic.setProformanceBase(principle);
                    refresh();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (ObjectNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        statisticsPeriod.getItems().addAll(unitTypes);
        statisticsPeriod.setOnAction((e) -> {
            String str = statisticsPeriod.getValue();
            if (str != null) {
                int i = 0;
                for (i = 0; i < unitTypes.length; i++) {
                    if (str.equals(unitTypes[i])) {
                        break;
                    }
                }
                try {
                    this.unitType = UnitType.values()[i];
                    logic.setUnitType(unitType);
                    refresh();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private boolean judgeDate() {
        LocalDate date = startDate.getValue();
        LocalDate date1 = endDate.getValue();
        if (date1 != null && date != null) {
            if (date1.compareTo(date) < 0) {
                MainUI.getInstance().addInfoPanel("开始日期必须在结束日期之前");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    private void refresh() {
        initBasicInfo(infoOneGroup);
        initBasicInfo(infoTwoGroup);
        barChartPane.getChildren().clear();
        initChart();
    }

    private boolean isContained(String id) {
        for (int i = 0; i < percentLabels.length; i++) {
            if (percentLabels[i].equals(id)) {
                return true;
            }
        }
        return false;
    }
}
