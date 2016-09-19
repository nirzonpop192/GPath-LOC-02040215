package com.siddiquinoor.restclient.activity;

/**
 * Activity for presenting the Dashboard of the application
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.gps_sub.SearchLocation;
import com.siddiquinoor.restclient.data_model.AGR_DataModel;
import com.siddiquinoor.restclient.data_model.CTDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.SyncDatabase;
import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.parse.JsonDeserialization;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    public static final String ADM_COUNTRY_CODE = "AdmCountryCode";
    public static final String ADM_DONOR_CODE = "AdmDonorCode";
    public static final String ADM_AWARD_CODE = "AdmAwardCode";
    public static final String LAY_R_1_LIST_CODE = "LayR1ListCode";
    public static final String LAY_R_2_LIST_CODE = "LayR2ListCode";
    public static final String LAY_R_3_LIST_CODE = "LayR3ListCode";
    public static final String LAY_R_4_LIST_CODE = "LayR4ListCode";
    public static final String PROG_CODE = "ProgCode";
    public static final String SRV_CODE = "SrvCode";
    public static final String OP_MONTH_CODE = "OpMonthCode";
    public static final String FDP_CODE = "FDPCode";
    public static final String ID = "ID";
    //  public static final String ID = MainActivity.ID;
    public static final String DIST_STATUS = "DistStatus";
    public static final String HHID = "HHID";
    public static final String MEM_ID = "MemID";
    public static final String GEO_LAY_R_CODE = "GeoLayRCode";
    public static final String GEO_LAY_R_NAME = "GeoLayRName";
    public static final String LAY_R_4_LIST_NAME = "LayR4ListName";
    public static final String ADM_PROG_CODE = "AdmProgCode";
    public static final String ADM_SRV_CODE = "AdmSrvCode";
    public static final String GRD_CODE = "GRDCode";
    public static final String DEFAULT_CAT_ACTIVE = "DefaultCatActive";
    public static final String GRD_TITLE = "GRDTitle";
    public static final String DEFAULT_CAT_EXIT = "DefaultCatExit";
    public static final String LAY_R_1_CODE = "LayR1Code";
    public static final String LAY_R_2_CODE = "LayR2Code";
    public static final String WH_CODE = "WHCode";
    public static final String FDP_CAT_CODE = "FDPCatCode";
    public static final String FDP_NAME = "FDPName";
    public static final String RPT_GROUP = "RptGroup";
    public static final String RPT_CODE = "RptCode";
    public static final String REQUEST_SL = "RequestSL";
    public static final String BTN_NEW1 = "btnNew";
    public static final String BTN_NEW = BTN_NEW1;
    public static final String BTN_SAVE = "btnSave";
    public static final String BTN_DEL = "btnDel";
    public static final String PLANTING_VALUE_CHAIN_CROP = "PlantingValueChainCrop";
    public static final String VULNERABLE_HH = "VulnerableHH";
    public static final String WINTER_CULTIVATION = "WinterCultivation";
    public static final String WILLINGNESS = "Willingness";
    public static final String DEPEND_ON_GANYU = "DependOnGanyu";
    public static final String PROG_NAME = "ProgName";
    public static final String PROG_SHORT_NAME = "ProgShortName";
    public static final String REG_N_DATE = "RegNDate";
    public static final String ENTRY_BY = "EntryBy";
    public static final String ENTRY_DATE = "EntryDate";
    public static final String ELDERLY_YN = "ElderlyYN";
    public static final String LAND_SIZE = "LandSize";
    public static final String REASON_CODE = "ReasonCode";
    public static final String REQUEST_DATE = "RequestDate";
    public static final String PRINT_DATE = "PrintDate";
    public static final String PRINT_BY = "PrintBy";
    public static final String DELIVERY_DATE = "DeliveryDate";
    public static final String DELIVERED_BY = "DeliveredBy";
    public static final String DEL_STATUS = "DelStatus";
    public static final String P_BSC_MEM_NAME_1_FIRST = "P_BSCMemName1_First";
    public static final String P_BSC_MEM_NAME_1_MIDDLE = "P_BSCMemName1_Middle";
    public static final String P_BSC_MEM_NAME_1_LAST = "P_BSCMemName1_Last";
    public static final String P_BSC_MEM_1_TITLE_POSITION = "P_BSCMem1_TitlePosition";
    public static final String PROXY_TYPE_ID = "Proxy_Type_ID";
    public static final String PROXY_ID_NO = "Proxy_ID_NO";
    public static final String P_BSC_MEM_NAME_2_FIRST = "P_BSCMemName2_First";
    public static final String P_BSC_MEM_NAME_2_MIDDLE = "P_BSCMemName2_Middle";
    public static final String P_BSC_MEM_NAME_2_LAST = "P_BSCMemName2_Last";
    public static final String P_BSC_MEM_2_TITLE_POSITION = "P_BSCMem2_TitlePosition";
    public static final String V_BSC_MEM_NAME_1_FIRST = "V_BSCMemName1_First";
    public static final String V_BSC_MEM_NAME_1_MIDDLE = "V_BSCMemName1_Middle";
    public static final String V_BSC_MEM_NAME_1_LAST = "V_BSCMemName1_Last";
    public static final String V_BSC_MEM_1_TITLE_POSITION = "V_BSCMem1_TitlePosition";
    public static final String V_BSC_MEM_NAME_2_FIRST = "V_BSCMemName2_First";
    public static final String V_BSC_MEM_NAME_2_MIDDLE = "V_BSCMemName2_Middle";
    public static final String V_BSC_MEM_NAME_2_LAST = "V_BSCMemName2_Last";
    public static final String V_BSC_MEM_2_TITLE_POSITION = "V_BSCMem2_TitlePosition";
    public static final String PROXY_DESIGNATION = "Proxy_Designation";
    public static final String PROXY_NAME_FIRST = "Proxy_Name_First";
    public static final String PROXY_NAME_MIDDLE = "Proxy_Name_Middle";
    public static final String PROXY_NAME_LAST = "Proxy_Name_Last";
    public static final String PROXY_BIRTH_YEAR = "Proxy_BirthYear";
    public static final String PROXY_PHOTO = "Proxy_Photo";
    public static final String CHILD_DOB = "ChildDOB";
    public static final String ELDERLY = "Elderly";
    public static final String BIRTH_YEAR = "BirthYear";
    public static final String PHOTO = "Photo";
    public static final String TYPE_ID = "Type_ID";
    public static final String ADM_COUNTRY_NAME = "AdmCountryName";
    public static final String START_DATE = "StartDate";
    public static final String END_DATE = "EndDate";
    public static final String GRP_CODE = "GrpCode";
    public static final String GRP_NAME = "GrpName";
    public static final String DESCRIPTION = "Description";
    public static final String SUB_GRP_CODE = "SubGrpCode";
    public static final String SUB_GRP_NAME = "SubGrpName";


    // json array key name
    public static final String SERVICE_TABLE_JSON_A = "service_table";
    public static final String REGN_LM_JSON_A = "regn_lm";
    public static final String REGN_PW_JSON_A = "regn_pw";

    public static final String REG_N_AGR_JSON_A = "reg_n_agr";


    public static final String REG_MEM_CARD_REQUEST_JSON_A = "reg_mem_card_request";
    public static final String STAFF_FDP_ACCESS_JSON_A = "staff_fdp_access";
    public static final String FDP_MASTER_JSON_A = "fdp_master";
    public static final String DISTRIBUTION_TABLE_JSON_A = "distribution_table";
    public static final String LUP_SRV_OPTION_LIST = "lup_srv_option_list";
    public static final String CARD_PRINT_REASON = "card_print_reason";
    public static final String REPORT_TEMPLATE = "report_template";
    public static final String MEMBERS_JSON_A = "members";
    public static final String REGISTRATION_JSON_A = "registration";
    public static final String VILLAGE_JSON_A = "village";
    public static final String UNIT_JSON_A = "unit";
    public static final String UPAZILLA = "upazilla";
    public static final String DISTRICT = "district";
    public static final String REG_LUP_GRADUATION_JSON_A = "reg_lup_graduation";
    public static final String LB_REG_HH_CATEGORY_JSON_A = "lb_reg_hh_category";
    public static final String REGN_CA_2 = "regn_ca2";
    public static final String ADM_PROGRAM_MASTER_JSON_A = "adm_program_master";
    public static final String ADM_SERVICE_MASTER_JSON_A = "adm_service_master";
    public static final String ADM_COUNTRY_AWARD_JSON_A = "adm_countryaward";
    public static final String GPS_LOCATION_JSON_A = "gps_location";
    public static final String GPS_SUBGROUP_JSON_A = "gps_subgroup";
    public static final String GPS_GROUP_JSON_A = "gps_group";
    public static final String VALID_DATES_JSON_A = "valid_dates";
    public static final String COUNTRIES_JSON_A = "countries";

    public static final String USR_ID = "UsrID";
    public static final String REGN_CU_2_JSON_A = "regn_cu2";
    public static final String STAFF_ACCESS_INFO_JSON_A = "staff_access_info";
    public static final String RELATION_JSON_A = "relation";
    public static final String USER_JSON_A = "user";
    public static final String ADM_DONOR_JSON_A = "adm_donor";
    public static final String ADM_OP_MONTH_JSON_A = "adm_op_month";
    public static final String ADM_COUNTRY_PROGRAM_JSON_A = "adm_country_program";
    public static final String DOB_SERVICE_CENTER_JSON_A = "dob_service_center";
    public static final String LAYER_LABELS_JSON_A = "layer_labels";

    public static final String FOOD_FLAG = "FoodFlag";
    public static final String N_FOOD_FLAG = "NFoodFlag";
    public static final String CASH_FLAG = "CashFlag";
    public static final String VO_FLAG = "VOFlag";

    public static final String SRV_CENTER_CODE = "SrvCenterCode";
    public static final String SRV_CENTER_NAME = "SrvCenterName";
    public static final String BTN_PEPR = "btnPepr";
    public static final String BTN_APRV = "btnAprv";
    public static final String BTN_REVW = "btnRevw";
    public static final String BTN_VRFY = "btnVrfy";
    public static final String BTN_D_TRAN = "btnDTran";
    public static final String STF_CODE = "StfCode";
    public static final String LAY_R_LIST_CODE = "LayRListCode";
    public static final String HH_HEAD_CAT_CODE = "HHHeadCatCode";
    public static final String CAT_NAME = "CatName";

    public static final String LAY_R_LIST_NAME = "LayRListName";
    public static final String LAY_R_2_LIST_NAME = "LayR2ListName";
    public static final String LAY_R_3_LIST_NAME = "LayR3ListName";
    public static final String HH_COUNT = "HHCount";
    public static final String HH_RELATION_CODE = "HHRelationCode";
    public static final String RELATION_NAME = "RelationName";
    public static final String RPT_LABEL = "RptLabel";
    public static final String RPT_G_N_CODE = "Code";
    public static final String REASON_TITLE = "ReasonTitle";
    public static final String LUP_OPTION_CODE = "LUPOptionCode";
    public static final String LUP_OPTION_NAME = "LUPOptionName";
    public static final String USR_LOG_IN_NAME = "UsrLogInName";
    public static final String USR_EMAIL_VERIFICATION = "UsrEmailVerification";
    public static final String USR_STATUS = "UsrStatus";
    public static final String USR_EMAIL = "UsrEmail";
    public static final String USR_LAST_NAME = "UsrLastName";
    public static final String USR_FIRST_NAME = "UsrFirstName";
    public static final String USR_LOG_IN_PW = "UsrLogInPW";
    public static final String REG_M_ASSIGN_PROG_SRV_JSON_A = "reg_m_assign_prog_srv";
    public static final String CA_2_DOB = "CA2DOB";
    public static final String CA_2_GRD_DATE = "CA2GRDDate";
    public static final String LMGRD_DATE = "LMGRDDate";
    public static final String LMDOB = "LMDOB";
    public static final String CU_2_DOB = "CU2DOB";
    public static final String CU_2_GRD_DATE = "CU2GRDDate";
    public static final String LOCATION_CODE = "LocationCode";
    public static final String LOCATION_NAME = "LocationName";
    public static final String LONG = "Long";
    public static final String LATD = "Latd";
    public static final String AWARD_REF_NUMBER = "AwardRefNumber";
    public static final String AWARD_START_DATE = "AwardStartDate";
    public static final String AWARD_END_DATE = "AwardEndDate";
    public static final String AWARD_SHORT_NAME = "AwardShortName";
    public static final String AWARD_STATUS = "AwardStatus";
    public static final String ADM_DONOR_NAME = "AdmDonorName";
    public static final String OP_CODE = "OpCode";
    public static final String SRV_SL = "SrvSL";
    public static final String SRV_DT = "SrvDT";
    public static final String SRV_STATUS = "SrvStatus";
    public static final String DIST_DT = "DistDT";
    public static final String GRD_DATE = "GRDDate";
    public static final String LMP_DATE = "LMPDate";
    public static final String PWGRD_DATE = "PWGRDDate";
    public static final String AFT_CNT_WAGE_ENR = "AFTCntWageEnr";
    public static final String BRF_CNT_WAGE_ENR = "BRFCntWageEnr";
    public static final String BRF_ACRE_ORCHARDS = "BRFAcreOrchards";
    public static final String BRF_VAL_ORCHARDS = "BRFValOrchards";
    public static final String AFT_ACRE_ORCHARDS = "AFTAcreOrchards";
    public static final String AFT_VAL_ORCHARDS = "AFTValOrchards";
    public static final String BRF_VAL_EMPLOYMENT = "BRFValEmployment";
    public static final String AFT_VAL_EMPLOYMENT = "AFTValEmployment";
    public static final String BRF_VAL_REMITTANCES = "BRFValRemittances";
    public static final String EXTRA_CHRONICALLY_ILL_DISABLED_PERSON_BECAUSE_EBOLA = "ExtraChronicallyIllDisabledPersonBecauseEbola";
    public static final String BRF_CNT_CATTLE = "BRFCntCattle";
    public static final String BRF_VAL_CATTLE = "BRFValCattle";
    public static final String DISTRICT_NAME = "DistrictName";
    public static final String UPAZILLA_NAME = "UpazillaName";
    public static final String UNIT_NAME = "UnitName";
    public static final String VILLAGE_NAME = "VillageName";
    public static final String HH_MEM_ID = "HHMemID";
    public static final String MEM_NAME = "MemName";
    public static final String MEM_SEX = "MemSex";
    public static final String HH_RELATION = "HHRelation";
    public static final String DISABLED = "Disabled";
    public static final String MEM_AGE = "MemAge";
    public static final String MARITAL_STATUS = "MaritalStatus";
    public static final String CONTACT_NO = "ContactNo";
    public static final String MEM_OTHER_ID = "MemOtherID";
    public static final String MEM_NAME_FIRST = "MemName_First";
    public static final String MEM_NAME_MIDDLE = "MemName_Middle";
    public static final String MEM_NAME_LAST = "MemName_Last";
    public static final String REGISTRATION_ID = "RegistrationID";
    public static final String PERSON_NAME = "PersonName";
    public static final String SEX = "SEX";
    public static final String HH_SIZE = "HHSize";
    public static final String LATITUDE = "Latitude";
    public static final String LONGITUDE = "Longitude";
    public static final String AG_LAND = "AGLand";
    public static final String V_STATUS = "VStatus";
    public static final String M_STATUS = "MStatus";
    public static final String VSLA_GROUP = "VSLAGroup";
    public static final String GPS_LONG_SWAP = "GPSLongSwap";
    public static final String HH_HEAD_CAT = "HHHeadCat";
    public static final String LT_2_YRS_M = "LT2yrsM";
    public static final String LT_2_YRS_F = "LT2yrsF";
    public static final String M_2_TO_5_YRS = "M2to5yrs";
    public static final String F_2_TO_5_YRS = "F2to5yrs";
    public static final String M_6_TO_12_YRS = "M6to12yrs";
    public static final String AFT_CNT_CATTLE = "AFTCntCattle";

    public static final String F_6_TO_12_YRS = "F6to12yrs";
    public static final String M_13_TO_17_YRS = "M13to17yrs";
    public static final String BRF_CNT_OTHER = "BRFCntOther";
    public static final String BRF_VAL_OTHER = "BRFValOther";
    public static final String AFT_CNT_OTHER = "AFTCntOther";
    public static final String AFT_VAL_OTHER = "AFTValOther";
    public static final String BRF_ACRE_CULTIVABLE = "BRFAcreCultivable";
    public static final String BRF_VAL_CULTIVABLE = "BRFValCultivable";
    public static final String F_13_TO_17_YRS = "F13to17yrs";
    public static final String ORPHN_LT_18_YRS_M = "Orphn_LT18yrsM";
    public static final String MONTH_LABEL = "MonthLabel";
    public static final String USA_START_DATE = "UsaStartDate";
    public static final String USA_END_DATE = "UsaEndDate";
    public static final String ORPHN_LT_18_YRS_F = "Orphn_LT18yrsF";
    public static final String ADLT_18_TO_59_M = "Adlt_18to59M";
    public static final String ADLT_18_TO_59_F = "Adlt_18to59F";
    public static final String ELD_60_P_M = "Eld_60pM";
    public static final String ELD_60_P_F = "Eld_60pF";
    public static final String PLW = "PLW";
    public static final String CHRONICALLY_ILL = "ChronicallyIll";
    public static final String LIVING_DECEASED_CONTRACT_EBOLA = "LivingDeceasedContractEbola";
    public static final String REG_N_CT_JSON_A = "reg_n_ct";
    public static final String C_11_CT_PR = "C11_CT_PR";
    public static final String C_21_CT_PR = "C21_CT_PR";
    public static final String C_31_CT_PR = "C31_CT_PR";
    public static final String C_32_CT_PR = "C32_CT_PR";
    public static final String C_33_CT_PR = "C33_CT_PR";
    public static final String C_34_CT_PR = "C34_CT_PR";
    public static final String C_35_CT_PR = "C35_CT_PR";
    public static final String C_36_CT_PR = "C36_CT_PR";
    public static final String C_37_CT_PR = "C37_CT_PR";
    public static final String C_38_CT_PR = "C38_CT_PR";
    public static final String EXTRA_ELDERLY_PERSON_BECAUSE_EBOLA = "ExtraElderlyPersonBecauseEbola";
    public static final String EXTRA_CHILD_BECAUSE_EBOLA = "ExtraChildBecauseEbola";
    public static final String AFT_VAL_CATTLE = "AFTValCattle";
    public static final String BRF_CNT_SHEEP_GOATS = "BRFCntSheepGoats";
    public static final String BRF_VAL_SHEEP_GOATS = "BRFValSheepGoats";
    public static final String MULTIPLE_SRV = "MultipleSrv";
    public static final String DISTRIBUTION_EXT_TABLE_JSON_A = "distribution_ext_table";
    public static final String VO_ITM_SPEC = "VOItmSpec";
    public static final String VO_ITM_UNIT = "VOItmUnit";
    public static final String VO_REF_NUMBER = "VORefNumber";
    public static final String VO_ITM_COST = "VOItmCost";
    public static final String DEFAULT_FOOD_DAYS = "DefaultFoodDays";
    public static final String DEFAULT_N_FOOD_DAYS = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS = "DefaultCashDays";
    public static final String DEFAULT_VO_DAYS = "DefaultVODays";
    public static final String SRV_SPECIFIC = "SrvSpecific";
    public static final String LIBERIA_COUNTRY_CODE = "0004";
    public static final String DIST_FLAG = "DistFlag";
    public static final String SRV_OP_MONTH_CODE = "SrvOpMonthCode";
    public static final String VO_ITM_MEAS_TABLE_JSON_A = "vo_itm_meas_table";
    public static final String LUP_GPS_TABLE_JSON_A = "lup_gps_table";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_JSON_A = "gps_sub_group_attributes";
    public static final String GPS_LOCATION_ATTRIBUTES_JSON_A = "gps_location_attributes";


    private final String TAG = MainActivity.class.getSimpleName();

    // for liberai developments  Mode
    private boolean DEVELOPMENTS_MODE = true;

    private SQLiteHandler db;
    // Connection detector class
    ConnectionDetector cd;
    // flag for Internet connection status
    Boolean isInternetPresent = false;

    public static Activity main_activity;
    private View my_view;

    AlertDialogManager alert;


    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private Button btnNewReg;
    private Button btnSummaryRep;
    ///private Button btnViewRec;
    private Button btnSyncRec;
    private SQLiteHandler sqlH;
    private Spinner spCountry;
    private String idCountry;
    private String strCountry;
    private static final String Y = "YES";
    private static final String N = "NO";
    // button declear here
    private Button btnService, btnGPS, btnAssign, btnGraduation, btnCardRequest, btnDistribution, btnGroup;

    private ProgressDialog progressDialog;

    private TextView textGeoData;
    private TextView textLastSync;
    private TextView textSyncRequired;
    private TextView textOperationMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_activity = this;
        spCountry = (Spinner) findViewById(R.id.spDCountry);// add the spinner
        sqlH = new SQLiteHandler(this); //  it should be other wise it will show null point Exception


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // connection manager
        cd = new ConnectionDetector(getApplicationContext());
        // find View by ID for all Views
        viewRefernce();


        SharedPreferences settings;

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstRun = settings.getBoolean(IS_APP_FIRST_RUN, false);

        operationMode(settings);
        txtName.setText(getUserName());
        textLastSync.setText(db.lastSyncStatus());






     /*   btnViewRec = (Button) findViewById(R.id.btnViewRecord);
        btnViewRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iView = new Intent(getApplicationContext(), MW_RegisterViewRecord.class);
                startActivity(iView);
                //main_activity.finish();
            }
        });*/


        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });


        buttonSetListener();
        // when the MainActivity run for first time  The JSon Data inject to the
        // SQLite database from text file
        if (isFirstRun) {
            SharedPreferences.Editor editor;
            settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            editor = settings.edit();
            new Inject_All_DataIntoSQLite().execute();
            editor.putBoolean(IS_APP_FIRST_RUN, false);
            editor.commit();
        }
        loadCountry();
        setAllButtonDisabled();
        viewAccessController(settings);
        //operationMode(settings);


    }

    /**
     * <p>
     * This method control the button view with respect to operation
     * </p>
     *
     * @param settings Shared Preference Object
     */

    private void viewAccessController(SharedPreferences settings) {
        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        Log.d("NIR1", "operation mode : " + operationMode);
        switch (operationMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
                btnNewReg.setEnabled(true);
                btnAssign.setEnabled(true);
                //   btnService.setEnabled(true);
                btnCardRequest.setEnabled(true);
                btnGraduation.setEnabled(true);
                btnGroup.setEnabled(true);


                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                btnDistribution.setEnabled(true);

                break;
            case UtilClass.SERVICE_OPERATION_MODE:
                btnService.setEnabled(true);

                break;
        }
    }


    private void buttonSetListener() {
        btnNewReg.setOnClickListener(this);
        btnSummaryRep.setOnClickListener(this);
        btnCardRequest.setOnClickListener(this);
        btnSyncRec.setOnClickListener(this);
        btnDistribution.setOnClickListener(this);
        btnService.setOnClickListener(this);
        btnGPS.setOnClickListener(this);
        btnGraduation.setOnClickListener(this);
        btnAssign.setOnClickListener(this);
        btnGroup.setOnClickListener(this);

    }

    /**
     * <p>
     * set all button disable first expect syn & summary report button
     * </p>
     */
    private void setAllButtonDisabled() {
        btnNewReg.setEnabled(false);
        btnCardRequest.setEnabled(false);
        btnDistribution.setEnabled(false);
        btnService.setEnabled(false);
        //  btnGPS.setEnabled(false);
        btnGraduation.setEnabled(false);
        btnAssign.setEnabled(false);
        btnGroup.setEnabled(false);

    }

    private void viewRefernce() {


        txtName = (TextView) findViewById(R.id.user_name);
        //txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        textGeoData = (TextView) findViewById(R.id.tv_geo_data_1);
        textLastSync = (TextView) findViewById(R.id.tv_last_sync);
        textSyncRequired = (TextView) findViewById(R.id.tv_sync_required);
        textOperationMode = (TextView) findViewById(R.id.tv_operation_mode);

        btnNewReg = (Button) findViewById(R.id.btnNewReg);
        btnSummaryRep = (Button) findViewById(R.id.btnSummaryReport);
        btnCardRequest = (Button) findViewById(R.id.btnCardRequest);
        btnGPS = (Button) findViewById(R.id.btnGPS);
        btnSyncRec = (Button) findViewById(R.id.btnSyncRecord);
        btnGraduation = (Button) findViewById(R.id.btnGraduation);
        btnAssign = (Button) findViewById(R.id.btnAssinge);
        btnService = (Button) findViewById(R.id.btnService);
        btnDistribution = (Button) findViewById(R.id.btnDistribution);
        textLastSync = (TextView) findViewById(R.id.tv_last_sync);
        btnGroup = (Button) findViewById(R.id.btnGroup);


        if (db.selectUploadSyntextRowCount() > 0) {
            textSyncRequired.setText(Y);
        } else {
            textSyncRequired.setText(N);
        }
        textOperationMode = (TextView) findViewById(R.id.tv_operation_mode);
        textGeoData = (TextView) findViewById(R.id.tv_geo_data_1);
    }

    private void synchronizeData(View v) {
        final AppController globalVariable = (AppController) getApplicationContext();
        globalVariable.setMainViewContext(v);
        MainTask main_task = new MainTask();
        main_task.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGroup:

                Intent iGroupSear = new Intent(getApplicationContext(), GroupSearchPage.class);
                iGroupSear.putExtra(KEY.COUNTRY_ID, idCountry);
                iGroupSear.putExtra(KEY.STR_COUNTRY, strCountry);
                startActivity(iGroupSear);

                break;
            case R.id.btnNewReg:
                Intent iReg;
                if (idCountry.equals(LIBERIA_COUNTRY_CODE)) {
                    iReg = new Intent(getApplicationContext(), RegisterLiberia.class);
                    iReg.putExtra("country_code", idCountry);
                    iReg.putExtra("country_name", strCountry);
                    startActivity(iReg);

                } else {
                    iReg = new Intent(getApplicationContext(), Register.class);
                    iReg.putExtra("country_code", idCountry);
                    iReg.putExtra("country_name", strCountry);
                    startActivity(iReg);
                }
                break;
            case R.id.btnSummaryReport:
                Intent iSumm = new Intent(getApplicationContext(), AllSummaryActivity.class);
                iSumm.putExtra(KEY.COUNTRY_ID, idCountry);
                iSumm.putExtra(KEY.STR_COUNTRY, strCountry);
                startActivity(iSumm);
                //main_activity.finish();
                break;
            case R.id.btnSyncRecord:
                my_view = v;
                isInternetPresent = cd.isConnectingToInternet();

                if (isInternetPresent)
                    synchronizeData(my_view);
                else {

                    showAlert("Check your internet connectivity!!");
                }
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                Date now = new Date();
                String SyncDate = date.format(now);
                db.insertIntoLastSyncTraceStatus(getUserID(), getUserName(), SyncDate);
                //textSyncRequired = (TextView)findViewById(R.id.tv_sync_required);
                textSyncRequired.setText(N);
                if (db.lastSyncStatus().equals("")) {
                    textLastSync.setText("N/A");
                } else {
                    textLastSync.setText(db.lastSyncStatus());
                }
                break;

            case R.id.btnCardRequest:
                finish();
                Intent iCardR = new Intent(getApplicationContext(), CardRequestActivity.class);
                iCardR.putExtra("ID_COUNTRY", idCountry);
                iCardR.putExtra("STR_COUNTRY", strCountry);
                startActivity(iCardR);
                break;
            case R.id.btnDistribution:
                finish();
                Intent iDist = new Intent(getApplicationContext(), DistributionActivity.class);
                iDist.putExtra(KEY.COUNTRY_ID, idCountry);
                iDist.putExtra(KEY.STR_COUNTRY, strCountry);
                iDist.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iDist);
                break;

            case R.id.btnService:
                Intent iSer = new Intent(getApplicationContext(), ServiceActivity.class);
                iSer.putExtra(KEY.COUNTRY_ID, idCountry);
                iSer.putExtra(KEY.STR_COUNTRY, strCountry);
                iSer.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iSer);
                break;
            case R.id.btnGPS:
                //   Intent iMap = new Intent(getApplicationContext(), MapActivity.class);
                finish();
                Intent iMap = new Intent(getApplicationContext(), SearchLocation.class);
                iMap.putExtra(KEY.COUNTRY_ID, idCountry);
                iMap.putExtra(KEY.STR_COUNTRY, strCountry);
                iMap.putExtra(KEY.DIR_CLASS_NAME_KEY, "MainActivity");
                startActivity(iMap);
                break;

            case R.id.btnGraduation:
                /**
                 *  when user press the assign page it will take to the member page
                 */

                Intent iMemSearchPage_1 = new Intent(getApplicationContext(), MemberSearchPage.class);
                iMemSearchPage_1.putExtra(KEY.COUNTRY_ID, idCountry);
                finish();
                startActivity(iMemSearchPage_1);

                break;

            case R.id.btnAssinge:
                /**
                 *  when user press the assign page it will take to the member page
                 */


                Intent iMemSearchPage = new Intent(getApplicationContext(), MemberSearchPage.class);
                iMemSearchPage.putExtra(KEY.COUNTRY_ID, idCountry);
                iMemSearchPage.putExtra(KEY.STR_COUNTRY, strCountry);

                /**
                 * rechek sub assigne page involed  or not
                 */
                iMemSearchPage.putExtra(OldAssignActivity.SUB_ASSIGN_DIR, false);
                finish();
                startActivity(iMemSearchPage);
                break;

        }

    }

    private class MainTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                //doSomeTasks();
            } catch (Exception e) {
                Log.e("Async task", "Task Failed " + e);
            }
            return "Success";
        }

        @Override
        protected void onPreExecute() {
            SyncDatabase sync = new SyncDatabase(getApplicationContext(), main_activity);
            sync.startTask();
        }

        ;

        @Override
        protected void onPostExecute(String result) {
            //finalizeSync();
            Log.d(TAG, "Task Finish");
            //SyncDatabase.pDialog.dismiss();
        }

        ;
    } // end Asynchronous class MainTask


    /**
     * LOAD :: COUNTRY
     */
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class Inject_RegNAssProgSrvDataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            hideDialog();


            loadCountry();

            // set the user name
            txtName.setText(getUserName());
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile("reg_ass_prog_srv_data");
            try {


                int size;

                JSONObject jObj = new JSONObject(retreiveData);

                if (!jObj.isNull(REG_M_ASSIGN_PROG_SRV_JSON_A)) {

                    JsonDeserialization.regNAssignProgSrvParser(jObj.getJSONArray(REG_M_ASSIGN_PROG_SRV_JSON_A), db);

                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(REGN_PW_JSON_A)) {// this is not servie
                    JSONArray regn_pws = jObj.getJSONArray(REGN_PW_JSON_A);


                    String AdmCountryCode;
                    String LayR1ListCode;
                    String LayR2ListCode;
                    String LayR3ListCode;
                    String LayR4ListCode;
                    String HHID;
                    String MemID;
                    String RegNDate;
                    String LMPDate;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String GRDCode;
                    String PWGRDDate;
                    size = regn_pws.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject regn_pw = regn_pws.getJSONObject(i);

                        AdmCountryCode = regn_pw.getString(ADM_COUNTRY_CODE);
                        LayR1ListCode = regn_pw.getString(LAY_R_1_LIST_CODE);
                        LayR2ListCode = regn_pw.getString(LAY_R_2_LIST_CODE);
                        LayR3ListCode = regn_pw.getString(LAY_R_3_LIST_CODE);
                        LayR4ListCode = regn_pw.getString(LAY_R_4_LIST_CODE);
                        HHID = regn_pw.getString(MainActivity.HHID);
                        MemID = regn_pw.getString(MEM_ID);
                        RegNDate = regn_pw.getString(REG_N_DATE);
                        LMPDate = regn_pw.getString(LMP_DATE);
                        AdmProgCode = regn_pw.getString(ADM_PROG_CODE);
                        AdmSrvCode = regn_pw.getString(ADM_SRV_CODE);
                        GRDCode = regn_pw.getString(GRD_CODE);
                        PWGRDDate = regn_pw.getString(PWGRD_DATE);


                        sqlH.addRegNPWFromOnLine(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, HHID, MemID, AdmProgCode, AdmSrvCode, RegNDate, GRDCode, LMPDate, PWGRDDate);//, SrvCenterCatCode, FDPCode);
                        //  Log.d(TAG, "Insert  into Pregnant women table" );
                    }
                }
                publishProgress(++progressIncremental);


                // Adding data into  regn_lm  Table


                if (!jObj.isNull(REGN_LM_JSON_A)) {// this is not servie
                    JSONArray regn_lms = jObj.getJSONArray(REGN_LM_JSON_A);


                    String AdmCountryCode;
                    String LayR1ListCode;
                    String LayR2ListCode;
                    String LayR3ListCode;
                    String LayR4ListCode;
                    String HHID;
                    String MemID;
                    String RegNDate;
                    String LMDOB;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String GRDCode;
                    String LMGRDDate;
                    String ChildName;
                    String ChildSex;
                    size = regn_lms.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject regn_lm = regn_lms.getJSONObject(i);

                        AdmCountryCode = regn_lm.getString(ADM_COUNTRY_CODE);
                        LayR1ListCode = regn_lm.getString(LAY_R_1_LIST_CODE);
                        LayR2ListCode = regn_lm.getString(LAY_R_2_LIST_CODE);
                        LayR3ListCode = regn_lm.getString(LAY_R_3_LIST_CODE);
                        LayR4ListCode = regn_lm.getString(LAY_R_4_LIST_CODE);
                        HHID = regn_lm.getString(MainActivity.HHID);
                        MemID = regn_lm.getString(MEM_ID);
                        RegNDate = regn_lm.getString(REG_N_DATE);
                        LMDOB = regn_lm.getString(MainActivity.LMDOB);
                        AdmProgCode = regn_lm.getString(ADM_PROG_CODE);
                        AdmSrvCode = regn_lm.getString(ADM_SRV_CODE);
                        GRDCode = regn_lm.getString(GRD_CODE);
                        LMGRDDate = regn_lm.getString(LMGRD_DATE);
                        ChildName = regn_lm.getString("ChildName");
                        ChildSex = regn_lm.getString("ChildSex");


                        db.addRegNLMFromOnLine(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, HHID, MemID, AdmProgCode, AdmSrvCode, RegNDate, GRDCode, LMDOB, LMGRDDate, ChildName, ChildSex);//, SrvCenterCatCode, FDPCode);


                    }
                }

                publishProgress(++progressIncremental);

                //regn_cu2 Table


                if (!jObj.isNull(REGN_CU_2_JSON_A)) {// this is not servie
                    JSONArray regn_cu2s = jObj.getJSONArray(REGN_CU_2_JSON_A);
                    size = regn_cu2s.length();


                    String AdmCountryCode;
                    String LayR1ListCode;
                    String LayR2ListCode;
                    String LayR3ListCode;
                    String LayR4ListCode;
                    String HHID;
                    String MemID;
                    String RegNDate;
                    String CU2DOB;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String GRDCode;
                    String CU2GRDDate;
                    String ChildName;
                    String ChildSex;
//                    Log.d("nir", "Size of RegN CU 2 : " + size);
                    for (int i = 0; i < size; i++) {
                        JSONObject regn_cu2 = regn_cu2s.getJSONObject(i);

                        AdmCountryCode = regn_cu2.getString(ADM_COUNTRY_CODE);
                        LayR1ListCode = regn_cu2.getString(LAY_R_1_LIST_CODE);
                        LayR2ListCode = regn_cu2.getString(LAY_R_2_LIST_CODE);
                        LayR3ListCode = regn_cu2.getString(LAY_R_3_LIST_CODE);
                        LayR4ListCode = regn_cu2.getString(LAY_R_4_LIST_CODE);
                        HHID = regn_cu2.getString(MainActivity.HHID);
                        MemID = regn_cu2.getString(MEM_ID);
                        RegNDate = regn_cu2.getString(REG_N_DATE);
                        CU2DOB = regn_cu2.getString(CU_2_DOB);
                        AdmProgCode = regn_cu2.getString(ADM_PROG_CODE);
                        AdmSrvCode = regn_cu2.getString(ADM_SRV_CODE);
                        GRDCode = regn_cu2.getString(GRD_CODE);
                        CU2GRDDate = regn_cu2.getString(CU_2_GRD_DATE);
                        ChildName = regn_cu2.getString("ChildName");
                        ChildSex = regn_cu2.getString("ChildSex");


                        db.addRegNCU2_FromOnLine(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, HHID, MemID, AdmProgCode, AdmSrvCode, RegNDate, GRDCode, CU2DOB, CU2GRDDate, ChildName, ChildSex);


                        Log.d("nir", "In Reg CU2 Table- AdmCountryCode :" + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " HHID : " + HHID + " MemID  : " + MemID + " AdmProgCode : " + AdmProgCode + " AdmSrvCode : " + AdmSrvCode + " RegNDate : " + RegNDate + " GRDCode : " + GRDCode +/* " EntryBy : " + EntryBy + " EntryDate : " + EntryDate + */" LMDOB : " + CU2DOB + " LMGRDDate : " + CU2GRDDate);// + " FDPCode  : " + FDPCode );
                    }
                }

                publishProgress(++progressIncremental);


                //regn_ca2 Table


                //* Adding data into  regn_ca2  Table


                if (!jObj.isNull(REGN_CA_2)) {// this is not servie
                    JSONArray regn_ca2s = jObj.getJSONArray(REGN_CA_2);
                    size = regn_ca2s.length();

                    String AdmCountryCode;
                    String LayR1ListCode;
                    String LayR2ListCode;
                    String LayR3ListCode;
                    String LayR4ListCode;
                    String HHID;
                    String MemID;
                    String RegNDate;
                    String CA2DOB;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String GRDCode;
                    String CA2GRDDate;
                    String ChildName;
                    String ChildSex;
                    for (int i = 0; i < size; i++) {
                        JSONObject regn_ca2 = regn_ca2s.getJSONObject(i);

                        AdmCountryCode = regn_ca2.getString(ADM_COUNTRY_CODE);
                        LayR1ListCode = regn_ca2.getString(LAY_R_1_LIST_CODE);
                        LayR2ListCode = regn_ca2.getString(LAY_R_2_LIST_CODE);
                        LayR3ListCode = regn_ca2.getString(LAY_R_3_LIST_CODE);
                        LayR4ListCode = regn_ca2.getString(LAY_R_4_LIST_CODE);
                        HHID = regn_ca2.getString(MainActivity.HHID);
                        MemID = regn_ca2.getString(MEM_ID);
                        RegNDate = regn_ca2.getString(REG_N_DATE);
                        CA2DOB = regn_ca2.getString(CA_2_DOB);
                        AdmProgCode = regn_ca2.getString(ADM_PROG_CODE);
                        AdmSrvCode = regn_ca2.getString(ADM_SRV_CODE);
                        GRDCode = regn_ca2.getString(GRD_CODE);
                        CA2GRDDate = regn_ca2.getString(CA_2_GRD_DATE);
                        ChildName = regn_ca2.getString("ChildName");
                        ChildSex = regn_ca2.getString("ChildSex");


                        db.addRegNCA2_FromOnLine(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode,
                                HHID, MemID, AdmProgCode, AdmSrvCode, RegNDate, GRDCode, CA2DOB, CA2GRDDate, ChildName, ChildSex);


                        Log.d("nir", "In RegNCA2 Table- AdmCountryCode :" + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " HHID : " + HHID + " MemID  : " + MemID + " AdmProgCode : " + AdmProgCode + " AdmSrvCode : " + AdmSrvCode + " RegNDate : " + RegNDate + " GRDCode : " + GRDCode + /*" EntryBy : " + EntryBy + " EntryDate : " + EntryDate +*/ " LMDOB : " + CA2DOB + " LMGRDDate : " + CA2GRDDate);// + " FDPCode  : " + FDPCode );
                    }
                }

                publishProgress(++progressIncremental);

                if (!jObj.isNull(REG_N_AGR_JSON_A)) {


                    JSONArray reg_n_agr_tableDatas = jObj.getJSONArray(REG_N_AGR_JSON_A);
                    size = reg_n_agr_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject reg_n_agr_tableData = reg_n_agr_tableDatas.getJSONObject(i);
                        AGR_DataModel data = new AGR_DataModel();
                        data.setCountryCode(reg_n_agr_tableData.getString(ADM_COUNTRY_CODE));

                        data.setDistrictCode(reg_n_agr_tableData.getString(LAY_R_1_LIST_CODE));
                        data.setUpazillaCode(reg_n_agr_tableData.getString(LAY_R_2_LIST_CODE));
                        data.setUnitCode(reg_n_agr_tableData.getString(LAY_R_3_LIST_CODE));
                        data.setVillageCode(reg_n_agr_tableData.getString(LAY_R_4_LIST_CODE));
                        data.setHhId(reg_n_agr_tableData.getString(HHID));

                        data.setHhMemId(reg_n_agr_tableData.getString(MEM_ID));
                        data.setRegnDate(reg_n_agr_tableData.getString(REG_N_DATE));
                        data.setElderleyYN(reg_n_agr_tableData.getString(ELDERLY_YN));
                        data.setLandSize(reg_n_agr_tableData.getString(LAND_SIZE));
                        data.setDepenonGanyu(reg_n_agr_tableData.getString(DEPEND_ON_GANYU));
                        data.setWillingness(reg_n_agr_tableData.getString(WILLINGNESS));
                        data.setWinterCultivation(reg_n_agr_tableData.getString(WINTER_CULTIVATION));
                        data.setVulnerableHh(reg_n_agr_tableData.getString(VULNERABLE_HH));
                        data.setPlantingVcrop(reg_n_agr_tableData.getString(PLANTING_VALUE_CHAIN_CROP));


                        String AGOINVC = reg_n_agr_tableData.getString("AGOINVC");
                        String AGONASFAM = reg_n_agr_tableData.getString("AGONASFAM");
                        String AGOCU = reg_n_agr_tableData.getString("AGOCU");
                        String AGOOther = reg_n_agr_tableData.getString("AGOOther");
                        int LSGoat = Integer.parseInt(reg_n_agr_tableData.getString("LSGoat"));
                        int LSChicken = Integer.parseInt(reg_n_agr_tableData.getString("LSChicken"));
                        int LSPigeon = Integer.parseInt(reg_n_agr_tableData.getString("LSPigeon"));
                        int LSOther = Integer.parseInt(reg_n_agr_tableData.getString("LSOther"));


                        db.addInRegNAgrTableFromOnline(data, AGOINVC, AGONASFAM, AGOCU, AGOOther, LSGoat, LSChicken, LSPigeon
                                , LSOther);

                    }
                }
                publishProgress(++progressIncremental);


                if (!jObj.isNull(REG_N_CT_JSON_A)) {
                    JSONArray reg_n_ct_tableDatas = jObj.getJSONArray(REG_N_CT_JSON_A);
                    size = reg_n_ct_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject reg_n_ct_tableData = reg_n_ct_tableDatas.getJSONObject(i);
                        CTDataModel data = new CTDataModel();
                        data.setCountryCode(reg_n_ct_tableData.getString(ADM_COUNTRY_CODE));
                        data.setDistrictCode(reg_n_ct_tableData.getString(LAY_R_1_LIST_CODE));
                        data.setUpazillaCode(reg_n_ct_tableData.getString(LAY_R_2_LIST_CODE));
                        data.setUnitCode(reg_n_ct_tableData.getString(LAY_R_3_LIST_CODE));
                        data.setVillageCode(reg_n_ct_tableData.getString(LAY_R_4_LIST_CODE));
                        data.setHhId(reg_n_ct_tableData.getString(HHID));
                        data.setMemID(reg_n_ct_tableData.getString(MEM_ID));
                        data.setC11CtPr(reg_n_ct_tableData.getString(C_11_CT_PR));
                        data.setC21CtPr(reg_n_ct_tableData.getString(C_21_CT_PR));
                        data.setC31CtPr(reg_n_ct_tableData.getString(C_31_CT_PR));
                        data.setC32CtPr(reg_n_ct_tableData.getString(C_32_CT_PR));
                        data.setC33CtPr(reg_n_ct_tableData.getString(C_33_CT_PR));
                        data.setC34CtPr(reg_n_ct_tableData.getString(C_34_CT_PR));
                        data.setC35CtPr(reg_n_ct_tableData.getString(C_35_CT_PR));
                        data.setC36CtPr(reg_n_ct_tableData.getString(C_36_CT_PR));
                        data.setC37CtPr(reg_n_ct_tableData.getString(C_37_CT_PR));
                        data.setC38CtPr(reg_n_ct_tableData.getString(C_38_CT_PR));


                        db.addMemIntoCT_Table(data);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                if (!jObj.isNull("reg_n_ffa")) {


                    JsonDeserialization.reg_N_FFAPerser(jObj.getJSONArray("reg_n_ffa"), db);

                }

            } catch (Exception e) {
                Log.d(TAG, "Expetion : " + e);
                e.printStackTrace();
            }
            return null;

        }
    }

    private class Inject_Reg_HouseH_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // insert assigne data
            new Inject_Service_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.REG_HOUSE_HOLD_DATA);
            /**
             * the parsing held by other class
             */

            JsonDeserialization.RegistrationNHHParser(retreiveData, db);


            return null;
        }
    }

    private class Inject_Service_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // insert assigne data
            new Inject_Reg_Member_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.SERVICE_DATA);


            // int size;

            /**
             * The total string Convert into JSON object
             * */

            try {
                JSONObject jObj = new JSONObject(retreiveData);

                if (!jObj.isNull(MainActivity.SERVICE_TABLE_JSON_A)) {// this is not servie
//
//
                    JSONArray services_data = jObj.getJSONArray(MainActivity.SERVICE_TABLE_JSON_A);


                    publishProgress(++progressIncremental);
                    JsonDeserialization.SrvTableParser(services_data, db);
                }


                if (!jObj.isNull("service_exe_table")) {// this is not servie
                    JSONArray services_exe_table = jObj.getJSONArray("service_exe_table");

                    publishProgress(++progressIncremental);
                    JsonDeserialization.SrvExtTableParser(services_exe_table, db);
                }
            } catch (Exception e) {
                Log.d(TAG, "Exception : " + e);
                e.printStackTrace();
            }


            return null;
        }
    }

    /**
     * inject Reg member data
     */

    private class Inject_Reg_Member_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_Reg_Member_Prog_Grp_DataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.REG_MEMBER_DATA);
            JsonDeserialization.RegNMemberParser(retreiveData, db);
            publishProgress(++progressIncremental);

            return null;
        }
    }


    /**
     * inject Reg member Program  Group data
     */

    private class Inject_Reg_Member_Prog_Grp_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            new Inject_RegNAssProgSrvDataIntoSQLite().execute();

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... params) {

            String retreiveData = readDataFromFile(LoginActivity.REG_MEMBER_PROG_GROUP_DATA);

            JsonDeserialization.RegNMemProGrpParser(retreiveData, db);
            publishProgress(++progressIncremental);

            JsonDeserialization.GpsLocationContentParser(retreiveData, db);
            publishProgress(++progressIncremental);

            JsonDeserialization.SrvSpecificTableParser(retreiveData, db);
            publishProgress(++progressIncremental);


            return null;
        }
    }

    private int progressIncremental;

    private class Inject_All_DataIntoSQLite extends AsyncTask<Void, Integer, Void> {


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /**
             * Read JSON DATA  from the text file
             * */
            String retreiveData = readDataFromFile("all_data");
            try {


                int size;
                //  try {
                /**
                 * The total string Convert into JSON object
                 * */

                JSONObject jObj = new JSONObject(retreiveData);
                db.deleteUsers();

                // User successfully Verified // May apply isNull()
                // Now store user's info into local database


                String user_id = jObj.getString(USR_ID);

                JSONObject user = jObj.getJSONObject(USER_JSON_A);

                String country_code = user.getString(ADM_COUNTRY_CODE);
                String login_name = user.getString(USR_LOG_IN_NAME);
                String login_pw = user.getString(USR_LOG_IN_PW);
                String first_name = user.getString(USR_FIRST_NAME);
                String last_name = user.getString(USR_LAST_NAME);
                String email = user.getString(USR_EMAIL);
                String email_verification = user.getString(USR_EMAIL_VERIFICATION);
                String user_status = user.getString(USR_STATUS);
                String entry_by = user.getString(ENTRY_BY);
                String entry_date = user.getString(ENTRY_DATE);

                setUserName(first_name); // Setting User hhName into session
                setStaffID(user_id); // Setting Staff ID to use when sync data
                //   setUserID(user_name);
                // setUserPassword(password);

                setUserCountryCode(country_code); // Setting Country code

                // Inserting row in users table

                db.addUser(user_id, country_code, login_name, login_pw, first_name, last_name, email, email_verification, user_status, entry_by, entry_date);

                publishProgress(++progressIncremental);
                // Adding data into Country Table
                if (!jObj.isNull(COUNTRIES_JSON_A)) {
                    JSONArray countries = jObj.getJSONArray(COUNTRIES_JSON_A);
                    size = countries.length();

                    for (int i = 0; i < size; i++) {
                        JSONObject country = countries.getJSONObject(i);

                        String AdmCountryCode = country.getString(ADM_COUNTRY_CODE);
                        String AdmCountryName = country.getString(ADM_COUNTRY_NAME);

                        db.addCountry(AdmCountryCode, AdmCountryName);

                        Log.d(TAG, "Country Code : " + AdmCountryCode + " Country hhName : " + AdmCountryName);
                    }
                }
                publishProgress(++progressIncremental);

                // Adding data into Valid Registration Date Table
                if (!jObj.isNull(VALID_DATES_JSON_A)) {
                    JSONArray valid_dates = jObj.getJSONArray(VALID_DATES_JSON_A);
                    size = valid_dates.length();
                    // lunchBarDialog("valid_dates",size);
                    for (int i = 0; i < size; i++) {
                        JSONObject valid_date = valid_dates.getJSONObject(i);
                        String AdmCountryCode = valid_date.getString(ADM_COUNTRY_CODE);
                        String StartDate = valid_date.getString(START_DATE);
                        String EndDate = valid_date.getString(END_DATE);

                        db.addValidDateRange(AdmCountryCode, StartDate, EndDate);

//                        Log.d(TAG, "Country Code : " + AdmCountryCode + " Start Date : " + StartDate + " End Date: " + EndDate);
                    }
                }
                publishProgress(++progressIncremental);


                // create table
                // Adding data into GPS group Table
                if (!jObj.isNull(GPS_GROUP_JSON_A)) {
                    JSONArray gps_groups = jObj.getJSONArray(GPS_GROUP_JSON_A);
                    size = gps_groups.length();
                    //  lunchBarDialog("gps_group",size);
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_group = gps_groups.getJSONObject(i);


                        String GrpCode = gps_group.getString(GRP_CODE);
                        String GrpName = gps_group.getString(GRP_NAME);
                        String Description = gps_group.getString(DESCRIPTION);


                        db.addGpsGroup(GrpCode, GrpName, Description);


//                        Log.d(TAG, " GroupCode : " + GrpCode + " GrpName : " + GrpName + " Description : " + Description);
                    }
                }

                publishProgress(++progressIncremental);
                // Adding data into GPS SUb group Table

                if (!jObj.isNull(GPS_SUBGROUP_JSON_A)) {
                    JSONArray gps_subgroups = jObj.getJSONArray(GPS_SUBGROUP_JSON_A);
                    size = gps_subgroups.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_subgroup = gps_subgroups.getJSONObject(i);
                        String GrpCode = gps_subgroup.getString(GRP_CODE);
                        String SubGrpCode = gps_subgroup.getString(SUB_GRP_CODE);
                        String SubGrpName = gps_subgroup.getString(SUB_GRP_NAME);
                        String Description = gps_subgroup.getString(DESCRIPTION);
                        db.addGpsSubGroup(GrpCode, SubGrpCode, SubGrpName, Description);


                        // Log.d(TAG, "In Sub Group Table: GroupCode : " + GrpCode + " SubGroupCode : " + SubGrpCode +" SubGrpName : " + SubGrpName + " Description : " + Description  );
                    }
                }
                publishProgress(++progressIncremental);

                // * Adding data into GPS Location Table

                if (!jObj.isNull(GPS_LOCATION_JSON_A)) {
                    JSONArray gps_locations = jObj.getJSONArray(GPS_LOCATION_JSON_A);
                    JsonDeserialization.gpsLocationParse(gps_locations, db);


                }

                publishProgress(++progressIncremental);

                // * Adding data into adm_countryaward Table

                if (!jObj.isNull(ADM_COUNTRY_AWARD_JSON_A)) {

                    String AdmCountryCode;
                    String AdmDonorCode;
                    String AdmAwardCode;
                    String AwardRefNumber;
                    String AwardStartDate;
                    String AwardEndDate;
                    String AwardShortName;
                    String AwardStatus;
                    JSONArray adm_countryawards = jObj.getJSONArray(ADM_COUNTRY_AWARD_JSON_A);
                    size = adm_countryawards.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_countryaward = adm_countryawards.getJSONObject(i);

                        AdmCountryCode = adm_countryaward.getString(ADM_COUNTRY_CODE);
                        AdmDonorCode = adm_countryaward.getString(ADM_DONOR_CODE);
                        AdmAwardCode = adm_countryaward.getString(ADM_AWARD_CODE);
                        AwardRefNumber = adm_countryaward.getString(AWARD_REF_NUMBER);
                        AwardStartDate = adm_countryaward.getString(AWARD_START_DATE);
                        AwardEndDate = adm_countryaward.getString(AWARD_END_DATE);
                        AwardShortName = adm_countryaward.getString(AWARD_SHORT_NAME);
                        AwardStatus = adm_countryaward.getString(AWARD_STATUS);


                        db.addCountryAward(AdmCountryCode, AdmDonorCode, AdmAwardCode, AwardRefNumber, AwardStartDate, AwardEndDate, AwardShortName, AwardStatus);


                        // Log.d(TAG, "In Country Award Table- AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " AwardRefNumber : " + AwardRefNumber + " AwardStartDate : " + AwardStartDate + " AwardEndDate  : " + AwardEndDate + " Long : " + AwardShortName + " Latd : " + AwardStatus  );
                    }
                }

                publishProgress(++progressIncremental);


                // * Adding data into DonorName Table


                if (!jObj.isNull(ADM_DONOR_JSON_A)) {

                    JSONArray adm_donors = jObj.getJSONArray(ADM_DONOR_JSON_A);
                    size = adm_donors.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_donor = adm_donors.getJSONObject(i);

                        String AdmDonorCode = adm_donor.getString(ADM_DONOR_CODE);
                        String AdmDonorName = adm_donor.getString(ADM_DONOR_NAME);
                        db.addDonorName(AdmDonorCode, AdmDonorName);


                        //  Log.d(TAG, "In Donor Table-  AdmDonorCode : " + AdmDonorCode + " AdmDonorName : " + AdmDonorName  );
                    }
                }
                publishProgress(++progressIncremental);

                //adm_program_master

                // * Adding data into adm_program_master Table

                if (!jObj.isNull(ADM_PROGRAM_MASTER_JSON_A)) {
                    JSONArray adm_program_masters = jObj.getJSONArray(ADM_PROGRAM_MASTER_JSON_A);
                    size = adm_program_masters.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                        String AdmProgCode = adm_program_master.getString(ADM_PROG_CODE);
                        String AdmAwardCode = adm_program_master.getString(ADM_AWARD_CODE);
                        String AdmDonorCode = adm_program_master.getString(ADM_DONOR_CODE);
                        String ProgName = adm_program_master.getString(PROG_NAME);
                        String ProgShortName = adm_program_master.getString(PROG_SHORT_NAME);
                        String MultipleSrv = adm_program_master.getString(MULTIPLE_SRV);
                        db.addProgram(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);


                        Log.d(TAG, "In Program master Table- AdmProgCode :" + AdmProgCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " ProgName : " + ProgName + " ProgShortName  : " + ProgShortName);
                    }
                }
                publishProgress(++progressIncremental);


                // * Adding data into adm_service_master Table
                // *
                if (!jObj.isNull(ADM_SERVICE_MASTER_JSON_A)) {
                    JSONArray adm_service_masters = jObj.getJSONArray(ADM_SERVICE_MASTER_JSON_A);
                    size = adm_service_masters.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_service_master = adm_service_masters.getJSONObject(i);

                        String AdmProgCode = adm_service_master.getString(ADM_PROG_CODE);
                        String AdmSrvCode = adm_service_master.getString(ADM_SRV_CODE);
                        String AdmSrvName = adm_service_master.getString("AdmSrvName");
                        String AdmSrvShortName = adm_service_master.getString("AdmSrvShortName");

                        db.addServiceMaster(AdmProgCode, AdmSrvCode, AdmSrvName, AdmSrvShortName);


                        Log.d(TAG, "In adm_service_master Table- AdmProgCode :" + AdmProgCode + " AdmSrvCode : " + AdmSrvCode + " AdmSrvName : " + AdmSrvName + " AdmSrvShortName : " + AdmSrvShortName);
                    }
                }
                publishProgress(++progressIncremental);
                //adm_op_month


                if (!jObj.isNull(ADM_OP_MONTH_JSON_A)) {
                    JSONArray adm_op_months = jObj.getJSONArray(ADM_OP_MONTH_JSON_A);
                    size = adm_op_months.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_op_month = adm_op_months.getJSONObject(i);

                        String AdmCountryCode = adm_op_month.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = adm_op_month.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = adm_op_month.getString(ADM_AWARD_CODE);
                        String OpCode = adm_op_month.getString(OP_CODE);
                        String OpMonthCode = adm_op_month.getString(OP_MONTH_CODE);
                        String MonthLabel = adm_op_month.getString(MONTH_LABEL);
                        String StartDate = adm_op_month.getString(START_DATE);
                        String EndDate = adm_op_month.getString(END_DATE);

                        String UsaStartDate = adm_op_month.getString(USA_START_DATE);
                        String UsaEndDate = adm_op_month.getString(USA_END_DATE);
                        String Status = adm_op_month.getString("Status");
                        db.addOpMonthFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, OpCode, OpMonthCode, MonthLabel, StartDate, EndDate, UsaStartDate, UsaEndDate, Status);


                        //       Log.d(TAG, "In adm_op_month program master Table- AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " OpCode : " + OpCode + " OpMonthCode : " + OpMonthCode + " MonthLabel : " + MonthLabel + " StartDate : " + StartDate + " EndDate : " + EndDate + " Status : " + Status);
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull(ADM_COUNTRY_PROGRAM_JSON_A)) {
                    JSONArray adm_country_programs = jObj.getJSONArray(ADM_COUNTRY_PROGRAM_JSON_A);
                    size = adm_country_programs.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject adm_country_program = adm_country_programs.getJSONObject(i);
                        String AdmCountryCode = adm_country_program.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = adm_country_program.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = adm_country_program.getString(ADM_AWARD_CODE);
                        String AdmProgCode = adm_country_program.getString(ADM_PROG_CODE);
                        String AdmSrvCode = adm_country_program.getString(ADM_SRV_CODE);
                        String FoodFlag = adm_country_program.getString(FOOD_FLAG);
                        String NFoodFlag = adm_country_program.getString(N_FOOD_FLAG);
                        String CashFlag = adm_country_program.getString(CASH_FLAG);
                        String VOFlag = adm_country_program.getString(VO_FLAG);
                        String DefaultFoodDays = adm_country_program.getString(DEFAULT_FOOD_DAYS);
                        String DefaultNFoodDays = adm_country_program.getString(DEFAULT_N_FOOD_DAYS);
                        String DefaultCashDays = adm_country_program.getString(DEFAULT_CASH_DAYS);
                        String DefaultVODays = adm_country_program.getString(DEFAULT_VO_DAYS);
                        String SrvSpecific = adm_country_program.getString(SRV_SPECIFIC);


                        db.insertAdmCountryProgram(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, FoodFlag, NFoodFlag, CashFlag, VOFlag, DefaultFoodDays, DefaultNFoodDays, DefaultCashDays, DefaultVODays, SrvSpecific);

//                        Log.d(TAG, "In adm_country_program Table flag FoodFlag :" + FoodFlag + "NFoodFlag : " + NFoodFlag + " CashFlag :" + CashFlag + " VOFlag : " + VOFlag + " SrvSpecific :" + SrvSpecific);


                    }
                }

                publishProgress(++progressIncremental);


                // * Adding data into  dob_service_center  Table


                if (!jObj.isNull(DOB_SERVICE_CENTER_JSON_A)) {// this is not servie
                    JSONArray dob_service_centers = jObj.getJSONArray(DOB_SERVICE_CENTER_JSON_A);
                    size = dob_service_centers.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject dob_service_center = dob_service_centers.getJSONObject(i);

                        String AdmCountryCode = dob_service_center.getString(ADM_COUNTRY_CODE);
                        String SrvCenterCode = dob_service_center.getString(SRV_CENTER_CODE);
                        String SrvCenterName = dob_service_center.getString(SRV_CENTER_NAME);
                        // String SrvCenterAddress = dob_service_center.getString("SrvCenterAddress");
                        //   String SrvCenterCatCode = dob_service_center.getString("SrvCenterCatCode");

                        String FDPCode = dob_service_center.getString(FDP_CODE);


                        db.addServiceCenter(AdmCountryCode, SrvCenterCode, SrvCenterName, FDPCode);

//                        Log.d("NIR1", "In Service Center Table - AdmCountryCode :" + AdmCountryCode + " SrvCenterCode : " + SrvCenterCode + " SrvCenterName : " + SrvCenterName);
                        //+ " SrvCenterAddress : " + SrvCenterAddress + " SrvCenterCatCode  : " + SrvCenterCatCode + " FDPCode  : " + FDPCode );
                    }
                }
                publishProgress(++progressIncremental);


                // * Adding data into  dbo_staff_geo_info_access  Table


                if (!jObj.isNull(STAFF_ACCESS_INFO_JSON_A)) {// this is not servie
                    JSONArray staff_access_info_accesses = jObj.getJSONArray(STAFF_ACCESS_INFO_JSON_A);
                    size = staff_access_info_accesses.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_access_info_access = staff_access_info_accesses.getJSONObject(i);

                        String StfCode = staff_access_info_access.getString(STF_CODE);
                        String AdmCountryCode = staff_access_info_access.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = staff_access_info_access.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = staff_access_info_access.getString(ADM_AWARD_CODE);
                        String LayRListCode = staff_access_info_access.getString(LAY_R_LIST_CODE);
                        String btnNew = staff_access_info_access.getString(BTN_NEW1);
                        String btnSave = staff_access_info_access.getString(BTN_SAVE);
                        String btnDel = staff_access_info_access.getString(BTN_DEL);
                        String btnPepr = staff_access_info_access.getString(BTN_PEPR);
                        String btnAprv = staff_access_info_access.getString(BTN_APRV);
                        String btnRevw = staff_access_info_access.getString(BTN_REVW);
                        String btnVrfy = staff_access_info_access.getString(BTN_VRFY);
                        String btnDTran = staff_access_info_access.getString(BTN_D_TRAN);


                        //String FDPCode = dbo_staff_geo_info_access.getString("FDPCode");
                        String disCode = LayRListCode.substring(0, 2);
                        String upCode = LayRListCode.substring(2, 4);
                        String unCode = LayRListCode.substring(4, 6);
                        String vCode = LayRListCode.substring(6);
                        db.addStaffGeoAccessInfoFromOnline(StfCode, AdmCountryCode, AdmDonorCode, AdmAwardCode, LayRListCode, disCode, upCode, unCode, vCode, btnNew, btnSave, btnDel, btnPepr, btnAprv, btnRevw, btnVrfy, btnDTran);//, SrvCenterCatCode, FDPCode);


                        //  Log.d(TAG, "In addStaffGeoAccessInfoFromOnline Table- StfCode :" + StfCode + " AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode +               " AdmAwardCode : " + AdmAwardCode + " LayRListCode  : " + LayRListCode);// + " FDPCode  : " + FDPCode );
                    }
                }


                if (!jObj.isNull(LB_REG_HH_CATEGORY_JSON_A)) {
                    JSONArray lb_reg_hh_categorys = jObj.getJSONArray(LB_REG_HH_CATEGORY_JSON_A);
                    size = lb_reg_hh_categorys.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lb_reg_hh_category = lb_reg_hh_categorys.getJSONObject(i);
                        String AdmCountryCode = lb_reg_hh_category.getString(ADM_COUNTRY_CODE);
                        String HHHeadCatCode = lb_reg_hh_category.getString(HH_HEAD_CAT_CODE);
                        String CatName = lb_reg_hh_category.getString(CAT_NAME);
                        db.addHHCategory(AdmCountryCode, HHHeadCatCode, CatName);


                        // Log.d(TAG, "In House hold Category Table: AdmCountryCode : " + AdmCountryCode + " HHHeadCatCode : " + HHHeadCatCode +" SubGrpName : " + SubGrpName + " Description : " + Description  );
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull(REG_LUP_GRADUATION_JSON_A)) {
                    JSONArray reg_lup_graduations = jObj.getJSONArray(REG_LUP_GRADUATION_JSON_A);
                    size = reg_lup_graduations.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject reg_lup_graduation = reg_lup_graduations.getJSONObject(i);

                        String AdmProgCode = reg_lup_graduation.getString(ADM_PROG_CODE);
                        String AdmSrvCode = reg_lup_graduation.getString(ADM_SRV_CODE);
                        String GRDCode = reg_lup_graduation.getString(GRD_CODE);
                        String GRDTitle = reg_lup_graduation.getString(GRD_TITLE);
                        String DefaultCatActive = reg_lup_graduation.getString(DEFAULT_CAT_ACTIVE);
                        String DefaultCatExit = reg_lup_graduation.getString(DEFAULT_CAT_EXIT);


                        db.addGraduation(AdmProgCode, AdmSrvCode, GRDCode, GRDTitle, DefaultCatActive, DefaultCatExit);


                        // Log.d(TAG, "In reg_lup_graduation: AdmProgCode : " + AdmProgCode + " AdmSrvCode : " + AdmSrvCode + " GRDCode : " + GRDCode + " GRDTitle : " + GRDTitle + " DefaultCatActive : " + DefaultCatActive+ " DefaultCatExit : " + DefaultCatExit   );
                    }
                }
                publishProgress(++progressIncremental);
                // Adding data into Layer Label Table
                if (!jObj.isNull(LAYER_LABELS_JSON_A)) {
                    JSONArray layer_labels = jObj.getJSONArray(LAYER_LABELS_JSON_A);
                    size = layer_labels.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject layer_label = layer_labels.getJSONObject(i);

                        String AdmCountryCode = layer_label.getString(ADM_COUNTRY_CODE);
                        String GeoLayRCode = layer_label.getString(GEO_LAY_R_CODE);
                        String GeoLayRName = layer_label.getString(GEO_LAY_R_NAME);
                        db.addLayerLabel(AdmCountryCode, GeoLayRCode, GeoLayRName);

//                        Log.d(TAG, "Country Code : " + AdmCountryCode + " Layer code: " + GeoLayRCode + " Layer hhName: " + GeoLayRName);
                    }
                }
                publishProgress(++progressIncremental);

                // Adding data into District Table
                if (!jObj.isNull(DISTRICT)) {
                    JSONArray district = jObj.getJSONArray(DISTRICT);
                    size = district.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject dist = district.getJSONObject(i);

                        String AdmCountryCode = dist.getString(ADM_COUNTRY_CODE);
                        String GeoLayRCode = dist.getString(GEO_LAY_R_CODE);
                        String LayRListCode = dist.getString(LAY_R_LIST_CODE);
                        String LayRListName = dist.getString(LAY_R_LIST_NAME);

                        db.addDistrict(AdmCountryCode, GeoLayRCode, LayRListCode, LayRListName);

                        //  Log.d(TAG, "AdmCountryCode : " + AdmCountryCode + " LayRListCode : " + LayRListCode + " LayRListName : " + LayRListName);
                    }
                }
                publishProgress(++progressIncremental);
                // Adding data into Upazilla Table
                if (!jObj.isNull(UPAZILLA)) {

                    JSONArray upazilla = jObj.getJSONArray(UPAZILLA);

                    size = upazilla.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject up = upazilla.getJSONObject(i);

                        String AdmCountryCode = up.getString(ADM_COUNTRY_CODE);
                        String GeoLayRCode = up.getString(GEO_LAY_R_CODE);
                        String LayR1ListCode = up.getString(LAY_R_1_LIST_CODE);
                        String LayR2ListCode = up.getString(LAY_R_2_LIST_CODE);
                        String LayR2ListName = up.getString(LAY_R_2_LIST_NAME);

                        db.addUpazilla(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR2ListName);

                        //  Log.d(TAG, "AdmCountryCode : " + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR2ListName : " + LayR2ListName);
                    }
                }
                publishProgress(++progressIncremental);

                // Adding data into Unit Table
                if (!jObj.isNull(UNIT_JSON_A)) {

                    JSONArray unit = jObj.getJSONArray(UNIT_JSON_A);
                    size = unit.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject un = unit.getJSONObject(i);

                        String AdmCountryCode = un.getString(ADM_COUNTRY_CODE);
                        String GeoLayRCode = un.getString(GEO_LAY_R_CODE);
                        String LayR1ListCode = un.getString(LAY_R_1_LIST_CODE);
                        String LayR2ListCode = un.getString(LAY_R_2_LIST_CODE);
                        String LayR3ListCode = un.getString(LAY_R_3_LIST_CODE);
                        String LayR3ListName = un.getString(LAY_R_3_LIST_NAME);

                        db.addUnit(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR3ListName);

                        // Log.d(TAG, "AdmCountryCode : " + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR3ListName : " + LayR3ListName);
                    }
                }
                publishProgress(++progressIncremental);
                // Adding data into Village Table
                if (!jObj.isNull(VILLAGE_JSON_A)) {

                    JSONArray village = jObj.getJSONArray(VILLAGE_JSON_A);

                    size = village.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vil = village.getJSONObject(i);

                        String AdmCountryCode = vil.getString(ADM_COUNTRY_CODE);
                        String GeoLayRCode = vil.getString(GEO_LAY_R_CODE);
                        String LayR1ListCode = vil.getString(LAY_R_1_LIST_CODE);
                        String LayR2ListCode = vil.getString(LAY_R_2_LIST_CODE);
                        String LayR3ListCode = vil.getString(LAY_R_3_LIST_CODE);
                        String LayR4ListCode = vil.getString(LAY_R_4_LIST_CODE);
                        String LayR4ListName = vil.getString(LAY_R_4_LIST_NAME);
                        String HHCount = vil.getString(HH_COUNT);

                        db.addVillage(AdmCountryCode, GeoLayRCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, LayR4ListName, HHCount);

                        //  Log.d(TAG, "AdmCountryCode : " + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " LayR4ListName: " + LayR4ListName+" HHCount : "+HHCount);
                    }
                }
                publishProgress(++progressIncremental);

                // Adding data into Relation Table
                if (!jObj.isNull(RELATION_JSON_A)) {

                    JSONArray relation = jObj.getJSONArray(RELATION_JSON_A);

                    size = relation.length();

                    for (int i = 0; i < size; i++) {

                        JSONObject rel = relation.getJSONObject(i);

                        String Relation_Code = rel.getString(HH_RELATION_CODE);
                        String RelationName = rel.getString(RELATION_NAME);

                        db.addRelation(Relation_Code, RelationName);

//                        Log.d(TAG, "Relation_Code : " + Relation_Code + " RelationName : " + RelationName);
                    }
                }
                publishProgress(++progressIncremental);


                if (!jObj.isNull(REPORT_TEMPLATE)) {
                    JSONArray report_templates = jObj.getJSONArray(REPORT_TEMPLATE);
                    size = report_templates.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject report_template = report_templates.getJSONObject(i);

                        String AdmCountryCode = report_template.getString(ADM_COUNTRY_CODE);
                        String RptLabel = report_template.getString(RPT_LABEL);
                        String Code = report_template.getString(RPT_G_N_CODE);

                        db.addCardType(AdmCountryCode, RptLabel, Code);

//                        Log.d(TAG, "In Report Template Table: RptLabel : " + RptLabel + " Code : " + Code);
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull(CARD_PRINT_REASON)) {
                    JSONArray card_print_reasons = jObj.getJSONArray(CARD_PRINT_REASON);
                    size = card_print_reasons.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject card_print_reason = card_print_reasons.getJSONObject(i);

                        String ReasonCode = card_print_reason.getString(REASON_CODE);
                        String ReasonTitle = card_print_reason.getString(REASON_TITLE);

                        db.addCardPrintReason(ReasonCode, ReasonTitle);

//                        Log.d(TAG, "In Card Reason Table: ReasonCode : " + ReasonCode + " ReasonTitle : " + ReasonTitle);
                    }
                }
