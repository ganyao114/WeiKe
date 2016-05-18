package com.just.weike.Service.impl;

import java.lang.ref.WeakReference;

import com.gy.materialdesign.views.ProgressBarDeterminate;
import com.just.weike.Service.impl.Thread.DownloadThread;
import com.just.weike.Service.impl.Thread.Pool.MyWorkThreadQueue;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class DownloadManager {
	private static ProgressBarDeterminate progressBar;
	private static Context mContext;
	private Handler myHandler;
	private DownloadThread downloadRunnable;
	public DownloadManager(Context context,String filepath) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
		downloadRunnable = new DownloadThread(myHandler,filepath);
		myHandler = new Myhandler(downloadRunnable);
		download();
	}
	
	public void download() {
		MyWorkThreadQueue.AddTask(downloadRunnable);
	}
	
	private static class Myhandler extends Handler
	{
		WeakReference<DownloadThread> mcontext;
		public Myhandler(DownloadThread mcontext)
		{
			this.mcontext = new WeakReference<DownloadThread>(mcontext);
		}
		public void handleMessage(Message msg)
        {
        if (!Thread.currentThread().isInterrupted()) {
            switch (msg.what) {
            case 0:
                progressBar.setMax(mcontext.get().FileLength);
                Log.i("文件长度----------->", mcontext.get().FileLength+""); 
                break;
            case 1:
                progressBar.setProgress(mcontext.get().DownedFileLength);
                int x=mcontext.get().DownedFileLength*100/mcontext.get().FileLength;
            //    textView.setText(x+"%");
                break;
            case 2:
                Toast.makeText(mContext, "下载完成", Toast.LENGTH_LONG).show();
                break;
                 
            default:
                break;
            }
        }  
        }
	}
}
