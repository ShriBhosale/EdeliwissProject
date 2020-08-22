package com.shreeya.experiment;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.shreeya.MyTestLauncher;
import com.shreeya.util.Help;
import com.shreeya.util.HelperCode;

public class Report {
	public ExtentHtmlReporter htmlextent = null;
	public ExtentReports report = null;
	public ExtentTest test = null;
	public Report() {
		
		htmlextent = new ExtentHtmlReporter("E:\\EdelweissProject\\EuatReport\\ExtendReport.html");

		report = new ExtentReports();
		//htmlextent.config().setReportName();
		report.attachReporter(htmlextent);
		
	}
	
	public void createTest(String testName) {
		test=report.createTest(testName);
		test.log(Status.INFO, "Shreeya Friends name :)");
	}
	
	public void printLog(String msg) {
		test.log(Status.INFO, msg);
	}
	
	public void printLog(String msg,boolean passOrNot) {
		if(passOrNot)
			test.log(Status.PASS, msg);
		else
			test.log(Status.FAIL, msg);
	}

	
	public  String captureScreen(WebDriver driver)  {
		//String screenshotPath="E:\\EdelweissProject\\TestData\\TestData\\OrangeHRM\\Screenshot\\Screenshot.png";
		
		String screenshotPath="./report/Screenshot/Screenshot_35464121541.png";
		
		//Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)driver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(screenshotPath);

                //Copy file at destination

                try {
					FileUtils.copyFile(SrcFile, DestFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                return screenshotPath;
	}
	
	public void addScreenshot(String screenshotPath) {
		try {
			test.log(Status.INFO, "WatchList", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logFlush() {
		report.flush();
	}
}
