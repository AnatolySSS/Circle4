package com.scorp.circle4.data;

import android.provider.BaseColumns;

public final class CircleContract {

    private CircleContract() {}

    public static final class CircleEntry implements BaseColumns {

        public static final String TABLE_NAME = "circles";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_CIRCLE_NAME = "name";
        public static final String COLUMN_CIRCLE_PRICE = "price";
        public static final String COLUMN_CIRCLE_ISBOUGHT = "isbought";
        public static final String COLUMN_CIRCLE_PICTURE = "picture";

        public static final int ISBOUGHT_NO = 0;
        public static final int ISBOUGHT_YES = 1;
    }
}
