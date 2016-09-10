package ui.userUI.portfolioManagementUI;

/**
 * Created by tjDu on 2016/8/30.
 */

import RMIModule.BLInterfaces;
import beans.PerformanceAttribution;
import bl.fof.FOFPerformanceAttributionLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import starter.MainUI;
import ui.util.IOHelper;
import util.FOFUtilInfo;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 业绩归因
 */
public class PerformanceAttributionController implements Initializable {
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<String> classification;
    @FXML
    private TableView<PerformanceAttribution> table;
    @FXML
    private TableColumn<PerformanceAttribution, String> code;
    @FXML
    private TableColumn<PerformanceAttribution, String> name;
    @FXML
    private TableColumn<PerformanceAttribution, String> endingPerValue;
    @FXML
    private TableColumn<PerformanceAttribution, String> endingHoldNum;
    @FXML
    private TableColumn<PerformanceAttribution, String> endingTotalValue;
    @FXML
    private TableColumn<PerformanceAttribution, String> periodProfit;
    @FXML
    private TableColumn<PerformanceAttribution, String> periodProfitFinishProfit;
    @FXML
    private TableColumn<PerformanceAttribution, String> periodProfitRate;
    @FXML
    private TableColumn<PerformanceAttribution, String> category;
    @FXML
    private TableColumn<PerformanceAttribution, String> beginingPerValue;
    @FXML
    private TableColumn<PerformanceAttribution, String> beginingHoldNum;
    @FXML
    private TableColumn<PerformanceAttribution, String> beginingTotalValue;
    @FXML
    private TableColumn<PerformanceAttribution, String> unitProfit;
    @FXML
    private TableColumn<PerformanceAttribution, String> endingHoldRatio;

    private FOFPerformanceAttributionLogic logic;

    private String greenFill = "-fx-text-fill:#9ac94a;";
    private String redFill = "-fx-text-fill:#eb494d;";

    private final String sDate = "2016-01-01";
    private final String eDate = "2016-08-26";
    private final String fundType = "沪深300";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = BLInterfaces.getFofPerformanceAttributionLogic();
        try {
            logic.setStartDate(sDate);
            logic.setEndDate(eDate);
            logic.setProformanceBase(FOFUtilInfo.performanceBaseInfo.get(fundType));
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }
        setDate();
        initComboBox();
        initTable();
    }

    public void setDate() {
        startDate.setOnAction((e) -> {
            LocalDate date = startDate.getValue();
            try {
                if (judgeDate()) {
                    logic.setStartDate(date.toString());
                    initTable();
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
                    logic.setEndDate(date.toString());
                    initTable();
                }
            } catch (ParameterException e1) {
                e1.printStackTrace();
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

        });
    }

    public void initComboBox() {
        classification.getItems().addAll("沪深300", "上证综指", "上证基金指数");
        classification.setOnAction((e) -> {
            String str = classification.getValue();
            if (str != null) {
                String code = FOFUtilInfo.performanceBaseInfo.get(str);
                try {
                    logic.setProformanceBase(code);
                    initTable();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (ObjectNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void initTable() {
        List<PerformanceAttribution> list = null;
        try {
            list = logic.getPerformanceAttribution();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        table.setItems(FXCollections.observableArrayList(list));

        code.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().fundCode));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().fundName));
        endingPerValue.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().endingPerValue + ""));
        endingHoldNum.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().endingHoldNum + ""));
        endingTotalValue.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().endingTotalValue + ""));
        periodProfit.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().periodProfit + ""));
        periodProfitFinishProfit.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().periodProfitFinishProfit + ""));
        periodProfitRate.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().periodProfitRate + ""));
        beginingPerValue.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().beginingPerValue + ""));
        beginingHoldNum.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().beginingHoldNum + ""));
        beginingTotalValue.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().beginingTotalValue + ""));
        unitProfit.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().unitProfit + ""));
        endingHoldRatio.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().endingHoldRatio + ""));
        table.setRowFactory(tv -> {
            TableRow<PerformanceAttribution> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    PerformanceAttribution rowData = row.getItem();
                    IOHelper.writeName(rowData.fundCode);
                    MainUI.getInstance().changeScene("user_guidePanel", "marketPanel");
                }
            });
            return row;
        });

        setColumnColor(periodProfit);
        setColumnColor(periodProfitFinishProfit);
        setColumnColor(periodProfitRate);
    }

    private void setColumnColor(TableColumn<PerformanceAttribution, String> c) {
        c.setCellFactory(column -> {
            return new TableCell<PerformanceAttribution, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(null);
                    setText(empty ? "" : getItem().toString());
                    if (!isEmpty()) {
                        Double t = Double.parseDouble(item);
                        if (t > 0) {
                            c.setStyle(redFill);
                        } else if (t < 0) {
                            c.setStyle(greenFill);
                        }
                    }
                }
            };
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
}
