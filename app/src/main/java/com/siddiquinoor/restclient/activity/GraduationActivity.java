package com.siddiquinoor.restclient.activity;
/**
 * This class is for Graduate the member
 *
 * @author: Faisal Mohammad
 * @virsion:
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.CalculationPadding;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.GraduationGridAdapter;
import com.siddiquinoor.restclient.views.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;


public class GraduationActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private final String TAG = GraduationActivity.class.getName();

    private String idCountry;
    private String strCounty;
    private String idAward;
    private String strAward;

    private String idProgram;
    private String strProgram;
    private String idCriteria;
    private String idService;
    private String strCriteria;

    private Spinner spAward;
    private Spinner spProgram;
    private Spinner spCriteria;

    private Context mContext;
    private SQLiteHandler sqlH;
    private ListView lv_garduation;
    private EditText edt_searchId;
    private Button btnSearchId;


    private Button btnHome;
    private Button btnSummary;
    private GraduationGridAdapter adapter;
    private String idDonor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graduation);

        initial();


        Intent intent = getIntent();

        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        if (dir.equals("MemberSearchPage")) {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            String memberId = intent.getStringExtra(KEY.MEMBER_ID);
            if (memberId.length() > 5) {
                edt_searchId.setText(memberId);
                loadAward(idCountry, memberId);
            }


        } else {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            strCounty = intent.getStringExtra(KEY.STR_COUNTRY);

            loadAward(idCountry, "");

        }


        Log.d(TAG, "id Country : id " + idCountry);

//        loadAward(idCountry);


        setAllListener();

    }

    private void initial() {
        mContext = GraduationActivity.this;
        sqlH = new SQLiteHandler(this);
        viewReference();
    }


    private void setAllListener() {
        btnSearchId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memberId = edt_searchId.getText().toString();
                loadGraduationGrid(idCountry, idDonor, idAward, idProgram, idService, memberId);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity) mContext);
            }
        });
        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryActivity((Activity) mContext, idCountry);
            }
        });
    }

    private void viewReference() {
        spAward = (Spinner) findViewById(R.id.sp_Award_Graduation);
        spProgram = (Spinner) findViewById(R.id.sp_Program_Graduation);
        spCriteria = (Spinner) findViewById(R.id.sp_Criteria_Graduation);

        btnHome = (Button) findViewById(R.id.btnHomeFooter);

        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);


        lv_garduation = (ListView) findViewById(R.id.lv_graduationList);
        edt_searchId = (EditText) findViewById(R.id.edt_searchId);
        btnSearchId = (Button) findViewById(R.id.btn_Graduation_SearchID);

    }


    /**
     * LOAD :: Award
     */
    private void loadAward(final String cCode, final String memId) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_AWARD_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spAward.setAdapter(dataAdapter);


        if (idAward != null) {
            for (int i = 0; i < spAward.getCount(); i++) {
                String award = spAward.getItemAtPosition(i).toString();
                if (award.equals(strAward)) {
                    position = i;
                }
            }
            spAward.setSelection(position);
        }


        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                idAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();


                if (idAward.length() > 2) {
                    idDonor = idAward.substring(0, 2);
                    idAward = idAward.substring(2);
                    loadProgram(cCode, idDonor, idAward, memId);
                }
                Log.d(TAG, "idAward : " + idAward.substring(2) + " donor id :" + idAward.substring(0, 2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Program
     */
    private void loadProgram(final String idcCode, final String donorCode, final String awardCode, final String memId) {

        int position = 0;
        String criteria = "SELECT " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " , " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_SHORT_NAME_COL
                + " FROM " + SQLiteHandler.PROGRAM_MASTER_TABLE
                + " INNER JOIN " + SQLiteHandler.ADM_AWARD_TABLE
                + " ON " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " INNER JOIN " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss "
                + " ON regAss." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " WHERE " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.PROGRAM_MASTER_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND regAss." + SQLiteHandler.DISTRICT_CODE_COL
                + " || '' || regAss." + SQLiteHandler.UPCODE_COL
                + " || '' || regAss." + SQLiteHandler.UCODE_COL
                + " || '' || regAss." + SQLiteHandler.VCODE_COL
                + " || '' || regAss." + SQLiteHandler.HHID_COL
                + " || '' || regAss." + SQLiteHandler.HH_MEM_ID

                + " = '" + memId + "'";


        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spProgram.setAdapter(dataAdapter);


        if (idProgram != null) {
            for (int i = 0; i < spProgram.getCount(); i++) {
                String prog = spProgram.getItemAtPosition(i).toString();
                if (prog.equals(strProgram)) {
                    position = i;
                }
            }
            spProgram.setSelection(position);
        }


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();

                Log.d(TAG, "load Prog data " + idProgram);

                loadCriteria(awardCode, donorCode, idProgram, idcCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Criteria
     */
    private void loadCriteria(final String awardCode, final String donorCode, final String programCode, final String cCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + "='" + programCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.SERVICE_MASTER_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listCriteria);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCriteria.setAdapter(dataAdapter);


        if (idCriteria != null) {
            for (int i = 0; i < spCriteria.getCount(); i++) {
                String award = spCriteria.getItemAtPosition(i).toString();
                if (award.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }


        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getId();

                if (Integer.parseInt(idCriteria) > 0) {
                    idService = idCriteria;
                    /**
                     * safety Block
                     */
                    if (edt_searchId.getText().toString().length() > 5)
                        loadGraduationGrid(cCode, donorCode, awardCode, programCode, idService, edt_searchId.getText().toString());
                    else
                        loadGraduationGrid(cCode, donorCode, awardCode, programCode, idService, "");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Grid
     * This method load the list of GraduatedPeople
     *
     * @param countryCode Country Code
     * @param donorCode   Donor Code
     * @param awardCode   Award Code
     * @param programCode Program Code
     * @param serviceCode Service Code
     * @param memid       Member id
     */

    public void loadGraduationGrid(String countryCode, String donorCode, String awardCode, String programCode,
                                   String serviceCode, String memid) {
        Log.d(TAG, "In loadGraduationGrid List ");
        // use variable to like operation

        ArrayList<GraduationGridDataModel> graduationList = sqlH.getMemberGraduationStatusList(countryCode, donorCode, awardCode, programCode, serviceCode, memid);

        adapter = new GraduationGridAdapter(graduationList, this, strAward, strProgram, strCriteria, awardCode, programCode, donorCode, serviceCode);
        adapter.notifyDataSetChanged();
        lv_garduation.setAdapter(adapter);
        lv_garduation.setOnItemClickListener(this);
        lv_garduation.setFocusableInTouchMode(true);
        //hidePDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * back press button is disable
     */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setUpSummaryButton();
        setUpHomeButton();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        setPaddingButton(mContext, summeryImage, btnSummary);
    }

    /**
     * Icon set by the method
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);
    }


}
