package com.sabre.tripsafe.checkin.time.event;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.sabre.tripsafe.checkin.CheckInPreferences;
import com.sabre.tripsafe.checkin.receivers.MissedCheckinReciever;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class MissedEvent extends AbstractCheckInEvent {
    private CheckInPreferences preferences;
    public static String EMAIL_KEY="CHECK_IN_PREFERENCES_EMAIL";
    public static String PHONE_KEY="CHECK_IN_PREFERENCES_PHONE";
    public static String TIME_KEY="CHECK_IN_TIME_PREFERENCE";
    public MissedEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter,
                       CheckInPreferences preferences) throws IllegalArgumentException {
        super(calendar, gracePeriodBefore, gracePeriodAfter);

        this.preferences = preferences;
    }

    public boolean isCheckInAllowed(Calendar calendar) {
        return checkInPeriod.contains(calendar);
    }

    public Calendar getStartOfCheckInPeriod() {
        return checkInPeriod.start;
    }

    public Calendar getEndOfCheckInPeriod() {
        return checkInPeriod.end;
    }

    @Override
    public Calendar getAdjustedCalendar() {
        return checkInPeriod.end;
    }

    /*
    *Creates a pending intent to start MissedCheckInReciever, Only creates once
    */
    @Override
    public PendingIntent createPendingIntent(Context context) {
        if (pendingIntent == null) {
            Intent intent = new Intent(MissedCheckinReciever.INTENT_STRING);
            intent.putExtra(EMAIL_KEY,preferences.getEmailAddresses());
            intent.putExtra(PHONE_KEY,preferences.getPhoneNumbers());
            intent.putExtra(TIME_KEY,getBaseCalendar());
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        } else {
            throw new IllegalStateException("Error, Attempt to create multiple Intents, " +
                    "MissedEvent should only have a single Intent associated with it");
        }
        return pendingIntent;
    }
}
