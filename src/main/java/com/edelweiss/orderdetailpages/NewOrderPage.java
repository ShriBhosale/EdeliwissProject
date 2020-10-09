package com.edelweiss.orderdetailpages;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.model.TestDataModel;
import com.edelweiss.util.ConfigReader;
import com.edelweiss.util.CsvReaderCode;
import com.edelweiss.util.HelperCode;
import com.edelweiss.util.SeleniumCoder;

public class NewOrderPage extends SeleniumCoder {
	
	WebElement placeOrderTextField;
	WebDriver driver;
	private WebElement buyButton;
	private WebElement noOfSharesTextField;
	private WebElement enterPriceTextField;
	private WebElement cnsRadioButton;
	private WebElement OptionalFieldsLabel;
	private WebElement placeOrderButton;
	private WebElement confirmButton;
	private WebElement nseLink;
	
	OrderDetail detail;
	private WebElement amoCheckBox;
	private boolean flag;
	
	private WebElement placeOrderButon;
	private WebElement productTypeRadioButton;
	private WebElement bseLink;
	
	public NewOrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public HashMap newOrderExecution(TestDataModel model,WebDriver driver,int orderNo) throws InterruptedException, IOException {
		Reporter.log("<====@@@@ OrderNo in Sheet "+model.getOrderNo()+" Action : "+model.getAction()+" @@@@===>",true);	
		HashMap<WebDriver,String> mapObject=new HashMap<WebDriver,String>();
		ConfigReader configReader=new ConfigReader();
		String amoFlag=configReader.configReader("amoFlag");
		HelperCode helperObject=new HelperCode();
		CsvReaderCode csvReader=new CsvReaderCode();
		OrderDetail orderDetail=new OrderDetail(driver);
		detail=new OrderDetail(driver);
		//Thread.sleep(7000);
		Reporter.log("New Order execution Started..........",true);
		if(orderNo!=1) {
			placeOrderButon=fluentWaitCodeXpath(driver,"//a[text()='Place Order']","Place order Link");
			clickElement(placeOrderButon,"Place order Link");
		}
	
		placeOrderTextField=fluentWaitCodeXpath(driver,"//a[text()='Place Order']//following::input","Place Order Textfield");
		if(elementPresentOrNot(placeOrderTextField))
		clearAndSendKey(placeOrderTextField,model.getScript(),"Place Order Textfield");
		
		//sendKeyClickOnDownArrow(placeOrderTextField,model.getScript());
		/*Thread.sleep(3000);*/
		if(model.getSegment().equalsIgnoreCase("NSE")) {
		nseLink=fluentWaitCodeXpath(driver,"//*[@id=\"myModal\"]/div/div/div[3]/div[2]/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div/div/ul/li[1]/a/span[2]","NSE Link");
		clickElement(nseLink,"NSE Link");
		}else if(model.getSegment().equalsIgnoreCase("BSE")) {
		bseLink=fluentWaitCodeXpath(driver, "//*[@id=\"myModal\"]/div/div/div[3]/div[2]/div[1]/div/div[1]/div[1]/div/div/div/div[1]/div/div/ul/li[2]/a/span[2]","BSE Link");
		clickElement(bseLink,"BSE Link");
		}
		
		
		
		//downErrorKeyEnter(placeOrderTextField);
		/*Thread.sleep(2000);*/
		if(model.getOrderType().equalsIgnoreCase("Buy")) {
		buyButton=fluentWaitCodeXpath(driver,"//a[text()='Buy']","Buy button");
		clickElement(buyButton,"Buy button");
		}else if(model.getOrderType().equalsIgnoreCase("Sell")) {
			buyButton=fluentWaitCodeXpath(driver, "//a[text()='Sell']","Sell button");
			clickElement(buyButton,"Sell button");
		}
		/*Thread.sleep(4000);*/
		
		Thread.sleep(2000);
		productType(driver, model.getProductType());
		noOfSharesTextField=fluentWaitCodeXpath(driver,"//input[@placeholder='No. of Shares']","No of shares Textfield");
		
		if(model.getScenario().equalsIgnoreCase("Partial Order")) {
			clearAndSendKey(noOfSharesTextField,model.getQtyMod(),"No of shares Textfield (Mod Qty)");
		}else
		clearAndSendKey(noOfSharesTextField,model.getQty(),"No of shares Textfield ");
		/*Thread.sleep(2000);*/
		enterPriceTextField=fluentWaitCodeXpath(driver,"//input[@placeholder='Enter Price']","Enter Price TextField");
		sendKey(enterPriceTextField, model.getOrderPrice(),"Enter Price TextField");
		
		OptionalFieldsLabel=fluentWaitCodeXpath(driver,"//*[@id=\"myModal\"]/div/div/div[3]/div[2]/div[1]/div/div[2]/div/form/div[2]/div[3]/div[1]/div[1]","OptionalFields Label");
		clickElement(OptionalFieldsLabel,"OptionalFields Label");
		/*Thread.sleep(1000);*/
		
		orderDetail.amoCheckbox(amoFlag);
		placeOrderButton=fluentWaitCodeXpath(driver,"//input[@value ='Place Order']","Place Order Button");
		clickElement(placeOrderButton,"Place Order Button");
		
		try {
		confirmButton=fluentWaitCodeXpath(driver,"//input[@value='Confirm']",30,"confirm button");
		}catch(Exception e) {
			clickElement(placeOrderButton,"Place Order Button");
		}

		//confirmButton=driver.findElement(By.xpath("//input[@value='Confirm']"));
		clickElement(confirmButton,"Confirm Button");
		
		/*Thread.sleep(3000);*/
		
		
		/*String status=helperObject.outputProcessor(driver, "New", orderNo,"No status",model);*/
		mapObject.put(driver, "dfs");
		return mapObject;
	}

	public void productType(WebDriver driver,String productTypeStr) {
		Reporter.log("This is product type ====> "+productTypeStr,true);
		if(productTypeStr.equalsIgnoreCase("CNC")) {
			productTypeRadioButton=fluentWaitCodeXpath(driver,"//label[text()='Delivery CNC']","CNS Product type");
			selectRadioButton(productTypeRadioButton, "CNS Product type");
		}else if(productTypeStr.equalsIgnoreCase("MTF")) {
			productTypeRadioButton=fluentWaitCodeXpath(driver,"//label[text()='Margin Trading MTF']","MTF Product type");
			selectRadioButton(productTypeRadioButton, "MTF Product type");
		}else if(productTypeStr.endsWith("NRML")) {
			productTypeRadioButton=fluentWaitCodeXpath(driver, "//label[text()='Delivery Plus -  NRML']","NRML Product type");
			selectRadioButton(productTypeRadioButton,"NRML Product type");
		}
	}
	
}
