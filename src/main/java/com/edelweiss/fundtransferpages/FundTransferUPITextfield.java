package com.edelweiss.fundtransferpages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.util.ConfigReader;
import com.edelweiss.util.Help;
import com.edelweiss.util.ScreenshortProvider;
import com.edelweiss.util.SeleniumCoder;

public class FundTransferUPITextfield extends SeleniumCoder{

	WebDriver driver;
	WebElement upiRadioButton;
	WebElement okButton;
	WebElement upiTextfield;
	WebElement fundTransferTab;
	WebElement upiCreateText;
	WebElement backtoFundTransferButton;
	WebElement createUPILink;
	WebElement upiCreationPage;
	
	Help help;
	List<String> detailList;
	
	String upiCreateTextStr;
	private WebElement addFundTab;
	private WebElement balSummaryLabel;
	private WebElement upiErrorMsg;
	private String upiErrorStr;
	private WebElement upiDropdownButton;
	private String upiDropdownText;
	private WebElement upiDropdownBelowLabel;
	private WebElement bankAccountRedionButton;
	private WebElement amountToTransferTextField;
	private WebElement submitButton;
	private WebElement upiServicePageLabel;
	private String upiServiceProviderLabel;
	
	FundTransferCommon fundTransferCommon;
	private WebElement eCollectRadionButton;
	private WebElement addNewUPIidLink;
	private WebElement upiError;
	
	ConfigReader configReader;
	private WebElement bankAccountLabel;
	
	public FundTransferUPITextfield(WebDriver driver) {
		super(driver);
		this.driver=driver;
		help=new Help();
		detailList=new ArrayList<String>();
		fundTransferCommon=new FundTransferCommon(driver);
		configReader=new ConfigReader();
	}
	
	public void elementlocate() {
		upiRadioButton=fluentWaitCodeXpath(driver, "//label[@for='upi']",160,"UPI radio button");
		
		okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
		if(okButton!=null)
			clickElement(okButton, "OK button");
		amountToTransferTextField=fluentWaitCodeName(driver, "amt", 20,"Amount To Transfer TextField");
		submitButton=fluentWaitCodeXpath(driver, "//input[@value='Submit']","submit button");
	}
	
	public void locateUPITextfield() {
		Reporter.log("===> locateUPITextfield <===", true);
		if(upiRadioButton.isSelected()==false) {
		clickElement(upiRadioButton, "UPI radio button");
		okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
		if(okButton!=null)
			clickElement(okButton, "OK button");
		upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI texfield");
		}
	}
	
	public void checkGuidlinText() {
		Reporter.log("===> checkGuidlinText <===", true);
		detailList.add("@@> Verify guiding text is present for Upi ID text field. <@@");
		locateUPITextfield();
		upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI texfield");
		detailList.add("Guid text : "+help.commpareTwoString(getValueFromAttribute(upiTextfield, "placeholder", "UPI texfield"), "Enter UPI id linked to above account"));
		detailList.add(ScreenshortProvider.captureScreen(driver, "UPIGuidLine","FundTransfer"));
	}
	
	public void redirectToUPICreationStepPage() {
		Reporter.log("===> redirectToUPICreationStepPage <===", true);
		detailList.add("@@> Verify for UPI id field for option available to create new UPI  id. <@@");
		locateUPITextfield();
		upiCreateText=fluentWaitCodeXpath("//div[@ng-disabled='upitextbox']", "UPI Text");
		upiCreateTextStr=removeHtmlText(fetchTextFromElement(upiCreateText));
		detailList.add(help.commpareTwoString(upiCreateTextStr.trim(), "Don't have a UPI ID? Know how  to create"));
		createUPILink=fluentWaitCodeXpath("//a[text()='Know how']", "create upi link");
		clickElement(createUPILink, "create upi link");
		upiCreationPage=fluentWaitCodeXpath("//h5[text()='Follow the below steps to create a new UPI ID']", "UPI creation page");
		detailList.add(help.commpareTwoString(fetchTextFromElement(upiCreationPage), "Follow the below steps to create a new UPI ID"));

		backtoFundTransferButton=fluentWaitCodeXpath("//button[@gtmdir-text='Back to Fund Transfer | UPI']", "Back to fund transfer");
		if(backtoFundTransferButton!=null) {
			detailList.add("Back to Fund transfer Button is present-PASS");
		}else {
			detailList.add("Back to Fund transfer Button is not present-FAIL");
		}
		detailList.add(ScreenshortProvider.captureScreen(driver, "BackToFundTransferPage","FundTransfer"));
	}
	
