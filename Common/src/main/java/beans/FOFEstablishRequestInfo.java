package beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 2016/9/3.
 */
public class FOFEstablishRequestInfo implements Serializable {
    public String fofCode;
    public String fofName;
    /**
     * 仓位信息
     */
    public List<PositionInfo> positionInfos;
    /**
     * 总投资额
     */
    public double totalInvestValue;
    /**
     * 现金比率
     */
    public double cashValueRatio;
}
