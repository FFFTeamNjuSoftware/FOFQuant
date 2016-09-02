package entities;

import javax.persistence.*;

/**
 * Created by st0001 on 2016/9/1.
 */
@Entity
@Table(name = "fof_asset_allocation", schema = "fofquant", catalog = "")
@IdClass(FofAssetAllocationEntityPK.class)
public class FofAssetAllocationEntity {
    private String fofId;
    private String date;
    private String fundCode;
    private String fundName;
    private Double endNetWorth;
    private Double endHoldNum;
    private Double endHoldValue;
    private Double periodProfit;
    private Double periodFloatProfit;
    private Double periodProfitRate;
    private Double profitRatio;
    private Double endHoldRatio;
    private Double unitProfit;

    @Id
    @Column(name = "fof_id", nullable = false, length = 255)
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Id
    @Column(name = "date", nullable = false, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @Column(name = "fund_code", nullable = false, length = 255)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Basic
    @Column(name = "fund_name", nullable = true, length = 255)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "endNetWorth", nullable = true, precision = 0)
    public Double getEndNetWorth() {
        return endNetWorth;
    }

    public void setEndNetWorth(Double endNetWorth) {
        this.endNetWorth = endNetWorth;
    }

    @Basic
    @Column(name = "endHoldNum", nullable = true, precision = 0)
    public Double getEndHoldNum() {
        return endHoldNum;
    }

    public void setEndHoldNum(Double endHoldNum) {
        this.endHoldNum = endHoldNum;
    }

    @Basic
    @Column(name = "endHoldValue", nullable = true, precision = 0)
    public Double getEndHoldValue() {
        return endHoldValue;
    }

    public void setEndHoldValue(Double endHoldValue) {
        this.endHoldValue = endHoldValue;
    }

    @Basic
    @Column(name = "periodProfit", nullable = true, precision = 0)
    public Double getPeriodProfit() {
        return periodProfit;
    }

    public void setPeriodProfit(Double periodProfit) {
        this.periodProfit = periodProfit;
    }

    @Basic
    @Column(name = "periodFloatProfit", nullable = true, precision = 0)
    public Double getPeriodFloatProfit() {
        return periodFloatProfit;
    }

    public void setPeriodFloatProfit(Double periodFloatProfit) {
        this.periodFloatProfit = periodFloatProfit;
    }

    @Basic
    @Column(name = "periodProfitRate", nullable = true, precision = 0)
    public Double getPeriodProfitRate() {
        return periodProfitRate;
    }

    public void setPeriodProfitRate(Double periodProfitRate) {
        this.periodProfitRate = periodProfitRate;
    }

    @Basic
    @Column(name = "profitRatio", nullable = true, precision = 0)
    public Double getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(Double profitRatio) {
        this.profitRatio = profitRatio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofAssetAllocationEntity that = (FofAssetAllocationEntity) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (fundName != null ? !fundName.equals(that.fundName) : that.fundName != null)
            return false;
        if (endNetWorth != null ? !endNetWorth.equals(that.endNetWorth) : that.endNetWorth != null)
            return false;
        if (endHoldNum != null ? !endHoldNum.equals(that.endHoldNum) : that.endHoldNum != null)
            return false;
        if (endHoldValue != null ? !endHoldValue.equals(that.endHoldValue) : that.endHoldValue != null)
            return false;
        if (periodProfit != null ? !periodProfit.equals(that.periodProfit) : that.periodProfit != null)
            return false;
        if (periodFloatProfit != null ? !periodFloatProfit.equals(that.periodFloatProfit) : that.periodFloatProfit != null)
            return false;
        if (periodProfitRate != null ? !periodProfitRate.equals(that.periodProfitRate) : that.periodProfitRate != null)
            return false;
        if (profitRatio != null ? !profitRatio.equals(that.profitRatio) : that.profitRatio != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (fundName != null ? fundName.hashCode() : 0);
        result = 31 * result + (endNetWorth != null ? endNetWorth.hashCode() : 0);
        result = 31 * result + (endHoldNum != null ? endHoldNum.hashCode() : 0);
        result = 31 * result + (endHoldValue != null ? endHoldValue.hashCode() : 0);
        result = 31 * result + (periodProfit != null ? periodProfit.hashCode() : 0);
        result = 31 * result + (periodFloatProfit != null ? periodFloatProfit.hashCode() : 0);
        result = 31 * result + (periodProfitRate != null ? periodProfitRate.hashCode() : 0);
        result = 31 * result + (profitRatio != null ? profitRatio.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "endHoldRatio", nullable = true, precision = 0)
    public Double getEndHoldRatio() {
        return endHoldRatio;
    }

    public void setEndHoldRatio(Double endHoldRatio) {
        this.endHoldRatio = endHoldRatio;
    }

    @Basic
    @Column(name = "unitProfit", nullable = true, precision = 0)
    public Double getUnitProfit() {
        return unitProfit;
    }

    public void setUnitProfit(Double unitProfit) {
        this.unitProfit = unitProfit;
    }
}