	public void closeUPIStepCreationPage() {
		Reporter.log("===> closeUPIStepCreationPage <===", true);
		detailList.add("@@> Verify when user clicks on back To fund Transfer.It is redirect to add fundtransfer page.. <@@");
		clickElement(backtoFundTransferButton, "Back fund transfer button");
		addFundTab=fluentWaitCodeXpath("//*[@id=\"myModal\"]/div/div/div[3]/div[2]/div[5]/div[1]/div[2]/ul/li[1]/a", "add fund transfer");
		if(addFundTab!=null) {
			balSummaryLabel=fluentWaitCodeXpath("//label[@class='balSumry ng-binding']", "Balance summary");
			detailList.add("titile : "+help.removeHtmlText(fetchTextFromElement(balSummaryLabel)));
			detailList.add("Redirect to Add Fund transfer page-PASS");
		}
		detailList.add(ScreenshortProvider.captureScreen(driver, "Add fund transfer page","FundTransfer"));
	}
	
	public void enterInvalidUPIid() {
		Reporter.log("===> enterInvalidUPIid <===", true);
		detailList.add("@@>Verify UPI id field by entering the invalid.<@@");
		locateUPITextfield();
		clearAndSendKey(upiTextfield, "A", "UPI textfield");
		upiErrorMsg=fluentWaitCodeXpath("//input[@id='upiIdTxt']//following::span[@class='icom-alert-triangle'][1]", "UPI error msg");
		if(upiErrorMsg!=null) {
		upiErrorStr=fetchTextFromElement(upiErrorMsg);
		detailList.add("Error msg : "+help.commpareTwoString(upiErrorStr, "UPI ID should be in the format name@bankname."));
		}
		detailList.add(ScreenshortProvider.captureScreen(driver, "UPITextfieldINValid","FundTransfer"));
	}
	
	public void enterDiffBankUPIid() {
		Reporter.log("===> enterDiffBankUPIid <===",true);
		detailList.add("@@> Verify when user enter hdfc bank UPI id for different bank <@@");
		detailList.add("Bank name : Kotak Mahindra Bank");
		upiExecution("Kotak Mahindra Bank","200","qrtest1@hdfcbank",true);
	}
	
	public void enterValidUPIid() {
		Reporter.log("===> enterValidUPIid <===", true);
		detailList.add("@@> Verify UPI id field by entering the valid UPI id <@@");
		upiExecution("HDFC BANK LTD.","200","cup@hdfcbank",false);
	}
	
	public void upiExecution(String bankName,String amount,String upiId,boolean negative) {
		Reporter.log("===> upiExecution <===");
		bankAccountLabel=fluentWaitCodeXpath(driver, "//span[text()='"+bankName+"']",bankName);
		bankAccountRedionButton=fluentWaitCodeXpath("//span[text()='"+bankName+"']//preceding::input[1]",bankName+ "radio button");
		String selectedBankStr=getValueFromAttribute(bankAccountRedionButton, "gtmdir-text", "bank Radio button");
		Reporter.log("bank selected bank : "+selectedBankStr, true);
		if(!selectedBankStr.contains(bankName)) {
			
				clickElement(bankAccountLabel, bankName+"radio button");
		}
		upiRadioButton=fluentWaitCodeXpath(driver, "//label[@for='upi']","UPI radio button");
		if(upiRadioButton.isSelected()==false)
		clickElement(upiRadioButton, "UPI radio button");
		okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
		if(okButton!=null) {
			clickElement(okButton , "Ok button");
		}
		fillUpiTextfield(upiId,false);
		
		staticWait(500);
		upiErrorMsg=fluentWaitCodeXpath("//input[@id='upiIdTxt']//following::span[@class='icom-alert-triangle'][1]",100, "UPI error msg");
		if(upiErrorMsg==null) {
			upiErrorMsg=fluentWaitCodeXpath("//input[@id='upiIdTxt']//following::span[@class='icom-alert-triangle'][2]",100, "UPI error msg");
		}
		if(upiErrorMsg.isDisplayed()) {
			/*
			 * upiErrorMsg=fluentWaitCodeXpath(
			 * "//input[@id='upiIdTxt']//following::span[@class='icom-alert-triangle'][1]",
			 * 10, "UPI error msg");
			 */
			upiErrorStr=fetchTextFromElement(upiErrorMsg);
			detailList.add("Error msg : "+help.commpareTwoString(upiErrorStr, "This UPI ID is not linked to selected bank account.Please recheck"));
		}else {
			
			amountToTransferTextField=fluentWaitCodeName(driver, "amt", 20,"Amount To Transfer TextField");
			clearAndSendKey(amountToTransferTextField,amount, "Amount textfield");
			submitButton=fluentWaitCodeXpath(driver, "//input[@value='Submit']","submit button");
			clickElement(submitButton, "Submit button");
			staticWait(3000);
			//upiServicePageLabel=elementLocateBytag("h5");
			detailList.add(ScreenshortProvider.captureScreen(driver, "UPIServicePageNotExpected","FundTransfer"));
			/*
			 * upiServicePageLabel=elementLocateBytag("h5");
			 * upiServiceProviderLabel=fetchTextFromElement(upiServicePageLabel);
			 */
			upiServiceProviderLabel=fundTransferCommon.checkAmountInUpiServicesProviderPage();
			errorChecker(negative);
			if(negative) {
				detailList.add("This error msg come after enter amount and click on submit button-FAIL");
			}else {
				if(!upiServiceProviderLabel.equalsIgnoreCase("Manage UPI IDs"))
				detailList.add(fundTransferCommon.removeHtmlTextCheckText(upiServiceProviderLabel,amount));
			}
			backToFundTransferModule();
		}
		
	}
	
