package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by st0001 on 2016/9/1.
 */
public class FofAssetAllocationEntityPK implements Serializable {
    private String fofId;
    private String date;
    private String fundCode;

    @Column(name = "fof_id", nullable = false, length = 255)
    @Id
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Column(name = "date", nullable = false, length = 255)
    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Column(name = "fund_code", nullable = false, length = 255)
    @Id
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofAssetAllocationEntityPK that = (FofAssetAllocationEntityPK) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        return result;
    }
}
