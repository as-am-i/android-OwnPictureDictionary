package com.asamitanii.android.mypicturedictionary;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordListFragment extends Fragment {

    private RecyclerView mWordRecyclerView;
    private WordAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        mWordRecyclerView = (RecyclerView) view.findViewById(R.id.word_recycler_view);
        mWordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        WordLab wordLab = WordLab.get(getActivity());
        List<Word> words = wordLab.getWords();

        mAdapter = new WordAdapter(words);
        mWordRecyclerView.setAdapter(mAdapter);
    }

    /*
        WordHolder
     */
    private class WordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Word mWord;

        private TextView mWordNameTextView;
        private TextView mTagFirstTextView;

        public WordHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_word, parent,false));
            itemView.setOnClickListener(this);

            mWordNameTextView = itemView.findViewById(R.id.word_name);
            mTagFirstTextView = itemView.findViewById(R.id.word_tag);

        }

        // bind
        public void bind(Word word) {
            mWord = word;
            mWordNameTextView.setText(mWord.getName());
            mTagFirstTextView.setText(mWord.getAllTagsString());
        }

        @Override
        public void onClick(View v) {
            Intent intent = WordActivity.newIntent(getActivity(), mWord.getId());
            startActivity(intent);
        }
    }


    /*
        WordAdapter
     */
    private class WordAdapter extends RecyclerView.Adapter<WordHolder> {

        private List<Word> mWords;

        public WordAdapter(List<Word> words) {
            mWords = words;
        }


        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new WordHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = mWords.get(position);
            holder.bind(word);
        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }
    }
}
