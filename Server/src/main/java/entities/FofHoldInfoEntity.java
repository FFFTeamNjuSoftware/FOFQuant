package entities;

import javax.persistence.*;

/**
 * Created by st0001 on 2016/9/1.
 */
@Entity
@Table(name = "fof_hold_info", schema = "fofquant", catalog = "")
@IdClass(FofHoldInfoEntityPK.class)
public class FofHoldInfoEntity {
    private String fofId;
    private String fundId;
    private String date;
    private Double holdNum;
    private Double holdValue;
    private Double ratio;
    private Double dayProfit;
    private Double floatProfit;
    private Double floatProfitRatio;
    private Double totalProfit;
    private Double totalProfitRatio;
    private Double finishedProfit;

    @Id
    @Column(name = "fof_id", nullable = false, length = 255)
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Id
    @Column(name = "fund_id", nullable = false, length = 255)
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    @Id
    @Column(name = "date", nullable = false, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "hold_num", nullable = true, precision = 0)
    public Double getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Double holdNum) {
        this.holdNum = holdNum;
    }

    @Basic
    @Column(name = "hold_value", nullable = true, precision = 0)
    public Double getHoldValue() {
        return holdValue;
    }

    public void setHoldValue(Double holdValue) {
        this.holdValue = holdValue;
    }

    @Basic
    @Column(name = "ratio", nullable = true, precision = 0)
    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Basic
    @Column(name = "dayProfit", nullable = true, precision = 0)
    public Double getDayProfit() {
        return dayProfit;
    }

    public void setDayProfit(Double dayProfit) {
        this.dayProfit = dayProfit;
    }

    @Basic
    @Column(name = "floatProfit", nullable = true, precision = 0)
    public Double getFloatProfit() {
        return floatProfit;
    }

    public void setFloatProfit(Double floatProfit) {
        this.floatProfit = floatProfit;
    }

    @Basic
    @Column(name = "floatProfitRatio", nullable = true, precision = 0)
    public Double getFloatProfitRatio() {
        return floatProfitRatio;
    }

    public void setFloatProfitRatio(Double floatProfitRatio) {
        this.floatProfitRatio = floatProfitRatio;
    }

    @Basic
    @Column(name = "totalProfit", nullable = true, precision = 0)
    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Basic
    @Column(name = "totalProfitRatio", nullable = true, precision = 0)
    public Double getTotalProfitRatio() {
        return totalProfitRatio;
    }

    public void setTotalProfitRatio(Double totalProfitRatio) {
        this.totalProfitRatio = totalProfitRatio;
    }

    @Basic
    @Column(name = "finishedProfit", nullable = true, precision = 0)
    public Double getFinishedProfit() {
        return finishedProfit;
    }

    public void setFinishedProfit(Double finishedProfit) {
        this.finishedProfit = finishedProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofHoldInfoEntity that = (FofHoldInfoEntity) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (fundId != null ? !fundId.equals(that.fundId) : that.fundId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (holdNum != null ? !holdNum.equals(that.holdNum) : that.holdNum != null) return false;
        if (holdValue != null ? !holdValue.equals(that.holdValue) : that.holdValue != null)
            return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;
        if (dayProfit != null ? !dayProfit.equals(that.dayProfit) : that.dayProfit != null)
            return false;
        if (floatProfit != null ? !floatProfit.equals(that.floatProfit) : that.floatProfit != null)
            return false;
        if (floatProfitRatio != null ? !floatProfitRatio.equals(that.floatProfitRatio) : that.floatProfitRatio != null)
            return false;
        if (totalProfit != null ? !totalProfit.equals(that.totalProfit) : that.totalProfit != null)
            return false;
        if (totalProfitRatio != null ? !totalProfitRatio.equals(that.totalProfitRatio) : that.totalProfitRatio != null)
            return false;
        if (finishedProfit != null ? !finishedProfit.equals(that.finishedProfit) : that.finishedProfit != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (fundId != null ? fundId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (holdNum != null ? holdNum.hashCode() : 0);
        result = 31 * result + (holdValue != null ? holdValue.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        result = 31 * result + (dayProfit != null ? dayProfit.hashCode() : 0);
        result = 31 * result + (floatProfit != null ? floatProfit.hashCode() : 0);
        result = 31 * result + (floatProfitRatio != null ? floatProfitRatio.hashCode() : 0);
        result = 31 * result + (totalProfit != null ? totalProfit.hashCode() : 0);
        result = 31 * result + (totalProfitRatio != null ? totalProfitRatio.hashCode() : 0);
        result = 31 * result + (finishedProfit != null ? finishedProfit.hashCode() : 0);
        return result;
    }
}
