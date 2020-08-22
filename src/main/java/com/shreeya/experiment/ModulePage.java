package com.shreeya.experiment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ModulePage extends WebCode{
	WebDriver driver;
	public ModulePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}

	public void executeModule(String module,String moduleName) {
		ModulePage page=new ModulePage(driver);
		page.click(page.locate(module,moduleName),moduleName);
		
	}
}
