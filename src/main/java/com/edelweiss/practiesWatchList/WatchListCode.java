package com.edelweiss.practiesWatchList;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.edelweiss.experiment.Report;
import com.edelweiss.model.WatchListModel;
import com.edelweiss.util.ScreenshortProvider;

public class WatchListCode extends WebCoder{
	
	WebDriver driver;
	WatchListKeyword keyword;
	List<String> detailList;

	public WatchListCode(WebDriver driver) {
		super(driver);
		this.driver=driver;
		keyword=new WatchListKeyword();
		detailList=new ArrayList<String>();
	}
	
	public void addWatchList(WatchListModel model) {
		detailList.add("<@@=== Add WatchList ====@@>");
		log.serverLogBold("AddWatchList : ", ServerLogMode.NORMAL);
		clickAction("//span[text()='New Watchlist']", "New WatchList button");
		typeUsingJavaScript("//label[text()='Name Your Watchlist']//following::input[1]", "WatchList name textfield",model.getWatchListName());
		clickMe("//button[text()='Create']","Create button");
		addScript(model);
	}
	
	private void deleteWatchList() {
		// TODO Auto-generated method stub
		
	}
	
	private void addScript(WatchListModel model) {
		String xpathStr=xpathCreator("Add a Scrip to ", model.getWatchListName(), "AddScript title");
		sendKey("//h4[text()='"+xpathStr+"']//following::input[2]", "Add Script Textfield", model.getScriptName());
		clickMe(fluentWaitPartialLink(model.getScriptName().toUpperCase(), model.getScriptName()+" drop down option"), model.getScriptName()+" drop down option");
		clickMe("//button[text()='Add Scrip']", "Add script");
		detailList.add(fetchText("//button[text()='Ok']//preceding::p[1]", "popup msg"));
		clickMe("//button[text()='Ok']", "Ok");
		detailList.add(ScreenshortProvider.captureScreen(driver, "WatchList-"+model.getReferNo(), "WathchList"));
	}

	private void addScripts(WatchListModel model) {
		
		
	}

	public void watchListCodeExecute(WatchListModel model,Report report) {
		List<String> stepList=keyword.keyWordProvider();
		for(String step:stepList) {
			switch(step){
			case "AddWatchList":
				addWatchList(model);
				break;
			case "AddScript":
				addScripts(model);
				break;
			case "DeleteWatchList":
				deleteWatchList();
				break;
			case "Report":
				printReport(report);
				break;
			
			}
		}
	}

	private void printReport(Report report) {
		report.print(detailList);
	}

	

	
}
