package com.edelweiss.orderdetailpages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.model.LatestLoginModel;
import com.edelweiss.model.LoginModel;
import com.edelweiss.model.OrderDetailLoginModel;
import com.edelweiss.model.TestDataModel;
import com.edelweiss.orderdetailpages.LoginPage;
import com.edelweiss.orderdetailpages.NewOrderPage;
import com.edelweiss.util.BrowserLaunch;
import com.edelweiss.util.HelperCode;
import com.edelweiss.util.SeleniumCoder;

public class PartialOrderPage extends SeleniumCoder {
	
	WebDriver driver;
	BrowserLaunch launch;
	LoginPage loginObject;
	LatestLoginModel lastLoginModel;
	private WebElement placeOrderLink;
	private WebElement orderStatusLink;
	
	public PartialOrderPage(WebDriver driver) throws MalformedURLException{
		super(driver);
		this.driver=driver;
		
	}
	
	public void partialOrderExecution(TestDataModel model,int orderNo,OrderDetailLoginModel loginModel) throws InterruptedException, IOException {
		Reporter.log("Partial Order Execution Method",true);
		lastLoginModel=new LatestLoginModel(loginModel.getUserId(),loginModel.getPassword(),loginModel.getYob(),"OrderDetail");
		launch=new BrowserLaunch();
		this.driver=launch.browserLaunch("Partial Order");
		 loginObject=new LoginPage(driver);
		NewOrderPage newObect=new NewOrderPage(driver);
		loginObject.loginExecution("Partial Order",lastLoginModel,driver);
		newObect.newOrderExecution(model, driver, orderNo);
		
	}
	
	public void orderDetail(WebDriver driverBuyOrder,TestDataModel model,int orderNo) throws InterruptedException, IOException {
		Reporter.log("Partial Order : Order Detail ", true);
		HelperCode helperCodeObj=new HelperCode();
		//orderNo++;
		String orderStatus=helperCodeObj.outputProcessor(driver,"Partial Order", orderNo,"Open", model,0);
		if(orderStatus.equalsIgnoreCase("complete")||orderStatus.equalsIgnoreCase("open")) {
			placeOrderLink=fluentWaitCodeXpath(driverBuyOrder,"//a[text()='Place Order']","Place Order link");
			clickElement(placeOrderLink,"Place Order link");
			orderStatusLink=fluentWaitCodeXpath(driverBuyOrder,"//a[text()='Place Order']//following::a[1]","Order status link");
			clickElement(orderStatusLink, "Order status link");
			helperCodeObj.outputProcessor(driverBuyOrder, "Buy Partial Order", orderNo,orderStatus, model,0);
		}
		loginObject.logout(driver);
		driver.close();
	}

}
