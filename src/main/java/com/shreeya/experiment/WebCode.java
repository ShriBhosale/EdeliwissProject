package com.shreeya.experiment;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class WebCode {

	WebDriver driver;
	ExceptionHandling handle;
	WebElement element=null;
	public WebCode(WebDriver driver) {
		this.driver=driver;
		handle=new ExceptionHandling(driver);
	}
	
	public WebElement locate(String name,String elementName) {
		Reporter.log("locate Driver : "+driver, true);
		
		try {
		 element=driver.findElement(By.xpath(name));
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "locate", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "locate", element,e);
		}
		return element;
	}
	
	public void click(WebElement element,String elementName) {
		Reporter.log("click :  Driver ======>  "+driver,true);
		try {
			//new WebDriverWait(driver, 50).ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		
		}catch(StaleElementReferenceException e) {
			System.out.println("StaleElementReferenceException");
			handle.exceptionHandler("StaleElementReferenceException", elementName,"click",element,e);
		}
	}
	
	public void sendKey(WebElement element,String msg) {
		element.sendKeys(msg);
	}
}
