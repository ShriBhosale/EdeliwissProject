package com.edelweiss.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

public class BrowserLaunch {
	WebDriver driver = null;
	ConfigReader config;
	
	public BrowserLaunch() {
		config=new ConfigReader();
	}

	public WebDriver browserLaunch(String scenario) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\grid\\chromedriver.exe");
		killChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		//cash clear 2 line
		options.addArguments("disable-infobars");
		options.addArguments("start-maximized");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		//cash clear
		capabilities.setCapability("applicationCacheEnabled", false);
		//Hub hub = FunctionKeyword.getHub();
		if (!scenario.equalsIgnoreCase("Partial Order")) {

			try {
				/*
				 * driver = new RemoteWebDriver(new URL( "http://" + hub.getConfiguration().host
				 * + ":" + hub.getConfiguration().port + "/wd/hub"), options);
				 */
				driver= new ChromeDriver(capabilities);
				Reporter.log("if driver : "+driver, true);
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			/*
			 * driver = new RemoteWebDriver( new URL("http://" + hub.getConfiguration().host
			 * + ":" + hub.getConfiguration().port + "/wd/hub"), options);
			 */
			driver=new ChromeDriver(capabilities);
		}
		//((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		driver.manage().timeouts().pageLoadTimeout(150, TimeUnit.SECONDS);

		 //driver.get("https://www.google.com/");
		//driver.get("https://ewuat.edelbusiness.in/ewhtml/");
		//driver.get("https://ewuat.edelbusiness.in");
		//watchLsit only
		//driver.get("https://ewuat.edelbusiness.in/delta/");
		driver.get(config.configReader("URL"));
		Reporter.log("Execution driver : Browser lanuch : "+driver,true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Reporter.log("After browser : "+driver, true);
		Reporter.log("Browser Launch", true);
		return driver;
	}
	
	public void driverClose() {
		try {
			if(driver!=null)
				driver.close();
		}catch(NoSuchSessionException e) {
			Reporter.log("Driver already close", true);
		}
	}
	
	public void killChromeDriver() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}