package com.sabre.tripsafe;

import android.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
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
 * Created by Alan on 7/24/2015.
 */

/*
 A fragment to hookup code directly to UI for quick and dirty testing/debugging
 */
public class DeveloperFragment extends Fragment {

    private View view;
    private Button showReminderButton;
    private Button scheduleCheckIn;
    private Button checkIn;
    private Button sendText;

    public static DeveloperFragment newInstance() {
        DeveloperFragment fragment = new DeveloperFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public DeveloperFragment() {
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
        view = inflater.inflate(R.layout.fragment_developer, container, false);

        showReminderButton = (Button) view.findViewById(R.id.developer_show_reminder);
        showReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInManager.showReminder(getActivity());
            }
        });

        scheduleCheckIn = (Button) view.findViewById(R.id.developer_test_schedule_checkin);

        scheduleCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now=Calendar.getInstance();
                Calendar oneMinuteFromNow = (Calendar)now.clone();
                oneMinuteFromNow.add(Calendar.MINUTE,1);
                CheckInPreferences preferences = new CheckInPreferences(new Time(0,59),new Period(now,oneMinuteFromNow),true);
                CheckInManager.createCheckInEvents(getActivity(), preferences);
            }
        });

        checkIn = (Button) view.findViewById(R.id.developer_test_checkin);

        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInManager.updateEventsOnCheckIn(getActivity(), Calendar.getInstance());
            }
        });

        sendText = (Button) view.findViewById(R.id.developer_test_phone);

        sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager.getDefault().sendTextMessage("phone number to test",null,"Hello World, I am a SMS message",null,null);
            }
        });


        return view;
    }


}
