package com.asamitanii.android.mypicturedictionary;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID wordId = (UUID) getArguments().getSerializable(ARG_WORD_ID);

        mWord = WordLab.get(getActivity()).getWord(wordId);

        mMeaningImageFirst = WordLab.get(getActivity()).getPhotoFile(mWord, 1);

        mList = new List();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_word, container, false);

        mListName = v.findViewById(R.id.word_list_name);
        mListName.setText(mList.getListName());

        mWordName = v.findViewById(R.id.word_name);
        mWordName.setText(mWord.getName());

        mWordDescription = v.findViewById(R.id.word_description);
        mWordDescription.setText(mWord.getTextMeaning());

        mFirstPhoto = v.findViewById(R.id.meaning_image_1);

        mImageSecond = v.findViewById(R.id.meaning_image_2);
        mImageThird = v.findViewById(R.id.meaning_image_3);

        mTagFirst = v.findViewById(R.id.word_tag1);
        mTagSecond = v.findViewById(R.id.word_tag2);
        mTagFirst.setText(mWord.getTagFirst());
        mTagSecond.setText(mWord.getTagSecond());

        updatePhotoView();

        return v;
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
    }


}
