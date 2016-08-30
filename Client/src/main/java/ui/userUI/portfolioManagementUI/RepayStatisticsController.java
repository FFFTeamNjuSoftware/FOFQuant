package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.ProfitStatisticsInfo;
import beans.ProfitStatisticsInfoOne;
import beans.ProfitStatisticsInfoTwo;
import bl.fof.FOFProfitStatisticsLogic;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

import java.lang.reflect.Field;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

/**
 * Created by tjDu on 2016/8/29.
 */
public class RepayStatisticsController implements Initializable {
    @FXML
    Group infoOneGroup;
    @FXML
    Group infoTwoGroup;

    private FOFProfitStatisticsLogic logic;

    private ProfitStatisticsInfo profitInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = BLInterfaces.getFofProfitStatisticsLogic();
        try {
            profitInfo = logic.getProfitStatisticsInfo();
            System.out.println(profitInfo.average.relatedRise);
            System.out.println(profitInfo.aveSequence.relatedRise);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        initBasicInfo();
    }

    public void initBasicInfo() {
        if (profitInfo != null) {
            ObservableList<Node> groups = infoOneGroup.getChildren();
            for (Node group : groups) {
                String groupId = group.getId();
                ObservableList<Node> labels = ((Group) group).getChildren();
                try {
                    Field field = profitInfo.getClass().getDeclaredField(groupId);
                    field.setAccessible(true);
                    Object object = field.get(profitInfo);
                    ProfitStatisticsInfoOne infoOne = (ProfitStatisticsInfoOne) object;
                    for (Node label : labels) {
                        String labelId = label.getId();
                        Label lab = (Label) label;
                        Field field1 = infoOne.getClass().getDeclaredField(labelId);
                        field1.setAccessible(true);
                        Double num = field1.getDouble(infoOne);
                        lab.setText(num+"");
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