//                publishProgress(33);
                publishProgress(++progressIncremental);


                if (!jObj.isNull(REG_MEM_CARD_REQUEST_JSON_A)) {
                    JSONArray reg_mem_card_requests = jObj.getJSONArray(REG_MEM_CARD_REQUEST_JSON_A);
                    size = reg_mem_card_requests.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject reg_mem_card_request = reg_mem_card_requests.getJSONObject(i);

                        String AdmCountryCode = reg_mem_card_request.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = reg_mem_card_request.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = reg_mem_card_request.getString(ADM_AWARD_CODE);
                        String LayR1ListCode = reg_mem_card_request.getString(LAY_R_1_LIST_CODE);
                        String LayR2ListCode = reg_mem_card_request.getString(LAY_R_2_LIST_CODE);
                        String LayR3ListCode = reg_mem_card_request.getString(LAY_R_3_LIST_CODE);
                        String LayR4ListCode = reg_mem_card_request.getString(LAY_R_4_LIST_CODE);
                        String HHID = reg_mem_card_request.getString(MainActivity.HHID);
                        String MemID = reg_mem_card_request.getString(MEM_ID);
                        String RptGroup = reg_mem_card_request.getString(RPT_GROUP);
                        String RptCode = reg_mem_card_request.getString(RPT_CODE);
                        String RequestSL = reg_mem_card_request.getString(REQUEST_SL);
                        String ReasonCode = reg_mem_card_request.getString(REASON_CODE);
                        String RequestDate = reg_mem_card_request.getString(REQUEST_DATE);
                        String PrintDate = reg_mem_card_request.getString(PRINT_DATE);
                        String PrintBy = reg_mem_card_request.getString(PRINT_BY);
                        String DeliveryDate = reg_mem_card_request.getString(DELIVERY_DATE);
                        String DeliveredBy = reg_mem_card_request.getString(DELIVERED_BY);
                        String DelStatus = reg_mem_card_request.getString(DEL_STATUS);
                        String EntryBy = reg_mem_card_request.getString(ENTRY_BY);
                        String EntryDate = reg_mem_card_request.getString(ENTRY_DATE);

                        db.addCardRequestDataFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode,
                                HHID, MemID, RptGroup, RptCode, RequestSL, ReasonCode, RequestDate,
                                PrintDate, PrintBy, DeliveryDate, DeliveredBy, DelStatus, EntryBy, EntryDate);

                        //Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(STAFF_FDP_ACCESS_JSON_A)) {
                    JSONArray staff_fdp_accesses = jObj.getJSONArray(STAFF_FDP_ACCESS_JSON_A);
                    size = staff_fdp_accesses.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_fdp_access = staff_fdp_accesses.getJSONObject(i);

                        String StfCode = staff_fdp_access.getString(STF_CODE);
                        String AdmCountryCode = staff_fdp_access.getString(ADM_COUNTRY_CODE);
                        String FDPCode = staff_fdp_access.getString(FDP_CODE);
                        String btnNew = staff_fdp_access.getString(BTN_NEW);
                        String btnSave = staff_fdp_access.getString(BTN_SAVE);
                        String btnDel = staff_fdp_access.getString(BTN_DEL);


                        db.addStaffFDPAccess(StfCode, AdmCountryCode, FDPCode, btnNew, btnSave, btnDel);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull(FDP_MASTER_JSON_A)) {
                    JSONArray fdp_masters = jObj.getJSONArray(FDP_MASTER_JSON_A);
                    size = fdp_masters.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject fdp_master = fdp_masters.getJSONObject(i);

                        String AdmCountryCode = fdp_master.getString(ADM_COUNTRY_CODE);
                        String FDPCode = fdp_master.getString(FDP_CODE);
                        String FDPName = fdp_master.getString(FDP_NAME);
                        String FDPCatCode = fdp_master.getString(FDP_CAT_CODE);
                        String WHCode = fdp_master.getString(WH_CODE);
                        String LayR1Code = fdp_master.getString(LAY_R_1_CODE);
                        String LayR2Code = fdp_master.getString(LAY_R_2_CODE);


                        db.addFDPMaster(AdmCountryCode, FDPCode, FDPName, FDPCatCode, WHCode, LayR1Code, LayR2Code);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(DISTRIBUTION_TABLE_JSON_A)) {
                    JSONArray distribution_tableDatas = jObj.getJSONArray(DISTRIBUTION_TABLE_JSON_A);
                    size = distribution_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distribution_tableData = distribution_tableDatas.getJSONObject(i);
                        DistributionSaveDataModel data = new DistributionSaveDataModel();
                        data.setCountryCode(distribution_tableData.getString(ADM_COUNTRY_CODE));
                        data.setAdmDonorCode(distribution_tableData.getString(ADM_DONOR_CODE));
                        data.setAdmAwardCode(distribution_tableData.getString(ADM_AWARD_CODE));
                        data.setDistrictCode(distribution_tableData.getString(LAY_R_1_LIST_CODE));
                        data.setUpCode(distribution_tableData.getString(LAY_R_2_LIST_CODE));
                        data.setUniteCode(distribution_tableData.getString(LAY_R_3_LIST_CODE));
                        data.setVillageCode(distribution_tableData.getString(LAY_R_4_LIST_CODE));
                        data.setProgCode(distribution_tableData.getString(PROG_CODE));
                        data.setSrvCode(distribution_tableData.getString(SRV_CODE));
                        data.setOpMonthCode(distribution_tableData.getString(OP_MONTH_CODE));
                        data.setFDPCode(distribution_tableData.getString(FDP_CODE));
                        data.setID(distribution_tableData.getString(ID));
                        data.setDistStatus(distribution_tableData.getString(DIST_STATUS));

                        data.setSrvOpMonthCode(distribution_tableData.getString(SRV_OP_MONTH_CODE));
                        data.setDistFlag(distribution_tableData.getString(DIST_FLAG));
                        data.setWd(distribution_tableData.getString("WD"));

                        db.addInDistributionTableFormOnLine(data);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(DISTRIBUTION_EXT_TABLE_JSON_A)) {
                    JSONArray distribution_ext_tableDatas = jObj.getJSONArray(DISTRIBUTION_EXT_TABLE_JSON_A);
                    size = distribution_ext_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distribution_ext_tableData = distribution_ext_tableDatas.getJSONObject(i);
                        // DistributionSaveDataModel data = new DistributionSaveDataModel();

                        String AdmCountryCode = distribution_ext_tableData.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = distribution_ext_tableData.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = distribution_ext_tableData.getString(ADM_AWARD_CODE);
                        String LayR1ListCode = distribution_ext_tableData.getString(LAY_R_1_LIST_CODE);
                        String LayR2ListCode = distribution_ext_tableData.getString(LAY_R_2_LIST_CODE);
                        String LayR3ListCode = distribution_ext_tableData.getString(LAY_R_3_LIST_CODE);
                        String LayR4ListCode = distribution_ext_tableData.getString(LAY_R_4_LIST_CODE);
                        String ProgCode = distribution_ext_tableData.getString(PROG_CODE);
                        String SrvCode = distribution_ext_tableData.getString(SRV_CODE);
                        String OpMonthCode = distribution_ext_tableData.getString(OP_MONTH_CODE);
                        String FDPCode = distribution_ext_tableData.getString(FDP_CODE);
                        String ID = distribution_ext_tableData.getString(MainActivity.ID);
                        String VOItmSpec = distribution_ext_tableData.getString(VO_ITM_SPEC);
                        String VOItmUnit = distribution_ext_tableData.getString(VO_ITM_UNIT);
                        String VORefNumber = distribution_ext_tableData.getString(VO_REF_NUMBER);

                        String SrvOpMonthCode = distribution_ext_tableData.getString(SRV_OP_MONTH_CODE);
                        String DistFlag = distribution_ext_tableData.getString(DIST_FLAG);


                        //data.setDistStatus(distribution_ext_tableData.getString(DIST_STATUS);

                        db.addInDistributionExtendedTable(AdmCountryCode, AdmDonorCode, AdmAwardCode,
                                LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, ProgCode,
                                SrvCode, OpMonthCode, FDPCode, ID, VOItmSpec, VOItmUnit,
                                VORefNumber, SrvOpMonthCode, DistFlag,

                                "", "", "1");

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);

                if (!jObj.isNull("distbasic_table")) {
                    JSONArray distbasic_table = jObj.getJSONArray("distbasic_table");
                    size = distbasic_table.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject distbasic = distbasic_table.getJSONObject(i);
                        // DistributionSaveDataModel data = new DistributionSaveDataModel();

                        String AdmCountryCode = distbasic.getString(ADM_COUNTRY_CODE);
                        String AdmDonorCode = distbasic.getString(ADM_DONOR_CODE);
                        String AdmAwardCode = distbasic.getString(ADM_AWARD_CODE);
                        String ProgCode = distbasic.getString(PROG_CODE);
                        String OpCode = distbasic.getString("OpCode");
                        String SrvOpMonthCode = distbasic.getString("SrvOpMonthCode");
                        String DisOpMonthCode = distbasic.getString("DisOpMonthCode");
                        String FDPCode = distbasic.getString(FDP_CODE);
                        String DistFlag = distbasic.getString("DistFlag");
                        ///   String FoodFlag = distbasic.getString("FoodFlag");
                        String OrgCode = distbasic.getString("OrgCode");
                        String Distributor = distbasic.getString("Distributor");
                        String DistributionDate = distbasic.getString("DistributionDate");
                        String DeliveryDate = distbasic.getString("DeliveryDate");
                        String Status = distbasic.getString("Status");
                        String PreparedBy = distbasic.getString("PreparedBy");
                        String VerifiedBy = distbasic.getString("VerifiedBy");
                        String ApproveBy = distbasic.getString("ApproveBy");


                        //data.setDistStatus(distribution_ext_tableData.getString(DIST_STATUS);

                        db.addInDistributionNPlaneTable(AdmCountryCode, AdmDonorCode, AdmAwardCode, ProgCode,
                                OpCode, SrvOpMonthCode, DisOpMonthCode, FDPCode, DistFlag, OrgCode, Distributor,
                                DistributionDate, DeliveryDate, Status, PreparedBy, VerifiedBy, ApproveBy);


                        Log.d(TAG, "AdmCountryCode: " + AdmCountryCode + AdmDonorCode + AdmAwardCode + ProgCode +
                                OpCode + SrvOpMonthCode + DisOpMonthCode + FDPCode + DistFlag + OrgCode + Distributor +
                                DistributionDate + DeliveryDate + Status + PreparedBy + VerifiedBy + ApproveBy);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }


                publishProgress(++progressIncremental);


                if (!jObj.isNull(LUP_SRV_OPTION_LIST)) {
                    JSONArray lup_srv_option_listDatas = jObj.getJSONArray(LUP_SRV_OPTION_LIST);
                    size = lup_srv_option_listDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_srv_option_listData = lup_srv_option_listDatas.getJSONObject(i);

                        String countryCode = lup_srv_option_listData.getString(ADM_COUNTRY_CODE);

                        String programCode = lup_srv_option_listData.getString(PROG_CODE);
                        String serviceCode = lup_srv_option_listData.getString(SRV_CODE);
                        String LUPOptionCode = lup_srv_option_listData.getString(LUP_OPTION_CODE);
                        String LUPOptionName = lup_srv_option_listData.getString(LUP_OPTION_NAME);


                        db.addInLupSrvOptionListFromOnline(countryCode, programCode, serviceCode, LUPOptionCode, LUPOptionName);

                        //  Log.d(TAG, "In Reg Mem Card Request Table: AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : "
                        //        + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode+ " HHID : " + HHID);
                    }
                }

                publishProgress(++progressIncremental);


                if (!jObj.isNull("vo_itm_table")) {
                    JSONArray vo_itm_tableDatas = jObj.getJSONArray("vo_itm_table");
                    size = vo_itm_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_tableData = vo_itm_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String CatCode = vo_itm_tableData.getString("CatCode");
                        String ItmCode = vo_itm_tableData.getString("ItmCode");
                        String ItmName = vo_itm_tableData.getString("ItmName");


                        db.addVoucherItemTableFromOnline(CatCode, ItmCode, ItmName);

//                        Log.d(TAG, "In Voucher item table : CatCode : " + CatCode + " ItmCode : " + ItmCode + " ItmName : " + ItmName);

                    }
                }

