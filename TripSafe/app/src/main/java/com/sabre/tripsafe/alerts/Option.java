package com.sabre.tripsafe.alerts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rsitisr on 2015-07-28.
 */
public interface Option {

    OptionType OPTION_TYPE = OptionType.BASIC;
    boolean enabled = false;
    int threshold = 1;

    View getView(int position, View convertView, ViewGroup parent, Context mCtx);

    OptionType getOptionType();
    boolean isEnabled();
    int getThreshold();

    void setEnabled(boolean enabled);
    void setThreshold(int threshold);

    void doAlert();

}