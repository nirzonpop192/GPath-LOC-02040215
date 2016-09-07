package com.siddiquinoor.restclient.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.agr.AssignAGR;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.ddr.AssignForDDRMalwaiFFA;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.ddr.AssignForDDRMalwaiVUL;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn.AssignCA2;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn.AssignCU2;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn.AssignLM;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn.AssignPW;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.peer.AssignForLiberiaAIV;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.peer.AssignForLiberiaCFW;
import com.siddiquinoor.restclient.activity.sub_activity.assign_program.peer.AssignForLiberiaUCT;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.AssignDataModelAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AssignActivity extends BaseActivity {



    private static final String TAG = OldAssignActivity.class.getName();
    AlertDialog goToDialog;
    Intent intent;
    public static final String ID_COUNTRY = "ID_COUNTRY";
    public static final String ASSIGN_AWARD_CODE = "ASSIGN_AWARD_CODE";
    public static final String ASSIGN_AWARD_STR = "ASSIGN_AWARD_STR";
    public static final String ASSIGN_DONOR_CODE = "ASSIGN_DONOR_CODE";
    public static final String ASSIGN_PROGRAM_CODE = "ASSIGN_PROGRAM_CODE";
    public static final String ASSIGN_PROGRAM_STR = "ASSIGN_PROGRAM_STR";
    public static final String ASSIGN_CRITERIA_CODE = "ASSIGN_CRITERIA_CODE";
    public static final String ASSIGN_CRITERIA_STR = "ASSIGN_CRITERIA_STR";
    public static final String ASSIGN_VILLAGE_CODE = "ASSIGN_VILLAGE_CODE";
    public static final String ASSIGN_VILLAGE_STR = "ASSIGN_VILLAGE_STR";
    public static final String ASSIGN_DISTRICT_CODE = "ASSIGN_DISTRICT_CODE";
    public static final String ASSIGN_UPZELA_CODE = "ASSIGN_UPZELA_CODE";
    public static final String ASSIGN_UNIT_CODE = "ASSIGN_UNIT_CODE";
    public static final String SUB_ASSIGN_DIR = "Sub_Ass_dir";
    private SQLiteHandler sqlH;
    private Context mcontext;

    private String idCountry;
    private String strAward;
    private String idAward;
    private Spinner spAward;
    private Spinner spProgram;
    private String idProgram;
    private String strProgram;

    private Spinner spCriteria;
    private String idCriteria;
    private String strCriteria;
    //private Spinner spVillage;
    private String idVillage;
    private String strVillage;

    private EditText edt_mmSearch;
    String entryBy;
    String entryDate;


    private String idDistrictC;
    private String idUpazilaC;
    private String idUnitC;
    private String idVillageC;
    private String idService;
    private String idDonor;
    private ArrayList<AssignDataModel> assignedArray = new ArrayList<AssignDataModel>();
    ListView listViewAss;
    private AssignDataModelAdapter adapter;

    private static ProgressDialog pDialog;
    ADNotificationManager dialog;

    /**
     * fotter button important
     */
    private Button btnGoTo;
    private Button btnSummary;

    private boolean mredirection;
    //private TextView tvLayR4Lable;
    private String idHH;
    private String idMember;


    // donor code name
    private static final String USAID = "01";
    private static final String FFP = "03";
    private static final String OFDA = "02";

    // award code name
    private static final String NJIRA = "01";
    private static final String PEER = "10";
    private static final String LAUNC = "09";
    private static final String EC3P = "06";
    private static final String EBOLA = "07";
    private static final String RAPID = "08";

    // program code name
    private static final String MCHN = "001";
    private static final String UCT = "008";
    private static final String AIV = "009";
    private static final String CFW = "010";
    private static final String EC3 = "004";
    private static final String ETU = "005";
    private static final String REACT = "006";
    private static final String AGRP = "003";

    //service code
    private static final String PREGNANT_WOMEN = "01";
    private static final String LACTATING_MOTHER = "02";
    private static final String CHILDEN_UNDER_2 = "03";
    private static final String CHILD_ABOVE_2 = "04";

    private static final String EVD_C1 = "01";
    private static final String CFED_C2 = "02";
    private static final String PLW_C3 = "03";
    private static final String MS = "01";
    private static final String MTS = "01";
    private static final String MA = "01";

    private static final String AGR = "01";
    private static final String PG = "03";
    private static final String IG = "04";
    private static final String LG = "05";
    private static final String MG = "06";

    private static final String DDR = "002";
    private static final String VUL = "01";
    private static final String FFA = "02";

    private String memberId15D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign);
        mcontext = AssignActivity.this;
        dialog = new ADNotificationManager();

        viewReference();


        buttonListener();


    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "in on Start method ");


        Intent intent = getIntent();
        boolean redirection;

        redirection = intent.getBooleanExtra(OldAssignActivity.SUB_ASSIGN_DIR, false);
        mredirection = redirection;
        // if it Comes from Sub page Of
        if (redirection) {
            // // TODO: it commes from Summay page than it should  go differet algorith


            strAward = intent.getStringExtra(ASSIGN_AWARD_STR);
            strProgram = intent.getStringExtra(ASSIGN_PROGRAM_STR);
            strCriteria = intent.getStringExtra(ASSIGN_CRITERIA_STR);
            idCountry = intent.getStringExtra(ID_COUNTRY);
            idAward = intent.getStringExtra(ASSIGN_AWARD_CODE);
            idDonor = intent.getStringExtra(ASSIGN_DONOR_CODE);
            idProgram = intent.getStringExtra(ASSIGN_PROGRAM_CODE);
            idCriteria = intent.getStringExtra(ASSIGN_CRITERIA_CODE);


            memberId15D = intent.getStringExtra(KEY.MEMBER_ID);
            edt_mmSearch.setText(memberId15D);
            if (memberId15D.length() > 7) {
                idDistrictC = memberId15D.substring(0, 2);
                idUpazilaC = memberId15D.substring(2, 4);
                idUnitC = memberId15D.substring(4, 6);
                idVillage = idVillageC = memberId15D.substring(6, 8);
                idHH = memberId15D.substring(8, 13);
                idMember = memberId15D.substring(13);


                loadAward(idCountry);
            }


        } else {
            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            loadAward(idCountry);
            memberId15D = intent.getStringExtra(KEY.MEMBER_ID);
            edt_mmSearch.setText(memberId15D);
            if (memberId15D.length() > 7) {
                idDistrictC = memberId15D.substring(0, 2);
                idUpazilaC = memberId15D.substring(2, 4);
                idUnitC = memberId15D.substring(4, 6);
                idVillage = idVillageC = memberId15D.substring(6, 8);
                idHH = memberId15D.substring(8, 13);
                idMember = memberId15D.substring(13);
            }
            //  String temId = edt_mmSearch.getText().toString().trim();


        }
    }


    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    private void viewReference() {
        spAward = (Spinner) findViewById(R.id.sp_assine_awardList);
        spProgram = (Spinner) findViewById(R.id.spProgram);
        spCriteria = (Spinner) findViewById(R.id.spCriteriaA);


        listViewAss = (ListView) findViewById(R.id.lv_assign);
        // btnSearch = (Button) findViewById(R.id.btn_assign_search);
        edt_mmSearch = (EditText) findViewById(R.id.edt_assign_memberSearch);

        btnGoTo = (Button) findViewById(R.id.btnHomeFooter);


        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        setUpSummaryButton();
        setUpGotoButton();

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.goto_back);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        btnSummary.setPadding(180, 10, 180, 10);
    }

    /**
     * Icon set by the method
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setUpGotoButton() {
        btnGoTo.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_b);
        btnGoTo.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btnGoTo.setPadding(180, 10, 180, 10);
    }


    private void buttonListener() {

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent iMemSearch = new Intent(getApplicationContext(), MemberSearchPage.class);
                iMemSearch.putExtra(KEY.COUNTRY_ID, idCountry);
                finish();
                startActivity(iMemSearch);
            }
        });

        sqlH = new SQLiteHandler(this);

        pDialog = new ProgressDialog(this);


        edt_mmSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSoftKeyboard(edt_mmSearch);
            }
        });

        btnGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // goToAlert();
                if (btnNextValidation())
                    goToSubAssignClass();
            }
        });
    }


    private boolean btnNextValidation() {
// if ((spAward != null && spAward.getSelectedItem() != null) || (spProgram != null && spProgram.getSelectedItem() != null) || (spCriteria != null && spCriteria.getSelectedItem() != null)) {
        if ((idAward == null || strAward == null) || (idProgram.equals("00") || strProgram.equals("Select Program")) || (idCriteria.equals("00") || strCriteria.equals("Select Criteria"))) {
            return false;
        } else {
            return true;
        }
    }

    private void goToSubAssignClass() {

        Intent iSubAssignClass = null;
        // String donorId;
        String awardCode;
        String proCode;
        String srvCode;

        //donorId = assignedArray.get(0).getDonor_code();
        awardCode = assignedArray.get(0).getAward_code();
        proCode = assignedArray.get(0).getProgram_code();
        srvCode = assignedArray.get(0).getService_code();
        AssignDataModel memData = assignedArray.get(0);
        Log.d("SIM", "idDonor :" + idDonor);
        switch (idDonor) {
            case USAID: //DONOR ID USAID OPEN
                switch (awardCode) {
                    case NJIRA://AWARD CODE NJIRA OPEN
                        switch (proCode) {
                            case MCHN: //PROGRAM CODE MCHN OPEN
                                switch (srvCode) {
                                    case PREGNANT_WOMEN:
                                        iSubAssignClass = new Intent(mcontext, AssignPW.class);
                                        break;
                                    case LACTATING_MOTHER:
                                        iSubAssignClass = new Intent(mcontext, AssignLM.class);
                                        break;
                                    case CHILDEN_UNDER_2:
                                        iSubAssignClass = new Intent(mcontext, AssignCU2.class);
                                        break;
                                    case CHILD_ABOVE_2:
                                        iSubAssignClass = new Intent(mcontext, AssignCA2.class);
                                        break;
                                }
                                break;//PROGRAM CODE MCHN CLOSE

                            case DDR://PROGRAM CODE DDR START
                                switch (srvCode) {
                                    case VUL:
                                        iSubAssignClass = new Intent(mcontext, AssignForDDRMalwaiVUL.class);
                                        break;
                                    case FFA:
                                        iSubAssignClass = new Intent(mcontext, AssignForDDRMalwaiFFA.class);
                                        break;
                                }
                                break;//PROGRAM CODE DDR CLOSE

                            case AGRP://program code AGRP OPEN
                                switch (srvCode) {
                                    case AGR:
                                        iSubAssignClass = new Intent(mcontext, AssignAGR.class);
                                        break;
                                    case PG:
                                        iSubAssignClass = new Intent(mcontext, AssignAGR.class);
                                        break;
                                    case IG:
                                        iSubAssignClass = new Intent(mcontext, AssignAGR.class);
                                        break;
                                    case LG:
                                        iSubAssignClass = new Intent(mcontext, AssignAGR.class);
                                        break;
                                    case MG:
                                        iSubAssignClass = new Intent(mcontext, AssignAGR.class);
                                        break;
                                }
                                break;//program code AGRP ClOSE
                        }
                        break;//AWARD CODE NJIRA CLOSE
                }
                break; // DONOR ID USAID CLOSE

            case FFP://DONAR CODE FFP OPEN
                switch (awardCode) {
                    case PEER://AWARD CODE PEER OPEN
                        switch (proCode) {
                            case UCT://PROGRAM CODE UCT OPEN
                                switch (srvCode) {
                                    case EVD_C1:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaUCT.class);
                                        break;
                                    case CFED_C2:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaUCT.class);
                                        break;
                                    case PLW_C3:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaUCT.class);
                                        break;

                                }
                                break;//PROGRAM CODE UCT CLOSE

                            case AIV://program Code AIV Start
                                switch (srvCode) {
                                    case EVD_C1:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaAIV.class);
                                        break;
                                    case CFED_C2:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaAIV.class);
                                        break;
                                    case PLW_C3:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaAIV.class);
                                        break;
                                }
                                break;//program Code AIV CLOSE
                            case CFW://program code CFW start
                                switch (srvCode) {
                                    case EVD_C1:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaCFW.class);
                                        break;
                                    case CFED_C2:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaCFW.class);
                                        break;
                                    case PLW_C3:
                                        iSubAssignClass = new Intent(mcontext, AssignForLiberiaCFW.class);
                                        break;
                                }
                                break;//program code CFW close

                        }
                        break;//AWARD CODE PEER CLOSE
                }

                break;//DONAR CODE FFP CLOSE
            case OFDA://DONAR CODE OFDA OPEN
                switch (awardCode) {
                    case EC3P://AWARD EC3P OPEN
                        switch (proCode) {
                            case EC3://PROGRAM CODE EC3 OPEN
                                switch (srvCode) {
                                    case MS://SERVICE CODE MS OPEN
                                        //TODO: INTENT GOSE HERE WHEN DEVELOPED
                                        break;//SERVICE CODE MS CLOSE
                                }
                                break;//PROGRAM CODE EC3 CLOSE
                        }
                        break;//AWARD EC3P CLOSE
                    case EBOLA:/**AWARD EBOLA START*/
                        switch (proCode) {
                            case ETU://PROGRAM CODE ETU OPEN
                                switch (srvCode) {
                                    case MTS://SERVICE CODE MTS OPEN
                                        //TODO: INTENT GOSE HERE WHEN DEVELOPED
                                        break;//SERVICE CODE MTS CLOSE
                                }
                                break;//PROGRAM CODE ETU CLOSE
                        }
                        break;//AWARD EBOLA CLOSE
                    case RAPID://AWARD CODE RAPID OPEN
                        switch (proCode) {
                            case REACT://PROGRAM CODE REACT OPEN
                                switch (srvCode) {
                                    case MA://SERVICE CODE MA OPEN
                                        //TODO: INTENT GOSE HERE WHEN DEVELOPED
                                        break;//SERVICE CODE MA CLOSE
                                }
                                break;//PROGRAM CODE REACT CLOSE
                        }
                        break;//AWARD CODE RAPID CLOSE
                }

                break;//DONAR CODE OFDA CLOSE
        }


        if (iSubAssignClass != null) {


            iSubAssignClass.putExtra(KEY.PAGE_TITLE, "");
            iSubAssignClass.putExtra(KEY.DIRECTORY, "Assign");
            iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_ID, memData.getHh_id());
            iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_NAME, memData.getHh_name());
            iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_ID, memData.getMemId());
            iSubAssignClass.putExtra(KEY.ASSIGN_HOUSEHOLD_MEMBER_NAME, memData.getHh_mm_name());
            iSubAssignClass.putExtra(KEY.ASSIGN_CRITERIA, memData.getAssign_criteria());
            iSubAssignClass.putExtra(KEY.ASSIGN_COUNTRY_CODE, memData.getCountryCode());
            iSubAssignClass.putExtra(KEY.ASSIGN_DISTRICT_CODE, memData.getDistrictCode());
            iSubAssignClass.putExtra(KEY.ASSIGN_UPOZILLA_CODE, memData.getUpazillaCode());
            iSubAssignClass.putExtra(KEY.ASSIGN_UNIT_CODE, memData.getUnitCode());
            iSubAssignClass.putExtra(KEY.ASSIGN_VILLAGE_CODE, memData.getVillageCode());
      /*omit the code      memData.setTemVillageString(villageStr);
            iSubAssignClass.putExtra(KEY.ASSIGN_VILLAGE_STRING, villageStr);
           */
            iSubAssignClass.putExtra(KEY.ASSIGN_AWARD_CODE, memData.getAward_code());
            memData.setTemAwardString(strAward);
            iSubAssignClass.putExtra(KEY.ASSIGN_AWARD_STRING, strAward);
            iSubAssignClass.putExtra(KEY.ASSIGN_PROGRAM_CODE, memData.getProgram_code());
            memData.setTemProgramString(strProgram);
            iSubAssignClass.putExtra(KEY.ASSIGN_PROGRAM_STRING, strProgram);
            /** service Code is criteria Code **/
            iSubAssignClass.putExtra(KEY.ASSIGN_SERVICE_CODE, memData.getService_code());
            memData.setTemCriteriaString(strCriteria);
            iSubAssignClass.putExtra(KEY.ASSIGN_CRITERIA_STRING, strCriteria);

            iSubAssignClass.putExtra(KEY.ASSIGN_DONER_CODE, memData.getDonor_code());
            iSubAssignClass.putExtra(KEY.ASSIGN_DATA_OBJECT_KEY, assignedArray.get(0));
            iSubAssignClass.putExtra(KEY.MEMBER_ID, memberId15D);

            finish();
            startActivity(iSubAssignClass);
        }
    }


    /**
     * LOAD :: Award
     */
    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + idCountry + "'";
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
                String awardCode = ((SpinnerHelper) spAward.getSelectedItem()).getId();

                // String donorId=idAward.substring(0,1)
                //     if (!mredirection){
                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);
                    loadProgram(idCountry, idAward, idDonor);
                    Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }


            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner


    /**
     * LOAD :: Program
     */
    private void loadProgram(final String cCode, final String awardCode, final String donorCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

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

                Log.d(TAG, "idProgram: " + idProgram + "strProgram: " + strProgram);

                loadCriteria(awardCode, donorCode, idProgram, cCode);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Criteria
     */
    private void loadCriteria(final String idAward, final String donorId, final String idProgram, final String cCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorId + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + "='" + idProgram + "'";
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
                String criteriaN = spCriteria.getItemAtPosition(i).toString();
                if (criteriaN.equals(strCriteria)) {
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

                /**
                 * @confuse : WTF what I have done */
                if (Integer.parseInt(idCriteria) > 0) {
                    //    loadLayRList(cCode, idAward, donorId, idProgram, idCriteria);// idService=idCriteria
                   /* LoadListView loading = new LoadListView(idCountryC, idDistrictC, idUpazilaC, idUnitC, idVillageC, idDonor, idAward, idProgram, idService, "");
                    loading.execute();*/
                    loadAssignedListData(idCountry, idDistrictC, idUpazilaC, idUnitC, idVillage, idDonor, idAward, idProgram, idCriteria, "");


                } else {
                    adapter = new AssignDataModelAdapter();
                    adapter.notifyDataSetChanged();
                    listViewAss.setAdapter(adapter);
                }


                Log.d(TAG, "load idCriteria id: " + idCriteria + " Critrei a name: " + strCriteria);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    private void loadAssignedListData(String cCode, String dCode, String upCode, String uCode, String vCode, String donorCode, String awardCode, String progCode, String srvCode, String memSId) { // mwmSId = memeber searchin variable
        //listViewAss.setAdapter(null);
        List<AssignDataModel> assDatalist = sqlH.getSingleMemberForAssign(cCode, dCode, upCode, uCode, vCode, idHH, idMember, donorCode, awardCode, progCode, srvCode);
        // listViewAss.setAdapter(null);
        if (assDatalist.size() != 0) {
            assignedArray.clear();
            for (AssignDataModel asdata : assDatalist) {
                // add contacts data in arrayList

                assignedArray.add(asdata);
            }


            try {
                entryBy = getStaffID();
                entryDate = getDateTime();
            } catch (ParseException pe) {
                pe.printStackTrace();
            }


            adapter = new AssignDataModelAdapter((Activity) AssignActivity.this, assignedArray, awardCode, strAward, progCode, strProgram, srvCode, idDonor, strCriteria, idCriteria, strCriteria, strVillage, entryBy, entryDate);

            if (adapter != null) {

                adapter.notifyDataSetChanged();
                listViewAss.setAdapter(adapter);

                listViewAss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
            }

        }
    }


    /*public void goToAlert() {
        final CharSequence[] items = getResources().getStringArray(R.array.assign_got_array);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(AssignActivity.this, android.R.style.Theme_Holo_Light_Dialog));

        builder.setTitle("GO TO:");


        builder.setIcon(R.drawable.navigation_icon);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        finish();
                        intent = new Intent(AssignActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        finish();
                        intent = new Intent(AssignActivity.this, DistributionActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AssignActivity");
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 2:
                        finish();
                        intent = new Intent(AssignActivity.this, ServiceActivity.class);
                        intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AssignActivity");
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 3:
                        finish();
                        intent = new Intent(AssignActivity.this, AllSummaryActivity.class);
                        intent.putExtra(KEY.COUNTRY_ID, sqlH.selectCountryCode());
                        startActivity(intent);
                        break;
                    case 4:h heloo world he
                        finish();
                        intent = new Intent(AssignActivity.this, RegisterLiberia.class);
                        startActivity(intent);
                        break;
                }
                goToDialog.dismiss();
            }
        });
        goToDialog = builder.create();
        goToDialog.show();
        int titleDividerId = goToDialog.getContext().getResources().getIdentifier("titleDivider", "id", "android");//("android:id/titleDivider",null,null);
        //   View titleDivider = activityDialog.findViewById(titleDividerId);
        View titleDivider = goToDialog.getWindow().getDecorView().findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(getResources().getColor(R.color.blue));
        }
// setAlertDevider();

    }*/
}
