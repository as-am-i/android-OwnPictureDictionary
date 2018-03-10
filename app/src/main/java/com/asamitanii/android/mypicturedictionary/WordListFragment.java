package com.asamitanii.android.mypicturedictionary;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanii_asami on 2/2/18.
 */

public class WordListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    private RecyclerView mWordRecyclerView;
    private WordAdapter mAdapter;
    private boolean mSubtitleVisible;

    private List<ParseObject> words;

//    private TextView mMessageNoWords;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        words = new ArrayList<>();
        loadDataFromParse();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);

        mWordRecyclerView = view.findViewById(R.id.word_recycler_view);
        mWordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mMessageNoWords = view.findViewById(R.id.message_no_word);

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataFromParse();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_word_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_word:
                Word word = new Word();
                word.setName("New");
                WordLab.get(getActivity()).addWord(word);

                loadDataFromParse();
                updateUI();

                // to start an instance of WordEditActivity to edit the new Word
                Intent intent = WordEditActivity.newIntent(getActivity(), word.getId());
                startActivity(intent);

                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();

                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    private void loadDataFromParse() {
        WordLab wordLab = WordLab.get(getActivity());
        wordLab.loadWords(this);
        words = wordLab.getWordList();
    }

    public void updateData() {
        mAdapter.notifyDataSetChanged();
    }

    private void updateUI() {
//        WordLab wordLab = WordLab.get(getActivity());
//        List<Word> words = wordLab.getWords();

        if (mAdapter == null) {
            mAdapter = new WordAdapter(words);
            mWordRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
            mAdapter.setWords(words);
        }

//        if (words.size() > 0) {
//            mMessageNoWords.setVisibility(View.GONE);
//        } else {
//            mMessageNoWords.setVisibility(View.VISIBLE);
//        }

        updateSubtitle();
    }

    private void updateSubtitle() {
        WordLab wordLab = WordLab.get(getActivity());
        int wordSize = words.size();
        String subtitle = getResources().getQuantityString(R.plurals.subtitle_prural, wordSize, wordSize);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /*
        WordHolder
     */
    private class WordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Word mWord;

        private RecyclerView mTagRecyclerView;
        private HorizontalTagAdapter mTagAdapter;

        private TextView mWordNameTextView;

        public WordHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_word, parent,false));
            itemView.setOnClickListener(this);

            // inflate word name here
            mWordNameTextView = itemView.findViewById(R.id.word_name);

            // inflate tags here
            mTagRecyclerView = itemView.findViewById(R.id.tag_recycler_view);

            // overrided new LinearLayoutManager to use HORIZONTAL for LayoutManager
            Context context = itemView.getContext();
            mTagRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

            // create a new tagAdapter and set it to TagRecyclerView
            mTagAdapter = new HorizontalTagAdapter(mWord);
        }

        // bind inside WordHolder
        public void bind(Word word) {
            mWord = word;
            mWordNameTextView.setText(mWord.getName());

            mTagAdapter.setTags(mWord.getTagList());
            mTagRecyclerView.setAdapter(mTagAdapter);
        }

        @Override
        public void onClick(View v) {
            // to start an instance of WordPagerActivity when clicked
            Intent intent = WordPagerActivity.newIntent(getActivity(), mWord.getId());
            startActivity(intent);
        }
    }


    /*
        WordAdapter
     */
    private class WordAdapter extends RecyclerView.Adapter<WordHolder> {

        private List<ParseObject> mWords;

        public WordAdapter(List<ParseObject> words) {
            mWords = words;
        }


        @Override
        public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new WordHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {
            Word word = (Word )mWords.get(position);
            holder.bind(word);
        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }

        // to used in updateUI() for refreshing model data with database
        public void setWords(List<ParseObject> words) {
            mWords = words;
        }
    }
}
