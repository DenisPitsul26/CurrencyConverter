package com.vallsoft.currencyconverter.Fragments;

import android.support.v4.app.Fragment;
import android.view.View;


public class BaseFragments extends Fragment {

    String title;
    View fragmentView;

    public View getFragmentView() {
        return fragmentView;
    }

    public void setFragmentView(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public View findViewById(int res) {
        return getFragmentView().findViewById(res);
    }
}

