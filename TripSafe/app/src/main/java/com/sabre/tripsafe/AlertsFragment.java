package com.sabre.tripsafe;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by rsitisr on 2015-07-27.
 */
public class AlertsFragment extends Fragment {

    private View view;

    private int selectedAlertIdx;
    private ListView alerts;

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

        alerts = (ListView) view.findViewById(R.id.alertsListView);
        alerts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        alerts.setAdapter(new ArrayAdapter<String>(
                alerts.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new String[]{
                        "item 1",
                        "item 2",
                        "item 3",
                        "item 4"
                }
        ));
        alerts.setItemChecked(selectedAlertIdx, true);

        return view;
    }

    private void selectItem(int position) {
        selectedAlertIdx = position;
        if (alerts != null) {
            alerts.setItemChecked(position, true);
        }
    }

}