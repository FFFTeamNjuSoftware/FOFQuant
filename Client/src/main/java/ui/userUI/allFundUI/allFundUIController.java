package ui.userUI.allFundUI;

import RMIModule.BLInterfaces;
import beans.FundQuickInfo;
import beans.PriceInfo;
import beans.ProfitChartInfo;
import bl.BaseInfoLogic;
import bl.MarketLogic;
import exception.ObjectNotFoundException;
import exception.ParameterException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import util.ChartType;
import util.TimeType;
import util.UnitType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/19.
 */
public class allFundUIController implements Initializable {

    @FXML
    private Label tab1,tab2;
    @FXML
    private ImageView tabImage1,tabImage2;

    @FXML
    private TableView<FundQuickInfo> table;
    @FXML
    private TableColumn<FundQuickInfo, String> codeColumn;
    @FXML
    private TableColumn<FundQuickInfo, String> full_nameColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> daily_riseColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> current_netWorthColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearOneMonthColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearThreeMonthColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearSixMonthColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearOneYearColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearThreeYearColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> nearFiveYearColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> sinceEstablishColumn;
    @FXML
    private TableColumn<FundQuickInfo, Number> yearRateColumn;
    @FXML
    private Label fullNameLabel,fundIDLabel;

    @FXML
    private ComboBox comboBox1;
    private String selectedType ;

    private String[] basicTypes ={"固定收益类","权益类","其他类"};
    private String[] marketTypes ={"开放式基金","股票型开放式基金","债券型开放式基金",
            "混合型开放式基金","保本型开放式基金","指数型开放式基金","货币市场基金","QDII基金","LOF基金","ETF基金"};

    private int selectedIndex;
    private FundQuickInfo fundQuickInfo;
    private String fundID,fundName;
    private String selectedSectorID;
    private String[] basicID = new String[]{"000011","000012","000013"};
    private String[] marketID = new String[]{"000001","000002","000003","000004","000005","000006",
            "000007","000008", "000009","000010"};

    private BLInterfaces blInterfaces = new BLInterfaces();
    private BaseInfoLogic baseInfoLogic ;
    private MarketLogic marketLogic;
    private List<PriceInfo> priceInfoList = new  ArrayList<PriceInfo>();
    private List<ProfitChartInfo> profitChartInfoList = new  ArrayList<ProfitChartInfo>();
    private List<FundQuickInfo> fundQuickInfoList = new ArrayList<FundQuickInfo>();
    private int k=0;//标记tab第一次
    private allFundUIController instance;

    @FXML
    private LineChart lineChart1,lineChart2;
    @FXML
    private CategoryAxis date1Axis,date2Axis;
    @FXML
    private NumberAxis y1Axis,y2Axis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       instance= this;
        baseInfoLogic = blInterfaces.getBaseInfoLogic();
        initTab();
        comboBox1.setValue("开放式基金");
        comboBox1.setItems(FXCollections.observableArrayList(marketTypes));
        initComboxBox(marketID);
        init("000001");
    }

    private void init(String sectorID){

        try {
            fundQuickInfoList= baseInfoLogic.getFundQuickInfo(sectorID);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        table.setItems(FXCollections.observableArrayList(fundQuickInfoList));

        table.setRowFactory(new Callback<TableView<FundQuickInfo>, TableRow<FundQuickInfo>>() {
            @Override
            public TableRow<FundQuickInfo> call(TableView<FundQuickInfo> table) {
                // TODO Auto-generated method stub

                return new TableRowControl(table);
            }
        });

        codeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().code));
        full_nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().simple_name));

