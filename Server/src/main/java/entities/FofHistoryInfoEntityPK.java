package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Daniel on 2016/9/2.
 */
public class FofHistoryInfoEntityPK implements Serializable {
    private String fofId;
    private String date;

    @Column(name = "fof_id", nullable = false, length = 255)
    @Id
    public String getFofId() {
        return fofId;
    }

    public void setFofId(String fofId) {
        this.fofId = fofId;
    }

    @Column(name = "date", nullable = false, length = 255)
    @Id
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FofHistoryInfoEntityPK that = (FofHistoryInfoEntityPK) o;

        if (fofId != null ? !fofId.equals(that.fofId) : that.fofId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fofId != null ? fofId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
