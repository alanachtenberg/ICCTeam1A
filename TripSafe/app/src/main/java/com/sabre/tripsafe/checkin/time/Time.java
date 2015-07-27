package com.sabre.tripsafe.checkin.time;

import android.util.Log;

import com.sabre.tripsafe.MainActivity;

/**
 * Created by Alan on 7/25/2015.
 */
public class Time {

    private int minute;
    private int second;

    /*
    *@param h hours in military time
    * @param m minutes
    */
    public Time(int m, int s) {
        minute = m;
        second = s;
        if (!validate()){
            minute=-1;
            second=-1;
        }
    }

    private boolean validate() {
        if (0 > minute || minute > 59) {
            Log.e(MainActivity.TAG, String.format("Invalid time set, minute is out of range. value =%d", minute));
            return false;
        }
        if (0 > second || second > 59) {
            Log.e(MainActivity.TAG, String.format("Invalid time set, second is out of range. value=%d", second));
            return false;
        }
        return true;
    }

    public int getMinute(){
        return minute;
    }
    public int getSecond(){
        return second;
    }
}
