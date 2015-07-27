package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.time.event.AbstractCheckInEvent;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class ReminderEvent extends AbstractCheckInEvent {

    public ReminderEvent(Calendar calendar) {
        super(calendar);
    }

    @Override
    protected Calendar createAdjustedCalendar() {
        Calendar calendar = (Calendar) getBaseCalendar().clone();
        calendar.add(Calendar.SECOND, -3);
        return calendar;
    }


}
