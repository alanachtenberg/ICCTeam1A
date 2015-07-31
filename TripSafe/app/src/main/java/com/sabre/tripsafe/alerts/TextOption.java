package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class TextOption extends BasicOption {

    private OptionType optionType;
    private boolean enabled;
    private int threshold;
    private String phoneNumber;

    public TextOption(boolean enabled, int threshold, String phoneNumber) {
        super(enabled, threshold);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void doAlert() {
        // TODO: send text
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int getThreshold() {
        return threshold;
    }

    @Override
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

}
