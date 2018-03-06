package com.asamitanii.android.mypicturedictionary;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

@ParseClassName("Meaning")
public class Meaning extends ParseObject {
    private UUID mId;
    private String mDescription;
    private Word mWordId;
    private User mOwnerId;
    private String mTypeOfDescription;

    public Meaning() {
        mId = UUID.randomUUID();
        setDescription("Default text description for the word");
    }

    public UUID getId() {
        return mId;
    }

    public Word getWordId() {
        return mWordId;
    }

    public User getOwnerId() {
        return mOwnerId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTypeOfDescription() {
        return mTypeOfDescription;
    }

    public void setTypeOfDescription(String typeOfDescription) {
        mTypeOfDescription = typeOfDescription;
    }
}
