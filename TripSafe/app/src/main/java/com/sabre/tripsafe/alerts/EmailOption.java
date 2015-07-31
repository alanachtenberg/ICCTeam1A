package com.sabre.tripsafe.alerts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.sabre.tripsafe.R;

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
    public View getView(int position, View convertView, ViewGroup parent, Context mCtx) {
        final int p = position;
        LayoutInflater inflater = (LayoutInflater) mCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.view_email_option, parent, false);
        final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        final Button button = (Button) rowView.findViewById(R.id.button);
        button.setText(Integer.toString(getThreshold()));
        checkBox.setChecked(isEnabled());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
                if (checkBox.isChecked())
                    setEnabled(true);
                else
                    setEnabled(false);
            }
        });

        final EditText editText = (EditText) rowView.findViewById(R.id.editText);
        editText.setText(emailAddress);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                emailAddress = s.toString();
            }
        });
        return rowView;
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
