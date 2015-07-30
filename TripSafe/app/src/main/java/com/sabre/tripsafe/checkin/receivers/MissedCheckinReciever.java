package com.sabre.tripsafe.checkin.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;

import com.sabre.tripsafe.checkin.time.event.MissedEvent;

import java.util.Calendar;

import static com.sabre.tripsafe.MainActivity.TAG;

/**
 * Created by Alan on 7/25/2015.
 */
public class MissedCheckinReciever extends BroadcastReceiver {

    private static SmsManager smsManager = SmsManager.getDefault();
    public static String INTENT_STRING="com.sabre.tripsafe.missed";

    String message = "Check in event was missed at";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Missed CheckIn Alarm Broadcast recieved");
        Bundle extras = intent.getExtras();
        String[] emails = (String[])extras.get(MissedEvent.EMAIL_KEY);
        String[] phones = (String[])extras.get(MissedEvent.PHONE_KEY);
        Calendar time = (Calendar)extras.get(MissedEvent.TIME_KEY);
        for (String phone : phones) {
            smsManager.sendTextMessage(phone, null, message+ " " +time.toString(), null, null);//TODO test on real phone
        }
        for (String email : emails){
        }
    }
}
