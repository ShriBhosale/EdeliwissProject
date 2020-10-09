package com.edelweiss.seemarginpages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.model.LoginModel;
import com.edelweiss.orderdetailpages.LoginPage;
import com.edelweiss.util.SeleniumCoder;

public class SeeMarginExecution extends SeleniumCoder {
	WebDriver driver;
	private WebElement seeMarginTab;
	
	public SeeMarginExecution(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void seeMarginExecute() throws InterruptedException, IOException {
		
		  seeMarginTab=fluentWaitCodeXpath(driver, "//a[text()='See Margin']", "See Margin Tab");
		  clickElement(seeMarginTab, "See Margin Tab");
		  //Thread.sleep(50000);
		
	}
}
