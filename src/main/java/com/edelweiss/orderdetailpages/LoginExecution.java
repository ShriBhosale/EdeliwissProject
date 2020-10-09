package com.edelweiss.orderdetailpages;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.MyTestLauncher;
import com.edelweiss.model.LatestLoginModel;
import com.edelweiss.model.LoginTestModel;
import com.edelweiss.util.CsvReaderCode;
import com.edelweiss.util.ExtendReporter;
import com.edelweiss.util.SeleniumCoder;

public class LoginExecution extends SeleniumCoder {

	LoginPage loginPage;
	WebDriver driver;
	LoginTestModel loginTestModel = null;
	ExtendReporter report;
	
	
	public static WebDriver loginWebDriver;

	public LoginExecution(WebDriver driver) {
		super(driver);
		this.driver = driver;
		loginPage = new LoginPage(driver);
		report = new ExtendReporter();
		
	}

	public boolean loginExecution(String scenario, LatestLoginModel loginModelObject)
			throws InterruptedException, IOException {
		boolean skipScenario = false;
		Map<Boolean, WebDriver> loginMap = new HashMap<>(); //
		//driver = browserLaunch(scenario);

		if (!loginModelObject.getModule().equalsIgnoreCase("login")) {
			try {
				loginWebDriver = loginPage.loginCodeExecution(scenario, loginModelObject);
				if (!loginPage.popupFlag) {
					WebElement placeOrderLink = fluentWaitCodeXpath("//a[text()='Place Order']", 10, "Place order tab");
					if (placeOrderLink == null)
						skipScenario = true;
				}
			} catch (TimeoutException e) {
				report.abnormalErrorHandling(driver, elementNameError, loginModelObject.getModule());
				skipScenario = true;
			} catch (ElementNotInteractableException e) {
				report.abnormalErrorHandling(driver, elementNameError, loginModelObject.getModule());
				skipScenario = true;
			}
		} else {
			loginRegressionExecution(loginModelObject);
		}
		loginMap.put(skipScenario, loginWebDriver);

		return skipScenario;
	}

	public void loginRegressionExecution(LatestLoginModel loginModelObject) throws IOException, InterruptedException {

		ExtendReporter extend = new ExtendReporter(MyTestLauncher.reportFolderPath[1], "LoginRegression", 0);
		CsvReaderCode csvReader = new CsvReaderCode();
		Iterator<LoginTestModel> csvLoginTestIterator = csvReader.loginTestDataProvider();
		while (csvLoginTestIterator.hasNext()) {
			try {
				loginTestModel = csvLoginTestIterator.next();
				Reporter.log(loginTestModel.toString(), true);
				loginPage.loginCodeExecution(driver, loginTestModel, extend);
			} catch (NullPointerException e) {
				extend.loginReport(driver, extend, loginTestModel, "Error", elementNameError);
				loginPage.pageRefresh();
				continue;
			} catch (NoSuchElementException e1) {
				extend.loginReport(driver, extend, loginTestModel, "Error", elementNameError);
				loginPage.pageRefresh();
				continue;
			} catch (ElementNotInteractableException e1) {
				extend.loginReport(driver, extend, loginTestModel, "Error", elementNameError);
				loginPage.pageRefresh();
				continue;
			}
		}
		driver.close();
		Reporter.log("Driver close", true);
		extend.logFlush();

	}

	public void loginStepExecution(String scenario, LatestLoginModel loginModelObject)
			throws InterruptedException, IOException {
		try {
			loginPage.loginCodeExecution(scenario, loginModelObject);
		} catch (NullPointerException e) {

			loginPage.pageRefresh();
		}
	}
}
