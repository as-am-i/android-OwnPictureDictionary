package com.asamitanii.android.mypicturedictionary;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class List {
    private UUID mId;
    private String mListName;
    private User mOwnerId;

    public List() {
        mId = UUID.randomUUID();
        setListName("Default_1");
    }

    public UUID getId() {
        return mId;
    }

    public User getOwnerId() {
        return mOwnerId;
    }

    public String getListName() {
        return mListName;
    }

    public void setListName(String listName) {
        mListName = listName;
    }
}
