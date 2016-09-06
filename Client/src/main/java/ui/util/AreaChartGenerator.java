package ui.util;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Created by tjDu on 2016/9/5.
 */
public class AreaChartGenerator {
    private AreaChart areaChart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private AnchorPane pane;
    private XYChart.Series<String, Number> series;

    public AreaChartGenerator(AnchorPane pane, XYChart.Series series, String title) {
        this.series = series;
        this.pane = pane;
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        areaChart = new AreaChart(xAxis, yAxis);
        yAxis.setForceZeroInRange(false);
        areaChart.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
        areaChart.getData().add(series);
        pane.getChildren().add(areaChart);
        areaChart.setTitle(title);
        setupHover();
    }

    private void setupHover() {
        Label label = new Label();
        label.setTextFill(Color.DARKCYAN);
        label.setStyle("-fx-font: 15 arial;");

        for (final XYChart.Data<String, Number> dt : series.getData()) {
            final Node n = dt.getNode();
            n.setEffect(null);
            n.setOnMouseEntered((e) -> {
                n.setCursor(Cursor.HAND);
                label.setLayoutX(n.getLayoutX() + 40);
                label.setTranslateY(n.getLayoutY() + 10);
                label.setText(String.valueOf(dt.getYValue()));
            });
        }

        pane.getChildren().add(label);
    }
}
