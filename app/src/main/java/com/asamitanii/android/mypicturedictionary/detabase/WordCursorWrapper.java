package com.asamitanii.android.mypicturedictionary.detabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.asamitanii.android.mypicturedictionary.Tag;
import com.asamitanii.android.mypicturedictionary.Word;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema.WordTable;

import java.util.ArrayList;
import java.util.List;
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
        String tag_list = getString(getColumnIndex(WordTable.Cols.TAG_LIST));

        Word word = new Word(UUID.fromString(uuidString));
        word.setName(word_name);
        word.setTextMeaning(meaning_text);

        // TAG_LIST contains all the tags as a string due to word.getAllTagsString()
        String[] strings = tag_list.split(",");
        List<Tag> tagList = new ArrayList<>();
        for (String string : strings) {
            Tag tag = new Tag();
            tag.setTagName(string);
            tagList.add(tag);
        }

        word.setTagList(tagList);

        return word;
    }
}
