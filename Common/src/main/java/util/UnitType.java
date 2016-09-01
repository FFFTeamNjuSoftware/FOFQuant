package util;

/**
 * Created by Daniel on 2016/8/15.
 */

/**
 * 周期类型
 */
public enum UnitType {

	DAY("天"), WEEK("周"), MONTH("月"), QUARTER("季度"), YEAR("年");
	private String value;

	private UnitType(String value) {
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
