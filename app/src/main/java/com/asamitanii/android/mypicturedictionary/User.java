package com.asamitanii.android.mypicturedictionary;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.UUID;

/**
 * Created by tanii_asami on 2/1/18.
 */

@ParseClassName("User")
public class User extends ParseObject {
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
