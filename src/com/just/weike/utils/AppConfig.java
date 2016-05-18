package com.just.weike.utils;

import com.just.weike.Service.impl.MyPrefrenceManager;

public class AppConfig {
	public static String BooksDatabaseName = "book";
	public static String BookListTableName = "booklist";
	public static String AppDataRootDir = MyPrefrenceManager.rootpath+"/Weike";
	public static String AppBooksStorageDir = AppDataRootDir+"/Books";
	public static int BooksSubjectsColumeNum = 1; //Î´Ö¸¶¨
	public static String ServiceUrl = "http://121.42.160.142:8080/MicroClass/";//"http://192.168.1.133:8084/MicroClass/";
}
