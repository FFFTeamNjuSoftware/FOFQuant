package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/16.
 */
@Entity
@Table(name = "industry_hold_info", schema = "fofquant", catalog = "")
@IdClass(IndustryHoldInfoEntityPK.class)
public class IndustryHoldInfoEntity {
    private String date;
    private String fundCode;
    private String industryCode;
    private String industryName;
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
    @Column(name = "industryCode", nullable = false, length = 255)
    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    @Basic
    @Column(name = "industryName", nullable = true, length = 255)
    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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

        IndustryHoldInfoEntity that = (IndustryHoldInfoEntity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (industryCode != null ? !industryCode.equals(that.industryCode) : that.industryCode != null)
            return false;
        if (industryName != null ? !industryName.equals(that.industryName) : that.industryName != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (industryCode != null ? industryCode.hashCode() : 0);
        result = 31 * result + (industryName != null ? industryName.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        return result;
    }
}
