package com.asamitanii.android.mypicturedictionary;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class Tag {

    public static final String ID = "objectId";
    public static final String NAME = "name";

    private String tagId = "";
    private String tagName = "";

    public Tag() {

    }

    public String getId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String name) {
        if (name.charAt(0) == '#') {
            tagName = name;
        } else {
            tagName = "#" + name;
        }
    }

    public void saveInBackground() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tag");
        query.whereEqualTo("name", getTagName());

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(java.util.List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    Log.d("score", "Retrieved " + objects.size() + " scores");
                    if(objects.size()==0){
                        ParseObject tag = new ParseObject("Tag");
                        tag.put(NAME, getTagName());
                        tag.saveInBackground();
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }

        });



    }
}
