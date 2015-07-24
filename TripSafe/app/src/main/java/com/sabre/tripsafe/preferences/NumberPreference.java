package com.sabre.tripsafe.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;

import com.sabre.tripsafe.R;

public class NumberPreference extends DialogPreference {
    private int mMaxValue;
    private int mMinValue;
    private String mValueDescription;


    private NumberPicker picker = null;

    public NumberPreference(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);
        TypedArray attributes = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.NumberPreference,0,0);
        try {
            setMaxValue(attributes.getInteger(R.styleable.NumberPreference_maxValue, 60));
            setMinValue(attributes.getInteger(R.styleable.NumberPreference_minValue, 1));
            setValueDescription(attributes.getString(R.styleable.NumberPreference_valueDescription));
            setPositiveButtonText("Set");
            setNegativeButtonText("Cancel");
        }
        finally {
            attributes.recycle();
        }
    }

    public int getValue(){
        if (picker!=null)
            return picker.getValue();
        else
            return -1;
    }

    public void setMaxValue(int maxValue){
        mMaxValue=maxValue;
        if (picker!=null){
            picker.setMaxValue(mMaxValue);
        }
    }
    public int getMaxValue(){
        return mMaxValue;
    }
    public void setMinValue(int minValue){
        mMinValue=minValue;
        if (picker!=null){
            picker.setMinValue(mMaxValue);
        }
    }
    public int getMinValue(){
        return mMinValue;
    }

    public void setValueDescription(String valueDescription){
        mValueDescription=valueDescription;
    }

    public String getValueDescription(){
        return mValueDescription;
    }


    @Override
    protected View onCreateDialogView() {
        picker = new NumberPicker(getContext());
        picker.setMaxValue(this.getMaxValue());
        picker.setMinValue(this.getMinValue());
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
            int number = picker.getValue();
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
        setSummary(numberString+" "+getValueDescription());
    }
}