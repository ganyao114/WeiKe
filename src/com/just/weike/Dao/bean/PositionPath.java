package com.just.weike.Dao.bean;

import java.io.Serializable;

public class PositionPath implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7066033588117775979L;
	private String college;
	private String subject;
	private String grade;
	
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public PositionPath() {
		// TODO Auto-generated constructor stub
	}

}
