package ui.loginUI;

import RMIModule.BLInterfaces;
import beans.FundInfo;
import beans.FundQuickInfo;
import beans.UserInfo;
import bl.BaseInfoLogic;
import bl.UserLogic;
import exception.AuthorityException;
import exception.ObjectNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.dom4j.DocumentException;
import starter.Main;
import starter.MainUI;
import ui.util.IOHelper;
import util.UserType;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by QiHan on 2016/8/14.
 */
public class loginUIController implements Initializable {

	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button minBtn, exitBtn, loginBtn;
	@FXML
	private Label tipLabel;

	private MainUI mainUI;

	private loginUIController instance;

	private BLInterfaces blInterfaces = new BLInterfaces();
	;
	private UserLogic userLogic;
	private UserInfo userInfo = new UserInfo();
	;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		instance = this;
		buttonInit();
		init();
	}


	public void init() {

		userNameField.setPromptText("用户名");
		passwordField.setPromptText("密码");
		userNameField.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			tipLabel.setVisible(false);
		});
		passwordField.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			tipLabel.setVisible(false);
		});
	}

	@FXML
	public void toLogin() {

		String userName = userNameField.getText();
		String password = passwordField.getText();
		System.out.println(userName + "..." + password);

		try {
			userLogic = blInterfaces.getUserLogic();
			userInfo = userLogic.loginIn(userName, password);
			System.out.println(userInfo);
			mainUI = MainUI.getInstance();
			if (userInfo.userType == UserType.MANAGER) {
				//进入管理员界面
				System.out.println("......manager......");
				IOHelper.writeName(userName);
				mainUI.changeScene("manager_guidePanel", "userManagerPanel");
			} else if (userInfo.userType == UserType.NORMAL) {
				//进入普通用户界面
				IOHelper.writeName(userName);
				System.out.println("......normal......");
				mainUI.changeScene("user_guidePanel", "buildHomePanel");
//				getFundDataThread();
			} else {
				System.out.println("......login fail......");
			}
			///////////////////////////////////////////////
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("......RemoteException......");
			tipLabel.setVisible(true);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
			System.out.println("......账号不存在......");
			tipLabel.setVisible(true);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("......NullPointerException......");
			tipLabel.setVisible(true);
		} catch (AuthorityException e) {
			e.printStackTrace();
			System.out.println("......密码错误......");
			tipLabel.setVisible(true);
		}
	}


	public void buttonInit() {
		minBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			minBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
		});

		minBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			minBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
		});

//Removing the shadow when the mouse cursor is off
		minBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			minBtn.setStyle("-fx-background-color: transparent;");
		});

		exitBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			exitBtn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
		});

		exitBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			exitBtn.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
		});

		exitBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			exitBtn.setStyle("-fx-background-color: transparent;");
		});

		loginBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			loginBtn.setStyle("-fx-background-color: #87CCF3;");
		});

		loginBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			loginBtn.setStyle("-fx-background-color: #1F77B9;");
		});

		loginBtn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			loginBtn.setStyle("-fx-background-color: #23a3f3;");
		});
	}

	@FXML
	public void toMinScreen() {
		MainUI.getPrimaryStage().setIconified(true);
	}

	@FXML
	public void toExitScreen() {
		MainUI.getPrimaryStage().close();
	}

}
