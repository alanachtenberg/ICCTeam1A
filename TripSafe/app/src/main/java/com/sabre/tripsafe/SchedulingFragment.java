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
                new LinkedList<Option>(Arrays.asList( new BasicOption(true, 4),
                                                           new BasicOption(false, 3),
                                                           new BasicOption(true, 5),
                                                           new BasicOption(true, 3),
                                                           new BasicOption(false, 1),
                                                           new BasicOption(true, 2),
                                                           new BasicOption(true, 5))

                )
        ));
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
            final int p = position;
            LayoutInflater inflater = (LayoutInflater) mCtx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.view_basic_option, parent, false);
            final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
            final TextView textView = (TextView) rowView.findViewById(R.id.textView);
            final Button button = (Button) rowView.findViewById(R.id.button);
            button.setText(Integer.toString(options.get(p).getThreshold()));
            checkBox.setChecked(options.get(p).isEnabled());
            textView.setText("Enabled: " + options.get(p).isEnabled() + "; Threshold: " + options.get(p).getThreshold());
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                    if (checkBox.isChecked())
                        options.get(p).setEnabled(true);
                    else
                        options.get(p).setEnabled(false);
                    textView.setText("Enabled: " + options.get(p).isEnabled() + "; Threshold: " + options.get(p).getThreshold());
                }
            });

            return rowView;
        }
    }

    private void selectItem(int position) {
        selectedOptionIdx = position;
        if (optionsListView != null) {
            optionsListView.setItemChecked(position, true);
        }
    }

}
