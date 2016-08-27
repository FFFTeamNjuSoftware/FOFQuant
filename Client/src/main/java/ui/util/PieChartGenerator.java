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
	}

	public void setData() {
		if(pane == null) {
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
		caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : piechart.getData()) {
			Node n = data.getNode();
			n.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					n.setCursor(Cursor.HAND);
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					caption.setText(String.valueOf(data.getPieValue()));
				}
			});
		}
		if(pane != null)
			pane.getChildren().add(caption);
	}
}
