package com.sabre.tripsafe;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.test.ActivityInstrumentationTestCase2;

import com.sabre.tripsafe.checkin.CheckInManager;
import com.sabre.tripsafe.checkin.CheckInPreferences;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by SG0222314 on 7/27/2015.
 */
public class CheckInManagerTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static MainActivity mainActivity;
    private static CheckInPreferences checkInPreferences;
    private static SharedPreferences sharedPreferences;

    private static Map<String, ?> originalPreferences;

    public CheckInManagerTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mainActivity = getActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity.getApplicationContext());
        originalPreferences = sharedPreferences.getAll();
        setSharedPreferences();
    }

    private void setSharedPreferences() {
        sharedPreferences.edit().putInt("grace_before_preference", 10).commit();
        sharedPreferences.edit().putInt("grace_after_preference", 10).commit();
        sharedPreferences.edit().putBoolean("location_required", false).commit();
        sharedPreferences.edit().putInt("location_distance", 10).commit();
    }

    private void restoreSharedPreferences() {
        sharedPreferences.edit().putInt("grace_before_preference", (int) originalPreferences.get("grace_before_preference")).commit();
        sharedPreferences.edit().putInt("grace_after_preference", (int) originalPreferences.get("grace_after_preference")).commit();
        sharedPreferences.edit().putBoolean("location_required", (boolean)originalPreferences.get("location_required")).commit();
        sharedPreferences.edit().putInt("location_distance", (int) originalPreferences.get("location_distance")).commit();
    }
}
