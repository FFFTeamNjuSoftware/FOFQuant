package ui.util;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

import java.util.Map;

/**
 * Created by tjDu on 2016/8/27.
 */
public class BarChartGenerator {
    private AnchorPane pane;
    private BarChart<String, Number> barChart;
    private XYChart.Series<String, Number> dataSeries;

    public BarChartGenerator(AnchorPane pane, String xName, String yName, XYChart.Series<String, Number> dataSeries) {
        this.pane = pane;
        this.dataSeries = dataSeries;
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel(xName);
        xAxis.setMaxHeight(12);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(yName);
        barChart = new BarChart<String, Number>(xAxis, yAxis);
        barChart.getData().add(dataSeries);

        barChart.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
        pane.getChildren().add(barChart);
    }
}
