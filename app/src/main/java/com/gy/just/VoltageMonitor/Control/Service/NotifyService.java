package com.gy.just.VoltageMonitor.Control.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.gy.just.VoltageMonitor.Model.Global.Global;
import com.gy.just.VoltageMonitor.Model.Net.GetNotifyModel;
import com.gy.myframework.IOC.Service.event.impl.EventPoster;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotifyService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_NOTIFY = "com.gy.just.VoltageMonitor.Control.Service.action.StartNotify";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.gy.just.VoltageMonitor.Control.Service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.gy.just.VoltageMonitor.Control.Service.extra.PARAM2";

    public NotifyService() {
        super("NotifyService");
        EventPoster.Regist(this);
        Log.e("gy","NotifyService");
        handleActionStartNotify(Global.user.getLoginname(),"");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionStartNotify(Context context, String param1, String param2) {
        Intent intent = new Intent(context, NotifyService.class);
        intent.setAction(ACTION_NOTIFY);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_NOTIFY.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionStartNotify(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStartNotify(String param1, String param2) {
        GetNotifyModel model = new GetNotifyModel(param1);
        model.doHttp();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        EventPoster.Unregist(this);
    }
}
