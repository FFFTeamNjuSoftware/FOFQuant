package ui.util;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import starter.MainUI;

import static util.FOFUtilInfo.performanceBaseInfo;

/**
 * Created by OptimusPrime on 2016/8/27.
 */
public class InitHelper {

	public static final String[] referType = (String[]) performanceBaseInfo.keySet().toArray(new String[performanceBaseInfo.keySet().size()]);
	public static void beautifyImageViews(ImageView[] imageViews) {
		for (int i = 0; i < imageViews.length; i++) {
			ImageView tempImage = imageViews[i];
			tempImage.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
			});

			tempImage.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
			});

			tempImage.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				tempImage.setStyle("-fx-background-color: transparent;");
			});
		}
	}
	public static void beautifyImageViews2(ImageView[] imageViews) {
		Boolean[] isPressed=new Boolean[imageViews.length];
		 int[] s = new int[imageViews.length];
		for(int r=0;r<imageViews.length;r++){
			s[r]=0;
		}
		for (final int[] i = {0}; i[0] < imageViews.length; i[0]++) {
			int j = i[0];
			imageViews[i[0]].addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
				imageViews[j].setStyle("-fx-background-color: #AFE1FE; -fx-opacity:0.3");
			});

			imageViews[i[0]].addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
				imageViews[j].setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.3");
				for(int r=0;r<imageViews.length;r++){
					s[r]=0;
				}
				s[j] =1;
				for(int k = 0; k< imageViews.length; k++){
					if(s[k]!=1){
						imageViews[k].setStyle("-fx-background-color: transparent;");
					}
				}
			});
			imageViews[i[0]].addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
				for(int k = 0; k< imageViews.length; k++){
					if(s[k]!=1){
						imageViews[k].setStyle("-fx-background-color: transparent;");
					}
				}
			});

		}
	}
	public static void beautifyImageView(ImageView image){
		image.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			image.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
		});

//		image.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
//			image.setStyle("-fx-background-color: #1F77B9;  -fx-opacity:0.5");
//		});

		image.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			image.setStyle("-fx-background-color: transparent;");
		});
	}
	public static void beautifyButton(Button btn){
		btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			btn.setStyle("-fx-background-color: #23a3f3; -fx-opacity:0.5");
		});

		btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			btn.setStyle("-fx-background-color: transparent;");
		});
	}
}
