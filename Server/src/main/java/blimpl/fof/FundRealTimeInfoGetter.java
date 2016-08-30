package blimpl.fof;

import beans.FundRealTimeInfo;
import blimpl.AnalyseFundJSResult;
import blimpl.AnalyseFundJSResultImpl;
import com.google.gson.Gson;
import util.HttpTool;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 2016/8/30.
 */
public class FundRealTimeInfoGetter {
    private final String url_pattern = "http://fundgz.1234567.com.cn/js/%s.js?rt=%s";
    AnalyseFundJSResult analyseFundJSResult;

    public FundRealTimeInfoGetter() {
        analyseFundJSResult = new AnalyseFundJSResultImpl();
    }

    public FundRealTimeInfo getFundRealTimeInfo(String code) {
        HttpTool httpTool = new HttpTool();
        String url_str = String.format(url_pattern, code, Math.random());
        try {
            String result = analyseFundJSResult.getJSONContent(httpTool.getURLContent(url_str));
            return new Gson().fromJson(result, FundRealTimeInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

