package beans;

import java.util.List;
import java.util.Map;

/**
 * Created by Seven on 16/9/5.
 */
public class RiskyParityDeploy {
     /*
     该配置组合所包含的基金在每个持有期所对应的权重
      */
     public List<Map<String,Double>> proportion;

     /*
     配置组合对应的日收益率
      */
     public List<Double> profits;

     /*
     该配置组合所包含的基金个数
      */
     public int fundNum;

     /*
     该组合在不同的持有期内的收益率
      */
     public double[] rpturn;

     /*
     该组合的窗口期
      */
     public int window;

     /*
     该组合持有期
      */
     public int hold;

     /*
     杠杆
      */
     public int level;

     /*
     该组合夏普比率
      */
     public double sharpe;
}
