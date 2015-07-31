package com.sabre.tripsafe.alerts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sabre.tripsafe.R;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class BasicOption implements Option {

    private OptionType optionType;
    private boolean enabled;
    private int threshold;

    public BasicOption(boolean enabled, int threshold) {
        this.enabled = enabled;
        this.threshold = threshold;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent, Context mCtx) {
        final int p = position;
        LayoutInflater inflater = (LayoutInflater) mCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.view_basic_option, parent, false);
        final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        final Button button = (Button) rowView.findViewById(R.id.button);
        button.setText(Integer.toString(getThreshold()));
        checkBox.setChecked(isEnabled());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                if (checkBox.isChecked())
                    setEnabled(true);
                else
                    setEnabled(false);
            }
        });

        return rowView;
    }

    @Override
    public void doAlert() {}

    @Override
    public OptionType getOptionType() {
        return optionType;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
