package com.siddiquinoor.restclient.activity.sub_activity.assign_program.mchn;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.siddiquinoor.restclient.activity.AllSummaryActivity;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.OldAssignActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AssignPW extends BaseActivity {

    public static final int PW_GRD_DAYS_270 = 270;
    private final String TAG = "AssignPW";

    private TextView tv_HhName;

    private TextView tv_MemberName;
    private TextView tv_MemberId;
    private TextView tv_AsCriteria;
    private TextView tv_regDate;
    private TextView tv_LmpDate;

    Button btnSave;

    private SQLiteHandler sqlH;
    private Button btnHome;
    private Button btnSummary;
    private Button btnBackToAssign;
    /**
     * for Showing error Dialog
     */
    private ADNotificationManager erroDialog = new ADNotificationManager();
    /**
     * Context
     */
    private Context mContext;


    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();


    private AssignDataModel assignMem = new AssignDataModel();

    private String memberId15D;


    /**
     * Spinner to Assign  Categories & Group Code
     */
    private Spinner spGroupCategories, spGroup, spActive;
    /**
     * Spinner Id  (Categories & Group& Active)
     */
    private String idGroupCat, idGroup, idActive;
    /**
     * Spinner Vaules Categories & Group
     */
    private String strGroupCat, strGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_assign_pregnant_women);


        mContext = AssignPW.this;
        sqlH = new SQLiteHandler(this);

        viewReference();


        Intent mIntent = getIntent();

        assignMem = mIntent.getExtras().getParcelable(KEY.ASSIGN_DATA_OBJECT_KEY);
        memberId15D = mIntent.getExtras().getString(KEY.MEMBER_ID);
        if (assignMem != null) {
            //   Log.d("SAFA", " getMember_age :" + assignMem.getMember_age());

            String memRegDir = mIntent.getStringExtra(KEY.DIRECTORY);


            setTextToTextViews();


            idGroupCat = assignMem.getGroupCatCode();
            strGroupCat = assignMem.getGroupCatName();
            idGroup = assignMem.getGroupCode();
            strGroup = assignMem.getGroupName();
            idActive = assignMem.getActiveCode();


            loadGroupCategory(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code());

            calculateTheDaysBtnRegDateNLmpDate();

        }


        setListeners();

    }

    private void calculateTheDaysBtnRegDateNLmpDate() {
        String tem = sqlH.getRegDate_RegNAsgProgSrv(assignMem);
        if (tem.equals("null")) {
            tem = "";
        }

        tv_regDate.setText(tem);


        tv_LmpDate.setText(sqlH.getLMPDate_PW(assignMem));


    }

    private void setListeners() {
        tv_regDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRegDate();
            }
        });
        tv_LmpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLmDate();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAssignBeneficiary();
            }
        });
        goToHome();

        btnSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSummaryPage();

            }
        });
        btnBackToAssign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAssignBeneficiaryPage();
            }
        });
    }

    private void goToSummaryPage() {

        Intent iAssignSummary = new Intent(mContext, AllSummaryActivity.class);
        iAssignSummary.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        finish();

        startActivity(iAssignSummary);
    }


    private void goToHome() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);
            }
        });
    }

    private void setTextToTextViews() {
        tv_MemberName.setText(assignMem.getHh_mm_name());
        tv_MemberId.setText(assignMem.getNewId());
        tv_AsCriteria.setText(assignMem.getAssign_criteria());

        tv_HhName.setText(assignMem.getHh_name());
    }

    /**
     * LOAD :: GroupCategory
     *
     * @param cCode     Adm Country Code
     * @param donorCode Adm Donor Code
     * @param awardCode Adm Award Code
     * @param progCode  Adm Program Cod e
     */
    private void loadGroupCategory(final String cCode, final String donorCode, final String awardCode,
                                   final String progCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' ";


        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spGroupCategories.setAdapter(dataAdapter);


        if (idGroupCat != null) {
            for (int i = 0; i < spGroupCategories.getCount(); i++) {
                String groupCategory = spGroupCategories.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroupCat)) {
                    position = i;
                }
            }
            spGroupCategories.setSelection(position);
        }


        spGroupCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getValue();
                idGroupCat = ((SpinnerHelper) spGroupCategories.getSelectedItem()).getId();

                if (idGroupCat.length() > 2)
                    loadGroup(cCode, donorCode, awardCode, progCode, idGroupCat);

                Log.d(TAG, "Group Category ,idGroupCat:" + idGroupCat + " strGroupCat : " + strGroupCat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: Group
     * This method lode the data from table to spinner
     *
     * @param cCode       Country Code
     * @param donorCode   Donor Code
     * @param awardCode   award Code
     * @param progCode    Program Code
     * @param grpCateCode Group Categories Code
     */
    private void loadGroup(final String cCode, final String donorCode, final String awardCode
            , final String progCode, final String grpCateCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND " + SQLiteHandler.GROUP_CAT_CODE_COL + " = '" + grpCateCode + "' "
                //    + " AND " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " = '" + idSrvCenter + "' "
                ;


        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.COMMUNITY_GROUP_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spGroup.setAdapter(dataAdapter);


        if (idGroup != null) {
            for (int i = 0; i < spGroup.getCount(); i++) {
                String groupCategory = spGroup.getItemAtPosition(i).toString();
                if (groupCategory.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
        }


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();
                Log.d("HEO", "Group  ,idGroup:" + idGroup + " strGroup : " + strGroup);
                if (idGroup.length() > 2) {


                    loadActiveStatus();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * ** LOAD: Active Status
     */
    private void loadActiveStatus() {
        int pos = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrActive, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spActive.setAdapter(adptMartial);

        if (idActive != null) {
            if (idActive.equals("Y"))
                pos = 0;
            else
                pos = 1;

            spActive.setSelection(pos);
        }


        spActive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strActive = parent.getItemAtPosition(position).toString();

                if (strActive.equals("Yes"))
                    idActive = "Y";

                else
                    idActive = "N";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void saveAssignBeneficiary() {

        String regDate = tv_regDate.getText().toString();
        String lmpDate = tv_LmpDate.getText().toString();
        long days = 0;
        //  Log.d(TAG," reg Date "+regDate);
        if (sqlH.get_ProgramMultipleSrv(assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code()).equals("N")
                && sqlH.get_MemOthCriteriaLive(assignMem.getCountryCode(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getProgram_code(), assignMem.getService_code())) {
            erroDialog.showErrorDialog(mContext, "Member remains active in other criteria. Save attempt denied.");
        } else {
            if (regDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing registration date. Save attempt denied.");
            } else if (lmpDate.equals("")) {
                erroDialog.showErrorDialog(mContext, "Missing LMP date. Save attempt denied.");
            } else if (idGroup == null || idGroup.equals("00")) {
                erroDialog.showErrorDialog(mContext, "Missing Group. Save attempt denied.");
            } else if (assignMem.getMember_age() == null) {
                erroDialog.showErrorDialog(mContext, "Invalid age specified.");
            } else if (Integer.parseInt(assignMem.getMember_age()) < 12 || Integer.parseInt(assignMem.getMember_age()) > 40) {
                erroDialog.showErrorDialog(mContext, "Invalid age specified.");
            } else {


                days = getDateDifference(tv_regDate.getText().toString(), tv_LmpDate.getText().toString());

                /**
                 * check the registration validation
                 */

                if (days > 30 && days < 270) {
                    // its valid date range

                    String entryBy = getStaffID();
                    String entryDate = "";
                    assignMem.setEntryBy(entryBy);
                    try {
                        entryDate = getDateTime();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    assignMem.setEntryDate(entryDate);

                    Log.d(TAG, " reg Date : " + tv_regDate.getText().toString());


                    /**
                     * insert for server */
                    SQLServerSyntaxGenerator assign_pw = new SQLServerSyntaxGenerator();
                    assign_pw.setAdmCountryCode(assignMem.getCountryCode());
                    assign_pw.setLayR1ListCode(assignMem.getDistrictCode());
                    assign_pw.setLayR2ListCode(assignMem.getUpazillaCode());
                    assign_pw.setLayR3ListCode(assignMem.getUnitCode());
                    assign_pw.setLayR4ListCode(assignMem.getVillageCode());
                    assign_pw.setHHID(assignMem.getHh_id());
                    assign_pw.setMemID(assignMem.getMemId());
                    assign_pw.setAdmDonorCode(assignMem.getDonor_code());
                    assign_pw.setAdmAwardCode(assignMem.getAward_code());
                    assign_pw.setProgCode(assignMem.getProgram_code());
                    assign_pw.setSrvCode(assignMem.getService_code());
                    assign_pw.setRegNDate(assignMem.getRegNDate());
                    assign_pw.setLMPDate(lmpDate);
                    assign_pw.setGRDCode(assignMem.getGrdCode());
                    assign_pw.setGRDDate(assignMem.getGrdDate());
                    assign_pw.setPWGRDDate(assignMem.getGrdDate());
                    assign_pw.setEntryBy(assignMem.getEntryBy());
                    assign_pw.setEntryDate(assignMem.getEntryDate());
                                       /* @comments :update the RegNAssProSrv */
                    if (sqlH.ifExistsInRegNAssProgSrv(assignMem)) {
                        // this member exits

                        assignMem.setRegNDate(regDate);

                        assignMem.setGrdDate(calculateGRDDate(tv_LmpDate.getText().toString()));
                        // set the grd code here than update the regAss program Srv


                        int id = sqlH.editMemberDataIn_RegNAsgProgSrv(assignMem);

                        /**
                         * update for server */


                        assign_pw.setRegNDate(assignMem.getRegNDate());


                        assign_pw.setGRDDate(assignMem.getGrdDate());


                        sqlH.insertIntoUploadTable(assign_pw.updateRegAssProgSrvForAssign());

                    } else {
                        // if not exits than add to the RegN
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                        assignMem.setGrdDate(calculateGRDDate(tv_LmpDate.getText().toString()));

                        /**
                         * insert into local device for local device*/
                        sqlH.addMemberDataInto_RegNAsgProgSrv(assignMem);

                        /**
                         * insert for server */


                        assign_pw.setRegNDate(assignMem.getRegNDate());
                        assign_pw.setGRDCode(assignMem.getGrdCode());
                        assign_pw.setGRDDate(assignMem.getGrdDate());

                        sqlH.insertIntoUploadTable(assign_pw.insertIntoRegAssProgSrv());
                        // assign.setRegNStatus(assignMem.getREG());


                    }
                    /**
                     * RegNPw
                     */
                    if (sqlH.ifExistsInPWTable(assignMem)) {
                        //  if this member already exits in this table than reg, lmp & grd date will update
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdDate(calculateGRDDate(tv_LmpDate.getText().toString()));
                        assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                        assignMem.setLmpDate(lmpDate);

                        long id = sqlH.editMemberIn_PW(assignMem, lmpDate);

                        /**
                         *  over write the setter
                         */

                        assign_pw.setRegNDate(assignMem.getRegNDate());
                        assign_pw.setLMPDate(lmpDate);
                        assign_pw.setGRDCode(assignMem.getGrdCode());
                        assign_pw.setPWGRDDate(assignMem.getGrdDate());

                        sqlH.insertIntoUploadTable(assign_pw.updateRegNPWForPregnantWomen());


                    } else {
                        /**
                         * Insert New Entry
                         */
                        assignMem.setRegNDate(regDate);
                        assignMem.setGrdCode(sqlH.getGRDDefaultActiveReason(assignMem.getProgram_code(), assignMem.getService_code()));
                        assignMem.setGrdDate(calculateGRDDate(tv_LmpDate.getText().toString()));
                        assignMem.setLmpDate(lmpDate);
                        long id = sqlH.addMotherInto_PW(assignMem, lmpDate);//

                        /**
                         *  over write the setter
                         */
                        assign_pw.setRegNDate(assignMem.getRegNDate());
                        assign_pw.setLMPDate(lmpDate);
                        assign_pw.setGRDCode(assignMem.getGrdCode());
                        assign_pw.setPWGRDDate(assignMem.getGrdDate());

                        sqlH.insertIntoUploadTable(assign_pw.insertRegNPWForPregnantWomen());

                    }

                    assign_pw.setActive(idActive);
                    assign_pw.setGrpCode(idGroup);
                    /**
                     * check group
                     */
                    if (sqlH.ifExistsInRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code())) {
                        sqlH.editMemberIn_RegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup, idActive, entryBy, entryDate);

                        /**
                         * Upload Syntax
                         */
                        sqlH.insertIntoUploadTable(assign_pw.UpdateRegNMemProgGrp());


                    } else {
                        sqlH.insertIntoUploadTable(assign_pw.insertInToRegNMemProgGrp());

                        sqlH.addRegNmemProgGroup(assignMem.getCountryCode(), assignMem.getDonor_code(), assignMem.getAward_code(), assignMem.getDistrictCode(), assignMem.getUpazillaCode(), assignMem.getUnitCode(), assignMem.getVillageCode(), assignMem.getHh_id(), assignMem.getMemId(), assignMem.getProgram_code(), assignMem.getService_code(), idGroup,strGroup, idActive, entryBy, entryDate);
                    }
                    sqlH.insertIntoUploadTable(assign_pw.sqlSpRegNMemAwardProgCombN_Save());
                    Toast.makeText(mContext, "Saved Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    erroDialog.showErrorDialog(mContext, " Invalid pregnancy or exceeds allowable range under this category");
                }// end of else block


            }
        }// end of else
    }


    private void gotoAssignBeneficiaryPage() {
        /** @date : 2016-02-23 */

  /*      Intent iAssign = new Intent(mContext, OldAssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        iAssign.putExtra(OldAssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(OldAssignActivity.ASSIGN_AWARD_CODE, assignMem.getAward_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_AWARD_STR, assignMem.getTemAwardString());
        iAssign.putExtra(OldAssignActivity.ASSIGN_PROGRAM_CODE, assignMem.getProgram_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_PROGRAM_STR, assignMem.getTemProgramString());
        iAssign.putExtra(OldAssignActivity.ASSIGN_DONOR_CODE, assignMem.getDonor_code());
        iAssign.putExtra(OldAssignActivity.ASSIGN_CRITERIA_CODE, assignMem.getService_code());*//** service Code is criteria Code           *//*
        iAssign.putExtra(OldAssignActivity.ASSIGN_CRITERIA_STR, assignMem.getTemCriteriaString());
        iAssign.putExtra(OldAssignActivity.ASSIGN_VILLAGE_CODE, assignMem.getVillageCode());
        iAssign.putExtra(OldAssignActivity.ASSIGN_VILLAGE_STR, assignMem.getTemVillageString());

        iAssign.putExtra(OldAssignActivity.ASSIGN_DISTRICT_CODE, assignMem.getDistrictCode());
        iAssign.putExtra(OldAssignActivity.ASSIGN_UPZELA_CODE, assignMem.getUpazillaCode());
        iAssign.putExtra(OldAssignActivity.ASSIGN_UNIT_CODE, assignMem.getUnitCode());
*/
        Intent iAssign = new Intent(mContext, AssignActivity.class);
        finish();

        iAssign.putExtra(KEY.COUNTRY_ID, assignMem.getCountryCode());
        iAssign.putExtra(AssignActivity.SUB_ASSIGN_DIR, true);
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_CODE, assignMem.getAward_code());
        iAssign.putExtra(AssignActivity.ASSIGN_AWARD_STR, assignMem.getTemAwardString());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_CODE, assignMem.getProgram_code());
        iAssign.putExtra(AssignActivity.ASSIGN_PROGRAM_STR, assignMem.getTemProgramString());
        iAssign.putExtra(AssignActivity.ASSIGN_DONOR_CODE, assignMem.getDonor_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_CODE, assignMem.getService_code());
        iAssign.putExtra(AssignActivity.ASSIGN_CRITERIA_STR, assignMem.getTemCriteriaString());
        iAssign.putExtra(KEY.MEMBER_ID, memberId15D);


        startActivity(iAssign);


    }

    /**
     * set date picker for registration
     */
    public void updateRegDate() {

        tv_regDate.setText(formatUSA.format(calendar.getTime()));
    }

    public void setRegDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateRegDate();
        }
    };

    /**
     * set date picker for Lm date
     */
    public void updateLmDate() {

        tv_LmpDate.setText(formatUSA.format(calendar.getTime()));
    }

    public void setLmDate() {
        new DatePickerDialog(mContext, dat, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dat = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLmDate();
        }
    };

    private void viewReference() {
        tv_HhName = (TextView) findViewById(R.id.as_PW_ed_hhName);

        tv_MemberId = (TextView) findViewById(R.id.as_PW_ed_MemberID);
        tv_MemberName = (TextView) findViewById(R.id.as_PW_ed_MemberName);
        tv_AsCriteria = (TextView) findViewById(R.id.as_PW_ed_asCriteria);
        // tvPageTitle= (TextView) findViewById(R.id.as_PW_e);
        tv_regDate = (TextView) findViewById(R.id.as_PW_edt_regD);
        tv_LmpDate = (TextView) findViewById(R.id.as_PW_edt_lmpDate);

        /**
         * 4 Button
         */
        btnSave = (Button) findViewById(R.id.btn_assign_PW_save);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnBackToAssign = (Button) findViewById(R.id.btn_PW_goAssignePage);
        /**
         * 3 Spinner
         */
        spGroupCategories = (Spinner) findViewById(R.id.sp_ass_pwGroupCategories);
        spGroup = (Spinner) findViewById(R.id.sp_ass_pwGroup);
        spActive = (Spinner) findViewById(R.id.sp_ass_pwActive);


        setUpHomeButton();
        setUpSummaryButton();
        setUpSaveButton();
        setUpGoToServiceButton();

    }


    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 10, 180, 10);
    }

    private void setUpSummaryButton() {
        btnSummary.setText("");
        Drawable summeryImage = getResources().getDrawable(R.drawable.summession_b);
        btnSummary.setCompoundDrawablesRelativeWithIntrinsicBounds(summeryImage, null, null, null);
        btnSummary.setPadding(180, 10, 180, 10);
    }

    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnSave.setPadding(180, 10, 180, 10);
    }

    private void setUpGoToServiceButton() {
        btnBackToAssign.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackToAssign.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnBackToAssign.setPadding(180, 10, 180, 10);
    }


    // to Stop the back press button
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private long getDateDifference(String from, String to) {
        long days = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date dateFrom = myFormat.parse(from);
            Date dateTo = myFormat.parse(to);
            long difference = 0;
            difference = dateFrom.getTime() - dateTo.getTime();
            days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    /**
     * calculate the the graduation date
     *
     * @param regDate registrationDate
     * @return grdDate
     * @since 2015-11-23
     */
    private String calculateGRDDate(String regDate) {
        String outputDate = "";
        SimpleDateFormat myFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        SimpleDateFormat saveFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(myFormat.parse(regDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, PW_GRD_DAYS_270);

        outputDate = saveFormat.format(calendar.getTime());

        return outputDate;
    }

}
