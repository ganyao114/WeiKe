package com.just.weike.test;

import java.util.ArrayList;
import java.util.List;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.ClassifyBean;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Dao.bean.PositionBean;
import com.just.weike.Dao.bean.TokeBean;

public class TestDao {

	public TestDao() {
		// TODO Auto-generated constructor stub
	}
	
	public static PagerViewBean getPagerViewBean()
	{	
		PagerViewBean bean = new PagerViewBean();
		bean.setTitle(new String[]{"公告1","公告2","公告3","公告4","公告5"});
		bean.setImgsrc(new String[]{"http://down1.sucaitianxia.com/ppt/19/ppt5045.jpg","http://pic.58pic.com/58pic/16/84/10/558PICc58PICQ9N_1024.jpg"
				,"http://www.yanj.cn/upload/editor/20140120160119_13197.png","http://www.yanj.cn/upload/store/goods/144/144_869c84546a5fd9ca77cbf016c94f151e.jpg_max.jpg","http://pic25.nipic.com/20121209/9252150_194258033000_2.jpg"});
		return bean;
	}
	
	public static List<Books> getBooks()
	{
		List<Books> books = new ArrayList<Books>();
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "编译原理", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 0, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "Android编程基础", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 0, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 0, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 0, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 0, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		books.add(new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20));
		return books;
	}
	
	public static List<ClassifyBean> getClassifyBeans()
	{
		List<ClassifyBean> list = new ArrayList<ClassifyBean>();
		list.add(new ClassifyBean("Java", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("C", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("C++", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("C#", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("Physon", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("JSP", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		list.add(new ClassifyBean("ARM ASM", "http://img.web07.cn/uploads/QQ/c111018/131Y02B2620-2V19.png"));
		return list;
	}
	
	public static List<PositionBean> getPositionBeans(String kind)
	{	
		List<PositionBean> list = new ArrayList<PositionBean>();
		switch (kind) {
		case "init":
			list.add(new PositionBean("J", "江苏", "province"));
			break;
		case "province":
			list.add(new PositionBean("N", "南京", "city"));
			list.add(new PositionBean("Z", "镇江", "city"));
			break;
		case "city":
			list.add(new PositionBean("J", "江苏科技大学", "college"));
			list.add(new PositionBean("J", "江苏大学", "college"));
			break;
		case "college":
			list.add(new PositionBean("J", "计算机科学与技术", "subject"));
			list.add(new PositionBean("R", "软件工程", "subject"));
			break;
		case "subject":
			list.add(new PositionBean("D", "大一", "grade"));
			list.add(new PositionBean("D", "大二", "grade"));
			list.add(new PositionBean("D", "大三", "grade"));
			list.add(new PositionBean("D", "大四", "grade"));
			list.add(new PositionBean("Y", "研一", "grade"));
			list.add(new PositionBean("Y", "研二", "grade"));
			list.add(new PositionBean("Y", "研三", "grade"));
			break;
		default:
			break;
		}
		
		return list;
	}

	public static Books GetBook()
	{
		return new Books(1, "计算机操作系统", "甘尧", "15MB", "管理员", "PDF", null, "2015/9/13", 15, 1, "这是一本关于计算机操作系统的书",20);
	}
	
	public static List<TokeBean> getToke()
	{
		List<TokeBean> list = new ArrayList<TokeBean>();
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		list.add(new TokeBean("提个问", "admin", 1, "测试，这本书是啥意思", "2015/9/13", 0, 0, 6, 0));
		return list;
	}
	
}
