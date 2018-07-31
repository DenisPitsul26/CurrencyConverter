package com.vallsoft.currencyconverter;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.vallsoft.currencyconverter.Fragments.BaseFragments;
import com.vallsoft.currencyconverter.Fragments.FragmentConverter;
import com.vallsoft.currencyconverter.Fragments.FragmentExchangeRates;
import com.vallsoft.currencyconverter.Fragments.PagerAdapter;

import java.util.ArrayList;


public class CurrencyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ConnectivityManager cm;
    TabLayout tabs;
    ViewPager pager;
    boolean flag = false;
    private InterstitialAd mInterstitialAd;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intiComponentTabsLayout();
        mInterstitialAd = createNewIntAd();
        loadIntAdd();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private InterstitialAd createNewIntAd() {
        InterstitialAd intAd = new InterstitialAd(this);
        // set the adUnitId (defined in values/strings.xml)
        intAd.setAdUnitId(getString(R.string.ad_id_interstitial));
        intAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                if (flag == true) {

                } else {
                    //Utils.networkCall(CurrencyActivity.this,mAuthProgressDialog);
                    Utils.newNetworkCall(CurrencyActivity.this, mAuthProgressDialog);
                }
            }
        });
        return intAd;
    }

    private void showIntAdd() {

// Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {//Utils.networkCall(this,mAuthProgressDialog);
            Utils.newNetworkCall(this, mAuthProgressDialog);
        }
    }

    private void loadIntAdd() {
        // Disable the  level two button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("754DB6521943676637AE86202C5ACE52")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void intiComponentTabsLayout() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        ArrayList<BaseFragments> pages = new ArrayList<>();
        FragmentConverter fragmentConverter = new FragmentConverter();
        fragmentConverter.setTitle("Converter");
        pages.add(fragmentConverter);
        FragmentExchangeRates fragmentExchangeRates = new FragmentExchangeRates();
        fragmentExchangeRates.setTitle("Exchange Rate");
        pages.add(fragmentExchangeRates);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), pages);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(pages.size());
        tabs.setupWithViewPager(pager);
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setMessage("getting data please wait...");
        mAuthProgressDialog.setCancelable(false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    flag = true;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.currency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            showIntAdd();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_review) {
            rate();
        } else if (id == R.id.nav_email) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ivan.myroniv@gmail.com"});
            intent.setData(Uri.parse("mailto:"));
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            share();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void rate() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Download Currency FragmentConverter app at: https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
