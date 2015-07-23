package com.sabre.tripsafe.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPreference extends DialogPreference {
    private static int MAX_VALUE = 60;
    private static int MIN_VALUE = 1;

    private int number = 0;
    private NumberPicker picker = null;

    public NumberPreference(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);
        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
    }

    public String getValue(){
        return Integer.toString(number);
    }


    @Override
    protected View onCreateDialogView() {
        picker = new NumberPicker(getContext());
        picker.setMaxValue(MAX_VALUE);
        picker.setMinValue(MIN_VALUE);
        return (picker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);
    }


    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);
        if (positiveResult) {
            number = picker.getValue();
            if (callChangeListener(number)) {
                persistString(Integer.toString(number));
            }
            setSummary(getValue()+" min");
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return (a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        String numberString = null;
        if (restoreValue) {
            if (defaultValue == null) {
                numberString = getPersistedString("1");
            } else {
                numberString = getPersistedString(defaultValue.toString());
            }
        } else {
            numberString = defaultValue.toString();
        }
        number = Integer.parseInt(numberString);
        setSummary(getValue()+" min");
    }
}