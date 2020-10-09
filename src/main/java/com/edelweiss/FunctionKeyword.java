package com.edelweiss;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ListIterator;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.edelweiss.model.LoginModel;
import com.edelweiss.model.MasterTestModel;
import com.edelweiss.model.TestDataModel;

import com.edelweiss.orderdetailpages.LoginPage;
import com.edelweiss.orderdetailpages.OrderAction;

import com.edelweiss.util.ApacheCode;
import com.edelweiss.util.BrowserLaunch;
import com.edelweiss.util.CsvReaderCode;

import com.edelweiss.util.ExtendReporter;
import com.edelweiss.util.FolderStructure;
import com.edelweiss.util.HelperCode;

public class FunctionKeyword {

	public static String keyWord = "no Keyword in FunctionKeyWord";
	LoginPage login;
	WebDriver driver;
	OrderAction orderActioObj;
	HelperCode helperObject;
	TestDataModel testDataObject;
	MasterTestModel masterTestmodel;
	ExtendReporter reporter;
	private String step;

	
	public static ApacheCode apacheCodeObj;

	@BeforeSuite
	public void beforeSuite() throws IOException {}

	@BeforeTest
	public void executionBefore() throws IOException {

		Reporter.log("Execution Before ", true);
		BrowserLaunch browserLunch = new BrowserLaunch();
		driver = browserLunch.browserLaunch("Normal");
		login = new LoginPage(driver);
		orderActioObj = new OrderAction(driver);
		testDataObject = new TestDataModel();
		helperObject = new HelperCode();
		reporter = new ExtendReporter();

		apacheCodeObj = new ApacheCode(MyTestLauncher.folderPath[0]);

		

	}

	@BeforeMethod
	public void beforeTest() {
		Reporter.log("Before Test case...", true);
		Reporter.log("BeforeMethod", true);
	}

	@Parameters({ "Reference", "UserId", "Pwd", "Yob", "StartNo", "EndNo", "Module", "Execute" })
	@Test
	public void executionWithKeyword(String referenceNo, String userId, String pwd, String yob, String startNo,
			String endNo, String module, String execute) throws InterruptedException, IOException {
		if (execute.equalsIgnoreCase("Yes")) {
			CsvReaderCode code = new CsvReaderCode();
			Reporter.log("<==================== Function KeyWord : executionWithKeyword ================>", true);
			Reporter.log("Before Iterator " + referenceNo);

			Reporter.log("After Iterator", true);

			LoginModel loginModelObj = new LoginModel(referenceNo, userId, pwd, yob, startNo, endNo, module, execute);
			Reporter.log("Login Data ====> " + loginModelObj.toString(), true);

			Reporter.log("KeyWord before process  ====> " + module, true);

			ListIterator<String> csvMasterTestModelIterator = code.masterTestDataProvider(module);
			int count = 0;
			Reporter.log(
					"Check Point ===================================================================================================================@@@@>>> "
							+ loginModelObj.toString(),
					true);
			while (csvMasterTestModelIterator.hasNext()) {
				step = csvMasterTestModelIterator.next();
				System.out.println("count : " + count++);

				Reporter.log("Step ===> " + step, true);

				switch (KeywordStringProcess(step)) {

				case "login":
					LoginPage loginPageObj = new LoginPage(driver);
					Reporter.log("Login functionality", true);
					loginPageObj.loginExecution("normal", loginModelObj);
					break;

				case "orderdetail":
					OrderAction orderActionObj = new OrderAction(driver);
					Reporter.log("Order detail functionality", true);
					orderActionObj.orderActionStart(loginModelObj);
					break;

				case "logout":
					terminateExecution(module, driver);
					break;

				}

			}

		}

	}

	public String KeywordStringProcess(String keywordString) {
		String keywordInLowerCase = keywordString.toLowerCase();
		Reporter.log("keyword =====> " + keywordInLowerCase.trim(), true);
		if (keywordInLowerCase.contains(" "))
			keywordInLowerCase = keywordInLowerCase.replace(" ", "");
		return keywordInLowerCase.trim();
	}

	public void terminateExecution(String module, WebDriver driver) throws InterruptedException, IOException {
		Reporter.log("terminateExecution", true);
		reporter.captureScreen(driver, MyTestLauncher.folderPath[1], "LastScreenshot", 1);
		if (driver != null) {
			if (!module.equalsIgnoreCase("orderdetail")) {
				
				reporter.reporter(driver, module, MyTestLauncher.folderPath);
				helperObject.outputProcessor(driver, "newOrder", 0, "Terminate", testDataObject, 0);
			}
			login.logout(driver);

			Reporter.log("Execution Terminate.... :)", true);
		} else {

		}
	}

	@AfterMethod
	public void testAfter() {
		Reporter.log("End test case execution", true);
	}

	@AfterTest
	public void endExecution() throws IOException {
		
		driver.close();

	}

	@AfterSuite
	public void afterSuite() throws IOException {

		Reporter.log("Folder Path ====> " + MyTestLauncher.folderPath[0], true);
		apacheCodeObj.outputExcelFileClose(MyTestLauncher.folderPath[0]);
		
	}

}
