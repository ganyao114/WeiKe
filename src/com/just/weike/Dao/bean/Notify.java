package com.just.weike.Dao.bean;

import java.io.Serializable;

public class Notify implements Serializable{
	
	private static final long serialVersionUID = -4566714324773307117L;
	public String sender;
	public String time;
	public String business;
	public String value;
	public String datapath;
	public Notify(String sender,String time,String business,String value,String datapath) {
		// TODO Auto-generated constructor stub
		this.sender = sender;
		this.time = time;
		this.business = business;
		this.value = value;
		this.datapath = datapath;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDatapath() {
		return datapath;
	}
	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}
	
}
