package com.just.weike.Dao.bean;

import java.io.Serializable;

public class Books implements Serializable{
	
	private static final long serialVersionUID = 212617952715550351L;
	public int id,pages,ReadCount;
	public String Name,Author,Size,UpLoadPerson,Kind,Introduct;
	public PositionPath Position;
	public String UploadDate;
	private int isNew;
	
	public Books() {
		// TODO Auto-generated constructor stub
	}
	
	public Books(int id,String Name,String Author,String Size,
			String UpLoadPerson,String Kind,PositionPath Position
			,String updatedate,int pages,int isNew
			,String Introduct,int ReadCount) {
		this.id = id;
		this.Name = Name;
		this.Author = Author;
		this.Size = Size;
		this.UpLoadPerson = UpLoadPerson;
		this.Kind = Kind;
		this.Position = Position;
		this.UploadDate = updatedate;
		this.pages = pages;
		this.isNew = isNew;
		this.Introduct = Introduct;
		this.ReadCount = ReadCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getUpLoadPerson() {
		return UpLoadPerson;
	}
	public void setUpLoadPerson(String upLoadPerson) {
		UpLoadPerson = upLoadPerson;
	}
	public String getKind() {
		return Kind;
	}
	public void setKind(String kind) {
		Kind = kind;
	}
	public PositionPath getPosition() {
		return Position;
	}
	public void setPosition(PositionPath position) {
		Position = position;
	}
	public String getUploadDate() {
		return UploadDate;
	}
	public void setUploadDate(String uploadDate) {
		UploadDate = uploadDate;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public String getIntroduct() {
		return Introduct;
	}

	public void setIntroduct(String introduct) {
		Introduct = introduct;
	}

	public int getReadCount() {
		return ReadCount;
	}

	public void setReadCount(int readCount) {
		ReadCount = readCount;
	}

}
