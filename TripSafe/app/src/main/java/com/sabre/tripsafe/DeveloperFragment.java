package com.sabre.tripsafe;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sabre.tripsafe.checkin.CheckInManager;

/**
 * Created by Alan on 7/24/2015.
 */

/*
 A fragment to hookup code directly to UI for quick and dirty testing/debugging
 */
public class DeveloperFragment extends Fragment {

    private View view;
    private Button showReminderButton;
    private Button dummy;

    public static DeveloperFragment newInstance() {
        DeveloperFragment fragment = new DeveloperFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public DeveloperFragment(){
        //empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_developer, container, false);

        showReminderButton = (Button) view.findViewById(R.id.developer_show_reminder);
        showReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckInManager.showReminder(getActivity().getFragmentManager());
            }
        });

        dummy = (Button) view.findViewById(R.id.developer_dummy);

        return view;
    }

}
