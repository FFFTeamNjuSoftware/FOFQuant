package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/16.
 */
@Entity
@Table(name = "bond_hold_info", schema = "fofquant", catalog = "")
@IdClass(BondHoldInfoEntityPK.class)
public class BondHoldInfoEntity {
    private String date;
    private String fundCode;
    private String bondCode;
    private String bondName;
    private Double holdNum;
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
    @Column(name = "bondCode", nullable = false, length = 255)
    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    @Basic
    @Column(name = "bondName", nullable = true, length = 255)
    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    @Basic
    @Column(name = "holdNum", nullable = true, precision = 0)
    public Double getHoldNum() {
        return holdNum;
    }

    public void setHoldNum(Double holdNum) {
        this.holdNum = holdNum;
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

        BondHoldInfoEntity that = (BondHoldInfoEntity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (bondCode != null ? !bondCode.equals(that.bondCode) : that.bondCode != null)
            return false;
        if (bondName != null ? !bondName.equals(that.bondName) : that.bondName != null)
            return false;
        if (holdNum != null ? !holdNum.equals(that.holdNum) : that.holdNum != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (ratio != null ? !ratio.equals(that.ratio) : that.ratio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (bondCode != null ? bondCode.hashCode() : 0);
        result = 31 * result + (bondName != null ? bondName.hashCode() : 0);
        result = 31 * result + (holdNum != null ? holdNum.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (ratio != null ? ratio.hashCode() : 0);
        return result;
    }
}
