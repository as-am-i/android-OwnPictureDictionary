package com.asamitanii.android.mypicturedictionary;

import android.content.Context;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;

    private List<Word> mWords;

    public static WordLab get(Context context) {
        if (sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }

    // singleton
    private WordLab(Context context) {
        mWords = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Word word = new Word();

            word.setName("Word" + i);
            Tag tag = new Tag();
            tag.setTagName("tagtagtag");
            word.addTag(tag);

            mWords.add(word);
        }
    }

    public List<Word> getWords() {
        return mWords;
    }

    public Word getWord(UUID id) {
        for (Word word : mWords) {
            if (word.getId().equals(id)) {
                return word;
            }
        }
        return null;
    }
}
