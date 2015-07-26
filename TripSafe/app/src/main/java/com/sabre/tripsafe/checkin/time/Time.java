package com.sabre.tripsafe.checkin.time;

import android.util.Log;

import com.sabre.tripsafe.MainActivity;

/**
 * Created by Alan on 7/25/2015.
 */
public class Time {

    private int hour;
    private int minute;

    /*
    *@param h hours in military time
    * @param m minutes
    */
    public Time(int h, int m) {
        hour = h;
        minute = m;
        if (!validate()){
            hour=-1;
            minute=-1;
        }
    }

    private boolean validate() {
        if (0 > hour || hour > 23) {
            Log.e(MainActivity.TAG, String.format("Invalid time set, hour is out of range. value =%d", hour));
            return false;
        }
        if (0 > minute || minute > 59) {
            Log.e(MainActivity.TAG, String.format("Invalid time set, minute is out of range. value=%d", minute));
            return false;
        }
        return true;
    }

    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return minute;
    }
}
