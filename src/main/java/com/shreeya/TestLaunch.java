package com.shreeya;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;

import com.opencsv.CSVWriter;
import com.shreeya.model.LoginModel;
import com.shreeya.model.TestDataModel;
import com.shreeya.orderdetailpages.CxlOrderPage;
import com.shreeya.orderdetailpages.LoginPage;
import com.shreeya.orderdetailpages.ModOrderPage;
import com.shreeya.orderdetailpages.NewOrderPage;
import com.shreeya.orderdetailpages.PartialOrderPage;
import com.shreeya.util.BrowserLaunch;
import com.shreeya.util.CsvReaderCode;
import com.shreeya.util.ExtendReporter;
import com.shreeya.util.HelperCode;


public class TestLaunch {

	 WebDriver driver1;
	static Iterator<TestDataModel> csvTestDataModelIterator;
	static Iterator<LoginModel>loginIterator;
	static TestDataModel model,testModel;
	CsvReaderCode coder;
	LoginPage login;
	ExtendReporter reporter;
	String [] orderDetailArray;
	//CSVWriter writer;
	private String newOrderStatus="rejected";
	NewOrderPage newOrder;
	ModOrderPage modOrder;
	CxlOrderPage cxlOrder;
	int countOfrejectNew=0;
	static int rowNo=0;
	private boolean partialOrderReport;
	private CSVWriter writer;
	//ArrayList<ExecutionModel> loginModelList;
	WebDriver driver;

	
	public TestLaunch() throws IOException {
		/*
		 * coder = new CsvReaderCode(); writer = coder.writerProvider();
		 * csvTestDataModelIterator = coder.testDataProvider();
		 * loginModelList=coder.LoginFileReader();
		 * loginIterator=loginModelList.iterator();
		 * 
		 * login = new LoginPage(driver); newOrder=new NewOrderPage(driver); modOrder =
		 * new ModOrderPage(driver); cxlOrder = new CxlOrderPage(driver);
		 */}
	
	
	public void Execution() throws InterruptedException, IOException {
		/*
		 * //ApacheCode excelWriter=new ApacheCode(); HashMap<WebDriver,String>
		 * mapObject=new HashMap<WebDriver,String>(); HashMap<WebDriver,String>
		 * newMapObject=new HashMap<WebDriver,String>(); PartialOrderPage
		 * partialOrderOb=new PartialOrderPage(driver); HelperCode helperObject=new
		 * HelperCode(); LoginModel loginModel=new LoginModel(); int orderNo=0;
		 * while(loginIterator.hasNext()) { loginModel=loginIterator.next();
		 * login.loginExecution("Normal",loginModel); //login.headerInExcel(writer);
		 * 
		 * String timeStamp=helperObject.timeStampGenerator(); //reporter=new
		 * ExtendReporter(timeStamp,"dfsf","jgug",1);
		 * 
		 * while (csvTestDataModelIterator.hasNext() &&(driver1!=null)) { model =
		 * csvTestDataModelIterator.next(); orderNo++; int
		 * startExecution=Integer.valueOf(loginModel.getStartingRowNo()); int
		 * endExecution=Integer.valueOf(loginModel.getEndRowNo());
		 * System.out.println("endExecution ================================@> "
		 * +endExecution+"\nOrderNo ==========@> "+orderNo); if(orderNo>=endExecution+1)
		 * break; if(orderNo>=startExecution) { if(orderNo==startExecution) { rowNo=0; }
		 * 
		 * rowNo++; System.out.println("Order No ========================@> "
		 * +orderNo+"\nStartExecutionNo =======================@> "+startExecution);
		 * if(model.getScenario().equalsIgnoreCase("Partial Order")){
		 * if(!newOrderStatus.equalsIgnoreCase("rejected")||newOrderStatus.
		 * equalsIgnoreCase("put order req received")){
		 * partialOrderOb.partialOrderExecution(model, orderNo,loginModel);
		 * partialOrderOb.orderDetail(driver1, model,orderNo); model =
		 * csvTestDataModelIterator.next(); orderNo++; orderNo++; }else continue;
		 * 
		 * } if (model.getAction().equalsIgnoreCase("New")&&(!model.getScenario().
		 * equalsIgnoreCase("Partial Order"))) {
		 * System.out.println("TestLaunch::Action :: "+model.getAction()
		 * +"\n Scenario :: "+model.getScenario());
		 * newMapObject=newOrder.newOrderExecution(model,driver1,orderNo); //
		 * newOrderStatus = helperObject.statusRemoveBracket(newMapObject.values());
		 * if(newOrderStatus.equalsIgnoreCase("rejected")) { countOfrejectNew++; } }
		 * else if (model.getAction().equalsIgnoreCase("Mod")) { // newOrderStatus =
		 * helperObject.statusRemoveBracket(newMapObject.values());
		 * System.out.println("TestLaunch::Action :: "+model.getAction());
		 * if(!newOrderStatus.equalsIgnoreCase("rejected")) {
		 * mapObject=modOrder.modExecution(model,driver1,orderNo,newOrderStatus); }else
		 * { continue; }
		 * 
		 * } else if (model.getAction().equalsIgnoreCase("Cxl")) { String modOrderStatus
		 * = helperObject.statusRemoveBracket(mapObject.values());
		 * if(modOrderStatus.equalsIgnoreCase("complete"))
		 * newOrderStatus=modOrderStatus; else //newOrderStatus =
		 * helperObject.statusRemoveBracket(newMapObject.values());
		 * System.out.println("TestLaunch::Action :: "+model.getAction());
		 * if(!newOrderStatus.equalsIgnoreCase("rejected")) {
		 * mapObject=cxlOrder.cxlExecution(driver1,orderNo,newOrderStatus,model); }else
		 * { continue; } }
		 * 
		 * 
		 * System.out.println("Action ====> "+model.getAction()
		 * +" newOrderStatus =====> "+newOrderStatus+"\nRowNo==============> "+rowNo);
		 * 
		 * String status=helperObject.outputProcessor(driver1, model.getAction(),
		 * orderNo, newOrderStatus, model,rowNo);
		 * if(model.getAction().equalsIgnoreCase("New")&&
		 * model.getScenario().equalsIgnoreCase("Fresh Order Placement")) {
		 * newOrderStatus=status; } if(countOfrejectNew==4) {
		 * 
		 * break; }
		 * 
		 * 
		 * } } if(driver1 != null) { helperObject.outputProcessor(driver1,
		 * model.getAction(), orderNo, "Terminate", model,rowNo); login.logout(driver1);
		 * driver1.close(); }else {
		 * 
		 * }
		 * 
		 * //coder.closeWriteFile(writer);
		 * 
		 * //excelWriter.closeExcelWriting(); //reporter.logFlush(); }
		 */}
	
	
	public void loginData() throws IOException {
		CsvReaderCode reader=new CsvReaderCode();
		//loginIterator=reader.LoginFileReader();
		LoginModel model=loginIterator.next();
	}
	
	public static  void main(String[] args) throws InterruptedException, IOException {
		TestLaunch testObject=new TestLaunch();
		testObject.Execution();
	}
	
	
}
