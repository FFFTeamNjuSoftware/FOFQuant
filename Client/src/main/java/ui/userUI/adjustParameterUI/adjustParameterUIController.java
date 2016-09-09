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
import ui.util.IOHelper;
import ui.util.LineChartGenerator;
import ui.util.PieChartGenerator;
import util.ChartType;
import util.HoldingType;
import util.TimeType;
import util.UnitType;

import java.lang.reflect.Field;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by tj on 2016/8/18.
 */
public class adjustParameterUIController implements Initializable {

    private adjustParameterUIController instance;
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
		instance=this;
		init();
    }

    public void init() {
       
    }

}