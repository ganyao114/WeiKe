package com.just.weike.ui.adapter;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.ui.IAddViewOnclickCallBack;
import com.just.weike.ui.impl.ExpandCollapseAnimation;
import com.just.weike.utils.UrlFactory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BookListAdapter extends BaseAdapter implements OnClickListener{
	
	private List<Books> list;
	private Context context;
	private LayoutInflater mInflater;
	private ImageLoader imageLoader;
	private IAddViewOnclickCallBack callBack;
	public int ctr = -1;
	public int realctr = -1;
	public boolean onclick = false;
	
	public BookListAdapter(Context context,List<Books> list,IAddViewOnclickCallBack callBack) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
		this.imageLoader = new ImageLoader(context);
		this.callBack = callBack;
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
			convertView = mInflater.inflate(R.layout.booklist_item_layout, null);
			holder.bookname = (TextView) convertView.findViewById(R.id.booknametxt);
			holder.bookcover = (ImageView) convertView.findViewById(R.id.bookcover);
			holder.date = (TextView) convertView.findViewById(R.id.uploaddate);
			holder.uploader = (TextView) convertView.findViewById(R.id.uploader);
			holder.readcount = (TextView) convertView.findViewById(R.id.readcount);
			holder.introduct = (TextView) convertView.findViewById(R.id.summarytxt);
			holder.size = (TextView) convertView.findViewById(R.id.booksize);
			holder.Kind = (TextView) convertView.findViewById(R.id.bookkind);
			holder.Pages = (TextView) convertView.findViewById(R.id.bookpages);
			holder.progress = (TextView) convertView.findViewById(R.id.bookauthor);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if(!onclick)
			imageLoader.DisplayImage(UrlFactory.getBookCoverUrl(list.get(position)), holder.bookcover);
		
		holder.bookname.setText(list.get(position).getName());
		holder.introduct.setText(list.get(position).getIntroduct());
		holder.date.setText(list.get(position).getUploadDate());
		holder.uploader.setText(list.get(position).getUpLoadPerson());
		holder.readcount.setText("已读:"+list.get(position).getReadCount());
		holder.Kind.setText("格式: "+list.get(position).getKind());
		holder.size.setText("大小: "+list.get(position).getSize());
		holder.Pages.setText("页数: "+list.get(position).getPages());
		holder.progress.setText("作者: "+list.get(position).getAuthor());
		if (position == ctr)
		{
			if(convertView.findViewById(R.id.addview).getVisibility() == View.VISIBLE)
			{	
				animateView(convertView.findViewById(R.id.addview),1);
			}
			else{
				animateView(convertView.findViewById(R.id.addview),0);
			}
			convertView.findViewById(R.id.read).setOnClickListener(this);
			convertView.findViewById(R.id.detail).setOnClickListener(this);
			convertView.findViewById(R.id.download).setOnClickListener(this);
			convertView.findViewById(R.id.save).setOnClickListener(this);
		}else {
			convertView.findViewById(R.id.addview).setVisibility(View.GONE);
		}
		if (list.get(position).getIsNew() == 0) {
			convertView.findViewById(R.id.newicon).setVisibility(View.VISIBLE);
		}else {
			convertView.findViewById(R.id.newicon).setVisibility(View.GONE);
		}
		return convertView;
	}
	
	public void changeAddView()
	{
		
	}
	
	class ViewHolder{
		TextView bookname;
		TextView introduct;
		ImageView bookcover;
		TextView uploader;
		TextView date;
		TextView readcount;
		TextView size;
		TextView Kind;
		TextView Pages;
		TextView progress;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int flag = v.getId();
		callBack.onAddviewCall(flag, realctr);
	}
	
	private void animateView(final View target, final int type) {
		Animation anim = new ExpandCollapseAnimation(
				target,
				type
		);
		anim.setDuration(330);
		target.startAnimation(anim);
	}

}
