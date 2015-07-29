package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class TextAlert extends BasicAlert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;
    private String phoneNumber;

    public TextAlert() {
        alertType = AlertType.TEXT;
        enabled = false;
        threshold = 1;
        phoneNumber = "";
    }

    public TextAlert(Alert alert) {
        alertType = AlertType.TEXT;
        enabled = alert.getEnabled();
        threshold = alert.getThreshold();
        phoneNumber = "";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
