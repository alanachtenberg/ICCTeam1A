package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public interface Option {

    OptionType OPTION_TYPE = OptionType.BASIC;
    boolean enabled = false;
    int threshold = 1;

    OptionType getOptionType();
    boolean isEnabled();
    int getThreshold();

    void setEnabled(boolean enabled);
    void setThreshold(int threshold);

    void doAlert();

}