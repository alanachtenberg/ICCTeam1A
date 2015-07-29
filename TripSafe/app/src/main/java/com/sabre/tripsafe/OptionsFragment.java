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
public class OptionsFragment extends Fragment {

    private View view;

    private int selectedOptionIdx;
    private ListView optionsListView;

    public static OptionsFragment newInstance() {
        OptionsFragment fragment = new OptionsFragment();
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

        optionsListView = (ListView) view.findViewById(R.id.alertsListView);
        optionsListView.setOnItemClickListener(null);
        optionsListView.setAdapter(new ArrayAdapter<BasicOption>(
                optionsListView.getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new BasicOption[] {
                        new BasicOption(optionsListView.getContext(), true, 4),
                        new BasicOption(optionsListView.getContext(), false, 3),
                        new BasicOption(optionsListView.getContext(), true, 5),
                        new BasicOption(optionsListView.getContext(), true, 3),
                        new BasicOption(optionsListView.getContext(), true, 23),
                        new BasicOption(optionsListView.getContext(), true, 13),
                        new BasicOption(optionsListView.getContext(), true, 5)
                }
        ));
        optionsListView.setItemChecked(selectedOptionIdx, true);

        return view;
    }

    private void selectItem(int position) {
        selectedOptionIdx = position;
        if (optionsListView != null) {
            optionsListView.setItemChecked(position, true);
        }
    }

}
