package com.asamitanii.android.mypicturedictionary;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by tanii_asami on 1/31/18.
 */

public class Word {

    private UUID mId;
    private String mName;

    private List<Tag> mTagList;

    public Word() {
        mId = UUID.randomUUID();
        setName("Default word");
        mTagList = new ArrayList<>();
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

    public List<Tag> getTagList() {
        return mTagList;
    }

    public void setTagList(List<Tag> tagList) {
        mTagList = tagList;
    }

    public void addTag(Tag tag) {
        mTagList.add(tag);
    }

    public String getAllTagsString() {
        StringBuilder allTags = new StringBuilder();
        for (Tag tag : mTagList) {
            allTags.append(tag.getTagName()).append(" ");
        }
        return allTags.toString();
    }
}
