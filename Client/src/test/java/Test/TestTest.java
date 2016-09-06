//package Test;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.chart.AreaChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import javafx.util.StringConverter;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.After;
//
//import javax.persistence.Table;
//import javax.print.Doc;
//import javax.swing.text.Document;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Test Tester.
// *
// * @author <Authors name>
// * @version 1.0
// * @since <pre>08/13/2016</pre>
// */
//public class TestTest extends Application {
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) {
//		primaryStage.setTitle("Hello World!");
//
//		StackPane root = new StackPane();
//		TableView<Document> tableView=createTableWithGraphic();
//		root.getChildren().add(tableView);
//		primaryStage.setScene(new Scene(root, 300, 250));
//		primaryStage.show();
//	}
//	public TableView<Document> createTableWithGraphic() {
//		final TableView<Document> tableView = new TableView<Document>();
////		addDocumentColumns(tableView);
//		TableColumn<Document, List<Double>> chartColumn = new TableColumn<>(
//				"图形");
//		chartColumn
//				.setCellValueFactory(new PropertyValueFactory<Document, List<Double>>(
//						"charts"));
//		chartColumn
//				.setCellFactory(new Callback<TableColumn<Document, List<Double>>, TableCell<Document, List<Double>>>() {
//					@Override
//					public TableCell<Document, List<Double>> call(
//							TableColumn<Document, List<Double>> param) {
//						return new ChartTableCell();
//					}
//				});
//		tableView.getColumns().add(chartColumn);
//		chartColumn.setMinWidth(150);
//		chartColumn.setPrefWidth(200);
//		chartColumn.setMaxWidth(300);
////		tableView.setItems(documentData);
//		return tableView;
//	}
//
//	public class ChartTableCell extends TableCell<Document, List<Double>> {
//		NumberAxis xAxis;
//		NumberAxis yAxis;
//		AreaChart<Number, Number> chart;
//		public ChartTableCell() {
//			super();
//			getStyleClass().add("table-chart-cell");
//			xAxis = new NumberAxis();
//			yAxis = new NumberAxis();
//			chart = new AreaChart<Number, Number>(xAxis, yAxis);
//			chart.getStyleClass().add("table-chart");
//			chart.setLegendVisible(false);
//			chart.setTitle(null);
//			chart.setAnimated(false);
//
//			xAxis.setLabel(null);
//			xAxis.setTickMarkVisible(false);
//			xAxis.setMinorTickVisible(false);
//			xAxis.setTickLabelFormatter(new StringConverter<Number>() {
//				@Override
//				public String toString(Number t) {
//					return "";
//				}
//
//				@Override
//				public Number fromString(String string) {
//					return 0;
//				}
//			});
//
//			yAxis.setLabel(null);
//			yAxis.setTickMarkVisible(false);
//			yAxis.setMinorTickVisible(false);
//			yAxis.setTickLabelFormatter(new StringConverter<Number>() {
//				@Override
//				public String toString(Number t) {
//					return "";
//				}
//
//				@Override
//				public Number fromString(String string) {
//					return 0;
//				}
//			});
//			chart.setMinSize(20, 35);
//			chart.setPrefSize(150, 35);
//			chart.setMaxSize(Double.MAX_VALUE, 35);
//			setGraphic(chart);
//			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//		}
//
//		@Override
//		public void updateItem(List<Double> item, boolean empty) {
//			super.updateItem(item, empty);
//			if (empty) {
//				setGraphic(null);
//			} else {
//				XYChart.Series<Number, Number> salesSeries = new XYChart.Series<Number, Number>();
//				for (int i = 0; i < item.size(); i++) {
//					salesSeries.getData().add(
//							new XYChart.Data<Number, Number>(i, item.get(i)));
//				}
//				chart.getData().clear();
//				chart.getData().add(salesSeries);
//				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
//			}
//		}
//	}
//
//
//}
