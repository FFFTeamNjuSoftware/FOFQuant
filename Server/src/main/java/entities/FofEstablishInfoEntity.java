package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/9/2.
 */
@Entity
@Table(name = "fof_establish_info", schema = "fofquant", catalog = "")
@IdClass(FofEstablishInfoEntityPK.class)
public class FofEstablishInfoEntity {
    private String fofCode;
    private String fundCode;
    private String buyDate;
    private Double buyPrice;
    private Double buyNumber;
    private Double cost;
    private Double otherFee;

    @Id
    @Column(name = "fof_code", nullable = false, length = 255)
    public String getFofCode() {
        return fofCode;
    }

    public void setFofCode(String fofCode) {
        this.fofCode = fofCode;
    }

    @Id
    @Column(name = "fund_code", nullable = false, length = 255)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Basic
    @Column(name = "buy_date", nullable = true, length = 255)
    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    @Basic
    @Column(name = "buy_price", nullable = true, precision = 0)
    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Basic
    @Column(name = "buy_number", nullable = true, precision = 0)
    public Double getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Double buyNumber) {
        this.buyNumber = buyNumber;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "other_fee", nullable = true, precision = 0)
    public Double getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(Double otherFee) {
        this.otherFee = otherFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofEstablishInfoEntity that = (FofEstablishInfoEntity) o;

        if (fofCode != null ? !fofCode.equals(that.fofCode) : that.fofCode != null) return false;
        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (buyDate != null ? !buyDate.equals(that.buyDate) : that.buyDate != null) return false;
        if (buyPrice != null ? !buyPrice.equals(that.buyPrice) : that.buyPrice != null)
            return false;
        if (buyNumber != null ? !buyNumber.equals(that.buyNumber) : that.buyNumber != null)
            return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (otherFee != null ? !otherFee.equals(that.otherFee) : that.otherFee != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofCode != null ? fofCode.hashCode() : 0;
        result = 31 * result + (fundCode != null ? fundCode.hashCode() : 0);
        result = 31 * result + (buyDate != null ? buyDate.hashCode() : 0);
        result = 31 * result + (buyPrice != null ? buyPrice.hashCode() : 0);
        result = 31 * result + (buyNumber != null ? buyNumber.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (otherFee != null ? otherFee.hashCode() : 0);
        return result;
    }
}
