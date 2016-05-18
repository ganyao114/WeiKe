package com.just.weike.ui.impl;

import java.lang.ref.WeakReference;

import com.gy.materialdesign.widgets.ProgressDialog;
import com.gy.materialedittext.MaterialEditText;
import com.gy.widget.meteriadialog.MaterialDialog;
import com.just.weike.R;
import com.just.weike.Dao.bean.LoginBean;
import com.just.weike.Dao.impl.UserServiceiml;
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

public class SetLoginDialog implements OnFocusChangeListener{
	private Context mContext;
	private MaterialEditText loginNameEditText,loginPasswdEditText;
	private LayoutInflater mInflater;
	private View contentView;
	private MaterialDialog materialDialog;
	private static ProgressDialog progressdialog;
	private IOnLoginedCallBack callBack;
	
	public SetLoginDialog(Context context,IOnLoginedCallBack callBack) {
		// TODO Auto-generated constructor stub
		this.callBack = callBack;
		this.mContext = context;
		progressdialog = new ProgressDialog(context, "正在登陆");
		mInflater = LayoutInflater.from(mContext);
		contentView = mInflater.inflate(R.layout.login_layout, null);
		materialDialog = new MaterialDialog(context);
		initView();
		setdialogview();
	}
	
	private void initView()
	{
		loginNameEditText = (MaterialEditText) contentView.findViewById(R.id.loginname);
		loginPasswdEditText = (MaterialEditText) contentView.findViewById(R.id.loginpasswd);
		loginNameEditText.setOnFocusChangeListener(this);
		loginPasswdEditText.setOnFocusChangeListener(this);
	}
	
	private void showTip(String str) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
	}
	
	private void setdialogview()
	{
		materialDialog.setTitle("登陆");
		materialDialog.setContentView(contentView);
		materialDialog.setPositiveButton("登陆", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doLogin();
			}
		});
		materialDialog.setNegativeButton("注册", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				materialDialog.dismiss();
				callBack.OnClickRegist();
			}
		});
		materialDialog.show();
	}
	
	private void doLogin()
	{
		LoginBean loginBean = new LoginBean(loginNameEditText.getText().toString(), loginPasswdEditText.getText().toString());
		if (isIlegal(loginBean)) {
			progressdialog = new ProgressDialog(mContext, "正在登陆...");
			progressdialog.setCancelable(false);
			progressdialog.show();
			MyWorkThreadQueue.AddTask(new LoginThread(new MyHandle(this), loginBean, UserServiceiml.getInstance()));
		}else {
			showTip("不合法");
		}
	}
	
	private boolean isIlegal(LoginBean loginBean)
	{
		return true;
	}
	
	private static class MyHandle extends Handler
	{
		private final WeakReference<SetLoginDialog> mcontext;
		public MyHandle(SetLoginDialog context) {
			mcontext=new WeakReference<SetLoginDialog>(context);
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
				((SetLoginDialog) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				((SetLoginDialog) mcontext.get()).showTip(ExceptionContent.MSG_LOGIN_SUCCESS);
				StaticDataPackage.LoginName = ((SetLoginDialog) mcontext.get()).loginNameEditText.getText().toString();
				((SetLoginDialog) mcontext.get()).materialDialog.dismiss();
				((SetLoginDialog) mcontext.get()).callBack.OnLogined();
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
