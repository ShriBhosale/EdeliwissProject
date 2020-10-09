package com.edelweiss.fundtransferpages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.edelweiss.model.FundTransferModel;
import com.edelweiss.util.Help;
import com.edelweiss.util.ScreenshortProvider;
import com.edelweiss.util.SeleniumCoder;
import com.edelweiss.watchlistPages.WatchListCommon;

public class FundTransferPage extends SeleniumCoder {

	WebDriver driver;
	
	WebElement mtfRadioButton;
	WebElement eCollectRadionButton;
	WebElement upiViaORCodeRadioButton;
	WebElement amountToTransferTextField;
	WebElement submitButton;
	WebElement bankAccountRedionButton;
	WebElement internetBankingRadioButton;
	WebElement okButton;
	WebElement yesBankAlert;
	WebElement upiRadioButton;
	WebElement qickSelectAmountLabel;
	WebElement upiTextfield;
	WebElement upiCreateText;
	WebElement createUPILink;
	WebElement backtoFundTransferButton;
	WebElement addFundTab;
	WebElement balSummaryLabel;
	WebElement upiCreationPage;
	WebElement upiErrorMsg;
	WebElement upiServicePageLabel;
	
	Payment payment;
	FundTransferCommon fundTransferCommon;
	Help help;
	
	String upiCreateTextStr;
	String upiServiceProviderLabel;

	private WebElement amountErrorMsgLabel;
	
	public static List<String> detailList;
	public static String bankName;

	private Object amountErrorMsgStr;

	private String upiErrorStr;

	private WebElement upiDropdownButton;

	private String upiDropdownText;

	private WebElement upiDropdownBelowLabel;

	private WebElement accountNoLabel;

	private WebElement amountLabel;

	private String addFundScreenshot;

	private WebElement paymentModeLabel;

	private WebElement bankAccountLabel;

	public static String addFundAmountStr;
	public FundTransferPage() {
		
	}
	
