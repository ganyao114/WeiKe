/*
 * @Title ThreadDAO.java
 * @Copyright Copyright 2010-2015 Yann Software Co,.Ltd All Rights Reserved.
 * @Description��
 * @author Yann
 * @date 2015-8-8 ����10:55:21
 * @version 1.0
 */
package com.gy.myframework.Service.loader.downloader.db;

import com.gy.myframework.Service.loader.downloader.entity.ThreadInfo;

import java.util.List;


public interface ThreadDAO {

    public void insertThread(ThreadInfo threadInfo);


    public void deleteThread(String url, int thread_id);


    public void updateThread(String url, int thread_id, int finished);


    public List<ThreadInfo> getThreads(String url);


    public boolean isExists(String url, int thread_id);
}
