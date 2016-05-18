package com.just.weike.Dao.bean;

import java.io.Serializable;

public class ClassifyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9024391428414023709L;
	
	private String name;
	private String coverurl;
	
	public ClassifyBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ClassifyBean(String name,String coverurl) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.coverurl = coverurl;
	}
	
	public String getName() {
		return name; 
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}

}
