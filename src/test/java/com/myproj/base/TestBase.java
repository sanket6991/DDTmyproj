package com.myproj.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.myproj.utilities.ExcelReader;
import com.myproj.utilities.ExtentManager;
import com.myproj.utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static WebElement dropdown;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static String browser;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader xlsx = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");

	// "/home/sanket-laptop/Documents/testdata.xlsx");
	public static ExtentReports reo = ExtentManager.getInstance();
	public static ExtentTest test;

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void setUp() throws InterruptedException {
		BasicConfigurator.configure();
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "/src/test/resources/properties/log4j.properties");

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

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = Config.getProperty("browser");

			}

			Config.setProperty("browser", browser);

			if (Config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions ops1 = new FirefoxOptions();
				ops1.addArguments("--remote-allow-origins=*");
				ops1.addArguments("--disable");
				DesiredCapabilities cp1 = new DesiredCapabilities();
				cp1.setCapability(ChromeOptions.CAPABILITY, ops1);
				ops1.merge(cp1);
				driver = new FirefoxDriver(ops1);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
				log.debug("Firefox Launched !!!");
			} else if (Config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions ops = new ChromeOptions();
				ops.addArguments("--remote-allow-origins=*");
				//ops.setHeadless(true);
				ops.addArguments("--disable");
				DesiredCapabilities cp = new DesiredCapabilities();
				cp.setCapability(ChromeOptions.CAPABILITY, ops);
				ops.merge(cp);
				driver = new ChromeDriver(ops);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				log.debug("Chrome Launched !!!");
			} else if (Config.getProperty("browser").equals("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

				log.debug("Edge Launched !!!");

			}

			driver.get(Config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			// wait = new WebDriverWait(driver, 5);//
			// Thread.sleep(5000);
		}
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {

		}
		return false;

	}

	public static void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);
	}


	public static void select(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " Selected value as " + value);

		}

	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			Reporter.log("<br>" + "Verification failuer : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" herf=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ "height=200 width=200></img</a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			Reporter.log("<target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=");
			test.log(LogStatus.FAIL, "Verification Failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			reo.endTest(test);
			reo.flush();
		}

	}
}

/*
 * @AfterSuite public void tearDown() {
 * 
 * if (driver != null) { driver.quit();
 * 
 * } log.debug("Test Execution Completed !!!"); } }
 */
