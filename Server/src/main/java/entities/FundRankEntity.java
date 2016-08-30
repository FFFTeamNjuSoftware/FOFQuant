package entities;

import javax.persistence.*;

/**
 * Created by Daniel on 2016/8/30.
 */
@Entity
@Table(name = "fund_rank", schema = "fofquant", catalog = "")
public class FundRankEntity {
    private String code;
    private Double mrar;
    private Double grade;
    private Double rank;

    @Id
    @Column(name = "code", nullable = false, length = 255)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "mrar", nullable = true, precision = 0)
    public Double getMrar() {
        return mrar;
    }

    public void setMrar(Double mrar) {
        this.mrar = mrar;
    }

    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "rank", nullable = true, precision = 0)
    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundRankEntity that = (FundRankEntity) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (mrar != null ? !mrar.equals(that.mrar) : that.mrar != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (rank != null ? !rank.equals(that.rank) : that.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (mrar != null ? mrar.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }
}
