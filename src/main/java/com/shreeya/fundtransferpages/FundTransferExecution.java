package com.shreeya.fundtransferpages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.shreeya.model.LoginModel;
import com.shreeya.orderdetailpages.LoginPage;
import com.shreeya.util.SeleniumCoder;

public class FundTransferExecution extends SeleniumCoder{

	WebDriver driver;
	private WebElement fundTransferTab;
	
	public FundTransferExecution(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public void fundTransferExecute(LoginModel loginModel) throws InterruptedException, IOException {
		
		
		fundTransferTab=fluentWaitCodeXpath(driver, "//a[text()='Fund Transfer']");
		clickElement(fundTransferTab, "Fund Transfer Tab");
		
	}
}
