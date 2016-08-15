package config;

import bl.BaseInfoLogic;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
 * Created by Daniel on 2016/8/15.
 */
public class RMIConfig {
    private RMIConfig() {

    }

    private static RMIConfig instance;

    private String ipAddress;
    private int port;

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public static RMIConfig getInstance() throws DocumentException {
        if (instance == null) {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new File(RMIConfig.class.getResource("/")
                    .getPath() + "RMIConfig.xml"));
            Element root = document.getRootElement();
            instance = new RMIConfig();
            instance.ipAddress = root.element("ip").getStringValue();
            instance.port = new Integer(root.element("port").getStringValue());
        }
        return instance;
    }
//
    public static void main(String[] args) throws Exception {
        RMIConfig config = RMIConfig.getInstance();
        System.out.println(config.getIpAddress()+":"+config.getPort());
        System.out.println(BaseInfoLogic.class.getSimpleName());
    }

}
