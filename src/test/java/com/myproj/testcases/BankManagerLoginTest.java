package com.myproj.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.myproj.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException {
		log.debug("Inside Login Test");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("bmlBtn"))));
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))),"Login is not Successful");
		log.debug("Login Successfully Executed");
		Reporter.log("Login Successfully Executed");
		Reporter.log("<a target=\"_blank\" href=/home/sanket-laptop/Desktop/List.png>Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href=/home/sanket-laptop/Desktop/List.png/s><img src=\"\"></img></a>");

	}

}
