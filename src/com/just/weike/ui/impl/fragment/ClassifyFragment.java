package com.just.weike.ui.impl.fragment;

import java.lang.ref.WeakReference;
import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.ClassifyBean;
import com.just.weike.Service.impl.Thread.GetClassifyThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;
import com.just.weike.ui.adapter.ClassifyAdapter;
import com.just.weike.ui.impl.activity.ClassifyListActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ClassifyFragment extends Fragment implements OnItemClickListener{
	
	private GridView gridView;
	private Handler handler;
	private BaseAdapter adapter;
	private List<ClassifyBean> classifyBeanslist;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.classifyfragment_layout, null);
		gridView = (GridView) view.findViewById(R.id.classifygrid);
		handler = new MyHandle(this);
		showGrid();
		return view;
	}
	
	private void showGrid()
	{
		MyWorkThreadQueue.AddTask(new GetClassifyThread(handler));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		showList(position);
	}
	
	private static class MyHandle extends Handler
	{
		private final WeakReference<ClassifyFragment> mcontext;
		public MyHandle(ClassifyFragment context) {
			mcontext=new WeakReference<ClassifyFragment>(context);
		}
		@Override
		public void handleMessage(Message msg) {
			
			int Flag = msg.what;
			switch (Flag) {
			case 0:
				String errmsg = (String) msg.getData().getSerializable(
						"ErroMsg");
				((ClassifyFragment) mcontext.get()).showTip(errmsg);
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<ClassifyBean> list = (List<ClassifyBean>) msg.getData().getSerializable("classifyitems");
				((ClassifyFragment) mcontext.get()).doShowGrid(list);
				break;
			default:

				break;
			}
			
			}
		}

	private void showTip(String tip) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), tip, Toast.LENGTH_LONG).show();
	}
	
	private void doShowGrid(List<ClassifyBean> list)
	{	
		classifyBeanslist = list;
		adapter = new ClassifyAdapter(getActivity(), list);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(this); 
	}
	
	private void showList(int position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(getActivity(), ClassifyListActivity.class);
		intent.putExtra("classify", classifyBeanslist.get(position).getName());
		startActivity(intent);
	}
	
}
