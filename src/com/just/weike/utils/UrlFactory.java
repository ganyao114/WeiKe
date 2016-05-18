package com.just.weike.utils;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.TokeBean;

public class UrlFactory {
	
	public static String getBookCoverUrl(Books book)
	{
		String bookfolder = book.getName() + "-" + book.getUpLoadPerson();
		String coverpath = bookfolder + "\\" + "tiny_1" + ".jpeg";
		String url = AppConfig.ServiceUrl + "GetImgServlet" + "?LoginName=" + StaticDataPackage.LoginName 
				+ "&Url=" + coverpath;
		
		return url;
		
	}
	
	public static String getBookPageUrl(Books book,int page)
	{	
		String bookfolder = book.getName() + "-" + book.getUpLoadPerson();
		String pagepath = bookfolder + "\\" + "raw_" + page + ".jpeg";
		String url = AppConfig.ServiceUrl + "GetImgServlet" + "?LoginName=" + StaticDataPackage.LoginName 
				+ "&Url=" + pagepath;
		
		return url;
		
	}

	public static String getTokeImgs(TokeBean bean,int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getTokeIcon(TokeBean tokeBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
