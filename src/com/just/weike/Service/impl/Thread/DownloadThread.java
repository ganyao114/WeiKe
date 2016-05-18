package com.just.weike.Service.impl.Thread;

import java.io.FileOutputStream;
import java.io.OutputStream;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.just.weike.Dao.bean.HttpTheadConfigBean;
import com.just.weike.Service.DownloadReturn;
import com.just.weike.Service.impl.Thread.Templet.HttpThreadTemplet;

public class DownloadThread extends HttpThreadTemplet {
	
	private Handler handler;
	private String fileString;
	private DownloadReturn downloadReturn;
	private OutputStream outputStream;
	public int DownedFileLength=0;
	public int FileLength;
	
	public DownloadThread(Handler handler,String fileString) {
		super(handler);
		// TODO Auto-generated constructor stub
		this.fileString = fileString;
	}

	@Override
	protected void OnRun() throws Exception {
		// TODO Auto-generated method stub
		Message message = new Message();
		downloadReturn = httpService.DownFile(fileString, handler);
		FileLength = downloadReturn.Filelenth;
		outputStream = new FileOutputStream(downloadReturn.file);
		byte[] buffer = new byte[1024 * 4];

		message.what = 0;
		handler.sendMessage(message);
		while (DownedFileLength < downloadReturn.Filelenth) {
			outputStream.write(buffer);
			DownedFileLength += downloadReturn.inputStream.read(buffer);
			Log.i("-------->", DownedFileLength + "");
			Message message1 = new Message();
			message1.what = 1;
			handler.sendMessage(message1);
		}
		Message message2 = new Message();
		message2.what = 2;
		handler.sendMessage(message2);
	}

	@Override
	protected HttpTheadConfigBean SetConfig() {
		HttpTheadConfigBean configBean = new HttpTheadConfigBean
				(false, 0, "连接超时", "加载超时","加载错误");
		return configBean; 
	}

}
