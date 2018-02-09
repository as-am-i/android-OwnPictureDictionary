package com.asamitanii.android.mypicturedictionary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class WordFragment extends Fragment {

    private static final String ARG_WORD_ID = "word_id";

    private Word mWord;
    private List mList;
    private TextView mListName;
    private TextView mWordName;
    private TextView mWordDescription;

    private ImageView mImageSecond;
    private ImageView mImageThird;
    private TextView mTagFirst;
    private TextView mTagSecond;

    private ImageView mFirstPhoto;
    private File mMeaningImageFirst;

    private ImageView mSecondPhoto;
    private File mMeaningImageSecond;

    private ImageView mThirdPhoto;
    private File mMeaningImageThird;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        UUID wordId = (UUID) getArguments().getSerializable(ARG_WORD_ID);

        mWord = WordLab.get(getActivity()).getWord(wordId);

        mMeaningImageFirst = WordLab.get(getActivity()).getPhotoFile(mWord, 1);
        mMeaningImageSecond = WordLab.get(getActivity()).getPhotoFile(mWord, 2);
        mMeaningImageThird = WordLab.get(getActivity()).getPhotoFile(mWord, 3);

        mList = new List();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_word, container, false);

//        mListName = v.findViewById(R.id.word_list_name);
//        mListName.setText(mList.getListName());

        mWordName = v.findViewById(R.id.word_name);
        mWordName.setText(mWord.getName());

        mWordDescription = v.findViewById(R.id.word_description);
        mWordDescription.setText(mWord.getTextMeaning());

        mFirstPhoto = v.findViewById(R.id.meaning_image_1);
        mSecondPhoto = v.findViewById(R.id.meaning_image_2);
        mThirdPhoto = v.findViewById(R.id.meaning_image_3);

        mTagFirst = v.findViewById(R.id.word_tag1);
        mTagSecond = v.findViewById(R.id.word_tag2);
        mTagFirst.setText(mWord.getTagFirst());
        mTagSecond.setText(mWord.getTagSecond());

        updatePhotoView();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_word, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_word:
                Intent intent = WordEditActivity.newIntent(getActivity(), mWord.getId());
                startActivity(intent);
                return true;

            case R.id.delete_word:
                WordLab.get(getActivity()).deleteWord(mWord);
                getActivity().finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static WordFragment newInstance(UUID wordId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_WORD_ID, wordId);

        WordFragment fragment = new WordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void updatePhotoView() {
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
