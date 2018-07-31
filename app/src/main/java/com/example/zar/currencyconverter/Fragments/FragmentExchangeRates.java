package com.example.zar.currencyconverter.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zar.currencyconverter.CurrenciesSqlAdapter;
import com.example.zar.currencyconverter.R;
import com.example.zar.currencyconverter.Utils;
import com.example.zar.currencyconverter.data.Contract;


public class FragmentExchangeRates extends BaseFragments implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CURRENCY_LOADER = 0;
    private static final int SEARCH_LOADER = 1;
    ListView exchangeList;
    ConnectivityManager cm;
    CurrenciesSqlAdapter currenciesSqlAdapter;
    TextView txtUpdateDate;
    EditText editText;
    String searchText;
    private ProgressDialog mAuthProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.activity_exchange_list_per_doller, container, false));
        initUi();
        return getFragmentView();
    }

    public void initUi() {
        cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        exchangeList = (ListView) findViewById(R.id.exchange_rate_list);
        txtUpdateDate = (TextView) findViewById(R.id.txt_update_time);
        editText = (EditText) findViewById(R.id.search);
        mAuthProgressDialog = new ProgressDialog(getActivity());
        mAuthProgressDialog.setMessage("getting data please wait...");
        mAuthProgressDialog.setCancelable(false);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    searchText = editText.getText().toString();

                    initLoader();

                } else {
                    initLoaderTwo();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        String[] projection = {Contract.CurrencyEntery.COLUMN_UPDATE_TIME, Contract.CurrencyEntery.COLUMN_CURRECNY_RATE};
        String selection = Contract.CurrencyEntery._ID + "='" + 1 + "';";
        Cursor cursor = getContext().getContentResolver().query(Contract.CurrencyEntery.CONTENT_URI, projection, selection, null, null);
        if (cursor.moveToFirst()) {
            String date = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_UPDATE_TIME));
            String rate = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE));

            if (rate.equals("1")) {
                // Utils.networkCall(getActivity(),mAuthProgressDialog);
                Utils.newNetworkCall(getActivity(), mAuthProgressDialog);

            } else {
                txtUpdateDate.setText(date);
            }


        } else {
            Utils.placeHolderDataSql(getActivity());
        }

        currenciesSqlAdapter = new CurrenciesSqlAdapter(getActivity(), null);
        exchangeList.setAdapter(currenciesSqlAdapter);
        getActivity().getSupportLoaderManager().initLoader(CURRENCY_LOADER, null, this);

    }

    public void initLoaderTwo() {
        getActivity().getSupportLoaderManager().initLoader(CURRENCY_LOADER, null, this);
    }

    public void initLoader() {
        getActivity().getSupportLoaderManager().initLoader(SEARCH_LOADER, null, this);
        getLoaderManager().restartLoader(SEARCH_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CURRENCY_LOADER:
                return new CursorLoader(getContext(),
                        Contract.CurrencyEntery.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

            case SEARCH_LOADER:
                String[] argList = {searchText + "%"};
                return new CursorLoader(getContext(),
                        Contract.CurrencyEntery.CONTENT_URI,
                        null,
                        Contract.CurrencyEntery.COLUMN_COUNTRY_NAME + " LIKE ?",
                        argList,
                        null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        currenciesSqlAdapter.swapCursor(data);
        if (data.moveToFirst()) {
            String date = data.getString(data.getColumnIndex(Contract.CurrencyEntery.COLUMN_UPDATE_TIME));
            String name = data.getString(data.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRENCY_CODE));
            String rate = data.getString(data.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE));
            int id = data.getInt(data.getColumnIndex(Contract.CurrencyEntery._ID));
        }
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        currenciesSqlAdapter.swapCursor(null);
    }

}