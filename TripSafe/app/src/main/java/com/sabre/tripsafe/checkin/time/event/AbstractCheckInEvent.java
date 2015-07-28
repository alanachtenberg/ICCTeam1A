package com.sabre.tripsafe.checkin.time.event;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public abstract class AbstractCheckInEvent {

    private long id = 0;
    private String idString = "";

    private Calendar calendar;
    private Calendar adjustedCalendar;

    protected AbstractCheckInEvent(Calendar calendar) {
        this.calendar = calendar;
        this.adjustedCalendar = createAdjustedCalendar();
        id = calendar.getTimeInMillis();
    }

    public long getId() {
        return id;
    }

    public String getIdString() {
        return idString;
    }

    public Calendar getBaseCalendar() {
        return calendar;
    }

    public Calendar getAdjustedCalendar() {
        return adjustedCalendar;
    }

    protected abstract Calendar createAdjustedCalendar();

}
