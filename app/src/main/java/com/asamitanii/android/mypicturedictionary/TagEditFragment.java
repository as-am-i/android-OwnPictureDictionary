package com.asamitanii.android.mypicturedictionary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by tanii_asami on 2/26/18.
 */

public class TagEditFragment extends DialogFragment {



    private Word mWord;
    private EditText mTagField;

    private String mTempTag = "";



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.tag_edit_dialog_title);

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_tag_edit, null);
        builder.setView(view);

        mTagField = view.findViewById(R.id.editing_tag_in_dialog);
        mTagField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTempTag = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Add action buttons
           builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // add a new tag here...
                mWord.addTag(mTempTag);
            }
        }).setNegativeButton(R.string.cancel, null);

        return builder.create();
    }

    @Override
    public void onPause() {
        super.onPause();

        mWord.saveAll();
    }

    public void setWord(Word word) {
        mWord = word;
    }


}
