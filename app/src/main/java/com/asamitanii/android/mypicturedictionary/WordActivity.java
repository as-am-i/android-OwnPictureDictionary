package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class WordActivity extends SingleFragmentActivity {

    public static final String EXTRA_WORD_ID = "com.asamitanii.android/mypicturedictionary.word_id";

    public static Intent newIntent(Context packageContext, UUID wordId) {
        Intent intent = new Intent(packageContext, WordActivity.class);
        intent.putExtra(EXTRA_WORD_ID, wordId);
        return intent;
    }

    @Override
    protected WordFragment createFragment() {
        return new WordFragment();
    }
}
