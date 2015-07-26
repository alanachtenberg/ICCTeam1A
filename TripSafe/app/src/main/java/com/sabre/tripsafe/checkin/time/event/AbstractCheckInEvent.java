package com.sabre.tripsafe.checkin.time.event;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public abstract class AbstractCheckInEvent {

    private int id = 0;
    private String idString = "";

    private Calendar calendar;
    private Calendar adjustedCalendar;

    protected AbstractCheckInEvent(Calendar calendar) {
        this.calendar = calendar;
        this.adjustedCalendar = createAdjustedCalendar();
        createID();
    }

    private void createID() {
        idString = Integer.toString(calendar.get(Calendar.YEAR))
                + Integer.toString(calendar.get(Calendar.DAY_OF_YEAR))
                + Integer.toString(calendar.get(Calendar.HOUR_OF_DAY))
                + Integer.toString(calendar.get(Calendar.MINUTE));
        id = Integer.parseInt(idString);// unique... enough, for our purposes at least
    }

    public int getId() {
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

    public abstract Calendar createAdjustedCalendar();

}
