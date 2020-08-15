package com.shreeya;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.shreeya.model.LoginModel;
import com.shreeya.model.TestDataModel;
import com.shreeya.orderdetailpages.LoginPage;
import com.shreeya.orderdetailpages.OrderAction;
import com.shreeya.util.ApacheCode;

import com.shreeya.util.BrowserLaunch;

import com.shreeya.util.ConfigReader;

import com.shreeya.util.FolderStructure;
import com.shreeya.util.HelperCode;

public class Execution {
	/*
	 * 
	 * LoginPage login; WebDriver driver; OrderAction orderActioObj; HelperCode
	 * helperObject; TestDataModel testDataObject;
	 * 
	 * 
	 * //public static String MyTestLauncher.folderPath[]=null; public static
	 * ApacheCode apacheCodeObj; public static int noInstance=0; public static int
	 * noExecution=0;
	 * 
	 * 
	 * @BeforeTest public void executionBefore() throws IOException {
	 * Reporter.log("Execution Before ", true); BrowserLaunch launch=new
	 * BrowserLaunch(); driver=launch.browserLaunch("Normal"); login=new
	 * LoginPage(driver); orderActioObj=new OrderAction(driver); testDataObject=new
	 * TestDataModel(); helperObject=new HelperCode();
	 * 
	 * FolderStructure folderCreationObj=new FolderStructure(); <<<<<<< HEAD
	 * Reporter.
	 * log("Above folder Creation============================================================================&^*&^&*^&8686868688>>>>>>"
	 * ); MyTestLauncher.folderPath=folderCreationObj.reportFolderCreator();
	 * apacheCodeObj=new ApacheCode(MyTestLauncher.folderPath[0]);
	 * 
	 * //apacheCodeObj.outputFileWriterHeader(MyTestLauncher.folderPath[0]); =======
	 * 
	 * folderPath=folderCreationObj.reportFolderCreator(); apacheCodeObj=new
	 * ApacheCode(folderPath[0]); ConfigReader reader=new ConfigReader();
	 * noInstance=Integer.valueOf(reader.configReader("NumberInstance"));
	 * //apacheCodeObj.outputFileWriterHeader(folderPath[0]); >>>>>>> paralleExecute
	 * 
	 * }
	 * 
	 * @BeforeMethod public void beforeTest() {
	 * Reporter.log("Before Test case...",true); Reporter.log("BeforeMethod",true);
	 * }
	 * 
	 * @Parameters({"Reference","UserId","Pwd","Yob","StartNo","EndNo","Module"})
	 * 
	 * @Test public void execution(String referenceNo,String userId,String
	 * pwd,String yob,String startNo,String endNo,String module) { Reporter.
	 * log("*******<<<<<<<<<<<<<<<Your Enter into TestCase>>>>>>>>>>>>>>>********"
	 * ,true); Reporter.log("Before Iterator "+referenceNo);
	 * 
	 * Reporter.log("After Iterator",true);
	 * 
	 * 
	 * LoginModel loginModelObj =new LoginModel(referenceNo, userId, pwd, yob,
	 * startNo, endNo, module);
	 * Reporter.log("Login Data ====> "+loginModelObj.toString(),true);
	 * if(referenceNo.equals(loginModelObj.getReferNo())) { try {
	 * login.loginExecution("Normal",loginModelObj);
	 * //driver=orderActioObj.orderActionStart(driver,loginModelObj);
	 * terminateExecution(driver); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
	 * TODO Auto-generated catch Reporter.log(e.getMessage()); } }
	 * 
	 * } Reporter.log("Outside form execution............",true); }
	 * 
	 * public void terminateExecution(WebDriver driver) throws InterruptedException,
	 * IOException {
	 * 
	 * if(driver != null) {
	 * 
	 * helperObject.outputProcessor(driver, "newOrder", 0, "Terminate",
	 * testDataObject,0); login.logout(driver); driver.close();
	 * Reporter.log("Execution Terminate.... :)",true); }else {
	 * 
	 * } }
	 * 
	 * @AfterMethod public void testAfter() throws IOException {
	 * Reporter.log("End test case execution",true);
	 * 
	 * noExecution++;
	 * 
	 * // if(noInstance==noExecution) //
	 * apacheCodeObj.outputExcelFileClose(folderPath[0]); }
	 * 
	 * @AfterTest public void endExecution() throws IOException {
	 * 
	 * //apacheCodeObj.closeExcelWriting();
	 * Reporter.log("Folder Path ====> "+MyTestLauncher.folderPath, true);
	 * //apacheCodeObj.outputExcelFileClose(); }
	 * 
	 * public void orderDetailExecution() {
	 * 
	 * //Reporter.log("Folder Path ====> "+folderPath[0], true);
	 * 
	 * 
	 * }
	 */}
