package com.shreeya.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReporter {

	public static ExtentHtmlReporter htmlextent = null;
	public static ExtentReports report = null;
	public static ExtentTest test = null;
	HelperCode helperObject;
	private String reportPathString;
	
	public  ExtendReporter(String folderPathString) {
		helperObject=new HelperCode();
		reportPathString=folderPathString+"\\HtmlReport"+helperObject.timeStampGenerator()+".html";
		setReportPathString(reportPathString);
		htmlextent = new ExtentHtmlReporter(getReportPathString());
		report = new ExtentReports();
		report.attachReporter(htmlextent);
		
	}
	
	public void tearDown(String output) {
		if(output.equalsIgnoreCase("PASS")) {
			test.log(Status.PASS,"Test Pass....");
		}else
			test.log(Status.FAIL, "Test Fail...");
	}
	
	public String getReportPathString() {
		return reportPathString;
	}



	public void setReportPathString(String reportPathString) {
		this.reportPathString = reportPathString;
	}



	public void testCreation(String testName) {
		test = report.createTest(testName);
	}
	
	/*public void addScreenshotMethod(WebDriver driver) throws IOException {
		 test.addScreenCaptureFromPath(captureScreen(driver));
		 
	}*/
	
	public String captureScreen(WebDriver driver,String folderPathString) throws IOException {
		
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest =folderPathString+"\\Screenshot"+helperObject.timeStampGenerator()+".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	public void logsPrinter(ArrayList<String> msgs) {
		for(String msg:msgs) {
			test.log(Status.INFO, msg);
		}
	}
	
	public void reportGenerator(String [] orderDetailArray) {
		test.log(Status.INFO, "Action : "+orderDetailArray[1]);
		test.log(Status.INFO, "Status : "+orderDetailArray[2]);
		test.log(Status.INFO, "Order Action :: "+orderDetailArray[3]);
		test.log(Status.INFO, "Trading Symbol :: "+orderDetailArray[4]);
		test.log(Status.INFO, "Product Type :: "+orderDetailArray[5]);
		test.log(Status.INFO, "Order Price :: "+orderDetailArray[6]);
		test.log(Status.INFO, "Order Type :: "+orderDetailArray[7]);
		test.log(Status.INFO, "User id :: "+orderDetailArray[8]);
		test.log(Status.INFO, "Exchange : "+orderDetailArray[9]);
		test.log(Status.INFO, "Validity :: "+orderDetailArray[10]);
		test.log(Status.INFO, "Nest Id :: "+orderDetailArray[11]);
		test.log(Status.INFO, "Rejection Reason : "+orderDetailArray[12]);
	}
	
	public void report(String [] arry) {
		for(String str:arry) {
			test.log(Status.INFO, str);
		}
	}
	
	public void logFlush() {
		report.flush();
		
	}
	

}
