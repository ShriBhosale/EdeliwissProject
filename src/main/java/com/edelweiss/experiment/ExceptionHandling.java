package com.edelweiss.experiment;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.edelweiss.practiesWatchList.ServerLogMode;
import com.edelweiss.practiesWatchList.ServerReport;
import com.edelweiss.practiesWatchList.ServerReport;
import com.edelweiss.util.ScreenshortProvider;

public class ExceptionHandling {
	
	Report report;
	WebDriver driver;
	static String elementName;
	static String exceptionString;
	ServerReport log;
	static String failTaskScreenshot;
	
	public ExceptionHandling(WebDriver driver) {
		this.driver=driver;
		log=new ServerReport();
	}
	
	public static void nullPointerHandler(String elementName) {
		System.out.println("Null pointer exception");
		
	}
	
	public void invalidSelectorExceptinHandly(WebElement element,String action,Exception e) {
		printException(e);
	}
	
	
	

	private void noSuchElementExceptionHandle(WebElement element, String action, Exception e) {
		
		printException(e);
	}

	private void staleElementExceptionHandler(String elementName,WebElement element,String action) {
		
		new WebDriverWait(driver, 50).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	public void printException(Exception e) {
		StackTraceElement [] locaString=e.getStackTrace();
		log.serverLog("<b>Exception Name : </b>" + e.toString() + "<br><b>Element Name : </b>" + elementName, ServerLogMode.DEBUG);
		log.serverLog("<b>Exception location : </b>", ServerLogMode.DEBUG);
		for(StackTraceElement st:locaString) {
			if(st.toString().contains("com.edelweiss")) {
				System.out.println("<br>"+st.toString());
				log.serverLog("<br>"+st.toString(), ServerLogMode.DEBUG);
			}
		}
	}
	
	private void elementNotInteractableException(WebElement element, String action, Exception e) {
		
		printException(e);
		element=null;
		element.toString();
		
	}
	
	public void exceptionHandler(String exceptionName,String elementName,String action,WebElement element,Exception e) {
		this.elementName=elementName;
		this.exceptionString=exceptionName;
		this.failTaskScreenshot=ScreenshortProvider.captureScreen(driver,elementName+"Fail To Locate");
		log.serverLog("ElementName : "+elementName, ServerLogMode.DEBUG);
		log.serverLog("Exception Name : "+exceptionString, ServerLogMode.DEBUG);
		//report=new Report(M);
		switch(exceptionName) {
		
		case "NullPointer":
			nullPointerHandler(elementName);
			break;
		case "StaleElementReferenceException":
			staleElementExceptionHandler(elementName,element,action);
				break;
		case "InvalidSelectorException":
			invalidSelectorExceptinHandly(element,action,e);
			break;
		case "NoSuchElementException":
			noSuchElementExceptionHandle(element,action,e);
			break;
		case "ElementNotInteractableException" :
			elementNotInteractableException(element,action,e);
			
		}
	}

	
	
}
