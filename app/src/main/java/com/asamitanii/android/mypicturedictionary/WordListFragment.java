package com.asamitanii.android.mypicturedictionary;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private class WordHolder extends RecyclerView.ViewHolder {
        public WordHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_word, parent,false));
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
            return null;
        }

        @Override
        public void onBindViewHolder(WordHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
