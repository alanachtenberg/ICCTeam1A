package com.sabre.tripsafe;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.sabre.tripsafe.alerts.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rsitisr on 2015-07-27.
 */
public class SchedulingFragment extends ListFragment {

    private View view;

    private int selectedOptionIdx;
    private ListView optionsListView;

    public static SchedulingFragment newInstance() {
        SchedulingFragment fragment = new SchedulingFragment();
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
        view = inflater.inflate(R.layout.fragment_schedule, container, false);

        optionsListView = (ListView) view.findViewById(android.R.id.list);
        optionsListView.setOnItemClickListener(null);
        optionsListView.setAdapter(new OptionsAdapter(
                optionsListView.getContext(),
                R.layout.fragment_schedule,
                new LinkedList<Option>(Arrays.asList(
                        new TextOption(true, 3, "3432436745"),
                        new EmailOption(false, 1, "someone@sabre.com"),
                        new TextOption(true, 1, "3076532810"),
                        new EmailOption(true, 2, "needhelp@sabre.com"),
                        new TextOption(true, 2, "9755434557")
                )
        )));
        optionsListView.setItemChecked(selectedOptionIdx, true);

        return view;
    }

    class OptionsAdapter extends ArrayAdapter<Option> {
        Context mCtx;
        List<Option> options;

        OptionsAdapter(Context context, int resourceId, List<Option> options) {
            super(context, resourceId, options);
            mCtx = context;
            this.options = options;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return options.get(position).getView(position, convertView, parent, mCtx);
        }
    }

    private void selectItem(int position) {
        selectedOptionIdx = position;
        if (optionsListView != null) {
            optionsListView.setItemChecked(position, true);
        }
    }

}
