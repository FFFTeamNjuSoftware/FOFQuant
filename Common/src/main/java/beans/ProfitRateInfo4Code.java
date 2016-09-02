package beans;

/**
 * Created by tjDu on 2016/9/2.
 */

import javafx.beans.property.SimpleStringProperty;

/**
 * 为了画图方便
 */
public class ProfitRateInfo4Code {
    /**
     * 基金代码
     */
    private final SimpleStringProperty code;
    /**
     * 基金名称
     */
    private final SimpleStringProperty name;

    /**
     * 收益率指标
     * 以下参数都是%，如返回0.5代表0.5%
     */
    /**
     * 近一月
     */
    private final SimpleStringProperty nearOneMonth;
    /**
     * 近三月
     */
    private final SimpleStringProperty nearThreeMonth;
    /**
     * 近六月
     */
    private final SimpleStringProperty nearSixMonth;
    /**
     * 近一年
     */
    private final SimpleStringProperty nearOneYear;
    /**
     * 近两年
     */
    private final SimpleStringProperty nearTwoYear;
    /**
     * 近三年
     */
    private final SimpleStringProperty nearThreeYear;
    /**
     * 近五年
     */
    private final SimpleStringProperty nearFiveYear;
    /**
     * 年初至今
     */
    private final SimpleStringProperty sinceThisYear;
    /**
     * 自建立
     */
    private final SimpleStringProperty sinceEstablish;
    /**
     * 年化收益
     */
    private final SimpleStringProperty yearRate;

    public ProfitRateInfo4Code(ProfitRateInfo info, String code, String name) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.nearOneMonth = new SimpleStringProperty(info.nearOneMonth + "%");
        this.nearThreeMonth = new SimpleStringProperty(info.nearThreeMonth + "%");
        this.nearSixMonth = new SimpleStringProperty(info.nearSixMonth + "%");
        this.nearOneYear = new SimpleStringProperty(info.nearOneYear + "%");
        this.nearTwoYear = new SimpleStringProperty(info.nearTwoYear + "%");
        this.nearThreeYear = new SimpleStringProperty(info.nearThreeYear + "%");
        this.nearFiveYear = new SimpleStringProperty(info.nearFiveYear + "%");
        this.sinceThisYear = new SimpleStringProperty(info.sinceThisYear + "%");
        this.sinceEstablish = new SimpleStringProperty(info.sinceEstablish + "%");
        this.yearRate = new SimpleStringProperty(info.yearRate + "%");
    }


    public String getCode() {
        return code.get();
    }

    public SimpleStringProperty codeProperty() {
        return code;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getNearOneMonth() {
        return nearOneMonth.get();
    }

    public SimpleStringProperty nearOneMonthProperty() {
        return nearOneMonth;
    }

    public String getNearThreeMonth() {
        return nearThreeMonth.get();
    }

    public SimpleStringProperty nearThreeMonthProperty() {
        return nearThreeMonth;
    }

    public String getNearSixMonth() {
        return nearSixMonth.get();
    }

    public SimpleStringProperty nearSixMonthProperty() {
        return nearSixMonth;
    }

    public String getNearOneYear() {
        return nearOneYear.get();
    }

    public SimpleStringProperty nearOneYearProperty() {
        return nearOneYear;
    }

    public String getNearTwoYear() {
        return nearTwoYear.get();
    }

    public SimpleStringProperty nearTwoYearProperty() {
        return nearTwoYear;
    }

    public String getNearThreeYear() {
        return nearThreeYear.get();
    }

    public SimpleStringProperty nearThreeYearProperty() {
        return nearThreeYear;
    }

    public String getNearFiveYear() {
        return nearFiveYear.get();
    }

    public SimpleStringProperty nearFiveYearProperty() {
        return nearFiveYear;
    }

    public String getSinceThisYear() {
        return sinceThisYear.get();
    }

    public SimpleStringProperty sinceThisYearProperty() {
        return sinceThisYear;
    }

    public String getSinceEstablish() {
        return sinceEstablish.get();
    }

    public SimpleStringProperty sinceEstablishProperty() {
        return sinceEstablish;
    }

    public String getYearRate() {
        return yearRate.get();
    }

    public SimpleStringProperty yearRateProperty() {
        return yearRate;
    }
}
