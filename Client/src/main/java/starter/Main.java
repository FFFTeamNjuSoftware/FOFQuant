package starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by QiHan on 2016/8/14.
 */
public class Main  extends Application {

    private static Main instance;
    private static Stage primaryStage;
    private static Scene primaryScene;
    private static AnchorPane loginPanel;


    public static Main getInstance() {
        return instance;
    }

    public static Scene getFactoryScene(Parent parent) {
        /**
         * change cursor
         */
        Scene ans=new Scene(parent);
        return ans;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        this.primaryStage = primaryStage;
        loginPanel= FXMLLoader.load(getClass().getClassLoader().getResource("loginPanel.fxml"));

        primaryStage.setHeight(618);
        primaryStage.setWidth(1000);
        primaryStage.setTitle("FoFQuant");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.isResizable();
        primaryStage.setScene(getFactoryScene(loginPanel));
        enableDragAndResize(primaryStage.getScene());
        primaryStage.show();
    }


    /**
     * =========================================================================
     * =========================================================================
     */
    /**
     * drag
     */
    private static double calX;
    private static double calY;
    private static double oldPosX;
    private static double oldPosY;
    private static double oldWidth;
    private static double oldHeight;
    private static boolean dragging = false;
    private static boolean resizing = false;
    private static CURSOR_AREA pressedArea = CURSOR_AREA.CENTER;
    public static final double ResizePadding = 5;
    private static final double padding = ResizePadding;
    public static final double titleHeight = 625;//70
    public static final double LeftTabsWidth = 80;//179


    private static enum CURSOR_AREA{
        NORTH_WEST,
        NORTH_EAST,
        SOUTH_WEST,
        SOUTH_EAST,
        NORTH,
        SOUTH,
        WEST,
        EAST,
        TITLE,
        CENTER
    }

    private static CURSOR_AREA getCursorArea(double x, double y, double width, double height){
        if(y < padding && x < padding){
            return CURSOR_AREA.NORTH_WEST;
        }else if(y < padding && x > width - padding){
            return CURSOR_AREA.NORTH_EAST;
        }else if(y > height - padding && x < padding){
            return CURSOR_AREA.SOUTH_WEST;
        }else if(y > height - padding && x > width - padding){
            return CURSOR_AREA.SOUTH_EAST;
        }else if(y> height - padding){
            return CURSOR_AREA.SOUTH;
        }else if(x> width - padding){
            return CURSOR_AREA.EAST;
        }else if(y < padding){
            return CURSOR_AREA.NORTH;
        }else if(x < padding){
            return CURSOR_AREA.WEST;
        }else if(y < titleHeight){
            return CURSOR_AREA.TITLE;
        }else{
            return CURSOR_AREA.CENTER;
        }
    }

    private static final double minWidth = 1000;
    private static final double minHeight = 618;
    private static void setBounds(Stage stage, double x, double y, double w, double h){
        if(w > minWidth){
            stage.setWidth(w);
            stage.setX(x);
            stage.setY(y);
        }else{
            stage.setWidth(minWidth);
        }
        if(h > minHeight){
            stage.setHeight(h);
            stage.setX(x);
            stage.setY(y);
        }else{
            stage.setHeight(minHeight);
        }
    }

    static double ix;
    static double iy;

    private static void enableDragAndResize(Scene scene){
        scene.setOnMousePressed(
                me -> {
                    pressedArea = getCursorArea(me.getX(), me.getY(), primaryStage.getWidth(), primaryStage.getHeight());
                    if (pressedArea == CURSOR_AREA.TITLE) {
                        resizing = false;
                        dragging = true;
                        calX = me.getScreenX() - primaryStage.getX();
                        calY = me.getScreenY() - primaryStage.getY();
                    } else if (pressedArea == CURSOR_AREA.CENTER) {
                        resizing = false;
                        dragging = false;
                    } else {
                        resizing = true;
                        dragging = false;
                        oldPosX = primaryStage.getX();
                        oldPosY = primaryStage.getY();
                        oldWidth = primaryStage.getWidth();
                        oldHeight = primaryStage.getHeight();
                        calX = me.getScreenX();
                        calY = me.getScreenY();
                    }
                }
        );
        scene.setOnMouseDragged(
                me -> {
                    if (dragging) {
                        primaryStage.setX(me.getScreenX() - calX);
                        primaryStage.setY(me.getScreenY() - calY);
                    } else if (resizing) {
                        double dx = me.getScreenX() - calX;
                        double dy = me.getScreenY() - calY;
                        switch (pressedArea) {
                            case NORTH_WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY + dy, oldWidth - dx, oldHeight - dy);
                                break;
                            case NORTH_EAST:
                                setBounds(primaryStage, oldPosX, oldPosY + dy, oldWidth + dx, oldHeight - dy);
                                break;
                            case SOUTH_WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY, oldWidth - dx, oldHeight + dy);
                                break;
                            case SOUTH_EAST:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth + dx, oldHeight + dy);
                                break;
                            case NORTH:
                                setBounds(primaryStage, oldPosX, oldPosY + dy, oldWidth, oldHeight - dy);
                                break;
                            case SOUTH:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth, oldHeight + dy);
                                break;
                            case WEST:
                                setBounds(primaryStage, oldPosX + dx, oldPosY, oldWidth - dx, oldHeight);
                                break;
                            case EAST:
                                setBounds(primaryStage, oldPosX, oldPosY, oldWidth + dx, oldHeight);
                                break;
                            case TITLE:
                                break;
                            case CENTER:
                                break;
                        }
                    }
                }
        );
        // change the look of the mouse when it is moved to the sides
        scene.setOnMouseMoved(
                me -> {
                    CURSOR_AREA area = getCursorArea(me.getX(), me.getY(), primaryStage.getWidth(), primaryStage.getHeight());
                    Cursor cursor = Cursor.DEFAULT;
                    switch (area) {
                        case NORTH_WEST:
                            cursor = Cursor.NW_RESIZE;
                            break;
                        case NORTH_EAST:
                            cursor = Cursor.NE_RESIZE;
                            break;
                        case SOUTH_WEST:
                            cursor = Cursor.SW_RESIZE;
                            break;
                        case SOUTH_EAST:
                            cursor = Cursor.SE_RESIZE;
                            break;
                        case NORTH:
                        case SOUTH:
                            cursor = Cursor.V_RESIZE;
                            break;
                        case WEST:
                        case EAST:
                            cursor = Cursor.H_RESIZE;
                            break;
                        case TITLE:
                        case CENTER:
                            cursor = Cursor.DEFAULT;
                            break;
                    }
                    primaryStage.getScene().setCursor(cursor);
                }
        );
    }


    /**
     * =========================================================================
     * =========================================================================
     */

    public static void main(String[] args)
    {
        launch(args);
    }
}
