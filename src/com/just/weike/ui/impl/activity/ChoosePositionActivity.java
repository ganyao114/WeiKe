package com.just.weike.ui.impl.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gy.materialdesign.widgets.ProgressDialog;
import com.gy.widget.siderbar.SiderBar;
import com.gy.widget.siderbar.SiderBarListener;
import com.just.weike.R;
import com.just.weike.Dao.bean.PositionBean;
import com.just.weike.Dao.bean.RegistBean;
import com.just.weike.Service.impl.Thread.GetPositionItemsThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.IOnRegistedCallBack;
import com.just.weike.ui.adapter.PositionItemAdapter;
import com.just.weike.ui.impl.SetLoginDialog;
import com.just.weike.ui.impl.SetRegistDialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChoosePositionActivity extends Activity implements OnClickListener,SiderBarListener
,OnItemClickListener,IOnRegistedCallBack{
	
	private TextView backButton;
	private ImageView refreshButton;
	private ListView listview;
	private SiderBar siderbar;
	private Map<String,PositionBean> positionStorage;
	private List<PositionBean> list;
	private String currentcolume = "country";
	private BaseAdapter adapter; 
	private MyHandler handler;
	private ProgressDialog progressdialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		handler = new MyHandler(this);
		list = new ArrayList<PositionBean>();
		initPositionStorage();
		setContentView(R.layout.choosepositionlist_layout);
		initView();
		setListening();
		initList();
	}
	
	private void initPositionStorage()
	{
		positionStorage = new HashMap<String,PositionBean>();
	}
	
	private void initView()
	{
		backButton = (TextView) findViewById(R.id.index_city_back);
		refreshButton = (ImageView) findViewById(R.id.index_city_flushcity);
		listview = (ListView) findViewById(R.id.city_list);
		siderbar = (SiderBar) findViewById(R.id.city_side_bar);
	}
	
	private void setListening()
	{
		backButton.setOnClickListener(this);
		refreshButton.setOnClickListener(this);
		listview.setOnItemClickListener(this);
		siderbar.setOnSlideListener(this);
	}
	
	private void initList()
	{	
		progressdialog = new ProgressDialog(this, "请稍候...");
		progressdialog.setCancelable(false);
		progressdialog.show();
		MyWorkThreadQueue.AddTask(new GetPositionItemsThread(handler, "init",null));
	}
	
	private void showList()
	{
		if (currentcolume.equals("province")) {
			adapter = new PositionItemAdapter(this, list);
			listview.setAdapter(adapter);
		}else {
			adapter.notifyDataSetChanged();
		}
		showTile();
	}
	
	private void showTile()
	{
		switch (currentcolume) {
		case "country":
			backButton.setText("选择国家");
			break;
		case "province":
			backButton.setText("选择省份");
			break;
		case "city":
			backButton.setText("选择城市");
			break;
		case "college":
			backButton.setText("选择大学");
			break;
		case "subject":
			backButton.setText("选择专业");
			break;
		case "grade":
			backButton.setText("选择年级");
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int viewid = v.getId();
		switch (viewid) {
		case R.id.index_city_back:
			finish();
			break;
		case R.id.index_city_flushcity:
			
			break;
		default:
			break;
		} 
	}

	@Override
	public void OnSlide(String S) {
		// TODO Auto-generated method stub
		listview.setSelection(findIndex(list, S));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		 positionStorage.put(list.get(position).getColume(),list.get(position) );
		 if(list.get(position).getColume().equals("grade"))
		 {	
			 RegistBean registBean = new RegistBean();
			 registBean.setCollege("江苏科技大学");
			 registBean.setSubject("软件工程");
			 registBean.setGrade("大三");
			 new SetRegistDialog(this, this,registBean);
		 }else
			 showNext(position);
	}
	
	private void showNext(int position)
	{	
		progressdialog.show();
		MyWorkThreadQueue.AddTask(new GetPositionItemsThread(handler, currentcolume,list.get(position).getName()));
	}
	
	private void doShow(List<PositionBean> tmplist)
	{
		if (list!=null) {
			list.clear();
		}
		list.addAll(tmplist);
		currentcolume = tmplist.get(0).getColume();
		showList();
	}
	
	private int findIndex(List<PositionBean> list,String s){
		if (list!=null) {
			for (int i = 0; i < list.size(); i++) {
				PositionBean city = list.get(i);
				//根据city中的sortKey进行比较
				if (s.equals(city.getIndex())) {
					return i;
				}
			}
		}else{
			Toast.makeText(this, "暂无信息", Toast.LENGTH_SHORT).show();
		}
		return -1;
	}
	
	private static class MyHandler extends Handler {
		private final WeakReference<ChoosePositionActivity> mcontext;

		public MyHandler(ChoosePositionActivity context) {
			mcontext = new WeakReference<ChoosePositionActivity>(context);
		}

		@Override
		public void handleMessage(Message msg) {

			int Flag = msg.what;
			((ChoosePositionActivity) mcontext.get()).progressdialog.dismiss();
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((ChoosePositionActivity) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<PositionBean> tmplist = (List<PositionBean>) msg.getData().getSerializable("positionbean");
				((ChoosePositionActivity) mcontext.get()).doShow(tmplist);
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

	@Override
	public void OnRegisted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnClickLogin() {
		// TODO Auto-generated method stub
		new SetLoginDialog(this, null);
	}
	
}
