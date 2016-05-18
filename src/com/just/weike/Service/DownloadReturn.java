package com.just.weike.Service;

import java.io.File;
import java.io.InputStream;

public class DownloadReturn {
	public int Filelenth;
	public InputStream inputStream;
	public File file;
	public DownloadReturn(int Filelenth,InputStream inputStream,File file) {
		// TODO Auto-generated constructor stub
		this.Filelenth = Filelenth;
		this.inputStream = inputStream;
		this.file = file;
	}

}
