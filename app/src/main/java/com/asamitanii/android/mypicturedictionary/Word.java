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

    private String mTagFirst;
    private String mTagSecond;


    //private List<Meaning> mMeaningList;

    public Word() {
        this(UUID.randomUUID());
        setName("New");

        //mTagList = new ArrayList<>();
        //mTagList.add(new Tag());

      //  mMeaningList = new ArrayList<>();

    }

    public Word(UUID id) {
        mId = id;
        setName("New");
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
/*
    public List<Tag> getTagList() {
        return mTagList;
    }
*/


    /*
    public String getTagOfFirst() {
        return mTagList.get(0).getTagName();
    }

    public void setTagOfFirst(String tagName) {
        mTagList.get(0).setTagName(tagName);
    }

    public String getTagOfSecond() {
        return mTagList.get(1).getTagName();
    }

    public void setTagOfSecond(String tagName) {
        mTagList.get(1).setTagName(tagName);
    }

*/
    /*
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
*/
    public String getTextMeaning() {
        return mTextMeaning;
    }

    public void setTextMeaning(String textMeaning) {
        mTextMeaning = textMeaning;
    }

    public String getTagFirst() {
        return mTagFirst;
    }

    public void setTagFirst(String tagFirst) {
        mTagFirst = tagFirst;
    }

    public String getTagSecond() {
        return mTagSecond;
    }

    public void setTagSecond(String tagSecond) {
        mTagSecond = tagSecond;
    }

    public String getPhotoFilename(int number) {
        return "IMG_" + getId().toString() + "_" + number + ".jpg";
    }
}
