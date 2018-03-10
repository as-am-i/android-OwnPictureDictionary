package com.asamitanii.android.mypicturedictionary;

import android.support.v4.content.FileProvider;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Created by tanii_asami on 1/31/18.
 */

@ParseClassName("Word")
public class Word extends ParseObject {

    public static final String ID = "objectId";
    public static final String NAME = "name";
    public static final String MEANING_TEXT = "meaning_text";;

    private List<Tag> mTagList;

    private String mTextMeaning;

    //private List<Meaning> mMeaningList;

    public Word() {

        mTagList = new ArrayList<>();
    }

    public String getId() {
        return getObjectId();
    }

    public String getName() {
        String name = getString(NAME);
        if (name != null) {
            return name;
        } else {
            return "No name";
        }
    }

    public void setName(String name) {
        put(NAME, name);
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

    public void deleteTag(int tag) {
        mTagList.remove(tag);
    }

    public String getAllTagsString() {
        StringBuilder allTags = new StringBuilder();
        for (Tag tag : mTagList) {
            allTags.append(tag.getTagName()).append(",");
        }
        return allTags.toString();
    }

    public String getMeaningText() {
        return getString(MEANING_TEXT);
    }

    public void setMeaningText(String text) {
        put(MEANING_TEXT, text);
    }

    public String getPhotoFilename(int number) {
        return "IMG_" + getId().toString() + "_" + number + ".jpg";
    }
}
