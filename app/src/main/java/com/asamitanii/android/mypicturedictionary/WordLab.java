package com.asamitanii.android.mypicturedictionary;

import android.content.Context;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;

    public static WordLab get(Context context) {
        if (sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }

    // singleton
    private WordLab(Context context) {

    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();

        return words;
    }
}
