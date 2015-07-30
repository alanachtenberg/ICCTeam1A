package com.sabre.tripsafe.checkin;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.sabre.tripsafe.checkin.time.Time;
import com.sabre.tripsafe.checkin.time.event.AbstractCheckInEvent;
import com.sabre.tripsafe.checkin.time.event.EventComparator;
import com.sabre.tripsafe.checkin.time.event.MissedEvent;
import com.sabre.tripsafe.checkin.time.event.ReminderEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

    private static HashMap<String,ReminderEvent> reminderEvents = new HashMap<String,ReminderEvent>();//reminder events and missed events share an ID, no need to use two priority queues
    private static PriorityQueue<MissedEvent> missedEvents = new PriorityQueue<MissedEvent>(10, new EventComparator());


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

    /*
    * Schedules Checkin events as alarms on alarm manager.
     */
    public static void createCheckInEvents(Activity activity, CheckInPreferences checkInPreferences) {
        try {
            LoadContext(activity);
            ArrayList<Calendar> calendars = checkInPreferences.generateCalendars();
            for (Calendar calendar : calendars) {
                if (checkInPreferences.isReminderEnabled()) {
                    createReminderEvent(calendar);
                }
                createMissedCheckInEvent(calendar, checkInPreferences);
            }
        } finally {
            unLoadContext();
        }
    }

    public static void updateEventsOnCheckIn(Activity activity, Calendar checkInTime) {
        try {
            LoadContext(activity);
            for (MissedEvent missedEvent : missedEvents) {
                Time diff = Time.getDifference(checkInTime, missedEvent.getBaseCalendar());
                if ((diff.getMinute() * 60 + diff.getSecond()) > graceBeforePref) {//since priority queue sorts earliest events first, we stop looking when the checkIn time is more than the maximum allowed seconds before
                    Log.w(TAG,String.format("Attempted check in more than maximum seconds(%d) before next scheduled Checkin, checkin ignored",graceBeforePref));
                    break;
                }
                if (missedEvent.isCheckInAllowed(checkInTime)) {
                    logCheckInSuccessful(missedEvent);
                    ReminderEvent reminderEvent = reminderEvents.get(missedEvent.getIdString());
                    cancelEvent(reminderEvent);//notify alarm manager event is no longer valid
                    cancelEvent(missedEvent);
                    missedEvents.remove(missedEvent);//remove from queue
                    reminderEvents.remove(reminderEvent);//clean up mem
                }
            }
        } finally {
            unLoadContext();
        }
    }

    private static void createReminderEvent(Calendar calendar) {
        ReminderEvent event = new ReminderEvent(calendar, graceBeforePref, graceAfterPref);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, event.getAdjustedCalendar().getTimeInMillis(), event.createPendingIntent(context));
        reminderEvents.put(event.getIdString(),event);
    }

    private static void createMissedCheckInEvent(Calendar calendar, CheckInPreferences preferences) {
        MissedEvent event = new MissedEvent(calendar, graceBeforePref, graceAfterPref, preferences);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, event.getAdjustedCalendar().getTimeInMillis(), event.createPendingIntent(context));
        logCheckInInfo(event);
        missedEvents.add(event);
    }

    /*
    *Removes pending event from alarm manager, usage ex. on successful checkin remove reminder and missed events
     */
    private static void cancelEvent(AbstractCheckInEvent event) {
        PendingIntent intent = event.getPendingIntent();
        if (intent == null) {
            throw new IllegalStateException("Attempt to cancel alarm/event that has null Intent");
        }
        alarmManager.cancel(intent);
    }

    private static void logCheckInInfo(MissedEvent event) {
        Calendar now = Calendar.getInstance();
        Time baseDiff = Time.getDifference(now, event.getBaseCalendar());
        Time startDiff = Time.getDifference(now, event.getStartOfCheckInPeriod());
        Time endDiff = Time.getDifference(now, event.getEndOfCheckInPeriod());
        Log.i(TAG, String.format("Scheduled Checkin (%dm:%ds) from now. ID=%d", baseDiff.getMinute(), baseDiff.getSecond(),event.getId()));
        Log.i(TAG, String.format("Allowed Checkin Period is (%dm:%ds) - (%dm:%ds) from now.",
                startDiff.getMinute(), startDiff.getSecond(), endDiff.getMinute(), endDiff.getSecond()));
    }

    private static void logCheckInSuccessful(MissedEvent event) {
        Log.i(TAG, String.format("Checkin successful for Checkin ID=%d",event.getId()));
    }

}
