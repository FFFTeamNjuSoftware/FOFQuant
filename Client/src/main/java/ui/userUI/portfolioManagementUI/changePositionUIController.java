package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import util.TimeType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by QiHan on 2016/8/29.
 */
public class changePositionUIController implements Initializable {
    private changePositionUIController instance;
    private BLInterfaces blInterfaces = new BLInterfaces();
    private FOFProfitAnalyseLogic profitAnalyseLogic;
    private FOFProfitAnalyse profitAnalyse_three, profitAnalyse_half, profitAnalyse_year,profitAnalyse_establish;
    @FXML
    private TableView table1,table2;
    @FXML
    private TableColumn table1column1,table1column2,table1column3,table1column4
            ,table2column1,table2column2,table2column3,table2column4;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instance = this;
        initTable();
    }

    public void initTable() {
        profitAnalyseLogic = blInterfaces.getFofProfitAnalyseLogic();
        try {
            profitAnalyse_three = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.THREE_MONTH);
            profitAnalyse_half = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIS_MONTH);
            profitAnalyse_year = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SIN_THIS_YEAR);
            profitAnalyse_establish = profitAnalyseLogic.getFOFProfitAnalyse(TimeType.SINCE_ESTABLISH);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        table1.setItems(FXCollections.observableArrayList(new TableData(
                profitAnalyse_three.totalProfit,profitAnalyse_half.totalProfit,profitAnalyse_year.totalProfit,profitAnalyse_establish.totalProfit),new TableData(
                profitAnalyse_three.relatedTotalProfit,profitAnalyse_half.relatedTotalProfit,profitAnalyse_year.relatedTotalProfit,profitAnalyse_establish.relatedTotalProfit),new TableData(
                profitAnalyse_three.maxRise,profitAnalyse_half.maxRise,profitAnalyse_year.maxRise,profitAnalyse_establish.maxRise ),new TableData(
                profitAnalyse_three.maxRiseDays+0.0,profitAnalyse_half.maxRiseDays+0.0,profitAnalyse_year.maxRiseDays+0.0,profitAnalyse_establish.maxRiseDays+0.0),new TableData(
                profitAnalyse_three.maxRiseRecoverDays+0.0,profitAnalyse_half.maxRiseRecoverDays+0.0,profitAnalyse_year.maxRiseRecoverDays+0.0,profitAnalyse_establish.maxRiseRecoverDays+0.0),new TableData(
                profitAnalyse_three.minRise,profitAnalyse_half.minRise,profitAnalyse_year.minRise,profitAnalyse_establish.minRise ),new TableData(
                profitAnalyse_three.minRiseDays+0.0,profitAnalyse_half.minRiseDays+0.0,profitAnalyse_year.minRiseDays+0.0,profitAnalyse_establish.minRiseDays+0.0),new TableData(
                profitAnalyse_three.minRiseRecoverDays+0.0,profitAnalyse_half.minRiseRecoverDays+0.0,profitAnalyse_year.minRiseRecoverDays+0.0,profitAnalyse_establish.minRiseRecoverDays+0.0),new TableData(
                profitAnalyse_three.yearProfitRate,profitAnalyse_half.yearProfitRate,profitAnalyse_year.yearProfitRate ,profitAnalyse_establish.yearProfitRate),new TableData(
                profitAnalyse_three.yearRelatedProfitRate,profitAnalyse_half.yearRelatedProfitRate,profitAnalyse_year.yearRelatedProfitRate,profitAnalyse_establish.yearRelatedProfitRate),new TableData(
                profitAnalyse_three.downsideRisk,profitAnalyse_half.downsideRisk,profitAnalyse_year.downsideRisk ,profitAnalyse_establish.downsideRisk)));

        table1column1.setCellValueFactory(new PropertyValueFactory<TableData,Double>("one"));
        table1column2.setCellValueFactory(new PropertyValueFactory<TableData,Double>("two"));
        table1column3.setCellValueFactory(new PropertyValueFactory<TableData,Double>("three"));
        table1column4.setCellValueFactory(new PropertyValueFactory<TableData,Double>("four"));

        table2.setItems(FXCollections.observableArrayList(new TableData(
                profitAnalyse_three.yearWaveRate,profitAnalyse_half.yearWaveRate,profitAnalyse_year.yearWaveRate,profitAnalyse_establish.yearWaveRate),new TableData(
                profitAnalyse_three.trackingError,profitAnalyse_half.trackingError,profitAnalyse_year.trackingError,profitAnalyse_establish.trackingError),new TableData(
                profitAnalyse_three.correlationCoefficent,profitAnalyse_half.correlationCoefficent,profitAnalyse_year.correlationCoefficent,profitAnalyse_establish.correlationCoefficent ),new TableData(
                profitAnalyse_three.alpha,profitAnalyse_half.alpha,profitAnalyse_year.alpha,profitAnalyse_establish.alpha),new TableData(
                profitAnalyse_three.beta,profitAnalyse_half.beta,profitAnalyse_year.beta,profitAnalyse_establish.beta),new TableData(
                profitAnalyse_three.sharpe,profitAnalyse_half.sharpe,profitAnalyse_year.sharpe,profitAnalyse_establish.sharpe ),new TableData(
                profitAnalyse_three.treynor,profitAnalyse_half.treynor,profitAnalyse_year.treynor,profitAnalyse_establish.treynor),new TableData(
                profitAnalyse_three.Jensen,profitAnalyse_half.Jensen,profitAnalyse_year.Jensen,profitAnalyse_establish.Jensen ),new TableData(
                profitAnalyse_three.R2,profitAnalyse_half.R2,profitAnalyse_year.R2,profitAnalyse_establish.R2),new TableData(
                profitAnalyse_three.semiVariance,profitAnalyse_half.semiVariance,profitAnalyse_year.semiVariance,profitAnalyse_establish.semiVariance ),new TableData(
                profitAnalyse_three.sortino,profitAnalyse_half.sortino,profitAnalyse_year.sortino,profitAnalyse_establish.sortino)));

        table2column1.setCellValueFactory(new PropertyValueFactory<TableData,Double>("one"));
        table2column2.setCellValueFactory(new PropertyValueFactory<TableData,Double>("two"));
        table2column3.setCellValueFactory(new PropertyValueFactory<TableData,Double>("three"));
        table2column4.setCellValueFactory(new PropertyValueFactory<TableData,Double>("four"));


    }

    public static class TableData {
        SimpleDoubleProperty one, two, three,four;

        public TableData(Double one, Double two, Double three,Double four) {
            this.one = new SimpleDoubleProperty(one);
            this.two = new SimpleDoubleProperty(two);
            this.three = new SimpleDoubleProperty(three);
            this.four = new SimpleDoubleProperty(four);
        }

        public Double getOne() {
            return one.get();
        }

        public void setOne(Double one) {
            this.one.set(one);
        }

        public Double getThree() {
            return three.get();
        }

        public void setThree(Double three) {
            this.three.set(three);
        }

        public Double getTwo() {
            return two.get();
        }

        public void setTwo(Double two) {
            this.two.set(two);
        }

        public Double getFour() {
            return two.get();
        }

        public void setFour(Double four) {
            this.two.set(four);
        }

    }

}
