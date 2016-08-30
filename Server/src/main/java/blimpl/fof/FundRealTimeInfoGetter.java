package blimpl.fof;

import beans.FundRealTimeInfo;
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
    private final String content_pattern = "\\{.*\\}";
    private String code;

    public FundRealTimeInfoGetter() {
    }

    public FundRealTimeInfo getFundRealTimeInfo(String code) {
        HttpTool httpTool = new HttpTool();
        String url_str = String.format(url_pattern, code, Math.random());
        try {
            String content = httpTool.getURLContent(url_str);
            Pattern pattern = Pattern.compile(content_pattern);
            Matcher matcher = pattern.matcher(content);
            if (matcher.find())
                return new Gson().fromJson(matcher.group(), FundRealTimeInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

