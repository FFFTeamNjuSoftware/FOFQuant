package beans;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/9/3.
 */
public class PositionInfo implements Serializable {
    public String fundCode;
    public double weight;

    public static PositionInfo getPositonInfo(String fundCode, double weight) {
        PositionInfo positionInfo = new PositionInfo();
        positionInfo.fundCode = fundCode;
        positionInfo.weight = weight;
        return positionInfo;
    }
}
