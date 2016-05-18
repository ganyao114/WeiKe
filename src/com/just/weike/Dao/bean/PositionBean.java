package com.just.weike.Dao.bean;

public class PositionBean extends PositionBase{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3707611883499377821L;
	private String colume;
	
	public PositionBean() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public PositionBean(String index,String name,String colume) {
		// TODO Auto-generated constructor stub
		super(index, name);
		this.colume = colume;
	}
	public String getColume() {
		return colume;
	}
	public void setColume(String colume) {
		this.colume = colume;
	}

}
