package com.sabre.tripsafe.alerts;

import android.content.Context;
import android.view.View;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class BasicOption extends View implements Option {

    private OptionType optionType;
    private boolean enabled;
    private int threshold;

    public BasicOption(Context context, boolean enabled, int threshold) {
        super(context);
        this.enabled = enabled;
        this.threshold = threshold;
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
