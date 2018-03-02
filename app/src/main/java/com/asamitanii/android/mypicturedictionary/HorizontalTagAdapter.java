package com.asamitanii.android.mypicturedictionary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tanii_asami on 2/15/18.
 */

public class HorizontalTagAdapter extends RecyclerView.Adapter<HorizontalTagAdapter.TagHolder> {

    private List<Tag> mTags;
    private Tag mTag;
    private Word mWord;

    public HorizontalTagAdapter(Word word) {
        mWord = word;
    }



    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        return new TagHolder(layoutInflater, parent);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {

        holder.bind(mTags.get(position), position);
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
    public class TagHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTagTextView;
        int position;

        // inflate tags
        public TagHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tag, parent, false));
            itemView.setOnClickListener(this);

            mTagTextView = itemView.findViewById(R.id.horizontal_word_tag);
        }

        // bind tags
        public void bind(Tag tag, int position) {
            this.position = position;
            mTagTextView.setText(tag.getTagName());
        }

        @Override
        public void onClick(View v) {
            // the user can delete tag only in WordEditActivity
            if (v.getContext() instanceof WordEditActivity) {
                // delete tag here
                Toast.makeText(v.getContext(),  "Deleted!", Toast.LENGTH_SHORT).show();
                mWord.deleteTag(position);

                // update UI
                notifyItemRemoved(position);
                //notifyDataSetChanged();
                //setTags(mTags);
            }
        }
    }
}
