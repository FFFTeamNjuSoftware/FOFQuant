package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Daniel on 2016/8/30.
 */
public class HttpTool {
    public String getURLContent(String url_str) throws MalformedURLException, IOException {
        URL url = new URL(url_str);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestProperty("content-type", "text/html");
        conn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn
                .getInputStream(), Charset.forName("utf8")));
        String tem;
        String re = "";
        while ((tem = reader.readLine()) != null)
            re += tem + "\n";
        return re;
    }
}
