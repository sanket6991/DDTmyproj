package com.myproj.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;
import com.myproj.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	//public static void openAccountTest(String Customer, String Currency) throws InterruptedException
	public static void openAccountTest(Hashtable<String,String>data) throws InterruptedException{
		if(!TestUtil.isTestRunnable("openAccountTest", xlsx))
			{
			throw new SkipException("Skipping the test"+"openAccountTest"+" as runmode is NO");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("openaccount_CSS"))));
		click("openaccount_CSS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("customer_CSS"))));
		select("customer_CSS", data.get("Customer"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("currency_CSS"))));
		select("currency_CSS", data.get("Currency"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("process_CSS"))));
		click("process_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number"));
		alert.accept();

	}
}
