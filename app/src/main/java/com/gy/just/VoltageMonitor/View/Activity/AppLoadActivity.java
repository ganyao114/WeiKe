package com.gy.just.VoltageMonitor.View.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gy.just.VoltageMonitor.Control.Service.NotiService;
import com.gy.just.VoltageMonitor.EventFlags.LoginedEvent;
import com.gy.just.VoltageMonitor.Model.Bean.UserBean;
import com.gy.just.VoltageMonitor.Model.Bean.UserSp;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;
import com.gy.myframework.IOC.Service.event.annotation.InjectEvent;
import com.gy.myframework.IOC.Service.event.entity.EventThreadType;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class AppLoadActivity extends Activity implements View.OnClickListener {

    private ImageView loadingIv;
//    private TextView appname;

    private ProgressDialog pDialog;
    private String nowVersion;
    private ProgressDialog progressDialog;
    private final static String fileName = "mddianya";
    private String newVersionName = "";
    private boolean isNeedUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 取消状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_load);

        // 三秒钟之后进入login
        loadingIv = (ImageView) this.findViewById(R.id.imageView);
//        appname = (TextView) findViewById(R.id.appname);
        loadingIv.setOnClickListener(this);
        // 从浅到深,从百分之10到百分之百
        AlphaAnimation animation = new AlphaAnimation(0.1f, 1.0f);

        animation.setDuration(1500);


        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1500);
        loadingIv.setAnimation(animation);
//        appname.setAnimation(animation);
        // 给animation设置监听

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(), 0);
            nowVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        checkUpdate();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isNeedUpdate)
                    toLoginView();
            }
        });
    }



    private void toLoginView(){
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        toLoginView();
    }


    /**
     * 下载更新,
     */
    protected void checkUpdate() {
        // TODO Auto-generated method stub
//        proDialogShow(this, "正在查询...");
        RequestParams params = new RequestParams(Config.Url.UPDATE_CHECK());
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
//                PDialogHide();
                System.out.println("提示网络错误");
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(String arg0) {
                // TODO Auto-generated method stub
//                PDialogHide();
                try {
                    JSONObject object = new JSONObject(arg0);
                    if (object != null) {
                        String desc = object.getString("description");
                        String downloadurl = object.getString("downloadUrl");
                        String versionname = object.getString("versionName");
                        newVersionName = versionname;
                        if (nowVersion.equals(versionname)) {
                        } else {
                            // 不同，弹出更新提示对话框
                            isNeedUpdate = true;
                            setUpDialog(versionname, downloadurl, desc);
                        }
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *
     * @param versionname
     *            地址中版本的名字
     * @param downloadurl
     *            下载包的地址
     * @param desc
     *            版本的描述
     */
    protected void setUpDialog(String versionname, final String downloadurl,
                               String desc) {
        // TODO Auto-generated method stub
        AlertDialog dialog = new AlertDialog.Builder(this).setCancelable(false)
                .setTitle("下载" + versionname + "版本").setMessage(desc)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toLoginView();
                    }
                })
                .setPositiveButton("下载", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        setDownLoad(downloadurl);
                    }
                }).create();
        dialog.show();
    }

    /**
     * 下载包
     *
     * @param downloadurl
     *            下载的url
     *
     */
    @SuppressLint("SdCardPath")
    protected void setDownLoad(String downloadurl) {
        // TODO Auto-generated method stub
        RequestParams params = new RequestParams(downloadurl);
        params.setAutoRename(true);//断点下载
        params.setSaveFilePath(Environment
                .getExternalStorageDirectory() + "/" + getFileName());
        x.http().get(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                System.out.println("提示更新失败");
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onSuccess(File arg0) {
                // TODO Auto-generated method stub
                if(progressDialog!=null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), getFileName())),
                        "application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onLoading(long arg0, long arg1, boolean arg2) {
                // TODO Auto-generated method stub
                progressDialog.setMax((int)arg0);
                progressDialog.setProgress((int)arg1);
            }

            @Override
            public void onStarted() {
                // TODO Auto-generated method stub
                System.out.println("开始下载");
                progressDialog = new ProgressDialog(AppLoadActivity.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置为水平进行条
                progressDialog.setMessage("正在下载中...");
                progressDialog.setProgress(0);
                progressDialog.show();
            }

            @Override
            public void onWaiting() {
                // TODO Auto-generated method stub

            }
        });
    }

//    private void proDialogShow(Context context, String msg) {
//        pDialog = new ProgressDialog(context);
//        pDialog.setMessage(msg);
//        // pDialog.setCancelable(false);
//        pDialog.show();
//    }
//
//    private void PDialogHide() {
//        try {
//            if (pDialog != null && pDialog.isShowing()) {
//                pDialog.dismiss();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private String getFileName(){
        return fileName + newVersionName + ".apk";
    }
}
