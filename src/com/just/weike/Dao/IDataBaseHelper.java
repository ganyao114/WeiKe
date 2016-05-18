package com.just.weike.Dao;

import java.util.List;

import android.content.Context;
import android.database.Cursor;

public interface IDataBaseHelper {
	public boolean insert(String database,String table,String value);
	public boolean createtable(String database,String table,List<String> items, Context context);
	public boolean deletetable(String database,String table);
	public List<String>gettableitems(String database,String table);
	public void initdatabase(String databasename,List<String> tablecreatcmds,Context context);
	public boolean isfirstrun(Context context);
	public boolean refreshtableitems(String database,String table,List<String> items,Context context);
	public boolean istableempty(String database,String table,Context context);
	public Cursor getCursor(String databasename,String table);
}
