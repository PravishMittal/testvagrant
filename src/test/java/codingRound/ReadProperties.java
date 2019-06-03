package codingRound;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	Properties prop = new Properties();
	InputStream input = null;

	public ReadProperties() throws IOException {
		input = new FileInputStream("src/test/resources/config.properties");
		prop.load(input);

	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
