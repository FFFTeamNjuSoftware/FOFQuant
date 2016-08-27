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


    public LineChartGenerator(AnchorPane pane, String[] names) {
        serieses = new ArrayList<>();
        this.pane = pane;
        lineChart = new LineChart<String, Number>(xAxis, yAxis);
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
            lineChart.setCreateSymbols(true);
        }
        pane.getChildren().add(lineChart);
    }

    private void setupHover() {
        for (int j = 0; j < serieses.size(); j++) {
            XYChart.Series<String, Number> series = serieses.get(j);
            Label label = new Label();
            label.setTextFill(Color.DARKCYAN);
            label.setStyle("-fx-font: 18 arial;" + "-fx-opacity:0.5");
            for (final Data<String, Number> dt : series.getData()) {
                final Node n = dt.getNode();
                n.setEffect(null);
                n.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        n.setCursor(Cursor.HAND);
                        label.setLayoutX(n.getLayoutX() + 40);
                        label.setTranslateY(n.getLayoutY() + 10);
                        label.setText(String.valueOf(dt.getYValue()));
                    }
                });
            }
            pane.getChildren().add(label);
        }
    }
}
