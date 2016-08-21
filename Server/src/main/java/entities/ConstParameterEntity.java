package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/21.
 */
@Entity
@Table(name = "const_parameter", schema = "fofquant", catalog = "")
public class ConstParameterEntity {
    private int id;
    private Double noRiskProfit;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "no_risk_profit", nullable = true, precision = 0)
    public Double getNoRiskProfit() {
        return noRiskProfit;
    }

    public void setNoRiskProfit(Double noRiskProfit) {
        this.noRiskProfit = noRiskProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstParameterEntity that = (ConstParameterEntity) o;

        if (id != that.id) return false;
        if (noRiskProfit != null ? !noRiskProfit.equals(that.noRiskProfit) : that.noRiskProfit != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (noRiskProfit != null ? noRiskProfit.hashCode() : 0);
        return result;
    }
}
