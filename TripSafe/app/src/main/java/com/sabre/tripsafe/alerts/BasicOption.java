package com.sabre.tripsafe.alerts;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sabre.tripsafe.R;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class BasicOption extends RelativeLayout implements Option {

    private OptionType optionType;
    private boolean enabled;
    private int threshold;

    public static BasicOption inflate(ViewGroup parent) {
        BasicOption option = (BasicOption) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_basic_option, parent, false);
        return option;
    }

    @Override
    public OptionType getOptionType() {
        return optionType;
    }

    @Override
    public boolean getEnabled() {
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
