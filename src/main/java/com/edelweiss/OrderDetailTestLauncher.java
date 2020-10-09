package com.edelweiss;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.edelweiss.model.LoginModel;
import com.edelweiss.model.OrderDetailLoginModel;
import com.edelweiss.util.ConfigReader;
import com.edelweiss.util.CsvReaderCode;
import com.edelweiss.util.FolderStructure;
import com.edelweiss.util.Help;
public class OrderDetailTestLauncher {
	public static ArrayList<OrderDetailLoginModel> loginData;
	public static FolderStructure folderCreationObj;
	public static String [] reportFolderPath;
	public static String[] folderPath;
	static Help help=new Help();
	
	public OrderDetailTestLauncher() {
		
	}


	public static void main( String[] args ) throws IOException {
		Reporter.log("================<< Execution Started >>================");
		// TODO Auto-generated method stub
		folderCreationObj = new FolderStructure();
		 reportFolderPath = folderCreationObj.reportFolderCreator();
		CsvReaderCode csvReader = new CsvReaderCode(); 
		loginData =csvReader.LoginFileReaderOD();
		ConfigReader reader=new ConfigReader();
		int noInstance=Integer.valueOf(reader.configReader("NumberInstance"));
		Iterator<OrderDetailLoginModel> loginIteratior = loginData.iterator();

		FolderStructure folderCreationObj = new FolderStructure();
		
		folderPath = folderCreationObj.reportFolderCreator();

		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		suite.setParallel(XmlSuite.ParallelMode.TESTS);
		suite.setThreadCount(noInstance);
		int count=0;
		 while(loginIteratior.hasNext()) {
			 OrderDetailLoginModel loginModel = loginIteratior.next(); 
			 Reporter.log("MyTestLauncher "+loginModel.toString(), true);
			 Map<String,String> testScenarioParameters = new HashMap<>();
			 testScenarioParameters.put("Reference", loginModel.getReferNo());
			 testScenarioParameters.put("UserId", loginModel.getUserId());
			 testScenarioParameters.put("Pwd", loginModel.getPassword());
			 testScenarioParameters.put("Yob", loginModel.getYob());
			 testScenarioParameters.put("StartNo", loginModel.getStartingRowNo());
			 testScenarioParameters.put("EndNo", loginModel.getEndRowNo());
			 testScenarioParameters.put("Module", loginModel.getModule());
			 testScenarioParameters.put("Execute", loginModel.getExecute());
		XmlTest test = new XmlTest(suite);
		test.setName(loginModel.getReferNo());
		test.setParameters(testScenarioParameters);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass(OrderDetailFunction.class.getName()));
		test.setXmlClasses(classes) ;
		 }
		 List<XmlSuite> suites = new ArrayList<XmlSuite>();
		 suites.add(suite);
		 
		 TestNG tng = new TestNG();
		 
		 tng.setXmlSuites(suites);
		tng.setOutputDirectory(reportFolderPath[0]+"/SeverLog");
		 tng.run(); 
		 
	}



}
