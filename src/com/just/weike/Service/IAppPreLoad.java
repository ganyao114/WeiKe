package com.just.weike.Service;

import java.util.List;

public interface IAppPreLoad {
	public void firstruninit();
	public List<String> getSubjectsLocal();
	public List<String> getSubjectsOnline();
	public boolean SetSubujectsData();
}
