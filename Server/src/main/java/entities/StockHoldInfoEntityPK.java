package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/16.
 */
public class StockHoldInfoEntityPK implements Serializable {
    private String date;
    private String fundCode;
    private String stockCode;

    @Column(name = "date", nullable = false, length = 255)
    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "fundCode", nullable = false, length = 255)
    @Id
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Column(name = "stockCode", nullable = false, length = 255)
    @Id
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockHoldInfoEntityPK that = (StockHoldInfoEntityPK) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (stockCode != null ? !stockCode.equals(that.stockCode) : that.stockCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (stockCode != null ? stockCode.hashCode() : 0);
        return result;
    }
}
