package com.myproj.testcases;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getData")
	public static void addCustomer(String firstName, String lastName, String postCode) {
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		log.debug("Clicked On Add Customer Button !!!");
		driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode"))).sendKeys(postCode);
		log.debug("Added Customer Data !!!");
		driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
		log.debug("Customer Added Successfully!!!");


	}

	@DataProvider
	public Object[][] getData() {

		int rows = xlsx.getRowCount("Sheet1");
		int cols = xlsx.getColumnCount("Sheet1");

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = xlsx.getCellData(browser, cols, rowNum);

			}
		}
		return data;
	}
}