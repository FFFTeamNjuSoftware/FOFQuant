package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/20.
 */
@Entity
@Table(name = "fund_quick_infos", schema = "fofquant", catalog = "")
public class FundQuickInfosEntity {
    private String fundCode;
    private String simpleName;
    private Double netWorth;
    private Double dailyRise;
    private Double oneMonth;
    private Double threeMonth;
    private Double sixMonth;
    private Double oneYear;
    private Double threeYear;
    private Double fiveYear;
    private Double sinceEstablish;
    private Double yearRate;

    @Id
    @Column(name = "fundCode", nullable = false, length = 255)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Basic
    @Column(name = "simpleName", nullable = true, length = 255)
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Basic
    @Column(name = "net_worth", nullable = true, precision = 0)
    public Double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(Double netWorth) {
        this.netWorth = netWorth;
    }

    @Basic
    @Column(name = "daily_rise", nullable = true, precision = 0)
    public Double getDailyRise() {
        return dailyRise;
    }

    public void setDailyRise(Double dailyRise) {
        this.dailyRise = dailyRise;
    }

    @Basic
    @Column(name = "oneMonth", nullable = true, precision = 0)
    public Double getOneMonth() {
        return oneMonth;
    }

    public void setOneMonth(Double oneMonth) {
        this.oneMonth = oneMonth;
    }

    @Basic
    @Column(name = "threeMonth", nullable = true, precision = 0)
    public Double getThreeMonth() {
        return threeMonth;
    }

    public void setThreeMonth(Double threeMonth) {
        this.threeMonth = threeMonth;
    }

    @Basic
    @Column(name = "sixMonth", nullable = true, precision = 0)
    public Double getSixMonth() {
        return sixMonth;
    }

    public void setSixMonth(Double sixMonth) {
        this.sixMonth = sixMonth;
    }

    @Basic
    @Column(name = "oneYear", nullable = true, precision = 0)
    public Double getOneYear() {
        return oneYear;
    }

    public void setOneYear(Double oneYear) {
        this.oneYear = oneYear;
    }

    @Basic
    @Column(name = "threeYear", nullable = true, precision = 0)
    public Double getThreeYear() {
        return threeYear;
    }

    public void setThreeYear(Double threeYear) {
        this.threeYear = threeYear;
    }

    @Basic
    @Column(name = "fiveYear", nullable = true, precision = 0)
    public Double getFiveYear() {
        return fiveYear;
    }

    public void setFiveYear(Double fiveYear) {
        this.fiveYear = fiveYear;
    }

    @Basic
    @Column(name = "sinceEstablish", nullable = true, precision = 0)
    public Double getSinceEstablish() {
        return sinceEstablish;
    }

    public void setSinceEstablish(Double sinceEstablish) {
        this.sinceEstablish = sinceEstablish;
    }

    @Basic
    @Column(name = "yearRate", nullable = true, precision = 0)
    public Double getYearRate() {
        return yearRate;
    }

    public void setYearRate(Double yearRate) {
        this.yearRate = yearRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundQuickInfosEntity that = (FundQuickInfosEntity) o;

        if (fundCode != null ? !fundCode.equals(that.fundCode) : that.fundCode != null)
            return false;
        if (simpleName != null ? !simpleName.equals(that.simpleName) : that.simpleName != null)
            return false;
        if (netWorth != null ? !netWorth.equals(that.netWorth) : that.netWorth != null)
            return false;
        if (dailyRise != null ? !dailyRise.equals(that.dailyRise) : that.dailyRise != null)
            return false;
        if (oneMonth != null ? !oneMonth.equals(that.oneMonth) : that.oneMonth != null)
            return false;
        if (threeMonth != null ? !threeMonth.equals(that.threeMonth) : that.threeMonth != null)
            return false;
        if (sixMonth != null ? !sixMonth.equals(that.sixMonth) : that.sixMonth != null)
            return false;
        if (oneYear != null ? !oneYear.equals(that.oneYear) : that.oneYear != null) return false;
        if (threeYear != null ? !threeYear.equals(that.threeYear) : that.threeYear != null)
            return false;
        if (fiveYear != null ? !fiveYear.equals(that.fiveYear) : that.fiveYear != null)
            return false;
        if (sinceEstablish != null ? !sinceEstablish.equals(that.sinceEstablish) : that.sinceEstablish != null)
            return false;
        if (yearRate != null ? !yearRate.equals(that.yearRate) : that.yearRate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fundCode != null ? fundCode.hashCode() : 0;
        result = 31 * result + (simpleName != null ? simpleName.hashCode() : 0);
        result = 31 * result + (netWorth != null ? netWorth.hashCode() : 0);
        result = 31 * result + (dailyRise != null ? dailyRise.hashCode() : 0);
        result = 31 * result + (oneMonth != null ? oneMonth.hashCode() : 0);
        result = 31 * result + (threeMonth != null ? threeMonth.hashCode() : 0);
        result = 31 * result + (sixMonth != null ? sixMonth.hashCode() : 0);
        result = 31 * result + (oneYear != null ? oneYear.hashCode() : 0);
        result = 31 * result + (threeYear != null ? threeYear.hashCode() : 0);
        result = 31 * result + (fiveYear != null ? fiveYear.hashCode() : 0);
        result = 31 * result + (sinceEstablish != null ? sinceEstablish.hashCode() : 0);
        result = 31 * result + (yearRate != null ? yearRate.hashCode() : 0);
        return result;
    }
}
