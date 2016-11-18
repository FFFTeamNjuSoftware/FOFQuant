package dataupdate;

import beans.FundRealTimeInfo;
import dataupdate.AnalyseFundJSResult;
import dataupdate.AnalyseFundJSResultImpl;
import com.google.gson.Gson;
import exception.ObjectExistedException;
import exception.ObjectNotFoundException;
import util.HttpTool;

import java.io.IOException;

/**
 * Created by Daniel on 2016/8/30.
 */
public class FundRealTimeInfoGetter {
    private final String url_pattern = "http://fundgz.1234567.com.cn/js/%s.js?rt=%s";
    AnalyseFundJSResult analyseFundJSResult;

    public FundRealTimeInfoGetter() {
        analyseFundJSResult = new AnalyseFundJSResultImpl();
    }

    public FundRealTimeInfo getFundRealTimeInfo(String code) throws ObjectNotFoundException {
        HttpTool httpTool = new HttpTool();
        String url_str = String.format(url_pattern, code, Math.random());
        try {
            String result = analyseFundJSResult.getJSONContent(httpTool.getURLContent(url_str,
                    "utf-8"));
            return new Gson().fromJson(result, FundRealTimeInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ObjectNotFoundException("fundinfo not found" + code);
        }
    }

    public static void main(String []args){
        FundRealTimeInfoGetter fundRealTimeInfoGetter=new FundRealTimeInfoGetter();
        try {
            System.out.println(new Gson().toJson(fundRealTimeInfoGetter.getFundRealTimeInfo
                    ("000001")));
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}

