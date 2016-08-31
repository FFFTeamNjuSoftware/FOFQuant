package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/31.
 */
@Entity
@Table(name = "fund_realtime_info", schema = "fofquant", catalog = "")
@IdClass(FundRealtimeInfoEntityPK.class)
public class FundRealtimeInfoEntity {
    private String code;
    private String date;
    private String time;
    private Double gsz;
    private Double gsrise;

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

    @Id
    @Column(name = "time", nullable = false, length = 255)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "gsz", nullable = true, precision = 0)
    public Double getGsz() {
        return gsz;
    }

    public void setGsz(Double gsz) {
        this.gsz = gsz;
    }

    @Basic
    @Column(name = "gsrise", nullable = true, precision = 0)
    public Double getGsrise() {
        return gsrise;
    }

    public void setGsrise(Double gsrise) {
        this.gsrise = gsrise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundRealtimeInfoEntity that = (FundRealtimeInfoEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (gsz != null ? !gsz.equals(that.gsz) : that.gsz != null) return false;
        if (gsrise != null ? !gsrise.equals(that.gsrise) : that.gsrise != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (gsz != null ? gsz.hashCode() : 0);
        result = 31 * result + (gsrise != null ? gsrise.hashCode() : 0);
        return result;
    }
}
