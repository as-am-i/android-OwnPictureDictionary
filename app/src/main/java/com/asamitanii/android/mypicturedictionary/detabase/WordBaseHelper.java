package com.asamitanii.android.mypicturedictionary.detabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asamitanii.android.mypicturedictionary.detabase.WordDbSchema.WordTable;

/**
 * Created by tanii_asami on 2/9/18.
 */

public class WordBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "wordBase.db";

    public WordBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + WordTable.NAME + "(" + " _id integer primary key autoincrement, " + WordTable.Cols.UUID + ", " + WordTable.Cols.WORD_NAME + ", " + WordTable.Cols.MEANING_TEXT + ", " + WordTable.Cols.TAG_LIST + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
