package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.CheckInPreferences;
import com.sabre.tripsafe.checkin.time.Period;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class MissedEvent extends AbstractCheckInEvent {
    private CheckInPreferences preferences;
    private Period checkInPeriod;
    private int gracePeriodAfter = 0;
    private int gracePeriodBefore = 0;

    protected MissedEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter,
                          CheckInPreferences preferences) throws IllegalArgumentException {

        super(calendar);
        checkInPeriod = createCheckInPeriod();
        if (gracePeriodBefore < 0)
            throw new IllegalArgumentException("gracePeriodBefore must be a positive number");
        if (gracePeriodAfter < 0)
            throw new IllegalArgumentException("gracePeriodAfter must be a positive number");
        this.gracePeriodAfter = gracePeriodAfter;
        this.preferences = preferences;
    }

    private Period createCheckInPeriod(){
        Calendar start = (Calendar)getBaseCalendar().clone();
        Calendar end = (Calendar)getBaseCalendar().clone();
        start.add(Calendar.SECOND,-gracePeriodBefore);//negative to change time to before the base
        end.add(Calendar.SECOND,gracePeriodAfter);
        return  new Period(start,end);
    }


    @Override
    protected Calendar createAdjustedCalendar() {
        Calendar calendar = (Calendar) getBaseCalendar().clone();
        calendar.add(Calendar.SECOND, gracePeriodAfter);
        return calendar;
    }

    public boolean isCheckInAllowed(Calendar calendar){
        return checkInPeriod.contains(calendar);
    }
}
