package com.just.weike.Dao.bean;

import java.io.Serializable;

public class RegistBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4456720217645900610L;
	
	private String college;
	private String subject;
	private String username;
	private String LoginName;
	private String passwd;
	private long num;
	private String grade;
	private String mail;
	
	public RegistBean() {
		// TODO Auto-generated constructor stub
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginName() {
		return LoginName;
	}

	public void setLoginName(String loginName) {
		LoginName = loginName;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
