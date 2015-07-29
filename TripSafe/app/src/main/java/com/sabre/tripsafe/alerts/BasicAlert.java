package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class BasicAlert implements Alert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;

    public BasicAlert() {}

    public BasicAlert(Alert alert) {
        alertType = AlertType.BASIC;
        enabled = alert.getEnabled();
        threshold = alert.getThreshold();
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
