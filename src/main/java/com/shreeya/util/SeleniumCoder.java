package com.shreeya.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers.IntArraySerializer;
import com.google.common.base.Function;
import com.shreeya.FunctionKeyword;

public class SeleniumCoder extends ExceptionHandler{

	static Logger log = Logger.getLogger(SeleniumCoder.class.getName());
	 WebDriver driver=null;
	
	ExtendReporter report=new ExtendReporter();
	int maximumDelay=10;
	private long explicityWaitCount=20;
	public static String elementNameError="no element";
	public SeleniumCoder(WebDriver driver) {
		
		this.driver=driver;
	}
	
	
	
	public void sendKey(WebElement element,String msg,String elementName) throws InterruptedException {
		/* Thread.sleep(2000); */
		
		element.sendKeys(msg);
		System.out.println("elementName : "+elementName+" msg : "+msg);
		
	}
	
	public void sendKeyClickOnDownArrow(WebElement element,String msg) {
		
			element.sendKeys(msg);
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.ENTER);
			System.out.println("element : "+element+" msg : "+msg);
			
	}
	
	public void clickElement(WebElement element,String elementName) throws InterruptedException {
		Thread.sleep(500);
		try {
		if(element.isEnabled()==true) {
			element.click();
			Reporter.log(elementName+" Click ",true);
		}else {
			Reporter.log(elementName+" no present",true);
			WebDriverWait wait = new WebDriverWait(driver, explicityWaitCount);
			Reporter.log("Checking element visible or not "+ elementName,true);
			wait.until(ExpectedConditions.visibilityOf(element)); 
			Reporter.log("Checking element clickable or not "+ elementName,true);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Reporter.log("After explicityWait : "+elementName, true);
			element.click();
		}
		}catch(ElementNotInteractableException e) {
			System.out.println("Convert driver into javascript than click on element  "+elementName);
			//convertInJavaScriptAndClick(element);
			WebDriverWait wait = new WebDriverWait(driver, explicityWaitCount);
			wait.until(ExpectedConditions.visibilityOf(element)); 
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Reporter.log("After explicityWait : "+elementName, true);
			element.click();
		}catch(TimeoutException e1) {
			Reporter.log("TimeoutException for this  "+elementName,true);
			ExtendReporter reporter=new ExtendReporter();
			/*
			 * try { reporter.abnormalErrorHandling(driver); } catch (IOException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
		}
		
		
	}
	
	public void downErrorKeyEnter(WebElement element) {
		Reporter.log("Click Down Error Button\nClick Enter Button");
		element.sendKeys(Keys.DOWN);
		element.sendKeys(Keys.ENTER);
	}
	
	public void clearAndSendKey(WebElement element,String msg,String elementName) {
		
		/* try { */
		element.clear();
		element.sendKeys(msg);
		System.out.println("elementName : "+elementName+" msg : "+msg);
		/*}catch(ElementNotInteractableException e) {
			elementNameError=elementName;
			Reporter.log("<b>Exception Name : </b>"+e.toString()+"<br><b>Exception location : </b>"+e.getStackTrace()[0]+"<br><b>Element Name : </b>"+elementName);
		}*/
		
	}
	
	public WebElement fluentWaitMethod(WebDriver driver,final String xpathStr) throws InterruptedException {
		Thread.sleep(3000);
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver) 
		       .withTimeout(maximumDelay, TimeUnit.SECONDS)
		       .pollingEvery(20, TimeUnit.SECONDS)
		       .ignoring(NoSuchElementException.class);
		   WebElement element=driver.findElement(By.xpath(xpathStr));
		   String value=element.getAttribute("innerHTML");
		   System.out.println("Innter html :: "+value);

		   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		     public WebElement apply(WebDriver driver) {
		       return driver.findElement(By.xpath(xpathStr));
		     }
		   });
		 
		return element;

	}
	
	public WebElement fluentWaitMethodID(WebDriver driver,final String idString) {
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		WebElement element = null;
		try {
		   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		       .withTimeout(maximumDelay, TimeUnit.SECONDS)
		       .pollingEvery(3, TimeUnit.SECONDS)
		       .ignoring(NoSuchElementException.class);
		   System.out.println("fluentWaitMethodID :: ");
		    element=driver.findElement(By.id(idString));
		   String value=element.getAttribute("id");
		   System.out.println("Innter html :: "+value);

		   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
		     public WebElement apply(WebDriver driver) {
		       return driver.findElement(By.id(idString));
		     }
		   });
		}catch(TimeoutException e) {
			Reporter.log("fluentWaitMethodID", true);
		}
		return element;

	}
	
	public String fetchTextFromElement(WebElement element,String elementName) {
		String elementText="no element text";
		try {
			elementText=element.getAttribute("innerHTML");
			Reporter.log(elementName+" : "+elementText, true);
			
		}catch(Exception e) {
			Reporter.log(elementName+" not found", true);
			Reporter.log(e.getMessage(), true);
		}
		return  elementText;
	}
	

	
	public boolean elementPresentOrNot(WebDriver driver,String xpathString,String attributeForXpath,String elementName) {
		boolean displayFlag=false;
		WebElement element = null;
		try {
			if(attributeForXpath.equalsIgnoreCase("xpath"))
				element=fluentWaitCodeXpath(driver,xpathString,10,elementName);
		if(element.isDisplayed())
			displayFlag=true;
		}catch(NoSuchElementException e) {
			System.out.println(e);
		}catch(TimeoutException e) {
			System.out.println(e);
		}catch(ElementNotVisibleException e) {
			System.out.println(e);
		}
		return displayFlag;
			
			
	}
	
	public boolean elementPresentOrNot(WebDriver driver,WebElement element) {
		boolean displayFlag=false;
		
		try {
			
		if(element.isDisplayed())
			displayFlag=true;
		}catch(NoSuchElementException e) {
			System.out.println(e);
		}catch(TimeoutException e) {
			System.out.println(e);
		}catch(ElementNotVisibleException e) {
			System.out.println(e);
		}catch(StaleElementReferenceException e) {
			System.out.println(e);
			displayFlag=false;
		}
		return displayFlag;
			
			
	}
	public  WebElement fluentWaitCodeId(WebDriver driver,final String idString,String elementName) {
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		 WebElement element=null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(maximumDelay, TimeUnit.SECONDS)
			       .pollingEvery(3, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
		try {
			    element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       //WebElement searchTextField =driver.findElement(By.name("q"));
			    	 WebElement element =driver.findElement(By.id(idString));
			       if(element.isEnabled()) {
			    	   System.out.println("Element Found");
			    	   
			       }
			       return element;
			     }
			     
			   });
		}catch(TimeoutException e) {
			timeOutExceptionHandler(elementName);
			elementNameError=elementName;
			Reporter.log("<b>Exception Name : </b>"+e.toString()+"<br><b>Exception location : </b>"+e.getStackTrace()[0]+"<br><b>Element Name : </b>"+elementName);
			
		}
			   
			   return element;
		   
	}
	
	public  WebElement fluentWaitCodeXpath(WebDriver driver,final String xpathString,String elementName) {
		 WebElement element=null;
		try {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(maximumDelay, TimeUnit.SECONDS)
			       .pollingEvery(3, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class,StaleElementReferenceException.class);
		
			element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       //WebElement searchTextField =driver.findElement(By.name("q"));
			    	 WebElement element =driver.findElement(By.xpath(xpathString));
			       if(element.isEnabled()) {
			    	   
			    	   
			       }else {
			    	   System.out.println("Element not Found");
			       }
			       return element;
			     }
			     
			   });
		
		}catch(TimeoutException e)  {
			Reporter.log("Timeout exception "+elementName, true);
			//timeOutExceptionHandler(elementName);
			elementNameError=elementName;
			Reporter.log("<b>Exception Name : </b>"+e.toString()+"<br><b>Exception location : </b>"+e.getStackTrace()[0]+"<br><b>Element Name : </b>"+elementName);
		}catch(StaleElementReferenceException e)  {
			Reporter.log("Timeout exception "+elementName, true);
			//timeOutExceptionHandler(elementName);
			elementNameError=elementName;
			Reporter.log("<b>Exception Name : </b>"+e.toString()+"<br><b>Exception location : </b>"+e.getStackTrace()[0]+"<br><b>Element Name : </b>"+elementName);
		}catch(ElementNotInteractableException e)  {
			Reporter.log("Timeout exception "+elementName, true);
			//timeOutExceptionHandler(elementName);
			elementNameError=elementName;
			Reporter.log("<b>Exception Name : </b>"+e.toString()+"<br><b>Exception location : </b>"+e.getStackTrace()[0]+"<br><b>Element Name : </b>"+elementName);
		}
			   return element;
		   
	}
	
	public  WebElement fluentWaitCodeXpath(WebDriver driver,final String xpathString,int maxWaitTime,String elementName) {
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(maxWaitTime, TimeUnit.SECONDS)
			       .pollingEvery(1, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);

			   WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       //WebElement searchTextField =driver.findElement(By.name("q"));
			    	 WebElement element =driver.findElement(By.xpath(xpathString));
			       if(element.isEnabled()) {
			    	   System.out.println("Element Found");
			    	   
			       }
			       return element;
			     }
			     
			   });
			   
			   return element;
		   
	}
	
	public void selectRadioButton(WebElement element,String nameElement) {
		if(element.isDisplayed()) {
			element.click();
		}else {
			System.out.println(nameElement+" is already selected");
		}
	}
	
	
	public void convertInJavaScriptAndClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
			   
			  
	public void hoverAndClickOption(WebDriver driver,String parentElementStr,String childElementStr) {
		Reporter.log("Click on Buy/Sell button and click on place order link", true);
		WebElement childElement=null;
		WebElement parentElement=fluentWaitCodeXpath(driver,parentElementStr,"ParentElement");
		Actions action = new Actions(driver);
		action.moveToElement(parentElement).click().perform();
		 childElement=fluentWaitCodeXpath(driver,childElementStr,"Child Element");
		
		childElement.click();
	}
	
	protected List<WebElement> FluentWaitForElementList(final String xapthString,final WebDriver driverI) 
    {
        final WebDriver driver=driverI;
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(maximumDelay, TimeUnit.SECONDS);
        fluentWait.pollingEvery(3, TimeUnit.SECONDS);
        fluentWait.ignoring(NoSuchElementException.class);
        

        List<WebElement> list= fluentWait.until(new Function<WebDriver,List<WebElement>>()
                {
                    public List<WebElement> apply(WebDriver input) {
                        // TODO Auto-generated method stub
                        List<WebElement> list = driver.findElements(By.xpath(xapthString));

                        if(list.size() > 0)
                            throw new NoSuchElementException("List is not loaded");
                        else
                            return list;
                    }

                });
        return list;
    }

	public  WebElement fluentWaitCodeId(WebDriver driver,final String idString,int maxDelay) {
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		 WebElement element=null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(maxDelay, TimeUnit.SECONDS)
			       .pollingEvery(3, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
	
			    element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       //WebElement searchTextField =driver.findElement(By.name("q"));
			    	 WebElement element =driver.findElement(By.id(idString));
			       if(element.isEnabled()) {
			    	   System.out.println("Element Found");
			    	   
			       }
			       return element;
			     }
			     
			   });
		
			   
			   return element;
		   
	}
	
	public  WebElement fluentWaitCodeName(WebDriver driver,final String idString,int maxDelay) {
		// Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 5 seconds.
		 WebElement element=null;
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			       .withTimeout(maxDelay, TimeUnit.SECONDS)
			       .pollingEvery(3, TimeUnit.SECONDS)
			       .ignoring(NoSuchElementException.class);
	
			    element = wait.until(new Function<WebDriver, WebElement>() {
			     public WebElement apply(WebDriver driver) {
			       //WebElement searchTextField =driver.findElement(By.name("q"));
			    	 WebElement element =driver.findElement(By.name(idString));
			       if(element.isEnabled()) {
			    	   System.out.println("Element Found");
			    	   
			       }
			       return element;
			     }
			     
			   });
		
			   
			   return element;
		   
	}
	
	public void clickElementWithOutChecking(WebElement element,String elementName) throws InterruptedException {
		try {
			if(element.isSelected()) {
				Reporter.log(elementName+" is selected already...", true);
			}else {
			element.click();
			Reporter.log(elementName+" Click ",true);
			}
		
		}catch(ElementNotInteractableException e) {
			System.out.println("Convert driver into javascript than click on element  "+elementName);
			//convertInJavaScriptAndClick(element);
			explicityWaitMethod(element, elementName);
			Reporter.log("After explicityWait : "+elementName, true);
			element.click();
		}catch(TimeoutException e1) {
			Reporter.log("TimeoutException for this  "+elementName,true);
			ExtendReporter reporter=new ExtendReporter();
//			try {
//				//reporter.abnormalErrorHandling(driver);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}catch(StaleElementReferenceException e) {
			Reporter.log(e.getMessage(), true);
			explicityWaitMethod(element, elementName);
			Reporter.log(elementName+" is selected already...", true);
			
			if(element.isSelected()) {
				Reporter.log(elementName+" is selected already...", true);
			}else {
			element.click();
			Reporter.log(elementName+" Click ",true);
			}
		}
		
		
		
	}
	
	public void explicityWaitMethod(WebElement element,String elementName) {
		WebDriverWait wait = new WebDriverWait(driver, explicityWaitCount);
		wait.until(ExpectedConditions.visibilityOf(element)); 
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Reporter.log(elementName+" is selected already...", true);
	}
	
	
	
}
