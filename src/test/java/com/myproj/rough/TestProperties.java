package com.myproj.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {

		Properties Config = new Properties();
		Properties OR = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
		Config.load(fis);
		System.out.println(Config.getProperty("browser"));
		fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/properties/OR.properties");
		OR.load(fis);
		
		System.out.println(OR.getProperty("bmlBtn"));
	}
}
