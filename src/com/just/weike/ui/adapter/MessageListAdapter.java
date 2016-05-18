package com.just.weike.ui.adapter;

import java.util.List;

import com.just.weike.R;
import com.just.weike.Dao.bean.Message;
import com.just.weike.utils.StaticDataPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private Context mContext;
	private List<Message> msgs;
	public MessageListAdapter(Context context,List<Message> messages) {
		// TODO Auto-generated constructor stub
		mInflater = LayoutInflater.from(context);
		this.msgs = messages;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return msgs.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return msgs.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Message msg = msgs.get(position);
		boolean isMymsg = isMyMsg(msg);
		ViewHolder viewHolder = null;

		if (isMymsg) {
			// 如果是对方发来的消息，则显示的是左气泡
			convertView = mInflater.inflate(
					R.layout.chatting_item_msg_text_left, null);
		} else {
			// 如果是自己发出的消息，则显示的是右气泡
			convertView = mInflater.inflate(
					R.layout.chatting_item_msg_text_right, null);
		}

		viewHolder = new ViewHolder();
		viewHolder.tvSendTime = (TextView) convertView
				.findViewById(R.id.tv_sendtime);
		viewHolder.tvUserName = (TextView) convertView
				.findViewById(R.id.tv_username);
		viewHolder.tvContent = (TextView) convertView
				.findViewById(R.id.tv_chatcontent);
		viewHolder.isMymsg = isMymsg;
		viewHolder.tvSendTime.setText(msg.Data);
		viewHolder.tvUserName.setText(msg.Sender);
		viewHolder.tvContent.setText(msg.Content);

		return convertView;
	}
	
	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public boolean isMymsg = true; 	
	}

	private boolean isMyMsg(Message msg)
	{	
		if (msg.Sender.equals(StaticDataPackage.LoginName)) {
			return false;
		}else {
			return true;
		}
	}

}
