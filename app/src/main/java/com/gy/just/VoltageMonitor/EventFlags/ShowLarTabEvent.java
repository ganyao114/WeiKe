package com.gy.just.VoltageMonitor.EventFlags;

import android.support.annotation.IdRes;
import android.widget.AdapterView;

import com.gy.just.VoltageMonitor.View.Activity.LarTabListActivity;
import com.gy.just.VoltageMonitor.View.Adapter.BaseMultiHostTabAdapter;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by gy on 2016/4/25.
 */
public class ShowLarTabEvent implements Serializable{

    private int tabid;

    private String title;
    private Class pojoclazz;
    private List list;
    private BaseMultiHostTabAdapter adapter;

    private @IdRes int contentViewId;
    private @IdRes int headId;
    private @IdRes int toolBarId;
    private @IdRes int listViewId;
    private @IdRes int headSrcrollViewId;
    private @IdRes int fabViewId;

    private WeakReference<LarTabListActivity> acRef;
    private AdapterView.OnItemClickListener listener;

    public int getTabid() {
        return tabid;
    }

    public void setTabid(int tabid) {
        this.tabid = tabid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getPojoclazz() {
        return pojoclazz;
    }

    public void setPojoclazz(Class pojoclazz) {
        this.pojoclazz = pojoclazz;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public BaseMultiHostTabAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(BaseMultiHostTabAdapter adapter) {
        this.adapter = adapter;
    }

    public int getContentViewId() {
        return contentViewId;
    }

    public void setContentViewId(int contentViewId) {
        this.contentViewId = contentViewId;
    }

    public int getHeadId() {
        return headId;
    }

    public void setHeadId(int headId) {
        this.headId = headId;
    }

    public int getToolBarId() {
        return toolBarId;
    }

    public void setToolBarId(int toolBarId) {
        this.toolBarId = toolBarId;
    }

    public int getListViewId() {
        return listViewId;
    }

    public void setListViewId(int listViewId) {
        this.listViewId = listViewId;
    }

    public int getHeadSrcrollViewId() {
        return headSrcrollViewId;
    }

    public void setHeadSrcrollViewId(int headSrcrollViewId) {
        this.headSrcrollViewId = headSrcrollViewId;
    }

    public int getFabViewId() {
        return fabViewId;
    }

    public void setFabViewId(int fabViewId) {
        this.fabViewId = fabViewId;
    }

    public WeakReference<LarTabListActivity> getAcRef() {
        return acRef;
    }

    public void setAcRef(WeakReference<LarTabListActivity> acRef) {
        this.acRef = acRef;
    }

    public AdapterView.OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }
}
