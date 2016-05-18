package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;

import com.gy.materialdesign.widgets.ProgressDialog;
import com.gy.materialedittext.MaterialEditText;
import com.gy.widget.meteriadialog.MaterialDialog;
import com.just.weike.R;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.LoginBean;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Dao.impl.UserServiceiml;
import com.just.weike.Service.impl.Thread.AddTokeThread;
import com.just.weike.Service.impl.Thread.LoginThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.IOnLoginedCallBack;
import com.just.weike.utils.ExceptionContent;
import com.just.weike.utils.StaticDataPackage;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Toast;

public class SendTokeDialog implements OnFocusChangeListener{
	private Context mContext;
	private MaterialEditText TitleEditText,ContentEditText;
	private LayoutInflater mInflater;
	private View contentView;
	private MaterialDialog materialDialog;
	private static ProgressDialog progressdialog;
	private Books book;
	private int page;
	private Handler handler;
	
	public SendTokeDialog(Context context,Books book,int page) {
		// TODO Auto-generated constructor stub
		this.book = book;
		this.page = page;
		this.mContext = context;
		handler = new MyHandle(this);
		progressdialog = new ProgressDialog(context, "正在发送");
		mInflater = LayoutInflater.from(mContext);
		contentView = mInflater.inflate(R.layout.sendtoke_layout, null);
		materialDialog = new MaterialDialog(context);
		initView();
		setdialogview();
	}
	
	private void initView()
	{
		TitleEditText = (MaterialEditText) contentView.findViewById(R.id.toketitle);
		ContentEditText = (MaterialEditText) contentView.findViewById(R.id.tokecontent);
		TitleEditText.setOnFocusChangeListener(this);
		ContentEditText.setOnFocusChangeListener(this);
	}
	
	private void showTip(String str) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
	}
	
	private void setdialogview()
	{
		materialDialog.setTitle("发送");
		materialDialog.setContentView(contentView);
		materialDialog.setPositiveButton("发送", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doSend();
			}
		});
		materialDialog.setNegativeButton("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				materialDialog.dismiss();
			}
		});
		materialDialog.show();
	}
	
	private void doSend()
	{
		String title = TitleEditText.getText().toString();
		String content = ContentEditText.getText().toString();
		if (title!=""&&content!=null) {
			progressdialog = new ProgressDialog(mContext, "正在发送...");
			progressdialog.setCancelable(false);
			progressdialog.show();
			TokeBean toke = new TokeBean();
			toke.setSender(StaticDataPackage.LoginName);
			toke.setBookId(book.getId());
			toke.setPage(page);
			toke.setTitle(title);
			toke.setContent(content);
			MyWorkThreadQueue.AddTask(new AddTokeThread(handler, toke));
		}else {
			showTip("不合法");
		}
	}
	
	
	private static class MyHandle extends Handler
	{
		private final WeakReference<SendTokeDialog> mcontext;
		public MyHandle(SendTokeDialog context) {
			mcontext=new WeakReference<SendTokeDialog>(context);
		}
		@Override
		public void handleMessage(Message msg) {
			
			if (progressdialog != null) {
				progressdialog.dismiss();
			}
			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((SendTokeDialog) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				((SendTokeDialog) mcontext.get()).showTip("发送成功");
				((SendTokeDialog) mcontext.get()).materialDialog.dismiss();
				break;
			default:

				break;
			}
			
			}
		}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		if (hasFocus) {
			MaterialEditText editText = (MaterialEditText) v;
			editText.setText("");
		}
		
	}
	

}
