package com.sabre.tripsafe.checkin;

import android.util.Log;

import com.sabre.tripsafe.MainActivity;
import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.Time;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * Created by Alan on 7/23/2015.
 */
/*
* Multiple CheckInPrefernces can exist and each can have multiple Checkins associated with them.
 */
public class CheckInPreferences {

    private String emailAddress;
    private String phoneNumber;
    private boolean reminderEnabled;
    private Period period;
    private Time time;


    public CheckInPreferences(Time time, Period period, boolean reminderEnabled) {
        this.time = time;
        this.period = period;
        this.reminderEnabled = reminderEnabled;
    }

    public boolean setEmailAddress(String emailAddress) {
        if (validateEmail(emailAddress)) {
            this.emailAddress = emailAddress;
            return true;
        }
        System.out.println(String.format("Attempt to set invalid email address. value=%s", emailAddress));
        emailAddress = null;
        return false;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (validatePhone(phoneNumber)) {
            this.phoneNumber = emailAddress;
            return true;
        }
        System.out.println( String.format("Attempt to set invalid phone number. value=%s", phoneNumber));
        phoneNumber = null;
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Time getTime() {
        return time;
    }

    public Period getPeriod() {
        return period;
    }

    public boolean isReminderEnabled() {
        return reminderEnabled;
    }

    /*
    *Returns Calendar objects that represent time for a check in once per day during the
    * Check-in period
    */
    public ArrayList<Calendar> generateCalendars() {
        ArrayList<Calendar> calendars = new ArrayList<Calendar>();
        Calendar current = period.start;
        current.set(Calendar.HOUR_OF_DAY, time.getHour());//HOUR_OF_DAY is in military time
        current.set(Calendar.MINUTE, time.getMinute());
        while (period.contains(current)) {
            calendars.add((Calendar) current.clone());
            current.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendars;
    }


    private static boolean validatePhone(String phone) {
        phone.trim();
        return Pattern.matches("(1\\-)?\\d\\d\\d\\-\\d\\d\\d\\-\\d\\d\\d\\d", phone);
    }

    private static boolean validateEmail(String email) {
        email.trim();
        return Pattern.matches("[\\_\\d\\w]+\\@[\\_\\d\\.\\w]+\\.(com|edu|org)", email);
    }

}
