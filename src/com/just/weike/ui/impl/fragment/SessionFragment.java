package com.just.weike.ui.impl.fragment;

import com.just.weike.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SessionFragment extends Fragment {
	
	private View view;
	private ListView sessionlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.sessionfragment_view, null);
		sessionlist = (ListView) view.findViewById(R.id.sessionlist); 
		return view;
	}

	

}
