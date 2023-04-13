package com.myproj.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getData")
	public static void addCustomer(String firstName, String lastName, String postCode, String alerttext)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addCustBtn"))));
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		log.debug("Clicked On Add Customer Button !!!");
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
		log.debug("Added Customer Data !!!");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("addbtn"))));
		driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
		log.debug("Customer Added Successfully!!!");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alerttext));
		alert.accept();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data1 = xlsx.getSheetData("Sheet1");

		return data1;
	}

}
