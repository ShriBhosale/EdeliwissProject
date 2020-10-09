package com.edelweiss.seeholdingspages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.model.LoginModel;
import com.edelweiss.orderdetailpages.LoginPage;
import com.edelweiss.util.SeleniumCoder;

public class SeeHoldingsExecution extends SeleniumCoder {

	WebDriver driver;
	private WebElement seeHoldingsTab;

	public SeeHoldingsExecution(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void seeHoldingsExecute() throws InterruptedException, IOException {

		seeHoldingsTab = fluentWaitCodeXpath(driver, "//a[text()='Fund Transfer']//following::a[text()='See Holdings']", "See Holdings Tab");
		clickElement(seeHoldingsTab, "See Holdings Tab");

	}
}
