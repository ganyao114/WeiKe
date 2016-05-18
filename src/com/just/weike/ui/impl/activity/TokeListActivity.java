package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import com.gy.widget.floatactionmenu.FloatingActionButton;
import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.impl.Thread.GetTokeListThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.adapter.TokeListAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TokeListActivity extends Activity implements OnItemClickListener,OnClickListener{
	
	private Handler handler;
	private TokeListAdapter adapter;
	private TextView backtxt;
	private ListView listview;
	private Books book;
	private int page;
	private List<TokeBean> list;
	private FloatingActionButton AddToke;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_toke_list_layout);
		AddToke = (FloatingActionButton) findViewById(R.id.addtokebt);
		AddToke.setOnClickListener(this);
		listview = (ListView) findViewById(R.id.tokelistview);
		backtxt = (TextView) findViewById(R.id.index_classify_back);
		handler = new MyHandler(this);
		book = (Books) getIntent().getSerializableExtra("book");
		page = getIntent().getIntExtra("page",-1);
		show();
	}
	
	private void show()
	{	
		//listview.setOnItemClickListener(this);
		if(book!=null){
			MyWorkThreadQueue.AddTask(new GetTokeListThread(handler, book, page));
			
		}
			
	}

	private static class MyHandler extends Handler {
		private final WeakReference<TokeListActivity> mcontext;

		public MyHandler(TokeListActivity context) {
			mcontext = new WeakReference<TokeListActivity>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((TokeListActivity) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<TokeBean> tmplist = (List<TokeBean>) msg.getData().getSerializable("list");
				((TokeListActivity) mcontext.get()).doShow(tmplist);
				break;
			default:

				break;
			}

		}
	}

	private void doShow(List<TokeBean> tmplist) {
		// TODO Auto-generated method stub
		list = tmplist;
		adapter = new TokeListAdapter(this, tmplist);
		listview.setAdapter(adapter);
	}

	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(this, TokeReplyActivity.class);
		intent.putExtra("toke", list.get(position));
		startActivity(intent);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.addtokebt:
			new SendTokeDialog(this,book,page);
			break;

		default:
			break;
		}
	}

	
}
