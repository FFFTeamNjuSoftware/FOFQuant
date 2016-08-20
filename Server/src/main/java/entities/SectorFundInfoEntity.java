package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/20.
 */
@Entity
@Table(name = "sector_fund_info", schema = "fofquant", catalog = "")
@IdClass(SectorFundInfoEntityPK.class)
public class SectorFundInfoEntity {
    private String sectorId;
    private String code;

    @Id
    @Column(name = "sector_id", nullable = false, length = 255)
    public String getSectorId() {
        return sectorId;
    }

    public void setSectorId(String sectorId) {
        this.sectorId = sectorId;
    }

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectorFundInfoEntity that = (SectorFundInfoEntity) o;

        if (sectorId != null ? !sectorId.equals(that.sectorId) : that.sectorId != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sectorId != null ? sectorId.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
