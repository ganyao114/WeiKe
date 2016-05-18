package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IndexPreviewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Bitmap> imgs; 
	public IndexPreviewAdapter(Context context,List<Bitmap> imgs) {
		// TODO Auto-generated constructor stub
		this.imgs = imgs;
		mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imgs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imgs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = View.inflate(mContext, R.layout.indexpreviewlist_item, null);
		ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
		TextView textView = (TextView) view.findViewById(R.id.textView1);
		imageView.setImageBitmap(imgs.get(position));
		textView.setText(position+1+"");
		return view;
	}

}
