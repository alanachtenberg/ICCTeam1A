package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-28.
 */
public class EmailOption extends BasicOption {

    private OptionType OPTION_TYPE;
    private boolean enabled;
    private int threshold;
    private String emailAddress;

    public EmailOption() {
        OPTION_TYPE = OptionType.EMAIL;
        enabled = false;
        threshold = 1;
        emailAddress = "";
    }

    public EmailOption(Option option) {
        OPTION_TYPE = OptionType.EMAIL;
        enabled = option.getEnabled();
        threshold = option.getThreshold();
        emailAddress = "";
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
