package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by tanii_asami on 2/6/18.
 */

public class WordEditActivity extends SingleFragmentActivity {

    public static final String EXTRA_WORD_ID = "com.asamitanii.android.mypicturedictionary.word_id";

    private Word mWord;

    @Override
    protected Fragment createFragment() {
        return new WordEditFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String wordId = (String) getIntent().getSerializableExtra(EXTRA_WORD_ID);
        mWord = WordLab.get(this).getWord(wordId);
        setTitle(mWord.getName());
    }

    public static Intent newIntent(Context packageContext, String wordId) {
        Intent intent = new Intent(packageContext, WordEditActivity.class);
        intent.putExtra(EXTRA_WORD_ID, wordId);
        return intent;
    }
}
