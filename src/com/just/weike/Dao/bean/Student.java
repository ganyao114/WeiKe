package com.just.weike.Dao.bean;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{
	
	private static final long serialVersionUID = -5106735720231210692L;
	public String UserName;
	public String LoginName;
	public String PassWord;
	public String Gender;
	public String Job;
	public List<String> Position;
	public List<String> Subjects;
	public int Schoolid;
	public int Classid;
	public Student(String UserName,String LoginName, String PassWord, String Gender,
			List<String> Position,String Job,int Schoolid,int Classid,List<String> Subjects) {
		// TODO Auto-generated constructor stub
		this.UserName = UserName;
		this.LoginName = LoginName;
		this.PassWord = PassWord;
		this.Gender = Gender;
		this.Job = Job;
		this.Position = Position;
		this.Schoolid = Schoolid;
		this.Classid = Classid;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String loginName) {
		LoginName = loginName;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public List<String> getPosition() {
		return Position;
	}
	public void setPosition(List<String> position) {
		Position = position;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	public int getSchoolid() {
		return Schoolid;
	}
	public void setSchoolid(int schoolid) {
		Schoolid = schoolid;
	}
	public int getClassid() {
		return Classid;
	}
	public void setClassid(int classid) {
		Classid = classid;
	}
	public List<String> getSubjects() {
		return Subjects;
	}
	public void setSubjects(List<String> subjects) {
		Subjects = subjects;
	}

}
