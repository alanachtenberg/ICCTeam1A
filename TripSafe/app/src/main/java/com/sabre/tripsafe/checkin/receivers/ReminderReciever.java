package com.sabre.tripsafe.checkin.receivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sabre.tripsafe.MainActivity;
import com.sabre.tripsafe.checkin.CheckInManager;

import java.util.InputMismatchException;

import static com.sabre.tripsafe.MainActivity.TAG;

/**
 * Created by Alan on 7/25/2015.
 */
public class ReminderReciever  extends BroadcastReceiver{
    public static String INTENT_STRING="com.sabre.tripsafe.reminder";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Reminder Alarm Broadcast recieved");
        //CheckInManager.showReminder((Activity)context);//TODO fix displaying content, throws exception
    }
}
