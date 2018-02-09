package com.asamitanii.android.mypicturedictionary;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    private ImageView mFirstPhoto;
    private File mMeaningImageFirst;

    private ImageView mSecondPhoto;
    private File mMeaningImageSecond;

    private ImageView mThirdPhoto;
    private File mMeaningImageThird;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID wordId = (UUID) getActivity().getIntent().getSerializableExtra(WordEditActivity.EXTRA_WORD_ID);
        mWord = WordLab.get(getActivity()).getWord(wordId);
        // now I have a file here
        mMeaningImageFirst = WordLab.get(getActivity()).getPhotoFile(mWord, 1);
        mMeaningImageSecond = WordLab.get(getActivity()).getPhotoFile(mWord,2);
        mMeaningImageThird = WordLab.get(getActivity()).getPhotoFile(mWord, 3);
    }

    @Override
    public void onPause() {
        super.onPause();

        WordLab.get(getActivity()).updateWord(mWord);
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

        mFirstPhoto = v.findViewById(R.id.meaning_image_1);
        mFirstPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {

                                OutputStream os;
                                try {
                                    os = new FileOutputStream(mMeaningImageFirst);
                                    r.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, os);
                                    os.flush();
                                    os.close();
                                    updatePhotoView();
                                } catch (Exception e) {
                                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                                }
                            }
                        }).show(getActivity().getSupportFragmentManager());

            }
        });

        mSecondPhoto = v.findViewById(R.id.meaning_image_2);
        mSecondPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {

                                OutputStream os;
                                try {
                                    os = new FileOutputStream(mMeaningImageSecond);
                                    r.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, os);
                                    os.flush();
                                    os.close();
                                    updatePhotoView();
                                } catch (Exception e) {
                                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                                }
                            }
                        }).show(getActivity().getSupportFragmentManager());

            }
        });

        mThirdPhoto = v.findViewById(R.id.meaning_image_3);
        mThirdPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {

                                OutputStream os;
                                try {
                                    os = new FileOutputStream(mMeaningImageThird);
                                    r.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, os);
                                    os.flush();
                                    os.close();
                                    updatePhotoView();
                                } catch (Exception e) {
                                    Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
                                }
                            }
                        }).show(getActivity().getSupportFragmentManager());

            }
        });


        updatePhotoView();




        return v;

    }

    private void updatePhotoView() {
        // no instance created or no picture the user didn't take
        if (mMeaningImageFirst == null || !mMeaningImageFirst.exists()) {
            mFirstPhoto.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mMeaningImageFirst.getPath(), getActivity());
            mFirstPhoto.setImageBitmap(bitmap);
        }

        if (mMeaningImageSecond == null || !mMeaningImageSecond.exists()) {
            mSecondPhoto.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mMeaningImageSecond.getPath(), getActivity());
            mSecondPhoto.setImageBitmap(bitmap);
        }

        if (mMeaningImageThird == null || !mMeaningImageThird.exists()) {
            mThirdPhoto.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(mMeaningImageThird.getPath(), getActivity());
            mThirdPhoto.setImageBitmap(bitmap);
        }

    }
}
