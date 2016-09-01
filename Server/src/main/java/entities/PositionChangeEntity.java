package entities;

import javax.persistence.*;

/**
 * Created by st0001 on 2016/9/1.
 */
@Entity
@Table(name = "position_change", schema = "fofquant", catalog = "")
public class PositionChangeEntity {
    private int id;
    private String changeDate;
    private String changeTime;
    private String fundCode;
    private String fundName;
    private Double buyNum;
    private Double buyPrice;
    private Double saleNum;
    private Double salePrice;
    private String fofCode;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "changeDate", nullable = true, length = 255)
    public String getFofCode() {
        return fofCode;
    }
    public void setFofCode(String fofCode) {
        this.fofCode = fofCode;
    }

    @Basic
    @Column(name = "changeDate", nullable = true, length = 255)
    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    @Basic
    @Column(name = "changeTime", nullable = true, length = 255)
    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    @Basic
    @Column(name = "fundCode", nullable = true, length = 255)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Basic
    @Column(name = "fundName", nullable = true, length = 255)
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @Basic
    @Column(name = "buyNum", nullable = true, precision = 0)
    public Double getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Double buyNum) {
        this.buyNum = buyNum;
    }

    @Basic
    @Column(name = "buyPrice", nullable = true, precision = 0)
    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "saleNum", nullable = true, precision = 0)
    public Double getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Double saleNum) {
        this.saleNum = saleNum;
    }

    @Basic
    @Column(name = "salePrice", nullable = true, precision = 0)
    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionChangeEntity that = (PositionChangeEntity) o;

        if (id != that.id) return false;
        if (changeDate != null ? !changeDate.equals(that.changeDate) : that.changeDate != null)
            return false;
        if (changeTime != null ? !changeTime.equals(that.changeTime) : that.changeTime != null)
            return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (fundName != null ? !fundName.equals(that.fundName) : that.fundName != null)
            return false;
        if (buyNum != null ? !buyNum.equals(that.buyNum) : that.buyNum != null) return false;
        if (buyPrice != null ? !buyPrice.equals(that.buyPrice) : that.buyPrice != null)
            return false;
        if (saleNum != null ? !saleNum.equals(that.saleNum) : that.saleNum != null) return false;
        if (salePrice != null ? !salePrice.equals(that.salePrice) : that.salePrice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (changeDate != null ? changeDate.hashCode() : 0);
        result = 31 * result + (changeTime != null ? changeTime.hashCode() : 0);
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (fundName != null ? fundName.hashCode() : 0);
        result = 31 * result + (buyNum != null ? buyNum.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (saleNum != null ? saleNum.hashCode() : 0);
        result = 31 * result + (salePrice != null ? salePrice.hashCode() : 0);
        return result;
    }
}
