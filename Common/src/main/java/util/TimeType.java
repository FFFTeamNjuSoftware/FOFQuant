package util;


/**
 * Created by Daniel on 2016/8/18.
 */
public enum TimeType {
    ONE_MONTH("一个月"), THREE_MONTH("三个月"), SIS_MONTH(""), ONE_YEAR("一年"), THREE_YEAR("三年"), FIVE_YEAR("五年"), SIN_THIS_YEAR(""),
    SINCE_ESTABLISH("建立至今"), SELECT_PERIOD("选择区间");
    private String value;

    private TimeType(String value) {

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
