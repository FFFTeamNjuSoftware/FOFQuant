package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/9/2.
 */
@Entity
@Table(name = "fof_history_info", schema = "fofquant", catalog = "")
@IdClass(FofHistoryInfoEntityPK.class)
public class FofHistoryInfoEntity {
    private String fofId;
    private String date;
    private Double totalProfit;
    private Double totalProfitRate;
    private Double scale;
    private Double totalValue;
    private Double dailyProfit;
    private Double dailyProfitRate;
    private Double cashValue;

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

    @Basic
    @Column(name = "total_profit", nullable = true, precision = 0)
    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    @Basic
    @Column(name = "total_profit_rate", nullable = true, precision = 0)
    public Double getTotalProfitRate() {
        return totalProfitRate;
    }

    public void setTotalProfitRate(Double totalProfitRate) {
        this.totalProfitRate = totalProfitRate;
    }

    @Basic
    @Column(name = "scale", nullable = true, precision = 0)
    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @Basic
    @Column(name = "total_value", nullable = true, precision = 0)
    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Basic
    @Column(name = "daily_profit", nullable = true, precision = 0)
    public Double getDailyProfit() {
        return dailyProfit;
    }

    public void setDailyProfit(Double dailyProfit) {
        this.dailyProfit = dailyProfit;
    }

    @Basic
    @Column(name = "daily_profit_rate", nullable = true, precision = 0)
    public Double getDailyProfitRate() {
        return dailyProfitRate;
    }

    public void setDailyProfitRate(Double dailyProfitRate) {
        this.dailyProfitRate = dailyProfitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofHistoryInfoEntity that = (FofHistoryInfoEntity) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (totalProfit != null ? !totalProfit.equals(that.totalProfit) : that.totalProfit != null)
            return false;
        if (totalProfitRate != null ? !totalProfitRate.equals(that.totalProfitRate) : that.totalProfitRate != null)
            return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (totalValue != null ? !totalValue.equals(that.totalValue) : that.totalValue != null)
            return false;
        if (dailyProfit != null ? !dailyProfit.equals(that.dailyProfit) : that.dailyProfit != null)
            return false;
        if (dailyProfitRate != null ? !dailyProfitRate.equals(that.dailyProfitRate) : that.dailyProfitRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalProfit != null ? totalProfit.hashCode() : 0);
        result = 31 * result + (totalProfitRate != null ? totalProfitRate.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (totalValue != null ? totalValue.hashCode() : 0);
        result = 31 * result + (dailyProfit != null ? dailyProfit.hashCode() : 0);
        result = 31 * result + (dailyProfitRate != null ? dailyProfitRate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "cash_value", nullable = true, precision = 0)
    public Double getCashValue() {
        return cashValue;
    }

    public void setCashValue(Double cashValue) {
        this.cashValue = cashValue;
    }
}
