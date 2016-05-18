package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.Dao.bean.SessionListBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SessionListAdapter extends BaseAdapter {
	
	private List<SessionListBean> list;
	private Context context;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	
	public SessionListAdapter(Context context,List<SessionListBean> list) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		mInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	class ViewHolder
	{
		
	}

}
