package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class EmailAlert extends BasicAlert {

    private AlertType alertType;
    private boolean enabled;
    private int threshold;
    private String emailAddress;

    public EmailAlert() {
        alertType = AlertType.EMAIL;
        enabled = false;
        threshold = 1;
        emailAddress = "";
    }

    public EmailAlert(Alert alert) {
        alertType = AlertType.EMAIL;
        enabled = alert.getEnabled();
        threshold = alert.getThreshold();
        emailAddress = "";
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
