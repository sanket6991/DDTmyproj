//package grid;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.openqa.selenium.Capabilities;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.annotations.Parameters;
//import org.testng.annotations.Test;
//
//public class TestParellel {
//	Capabilities cap;
//	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();  
//
//public WebDriver getDriver() {
//	
//	return driver.get();
//	
//}
//@Parameters({"browser"})
//@Test
//public void doSearch(String browser) throws MalformedURLException {
//	
//	if(browser.equals("firefox")) {
//		cap = new FirefoxOptions();
//	}
//	
//	if(browser.equals("chrome")) {
//		cap = new ChromeOptions();
//	}
//	driver.set(new RemoteWebDriver(new URL("http://10.0.2.15:4444"), cap));
//	getDriver().
//}
//
//}
