package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/18.
 */
@Entity
@Table(name = "index_price", schema = "fofquant", catalog = "")
@IdClass(IndexPriceEntityPK.class)
public class IndexPriceEntity {
    private String code;
    private String date;
    private Double close;
    private Double daily_rise;

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "close", nullable = true, precision = 0)
    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    @Basic
    @Column(name = "daily_rise", nullable = true, precision = 0)
    public Double getDaily_rise() {
        return daily_rise;
    }

    public void setDaily_rise(Double daily_rise) {
        this.daily_rise = daily_rise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndexPriceEntity that = (IndexPriceEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (close != null ? !close.equals(that.close) : that.close != null) return false;
        if (daily_rise != null ? !daily_rise.equals(that.daily_rise) : that.daily_rise != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (close != null ? close.hashCode() : 0);
        result = 31 * result + (daily_rise != null ? daily_rise.hashCode() : 0);
        return result;
    }
}
