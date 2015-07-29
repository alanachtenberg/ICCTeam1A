package com.sabre.tripsafe.alerts;

/**
 * Created by rsitisr on 2015-07-29.
 */
public class Options {

    public static Option castOption(Option option, OptionType newOptionType) {
        if (newOptionType == OptionType.EMAIL)
            return new EmailOption(option);
        else if (newOptionType == OptionType.TEXT)
            return new TextOption(option);
        else
            return new BasicOption(option);
    }

}
