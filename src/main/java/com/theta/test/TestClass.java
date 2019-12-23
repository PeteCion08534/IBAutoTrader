package com.theta.test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TestClass implements Serializable {

	private String Id;
	private Calendar dt;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public Calendar getDt() {
		return dt;
	}

	public String getDtString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM:dd:yyyy:HH:mm:ss");
		String strdate = sdf.format(dt.getTime());
		return strdate;
	}

	public void setDt(Calendar dt) {
		this.dt = dt;
	}

	public String serializeTestClass() {		
		
		StringBuilder builder = new StringBuilder();
		builder.append("[Id|dt][");
		builder.append(Id);
		builder.append("|");
		builder.append(this.getDtString());
		builder.append("]");
		return builder.toString();
	}

	public String deSerializeTestClass(String inString) {

		//inString
		
		StringBuilder builder = new StringBuilder();
		builder.append("[Id|dt][");
		builder.append(Id);
		builder.append("|");
		builder.append(this.getDtString());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
