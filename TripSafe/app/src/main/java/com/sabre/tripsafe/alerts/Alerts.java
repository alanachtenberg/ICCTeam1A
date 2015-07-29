package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-29.
 */
public class Alerts {

    public static Alert castAlert(Alert alert, AlertType newAlertType) {
        if (newAlertType == AlertType.EMAIL)
            return new EmailAlert(alert);
        else if (newAlertType == AlertType.TEXT)
            return new TextAlert(alert);
        else
            return new NullAlert(alert);
    }

}
