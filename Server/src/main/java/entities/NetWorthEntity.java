package entities;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Daniel on 2016/8/16.
 */
@Entity
@Table(name = "net_worth", schema = "fofquant", catalog = "")
@IdClass(NetWorthEntityPK.class)
public class NetWorthEntity {
    private Date date;
    private String code;
    private Double unitWorth;
    private Double totalWorth;
    private Double dailyRise;
    private Double fqWorth;

    @Id
    @Column(name = "fqnet", nullable = false)
    public Double getFqWorth() {
        return fqWorth;
    }

    public void setFqWorth(Double fqWorth) {
        this.fqWorth = fqWorth;
    }

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "unit_worth", nullable = true, precision = 0)
    public Double getUnitWorth() {
        return unitWorth;
    }

    public void setUnitWorth(Double unitWorth) {
        this.unitWorth = unitWorth;
    }

    @Basic
    @Column(name = "total_worth", nullable = true, precision = 0)
    public Double getTotalWorth() {
        return totalWorth;
    }

    public void setTotalWorth(Double totalWorth) {
        this.totalWorth = totalWorth;
    }

    @Basic
    @Column(name = "daily_rise", nullable = true, precision = 0)
    public Double getDailyRise() {
        return dailyRise;
    }

    public void setDailyRise(Double dailyRise) {
        this.dailyRise = dailyRise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetWorthEntity that = (NetWorthEntity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (unitWorth != null ? !unitWorth.equals(that.unitWorth) : that.unitWorth != null)
            return false;
        if (totalWorth != null ? !totalWorth.equals(that.totalWorth) : that.totalWorth != null)
            return false;
        if (dailyRise != null ? !dailyRise.equals(that.dailyRise) : that.dailyRise != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (unitWorth != null ? unitWorth.hashCode() : 0);
        result = 31 * result + (totalWorth != null ? totalWorth.hashCode() : 0);
        result = 31 * result + (dailyRise != null ? dailyRise.hashCode() : 0);
        return result;
    }
}
