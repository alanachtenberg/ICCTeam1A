package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
interface Alert {

    enum AlertType {
        NULL, EMAIL, TEXT
    }

    int threshold = -1;
    AlertType alertType = AlertType.NULL;

    int getThreshold();
    AlertType getAlertType();

    void setThreshold(int threshold);
    void setAlertType(AlertType alertType);

}
