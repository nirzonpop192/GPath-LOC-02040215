package com.siddiquinoor.restclient.parse;

import android.util.Base64;
import android.util.Log;

import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.data_model.AdmCountryDataModel;
import com.siddiquinoor.restclient.manager.SQLiteHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Faisal on 7/16/2016.
 * this Class DCeserializ the Json data
 */
public class Parser {

    /**
     * Json column values constant
     */
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
    //  public static final String ID = ID;
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

    public static final String DIST_FLAG = "DistFlag";
    public static final String SRV_OP_MONTH_CODE = "SrvOpMonthCode";
    public static final String VO_ITM_MEAS_TABLE_JSON_A = "vo_itm_meas_table";
    public static final String LUP_GPS_TABLE_JSON_A = "lup_gps_table";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_JSON_A = "gps_sub_group_attributes";
    public static final String GPS_LOCATION_ATTRIBUTES_JSON_A = "gps_location_attributes";


    private static final String TAG = "JsonDeserialization";

    /**
     * This method desirizie the JSon data and insert in the  sqlite data base
     *
     * @param jsonString A String which is Json format
     * @param sqlH       SqLite Handler  Object reference
     */

    public static void RegistrationNHHParser(String jsonString, SQLiteHandler sqlH) {
        String AdmCountryCode;
        String DistrictName;
        String UpazillaName;
        String UnitName;
        String VillageName;
        String RegNAddLookupCode;
        String RegistrationID;
        String RegNDate;
        String PersonName;
        String sex;
        String HHSize;
        String Latitude;
        String Longitude;
        String AGLand;
        String VStatus;
        String MStatus;
        String EntryBy;
        String EntryDate;
        String VSLAGroup;
        String GPSLongSwap;

        String LTp2Hectres;
        String LT3mFoodStock;
        String NoMajorCommonLiveStock;
        String ReceiveNoFormalWages;
        String NoIGA;
        String RelyPiecework;


        String HHHeadCat;
        String LT2yrsM;
        String LT2yrsF;
        String M2to5yrs;
        String F2to5yrs;
        String M6to12yrs;
        String F6to12yrs;
        String M13to17yrs;
        String F13to17yrs;
        String Orphn_LT18yrsM;
        String Orphn_LT18yrsF;
        String Adlt_18to59M;
        String Adlt_18to59F;
        String Eld_60pM;
        String Eld_60pF;
        String plw;
        String ChronicallyIll;
        String LivingDeceasedContractEbola;
        String ExtraChildBecauseEbola;
        String ExtraElderlyPersonBecauseEbola;
        String ExtraChronicallyIllDisabledPersonBecauseEbola;
        String BRFCntCattle;
        String BRFValCattle;
        String AFTCntCattle;
        String AFTValCattle;
        String BRFCntSheepGoats;
        String BRFValSheepGoats;
        String AFTCntSheepGoats;
        String AFTValSheepGoats;
        String BRFCntPoultry;
        String BRFValPoultry;
        String AFTCntPoultry;
        String AFTValPoultry;
        String BRFCntOther;
        String BRFValOther;
        String AFTCntOther;
        String AFTValOther;
        String BRFAcreCultivable;
        String BRFValCultivable;
        String AFTAcreCultivable;
        String AFTValCultivable;
        String BRFAcreNonCultivable;
        String BRFValNonCultivable;
        String AFTAcreNonCultivable;
        String AFTValNonCultivable;
        String BRFAcreOrchards;
        String BRFValOrchards;
        String AFTAcreOrchards;
        String AFTValOrchards;
        String BRFValCrop;
        String AFTValCrop;
        String BRFValLivestock;
        String AFTValLivestock;
        String BRFValSmallBusiness;
        String AFTValSmallBusiness;
        String BRFValEmployment;
        String AFTValEmployment;
        String BRFValRemittances;
        String AFTValRemittances;
        String BRFCntWageEnr;
        String AFTCntWageEnr;
        String WRank;


        try {


            int size;

            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing registration data into local database
            if (!jObj.isNull(REGISTRATION_JSON_A)) {

                JSONArray registration = jObj.getJSONArray(REGISTRATION_JSON_A);


                // Adding data into Registration Table
                size = registration.length();
                for (int i = 0; i < size; i++) {


                    JSONObject reg = registration.getJSONObject(i);

                    AdmCountryCode = reg.getString(ADM_COUNTRY_CODE);
                    DistrictName = reg.getString(DISTRICT_NAME);
                    UpazillaName = reg.getString(UPAZILLA_NAME);
                    UnitName = reg.getString(UNIT_NAME);
                    VillageName = reg.getString(VILLAGE_NAME);
                    RegistrationID = reg.getString(REGISTRATION_ID);
                    RegNDate = reg.getString(REG_N_DATE);
                    PersonName = reg.getString(PERSON_NAME);
                    sex = reg.getString(SEX);
                    HHSize = reg.getString(HH_SIZE);
                    Latitude = reg.getString(LATITUDE);
                    Longitude = reg.getString(LONGITUDE);
                    AGLand = reg.getString(AG_LAND);
                    VStatus = reg.getString(V_STATUS);
                    MStatus = reg.getString(M_STATUS);
                    EntryBy = reg.getString(ENTRY_BY);
                    EntryDate = reg.getString(ENTRY_DATE);
                    VSLAGroup = reg.getString(VSLA_GROUP);
                    GPSLongSwap = reg.getString(GPS_LONG_SWAP);
                    RegNAddLookupCode = reg.getString("RegNAddLookupCode");
                    LTp2Hectres = reg.getString("LTp2Hectres");
                    LT3mFoodStock = reg.getString("LT3mFoodStock");
                    NoMajorCommonLiveStock = reg.getString("NoMajorCommonLiveStock");
                    ReceiveNoFormalWages = reg.getString("ReceiveNoFormalWages");
                    NoIGA = reg.getString("NoIGA");
                    RelyPiecework = reg.getString("RelyPiecework");

                    HHHeadCat = reg.getString(HH_HEAD_CAT);
                    LT2yrsM = reg.getString(LT_2_YRS_M);
                    LT2yrsF = reg.getString(LT_2_YRS_F);
                    M2to5yrs = reg.getString(M_2_TO_5_YRS);
                    F2to5yrs = reg.getString(F_2_TO_5_YRS);
                    M6to12yrs = reg.getString(M_6_TO_12_YRS);
                    F6to12yrs = reg.getString(F_6_TO_12_YRS);
                    M13to17yrs = reg.getString(M_13_TO_17_YRS);
                    F13to17yrs = reg.getString(F_13_TO_17_YRS);
                    Orphn_LT18yrsM = reg.getString(ORPHN_LT_18_YRS_M);
                    Orphn_LT18yrsF = reg.getString(ORPHN_LT_18_YRS_F);
                    Adlt_18to59M = reg.getString(ADLT_18_TO_59_M);
                    Adlt_18to59F = reg.getString(ADLT_18_TO_59_F);
                    Eld_60pM = reg.getString(ELD_60_P_M);
                    Eld_60pF = reg.getString(ELD_60_P_F);
                    plw = reg.getString(PLW);
                    ChronicallyIll = reg.getString(CHRONICALLY_ILL);
                    LivingDeceasedContractEbola = reg.getString(LIVING_DECEASED_CONTRACT_EBOLA);
                    ExtraChildBecauseEbola = reg.getString(EXTRA_CHILD_BECAUSE_EBOLA);
                    ExtraElderlyPersonBecauseEbola = reg.getString(EXTRA_ELDERLY_PERSON_BECAUSE_EBOLA);
                    ExtraChronicallyIllDisabledPersonBecauseEbola = reg.getString(EXTRA_CHRONICALLY_ILL_DISABLED_PERSON_BECAUSE_EBOLA);
                    BRFCntCattle = reg.getString(BRF_CNT_CATTLE);
                    BRFValCattle = reg.getString(BRF_VAL_CATTLE);
                    AFTCntCattle = reg.getString(AFT_CNT_CATTLE);
                    AFTValCattle = reg.getString(AFT_VAL_CATTLE);
                    BRFCntSheepGoats = reg.getString(BRF_CNT_SHEEP_GOATS);
                    BRFValSheepGoats = reg.getString(BRF_VAL_SHEEP_GOATS);
                    AFTCntSheepGoats = reg.getString("AFTCntSheepGoats");
                    AFTValSheepGoats = reg.getString("AFTValSheepGoats");
                    BRFCntPoultry = reg.getString("BRFCntPoultry");
                    BRFValPoultry = reg.getString("BRFValPoultry");
                    AFTCntPoultry = reg.getString("AFTCntPoultry");
                    AFTValPoultry = reg.getString("AFTValPoultry");
                    BRFCntOther = reg.getString(BRF_CNT_OTHER);
                    BRFValOther = reg.getString(BRF_VAL_OTHER);
                    AFTCntOther = reg.getString(AFT_CNT_OTHER);
                    AFTValOther = reg.getString(AFT_VAL_OTHER);
                    BRFAcreCultivable = reg.getString(BRF_ACRE_CULTIVABLE);
                    BRFValCultivable = reg.getString(BRF_VAL_CULTIVABLE);
                    AFTAcreCultivable = reg.getString("AFTAcreCultivable");
                    AFTValCultivable = reg.getString("AFTValCultivable");
                    BRFAcreNonCultivable = reg.getString("BRFAcreNonCultivable");
                    BRFValNonCultivable = reg.getString("BRFValNonCultivable");
                    AFTAcreNonCultivable = reg.getString("AFTAcreNonCultivable");
                    AFTValNonCultivable = reg.getString("AFTValNonCultivable");
                    BRFAcreOrchards = reg.getString(BRF_ACRE_ORCHARDS);
                    BRFValOrchards = reg.getString(BRF_VAL_ORCHARDS);
                    AFTAcreOrchards = reg.getString(AFT_ACRE_ORCHARDS);
                    AFTValOrchards = reg.getString(AFT_VAL_ORCHARDS);
                    BRFValCrop = reg.getString("BRFValCrop");
                    AFTValCrop = reg.getString("AFTValCrop");
                    BRFValLivestock = reg.getString("BRFValLivestock");
                    AFTValLivestock = reg.getString("AFTValLivestock");
                    BRFValSmallBusiness = reg.getString("BRFValSmallBusiness");
                    AFTValSmallBusiness = reg.getString("AFTValSmallBusiness");
                    BRFValEmployment = reg.getString(BRF_VAL_EMPLOYMENT);
                    AFTValEmployment = reg.getString(AFT_VAL_EMPLOYMENT);
                    BRFValRemittances = reg.getString(BRF_VAL_REMITTANCES);
                    AFTValRemittances = reg.getString("AFTValRemittances");
                    BRFCntWageEnr = reg.getString(BRF_CNT_WAGE_ENR);
                    AFTCntWageEnr = reg.getString(AFT_CNT_WAGE_ENR);
                    WRank = reg.getString("WRank");


                    String Sync = "1";


                    sqlH.addRegistrationFromOnline(AdmCountryCode, DistrictName, UpazillaName, UnitName, VillageName, RegistrationID, RegNDate, PersonName, SEX, HHSize, Latitude, Longitude, AGLand, VStatus, MStatus, EntryBy, EntryDate, VSLAGroup, GPSLongSwap, RegNAddLookupCode, HHHeadCat, LT2yrsM, LT2yrsF, M2to5yrs, F2to5yrs
                            , M6to12yrs, F6to12yrs, M13to17yrs, F13to17yrs, Orphn_LT18yrsM, Orphn_LT18yrsF, Adlt_18to59M, Adlt_18to59F, Eld_60pM, Eld_60pF, PLW, ChronicallyIll, LivingDeceasedContractEbola, ExtraChildBecauseEbola, ExtraElderlyPersonBecauseEbola, ExtraChronicallyIllDisabledPersonBecauseEbola
                            , BRFCntCattle, BRFValCattle, AFTCntCattle, AFTValCattle, BRFCntSheepGoats, BRFValSheepGoats, AFTCntSheepGoats, AFTValSheepGoats, BRFCntPoultry, BRFValPoultry, AFTCntPoultry, AFTValPoultry, BRFCntOther, BRFValOther, AFTCntOther, AFTValOther, BRFAcreCultivable
                            , BRFValCultivable, AFTAcreCultivable, AFTValCultivable, BRFAcreNonCultivable, BRFValNonCultivable, AFTAcreNonCultivable, AFTValNonCultivable, BRFAcreOrchards, BRFValOrchards, AFTAcreOrchards, AFTValOrchards, BRFValCrop, AFTValCrop, BRFValLivestock, AFTValLivestock, BRFValSmallBusiness
                            , AFTValSmallBusiness, BRFValEmployment, AFTValEmployment, BRFValRemittances, AFTValRemittances, BRFCntWageEnr, AFTCntWageEnr, Sync, WRank
                            , LTp2Hectres, LT3mFoodStock, NoMajorCommonLiveStock, ReceiveNoFormalWages, NoIGA, RelyPiecework);

                       /* Log.d(TAG, "in Reg data Reg data " + AdmCountryCode + " , " + DistrictName + " , " + UpazillaName + " , " + UnitName + " , " + VillageName + " , " + RegistrationID + " , " + RegNDate + " , " + PersonName + " , " + SEX + " , " + HHSize + " , " + Latitude + " , " + Longitude + " , " + AGLand + " , " + VStatus + " , " + MStatus + " , " + EntryBy + " , " + EntryDate + " , " + VSLAGroup + " , " + GPSLongSwap + " , " + HHHeadCat + " , " + LT2yrsM + " , " + LT2yrsF + " , " + M2to5yrs + " , " + F2to5yrs
                                + " , " + M6to12yrs + " , " + F6to12yrs + " , " + M13to17yrs + " , " + F13to17yrs + " , " + Orphn_LT18yrsM + " , " + Orphn_LT18yrsF + " , " + Adlt_18to59M + " , " + Adlt_18to59F + " , " + Eld_60pM + " , " + Eld_60pF + " , " + PLW + " , " + ChronicallyIll + " , " + LivingDeceasedContractEbola + " , " + ExtraChildBecauseEbola + " , " + ExtraElderlyPersonBecauseEbola + " , " + ExtraChronicallyIllDisabledPersonBecauseEbola
                                + " , " + BRFCntCattle + " , " + BRFValCattle + " , " + AFTCntCattle + " , " + AFTValCattle + " , " + BRFCntSheepGoats + " , " + BRFValSheepGoats + " , " + AFTCntSheepGoats + " , " + AFTValSheepGoats + " , " + BRFCntPoultry + " , " + BRFValPoultry + " , " + AFTCntPoultry + " , " + AFTValPoultry + " , " + BRFCntOther + " , " + BRFValOther + " , " + AFTCntOther + " , " + AFTValOther + " , " + BRFAcreCultivable
                                + " , " + BRFValCultivable + " , " + AFTAcreCultivable + " , " + AFTValCultivable + " , " + BRFAcreNonCultivable + " , " + BRFValNonCultivable + " , " + AFTAcreNonCultivable + " , " + AFTValNonCultivable + " , " + BRFAcreOrchards + " , " + BRFValOrchards + " , " + AFTAcreOrchards + " , " + AFTValOrchards + " , " + BRFValCrop + " , " + AFTValCrop + " , " + BRFValLivestock + " , " + AFTValLivestock + " , " + BRFValSmallBusiness
                                + " , " + AFTValSmallBusiness + " , " + BRFValEmployment + " , " + AFTValEmployment + " , " + BRFValRemittances + " , " + AFTValRemittances + " , " + BRFCntWageEnr + " , " + AFTCntWageEnr + " , " + Sync);
                        */
                    //  Log.d(TAG+" , "+ "Data added into Registration Table");
                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }
    }

    /**
     * @param services_table The Json Array
     * @param sqlH           Database helper
     */

    public static void SrvTableParser(JSONArray services_table, SQLiteHandler sqlH) {

        String AdmCountryCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String LayR1ListCode;
        String LayR2ListCode;
        String LayR3ListCode;
        String LayR4ListCode;
        String hhID;
        String MemID;
        String ProgCode;
        String SrvCode;
        String OpCode;
        String OpMonthCode;
        String SrvSL;
        String SrvCenterCode;
        String SrvDT;
        String SrvStatus;
        String DistStatus;
        String DistDT;
        String fdpCode;
        String GrpCode;
        String WD;
        String DistFlag;
//        try {


        int size;


        size = services_table.length();
        for (int i = 0; i < size; i++) {
            try {
                JSONObject service = services_table.getJSONObject(i);
                AdmCountryCode = service.getString(ADM_COUNTRY_CODE);
                AdmDonorCode = service.getString(ADM_DONOR_CODE);
                AdmAwardCode = service.getString(ADM_AWARD_CODE);
                LayR1ListCode = service.getString(LAY_R_1_LIST_CODE);
                LayR2ListCode = service.getString(LAY_R_2_LIST_CODE);
                LayR3ListCode = service.getString(LAY_R_3_LIST_CODE);
                LayR4ListCode = service.getString(LAY_R_4_LIST_CODE);
                hhID = service.getString(HHID);
                MemID = service.getString(MEM_ID);
                ProgCode = service.getString(PROG_CODE);
                SrvCode = service.getString(SRV_CODE);
                OpCode = service.getString(OP_CODE);
                OpMonthCode = service.getString(OP_MONTH_CODE);
                SrvSL = service.getString(SRV_SL);
                SrvCenterCode = service.getString(SRV_CENTER_CODE);
                SrvDT = service.getString(SRV_DT);
                SrvStatus = service.getString(SRV_STATUS);
                DistStatus = service.getString(DIST_STATUS);
                DistDT = service.getString(DIST_DT);
                fdpCode = service.getString(FDP_CODE);
                GrpCode = service.getString("GrpCode");
                WD = service.getString("WD");
                DistFlag = service.getString("DistFlag");

                sqlH.addServiceFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, hhID, MemID, ProgCode, SrvCode, OpCode, OpMonthCode, SrvSL, SrvCenterCode, SrvDT, SrvStatus, DistStatus, DistDT, fdpCode, WD, DistFlag, GrpCode, "1");


                Log.d(TAG, "In Service Table- AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhID + " MemID : " + MemID + " ProgCode : " + ProgCode + " SrvCode : " + SrvCode + " OpCode : " + OpCode + " OpMonthCode : " + OpMonthCode +
                        " SrvSL : " + SrvSL + "SrvDT: " + SrvDT + " SrvStatus : " + SrvStatus + " WD :" + WD);
            } catch (Exception e) {
                Log.d(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        } // end of the for


    }// end of the method

    public static void SrvExtTableParser(JSONArray services_exe_table, SQLiteHandler sqlH) {
        try {


            int size;


            String AdmCountryCode;
            String AdmDonorCode;
            String AdmAwardCode;
            String LayR1ListCode;
            String LayR2ListCode;
            String LayR3ListCode;
            String LayR4ListCode;
            String hhID;
            String MemID;
            String ProgCode;
            String SrvCode;
            String OpCode;
            String OpMonthCode;
            String VOItmSpec;
            String VOItmUnit;
            String VORefNumber;
            String VOItmCost;

            /**
             * The total string Convert into JSON object
             * */

//            JSONObject jObj = new JSONObject(jsonString);

     /*       if (!jObj.isNull("service_exe_table")) {// this is not servie
                JSONArray services_exe_table = jObj.getJSONArray("service_exe_table");*/
            size = services_exe_table.length();
            for (int i = 0; i < size; i++) {
                JSONObject services_exe = services_exe_table.getJSONObject(i);

                AdmCountryCode = services_exe.getString(ADM_COUNTRY_CODE);
                AdmDonorCode = services_exe.getString(ADM_DONOR_CODE);
                AdmAwardCode = services_exe.getString(ADM_AWARD_CODE);
                LayR1ListCode = services_exe.getString(LAY_R_1_LIST_CODE);
                LayR2ListCode = services_exe.getString(LAY_R_2_LIST_CODE);
                LayR3ListCode = services_exe.getString(LAY_R_3_LIST_CODE);
                LayR4ListCode = services_exe.getString(LAY_R_4_LIST_CODE);
                hhID = services_exe.getString(HHID);
                MemID = services_exe.getString(MEM_ID);
                ProgCode = services_exe.getString(PROG_CODE);
                SrvCode = services_exe.getString(SRV_CODE);
                OpCode = services_exe.getString(OP_CODE);
                OpMonthCode = services_exe.getString(OP_MONTH_CODE);
                VOItmSpec = services_exe.getString(VO_ITM_SPEC);
                VOItmUnit = services_exe.getString(VO_ITM_UNIT);
                VORefNumber = services_exe.getString(VO_REF_NUMBER);
                VOItmCost = services_exe.getString(VO_ITM_COST);
// todo : add dist flag

                sqlH.addServiceExtendedFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode,
                        LayR4ListCode, hhID, MemID, ProgCode, SrvCode, OpCode, OpMonthCode,
                        VOItmSpec, VOItmUnit, VORefNumber, VOItmCost, "1");


             /*   Log.d(TAG, "In Service Extendtion - AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : "
                        + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " LayR1ListCode : "
                        + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : "
                        + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhID
                        + " MemID : " + MemID + " ProgCode : " + ProgCode + " SrvCode : " + SrvCode
                        + " OpCode : " + OpCode + " OpMonthCode : " + OpMonthCode +
                        " VOItmSpec : " + VOItmSpec + "VOItmUnit: " + VOItmUnit + " VORefNumber : " + VORefNumber + " VOItmCost : " + VOItmCost);*/
            }// end of for scope

            // }// end of if scope
        } catch (Exception e) {
            Log.d(TAG, "Exception : " + e);
            e.printStackTrace();
        }

    }// end of the method

    public static void SrvSpecificTableParser(String jsonString, SQLiteHandler sqlH) {
        try {


            String AdmCountryCode;
            String AdmDonorCode;
            String AdmAwardCode;
            String LayR1ListCode;
            String LayR2ListCode;
            String LayR3ListCode;
            String LayR4ListCode;
            String hhID;
            String MemID;
            String ProgCode;
            String SrvCode;
            String OpCode;
            String OpMonthCode;
            String SrvCenterCode;
            String FDPCode;
            String SrvStatus;
            String BabyStatus;
            String GMPAttendace;

            String WeightStatus;
            String NutAttendance;
            String VitA_Under5;
            String Exclusive_CurrentlyBF;
            String DateCompFeeding;
            String CMAMRef;
            String CMAMAdd;
            String ANCVisit;
            String PNCVisit_2D;
            String PNCVisit_1W;
            String PNCVisit_6W;
            String DeliveryStaff_1;

            String DeliveryStaff_2;
            String DeliveryStaff_3;
            String HomeSupport24H_1d;
            String HomeSupport24H_2d;
            String HomeSupport24H_3d;
            String HomeSupport24H_8d;

            String HomeSupport24H_14d;
            String HomeSupport24H_21d;
            String HomeSupport24H_30d;
            String HomeSupport24H_60d;
            String HomeSupport24H_90d;

            String HomeSupport48H_1d;
            String HomeSupport48H_3d;
            String HomeSupport48H_8d;
            String HomeSupport48H_30d;
            String HomeSupport48H_60d;

            String HomeSupport48H_90d;
            String Maternal_Bleeding;
            String Maternal_Seizure;
            String Maternal_Infection;
            String Maternal_ProlongedLabor;
            String Maternal_ObstructedLabor;
            String Maternal_PPRM;
            String NBorn_Asphyxia;
            String NBorn_Sepsis;
            String NBorn_Hypothermia;
            String NBorn_Hyperthermia;
            String NBorn_NoSuckling;
            String NBorn_Jaundice;
            String Child_Diarrhea;
            String Child_Pneumonia;
            String Child_Fever;
            String Child_CerebralPalsy;
            String Immu_Polio;
            String Immu_BCG;
            String Immu_Measles;
            String Immu_DPT_HIB;
            String Immu_Lotta;
            String Immu_Other;
            String FPCounsel_MaleCondom;
            String FPCounsel_FemaleCondom;
            String FPCounsel_Pill;
            String FPCounsel_Depo;
            String FPCounsel_LongParmanent;
            String FPCounsel_NoMethod;
            String CropCode;
            String LoanSource;
            String LoanAMT;
            String AnimalCode;
            String LeadCode;


            int size;

            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);

            if (!jObj.isNull("service_specific_table")) {// this is not servie
                JSONArray service_specific_table = jObj.getJSONArray("service_specific_table");
                size = service_specific_table.length();
                for (int i = 0; i < size; i++) {
                    JSONObject srvSpecific = service_specific_table.getJSONObject(i);

                    AdmCountryCode = srvSpecific.getString(ADM_COUNTRY_CODE);
                    AdmDonorCode = srvSpecific.getString(ADM_DONOR_CODE);
                    AdmAwardCode = srvSpecific.getString(ADM_AWARD_CODE);
                    LayR1ListCode = srvSpecific.getString(LAY_R_1_LIST_CODE);
                    LayR2ListCode = srvSpecific.getString(LAY_R_2_LIST_CODE);
                    LayR3ListCode = srvSpecific.getString(LAY_R_3_LIST_CODE);
                    LayR4ListCode = srvSpecific.getString(LAY_R_4_LIST_CODE);
                    hhID = srvSpecific.getString(HHID);
                    MemID = srvSpecific.getString(MEM_ID);
                    ProgCode = srvSpecific.getString(PROG_CODE);
                    SrvCode = srvSpecific.getString(SRV_CODE);
                    OpCode = srvSpecific.getString(OP_CODE);
                    OpMonthCode = srvSpecific.getString(OP_MONTH_CODE);


                    SrvCenterCode = srvSpecific.getString("SrvCenterCode");
                    FDPCode = srvSpecific.getString("FDPCode");
                    SrvStatus = srvSpecific.getString("SrvStatus");
                    BabyStatus = srvSpecific.getString("BabyStatus");
                    GMPAttendace = srvSpecific.getString("GMPAttendace");

                    WeightStatus = srvSpecific.getString("WeightStatus");
                    NutAttendance = srvSpecific.getString("NutAttendance");
                    VitA_Under5 = srvSpecific.getString("VitA_Under5");
                    Exclusive_CurrentlyBF = srvSpecific.getString("Exclusive_CurrentlyBF");
                    DateCompFeeding = srvSpecific.getString("DateCompFeeding");

                    CMAMRef = srvSpecific.getString("CMAMRef");
                    CMAMAdd = srvSpecific.getString("CMAMAdd");


                    ANCVisit = srvSpecific.getString("ANCVisit");
                    PNCVisit_2D = srvSpecific.getString("PNCVisit_2D");
                    PNCVisit_1W = srvSpecific.getString("PNCVisit_1W");
                    PNCVisit_6W = srvSpecific.getString("PNCVisit_6W");
                    DeliveryStaff_1 = srvSpecific.getString("DeliveryStaff_1");

                    DeliveryStaff_2 = srvSpecific.getString("DeliveryStaff_2");
                    DeliveryStaff_3 = srvSpecific.getString("DeliveryStaff_3");
                    HomeSupport24H_1d = srvSpecific.getString("HomeSupport24H_1d");
                    HomeSupport24H_2d = srvSpecific.getString("HomeSupport24H_2d");
                    HomeSupport24H_3d = srvSpecific.getString("HomeSupport24H_3d");
                    HomeSupport24H_8d = srvSpecific.getString("HomeSupport24H_8d");

                    HomeSupport24H_14d = srvSpecific.getString("HomeSupport24H_14d");
                    HomeSupport24H_21d = srvSpecific.getString("HomeSupport24H_21d");
                    HomeSupport24H_30d = srvSpecific.getString("HomeSupport24H_30d");
                    HomeSupport24H_60d = srvSpecific.getString("HomeSupport24H_60d");
                    HomeSupport24H_90d = srvSpecific.getString("HomeSupport24H_90d");

                    HomeSupport48H_1d = srvSpecific.getString("HomeSupport48H_1d");
                    HomeSupport48H_3d = srvSpecific.getString("HomeSupport48H_3d");
                    HomeSupport48H_8d = srvSpecific.getString("HomeSupport48H_8d");
                    HomeSupport48H_30d = srvSpecific.getString("HomeSupport48H_30d");
                    HomeSupport48H_60d = srvSpecific.getString("HomeSupport48H_60d");


                    HomeSupport48H_90d = srvSpecific.getString("HomeSupport48H_90d");
                    Maternal_Bleeding = srvSpecific.getString("Maternal_Bleeding");
                    Maternal_Seizure = srvSpecific.getString("Maternal_Seizure");
                    Maternal_Infection = srvSpecific.getString("Maternal_Infection");
                    Maternal_ProlongedLabor = srvSpecific.getString("Maternal_ProlongedLabor");
                    Maternal_ObstructedLabor = srvSpecific.getString("Maternal_ObstructedLabor");
                    Maternal_PPRM = srvSpecific.getString("Maternal_PPRM");
                    NBorn_Asphyxia = srvSpecific.getString("NBorn_Asphyxia");
                    NBorn_Sepsis = srvSpecific.getString("NBorn_Sepsis");
                    NBorn_Hypothermia = srvSpecific.getString("NBorn_Hypothermia");
                    NBorn_Hyperthermia = srvSpecific.getString("NBorn_Hyperthermia");
                    NBorn_NoSuckling = srvSpecific.getString("NBorn_NoSuckling");
                    NBorn_Jaundice = srvSpecific.getString("NBorn_Jaundice");
                    Child_Diarrhea = srvSpecific.getString("Child_Diarrhea");

                    Child_Pneumonia = srvSpecific.getString("Child_Pneumonia");
                    Child_Fever = srvSpecific.getString("Child_Fever");
                    Child_CerebralPalsy = srvSpecific.getString("Child_CerebralPalsy");
                    Immu_Polio = srvSpecific.getString("Immu_Polio");
                    Immu_BCG = srvSpecific.getString("Immu_BCG");

                    Immu_Measles = srvSpecific.getString("Immu_Measles");
                    Immu_DPT_HIB = srvSpecific.getString("Immu_DPT_HIB");
                    Immu_Lotta = srvSpecific.getString("Immu_Lotta");

                    Immu_Other = srvSpecific.getString("Immu_Other");
                    FPCounsel_MaleCondom = srvSpecific.getString("FPCounsel_MaleCondom");
                    FPCounsel_FemaleCondom = srvSpecific.getString("FPCounsel_FemaleCondom");
                    FPCounsel_Pill = srvSpecific.getString("FPCounsel_Pill");
                    FPCounsel_Depo = srvSpecific.getString("FPCounsel_Depo");
                    FPCounsel_LongParmanent = srvSpecific.getString("FPCounsel_LongParmanent");
                    FPCounsel_NoMethod = srvSpecific.getString("FPCounsel_NoMethod");
                    CropCode = srvSpecific.getString("CropCode");
                    LoanSource = srvSpecific.getString("LoanSource");
                    LoanAMT = srvSpecific.getString("LoanAMT");
                    AnimalCode = srvSpecific.getString("AnimalCode");
                    LeadCode = srvSpecific.getString("LeadCode");


                    sqlH.addServiceSpecificTableFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode, LayR1ListCode, LayR2ListCode, LayR3ListCode,
                            LayR4ListCode, hhID, MemID, ProgCode, SrvCode, OpCode, OpMonthCode,
                            SrvCenterCode, FDPCode, SrvStatus, BabyStatus, GMPAttendace, WeightStatus,
                            NutAttendance, VitA_Under5, Exclusive_CurrentlyBF,
                            DateCompFeeding, CMAMRef,
                            CMAMAdd, ANCVisit,
                            PNCVisit_2D, PNCVisit_1W,
                            PNCVisit_6W, DeliveryStaff_1,
                            DeliveryStaff_2, DeliveryStaff_3,
                            HomeSupport24H_1d, HomeSupport24H_2d,
                            HomeSupport24H_3d, HomeSupport24H_8d,
                            HomeSupport24H_14d, HomeSupport24H_21d,
                            HomeSupport24H_30d, HomeSupport24H_60d,
                            HomeSupport24H_90d, HomeSupport48H_1d,
                            HomeSupport48H_3d, HomeSupport48H_8d,
                            HomeSupport48H_30d, HomeSupport48H_60d,
                            HomeSupport48H_90d, Maternal_Bleeding,
                            Maternal_Seizure, Maternal_Infection,
                            Maternal_ProlongedLabor, Maternal_ObstructedLabor,
                            Maternal_PPRM, NBorn_Asphyxia,
                            NBorn_Sepsis, NBorn_Hypothermia,
                            NBorn_Hyperthermia, NBorn_NoSuckling,
                            NBorn_Jaundice, Child_Diarrhea,
                            Child_Pneumonia, Child_Fever,
                            Child_CerebralPalsy, Immu_Polio,
                            Immu_BCG,
                            Immu_Measles, Immu_DPT_HIB,
                            Immu_Lotta, Immu_Other,
                            FPCounsel_MaleCondom, FPCounsel_FemaleCondom,
                            FPCounsel_Pill, FPCounsel_Depo,
                            FPCounsel_LongParmanent, FPCounsel_NoMethod,
                            CropCode, LoanSource, LoanAMT,
                            AnimalCode, LeadCode);

/*                      For test
                    Log.d(TAG, "Service Specific - AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : "
                            + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " LayR1ListCode : "
                            + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : "
                            + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhID
                            + " MemID : " + MemID + " ProgCode : " + ProgCode + " SrvCode : " + SrvCode
                            + " OpCode : " + OpCode + " OpMonthCode : " + OpMonthCode);*/
                }// end of for scope

            }// end of if scope
        } catch (Exception e) {
            Log.d(TAG, "Exception : " + e);
            e.printStackTrace();
        }

    }// end of the method


