package com.sabre.tripsafe.checkin.time.event;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.sabre.tripsafe.checkin.receivers.ReminderReciever;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class ReminderEvent extends AbstractCheckInEvent {

    public ReminderEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter) {
        super(calendar, gracePeriodBefore, gracePeriodAfter);
    }

    @Override
    public Calendar getAdjustedCalendar() {
        Calendar adjusted = (Calendar)getBaseCalendar().clone();
        adjusted.add(Calendar.SECOND,-3);
        return adjusted;
    }

    /*
    *Creates a pending intent to start ReminderReciever, Only creates once
     */
    @Override
    public PendingIntent createPendingIntent(Context context) {
        if (pendingIntent == null) {
            Intent intent = new Intent(ReminderReciever.INTENT_STRING);
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        } else {
            throw new IllegalStateException("Error, Attempt to create multiple Intents, " +
                    "ReminderEvent should only have a single Intent associated with it");
        }
        return pendingIntent;
    }


}
