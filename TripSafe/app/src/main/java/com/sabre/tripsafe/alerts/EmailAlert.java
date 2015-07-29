package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class EmailAlert implements Alert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;
    private String emailAddress;

    @Override
    public AlertType getAlertType() {
        return alertType;
    }

    public String getEmailAddress() {
        return emailAddress;
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
    public void setAlertType(AlertType newAlertType) {
        if (alertType != newAlertType)
            this = (EmailAlert) Alerts.castAlert(this, newAlertType);
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
