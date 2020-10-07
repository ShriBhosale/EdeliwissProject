package com.shreeya.experiment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.shreeya.model.LatestLoginModel;
import com.shreeya.model.LoginModel;
import com.shreeya.orderdetailpages.LoginPage;
import com.shreeya.util.BrowserLaunch;

public class Execution extends WebCode {

	static Report report;

	public Execution(WebDriver driver) {
		super(driver);
		
	}

	static int count = 0;

	public static void main(String[] args) throws InterruptedException {
		BrowserLaunch launch=new BrowserLaunch();
		WebDriver driver=launch.browserLaunch("normal");
		Reporter.log("Driver ======>  "+driver,true);
		Execution execute=new Execution(driver);
		ModulePage page=new ModulePage(driver);
		LoginPage login=new LoginPage(driver);
		//Report report=new Report("SDfsdf"+driver);
		
		LatestLoginModel loginModelObject=new LatestLoginModel(1, "60003800", "abc123", "2000", "OrderDetail", "Equity");
		login.loginCodeExecution("Abc", loginModelObject);
		String[] moduleArray = { /*
									 * "//a[text()='Fund Transfer']//preceding::a[2]-MyPosition",
									 * "//a[text()='Fund Transfer']//preceding::a[1]-SeeMagin",
									 */
				"//a[text()='Fund Transfer']-Fund Transfer"/*
															 * ,
															 * "//a[text()='Fund Transfer']//following::a[1]-See Holding"
															 */};
		
		for(String module:moduleArray) {
			String [] moduleNameArray=module.split("-");
			report.createTest(moduleNameArray[1]);
			try {
			//page.executeModule(moduleNameArray[0],moduleNameArray[1]);
			report.printLog("Element Name : "+moduleNameArray[1]+" Tab",true);
			
			}catch(NullPointerException e) {
				report.printLog("Element Name : "+ExceptionHandling.elementName,false);
				report.printLog("Exception name : "+ExceptionHandling.exceptionString,false);
			}
		}
		
		report.logFlush();
	}

}
