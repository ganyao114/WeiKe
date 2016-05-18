package com.just.weike.Dao.bean;

import java.io.Serializable;

public class NavigationTopBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3041538268704847148L;
	private String name;
	private String photoBitmapUrl;
	
	public NavigationTopBean() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoBitmapUrl() {
		return photoBitmapUrl;
	}

	public void setPhotoBitmapUrl(String photoBitmapUrl) {
		this.photoBitmapUrl = photoBitmapUrl;
	}
	
}
