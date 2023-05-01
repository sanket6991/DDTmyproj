package com.myproj.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.myproj.base.TestBase;

public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "/target/surefire-reports/html/" + screenshotName));
	}

	@DataProvider(name="dp")
	public static Object[][] getData( Method m) {
		String sheetName =m.getName();
		/*
		 * int rows = xlsx.getRowCount(sheetName); int cols =
		 * xlsx.getColumnCount(sheetName);
		 */
		
		Object[][] data = xlsx.getSheetData(sheetName);
//		Object[][] data = new Object[rows-1][1];
//		Hashtable<String,String> table = null;
//		for(int rowNum=2; rowNum<=rows;rowNum++) {
//			table = new Hashtable<String, String>();
//			for(int colNum = 0; colNum<cols ; colNum++) {
//				table.put(xlsx.getCellData(sheetName, colNum, rowNum), xlsx.getCellData(sheetName, colNum, rowNum));
//				data[rowNum-2][colNum]=table;


	return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader xlsx) {
		String sheetName1 = "test_suite";
		int rows = xlsx.getRowCount(sheetName1);

		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = xlsx.getCellData(testName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName))
				;
			{
				String runmode = xlsx.getCellData(sheetName1, "Runmode", rNum);
				if (runmode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;

	}
}
