package com.asamitanii.android.mypicturedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asamitanii.android.mypicturedictionary.detabase.WordBaseHelper;
import com.asamitanii.android.mypicturedictionary.detabase.WordCursorWrapper;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema.WordTable;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;

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
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();

        WordCursorWrapper cursor = queryWords(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                words.add(cursor.getWord());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return words;
    }

    public void addWord(Word w) {
        ContentValues values = getContentValues(w);

        mDatabase.insert(WordTable.NAME, null, values);
    }

    public void deleteWord(Word w) {
        mDatabase.delete(WordTable.NAME, WordTable.Cols.UUID + " = ?", new String[] { w.getId().toString()});
    }

    public Word getWord(UUID id) {
        WordCursorWrapper cursor = queryWords(WordTable.Cols.UUID + " = ?", new String[] { id.toString() });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getWord();
        } finally {
            cursor.close();
        }
    }

    // update rows in the database
    public void updateWord(Word word) {
        String uuidString = word.getId().toString();
        ContentValues values = getContentValues(word);

        mDatabase.update(WordTable.NAME, values, WordTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    // to read in data from SQLite using query()
    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                WordTable.NAME, null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return new WordCursorWrapper(cursor);
    }

    // putting a Word into a ContentValues, which is the assistance for writes and updates to databases
    private static ContentValues getContentValues(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordTable.Cols.UUID, word.getId().toString());
        values.put(WordTable.Cols.WORD_NAME, word.getName());
        values.put(WordTable.Cols.MEANING_TEXT, word.getTextMeaning());
        values.put(WordTable.Cols.TAG_LIST, word.getAllTagsString());

        return values;
    }

    public File getPhotoFile(Word mWord, int number) {
        File fileDir = mContext.getFilesDir();
        return new File(fileDir, mWord.getPhotoFilename(number));
    }
}
