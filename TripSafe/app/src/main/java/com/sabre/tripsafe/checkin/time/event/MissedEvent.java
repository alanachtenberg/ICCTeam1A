package com.sabre.tripsafe.checkin.time.event;

import com.sabre.tripsafe.checkin.CheckInPreferences;
import com.sabre.tripsafe.checkin.time.Period;

import java.util.Calendar;

/**
 * Created by Alan on 7/26/2015.
 */
public class MissedEvent extends AbstractCheckInEvent {
    private CheckInPreferences preferences;

    public MissedEvent(Calendar calendar, int gracePeriodBefore, int gracePeriodAfter,
                       CheckInPreferences preferences) throws IllegalArgumentException {
        super(calendar, gracePeriodBefore, gracePeriodAfter);

        this.preferences = preferences;
    }

    public boolean isCheckInAllowed(Calendar calendar) {
        return checkInPeriod.contains(calendar);
    }

    public Calendar getStartOfCheckInPeriod() {
        return checkInPeriod.start;
    }

    public Calendar getEndOfCheckInPeriod() {
        return checkInPeriod.end;
    }

    @Override
    public Calendar getAdjustedCalendar() {
        return checkInPeriod.end;
    }
}