    public static void RegNMemProGrpParser(String jsonString, SQLiteHandler sqlH) {


        try {


            int size;


            String AdmCountryCode;
            String AdmDonorCode;
            String AdmAwardCode;
            String LayR1ListCode;
            String LayR2ListCode;
            String LayR3ListCode;
            String LayR4ListCode;
            String hhID;
            String MemID;
            String ProgCode;
            String SrvCode;
            String GrpCode;
            String Active;

            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing members data into local database
            if (!jObj.isNull("reg_n_mem_prog_grp")) {

                JSONArray reg_n_mem_prog_grps = jObj.getJSONArray("reg_n_mem_prog_grp");


                // Adding data into Registration Table
                size = reg_n_mem_prog_grps.length();

                for (int i = 0; i < size; i++) {

                    JSONObject reg_n_mem_prog_grp = reg_n_mem_prog_grps.getJSONObject(i);


                    AdmCountryCode = reg_n_mem_prog_grp.getString("AdmCountryCode");
                    AdmDonorCode = reg_n_mem_prog_grp.getString("AdmDonorCode");
                    AdmAwardCode = reg_n_mem_prog_grp.getString("AdmAwardCode");
                    LayR1ListCode = reg_n_mem_prog_grp.getString("LayR1ListCode");
                    LayR2ListCode = reg_n_mem_prog_grp.getString("LayR2ListCode");
                    LayR3ListCode = reg_n_mem_prog_grp.getString("LayR3ListCode");
                    LayR4ListCode = reg_n_mem_prog_grp.getString("LayR4ListCode");
                    hhID = reg_n_mem_prog_grp.getString(HHID);
                    MemID = reg_n_mem_prog_grp.getString("MemID");
                    ProgCode = reg_n_mem_prog_grp.getString(PROG_CODE);
                    SrvCode = reg_n_mem_prog_grp.getString(SRV_CODE);
                    GrpCode = reg_n_mem_prog_grp.getString("GrpCode");
                    Active = reg_n_mem_prog_grp.getString("Active");


                    sqlH.addRegNmemProgGroupFromOnline(AdmCountryCode, AdmDonorCode, AdmAwardCode
                            , LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode
                            , hhID, MemID, ProgCode, SrvCode, GrpCode, Active

                    );


                    Log.d(TAG, " Member Prog group  : id:" + i + " values : AdmCountryCode:" + AdmCountryCode
                            + "  AdmDonorCode:" + AdmDonorCode + "  AdmAwardCode:" + AdmAwardCode
                            + "  LayR1ListCode:" + LayR1ListCode + "  LayR2ListCode:" + LayR2ListCode
                            + "  LayR3ListCode:" + LayR3ListCode + "  LayR4ListCode:" + LayR4ListCode
                            + "  hhID:" + hhID + "  MemID:" + MemID
                            + "  ProgCode:" + ProgCode + "  SrvCode:" + SrvCode
                            + "  GrpCode:" + GrpCode + "  Active:" + Active

                    );


                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }

    }


    public static void RegNMemberParser(String jsonString, SQLiteHandler sqlH) {
        try {


            int size;

            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing members data into local database
            if (!jObj.isNull(MEMBERS_JSON_A)) {

                JSONArray members = jObj.getJSONArray(MEMBERS_JSON_A);


                String AdmCountryCode;
                String DistrictName;
                String UpazillaName;
                String UnitName;
                String VillageName;
                String hhID;
                String HHMemID;
                String MemName;
                String MemSex;
                String HHRelation;
                String EntryBy;
                String EntryDate;
                String lmp_date;
                String child_dob;
                String elderly;
                String disabled;
                String MemAge;
                String RegNDate;
                String BirthYear;
                String MaritalStatus;
                String ContactNo;
                String MemOtherID;
                String MemName_First;
                String MemName_Middle;
                String MemName_Last;
                String Photo;
                String Type_ID;

                String TypeID_NO;
                String V_BSCMemName1_First;
                String V_BSCMemName1_Middle;
                String V_BSCMemName1_Last;
                String V_BSCMem1_TitlePosition;
                String V_BSCMemName2_First;
                String V_BSCMemName2_Middle;
                String V_BSCMemName2_Last;
                String V_BSCMem2_TitlePosition;
                String Proxy_Designation;
                String Proxy_Name_First;
                String Proxy_Name_Middle;
                String Proxy_Name_Last;
                String Proxy_BirthYear;
                String Proxy_Photo;
                String Proxy_Type_ID;
                String Proxy_ID_NO;
                String P_BSCMemName1_First;
                String P_BSCMemName1_Middle;
                String P_BSCMemName1_Last;
                String P_BSCMem1_TitlePosition;
                String P_BSCMemName2_First;
                String P_BSCMemName2_Middle;
                String P_BSCMemName2_Last;
                String P_BSCMem2_TitlePosition;
                String GrpCode;


                // Adding data into Registration Table
                size = members.length();

                for (int i = 0; i < size; i++) {

                    JSONObject member = members.getJSONObject(i);

                    AdmCountryCode = member.getString(ADM_COUNTRY_CODE);
                    DistrictName = member.getString(DISTRICT_NAME);
                    UpazillaName = member.getString(UPAZILLA_NAME);
                    UnitName = member.getString(UNIT_NAME);
                    VillageName = member.getString(VILLAGE_NAME);
                    hhID = member.getString(HHID);
                    HHMemID = member.getString(HH_MEM_ID);
                    MemName = member.getString(MEM_NAME);
                    MemSex = member.getString(MEM_SEX);
                    HHRelation = member.getString(HH_RELATION);
                    EntryBy = member.getString(ENTRY_BY);
                    EntryDate = member.getString(ENTRY_DATE);
                    lmp_date = member.getString(LMP_DATE);
                    child_dob = member.getString(CHILD_DOB);
                    elderly = member.getString(ELDERLY);
                    disabled = member.getString(DISABLED);
                    MemAge = member.getString(MEM_AGE);
                    RegNDate = member.getString(REG_N_DATE);
                    BirthYear = member.getString(BIRTH_YEAR);
                    MaritalStatus = member.getString(MARITAL_STATUS);
                    ContactNo = member.getString(CONTACT_NO);
                    MemOtherID = member.getString(MEM_OTHER_ID);
                    MemName_First = member.getString(MEM_NAME_FIRST);
                    MemName_Middle = member.getString(MEM_NAME_MIDDLE);
                    MemName_Last = member.getString(MEM_NAME_LAST);
                    Photo = member.getString(PHOTO);
                    Type_ID = member.getString(TYPE_ID);
                    //   String TypeID_NO                 = member.getString("TypeID_NO"); ISSUE: org.json.JSONException: No value for TypeID_NO !!! WHY FOR NULL VALUE THE TYPE ID NO GET EXCEPTION??
                    // // TODO: 7/31/2016  debug it TypeID_NO
                    TypeID_NO = "";
                    V_BSCMemName1_First = member.getString(V_BSC_MEM_NAME_1_FIRST);
                    V_BSCMemName1_Middle = member.getString(V_BSC_MEM_NAME_1_MIDDLE);
                    V_BSCMemName1_Last = member.getString(V_BSC_MEM_NAME_1_LAST);
                    V_BSCMem1_TitlePosition = member.getString(V_BSC_MEM_1_TITLE_POSITION);
                    V_BSCMemName2_First = member.getString(V_BSC_MEM_NAME_2_FIRST);
                    V_BSCMemName2_Middle = member.getString(V_BSC_MEM_NAME_2_MIDDLE);
                    V_BSCMemName2_Last = member.getString(V_BSC_MEM_NAME_2_LAST);
                    V_BSCMem2_TitlePosition = member.getString(V_BSC_MEM_2_TITLE_POSITION);
                    Proxy_Designation = member.getString(PROXY_DESIGNATION);
                    Proxy_Name_First = member.getString(PROXY_NAME_FIRST);
                    Proxy_Name_Middle = member.getString(PROXY_NAME_MIDDLE);
                    Proxy_Name_Last = member.getString(PROXY_NAME_LAST);
                    Proxy_BirthYear = member.getString(PROXY_BIRTH_YEAR);
                    Proxy_Photo = member.getString(PROXY_PHOTO);
                    Proxy_Type_ID = member.getString(PROXY_TYPE_ID);
                    Proxy_ID_NO = member.getString(PROXY_ID_NO);
                    P_BSCMemName1_First = member.getString(P_BSC_MEM_NAME_1_FIRST);
                    P_BSCMemName1_Middle = member.getString(P_BSC_MEM_NAME_1_MIDDLE);
                    P_BSCMemName1_Last = member.getString(P_BSC_MEM_NAME_1_LAST);
                    P_BSCMem1_TitlePosition = member.getString(P_BSC_MEM_1_TITLE_POSITION);
                    P_BSCMemName2_First = member.getString(P_BSC_MEM_NAME_2_FIRST);
                    P_BSCMemName2_Middle = member.getString(P_BSC_MEM_NAME_2_MIDDLE);
                    P_BSCMemName2_Last = member.getString(P_BSC_MEM_NAME_2_LAST);
                    P_BSCMem2_TitlePosition = member.getString(P_BSC_MEM_2_TITLE_POSITION);
                    GrpCode = member.getString(GRP_CODE);


                    sqlH.addMemberData(AdmCountryCode, DistrictName, UpazillaName, UnitName, VillageName, hhID, HHMemID, MemName, MemSex, HHRelation, EntryBy, EntryDate, lmp_date, child_dob, elderly, disabled, MemAge, RegNDate, BirthYear, MaritalStatus, ContactNo, MemOtherID, MemName_First, MemName_Middle, MemName_Last, Photo,
                            Type_ID, TypeID_NO, V_BSCMemName1_First, V_BSCMemName1_Middle, V_BSCMemName1_Last, V_BSCMem1_TitlePosition, V_BSCMemName2_First, V_BSCMemName2_Middle, V_BSCMemName2_Last, V_BSCMem2_TitlePosition,
                            Proxy_Designation, Proxy_Name_First, Proxy_Name_Middle, Proxy_Name_Last, Proxy_BirthYear, Proxy_Photo
                            , Proxy_Type_ID, Proxy_ID_NO, P_BSCMemName1_First, P_BSCMemName1_Middle, P_BSCMemName1_Last, P_BSCMem1_TitlePosition, P_BSCMemName2_First, P_BSCMemName2_Middle, P_BSCMemName2_Last, P_BSCMem2_TitlePosition, GrpCode);

//                    Log.d(TAG, " Member table data : id:" + i + " valiues :" + AdmCountryCode + DistrictName + UpazillaName + UnitName + VillageName + hhID + HHMemID + MemName + MemSex + HHRelation + EntryBy + EntryDate + lmp_date + child_dob + elderly + disabled + MemAge + " RegNDate : " + RegNDate + "BirthYear: " + BirthYear + MaritalStatus + ContactNo + MemOtherID + MemName_First + MemName_Middle + MemName_Last + " Photo :" + Photo +
//                            Type_ID + TypeID_NO + V_BSCMemName1_First + V_BSCMemName1_Middle + V_BSCMemName1_Last + V_BSCMem1_TitlePosition + V_BSCMemName2_First + V_BSCMemName2_Middle + V_BSCMemName2_Last + V_BSCMem2_TitlePosition +
//                            Proxy_Designation + Proxy_Name_First);//, Proxy_Name_Middle, Proxy_Name_Last, Proxy_BirthYear //,Proxy_Photo
                    // , Proxy_Type_ID, Proxy_ID_NO, P_BSCMemName1_First, P_BSCMemName1_Middle, P_BSCMemName1_Last, P_BSCMem1_TitlePosition, P_BSCMemName2_First, P_BSCMemName2_Middle, P_BSCMemName2_Last, P_BSCMem2_TitlePosition);


                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }
    }


    public static void GpsLocationContentParser(String jsonString, SQLiteHandler sqlH) {


        try {


            int size;


            String AdmCountryCode;
            String GrpCode;
            String SubGrpCode;
            String LocationCode;
            String ContentCode;
            String ImageFileString;
            String Remarks;
            String EntryBy;
            String EntryDate;


            /**
             * The total string Convert into JSON object
             * */

            JSONObject jObj = new JSONObject(jsonString);


            // Adding existing members data into local database
            if (!jObj.isNull("gps_location_content")) {

                JSONArray gps_location_contents = jObj.getJSONArray("gps_location_content");


                // Adding data into Registration Table
                size = gps_location_contents.length();

                for (int i = 0; i < size; i++) {

                    JSONObject gps_location_content = gps_location_contents.getJSONObject(i);


                    AdmCountryCode = gps_location_content.getString("AdmCountryCode");
                    GrpCode = gps_location_content.getString("GrpCode");
                    SubGrpCode = gps_location_content.getString("SubGrpCode");
                    LocationCode = gps_location_content.getString("LocationCode");
                    ContentCode = gps_location_content.getString("ContentCode");
                    ImageFileString = gps_location_content.getString("ImageFileString");
                    Remarks = gps_location_content.getString("Remarks");

                    byte[] imageByteArray;

                    if (ImageFileString.length() > 10) {
                        String tem = removeNewLineFromImage(ImageFileString);
                        String base64String = removeSlashFromImage(tem);


                        imageByteArray = Base64.decode(base64String, Base64.DEFAULT);

                    } else {
                        imageByteArray = null;
                    }

                    EntryBy = "";
                    EntryDate = "";

                    sqlH.insertIntoGPSLocationContentTable(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, ContentCode, imageByteArray, Remarks, EntryBy, EntryDate);


                    Log.d(TAG, " GPS LoCation Location" + i + " values : AdmCountryCode:" + AdmCountryCode
                            + "  GrpCode:" + GrpCode
                            + "  SubGrpCode:" + SubGrpCode + "  LocationCode:" + LocationCode
                            + "  ContentCode:" + ContentCode + "  ContentCode:" + ContentCode
                            + "  Remarks:" + Remarks
                    );


                }
            }


        } catch (Exception e) {
            Log.d(TAG, "Expetion : " + e);
            e.printStackTrace();
        }

    }


    private static String removeNewLineFromImage(String str) {


        return str.replace("\\n", "").replace("\\r", "");
    }

    private static String removeSlashFromImage(String str) {

        return str.replace("\\", "");

    }

    public static void gpsLocationParse(JSONArray gps_locations, SQLiteHandler sqlH) {
        String AdmCountryCode;
        String GrpCode;
        String SubGrpCode;
        String LocationCode;
        String LocationName;
        String Long;
        String Latd;
        int size = gps_locations.length();
        for (int i = 0; i < size; i++) {
            try {

                JSONObject gps_location = gps_locations.getJSONObject(i);
                AdmCountryCode = gps_location.getString(ADM_COUNTRY_CODE);
                GrpCode = gps_location.getString(GRP_CODE);
                SubGrpCode = gps_location.getString(SUB_GRP_CODE);
                LocationCode = gps_location.getString(LOCATION_CODE);
                LocationName = gps_location.getString(LOCATION_NAME);
                Long = gps_location.getString(LONG);
                Latd = gps_location.getString(LATD);


                sqlH.addGpsLocation(AdmCountryCode, GrpCode, SubGrpCode, LocationCode, LocationName, Latd, Long);


            } catch (Exception e) {
                Log.d(TAG, "Expetion : " + e);
                e.printStackTrace();
            }// end of catch
        }// end of the for
    } //end of the method


    public static void regNAssignProgSrvParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size;
        String AdmCountryCode;
        String LayR1ListCode;
        String LayR2ListCode;
        String LayR3ListCode;
        String LayR4ListCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String hhID;
        String MemID;
        String ProgCode;
        String SrvCode;
        String RegNDate;
        String GRDCode;
        String GRDDate;
        String SrvMin;
        String SrvMax;
        // JSONArray reg_m_assign_prog_srvs = jObj.getJSONArray(REG_M_ASSIGN_PROG_SRV_JSON_A);
        size = jsonArrayData.length();
        for (int i = 0; i < size; i++) {
            try {

                JSONObject reg_m_assign_prog_srv = jsonArrayData.getJSONObject(i);
                AdmCountryCode = reg_m_assign_prog_srv.getString(ADM_COUNTRY_CODE);
                LayR1ListCode = reg_m_assign_prog_srv.getString(LAY_R_1_LIST_CODE);
                LayR2ListCode = reg_m_assign_prog_srv.getString(LAY_R_2_LIST_CODE);
                LayR3ListCode = reg_m_assign_prog_srv.getString(LAY_R_3_LIST_CODE);
                LayR4ListCode = reg_m_assign_prog_srv.getString(LAY_R_4_LIST_CODE);
                AdmDonorCode = reg_m_assign_prog_srv.getString(ADM_DONOR_CODE);
                AdmAwardCode = reg_m_assign_prog_srv.getString(ADM_AWARD_CODE);
                hhID = reg_m_assign_prog_srv.getString(HHID);
                MemID = reg_m_assign_prog_srv.getString(MEM_ID);
                ProgCode = reg_m_assign_prog_srv.getString(PROG_CODE);
                SrvCode = reg_m_assign_prog_srv.getString(SRV_CODE);
                RegNDate = reg_m_assign_prog_srv.getString(REG_N_DATE);
                GRDCode = reg_m_assign_prog_srv.getString(GRD_CODE);
                GRDDate = reg_m_assign_prog_srv.getString(GRD_DATE);
                SrvMin = reg_m_assign_prog_srv.getString("SrvMin");
                SrvMax = reg_m_assign_prog_srv.getString("SrvMax");


                sqlH.addRegNassignProgServiceFromOnline(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode, AdmDonorCode, AdmAwardCode, hhID, MemID, ProgCode, SrvCode, RegNDate, GRDCode, GRDDate, SrvMin, SrvMax);


               /* Log.d(TAG, "RegNAss- AdmCountryCode :" + AdmCountryCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode
                        + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhID + " MemID : " + MemID + " ProgCode : " + ProgCode + " SrvCode : " + SrvCode +
                        " RegNDate : " + RegNDate + "GRDCode: " + GRDCode + " GDRDate : " + GRDDate);
*/

            } catch (Exception e) {
                Log.d(TAG, "Expetion : " + e);
                e.printStackTrace();
            }
        }

    }

    public static void reg_N_FFAParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {
        int size;

        String CountryCode;
        String DistrictCode;
        String UpazillaCode;
        String UnitCode;
        String VillageCode;
        String HhId;
        String MemID;
        String OrphanedChildren;
        String ChildHeaded;
        String ElderlyHeaded;
        String ChronicallyIll;
        String FemaleHeaded;
        String CropFailure;
        String ChildrenRecSuppFeedN;
        String Willingness;


        size = jsonArrayData.length();

        try {
            for (int i = 0; i < size; i++) {
                JSONObject reg_n_ffa_tableData = jsonArrayData.getJSONObject(i);

                CountryCode = reg_n_ffa_tableData.getString(ADM_COUNTRY_CODE);
                DistrictCode = reg_n_ffa_tableData.getString(LAY_R_1_LIST_CODE);
                UpazillaCode = reg_n_ffa_tableData.getString(LAY_R_2_LIST_CODE);
                UnitCode = reg_n_ffa_tableData.getString(LAY_R_3_LIST_CODE);
                VillageCode = reg_n_ffa_tableData.getString(LAY_R_4_LIST_CODE);
                HhId = reg_n_ffa_tableData.getString(HHID);
                MemID = reg_n_ffa_tableData.getString(MEM_ID);
                OrphanedChildren = reg_n_ffa_tableData.getString("OrphanedChildren");
                ChildHeaded = reg_n_ffa_tableData.getString("ChildHeaded");
                ElderlyHeaded = reg_n_ffa_tableData.getString("ElderlyHeaded");
                ChronicallyIll = reg_n_ffa_tableData.getString("ChronicallyIll");
                FemaleHeaded = reg_n_ffa_tableData.getString("FemaleHeaded");
                CropFailure = reg_n_ffa_tableData.getString("CropFailure");
                ChildrenRecSuppFeedN = reg_n_ffa_tableData.getString("ChildrenRecSuppFeedN");
                Willingness = reg_n_ffa_tableData.getString("Willingness");


                sqlH.insertIntoDDR_RegN_FFATable(CountryCode, DistrictCode, UpazillaCode, UnitCode, VillageCode, HhId, MemID, OrphanedChildren, ChildHeaded, ElderlyHeaded, ChronicallyIll, FemaleHeaded, CropFailure, ChildrenRecSuppFeedN, Willingness, "", "");
            }

        } catch (Exception e) {
            Log.e(TAG, "Expetion : " + e);
            e.printStackTrace();
        }

    }


    public static void staff_master_DataParser(JSONArray reg_m_assign_prog_srvs, SQLiteHandler sqlH) {

    }


    public static void DTA_Parser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic;
        String DTQCode;
        String DTACode;
        String DTALabel;
        String DTAValue;
        String DTSeq;
        String DTAShort;
        String DTScoreCode;
        String DTSkipDTQCode;
        String DTACompareCode;
        String ShowHide;
        String MaxValue;
        String MinValue;
        String DataType;
        String MarkOnGrid;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTQCode = jsonObject.getString("DTQCode");
                DTACode = jsonObject.getString("DTACode");
                DTALabel = jsonObject.getString("DTALabel");
                DTAValue = jsonObject.getString("DTAValue");
                DTSeq = jsonObject.getString("DTSeq");
                DTAShort = jsonObject.getString("DTAShort");
                DTScoreCode = jsonObject.getString("DTScoreCode");
                DTSkipDTQCode = jsonObject.getString("DTSkipDTQCode");
                DTACompareCode = jsonObject.getString("DTACompareCode");
                ShowHide = jsonObject.getString("ShowHide");
                MaxValue = jsonObject.getString("MaxValue");
                MinValue = jsonObject.getString("MinValue");
                DataType = jsonObject.getString("DataType");
                MarkOnGrid = jsonObject.getString("MarkOnGrid");


                Log.d("NIR_192",
                        "in DTA table  DTBasic :" + DTBasic +
                                " DTQCode :" + DTQCode +
                                " DTACode :" + DTACode +
                                " DTALabel :" + DTALabel +
                                " DTAValue :" + DTAValue +
                                " DTSeq :" + DTSeq +
                                " DTAShort :" + DTAShort +
                                " DTScoreCode :" + DTScoreCode +
                                " DTSkipDTQCode :" + DTSkipDTQCode +
                                " DTACompareCode :" + DTACompareCode +
                                " ShowHide :" + ShowHide +
                                " MaxValue :" + MaxValue +
                                " MinValue :" + MinValue +
                                " DataType :" + DataType +
                                " MarkOnGrid :" + MarkOnGrid
                );


                sqlH.addIntoDTATable(DTBasic, DTQCode, DTACode, DTALabel, DTAValue, StringToLongNullCheck(DTSeq), DTAShort, DTScoreCode, DTSkipDTQCode, DTACompareCode, ShowHide
                        , StringToLongNullCheck(MaxValue), StringToLongNullCheck(MinValue), DataType, MarkOnGrid, "", "");

                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    private static long StringToLongNullCheck(String string) {

        long lgMaxValue = -1;
        if (string != null) {
            if (string.equals("null") || string.length() == 0) {
                lgMaxValue = 0;
            } else {
                lgMaxValue = (long) Double.parseDouble(string);
            }
        }

        return lgMaxValue;
    }


    public static void DTBasicParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic;
        String DTTitle;
        String DTSubTitle;
        String DTDescription;
        String DTAutoScroll;
        String DTAutoScrollText;
        String DTActive;
        String DTCategory;
        String DTGeoListLevel;
        String DTOPMode;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTTitle = jsonObject.getString("DTTitle");
                DTSubTitle = jsonObject.getString("DTSubTitle");
                DTDescription = jsonObject.getString("DTDescription");
                DTAutoScroll = jsonObject.getString("DTAutoScroll");
                DTAutoScrollText = jsonObject.getString("DTAutoScrollText");
                DTActive = jsonObject.getString("DTActive");
                DTCategory = jsonObject.getString("DTCategory");
                DTGeoListLevel = jsonObject.getString("DTGeoListLevel");
                DTOPMode = jsonObject.getString("DTOPMode");

                sqlH.addIntoDTBasic(DTBasic, DTTitle, DTSubTitle, DTDescription, DTAutoScroll, DTAutoScrollText, DTActive, DTCategory, DTGeoListLevel, DTOPMode, "", "");

                Log.d(TAG, " DTBasic Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void DTCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String CategoryName;
        String Frequency;

        String DTCategory;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTCategory = jsonObject.getString("DTCategory");
                CategoryName = jsonObject.getString("CategoryName");
                Frequency = jsonObject.getString("Frequency");


                sqlH.addIntoDTCategory(DTCategory, CategoryName, Frequency, "", "");

                Log.d(TAG, " DTCategory Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }
        }

    }


    public static void DTCountryProgramParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String AdmProgCode;
        String ProgActivityCode;
        String ProgActivityTitle;
        String DTBasic;
        String RefIdentifier;
        String Status;
        String RptFrequency;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                ProgActivityCode = jsonObject.getString("ProgActivityCode");
                ProgActivityTitle = jsonObject.getString("ProgActivityTitle");
                DTBasic = jsonObject.getString("DTBasic");
                RefIdentifier = jsonObject.getString("RefIdentifier");
                Status = jsonObject.getString("Status");
                RptFrequency = jsonObject.getString("RptFrequency");


                sqlH.addIntoDTCountryProgram(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, ProgActivityCode, ProgActivityTitle, DTBasic, RefIdentifier, Status, RptFrequency, "", "");

                Log.d(TAG, "D_T_CountryProgram");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTTableListCategoryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String TableName;
        String TableGroupCode;
        String UseAdminOnly;
        String UseReport;
        String UseTransaction;
        String UseLUP;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                TableName = jsonObject.getString("TableName");
                TableGroupCode = jsonObject.getString("TableGroupCode");
                UseAdminOnly = jsonObject.getString("UseAdminOnly");
                UseReport = jsonObject.getString("UseReport");
                UseTransaction = jsonObject.getString("UseTransaction");
                UseLUP = jsonObject.getString("UseLUP");

                sqlH.addIntoDTTableListCategory(TableName, TableGroupCode, UseAdminOnly, UseReport, UseTransaction, UseLUP);

                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


            // Log.d(TAG, "Country Code : " + AdmCountryCode + " Country hhName : " + AdmCountryName);
        }

    }

    public static void DTTableDefinitionParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String TableName;
        String FieldName;
        String FieldDefinition;
        String FieldShortName;
        String ValueUDF;
        String LUPTableName;
        String AdminOnly;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                TableName = jsonObject.getString("TableName");
                FieldName = jsonObject.getString("FieldName");
                FieldDefinition = jsonObject.getString("FieldDefinition");
                FieldShortName = jsonObject.getString("FieldShortName");
                ValueUDF = jsonObject.getString("ValueUDF");
                LUPTableName = jsonObject.getString("LUPTableName");
                AdminOnly = jsonObject.getString("AdminOnly");

                sqlH.addIntoDTTableDefinition(TableName, FieldName, FieldDefinition, FieldShortName, ValueUDF, LUPTableName, AdminOnly, "", "");

                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


            // Log.d(TAG, "Country Code : " + AdmCountryCode + " Country hhName : " + AdmCountryName);
        }

    }

    public static void DTResponseTableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic;
        String AdmCountryCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String AdmProgCode;
        String DTEnuID;
        String DTQCode;
        String DTACode;
        String DTRSeq;
        String DTAValue;
        String ProgActivityCode;
        String DTTimeString;
        String OpMode;
        String OpMonthCode;
        String DataType;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                DTEnuID = jsonObject.getString("DTEnuID");
                DTQCode = jsonObject.getString("DTQCode");
                DTACode = jsonObject.getString("DTACode");
                DTRSeq = jsonObject.getString("DTRSeq");
                DTAValue = jsonObject.getString("DTAValue");
                ProgActivityCode = jsonObject.getString("ProgActivityCode");
                DTTimeString = jsonObject.getString("DTTimeString");
                OpMode = jsonObject.getString("OpMode");
                OpMonthCode = jsonObject.getString("OpMonthCode");
                DataType = jsonObject.getString("DataType");

                sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode,
                        DTRSeq, DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType);

