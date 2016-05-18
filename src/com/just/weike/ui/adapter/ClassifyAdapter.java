package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.ClassifyBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassifyAdapter extends BaseAdapter {
	
	private List<ClassifyBean> list;
	private Context context;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	
	public ClassifyAdapter(Context context,List<ClassifyBean> list) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.classifyitem_layout, null);
			holder.name = (TextView) convertView.findViewById(R.id.home_nav_item_desc);
			holder.cover = (ImageView) convertView.findViewById(R.id.home_nav_item_image);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.name.setText(list.get(position).getName());
		imageLoader.DisplayImage(list.get(position).getCoverurl(), holder.cover);
		
		return convertView;
	}
	
	class ViewHolder{
		ImageView cover;
		TextView name;
	}

}
