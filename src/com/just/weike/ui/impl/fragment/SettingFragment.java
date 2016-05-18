package com.just.weike.ui.impl.fragment;

import com.just.weike.R;
import com.just.weike.utils.StaticDataPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingFragment extends Fragment {
	private View view;
	private TextView name;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.settingfragment_layout, null);
		name = (TextView) view.findViewById(R.id.my_index_login_text);
		if (StaticDataPackage.LoginName!=null) {
			name.setText(StaticDataPackage.LoginName);
		}
		return view;
	}
}
