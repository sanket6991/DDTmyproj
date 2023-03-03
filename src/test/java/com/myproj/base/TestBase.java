package com.myproj.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriverWait wait;
	public static String browser;
	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() throws InterruptedException {
		
		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				Config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
				log.debug("OR file loaded !!!");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
				
				browser = System.getenv("browser");
			}else{
				
				browser = Config.getProperty("browser");
				
			}
			
			Config.setProperty("browser", browser);
			
			
			

			if (Config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				//WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
	 else if (Config.getProperty("browser").equals("chrome")) {

			/*System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
		*/	
		//	WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("Chrome Launched !!!");
		} else if (Config.getProperty("browser").equals("ie")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.get(Config.getProperty("testsiteurl"));
		log.debug("Navigated to : " + Config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
			//	TimeUnit.SECONDS);
		//wait = new WebDriverWait(driver, 5);//
		Thread.sleep(5000);
	}
	}


	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();

		}
	}
}
