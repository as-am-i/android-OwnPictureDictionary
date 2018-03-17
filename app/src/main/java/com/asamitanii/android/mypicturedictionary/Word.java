package com.asamitanii.android.mypicturedictionary;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanii_asami on 1/31/18.
 */

@ParseClassName("Word")
public class Word extends ParseObject {

    public static final String ID = "objectId";
    public static final String NAME = "name";
    public static final String MEANING_TEXT = "meaning_text";;
    public static final String TAG_LIST = "tag_list";

    private List<Tag> mTagList;

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
        if(mTagList.size() == 0){
            splitAllTags();
        }

        return mTagList;
    }

    public void setTagList(String tagListAsString) {
        put(TAG_LIST, tagListAsString);
    }

    public void splitAllTags() {
        if(getString(TAG_LIST) != null) {
            String[] strings = getString(TAG_LIST).split(",");
            for (String string : strings) {
                Tag tag = new Tag();
                tag.setTagName(string);
                mTagList.add(tag);
            }
        }
    }

    public void addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        mTagList.add(tag);

        // store in parse
        String tag_list = convertAllTags();
        setTagList(tag_list);
    }

    public void deleteTag(int tag) {
        mTagList.remove(tag);
        String tag_list = convertAllTags();
        setTagList(tag_list);
    }

    public String convertAllTags() {
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

    public void saveAll() {
        this.saveInBackground();

        for ( Tag tag : mTagList) {
            tag.saveInBackground();
        }
    }


}
