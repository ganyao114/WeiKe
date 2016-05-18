package com.just.weike.ui.impl.fragment;

import com.gy.widget.floatactionmenu.FloatingActionButton;
import com.just.weike.R;
import com.just.weike.ui.impl.activity.IMActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class NotifyFragment extends Fragment {
	
	private View view;
	private FloatingActionButton button;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.notifyfragmentlayout, null);
		button = (FloatingActionButton) view.findViewById(R.id.detailChatview);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(getActivity(), IMActivity.class);
				startActivity(intent);
			}
		}); 
		return view;
	}
	
	

}
