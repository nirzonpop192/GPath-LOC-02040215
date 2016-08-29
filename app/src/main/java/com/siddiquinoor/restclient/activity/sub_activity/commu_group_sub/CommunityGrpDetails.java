package com.siddiquinoor.restclient.activity.sub_activity.commu_group_sub;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.GroupSearchPage;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.MemberSearchPage;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommunityGrpDetails extends BaseActivity {

    private static final String TAG = CommunityGrpDetails.class.getSimpleName();
    private Context mContext = CommunityGrpDetails.this;
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private Spinner spCountry, spAward, spProgram/*, spOrganization*/, spStatus, spActive;
    private Spinner spStaffName;
    private TextView tvFormation;
    private Spinner spUpazilla, spGroup, spGroupCategories;
    private EditText edtRepresentative, edtContactNo;
    private Button btnSave, btnDelete, btnBackward, btnHome;
    private Intent intent;

    SQLiteHandler sqlH;
    private String idCountry;
    private String strCountry;
    private String idAward;
    private String strAward;
    private String idDonor;
    private String idProgram;
    private String strProgram;
    private String idActive;
    private String idStatus;
    private String strContactNo;
    private String strRepresentative;
   // private String strDate;
    private String entryBy;
    private String entryDate;
    private String strFormationDate;
    private String idUP;
    private String strUpazilla;
    private String idGroup;
    private String strGroup;
    private String idGroupCat;
    private String strGroupCat;

    CommunityGroupDataModel grpData;
    private String idOrg;
    private String strOrg;

    private String idStaff;
    private String strStaff;
    private Spinner spOrg;
    private EditText edtGroupName;
    private boolean addNewFlag = false;
    private String idDistrict;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_grp_details);
        sqlH = new SQLiteHandler(this);
        viewReferences();

        Intent intent = getIntent();


        addNewFlag = intent.getBooleanExtra(KEY.ADD_FLAG_KEY, false);
        if (!addNewFlag) {
            edtGroupName.setVisibility(View.GONE);
            grpData = intent.getParcelableExtra(KEY.COMMUNITY_GRP_DATA_OBJECT_KEY);
            Log.d("SAL", "getCommuCategoriesName: " + grpData.getCommuCategoriesName()
                    + "getCommunityGroupName: " + grpData.getCommunityGroupName()
                    + "getCommunityGroupCode: " + grpData.getCommunityGroupCode()
                    + "getAwardCode: " + grpData.getAwardCode()
                    + "getAwardName: " + grpData.getAwardName()
                    + "getAwardName: " + grpData.getProgramName()
                    + "getProgramCode: " + grpData.getProgramCode()
                    + "getLayr2Code: " + grpData.getLayr2Code()
                    + "getLayr2Name: " + grpData.getLayr2Name()
                    + "\ngetCommuCategoriesCode: " + grpData.getCommuCategoriesCode()
                    + "\ngetOrgonizationCode: " + grpData.getOrgonizationCode()
                    + "\ngetOrgonizationName: " + grpData.getOrgonizationName()
                    + "\ngetStaffCode: " + grpData.getStaffCode()
                    + "\ngetStaffName: " + grpData.getStaffName()
                    + "\ngetRepName: " + grpData.getRepName()
                    + "\ngetRepPhoneNo: " + grpData.getRepPhoneNo()
                    + "\ngetFormation: " + grpData.getFormation()
                    + "\ngetStatus: " + grpData.getStatus()
                    + "\ngetActive: " + grpData.getActive()
            );

            idAward = grpData.getAwardCode();
            strAward = grpData.getAwardName();
            idProgram = grpData.getProgramCode();
            strProgram = grpData.getProgramShortName();
            idUP = grpData.getLayr2Code();
            strUpazilla = grpData.getLayr2Name();
            idGroup = grpData.getCommunityGroupCode();
            strGroup = grpData.getCommunityGroupName();
            idGroupCat = grpData.getCommuCategoriesCode();
            strGroupCat = grpData.getCommuCategoriesName();
            idOrg = grpData.getOrgonizationCode();
            strOrg = grpData.getOrgonizationName();
            idStaff = grpData.getStaffCode();
            strStaff = grpData.getStaffName();
            edtRepresentative.setText(grpData.getRepName());

            edtContactNo.setText(grpData.getRepPhoneNo());
            tvFormation.setText(grpData.getFormation());

            idStatus = grpData.getStatus();
            idActive = grpData.getActive();
        } else {
            spGroup.setVisibility(View.GONE);
            idDonor = intent.getStringExtra(KEY.DONOR_CODE);
            idAward = intent.getStringExtra(KEY.AWARD_CODE);
            idProgram = intent.getStringExtra(KEY.PROGRAM_CODE);
            strProgram = sqlH.getProgramShortName(idAward, idDonor, idProgram);

        }


        loadCountry();
        //loadAward(idCountry);
        // staffName();
        loadActiveStatus();
        loadStatus();
        buttonActionListener();
        tvFormation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFormationDate();
            }
        });

        getEntryCredentials();
    }

    private void viewReferences() {
        spCountry = (Spinner) findViewById(R.id.sp_grp_details_country);
        spAward = (Spinner) findViewById(R.id.sp_grp_details_award);
        spProgram = (Spinner) findViewById(R.id.sp_grp_details_program);
        // spOrganization = (Spinner) findViewById(R.id.sp_grp_details_organization);
        spStatus = (Spinner) findViewById(R.id.sp_grp_details_status);
        spActive = (Spinner) findViewById(R.id.sp_grp_details_active);
        spUpazilla = (Spinner) findViewById(R.id.sp_grp_details_ta);
        spGroup = (Spinner) findViewById(R.id.sp_grp_details_group);
        spGroupCategories = (Spinner) findViewById(R.id.sp_grp_details_category);
        spStaffName = (Spinner) findViewById(R.id.sp_grp_details_staff_name);
        tvFormation = (TextView) findViewById(R.id.tv_grp_details_formation);
        edtRepresentative = (EditText) findViewById(R.id.edt_grp_details_representative);
        edtContactNo = (EditText) findViewById(R.id.edt_grp_details_contact_no);
        btnSave = (Button) findViewById(R.id.btn_grp_details_save);
        btnDelete = (Button) findViewById(R.id.btn_grp_details_delete);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        btnBackward = (Button) findViewById(R.id.btnRegisterFooter);

        spOrg = (Spinner) findViewById(R.id.sp_grp_details_organization);
        edtGroupName = (EditText) findViewById(R.id.edt_communityName);
        setHomeButton();
        setSaveButton();
        setBackwardButton();
        setDeleteButton();

    }


    private void setHomeButton() {
        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 10, 180, 10);
    }

    private void setSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnSave.setPadding(180, 10, 180, 10);
    }

    private void setBackwardButton() {
        btnBackward.setText("");
        Drawable backwardImage = getResources().getDrawable(R.drawable.goto_back);
        btnBackward.setCompoundDrawablesRelativeWithIntrinsicBounds(backwardImage, null, null, null);
        btnBackward.setPadding(180, 10, 180, 10);
    }

    private void setDeleteButton() {
        btnDelete.setText("");
        Drawable deleteImage = getResources().getDrawable(R.drawable.assign);
        btnDelete.setCompoundDrawablesRelativeWithIntrinsicBounds(deleteImage, null, null, null);
        btnDelete.setPadding(180, 10, 180, 10);
    }

    private void loadCountry() {

        SharedPreferences settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        int position = 0;
        String criteria = "";

        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        Log.d("NIR1", "operation mode : " + operationMode);
        switch (operationMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
                criteria = " INNER JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " ON "
                        + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = "
                        + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL;


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                criteria = " INNER JOIN " + SQLiteHandler.SELECTED_FDP_TABLE + " ON "
                        + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = "
                        + SQLiteHandler.SELECTED_FDP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL;

                break;
            case UtilClass.SERVICE_OPERATION_MODE:
                criteria = " INNER JOIN " + SQLiteHandler.SELECTED_SERVICE_CENTER_TABLE + " ON "
                        + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = "
                        + SQLiteHandler.SELECTED_SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL;


                break;
        }


        // Spinner Drop down elements for District
        List<SpinnerHelper> listCountry = sqlH.getListAndID(SQLiteHandler.COUNTRY_TABLE, criteria, null, true);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listCountry);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spCountry.setAdapter(dataAdapter);


        if (idCountry != null) {
            for (int i = 0; i < spCountry.getCount(); i++) {
                String district = spCountry.getItemAtPosition(i).toString();
                if (district.equals(strCountry)) {
                    position = i;
                }
            }
            spCountry.setSelection(position);
        }


        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getValue();
                idCountry = ((SpinnerHelper) spCountry.getSelectedItem()).getId();
                // create method load the .
                if (idCountry.length() > 2) {

                    loadAward(idCountry);


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    /**
     * LOAD :: UPAZILLA=loadLayR2List
     */
    private void loadLayR2List(String cCode) {
        int position = 0;
        String criteria = "SELECT " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.DISTRICT_CODE_COL + " || '' || " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPCODE_COL
                + ", " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " FROM " + SQLiteHandler.UPAZILLA_TABLE
                + " INNER JOIN "

                + SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE + " AS geo "
                + " ON geo." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND geo." + SQLiteHandler.DISTRICT_CODE_COL + " = " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.DISTRICT_CODE_COL
                + " AND geo." + SQLiteHandler.UPCODE_COL + " = " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPCODE_COL
                + " WHERE " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'"
                + " AND ( " + SQLiteHandler.BTN_NEW_COL + " = 1 OR "
                + SQLiteHandler.BTN_SAVE_COL + " = 1 OR "
                + SQLiteHandler.BTN_DEL_COL + " ) "
                + " GROUP BY " +
                SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPCODE_COL + ", " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL;
        // SQLiteQuery.getUpzillaJoinQuery(idCountry, idDist);

        // Spinner Drop down elements for District
        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUpazilla);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spUpazilla.setAdapter(dataAdapter);


        if (idUP != null) {

            for (int i = 0; i < spUpazilla.getCount(); i++) {
                String upazilla = spUpazilla.getItemAtPosition(i).toString();
                if (upazilla.equals(strUpazilla)) {
                    position = i;
                }
            }
            spUpazilla.setSelection(position);

            if (!addNewFlag) {
                spUpazilla.setEnabled(false);
            }

        }


        spUpazilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strUpazilla = ((SpinnerHelper) spUpazilla.getSelectedItem()).getValue();
                idUP = ((SpinnerHelper) spUpazilla.getSelectedItem()).getId();

                if (idUP.length() > 0) {
                    idDistrict = idUP.substring(0, 2);
                    idUP = idUP.substring(0, 2);
                    loadGroupCategory(idCountry, idDonor, idAward, idProgram);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadStaffName(String cCode, String orgCode) {
        int position = 0;


        String criteria = "SELECT " + SQLiteHandler.STAFF_ID_COL
                + ", " + SQLiteHandler.STAFF_NAME_COL
                + " FROM " + SQLiteHandler.STAFF_MASTER_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE + " ='" + cCode + "'"
                + " AND " + SQLiteHandler.ORG_CODE_COL + " ='" + orgCode + "'";

        // Spinner Drop down elements for District
        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUpazilla);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spStaffName.setAdapter(dataAdapter);


        if (idStaff != null) {

            for (int i = 0; i < spStaffName.getCount(); i++) {
                String staffName = spStaffName.getItemAtPosition(i).toString();
                if (staffName.equals(strStaff)) {
                    position = i;
                }
            }
            spStaffName.setSelection(position);
        }


        spStaffName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //strUpazilla = parent.getItemAtPosition(position).toString();
                strStaff = ((SpinnerHelper) spStaffName.getSelectedItem()).getValue();
                idStaff = ((SpinnerHelper) spStaffName.getSelectedItem()).getId();

                if (idStaff.length() > 0) {
                    //  idDistrict = idUP.substring(0, 2);
                    //   idUP = idUP.substring(0, 2);
                    // loadGroupCategory(idCountry, idDonor, idAward, idProgram);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void loadORGanization(final String cCode, final String donorCode, final String awardCode) {
        int position = 0;
        String criteria = "SELECT ProgOrgNRole.OrgCode ,  ProgOrgN.OrgNName" +
                "                                FROM ProgOrgNRole INNER JOIN " +
                "                                ProgOrgN ON ProgOrgNRole.OrgCode = ProgOrgN.OrgCode  " +
                "                                WHERE (ProgOrgNRole.CountryCode = '" + cCode + "')" +
                "                                AND (ProgOrgNRole.DonorCode = '" + donorCode + "') " +
                "                                AND (ProgOrgNRole.AwardCode = '" + awardCode + "') " +
                "                                AND (ProgOrgNRole.ImpYN = 'Y')" +
                "                                ORDER BY ProgOrgN.OrgNName";


        // Spinner Drop down elements for District

        List<SpinnerHelper> listUpazilla = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listUpazilla);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spOrg.setAdapter(dataAdapter);


        if (idOrg != null) {

            for (int i = 0; i < spOrg.getCount(); i++) {
                String orgation = spOrg.getItemAtPosition(i).toString();
                if (orgation.equals(strOrg)) {
                    position = i;
                }
            }
            spOrg.setSelection(position);
        }


        spOrg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //strUpazilla = parent.getItemAtPosition(position).toString();
                strOrg = ((SpinnerHelper) spOrg.getSelectedItem()).getValue();
                idOrg = ((SpinnerHelper) spOrg.getSelectedItem()).getId();

                if (idOrg.length() > 0)
                    loadStaffName(idCountry, idOrg);


                //Log.d(TAG, "Upazilla selected: " + strUpazilla);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

            /**
             * disable selection of the spinner
             */
            if (!addNewFlag) {
                spGroupCategories.setEnabled(false);
            }


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


    private void loadAward(final String idCountry) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + idCountry + "'";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_AWARD_TABLE, criteria, null, false);

        listAward.remove(0);
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
                if (awardCode.length() > 2) {
                    idDonor = awardCode.substring(0, 2);
                    idAward = awardCode.substring(2);
                    if (addNewFlag) {
                        loadProgram(idCountry, idAward, idDonor);
                        loadORGanization(idCountry, idAward, idDonor);
                    } else {
                        loadProgram(idCountry, idAward, idDonor);
                    }


                    Log.d(TAG, "idAward : " + idAward + " donor id :" + idAward.substring(0, 2));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


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
            /**
             * disable selection of the spinner
             */
            if (!addNewFlag) {
                spGroup.setEnabled(false);
            }
        }


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();
                Log.d("HEO", "Group  ,idGroup:" + idGroup + " strGroup : " + strGroup);
                if (idGroup.length() > 2) {


                    loadORGanization(idCountry, idDonor, idAward);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


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
            // block the  control selection of
            if (!addNewFlag) {
                spProgram.setEnabled(false);
            }
        }
        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
                if (idProgram.length() > 2) {
                    loadLayR2List(idCountry);
                    Log.d(TAG, "idProgram: " + idProgram + "strProgram: " + strProgram);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

 /*   private void staffName() {
        spStaffName.setText(getUserName());
    }*/

    private void loadActiveStatus() {
        int pos;

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

    private void loadStatus() {
        int pos = 0;

        ArrayAdapter<CharSequence> adptMartial = ArrayAdapter.createFromResource(
                this, R.array.arrStatus, R.layout.spinner_layout);

        adptMartial.setDropDownViewResource(R.layout.spinner_layout);
        spStatus.setAdapter(adptMartial);
        if (idStatus != null) {
            if (idStatus.equals("N"))
                pos = 0;
            else
                pos = 1;

            spStatus.setSelection(pos);
        }


        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String strStatus = parent.getItemAtPosition(position).toString();

                if (strStatus.equals("NEW"))
                    idStatus = "N";

                else
                    idStatus = "E";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void buttonActionListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getText();
                String entryBy = getStaffID();

                SQLServerSyntaxGenerator syntax = new SQLServerSyntaxGenerator();
                syntax.setAdmCountryCode(idCountry);
                syntax.setAdmDonorCode(idDonor);
                syntax.setAdmAwardCode(idAward);
                syntax.setProgCode(idProgram);
                syntax.setGrpCode(idGroup);
                syntax.setOrganizationCode(idOrg);
                syntax.setActive(idActive);
                //syntax.setIrrigationSystemUsed(null);
                //syntax.setLandSizeUnderIrrigation(null);
                //  syntax.setFundSupport(null);
                syntax.setRepresentativeName(strRepresentative);
                syntax.setRepresentativePhoneNumber(strContactNo);
                syntax.setFormationDate(strFormationDate);
                syntax.setStaffCode(idStaff);
                syntax.setEntryBy(getStaffID());
                syntax.setEntryDate(entryDate);

                String SrvCenterCode = "";

                String GrpName = edtGroupName.getText().toString();


                if (!addNewFlag) {
                    syntax.setGrpCode(idGroup);

                    sqlH.updateIntoGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup, idOrg, idStaff, null, null, null, idActive, strRepresentative, strContactNo, strFormationDate, null, idStatus, entryBy, entryDate, null, null);
                    sqlH.insertIntoUploadTable(syntax.updateIntoCommunityGrpDetail());
                } else {
                    idGroup = sqlH.getNextGroupId(idCountry, idDonor, idAward, idProgram);
                    Log.d("CHUP","idGroup:"+idGroup);
                    syntax.setGrpCode(idGroup);


                    syntax.setGrpName(GrpName);
                    syntax.setGrpCatCode(idGroupCat);
                    syntax.setLayR1ListCode(idDistrict);
                    syntax.setLayR2ListCode(idUP);


                    sqlH.addCommunityGroup(idCountry, idDonor, idAward, idProgram, idGroup, GrpName, idGroupCat, idDistrict, idUP, SrvCenterCode);
                    sqlH.insertIntoUploadTable(syntax.insertIntoCommunityGroupTable());

                    sqlH.addIntoGroupDetails(idCountry, idDonor, idAward, idProgram, idGroup, idOrg, getUserID(), null, null, null, idActive, strRepresentative, strContactNo, strFormationDate, null, idStatus, entryBy, entryDate, null, null);


                    sqlH.insertIntoUploadTable(syntax.insertIntoCommunityGrpDetail());

                }


                Toast.makeText(CommunityGrpDetails.this, "Save Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        btnBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGroupSear = new Intent(CommunityGrpDetails.this, GroupSearchPage.class);
                iGroupSear.putExtra(KEY.COUNTRY_ID, idCountry);
                iGroupSear.putExtra(KEY.STR_COUNTRY, strCountry);
                startActivity(iGroupSear);

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunityGrpDetails.this, MemberSearchPage.class);
                intent.putExtra(KEY.COUNTRY_ID, idCountry);
                finish();
                startActivity(intent);
            }
        });
    }


    private void getText() {
        strContactNo = edtContactNo.getText().toString();
        strRepresentative = edtRepresentative.getText().toString();
        strFormationDate = tvFormation.getText().toString();


        if (strContactNo.length() > 0) {
            //TODO: DO SOME WORK;
        } else {
            //TODO: Nothing To DO;
        }

        if (strRepresentative.length() > 0) {
            //TODO:: DO SOME WORK
        } else {
            //TODO:: NOthing To Do;
        }
        if (tvFormation.length() > 0) {
            //TODO:: DO SOME STAFF
        }
    }


    //TODO: DATE SETTINGS
    public void setFormationDate() {
        new DatePickerDialog(mContext, d, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updateFormationDate() {
        tvFormation.setText(formatUSA.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFormationDate();
        }
    };


    private void getEntryCredentials() {
        try {
            entryBy = getStaffID();
            entryDate = getDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
