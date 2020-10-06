package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import io.restassured.RestAssured;
import io.restassured.http.Header;

public class BasePage {

	public String propertiesReader(String propertiesKey)  {
		String path = "C:\\Users\\Jyoti\\eclipse-workspace\\spring2020\\API_Test\\header.properties";
		Properties properties = new Properties();
		FileInputStream fileInputStream=null;
		try {
			fileInputStream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return properties.getProperty(propertiesKey);
	}

	public String header_date(String Url, String headerKey) {
		// with map key and value
		String dates = RestAssured.get(Url).getHeader("Date");

		Iterable<Header> list = RestAssured.get(Url).getHeaders();
		Map<String, String> map = new LinkedHashMap<String, String>();

		for (Header allheaders : list) {
			map.put(allheaders.getName(), allheaders.getValue());//getting key and value
		}
		for (Entry<String, String> mapPair : map.entrySet()) {//for each  with keyset getting vale
			System.out.println(mapPair);
		}
		for (Entry<String, String> mapPair : map.entrySet()) {
			if (mapPair.getKey().equals(headerKey)) {
				return mapPair.getValue();
			}
		}
		return null;// System.out.println(mapPair);
	}

}
