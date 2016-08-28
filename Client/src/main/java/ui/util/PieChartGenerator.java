package ui.util;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class PieChartGenerator {
    private AnchorPane pane;
    private PieChart piechart;
    private ObservableList<PieChart.Data> pieChartData;

    public PieChartGenerator(AnchorPane pane, ObservableList<PieChart.Data> pieChartData) {
        this.pane = pane;
        this.piechart = new PieChart();
        this.pieChartData = pieChartData;
        setData();
        setupHover();
    }

    public void setData() {
        if (pane == null) {
            piechart.autosize();
            piechart.setLabelsVisible(false);
        }
        piechart.setData(pieChartData);
        pane.getChildren().add(piechart);
        piechart.setPrefSize(pane.getPrefWidth(), pane.getPrefHeight());
    }

    private void setupHover() {
        Label caption = new Label();
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 15 arial;");
        for (final PieChart.Data data : piechart.getData()) {
            Node n = data.getNode();
            n.setOnMouseEntered((e) -> {
                n.setCursor(Cursor.HAND);
                caption.setTranslateX(n.getLayoutX());
                caption.setTranslateY(n.getLayoutY());
                caption.setText(String.valueOf(data.getPieValue())+"%");
            });
        }
        pane.getChildren().add(caption);
    }
}
