package com.gy.myframework.Model.local.file.filemanager;

import com.gy.myframework.Model.local.file.filemanager.entity.TmpFileInfo;

/**
 * Created by gy on 2015/11/7.
 */
public interface IFileManagerCallback {
    public void onFileOpDeration(TmpFileInfo info);
    public void onCompelet(TmpFileInfo info);
}
