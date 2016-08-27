package com.siddiquinoor.restclient.activity.sub_activity.register_sub;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.AssignActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.Register;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.ViewRecordDetail;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.adapters.ListDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Siddiqui on 5/4/2015.
 */
public class RegisterMember extends BaseActivity {

    private static final String TAG = RegisterMember.class.getSimpleName();
    //    private static final int LACTATING_MOTHER = 2;
//    private static final int CHILD_UNDER_2 = 3;
//    private static final int CHILD_ABOVE_2 = 4;
    Context mContext;
//    private static final int PREGNANT_WOMEN = 1;

    private Button btnReg;
    private Button btnRecords;
    private Button btnSummary;
    private Button btnHome;
    private Button btngotToAssigne;
    private Button btn_mem_gotoHhDetails;

    private Button btnSaveMember;

    private String str_c_code;
    private String country_name;

    private String str_district;
    private String str_upazilla;
    private String str_union;
    private String str_village;

    private String str_districtCode;
    private String str_upazillaCode;
    private String str_unionCode;
    private String str_villageCode;


    private String str_entry_by;
    private String str_entry_date;
    private String str_agland;
    private String str_reg_date;


    private TextView tvHHID;
    private String str_hhID;

    private TextView tvHHName;
    private String str_hhName;

    private TextView tvMemID;
    private String str_hhMemID;

    private EditText txtMemName;
    private String str_MemName;

    private Spinner spGender;
    private String str_gender;

    private EditText txtAge;
    private String str_age;

    private Spinner spRelation;
    private String str_relation;
    private String idRelation;
    private String str_relation_code;

    private SQLiteHandler sqlH;

    private boolean is_edit = false;
    private int pID = 0;

    // for member edit
    private int mID = 0;
    private String hhID = null;
    private String MemID = null;
    private String MemberName = null;
    private String gender = null;


    private String lmp_date = null;
    private String child_dob = null;


    private String str_elderly = "";
    private String str_disabled = "";
    // private String idDonor;
    private String age = null;

    private String redirect = "";

    private Intent cIntent = null;

    int position = 0;

    private String criteria = "";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private Calendar calendarChildDOB = Calendar.getInstance();
    // private TextView lmpDate;
    // private TextView childDOB;


    // private Spinner spAward;
    // private Spinner spProgram;
    //  private Spinner spCriteria;

    //private String strAward;
    // private String idAward;
    // private String idProgram;
    // private String strProgram;
    // private String idCriteria;
    //private String strCriteria;
    private Button btn_Clear;

    //private LinearLayout llAssignController;

    private ADNotificationManager dialog;
    private boolean isMemberSaved;

    public boolean isMemberSaved() {
        return isMemberSaved;
    }

    public void setIsMemberSaved(boolean isMemberSaved) {
        this.isMemberSaved = isMemberSaved;
    }