//        full_nameColumn.setCellFactory(new Callback<TableColumn<FundQuickInfo, String>, TableCell<FundQuickInfo, String>>() {
//            @Override
//            public TableCell<FundQuickInfo, String> call(TableColumn<FundQuickInfo, String> arg0) {
//                return new TableCell<FundQuickInfo, String>() {
//                    ObservableValue ov1;
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (this.getIndex() < fundQuickInfoList.size()) {
//                            if(!isEmpty()){
//                                this.setTextFill(Color.ORANGE);
//                            }
//                        }
//                    }
//                };
//            }
//        });
        daily_riseColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().daily_rise));
        current_netWorthColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().current_netWorth));
        nearOneMonthColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().oneMonth));
        nearThreeMonthColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().threeMonth));
        nearSixMonthColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().halfYear));
        nearOneYearColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().oneYear));
        nearThreeYearColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().threeYear));
        nearFiveYearColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().fiveYear));
        sinceEstablishColumn .setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().sinceEstablish));
        yearRateColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                 cellData.getValue().yearRate));


    }

    private void initTab(){
        final int[] i = {0,1};
        tab1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                tabImage1.setVisible(true);
            }
        });
        tab1.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                i[0] =1;
                i[1] =0;
                 k=1;
                tabImage1.setVisible(true);
                tabImage2.setVisible(false);
                comboBox1.setValue("固定收益类");
                comboBox1.setItems(FXCollections.observableArrayList(basicTypes));
                initComboxBox(basicID);
            }
        });
        tab1.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(k==0&i[1]==0){tabImage1.setVisible(true);}else {
                    if (i[0] == 1) {
                        tabImage1.setVisible(true);
                    } else {
                        tabImage1.setVisible(false);
                    }
                }
            }
        });
        tab1.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                tabImage1.setVisible(true);
                tabImage2.setVisible(false);
            }
        });

        tab2.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                tabImage2.setVisible(true);
            }
        });
        tab2.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                i[1] =1;
                i[0] =0;
                 k=1;
                tabImage2.setVisible(true);
                tabImage1.setVisible(false);
                comboBox1.setValue("开放式基金");
                comboBox1.setItems(FXCollections.observableArrayList(marketTypes));
                initComboxBox(marketID);
            }
        });
        tab2.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                tabImage2.setVisible(true);
                tabImage1.setVisible(false);
            }
        });
        tab2.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(k==0&i[0]==0){tabImage2.setVisible(true);}else {
                    if (i[1] ==1) {
                        tabImage2.setVisible(true);
                    } else {
                        tabImage2.setVisible(false);
                    }
                }
            }
        });

    }

    private void initComboxBox(String[] a ){

        comboBox1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedSectorID = a[newValue.intValue()];
                System.out.println("the selected is: " + selectedSectorID);
//                if(a[0].equals(basicID[0])){selectedSectorID=basicID[newValue.intValue()];}
//                else if(a[0].equals(marketID[0])){selectedSectorID=marketID[newValue.intValue()];}
//                System.out.println("the selected id is: " + selectedSectorID);
                init(selectedSectorID);
            }
        });

    }

    private void initChart1(String code){

        marketLogic=blInterfaces.getMarketLogic();
        //String code, UnitType type, int counts
        try {
            priceInfoList =marketLogic.getPriceInfo(code, UnitType.WEEK,16);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        } catch (ParameterException e) {
            e.printStackTrace();
        }

        lineChart1.setTitle("净值走势");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("单位净值");
        double temp1=priceInfoList.get(0).price;
        for(int i = 0; i< priceInfoList.size(); i++) {
            series1.getData().add(new XYChart.Data(priceInfoList.get(i).date, priceInfoList.get(i).price));
        }
//String code, UnitType type, String startDate, String endDate

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("累计净值");
        double temp2=priceInfoList.get(0).total_netWorth;
        marketLogic=blInterfaces.getMarketLogic();
        for(int i = 0; i< priceInfoList.size(); i++) {
            series2.getData().add(new XYChart.Data(priceInfoList.get(i).date, priceInfoList.get(i).total_netWorth));
        }

        lineChart1.getData().add(0,series1);
        lineChart1.getData().add(1,series2);

    }

    private void initChart2(String code) {

            //String code, UnitType type, TimeType timeType, ChartType chartType
        try {
            profitChartInfoList = marketLogic.getFundProfitInfoChart(code, UnitType.WEEK, TimeType.THREE_MONTH,ChartType.MILLION_WAVE_CHART );
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        
        lineChart2.setTitle("收益走势");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("期间收益率");
        for(int i = 0; i< profitChartInfoList.size(); i++) {
            series1.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[0]));
        }
//String code, UnitType type, String startDate, String endDate

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("上证指数");
        marketLogic=blInterfaces.getMarketLogic();
        for(int i = 0; i< profitChartInfoList.size(); i++) {
            series2.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[2]));
        }

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("基金指数");
        marketLogic=blInterfaces.getMarketLogic();
        for(int i = 0; i< profitChartInfoList.size(); i++) {
            series3.getData().add(new XYChart.Data(profitChartInfoList.get(i).date, profitChartInfoList.get(i).values[1]));
        }

        lineChart2.getData().add(0,series1);
        lineChart2.getData().add(1,series2);
        lineChart2.getData().add(2,series3);



    }
    public class TableRowControl<T> extends TableRow<T> {

        public TableRowControl(TableView<T> tableView) {
            super();
            this.setTextFill(Color.RED);
            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
                        selectedIndex = TableRowControl.this.getIndex();
                        fundID = codeColumn.getCellData(selectedIndex);
                        fundName = full_nameColumn.getCellData(selectedIndex);
                        fullNameLabel.setText(fundName);
                        fundIDLabel.setText(fundID);
                        lineChart1.getData().clear();
                        lineChart2.getData().clear();
                        initChart1(fundID);
                        initChart2(fundID);
                    }
                    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                        selectedIndex = TableRowControl.this.getIndex();
                        fundID = codeColumn.getCellData(selectedIndex);
                        System.out.println("......Enter :" + fundID + " panel......");
                    }

                }
            });
        }
    }
}

