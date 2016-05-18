package com.just.weike.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.just.weike.R;
import com.just.weike.Dao.impl.DatabaseHelper;
import com.just.weike.Service.IAppPreLoad;
import com.just.weike.Service.impl.MyPrefrenceManager;

public class AppPreLoad extends Activity implements IAppPreLoad{
	private DatabaseHelper databaseHelper;
	private List<String> tablecreatcmds;
	private String sdcardroot = MyPrefrenceManager.rootpath;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preload_layout);
	}

	@Override
	public void firstruninit() {
		// TODO Auto-generated method stub
		tablecreatcmds = new ArrayList<String>();
		tablecreatcmds.add("create table "+AppConfig.BookListTableName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, path TEXT NOT NULL )");
		databaseHelper = new DatabaseHelper(this, null, null, 1);
		databaseHelper.initdatabase(AppConfig.BooksDatabaseName, tablecreatcmds, this);
		File file = new File(AppConfig.AppDataRootDir);
		if (!file.exists())
		{
			try {
				file.mkdir();
				new File(AppConfig.AppBooksStorageDir).mkdir();
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(this, "SD卡异常,软件无法继续运行", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}

	public List<String> getSubjectsLocal() {
		List<String> subjects = new ArrayList<String>();
		databaseHelper = new DatabaseHelper(this, null, null, 1);
		Cursor cursor = databaseHelper.getCursor(AppConfig.BooksDatabaseName, AppConfig.BookListTableName);
		for (int i = 0; i < cursor.getCount(); i++) {
			cursor.moveToFirst();
			cursor.move(i);
			subjects.add(cursor.getString(AppConfig.BooksSubjectsColumeNum));
		}
		return subjects;
	}

	@Override
	public List<String> getSubjectsOnline() {
		// TODO Auto-generated method stub
		List<String> subjects = new ArrayList<String>();
		return subjects;
	}

	@Override
	public boolean SetSubujectsData() {
		// TODO Auto-generated method stub
		List<String> subjectslocal = new ArrayList<String>();
		List<String> subjectsonline = new ArrayList<String>();
		subjectslocal = getSubjectsLocal();
		subjectsonline = getSubjectsOnline();
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.addAll(subjectsonline);
		hashSet.addAll(subjectslocal);
		List<String> list = new ArrayList<String>(hashSet);
		StaticDataPackage.subjects = list;
		return true;
	}

}
