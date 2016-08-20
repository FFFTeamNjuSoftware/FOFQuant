package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Daniel on 2016/8/20.
 */
@Entity
@Table(name = "company_info", schema = "fofquant", catalog = "")
public class CompanyInfoEntity {
    private String companyId;
    private String companyName;
    private Double scale;
    private Integer fundNum;
    private String establishDate;
    private String managerName;

    @Id
    @Column(name = "company_id", nullable = false, length = 255)
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Basic
    @Column(name = "company_name", nullable = true, length = 255)
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    @Column(name = "fund_num", nullable = true)
    public Integer getFundNum() {
        return fundNum;
    }

    public void setFundNum(Integer fundNum) {
        this.fundNum = fundNum;
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
    @Column(name = "manager_name", nullable = true, length = 255)
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyInfoEntity that = (CompanyInfoEntity) o;

        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null)
            return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null)
            return false;
        if (scale != null ? !scale.equals(that.scale) : that.scale != null) return false;
        if (fundNum != null ? !fundNum.equals(that.fundNum) : that.fundNum != null) return false;
        if (establishDate != null ? !establishDate.equals(that.establishDate) : that.establishDate != null)
            return false;
        if (managerName != null ? !managerName.equals(that.managerName) : that.managerName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = companyId != null ? companyId.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (fundNum != null ? fundNum.hashCode() : 0);
        result = 31 * result + (establishDate != null ? establishDate.hashCode() : 0);
        result = 31 * result + (managerName != null ? managerName.hashCode() : 0);
        return result;
    }
}
