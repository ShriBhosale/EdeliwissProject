package com.shreeya.practiesWatchList;

import org.testng.Reporter;

import com.shreeya.util.ConfigReader;

public class ServerReport {
	
	ConfigReader configReader;
	
	public ServerReport() {
		configReader=new ConfigReader();
		
	}
	
	public void serverLog(String msg,String mode) {
		String executionMode=configReader.configReader("ExecutionMode");
		if(executionMode.equalsIgnoreCase("Debug")||mode.equalsIgnoreCase("Normal")) 
			Reporter.log(msg, true);
				
	}
	
	public void serverLogBold(String msg,String mode) { 
			msg="<b>"+msg+"</b>";
			serverLog(msg, mode);	
	}
	
	public void serverLogInYellowColor(String msg,String mode) {
		msg="<b><font color='Yellow'>"+msg+"</font></b>";
		serverLog(msg, mode);	
				
	}
	



}
