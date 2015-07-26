package com.sabre.tripsafe.checkin;

import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.Time;

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


    public CheckInPreferences(Time time, Period period, boolean reminderEnabled){
        this.time=time; this.period=period;
        this.reminderEnabled=reminderEnabled;
    }

    public boolean setEmailAddress(String emailAddress){
        if(validateEmail(emailAddress)) {
            this.emailAddress = emailAddress;
            return true;
        }
        return false;
    }
    public String getEmailAddress(){
        return emailAddress;
    }

    public boolean setPhoneNumber(String phoneNumber){
        if(validatePhone(phoneNumber)) {
            this.phoneNumber = emailAddress;
            return true;
        }
        return false;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public Time getTime(){
        return time;
    }

    public Period getPeriod(){
        return period;
    }

    public boolean isReminderEnabled(){
        return reminderEnabled;
    }


    private static boolean validatePhone(String phone){
        phone.trim();
        return Pattern.matches("\\d\\d\\d\\-\\d\\d\\d\\-\\d\\d\\d\\d",phone);
    }
    private static boolean validateEmail(String email){
        email.trim();
        return Pattern.matches("[\\_0\\d\\w]+\\@[\\_\\d\\.\\w]+\\.(com|edu|org)",email);
    }

}
