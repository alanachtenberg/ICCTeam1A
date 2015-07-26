package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.CheckInPreferences;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class MissedEvent extends AbstractCheckInEvent {
    private CheckInPreferences preferences;
    private int gracePeriodAfter = 0;
    private int gracePeriodBefore = 0;

    protected MissedEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter,
                          CheckInPreferences preferences) throws IllegalArgumentException {

        super(calendar);
        if (gracePeriodAfter < 0)
            throw new IllegalArgumentException("gracePeriodAfter must be a positive number");
        this.gracePeriodAfter = gracePeriodAfter;
        this.preferences = preferences;
    }


    @Override
    public Calendar createAdjustedCalendar() {
        Calendar calendar = (Calendar) getBaseCalendar().clone();
        calendar.add(Calendar.MINUTE, gracePeriodAfter);
        return calendar;
    }
}
