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

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 2)
	//public static void addCustomer(Hashtable<String,String>data)
	public static void addCustomer(String firstName, String lastName, String postCode, String alerttext, String Runmode)
			throws InterruptedException {
		if (!Runmode.equals("Y"))
		//if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping The Test case as the runmode for data for data set is NO");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
	click("addCustBtn_CSS");
		log.debug("Clicked On Add Customer Button !!!");
		type("firstname_CSS", firstName);
		//type("firstname_CSS", data.get("firstName"));
		type("lastname_CSS", lastName);
		//type("lastname_CSS", data.get("lastName"));
		type("postcode_CSS", postCode);
		//type("postcode_CSS", data.get("postCode"));
		log.debug("Added Customer Data !!!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addbtn_CSS"))));
		click("addbtn_CSS");
		log.debug("Customer Added Successfully!!!");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alerttext));
		//Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
	}

}

