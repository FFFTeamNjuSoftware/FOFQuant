package ui.userUI.buildUI;

import RMIModule.BLInterfaces;
import beans.FOFQuickInfo;
import bl.ProfitFeatureLogic;
import bl.fof.FOFBaseInfoLogic;
import bl.fof.FOFGenerateLogic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starter.MainUI;
import ui.util.DisplayType;
import ui.util.InitHelper;
import ui.util.PieChartGenerator;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class BuildPanel3UIController implements Initializable {
	private BuildPanel3UIController buildPanel3UIController;
	@FXML
	private ImageView nextBt3;
	@FXML
	private ImageView waiting3;
	@FXML
	private Label label1,label2;
	@FXML
	private TableView build3Table1,build3Table2;
	@FXML
	private TableColumn<DisplayType,String> column1,column3;
	@FXML
	private TableColumn<DisplayType,Number> column4,column2;
	private String yellowFill="-fx-text-fill:#FFF850;";
	private String fontStyle="-fx-font-family: Impact; -fx-font-size: 20px;";

	private BLInterfaces blInterfaces = new BLInterfaces();
	private FOFGenerateLogic generateLogic;
	private ProfitFeatureLogic profitFeatureLogic  ;
	private FOFBaseInfoLogic baseInfoLogic;
	private Map<String, Map<String, Double>> map =null;
	private Map<String,Double> mapList1;
	private Map<String,Double> mapList2;
	private Map<String, Double> mapInfo;
	public static String profitKey = "000011";
	public static String solidKey = "000012";
	private List<DisplayType> list1,list2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.buildPanel3UIController=this;
		generateLogic = blInterfaces.getFofGenerateLogic();
		initPanel3();
		//scheduleTask3();
	}
	//	10s后执行
	private void scheduleTask3(){ Runnable runnable = new Runnable() {
		public void run() {
			// task to run goes here
			waiting3.setVisible(false);
		}
	};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
	//	service.scheduleAtFixedRate(runnable, 10, 1, TimeUnit.SECONDS);
		service.schedule(runnable,10, TimeUnit.SECONDS);
	}

	private void initPanel3(){
		ImageView[] imageViews = {nextBt3};
		InitHelper.beautifyImageViews(imageViews);
		try {
			map=generateLogic.getSmallClassConfiguration();
			mapInfo=generateLogic.getLargeClassConfiguration();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if (map != null&&mapInfo!=null) {
			label1.setText(mapInfo.get(profitKey) + "%");
			label2.setText(mapInfo.get(solidKey) + "%");

			mapList1 = map.get(profitKey) ;
			if(mapList1!=null){
				list1=getDisplayTypeList(mapList1);}
			build3Table1.setItems(FXCollections.observableArrayList(list1));
			column1.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			column1.setStyle(yellowFill);
			column2.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().getValue()));
			column2.setStyle(fontStyle);

			mapList2 = map.get(solidKey);
			if(mapList2!=null){
				list2=getDisplayTypeList(mapList2);
			}
			build3Table2.setItems(FXCollections.observableArrayList(list2));
			column3.setCellValueFactory(cellData -> new SimpleStringProperty(
					cellData.getValue().getKey()));
			column3.setStyle(yellowFill);
			column4.setCellValueFactory(cellData -> new SimpleDoubleProperty(
					cellData.getValue().getValue()));
			column4.setStyle(fontStyle);
		}

	}
	@FXML
	public void nextBt3Click() {
		MainUI.getInstance().changeScene("user_guidePanel","buildPanel4");
	}

	public List<DisplayType> getDisplayTypeList(Map<String, Double> map) {
		List<DisplayType> list=new ArrayList<DisplayType>();
		if (map != null) {
			list = new ArrayList<DisplayType>();
			for (Map.Entry<String, Double> entry : map.entrySet()) {
				FOFGenerateLogic info = null;
				DisplayType temp = null;
				temp = new DisplayType(entry.getKey(), entry.getValue(), "");
				if (temp != null) {
					list.add(temp);
				}
//				System.out.println(entry.getKey() + "/" + entry.getValue());
			}
		}
		return list;
	}
}
