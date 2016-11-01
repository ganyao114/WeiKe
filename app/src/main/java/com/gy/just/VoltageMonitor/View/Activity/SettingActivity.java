package com.gy.just.VoltageMonitor.View.Activity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Fragment.SettingFragment;

import java.io.IOException;


public class SettingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingFragment()).commit();
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
        try {
            wallpaperManager.setBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.login_back2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
