package com.myproj.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;
import com.myproj.utilities.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider="dp",priority=2)
	public static void addCustomer(String firstName, String lastName, String postCode, String alerttext)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
		click("addCustBtn_CSS");
		log.debug("Clicked On Add Customer Button !!!");
		type("firstname_CSS", firstName);
		type("lastname_CSS", lastName);
		type("postcode_CSS", postCode);
		log.debug("Added Customer Data !!!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addbtn_CSS"))));
		click("addbtn_CSS");
		log.debug("Customer Added Successfully!!!");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alerttext));
		alert.accept();
	}

}
