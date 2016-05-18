package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.ReplyBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TokeReplyListAdapter extends BaseAdapter {
	
	private List<ReplyBean> list;
	private LayoutInflater mInflater;
	private Context mContext;
	private ImageLoader imageLoader;
	
	public TokeReplyListAdapter(Context context,List<ReplyBean> list) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.list = list;
		this.mContext = context;
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
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.reply_item_layout, null);
			holder.title = (TextView) convertView.findViewById(R.id.reply_title);
			holder.content = (TextView) convertView.findViewById(R.id.reply_content);
			holder.time = (TextView) convertView.findViewById(R.id.reply_uploaddate);
			holder.sender = (TextView) convertView.findViewById(R.id.reply_uploader);
			holder.icon = (ImageView) convertView.findViewById(R.id.replyer_face);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		return null;
	}
	
	class ViewHolder{
		ImageView icon;
		TextView title;
		TextView content;
		TextView time;
		TextView sender;
	}
	
}
