package com.just.weike.Dao;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.ClassifyBean;
import com.just.weike.Dao.bean.LoginBean;
import com.just.weike.Dao.bean.Message;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Dao.bean.PositionBean;
import com.just.weike.Dao.bean.RegistBean;
import com.just.weike.Dao.bean.ReplyBean;
import com.just.weike.Dao.bean.Student;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.DownloadReturn;

import android.graphics.Bitmap;
import android.os.Handler;


public interface IHttpService {
	public String UserLogin (String LoginName,String LoginPassword) throws Exception;
	public boolean regist(RegistBean bean) throws Exception;
	public List<String> GetSpinnerItems(String kind,String value) throws Exception;
	public List<Books> GetBooksList(String sessionid)throws Exception; //暂未加参数，可加索引参数
	public String UpLoad(String LoginName,InputStream in,Map<String, String> data,String filename,String sessionid) throws Exception; 
	public Bitmap getImage(String path) throws Exception;
	public DownloadReturn DownFile(String urlString,Handler handler)throws Exception;
	public Student getUserInfo()throws Exception;
	public Bitmap getBitmap(String url,File f)throws Exception;
	public String login(LoginBean loginBean) throws Exception;
	public boolean sendmsg(String LoginName,Message msg) throws Exception;
	public List<PositionBean> getPositionItems(String colume,String key)throws Exception;
	public List<ClassifyBean> getClassifyItems(String LoginName)throws Exception;
	public PagerViewBean getPageView(String LoginName)throws Exception;
	public List<TokeBean> getTokeList(String LoginName,Books book,int Page)throws Exception;
	public List<ReplyBean> getReply(TokeBean toke)throws Exception;
	public String BookCmd(String loginName, Books book, String cmd)throws Exception;
	public boolean SendToke(String loginName,TokeBean toke)throws Exception;
}
