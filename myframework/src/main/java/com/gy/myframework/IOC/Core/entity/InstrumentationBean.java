package com.gy.myframework.IOC.Core.entity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onBefore;
import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onDestory;
import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onNewIntent;
import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onResume;
import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onStart;
import com.gy.myframework.IOC.UI.view.activitylifeinject.annotation.onStop;
import com.gy.myframework.IOC.UI.view.viewinject.impl.ViewInjectUtilsImpl;
import com.gy.myframework.config.configs;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * Created by gy on 2015/11/18.
 */
public class InstrumentationBean extends Instrumentation {


    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        inject(activity, onBefore.class, null);

        super.callActivityOnCreate(activity, icicle);
        if (configs.isIOC) {
            ViewInjectUtilsImpl.getInstance().inject(activity);
        }
        super.callActivityOnCreate(activity,icicle);

    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        inject(activity,onDestory.class,null);
    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        super.callActivityOnNewIntent(activity, intent);
        inject(activity, onNewIntent.class, intent);
    }

    @Override
    public void callActivityOnStart(Activity activity) {
        super.callActivityOnStart(activity);
        inject(activity, onStart.class, null);
    }

    @Override
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
    }

    @Override
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        inject(activity, onResume.class, null);
    }

    @Override
    public void callActivityOnStop(Activity activity) {
        super.callActivityOnStop(activity);
        inject(activity, onStop.class, null);
    }

    private void inject(Activity activity, Class<?> clazz, Intent intent) {
        ArrayList<InjectInvoker> jArrayList = AnnotationPackage.getInstance().getContextInvokers(activity.getClass(), clazz);
        if (jArrayList == null) {
            return;
        }
        try {
            for (InjectInvoker injectInvoker : jArrayList) {
                if (intent != null) {
                    injectInvoker.invoke(activity, intent);
                } else {
                    injectInvoker.invoke(activity);
                }
            }
        } catch (Exception e) {
            StringWriter buf = new StringWriter();
            PrintWriter w = new PrintWriter(buf);
            e.printStackTrace(w);
        }
    }

}
