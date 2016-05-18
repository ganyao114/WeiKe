/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.just.weike.Dao.bean;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class BookBase implements Serializable{
        public int id,pages,ReadCount,tokecount;
	public String Name,Author,Size,UpLoadPerson,Kind,Introduct;
	public String UploadDate;
	public int isNew;
	
	public BookBase() {
		// TODO Auto-generated constructor stub
	}
	
	public BookBase(int id,String Name,String Author,String Size,
			String UpLoadPerson,String Kind,String updatedate,int pages,int isNew
			,String Introduct,int ReadCount) {
		this.id = id;
		this.Name = Name;
		this.Author = Author;
		this.Size = Size;
		this.UpLoadPerson = UpLoadPerson;
		this.Kind = Kind;
		this.UploadDate = updatedate;
		this.pages = pages;
		this.isNew = isNew;
		this.Introduct = Introduct;
		this.ReadCount = ReadCount;
	}

    public int getTokecount() {
        return tokecount;
    }

    public void setTokecount(int tokecount) {
        this.tokecount = tokecount;
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
