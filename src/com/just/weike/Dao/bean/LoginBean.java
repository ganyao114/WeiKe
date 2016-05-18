package com.just.weike.Dao.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2734223793012430732L;
	public String LoginName;
	public String LoginPasswd;
	public LoginBean(String LoginName, String LoginPasswd) {
		// TODO Auto-generated constructor stub
		this.LoginName = LoginName;
		this.LoginPasswd = LoginPasswd;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getLoginPasswd() {
		return LoginPasswd;
	}
	public void setLoginPasswd(String loginPasswd) {
		LoginPasswd = loginPasswd;
	}

}
