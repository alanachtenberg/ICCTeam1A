package com.sabre.tripsafe.checkin.time;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alan on 7/25/2015.
 */
public class Period {
    public Calendar start;
    public Calendar end;

    public Period(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(Calendar date) {
        return ((date.before(end) || date.equals(end)) && (date.after(start) || date.equals(start)));
    }
}
