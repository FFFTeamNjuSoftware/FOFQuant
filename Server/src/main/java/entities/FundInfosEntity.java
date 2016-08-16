package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/16. 14 prameters
 */
@Entity
@Table(name = "fund_infos", schema = "fofquant", catalog = "")
public class FundInfosEntity {
    private String code;
    private String simpleName;
    private String fullName;
    private String fundType;
    private String establishDate;
    private Double establishScale;
    private Double scale;
    private Double netValue;
    private Double manageFee;
    private String compareBase;
    private String investTarget;
    private String investStrategy;
    private String riskFeature;
    private String investType;

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "simple_name", nullable = true, length = 255)
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 255)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "fund_type", nullable = true, length = 255)
    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    @Basic
    @Column(name = "establish_date", nullable = true, length = 255)
    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    @Basic
    @Column(name = "establish_scale", nullable = true, precision = 0)
    public Double getEstablishScale() {
        return establishScale;
    }

    public void setEstablishScale(Double establishScale) {
        this.establishScale = establishScale;
    }

    @Basic
    @Column(name = "scale", nullable = true, precision = 0)
    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    @Basic
    @Column(name = "net_value", nullable = true, precision = 0)
    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    @Basic
    @Column(name = "manage_fee", nullable = true, precision = 0)
    public Double getManageFee() {
        return manageFee;
    }

    public void setManageFee(Double manageFee) {
        this.manageFee = manageFee;
    }

    @Basic
    @Column(name = "compare_base", nullable = true, length = 255)
    public String getCompareBase() {
        return compareBase;
    }

    public void setCompareBase(String compareBase) {
        this.compareBase = compareBase;
    }

    @Basic
    @Column(name = "invest_target", nullable = true, length = -1)
    public String getInvestTarget() {
        return investTarget;
    }

    public void setInvestTarget(String investTarget) {
        this.investTarget = investTarget;
    }

    @Basic
    @Column(name = "invest_strategy", nullable = true, length = -1)
    public String getInvestStrategy() {
        return investStrategy;
    }

    public void setInvestStrategy(String investStrategy) {
        this.investStrategy = investStrategy;
    }

    @Basic
    @Column(name = "risk_feature", nullable = true, length = -1)
    public String getRiskFeature() {
        return riskFeature;
    }

    public void setRiskFeature(String riskFeature) {
        this.riskFeature = riskFeature;
    }

    @Basic
    @Column(name = "invest_type", nullable = true, length = 255)
    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundInfosEntity that = (FundInfosEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (simpleName != null ? !simpleName.equals(that.simpleName) : that.simpleName != null)
            return false;
        if (fullName != null ? !fullName.equals(that.fullName) : that.fullName != null)
            return false;
        if (fundType != null ? !fundType.equals(that.fundType) : that.fundType != null)
            return false;
        if (establishDate != null ? !establishDate.equals(that.establishDate) : that.establishDate != null)
            return false;
        if (establishScale != null ? !establishScale.equals(that.establishScale) : that.establishScale != null)
            return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (netValue != null ? !netValue.equals(that.netValue) : that.netValue != null)
            return false;
        if (manageFee != null ? !manageFee.equals(that.manageFee) : that.manageFee != null)
            return false;
        if (compareBase != null ? !compareBase.equals(that.compareBase) : that.compareBase != null)
            return false;
        if (investTarget != null ? !investTarget.equals(that.investTarget) : that.investTarget != null)
            return false;
        if (investStrategy != null ? !investStrategy.equals(that.investStrategy) : that.investStrategy != null)
            return false;
        if (riskFeature != null ? !riskFeature.equals(that.riskFeature) : that.riskFeature != null)
            return false;
        if (investType != null ? !investType.equals(that.investType) : that.investType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (simpleName != null ? simpleName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (fundType != null ? fundType.hashCode() : 0);
        result = 31 * result + (establishDate != null ? establishDate.hashCode() : 0);
        result = 31 * result + (establishScale != null ? establishScale.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (netValue != null ? netValue.hashCode() : 0);
        result = 31 * result + (manageFee != null ? manageFee.hashCode() : 0);
        result = 31 * result + (compareBase != null ? compareBase.hashCode() : 0);
        result = 31 * result + (investTarget != null ? investTarget.hashCode() : 0);
        result = 31 * result + (investStrategy != null ? investStrategy.hashCode() : 0);
        result = 31 * result + (riskFeature != null ? riskFeature.hashCode() : 0);
        result = 31 * result + (investType != null ? investType.hashCode() : 0);
        return result;
    }
}
