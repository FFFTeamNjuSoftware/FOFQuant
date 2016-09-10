package ui.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class LineChartGenerator {

    private LineChart<String, Number> lineChart;
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private List<XYChart.Series<String, Number>> serieses;

    private AnchorPane pane;

    public LineChartGenerator() {
    }

    public LineChartGenerator(AnchorPane pane, String[] names) {
        serieses = new ArrayList<>();
        this.pane = pane;
        lineChart = new LineChart<String, Number>(xAxis, yAxis);
        yAxis.setForceZeroInRange(false);
        for (String name : names) {
            XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
            series.setName(name);
            serieses.add(series);
        }
        lineChart.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
    }
    public void setData(String[] x, double[][] y) {
        for (int j = 0; j < serieses.size(); j++) {
            XYChart.Series<String, Number> series = serieses.get(j);
            for (int i = 0; i < x.length; i++) {
                series.getData().add(new Data<String, Number>(x[i], y[j][i]));
            }
            lineChart.getData().add(series);
        }
        pane.getChildren().add(lineChart);
        for (XYChart.Series<String, Number> s : lineChart.getData()) {
            s.getNode().setStyle("   -fx-stroke-width: 1; ");
        }
        lineChart.setCreateSymbols(true);
        lineChart.setStyle("-fx-legend-visible: false");
        setupHover();
    }

    private void setupHover() {
        Label label = new Label();
        label.setTextFill(Color.DARKCYAN);
        label.setStyle("-fx-font: 15 arial;");
        for (int j = 0; j < serieses.size(); j++) {
            XYChart.Series<String, Number> series = serieses.get(j);
            for (final Data<String, Number> dt : series.getData()) {
                final Node n = dt.getNode();
                n.setEffect(null);
                n.setOnMouseEntered((e) -> {
                    n.setCursor(Cursor.HAND);
                    label.setLayoutX(n.getLayoutX() + 40);
                    label.setTranslateY(n.getLayoutY() + 10);
                    label.setText(String.valueOf(dt.getYValue()));
                });
            }
        }
        pane.getChildren().add(label);
    }

    public void simpleSetupHover(LineChart<String, Number> chart,AnchorPane pane) {
        for (XYChart.Series<String, Number> s : chart.getData()) {
            s.getNode().setStyle("   -fx-stroke-width: 1; ");
        }
        XYChart.Series<String, Number> series = chart.getData().get(0);
        Label label = new Label();
        label.setTextFill(Color.DARKCYAN);
        label.setStyle("-fx-font: 15 arial;");
        for (final Data<String, Number> dt : series.getData()) {
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
        chart.setCreateSymbols(true);
    }
}
