package ui.util;

/**
 * Created by OptimusPrime on 2016/9/5.
 */
public class DisplayType {
	private String key;
	private Double value;

	public DisplayType(String key, Double value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
