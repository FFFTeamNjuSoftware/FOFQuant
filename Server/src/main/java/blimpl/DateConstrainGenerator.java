package blimpl;

import util.DateConstraint;
import util.UnitType;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2016/8/17.
 */
public class DateConstrainGenerator {
    static DateConstraint dayConstraint, weekConstraint, monthConstraint, quarterConstraint,
            yearConstraint;
    static final int[] field = {Calendar.YEAR, Calendar.MONTH, Calendar.WEEK_OF_YEAR};

    static Map<UnitType, DateConstraint> map;

    static {
        dayConstraint = (e1, e2) -> true;
        yearConstraint = (d1, d2) -> isNotSameField(d1, d2, field[0]);
        monthConstraint = (d1, d2) -> isNotSameField(d1, d2, field[0], field[1]);
        weekConstraint = (d1, d2) -> isNotSameField(d1, d2, field[0], field[2]);
        quarterConstraint = (d1, d2) -> {
            if (d1.get(Calendar.YEAR) != d2.get(Calendar.YEAR)) return true;
            return d1.get(Calendar.MONTH) / 3 != d2.get(Calendar.MONTH) / 3;
        };
        map = new HashMap<>();
        map.put(UnitType.DAY, dayConstraint);
        map.put(UnitType.WEEK, weekConstraint);
        map.put(UnitType.MONTH, monthConstraint);
        map.put(UnitType.QUARTER, quarterConstraint);
        map.put(UnitType.YEAR, yearConstraint);

    }

    static boolean isNotSameField(Calendar d1, Calendar d2, int... fields) {
        for (int field : fields) {
            if (d1.get(field) != d2.get(field))
                return true;
        }
        return false;
    }

    public static DateConstraint getDateConstrainByUnitType(UnitType type) {
        return map.get(type);
    }
}
