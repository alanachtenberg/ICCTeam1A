package com.sabre.tripsafe.checkin.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;

import com.sabre.tripsafe.R;
import com.sabre.tripsafe.checkin.time.event.MissedEvent;

import java.util.Calendar;

import static com.sabre.tripsafe.MainActivity.TAG;

/**
 * Created by Alan on 7/25/2015.
 */
public class MissedCheckinReciever extends BroadcastReceiver {
    private static SharedPreferences sp;
    private static SmsManager smsManager = SmsManager.getDefault();
    public static String INTENT_STRING = "com.sabre.tripsafe.missed";

    private Calendar time;
    private String[] emails;
    private String[] phones;
    private String userName;
    private String altContactMethod;
    private String message = "Check-in for %s was missed at %s try contacting %s";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Missed CheckIn Alarm Broadcast recieved");
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        initData(intent.getExtras());
        for (String phone : phones) {
            smsManager.sendTextMessage(phone, null, String.format(message, userName, time.toString(), altContactMethod), null, null);//TODO test on real phone
        }
        for (String email : emails) {
            //TODO
        }
    }

    private void initData(Bundle extras){
        emails = (String[]) extras.get(MissedEvent.EMAIL_KEY);
        phones = (String[]) extras.get(MissedEvent.PHONE_KEY);
        Calendar time = (Calendar) extras.get(MissedEvent.TIME_KEY);
        userName = sp.getString("user_name_preference","NULL");
        altContactMethod = sp.getString("alt_contact_preference","NULL");

    }
}
