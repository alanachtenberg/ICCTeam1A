package com.sabre.tripsafe.alerts;

import android.content.Context;
import android.view.View;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class BasicAlert extends View implements Alert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;

    public BasicAlert(Context context) {
        super(context);
    }

    @Override
    public AlertType getAlertType() {
        return alertType;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
