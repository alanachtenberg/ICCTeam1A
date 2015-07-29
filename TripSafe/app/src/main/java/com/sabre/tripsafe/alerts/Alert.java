package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
interface Alert {

    AlertType alertType = AlertType.NULL;
    boolean enabled = false;
    int threshold = -1;

    AlertType getAlertType();
    boolean getEnabled();
    int getThreshold();

    void setAlertType(AlertType alertType);
    void setEnabled(boolean enabled);
    void setThreshold(int threshold);

}