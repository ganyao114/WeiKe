package com.just.weike.Service;

import com.just.weike.Dao.bean.Notify;

public interface INotifyControl {
	public boolean sendnotify(Notify notify,String receiver);
}
