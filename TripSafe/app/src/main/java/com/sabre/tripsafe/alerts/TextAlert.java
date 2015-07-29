package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class TextAlert implements Alert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;
    private String phoneNumber;

    @Override
    public AlertType getAlertType() {
        return alertType;
    }

    @Override
    public boolean getEnabled() {
        return enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
