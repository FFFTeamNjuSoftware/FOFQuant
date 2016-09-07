package ui.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by OptimusPrime on 2016/9/5.
 */
public class DisplayType {
	private SimpleStringProperty key;
	private SimpleDoubleProperty value;
	private SimpleStringProperty name;

	public DisplayType(String key, double value, String name) {
		this.key = new SimpleStringProperty(key);
		this.value = new SimpleDoubleProperty(value);
		this.name = new SimpleStringProperty(name);
	}

	public String getKey() {
		return key.get();
	}

	public SimpleStringProperty keyProperty() {
		return key;
	}

	public void setKey(String key) {
		this.key.set(key);
	}

	public double getValue() {
		return value.get();
	}

	public SimpleDoubleProperty valueProperty() {
		return value;
	}

	public void setValue(double value) {
		this.value.set(value);
	}

	public String getName() {
		return name.get();
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}
}
