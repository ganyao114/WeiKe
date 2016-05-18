package com.just.weike.ui.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.gy.widget.meteriadialog.MaterialDialog;
import com.just.weike.Service.impl.MyPrefrenceManager;
import com.just.weike.ui.adapter.FilelistAdapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FileSelecter implements OnItemClickListener{
	private Context mContext;
	private MaterialDialog materialDialog;
	private String sdcardroot = MyPrefrenceManager.rootpath;
	private List<String> item, paths;
	private String selectFile;
	private ListView listView ;
	private FilelistAdapter adapter;
	public FileSelecter(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		initdialog();
		getdir(sdcardroot);
	}
	
	private void initdialog() {
		materialDialog = new MaterialDialog(mContext);
		listView = new ListView(mContext);
		listView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		materialDialog.setTitle("选择文件");
		materialDialog.setContentView(listView);
		materialDialog.setPositiveButton("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		materialDialog.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				materialDialog.dismiss();
			}
		});
		materialDialog.show();
		listView.setOnItemClickListener(this);
	}
	
	private void getdir(String filepath) {
		item = new ArrayList<String>();
		paths = new ArrayList<String>();
		File file = new File(filepath);
		File[] files = file.listFiles();
		if (!filepath.equals(sdcardroot)) {
			item.add("back");
			paths.add(file.getParent());
		}
		for (int i = 0; i < files.length; i++) {
			File tempfile = files[i];
			item.add(tempfile.getName());
			paths.add(tempfile.getPath());
		}
		if (adapter == null)
		{
			adapter = new FilelistAdapter(mContext, item, paths);
			listView.setAdapter(adapter);
		}else {
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		File templefile = new File(paths.get(position));
		if (templefile.isDirectory()) {
			try {
				getdir(paths.get(position));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				getdir(sdcardroot);
				Toast.makeText(mContext, "未获得权限无法查看",
						Toast.LENGTH_LONG).show();
			}
		} else {
			selectFile = templefile.getAbsolutePath();
			Toast.makeText(mContext, "你选择了: "+templefile.getName(), Toast.LENGTH_LONG).show();
		}
		
	}
	
}
