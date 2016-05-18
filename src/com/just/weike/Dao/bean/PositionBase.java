package com.just.weike.Dao.bean;

import java.io.Serializable;

public class PositionBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6049813342779606796L;
	protected String index;
	protected String name;
	
	public PositionBase() {
		// TODO Auto-generated constructor stub
	}
	
	public PositionBase(String index,String name) {
		// TODO Auto-generated constructor stub
		this.index = index;
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
