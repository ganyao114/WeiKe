package com.gy.just.VoltageMonitor.Control.Presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Control.Utils.MathUtils;
import com.gy.just.VoltageMonitor.EventFlags.ShowLocalEvent;
import com.gy.just.VoltageMonitor.EventFlags.ShowPositionEvent;
import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetNewsModel;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;
import com.gy.myframework.MVP.Presenter.Presenter;
import com.gy.myframework.MVP.View.context.entity.ContextChangeEvent;

import java.util.List;

/**
 * Created by gy on 2016/4/11.
 */
public class MainPresenter extends Presenter implements LocationListener {
    private LocationManager locationManager;
    private String locationProvider;

    @Override
    protected void onContextChanged(ContextChangeEvent event) {

    }

    @Override
    public void OnPresentInited(Context context) {
        initLoaction();
    }

    private void initLoaction() {
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(getContext(), "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return;
        }

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            //不为空,显示地理位置经纬度
            showLocation(location);
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 3000, 1, this);
    }

    private void showLocation(Location location) {
        Global.gpsx = MathUtils.convert4((float) location.getLatitude());
        Global.gpsy = MathUtils.convert4((float) location.getLongitude());
        Global.gpsz = MathUtils.convert4((float) location.getAltitude());
        ShowLocalEvent event = new ShowLocalEvent("纬度：" + Global.gpsx,"经度：" + Global.gpsy);
        EventPoster.BroadInMainloop(event);
        ShowPositionEvent event1 = new ShowPositionEvent();
        event1.x = Global.gpsx+"";
        event1.y = Global.gpsy+"";
        EventPoster.Broadcast(event1);
    }

    @Override
    public void DestoryPresent() {
        super.DestoryPresent();
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            }
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
