package com.edelweiss.practiesWatchList;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.edelweiss.experiment.ExceptionHandling;
import com.edelweiss.experiment.Report;

public class WebCoder {
	WebDriver driver;
	ExceptionHandling handle;
	ServerReport log;

	public WebCoder(WebDriver driver) {
		this.driver=driver;
		handle=new ExceptionHandling(driver);
		log=new ServerReport();
	}
	
	public WebElement  fluentWaitXpath(final String xpathStr,String elementName) {
		WebElement element=null;
		try {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(100, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		 //element = driver.findElement(By.xpath(xpathStr));
		element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathStr));
			}
		});
		}catch(TimeoutException e) {
			handle.exceptionHandler("TimeoutException", elementName, "Locate", element, e);
		}
		if(element==null)
			log.serverLog("elementName is not found", ServerLogMode.NORMAL);

		return element;
	}
	
	public WebElement  fluentWaitXpath(final String xpathStr,String elementName,int waitTime) {
		WebElement element=null;
		try {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(waitTime, TimeUnit.SECONDS)
				.pollingEvery(20, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		 

		element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathStr));
			}
		});
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "Locate", element, e);
		}
		if(element==null)
			log.serverLog(elementName+" is not found", ServerLogMode.NORMAL);

		return element;
	}
	
	public WebElement  fluentWaitPartialLink(final String patialLink,String elementName) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(100, TimeUnit.SECONDS)
				.pollingEvery(20, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		/*
		 * WebElement element = driver.findElement(By.partialLinkText(patialLink));
		 * String value = element.getAttribute("innerHTML");
		 * System.out.println("Innter html :: " + value);
		 */

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.partialLinkText(patialLink));
			}
		});
		
		if(element==null)
			log.serverLog("elementName is not found", ServerLogMode.NORMAL);

		return element;
	}
	
	public void clickMe(WebElement element,String elementName) {
		try {
			if(elementTillDisplay(element,elementName)) {
				element.click();
				log.serverLog("Click "+elementName, ServerLogMode.NORMAL);
			}else
				log.serverLog(elementName+" is not display",ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "click", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "click", element,e);
		}
	}
	
	
	public void sendKey(String xpath,String elementName,String msg) {
		WebElement element=fluentWaitXpath(xpath,elementName);
		try {
			if(elementTillDisplay(element,elementName)) {
			element.sendKeys(msg);
			log.serverLog(elementName+" : "+msg, ServerLogMode.NORMAL);
			}else
				log.serverLog(elementName+" is not display",ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "SendKeys", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "SendKeys", element,e);
		}catch(ElementNotInteractableException e) {
			handle.exceptionHandler("ElementNotInteractableException", elementName, "SendKeys", element,e);
		}
	}
	
	
	
	public void clickMe(String xpath,String elementName) {
		WebElement element=fluentWaitXpath(xpath,elementName);
		try {
			if(elementTillDisplay(element,elementName)) {
				element.click();
				log.serverLog("Click "+elementName+" button", ServerLogMode.NORMAL);
			}else 
				log.serverLog(elementName+" is not display", ServerLogMode.NORMAL);
			
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "click", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "click", element,e);
		}catch(ElementNotInteractableException e) {
			handle.exceptionHandler("ElementNotInteractableException", elementName, "SendKeys", element,e);
		}
	}
	
	public void hoverAndClickOption(String parentElementStr, String childElementStr,String childElementName)  {
		log.serverLog("click on Buy/Sell button and click on "+childElementName+" link", ServerLogMode.NORMAL);
		
		WebElement childElement = null;
		WebElement parentElement = fluentWaitXpath(parentElementStr,"Buy/Sell");
		Actions action = new Actions(driver);
		action.moveToElement(parentElement).click().perform();
		log.serverLog("click on Buy/Sell link", ServerLogMode.NORMAL);
		childElement = fluentWaitXpath(childElementStr,"childElement");
		staticWait(500);
		
		clickMe(childElement,childElementName);
		
	}
	
	public void clickAction(String xpath,String elementName) {

		WebElement element=fluentWaitXpath(xpath,elementName);
		try {
			if(elementTillDisplay(element,elementName)) {
			Actions action=new Actions(driver);
			action.moveToElement(element).click().perform();
			}else
				log.serverLog(elementName+" is not display",ServerLogMode.NORMAL);
			log.serverLog("Move to element and than click "+elementName, ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "click", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "click", element,e);
		}catch(ElementNotInteractableException e) {
			handle.exceptionHandler("ElementNotInteractableException", elementName, "SendKeys", element,e);
		}
	
	}
		
	private void staticWait(int waitTime) {
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void redirectToWatchListModule(boolean pageRefershOrNot,String childElementName,Report report) {
		log.serverLog("====> redirectToWatchListModule <====", ServerLogMode.DEBUG);
		staticWait(1000);
		
		  WebElement closePopupButton = fluentWaitXpath("//a[@class='ed-icon i-close lg']","closePopup");
		  if(closePopupButton!=null)
			  clickMe(closePopupButton, "Close popup button");
		try {
		
		staticWait(300);
		try {
		hoverAndClickOption("//*[@id='QuickSB']", "//li//a[text()='My Watchlist']",childElementName);
		}catch(ElementNotInteractableException e) {
			refreshPage();
			hoverAndClickOption("//*[@id='QuickSB']", "//li//a[text()='My Watchlist']",childElementName);
		}
		
		WebElement watchlistTitle=fluentWaitXpath("//h3[text()='Watchlist']","watchListTitle",200);
		if(watchlistTitle==null) {
			hoverAndClickOption("//*[@id='QuickSB']", "//li//a[text()='My Watchlist']",childElementName);
		}
		if(pageRefershOrNot)
			refreshPage();
		}catch(NullPointerException e) {
			report.createTest("FailReport");
			report.failReport();
		}catch(NoSuchElementException e) {
			report.createTest("FailReport");
			report.failReport();
		}
	}
	
	public void refreshPage() {
		log.serverLog("Page refresh...", ServerLogMode.NORMAL);
		driver.navigate().refresh();
	}
	
	public String xpathCreator(String xpath,String xpathPart,String elementName) {
		xpath=xpath+xpathPart;
		log.serverLog(elementName+" xpath creator : "+xpath, ServerLogMode.DEBUG);
		return xpath;
	}
	
	public String fetchText(String xpath,String elementName) {
		WebElement element = fluentWaitXpath(xpath, elementName);
		String text=null;
		try {
		 text=element.getText();
		log.serverLog("Element name : "+elementName+"fetch text : "+text, ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "click", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "click", element,e);
		}
		return text;
	}
	
	public boolean elementTillDisplay(WebElement element,String elementName) {
		boolean elementDisplay=false;
		int count=0;
		do {
			staticWait(100);
			count++;
			if(element.isDisplayed()) {
				elementDisplay=true;
				log.serverLog(elementName+" is display", ServerLogMode.DEBUG);
				break;
				
			}
			
		}while(count<10);
		if(elementDisplay==false)
			log.serverLog("count : "+count+" "+elementName+" not display...", ServerLogMode.DEBUG);
		return elementDisplay;
	}
	
	public void typeUsingJavaScript(String xpath,String elementName,String msg) {
		// Initialize JS object
		WebElement element=fluentWaitXpath(xpath, elementName);
		
		try {
			if(elementTillDisplay(element,elementName)) {
				element.click();
				element.sendKeys(msg);
			}else
				log.serverLog(elementName+" is not display",ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "SendKeys", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "SendKeys", element,e);
		}catch(ElementNotInteractableException e) {
			handle.exceptionHandler("ElementNotInteractableException", elementName, "SendKeys", element,e);
		}
	}
	
	public void sendKeyAction(String xpath,String elementName,String msg) {
		
		WebElement element=fluentWaitXpath(xpath,elementName);
		try {
			staticWait(2000);
			if(elementTillDisplay(element,elementName)) {
			Actions action=new Actions(driver);
			 action.moveToElement(element).sendKeys(msg).perform(); 
			
			log.serverLog("sendkey done", ServerLogMode.NORMAL);
			}else
				log.serverLog(elementName+" is not display",ServerLogMode.NORMAL);
			log.serverLog("Move to element and than click "+elementName, ServerLogMode.NORMAL);
		}catch(InvalidSelectorException e) {
			handle.exceptionHandler("InvalidSelectorException", elementName, "click", element,e);
		}catch(NoSuchElementException e) {
			handle.exceptionHandler("NoSuchElementException", elementName, "click", element,e);
		}catch(ElementNotInteractableException e) {
			handle.exceptionHandler("ElementNotInteractableException", elementName, "SendKeys", element,e);
		}
	
	}
}
