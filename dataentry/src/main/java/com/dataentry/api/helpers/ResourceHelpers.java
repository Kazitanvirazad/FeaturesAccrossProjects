package com.dataentry.api.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ResourceHelpers {
	public String getResource(String key, String resourcepath) {
		String result = null;
		try {
			Properties properties = new Properties();
			ClassLoader classLoader = ResourceHelpers.class.getClassLoader();
			URL resource = classLoader.getResource(resourcepath);
			File file = new File(resource.getFile());
			FileReader reader = new FileReader(file);
			properties.load(reader);
			if (reader != null) {
				result = properties.getProperty(key);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result != null ? result : "";
	}

}