    LinearLayout lLayoutLiberiaContainer;
    LinearLayout lLayoutMalawiContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.reg_nhh_mem_schema);
        mContext = this;
        dialog = new ADNotificationManager();
        setIsMemberSaved(false);

        // SqLite database handler
        sqlH = new SQLiteHandler(getApplicationContext());
        viewReference();

        btngotToAssigne.setEnabled(false);
        btnSaveMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemberData();
            }
        });


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReg = new Intent(getApplicationContext(), Register.class);
                finish();
                iReg.putExtra("country_code", str_c_code);
                iReg.putExtra(KEY.DISTRICT, str_district);
                iReg.putExtra(KEY.UPAZILLA, str_upazilla);
                iReg.putExtra(KEY.UNIT, str_union);
                iReg.putExtra(KEY.VILLAGE_NAME, str_village);
                iReg.putExtra(KEY.DISTRICT_CODE, str_districtCode);
                iReg.putExtra(KEY.UPAZILLA_CODE, str_upazillaCode);
                iReg.putExtra(KEY.UNIT_CODE, str_unionCode);
                iReg.putExtra(KEY.VILLAGE_CODE, str_villageCode);
                Log.d("REFAT--->", str_district + "\n" + str_upazilla + "\n" + str_union + "\n" + str_village
                        + "\n" + str_districtCode + "\n" + str_upazillaCode + "\n" + str_unionCode
                        + "\n" + str_villageCode);


                startActivity(iReg);

            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent iHome = new Intent(RegisterMember.this, MainActivity.class);
                startActivity(iHome);
            }
        });


        btn_mem_gotoHhDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHouseholdDetailsPage();

            }
        });


        btngotToAssigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAssigneNewMehod();

            }
        });

        btn_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });


        cIntent = getIntent();

        is_edit = cIntent.getBooleanExtra("is_edit", false);
        str_hhID = cIntent.getStringExtra("str_hhID");
        str_hhName = cIntent.getStringExtra("str_hhName");
        redirect = cIntent.getStringExtra("redirect");
        str_c_code = cIntent.getStringExtra("str_c_code");
        str_district = cIntent.getStringExtra("str_district");
        str_upazilla = cIntent.getStringExtra("str_upazilla");
        str_union = cIntent.getStringExtra("str_union");
        str_village = cIntent.getStringExtra("str_village");
        str_districtCode = cIntent.getStringExtra("str_districtCode");
        str_upazillaCode = cIntent.getStringExtra("str_upazillaCode");
        str_unionCode = cIntent.getStringExtra("str_unionCode");
        str_villageCode = cIntent.getStringExtra("str_villageCode");

        str_entry_by = cIntent.getStringExtra("str_entry_by");
        str_entry_date = cIntent.getStringExtra("str_entry_date");

        pID = cIntent.getIntExtra("pID", 20);
        /** For Liberia Member Designe test
         * */
      /*  if(str_c_code.equals("0004")){
            lLayoutMalawiContainer.setVisibility(View.GONE);
        }
        else*/
        // no need The Liberia Containnner
        lLayoutLiberiaContainer.setVisibility(View.GONE);

        if (!is_edit) {
            if (str_hhID.length() > 5) {
                setMemID(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID.substring(8));
            } else {
                setMemID(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID);
            }


        } else {
            // When Edit
            btngotToAssigne.setEnabled(true);
            // btnSaveMember.setText("UPDATE");
            mID = cIntent.getIntExtra("mId", 20);

            //str_hhID = cIntent.getStringExtra("str_hhID");
            MemID = cIntent.getStringExtra("MemID");
            MemberName = cIntent.getStringExtra("MemberName");


            gender = cIntent.getStringExtra("gender");
            idRelation = cIntent.getStringExtra("relation");
            str_relation = sqlH.getRelationString(idRelation);

            lmp_date = cIntent.getStringExtra("LMPDate");
            child_dob = cIntent.getStringExtra("ChildDOB");
            str_elderly = "";// cIntent.getStringExtra("Elderly");
            str_disabled = "";// cIntent.getStringExtra("Disabled");
            age = cIntent.getStringExtra("age");
            tvMemID.setText(MemID);
            txtMemName.setText(MemberName);
            // lmpDate.setText(lmp_date);
            //  childDOB.setText(child_dob);
            txtAge.setText(age);
        }
        if (redirect != null) {
        /*    if (redirect.equals("Sub_Assign")) {
                //  strAward = cIntent.getStringExtra(KEY.AWARD_NAME);
                idAward = cIntent.getStringExtra(KEY.AWARD_CODE);
                idProgram = cIntent.getStringExtra(KEY.PROGRAM_CODE);
                strProgram = cIntent.getStringExtra(KEY.PROGRAM_NAME);
                idDonor = cIntent.getStringExtra("DONOR_CODE");
                idCriteria = cIntent.getStringExtra("CRITERIA_CODE");
                strCriteria = cIntent.getStringExtra("CRITERIA_STR");
            }*/
        }

        tvHHID.setText(str_hhID);
        tvHHName.setText(str_hhName);

