package com.siddiquinoor.restclient.manager.sqlsyntax;

import com.siddiquinoor.restclient.manager.SQLiteHandler;

/**
 * Created by Faisal on 2/18/2016.
 * This Class Contain The Schema
 */
public class Schema {

    private static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS ";

    /**
     * The patten of Table & serial
     * 1> userLogin
     * 2> Country
     * 3> Layer Label
     * 4> District
     */
    // userLogin
    public static String sqlCreateUserLoginTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LOGIN_TABLE + "("
                + SQLiteHandler.USER_ID + " TEXT,"
                + SQLiteHandler.COUNTRY_CODE + " TEXT,"
                + SQLiteHandler.USER_LOGIN_NAME + " TEXT,"
                + SQLiteHandler.USER_LOGIN_PW + " TEXT,"
                + SQLiteHandler.USER_FIRST_NAME + " TEXT,"
                + SQLiteHandler.USER_LAST_NAME + " TEXT,"
                + SQLiteHandler.USER_EMAIL + " TEXT,"
                + SQLiteHandler.USER_EMAIL_VERIFICATION + " TEXT,"
                + SQLiteHandler.USER_STATUS + " TEXT,"
                + SQLiteHandler.ENTRY_BY + " TEXT," + SQLiteHandler.ENTRY_DATE + " DATE" + ")";
    }


    public static  String sqlCreateStaffMasterTable(){


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.STAFF_MASTER_TABLE + "("
                + SQLiteHandler.STAFF_ID_COL + " TEXT ,"
                + SQLiteHandler.COUNTRY_CODE + " TEXT,"
                + SQLiteHandler.STAFF_NAME_COL + " TEXT,"
                + SQLiteHandler.ORG_CODE_COL + " TEXT,"
                + SQLiteHandler.ORG_N_DESG_N_CODE_COL + " TEXT,"
                + SQLiteHandler.STAFF_STATUS_COL + " TEXT,"

                + SQLiteHandler.STAFF_CATEGORY_COL + " TEXT,"
                + SQLiteHandler.USER_LOGIN_NAME + " TEXT,"
                + SQLiteHandler.USER_LOGIN_PW + " TEXT,"
                + SQLiteHandler.STAFF_ADMIN_ROLE_COL + " TEXT "
             + ")";





    }

    // Country schema
    public static String sqlCreateCountry() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COUNTRY_TABLE +
                "(" + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE + " VARCHAR(5), " + SQLiteHandler.COUNTRY_NAME + " VARCHAR(50))";
    }

    // Layer Label schema
    public static String sqlCreateLayerLabel() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LAYER_LABEL_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE + " VARCHAR(4), "
                + SQLiteHandler.LAYER_CODE_COL + " VARCHAR(1), "
                + SQLiteHandler.LAYER_NAME_COL + " VARCHAR(50))";
    }

    // District
    public static String sqlCreateDistrict() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.DISTRICT_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.LAYER_CODE_COL + " VARCHAR(2),"
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.DISTRICT_NAME_COL + " VARCHAR(50))";
    }

    // upazilla
    public static String sqlCreateUpazilla() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.UPAZILLA_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.LAYER_CODE_COL + " VARCHAR(2),"
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(50), "
                + SQLiteHandler.UPZILLA_NAME_COL + " VARCHAR(50))";
    }


    // RegNHHMemschema schema
    public static String sqlCreateRegMember() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + " , " + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.DISTRICT_NAME_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.UPZILLA_NAME_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.UNITE_NAME_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.VILLAGE_NAME_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.HHID_COL + " VARCHAR(7) "
                + " , " + SQLiteHandler.HH_MEM_ID + " VARCHAR(5) "
                + " , " + SQLiteHandler.MEM_NAME_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.SEX_COL + " VARCHAR(8) "
                + " , " + SQLiteHandler.RELATION_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(10) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " , " + SQLiteHandler.LMP_DATE + " DATE "
                + " , " + SQLiteHandler.CHILD_DOB + " DATE "
                + " , " + SQLiteHandler.ELDERLY + " VARCHAR(1) "
                + " , " + SQLiteHandler.DISABLE + " VARCHAR(1) "
                + " , " + SQLiteHandler.MEM_AGE + " VARCHAR(5) "
                + " , " + SQLiteHandler.REG_DATE_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.MARITAL_STATUS_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.CONTACT_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEMBER_OTHER_ID_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.MEM_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEM_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.MEM_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PHOTO_COL + " BLOB "
                + " , " + SQLiteHandler.TYPE_ID_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.ID_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.V_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_DESIGNATION_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BIRTH_YEAR_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.PROXY_PHOTO_COL + " BLOB "
                + " , " + SQLiteHandler.PROXY_TYPE_ID_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_ID_NO_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_FIRST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_MIDDLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_LAST_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_TITLE_COL + " VARCHAR(50) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(5) "

                + " )";
    }


    /**
     *
     */
    public static String sqlCreateRegMemberCardPrintTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.MEMBER_CARD_PRINT_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.HHID_COL + " VARCHAR(10), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.REPORT_GROUP_COL + " VARCHAR(10), "
                + SQLiteHandler.REPORT_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.CARD_REQUEST_SL_COL + " VARCHAR(10), "
                + SQLiteHandler.CARD_PRINT_REASON_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.REQUEST_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.PRINT_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.PRINT_BY_COL + " VARCHAR(10), "
                + SQLiteHandler.DELIVERY_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.DELIVERY_BY_COL + " VARCHAR(10), "
                + SQLiteHandler.DELIVERY_STATUS_COL + " VARCHAR(10), "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(10), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50), "

                + SQLiteHandler.SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }

    // Valid Date
    public static String sqlCreateDateRange() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VALID_DATE_RANGE
                + "(" + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE + " VARCHAR(4), "
                + SQLiteHandler.DATE_START + " DATE, "
                + SQLiteHandler.DATE_END + " DATE)";
    }


    /**
     *
     */
    public static String sqlCreateCardPrintReasonTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.CARD_PRINT_REASON_TABLE + "("
                + SQLiteHandler.CARD_PRINT_REASON_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.CARD_PRINT_REASON_TITLE_COL + " VARCHAR(50) )";
    }

    /**
     *
     */
    public static String sqlCreateCardTypeTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REPORT_TEMPLATE_TABLE + "("
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(6), "
                + SQLiteHandler.REPORT_LABLE_COL + " VARCHAR(50), "
                + SQLiteHandler.REPORT_CODE_COL + " VARCHAR(10) )";

    }

    /**
     *
     */

    public static String sqlCreateVillage() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VILLAGE_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.LAYER_CODE_COL + " VARCHAR(2),"
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VILLAGE_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.HOUSE_HOLD_TARGET + " VARCHAR(50))"; // new
    }

    // added @2015-09-29
    public static String sqlCreateGraduationTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.GRD_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.GRD_TITLE_COL + " VARCHAR(50), "
                + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " VARCHAR(50), "
                + SQLiteHandler.DEFAULT_CAT_EXIT_COL + " VARCHAR(50) ) ";


    }


    /**
     *
     *
     *
     */

    // todo  delete LIBERIA_REGISTRATION_TABLE
    public static String sqlCreateLiberiaRegistrationTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LIBERIA_REGISTRATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DISTRICT_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.UPZILLA_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.UNITE_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.VILLAGE_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.PID_COL + " VARCHAR(50), "
                + SQLiteHandler.REG_DATE_COL + " DATE, "
                + SQLiteHandler.PNAME_COL + " VARCHAR(50), "
                + SQLiteHandler.LT2YRS_M_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.LT2YRS_F_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.M_2TO5YRS_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.F_2TO5YRS_COL + " INT  DEFAULT 0 , "
                // added @2015-09-22
                + SQLiteHandler.M_6TO12YRS_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.F_6TO12YRS_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.M_13TO17YRS_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.F_13TO17YRS_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ORPHN_LT18YRS_M_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ORPHN_LT18YRS_F_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ADLT_18TO59_M_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ADLT_18TO59_F_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ELD_60P_M_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.ELD_60P_F_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.PLW_NO_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.CHRO_ILL_NO_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_COUNT_CATTLE_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_VALUE_CATTLE_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.AFT_COUNT_CATTLE_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.AFT_VALUE_CATTLE_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.BRF_COUNT_SGOATS_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_VALUE_SGOATS_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.AFT_COUNT_SGOATS_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.AFT_VALUE_SGOATS_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.BRF_COUNT_POULTRY_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_VALUE_POULTRY_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.AFT_COUNT_POULTRY_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.AFT_VALUE_POULTRY_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.BRF_COUNT_OTHER_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_VALUE_OTHER_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.AFT_COUNT_OTHER_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.AFT_VALUE_OTHER_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.BRF_ACRE_CULTIVABLE_COL + " INT  DEFAULT 0 , "
                + SQLiteHandler.BRF_VALUE_CULTIVABLE_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.AFT_ACRE_CULTIVABLE_COL + " INT DEFAULT 0 , "
                + SQLiteHandler.AFT_VALUE_CULTIVABLE_COL + " REAL DEFAULT 0 , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(25), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50), "
                + SQLiteHandler.SYNC_COL + "  BOOLEAN DEFAULT 0 )";


    }

    public static String sqlCreateHouseHoldCategoryTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.HOUSE_HOLD_CATEGORY_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.CATEGORY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.CATEGORY_NAME_COL + " VARCHAR(120) ) "

                ;
    }


    public static String sqlCreateStaffGeoInfoAccessTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.STAFF_CODE + " VARCHAR(20), "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.LAYR_LIST_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(5), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(5), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(5) , "/*
                +BTN_APRV_COL+" VARCHAR(5), "
                +BTN_REVW_COL+" VARCHAR(5), "
                +BTN_VRFY_COL+" VARCHAR(5) , " // grad code colm
                +BTN_DTRAN_COL+" VARCHAR(5)"+" )";*/


                + SQLiteHandler.BTN_NEW_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_SAVE_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_DEL_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_PEPR_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_APRV_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_REVW_COL + " VARCHAR(5), "
                + SQLiteHandler.BTN_VRFY_COL + " VARCHAR(5) , " // grad code colm
                + SQLiteHandler.BTN_DTRAN_COL + " VARCHAR(5)" +
                " )";
    }

    public static String sqlCreateRegNCA2Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_CA2_TABLE + " ( "
             //   + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.HHID_COL + " VARCHAR(10), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(50), "
                + SQLiteHandler.CA2DOB_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.GRDCODE_COL + " VARCHAR(10) , " // grad code colm
                + SQLiteHandler.CA2_GRD_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_NAME_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_SEX_COL + " VARCHAR(2) , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }


    public static String sqlCreateRegNCU2Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_CU2_TABLE + " ( "
             //   + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(6), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(2), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(50), "
                + SQLiteHandler.CU2DOB_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.GRDCODE_COL + " VARCHAR(2) , " // grad code colm
                + SQLiteHandler.CU2_GRD_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_NAME_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_SEX_COL + " VARCHAR(2) , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(50) , "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50), "
                + SQLiteHandler.SYNC_COL + "  BOOLEAN DEFAULT 0 )";
    }

    public static String sqlCreateRegNPWTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_PW_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.HHID_COL + " VARCHAR(10), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(50), "
                + SQLiteHandler.LMP_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.GRDCODE_COL + " VARCHAR(10) , "
                + SQLiteHandler.PW_GRD_DATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.SYNC_COL + "  BOOLEAN DEFAULT 0 )";
    }

    public static String sqlCreateServiceCenterTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_CENTER_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SERVICE_CENTER_NAME_COL + " VARCHAR(100), "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(5)"
                + " )";

    }


    /*AdmCountryAward added by Faisal Mohammad create AdmCountryAward table AdmCountryAwardSchema schema */
    public static String sqlCreateCountryAwardTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.ADM_AWARD_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.AWARD_REF_N_COL + " VARCHAR(50), "
                + SQLiteHandler.AWARD_START_DATE_COL + " VARCHAR(100), "
                + SQLiteHandler.AWARD_END_DATE_COL + " VARCHAR(100), "
                + SQLiteHandler.AWARD_S_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.AWARD_STATUS_COL + " VARCHAR(50) ) ";
    }

    /**
     * creating Sub Assigne Table
     **/

    public static String sqlCreateRegN_AGR_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_AGR_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(5), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(20), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(20), "
                + SQLiteHandler.ELDERLY_YN_COL + " VARCHAR(1), "
                + SQLiteHandler.LAND_SIZE_COL + " VARCHAR(21), "
                + SQLiteHandler.DEPEND_ON_GANYU_COL + " VARCHAR(1), "
                + SQLiteHandler.WILLINGNESS_COL + " VARCHAR(1), "
                + SQLiteHandler.WINTER_CULTIVATION_COL + " VARCHAR(1), "
                + SQLiteHandler.VULNERABLE_HH_COL + " VARCHAR(1), "
                + SQLiteHandler.PLANTING_VALUE_CHAIN_CROP_COL + " VARCHAR(3), "

                + SQLiteHandler.AG_INVC + " VARCHAR(1) DEFAULT 'N', "
                + SQLiteHandler.AG_NASFAM + " VARCHAR(1) DEFAULT 'N', "
                + SQLiteHandler.AG_CU +" VARCHAR(1) DEFAULT 'N', "
                + SQLiteHandler.AG_OTHER+" VARCHAR(1) DEFAULT 'N', "
                + SQLiteHandler.AG_L_S_GOAT+" INT DEFAULT 0, "
                + SQLiteHandler.AG_L_S_CHICKEN +" INT DEFAULT 0, "
                + SQLiteHandler.AG_L_S_PIGION + " INT DEFAULT 0, "
                + SQLiteHandler.AG_L_S_OTHER + " INT DEFAULT 0, "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) DEFAULT '0' "
                + " )";


    }

    public static String sqlCreateDistributionTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.DISTRIBUTION_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.MEM_ID_15_D_COL + " VARCHAR(25), "
                + SQLiteHandler.DISTRIBUTION_STATUS_COL + " VARCHAR(2), "

                + SQLiteHandler.SRV_OP_MONTH_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.DIST_FLAG_COL + " VARCHAR(100), "
                + SQLiteHandler.WORK_DAY_COL + " VARCHAR(10), "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(20), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(22) "
                + " )";
    }

    public static String sqlCreateStaffFDPAccessTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + " ( "
                + SQLiteHandler.STAFF_CODE + " VARCHAR(5) , "
                + SQLiteHandler.COUNTRY_CODE + " VARCHAR(5) , "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(5) , "
                + SQLiteHandler.BTN_NEW_COL + " VARCHAR(1) , "
                + SQLiteHandler.BTN_SAVE_COL + " VARCHAR(1) , "
                + SQLiteHandler.BTN_DEL_COL + " VARCHAR(1)  "
                + " ) ";
    }

    public static String sqlCreateFDP_Master_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.FDP_MASTER_TABLE + " ( "
                + SQLiteHandler.COUNTRY_CODE + " VARCHAR(5) , "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(6) , "
                + SQLiteHandler.FDP_NAME_COL + " VARCHAR(100) , "
                + SQLiteHandler.FDA_CATEGORIES_CODE_COL + " VARCHAR(10) , "
                + SQLiteHandler.WH_CODE_COL + " VARCHAR(3)  ,"
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(6)  ,"
                + SQLiteHandler.UPCODE_COL + " VARCHAR(6)  "
                + " ) ";
    }


    public static String sqlCreate_RegN_CT_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_CT_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.HHID_COL + " VARCHAR(10), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.C11_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C21_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C31_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C32_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C33_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C34_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C35_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C36_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C37_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.C38_CT_PR + " VARCHAR(1) , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(10), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " )";
    }

    /**
     * In this table SQL Server will be Inserted
     */

    public static String sqlCreateUploadTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.UPLOAD_SYNTAX_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.SQL_QUERY_SYNTAX + " BLOB , "
                + SQLiteHandler.SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }

    public static String sqlCreateRegNLMTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_LM_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(6), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(3), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(50), "
                + SQLiteHandler.LM_DATE_COL + " VARCHAR(50), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.GRDCODE_COL + " VARCHAR(2) , "
                + SQLiteHandler.LMGRDDATE_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_NAME_COL + " VARCHAR(50) , "
                + SQLiteHandler.CHILD_SEX_COL + " VARCHAR(2) , "

                + SQLiteHandler.ENTRY_BY + " VARCHAR(5) , "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.SYNC_COL + "  BOOLEAN DEFAULT 0"
                + " )";

    }

    public static String sqlCreateADM_CountryProgram() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COUNTRY_PROGRAM_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT"
                + " , " + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.FOOD_FLAG + " VARCHAR(1)"
                + " , " + SQLiteHandler.NON_FOOD_FLAG + " VARCHAR(1)"
                + " , " + SQLiteHandler.CASH_FLAG + " VARCHAR(1)"
                + " , " + SQLiteHandler.VOUCHER_FLAG + " VARCHAR(1)"
                + " , " + SQLiteHandler.DEFAULT_FOOD_DAYS_COL + " VARCHAR(4)"
                + " , " + SQLiteHandler.DEFAULT_NO_FOOD_DAYS_COL + " VARCHAR(4)"
                + " , " + SQLiteHandler.DEFAULT_CASH_DAYS_COL + " VARCHAR(4)"
                + " , " + SQLiteHandler.DEFAULT_VOUCHAR_DAYS_COL + " VARCHAR(4)"
                + " , " + SQLiteHandler.SERVICE_SPECIFIC_FLAG_COL + " VARCHAR(4)"

                + " )";
    }

    public static String sqlCreateOpMonthTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.OP_MONTH_TABLE + " ( "
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.OPERATION_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(50), "
                + SQLiteHandler.MONTH_LABEL + " VARCHAR(50), "
                + SQLiteHandler.START_DATE + " VARCHAR(20), "
                + SQLiteHandler.END_DATE + " VARCHAR(20), "
                + SQLiteHandler.USA_START_DATE + " VARCHAR(20), "
                + SQLiteHandler.USA_END_DATE + " VARCHAR(20), "
                + SQLiteHandler.STATUS + " VARCHAR(20) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create  LOCATION TABLE table
     * AdmGpsLocation_Schema schema
     * remarks-
     */
    public static String sqlCreateGpsLocationTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_LOCATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.LOCATION_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.LOCATION_NAME_COL + " VARCHAR(100), "
                + SQLiteHandler.LATITUDE_COL + " VARCHAR(20), "
                + SQLiteHandler.LONGITUDE_COL + " VARCHAR(20) , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(20) , "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create GPS_SUB_GROUP_TABLE table
     * AdmServiceMaster_Schema schema
     * remarks-
     */

    public static String sqlCreateGpsSubGroupTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_SUB_GROUP_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SUB_GROUP_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.DESCRIPTION_COL + " VARCHAR(100) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create GPS_GROUP_TABLE table
     * <p/>
     * remarks-
     */
    public static String sqlCreateGpsGroupTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_GROUP_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.GROUP_NAME_COL + " VARCHAR(20), "
                + SQLiteHandler.DESCRIPTION_COL + " VARCHAR(100) ) ";
    }

    /**
     * added by Faisal Mohammad
     * create Registration Assign Program Srv table
     * <p/>
     * remarks-
     */
    public static String sqlCreateRegNAssignPrgSrvTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.HHID_COL + " VARCHAR(10), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(10), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(50)  , "
                + SQLiteHandler.GRD_CODE_COL + " VARCHAR(10)  , "
                + SQLiteHandler.GRD_DATE_COL + " VARCHAR(50)  , "
                + SQLiteHandler.REG_N_STATUS_COL + " VARCHAR(20) , "
                + SQLiteHandler.GRD_STATUS_COL + " VARCHAR(20)  , "

                + SQLiteHandler.SRV_MIN_DATE_COL + " VARCHAR(50)  , "
                + SQLiteHandler.SRV_MAX_DATE_COL + " VARCHAR(50)  , "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(5) , "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) , "
                + SQLiteHandler.SYNC_COL + " DEFAULT 0 " +
                " ) ";


    }


    /**
     * added by Faisal Mohammad
     * create Service Master table
     * AdmServiceMaster_Schema schema
     * remarks-
     */
    public static String sqlCreateServiceMasterTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_MASTER_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.SERVICE_NAME_COL + " VARCHAR(100), "
                + SQLiteHandler.SERVICE_SHORT_NAME_COL + " VARCHAR(50)  ) ";

    }

    /**
     * added by Faisal Mohammad
     * create Program Master table
     * AdmProgramMaster_Schema schema
     * remarks-
     */
    public static String sqlCreateProgramMasterTable() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.PROGRAM_MASTER_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.PROGRAM_NAME_COL + " VARCHAR(100), "
                + SQLiteHandler.PROGRAM_SHORT_NAME_COL + " VARCHAR(50), "
                + SQLiteHandler.MULTIPLE_SERVICE_FLAG_COL + " VARCHAR(2) "

                + " ) ";
    }

    /**
     * added by Faisal Mohammad
     * create Donor table
     * AdmDonorSchema schema
     * remarks - ok
     */
    public static String sqlCreateDonorTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.ADM_DONOR_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(20), "
                + SQLiteHandler.DONOR_NAME_COL + " VARCHAR(100) ) ";
    }


    public static String sqlCreateRegistration() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REGISTRATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRICT_NAME_COL + " VARCHAR(10), "
                + SQLiteHandler.UPZILLA_NAME_COL + " VARCHAR(10), "
                + SQLiteHandler.UNITE_NAME_COL + " VARCHAR(10), "
                + SQLiteHandler.VILLAGE_NAME_COL + " VARCHAR(10), "
                + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.PID_COL + " VARCHAR(10), "
                + SQLiteHandler.REG_DATE_COL + " DATE, "
                + SQLiteHandler.PNAME_COL + " VARCHAR(50), "
                + SQLiteHandler.SEX_COL + " VARCHAR(8), "
                + SQLiteHandler.HH_SIZE + " VARCHAR(10), "
                + SQLiteHandler.LATITUDE_COL + " VARCHAR(20), "
                + SQLiteHandler.LONGITUDE_COL + " VARCHAR(20), "
                + SQLiteHandler.AG_LAND + " VARCHAR(20), "
                + SQLiteHandler.V_STATUS + " VARCHAR(10), "
                + SQLiteHandler.M_STATUS + " VARCHAR(10), "

                + SQLiteHandler.ENTRY_BY + " VARCHAR(20), "
                + SQLiteHandler.ENTRY_DATE + " DATE, "
                + SQLiteHandler.VSLA_GROUP + " VARCHAR(10), "
                + SQLiteHandler.GPS_LONG_SWAP + " VARCHAR(100), "
                + SQLiteHandler.HOUSE_HOLD_TYPE_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.LT2YRS_M_COL + " VARCHAR(10), "
                + SQLiteHandler.LT2YRS_F_COL + " VARCHAR(10), "
                + SQLiteHandler.M_2TO5YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.F_2TO5YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.M_6TO12YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.F_6TO12YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.M_13TO17YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.F_13TO17YRS_COL + " VARCHAR(10), "
                + SQLiteHandler.ORPHN_LT18YRS_M_COL + " VARCHAR(10), "
                + SQLiteHandler.ORPHN_LT18YRS_F_COL + " VARCHAR(10), "
                + SQLiteHandler.ADLT_18TO59_M_COL + " VARCHAR(10), "
                + SQLiteHandler.ADLT_18TO59_F_COL + " VARCHAR(10), "
                + SQLiteHandler.ELD_60P_M_COL + " VARCHAR(10), "
                + SQLiteHandler.ELD_60P_F_COL + " VARCHAR(10), "
                + SQLiteHandler.PLW_NO_COL + " VARCHAR(10), "
                + SQLiteHandler.CHRO_ILL_NO_COL + " VARCHAR(10), "
                + SQLiteHandler.DECEASED_CONTRACT_EBOLA_COL + " VARCHAR(10), "
                + SQLiteHandler.EXTRA_CHILD_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + SQLiteHandler.EXTRA_ELDERLY_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + SQLiteHandler.EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_COUNT_CATTLE_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VALUE_CATTLE_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_COUNT_CATTLE_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_VALUE_CATTLE_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_COUNT_SGOATS_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VALUE_SGOATS_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_COUNT_SGOATS_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_VALUE_SGOATS_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_COUNT_POULTRY_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VALUE_POULTRY_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_COUNT_POULTRY_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_VALUE_POULTRY_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_COUNT_OTHER_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VALUE_OTHER_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_COUNT_OTHER_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_VALUE_OTHER_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_ACRE_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VALUE_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_ACRE_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_VALUE_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_ACRE_NON_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_NON_CULTIVABLE_COL + " VARCHAR(10), "
                + SQLiteHandler.AFT_ACRE_NON_CULTIVABLE + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_NON_CULTIVABLE + " VARCHAR(10), "
                + SQLiteHandler.BRF_ACRE_ORCHARDS + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_ORCHARDS + " VARCHAR(10), "
                + SQLiteHandler.AFT_ACRE_ORCHARDS + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_ORCHARDS + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_CROP + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_CROP + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_LIVESTOCK + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_LIVESTOCK + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_SMALL_BUSINESS + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_SMALL_BUSINESS + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_EMPLOYMENT + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_EMPLOYMENT + " VARCHAR(10), "
                + SQLiteHandler.BRF_VAL_REMITTANCES + " VARCHAR(10), "
                + SQLiteHandler.AFT_VAL_REMITTANCES + " VARCHAR(10), "
                + SQLiteHandler.BRF_CNT_WAGEENR + " VARCHAR(10), "
                + SQLiteHandler.AFT_CNT_WAGEENR + " VARCHAR(10), "
                + SQLiteHandler.W_RANK_COL + " VARCHAR(10), "

                + SQLiteHandler.LTP_2_HECTRES_COL+" VARCHAR(2) , "
                + SQLiteHandler.LT_3_FOOD_STOCK_COL +" VARCHAR(2) , "
                + SQLiteHandler.NO_MAJOR_COMMON_LIVE_STOCK_COL+" VARCHAR(2), "
                + SQLiteHandler.RECEIVE_NO_FORMAL_WAGES_COL+" VARCHAR(2), "
                + SQLiteHandler.NO_IGA_COL+" VARCHAR(2) , "
                + SQLiteHandler.RELY_PICE_EORK_COL+" VARCHAR(2) ,"

                + SQLiteHandler.SYNC_COL + " BOOLEAN DEFAULT 0 )";
    }

    /**
     * Service Table Schema
     */

    public static String sqlCreateServiceTable() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_TABLE + "("
             //   + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(5), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(2), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.OPERATION_CODE_COL + " VARCHAR(1), "
                + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.SERVICE_SL_COL + " VARCHAR(2)  , "
                + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(3) , "
                + SQLiteHandler.SERVICE_DT_COL + " VARCHAR(20) DEFAULT '00' , "
                + SQLiteHandler.SERVICE_STATUS_COL + " VARCHAR(2)  , "
                + SQLiteHandler.DISTRIBUTION_STATUS_COL + " VARCHAR(1)  , "
                + SQLiteHandler.DIST_DATE_COL + " VARCHAR(20) DEFAULT '00' , "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(4) , "
                + SQLiteHandler.WORK_DAY_COL + " VARCHAR(6) , "
                + SQLiteHandler.DIST_FLAG_COL + " VARCHAR(100), "
                /** todo: this column may not be necessary in future delete id no use now */
                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4)  , "
                // for total synch summary report need entry by & entry date
                + SQLiteHandler.ENTRY_BY + " VARCHAR(4) DEFAULT '00' , "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) DEFAULT '00' , "
                + SQLiteHandler.SYNC_COL + "  BOOLEAN DEFAULT 0 ) "
                ;// delete last comma ,

    }


    // Creating RegNHHMember Schema
    public static String sqlCreateRegRelation() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.RELATION_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.RELATION_CODE + " VARCHAR(10), "
                + SQLiteHandler.RELATION_NAME + " VARCHAR(100)"
                + " )";
    }

    // Creating LUP_SrvOptionList Schema
    public static String sqlCreateLUP_SrvOptionList() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE + "("
                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_OPTION_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_OPTION_NAME_COL + " VARCHAR(4) "
                + " )";
    }


    // create Vul table schema
    public static String sqlCreateRegNVUL_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_VUL_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(5), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(20), "
                + SQLiteHandler.REG_N_DAT_COL + " VARCHAR(20), "
                + SQLiteHandler.Disabled_YN_COL + " VARCHAR(1), "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(50) DEFAULT '0', "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) DEFAULT '0' "
                + " )";

    }

    // create Vul table schema
    public static String sqlCreateVoucherItem_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_ITEM_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.CATEGORY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.ITEM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.ITEM_NAME_COL + " VARCHAR(100) "

                + " )";

    }

    public static String sqlCreateVoucherItemMeas_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.MEAS_R_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.UNITE_MEAS_COL + " VARCHAR(5), "
                + SQLiteHandler.MEASE_TITLE_COL + " VARCHAR(100) "

                + " )";

    }

    public static String sqlCreateVoucherCountryProgItem_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.CATEGORY_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.ITEM_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.MEAS_R_CODE_COL + " VARCHAR(5), "
                + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + SQLiteHandler.UNITE_COST_COL + " VARCHAR(25), "
                + SQLiteHandler.ACTIVE_COL + " VARCHAR(5), "
                + SQLiteHandler.CURRENCY_COL + " VARCHAR(5), "
                + SQLiteHandler.SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";

    }


    public static String sqlCreateServiceExtended_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_EXTENDED_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.HHID_COL + " VARCHAR(5), "
                + SQLiteHandler.HH_MEM_ID + " VARCHAR(2), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.OPERATION_CODE_COL + " VARCHAR(1), "
                + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(2), "

                + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + SQLiteHandler.VOUCHER_UNIT_COL + " VARCHAR(25), "
                + SQLiteHandler.VOUCHER_REFERENCE_NUMBER_COL + " VARCHAR(18), "
                + SQLiteHandler.VOUCHER_ITEM_COST_COL + " VARCHAR(5), "
                + SQLiteHandler.DIST_FLAG_COL + " VARCHAR(100), "
                + SQLiteHandler.ENTRY_BY + " VARCHAR(5), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20), "
                + SQLiteHandler.SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";


    }


    public static String sqlCreateDistributionExtended_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3), "
                + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.MEM_ID_15_D_COL + " VARCHAR(25), "
                + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " VARCHAR(15), "
                + SQLiteHandler.VOUCHER_UNIT_COL + " VARCHAR(25), "
                + SQLiteHandler.VOUCHER_REFERENCE_NUMBER_COL + " VARCHAR(18), "

                + SQLiteHandler.SRV_OP_MONTH_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.DIST_FLAG_COL + " VARCHAR(100), "

                + SQLiteHandler.ENTRY_BY + " VARCHAR(5), "
                + SQLiteHandler.ENTRY_DATE + " VARCHAR(20), "
                + SQLiteHandler.SYNC_COL + " VARCHAR(5) DEFAULT '0' "
                + " )";


    }


    public static String sqlCreateSelectedVillage_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SELECTED_VILLAGE_TABLE + " ("

                + SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UPCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.UCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VCODE_COL + " VARCHAR(2), "
                + SQLiteHandler.LAYER_CODE_COL + " VARCHAR(2), "
                + SQLiteHandler.VILLAGE_NAME_COL + " VARCHAR(2), "
                + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(4) "

                + " )";


    }


    public static String sqlCreateSelectedFDP_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SELECTED_FDP_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.FDP_NAME_COL + " VARCHAR(100) "
                + " )";


    }

    public static String sqlCreateSelectedServiceCenter_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SELECTED_SERVICE_CENTER_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.SERVICE_CENTER_NAME_COL + " VARCHAR(100) "
                + " )";


    }


    public static String sqlCreateCommunityGroup_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.UPCODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(4) "
                + " )";


    }


    public static String sqlCreateGPSSubGroupAttributes_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_SUB_GROUP_ATTRIBUTES_TABLE + " ("


                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_TITLE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DATA_TYPE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOOK_UP_CODE_COL + " VARCHAR(4) "
                + " )";


    }

    public static String sqlCreateLUP_GPS_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_GPS_TABLE + " ("


                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOOK_UP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOOK_UP_NAME_COL + " VARCHAR(4) "
                + " )";


    }

    public static String sqlCreateGPSLocationAttributes_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_LOCATION_ATTRIBUTES_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOCATION_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ATTRIBUTE_VALUE_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.ATTRIBUTE_PHOTO_COL + " TEXT "
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " )";


    }

    // Creating LUP_SrvOptionList Schema
    public static String sqlCreateLUP_GpsList() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_GPS_LIST_TABLE + "("

                + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(5)  , "
                + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.ATTRIBUTE_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_VALUE_CODE_COL + " VARCHAR(4), "
                + SQLiteHandler.LUP_VALUE_TEXT_COL + " VARCHAR(100) "
                + " )";
    }



    public static String sqlCreateServiceSpecification_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.SERVICE_SPECIFIC_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.UPCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.UCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HHID_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.HH_MEM_ID + " VARCHAR(2) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.OPERATION_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.OP_MONTH_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.FDP_CODE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.SERVICE_STATUS_COL + " VARCHAR(4) "

                + " , " + SQLiteHandler.BABY_STATUS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.GMP_ATTENDACE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.WEIGHT_STATUS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NUT_ATTENDANCE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VITA_UNDER5_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.EXCLUSIVE_CURRENTLYBF_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DATE_COMPFEEDING_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.CMAMREF_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CMAMADD_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.ANCVISIT_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.PNCVISIT_2D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.PNCVISIT_1W_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.PNCVISIT_6W_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.DELIVERY_STAFF_1_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DELIVERY_STAFF_2_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DELIVERY_STAFF_3_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.HOME_SUPPORT24H_1D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_2D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_3D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_8D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_14D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_21D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_30D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_60D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT24H_90D_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.HOME_SUPPORT48H_1D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_3D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_8D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_30D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_60D_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HOME_SUPPORT48H_90D_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.MATERNAL_BLEEDING_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_SEIZURE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_INFECTION_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_PROLONGEDLABOR_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_OBSTRUCTEDLABOR_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.MATERNAL_PPRM_COL + " VARCHAR(2) "


                + " , " + SQLiteHandler.NBORN_ASPHYXIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_SEPSIS_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_HYPOTHERMIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_HYPERTHERMIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_NOSUCKLING_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.NBORN_JAUNDICE_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.CHILD_DIARRHEA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_PNEUMONIA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_FEVER_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.CHILD_CEREBRALPALSY_COL + " VARCHAR(2) "


                + " , " + SQLiteHandler.IMMU_POLIO_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_BCG_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_MEASLES_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_DPT_HIB_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_LOTTA_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.IMMU_OTHER_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.FPCOUNSEL_MALECONDOM_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_FEMALECONDOM_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_PILL_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_DEPO_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_LONGPARMANENT_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.FPCOUNSEL_NOMETHOD_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.CROP_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LOAN_SOURCE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LOAN_AMT_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.ANIMAL_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.LEAD_CODE_COL + " VARCHAR(3) "


                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " )";


    }


    public static String sqlCreateLUP_CommunityAnimalList_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_COMMUNITY_ANIMAL_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ANIMAL_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ANIMAL_TYPE_COL + " VARCHAR(100) "

                + " )";


    }


    public static String sqlCreateLUP_ProgramGroupCrop_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_PROG_GROUP_CROP_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.CROP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.CROP_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.CROP_CAT_COL + " VARCHAR(10) "

                + " )";


    }

    public static String sqlCreateLUP_CommunityLoanSource_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_COMMUNITY_LOAN_SOURCE_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOAN_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LOAN_SOURCE_COL + " VARCHAR(100) "


                + " )";


    }


    public static String sqlCreateLUP_CommunityLeadPosition_Table() {

        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.LUP_COMMUNITY_LEAD_POSITION_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LEAD_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LEAD_POSITION_COL + " VARCHAR(100) "


                + " )";
    }

    public static String sqlCreateRegNmemProgGrp_Table() {


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.UPCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.UCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HHID_COL + " VARCHAR(6) "
                + " , " + SQLiteHandler.HH_MEM_ID + " VARCHAR(2) "
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.SERVICE_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.ACTIVE_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "

                + " )";
    }


    public static String sqlCreateCommunityGroupCategoryes_Table() {


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE + " ("


                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "

                + " , " + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(2) "

                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(3) "
                + " , " + SQLiteHandler.GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_CAT_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.GROUP_CAT_SHORT_NAME_COL + " VARCHAR(5) "


                + " )";
    }


    public static String sqlCreate_Gps_Location_Content_Table() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.GPS_LOCATION_CONTENT_TABLE + " ("
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.SUB_GROUP_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.LOCATION_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.CONTENT_CODE_COL + " VARCHAR(5)"
                + " , " + SQLiteHandler.IMAGE_FILE_COL + " BLOB "
                + " , " + SQLiteHandler.REMARKES_COL + " VARCHAR(10)"
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " ) ";
    }

    public static String lastSyncTime()
    {
        return CREATE_TABLE_IF_NOT_EXISTS +SQLiteHandler.LAST_SYNC_TYRACE_TABLE
                +" ( "
                +SQLiteHandler.ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +SQLiteHandler.USER_ID + " TEXT , "
                +SQLiteHandler.USER_LOGIN_NAME+" TEXT ,"
                +SQLiteHandler.LAST_SYNC_TIME_COL+" TEXT "
                +" ) ";
    }

    public static String createTableRegN_FFA()
    {
        return CREATE_TABLE_IF_NOT_EXISTS+SQLiteHandler.REG_N_FFA_TABLE
                +" ( "
                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.UPCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.UCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.HHID_COL + " VARCHAR(5) "
                + " , " + SQLiteHandler.HH_MEM_ID+" VARCHAR(2) "
                + " , " + SQLiteHandler.ORPHAN_CHILDREN_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.CHILD_HEADED_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.ELDERLY_HEADED_COL+" VARCHAR(1) "
                + " , " + SQLiteHandler.CHRONICALLY_ILL_COL +" VARCHAR(1) "
                + " , " + SQLiteHandler.FEMALE_HEADED_COL +" VARCHAR(1) "
                + " , " + SQLiteHandler.CROP_FAILURE_COL + " VARCHAR(1) "
                +"  , " + SQLiteHandler.CHILDREN_REC_SUPP_FEED_N_COL +" VARCHAR(1) "
                +"  , " + SQLiteHandler.WILLINGNESS_COL+ " VARCHAR(1) "
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(20) "
                + " ) ";
    }



    public static String sqlCreateDistNPlanBasic() {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.DIST_N_PLAN_BASIC_TABLE + "("

                + SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DONOR_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.AWARD_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.OPERATION_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.SRV_OP_MONTH_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DIST_OP_MONTH_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.FDP_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DIST_FLAG_COL + " VARCHAR(10), "
                + SQLiteHandler.ORG_CODE_COL + " VARCHAR(10), "
                + SQLiteHandler.DISTRIBUTOR_COL + " VARCHAR(10), "

                + SQLiteHandler.DISTRIBUTION_DATE_COL + " VARCHAR(50)  , "

                + SQLiteHandler.DELIVERY_DATE_COL + " VARCHAR(50)  , "
                + SQLiteHandler.STATUS + " VARCHAR(50)  , "
                + SQLiteHandler.PREPARED_BY_COL + " VARCHAR(20) , "
                + SQLiteHandler.VERIFIED_BY_COL + " VARCHAR(20) , "
                + SQLiteHandler.APPROVED_BY_COL + " VARCHAR(20)  "


                +" ) ";


    }

    public static  String createTableLUP_RegNAddLookup()
    {
        return CREATE_TABLE_IF_NOT_EXISTS+ SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                +" ( "
                +SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " +SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " VARCHAR(3)"
                + " , " +SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL +" VARCHAR(100)"
                + " , " + SQLiteHandler.DISTRICT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.UPCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.UCODE_COL + " VARCHAR(2) "
                + " , " + SQLiteHandler.VCODE_COL + " VARCHAR(2) "
                +" ) ";
    }




    public static  String createTableCommunityGrpDetail(){


        return CREATE_TABLE_IF_NOT_EXISTS+ SQLiteHandler.COMMUNITY_GRP_DETAIL_TABLE
                +" ( "
                +SQLiteHandler.COUNTRY_CODE_COL + " VARCHAR(4) "
                + " , " +SQLiteHandler.DONOR_CODE_COL + " VARCHAR(4)"
                + " , " +SQLiteHandler.AWARD_CODE_COL +" VARCHAR(4)"
                + " , " + SQLiteHandler.PROGRAM_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.GROUP_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ORG_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.STAFF_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAND_SIZE_UNDER_IRRIGATION_COL + " VARCHAR(20) "
                + " , " + SQLiteHandler.IRRIGATION_SYSTEM_USED_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.FUND_SUPPORT_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.ACTIVE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.REP_NAME_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.REP_PHONE_NUMBER_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.FORMATION_DATE_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.TYPE_OF_GROUP  + " VARCHAR(4) "
                + " , " + SQLiteHandler.STATUS + " VARCHAR(100) "
                + " , " + SQLiteHandler.ENTRY_BY + " VARCHAR(4) "
                + " , " + SQLiteHandler.ENTRY_DATE + " VARCHAR(50) "
                + " , " + SQLiteHandler.PROJECT_NO_COL + " VARCHAR(100) "
                + " , " + SQLiteHandler.PROJECT_TITLE + " VARCHAR(200) "
                +" ) ";
    }


    public static String createTableProgOrgNRole()
    {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.PROGRAM_ORGANIZATION_ROLE_TABLE
                +" ( "
                +SQLiteHandler.COUNTRY_CODE_COL +" varchar (4) "
                +" , "+SQLiteHandler.DONOR_CODE_COL+" VARCHAR(4) "
                +" , "+SQLiteHandler.AWARD_CODE_COL+" VARCHAR(4) "
                +" , "+SQLiteHandler.ORG_CODE_COL +" VARCHAR(3) "
                +" , "+SQLiteHandler.PRIME_Y_N +" VARCHAR (1) "
                +" , "+SQLiteHandler.SUB_Y_N +" VARCHAR (1) "
                +" , "+SQLiteHandler.TECH_Y_N +" VARCHAR (1) "
                +" , "+SQLiteHandler.LONG_Y_N +" VARCHAR (1) "
                +" , "+SQLiteHandler.OTH_Y_N +" VARCHAR (1) "
                +" , "+SQLiteHandler.IMP_Y_N +" VARCHAR (1) "
                +" ) ";
    }
    public static String createTableProgOrgN()
    {
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.PROGRAM_ORGANIZATION_NAME_TABLE
                +" ( "
                +SQLiteHandler.ORG_CODE_COL+" VARCHAR(100), "
                +SQLiteHandler.ORGANIZATION_NAME+" VARCHAR(100) "
                +" , "+SQLiteHandler.ORGANIZATION_SHORT_NAME+" VARCHAR (20) "
                +" ) ";
    }

}
