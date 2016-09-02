package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Daniel on 2016/9/2.
 */
public class FofEstablishInfoEntityPK implements Serializable {
    private String fofCode;
    private String fundCode;

    @Column(name = "fof_code", nullable = false, length = 255)
    @Id
    public String getFofCode() {
        return fofCode;
    }

    public void setFofCode(String fofCode) {
        this.fofCode = fofCode;
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

        FofEstablishInfoEntityPK that = (FofEstablishInfoEntityPK) o;

        if (fofCode != null ? !fofCode.equals(that.fofCode) : that.fofCode != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofCode != null ? fofCode.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        return result;
    }
}
