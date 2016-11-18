package strategyimpl.fundrank;

import java.util.Comparator;

/**
 * Created by Daniel on 2016/11/15.
 */
public class FundComparator implements Comparator<FundIndexInfo> {
    int sign;

    public FundComparator() {
        sign = 1;
    }

    public FundComparator(int sign) {
        this.sign = sign;
    }

    @Override
    public int compare(FundIndexInfo o1, FundIndexInfo o2) {
        return sign * (o1.value < o2.value ? 1 : (o1.value > o2.value ? -1 : 0));
    }
}
