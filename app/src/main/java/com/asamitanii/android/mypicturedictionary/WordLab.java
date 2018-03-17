package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordLab {
    private static WordLab sWordLab;
    private List<ParseObject> mWordList;
    private Word mCurrentWord;
    private Context mContext;

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
    }

    public void addWord(Word word) {
        try {
            word.save();
        } catch (ParseException e){
            Log.e("WordLab ", "Error to save. " + e.getMessage());
        }
    }

    public void deleteWord(Word word) {
        word.saveInBackground();
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

    // update rows in the database
    public void updateWord(Word word) {
        word.saveInBackground();
    }

    // to get bytes to save the image file on Parse
    public static byte[] fullyReadFileToBytes(File f) throws IOException {
        int size = (int) f.length();
        byte bytes[] = new byte[size];
        byte tmpBuff[] = new byte[size];
        FileInputStream fis= new FileInputStream(f);;
        try {

            int read = fis.read(bytes, 0, size);
            if (read < size) {
                int remain = size - read;
                while (remain > 0) {
                    read = fis.read(tmpBuff, 0, remain);
                    System.arraycopy(tmpBuff, 0, bytes, size - remain, read);
                    remain -= read;
                }
            }
        }  catch (IOException e){
            throw e;
        } finally {
            fis.close();
        }

        return bytes;
    }

    public File getPhotoFile(Word mWord, int number) {
        File fileDir = mContext.getFilesDir();
        return new File(fileDir, mWord.getPhotoFilename(number));
    }
}
