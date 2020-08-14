package com.shreeya.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.testng.Reporter;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.shreeya.model.AlertAndNotificationModel;
import com.shreeya.model.ExecutionModel;
import com.shreeya.model.FundTransferModel;
import com.shreeya.model.LoginModel;
import com.shreeya.model.LoginTestModel;
import com.shreeya.model.MasterTestModel;
import com.shreeya.model.TestDataModel;
import com.shreeya.model.WatchListModel;

public class CsvReaderCode {

	String responseString;
	static Iterator<TestDataModel> csvTestDataModelIterator;
	static Iterator<MasterTestModel> csvMasterTestModelIterator;
	static TestDataModel model;
	CSVWriter writer;
	HelperCode helperObject;

	public CsvReaderCode() {
		
	}
	
	public CSVWriter writerProvider()throws IOException {
		helperObject = new HelperCode();
		File file = new File("E:\\EdelweissProject\\Reports\\ReportInExcel\\ExcelReport"+ helperObject.timeStampGenerator() + ".csv");

		FileWriter outputfile = new FileWriter(file, true);

		return new CSVWriter(outputfile);
	}

	public TestDataModel testDataProvider(String loginReferNo) {
		ConfigReader configReader=new ConfigReader();
		String testDataPath=configReader.configReader("TestData")+"\\ScenarioData";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<TestDataModel> csvToBean = new CsvToBeanBuilder(reader).withType(TestDataModel.class).build();

		Iterator<TestDataModel> csvTestDataModelIterator = csvToBean.iterator();
		
		while(csvTestDataModelIterator.hasNext()) {
			TestDataModel testDataModel=csvTestDataModelIterator.next();
			if(testDataModel.getOrderNo().equalsIgnoreCase(loginReferNo)) return testDataModel;
		}
		return null;
	}
	
	//Order Detail FrameWork it it neccessary
	/*
	 * public int noRowInTestData(String loginReferNo) { System.out.
	 * println("****************************************Number no counting start***************************************************"
	 * ); TestDataModel m1; int i=0; Iterator<TestDataModel>
	 * csvTestDataModelIterator=testDataProvider(); while
	 * (csvTestDataModelIterator.hasNext()) { m1=csvTestDataModelIterator.next();
	 * i++; }
	 * 
	 * return i; }
	 */

	public void WriteFile(String[] orderDetailArray,CSVWriter writer) {

		writer.writeNext(orderDetailArray);

	}

	public void closeWriteFile(CSVWriter writer) throws IOException {
		writer.close();
	}

	public static void main(String[] args) throws IllegalStateException, InstantiationException, IllegalAccessException,
			CsvRequiredFieldEmptyException, IOException {
		CsvReaderCode coder = new CsvReaderCode();
		
	}

	private static void While(boolean hasNext) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<ExecutionModel> LoginFileReader() {
		ConfigReader configReader=new ConfigReader();
		//String testDataPath=configReader.configReader("TestData")+"\\Execution";
		String testDataPath=configReader.configReader("LoginData");
		CSVReader reader = null;
		System.out.println("Login Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<ExecutionModel> csvToBean = new CsvToBeanBuilder(reader).withType(ExecutionModel.class).build();
		ArrayList<ExecutionModel> arrayListObject=new ArrayList<ExecutionModel>();
		Iterator<ExecutionModel> csvTestDataModelIterator = csvToBean.iterator();
		
		  while(csvTestDataModelIterator.hasNext()) { 
			  ExecutionModel some=csvTestDataModelIterator.next(); arrayListObject.add(some);
		 // System.out.println(some.toString());
		  }
		 
		
		
		
		return arrayListObject;
	
	}

	public ListIterator<String> masterTestDataProvider(String module) {
		ConfigReader configReader=new ConfigReader();
		
		String testDataPath=configReader.configReader("TestData")+"\\MasterTest";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<MasterTestModel> csvToBean = new CsvToBeanBuilder(reader).withType(MasterTestModel.class).build();

		Iterator<MasterTestModel> csvMasterTestIterator = csvToBean.iterator();
		List<String> steplist=new ArrayList<String>();
		while(csvMasterTestIterator.hasNext()) {
			MasterTestModel masterTestModel=csvMasterTestIterator.next();
			if(masterTestModel.getKeyword().equalsIgnoreCase(module)) {
				Reporter.log(masterTestModel.getSteps(), true);	
				steplist.add(masterTestModel.getSteps());
			}
			
			
		}
		return steplist.listIterator();
	}
	
	public Iterator<LoginTestModel> loginTestDataProvider() {
		ConfigReader configReader=new ConfigReader();
		String testDataPath=configReader.configReader("TestData")+"\\Login";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<LoginTestModel> csvToBean = new CsvToBeanBuilder(reader).withType(LoginTestModel.class).build();

		Iterator<LoginTestModel> csvLoginTestIterator = csvToBean.iterator();
		
		
		return csvLoginTestIterator;
	}
	
	public LoginTestModel loginTestDataProvider( String referenceNo ) {
		ConfigReader configReader=new ConfigReader();
		String testDataPath=configReader.configReader("TestData")+"\\Login";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<LoginTestModel> csvToBean = new CsvToBeanBuilder(reader).withType(LoginTestModel.class).build();

		Iterator<LoginTestModel> csvLoginTestIterator = csvToBean.iterator();
		
			
		
		  while (csvLoginTestIterator.hasNext()) { LoginTestModel login =
		  csvLoginTestIterator.next();
		  if(login.getReference_no().equalsIgnoreCase(referenceNo))return login; }
		 
		
		
		return null;
	}
	
	public Iterator<FundTransferModel> FundTransferDataProvider() {
		ConfigReader configReader=new ConfigReader();
		
		String testDataPath=configReader.configReader("TestData")+"\\FundTransfer";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<FundTransferModel> csvToBean = new CsvToBeanBuilder(reader).withType(FundTransferModel.class).build();

		Iterator<FundTransferModel> csvFundTransferIterator  = csvToBean.iterator();
		List<String> steplist=new ArrayList<String>();
		
		return csvFundTransferIterator;
	}
	
	public Iterator<WatchListModel> WatchListTestDataProvider() {
		ConfigReader configReader=new ConfigReader();
		String testDataPath=configReader.configReader("TestData")+"\\WatchList";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<WatchListModel> csvToBean = new CsvToBeanBuilder(reader).withType(WatchListModel.class).build();

		Iterator<WatchListModel> csvWatchListTestIterator = csvToBean.iterator();
		
		
		return csvWatchListTestIterator;
	}
	
	public Iterator<AlertAndNotificationModel> alertAndNotificationTestDataProvider() {
		ConfigReader configReader=new ConfigReader();
		String testDataPath=configReader.configReader("TestData")+"\\AlertAndNotification";
		CSVReader reader = null;
		System.out.println("Test Data ======> "+testDataPath);
		try {
			reader = new CSVReader(new FileReader(testDataPath+".txt"), '\t');
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CsvToBean<AlertAndNotificationModel> csvToBean = new CsvToBeanBuilder(reader).withType(AlertAndNotificationModel.class).build();

		Iterator<AlertAndNotificationModel> csvWatchListTestIterator = csvToBean.iterator();
		
		
		return csvWatchListTestIterator;
	}
}
