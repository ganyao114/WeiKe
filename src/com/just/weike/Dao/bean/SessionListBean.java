package com.just.weike.Dao.bean;

import java.io.Serializable;

public class SessionListBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805690749496117048L;
	
	private String coverurl;
	private String name;
	private String latestcontent;
	private int sessionid;
	private int newmessagescount;

	public SessionListBean() {
		// TODO Auto-generated constructor stub
	}

	public String getCoverurl() {
		return coverurl;
	}

	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLatestcontent() {
		return latestcontent;
	}

	public void setLatestcontent(String latestcontent) {
		this.latestcontent = latestcontent;
	}

	public int getSessionid() {
		return sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

	public int getNewmessagescount() {
		return newmessagescount;
	}

	public void setNewmessagescount(int newmessagescount) {
		this.newmessagescount = newmessagescount;
	}

}
