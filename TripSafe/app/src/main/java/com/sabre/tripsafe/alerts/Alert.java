package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
interface Alert {

    AlertType alertType = AlertType.BASIC;
    boolean enabled = false;
    int threshold = 1;

    AlertType getAlertType();
    boolean getEnabled();
    int getThreshold();

    void setEnabled(boolean enabled);
    void setThreshold(int threshold);

}