	public FundTransferPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		help=new Help();
		payment=new Payment(driver);
		fundTransferCommon=new FundTransferCommon();
		detailList=new ArrayList<String>();
	}
	
	public void UPIRadioButtonTestCase() {
		upiRadioButton=fluentWaitCodeXpath(driver, "//label[@for='upi']","UPI via OR Code radio button");
		okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
		if(okButton!=null) {
			clickElement(okButton , "Ok button");
		}
		clickElement(upiRadioButton, "UPI radioButton");
		okButton=fluentWaitCodeXpath("//input[@value='OK']",10,"Ok button");
		if(okButton!=null) {
			clickElement(okButton , "Ok button");
		}
		upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI texfield");
		
		
		//clickUPICreatelink();
		
	}
	
	public void clickUPICreatelink() {
		detailList.add("@@>Verify for UPI id field for option available to create new UPI  id by clicking on Know how.<@@");
		detailList.add(ScreenshortProvider.captureScreen(driver, "CheckingUPICreateLink","FundTransfer"));
		createUPILink=fluentWaitCodeXpath("//a[text()='Know how']", "create upi link");
		clickElement(createUPILink, "create upi link");
				detailList.add(ScreenshortProvider.captureScreen(driver, "UPIidCreationStepScreenshot","FundTransfer"));
		detailList.add("@@> Verify when user clicks on back To fund Transfer while creating the UPI id from add funds page.<@@");
		backtoFundTransferButton=fluentWaitCodeXpath("//button[@gtmdir-text='Back to Fund Transfer | UPI']", "Back to fund transfer");
		clickElement(backtoFundTransferButton, "Back to fund transfer");
		
		
		
		
		
		eCollectBankVerification("HDFC BANK LTD.");
	}
	
	
	
	public void eCollectBankVerification(String bankName) {
		detailList.add("Verify in case user's bank is e-collect bank.");
		bankAccountRedionButton=fluentWaitCodeXpath(driver, "//span[text()='"+bankName+"']",bankName);
		clickElement(bankAccountRedionButton, "Bank acount radio button");
		eCollectRadionButton=fluentWaitCodeXpath("//label[text()='eCollect']", "eCollect radion button");
		if(eCollectRadionButton!=null) {
			detailList.add("eCollect payment mode option is available.");
			upiRadioButton=fluentWaitCodeXpath(driver, "//label[@for='upi']","UPI radio button");
			if(upiRadioButton.isSelected()) {
				detailList.add("UPI Payment Option already selected-PASS");
			}else {
				detailList.add("UPI Payment Option already selected-FAIL");
			}
		}
		
	}
	
	public void fillUpiTextfield(String upiId) {
		upiDropdownButton=fluentWaitCodeXpath("//label[@for='upi']//following::button[1]",10,"UPI dropdown button");
		if(upiDropdownButton==null) {
		upiTextfield=fluentWaitCodeXpath("//input[@id='upiIdTxt']", "UPI textfield");
		clearAndSendKey(upiTextfield,upiId, "UPI textfield");
		}else {
			upiDropdownText=getValueFromAttribute(upiDropdownButton, "value", "UPI drop down");
			upiDropdownBelowLabel=fluentWaitCodeXpath("//label[@for='upi']//following::p[1]", "UPI dropdown below label");
			detailList.add(fetchTextFromElement(upiDropdownBelowLabel));
		}
	}
	
	public void backToFundTransferModule() {
		redirectGivenUrl("https://ewuat.edelbusiness.in/ewhtml/");
		hoverAndClickOption(driver, "//*[@id='QuickSB']", "//a[text()='Transfer Funds']");
	}
	

	public void paymentModeSelect(String paymentMode,String bankName){
		try {
			eCollectRadionButton=fluentWaitCodeXpath(driver, "//label[text()='UPI via QR code']//preceding::label[1]",15,"eCollect Radion Button");
			}catch(TimeoutException e) {
			internetBankingRadioButton=fluentWaitCodeXpath(driver, "//label[text()='Internet Banking']","internetBankingRadioButton");
			}
		if(!(bankName.equalsIgnoreCase("HDFC BANK LTD")||bankName.equalsIgnoreCase("Yes Bank"))){
			if(paymentMode.equalsIgnoreCase("Internet Banking")) 
			Reporter.log("Internet Banking radion button already selected ",true);
			else if(paymentMode.equalsIgnoreCase("eCollect")) {
				Reporter.log("eCollect Payment mode is not for "+bankName+" bank");
			}
			else if(paymentMode.equalsIgnoreCase("UPI via QR code")) {
				upiViaORCodeRadioButton=fluentWaitCodeXpath(driver, "//label[text()='UPI via QR code']","UPI via OR Code radio button");
				clickElement(upiViaORCodeRadioButton, "UPI via Or code radio button");
			}
			else if(paymentMode.equalsIgnoreCase("UPI")) {
				upiRadioButton=fluentWaitCodeXpath(driver,"//label[text()='UPI via QR code']//following::label[1]","upi Radio Button");
				clickElement(upiRadioButton, "UPI radio button");
			}
		}
		else if(bankName.equalsIgnoreCase("Yes Bank")) {
			yesBankAlert=fluentWaitCodeXpath(driver, "//span[text()=' Yes Bank']//following::span[6]","yesBank Alert");
			fetchTextFromElement(yesBankAlert, "yesBankAlert");
		}
			
			
	}
	
	public void checkAndClickAddFundTab() {
		/*
		 * paymentModeLabel=fluentWaitCodeXpath("//label[text()='Payment Mode']"
		 * ,10,"payment mode"); if(paymentModeLabel==null) {
		 */
			addFundTab=fluentWaitCodeXpath("//a[text()='TRANSFER STATUS']//preceding::a[text()='Add Funds']", "Add fund Tab");
			clickElement(addFundTab, "Add fund tab");
			/* } */
	}
	
	public String bankAccountSelect(FundTransferModel model){
		staticWait(4000);
		checkAndClickAddFundTab();
		String bankName=fundTransferCommon.bankNameGiver(model.getBank());
		bankAccountLabel=fluentWaitCodeXpath(driver, "//span[text()='"+bankName+"']",bankName);
		bankAccountRedionButton=fluentWaitCodeXpath("//span[text()='"+bankName+"']//preceding::input[1]",bankName+ "radio button");
		String selectedBankStr=getValueFromAttribute(bankAccountRedionButton, "gtmdir-text", "bank Radio button");
		Reporter.log("bank selected bank : "+selectedBankStr, true);
		if(!selectedBankStr.contains(bankName)) {
			
				clickElement(bankAccountLabel, bankName+"radio button");
		}
		accountNoLabel=fluentWaitCodeXpath("//span[text()='"+bankName+"']//following::span[1]", "account no");
		
		model.setBank(bankName);
		return fetchTextFromElement(accountNoLabel);
	}
	
	public void fillAmount(String amount){
		String [] amountArray=fundTransferCommon.amountEnter(amount);
		
		amountToTransferTextField=fluentWaitCodeName(driver, "amt", 20,"Amount To Transfer TextField");
		clearAndSendKey(amountToTransferTextField, amount, "Amount To Transfer TextField");
		
	
		submitButton=fluentWaitCodeXpath(driver, "//input[@value='Submit']","submit button");
		clickElement(submitButton, "Submit button");
	}
	
	public void addFundPageAmount() {
		Reporter.log("===> addFundPageAmount <===", true);
		staticWait(500);
		
		 do {
			 amountLabel=fluentWaitCodeXpath("//label[@class='amtBold ng-binding ng-scope']",100,"Add fund amount label");
			 addFundAmountStr=fetchTextFromElement(amountLabel).replace("Rs", "").trim(); 
			 Reporter.log("Inside do while for addFundAmountStr ", true);
		 }while(addFundAmountStr.equalsIgnoreCase(""));
		Reporter.log("addFundAmountStr : "+addFundAmountStr, true);
	}
	
	public void fundTransfer(FundTransferModel model,FundTransferReport report,String optionAfterTransfer) {
		Reporter.log("===> fundTransfer <===", true);
		Reporter.log(model.toString(), true);
		addFundPageAmount();
		
		String accountNo=bankAccountSelect(model);
		paymentModeSelect(model.getPaymentMode(),model.getBank());
		if(model.getBank().equalsIgnoreCase("HDFC BANK LTD")) {
		okButton=fluentWaitCodeXpath(driver, "//input[@value='OK']","ok Button");
		clickElement(okButton, "Ok button");
		}
		
		fillAmount(model.getAmount());
		addFundScreenshot=ScreenshortProvider.captureScreen(driver, "AddFundTransferForm","FundTransfer");
		detailList=payment.paymentCodeExecution(model,accountNo,optionAfterTransfer,addFundScreenshot);
		report.fundTransferReport(detailList,model,optionAfterTransfer);
	}

	public void fundTransferexecute(FundTransferModel model,FundTransferReport report,String seeMarginScreenshot) {
		Reporter.log("===> fundTransferexecute <====", true);
		detailList=new ArrayList<String>();
		detailList.add(seeMarginScreenshot);
		fundTransfer(model, report, "See Margin");
		
		fundTransfer(model, report, "Place order");
	}
}
