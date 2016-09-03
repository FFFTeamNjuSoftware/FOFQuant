package entities;

import javax.persistence.*;

/**
 * Created by st0001 on 2016/9/1.
 */
@Entity
@Table(name = "fof_info", schema = "fofquant", catalog = "")
public class FofInfoEntity {
    private String fofId;
    private String fofName;
    private String establishDate;
    private Double establishScale;
    private String compareBase;
    private Double netAsset;
    private Double scale;
    private Double netWorth;
    private Double totalProfit;
    private Double currentCost;

    @Id
    @Column(name = "fof_id", nullable = false, length = 255)
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Basic
    @Column(name = "net_worth", nullable = true, precision = 0)
    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    @Basic
    @Column(name = "fof_name", nullable = true, length = 255)
    public String getFofName() {
        return fofName;
    }

    public void setFofName(String fofName) {
        this.fofName = fofName;
    }

    @Basic
    @Column(name = "establish_date", nullable = true, length = 255)
    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    @Basic
    @Column(name = "establish_scale", nullable = true, precision = 0)
    public Double getEstablishScale() {
        return establishScale;
    }

    public void setEstablishScale(Double establishScale) {
        this.establishScale = establishScale;
    }

    @Basic
    @Column(name = "compare_base", nullable = true, length = 255)
    public String getCompareBase() {
        return compareBase;
    }

    public void setCompareBase(String compareBase) {
        this.compareBase = compareBase;
    }

    @Basic
    @Column(name = "net_asset", nullable = true, precision = 0)
    public Double getNetAsset() {
        return netAsset;
    }

    public void setNetAsset(Double netAsset) {
        this.netAsset = netAsset;
    }

    @Basic
    @Column(name = "scale", nullable = true, precision = 0)
    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofInfoEntity that = (FofInfoEntity) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (fofName != null ? !fofName.equals(that.fofName) : that.fofName != null) return false;
        if (establishDate != null ? !establishDate.equals(that.establishDate) : that.establishDate != null)
            return false;
        if (establishScale != null ? !establishScale.equals(that.establishScale) : that.establishScale != null)
            return false;
        if (compareBase != null ? !compareBase.equals(that.compareBase) : that.compareBase != null)
            return false;
        if (netAsset != null ? !netAsset.equals(that.netAsset) : that.netAsset != null)
            return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (fofName != null ? fofName.hashCode() : 0);
        result = 31 * result + (establishDate != null ? establishDate.hashCode() : 0);
        result = 31 * result + (establishScale != null ? establishScale.hashCode() : 0);
        result = 31 * result + (compareBase != null ? compareBase.hashCode() : 0);
        result = 31 * result + (netAsset != null ? netAsset.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        return result;
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
    @Column(name = "current_cost", nullable = true, precision = 0)
    public Double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(Double currentCost) {
        this.currentCost = currentCost;
    }
}
