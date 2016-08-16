package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Daniel on 2016/8/16.
 */
public class IndustryHoldInfoEntityPK implements Serializable {
    private String date;
    private String fundCode;
    private String industryCode;

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

    @Column(name = "industryCode", nullable = false, length = 255)
    @Id
    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IndustryHoldInfoEntityPK that = (IndustryHoldInfoEntityPK) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (industryCode != null ? !industryCode.equals(that.industryCode) : that.industryCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (industryCode != null ? industryCode.hashCode() : 0);
        return result;
    }
}
