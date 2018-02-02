package com.asamitanii.android.mypicturedictionary;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class Tag {
    private UUID mId;
    private String mTagName;

    public Tag() {
        mId = UUID.randomUUID();
        setTagName("#tag");
    }

    public UUID getId() {
        return mId;
    }

    public String getTagName() {
        return mTagName;
    }

    public void setTagName(String tagName) {
        mTagName = tagName;
    }
}
