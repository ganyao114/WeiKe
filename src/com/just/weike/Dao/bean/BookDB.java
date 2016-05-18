/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.just.weike.Dao.bean;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class BookDB extends BookBase implements Serializable{
       
	public String Position;
        public String ClassifyPath;

    public String getClassifyPath() {
        return ClassifyPath;
    }

    public void setClassifyPath(String ClassifyPath) {
        this.ClassifyPath = ClassifyPath;
    }
	
	public BookDB() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	
}
