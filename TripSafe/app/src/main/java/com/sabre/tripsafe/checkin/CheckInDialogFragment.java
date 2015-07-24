package com.sabre.tripsafe.checkin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Alan on 7/24/2015.
 */
public class CheckInDialogFragment extends DialogFragment {
    private static String DIALOGUE_TITLE="Check-in";
    private static String MESSAGE="Your scheduled check-in is due in 5 minutes";
    private static String POS_BUTTON="Start";
    private static String NEG_BUTTON="Cancel";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(DIALOGUE_TITLE);
        builder.setMessage(MESSAGE);

        builder.setPositiveButton(POS_BUTTON,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO start checkin process
            }
        });

        builder.setNegativeButton(NEG_BUTTON, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();//close dialog
            }
        });

        return builder.create();
    }
}
