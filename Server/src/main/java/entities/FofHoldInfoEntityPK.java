package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by st0001 on 2016/9/1.
 */
public class FofHoldInfoEntityPK implements Serializable {
    private String fofId;
    private String fundId;
    private String date;

    @Column(name = "fof_id", nullable = false, length = 255)
    @Id
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Column(name = "fund_id", nullable = false, length = 255)
    @Id
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    @Column(name = "date", nullable = false, length = 255)
    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofHoldInfoEntityPK that = (FofHoldInfoEntityPK) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (fundId != null ? !fundId.equals(that.fundId) : that.fundId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (fundId != null ? fundId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
