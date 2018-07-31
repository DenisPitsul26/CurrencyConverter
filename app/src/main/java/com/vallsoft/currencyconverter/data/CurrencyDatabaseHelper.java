package com.vallsoft.currencyconverter.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class CurrencyDatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mytask.db";


    public CurrencyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASK_TABLE = "CREATE TABLE " + Contract.CurrencyEntery.TABLE_NAME + " ("
                + Contract.CurrencyEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.CurrencyEntery.COLUMN_CURRENCY_CODE + " TEXT NULL, "
                + Contract.CurrencyEntery.COLUMN_CURRECNY_RATE + " TEXT NULL, "
                + Contract.CurrencyEntery.COLUMN_UPDATE_TIME + " TEXT NULL,"
                + Contract.CurrencyEntery.COLUMN_COUNTRY_NAME + " TEXT NULL,"
                + Contract.CurrencyEntery.COLUMN_FLAG + " INTEGER" + ");";
        db.execSQL(SQL_CREATE_TASK_TABLE);
        Log.e("Table :", "Created");
        Log.e("Table :", SQL_CREATE_TASK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertInto(String code, String rate, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
/*        db.rawQuery("Update "+CurrencyEntery.TABLE_NAME+" Set "+CurrencyEntery.COLUMN_CURRECNY_RATE+" ='"+rate+"' , "+CurrencyEntery.COLUMN_UPDATE_TIME+" ='"+time+"' Where "
                +CurrencyEntery.COLUMN_CURRENCY_CODE+" ='"+code+"'",null);*/
        ContentValues values = new ContentValues();
        values.put(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE, rate);
        values.put(Contract.CurrencyEntery.COLUMN_UPDATE_TIME, time);
        db.update(Contract.CurrencyEntery.TABLE_NAME, values, Contract.CurrencyEntery.COLUMN_CURRENCY_CODE + " = '" + code + "'", null);
        db.close();
    }


}
