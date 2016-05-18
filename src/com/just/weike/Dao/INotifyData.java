package com.just.weike.Dao;

import java.util.List;

import com.just.weike.Dao.bean.Notify;

public interface INotifyData {
	public boolean addnotify(Notify notify);
	public List<Notify> getnotifylist();
}
