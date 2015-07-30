package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.time.event.AbstractCheckInEvent;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class ReminderEvent extends AbstractCheckInEvent {


    public ReminderEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter) {
        super(calendar, gracePeriodBefore, gracePeriodAfter);
    }

    @Override
    public Calendar getAdjustedCalendar() {
        return checkInPeriod.start;
    }
}
