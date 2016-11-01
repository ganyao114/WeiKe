package com.gy.just.VoltageMonitor.Model.Bean;

import com.gy.just.VoltageMonitor.R;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.BindText;
import com.gy.myframework.IOC.UI.view.viewbinder.annotation.ListDataSrc;

import java.io.Serializable;

/**
 * Created by gy on 2016/5/2.
 */
@ListDataSrc(R.layout.item_filtertype_layout)
public class KindsCardPojo implements Serializable{
    @BindText(R.id.type_name)
    private String pt_name;
    private String pt_id;

    public String getPt_name() {
        return pt_name;
    }

    public void setPt_name(String pt_name) {
        this.pt_name = pt_name;
    }

    public String getPt_id() {
        return pt_id;
    }

    public void setPt_id(String pt_id) {
        this.pt_id = pt_id;
    }
}
