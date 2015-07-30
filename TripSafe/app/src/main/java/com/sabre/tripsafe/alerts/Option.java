package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
interface Option {

    OptionType OPTION_TYPE = OptionType.BASIC;
    boolean enabled = false;
    int threshold = 1;

    OptionType getOptionType();
    boolean getEnabled();
    int getThreshold();

    void setEnabled(boolean enabled);
    void setThreshold(int threshold);

}