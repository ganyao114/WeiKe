package com.just.weike.Dao.bean;

import java.io.Serializable;

public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6251812685759942634L;
	
	public String country;
	public String provice;
	public String city;
	public String college;
	public String field;
	public String year;
	
/*	public Position(String country,String provice,String city,
			String college,String field,String year) {
		// TODO Auto-generated constructor stub
		this.country = country;
		this.provice = provice;
		this.city = city;
		this.college = college;
		this.field = field;
		this.year = year;
	} */

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
