package com.edelweiss.model;


import com.opencsv.bean.CsvBindByName;

public class OrderDetailLoginModel {
	@CsvBindByName(column = "ReferNo")
	private String referNo;
	@CsvBindByName(column = "UserId")
	private String userId;
	@CsvBindByName(column = "Password")
	private String password;
	@CsvBindByName(column = "yob")
	private String yob;
	@CsvBindByName(column = "StartingRowNo")
	private String startingRowNo;
	@CsvBindByName(column = "EndRowNo")
	private String endRowNo;
	@CsvBindByName(column = "Module")
	private String module;
	@CsvBindByName(column = "Execute")
	private String execute;

	
	
	public OrderDetailLoginModel(String referNo, String userId, String password, String yob, String startingRowNo, String endRowNo,
			String module, String execute) {
		super();
		this.referNo = referNo;
		this.userId = userId;
		this.password = password;
		this.yob = yob;
		this.startingRowNo = startingRowNo;
		this.endRowNo = endRowNo;
		this.module = module;
		this.execute = execute;
	}

	public OrderDetailLoginModel() {}
	
	public String getReferNo() {
		return referNo;
	}
	public void setReferNo(String referNo) {
		this.referNo = referNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getYob() {
		return yob;
	}
	public void setYob(String yob) {
		this.yob = yob;
	}
	public String getStartingRowNo() {
		return startingRowNo;
	}
	public void setStartingRowNo(String startingRowNo) {
		this.startingRowNo = startingRowNo;
	}
	public String getEndRowNo() {
		return endRowNo;
	}
	public void setEndRowNo(String endRowNo) {
		this.endRowNo = endRowNo;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getExecute() {
		return execute;
	}

	public void setExecute(String execute) {
		this.execute = execute;
	}

	@Override
	public String toString() {
		return "LoginModel [referNo=" + referNo + ", userId=" + userId + ", password=" + password + ", yob=" + yob
				+ ", startingRowNo=" + startingRowNo + ", endRowNo=" + endRowNo + ", module=" + module + ", execute="
				+ execute + "]";
	}

}
