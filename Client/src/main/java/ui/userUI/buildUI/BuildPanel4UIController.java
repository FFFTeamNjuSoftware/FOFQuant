package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import beans.FOFHistoryInfo;
import bl.fof.FOFBaseInfoLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.AreaChartGenerator;
import ui.util.InitHelper;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel4UIController implements Initializable {
    private BuildPanel4UIController buildPanel4UIController;
    @FXML
    private ImageView nextBt4;
    @FXML
    private AnchorPane chartPane;
    @FXML
    private TextField combinationName;
    private FOFBaseInfoLogic logic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = BLInterfaces.getFofBaseInfoLogic();
        this.buildPanel4UIController = this;
        initBuildPanel4();
    }

    private void initBuildPanel4() {
        ImageView[] imageViews = {nextBt4};
        InitHelper.beautifyImageViews(imageViews);
        XYChart.Series series = new XYChart.Series<String, Number>();
        List<FOFHistoryInfo> list = null;
        try {
            list = logic.getFOFHistoryInfo();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (FOFHistoryInfo info : list) {
                series.getData().add(new XYChart.Data(info.date, info.totalProfitRate));
            }
        }
        new AreaChartGenerator(chartPane, series, "收益率折线图回测结果");
    }

    @FXML
    public void nextBt4Click() {
        combinationName.getText();
        MainUI.getInstance().changeScene("user_guidePanel", "analyseHomePanel");
    }
}
