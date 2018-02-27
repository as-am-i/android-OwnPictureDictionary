package com.asamitanii.android.mypicturedictionary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by tanii_asami on 2/26/18.
 */

public class TagEditFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.tag_edit_dialog_title).setPositiveButton(android.R.string.ok, null).create();
    }
}
