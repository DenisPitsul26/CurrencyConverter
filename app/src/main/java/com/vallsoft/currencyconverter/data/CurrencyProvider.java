package com.vallsoft.currencyconverter.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.vallsoft.currencyconverter.data.Contract.CurrencyEntery;


public class CurrencyProvider extends ContentProvider {

    public static final String LOG_TAG = CurrencyProvider.class.getSimpleName();
    private static final int CURRENCIES = 100;
    private static final int CURRENCY = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_TASK, CURRENCIES);
        sUriMatcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_TASK + "/#", CURRENCY);
    }

    private CurrencyDatabaseHelper mCurrencyDbHelper;

    @Override
    public boolean onCreate() {
        mCurrencyDbHelper = new CurrencyDatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase database = mCurrencyDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        Log.v(LOG_TAG, String.valueOf(match));

        switch (match) {
            case CURRENCIES:
                cursor = database.query(CurrencyEntery.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CURRENCY:
                selection = CurrencyEntery._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                Log.e(LOG_TAG, String.valueOf(uri));
                Log.e(LOG_TAG, String.valueOf(selectionArgs));
                Log.e(LOG_TAG, selection);

                cursor = database.query(CurrencyEntery.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CURRENCIES:
                return CurrencyEntery.CONTENT_LIST_TYPE;
            case CURRENCY:
                return CurrencyEntery.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri " + uri + " with match" + match);
        }

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CURRENCIES:
                return insertCurrency(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for" + uri);
        }
    }

    private Uri insertCurrency(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mCurrencyDbHelper.getWritableDatabase();
        long id = db.insert(CurrencyEntery.TABLE_NAME, null, contentValues);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mCurrencyDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case CURRENCIES:
                rowsDeleted = database.delete(CurrencyEntery.TABLE_NAME, selection, selectionArgs);
                break;
            case CURRENCY:
                selection = CurrencyEntery._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(CurrencyEntery.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CURRENCIES:
                return update(uri, values, selection, selectionArgs);
            case CURRENCY:
                selection = CurrencyEntery._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateCurrency(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("update is not supported for " + uri);
        }
    }

    private int updateCurrency(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mCurrencyDbHelper.getWritableDatabase();

        int rowsUpdated = db.update(CurrencyEntery.TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

}
