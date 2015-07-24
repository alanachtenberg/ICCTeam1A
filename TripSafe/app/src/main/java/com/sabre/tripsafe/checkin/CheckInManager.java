package com.sabre.tripsafe.checkin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import com.sabre.tripsafe.R;

/**
 * Created by Alan on 7/23/2015.
 */
public class CheckInManager {

    public static void showReminder(FragmentManager manager){
        DialogFragment fragment = new CheckInDialogFragment();
        fragment.show(manager,"fragment_check_in");
    }


}
