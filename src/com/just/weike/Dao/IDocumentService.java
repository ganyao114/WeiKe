package com.just.weike.Dao;

import java.util.List;

import com.just.weike.Dao.bean.Books;


public interface IDocumentService {
	public boolean adddoc(String type,Books info,String path);
	public String getdoc(Books info);
	public boolean deletedoc(Books info);
	public List<Books> getlist();
}
