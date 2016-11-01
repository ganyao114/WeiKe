package com.gy.just.VoltageMonitor.EventFlags;

/**
 * Created by gy on 2016/4/28.
 */
public class LoginErrorEvent {
    private String errorMsg;

    public LoginErrorEvent(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
