package com.gy.just.VoltageMonitor.View.Fragment;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.gy.just.VoltageMonitor.Model.Bean.UrlSetting;
import com.gy.just.VoltageMonitor.Model.Global.Config;
import com.gy.just.VoltageMonitor.Model.SharePrefrence.PhoneSetting;
import com.gy.just.VoltageMonitor.R;
import com.gy.just.VoltageMonitor.View.Activity.LoginActivity;
import com.gy.myframework.IOC.Model.local.shareprefrence.impl.SharePrefrenceUtils;
import com.gy.myframework.UI.customwidget.materaldialog.MaterialDialog;


/**
 * Created by tomchen on 2/27/16.
 */
public class SettingFragment extends PreferenceFragment {

    CheckBoxPreference wifi,bt;
    Preference url;

    private MaterialDialog urlsetDia;
    private AppCompatEditText urlEt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

//        SwitchCompat ss = (SwitchCompat) getActivity().findViewById(R.id.switch_compat);
//        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Logger.d("SwitchCompat " + buttonView + " changed to " + isChecked);
//            }
//        });

        wifi = (CheckBoxPreference) getPreferenceManager().findPreference("wifi");
        bt = (CheckBoxPreference) getPreferenceManager().findPreference("bluetooth");
        url = getPreferenceManager().findPreference("url");

        final PhoneSetting setting = new PhoneSetting();
        SharePrefrenceUtils.Get(getActivity(), setting);

        wifi.setChecked(setting.iswifi());

        bt.setChecked(setting.isbluetooth());

        wifi.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {

                    boolean checked = Boolean.valueOf(newValue.toString());
                    setting.setIswifi(checked);
                    SharePrefrenceUtils.Save(setting);
                    return true;

        }});

        bt.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                boolean checked = Boolean.valueOf(newValue.toString());
                setting.setIsbluetooth(checked);
                SharePrefrenceUtils.Save(setting);
                return true;

            }});

        url.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                setUrlsetDia();
                return true;
            }
        });

    }

    private void setUrlsetDia(){
        if (urlsetDia == null){
            urlsetDia = new MaterialDialog(getActivity());
            urlsetDia.setTitle("设置服务器地址");
            urlEt = new AppCompatEditText(getActivity());
            urlsetDia.setContentView(urlEt);
            urlsetDia.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = urlEt.getText().toString().trim();
                    if (TextUtils.isEmpty(url)){
                        Toast.makeText(getActivity(),"URL为空",Toast.LENGTH_LONG).show();
                        return;
                    }
                    UrlSetting setting = new UrlSetting();
                    setting.setBaseurl(url);
                    SharePrefrenceUtils.Save(setting);
                    Toast.makeText(getActivity(),"URL已设置",Toast.LENGTH_LONG).show();
                    Config.Url.BASE = url;
                    urlsetDia.dismiss();
                }
            });
            urlsetDia.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    urlsetDia.dismiss();
                }
            });
        }
        urlsetDia.show();
    }
}
