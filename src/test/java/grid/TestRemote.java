package grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestRemote {

	public static void main(String[] args) throws MalformedURLException {
		ChromeOptions opt = new ChromeOptions();

		WebDriver driver = new RemoteWebDriver(new URL(""), opt);
		// TODO Auto-generated method stub

	}

}
