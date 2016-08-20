package ui.userUI.allFundUI;

import RMIModule.BLInterfaces;
import beans.FundQuickInfo;
import bl.BaseInfoLogic;
import exception.ObjectNotFoundException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;

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
    private ComboBox comboBox;
    private String selectedType ;

    private String[] basicTypes ={"固定收益类","权益类","其他类"};
    private String[] marketTypes ={"开放式基金","股票型开放式基金","债券型开放式基金",
            "混合型开放式基金","保本型开放式基金","指数型开放式基金","货币市场基金","QDII基金","LOF基金","ETF基金"};

    private int selectedIndex;
    private FundQuickInfo fundQuickInfo;
    private String fundID;
    private String selectedSectorID;
    private String[] basicID = new String[]{"000011","000012","000013"};
    private String[] marketID = new String[]{"000001","000002","000003","000004","000005","000006",
            "000007","000008", "000009","000010"};

    private BLInterfaces blInterfaces = new BLInterfaces();
    private BaseInfoLogic baseInfoLogic ;
    private List<FundQuickInfo> fundQuickInfoList = new ArrayList<FundQuickInfo>();

    private allFundUIController instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       instance= this;
        baseInfoLogic = blInterfaces.getBaseInfoLogic();
        initTab();
        comboBox.setValue("固定收益类");
        comboBox.setItems(FXCollections.observableArrayList(basicTypes));
        initComboxBox();
        init("000011");
    }

    public void init(String sectorID){

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

        daily_riseColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(
                cellData.getValue().daily_rise));
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

    public void initTab(){
        tab1.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                tabImage1.setVisible(true);
            }
        });
        tab1.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                tabImage1.setVisible(true);
                tabImage2.setVisible(false);
                comboBox.setValue("固定收益类");
                comboBox.setItems(FXCollections.observableArrayList(marketTypes));

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
                tabImage2.setVisible(true);
                tabImage1.setVisible(false);
                comboBox.setValue("开放式基金");
                comboBox.setItems(FXCollections.observableArrayList(basicTypes));
            }
        });

    }

    public void initComboxBox(){

        comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                selectedType = basicTypes[newValue.intValue()];
                System.out.println("the selected is: " + selectedType);
                selectedSectorID=basicID[newValue.intValue()];
                System.out.println("the selected id is: " + selectedSectorID);
                init(selectedSectorID);
            }
        });

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
                        fundQuickInfo = fundQuickInfoList.get(selectedIndex);
                        fundID = codeColumn.getCellData(selectedIndex);
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

