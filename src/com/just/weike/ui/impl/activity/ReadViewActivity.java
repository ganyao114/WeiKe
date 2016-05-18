package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import com.gy.widget.floatactionmenu.FloatingActionButton;
import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.Service.impl.Thread.ShowDetailThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.adapter.BookIndexAdapter;
import com.just.weike.ui.impl.fragment.BookListFragment;
import com.just.weike.utils.UrlFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadViewActivity extends FragmentActivity implements OnItemClickListener,OnClickListener{
	
	private DrawerLayout mDrawerLayout;
	private ListView mLv;
	private View IndexTitle;
	private Books book;
	private ImageView readimg;
	private TextView notetxtview;
	private FloatingActionButton addnote,addtoke;
	private ImageLoader imageloader;
	private TitleHolder holder;
	private int currentPage = 1;
	private LayoutInflater mInflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_readview);
		addtoke = (FloatingActionButton) findViewById(R.id.pinglunaction);
		addtoke.setOnClickListener(this);
		mLv = (ListView) findViewById(R.id.id_lv);
		mInflater = getLayoutInflater();
		book = (Books) getIntent().getSerializableExtra("book");
		initView();
	}
	
	private void initView()
	{
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout2);
		mDrawerLayout.setDrawerListener(new DrawerListener() {
			
			@Override
			public void onDrawerStateChanged(int arg0) {
				Log.i("drawer", "drawer的状态：" + arg0);
			}
			
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				Log.i("drawer", arg1 + "");
			}
			
			@Override
			public void onDrawerOpened(View arg0) {
				Log.i("drawer", "抽屉被完全打开了！");
			}
			
			@Override
			public void onDrawerClosed(View arg0) {
				Log.i("drawer", "抽屉被完全关闭了！");
			}
		});
		
		
		mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		});
		imageloader = new ImageLoader(this);
		initTitle();
		initIndex();
		initMainview();
	}
 
	private void initTitle()
	{
		IndexTitle = mInflater.inflate(R.layout.booktitle_layout, null);
		holder = new TitleHolder();
		holder.bookname = (TextView) IndexTitle.findViewById(R.id.title_booknametxt);
		holder.bookcover = (ImageView) IndexTitle.findViewById(R.id.title_bookcover);
		holder.date = (TextView) IndexTitle.findViewById(R.id.title_uploaddate);
		holder.uploader = (TextView) IndexTitle.findViewById(R.id.title_uploader);
		holder.readcount = (TextView) IndexTitle.findViewById(R.id.title_readcount);
		holder.introduct = (TextView) IndexTitle.findViewById(R.id.title_summarytxt);
		holder.bookname.setText(book.getName());
		imageloader.DisplayImage(UrlFactory.getBookCoverUrl(book), holder.bookcover);
		holder.introduct.setText(book.getIntroduct());
		holder.date.setText(book.getUploadDate());
		holder.uploader.setText(book.getUpLoadPerson());
		holder.readcount.setText("已读:"+book.getReadCount());
		mLv.addHeaderView(IndexTitle);
	}
	
	private void initIndex()
	{
		mLv.setAdapter(new BookIndexAdapter(this, book));
		mLv.setOnItemClickListener(this);
	}
	
	private void initMainview()
	{
		readimg = (ImageView) findViewById(R.id.detailimgview);
		readimg.setOnClickListener(this);
		notetxtview = (TextView) findViewById(R.id.notetxt);
		addnote = (FloatingActionButton) findViewById(R.id.readviewaction_b);
		addnote.setOnClickListener(this);
		addtoke = (FloatingActionButton) findViewById(R.id.pinglunaction);
		addtoke.setOnClickListener(this);
		imageloader.DisplayImage(UrlFactory.getBookPageUrl(book, 1), readimg);
	}
	
	private void showDetail()
	{
		Intent intent = new Intent();
		intent.setClass(this, ShowDetailActivity.class);
		intent.putExtra("url", UrlFactory.getBookPageUrl(book, currentPage));
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		imageloader.DisplayImage(UrlFactory.getBookPageUrl(book, position), readimg);
		currentPage = position;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.detailimgview:
			showDetail();
			break;
		case R.id.pinglunaction:
			showTokes();
			break;
		case R.id.readviewaction_b:
			new SendTokeDialog(this, book, currentPage);
			break;
		default:
			break;
		}
	}
	
	private void showTokes() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, TokeListActivity.class);
		intent.putExtra("book", book);
		intent.putExtra("page", currentPage);
		startActivity(intent);
	}

	class TitleHolder
	{
		TextView bookname;
		TextView introduct;
		ImageView bookcover;
		TextView uploader;
		TextView date;
		TextView readcount;
	}
	


	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
	}

	
	
	
	
}
