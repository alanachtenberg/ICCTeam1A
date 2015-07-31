package com.sabre.tripsafe.alerts;

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
