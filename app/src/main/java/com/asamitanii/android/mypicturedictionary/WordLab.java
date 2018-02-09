package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.asamitanii.android.mypicturedictionary.detabase.WordBaseHelper;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;

    private List<Word> mWords;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static WordLab get(Context context) {
        if (sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }

    // singleton
    private WordLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new WordBaseHelper(mContext).getWritableDatabase();
        mWords = new ArrayList<>();
    }

    public List<Word> getWords() {
        return mWords;
    }

    public void addWord(Word w) {
        mWords.add(w);
    }

    public void deleteWord(Word w) {
        mWords.remove(w);
    }

    public Word getWord(UUID id) {
        for (Word word : mWords) {
            if (word.getId().equals(id)) {
                return word;
            }
        }
        return null;
    }

    public File getPhotoFile(Word mWord, int number) {
        File fileDir = mContext.getFilesDir();
        return new File(fileDir, mWord.getPhotoFilename(number));
    }
}
