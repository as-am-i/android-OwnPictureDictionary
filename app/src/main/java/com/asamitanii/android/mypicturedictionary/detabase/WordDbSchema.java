package com.asamitanii.android.mypicturedictionary.detabase;

/**
 * Created by tanii_asami on 2/9/18.
 */

public class WordDbSchema {
    public static final class WordTable {
        public static final String NAME = "words";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String WORD_NAME = "word_name";
            public static final String MEANING_TEXT = "meaning_text";
            public static final String TAG_LIST = "tag_list";
        }
    }
}
