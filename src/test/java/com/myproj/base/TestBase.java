package com.myproj.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				Config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {
				driver = new ChromeDriver();
			} else if (Config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (Config.getProperty("browser").equals("Edge")) {
				driver = new EdgeDriver();
			}
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();

		}
	}
}
