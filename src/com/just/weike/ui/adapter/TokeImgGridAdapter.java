package com.just.weike.ui.adapter;

import com.just.weike.R;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.utils.UrlFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class TokeImgGridAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater mInflater;
	private TokeBean bean;
	private ImageLoader imageLoader;
	
	public TokeImgGridAdapter(Context context,TokeBean bean) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.bean = bean;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bean.getReplycount();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
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
			convertView = mInflater.inflate(R.layout.toke_img_grid_item, null);
			holder.img = (ImageView) convertView.findViewById(R.id.toke_img_item);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		imageLoader.DisplayImage(UrlFactory.getTokeImgs(bean,position), holder.img);
		return convertView;
	}
	
	class ViewHolder{
		ImageView img;
	}

}
