package com.asamitanii.android.mypicturedictionary.detabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.asamitanii.android.mypicturedictionary.Word;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema.WordTable;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/9/18.
 */

public class WordCursorWrapper extends CursorWrapper {
    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(WordTable.Cols.UUID));
        String word_name = getString(getColumnIndex(WordTable.Cols.WORD_NAME));
        String meaning_text = getString(getColumnIndex(WordTable.Cols.MEANING_TEXT));
        String tag_first = getString(getColumnIndex(WordTable.Cols.TAG_FIRST));
        String tag_second = getString(getColumnIndex(WordTable.Cols.TAG_SECOND));

        Word word = new Word(UUID.fromString(uuidString));
        word.setName(word_name);
        word.setTextMeaning(meaning_text);
        word.setTagFirst(tag_first);
        word.setTagSecond(tag_second);

        return word;
    }
}
