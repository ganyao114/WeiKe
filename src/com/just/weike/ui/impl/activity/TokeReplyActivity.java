package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;
import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.ReplyBean;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.Service.impl.Thread.GetReplyListThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.adapter.TokeImgGridAdapter;
import com.just.weike.ui.adapter.TokeListAdapter;
import com.just.weike.ui.adapter.TokeListAdapter.ViewHolder;
import com.just.weike.ui.adapter.TokeReplyListAdapter;
import com.just.weike.utils.UrlFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TokeReplyActivity extends Activity{
	
	private ListView listview;
	private List<ReplyBean> list;
	private TokeListAdapter.ViewHolder holder;
	private TokeBean toke;
	private ImageLoader imageloader;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.toke_reply_layout);
		handler = new MyHandler(this);
		imageloader = new ImageLoader(this);
		holder = new TokeListAdapter.ViewHolder();
		listview = (ListView) findViewById(R.id.replylist);
		initViewheader();
		toke = (TokeBean) getIntent().getSerializableExtra("toke");
		setHeader();
		MyWorkThreadQueue.AddTask(new GetReplyListThread(handler,toke));
	}
	
	private void initViewheader()
	{
		holder.title = (TextView) findViewById(R.id.txtName);
		holder.content = (TextView) findViewById(R.id.txtContent);
//		holder.imgs = (GridView) findViewById(R.id.toke_imggride);
		holder.sender = (TextView) findViewById(R.id.txtDesc);
		holder.replycount = (TextView) findViewById(R.id.replycount);
		holder.icon = (ImageView) findViewById(R.id.imgPhoto);
	}
	
	private void setHeader()
	{
		holder.title.setText(toke.getTitle());
		holder.content.setText(toke.getContent());
		holder.sender.setText(toke.getSender());
		holder.replycount.setText(toke.getReplycount()+"Ìõ");
		imageloader.DisplayImage(UrlFactory.getTokeIcon(toke), holder.icon);
		setImgGrid(holder);
	}
	
	private void setImgGrid(ViewHolder holder)
	{	
		View view = findViewById(R.id.layRe);
		if(toke.getImgCount() > 0)
		{
			view.setVisibility(View.VISIBLE);
//			holder.imgs.setAdapter(new TokeImgGridAdapter(this, toke));
		}else {
			view.setVisibility(View.GONE);
		}
	}
	
	private static class MyHandler extends Handler {
		private final WeakReference<TokeReplyActivity> mcontext;

		public MyHandler(TokeReplyActivity context) {
			mcontext = new WeakReference<TokeReplyActivity>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((TokeReplyActivity) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<ReplyBean> tmplist = (List<ReplyBean>) msg.getData().getSerializable("toke");
				((TokeReplyActivity) mcontext.get()).doShow(tmplist);
				break;
			default:

				break;
			}

		}
	}

	private void showTip(String errmsg) {
		// TODO Auto-generated method stub
		Toast.makeText(this, errmsg, Toast.LENGTH_LONG).show();
	}

	private void doShow(List<ReplyBean> tmplist) {
		// TODO Auto-generated method stub
		listview.setAdapter(new TokeReplyListAdapter(this,tmplist));
	}
}
