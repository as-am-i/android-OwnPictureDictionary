package com.asamitanii.android.mypicturedictionary;

import android.os.Bundle;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordListActivity extends SingleFragmentActivity {

    @Override
    protected WordListFragment createFragment() {
        return new WordListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.word_list_name);
    }
}
