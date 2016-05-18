 package com.just.weike.ui.impl.fragment;

import java.lang.ref.WeakReference;
import java.util.List;

import com.gy.widget.mypageview.MyPageView;
import com.gy.widget.mypageview.MyPageViewListerner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Service.IUIupgdate;
import com.just.weike.Service.impl.Thread.GetBookListThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.IAddViewOnclickCallBack;
import com.just.weike.ui.adapter.BookListAdapter;
import com.just.weike.ui.impl.activity.ReadViewActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class BookListFragment extends Fragment implements MyPageViewListerner,IAddViewOnclickCallBack
	,OnItemClickListener,IUIupgdate, OnRefreshListener<ListView>,OnScrollListener{
	private PullToRefreshListView booklistol;
	private View listhead;
	private MyPageView myPageView;
	private BookListAdapter adapter;
	private View view;
	private List<Books> list;
	private Handler handler;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		handler = new MyHandler(this);
		view = inflater.inflate(R.layout.fragment_booklist, null);
		booklistol = (PullToRefreshListView)view.findViewById(R.id.booklist);
		booklistol.setMode(Mode.BOTH);
		listhead = inflater.inflate(R.layout.listviewhead, null);
		myPageView = (MyPageView)listhead.findViewById(R.id.myPageView1);
		ListView lv = booklistol.getRefreshableView();
		lv.addHeaderView(listhead);
		booklistol.setOnItemClickListener(this);
		booklistol.setOnRefreshListener(this);
		booklistol.setOnScrollListener(this);
		MyWorkThreadQueue.AddTask(new GetBookListThread(handler));
		return view;
	}
	
	private void showPageview(PagerViewBean bean)
	{
		myPageView.setPagerView(bean);
		myPageView.setOnItemClickListerner(this);
		myPageView.Start();
	}
	
	private void showView(List<Books> booklist,PagerViewBean bean) {
		// TODO Auto-generated method stub
		showPageview(bean);
		list = booklist;
		adapter = new BookListAdapter(getActivity(), list, this);
		booklistol.setAdapter(adapter);
	}
	
	@Override
	public void OnItemClicked(int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onAddviewCall(int id, int position) {
		// TODO Auto-generated method stub
		switch (id) {
		case R.id.read:
			Intent intent = new Intent();
			intent.setClass(getActivity(), ReadViewActivity.class);
			intent.putExtra("book", list.get(position));
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		adapter.ctr = position-2;
		adapter.realctr = position-2;
		adapter.onclick = true;
		adapter.notifyDataSetChanged();
	}
	
	
	
	private static class MyHandler extends Handler {
		private final WeakReference<BookListFragment> mcontext;

		public MyHandler(BookListFragment context) {
			mcontext = new WeakReference<BookListFragment>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((BookListFragment) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<Books> booklist = (List<Books>) msg.getData().getSerializable("books");
				PagerViewBean pagerViewBean = (PagerViewBean) msg.getData().getSerializable("pageview");
				((BookListFragment) mcontext.get()).showView(booklist,pagerViewBean);
				break;
			default:

				break;
			}

		}
	}

	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), errmsg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void defaultupdate() {
		// TODO Auto-generated method stub
		MyWorkThreadQueue.AddTask(new GetBookListThread(handler));
	}

	@Override
	public void onLogined() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		if(booklistol.isHeaderShown())
		{
			
		}else if(booklistol.isFooterShown()){
			
		}
		booklistol.onRefreshComplete();  
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		adapter.onclick = false;
		adapter.ctr = -1;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		
	}

	

	
}
