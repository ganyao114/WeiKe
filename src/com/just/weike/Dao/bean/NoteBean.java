package com.just.weike.Dao.bean;

import java.io.Serializable;

public class NoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8704058176976419975L;
	private Books book;
	private int page;
	private String title;
	private String authour;
	private String content;
	private int imgcounts;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public Books getBook() {
		return book;
	}

	public void setBook(Books book) {
		this.book = book;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthour() {
		return authour;
	}

	public void setAuthour(String authour) {
		this.authour = authour;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getImgcounts() {
		return imgcounts;
	}

	public void setImgcounts(int imgcounts) {
		this.imgcounts = imgcounts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public NoteBean() {
		// TODO Auto-generated constructor stub
	}

}
