package com.just.weike.ui;

import java.util.List;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.NavigationItem;
import com.just.weike.Dao.bean.NavigationTopBean;

public interface INavigationFragmentControl {
	public void RefreshList(List<NavigationItem> list);
	public void SetBookTitle(Books book);
	public void SetUserTitle(NavigationTopBean bean);
}
