package com.sabre.tripsafe.checkin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sabre.tripsafe.R;
import com.sabre.tripsafe.checkin.receivers.MissedCheckinReciever;
import com.sabre.tripsafe.checkin.receivers.ReminderReciever;
import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.event.EventComparator;
import com.sabre.tripsafe.checkin.time.event.MissedEvent;
import com.sabre.tripsafe.checkin.time.event.ReminderEvent;
import com.sabre.tripsafe.checkin.time.Time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Alan on 7/23/2015.
 */
public class CheckInManager {

    private static Context context = null;
    private static AlarmManager alarmManager = null;
    private static SharedPreferences sharedPreferences = null;
    private static int graceAfterPref = 0;
    private static int graceBeforePref = 0;

    private static PriorityQueue<ReminderEvent> reminderEvents = new PriorityQueue<ReminderEvent>(100, new EventComparator());
    private static PriorityQueue<MissedEvent> missedEvents = new PriorityQueue<MissedEvent>(100, new EventComparator());


    private static void LoadContext(Activity activity) {
        context = activity.getApplicationContext();
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        graceAfterPref = sharedPreferences.getInt("grace_after_preference",0);
        graceBeforePref = sharedPreferences.getInt("grace_before_preference",0);
    }

    private static void unLoadContext() {
        context = null;
        alarmManager = null;
        sharedPreferences = null;
        graceBeforePref = 0;
        graceAfterPref = 0;
    }

    public static void showReminder(Activity activity) {
        DialogFragment fragment = new CheckInDialogFragment();
        FragmentManager manager = activity.getFragmentManager();
        fragment.show(manager, "fragment_check_in");
    }

    public static List createCheckInEvents(Activity activity, CheckInPreferences checkInPreferences) {
        try {
            LoadContext(activity);
            ArrayList<Calendar> calendars = checkInPreferences.generateCalendars();
            for (Calendar calendar : calendars) {
                if (checkInPreferences.isReminderEnabled()) {
                    createReminderEvent(calendar);
                }
                createMissedCheckInEvent(calendar);
            }
            return calendars;
        } finally {
            unLoadContext();
        }
    }

    public static void updateEventsOnCheckIn(Activity activity, Calendar calendar) {
        try{
            LoadContext(activity);
        }
        finally {
            unLoadContext();
        }
        //TODO
    }

    private static void createReminderEvent(Calendar calendar) {
        ReminderEvent event = new ReminderEvent(calendar);
        Intent intent = new Intent(context, ReminderReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, event.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC, event.getAdjustedCalendar().getTimeInMillis(), pendingIntent);
    }

    private static void createMissedCheckInEvent(Calendar calendar) {
        //TODO
    }

    private static  void cancelReminderEvent(){
        //TODO
    }
    private static void cancelMissedCheckInEvent(){
        //TODO
    }

}
