package com.sabre.tripsafe.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.sabre.tripsafe.R;

/**
 * Created by Alan on 7/22/2015.
 */
public class SettingsFragment extends PreferenceFragment{


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SettingsFragment.
     */
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preference);
    }


}
