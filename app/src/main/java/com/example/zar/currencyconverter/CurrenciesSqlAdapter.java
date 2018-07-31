package com.example.zar.currencyconverter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zar.currencyconverter.data.Contract;


public class CurrenciesSqlAdapter extends CursorAdapter {

    public CurrenciesSqlAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.single_exchange_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView code = (TextView) view.findViewById(R.id.country_code);
        TextView name = (TextView) view.findViewById(R.id.country_name);
        TextView amount = (TextView) view.findViewById(R.id.amount_list);
        ImageView flag = (ImageView) view.findViewById(R.id.country_flag);
        String countryCode = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRENCY_CODE));
        String currencyAmount = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE));
        int countryFlag = cursor.getInt(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_FLAG));
        String countryName = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_COUNTRY_NAME));
        int id = cursor.getInt(cursor.getColumnIndex(Contract.CurrencyEntery._ID));
        code.setText(countryCode);
        amount.setText(currencyAmount);
        name.setText(countryName);
        flag.setImageResource(countryFlag);
    }
}
