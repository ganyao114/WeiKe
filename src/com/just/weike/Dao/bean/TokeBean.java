package com.just.weike.Dao.bean;

import java.io.Serializable;

public class TokeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7554225630585960099L;
	
	private String Title;
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	private String Sender;
	private int Tokeid;
	private String Content;
	private String Date;
	private int Replycount;
	private int ImgCount;
	private int BookId;
	private int page;

	public TokeBean(String title, String sender, int tokeid, String content,
			String date, int replycount, int imgCount, int bookId, int page) {
		super();
		Title = title;
		Sender = sender;
		Tokeid = tokeid;
		Content = content;
		Date = date;
		Replycount = replycount;
		ImgCount = imgCount;
		BookId = bookId;
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public TokeBean() {
		// TODO Auto-generated constructor stub
	}

	public String getSender() {
		return Sender;
	}

	public void setSender(String sender) {
		Sender = sender;
	}

	public int getTokeid() {
		return Tokeid;
	}

	public void setTokeid(int tokeid) {
		Tokeid = tokeid;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public int getReplycount() {
		return Replycount;
	}

	public void setReplycount(int replycount) {
		Replycount = replycount;
	}

	public int getImgCount() {
		return ImgCount;
	}

	public void setImgCount(int imgCount) {
		ImgCount = imgCount;
	}

}
