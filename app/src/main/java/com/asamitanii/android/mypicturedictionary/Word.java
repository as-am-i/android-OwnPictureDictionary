package com.asamitanii.android.mypicturedictionary;

import java.util.UUID;

/**
 * Created by tanii_asami on 1/31/18.
 */

public class Word {

    private UUID mId;
    private String mName;

    public Word() {
        mId = UUID.randomUUID();
        setName("Default word");
    }

    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
