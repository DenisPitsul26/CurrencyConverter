package com.example.zar.currencyconverter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.zar.currencyconverter.data.Contract;
import com.example.zar.currencyconverter.data.CurrencyDatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Utils {

    public static ProgressDialog mAuthProgressDialog;
    public static Activity mActivity;

    public static String YAHOO_API_URL = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22GMD,PKR,AED,INR,USD,EUR,BHD,AUD,CAD,JPY,CNY,RUB,SAR,IRR,IQD,QAR,EGP,TRY,LKR,BRL,PLN,SEK,KRW,CZK,DKK,CHF,ARS,NOK,THB,HUF,MXN,COP,RON,UAH,BGN,MYR,NZD,IDR,VND,CLP,SGD,TWD,AZN,PEN,LRD,BYR,AMD,GEL,HRK,JMD,PHP,KZT,BDT,ALL,ISK,TND,CRC,UYU,DOP,MAD,RSD,NAD,VEF,SYP,NIO,SDG,GTQ,DZD,MKD,BOB,TTD,UZS,JOD,KHR,KPW,KES,KWD,LBP,BND,TZS,BAM,XOF,PYG,HTG,YER,KGS,UGX,NGN,NPR,RWF,MDL,GNF,MNT,LAK,DOP,MMK,PAB,CUP,TOP,ZMW,MZN,BWP,LYD,ERN,OMR,GHS,HNL,SRD,GYD,WST,MRO,TMT,AFN,ZAR,TJS,GBP%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
    public static String CRYPTO_CURRENCIES_API_URL = "https://api.coinmarketcap.com/v1/ticker/?limit=10";

    public static String[] curreniesCode = new String[]{"GMD", "PKR", "AED", "INR", "USD", "EUR", "BHD", "AUD", "CAD", "JPY", "CNY", "RUB", "SAR", "IRR", "IQD", "QAR", "EGP", "TRY", "LKR", "BRL"
            , "PLN", "SEK", "KRW", "CZK", "DKK", "CHF", "ARS", "NOK", "THB", "HUF", "MXN", "COP", "RON", "UAH", "BGN", "MYR", "NZD", "IDR", "VND", "CLP", "SGD", "TWD", "AZN", "PEN", "LRD", "BYR",
            "AMD", "GEL", "HRK", "JMD", "PHP", "KZT", "BDT", "ALL", "ISK", "TND", "CRC", "UYU", "DOP", "MAD", "RSD", "NAD", "VEF", "SYP", "NIO", "SDG", "GTQ", "DZD", "MKD", "BOB", "TTD", "UZS", "JOD"
            , "KHR", "KPW", "KES", "KWD", "LBP", "BND", "TZS", "BAM", "XOF", "PYG", "HTG", "YER", "KGS", "UGX", "NGN", "NPR", "RWF", "MDL", "GNF", "MNT", "LAK", "DOP", "MMK", "PAB", "CUP", "TOP", "ZMW"
            , "MZN", "BWP", "LYD", "ERN", "OMR", "GHS", "HNL", "SRD", "GYD", "WST", "MRO", "TMT", "AFN", "ZAR", "TJS", "GBP"};


    public static void placeHolderDataSql(Activity mActivity) {
        Date date = new Date();
        String time = date.toString();
        ArrayList<ExchangeListData> placeHolderData = new ArrayList<>();
        placeHolderData.add(new ExchangeListData("PKR", "Pakistani Rupee", R.drawable.pak, time, "1"));
        placeHolderData.add(new ExchangeListData("AED", "U.A.E Dirham", R.drawable.uae, time, "1"));
        placeHolderData.add(new ExchangeListData("INR", "Indian Rupee", R.drawable.inr, time, "1"));
        placeHolderData.add(new ExchangeListData("USD", "U.S Dollar", R.drawable.usa, time, "1"));
        placeHolderData.add(new ExchangeListData("EUR", "Euro", R.drawable.eur, time, "1"));
        placeHolderData.add(new ExchangeListData("BHD", "Bahraini Dinar", R.drawable.baharain, time, "1"));
        placeHolderData.add(new ExchangeListData("AUD", "Australian Dollar", R.drawable.aus, time, "1"));
        placeHolderData.add(new ExchangeListData("CAD", "Canadian Dollar", R.drawable.cnd, time, "1"));
        placeHolderData.add(new ExchangeListData("JPY", "Japanese Yen", R.drawable.jpy, time, "1"));
        placeHolderData.add(new ExchangeListData("CNY", "Chinese Yuan", R.drawable.cny, time, "1"));
        placeHolderData.add(new ExchangeListData("RUB", "Russian Rouble", R.drawable.rus, time, "1"));
        placeHolderData.add(new ExchangeListData("SAR", "Saudi Arabia Rial", R.drawable.ksa, time, "1"));
        placeHolderData.add(new ExchangeListData("IRR", "Iran Rial", R.drawable.irr, time, "1"));
        placeHolderData.add(new ExchangeListData("IQD", "Iraqi Dinar", R.drawable.irq, time, "1"));
        placeHolderData.add(new ExchangeListData("QAR", "Qatar Rial", R.drawable.qat, time, "1"));
        placeHolderData.add(new ExchangeListData("EGP", "Egyptian Pound", R.drawable.egy, time, "1"));
        placeHolderData.add(new ExchangeListData("TRY", "Turkish Lira", R.drawable.tur, time, "1"));
        placeHolderData.add(new ExchangeListData("LKR", "Sri Lanka Rupee", R.drawable.sri, time, "1"));
        placeHolderData.add(new ExchangeListData("BRL", "Brazil Real", R.drawable.brazil, time, "1"));
        placeHolderData.add(new ExchangeListData("PLN", "Poland", R.drawable.poland, time, "1"));
        placeHolderData.add(new ExchangeListData("SEK", "Swedish Krona", R.drawable.sweden, time, "1"));
        placeHolderData.add(new ExchangeListData("KRW", "SouthKorean won", R.drawable.southkoreanwon, time, "1"));
        placeHolderData.add(new ExchangeListData("CZK", "Czech Koruna", R.drawable.czech, time, "1"));
        placeHolderData.add(new ExchangeListData("DKK", "Danish Krone", R.drawable.denmark, time, "1"));
        placeHolderData.add(new ExchangeListData("CHF", "Swiss Franc", R.drawable.swiss, time, "1"));
        placeHolderData.add(new ExchangeListData("ARS", "Argentine Peso", R.drawable.argentine, time, "1"));
        placeHolderData.add(new ExchangeListData("NOK", "Norwegian Krone", R.drawable.norway, time, "1"));
        placeHolderData.add(new ExchangeListData("THB", "Thai Baht", R.drawable.thiland, time, "1"));
        placeHolderData.add(new ExchangeListData("HUF", "Hungarien forient", R.drawable.hungry, time, "1"));
        placeHolderData.add(new ExchangeListData("MXN", "Mexican Peso", R.drawable.mexico, time, "1"));
        placeHolderData.add(new ExchangeListData("COP", "Colombian Peso", R.drawable.colombia, time, "1"));
        placeHolderData.add(new ExchangeListData("RON", "Romanian Leu", R.drawable.romanian, time, "1"));
        placeHolderData.add(new ExchangeListData("UAH", "Ukrainian hryvnia", R.drawable.ukraine, time, "1"));
        placeHolderData.add(new ExchangeListData("BGN", "Bulgarian Ley", R.drawable.bulgrian, time, "1"));
        placeHolderData.add(new ExchangeListData("MYR", "Malaysian Ringgit", R.drawable.malaysia, time, "1"));
        placeHolderData.add(new ExchangeListData("NZD", "New ZeaLand Dollar", R.drawable.newzealand, time, "1"));
        placeHolderData.add(new ExchangeListData("IDR", "Indonesian Rupiah", R.drawable.indonesia, time, "1"));
        placeHolderData.add(new ExchangeListData("VND", "Vietnamese dong", R.drawable.vietnam, time, "1"));
        placeHolderData.add(new ExchangeListData("CLP", "Chilean Peso", R.drawable.chile, time, "1"));
        placeHolderData.add(new ExchangeListData("SGD", "Singapore dollar", R.drawable.singapore, time, "1"));
        placeHolderData.add(new ExchangeListData("TWD", "Taiwan Dollar", R.drawable.taiwan, time, "1"));
        placeHolderData.add(new ExchangeListData("AZN", "Azerbaijani manat", R.drawable.azerbaijan, time, "1"));
        placeHolderData.add(new ExchangeListData("PEN", "Peru sol", R.drawable.peru, time, "1"));
        placeHolderData.add(new ExchangeListData("LRD", "Liberian Dollar", R.drawable.liberian, time, "1"));
        placeHolderData.add(new ExchangeListData("BYR", "Belarusia ruble", R.drawable.belarus, time, "1"));
        placeHolderData.add(new ExchangeListData("AMD", "Armenian dram", R.drawable.armenia, time, "1"));
        placeHolderData.add(new ExchangeListData("GEL", "Georgian lari", R.drawable.georgia, time, "1"));
        placeHolderData.add(new ExchangeListData("HRK", "Croatian kuna", R.drawable.croatia, time, "1"));
        placeHolderData.add(new ExchangeListData("JMD", "Jamaican dollar", R.drawable.jamaica, time, "1"));
        placeHolderData.add(new ExchangeListData("PHP", "Philippine peso", R.drawable.philippines, time, "1"));
        placeHolderData.add(new ExchangeListData("KZT", "Kazakhstani tenge", R.drawable.kazakhstan, time, "1"));
        placeHolderData.add(new ExchangeListData("BDT", "Bangladeshi taka", R.drawable.bangladesh, time, "1"));
        placeHolderData.add(new ExchangeListData("ALL", "Albanian lek", R.drawable.albania, time, "1"));
        placeHolderData.add(new ExchangeListData("ISK", "Icelandic krona", R.drawable.iceland, time, "1"));
        placeHolderData.add(new ExchangeListData("TND", "Tunisian dinar", R.drawable.tunisia, time, "1"));
        placeHolderData.add(new ExchangeListData("CRC", "Costarican colon", R.drawable.costarica, time, "1"));
        placeHolderData.add(new ExchangeListData("UYU", "Uruguayan peso", R.drawable.uruguay, time, "1"));
        placeHolderData.add(new ExchangeListData("DOP", "Dominican peso", R.drawable.dominica, time, "1"));
        placeHolderData.add(new ExchangeListData("MAD", "Moroccan dirham", R.drawable.morocco, time, "1"));
        placeHolderData.add(new ExchangeListData("RSD", "Serbian dinar", R.drawable.serbia, time, "1"));
        placeHolderData.add(new ExchangeListData("NAD", "Nambian Dollar", R.drawable.namibia, time, "1"));
        placeHolderData.add(new ExchangeListData("VEF", "Venezuelan bolivar", R.drawable.venezuela, time, "1"));
        placeHolderData.add(new ExchangeListData("SYP", "Syrian pound", R.drawable.syria, time, "1"));
        placeHolderData.add(new ExchangeListData("NIO", "Nicaraguan cordoba", R.drawable.nicaragua, time, "1"));
        placeHolderData.add(new ExchangeListData("SDG", "Sudanese pound", R.drawable.sudan, time, "1"));
        placeHolderData.add(new ExchangeListData("GTQ", "Guatemalan quetzel", R.drawable.guatemala, time, "1"));
        placeHolderData.add(new ExchangeListData("DZD", "Algerian dinar", R.drawable.algeria, time, "1"));
        placeHolderData.add(new ExchangeListData("MKD", "Macedonian dinar", R.drawable.macedonia, time, "1"));
        placeHolderData.add(new ExchangeListData("BOB", "Boliviano", R.drawable.bolivia, time, "1"));
        placeHolderData.add(new ExchangeListData("TTD", "Tobago dollar", R.drawable.tobago, time, "1"));
        placeHolderData.add(new ExchangeListData("UZS", "Uzbekistani solm", R.drawable.uzbekistan, time, "1"));
        placeHolderData.add(new ExchangeListData("JOD", "Jordanian dinar", R.drawable.jordan, time, "1"));
        placeHolderData.add(new ExchangeListData("KHR", "Cambodian riel", R.drawable.cambodia, time, "1"));
        placeHolderData.add(new ExchangeListData("KPW", "North Korean won", R.drawable.northkorea, time, "1"));
        placeHolderData.add(new ExchangeListData("KES", "Kenyan shilling", R.drawable.kenya, time, "1"));
        placeHolderData.add(new ExchangeListData("KWD", "Kuwait dinar", R.drawable.kuwait, time, "1"));
        placeHolderData.add(new ExchangeListData("LBP", "Lebanese pound", R.drawable.lebanon, time, "1"));
        placeHolderData.add(new ExchangeListData("BND", "Brunei dollar", R.drawable.brunei, time, "1"));
        placeHolderData.add(new ExchangeListData("TZS", "Tanzanian shilling", R.drawable.tanzania, time, "1"));
        placeHolderData.add(new ExchangeListData("BAM", "Bosnian mark", R.drawable.bosnia, time, "1"));
        placeHolderData.add(new ExchangeListData("XOF", "West African franc", R.drawable.centralafrican, time, "1"));
        placeHolderData.add(new ExchangeListData("PYG", "Paraguayan guarni", R.drawable.paraguay, time, "1"));
        placeHolderData.add(new ExchangeListData("HTG", "Haitian gourde", R.drawable.haiti, time, "1"));
        placeHolderData.add(new ExchangeListData("YER", "Yemeni rial", R.drawable.yemen, time, "1"));
        placeHolderData.add(new ExchangeListData("KGS", "Kyrgyzstani som", R.drawable.kyrgyzstan, time, "1"));
        placeHolderData.add(new ExchangeListData("UGX", "Uganda shilling", R.drawable.uganda, time, "1"));
        placeHolderData.add(new ExchangeListData("NGN", "Nigerian naira", R.drawable.nigeria, time, "1"));
        placeHolderData.add(new ExchangeListData("NPR", "Nepalese rupee", R.drawable.nepal, time, "1"));
        placeHolderData.add(new ExchangeListData("RWF", "Rwando franc", R.drawable.rwanda, time, "1"));
        placeHolderData.add(new ExchangeListData("MDL", "Moldovan leu", R.drawable.moldova, time, "1"));
        placeHolderData.add(new ExchangeListData("GNF", "Guinean franc", R.drawable.guinean, time, "1"));
        placeHolderData.add(new ExchangeListData("MNT", "Mongolian togrog", R.drawable.mongolia, time, "1"));
        placeHolderData.add(new ExchangeListData("LAK", "Lao Kip", R.drawable.laos, time, "1"));
        placeHolderData.add(new ExchangeListData("DOP", "Dominican peso", R.drawable.dominica, time, "1"));
        placeHolderData.add(new ExchangeListData("MMK", "Myanmar kyat", R.drawable.myanmar, time, "1"));
        placeHolderData.add(new ExchangeListData("PAB", "Panamanian balboa", R.drawable.panama, time, "1"));
        placeHolderData.add(new ExchangeListData("CUP", "Cubian peso", R.drawable.cuba, time, "1"));
        placeHolderData.add(new ExchangeListData("TOP", "Tonga", R.drawable.tonga, time, "1"));
        placeHolderData.add(new ExchangeListData("ZMW", "Zambian kwacha", R.drawable.zambia, time, "1"));
        placeHolderData.add(new ExchangeListData("MZN", "Mozambique", R.drawable.mozambique, time, "1"));
        placeHolderData.add(new ExchangeListData("BWP", "Bostwana pule", R.drawable.botswana, time, "1"));
        placeHolderData.add(new ExchangeListData("LYD", "Libyan dinar", R.drawable.libya, time, "1"));
        placeHolderData.add(new ExchangeListData("ERN", "Eritrean nakfa", R.drawable.eritrea, time, "1"));
        placeHolderData.add(new ExchangeListData("OMR", "Omani rial", R.drawable.oman, time, "1"));
        placeHolderData.add(new ExchangeListData("GHS", "Ghanaian cedi", R.drawable.ghana, time, "1"));
        placeHolderData.add(new ExchangeListData("HNL", "Honduran lempira", R.drawable.honduras, time, "1"));
        placeHolderData.add(new ExchangeListData("SRD", "Surinamese dollar", R.drawable.suriname, time, "1"));
        placeHolderData.add(new ExchangeListData("GYD", "Guyanese dollar", R.drawable.guyana, time, "1"));
        placeHolderData.add(new ExchangeListData("WST", "Samoan tala", R.drawable.samoa, time, "1"));
        placeHolderData.add(new ExchangeListData("MRO", "Mauritanian ouguiya", R.drawable.mauritania, time, "1"));
        placeHolderData.add(new ExchangeListData("TMT", "Turkmanistan manat", R.drawable.turkmenistan, time, "1"));
        placeHolderData.add(new ExchangeListData("AFN", "Afghani", R.drawable.afghanistan, time, "1"));
        placeHolderData.add(new ExchangeListData("ZAR", "SouthAfrican Rand", R.drawable.southafrica, time, "1"));
        placeHolderData.add(new ExchangeListData("TJS", "Tajikistani somoni", R.drawable.tajikistan, time, "1"));
        placeHolderData.add(new ExchangeListData("GBP", "UK Pound", R.drawable.eng, time, "1"));
        placeHolderData.add(new ExchangeListData("XBT", "Bitcoin", R.drawable.bitcoin, time, "1"));
        placeHolderData.add(new ExchangeListData("ETH", "Ethereum", R.drawable.ethereum, time, "1"));
        placeHolderData.add(new ExchangeListData("XRP", "Ripple", R.drawable.ripple, time, "1"));
        placeHolderData.add(new ExchangeListData("BCH", "Bitcoin cash", R.drawable.bitcoincash, time, "1"));
        placeHolderData.add(new ExchangeListData("LTC", "Litecoin", R.drawable.litecoin, time, "1"));
        placeHolderData.add(new ExchangeListData("DASH", "Dash", R.drawable.dash, time, "1"));
        placeHolderData.add(new ExchangeListData("XEM", "Nem", R.drawable.neo, time, "1"));
        placeHolderData.add(new ExchangeListData("XMR", "Monero", R.drawable.monero, time, "1"));


        for (int i = 0; i < placeHolderData.size(); i++) {
            ContentValues values = new ContentValues();
            ExchangeListData data = placeHolderData.get(i);
            values.put(Contract.CurrencyEntery.COLUMN_FLAG, data.getCountryFlag());
            values.put(Contract.CurrencyEntery.COLUMN_CURRENCY_CODE, data.getCountryCode());
            values.put(Contract.CurrencyEntery.COLUMN_COUNTRY_NAME, data.getCountryName());
            values.put(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE, data.getAmount());
            values.put(Contract.CurrencyEntery.COLUMN_UPDATE_TIME, data.getCurrentTime());
            mActivity.getContentResolver().insert(Contract.CurrencyEntery.CONTENT_URI, values);
        }

    }

    public static ArrayList<String> CryptoCurrJsonFilter(String jasonString) {
        ArrayList<String> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jasonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (!object.getString("name").equals("NEO") && !object.getString("name").equals("IOTA") && !object.getString("name").equals("Dash")) {
                    String s = object.getString("price_usd");
                    double d = Double.parseDouble(s);
                    d = 1 / d;
                    list.add("" + d);
                } else if (object.getString("name").equals("Dash")) {
                    String s = object.getString("price_usd");
                    double d = Double.parseDouble(s);
                    d = 1 / d;
                    list.add("" + d);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

   /* public static ArrayList<String> filterJASON(String jasonString){
        ArrayList<String> arrayList=new ArrayList<>();
        try {
            if (jasonString == null || jasonString.equals("")) {
                Log.d("server ; ", "down");
                mAuthProgressDialog.dismiss();
            }
            else {
                JSONObject jsonObject = new JSONObject(jasonString);
                JSONObject queryObject = jsonObject.getJSONObject("query");
                JSONObject resultObject = queryObject.getJSONObject("results");
                JSONArray rateObject = resultObject.getJSONArray("rate");

                for (int i = 0; i < rateObject.length(); i++) {
                    JSONObject countryObjects = rateObject.getJSONObject(i);
                    String currencyRate = countryObjects.getString("Rate");
                    arrayList.add(currencyRate);
                }
                Log.e("data:", arrayList.get(0));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;

    }*/

    public static String httpCalls(String input) {
        try {
            URL url = new URL(input);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String data = "";
            while (true) {
                String temp = br.readLine();
                if (temp == null) {
                    break;
                }
                data += temp;
            }
            urlConnection.disconnect();
            return data;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

  /*  public  static class CurrencyAsyncTaskActivity extends AsyncTask<String,Void,ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String data=httpCalls(params[0]);
            ArrayList<String> arrayList=filterJASON(data);
            return arrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<String> ratesList) {
            if (ratesList.size()==0) {
                Toast.makeText(mActivity,"Server not responded",Toast.LENGTH_SHORT).show();
            }
            else {
                for (int i = 0; i < ratesList.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE, ratesList.get(i));
                    values.put(Contract.CurrencyEntery.COLUMN_UPDATE_TIME, Calendar.getInstance().getTime().toString());
                    Uri uri = ContentUris.withAppendedId(Contract.CurrencyEntery.CONTENT_URI, i);
                    mActivity.getContentResolver().update(uri, values, null, null);
                }

            }
            CryptoCurrAsync bitcoinAsync = new CryptoCurrAsync();
            String bitcoinUrl = CRYPTO_CURRENCIES_API_URL;
            bitcoinAsync.execute(bitcoinUrl);
        }
    }*/

    public static void newNetworkCall(Activity activity, ProgressDialog progressDialog) {

        String CURRENCY_LAYER_URL = "http://apilayer.net/api/live?access_key=" + activity.getResources().getString(R.string.currencylayer_api_key) + "&currencies=GMD,PKR,AED,INR,USD,EUR,BHD,AUD,CAD,JPY,CNY,RUB,SAR,IRR,IQD,QAR,EGP,TRY,LKR,BRL,PLN,SEK,KRW,CZK,DKK,CHF,ARS,NOK,THB,HUF,MXN,COP,RON,UAH,BGN,MYR,NZD,IDR,VND,CLP,SGD,TWD,AZN,PEN,LRD,BYR,AMD,GEL,HRK,JMD,PHP,KZT,BDT,ALL,ISK,TND,CRC,UYU,DOP,MAD,RSD,NAD,VEF,SYP,NIO,SDG,GTQ,DZD,MKD,BOB,TTD,UZS,JOD,KHR,KPW,KES,KWD,LBP,BND,TZS,BAM,XOF,PYG,HTG,YER,KGS,UGX,NGN,NPR,RWF,MDL,GNF,MNT,LAK,DOP,MMK,PAB,CUP,TOP,ZMW,MZN,BWP,LYD,ERN,OMR,GHS,HNL,SRD,GYD,WST,MRO,TMT,AFN,ZAR,TJS,GBP&source=USD&format=1";
        mAuthProgressDialog = progressDialog;
        mActivity = activity;
        ConnectivityManager cm = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            mAuthProgressDialog.show();
            Log.e("size", "" + curreniesCode.length + "" + curreniesCode.length / 2);

            /*for (int i=0; i<116; i++) {

                String url="https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=USD&to_currency="+curreniesCode[i]+"&apikey=B1W4X18USDB9L0WI";
                NewApiNetworkCall call=new NewApiNetworkCall();
                call.execute(url);
            }*/
            CurrecyLayerAsync async = new CurrecyLayerAsync();
            async.execute(CURRENCY_LAYER_URL);
        } else {
            Toast.makeText(mActivity, "Please Press Refresh", Toast.LENGTH_LONG).show();
        }
    }

   /* public static void networkCall(Activity activity,ProgressDialog progressDialog) {
        mAuthProgressDialog=progressDialog;
        mActivity=activity;
        ConnectivityManager cm = (ConnectivityManager)mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            mAuthProgressDialog.show();
            CurrencyAsyncTaskActivity async=new CurrencyAsyncTaskActivity();
            async.execute(YAHOO_API_URL);
        }
        else {
            Toast.makeText(mActivity,"Please Press Refresh",Toast.LENGTH_LONG).show();
        }
    }*/

    public static ArrayList<String> currencyLayerJson(String jasonString) {

        ArrayList<String> arrayList = new ArrayList<>();
        try {
            if (jasonString == null || jasonString.equals("")) {
                mAuthProgressDialog.dismiss();
            } else {

                JSONObject object = new JSONObject(jasonString);
                JSONObject quotesObject = object.getJSONObject("quotes");

                for (int i = 0; i <= quotesObject.length(); i++) {
                    arrayList.add(quotesObject.getString("USD" + curreniesCode[i]));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;
    }

    /*public static class NewApiNetworkCall extends AsyncTask<String,Void,ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String result=httpCalls(params[0]);
            ArrayList<String> data=newJsonFormatter(result);
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            if (s!=null && s.size()!=0) {
                Log.e("tagdata","code"+s.get(0)+" rate"+s.get(1));
                CurrencyDatabaseHelper helper=new CurrencyDatabaseHelper(mActivity);
                helper.insertInto(s.get(0),s.get(1),s.get(2));
                if (s.get(0).equals("GBP")) {
                    mAuthProgressDialog.dismiss();
                    CryptoCurrAsync bitcoinAsync = new CryptoCurrAsync();
                    String bitcoinUrl = CRYPTO_CURRENCIES_API_URL;
                    bitcoinAsync.execute(bitcoinUrl);
                }
            }
            else {
                Log.e("not","not");
            }
        }
    }
    */

    public static class CryptoCurrAsync extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String> list = new ArrayList<>();
            String bitcoinJson = httpCalls(params[0]);
            list = CryptoCurrJsonFilter(bitcoinJson);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            if (list.size() == 0) {
                Toast.makeText(mActivity, "Crypto Cruencies data not found", Toast.LENGTH_SHORT).show();
                mAuthProgressDialog.dismiss();
            } else {
                for (int i = 0; i < list.size(); i++) {
                    ContentValues values = new ContentValues();
                    Uri currentPetUri = ContentUris.withAppendedId(Contract.CurrencyEntery.CONTENT_URI, 116 + i);
                    values.put(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE, list.get(i));
                    mActivity.getContentResolver().update(currentPetUri, values, null, null);
                }
                mAuthProgressDialog.dismiss();
            }
        }
    }

    public static class CurrecyLayerAsync extends AsyncTask<String, Void, ArrayList<String>> {


        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String result = httpCalls(params[0]);
            ArrayList<String> data = currencyLayerJson(result);
            return data;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            /*if (s!=null && s.size()!=0) {
                Log.e("tagdata","code"+s.get(0)+" rate"+s.get(1));
                CurrencyDatabaseHelper helper=new CurrencyDatabaseHelper(mActivity);
                helper.insertInto(s.get(0),s.get(1),s.get(2));
                if (s.get(0).equals("GBP")) {
                    mAuthProgressDialog.dismiss();
                    CryptoCurrAsync bitcoinAsync = new CryptoCurrAsync();
                    String bitcoinUrl = CRYPTO_CURRENCIES_API_URL;
                    bitcoinAsync.execute(bitcoinUrl);
                }
            }
            else {
                Log.e("not","not");
            }*/
            for (int i = 1; i < s.size(); i++) {
                CurrencyDatabaseHelper helper = new CurrencyDatabaseHelper(mActivity);
                helper.insertInto(curreniesCode[i], s.get(i), Calendar.getInstance().getTime().toString());
            }
            mAuthProgressDialog.dismiss();
            CryptoCurrAsync bitcoinAsync = new CryptoCurrAsync();
            String bitcoinUrl = CRYPTO_CURRENCIES_API_URL;
            bitcoinAsync.execute(bitcoinUrl);
        }
    }

/*
    public static  ArrayList<String> newJsonFormatter(String jasonString) {

        ArrayList<String> arrayList=new ArrayList<>();
        try {
            if (jasonString == null || jasonString.equals("")) {
                mAuthProgressDialog.dismiss();
            }
            else {
                JSONObject jsonObject = new JSONObject(jasonString);
                JSONObject jsonObject1=jsonObject.getJSONObject("Realtime Currency Exchange Rate");
                arrayList.add(jsonObject1.getString("3. To_Currency Code"));
                arrayList.add(jsonObject1.getString("5. Exchange Rate"));
                arrayList.add(jsonObject1.getString("6. Last Refreshed"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrayList;

    }*/


}
