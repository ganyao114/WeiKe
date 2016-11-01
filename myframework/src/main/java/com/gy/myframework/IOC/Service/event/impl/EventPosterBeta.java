package com.gy.myframework.IOC.Service.event.impl;

import android.os.Handler;
import android.os.Looper;

import com.gy.myframework.IOC.Core.invoke.DynamicHandler;
import com.gy.myframework.IOC.Service.event.entity.EventPackage;
import com.gy.myframework.IOC.Service.event.parase.EventAnnotationsFactory;
import com.gy.myframework.IOC.Service.event.proxy.EventRunnable;
import com.gy.myframework.IOC.Service.event.proxy.IEventProxy;
import com.gy.myframework.Service.thread.pool.impl.MyWorkThreadQueue;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by gy on 2015/11/24.
 */
public class EventPosterBeta {

    private static EventPosterBeta eventPoster;

    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new WeakHashMap<Class<?>, List<Class<?>>>();
    private static WeakHashMap<Class<?>,List<WeakReference<EventPackage>>> broadCastEvents;
    private static HashMap<Class<?>,HashMap<String,EventPackage>> postEvents;
    private String callMethod = "onEvent";
    private Class<?> callInter = IEventProxy.class;
    private Handler mainHandler;

    public static EventPosterBeta getInstance(){
        synchronized (EventPosterBeta.class) {
            if (eventPoster == null)
                eventPoster = new EventPosterBeta();
        }
        return eventPoster;
    }

    private EventPosterBeta() {
        broadCastEvents = new WeakHashMap<Class<?>,List<WeakReference<EventPackage>>>();
        postEvents = new HashMap<Class<?>,HashMap<String,EventPackage>>();
    }

    public void regist(Object object){
        EventAnnotationsFactory.getInstance().getEventAnnotations(object);
    }

    public void unregist(Object object){
        postEvents.remove(object.getClass());
        System.gc();
    }

    public void post(String key,Object object){
        for (Map.Entry<Class<?>,HashMap<String,EventPackage>> entry:postEvents.entrySet()){
            Map<String,EventPackage> tmp = entry.getValue();
            if (tmp.containsKey(key))
                doPost(tmp.get(key),object);
        }
    }

    public void broadcast(Object object){
        List<WeakReference<EventPackage>> list = broadCastEvents.get(object.getClass());
        if (list == null||list.size() == 0)
            return;
        for (WeakReference<EventPackage> broadRef:list){
            EventPackage event = broadRef.get();
            if (event == null)
                continue;
            if (event.getRegister().get() == null){
                list.remove(broadRef);
                continue;
            }
            doPost(event,object);
        }
    }

    private void doPost(EventPackage event, Object object) {
        switch (event.getMode()){
            case MainThread:
                doPostMainThread(event,object);
                break;
            case AsyncTask:
                doPostAsyc(event,object);
                break;
            case kidsThread:
                doPostKidsThread(event,object);
                break;
            case PostThread:
                break;
        }
    }

    private void doPostKidsThread(EventPackage event, Object object) {
        boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();
        if (isMainThread){
            doPostAsyc(event,object);
        }else {
            Object register = event.getRegister().get();
            if (register == null)
                return;
                try {
                    event.getMethod().invoke(register,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
        }
    }

    private void doPostAsyc(EventPackage event, Object object) {
        Object register = event.getRegister().get();
        if (register == null)
            return;
        DynamicHandler handler = new DynamicHandler(event.getRegister().get());
        handler.addMethod(callMethod, event.getMethod());
        Object bussnessproxy = Proxy.newProxyInstance(callInter.getClassLoader(), new Class<?>[]{callInter}, handler);
        if (register == null)
            return;
        MyWorkThreadQueue.AddTask(new EventRunnable((IEventProxy) bussnessproxy,object));
    }

    private void doPostMainThread(EventPackage event, Object object) {
        boolean isMainThread = Looper.getMainLooper() == Looper.myLooper();
        Object register = event.getRegister().get();
        if (register == null)
            return;
        if (isMainThread){
            try {
                event.getMethod().invoke(register,object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return;
        }
        DynamicHandler handler = new DynamicHandler(event.getRegister().get());
        handler.addMethod(callMethod, event.getMethod());
        Object bussnessproxy = Proxy.newProxyInstance(callInter.getClassLoader(), new Class<?>[]{callInter}, handler);
        if (mainHandler == null)
            mainHandler = new Handler(Looper.getMainLooper());
        if (register == null)
            return;
        mainHandler.post(new EventRunnable((IEventProxy) bussnessproxy,object));
    }

    public WeakHashMap<Class<?>, List<WeakReference<EventPackage>>> getBroadCastEvents() {
        return broadCastEvents;
    }


    public HashMap<Class<?>, HashMap<String, EventPackage>> getPostEvents() {
        return postEvents;
    }

}
