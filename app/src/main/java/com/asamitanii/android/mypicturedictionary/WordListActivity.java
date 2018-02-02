package com.asamitanii.android.mypicturedictionary;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordListActivity extends SingleFragmentActivity {

    @Override
    protected WordListFragment createFragment() {
        return new WordListFragment();
    }
}
