package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class EmailOption extends BasicOption {

    private OptionType optionType;
    private boolean enabled;
    private int threshold;
    private String emailAddress;

    public EmailOption(boolean enabled, int threshold, String emailAddress) {
        super(enabled, threshold);
        this.emailAddress = emailAddress;
    }

    @Override
    public void doAlert() {
        // TODO: send email
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
