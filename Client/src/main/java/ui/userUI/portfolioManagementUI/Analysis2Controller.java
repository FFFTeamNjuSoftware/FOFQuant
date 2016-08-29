package ui.userUI.portfolioManagementUI;

import RMIModule.BLInterfaces;
import beans.FOFProfitAnalyse;
import bl.fof.FOFProfitAnalyseLogic;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by OptimusPrime on 2016/8/28.
 */
public class Analysis2Controller implements Initializable {
	@FXML
	private ComboBox<Date> startCb,endCb;
	@FXML
	private Label startDateLb,endDateLb,combinationLb1,combinationLb2;
	@FXML
	private ImageView combinationImage1,combinationImage2;
	@FXML
	private TableView<FOFProfitAnalyse> table;
	@FXML
	private TableColumn<FOFProfitAnalyse,String> project1Cn,project2Cn;
	@FXML
	private TableColumn<FOFProfitAnalyse,Number> month3_1Cn,month3_2Cn,month6_1Cn,month6_2Cn,choose1Cn,choose2Cn;
	private Analysis2Controller analysis2Controller;
	private FOFProfitAnalyseLogic fofProfitAnalyseLogic;
	private FOFProfitAnalyse fofProfitAnalyse;
	private static int pressedLabel=-1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.analysis2Controller=this;
		this.fofProfitAnalyseLogic= BLInterfaces.getFofProfitAnalyseLogic();
		initComboboxes();
		initLabels();
		initTable();
	}
	public void initComboboxes(){

	}
	public void initLabels(){
		Label[] labels={combinationLb1,combinationLb2};
		ImageView[] imageViews={combinationImage1,combinationImage2};
		beautifyLabels(labels,imageViews);

	}
	public void initTable(){}
	@FXML
	public void combinationLb1Click(){

	}
	@FXML
	public void combinationLb2Click(){

	}


	public static void  beautifyLabels(Label[] labels, ImageView[] imageViews){
		assert(labels!=null&&labels!=null):("Null pointer!");
		assert(labels.length==labels.length):("The labels and imageViews not one-to-one");
		for (final int[] i = {0}; i[0] < imageViews.length; i[0]++) {
			int j = i[0];
			labels[i[0]].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				imageViews[j].setVisible(true);
			});
			labels[i[0]].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
				pressedLabel=j;
				for(int k = 0; k< labels.length; k++){
					if(k!=pressedLabel){
						imageViews[k].setVisible(false);
					}else{
						imageViews[k].setVisible(true);
					}
				}
			});
			labels[i[0]].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				for(int k = 0; k< labels.length; k++){
					if(k!=pressedLabel){
						imageViews[k].setVisible(false);
					}else{
						imageViews[k].setVisible(true);
					}
				}
			});
		}
	}



}
