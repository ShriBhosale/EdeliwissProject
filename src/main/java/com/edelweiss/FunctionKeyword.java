package com.edelweiss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.edelweiss.alertandnotification.AlertAndNotificationExecution;
import com.edelweiss.fundtransferpages.FundTransferExecution;
import com.edelweiss.model.LatestLoginModel;
import com.edelweiss.model.MasterTestModel;
import com.edelweiss.model.TestDataModel;
import com.edelweiss.mypositionspages.MyPositionsExecution;
import com.edelweiss.orderdetailpages.LoginExecution;
import com.edelweiss.orderdetailpages.LoginPage;
import com.edelweiss.orderdetailpages.OrderAction;
import com.edelweiss.practiesWatchList.WatchListExecution;
import com.edelweiss.seeholdingspages.SeeHoldingsExecution;
import com.edelweiss.seemarginpages.SeeMarginExecution;
import com.edelweiss.util.ApacheCode;
import com.edelweiss.util.BrowserLaunch;
import com.edelweiss.util.CsvReaderCode;

import com.edelweiss.util.ExtendReporter;
import com.edelweiss.util.FolderStructure;
import com.edelweiss.util.HelperCode;


public class FunctionKeyword {

	public static String keyWord = "no Keyword in FunctionKeyWord";
	LoginPage login;
	public static WebDriver driver;
	OrderAction orderActioObj;
	//HelperCode helperObject;
	TestDataModel testDataObject;
	MasterTestModel masterTestmodel;
	ExtendReporter htmlReport;
	BrowserLaunch browserLunch;
	private String step;
	boolean skipScenario=false;
	Map<String,ArrayList<String>> loginMap;
	ArrayList<String> equtiyLoginList;
	ArrayList<String> commodityLoginList;
	ArrayList<String> mergeIdLoginList;
	
	public static ApacheCode apacheCodeObj;
	public static FolderStructure folderCreationObj;
	LatestLoginModel latestLoginModel;
	private String segmentStr;
	AlertAndNotificationExecution alertAndNotificationExecution;
	

	@BeforeSuite
	public void beforeSuite() throws IOException {
		Reporter.log("Before suit", true);
		htmlReport=new ExtendReporter();
	}

	@BeforeTest
	public void executionBefore() throws IOException {

		Reporter.log("Execution Before ", true);
		 browserLunch = new BrowserLaunch();
		
		//orderActioObj = new OrderAction(driver,String.valueOf(latestLoginModel.getReferNo()));
		testDataObject = new TestDataModel();
		//helperObject = new HelperCode();
		loginMap=new HashMap<String,ArrayList<String>>();
		equtiyLoginList=new ArrayList<String>();
		commodityLoginList=new ArrayList<String>();
		mergeIdLoginList=new ArrayList<String>();
		apacheCodeObj = new ApacheCode();
		
		// apacheCodeObj.outputFileWriterHeader(folderPath[0]);

	}

	@BeforeMethod
	public void beforeTest() {
		Reporter.log("Before Test case...", true);
		Reporter.log("BeforeMethod", true);
	}

