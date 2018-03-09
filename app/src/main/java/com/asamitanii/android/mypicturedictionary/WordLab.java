package com.asamitanii.android.mypicturedictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asamitanii.android.mypicturedictionary.detabase.WordBaseHelper;
import com.asamitanii.android.mypicturedictionary.detabase.WordCursorWrapper;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema;
import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema.WordTable;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;
    private List<ParseObject> mWordList;
    private Word mCurrentWord;
    private Context mContext;
//    private SQLiteDatabase mDatabase;

    public static WordLab get(Context context) {
        if (sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }

    // singleton
    private WordLab(Context context) {
        mWordList = new ArrayList<>();
        mContext = context.getApplicationContext();
//        mDatabase = new WordBaseHelper(mContext).getWritableDatabase();
    }

//    public List<Word> getWords() {
//        List<Word> words = new ArrayList<>();
//
//        WordCursorWrapper cursor = queryWords(null, null);
//
//        try {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                words.add(cursor.getWord());
//                cursor.moveToNext();
//            }
//        } finally {
//            cursor.close();
//        }
//
//        return words;
//    }

    public void addWord(Word word) {
        try {
            word.save();
        } catch (ParseException e){
            Log.e("WordLab ", "Error to save. " + e.getMessage());
        }
//        ContentValues values = getContentValues(w);
//        mDatabase.insert(WordTable.NAME, null, values);
    }

    public void deleteWord(Word word) {
        word.deleteInBackground();
//        mDatabase.delete(WordTable.NAME, WordTable.Cols.UUID + " = ?", new String[] { w.getId().toString()});
    }

    public void loadWords(WordListFragment frag) {
     final WordListFragment fragment = frag;

     // create a query without any where clause, makes it use cashe in the devise, and find in backfroud to let the user do something
     ParseQuery<ParseObject> query = ParseQuery.getQuery("Word");
     query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
     query.findInBackground(new FindCallback<ParseObject>() {
         @Override
         public void done(List<ParseObject> words, ParseException e) {
             if (e == null) {
                 mWordList.clear();
                 mWordList.addAll(words);
                 fragment.updateData();
             } else {
                 Log.d("Word", "Error: " + e.getMessage());
             }
         }
     });
    }

    public List<ParseObject> getWordList() {
        return mWordList;
    }

    public Word getWord(String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Word");
        query.whereEqualTo(Word.ID, id);
        query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

        try {
            mCurrentWord = (Word) query.find().get(0);
        } catch (ParseException e) {
            Log.d("Word", "Error: " + e.getMessage());
        }

        return mCurrentWord;
    }

//    public Word getWord(UUID id) {
//        WordCursorWrapper cursor = queryWords(WordTable.Cols.UUID + " = ?", new String[] { id.toString() });
//
//        try {
//            if (cursor.getCount() == 0) {
//                return null;
//            }
//
//            cursor.moveToFirst();
//            return cursor.getWord();
//        } finally {
//            cursor.close();
//        }
//    }

    // update rows in the database
    public void updateWord(Word word) {
        word.saveInBackground();
//        String uuidString = word.getId().toString();
//        ContentValues values = getContentValues(word);
//
//        mDatabase.update(WordTable.NAME, values, WordTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    // to read in data from SQLite using query()
//    private WordCursorWrapper queryWords(String whereClause, String[] whereArgs) {
//        Cursor cursor = mDatabase.query(
//                WordTable.NAME, null, // columns - null selects all columns
//                whereClause,
//                whereArgs,
//                null, // groupBy
//                null, // having
//                null // orderBy
//        );
//        return new WordCursorWrapper(cursor);
//    }

    // putting a Word into a ContentValues, which is the assistance for writes and updates to databases
//    private static ContentValues getContentValues(Word word) {
//        ContentValues values = new ContentValues();
//        values.put(WordTable.Cols.UUID, word.getId().toString());
//        values.put(WordTable.Cols.WORD_NAME, word.getName());
//        values.put(WordTable.Cols.MEANING_TEXT, word.getTextMeaning());
//        values.put(WordTable.Cols.TAG_LIST, word.getAllTagsString());
//
//        return values;
//    }

    public File getPhotoFile(Word mWord, int number) {
        File fileDir = mContext.getFilesDir();
        return new File(fileDir, mWord.getPhotoFilename(number));
    }
}
