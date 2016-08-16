package entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Daniel on 2016/8/16.
 */
@Entity
@Table(name = "asset_allocation", schema = "fofquant", catalog = "")
@IdClass(AssetAllocationEntityPK.class)
public class AssetAllocation {
    private String code;
    private Date date;
    private Double stockValue;
    private Double stockRatio;
    private Double bondValue;
    private Double bondRatio;
    private Double cashValue;
    private Double cashRatio;
    private Double totalValue;
    private Double netValue;
    private Double otherRatio;
    private Double otherValue;

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "stock_value", nullable = true, precision = 0)
    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }

    @Basic
    @Column(name = "stock_ratio", nullable = true, precision = 0)
    public Double getStockRatio() {
        return stockRatio;
    }

    public void setStockRatio(Double stockRatio) {
        this.stockRatio = stockRatio;
    }

    @Basic
    @Column(name = "bond_value", nullable = true, precision = 0)
    public Double getBondValue() {
        return bondValue;
    }

    public void setBondValue(Double bondValue) {
        this.bondValue = bondValue;
    }

    @Basic
    @Column(name = "bond_ratio", nullable = true, precision = 0)
    public Double getBondRatio() {
        return bondRatio;
    }

    public void setBondRatio(Double bondRatio) {
        this.bondRatio = bondRatio;
    }

    @Basic
    @Column(name = "cash_value", nullable = true, precision = 0)
    public Double getCashValue() {
        return cashValue;
    }

    public void setCashValue(Double cashValue) {
        this.cashValue = cashValue;
    }

    @Basic
    @Column(name = "cash_ratio", nullable = true, precision = 0)
    public Double getCashRatio() {
        return cashRatio;
    }

    public void setCashRatio(Double cashRatio) {
        this.cashRatio = cashRatio;
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
    @Column(name = "net_value", nullable = true, precision = 0)
    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    @Basic
    @Column(name = "other_ratio", nullable = true, precision = 0)
    public Double getOtherRatio() {
        return otherRatio;
    }

    public void setOtherRatio(Double otherRatio) {
        this.otherRatio = otherRatio;
    }

    @Basic
    @Column(name = "other_value", nullable = true, precision = 0)
    public Double getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(Double otherValue) {
        this.otherValue = otherValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetAllocation that = (AssetAllocation) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (stockValue != null ? !stockValue.equals(that.stockValue) : that.stockValue != null)
            return false;
        if (stockRatio != null ? !stockRatio.equals(that.stockRatio) : that.stockRatio != null)
            return false;
        if (bondValue != null ? !bondValue.equals(that.bondValue) : that.bondValue != null)
            return false;
        if (bondRatio != null ? !bondRatio.equals(that.bondRatio) : that.bondRatio != null)
            return false;
        if (cashValue != null ? !cashValue.equals(that.cashValue) : that.cashValue != null)
            return false;
        if (cashRatio != null ? !cashRatio.equals(that.cashRatio) : that.cashRatio != null)
            return false;
        if (totalValue != null ? !totalValue.equals(that.totalValue) : that.totalValue != null)
            return false;
        if (netValue != null ? !netValue.equals(that.netValue) : that.netValue != null)
            return false;
        if (otherRatio != null ? !otherRatio.equals(that.otherRatio) : that.otherRatio != null)
            return false;
        if (otherValue != null ? !otherValue.equals(that.otherValue) : that.otherValue != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (stockValue != null ? stockValue.hashCode() : 0);
        result = 31 * result + (stockRatio != null ? stockRatio.hashCode() : 0);
        result = 31 * result + (bondValue != null ? bondValue.hashCode() : 0);
        result = 31 * result + (bondRatio != null ? bondRatio.hashCode() : 0);
        result = 31 * result + (cashValue != null ? cashValue.hashCode() : 0);
        result = 31 * result + (cashRatio != null ? cashRatio.hashCode() : 0);
        result = 31 * result + (totalValue != null ? totalValue.hashCode() : 0);
        result = 31 * result + (netValue != null ? netValue.hashCode() : 0);
        result = 31 * result + (otherRatio != null ? otherRatio.hashCode() : 0);
        result = 31 * result + (otherValue != null ? otherValue.hashCode() : 0);
        return result;
    }
}
