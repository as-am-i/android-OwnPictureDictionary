package com.asamitanii.android.mypicturedictionary;

import android.support.v4.content.FileProvider;

import java.io.File;
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

    private String mTextMeaning;

    //private List<Meaning> mMeaningList;

    public Word() {
        this(UUID.randomUUID());
        setName("New");

        mTagList = new ArrayList<>();
        //mTagList.add(new Tag());

      //  mMeaningList = new ArrayList<>();

    }

    public Word(UUID id) {
        mId = id;
        setName("New");

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

    public void addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        mTagList.add(tag);
    }

    public String getAllTagsString() {
        StringBuilder allTags = new StringBuilder();
        for (Tag tag : mTagList) {
            allTags.append(tag.getTagName()).append(" ");
        }
        return allTags.toString();
    }

    public String getTextMeaning() {
        return mTextMeaning;
    }

    public void setTextMeaning(String textMeaning) {
        mTextMeaning = textMeaning;
    }




    public String getPhotoFilename(int number) {
        return "IMG_" + getId().toString() + "_" + number + ".jpg";
    }
}