//                publishProgress(39);
                publishProgress(++progressIncremental);

                if (!jObj.isNull(VO_ITM_MEAS_TABLE_JSON_A)) {
                    JSONArray vo_itm_meas_tableDatas = jObj.getJSONArray(VO_ITM_MEAS_TABLE_JSON_A);
                    size = vo_itm_meas_tableDatas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_itm_meas_tableData = vo_itm_meas_tableDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        String MeasRCode = vo_itm_meas_tableData.getString("MeasRCode");
                        String UnitMeas = vo_itm_meas_tableData.getString("UnitMeas");
                        String MeasTitle = vo_itm_meas_tableData.getString("MeasTitle");


                        db.addVoucherItemMeasFromOnline(MeasRCode, UnitMeas, MeasTitle);

//                        Log.d(TAG, "In Voucher item table : MeasRCode : " + MeasRCode + " UnitMeas : " + UnitMeas + " MeasTitle : " + MeasTitle);

                    }
                }


                if (!jObj.isNull("vo_country_prog_itm")) {
                    JSONArray vo_country_prog_itmDatas = jObj.getJSONArray("vo_country_prog_itm");
                    size = vo_country_prog_itmDatas.length();


                    String AdmCountryCode;
                    String AdmDonorCode;
                    String AdmAwardCode;
                    String AdmProgCode;
                    String AdmSrvCode;
                    String CatCode;
                    String ItmCode;
                    String MeasRCode;
                    String VOItmSpec;
                    String UnitCost;
                    String Active;
                    String Currency;
                    for (int i = 0; i < size; i++) {
                        JSONObject vo_country_prog_itmData = vo_country_prog_itmDatas.getJSONObject(i);
                        //AGR_DataModel data = new AGR_DataModel();
                        AdmCountryCode = vo_country_prog_itmData.getString("AdmCountryCode");
                        AdmDonorCode = vo_country_prog_itmData.getString("AdmDonorCode");
                        AdmAwardCode = vo_country_prog_itmData.getString("AdmAwardCode");
                        AdmProgCode = vo_country_prog_itmData.getString("AdmProgCode");
                        AdmSrvCode = vo_country_prog_itmData.getString("AdmSrvCode");
                        CatCode = vo_country_prog_itmData.getString("CatCode");
                        ItmCode = vo_country_prog_itmData.getString("ItmCode");
                        MeasRCode = vo_country_prog_itmData.getString("MeasRCode");
                        VOItmSpec = vo_country_prog_itmData.getString(VO_ITM_SPEC);
                        UnitCost = vo_country_prog_itmData.getString("UnitCost");
                        Active = vo_country_prog_itmData.getString("Active");
                        Currency = vo_country_prog_itmData.getString("Currency");


                        db.addVoucherCountryProgItemFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AdmSrvCode, CatCode, ItmCode, MeasRCode, VOItmSpec, UnitCost, Active, Currency);

                        //                        Log.d(TAG, "In Voucher Country  Prog Item  table : AdmCountryCode : " + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode);

                    }
                }


                if (!jObj.isNull(LUP_GPS_TABLE_JSON_A)) {
                    JSONArray lup_gps_table_Datas = jObj.getJSONArray(LUP_GPS_TABLE_JSON_A);
                    size = lup_gps_table_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_gps_tableData = lup_gps_table_Datas.getJSONObject(i);

                        String GrpCode = lup_gps_tableData.getString("GrpCode");
                        String SubGrpCode = lup_gps_tableData.getString("SubGrpCode");
                        String AttributeCode = lup_gps_tableData.getString("AttributeCode");
                        String LookUpCode = lup_gps_tableData.getString("LookUpCode");
                        String LookUpName = lup_gps_tableData.getString("LookUpName");


                        db.addLUP_GPS_TableFromOnline(GrpCode, SubGrpCode, AttributeCode, LookUpCode, LookUpName);
                       /* Log.d("NIR2", "addLUP_GPS_TableFromOnline : GrpCode : " + GrpCode + " SubGrpCode : "
                                + SubGrpCode + " AttributeCode : " + AttributeCode
                                + " LookUpCode : " + LookUpCode + " LookUpName : " + LookUpName
                        );*/

                    }
                }


                if (!jObj.isNull(GPS_SUB_GROUP_ATTRIBUTES_JSON_A)) {
                    JSONArray gps_sub_group_attributes_Datas = jObj.getJSONArray(GPS_SUB_GROUP_ATTRIBUTES_JSON_A);
                    size = gps_sub_group_attributes_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_sub_group_attributes_Data = gps_sub_group_attributes_Datas.getJSONObject(i);


                        String GrpCode = gps_sub_group_attributes_Data.getString("GrpCode");
                        String SubGrpCode = gps_sub_group_attributes_Data.getString("SubGrpCode");
                        String AttributeCode = gps_sub_group_attributes_Data.getString("AttributeCode");
                        String AttributeTitle = gps_sub_group_attributes_Data.getString("AttributeTitle");
                        String DataType = gps_sub_group_attributes_Data.getString("DataType");
                        String LookUpCode = gps_sub_group_attributes_Data.getString("LookUpCode");


                        db.addGPS_SubGroupAttributesFromOnline(GrpCode, SubGrpCode, AttributeCode, AttributeTitle, DataType, LookUpCode);
                        /*Log.d("NIR2", "addGPS_SubGroupAttributesFromOnline : GrpCode : " + GrpCode
                                + " SubGrpCode : " + SubGrpCode + " AttributeCode : " + AttributeCode
                                + " AttributeTitle : " + AttributeTitle + " DataType : " + DataType
                                + " LookUpCode : " + LookUpCode);*/

                    }
                }


                if (!jObj.isNull(GPS_LOCATION_ATTRIBUTES_JSON_A)) {
                    JSONArray gps_location_attributes_Datas = jObj.getJSONArray(GPS_LOCATION_ATTRIBUTES_JSON_A);
                    size = gps_location_attributes_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject gps_location_attributes_Data = gps_location_attributes_Datas.getJSONObject(i);


                        String AdmCountryCode = gps_location_attributes_Data.getString("AdmCountryCode");
                        String GrpCode = gps_location_attributes_Data.getString("GrpCode");
                        String SubGrpCode = gps_location_attributes_Data.getString("SubGrpCode");
                        String LocationCode = gps_location_attributes_Data.getString("LocationCode");
                        String AttributeCode = gps_location_attributes_Data.getString("AttributeCode");
                        String AttributeValue = gps_location_attributes_Data.getString("AttributeValue");
                        String AttPhoto = gps_location_attributes_Data.getString("AttPhoto");

                        db.addGPSLocationAttributesFromOnline(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, AttributeCode, AttributeValue, AttPhoto);
                     /*   Log.d("NIR2", "addGPS_SubGroupAttributesFromOnline : AdmCountryCode : " + AdmCountryCode
                                + " GrpCode : " + GrpCode + " SubGrpCode : " + SubGrpCode
                                + " LocationCode : " + LocationCode + " AttributeCode : " + AttributeCode

                                + " AttributeValue : " + AttributeValue
                        );*/

                    }
                }


                if (!jObj.isNull("community_group")) {
                    JSONArray community_group_Datas = jObj.getJSONArray("community_group");
                    size = community_group_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject community_group_Data = community_group_Datas.getJSONObject(i);


                        String AdmCountryCode = community_group_Data.getString("AdmCountryCode");
                        String AdmDonorCode = community_group_Data.getString("AdmDonorCode");
                        String AdmAwardCode = community_group_Data.getString("AdmAwardCode");
                        String AdmProgCode = community_group_Data.getString("AdmProgCode");
                        String GrpCode = community_group_Data.getString("GrpCode");
                        String GrpName = community_group_Data.getString("GrpName");
                        String GrpCatCode = community_group_Data.getString("GrpCatCode");


                        String LayR1Code = community_group_Data.getString("LayR1Code");
                        String LayR2Code = community_group_Data.getString("LayR2Code");
                        String SrvCenterCode = community_group_Data.getString("SrvCenterCode");


                        db.addCommunityGroup(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode, GrpName, GrpCatCode, LayR1Code, LayR2Code, SrvCenterCode);


                    }
                }


                if (!jObj.isNull("lup_community_animal")) {
                    JSONArray lup_community_animal_Datas = jObj.getJSONArray("lup_community_animal");
                    size = lup_community_animal_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_community_animal_Data = lup_community_animal_Datas.getJSONObject(i);


                        String AdmCountryCode = lup_community_animal_Data.getString("AdmCountryCode");
                        String AdmDonorCode = lup_community_animal_Data.getString("AdmDonorCode");
                        String AdmAwardCode = lup_community_animal_Data.getString("AdmAwardCode");
                        String AdmProgCode = lup_community_animal_Data.getString("AdmProgCode");
                        String AnimalCode = lup_community_animal_Data.getString("AnimalCode");
                        String AnimalType = lup_community_animal_Data.getString("AnimalType");


                        db.addLUP_AnimalTypeFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, AnimalCode, AnimalType);


                    }
                }


                if (!jObj.isNull("lup_prog_group_crop")) {
                    JSONArray lup_prog_group_crop_Datas = jObj.getJSONArray("lup_prog_group_crop");
                    size = lup_prog_group_crop_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_prog_group_crop_Data = lup_prog_group_crop_Datas.getJSONObject(i);


                        String AdmCountryCode = lup_prog_group_crop_Data.getString("AdmCountryCode");
                        String AdmDonorCode = lup_prog_group_crop_Data.getString("AdmDonorCode");
                        String AdmAwardCode = lup_prog_group_crop_Data.getString("AdmAwardCode");
                        String AdmProgCode = lup_prog_group_crop_Data.getString("AdmProgCode");
                        String CropCode = lup_prog_group_crop_Data.getString("CropCode");
                        String CropList = lup_prog_group_crop_Data.getString("CropList");
                        String CropCatCode = lup_prog_group_crop_Data.getString("CropCatCode");


                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode
                                + "AdmDonorCode: " + AdmDonorCode + "AdmAwardCode : " + AdmAwardCode + "AdmProgCode:" + AdmProgCode
                                + " CropCode: " + CropCode + "CropList:" + CropList + " CropCatCode :" + CropCatCode);

                        db.addLUP_ProgramGroupCropFromOnLine(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, CropCode, CropList, CropCatCode);
                    }
                }


                if (!jObj.isNull("lup_community_loan_source")) {
                    JSONArray lup_community_loan_source_Datas = jObj.getJSONArray("lup_community_loan_source");
                    size = lup_community_loan_source_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_community_loan_source_Data = lup_community_loan_source_Datas.getJSONObject(i);


                        String AdmCountryCode = lup_community_loan_source_Data.getString("AdmCountryCode");
                        String AdmDonorCode = lup_community_loan_source_Data.getString("AdmDonorCode");
                        String AdmAwardCode = lup_community_loan_source_Data.getString("AdmAwardCode");
                        String AdmProgCode = lup_community_loan_source_Data.getString("AdmProgCode");
                        String LoanCode = lup_community_loan_source_Data.getString("LoanCode");
                        String LoanSource = lup_community_loan_source_Data.getString("LoanSource");


                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode
                                + "AdmDonorCode: " + AdmDonorCode + "AdmAwardCode : " + AdmAwardCode + "AdmProgCode:" + AdmProgCode
                                + " LoanCode: " + LoanCode + "LoanSource:" + LoanSource);

                        db.addLUP_CommunityLoanSource(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode
                                , LoanCode, LoanSource);
                    }
                }


                if (!jObj.isNull("lup_community__lead_position")) {
                    JSONArray lup_community_lead_position_Datas = jObj.getJSONArray("lup_community__lead_position");
                    size = lup_community_lead_position_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject lup_community_lead_position_Data = lup_community_lead_position_Datas.getJSONObject(i);


                        String AdmCountryCode = lup_community_lead_position_Data.getString("AdmCountryCode");
                        String AdmDonorCode = lup_community_lead_position_Data.getString("AdmDonorCode");
                        String AdmAwardCode = lup_community_lead_position_Data.getString("AdmAwardCode");
                        String AdmProgCode = lup_community_lead_position_Data.getString("AdmProgCode");
                        String LeadCode = lup_community_lead_position_Data.getString("LeadCode");
                        String LeadPosition = lup_community_lead_position_Data.getString("LeadPosition");


                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode
                                + "AdmDonorCode: " + AdmDonorCode + "AdmAwardCode : " + AdmAwardCode + "AdmProgCode:" + AdmProgCode
                                + " LeadCode: " + LeadCode + "LeadPosition:" + LeadPosition);
                        db.addLUP_CommunityLeadPostition(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, LeadCode, LeadPosition);

                    }
                }


                if (!jObj.isNull("community_group_category")) {
                    JSONArray community_group_category_Datas = jObj.getJSONArray("community_group_category");
                    size = community_group_category_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject community_group_category_Data = community_group_category_Datas.getJSONObject(i);


                        String AdmCountryCode = community_group_category_Data.getString("AdmCountryCode");
                        String AdmDonorCode = community_group_category_Data.getString("AdmDonorCode");
                        String AdmAwardCode = community_group_category_Data.getString("AdmAwardCode");
                        String AdmProgCode = community_group_category_Data.getString("AdmProgCode");
                        String GrpCatCode = community_group_category_Data.getString("GrpCatCode");
                        String GrpCatName = community_group_category_Data.getString("GrpCatName");
                        String GrpCatShortName = community_group_category_Data.getString("GrpCatShortName");


                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode
                                + "AdmDonorCode: " + AdmDonorCode + "AdmAwardCode : " + AdmAwardCode + "AdmProgCode:" + AdmProgCode
                                + " GrpCatCode: " + GrpCatCode + " GrpCatName:" + GrpCatName
                                + " GrpCatShortName: " + GrpCatShortName
                        );
                        db.addCommunityGroupCategoryFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCatCode, GrpCatName, GrpCatShortName);

                    }
                }
                if (!jObj.isNull("lup_reg_n_add_lookup")) {
                    JSONArray lup_reg_n_add_lookup = jObj.getJSONArray("lup_reg_n_add_lookup");
                    size = lup_reg_n_add_lookup.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject Lup_reg_n_add_lookup = lup_reg_n_add_lookup.getJSONObject(i);


                        String AdmCountryCode = Lup_reg_n_add_lookup.getString("AdmCountryCode");
                        String RegNAddLookupCode = Lup_reg_n_add_lookup.getString("RegNAddLookupCode");
                        String RegNAddLookup = Lup_reg_n_add_lookup.getString("RegNAddLookup");
                        String LayR1ListCode = Lup_reg_n_add_lookup.getString("LayR1ListCode");
                        String LayR2ListCode = Lup_reg_n_add_lookup.getString("LayR2ListCode");
                        String LayR3ListCode = Lup_reg_n_add_lookup.getString("LayR3ListCode");
                        String LayR4ListCode = Lup_reg_n_add_lookup.getString("LayR4ListCode");


                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode + "RegNAddLookupCode" + RegNAddLookupCode
                                + "RegNAddLookup: " + RegNAddLookup + "LayR1ListCode : " + LayR1ListCode + "LayR2ListCode:" + LayR2ListCode
                                + " LayR3ListCode: " + LayR3ListCode + "LayR4ListCode:" + LayR4ListCode);
                        db.addLUP_RegNAddLookup(AdmCountryCode, RegNAddLookupCode, RegNAddLookup, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode);

                    }
                }


                if (!jObj.isNull("prog_org_n_role")) {
                    JSONArray prog_org_n_role_Datas = jObj.getJSONArray("prog_org_n_role");
                    size = prog_org_n_role_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject prog_org_n_role_Data = prog_org_n_role_Datas.getJSONObject(i);
                        String AdmCountryCode = prog_org_n_role_Data.getString("AdmCountryCode");
                        String AdmDonorCode = prog_org_n_role_Data.getString("AdmDonorCode");
                        String AdmAwardCode = prog_org_n_role_Data.getString("AdmAwardCode");
                        String OrgNCode = prog_org_n_role_Data.getString("OrgNCode");
                        String PrimeYN = prog_org_n_role_Data.getString("PrimeYN");
                        String SubYN = prog_org_n_role_Data.getString("SubYN");
                        String TechYN = prog_org_n_role_Data.getString("TechYN");
                        String LogYN = prog_org_n_role_Data.getString("LogYN");
                        String ImpYN = prog_org_n_role_Data.getString("ImpYN");
                        String OthYN = prog_org_n_role_Data.getString("OthYN");
                        Log.d("InTest", "AdmCountryCode:" + AdmCountryCode
                                + "AdmDonorCode: " + AdmDonorCode + "AdmAwardCode : " + AdmAwardCode + "OrgNCode:" + OrgNCode
                                + " PrimeYN: " + PrimeYN + " SubYN:" + SubYN
                                + " TechYN: " + TechYN
                                + " LogYN: " + LogYN + "ImpYN:" + ImpYN + " OthYN: " + OthYN
                        );
                        db.insertIntoProgOrgNRole(AdmCountryCode, AdmDonorCode, AdmAwardCode, OrgNCode, PrimeYN, SubYN, TechYN, ImpYN, LogYN, OthYN);
                    }
                }
                if (!jObj.isNull("org_n_code")) {
                    JSONArray org_n_code_Datas = jObj.getJSONArray("org_n_code");
                    size = org_n_code_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject org_n_code_Data = org_n_code_Datas.getJSONObject(i);
                        String OrgNCode = org_n_code_Data.getString("OrgNCode");
                        String OrgNName = org_n_code_Data.getString("OrgNName");
                        String OrgNShortName = org_n_code_Data.getString("OrgNShortName");
                        Log.d(TAG, "OrgNName:" + OrgNName + "OrgNShortName:" + OrgNShortName);
                        db.insertIntoProgOrgN(OrgNCode, OrgNName, OrgNShortName);
                    }
                }

                if (!jObj.isNull("community_grp_detail")) {
                    JSONArray community_grp_detail_Datas = jObj.getJSONArray("community_grp_detail");
                    size = community_grp_detail_Datas.length();
                    for (int i = 0; i < size; i++) {
                        JSONObject community_grp_detail_Data = community_grp_detail_Datas.getJSONObject(i);

                        String AdmCountryCode = community_grp_detail_Data.getString("AdmCountryCode");
                        String AdmDonorCode = community_grp_detail_Data.getString("AdmDonorCode");
                        String AdmAwardCode = community_grp_detail_Data.getString("AdmAwardCode");
                        String AdmProgCode = community_grp_detail_Data.getString("AdmProgCode");
                        String GrpCode = community_grp_detail_Data.getString("GrpCode");
                        String OrgCode = community_grp_detail_Data.getString("OrgCode");
                        String StfCode = community_grp_detail_Data.getString("StfCode");
                        String LandSizeUnderIrrigation = community_grp_detail_Data.getString("LandSizeUnderIrrigation");
                        String IrrigationSystemUsed = community_grp_detail_Data.getString("IrrigationSystemUsed");
                        String FundSupport = community_grp_detail_Data.getString("FundSupport");
                        String ActiveStatus = community_grp_detail_Data.getString("ActiveStatus");
                        String RepName = community_grp_detail_Data.getString("RepName");
                        String RepPhoneNumber = community_grp_detail_Data.getString("RepPhoneNumber");
                        String FormationDate = community_grp_detail_Data.getString("FormationDate");
                        String TypeOfGroup = community_grp_detail_Data.getString("TypeOfGroup");
                        String Status = community_grp_detail_Data.getString("Status");
                        String EntryBy = community_grp_detail_Data.getString("EntryBy");
                        String EntryDate = community_grp_detail_Data.getString("EntryDate");
                        String ProjectNo = community_grp_detail_Data.getString("ProjectNo");
                        String ProjectTitle = community_grp_detail_Data.getString("ProjectTitle");
      /*                  Log.d(TAG, "AdmCountryCode:" + AdmCountryCode + "AdmDonorCode:" + AdmDonorCode + "AdmAwardCode:" + AdmAwardCode +
                                "AdmProgCode:" + AdmProgCode + "GrpCode:" + GrpCode + "OrgCode:" + OrgCode + "StfCode:" + StfCode + "LandSizeUnderIrrigation:" + LandSizeUnderIrrigation +
                                "IrrigationSystemUsed:" + IrrigationSystemUsed + "FundSupport:" + FundSupport + "ActiveStatus:" + ActiveStatus +
                                "RepName:" + RepName + "RepPhoneNumber:" + RepPhoneNumber + "FormationDate:" + FormationDate + "TypeOfGroup:" + TypeOfGroup +
                                "Status" + Status + "EntryBy:" + EntryBy + "EntryDate:" + EntryDate + "ProjectNo:" + ProjectNo + "ProjectTitle:" + ProjectTitle);*/
                        db.addIntoGroupDetails(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode,
                                OrgCode, StfCode, LandSizeUnderIrrigation, IrrigationSystemUsed, FundSupport,
                                ActiveStatus, RepName, RepPhoneNumber, FormationDate, TypeOfGroup, Status,
                                EntryBy, EntryDate, ProjectNo, ProjectTitle);

                    }
                }


                if (!jObj.isNull("staff_master")) {
                    JSONArray staff_master_Datas = jObj.getJSONArray("staff_master");
                    size = staff_master_Datas.length();

                    String StfCode;
                    String OrigAdmCountryCode;
                    String StfName;
                    String OrgNCode;
                    String OrgNDesgNCode;
                    String StfStatus;
                    String StfCategory;
                    String UsrLogInName;
                    String UsrLogInPW;
                    String StfAdminRole;
                    for (int i = 0; i < size; i++) {
                        JSONObject staff_master_Data = staff_master_Datas.getJSONObject(i);


                        StfCode = staff_master_Data.getString("StfCode");
                        OrigAdmCountryCode = staff_master_Data.getString("OrigAdmCountryCode");
                        StfName = staff_master_Data.getString("StfName");
                        OrgNCode = staff_master_Data.getString("OrgNCode");
                        OrgNDesgNCode = staff_master_Data.getString("OrgNDesgNCode");
                        StfStatus = staff_master_Data.getString("StfStatus");
                        StfCategory = staff_master_Data.getString("StfCategory");
                        UsrLogInName = staff_master_Data.getString("UsrLogInName");
                        UsrLogInPW = staff_master_Data.getString("UsrLogInPW");
                        StfAdminRole = staff_master_Data.getString("StfAdminRole");


                        db.insertIntoStaffMasterTable(StfCode, OrigAdmCountryCode, StfName, OrgNCode, OrgNDesgNCode, StfStatus, StfCategory, UsrLogInName, UsrLogInPW, StfAdminRole);

                    }
                }


                if (!jObj.isNull("gps_lup_list")) {
                    JSONArray gps_lup_list_data = jObj.getJSONArray("gps_lup_list");
                    size = gps_lup_list_data.length();

                    String GrpCode;
                    String SubGrpCode;
                    String AttributeCode;
                    String LupValueCode;
                    String LupValueText;

                    for (int i = 0; i < size; i++) {
                        JSONObject gps_lup_list = gps_lup_list_data.getJSONObject(i);


                        GrpCode = gps_lup_list.getString("GrpCode");
                        SubGrpCode = gps_lup_list.getString("SubGrpCode");
                        AttributeCode = gps_lup_list.getString("AttributeCode");
                        LupValueCode = gps_lup_list.getString("LupValueCode");
                        LupValueText = gps_lup_list.getString("LupValueText");


                        db.insertIntoLupGpsList(GrpCode, SubGrpCode, AttributeCode, LupValueCode, LupValueText);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            showProgressDialog();
            progressIncremental = 0;
        }

        @Override
        protected void onPostExecute(Void string) {

            new Inject_Reg_HouseH_DataIntoSQLite().execute();
        }
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * Read JSON DATA Insert into SQLite
     */
    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(42);
        progressDialog.setMessage("Retrieving...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void operationMode(SharedPreferences settings) {
        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        Log.d("NIR1", "operation mode : " + operationMode);
        switch (operationMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
                /*btnNewReg.setEnabled(true);
                btnAssign.setEnabled(true);
                btnService.setEnabled(true);*/
                textOperationMode.setText("REGISTRATION");
                List<String> list;
                list = db.selectGeoDataVillage();
                String villageName = "";
                for (int i = 0; i < list.size(); i++) {
                    // Log.d("REFAT121---->",""+list.get(i));
                    villageName += list.get(i) + "\n";
                }
                textGeoData.setText(villageName);

                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:

                textOperationMode.setText("DISTRIBUTION");

                list = db.selectGeoDataFDP();
                String fdPName = "";
                for (int i = 0; i < list.size(); i++) {
                    // Log.d("REFAT121---->",""+list.get(i));
                    fdPName += list.get(i) + "\n";
                }
                textGeoData.setText(fdPName);
                break;
            case UtilClass.SERVICE_OPERATION_MODE:

                textOperationMode.setText("SERVICE");
                //List<String> list;
                list = db.selectGeoDataCenter();
                String centerName = "";
                for (int i = 0; i < list.size(); i++) {
                    //   Log.d("REFAT121---->",""+list.get(i));
                    centerName += list.get(i) + "\n";
                }
                textGeoData.setText(centerName);

                break;
        }
    }
}
