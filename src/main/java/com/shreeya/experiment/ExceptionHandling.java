package com.shreeya.experiment;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class ExceptionHandling {
	
	Report report;
	WebDriver driver;
	static String elementName;
	static String exceptionString;
	
	public ExceptionHandling(WebDriver driver) {
		this.driver=driver;
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
		Reporter.log("<b>Exception Name : </b>" + e.toString() + "<br><b>Element Name : </b>" + elementName,true);
		Reporter.log("<b>Exception location : </b>", true);
		for(StackTraceElement st:locaString) {
			if(st.toString().contains("com.shreeya")) {
				System.out.println("<br>"+st.toString());
			}
		}
	}
	
	public void exceptionHandler(String exceptionName,String elementName,String action,WebElement element,Exception e) {
		this.elementName=elementName;
		this.exceptionString=exceptionName;
		report=new Report();
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
			
		}
	}
	
}
