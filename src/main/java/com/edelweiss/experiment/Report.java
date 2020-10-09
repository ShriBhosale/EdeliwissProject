package com.edelweiss.experiment;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import com.edelweiss.MyTestLauncher;
import com.edelweiss.practiesWatchList.ServerLogMode;
import com.edelweiss.practiesWatchList.ServerReport;
import com.edelweiss.util.Help;
import com.edelweiss.util.HelperCode;
import com.edelweiss.util.ScreenshortProvider;

public class Report {
	public ExtentHtmlReporter htmlextent = null;
	public ExtentReports report = null;
	public ExtentTest test = null;
	WebDriver driver;
	ServerReport log;
	private Help help;
	public Report(String reportPath,WebDriver driver) {
		this.driver=driver;
		htmlextent = new ExtentHtmlReporter(reportPath+"\\WatchListReport.html");

		report = new ExtentReports();
		//htmlextent.config().setReportName();
		report.attachReporter(htmlextent);
		log=new ServerReport();
	}
	
	public void createTest(String testName) {
		test=report.createTest(testName);
		
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
		log.serverLog("Screenshot path : "+screenshotPath, ServerLogMode.DEBUG);
		try {
			test.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void failReport() {
		printLog("Element Name : "+ExceptionHandling.elementName, false);
		printLog("Exception Name : "+ExceptionHandling.exceptionString, false);
		addScreenshot(ExceptionHandling.failTaskScreenshot);
	}
	
	public void logFlush() {
		
		report.flush();
	}
	
	public void print(List<String> printList) {
		
		help = new Help();
		int i = -1;
		for (String msg : printList) {
			i++;
			if (msg != null) {
				String[] array = help.separater(msg, "-");
				if (msg.contains("-")) {
					if (array[1].equalsIgnoreCase("PASS")) {
						test.log(Status.PASS, array[0]);
					} else {
						test.log(Status.FAIL, array[0]);
					}
				} else if (msg.contains("@@>")) {
					Reporter.log(msg, true);
					test.log(Status.INFO, "<b>===" + msg + "===</b>");
				} else if (msg.contains("Screenshort")) {
					help.screenshotFullPath(msg, test);
				} else if (msg.equalsIgnoreCase("No")) {
					continue;
				} else {
					test.log(Status.INFO, array[0]);
				}
			}
		}
		}
}
