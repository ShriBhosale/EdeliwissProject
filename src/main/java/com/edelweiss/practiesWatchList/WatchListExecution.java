package com.edelweiss.practiesWatchList;

import java.util.Iterator;

import org.openqa.selenium.WebDriver;

import com.edelweiss.MyTestLauncher;
import com.edelweiss.experiment.Report;
import com.edelweiss.model.WatchListModel;
import com.edelweiss.util.CsvReaderCode;


public class WatchListExecution extends WebCoder{
	
	WebDriver driver;
	WatchListModel model;
	Iterator<WatchListModel> csvWatchListIterator;
	ServerReport log;
	Report report;
	WatchListCode watchListcode;
	
	public WatchListExecution(WebDriver driver) {
		super(driver);
		this.driver=driver;
		CsvReaderCode csvReader=new CsvReaderCode();
		log=new ServerReport();
		report=new Report(MyTestLauncher.reportFolderPath[1],driver);
		csvWatchListIterator=csvReader.WatchListTestDataProvider();
		watchListcode=new WatchListCode(driver);
	}
	
	public void watchListExecute() {
		log.serverLogInYellowColor("====> watchListExecute <====", ServerLogMode.DEBUG);
		try {
		redirectToWatchListModule(true,"My Watchlist",report);
		while(csvWatchListIterator.hasNext()) {
			
			model=csvWatchListIterator.next();
			report.createTest(model.getWatchListName()+"_"+model.getReferNo());
			watchListcode.watchListCodeExecute(model,report);
			
			
		}
		
		}catch(NullPointerException e) {
			report.failReport();
		}
		report.logFlush();
	}

}
