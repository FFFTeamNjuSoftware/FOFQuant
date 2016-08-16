package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/16.
 */
@Entity
@Table(name = "stock_hold_info", schema = "fofquant", catalog = "")
@IdClass(StockHoldInfoEntityPK.class)
public class StockHoldInfoEntity {
    private String date;
    private String fundCode;
    private String stockCode;
    private String stockName;
    private Double holdNum;
    private Double value;
    private Double ratio;

    @Id
    @Column(name = "date", nullable = false, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Id
    @Column(name = "fundCode", nullable = false, length = 255)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Id
    @Column(name = "stockCode", nullable = false, length = 255)
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Basic
    @Column(name = "stockName", nullable = true, length = 255)
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Basic
    @Column(name = "holdNum", nullable = true, precision = 0)
    public Double getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Double holdNum) {
        this.holdNum = holdNum;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "ratio", nullable = true, precision = 0)
    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockHoldInfoEntity that = (StockHoldInfoEntity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (stockCode != null ? !stockCode.equals(that.stockCode) : that.stockCode != null)
            return false;
        if (stockName != null ? !stockName.equals(that.stockName) : that.stockName != null)
            return false;
        if (holdNum != null ? !holdNum.equals(that.holdNum) : that.holdNum != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (stockCode != null ? stockCode.hashCode() : 0);
        result = 31 * result + (stockName != null ? stockName.hashCode() : 0);
        result = 31 * result + (holdNum != null ? holdNum.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        return result;
    }
}