//        loadAward(str_c_code);
        loadGender();
        loadRelation();


        tvHHID.setEnabled(false);
        tvHHName.setEnabled(false);
        tvMemID.setEnabled(false);
        // Set focus to Member Name
        txtMemName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(txtMemName, InputMethodManager.SHOW_IMPLICIT);


    }

    private void gotoHouseholdDetailsPage() {
        String temHH;
        if (str_hhID.length() > 5) {
            temHH = str_hhID.substring(8);
        } else {
            temHH = str_hhID;
        }

        ListDataModel data = sqlH.getSingleRegisteredData(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, temHH);

        Intent dIntent = new Intent(RegisterMember.this, ViewRecordDetail.class);

        String temp_country_name = data.getCountryName();     // spinner as 01/02
        String district = data.getDistrict();     // spinner as 01/02
        String upazilla = data.getUpazilla();     // spinner as 01/02
        String unit = data.getUnitName();     // spinner as 01/02
        String village = data.getVillage();      // spinner as 01/02

        String country_code = data.getCountryCode();        // spinner
        String districtCode = data.getDistrictCode();     // spinner as 01/02
        String upazillaCode = data.getUpazillaCode();     // spinner as 01/02
        String unitCode = data.getUnitNameCode();     // spinner as 01/02
        String villageCode = data.getVillageCode();      // spinner as 01/02


        String regID = districtCode + upazillaCode + unitCode + villageCode + data.getRegID();
        String regDate = data.getRegDate();
        String personName = data.getName();
        String sex = data.getSex();          // spinner as 'M'/'F'
        String hhSize = data.getHhSize();
        String latitude = data.getLatitude();
        String longitude = data.getLongitude();
        String agLand = data.getAgLand();
        String vstatus = data.getvStatus();      // spinner as 'Y'/'N'
        String mstatus = data.getmStatus();      // spinner as 'Y'/'N'
        String entryBy = data.getEntryBy();
        String entryDate = data.getEntryDate();
        String vsla_group = data.getVSLAGroup();

        int pId = data.getID();


        dIntent.putExtra(KEY.COUNTRY_NAME, temp_country_name);
        dIntent.putExtra(KEY.DISTRICT, district);
        dIntent.putExtra(KEY.UPAZILLA, upazilla);
        dIntent.putExtra(KEY.UNIT, unit);
        dIntent.putExtra(KEY.VILLAGE_NAME, village);
        dIntent.putExtra(KEY.COUNTRY_CODE, country_code);
        dIntent.putExtra(KEY.DISTRICT_CODE, districtCode);
        dIntent.putExtra(KEY.UPAZILLA_CODE, upazillaCode);
        dIntent.putExtra(KEY.UNIT_CODE, unitCode);
        dIntent.putExtra(KEY.VILLAGE_CODE, villageCode);
        dIntent.putExtra(KEY.REG_ID, regID);
        dIntent.putExtra(KEY.REG_DATE, regDate);
        dIntent.putExtra(KEY.PERSON_NAME, personName);
        dIntent.putExtra(KEY.SEX, sex);
        dIntent.putExtra(KEY.HH_SIZE, hhSize);
        dIntent.putExtra(KEY.LATITUDE, latitude);
        dIntent.putExtra(KEY.LONGITUDE, longitude);
        dIntent.putExtra(KEY.AG_LAND, agLand);
        dIntent.putExtra(KEY.VSTATUS, vstatus);
        dIntent.putExtra(KEY.MSTATUS, mstatus);
        dIntent.putExtra(KEY.ENTRY_BY, entryBy);
        dIntent.putExtra(KEY.ENTRY_DATE, entryDate);
        dIntent.putExtra(KEY.VSLA_GROUP, vsla_group);
        dIntent.putExtra(KEY.P_ID, pId);
        finish();

        startActivity(dIntent);
    }

    private void clearField() {

        tvMemID.setText("");
        str_hhMemID = "";

        txtMemName.setText("");
        str_MemName = "";
        txtAge.setText("");
        str_age = "";
        idRelation = "";
        str_relation = "";

        setMemID(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, tvHHID.getText().toString().substring(8));


        loadGender();
        loadRelation();
        //  btnSaveMember.setText("Save");
    }

    private void viewReference() {
        tvHHID = (TextView) findViewById(R.id.tv_Mreg_HH_id);
        tvHHName = (TextView) findViewById(R.id.tv_reg_mem_hh_name);
        tvMemID = (TextView) findViewById(R.id.mem_id);   // Member ID
        txtMemName = (EditText) findViewById(R.id.mem_name);
        spGender = (Spinner) findViewById(R.id.spGender);
        txtAge = (EditText) findViewById(R.id.mem_age);
        spRelation = (Spinner) findViewById(R.id.spRelation);


        lLayoutLiberiaContainer = (LinearLayout) findViewById(R.id.lLayout_LiberiaViews); // to control layout
        lLayoutMalawiContainer = (LinearLayout) findViewById(R.id.lLayout_malawiViews); // to control layout

        btnSaveMember = (Button) findViewById(R.id.btnSaveMember);
        btngotToAssigne = (Button) findViewById(R.id.btn_mem_gotoAssign);
        btn_mem_gotoHhDetails = (Button) findViewById(R.id.btn_mem_gotoHouseholdDetails);
        btn_Clear = (Button) findViewById(R.id.btnClearMember);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnReg = (Button) findViewById(R.id.btnRegisterFooter);
        setHomeButtonIcon();
        setHhDetailsIcon();
        setSaveButtonIcon();
        setButtonClare();
        setAddMember();
        setAssigne();
    }

    private void setHhDetailsIcon() {
        btn_mem_gotoHhDetails.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.hh_details);
        btn_mem_gotoHhDetails.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btn_mem_gotoHhDetails.setPadding(180, 5, 180, 5);
    }

    private void setHomeButtonIcon() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 5, 180, 5);
    }

    private void setSaveButtonIcon() {
        btnSaveMember.setText("");
        Drawable imageSave = getResources().getDrawable(R.drawable.save_b);
        btnSaveMember.setCompoundDrawablesRelativeWithIntrinsicBounds(imageSave, null, null, null);
        btnSaveMember.setPadding(180, 5, 180, 5);

    }


    private void setButtonClare() {
        btn_Clear.setText("");
        Drawable imageClear = getResources().getDrawable(R.drawable.clear_b);
        btn_Clear.setCompoundDrawablesRelativeWithIntrinsicBounds(imageClear, null, null, null);
        btn_Clear.setPadding(180, 5, 180, 5);
    }

    private void setAddMember() {
        btnReg.setText("");
        Drawable addMemberIcon = getResources().getDrawable(R.drawable.registration);
        btnReg.setCompoundDrawablesRelativeWithIntrinsicBounds(addMemberIcon, null, null, null);
        btnReg.setPadding(180, 5, 180, 5);
    }


    private void setAssigne() {
        btngotToAssigne.setText("");
        Drawable addMemberIcon = getResources().getDrawable(R.drawable.assign);
        btngotToAssigne.setCompoundDrawablesRelativeWithIntrinsicBounds(addMemberIcon, null, null, null);
        btngotToAssigne.setPadding(180, 5, 180, 5);
    }


    private void goToAssigneNewMehod() {


        Intent intent = new Intent(RegisterMember.this, AssignActivity.class);
        finish();
        String tmpMemberCode = tvHHID.getText().toString() + tvMemID.getText().toString();
//        Log.d("MOR", "tmpMemberCode:" + tmpMemberCode + "str_c_code:" + str_c_code);
        intent.putExtra(KEY.COUNTRY_ID, str_c_code);
        intent.putExtra(KEY.MEMBER_ID, tmpMemberCode);
        startActivity(intent);

    }


    private void saveMemberData() {
        str_hhMemID = tvMemID.getText().toString();
        str_MemName = txtMemName.getText().toString();
        str_age = txtAge.getText().toString();


        String lmp_date = "";// lmpDate.getText().toString();
        String child_dob = "";// childDOB.getText().toString();

        boolean invalid = false;


        // TODO :: Need to check valid date range collect from online

        if (str_hhMemID.equals("")) {
            invalid = true;
            Toast.makeText(getApplicationContext(), "Enter any ID", Toast.LENGTH_SHORT).show();
        } else if (str_MemName.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Name. Enter Person Name");
            //Toast.makeText(getApplicationContext(), "Enter Person Name", Toast.LENGTH_SHORT).show();
        } else if (str_age.equals("")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing Age. Please select a Age");
            //Toast.makeText(getApplicationContext(), "Please select a Age", Toast.LENGTH_SHORT).show();
        } else if (Integer.valueOf(str_age) > 99) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Age exceeds allowable range.");
            //Toast.makeText(getApplicationContext(), "Please select a Age", Toast.LENGTH_SHORT).show();
        } else if (str_relation.equals("Select Relation")) {
            invalid = true;
            dialog.showErrorDialog(mContext, "Missing relation. Please select a Relation");
            // Toast.makeText(getApplicationContext(), "Missing relation. Please select a Relation", Toast.LENGTH_SHORT).show();
        } else if (invalid == false) {
            if (is_edit) {
                // Update Member data
                sqlH.editMalawiMemberData(mID, str_MemName, str_gender, idRelation, lmp_date, child_dob, str_elderly, str_disabled, str_age, pID);
                Toast.makeText(mContext, "The member has been uploaded  ", Toast.LENGTH_SHORT).show();
                // goToNextPage(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID, redirect);
                setIsMemberSaved(true);
            } else {
                /**
                 * Insert procedure
                 * */
                String temId;
                if (tvHHID.getText().toString().length() > 5)
                    temId = tvHHID.getText().toString().substring(8);
                else
                    temId = tvHHID.getText().toString();

                sqlH.addMemberDataForMalawi(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, temId, str_hhMemID, str_MemName, str_gender, idRelation, str_entry_by, str_entry_date, lmp_date, child_dob, str_elderly, str_disabled, str_age, pID);
                SQLServerSyntaxGenerator malawiMember = new SQLServerSyntaxGenerator();
                malawiMember.setAdmCountryCode(str_c_code);
                malawiMember.setLayR1ListCode(str_districtCode);
                malawiMember.setLayR2ListCode(str_upazillaCode);
                malawiMember.setLayR3ListCode(str_unionCode);
                malawiMember.setLayR4ListCode(str_villageCode);
                malawiMember.setHHID(temId);
                malawiMember.setMemID(str_hhMemID);
                malawiMember.setMmMemName(str_MemName);
                malawiMember.setMmMemSex(str_gender);
                malawiMember.setMmHHRelation(idRelation);
                malawiMember.setEntryBy(str_entry_by);
                malawiMember.setEntryDate(str_entry_date);
                sqlH.insertIntoUploadTable(malawiMember.insertIntoRegNMemberForMalawi());
                Toast.makeText(getApplicationContext(), "save successfully", Toast.LENGTH_LONG).show();
                setIsMemberSaved(true);
                //  this.finish();

                      /*  if(!redirect.isEmpty()){
                            goToNextPage(str_c_code, str_districtCode, str_upazillaCode, str_unionCode, str_villageCode, str_hhID, redirect);
                        }else{
                            Intent dIntent = new Intent(RegisterMember.this, RegisterMember.class);

                            dIntent.putExtra("redirect", "");
                            dIntent.putExtra("str_hhID", str_hhID);
                            dIntent.putExtra("str_hhName", str_hhName);
                            dIntent.putExtra("str_c_code", str_c_code);

                            dIntent.putExtra("str_district", str_district);
                            dIntent.putExtra("str_upazilla", str_upazilla);
                            dIntent.putExtra("str_union", str_union);
                            dIntent.putExtra("str_village", str_village);

                            dIntent.putExtra("str_districtCode", str_districtCode);
                            dIntent.putExtra("str_upazillaCode", str_upazillaCode);
                            dIntent.putExtra("str_unionCode", str_unionCode);
                            dIntent.putExtra("str_villageCode", str_villageCode);


                            dIntent.putExtra("str_entry_by", str_entry_by);
                            dIntent.putExtra("str_entry_date", str_entry_date);

                            startActivity(dIntent);
                        }*/
            }
        }
        /**
         * When the member is saved than the Assign controller will appears */
        /**
         * todo: enable asssigne Button
         *
         */
        btngotToAssigne.setEnabled(true);

    }

    private void goToNextPage(String c_code, String districtCode, String upazillaCode, String unionCode, String villageCode, String hhID, String redirect) {

        ContentValues results = new ContentValues();
        Intent dIntent = new Intent(this, ViewRecordDetail.class);

        dIntent.putExtra("regID", hhID);

        // getting House hold data
        results = sqlH.getHouseHoldData(c_code, districtCode, upazillaCode, unionCode, villageCode, hhID);

        country_name = results.getAsString("country_name");

        str_district = results.getAsString("str_district");
        str_upazilla = results.getAsString("str_upazilla");
        str_union = results.getAsString("str_union");
        str_village = results.getAsString("str_village");

        str_c_code = results.getAsString("str_c_code");
        str_districtCode = results.getAsString("str_districtCode");
        str_upazillaCode = results.getAsString("str_upazillaCode");
        str_unionCode = results.getAsString("str_unionCode");
        str_villageCode = results.getAsString("str_villageCode");

        str_reg_date = results.getAsString("str_reg_date");
        //str_hhName = results.getAsString("str_hhName");
        str_gender = results.getAsString("str_gender");
        String str_hhsize = results.getAsString("str_hhsize");
        String str_latitude = results.getAsString("str_latitude");
        String str_longitude = results.getAsString("str_longitude");
        str_agland = results.getAsString("str_agland");
        String str_vstatus = results.getAsString("str_vstatus");
        String str_mstatus = results.getAsString("str_mstatus");
        str_entry_by = cIntent.getStringExtra("str_entry_by");
        str_entry_date = cIntent.getStringExtra("str_entry_date");

        dIntent.putExtra("country_code", str_c_code);


        dIntent.putExtra("country_name", country_name);
        dIntent.putExtra("district", str_district);
        dIntent.putExtra("upazilla", str_upazilla);
        dIntent.putExtra("unit", str_union);
        dIntent.putExtra("village", str_village);

        dIntent.putExtra("districtCode", str_districtCode);
        dIntent.putExtra("upazillaCode", str_upazillaCode);
        dIntent.putExtra("unitCode", str_unionCode);
        dIntent.putExtra("villageCode", str_villageCode);


        //dIntent.putExtra("regID", str_village);
        dIntent.putExtra("regDate", str_reg_date);
        dIntent.putExtra("personName", str_hhName);
        dIntent.putExtra("sex", str_gender);
        dIntent.putExtra("hhSize", str_hhsize);
        dIntent.putExtra("latitude", str_latitude);
        dIntent.putExtra("longitude", str_longitude);
        dIntent.putExtra("agLand", str_agland);
        dIntent.putExtra("vstatus", str_vstatus);
        dIntent.putExtra("mstatus", str_mstatus);
        dIntent.putExtra("entryBy", str_entry_by);
        dIntent.putExtra("entryDate", str_entry_date);

        startActivity(dIntent);
    }

    private void setMemID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        String next_id = sqlH.getMemberID(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);
        tvMemID.setText(next_id);
        Log.d(TAG, "Member ID is = " + next_id);
    }

    /**
     * LOAD :: Relation
     */
    private void loadRelation() {

        // Spinner Drop down elements for District
        List<SpinnerHelper> listRelation = sqlH.getListAndID(sqlH.RELATION_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listRelation);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spRelation.setAdapter(dataAdapter);

        if (idRelation != null) {
            for (int i = 0; i < spRelation.getCount(); i++) {
                String relation = spRelation.getItemAtPosition(i).toString();
                if (relation.equals(str_relation)) {
                    position = i;
                }
            }
            spRelation.setSelection(position);
        }


        spRelation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_relation = ((SpinnerHelper) spRelation.getSelectedItem()).getValue();
                idRelation = ((SpinnerHelper) spRelation.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    /**
     * LOAD :: GENDER
     */
    private void loadGender() {

        spGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<CharSequence> adtGender = ArrayAdapter.createFromResource(
                this, R.array.spGenderItem, R.layout.spinner_layout);

        adtGender.setDropDownViewResource(R.layout.spinner_layout);
        spGender.setAdapter(adtGender);

        if (gender == null || !gender.equals("M"))
            gender = "Female";
        else
            gender = "Male";


        spGender.setSelection(getSpinnerIndex(spGender, gender));

        spGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_gender = parent.getItemAtPosition(position).toString();

                if (str_gender.equals("Male"))
                    str_gender = "M";
                else
                    str_gender = "F";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void updateDateLMB() {
        /*lmpDate.setText(format.format( calendar.getTime()));*/
    }

    public void setDateLMB() {
        new DatePickerDialog(RegisterMember.this, dpLMB, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dpLMB = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLMB();
        }
    };


    public void updateChildDOBDate() {
        //childDOB.setText(format.format( calendarChildDOB.getTime()));
    }

    public void setDateChildDOB() {
        new DatePickerDialog(RegisterMember.this, dpChildDOB, calendarChildDOB.get(Calendar.YEAR), calendarChildDOB.get(Calendar.MONTH), calendarChildDOB.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener dpChildDOB = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendarChildDOB.set(Calendar.YEAR, year);
            calendarChildDOB.set(Calendar.MONTH, monthOfYear);
            calendarChildDOB.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateChildDOBDate();
        }
    };
/*
    *//**
     * LOAD :: Award
     *//*
    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + idCountry + "'";
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

       *//* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*//*

        spAward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strAward = ((SpinnerHelper) spAward.getSelectedItem()).getValue();
                idAward = ((SpinnerHelper) spAward.getSelectedItem()).getId();

                // String donorId=idAward.substring(0,1)
                idDonor = idAward.substring(0, 2);
                loadProgram(idAward.substring(2), idDonor, idCountry);
                Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner


    *//**
     * LOAD :: Program
     *//*
    private void loadProgram(final String idAward, final String donorId, final String idcCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + idAward + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorId + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);

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

       *//* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*//*

        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                // if(idProgram.length()>2){
                Log.d(TAG, "load Prog data " + idProgram);
                // loadDistributionListView(idcCode,donorId,idAward,idCriteria.substring(0,3),idCriteria.substring(3));
                //  }
                //  loadProgram(idAward);
                loadCriteria(idAward, donorId, idProgram, idcCode);
                // Log.d(TAG, "idCriteria : " + idProgram);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    *//**
     * LOAD :: Criteria
     *//*
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
                String award = spCriteria.getItemAtPosition(i).toString();
                if (award.equals(strCriteria)) {
                    position = i;
                }
            }
            spCriteria.setSelection(position);
        }

       *//* if (!idDist.isEmpty()) {
            spDistrict.setSelection( getSpinnerIndex(spDistrict,idDist) );
        }*//*

        spCriteria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getValue();
                idCriteria = ((SpinnerHelper) spCriteria.getSelectedItem()).getId();


                if (Integer.parseInt(idCriteria) > 0) {
                    // Enable
                 *//*   loadVillage(cCode,idAward,donorId, idProgram,idCriteria );// idService=idCriteria

                    if (ext_village != null) {
                        for (int i = 0; i < spVillage.getCount(); i++) {
                            String village = spVillage.getItemAtPosition(i).toString();
                            if (village.equals(ext_village_name)) {
                                positionVillage = i;
                            }
                        }
                        spVillage.setSelection(positionVillage);
                    }*//*
                }

                // if(idCriteria.length()>2){
                Log.d(TAG, "load idCriteria data " + idCriteria + " Critrei a name " + strCriteria);
                // loadDistributionListView(idcCode,donorId,idAward,idCriteria.substring(0,3),idCriteria.substring(3));
                // }
                //  loadProgram(idAward);
                // Log.d(TAG, "idCriteria : " + idCriteria);
                //Log.d(TAG, "ID is: " + idDist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    */

    /*    private void goToAssigneOldMethod() {
        boolean invalid;
        if (isMemberSaved() || is_edit) {
            boolean generalCase = false;
            Intent iGotoAssignePage = new Intent();// catagories
            switch (Integer.parseInt(idCriteria)) {
                case PREGNANT_WOMEN:

                    if (str_gender.equals("F")) {
                        str_age = txtAge.getText().toString();
                        if (Integer.parseInt(str_age) >= 12 && Integer.parseInt(str_age) <= 40)
                            iGotoAssignePage = new Intent(mContext, AssignPW.class);

                        else dialog.showErrorDialog(mContext, "Invalid age specified.");
                    } else {
                        invalid = true;
                        iGotoAssignePage = new Intent(mContext, RegisterMember.class);
                        dialog.showErrorDialog(mContext, "Invalid identification");
                    }
                    break;
                case LACTATING_MOTHER:
                    if (str_gender.equals("F"))
                        iGotoAssignePage = new Intent(mContext, AssignLM.class);
                    else {
                        invalid = true;
                        iGotoAssignePage = new Intent(mContext, RegisterMember.class);
                        dialog.showErrorDialog(mContext, "Invalid identification");
                    }
                    break;

                case CHILD_UNDER_2:
                    iGotoAssignePage = new Intent(mContext, AssignCU2.class);
                    break;

                case CHILD_ABOVE_2:
                    iGotoAssignePage = new Intent(mContext, AssignCA2.class);
                    break;
                default:
                    iGotoAssignePage = new Intent(mContext, RegisterMember.class);
                    generalCase = true;
                    break;
            }

            String hhMemID = tvMemID.getText().toString();
            String hhMemName = txtMemName.getText().toString();
            iGotoAssignePage.putExtra("directory", TAG);
            iGotoAssignePage.putExtra("ASS_HHID", str_hhID);
            iGotoAssignePage.putExtra("ASS_HHNAME", str_hhName);
            iGotoAssignePage.putExtra("ASS_MMID", hhMemID);
            iGotoAssignePage.putExtra("ASS_MMNAME", hhMemName);
            iGotoAssignePage.putExtra("ASS_Criteria", strCriteria);
            iGotoAssignePage.putExtra("ASS_Mem_Age", txtAge.getText().toString());
            iGotoAssignePage.putExtra("ASS_C_Code", str_c_code);
            iGotoAssignePage.putExtra("ASS_DistrictCode", str_districtCode);
            iGotoAssignePage.putExtra("ASS_UpazillaCode", str_upazillaCode);
            iGotoAssignePage.putExtra("ASS_UnitCode", str_unionCode);
            iGotoAssignePage.putExtra("ASS_VillageCode", str_villageCode);
            iGotoAssignePage.putExtra("ASS_Award_code", idAward.substring(0, 2));
            iGotoAssignePage.putExtra("ASS_Award_Str", strAward);
            iGotoAssignePage.putExtra("ASS_Program_code", idProgram);
            iGotoAssignePage.putExtra("ASS_Program_Str", strProgram);
            iGotoAssignePage.putExtra("ASS_Service_code", idCriteria);
            iGotoAssignePage.putExtra("ASS_Criteria_Str", strCriteria);
            iGotoAssignePage.putExtra("ASS_EntryBy", str_entry_by);
            iGotoAssignePage.putExtra("ASS_EntryDate", str_entry_date);
            iGotoAssignePage.putExtra("ASS_Donor_code", idDonor);
            iGotoAssignePage.putExtra("ASS_Gender", gender);
            iGotoAssignePage.putExtra("ASS_IdRelation", idRelation);
            iGotoAssignePage.putExtra("ASS_StrRelation", str_relation);
            iGotoAssignePage.putExtra("ASS_StrDistrict", str_district);
            iGotoAssignePage.putExtra("ASS_StrUpzalla", str_upazilla);
            iGotoAssignePage.putExtra("ASS_StrUnit", str_union);


              *//*  Intent intentAddnewHH = new Intent(getApplicationContext(), AssignActivity.class);
                intentAddnewHH.putExtra("ID_COUNTRY", str_c_code);*//*
            if (generalCase) finish();

            startActivity(iGotoAssignePage);
        } else {
            dialog.showInfromDialog(mContext, "Alert", " the member is not Saved yet");
        }
    }*/


}
