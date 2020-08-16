package com.shreeya.experiment;

import org.openqa.selenium.WebDriver;

import com.shreeya.util.BrowserLaunch;

public class Execution {
	static WebDriver driver;
	static String dot=".";
	public static void main(String[] args) {
		BrowserLaunch lanuch =new BrowserLaunch();
		driver=lanuch.browserLaunch("Normal");
		Report report=new Report();
		report.createTest("Simple");
		report.printLog("Late check1");
		report.addScreenshot(dot+report.captureScreen(driver));
		driver.close();
		report.logFlush();
		
	}
	
}
