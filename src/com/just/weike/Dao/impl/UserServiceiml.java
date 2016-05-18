package com.just.weike.Dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.just.weike.Dao.IHttpService;
import com.just.weike.Dao.bean.Books;
import com.just.weike.Dao.bean.ClassifyBean;
import com.just.weike.Dao.bean.LoginBean;
import com.just.weike.Dao.bean.Message;
import com.just.weike.Dao.bean.PagerViewBean;
import com.just.weike.Dao.bean.PositionBase;
import com.just.weike.Dao.bean.PositionBean;
import com.just.weike.Dao.bean.PositionPath;
import com.just.weike.Dao.bean.RegistBean;
import com.just.weike.Dao.bean.ReplyBean;
import com.just.weike.Dao.bean.Student;
import com.just.weike.Dao.bean.TokeBean;
import com.just.weike.Service.DownloadReturn;
import com.just.weike.Service.ServiceRulesException;
import com.just.weike.Service.impl.Imgloader.ImageLoader;
import com.just.weike.utils.AppConfig;
import com.just.weike.utils.ExceptionContent;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class UserServiceiml implements IHttpService {
	
	private final static IHttpService httpService = new UserServiceiml();
	private String ServiceUrl = AppConfig.ServiceUrl;
	
	@SuppressWarnings("unused")
	private static final String TAG = "UserServiceiml";
	@SuppressWarnings("unused")
	private Context context;
	
	public UserServiceiml() {
		// TODO Auto-generated constructor stub
	}
	
	public static IHttpService getInstance()
	{
		return httpService;
	}
	
	@Override
	public String UserLogin(String LoginName, String LoginPassword)
			throws Exception {
		// TODO Auto-generated method stub
		/*
		 * Log.d(TAG,LoginName); Log.d(TAG, LoginPassword); String uri =
		 * "http://192.168.1.107:8080/Gy/Login.do?LoginName="
		 * +LoginName+"&"+"LoginPassword="+LoginPassword; HttpClient client =
		 * new DefaultHttpClient(); HttpGet get = new HttpGet(uri); HttpResponse
		 * response=client.execute(get); if
		 * (response.getStatusLine().getStatusCode()!=HttpStatus.SC_OK) { throw
		 * new ServiceRulesException(Login.MEG_LOGIN_ERROR); } String result =
		 * EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
		 */
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = "http://192.168.1.107:8080/Gy/Login.do";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				LoginName);
		NameValuePair loginPasswordPair = new BasicNameValuePair(
				"LoginPassword", LoginPassword);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(loginPasswordPair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_LOGIN_ERROR);
		}
		String sessionid = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
		HttpClientFactory.CloseHttpClient(client, 20);
		if (!sessionid.isEmpty()) {
			
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_LOGIN_FAILED);
		}
		return sessionid;
	}
	@Override
	public boolean regist(RegistBean registBean) throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl+"RegistServlet";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				registBean.getLoginName());
		NameValuePair loginPasswordPair = new BasicNameValuePair(
				"LoginPassword", registBean.getPasswd());
		NameValuePair Mail = new BasicNameValuePair(
				"Mail", registBean.getMail());
		NameValuePair College = new BasicNameValuePair(
				"College", registBean.getCollege());
		NameValuePair Subject = new BasicNameValuePair(
				"Subject", registBean.getSubject());
		NameValuePair Grade = new BasicNameValuePair(
				"Grade", registBean.getGrade());
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(loginPasswordPair);
		parameters.add(College);
		parameters.add(Subject);
		parameters.add(Mail);
		parameters.add(Grade);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_CONNECT_TIMEOUT);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
	//	HttpClientFactory.CloseHttpClient(client, 20);
		if (result.equals("success")) {
			
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_FAILED);
		}
		return true;
	}

	public List<String> GetSpinnerItems(String kind, String value)
			throws Exception {
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = "http://192.168.1.107:8080/Gy/GetSpinnerItems.do";
		HttpPost post = new HttpPost(uri);
		NameValuePair kindPair = new BasicNameValuePair("kind",
				kind);
		NameValuePair valuePair = new BasicNameValuePair(
				"value", value);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(kindPair);
		parameters.add(valuePair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_REGIST_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
		HttpClientFactory.CloseHttpClient(client, 20);
		if (result !=null) {
			JSONArray jsonArray = new JSONArray(result);
			List<String> templist = new ArrayList<String>();
			templist.add("无");
			for (int i = 0;i <jsonArray.length();i++) {
				templist.add((String) jsonArray.get(i));
			}
			return templist;
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_FAILED);
		}
		
	}

	@Override
	public List<Books> GetBooksList(String sessionid) throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = AppConfig.ServiceUrl+"GetBookListServlet";
		HttpPost post = new HttpPost(uri);
		NameValuePair loginname = new BasicNameValuePair("LoginName",
				sessionid);
	
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(loginname);
	//	parameters.add(valuePair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			
			throw new ServiceRulesException(ExceptionContent.MEG_REGIST_ERROR);
		}
		
		
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		HttpClientFactory.CloseHttpClient(client, 20);
		if (result !=null) {
			JSONArray jsonArray = new JSONArray(result);
			List<Books> templist = new ArrayList<Books>();
			for (int i = 0;i <jsonArray.length();i++) {
				Log.e("gy", "2");
				JSONObject bookObject = jsonArray.getJSONObject(i);
				int id = bookObject.getInt("id");
				String name = bookObject.getString("name");
				String kind = bookObject.getString("kind");
				String Author = bookObject.getString("author");
				String size = bookObject.getString("size");
				Log.e("gy", "3");
				String uploadperson = bookObject.getString("upLoadPerson");
				String date = bookObject.getString("uploadDate");
				String Introduct = bookObject.getString("introduct");
				int isNew = bookObject.getInt("isNew");
				int pages = bookObject.getInt("pages");
				int ReadCount = bookObject.getInt("readCount");
				JSONObject positionpath = bookObject.getJSONObject("position");
				PositionPath position = new PositionPath();
				position.setCollege(positionpath.getString("college"));
				position.setSubject(positionpath.getString("subject"));
				position.setGrade(positionpath.getString("grade"));
				templist.add(new Books(id, name, Author, size, uploadperson, kind, position,date,pages,isNew,Introduct,ReadCount));
			}
			return templist;
		} else {
			
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_FAILED);
		}
		
		
	}

	@Override
	public String UpLoad(String LoginName,InputStream in, Map<String, String> data,String filename,String sessionid)
			throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();

		HttpPost post = new HttpPost("http://192.168.1.107:8080/Gy/UpLoad.do");
		MultipartEntity entity = new MultipartEntity();
		for (Map.Entry<String,String> entry:data.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			entity.addPart(key,new StringBody(value,Charset.forName("UTF-8")));
		}
		entity.addPart("file", new InputStreamBody(in, "multipart/form-data",filename));
		post.setEntity(entity);
		
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			
			throw new ServiceRulesException(ExceptionContent.MEG_REGIST_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
		HttpClientFactory.CloseHttpClient(client, 30);
		return result;
	}

	@Override
	public Bitmap getImage(String path) throws Exception {
		// TODO Auto-generated method stub
		Bitmap bitmap = null;
		//OutputStream out = null;
		InputStream in = null;
		URL url = null;
		HttpURLConnection urlConnection = null;
		try {
			url = new URL(path);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoInput(true);
			urlConnection.setRequestMethod("GET");
			urlConnection.connect();
			int responseCode = urlConnection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new ServiceRulesException("连接服务器出错");
			}
			in = urlConnection.getInputStream();
			if (in != null) {
				bitmap = BitmapFactory.decodeStream(in);
			}
		} finally {
			if (in!=null) {
				in.close();
			}
			if (urlConnection!=null) {
				urlConnection.disconnect();
			}
		}
		return bitmap;
	}

	public DownloadReturn DownFile(String urlString,Handler handler) throws Exception
    {
         
        /*
         * 连接到服务器
         */
		URLConnection connection = null;
		InputStream inputStream = null;
		OutputStream outputStream;
		int FileLength;
        try {
             URL url=new URL(urlString);
             connection=url.openConnection();
             if (connection.getReadTimeout()==5) {
                throw new ServiceRulesException("连接服务器失败!");
                // return;
               }
             inputStream=connection.getInputStream();
             
        } catch (MalformedURLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        
        String savePAth=Environment.getExternalStorageDirectory()+"/Weike";
        File file1=new File(savePAth);
        if (!file1.exists()) {
            file1.mkdir();
        }
        String savePathString=Environment.getExternalStorageDirectory()+"/Weike/"+file1.getName()+".zip";
        File file =new File(savePathString);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }  
        }
       
        FileLength=connection.getContentLength();
        
        return new DownloadReturn(FileLength, inputStream,file);
    }

	@Override
	public Student getUserInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Bitmap getBitmap(String url,File f) throws Exception{
		try {
			Bitmap bitmap = null;
			URL imageUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imageUrl
					.openConnection();
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setInstanceFollowRedirects(true);
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new ServiceRulesException("连接服务器出错");
			}
			InputStream is = conn.getInputStream(); 
			OutputStream os = new FileOutputStream(f);
			
			ImageLoader.CopyStream(is, os);
			os.close();
			bitmap = ImageLoader.decodeFile(f);
			return bitmap;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String login(LoginBean loginBean) throws Exception{
		// TODO Auto-generated method stubh
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl+"LoginServlet";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				loginBean.LoginName);
		NameValuePair loginPasswordPair = new BasicNameValuePair(
				"LoginPassword", loginBean.LoginPasswd);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(loginPasswordPair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_LOGIN_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
	//	HttpClientFactory.CloseHttpClient(client, 20);
		if (result.equals("success")||result.equals("logined")) {
			
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_LOGIN_FAILED);
		}
		return result;
	}

	@Override
	public boolean sendmsg(String LoginName,Message msg) throws Exception{
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl+"SendMsg";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				LoginName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Sender", msg.Sender);
		jsonObject.put("Receiver", msg.Receiver);
		jsonObject.put("Content", msg.Content);
		jsonObject.put("Date",msg.Data);
		NameValuePair MsgPair = new BasicNameValuePair(
				"Message",jsonObject.toString());
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(MsgPair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MSG_LINK_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
	//	HttpClientFactory.CloseHttpClient(client, 20);
		if (result != null) { 
			
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_SEND_FAILED);
		} 
		return true;
	}

	@Override
	public List<PositionBean> getPositionItems(String colume, String key)
			throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl;
		HttpPost post = new HttpPost(uri);
		NameValuePair kindPair = new BasicNameValuePair("colume",
				colume);
		NameValuePair valuePair = new BasicNameValuePair(
				"key", key);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(kindPair);
		parameters.add(valuePair);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_REGIST_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
		HttpClientFactory.CloseHttpClient(client, 20);
		if (result !=null) {
			JSONArray jsonArray = new JSONArray(result);
			List<PositionBean> templist = new ArrayList<PositionBean>();
			for (int i = 0;i <jsonArray.length();i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String namereturn = object.getString("name");
				String columereturn = object.getString("colume");
				String indexreturn = object.getString("index");
				templist.add(new PositionBean(indexreturn, namereturn, columereturn));
			}
			return templist;
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_FAILED);
		}
	}

	@Override
	public List<ClassifyBean> getClassifyItems(String LoginName)
			throws Exception {
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl;
		HttpPost post = new HttpPost(uri);
		NameValuePair username = new BasicNameValuePair("loginname",
				LoginName);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(username);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_REGIST_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
		HttpClientFactory.CloseHttpClient(client, 20);
		if (result !=null) {
			JSONArray jsonArray = new JSONArray(result);
			List<ClassifyBean> templist = new ArrayList<ClassifyBean>();
			for (int i = 0;i <jsonArray.length();i++) {
				JSONObject object = jsonArray.getJSONObject(i);
				String namereturn = object.getString("name");
				String urlreturn = object.getString("coverurl");
				templist.add(new ClassifyBean(namereturn,urlreturn));
			}
			return templist;
		} else {
			throw new ServiceRulesException(ExceptionContent.MSG_REGIST_FAILED);
		}
	}

	@Override
	public PagerViewBean getPageView(String LoginName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TokeBean> getTokeList(String LoginName, Books book, int Page)
			throws Exception {
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl+"GetTokeListServlet";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				LoginName);
		NameValuePair bookidpar = new BasicNameValuePair(
				"bookid", book.getId()+"");
		NameValuePair pageid = new BasicNameValuePair(
				"page", Page+"");
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(bookidpar);
		parameters.add(pageid);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException("错误");
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
	//	HttpClientFactory.CloseHttpClient(client, 20);
		if (result!="unlogined") {
			
		} else {
			throw new ServiceRulesException("未登录");
		}
		
		JSONArray tokearray = new JSONArray(result);
		List<TokeBean> tokes = new ArrayList<TokeBean>();
		
		for(int i = 0;i < tokearray.length();i++){
			JSONObject tokeobj = tokearray.getJSONObject(i);
			TokeBean bean = new TokeBean();
			bean.setBookId(tokeobj.getInt("bookId"));
			bean.setContent(tokeobj.getString("content"));
			bean.setDate(tokeobj.getString("date"));
			bean.setImgCount(tokeobj.getInt("imgCount"));
			bean.setPage(tokeobj.getInt("page"));
			bean.setTitle(tokeobj.getString("title"));
			bean.setTokeid(tokeobj.getInt("tokeid"));
			bean.setSender(tokeobj.getString("sender"));
			tokes.add(bean);
		}
		
		return tokes;
	}

	@Override
	public List<ReplyBean> getReply(TokeBean toke) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String BookCmd(String loginName, Books book, String cmd)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean SendToke(String loginName, TokeBean toke) throws Exception {
		// TODO Auto-generated method stub
		HttpClient client = HttpClientFactory.GetHttpClient();
		String uri = ServiceUrl+"SendTokeServlet";
		HttpPost post = new HttpPost(uri);
		NameValuePair LoginNamePair = new BasicNameValuePair("LoginName",
				loginName);
		
		JSONObject tokeobj = new JSONObject();
		tokeobj.put("Title", toke.getTitle());
		tokeobj.put("Content", toke.getContent());
		tokeobj.put("Bookid", toke.getBookId());
		tokeobj.put("Sender", toke.getSender());
		tokeobj.put("Page", toke.getPage());
		
		NameValuePair tokepar = new BasicNameValuePair(
				"Toke",tokeobj.toString());
	
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(LoginNamePair);
		parameters.add(tokepar);
		post.setEntity(new UrlEncodedFormEntity(parameters, HTTP.UTF_8));
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ServiceRulesException(ExceptionContent.MEG_LOGIN_ERROR);
		}
		String result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
	//	client.getConnectionManager().shutdown();//关闭连接，未验证
	//	HttpClientFactory.CloseHttpClient(client, 20);
		if (result.equals("sucesses")) {
			
		} else {
			throw new ServiceRulesException("发送失败");
		}
		return false;
	}

	

}
