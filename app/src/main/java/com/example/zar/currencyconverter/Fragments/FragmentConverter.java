package com.example.zar.currencyconverter.Fragments;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zar.currencyconverter.CustomSpinnerAdapter;
import com.example.zar.currencyconverter.R;
import com.example.zar.currencyconverter.SpinnerModel;
import com.example.zar.currencyconverter.Utils;
import com.example.zar.currencyconverter.data.Contract;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.grantland.widget.AutofitHelper;


public class FragmentConverter extends BaseFragments {

    Spinner from, to;
    ImageView invert;
    EditText edtFrom;
    TextView resulTex;
    double fromDouble, toDouble, giveDouble, result;
    ArrayList<SpinnerModel> spinnerDataList;
    CustomSpinnerAdapter spinnerAdapter;
    private ProgressDialog mAuthProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setFragmentView(inflater.inflate(R.layout.content_currency, container, false));
        intiComponent();
        checkDataOfSqlite();
        spinnerInit();
        return getFragmentView();
    }

    public void spinnerInit() {
        final Resources res = getResources();
        spinnerDataList.add(new SpinnerModel("XBT", "Bitcoin", R.drawable.bitcoin));
        spinnerDataList.add(new SpinnerModel("USD", "U.S dollar", R.drawable.usa));
        spinnerDataList.add(new SpinnerModel("XRP", "Ripple", R.drawable.ripple));
        spinnerDataList.add(new SpinnerModel("BCH", "Bitcoin cash", R.drawable.bitcoincash));
        spinnerDataList.add(new SpinnerModel("LTC", "Litecoin", R.drawable.litecoin));
        spinnerDataList.add(new SpinnerModel("DASH", "Dash", R.drawable.dash));
        spinnerDataList.add(new SpinnerModel("XEM", "Nem", R.drawable.neo));
        spinnerDataList.add(new SpinnerModel("XMR", "Monero", R.drawable.monero));
        spinnerDataList.add(new SpinnerModel("ETH", "Ethereum", R.drawable.ethereum));
        spinnerDataList.add(new SpinnerModel("PKR", "Pakistani rupee", R.drawable.pak));
        spinnerDataList.add(new SpinnerModel("AED", "U.A.E dirham", R.drawable.uae));
        spinnerDataList.add(new SpinnerModel("AFN", "Afghani", R.drawable.afghanistan));
        spinnerDataList.add(new SpinnerModel("INR", "Indian rupee", R.drawable.inr));
        spinnerDataList.add(new SpinnerModel("GBP", "UK pound", R.drawable.eng));
        spinnerDataList.add(new SpinnerModel("EUR", "euro", R.drawable.eur));
        spinnerDataList.add(new SpinnerModel("BHD", "Bahraini Dinar", R.drawable.baharain));
        spinnerDataList.add(new SpinnerModel("AUD", "Australian dollar", R.drawable.aus));
        spinnerDataList.add(new SpinnerModel("CAD", "Canadian dollar", R.drawable.cnd));
        spinnerDataList.add(new SpinnerModel("JPY", "Japanese yen", R.drawable.jpy));
        spinnerDataList.add(new SpinnerModel("CNY", "Chinese yuan", R.drawable.cny));
        spinnerDataList.add(new SpinnerModel("RUB", "Russian rouble", R.drawable.rus));
        spinnerDataList.add(new SpinnerModel("SAR", "Saudi Arabia rial", R.drawable.ksa));
        spinnerDataList.add(new SpinnerModel("IRR", "Iran rial", R.drawable.irr));
        spinnerDataList.add(new SpinnerModel("IQD", "Iraqi dinar", R.drawable.irq));
        spinnerDataList.add(new SpinnerModel("QAR", "Qatar rial", R.drawable.qat));
        spinnerDataList.add(new SpinnerModel("EGP", "Egyptian pound", R.drawable.egy));
        spinnerDataList.add(new SpinnerModel("TRY", "Turkish lira", R.drawable.tur));
        spinnerDataList.add(new SpinnerModel("LKR", "Sri Lanka rupee", R.drawable.sri));
        spinnerDataList.add(new SpinnerModel("ZAR", "SouthAfrican rand", R.drawable.southafrica));
        spinnerDataList.add(new SpinnerModel("BRL", "Brazil real", R.drawable.brazil));
        spinnerDataList.add(new SpinnerModel("PLN", "Poland", R.drawable.poland));
        spinnerDataList.add(new SpinnerModel("SEK", "Swedish krona", R.drawable.sweden));
        spinnerDataList.add(new SpinnerModel("KRW", "SouthKorean won", R.drawable.southkoreanwon));
        spinnerDataList.add(new SpinnerModel("CZK", "Czech koruna", R.drawable.czech));
        spinnerDataList.add(new SpinnerModel("DKK", "Danish krone", R.drawable.denmark));
        spinnerDataList.add(new SpinnerModel("CHF", "Swiss franc", R.drawable.swiss));
        spinnerDataList.add(new SpinnerModel("ARS", "Argentine peso", R.drawable.argentine));
        spinnerDataList.add(new SpinnerModel("NOK", "Norwegian krone", R.drawable.norway));
        spinnerDataList.add(new SpinnerModel("THB", "Thai baht", R.drawable.thiland));
        spinnerDataList.add(new SpinnerModel("HUF", "Hungarien forient", R.drawable.hungry));
        spinnerDataList.add(new SpinnerModel("MXN", "Mexican peso", R.drawable.mexico));
        spinnerDataList.add(new SpinnerModel("COP", "Colombian peso", R.drawable.colombia));
        spinnerDataList.add(new SpinnerModel("RON", "Romanian leu", R.drawable.romanian));
        spinnerDataList.add(new SpinnerModel("UAH", "Ukrainian hryvnia", R.drawable.ukraine));
        spinnerDataList.add(new SpinnerModel("BGN", "Bulgarian Ley", R.drawable.bulgrian));
        spinnerDataList.add(new SpinnerModel("MYR", "Malaysian ringgit", R.drawable.malaysia));
        spinnerDataList.add(new SpinnerModel("NZD", "New ZeaLand dollar", R.drawable.newzealand));
        spinnerDataList.add(new SpinnerModel("IDR", "Indonesian rupiah", R.drawable.indonesia));
        spinnerDataList.add(new SpinnerModel("VND", "Vietnamese dong", R.drawable.vietnam));
        spinnerDataList.add(new SpinnerModel("CLP", "Chilean Peso", R.drawable.chile));
        spinnerDataList.add(new SpinnerModel("SGD", "Singapore dollar", R.drawable.singapore));
        spinnerDataList.add(new SpinnerModel("TWD", "Taiwan Dollar", R.drawable.taiwan));
        spinnerDataList.add(new SpinnerModel("AZN", "Azerbaijani manat", R.drawable.azerbaijan));
        spinnerDataList.add(new SpinnerModel("PEN", "Peru sol", R.drawable.peru));
        spinnerDataList.add(new SpinnerModel("LRD", "Liberian dollar", R.drawable.liberian));
        spinnerDataList.add(new SpinnerModel("BYR", "Belarusia ruble", R.drawable.belarus));
        spinnerDataList.add(new SpinnerModel("AMD", "Armenian dram", R.drawable.armenia));
        spinnerDataList.add(new SpinnerModel("GEL", "Georgian lari", R.drawable.georgia));
        spinnerDataList.add(new SpinnerModel("HRK", "Croatian kuna", R.drawable.croatia));
        spinnerDataList.add(new SpinnerModel("JMD", "Jamaican dollar", R.drawable.jamaica));
        spinnerDataList.add(new SpinnerModel("PHP", "Philippine peso", R.drawable.philippines));
        spinnerDataList.add(new SpinnerModel("KZT", "Kazakhstani tenge", R.drawable.kazakhstan));
        spinnerDataList.add(new SpinnerModel("BDT", "Bangladeshi taka", R.drawable.bangladesh));
        spinnerDataList.add(new SpinnerModel("ALL", "Albanian lek", R.drawable.albania));
        spinnerDataList.add(new SpinnerModel("ISK", "Icelandic krona", R.drawable.iceland));
        spinnerDataList.add(new SpinnerModel("TND", "Tunisian dinar", R.drawable.tunisia));
        spinnerDataList.add(new SpinnerModel("CRC", "Costarican colon", R.drawable.costarica));
        spinnerDataList.add(new SpinnerModel("UYU", "Uruguayan peso", R.drawable.uruguay));
        spinnerDataList.add(new SpinnerModel("DOP", "Dominican peso", R.drawable.dominica));
        spinnerDataList.add(new SpinnerModel("MAD", "Moroccan dirham", R.drawable.morocco));
        spinnerDataList.add(new SpinnerModel("RSD", "Serbian dinar", R.drawable.serbia));
        spinnerDataList.add(new SpinnerModel("NAD", "Nambian Dollar", R.drawable.namibia));
        spinnerDataList.add(new SpinnerModel("VEF", "Venezuelan bolivar", R.drawable.venezuela));
        spinnerDataList.add(new SpinnerModel("SYP", "Syrian pound", R.drawable.syria));
        spinnerDataList.add(new SpinnerModel("NIO", "Nicaraguan cordoba", R.drawable.nicaragua));
        spinnerDataList.add(new SpinnerModel("SDG", "Sudanese pound", R.drawable.sudan));
        spinnerDataList.add(new SpinnerModel("GTQ", "Guatemalan quetzel", R.drawable.guatemala));
        spinnerDataList.add(new SpinnerModel("DZD", "Algerian dinar", R.drawable.algeria));
        spinnerDataList.add(new SpinnerModel("MKD", "Macedonian dinar", R.drawable.macedonia));
        spinnerDataList.add(new SpinnerModel("BOB", "Boliviano", R.drawable.bolivia));
        spinnerDataList.add(new SpinnerModel("TTD", "Tobago dollar", R.drawable.tobago));
        spinnerDataList.add(new SpinnerModel("UZS", "Uzbekistani solm", R.drawable.uzbekistan));
        spinnerDataList.add(new SpinnerModel("JOD", "Jordanian dinar", R.drawable.jordan));
        spinnerDataList.add(new SpinnerModel("KHR", "Cambodian riel", R.drawable.cambodia));
        spinnerDataList.add(new SpinnerModel("KPW", "North Korean won", R.drawable.northkorea));
        spinnerDataList.add(new SpinnerModel("KES", "Kenyan shilling", R.drawable.kenya));
        spinnerDataList.add(new SpinnerModel("KWD", "Kuwait dinar", R.drawable.kuwait));
        spinnerDataList.add(new SpinnerModel("LBP", "Lebanese pound", R.drawable.lebanon));
        spinnerDataList.add(new SpinnerModel("BND", "Brunei dollar", R.drawable.brunei));
        spinnerDataList.add(new SpinnerModel("TZS", "Tanzanian shilling", R.drawable.tanzania));
        spinnerDataList.add(new SpinnerModel("BAM", "Bosnian mark", R.drawable.bosnia));
        spinnerDataList.add(new SpinnerModel("XOF", "West African franc", R.drawable.centralafrican));
        spinnerDataList.add(new SpinnerModel("PYG", "Paraguayan guarni", R.drawable.paraguay));
        spinnerDataList.add(new SpinnerModel("HTG", "Haitian gourde", R.drawable.haiti));
        spinnerDataList.add(new SpinnerModel("YER", "Yemeni rial", R.drawable.yemen));
        spinnerDataList.add(new SpinnerModel("KGS", "Kyrgyzstani som", R.drawable.kyrgyzstan));
        spinnerDataList.add(new SpinnerModel("UGX", "Uganda shilling", R.drawable.uganda));
        spinnerDataList.add(new SpinnerModel("NGN", "Nigerian naira", R.drawable.nigeria));
        spinnerDataList.add(new SpinnerModel("NPR", "Nepalese rupee", R.drawable.nepal));
        spinnerDataList.add(new SpinnerModel("RWF", "Rwando franc", R.drawable.rwanda));
        spinnerDataList.add(new SpinnerModel("MDN", "Moldovan leu", R.drawable.moldova));
        spinnerDataList.add(new SpinnerModel("GNF", "Guinean franc", R.drawable.guinean));
        spinnerDataList.add(new SpinnerModel("MNT", "Mongolian togrog", R.drawable.mongolia));
        spinnerDataList.add(new SpinnerModel("LAK", "Lao Kip", R.drawable.laos));
        spinnerDataList.add(new SpinnerModel("DOP", "Dominican peso", R.drawable.dominica));
        spinnerDataList.add(new SpinnerModel("MMK", "Myanmar kyat", R.drawable.myanmar));
        spinnerDataList.add(new SpinnerModel("PAB", "Panamanian balboa", R.drawable.panama));
        spinnerDataList.add(new SpinnerModel("CUP", "Cubian peso", R.drawable.cuba));
        spinnerDataList.add(new SpinnerModel("TOP", "Tonga", R.drawable.tonga));
        spinnerDataList.add(new SpinnerModel("ZMK", "Zambian kwacha", R.drawable.zambia));
        spinnerDataList.add(new SpinnerModel("MZN", "Mozambique", R.drawable.mozambique));
        spinnerDataList.add(new SpinnerModel("BWP", "Bostwana pule", R.drawable.botswana));
        spinnerDataList.add(new SpinnerModel("LYD", "Libyan dinar", R.drawable.libya));
        spinnerDataList.add(new SpinnerModel("ERN", "Eritrean nakfa", R.drawable.eritrea));
        spinnerDataList.add(new SpinnerModel("OMR", "Omani rial", R.drawable.oman));
        spinnerDataList.add(new SpinnerModel("GHS", "Ghanaian cedi", R.drawable.ghana));
        spinnerDataList.add(new SpinnerModel("HNL", "Honduran lempira", R.drawable.honduras));
        spinnerDataList.add(new SpinnerModel("SRD", "Surinamese dollar", R.drawable.suriname));
        spinnerDataList.add(new SpinnerModel("GYD", "Guyanese dollar", R.drawable.guyana));
        spinnerDataList.add(new SpinnerModel("WST", "Samoan tala", R.drawable.samoa));
        spinnerDataList.add(new SpinnerModel("MRO", "Mauritanian ouguiya", R.drawable.mauritania));
        spinnerDataList.add(new SpinnerModel("TMT", "Turkmanistan manat", R.drawable.turkmenistan));
        spinnerDataList.add(new SpinnerModel("TJS", "Tajikistani somoni", R.drawable.tajikistan));
        spinnerAdapter = new CustomSpinnerAdapter(getActivity(), R.layout.spinner_item, spinnerDataList, res);
        from.setAdapter(spinnerAdapter);
        to.setAdapter(spinnerAdapter);
        to.setSelection(1);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView countryName = (TextView) view.findViewById(R.id.currency_type);
                countryName.setVisibility(View.GONE);
                if (!edtFrom.getText().toString().equals("")) {
                    SpinnerModel modelFrom = (SpinnerModel) from.getSelectedItem();
                    SpinnerModel modelTo = (SpinnerModel) to.getSelectedItem();
                    String fromString = modelFrom.getCurrencyCode();
                    String toString = modelTo.getCurrencyCode();
                    queryingSql(fromString, toString);
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView countryName = (TextView) view.findViewById(R.id.currency_type);
                countryName.setVisibility(View.GONE);
                if (!edtFrom.getText().toString().equals("")) {
                    SpinnerModel modelFrom = (SpinnerModel) from.getSelectedItem();
                    SpinnerModel modelTo = (SpinnerModel) to.getSelectedItem();
                    String fromString = modelFrom.getCurrencyCode();
                    String toString = modelTo.getCurrencyCode();
                    queryingSql(fromString, toString);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AutofitHelper.create(edtFrom);
        AutofitHelper.create(resulTex);

        edtFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {

                    SpinnerModel modelFrom = (SpinnerModel) from.getSelectedItem();
                    SpinnerModel modelTo = (SpinnerModel) to.getSelectedItem();
                    String fromString = modelFrom.getCurrencyCode();
                    String toString = modelTo.getCurrencyCode();
                    queryingSql(fromString, toString);
                } else {
                    resulTex.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        invert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = from.getSelectedItemPosition();
                from.setSelection(to.getSelectedItemPosition());
                to.setSelection(index);
            }
        });
    }

    //checking current data in database
    public void checkDataOfSqlite() {
        String[] projection = {Contract.CurrencyEntery.COLUMN_CURRECNY_RATE};
        String selection = Contract.CurrencyEntery._ID + "='" + 1 + "';";
        Cursor cursor = getContext().getContentResolver().query(Contract.CurrencyEntery.CONTENT_URI, projection, selection, null, null);
        if (cursor.moveToFirst()) {
            String rate = cursor.getString(cursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE));
            if (rate.equals("1")) {
                // Utils.networkCall(getActivity(),mAuthProgressDialog);
                Utils.newNetworkCall(getActivity(), mAuthProgressDialog);
            }
        } else {
            Utils.placeHolderDataSql(getActivity());
        }
    }

    public void queryingSql(String fromString, String toString) {

        String[] projection = {Contract.CurrencyEntery.COLUMN_CURRECNY_RATE};
        String selectionFrom = Contract.CurrencyEntery.COLUMN_CURRENCY_CODE + "='" + fromString + "';";
        Cursor fromCursor = getContext().getContentResolver().query(Contract.CurrencyEntery.CONTENT_URI, projection, selectionFrom, null, null);
        if (fromCursor.moveToFirst()) {
            fromDouble = Double.parseDouble(fromCursor.getString(fromCursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE)));
        }
        String selectionTo = Contract.CurrencyEntery.COLUMN_CURRENCY_CODE + "='" + toString + "';";
        Cursor toCursor = getContext().getContentResolver().query(Contract.CurrencyEntery.CONTENT_URI, projection, selectionTo, null, null);
        if (toCursor.moveToFirst()) {
            toDouble = Double.parseDouble(toCursor.getString(toCursor.getColumnIndex(Contract.CurrencyEntery.COLUMN_CURRECNY_RATE)));
        }
        giveDouble = Double.parseDouble(edtFrom.getText().toString());
        result = (toDouble / fromDouble) * giveDouble;
        String decimalFormat = new DecimalFormat("#.####").format(result);
        resulTex.setText("" + decimalFormat);

    }

    public void intiComponent() {
        from = (Spinner) findViewById(R.id.spinner_from);
        to = (Spinner) findViewById(R.id.spinner_to);
        edtFrom = (EditText) findViewById(R.id.edt_from);
        resulTex = (TextView) findViewById(R.id.txt_converted);
        spinnerDataList = new ArrayList<SpinnerModel>();
        invert = (ImageView) findViewById(R.id.image_view);

        mAuthProgressDialog = new ProgressDialog(getActivity());
        mAuthProgressDialog.setMessage("getting data please wait...");
        mAuthProgressDialog.setCancelable(false);
    }

}
