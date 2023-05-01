package com.myproj.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.myproj.base.TestBase;
import com.myproj.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListerners extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = reo.startTest(result.getName().toUpperCase());
		// TODO Auto-generated method stub
		if(!TestUtil.isTestRunnable(result.getName(), xlsx)) {
			throw new SkipException("Skipping the test"+result.getName().toUpperCase()+" as runmode is NO");
		}
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		test.log(LogStatus.PASS, result.getName().toUpperCase() + "PASS");
		reo.endTest(test);
		reo.flush();
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + "PASS");

		System.setProperty("org.uncommon.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + "Failed with exception : " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("Login Successfully Executed");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + ">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
				+ " height=200 width=200></img></a>");

		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+"Skipped the Test as Run mode is No");
		reo.endTest(test);
		reo.flush();	
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}