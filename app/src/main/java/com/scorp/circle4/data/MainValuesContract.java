package com.scorp.circle4.data;

import android.provider.BaseColumns;

public final class MainValuesContract {

    private MainValuesContract() {}

    public static final class MainValuesEntry implements BaseColumns {

        public static final String TABLE_NAME = "main_values";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MAIN_VALUE_CURRENT_SCORE = "current_score";
        public static final String COLUMN_MAIN_VALUE_CURRENT_CIRCLE = "current_circle";
        public static final String COLUMN_MAIN_VALUE_RECORD_TOURNAMENT_SCORE = "record_tournament_score";
    }
}
