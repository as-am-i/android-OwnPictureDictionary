package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/6/18.
 */

public class WordEditActivity extends SingleFragmentActivity {

    private static final String EXTRA_WORD_ID = "com.asamitanii.android.mypicturedictionary.word_id";

    @Override
    protected Fragment createFragment() {
        return new WordEditFragment();
    }

    public static Intent newIntent(Context packageContext, UUID wordId) {
        Intent intent = new Intent(packageContext, WordEditActivity.class);
        intent.putExtra(EXTRA_WORD_ID, wordId);
        return intent;
    }
}
