package com.sabre.tripsafe.checkin;

import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.Time;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alan on 7/25/2015.
 */
public class CheckInPreferencesTest {
    private static final Time time = new Time(12, 30);//12:30 pm
    private static CheckInPreferences checkInPreferences;

    private static final Calendar today = Calendar.getInstance();
    private static Calendar tomorrow;

    @BeforeClass
    public static void setUp() {
        initCheckin(time, true);
    }

    private static void initCheckin(Time time, boolean remind) {
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY,time.getHour());//have to manually set the time of day, so that # of generated calendars is not ambiguous
        today.set(Calendar.MINUTE,time.getMinute());
        tomorrow = (Calendar) today.clone();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        Period period = new Period(today, tomorrow);
        checkInPreferences = new CheckInPreferences(time, period, remind);
    }

    @Test
    public void createCheckInEvents() {
        ArrayList<Calendar> calendars = (ArrayList<Calendar>)checkInPreferences.generateCalendars();
        assertEquals(2, calendars.size());
        validateTime(today, calendars.get(0));
        validateTime(tomorrow, calendars.get(1));
    }

    private static void validateTime(Calendar expected, Calendar actual) {
        assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));

        assertEquals(time.getHour(), actual.get(Calendar.HOUR_OF_DAY));
        assertEquals(time.getMinute(), actual.get(Calendar.MINUTE));
    }


}