	public String errorChecker(boolean negative) {
		String errorMsg="";
		upiError=fluentWaitCodeXpath("//input[@id='upiIdTxt']//following::span[@class='icom-alert-triangle ng-binding'][1]",50, "UPI error msg");
		if(upiError==null) {
			upiError=fluentWaitCodeXpath("//label[@for='upi']//following::span[3]",50,"UPI error msg");
		}
		if(upiError!=null) {
			if(upiError.isDisplayed()) {
				if(negative) {
					errorMsg="Error msg : "+fetchTextFromElement(upiError)+"-PASS";
					detailList.add(errorMsg);
					
				}else {
					errorMsg="Error msg : "+fetchTextFromElement(upiError)+"-FAIL";
					detailList.add(errorMsg);
				}
			}
		}
		return errorMsg;
	}
	
	public void backToFundTransferModule() {
		staticWait(500);
		Reporter.log("===> backToFundTransferModule <===", true);
		redirectGivenUrl("https://ewuat.edelbusiness.in/ewhtml/");
		hoverAndClickOption(driver, "//*[@id='QuickSB']", "//a[text()='Transfer Funds']");
	}
	
	public void fillUpiTextfield(String upiId,boolean primaryUPIid) {
		Reporter.log("===> fillUpiTextfield <===", true);
		configReader.fundTransferConfig();
		upiDropdownButton=fluentWaitCodeXpath("//label[@for='upi']//following::button[1]",10,"UPI dropdown button");
		if(upiDropdownButton.isDisplayed()==false) {
		upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI textfield");
		clearAndSendKey(upiTextfield,upiId, "UPI textfield");
		}else {
			if(primaryUPIid) {
			upiDropdownText=getValueFromAttribute(upiDropdownButton, "text", "UPI drop down");
			detailList.add("Primary Id : "+upiDropdownText+"-PASS");
			upiDropdownBelowLabel=fluentWaitCodeXpath("//label[@for='upi']//following::p[1]", "UPI dropdown below label");
			detailList.add(fetchTextFromElement(upiDropdownBelowLabel));
			}else {
				clickElement(upiDropdownButton, "UPI dropdown");
			addNewUPIidLink=fluentWaitCodeXpath("//a[text()='Add New UPI ID']", "Add new UPI id link");
			clickElement(addNewUPIidLink,"Add new UPI id link");
			okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
			if(okButton!=null) {
				clickElement(okButton , "Ok button");
			}
			upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI textfield");
			clearAndSendKey(upiTextfield,upiId, "UPI textfield");
			detailList.add("UPI id : "+getValueFromAttribute(upiTextfield, "value", "UPI textfield"));
			}
		}
	}
	
	public void eCollectBankVerification() {
		Reporter.log("===> eCollectBankVerification <==", true);
		String bankName=configReader.configReaderFM("eCollectBank");
		if(!bankName.equalsIgnoreCase("No")) {
		detailList.add("@@> Verify in case user's bank is ecollect bank. <@@");
		bankAccountRedionButton=fluentWaitCodeXpath(driver, "//span[text()='"+bankName+"']",bankName);
		clickElement(bankAccountRedionButton, "Bank acount radio button");
		eCollectRadionButton=fluentWaitCodeXpath("//label[text()='eCollect']", "eCollect radion button");
		if(eCollectRadionButton!=null) {
			detailList.add("eCollect payment mode option is available.");
			upiRadioButton=fluentWaitCodeXpath(driver, "//label[@for='upi']","UPI radio button");
			if(upiRadioButton.isEnabled()) {
				detailList.add("UPI Payment Option already selected-PASS");
			}else {
				detailList.add("UPI Payment Option already selected-FAIL");
			}
		}
		}
	}
	
	
	public void UPITextfieldExecution(FundTransferReport report) {
		detailList=new ArrayList<String>();
		configReader.fundTransferConfig();
		Reporter.log("<b>=====@@> UPITextfieldExecution <@@=====</b>", true);
		fundTransferTab=fluentWaitCodeXpath(driver, "//a[text()='Fund Transfer']",180,"fundTransferTab");
		Reporter.log("Fundtransfer tab : "+fundTransferTab, true);
		if(fundTransferTab!=null) {
			clickElement(fundTransferTab, "FundTransferTab");
		}
		  elementlocate(); 
			
			  checkGuidlinText(); 
			  redirectToUPICreationStepPage();
			  closeUPIStepCreationPage();
			  enterInvalidUPIid();
			 
		  
		  enterValidUPIid();
		  enterDiffBankUPIid();
		 
		 
		eCollectBankVerification();
		report.upiTextfieldReport(detailList);
	}
}
