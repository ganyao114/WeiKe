package com.just.weike.Dao.bean;

import java.io.Serializable;

public class ReplyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 726547890958967280L;
	
	private String title;
	private String sender;
	private String content;
	private String time;

	public ReplyBean() {
		// TODO Auto-generated constructor stub
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
