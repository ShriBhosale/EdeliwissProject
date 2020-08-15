package com.shreeya;

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

import com.shreeya.model.LoginModel;
import com.shreeya.util.ConfigReader;
import com.shreeya.util.CsvReaderCode;
import com.shreeya.util.FolderStructure;
import com.shreeya.util.Help;

public class MyTestLauncher {
	public static ArrayList<LoginModel> loginData;
	public static String[] folderPath;
	static Help help=new Help();


	public static void main( String[] args ) throws IOException {
		Reporter.log("================<< Execution Started >>================");
		// TODO Auto-generated method stub
		
		CsvReaderCode csvReader = new CsvReaderCode(); 
		loginData =csvReader.LoginFileReader();
		ConfigReader reader=new ConfigReader();
		int noInstance=Integer.valueOf(reader.configReader("NumberInstance"));
		Iterator<LoginModel> loginIteratior = loginData.iterator();

		FolderStructure folderCreationObj = new FolderStructure();
		
		folderPath = folderCreationObj.reportFolderCreator();

		XmlSuite suite = new XmlSuite();
		suite.setName("TmpSuite");
		suite.setParallel(XmlSuite.ParallelMode.TESTS);
		suite.setThreadCount(noInstance);
		int count=0;
		 while(loginIteratior.hasNext()) {
			 LoginModel loginModel = loginIteratior.next(); 
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
		classes.add(new XmlClass(FunctionKeyword.class.getName()));
		test.setXmlClasses(classes) ;
		 }
		 List<XmlSuite> suites = new ArrayList<XmlSuite>();
		 suites.add(suite);
		 
		 TestNG tng = new TestNG();
		 
		 tng.setXmlSuites(suites);
		tng.setOutputDirectory(help.replaceActualPath(folderPath[0])+"\\TestNg");
		 tng.run(); 
		 
	}

}
