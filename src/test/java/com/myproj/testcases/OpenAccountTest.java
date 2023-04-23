package com.myproj.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;
import com.myproj.utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	

	@Test(dataProviderClass=TestUtil.class, dataProvider = "dp")
	public static void openAccountTest(String Customer, String Currency)
			throws InterruptedException {
		click("openaccount_CSS");
		select("customer_CSS",Customer);
		select("openaccount_CSS",Customer);
		click("process_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains("Account created successfully with account Number"));
		alert.accept();
		
		
	}
}
