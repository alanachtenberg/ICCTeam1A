package com.sabre.tripsafe.checkin.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static com.sabre.tripsafe.MainActivity.TAG;

/**
 * Created by Alan on 7/25/2015.
 */
public class MissedCheckinReciever extends BroadcastReceiver {

    public static String INTENT_STRING="com.sabre.tripsafe.missed";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Missed CheckIn Alarm Broadcast recieved");
        //TODO send notification via sms or mms
    }
}
