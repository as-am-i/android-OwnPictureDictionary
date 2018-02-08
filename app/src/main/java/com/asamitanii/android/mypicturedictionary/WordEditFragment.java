package com.asamitanii.android.mypicturedictionary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/6/18.
 */

public class WordEditFragment extends Fragment {
    private Word mWord;
    private Meaning mMeaning;
    private Tag mTag;

    private EditText mNameField;
    private EditText mDescriptionField;
    private EditText mTagFirst;
    private EditText mTagSecond;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID wordId = (UUID) getActivity().getIntent().getSerializableExtra(WordEditActivity.EXTRA_WORD_ID);
        mWord = WordLab.get(getActivity()).getWord(wordId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_word_edit, container, false);


        mNameField = v.findViewById(R.id.word_name);
        mNameField.setText(mWord.getName());
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWord.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDescriptionField = v.findViewById(R.id.word_description);

        // in the EditField, get an instance of Meaning Model and get String from ihe instance
        mDescriptionField.setText(mWord.getTextMeaning());

        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWord.setTextMeaning(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTagFirst = v.findViewById(R.id.word_tag1);
        mTagFirst.setText(mWord.getTagFirst());
        mTagFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWord.setTagFirst(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTagSecond = v.findViewById(R.id.word_tag2);
        mTagSecond.setText(mWord.getTagSecond());

        mTagSecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWord.setTagSecond(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return v;

    }
}
