package com.just.weike.ui.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.just.weike.R;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.utils.UrlFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class TokeListAdapter extends BaseAdapter {
	
	private List<TokeBean> list;
	private Context mContext;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	private Map<Integer,TokeImgGridAdapter> map;
	
	public TokeListAdapter(Context context,List<TokeBean> list) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.list = list;
		imageLoader = new ImageLoader(context);
		map = new HashMap<Integer,TokeImgGridAdapter>();
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
			convertView = mInflater.inflate(R.layout.as_item_comment, null);
			holder.title = (TextView) convertView.findViewById(R.id.txtName);
			holder.content = (TextView) convertView.findViewById(R.id.txtContent);
			holder.imgs = (GridView) convertView.findViewById(R.id.toke_imggride);
			holder.sender = (TextView) convertView.findViewById(R.id.txtDesc);
			holder.replycount = (TextView) convertView.findViewById(R.id.replycount);
			holder.icon = (ImageView) convertView.findViewById(R.id.imgPhoto);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.title.setText(list.get(position).getTitle());
		holder.content.setText(list.get(position).getContent());
		holder.sender.setText("来自:"+list.get(position).getSender()+"于"+list.get(position).getDate());
		holder.replycount.setText("评论"+list.get(position).getReplycount()+"条");
		//imageLoader.DisplayImage(UrlFactory.getTokeIcon(list.get(position)), holder.icon);
		setImgGrid(position, holder.imgs, holder);
		
		return convertView;
	}
	
	private void setImgGrid(int position,View v,ViewHolder holder)
	{	
		if(list.get(position).getImgCount() > 0)
		{
			holder.imgs.setVisibility(View.VISIBLE);
			if(map.get(position) == null)
				map.put(position, new TokeImgGridAdapter(mContext, list.get(position)));
			holder.imgs.setAdapter(map.get(position));
		}else {
			holder.imgs.setVisibility(View.GONE);
		}
	}
	
	public static class ViewHolder{
		public TextView title;
		public TextView content;
		public GridView imgs;
		public TextView sender;
		public TextView replycount;
		public ImageView icon;
	}

}
