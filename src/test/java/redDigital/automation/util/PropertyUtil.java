package redDigital.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static InputStream resource = null;

    public static InputStream getSingleInstance() {
        if(resource == null) {
            return Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(Constants.PATH_PROPERTY);
        }
        return resource;
    }

    public static String getConfiguration(String property) {
        Properties properties = new Properties();
        String resulPath = null;
        {
            InputStream resource = getSingleInstance();

            try {
                properties.load(resource);
                resulPath = properties.getProperty(property);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        return resulPath;

    }


//    public static class ReadConfiguration {
//
//        Properties p = new Properties();
//
//        public Properties getConfigurationProperties() throws IOException{
//            InputStream stream = new FileInputStream(new File
//                    (System.getProperty("user.dir")+"\\src\\test\\resources\\config\\application.properties"));
//            p.load(stream);
//            return p;
//        }
//
//    }


}