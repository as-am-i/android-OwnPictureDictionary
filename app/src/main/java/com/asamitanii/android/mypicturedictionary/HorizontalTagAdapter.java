package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by tanii_asami on 2/15/18.
 */

public class HorizontalTagAdapter extends RecyclerView.Adapter<HorizontalTagAdapter.TagHolder> {

    private List<Tag> mTags;

    public HorizontalTagAdapter() {

    }



    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        return new TagHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {

        holder.bind(mTags.get(position));
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public void setTags(List<Tag> tagList) {
        mTags = tagList;
    }

    /*
        Tag Holder
     */
    public class TagHolder extends RecyclerView.ViewHolder {

        private TextView mTagTextView;

        // inflate tags
        public TagHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tag, parent, false));

            mTagTextView = itemView.findViewById(R.id.horizontal_word_tag);
        }

        // bind tags
        public void bind(Tag tag) {
            mTagTextView.setText(tag.getTagName());
        }
    }
}
