package com.asamitanii.android.mypicturedictionary;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

public class User {
    private UUID mId;
    private String mUserName;

    public User() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
