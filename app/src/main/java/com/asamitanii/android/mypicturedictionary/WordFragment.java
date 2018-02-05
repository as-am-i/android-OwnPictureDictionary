package com.asamitanii.android.mypicturedictionary;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class WordFragment extends Fragment {
    private Word mWord;
    private List mList;
    private Meaning mMeaning;
    private Tag mTag;
    private TextView mListName;
    private TextView mWordName;
    private TextView mWordDescription;
    private ImageView mImageFirst;
    private ImageView mImageSecond;
    private ImageView mImageThird;
    private TextView mTagFirst;
    private TextView mTagSecond;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID wordId = (UUID) getActivity().getIntent().getSerializableExtra(WordActivity.EXTRA_WORD_ID);
        mWord = WordLab.get(getActivity()).getWord(wordId);
        mList = new List();
        mMeaning = new Meaning();
        mTag = new Tag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_word, container, false);

        mListName = v.findViewById(R.id.word_list_name);
        mListName.setText(mList.getListName());

        mWordName = v.findViewById(R.id.word_name);
        mWordName.setText(mWord.getName());

        mWordDescription = v.findViewById(R.id.word_description);
        mWordDescription.setText(mMeaning.getDescription());

        mImageFirst = v.findViewById(R.id.meaning_image_1);
        mImageSecond = v.findViewById(R.id.meaning_image_2);
        mImageThird = v.findViewById(R.id.meaning_image_3);

        mTagFirst = v.findViewById(R.id.word_tag1);
        mTagSecond = v.findViewById(R.id.word_tag2);
        mTagFirst.setText(mTag.getTagName());
        mTagSecond.setText(mTag.getTagName());

        return v;
    }



}
