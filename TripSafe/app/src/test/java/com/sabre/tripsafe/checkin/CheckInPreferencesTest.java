package com.sabre.tripsafe.checkin;

import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.Time;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertSame;

/**
 * Created by Alan on 7/25/2015.
 */
public class CheckInPreferencesTest {
    private static final Time time = new Time(0, 30);// 30 seoncds
    private static CheckInPreferences checkInPreferences;

    private static final Calendar now = Calendar.getInstance();
    private static Calendar fiveMinutesLater;

    private static final String[] VALID_EMAILS = {"alanachtenberg@yahoo.com", "alan_acHtenberG1234@as.sabre.yahoo.edu", "1234__Alan@apache.org"};
    private static final String[] INVALID_EMAILS = {"alanachtenberg@yahoo.", "alan_acHtenberG1234@as.sabre.yahoo.mmm", "1234_@_Alan@apache.org"};
    private static final String[] VALID_PHONES = {"1-800-444-1204", "255-011-9999", "122-233-4555"};
    private static final String[] INVALID_PHONES = {"3-800-444-1204", "1255-011-9999", "122-2334-4555"};

    @BeforeClass
    public static void setUp() {
        initCheckin(time, true);
    }

    private static void initCheckin(Time time, boolean remind) {
        final Calendar today = Calendar.getInstance();
        today.set(Calendar.SECOND, time.getSecond());
        fiveMinutesLater = (Calendar) today.clone();
        fiveMinutesLater.add(Calendar.MINUTE, 5);
        Period period = new Period(today, fiveMinutesLater);
        checkInPreferences = new CheckInPreferences(time, period, remind);
    }

    @Test
    public void createCheckInEvents() {
        ArrayList<Calendar> calendars = (ArrayList<Calendar>) checkInPreferences.generateCalendars();
        assertEquals(6, calendars.size());
        validateTime(now, calendars.get(0));
        validateTime(fiveMinutesLater, calendars.get(5));
    }

    private static void validateTime(Calendar expected, Calendar actual) {
        assertEquals(expected.get(Calendar.YEAR), actual.get(Calendar.YEAR));
        assertEquals(expected.get(Calendar.MONTH), actual.get(Calendar.MONTH));
        assertEquals(expected.get(Calendar.DAY_OF_MONTH), actual.get(Calendar.DAY_OF_MONTH));
        assertEquals(expected.get(Calendar.HOUR_OF_DAY), actual.get(Calendar.HOUR_OF_DAY));
        assertEquals(expected.get(Calendar.MINUTE), actual.get(Calendar.MINUTE));

        assertEquals(time.getSecond(), actual.get(Calendar.SECOND));
    }

    @Test
    public void setValidCheckInPreferences() {
        for (int i = 0; i < VALID_EMAILS.length; ++i) {
            String email = VALID_EMAILS[i];
            String phone = VALID_PHONES[i];
            assertEquals(true, checkInPreferences.setEmailAddress(email));
            assertEquals(true, checkInPreferences.setPhoneNumber(phone));
            assertEquals(email, checkInPreferences.getEmailAddress());
            assertEquals(email, checkInPreferences.getPhoneNumber());
        }
    }

    @Test
    public void setInvalidCheckInPreferences() {
        for (int i = 0; i < INVALID_EMAILS.length; ++i) {
            String email = INVALID_EMAILS[i];
            String phone = INVALID_PHONES[i];
            assertEquals(false, checkInPreferences.setEmailAddress(email));
            assertEquals(false, checkInPreferences.setPhoneNumber(phone));
            assertNull(checkInPreferences.getEmailAddress());
            assertNull(checkInPreferences.getPhoneNumber());
        }
    }


}
