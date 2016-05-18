package com.just.weike.ui.impl;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Toast;

import com.gy.materialdesign.widgets.ProgressDialog;
import com.gy.materialedittext.MaterialEditText;
import com.gy.widget.Image.CircleImageView;
import com.gy.widget.meteriadialog.MaterialDialog;
import com.just.weike.R;
import com.just.weike.Dao.bean.RegistBean;
import com.just.weike.Service.impl.Thread.RegistThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.IOnRegistedCallBack;
import com.just.weike.utils.ExceptionContent;

public class SetRegistDialog implements OnFocusChangeListener{
	private LayoutInflater mInflater;
	private Context mContext;
	private View contentView;
	private static ProgressDialog progressdialog;
	private String Loginname, password, passwordconfirm,mail;
	private Handler handler;
	private MaterialEditText edLoginname,edPasswd,edPasswdConfirm,edMail;
	private MaterialDialog materialDialog;
	private CircleImageView photo;
	private IOnRegistedCallBack callBack;
	private RegistBean registbean;
	
	public SetRegistDialog(Context context,IOnRegistedCallBack callBack,RegistBean registbean) {
		// TODO Auto-generated constructor stub
		this.callBack = callBack;
		this.mContext = context;
		this.registbean = registbean;
		materialDialog = new MaterialDialog(mContext);
		handler = new MyHandle(this);
		mInflater = LayoutInflater.from(mContext);
		contentView = mInflater.inflate(R.layout.regist_layout, null);
		initview();
		setlistener();
		setdialogview();
	}
	
	private void initview()
	{
		edLoginname = (MaterialEditText) contentView.findViewById(R.id.materialEditText1);
		edPasswd = (MaterialEditText) contentView.findViewById(R.id.materialEditText2);
		edPasswdConfirm = (MaterialEditText) contentView.findViewById(R.id.materialEditText3);
		edMail = (MaterialEditText) contentView.findViewById(R.id.materialEditText4);
		photo = (CircleImageView) contentView.findViewById(R.id.icon);
		edLoginname.setOnFocusChangeListener(this);
		edMail.setOnFocusChangeListener(this);
		edPasswd.setOnFocusChangeListener(this);
		edPasswdConfirm.setOnFocusChangeListener(this);
	}
	
	private void setlistener()
	{
		photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 设置头像代码
				
			}
		});
	}
	
	private static class MyHandle extends Handler
	{
		private final WeakReference<SetRegistDialog> mcontext;
		public MyHandle(SetRegistDialog context) {
			mcontext=new WeakReference<SetRegistDialog>(context);
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
	  			((SetRegistDialog) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				((SetRegistDialog) mcontext.get()).showTip(ExceptionContent.MSG_REGIST_SUCCESS);
				((SetRegistDialog) mcontext.get()).materialDialog.dismiss();
				((SetRegistDialog) mcontext.get()).callBack.OnRegisted();
				break;
			default:

				break;
			}
			
			}
		}
	
	private void doregist()
	{	
		Loginname = edLoginname.getText().toString();
		password = edPasswd.getText().toString();
		passwordconfirm = edPasswdConfirm.getText().toString();
		mail = edMail.getText().toString();
		if (islegal(Loginname, password,passwordconfirm, mail, Loginname)) {
			registbean.setLoginName(Loginname);
			registbean.setPasswd(password);
			registbean.setMail(mail);
			progressdialog = new ProgressDialog(mContext, "正在注册...");
			progressdialog.show(); 
			MyWorkThreadQueue.AddTask(new RegistThread(handler,registbean));
		}else {
			Toast.makeText(mContext, "注册信息不合法", Toast.LENGTH_LONG).show();
		}	
	}
	
	private boolean islegal(String Loginname,String password,String passwordconfirm,String mail,String name) {
		return true; //尚未制定
	}
	
	
	private void showTip(String str) {
		// TODO Auto-generated method stub
		Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
	}

	
	private void setdialogview()
	{
		materialDialog.setTitle("注册");
		materialDialog.setContentView(contentView);
		materialDialog.setNegativeButton("登陆", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				materialDialog.dismiss();
				callBack.OnClickLogin();
			}
		});
		materialDialog.setPositiveButton("注册", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doregist();
			}
		});
		materialDialog.show();
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

