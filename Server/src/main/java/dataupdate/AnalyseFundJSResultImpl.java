package dataupdate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by st0001 on 2016/8/30.
 */
public class AnalyseFundJSResultImpl implements AnalyseFundJSResult {
    private final String content_pattern = "\\{.*\\}";

    @Override
    public String getJSONContent(String content) {
        Pattern pattern = Pattern.compile(content_pattern);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find())
            return matcher.group();
        return null;
    }
}
