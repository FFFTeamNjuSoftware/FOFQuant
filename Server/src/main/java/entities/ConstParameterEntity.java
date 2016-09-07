package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/21.
 */
@Entity
@Table(name = "const_parameter", schema = "fofquant", catalog = "")
public class ConstParameterEntity {
    private int id;
    private Double noRiskProfit;
    private Double stableIndex;
    private Double lowRiskIndex;
    private Double highRiskIndex;
    private Double stopLossValue;
    private Double maxRetreatRatio;
    private Double holdTime;
    private Double windowTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "no_risk_profit", nullable = true, precision = 0)
    public Double getNoRiskProfit() {
        return noRiskProfit;
    }

    public void setNoRiskProfit(Double noRiskProfit) {
        this.noRiskProfit = noRiskProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstParameterEntity that = (ConstParameterEntity) o;

        if (id != that.id) return false;
        if (noRiskProfit != null ? !noRiskProfit.equals(that.noRiskProfit) : that.noRiskProfit != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (noRiskProfit != null ? noRiskProfit.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "stable_index", nullable = true, precision = 0)
    public Double getStableIndex() {
        return stableIndex;
    }

    public void setStableIndex(Double stableIndex) {
        this.stableIndex = stableIndex;
    }

    @Basic
    @Column(name = "low_risk_index", nullable = true, precision = 0)
    public Double getLowRiskIndex() {
        return lowRiskIndex;
    }

    public void setLowRiskIndex(Double lowRiskIndex) {
        this.lowRiskIndex = lowRiskIndex;
    }

    @Basic
    @Column(name = "high_risk_index", nullable = true, precision = 0)
    public Double getHighRiskIndex() {
        return highRiskIndex;
    }

    public void setHighRiskIndex(Double highRiskIndex) {
        this.highRiskIndex = highRiskIndex;
    }

    @Basic
    @Column(name = "stop_loss_value", nullable = true, precision = 0)
    public Double getStopLossValue() {
        return stopLossValue;
    }

    public void setStopLossValue(Double stopLossValue) {
        this.stopLossValue = stopLossValue;
    }

    @Basic
    @Column(name = "max_retreat_ratio", nullable = true, precision = 0)
    public Double getMaxRetreatRatio() {
        return maxRetreatRatio;
    }

    public void setMaxRetreatRatio(Double maxRetreatRatio) {
        this.maxRetreatRatio = maxRetreatRatio;
    }

    @Basic
    @Column(name = "hold_time", nullable = true, precision = 0)
    public Double getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(Double holdTime) {
        this.holdTime = holdTime;
    }

    @Basic
    @Column(name = "window_time", nullable = true, precision = 0)
    public Double getWindowTime() {
        return windowTime;
    }

    public void setWindowTime(Double windowTime) {
        this.windowTime = windowTime;
    }
}
