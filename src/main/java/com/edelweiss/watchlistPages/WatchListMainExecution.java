package com.edelweiss.watchlistPages;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.edelweiss.MyTestLauncher;
import com.edelweiss.util.ExtendReporter;
import com.edelweiss.util.FolderStructure;
import com.edelweiss.util.SeleniumCoder;

public class WatchListMainExecution extends SeleniumCoder{

	WebDriver driver;
	WatchListExecution execution;
	WatchListExtraScenario extraScenario;
	WatchListSorting sorting;
	WatchListTestcase testcase;
	FolderStructure folderStructure;
	
	public WatchListMainExecution(WebDriver driver) {
		super(driver);
		this.driver=driver;
		execution=new WatchListExecution(driver);
		extraScenario=new WatchListExtraScenario(driver);
		sorting=new WatchListSorting(driver);
		testcase=new WatchListTestcase(driver);
		folderStructure=new FolderStructure();
	}
	
	public void watchListExecute(String segment) {
		Reporter.log("<b><font color='Yellow'>=========@@@@ WatchListExecute @@@@========</font></b>", true);
		//ExtendReporter reporter=new ExtendReporter(MyTestLauncher.reportFolderPath[0], "WatchList", 0);
		ExtendReporter reporter=new ExtendReporter("E:/EdelweissProject/DigitalWebPlatformAutomation/Watchlist", "WatchList", 0);

		
		  reporter=execution.watchListExecute(reporter);
			/*
			 * reporter=extraScenario.watchListExtraScenarioExecute(segment,reporter);
			 * reporter=sorting.sortingScenarioExecute(segment, reporter);
			 * 
			 * reporter=testcase.watchListTestcaseExecute(segment, reporter);
			 */

		 reporter.logFlush();
		folderStructure.copyFolderThenDelete("E:/EdelweissProject/DigitalWebPlatformAutomation/Watchlist", MyTestLauncher.reportFolderPath[0]);
		 
	}
}
