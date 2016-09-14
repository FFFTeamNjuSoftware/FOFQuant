package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import beans.ProfitChartInfo;
import bl.fof.FOFGenerateLogic;
import com.mathworks.toolbox.javabuilder.MWException;
import exception.NotInitialedException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import starter.MainUI;
import ui.util.AreaChartGenerator;
import ui.util.InitHelper;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
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

    private FOFGenerateLogic logic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = BLInterfaces.getFofGenerateLogic();
        this.buildPanel4UIController = this;
        initBuildPanel4();
    }

    private void initBuildPanel4() {
        ImageView[] imageViews = {nextBt4};
        InitHelper.beautifyImageViews(imageViews);
        XYChart.Series series = new XYChart.Series<String, Number>();
        List<ProfitChartInfo> list = null;
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, -1);
        Date lastYear = calendar.getTime();
        try {
            list = logic.getTestValues(lastYear.toString(),now.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        } catch (NotInitialedException e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (ProfitChartInfo info : list) {
                //基金收益率
                series.getData().add(new XYChart.Data(info.date, info.values[0]));
            }
        }
        new AreaChartGenerator(chartPane, series, "收益率折线图回测结果");
    }

    @FXML
    public void nextBt4Click() {
        try {
            logic.setFOFName(combinationName.getText());
            logic.saveResult();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MWException e) {
            e.printStackTrace();
        } catch (NotInitialedException e) {
            e.printStackTrace();
        }
        MainUI.getInstance().displaySuccessPane();
        MainUI.getInstance().changeScene("user_guidePanel", "analyseHomePanel");
    }
}
