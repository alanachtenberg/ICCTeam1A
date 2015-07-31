package com.sabre.tripsafe;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sabre.tripsafe.checkin.CheckInManager;
import com.sabre.tripsafe.checkin.CheckInPreferences;
import com.sabre.tripsafe.checkin.time.Period;
import com.sabre.tripsafe.checkin.time.Time;

import java.util.Calendar;

/**
 * Created by LeBat on 7/30/2015.
 */

/*
 The home fragment.
 */
public class HomeFragment extends Fragment {

    private View view;
    private Button scheduleCheckIn;
    private Button CheckIn;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        //empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        scheduleCheckIn = (Button) view.findViewById(R.id.schedule_checkin);
        scheduleCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInManager.showReminder(getActivity());
            }
        });

        CheckIn = (Button) view.findViewById(R.id.check_in);

        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = null;
                frag = OptionsFragment.newInstance();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                            .replace(R.id.container, frag)
                            .commit();

            }
        });

        return view;
    }


}