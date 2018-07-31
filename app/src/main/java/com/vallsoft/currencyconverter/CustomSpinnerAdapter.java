package com.vallsoft.currencyconverter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    public Resources res;
    public SpinnerModel spinnerValue = null;
    LayoutInflater inflater;
    private Activity activity;
    private ArrayList data;

    public CustomSpinnerAdapter(Activity mainActivity, int resource, ArrayList objects, Resources resLocal) {
        super(mainActivity, resource, objects);
        activity = mainActivity;
        data = objects;
        res = resLocal;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View rootView = inflater.inflate(R.layout.spinner_item, parent, false);
        spinnerValue = null;
        spinnerValue = (SpinnerModel) data.get(position);

        TextView currencyCode = (TextView) rootView.findViewById(R.id.amount_txt);
        TextView currencyType = (TextView) rootView.findViewById(R.id.currency_type);
        ImageView countryFlag = (ImageView) rootView.findViewById(R.id.country_flag);

        currencyCode.setText(spinnerValue.getCurrencyCode());
        currencyType.setText(spinnerValue.getCurrencyCountry());
        countryFlag.setImageResource(spinnerValue.getCountryFlag());

        return rootView;
    }

}
