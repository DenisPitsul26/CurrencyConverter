package com.example.zar.currencyconverter.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public class Contract {

    public static final String CONTENT_AUTHORITY = "com.example.zar.currencyconverter";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_TASK = "currencies";

    private Contract() {
    }

    public static final class CurrencyEntery implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_TASK);


        public static final String TABLE_NAME = "currencies";

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TASK;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TASK;

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_CURRENCY_CODE = "code";
        public static final String COLUMN_CURRECNY_RATE = "rate";
        public static final String COLUMN_UPDATE_TIME = "date";
        public static final String COLUMN_FLAG = "flag";
        public static final String COLUMN_COUNTRY_NAME = "name";
    }
}
