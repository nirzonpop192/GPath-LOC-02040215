package com.siddiquinoor.restclient.activity;
/**
 * This class is for Graduate the member
 * @author: Faisal Mohammad
 * @virsion:
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.GraduationGridAdapter;
import com.siddiquinoor.restclient.views.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;


public class GraduationActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private final String TAG=GraduationActivity.class.getName();

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


    private Button btn_Home;
    private Button btn_sammary;
    private GraduationGridAdapter adapter;
    private String idDonor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_graduation);

        mContext =GraduationActivity.this;
        sqlH=new SQLiteHandler(this);
        Intent intent=getIntent();

        idCountry=intent.getStringExtra(KEY.COUNTRY_ID);
        strCounty=intent.getStringExtra(KEY.STR_COUNTRY);

        referenceViewId();
        loadAward(idCountry);

        edt_searchId.requestFocus();
        InputMethodManager imm=(InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(edt_searchId, InputMethodManager.SHOW_IMPLICIT);


    }

    private void referenceViewId() {

        spAward= (Spinner) findViewById(R.id.sp_Award_Graduation);
        spProgram= (Spinner) findViewById(R.id.sp_Program_Graduation);
        spCriteria= (Spinner) findViewById(R.id.sp_Criteria_Graduation);

        btn_Home= (Button) findViewById(R.id.btnHomeFooter);
        btn_Home.setOnClickListener(this);
        btn_sammary= (Button) findViewById(R.id.btnRegisterFooter);
        btn_sammary.setText("Summary");
        btn_sammary.setOnClickListener(this);
        lv_garduation= (ListView) findViewById(R.id.lv_graduationList);
        edt_searchId= (EditText) findViewById(R.id.edt_searchId);
        btnSearchId = (Button) findViewById(R.id.btn_Graduation_SearchID);
        btnSearchId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHomeFooter:
              /*  Intent iHome=new Intent(mContext, MainActivity.class);
                startActivity(iHome);*/
                goToMainActivity((Activity)mContext);

                break;
            case R.id.btnRegisterFooter:
//                startActivity(new Intent(mContext, AllSummaryActivity.class));
                goToSummaryActivity((Activity) mContext, idCountry);
                break;
            case R.id.btn_Graduation_SearchID:
                String memberId=edt_searchId.getText().toString();
                loadGraduationGrid(idCountry, idDonor, idAward, idProgram, idService, memberId);
               // Toast.makeText(mContext,"search",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    /**
     * LOAD :: Award
     */
    private void loadAward(final String cCode){

        int position=0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE +"."+ SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_AWARD_TABLE, criteria, null,false);

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
            spAward.setSelection( position );
        }



        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                idAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();


                if (idAward.length()>2){
                         idDonor = idAward.substring(0, 2);
                         idAward = idAward.substring( 2);
                        loadProgram(idAward, idDonor, cCode);
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
    private void loadProgram (final String awardCode, final String donorCode, final String idcCode){

        int position=0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                +" AND " +SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'" ;
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null,false);

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
            spProgram.setSelection( position );
        }



        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram   =  ( (SpinnerHelper) spProgram.getSelectedItem () ).getValue();
                idProgram   = ( (SpinnerHelper) spProgram.getSelectedItem () ).getId();

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
    private void loadCriteria(final String awardCode, final String donorCode, final String programCode, final String cCode){

        int position=0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                +" AND " +SQLiteHandler.COUNTRY_PROGRAM_TABLE  +"."+ SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                +" AND " +SQLiteHandler.COUNTRY_PROGRAM_TABLE +"."+ SQLiteHandler.PROGRAM_CODE_COL + "='" + programCode + "'" ;
        // Spinner Drop down elements for District
        List<SpinnerHelper> listCriteria = sqlH.getListAndID(SQLiteHandler.SERVICE_MASTER_TABLE, criteria, null,false);

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
            spCriteria.setSelection( position );
        }



        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria   =  ( (SpinnerHelper) spCriteria.getSelectedItem () ).getValue();
                idCriteria    = ( (SpinnerHelper) spCriteria.getSelectedItem () ).getId();


                if(Integer.parseInt(idCriteria)>0) {
                    idService=idCriteria;
                  loadGraduationGrid(cCode,donorCode,awardCode,programCode,idService,"");
                }


                Log.d(TAG, "load idCriteria data " + idCriteria);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * This method load the list of GraduatedPeople
     * @param countryCode
     * @param donorCode
     * @param awardCode
     * @param programCode
     * @param serviceCode
     * @param memid
     */

    public void loadGraduationGrid(String countryCode, String donorCode, String awardCode,String programCode,
                                    String serviceCode,String memid)
    {
        Log.d(TAG, "In loadGraduationGrid List ");
        // use veriable to like operation

        ArrayList<GraduationGridDataModel> graduationList =
                sqlH.getGRDGridList(countryCode, programCode, serviceCode, donorCode, awardCode, memid);

        adapter = new GraduationGridAdapter(graduationList,this,strAward,strProgram,strCriteria,awardCode,programCode,donorCode,serviceCode);
        adapter.notifyDataSetChanged();
        lv_garduation.setAdapter(adapter);
        lv_garduation.setOnItemClickListener(this);
        lv_garduation.setFocusableInTouchMode(true);
        //hidePDialog();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /** back press button is disable */
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }


}
