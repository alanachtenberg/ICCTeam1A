package com.sabre.tripsafe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sabre.tripsafe.alerts.*;

/**
 * Created by rsitisr on 2015-07-27.
 */
public class AlertsFragment extends Fragment {

    private View view;

    private int selectedAlertIdx;
    private ListView alertsListView;

    public static AlertsFragment newInstance() {
        AlertsFragment fragment = new AlertsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_alerts, container, false);

        alertsListView = (ListView) view.findViewById(R.id.alertsListView);
        alertsListView.setOnItemClickListener(null);
        alertsListView.setAdapter(new ArrayAdapter<BasicAlert>(
                alertsListView.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new BasicAlert[] {
                        new BasicAlert(true, 4),
                        new BasicAlert(false, 3),
                        new BasicAlert(true, 5),
                        new BasicAlert(true, 3),
                        new BasicAlert(true, 23),
                        new BasicAlert(true, 13),
                        new BasicAlert(true, 5)
                }
        ));
        alertsListView.setItemChecked(selectedAlertIdx, true);

        return view;
    }

    private void selectItem(int position) {
        selectedAlertIdx = position;
        if (alertsListView != null) {
            alertsListView.setItemChecked(position, true);
        }
    }

}
