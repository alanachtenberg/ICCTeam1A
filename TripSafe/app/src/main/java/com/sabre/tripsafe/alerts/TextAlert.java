package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class TextAlert implements Alert {

    int threshold = -1;
    AlertType alertType = AlertType.TEXT;

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public AlertType getAlertType() {
        return alertType;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }

}
