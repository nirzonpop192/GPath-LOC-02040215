package com.siddiquinoor.restclient.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.AssignDataModelAdapter;
import com.siddiquinoor.restclient.views.adapters.MemberSearchAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;


import java.util.ArrayList;
import java.util.List;

public class MemberSearchPage extends BaseActivity {


    private static final String TAG = "MemberSearchPage";
    private SQLiteHandler sqlH;
    private Spinner spVillage;
    private String idVillage;
    private String strVillage;
    private String idCountry;
    private static ProgressDialog pDialog;
    private ListView listOfMember;
    private MemberSearchAdapter adapter;

    private String idDistrictC;
    private String idUpazilaC;
    private String idUnitC;
    private String idVillageC;

    private Button btnHome;

    private Button btn_searchMember;
    private EditText edt_memberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_search_page);
        initialize();

        Intent intent = getIntent();
        idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
        loadLayRList(idCountry);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(MemberSearchPage.this, MainActivity.class);
                startActivity(iHome);
            }
        });

        btn_searchMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idMember=edt_memberId.getText().toString();
                if (idMember.length()>0){

                    LoadListView loading = new LoadListView(idCountry, idDistrictC, idUpazilaC, idUnitC, idVillageC,idMember);
                    loading.execute();
                }
            }
        });
    }

    private void initialize() {
        sqlH = new SQLiteHandler(MemberSearchPage.this);

        viewReference();


    }

    /**
     * Refere the XML views with java object
     */
    private void viewReference() {
        spVillage = (Spinner) findViewById(R.id.search_mem_spVillage);
        listOfMember = (ListView) findViewById(R.id.lv_mem_search);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
     Button   btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnSummary.setVisibility(View.GONE);

        btn_searchMember= (Button) findViewById(R.id.btn_memberSearch);
        edt_memberId= (EditText) findViewById(R.id.edt_memberSearch);
        addIconHomeButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(380, 5, 380, 5);
    }
    /**
     * @param cCode Country Code
     */
    private void loadLayRList(final String cCode) {
        int position = 0;
        //  String criteria=" WHERE "+SQLiteHandler.COUNTRY_CODE_COL +" = '"+idcCode+"' ";
        String criteria = " AS v   INNER JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " as S "
                + " ON S." + SQLiteHandler.COUNTRY_CODE_COL

                + " ||''|| S." + SQLiteHandler.DISTRICT_CODE_COL
                + " ||''|| S." + SQLiteHandler.UPCODE_COL
                + " ||''|| S." + SQLiteHandler.UCODE_COL
                + " ||''|| S." + SQLiteHandler.VCODE_COL

                + "  = v." + SQLiteHandler.COUNTRY_CODE_COL
                + " ||''|| v." + SQLiteHandler.DISTRICT_CODE_COL
                + " ||''|| v." + SQLiteHandler.UPCODE_COL
                + " ||''|| v." + SQLiteHandler.UCODE_COL
                + " ||''|| v." + SQLiteHandler.VCODE_COL + " ";

        List<SpinnerHelper> listVillage = sqlH.getListAndID(SQLiteHandler.VILLAGE_TABLE_FOR_ASSIGN, criteria, cCode, false);

        // Creating adapter for spinner
        final ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listVillage);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spVillage.setAdapter(dataAdapter);
        //dataAdapter.notifyDataSetChanged();
        if (idVillage != null) {
            for (int i = 0; i < spVillage.getCount(); i++) {
                String village = spVillage.getItemAtPosition(i).toString();
                if (village.equals(strVillage)) {
                    position = i;
                }
            }
            spVillage.setSelection(position);
        }

        spVillage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getValue();
                idVillage = ((SpinnerHelper) spVillage.getSelectedItem()).getId();
                Log.d(TAG, "village id :" + idVillage);
                if (Integer.parseInt(idVillage) > 0) {
                    // after the village is loaded the search button is enable


                    String countryCode = idVillage.substring(0, 4);
                     idDistrictC      = idVillage.substring(4, 6);
                     idUpazilaC       = idVillage.substring(6, 8);
                     idUnitC          = idVillage.substring(8, 10);
                     idVillageC       = idVillage.substring(10);


                    LoadListView loading = new LoadListView(countryCode, idDistrictC, idUpazilaC, idUnitC, idVillageC,"");
                    loading.execute();


                } else {
                    adapter = new MemberSearchAdapter();
                    adapter.notifyDataSetChanged();
                    listOfMember.setAdapter(adapter);
                  /*  if (pDialog.isShowing()){
                        pDialog.dismiss();
                    }*/
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }// end of the village spinner

    private class LoadListView extends AsyncTask<Void, Integer, String> {

        // private String mMemberIdS;// for member id search
        private String temCCode;
        private String temDistCode;
        private String temUpazilaCode;
        private String temUnitCode;
        private String temVillageCode;
        private String memId;


        public LoadListView(final String temCCode, final String temDistCode, final String temUpazilaCode, final String temUnitCode, final String temVillageCode ,final String memId) {
            /*this.mMemberIdS = mMemberIdS;*/
            this.temCCode = temCCode;
            this.temDistCode = temDistCode;
            this.temUpazilaCode = temUpazilaCode;
            this.temUnitCode = temUnitCode;
            this.temVillageCode = temVillageCode;
            this.memId = memId;

        }

        @Override
        protected String doInBackground(Void... params) {


            loadAssignedListData(temCCode, temDistCode, temUpazilaCode, temUnitCode, temVillageCode,memId);


            return "successes";


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startProgressBar("Data is Loading");

        }


        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (adapter != null) {

                adapter.notifyDataSetChanged();
                listOfMember.setAdapter(adapter);

                listOfMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                listOfMember.setFocusableInTouchMode(true);

            } else {
                Log.d(TAG, "Adapter Is Empety ");

            }

        }
    }

    private void startProgressBar(String msg) {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        pDialog.show();
    }


    private void loadAssignedListData(final String cCode,final String dCode, final String upCode,final String uCode, final String vCode,final String memId) { // mwmSId = memeber searchin variable

        List<AssignDataModel> memberList = sqlH.getMemberList(cCode, dCode, upCode, uCode, vCode,memId);


        ArrayList<AssignDataModel> assignedArray = new ArrayList<AssignDataModel>();
        if (memberList.size() != 0) {
            assignedArray.clear();
            for (AssignDataModel asdata : memberList) {
                // add contacts data in arrayList

                assignedArray.add(asdata);
            }





            adapter = new MemberSearchAdapter((Activity) MemberSearchPage.this, assignedArray);
        }
    }


}
