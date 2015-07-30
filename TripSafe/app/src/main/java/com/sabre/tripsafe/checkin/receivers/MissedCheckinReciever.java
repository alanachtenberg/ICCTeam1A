package com.sabre.tripsafe.checkin.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.sabre.tripsafe.checkin.time.event.MissedEvent;

import static com.sabre.tripsafe.MainActivity.TAG;

/**
 * Created by Alan on 7/25/2015.
 */
public class MissedCheckinReciever extends BroadcastReceiver {

    public static String INTENT_STRING="com.sabre.tripsafe.missed";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Missed CheckIn Alarm Broadcast recieved");
        Bundle extras = intent.getExtras();
        String[] emails = (String[])extras.get(MissedEvent.EMAIL_KEY);
        String[] phones = (String[])extras.get(MissedEvent.PHONE_KEY);
        //TODO send notification via sms or mms
    }
}
