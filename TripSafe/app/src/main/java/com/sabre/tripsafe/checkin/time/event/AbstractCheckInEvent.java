package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.time.Period;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public abstract class AbstractCheckInEvent {

    private long id = 0;
    private String idString = "";
    private Calendar baseCalendar;

    private int gracePeriodBefore = 0;
    private int gracePeriodAfter = 0;

    protected Period checkInPeriod;

    protected AbstractCheckInEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter) {
        if (gracePeriodBefore < 0)
            throw new IllegalArgumentException("gracePeriodBefore must be a positive number");
        if (gracePeriodAfter < 0)
            throw new IllegalArgumentException("gracePeriodAfter must be a positive number");
        this.baseCalendar = calendar;
        id = calendar.getTimeInMillis();
        checkInPeriod = createCheckInPeriod();
    }

    private Period createCheckInPeriod(){
        Calendar start = (Calendar)getBaseCalendar().clone();
        start.add(Calendar.SECOND,-gracePeriodBefore);//negative to change time to before the base
        Calendar end = (Calendar)getBaseCalendar().clone();
        end.add(Calendar.SECOND, gracePeriodAfter);//negative to change time to before the base
        return  new Period(start,end);
    }

    public long getId() {
        return id;
    }

    public String getIdString() {
        return idString;
    }

    public Calendar getBaseCalendar() {
        return baseCalendar;
    }

    protected abstract Calendar getAdjustedCalendar();

}
