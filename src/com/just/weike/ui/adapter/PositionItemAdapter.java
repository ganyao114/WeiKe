package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.PositionBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PositionItemAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater mInflater;
	private List<PositionBean> list;
	public PositionItemAdapter(Context context,List<PositionBean> list) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.chooseposition_item, null);
			holder.name = (TextView) convertView.findViewById(R.id.city_list_item_name);
			holder.index = (TextView) convertView.findViewById(R.id.city_list_item_sort);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		if (haveHead(position)){
			holder.index.setVisibility(View.VISIBLE);
			holder.index.setText(list.get(position).getIndex());
		}else {
			holder.index.setVisibility(View.GONE);
		}
		return convertView;
	}

	private boolean haveHead(int position) {
		if (position == 0)
			return true;
		if (list.get(position).getIndex() != list.get(position - 1).getIndex())
			return true;
		else
			return false;
	}

	class ViewHolder {
		TextView name;
		TextView index;
	}

}
