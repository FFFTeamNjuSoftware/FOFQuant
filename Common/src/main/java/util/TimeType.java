package util;


/**
 * Created by Daniel on 2016/8/18.
 */
public enum TimeType {
	ONE_MONTH("一个月"), THREE_MONTH("三个月"), SIX_MONTH("六个月"), ONE_YEAR("一年"), THREE_YEAR("三年"), FIVE_YEAR("五年"), SIN_THIS_YEAR("年初至今"),

	SINCE_ESTABLISH("建立至今");
	private String value;

	private TimeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
