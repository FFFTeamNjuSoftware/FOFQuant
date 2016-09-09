package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/9/9.
 */
@Entity
@Table(name = "warn_log", schema = "fofquant", catalog = "")
public class WarnLogEntity {
    private int id;
    private String date;
    private Double totalProfit;
    private String warnInfo;
    private Double netWorth;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    @Column(name = "warnInfo", nullable = true, length = 255)
    public String getWarnInfo() {
        return warnInfo;
    }

    public void setWarnInfo(String warnInfo) {
        this.warnInfo = warnInfo;
    }

    @Basic
    @Column(name = "net_worth", nullable = true, precision = 0)
    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarnLogEntity that = (WarnLogEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (totalProfit != null ? !totalProfit.equals(that.totalProfit) : that.totalProfit != null)
            return false;
        if (warnInfo != null ? !warnInfo.equals(that.warnInfo) : that.warnInfo != null)
            return false;
        if (netWorth != null ? !netWorth.equals(that.netWorth) : that.netWorth != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (totalProfit != null ? totalProfit.hashCode() : 0);
        result = 31 * result + (warnInfo != null ? warnInfo.hashCode() : 0);
        result = 31 * result + (netWorth != null ? netWorth.hashCode() : 0);
        return result;
    }
}
