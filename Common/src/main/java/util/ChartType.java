package util;

/**
 * Created by Daniel on 2016/8/18.
 */
public enum ChartType {
	MILLION_WAVE_CHART("万元"), RATE_CHART("比率"), NET_WORTH_PERFORMANCE_FQ("复权"), NET_WORTH_PERFORMANCE_UNIT("单位"),
	NET_WORTH_PERFORMANCE_TOTAL("累计");
	private String value;

	private ChartType(String value) {
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
