package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Service.impl.Thread.GetClassifyListThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.IAddViewOnclickCallBack;
import com.just.weike.ui.adapter.BookListAdapter;

public class DownloadedBooksActivity extends Activity implements IAddViewOnclickCallBack,OnClickListener{

	private String classify;
	private Handler handler;
	private ListView listview;
	private BookListAdapter adapter;
	private TextView backtxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classifyookslayout);
		listview = (ListView) findViewById(R.id.classify_list);
		backtxt = (TextView) findViewById(R.id.index_classify_back);
		handler = new MyHandler(this);
		classify = getIntent().getStringExtra("classify");
		backtxt.setText(classify);
		backtxt.setOnClickListener(this);
		MyWorkThreadQueue.AddTask(new GetClassifyListThread(handler, classify));
	}
	
	private static class MyHandler extends Handler {
		private final WeakReference<DownloadedBooksActivity> mcontext;

		public MyHandler(DownloadedBooksActivity context) {
			mcontext = new WeakReference<DownloadedBooksActivity>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((DownloadedBooksActivity) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<Books> tmplist = (List<Books>) msg.getData().getSerializable("list");
				((DownloadedBooksActivity) mcontext.get()).doShow(tmplist);
				break;
			default:

				break;
			}

		}
	}

	private void doShow(List<Books> tmplist) {
		// TODO Auto-generated method stub
		adapter = new BookListAdapter(this,tmplist,this);
		listview.setAdapter(adapter);
	}

	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onAddviewCall(int id, int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.index_classify_back:
			finish();
			break;

		default:
			break;
		}
	}
	

}
