package blimpl;

/**
 * Created by st0001 on 2016/8/30.
 */

/**
 * 从返回的url结果提取出json部分
 */
public interface AnalyseFundJSResult {
    String getJSONContent(String originContent);
}
