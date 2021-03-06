package com.sabre.tripsafe;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;

import com.sabre.tripsafe.checkin.CheckInManager;
import com.sabre.tripsafe.preferences.SettingsFragment;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String TAG = "TRIP_SAFE";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        //registerReceiver(new ReminderReciever(),null); now in manifest
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment= null;
        switch (position){
            case 0:
                fragment=HomeFragment.newInstance();
                break;
            case 1:
                fragment= SchedulingFragment.newInstance();
                break;
            case 2:
                fragment=SettingsFragment.newInstance();
                break;
            case 3:
                fragment=DeveloperFragment.newInstance();
                break;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.app_name);
                break;
            case 2:
                mTitle = getString(R.string.title_schedule);
                break;
            case 3:
                mTitle = getString(R.string.title_settings);
            case 4:
                mTitle = "Developer Tools";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent().getAction().endsWith("showReminder")) {
            CheckInManager.showReminder(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

}
