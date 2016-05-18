package com.just.weike.ui.adapter;

import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.utils.UrlFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookIndexAdapter extends BaseAdapter {
	
	private Context context;
	private Books book;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	
	public BookIndexAdapter(Context context,Books book) {
		// TODO Auto-generated constructor stub
		this.book = book;
		this.context = context;
		mInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoader(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return book.getPages();
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
		ViewHolder holder;
		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.bookindex_fragment_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.indextitle);
			holder.preview = (ImageView) convertView.findViewById(R.id.review);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText("ตฺ"+(position+1)+"าณ");
		imageLoader.DisplayImage(UrlFactory.getBookPageUrl(book, position+1), holder.preview);
		return convertView;
	}

	class ViewHolder
	{
		TextView title;
		ImageView preview;
	}

}