	@Parameters({ "Reference", "UserIdEQ", "PasswordEQ", "YobEQ", "UserIdCO", "PasswordCO", "YobCO", "UserIdMI", "PasswordMI", "YobMI", "Module","Execution"})
	@Test
	public void executionWithKeyword(String referenceNo, String userIdEQ, String pwdEQ, String yobEQ, String userIdCO, String pwdCO, String yobCO,String userIdMI, String pwdMI, String yobMI, String module,String execution) throws InterruptedException, IOException {
		if(execution.equalsIgnoreCase("Yes")) {
		CsvReaderCode code = new CsvReaderCode();
		driver = browserLunch.browserLaunch("Normal");
		login = new LoginPage(driver);
		Reporter.log("<b><==================== Function KeyWord : executionWithKeyword ================></b>", true);
		Reporter.log("Before Iterator " + referenceNo);
		Map<Boolean,WebDriver> loginDriverMap;
		Reporter.log("After Iterator", true);
		equtiyLoginList.add(referenceNo);
		equtiyLoginList.add(userIdEQ);
		equtiyLoginList.add(pwdEQ);
		equtiyLoginList.add(yobEQ);
		equtiyLoginList.add(module);
		
		commodityLoginList.add(referenceNo);
		commodityLoginList.add(userIdCO);
		commodityLoginList.add(pwdCO);
		commodityLoginList.add(yobCO);
		commodityLoginList.add(module);
		
		mergeIdLoginList.add(referenceNo);
		mergeIdLoginList.add(userIdMI);
		mergeIdLoginList.add(pwdMI);
		mergeIdLoginList.add(yobMI);
		mergeIdLoginList.add(module);
		
		loginMap.put("Equity", equtiyLoginList);
		loginMap.put("Commodity", commodityLoginList);
		loginMap.put("MergeId", mergeIdLoginList);
		
		for(Map.Entry<String,ArrayList<String>> entry : loginMap.entrySet()) {
			List<String> login=entry.getValue();
			segmentStr=entry.getKey();
			int referNo=Integer.valueOf(login.get(0));
			if(!login.get(1).equalsIgnoreCase("No")) {
			latestLoginModel=new LatestLoginModel(referNo, login.get(1), login.get(2), login.get(3), login.get(4),entry.getKey());
			
			}else {
				continue;
			}
		
		//LoginModel loginModelObj = new LoginModel(referenceNo, userId, pwd, yob, startNo, endNo, module);
		Reporter.log("Login Data ====> " + latestLoginModel.toString(), true);

		Reporter.log("KeyWord before process  ====> " + module, true);

		ListIterator<String> csvMasterTestModelIterator = code.masterTestDataProvider(module);
		int count = 0;
		
		while (csvMasterTestModelIterator.hasNext()) {
			step = csvMasterTestModelIterator.next();
			System.out.println("count : " + count++);

			Reporter.log("Step ===> " + step, true);

			switch (KeywordStringProcess(step)) {

			case "login":
				LoginExecution loginPageObj = new LoginExecution(driver);
				Reporter.log("Login functionality", true);
				skipScenario=loginPageObj.loginExecution("normal", latestLoginModel);
				
				if(skipScenario) {
					Reporter.log("<b><u>Login step fail</b></u>", true);
					continue;
				}
				break;

			case "orderdetail":
				//OrderAction orderActionObj = new OrderAction(driver,String.valueOf(latestLoginModel.getReferNo()));
				OrderAction orderActionObj = new OrderAction(driver);
				Reporter.log("Order detail functionality", true);
				if(skipScenario==false)
				//orderActionObj.orderActionStart(loginModelObj);
				break;

			case "fundtransfer":
				FundTransferExecution fundTransferObj = new FundTransferExecution(LoginExecution.loginWebDriver);
				Reporter.log("Execution driver : FunctionKeyWord : "+LoginExecution.loginWebDriver, true);
				if(skipScenario==false)
				fundTransferObj.fundTransferExecute(latestLoginModel);
				Reporter.log("fun transfer executin", true);
				break;
			case "mypositions":
				MyPositionsExecution myPositionObj = new MyPositionsExecution(LoginExecution.loginWebDriver);
				if(skipScenario==false)
				myPositionObj.myPositionsExecute();
				Reporter.log("My Position Module", true);
				break;
			case "seemargin":
				SeeMarginExecution seeMarginObj = new SeeMarginExecution(LoginExecution.loginWebDriver);
				if(skipScenario==false)
				seeMarginObj.seeMarginExecute();
				Reporter.log("See Margin Module", true);
				break;
			case "seeholdings":
				SeeHoldingsExecution seeHoldingsObj = new SeeHoldingsExecution(LoginExecution.loginWebDriver);
				if(skipScenario==false)
				seeHoldingsObj.seeHoldingsExecute();
				Reporter.log("See Holdings Module", true);
				break;
				
			case "watchlist":
				//WatchListMainExecution watchListObj = new WatchListMainExecution(LoginExecution.loginWebDriver);
				WatchListExecution watchListObj=new WatchListExecution(LoginExecution.loginWebDriver);
				/* watchListObj.watchListExecute(segmentStr); */
				if(skipScenario==false)
					watchListObj.watchListExecute();
				Reporter.log("Watchlist Module", true);
				break;
				
			case "alertandnotification":
				Reporter.log("Execution driver : FunctionalKeyword : altert :  "+LoginExecution.loginWebDriver, true);
				AlertAndNotificationExecution alertAndNotificationExecution=new AlertAndNotificationExecution(LoginExecution.loginWebDriver);
				alertAndNotificationExecution.alertAndNotificationExecute(segmentStr);
				break;
			case "logout":
				if(skipScenario==false)
				terminateExecution(module, LoginExecution.loginWebDriver,referenceNo);
				break;
			/*
			 * default: Reporter.
			 * log("Please Enter follow mentioned keyword : \nlogin\norderdetail\nfundtransfer\nmypositions\nseemargin\nseeholdings\nlogout"
			 * , true);
			 */
			}

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

	public void terminateExecution(String module, WebDriver driver,String referNo) throws InterruptedException, IOException {
		Reporter.log("FunctionKeyword : TerminateExecution",true);
		if (driver != null) {
			if (!(module.equalsIgnoreCase("orderdetail")||module.equalsIgnoreCase("fundtransfer")||
					module.equalsIgnoreCase("login")||module.equalsIgnoreCase("watchlist")||module.equalsIgnoreCase("alertandnotification"))) {
				ExtendReporter reporter = new ExtendReporter();
				reporter.reporter(driver, module, MyTestLauncher.reportFolderPath,referNo);
				//helperObject.outputProcessor(driver, "newOrder", 0, "Terminate", testDataObject, 0);
			}
			//unComment
			//htmlReport.captureScreen(driver, MyTestLauncher.reportFolderPath[2], "beforLogout", 1);
			Reporter.log("Module name : "+module, true);
			if(!module.equalsIgnoreCase("login"))
			login.logout(LoginExecution.loginWebDriver);
			
			Reporter.log("Execution Terminate.... :)", true);
		} else {

		}
	}

	@AfterMethod
	public void testAfter(ITestResult result) throws IOException {
		Reporter.log("End test case execution", true);
		//unComment
		//htmlReport.captureScreen(driver, MyTestLauncher.reportFolderPath[2], "AnyModule", 1);
		browserLunch.driverClose();
	}

	@AfterTest
	public void endExecution() throws IOException {
		// apacheCodeObj.closeExcelWriting();
		
		Reporter.log("Folder Path ====> " + MyTestLauncher.reportFolderPath[0], true);
		
		//apacheCodeObj.outputExcelFileClose(folderPath[0]);

	}

	@AfterSuite
	public void afterSuite() throws IOException {
		/*
		 * Process pro =
		 * Runtime.getRuntime().exec("cmd.exe /k netstat -ano|findstr 5554");
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(pro.getInputStream())); String s = br.readLine();
		 * 
		 * String[] output = s.split("    ");
		 * 
		 * Runtime.getRuntime().exec("cmd.exe /k taskkill /f /pid " +
		 * output[output.length - 1]);
		 */
	}

}
