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
import android.util.Log;

import com.sabre.tripsafe.checkin.receivers.MissedCheckinReciever;
import com.sabre.tripsafe.checkin.receivers.ReminderReciever;
import com.sabre.tripsafe.checkin.time.Time;
import com.sabre.tripsafe.checkin.time.event.EventComparator;
import com.sabre.tripsafe.checkin.time.event.MissedEvent;
import com.sabre.tripsafe.checkin.time.event.ReminderEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;

import static com.sabre.tripsafe.MainActivity.TAG;

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
        graceAfterPref = (int) sharedPreferences.getInt("grace_after_preference", -1);
        graceBeforePref = (int) sharedPreferences.getInt("grace_before_preference", -1);
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
                createMissedCheckInEvent(calendar, checkInPreferences);
            }
            return calendars;
        } finally {
            unLoadContext();
        }
    }

    public static void updateEventsOnCheckIn(Activity activity, Calendar calendar) {
        try {
            LoadContext(activity);
            //TODO
        } finally {
            unLoadContext();
        }
    }

    private static void createReminderEvent(Calendar calendar) {
        ReminderEvent event = new ReminderEvent(calendar,graceBeforePref,graceAfterPref);
        Intent intent = new Intent(ReminderReciever.INTENT_STRING);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, event.getAdjustedCalendar().getTimeInMillis(), pendingIntent);
        reminderEvents.add(event);
    }

    private static void createMissedCheckInEvent(Calendar calendar, CheckInPreferences preferences) {
        MissedEvent event = new MissedEvent(calendar, graceBeforePref, graceAfterPref, preferences);
        Intent intent = new Intent(MissedCheckinReciever.INTENT_STRING);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, event.getAdjustedCalendar().getTimeInMillis(), pendingIntent);
        logCheckInInfo(event);
        missedEvents.add(event);
    }

    private static void cancelReminderEvent() {
        //TODO
    }

    private static void cancelMissedCheckInEvent() {
        //TODO
    }

    private static void logCheckInInfo(MissedEvent event) {
        Calendar now = Calendar.getInstance();
        Time baseDiff = Time.getDifference(now, event.getBaseCalendar());
        Time startDiff = Time.getDifference(now, event.getStartOfCheckInPeriod());
        Time endDiff = Time.getDifference(now, event.getEndOfCheckInPeriod());
        Log.i(TAG, String.format("Scheduled Checkin (%dm:%ds) from now.", baseDiff.getMinute(), baseDiff.getSecond()));
        Log.i(TAG, String.format("Allowed Checkin Period is (%dm:%ds) - (%dm:%ds) from now.",
                startDiff.getMinute(), startDiff.getSecond(), endDiff.getMinute(), endDiff.getSecond()));
    }

}
