package redDigital.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Miguel Peceros
 *
 */
public class ReadConfiguration {

	Properties p = new Properties();
 
	public Properties getConfigurationProperties() throws IOException{
		InputStream stream = new FileInputStream(new File
				(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\application.properties"));
		p.load(stream);
		return p;
	}
	
}