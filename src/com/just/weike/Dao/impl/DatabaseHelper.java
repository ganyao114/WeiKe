package com.just.weike.Dao.impl;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.just.weike.Dao.IDataBaseHelper;

public class DatabaseHelper extends SQLiteOpenHelper implements IDataBaseHelper {
	private Context mContext;
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}

	@Override
	public boolean insert(String database, String table, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createtable(String database, String table,
			List<String> items, Context context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletetable(String database, String table) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> gettableitems(String database, String table) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  void initdatabase(String databasename,List<String> tablecreatcmds,Context context) {
			DatabaseHelper dHelper = new DatabaseHelper(context,
					databasename, null, 1);
			SQLiteDatabase db = dHelper.getWritableDatabase();
			for (String tablecreatcmd : tablecreatcmds) {
				db.execSQL(tablecreatcmd);
			}
			dHelper.close();
			db.close();
	}

	@Override
	public boolean isfirstrun(Context context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean refreshtableitems(String database, String table,
			List<String> items, Context context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean istableempty(String database, String table, Context context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cursor getCursor(String databasename, String table) {
		// TODO Auto-generated method stub
		DatabaseHelper dHelper = new DatabaseHelper(mContext, databasename,
				null, 1);
		SQLiteDatabase db = dHelper.getWritableDatabase();

		Cursor cursor = db.query(table, null, null, null, null, null, null);
		return cursor;
	}

}