//                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTQResModeParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String QResMode;
        String QResLupText;
        String DataType;
        String LookUpUDFName;
        String ResponseValueControl;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                QResMode = jsonObject.getString("QResMode");
                QResLupText = jsonObject.getString("QResLupText");
                DataType = jsonObject.getString("DataType");
                LookUpUDFName = jsonObject.getString("LookUpUDFName");
                ResponseValueControl = jsonObject.getString("ResponseValueControl");

                sqlH.addIntoDTQResMode(QResMode, QResLupText, DataType, LookUpUDFName, ResponseValueControl);

                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTQTableParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String DTBasic;
        String DTQCode;
        String QText;
        String QResMode;
        String QSeq;
        String AllowNull;
        String LUPTableName;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                DTBasic = jsonObject.getString("DTBasic");
                DTQCode = jsonObject.getString("DTQCode");
                QText = jsonObject.getString("QText");
                QResMode = jsonObject.getString("QResMode");
                QSeq = jsonObject.getString("QSeq");
                AllowNull = jsonObject.getString("AllowNull");
                LUPTableName = jsonObject.getString("LUPTableName");

                sqlH.addIntoDTQTable(DTBasic, DTQCode, QText, QResMode, QSeq, AllowNull, LUPTableName);

//                Log.d(TAG, "DT Ans Table");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }

    public static void DTGeoListLevelParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String GeoLevel;
        String GeoLevelName;
        String ListUDFName;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                GeoLevel = jsonObject.getString("GeoLevel");
                GeoLevelName = jsonObject.getString("GeoLevelName");
                ListUDFName = jsonObject.getString("ListUDFName");
                sqlH.addIntoDTGeoListLevel(GeoLevel, GeoLevelName, ListUDFName, "", "");

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void DTLUPParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode;
        String TableName;
        String ListCode;
        String ListName;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);
                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                TableName = jsonObject.getString("TableName");
                ListCode = jsonObject.getString("ListCode");
                ListName = jsonObject.getString("ListName");

                sqlH.addIntoDTLUP(AdmCountryCode, TableName, ListCode, ListName);

            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }

    }


    public static void CommunityGroupParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();
        String AdmCountryCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String AdmProgCode;
        String GrpCode;
        String GrpName;
        String GrpCatCode;
        String LayR1Code;
        String LayR2Code;
        String LayR3Code;
        String SrvCenterCode;
        String EntryBy;
        String EntryDate;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                GrpCode = jsonObject.getString("GrpCode");
                GrpName = jsonObject.getString("GrpName");
                GrpCatCode = jsonObject.getString("GrpCatCode");
                LayR1Code = jsonObject.getString("LayR1Code");
                LayR2Code = jsonObject.getString("LayR2Code");
                LayR3Code = jsonObject.getString("LayR3Code");

                SrvCenterCode = jsonObject.getString("SrvCenterCode");

                EntryBy = "";
                EntryDate = "";


                sqlH.addCommunityGroup(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode, GrpName, GrpCatCode, LayR1Code, LayR2Code, LayR3Code, SrvCenterCode, EntryBy, EntryDate);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }// end of for

    }


    public static void CommunityGroupDetailsParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();

        String AdmCountryCode;
        String AdmDonorCode;
        String AdmAwardCode;
        String AdmProgCode;
        String GrpCode;
        String OrgCode;
        String StfCode;
        String LandSizeUnderIrrigation;
        String IrrigationSystemUsed;
        String FundSupport;
        String ActiveStatus;
        String RepName;
        String RepPhoneNumber;
        String FormationDate;
        String TypeOfGroup;
        String Status;
        String EntryBy;
        String EntryDate;
        String ProjectNo;
        String ProjectTitle;
        String LayR1Code;
        String LayR2Code;
        String LayR3Code;


        for (int i = 0; i < size; i++) {
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);


                AdmCountryCode = jsonObject.getString("AdmCountryCode");
                AdmDonorCode = jsonObject.getString("AdmDonorCode");
                AdmAwardCode = jsonObject.getString("AdmAwardCode");
                AdmProgCode = jsonObject.getString("AdmProgCode");
                GrpCode = jsonObject.getString("GrpCode");
                OrgCode = jsonObject.getString("OrgCode");
                StfCode = jsonObject.getString("StfCode");
                LandSizeUnderIrrigation = jsonObject.getString("LandSizeUnderIrrigation");
                IrrigationSystemUsed = jsonObject.getString("IrrigationSystemUsed");
                FundSupport = jsonObject.getString("FundSupport");
                ActiveStatus = jsonObject.getString("ActiveStatus");
                RepName = jsonObject.getString("RepName");
                RepPhoneNumber = jsonObject.getString("RepPhoneNumber");
                FormationDate = jsonObject.getString("FormationDate");
                TypeOfGroup = jsonObject.getString("TypeOfGroup");
                Status = jsonObject.getString("Status");
                ProjectNo = jsonObject.getString("ProjectNo");
                ProjectTitle = jsonObject.getString("ProjectTitle");

                LayR1Code = jsonObject.getString("LayR1Code");
                LayR2Code = jsonObject.getString("LayR2Code");
                LayR3Code = jsonObject.getString("LayR3Code");


                EntryBy = "";
                EntryDate = "";

            /*    Log.d(TAG, "AdmCountryCode:" + AdmCountryCode + "AdmDonorCode:" + AdmDonorCode + "AdmAwardCode:" + AdmAwardCode +
                                "AdmProgCode:" + AdmProgCode + "GrpCode:" + GrpCode + "OrgCode:" + OrgCode + "StfCode:" + StfCode + "LandSizeUnderIrrigation:" + LandSizeUnderIrrigation +
                                "IrrigationSystemUsed:" + IrrigationSystemUsed + "FundSupport:" + FundSupport + "ActiveStatus:" + ActiveStatus +
                                "RepName:" + RepName + "RepPhoneNumber:" + RepPhoneNumber + "FormationDate:" + FormationDate + "TypeOfGroup:" + TypeOfGroup +
                                "Status" + Status + "EntryBy:" + EntryBy + "EntryDate:" + EntryDate + "ProjectNo:" + ProjectNo + "ProjectTitle:" + ProjectTitle
                        + "/n LayR1Code:"+ LayR1Code + "LayR2Code:"+ LayR2Code+"LayR3Code:" + LayR3Code
                );*/


                sqlH.addIntoGroupDetails(AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, GrpCode,
                        OrgCode, StfCode, LandSizeUnderIrrigation, IrrigationSystemUsed, FundSupport,
                        ActiveStatus, RepName, RepPhoneNumber, FormationDate, TypeOfGroup, Status,
                        EntryBy, EntryDate, ProjectNo, ProjectTitle, LayR1Code, LayR2Code, LayR3Code);


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }// end of for

    }


    public static void RegN_CA2Parser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        int size = jsonArrayData.length();


        String AdmCountryCode;
        String LayR1ListCode;
        String LayR2ListCode;
        String LayR3ListCode;
        String LayR4ListCode;
        String hhId;
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
            try {

                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(ADM_COUNTRY_CODE);
                LayR1ListCode = jsonObject.getString(LAY_R_1_LIST_CODE);
                LayR2ListCode = jsonObject.getString(LAY_R_2_LIST_CODE);
                LayR3ListCode = jsonObject.getString(LAY_R_3_LIST_CODE);
                LayR4ListCode = jsonObject.getString(LAY_R_4_LIST_CODE);
                hhId = jsonObject.getString(HHID);
                MemID = jsonObject.getString(MEM_ID);
                RegNDate = jsonObject.getString(REG_N_DATE);
                CA2DOB = jsonObject.getString(CA_2_DOB);
                AdmProgCode = jsonObject.getString(ADM_PROG_CODE);
                AdmSrvCode = jsonObject.getString(ADM_SRV_CODE);
                GRDCode = jsonObject.getString(GRD_CODE);
                CA2GRDDate = jsonObject.getString(CA_2_GRD_DATE);
                ChildName = jsonObject.getString("ChildName");
                ChildSex = jsonObject.getString("ChildSex");


                sqlH.addRegNCA2_FromOnLine(AdmCountryCode, LayR1ListCode, LayR2ListCode, LayR3ListCode, LayR4ListCode,
                        hhId, MemID, AdmProgCode, AdmSrvCode, RegNDate, GRDCode, CA2DOB, CA2GRDDate, ChildName, ChildSex);


                Log.d(TAG, "In RegNCA2 Table- AdmCountryCode :" + AdmCountryCode + " LayR1ListCode : " + LayR1ListCode + " LayR2ListCode : " + LayR2ListCode + " LayR3ListCode : " + LayR3ListCode + " LayR4ListCode : " + LayR4ListCode + " hhID : " + hhId + " MemID  : " + MemID + " AdmProgCode : " + AdmProgCode + " AdmSrvCode : " + AdmSrvCode + " RegNDate : " + RegNDate + " GRDCode : " + GRDCode + /*" EntryBy : " + EntryBy + " EntryDate : " + EntryDate +*/ " LMDOB : " + CA2DOB + " LMGRDDate : " + CA2GRDDate);// + " FDPCode  : " + FDPCode );


            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }// end of for

    }

    /**
     * Parse and insert into the db
     *
     * @param jsonArrayData countrys json array
     * @param sqlH          database
     */
    public static void AdmCountryParser(JSONArray jsonArrayData, SQLiteHandler sqlH) {

        ArrayList<AdmCountryDataModel> arrayList = AdmCountryParser(jsonArrayData);


        for (int i = 0; i < arrayList.size(); i++) {

            sqlH.addCountry(arrayList.get(i).getAdmCountryCode(), arrayList.get(i).getAdmCountryName());


        }// end of for

    }


    public static ArrayList<AdmCountryDataModel> AdmCountryParser(JSONArray jsonArrayData) {

        int size = jsonArrayData.length();

/**
 * to see the log use those tep veriable
 */
        String AdmCountryCode;
        String AdmCountryName;
        ArrayList<AdmCountryDataModel> arrayList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            try {
                JSONObject jsonObject = jsonArrayData.getJSONObject(i);

                AdmCountryCode = jsonObject.getString(Parser.ADM_COUNTRY_CODE);
                AdmCountryName = jsonObject.getString(Parser.ADM_COUNTRY_NAME);

                AdmCountryDataModel dataModel = new AdmCountryDataModel();

                dataModel.setAdmCountryCode(AdmCountryCode);
                dataModel.setAdmCountryName(AdmCountryName);

                arrayList.add(dataModel);

                Log.d(TAG, "In Adm country Table- AdmCountryCode :" + AdmCountryCode + " AdmCountryName : " + AdmCountryName);
            } catch (Exception e) {
                Log.e(TAG, "Exception : " + e);
                e.printStackTrace();
            }


        }// end of for
        return arrayList;
    }


}// end of the class
