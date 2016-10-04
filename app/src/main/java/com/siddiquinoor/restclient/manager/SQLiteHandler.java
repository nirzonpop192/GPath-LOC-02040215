package com.siddiquinoor.restclient.manager;

/**
 * This class is the Base Handler of all SQL operation
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.siddiquinoor.restclient.data_model.AGR_DataModel;
import com.siddiquinoor.restclient.data_model.AssignDDR_FFA_DataModel;
import com.siddiquinoor.restclient.data_model.DTQResModeDataModel;
import com.siddiquinoor.restclient.data_model.DTResponseTableDataModel;
import com.siddiquinoor.restclient.data_model.DT_ATableDataModel;
import com.siddiquinoor.restclient.data_model.FDPItem;
import com.siddiquinoor.restclient.data_model.GPS_LocationAttributeDataModel;
import com.siddiquinoor.restclient.data_model.GPS_LocationDataModel;
import com.siddiquinoor.restclient.data_model.GPS_SubGroupAttributeDataModel;
import com.siddiquinoor.restclient.data_model.Lup_gpsListDataModel;
import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;

import com.siddiquinoor.restclient.data_model.ServiceCenterItem;
import com.siddiquinoor.restclient.data_model.ServiceSpecificDataModel;
import com.siddiquinoor.restclient.data_model.VillageItem;
import com.siddiquinoor.restclient.manager.sqlsyntax.Schema;
import com.siddiquinoor.restclient.data_model.CTDataModel;
import com.siddiquinoor.restclient.data_model.RegN_HH_libDataModel;
import com.siddiquinoor.restclient.data_model.RegN_MM_libDataModel;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.CommunityGroupDataModel;
import com.siddiquinoor.restclient.views.adapters.DistributionGridDataModel;
import com.siddiquinoor.restclient.views.adapters.DistributionSaveDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQuesDataModel;
import com.siddiquinoor.restclient.views.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.adapters.ListDataModel;
import com.siddiquinoor.restclient.views.adapters.GPSLocationLatLong;
import com.siddiquinoor.restclient.views.adapters.MemberModel;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;
import com.siddiquinoor.restclient.views.adapters.ServiceSlDataModle;
import com.siddiquinoor.restclient.views.adapters.SummaryAssignListModel;
import com.siddiquinoor.restclient.views.adapters.SummaryCriteriaModel;
import com.siddiquinoor.restclient.views.adapters.SummaryGroupListDataModel;
import com.siddiquinoor.restclient.views.adapters.SummaryIdListInGroupDataModel;
import com.siddiquinoor.restclient.views.adapters.SummaryModel;
import com.siddiquinoor.restclient.views.adapters.SummaryServiceListModel;
import com.siddiquinoor.restclient.views.adapters.VouItemServiceExtDataModel;
import com.siddiquinoor.restclient.views.adapters.raf_data_model.GraduationDateCode;
import com.siddiquinoor.restclient.views.helper.LocationHelper;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    // All Static variables

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "pci";
    // Android meta data table
    public static final String SQLITE_SEQUENCE = "SQLITE_SEQUENCE";
    public static final String TABLE_NAME = "NAME";
    public static final String SQL_QUERY_SYNTAX = "SqlQuery";
    public static final String UPLOAD_SYNTAX_TABLE = "UploadSyntax";
    public static final String FOOD_FLAG = "FoodFlag";
    public static final String NON_FOOD_FLAG = "NFoodFlag";
    public static final String CASH_FLAG = "CashFlag";
    public static final String VOUCHER_FLAG = "VOFlag";
    public static final String ACTIVE = "A";
    public static final int REGISTRATION_OP_CODE = 1;
    public static final String ASSIGN_SUMMARY_PROGRAM_DETAILS = "assignSummaryProgramDetails";
    public static final String FDP_LAY_R2 = "FdpLayR2";
    private static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";


    private String next_id = "";
    private static final int ID_LENGTH = 5;

    // Login table name
    // todo: rename table Name
    public static final String LOGIN_TABLE = "UsrLogIn";
    public static final String STAFF_MASTER_TABLE = "StaffMaster";
    public static final String STAFF_ID_COL = "StfCode";
    public static final String STAFF_NAME_COL = "StfName";
    public static final String STAFF_STATUS_COL = "StfStatus";
    public static final String STAFF_CATEGORY_COL = "StfCategory";
    public static final String STAFF_ADMIN_ROLE_COL = "StfAdminRole";


    public static final String COUNTRY_TABLE = "Countries";
    public static final String VALID_DATE_RANGE = "ValidDateRange";
    public static final String LAYER_LABEL_TABLE = "LayerLabel";
    public static final String DISTRICT_TABLE = "District";
    public static final String UPAZILLA_TABLE = "Upazilla";
    public static final String UNIT_TABLE = "Unit";
    public static final String VILLAGE_TABLE = "Village";
    public static final String VILLAGE_TABLE_FOR_ASSIGN = "VillageForQuery"; // THIS KEY USE FOR QUERY IN ASSIGN
    public static final String REGISTRATION_TABLE = "Registration";
    public static final String REGISTRATION_MEMBER_TABLE = "RegMembers";
    public static final String RELATION_TABLE = "MemRelation";


    public static final String SERVICE_TABLE = "Service";
    public static final String ADM_AWARD_TABLE = "AdmAward";
    public static final String ADM_DONOR_TABLE = "AdmDonor";
    public static final String PROGRAM_MASTER_TABLE = "ProgramMaster";
    public static final String SERVICE_MASTER_TABLE = "ServiceMaster";
    public static final String REG_N_ASSIGN_PROG_SRV_TABLE = "RegNAssignProgService";
    public static final String GPS_GROUP_TABLE = "GpsGroup";
    public static final String GPS_SUB_GROUP_TABLE = "GpsSubGroup";
    public static final String GPS_LOCATION_TABLE = "GpsLocation";
    public static final String OP_MONTH_TABLE = "OpMonthTable";
    public static final String COUNTRY_PROGRAM_TABLE = "Program";
    public static final String SERVICE_CENTER_TABLE = "ServiceCenter";

    public static final String STAFF_GEO_INFO_ACCESS_TABLE = "StaffGeoInfoAccess";

    public static final String HOUSE_HOLD_CATEGORY_TABLE = "HouseHoldCategory";
    public static final String LIBERIA_REGISTRATION_TABLE = "Liberia_Registration";

    public static final String REG_N_LUP_GRADUATION_TABLE = "Graduation";

    public static final String REPORT_TEMPLATE_TABLE = "ReportTemplate";
    public static final String CARD_PRINT_REASON_TABLE = "CardPrintReason";
    public static final String MEMBER_CARD_PRINT_TABLE = "MemberCardPrint";

    public static final String VOUCHER_ITEM_TABLE = "VoucherItemTable";
    public static final String VOUCHER_ITEM__MEAS_TABLE = "VoucherItemMeas";
    public static final String VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE = "VoucherCountryProgItem";
    public static final String SERVICE_EXTENDED_TABLE = "ServiceExtended";
    public static final String DISTRIBUTION_EXTENDED_TABLE = "DistributionExtended";
    public static final String SELECTED_VILLAGE_TABLE = "SelectedVillage";
    public static final String SELECTED_FDP_TABLE = "SelectedFDP";
    public static final String SELECTED_SERVICE_CENTER_TABLE = "SelectedCenter";
    public static final String COMMUNITY_GROUP_TABLE = "CommunityGroup";
    public static final String GPS_SUB_GROUP_ATTRIBUTES_TABLE = "GPSSubGroupAttributes";
    public static final String LUP_GPS_TABLE = "LUP_GPSTable";
    public static final String LUP_COMMUNITY_ANIMAL_TABLE = "LUP_CommnityAnimalList";
    public static final String LUP_PROG_GROUP_CROP_TABLE = "LUP_ProgGroupCropList";
    public static final String LUP_COMMUNITY_LOAN_SOURCE_TABLE = "LUP_CommnityLoanSource";
    public static final String REG_N_MEM_PROG_GRP_TABLE = "RegNMemProgGrp";
    public static final String COMMUNITY_GROUP_CATEGORY_TABLE = "CommunityGroupCategory";
    public static final String DTQRES_MODE_TABLE = "DTQResMode";
    public static final String GPS_LOCATION_CONTENT_TABLE = "GPSLocationContent";


    public static final String LUP_COMMUNITY_LEAD_POSITION_TABLE = "LUP_CommunityLeadPosition";

    public static final String GPS_LOCATION_ATTRIBUTES_TABLE = "GPSLocationAttributes";
    public static final String SERVICE_SPECIFIC_TABLE = "SrvSpecific";

    public static final String LAST_SYNC_TYRACE_TABLE = "LastSyncTrace";
    public static final String REG_N_FFA_TABLE = "RegN_FFA";
    public static final String DIST_N_PLAN_BASIC_TABLE = "DistNPlanBasic";
    public static final String LUP_REGN_ADDRESS_LOOKUP_TABLE = "LUP_RegNAddLookup";
    public static final String COMMUNITY_GRP_DETAIL_TABLE = "CommunityGrpDetail";
    public static final String DTGEO_LIST_LEVEL_TABLE = "DTGeoListLevel";
    public static final String PROGRAM_ORGANIZATION_ROLE_TABLE = "ProgOrgNRole";
    public static final String PROGRAM_ORGANIZATION_NAME_TABLE = "ProgOrgN";

    public static final String DT_RESPONSE_TABLE_COL = "DTResponseTable";
    public static final String DT_CATEGORY_TABLE = "DTCategory";
    public static final String DT_COUNTRY_PROGRAM_TABLE = "DTCountryProgram";
    public static final String DTGEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DTQRES_MODE_COL = "DTQResMode";
    public static final String DTQ_TABLE = "DTQTable";
    public static final String DT_TABLE_DEFINITION_TABLE = "DTTableDefinition";
    public static final String DTTABLE_LIST_CATEGORY_TABLE = "DTTableListCategory";

    public static final String DT_A_TABLE = "DTATable";
    public static final String DT_BASIC_TABLE = "DTBasic";
    public static final String DT_BASIC_COL = "DTBasic";
    public static final String DTQ_CODE_COL = "DTQCode";
    public static final String DTA_CODE_COL = "DTACode";
    public static final String DTA_LABEL_COL = "DTALabel";

    public static final String DT_SEQ_COL = "DTSeq";
    public static final String DTA_SHORT_COL = "DTAShort";
    public static final String DT_SCORE_CODE_COL = "DTScoreCode";
    public static final String DTSKIP_DTQ_CODE_COL = "DTSkipDTQCode";
    public static final String DTA_COMPARE_CODE_COL = "DTACompareCode";
    public static final String DT_TITLE_COL = "DTTitle";
    public static final String DT_SUB_TITLE_COL = "DTSubTitle";
    public static final String DT_DESCRIPTION_COL = "DTDescription";
    public static final String DT_AUTO_SCROLL_COL = "DTAutoScroll";
    public static final String DTAUTO_SCROLL_TEXT = "DTAutoScrollText";
    public static final String DT_ACTIVE_COL = "DTActive";
    public static final String DT_CATEGORY_COL = "DTCategory";
    public static final String DT_GEO_LIST_LEVEL_COL = "DTGeoListLevel";
    public static final String DT_OP_MODE_COL = "DTOPMode";

    public static final String FREQUENCY_COL = "Frequency";
    public static final String PROG_ACTIVITY_CODE_COL = "ProgActivityCode";
    public static final String PROG_ACTIVITY_TITLE_COL = "ProgActivityTitle";
    public static final String REF_IDENTIFIER_COL = "RefIdentifier";
    public static final String RPT_FREQUENCY_COL = "RptFrequency";
    public static final String GEO_LEVEL_COL = "GeoLevel";
    public static final String GEO_LEVEL_NAME_COL = "GeoLevelName";
    public static final String LIST_UDF_NAME_COL = "ListUDFName";
    public static final String QRES_MODE_COL = "QResMode";
    public static final String QRES_LUP_TEXT_COL = "QResLupText";
    public static final String LOOK_UP_UDF_NAME_COL = "LookUpUDFName";
    public static final String RESPONSE_VALUE_CONTROL_COL = "ResponseValueControl";
    public static final String QTEXT_COL = "QText";
    public static final String ALLOW_NULL_COL = "AllowNull";
    public static final String LUP_TABLE_NAME = "LUPTableName";

    public static final String QSEQ_SCOL = "QSeq";
    public static final String DT_ENU_ID_COL = "DTEnuID";
    public static final String OP_MODE_COL = "OpMode";
    public static final String DTTIME_STRING_COL = "DTTimeString";
    public static final String DT_RSEQ_COL = "DTRSeq";
    public static final String DTA_VALUE_COL = "DTAValue";
    public static final String TABLE_NAME_COL = "TableName";
    public static final String FIELD_NAME_COL = "FieldName";
    public static final String FIELD_DEFINITION_COL = "FieldDefinition";
    public static final String FIELD_SHORT_NAME_COL = "FieldShortName";
    public static final String VALUE_UDF_COL = "ValueUDF";
    public static final String LUPTABLE_NAME_COL = "LUPTableName";
    public static final String ADMIN_ONLY_COL = "AdminOnly";
    public static final String TABLE_GROUP_CODE_COL = "TableGroupCode";
    public static final String USE_ADMIN_ONLY_COL = "UseAdminOnly";
    public static final String USE_REPORT_COL = "UseReport";
    public static final String USE_TRANSACTION_COL = "UseTransaction";
    public static final String USE_LUP_COL = "UseLUP";


    public static final String SHOW_HIDE_COL = "ShowHide";
    public static final String MAX_VALUE_COL = "MaxValue";
    public static final String MIN_VALUE_COL = "MinValue";
    public static final String MARK_ON_GRID_COL = "MarkOnGrid";

    public static final String LAND_SIZE_UNDER_IRRIGATION_COL = "LandSizeUnderIrrigation";
    public static final String IRRIGATION_SYSTEM_USED_COL = "IrrigationSystemUsed";
    public static final String FUND_SUPPORT_COL = "FundSupport";
    public static final String REP_NAME_COL = "RepName";
    public static final String REP_PHONE_NUMBER_COL = "RepPhoneNumber";
    public static final String FORMATION_DATE_COL = "FormationDate";
    public static final String PROJECT_NO_COL = "ProjectNo";
    public static final String PROJECT_TITLE = "ProjectTitle";


    public static final String UPZELLA_TABLE_CUSTOM_QUERY = "UpazellaTableCustomQuery";

    // Specific Assigne Table
    public static final String REG_N_LM_TABLE = "RegN_LM";
    public static final String REG_N_PW_TABLE = "RegN_PW";
    public static final String REG_N_CU2_TABLE = "RegN_CU2";
    public static final String REG_N_CA2_TABLE = "RegN_CA2";
    public static final String REG_N_AGR_TABLE = "RegN_ARG";
    public static final String REG_N_CT_TABLE = "RegN_CT";
    public static final String REG_N_VUL_TABLE = "RegN_VUL";

    public static final String LUP_SRV_OPTION_LIST_TABLE = "LUP_SrvOptionList";
    public static final String LUP_GPS_LIST_TABLE = "LUP_GPSList";

    public static final String LUP_VALUE_CODE_COL = "LupValueCode";
    public static final String LUP_VALUE_TEXT_COL = "LupValueText";


    /**
     * *************************************************************************
     * COLUMN NAME DEFINE FROM HERE
     * **************************************************************************
     */

    // Login Table Columns names
    public static final String USER_ID = "UsrID";
    public static final String COUNTRY_CODE = "CountryCode";
    public static final String STAFF_CODE = "StaffCode";
    public static final String USER_LOGIN_NAME = "UsrLogInName";
    public static final String USER_LOGIN_PW = "UsrLogInPW";
    public static final String USER_FIRST_NAME = "UsrFirstName";
    public static final String USER_LAST_NAME = "UsrLastName";
    public static final String USER_EMAIL = "UsrEmail";
    public static final String USER_EMAIL_VERIFICATION = "UsrEmailVerification";
    public static final String USER_STATUS = "UsrStatus";

    public static final String LTP_2_HECTRES_COL = "LTp2Hectres";
    public static final String LT_3_FOOD_STOCK_COL = "LT3mFoodStock";
    public static final String NO_MAJOR_COMMON_LIVE_STOCK_COL = "NoMajorCommonLiveStock";
    public static final String RECEIVE_NO_FORMAL_WAGES_COL = "ReceiveNoFormalWages";
    public static final String NO_IGA_COL = "NoIGA";
    public static final String RELY_PICE_EORK_COL = "RelyPiecework";


    // COUNTRY
    public static final String ID_COL = "ID";
    public static final String COUNTRY_NAME = "CountryName";

    // Valid Date Range
    public static final String LAYER_CODE_COL = "LayerCode";
    public static final String LAYER_NAME_COL = "LayerName";

    // Layer Label
    public static final String DATE_START = "StartDate";
    public static final String DATE_END = "EndDate";

    // DISTRICT
    public static final String DISTRICT_CODE_COL = "DistrictCode";
    public static final String DISTRICT_NAME_COL = "DistrictName";

    // Upazilla
    public static final String UPCODE_COL = "UpazillaCode";
    public static final String UPZILLA_NAME_COL = "UpazillaName";

    // UNIT
    public static final String UCODE_COL = "UnitCode";
    public static final String UNITE_NAME_COL = "UnitName";

    // Village
    public static final String VCODE_COL = "VillageCode";
    public static final String VILLAGE_NAME_COL = "VillageName";
    public static final String HOUSE_HOLD_TARGET = "HhCount";


    //-------------- REGISTRATION-------
    public static final String COUNTRY_CODE_COL = "CountryCode";
    public static final String PID_COL = "RegistrationID";
    public static final String PNAME_COL = "PersonName";
    public static final String SEX_COL = "Sex";
    public static final String REG_DATE_COL = "RegistrationDate";
    public static final String VSLA_GROUP = "VSLAGroup";
    public static final String REGN_ADDRESS_LOOKUP_CODE_COL = "RegNAddLookupCode";
    public static final String REGN_ADDRESS_LOOKUP_NAME_COL = "RegNAddLookup";

    //public static final String PHOTO_COL            = "Photo";

    public static final String LATITUDE_COL = "Latitude";
    public static final String LONGITUDE_COL = "Longitude";
    public static final String HH_SIZE = "HHSize";
    public static final String AG_LAND = "AGLand";
    public static final String V_STATUS = "VStatus";
    public static final String M_STATUS = "MStatus";
    public static final String ENTRY_BY = "EntryBy";
    public static final String ENTRY_DATE = "EntryDate";
    public static final String RELATION_CODE = "HHRelationCode";
    public static final String RELATION_NAME = "RelationName";
    public static final String HOUSE_HOLD_TYPE_CODE_COL = "HHHeadCat";
    public static final String LT2YRS_M_COL = "LT2yrsM";
    public static final String LT2YRS_F_COL = "LT2yrsF";
    public static final String M_2TO5YRS_COL = "M2to5yrs";
    public static final String F_2TO5YRS_COL = "F2to5yrs";
    public static final String M_6TO12YRS_COL = "M6to12yrs";
    public static final String F_6TO12YRS_COL = "F6to12yrs";
    public static final String M_13TO17YRS_COL = "M13to17yrs";
    public static final String F_13TO17YRS_COL = "F13to17yrs";
    public static final String ORPHN_LT18YRS_M_COL = "Orphn_LT18yrsM";
    public static final String ORPHN_LT18YRS_F_COL = "Orphn_LT18yrsF";
    public static final String ADLT_18TO59_M_COL = "Adlt_18to59M";
    public static final String ADLT_18TO59_F_COL = "Adlt_18to59F";
    public static final String ELD_60P_M_COL = "Eld_60pM";
    public static final String ELD_60P_F_COL = "Eld_60pF";
    public static final String PLW_NO_COL = "Plw_No";
    public static final String CHRO_ILL_NO_COL = "Chro_Ill_No";
    public static final String DECEASED_CONTRACT_EBOLA_COL = "LivingDeceasedContractEbola";
    public static final String EXTRA_CHILD_CAUSE_EBOLA_COL = "ExtraChildBecauseEbola";
    public static final String EXTRA_ELDERLY_CAUSE_EBOLA_COL = "ExtraElderlyPersonBecauseEbola";
    public static final String EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL = "ExtraChronicallyIllDisabledPersonBecauseEbola";
    public static final String BRF_COUNT_CATTLE_COL = "BrfCountCattle";
    public static final String BRF_VALUE_CATTLE_COL = "BrfValueCattle";
    public static final String AFT_COUNT_CATTLE_COL = "AftCountCattle";
    public static final String AFT_VALUE_CATTLE_COL = "AftValueCattle";
    public static final String BRF_COUNT_SGOATS_COL = "BrfCountSgoats";
    public static final String BRF_VALUE_SGOATS_COL = "BrfValueSgoats";
    public static final String AFT_COUNT_SGOATS_COL = "AftCountSgoats";
    public static final String AFT_VALUE_SGOATS_COL = "AftValueSgoats";
    public static final String BRF_COUNT_POULTRY_COL = "BrfCountPoultry";
    public static final String BRF_VALUE_POULTRY_COL = "BrfValuePoultry";
    public static final String AFT_COUNT_POULTRY_COL = "AftCountPoultry";
    public static final String AFT_VALUE_POULTRY_COL = "AftValuePoultry";
    public static final String BRF_COUNT_OTHER_COL = "BrfCountOther";
    public static final String BRF_VALUE_OTHER_COL = "BrfValueOther";
    public static final String AFT_COUNT_OTHER_COL = "AftCountOther";
    public static final String AFT_VALUE_OTHER_COL = "AftValueOther";
    public static final String BRF_ACRE_CULTIVABLE_COL = "BrfAcreCultivable";
    public static final String BRF_VALUE_CULTIVABLE_COL = "BrfValueCultivable";
    public static final String AFT_ACRE_CULTIVABLE_COL = "AftAcreCultivable";
    public static final String AFT_VALUE_CULTIVABLE_COL = "AftValueCultivable";
    public static final String BRF_ACRE_NON_CULTIVABLE_COL = "BRFAcreNonCultivable";
    public static final String BRF_VAL_NON_CULTIVABLE_COL = "BRFValNonCultivable";
    public static final String AFT_ACRE_NON_CULTIVABLE = "AFTAcreNonCultivable";
    public static final String AFT_VAL_NON_CULTIVABLE = "AFTValNonCultivable";
    public static final String BRF_ACRE_ORCHARDS = "BRFAcreOrchards";
    public static final String BRF_VAL_ORCHARDS = "BRFValOrchards";
    public static final String AFT_ACRE_ORCHARDS = "AFTAcreOrchards";
    public static final String AFT_VAL_ORCHARDS = "AFTValOrchards";
    public static final String BRF_VAL_CROP = "BRFValCrop";
    public static final String AFT_VAL_CROP = "AFTValCrop";
    public static final String BRF_VAL_LIVESTOCK = "BRFValLivestock";
    public static final String AFT_VAL_LIVESTOCK = "AFTValLivestock";
    public static final String BRF_VAL_SMALL_BUSINESS = "BRFValSmallBusiness";
    public static final String AFT_VAL_SMALL_BUSINESS = "AFTValSmallBusiness";
    public static final String BRF_VAL_EMPLOYMENT = "BRFValEmployment";
    public static final String AFT_VAL_EMPLOYMENT = "AFTValEmployment";
    public static final String BRF_VAL_REMITTANCES = "BRFValRemittances";
    public static final String AFT_VAL_REMITTANCES = "AFTValRemittances";
    public static final String BRF_CNT_WAGEENR = "BRFCntWageEnr";
    public static final String AFT_CNT_WAGEENR = "AFTCntWageEnr";
    public static final String GPS_LONG_SWAP = "GPSLongSwap";
    public static final String SYNC_COL = "SyncStatus";
    public static final String W_RANK_COL = "WRank";


    public static final String LAST_SYNC_TIME_COL = "LastSyncTime";


    // Registration Member

    public static final String HHID_COL = "RegisterID";
    public static final String HH_MEM_ID = "MemberID";
    public static final String MEM_NAME_COL = "MemberName";
    public static final String RELATION_COL = "Relation";
    public static final String LMP_DATE = "LMPDate";
    public static final String CHILD_DOB = "ChildDOB";
    public static final String ELDERLY = "Elderly";
    public static final String DISABLE = "Disable";
    public static final String MEM_AGE = "MemAge";
    public static final String BIRTH_YEAR_COL = "BirthYear";
    public static final String MARITAL_STATUS_COL = "MaritalStatus";
    public static final String CONTACT_NO_COL = "ContactNo";
    public static final String MEMBER_OTHER_ID_COL = "MemberOrtherId";
    public static final String MEM_NAME_FIRST_COL = "MemberNameFirst";
    public static final String MEM_NAME_MIDDLE_COL = "MemberNameMiddle";
    public static final String MEM_NAME_LAST_COL = "MemberNameLast";
    public static final String PHOTO_COL = "Photo";
    public static final String TYPE_ID_COL = "TypeId";
    public static final String ID_NO_COL = "IdNo";
    public static final String V_BSC_MEM_1_NAME_FIRST_COL = "Vmem1NameFirst";
    public static final String V_BSC_MEM_1_NAME_MIDDLE_COL = "Vmem1NameMid";
    public static final String V_BSC_MEM_1_NAME_LAST_COL = "Vmem1NameLast";
    public static final String V_BSC_MEM_1_TITLE_COL = "Vmem1title";

    public static final String V_BSC_MEM_2_NAME_FIRST_COL = "Vmem2NameFirst";
    public static final String V_BSC_MEM_2_NAME_MIDDLE_COL = "Vmem2NameMid";
    public static final String V_BSC_MEM_2_NAME_LAST_COL = "Vmem2NameLast";
    public static final String V_BSC_MEM_2_TITLE_COL = "Vmem2title";

    public static final String PROXY_DESIGNATION_COL = "ProxyDesignation";
    public static final String PROXY_NAME_FIRST_COL = "ProxyNameFirst";
    public static final String PROXY_NAME_MIDDLE_COL = "ProxyNameMid";
    public static final String PROXY_NAME_LAST_COL = "ProxyNameLast";

    public static final String PROXY_BIRTH_YEAR_COL = "ProxyBirthYear";
    public static final String PROXY_PHOTO_COL = "ProxyPhoto";
    public static final String PROXY_TYPE_ID_COL = "ProxyTypeId";
    public static final String PROXY_ID_NO_COL = "ProxyIdNo";
    public static final String PROXY_BSC_MEM_1_NAME_FIRST_COL = "ProxyMem1NameFirst";
    public static final String PROXY_BSC_MEM_1_NAME_MIDDLE_COL = "ProxyMem1NameMid";
    public static final String PROXY_BSC_MEM_1_NAME_LAST_COL = "ProxyMem1NameLast";
    public static final String PROXY_BSC_MEM_1_TITLE_COL = "ProxyMem1Title";

    public static final String PROXY_BSC_MEM_2_NAME_FIRST_COL = "ProxyMem2NameFirst";
    public static final String PROXY_BSC_MEM_2_NAME_MIDDLE_COL = "ProxyMem2NameMid";
    public static final String PROXY_BSC_MEM_2_NAME_LAST_COL = "ProxyMem2NameLast";
    public static final String PROXY_BSC_MEM_2_TITLE_COL = "ProxyMem2Title";


    /**
     * ADDED BY POP COLUMN FOR SERVICE TABLE
     */

    public static final String DONOR_CODE_COL = "DonorCode";
    public static final String AWARD_CODE_COL = "AwardCode";
    public static final String PROGRAM_CODE_COL = "ProgramCode";
    public static final String SERVICE_CODE_COL = "ServiceCode";
    public static final String OPERATION_CODE_COL = "OpCode";
    public static final String OP_MONTH_CODE_COL = "OpMonthCode";
    public static final String SERVICE_SL_COL = "ServiceSL";
    public static final String SERVICE_DT_COL = "ServiceDT";
    public static final String SERVICE_STATUS_COL = "ServiceStatus";
    public static final String WORK_DAY_COL = "WD";
    /**
     * DisNplan table
     */
    public static final String SRV_OP_MONTH_CODE_COL = "SrvOpMonthCode";

    // ADDED BY POP COLUMN FOR COUNTRY_AWARD TABLE

    public static final String AWARD_REF_N_COL = "AwardRefNumber";
    public static final String AWARD_START_DATE_COL = "AwardStartDate";
    public static final String AWARD_END_DATE_COL = "AwardEndDate";
    public static final String AWARD_S_NAME_COL = "AwardShortName"; // award short name column
    public static final String AWARD_STATUS_COL = "AwardStatus";

    // ADDED BY POP COLUMN FOR DONOR TABLE
    public static final String DONOR_NAME_COL = "DonorName";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String PROGRAM_NAME_COL = "ProgramName";
    public static final String PROGRAM_SHORT_NAME_COL = "ProgramShortName";
    public static final String MULTIPLE_SERVICE_FLAG_COL = "MultipleSrv";
    public static final String DEFAULT_FOOD_DAYS_COL = "DefaultFoodDays";
    public static final String DEFAULT_NO_FOOD_DAYS_COL = "DefaultNFoodDays";
    public static final String DEFAULT_CASH_DAYS_COL = "DefaultCashDays";
    public static final String DEFAULT_VOUCHAR_DAYS_COL = "DefaultVODays";
    public static final String SERVICE_SPECIFIC_FLAG_COL = "SrvSpecific";

    // ADDED BY POP COLUMN FOR PROGRAM TABLE

    public static final String SERVICE_NAME_COL = "ServiceName";
    public static final String SERVICE_SHORT_NAME_COL = "ServiceShortName";

    // ADDED BY POP COLUMN FOR REG_N_ASSIGN_PROG_SRV_TABLE  TABLE

    public static final String REG_N_DAT_COL = "RegNDate";
    public static final String GRD_CODE_COL = "GRDCode";
    public static final String GRD_DATE_COL = "GRDDate";
    public static final String SRV_MIN_DATE_COL = "SrvMin";
    public static final String SRV_MAX_DATE_COL = "SrvMax";

    public static final String REG_N_STATUS_COL = "RegNStatus";
    public static final String GRD_STATUS_COL = "GRDStatus";


    // ADDED BY POP COLUMN FOR GRADUATION  TABLE

    public static final String GRD_TITLE_COL = "GRDTitle";
    public static final String DEFAULT_CAT_ACTIVE_COL = "DefaultCatActive";
    public static final String DEFAULT_CAT_EXIT_COL = "DefaultCatExit";


    // ADDED BY POP COLUMN FOR GPS GROUP TABLE

    public static final String GROUP_CODE_COL = "GroupCode";
    public static final String GROUP_NAME_COL = "GroupName";
    public static final String DESCRIPTION_COL = "Description";


    public static final String PRIME_Y_N = "PrimeYN";
    public static final String SUB_Y_N = "SubYN";
    public static final String TECH_Y_N = "TechYN";
    public static final String IMP_Y_N = "ImpYN";
    public static final String LONG_Y_N = "LogYN";
    public static final String OTH_Y_N = "OthYN";
    public static final String ORGANIZATION_NAME = "OrgNName";
    public static final String ORGANIZATION_SHORT_NAME = "OrgNShortName";


    // ADDED BY POP COLUMN FOR GPS SUB GROUP TABLE

    public static final String SUB_GROUP_CODE_COL = "SubGroupCode";
    public static final String SUB_GROUP_NAME_COL = "SubGroupName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE

    public static final String LOCATION_CODE_COL = "LocationCode";
    public static final String LOCATION_NAME_COL = "LocationName";

    // ADDED BY POP COLUMN FOR GPS LOCATION  TABLE


    public static final String MONTH_LABEL = "MonthLabel";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";

    public static final String USA_START_DATE = "usaStartDate";
    public static final String USA_END_DATE = "usaEndDate";
    public static final String STATUS = "Status";

    /**
     * ADDED BY POP COLUMN FOR GPS REG_N_LM  TABLE
     */


    public static final String LM_DATE_COL = "LMDob";
    public static final String GRDCODE_COL = "GRDCode";
    public static final String LMGRDDATE_COL = "LMGRDDate";
    public static final String CHILD_NAME_COL = "ChildName";
    public static final String CHILD_SEX_COL = "ChildSex";


    // ADDED BY POP COLUMN FOR SERVICE CENTER  TABLE


    public static final String SERVICE_CENTER_CODE_COL = "SrvCenterCode";
    public static final String SERVICE_CENTER_NAME_COL = "SrvCenterName";
    public static final String SERVICE_CENTER_ADDRESS_COL = "SrvCenterAddress";
    public static final String SERVICE_CENTER_CAT_COL = "SrvCenterCatCode";
    //  public static final String FDP_CODE_COL = "FDPcode";
    //public static final String SERVICE_CENTER_ADDRESS_COL ="SrvCenterAddress";

    // special condition constant
    public static final String SERVICE_SUMMARY_CRITERIA_QUERY = "serviceSummaryCriteria";
    public static final String GRADUATION_PROGRAM_QUERY = "graduationProgramQuery";

    // / ADDED BY POP COLUMN FOR REG_N_PW  TABLE


    public static final String PW_GRD_DATE_COL = "PWGRDDate";
    public static final String LMP_DATE_COL = "LMPDate";

    // ADDED BY POP COLUMN FOR REG_N_CA2  TABLE

    public static final String CA2_GRD_DATE_COL = "CA2GRDDate";
    public static final String CA2DOB_DATE_COL = "CA2Dob";

    // ADDED BY POP COLUMN FOR REG_N_CU2  TABLE


    public static final String CU2_GRD_DATE_COL = "CU2GRDDate";
    public static final String CU2DOB_DATE_COL = "CU2Dob";


    // ADDED BY POP COLUMN FOR STAFF_GEO_INFO_ACCESS_TABLE


    public static final String STAFF_CODE_COL = "StfCode";
    public static final String LAYR_LIST_CODE_COL = "LayRListCode";
    public static final String BTN_NEW_COL = "btnNew";
    public static final String BTN_SAVE_COL = "btnSave";
    public static final String BTN_DEL_COL = "btnDel";
    public static final String BTN_PEPR_COL = "btnPepr";

    public static final String BTN_APRV_COL = "btnAprv";
    public static final String BTN_REVW_COL = "btnRevw";
    public static final String BTN_VRFY_COL = "btnVrfy";
    public static final String BTN_DTRAN_COL = "btnDTran";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String CATEGORY_CODE_COL = "CategoryCode";
    public static final String CATEGORY_NAME_COL = "CategoryName";


    // ADDED BY POP COLUMN FOR HOUSE_HOLD_CATEGORIES


    public static final String REPORT_LABLE_COL = "ReportLable";
    public static final String REPORT_CODE_COL = "ReportCode";

    // ADDED BY POP COLUMN FOR CARD_PRINT_REASON_TABLE

    public static final String CARD_PRINT_REASON_TITLE_COL = "ReasonTitle";
    public static final String CARD_PRINT_REASON_CODE_COL = "ReasonCode";

    // ADDED BY POP COLUMN FOR MEMBER_CARD_PRINT_TABLE

    public static final String REPORT_GROUP_COL = "ReportGroup";
    public static final String CARD_REQUEST_SL_COL = "RequestSl";
    public static final String REQUEST_DATE_COL = "RequestDate";
    public static final String PRINT_DATE_COL = "PrintDate";
    public static final String PRINT_BY_COL = "PrintBy";
    public static final String DELIVERY_DATE_COL = "DeliveryDate";
    public static final String DELIVERY_BY_COL = "DeliveryBy";
    public static final String DELIVERY_STATUS_COL = "DeliveryStatus";

    // COLOUMN FOR REGN_CT TABLE


    public static final String C11_CT_PR = "C11_CT_PR";
    public static final String C21_CT_PR = "C21_CT_PR";
    public static final String C31_CT_PR = "C31_CT_PR";
    public static final String C32_CT_PR = "C32_CT_PR";
    public static final String C33_CT_PR = "C33_CT_PR";
    public static final String C34_CT_PR = "C34_CT_PR";
    public static final String C35_CT_PR = "C35_CT_PR";
    public static final String C36_CT_PR = "C36_CT_PR";
    public static final String C37_CT_PR = "C37_CT_PR";
    public static final String C38_CT_PR = "C38_CT_PR";


    public static final String STAFF_FDP_ACCESS_TABLE = "StaffFdpAccess";
    public static final String FDP_MASTER_TABLE = "FDPMaster";
    public static final String FDP_CODE_COL = "FDPCode";
    public static final String FDP_NAME_COL = "FDPName";
    public static final String FDA_CATEGORIES_CODE_COL = "FDPCatCode";
    public static final String WH_CODE_COL = "WHCode";

    // RegN_ARG Column
    public static final String ELDERLY_YN_COL = "ElderlyYN";
    public static final String LAND_SIZE_COL = "LandSize";
    public static final String DEPEND_ON_GANYU_COL = "DependOnGanyu";
    public static final String WILLINGNESS_COL = "Willingness";
    public static final String WINTER_CULTIVATION_COL = "WinterCultivation";
    public static final String VULNERABLE_HH_COL = "VulnerableHH";
    public static final String PLANTING_VALUE_CHAIN_CROP_COL = "PlantingValueChainCrop";
    // RegN_vul Column
    public static final String Disabled_YN_COL = "DisabledYN";

    // LUP_SrvOptionList TABLE COLUMN
    public static final String LUP_OPTION_CODE_COL = "LUPOptionCode";
    public static final String LUP_OPTION_NAME_COL = "LUPOptionName";

    ////////////////SPECIAL QURY----------
    public static final String VILLAGE_TABLE_QUERY_FROM_REG = "villageQuery";
    public static final String VILLAGE_TABLE_QUERY_FOR_RECORDS = "villageQueryForRecords";


    public static final String DISTRIBUTION_TABLE = "Distribution";
    public static final String DISTRIBUTION_STATUS_COL = "DistributionStatus";
    public static final String MEM_ID_15_D_COL = "nId";
    public static final String DIST_DATE_COL = "DistDate";

    public static final String DIST_FLAG_COL = "DistFLAG";

    public static final String DIST_OP_MONTH_CODE_COL = "DisOpMonthCode";
    public static final String ORG_CODE_COL = "OrgCode";
    public static final String ORG_N_DESG_N_CODE_COL = "OrgNDesgNCode";
    public static final String DISTRIBUTOR_COL = "Distributor";
    public static final String DISTRIBUTION_DATE_COL = "DistributionDate";
    public static final String PREPARED_BY_COL = "PreparedBy";
    public static final String VERIFIED_BY_COL = "VerifiedBy";
    public static final String APPROVED_BY_COL = "ApproveBy";


    // Voucher item table column
    public static final String ITEM_CODE_COL = "ItmCode";
    public static final String ITEM_NAME_COL = "ItmName";


    // Voucher item  meas table column
    public static final String MEAS_R_CODE_COL = "MeasRCode";
    public static final String UNITE_MEAS_COL = "UnitMeas";
    public static final String MEASE_TITLE_COL = "MeasTitle";


    public static final String VOUCHER_ITEM_SPEC_COL = "VOItmSpec";
    public static final String UNITE_COST_COL = "UnitCost";
    public static final String ACTIVE_COL = "Active";
    public static final String CURRENCY_COL = "Currency";
    public static final String VOUCHER_UNIT_COL = "VOItmUnit";
    public static final String VOUCHER_REFERENCE_NUMBER_COL = "VORefNumber";
    public static final String VOUCHER_ITEM_COST_COL = "VOItmCost";

    public static final String ATTRIBUTE_CODE_COL = "AttributeCode";
    public static final String ATTRIBUTE_TITLE_COL = "AttributeTitle";
    public static final String ATTRIBUTE_VALUE_COL = "AttributeValue";
    public static final String ATTRIBUTE_PHOTO_COL = "AttPhoto";
    public static final String DATA_TYPE_COL = "DataType";
    //  public static final String LOOKUP_TABLE_NAME_COL = "LookUpCode";
    public static final String LOOK_UP_CODE_COL = "LookUpCode";
    public static final String LOOK_UP_NAME_COL = "LookUpName";
    /**
     * For getListAndID() methods when the table name already use
     */
    public static final String CUSTOM_QUERY = "CustomQuery";
    /**
     * for Service Specification Coloumn
     */
    public static final String BABY_STATUS_COL = "BabyStatus";
    public static final String GMP_ATTENDACE_COL = "GMPAttendace";
    public static final String WEIGHT_STATUS_COL = "WeightStatus";
    public static final String NUT_ATTENDANCE_COL = "NutAttendance";
    public static final String VITA_UNDER5_COL = "VitA_Under5";
    public static final String EXCLUSIVE_CURRENTLYBF_COL = "Exclusive_CurrentlyBF";
    public static final String DATE_COMPFEEDING_COL = "DateCompFeeding";
    public static final String CMAMREF_COL = "CMAMRef";
    public static final String CMAMADD_COL = "CMAMAdd";
    public static final String ANCVISIT_COL = "ANCVisit";
    public static final String PNCVISIT_2D_COL = "PNCVisit_2D";
    public static final String PNCVISIT_1W_COL = "PNCVisit_1W";
    public static final String PNCVISIT_6W_COL = "PNCVisit_6W";
    public static final String DELIVERY_STAFF_1_COL = "DeliveryStaff_1";
    public static final String DELIVERY_STAFF_2_COL = "DeliveryStaff_2";
    public static final String DELIVERY_STAFF_3_COL = "DeliveryStaff_3";
    public static final String HOME_SUPPORT24H_1D_COL = "HomeSupport24H_1d";
    public static final String HOME_SUPPORT24H_2D_COL = "HomeSupport24H_2d";
    public static final String HOME_SUPPORT24H_3D_COL = "HomeSupport24H_3d";
    public static final String HOME_SUPPORT24H_8D_COL = "HomeSupport24H_8d";
    public static final String HOME_SUPPORT24H_14D_COL = "HomeSupport24H_14d";
    public static final String HOME_SUPPORT24H_21D_COL = "HomeSupport24H_21d";
    public static final String HOME_SUPPORT24H_30D_COL = "HomeSupport24H_30d";
    public static final String HOME_SUPPORT24H_60D_COL = "HomeSupport24H_60d";
    public static final String HOME_SUPPORT24H_90D_COL = "HomeSupport24H_90d";
    public static final String HOME_SUPPORT48H_1D_COL = "HomeSupport48H_1d";
    public static final String HOME_SUPPORT48H_3D_COL = "HomeSupport48H_3d";
    public static final String HOME_SUPPORT48H_8D_COL = "HomeSupport48H_8d";
    public static final String HOME_SUPPORT48H_30D_COL = "HomeSupport48H_30d";
    public static final String HOME_SUPPORT48H_60D_COL = "HomeSupport48H_60d";
    public static final String HOME_SUPPORT48H_90D_COL = "HomeSupport48H_90d";
    public static final String MATERNAL_BLEEDING_COL = "Maternal_Bleeding";
    public static final String MATERNAL_SEIZURE_COL = "Maternal_Seizure";
    public static final String MATERNAL_INFECTION_COL = "Maternal_Infection";
    public static final String MATERNAL_PROLONGEDLABOR_COL = "Maternal_ProlongedLabor";
    public static final String MATERNAL_OBSTRUCTEDLABOR_COL = "Maternal_ObstructedLabor";
    public static final String MATERNAL_PPRM_COL = "Maternal_PPRM";
    public static final String NBORN_ASPHYXIA_COL = "NBorn_Asphyxia";
    public static final String NBORN_SEPSIS_COL = "NBorn_Sepsis";
    public static final String NBORN_HYPOTHERMIA_COL = "NBorn_Hypothermia";
    public static final String NBORN_HYPERTHERMIA_COL = "NBorn_Hyperthermia";
    public static final String NBORN_NOSUCKLING_COL = "NBorn_NoSuckling";
    public static final String NBORN_JAUNDICE_COL = "NBorn_Jaundice";
    public static final String CHILD_DIARRHEA_COL = "Child_Diarrhea";
    public static final String CHILD_PNEUMONIA_COL = "Child_Pneumonia";
    public static final String CHILD_FEVER_COL = "Child_Fever";
    public static final String CHILD_CEREBRALPALSY_COL = "Child_CerebralPalsy";
    public static final String IMMU_POLIO_COL = "Immu_Polio";
    public static final String IMMU_BCG_COL = "Immu_BCG";
    public static final String IMMU_MEASLES_COL = "Immu_Measles";
    public static final String IMMU_DPT_HIB_COL = "Immu_DPT_HIB";
    public static final String IMMU_LOTTA_COL = "Immu_Lotta";
    public static final String IMMU_OTHER_COL = "Immu_Other";
    public static final String FPCOUNSEL_MALECONDOM_COL = "FPCounsel_MaleCondom";
    public static final String FPCOUNSEL_FEMALECONDOM_COL = "FPCounsel_FemaleCondom";
    public static final String FPCOUNSEL_PILL_COL = "FPCounsel_Pill";
    public static final String FPCOUNSEL_DEPO_COL = "FPCounsel_Depo";
    public static final String FPCOUNSEL_LONGPARMANENT_COL = "FPCounsel_LongParmanent";
    public static final String FPCOUNSEL_NOMETHOD_COL = "FPCounsel_NoMethod";
    public static final String CROP_CODE_COL = "CropCode";
    public static final String LOAN_SOURCE_COL = "LoanSource";
    public static final String LOAN_AMT_COL = "LoanAMT";
    public static final String ANIMAL_CODE_COL = "AnimalCode";
    public static final String LEAD_CODE_COL = "LeadCode";
    /**
     * For Group Community table
     */
    public static final String GROUP_CAT_CODE_COL = "GrpCatCode";
    /**
     * For Group Community Categories table
     */
    public static final String GROUP_CAT_NAME_COL = "GrpCatName";
    public static final String GROUP_CAT_SHORT_NAME_COL = "GrpCatShortName";


    /**
     * LUP_PROG_GROUP_CROP_TABLE FOR COLMN
     */
    public static final String CROP_NAME_COL = "CropList";
    public static final String CROP_CAT_COL = "CropCatCode";


    /**
     * LUP_COMMUNITY_ANIMAL_TABLE FOR COLMN
     */
    public static final String ANIMAL_TYPE_COL = "AnimalType";

    /**
     * LUP_COMMUNITY_LOAN_SOURCE_TABLE FOR COLMN
     */
    public static final String LOAN_CODE_COL = "LoanCode";

    /**
     * LUP_COMMUNITY_LEAD_POSITION_TABLE FOR COLMN
     */
    public static final String LEAD_POSITION_COL = "LoanCode";


    public static final String CONTENT_CODE_COL = "ContentCode";
    public static final String IMAGE_FILE_COL = "ImageFile";
    public static final String REMARKES_COL = "Remarks";


    public static final String ORPHAN_CHILDREN_COL = "OrphanedChildren";
    public static final String CHILD_HEADED_COL = "ChildHeaded";
    public static final String ELDERLY_HEADED_COL = "ElderlyHeaded";
    public static final String CHRONICALLY_ILL_COL = "ChronicallyIll";
    public static final String CROP_FAILURE_COL = "CropFailure";
    public static final String FEMALE_HEADED_COL = "FemaleHeaded";
    public static final String CHILDREN_REC_SUPP_FEED_N_COL = "ChildrenRecSuppFeedN";


    //public static boolean is_online = false;


//    private ArrayList<dataDB> apps = new ArrayList<dataDB>();

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create Upload  table
        db.execSQL(Schema.sqlCreateUploadTable());

        // create User Login table
        db.execSQL(Schema.sqlCreateUserLoginTable());
        // STAFF  FOR ADMIN

        db.execSQL(Schema.sqlCreateStaffMasterTable());

        // create Country Table
        db.execSQL(Schema.sqlCreateCountry());

        // create Layer Label
        db.execSQL(Schema.sqlCreateLayerLabel());


        // create District Table
        db.execSQL(Schema.sqlCreateDistrict());


        // create Upazilla Table
        db.execSQL(Schema.sqlCreateUpazilla());


        // create Unit Table
        db.execSQL(sqlCreateUnit());


        // create Village Table
        db.execSQL(Schema.sqlCreateVillage());


        // create Valid Date Range Table
        db.execSQL(Schema.sqlCreateDateRange());


        // create Registration Table
        db.execSQL(Schema.sqlCreateRegistration());


        // create Relation Table
        db.execSQL(Schema.sqlCreateRegRelation());


        // create Member Table
        db.execSQL(Schema.sqlCreateRegMember());


        // create Service Table
        db.execSQL(Schema.sqlCreateServiceTable());


        db.execSQL(Schema.sqlCreateCountryAwardTable());


        db.execSQL(Schema.sqlCreateDonorTable());


        db.execSQL(Schema.sqlCreateProgramMasterTable());


        db.execSQL(Schema.sqlCreateServiceMasterTable());


        db.execSQL(Schema.sqlCreateRegNAssignPrgSrvTable());


        db.execSQL(Schema.sqlCreateGpsGroupTable());


        db.execSQL(Schema.sqlCreateGpsSubGroupTable());


        db.execSQL(Schema.sqlCreateGpsLocationTable());


        db.execSQL(Schema.sqlCreateOpMonthTable());


        db.execSQL(Schema.sqlCreateADM_CountryProgram());


        db.execSQL(Schema.sqlCreateRegNLMTable());


        db.execSQL(Schema.sqlCreateServiceCenterTable());


        // create dob Reg-N-PW Table -- ADDED BY POP REMARKS
        db.execSQL(Schema.sqlCreateRegNPWTable());


        db.execSQL(Schema.sqlCreateRegNCU2Table());


        // create dob Reg-N-CA2 Table --
        db.execSQL(Schema.sqlCreateRegNCA2Table());


        // create dob StaffGeoInfoAccess Table --
        db.execSQL(Schema.sqlCreateStaffGeoInfoAccessTable());


        // create dob StaffGeoInfoAccess Table --
        db.execSQL(Schema.sqlCreateHouseHoldCategoryTable());

        // TO MARGE THE TABEL DELETE THE CODE
        // todo  delete LIBERIA_REGISTRATION_TABLE
        db.execSQL(Schema.sqlCreateLiberiaRegistrationTable());
//        Log.d(TAG, " LiberiaRegistration created");

        db.execSQL(Schema.sqlCreateGraduationTable());


        db.execSQL(Schema.sqlCreateCardTypeTable());


        db.execSQL(Schema.sqlCreateCardPrintReasonTable());


        db.execSQL(Schema.sqlCreateRegMemberCardPrintTable());

        db.execSQL(Schema.sqlCreate_RegN_CT_Table());


        db.execSQL(Schema.sqlCreateStaffFDPAccessTable());
//        Log.d(TAG, " sql Create StaffFDPAccess Table ");
        db.execSQL(Schema.sqlCreateFDP_Master_Table());
//        Log.d(TAG, "  Create FDP_Master_Table ");

        db.execSQL(Schema.sqlCreateDistributionTable());
//        Log.d(TAG, "  Create Distribution Table ");

        db.execSQL(Schema.sqlCreateRegN_AGR_Table());
//        Log.d(TAG, "  Create RegNArg Table ");

        db.execSQL(Schema.sqlCreateLUP_SrvOptionList());
//        Log.d(TAG, "  Create LUP_SrvOptionList Table ");

        db.execSQL(Schema.sqlCreateRegNVUL_Table());
//        Log.d(TAG, "  Create RegN Vul Table ");

        db.execSQL(Schema.sqlCreateVoucherItem_Table());
//        Log.d(TAG, "  Create Voucher Item Table ");


        db.execSQL(Schema.sqlCreateVoucherItemMeas_Table());
//        Log.d(TAG, "  Create VoucherItemMeas_Table ");

        db.execSQL(Schema.sqlCreateVoucherCountryProgItem_Table());
//        Log.d(TAG, "  Create VoucherCountryProgItem_Table ");


        db.execSQL(Schema.sqlCreateServiceExtended_Table());
//        Log.d(TAG, "  Create sqlCreateServiceExtended_Table_Table ");


        db.execSQL(Schema.sqlCreateDistributionExtended_Table());
//        Log.d(TAG, "  Create sqlCreateDistributionExtended_Table ");

        db.execSQL(Schema.sqlCreateSelectedVillage_Table());
//        Log.d(TAG, "  Create sqlCreateSelected Village_Table ");

        db.execSQL(Schema.sqlCreateSelectedFDP_Table());


        db.execSQL(Schema.sqlCreateSelectedServiceCenter_Table());

        db.execSQL(Schema.sqlCreateCommunityGroup_Table());

        db.execSQL(Schema.sqlCreateGPSSubGroupAttributes_Table());

        db.execSQL(Schema.sqlCreateLUP_GPS_Table());

        db.execSQL(Schema.sqlCreateGPSLocationAttributes_Table());

        db.execSQL(Schema.sqlCreateServiceSpecification_Table());

        db.execSQL(Schema.sqlCreateLUP_CommunityAnimalList_Table());

        db.execSQL(Schema.sqlCreateLUP_ProgramGroupCrop_Table());

        db.execSQL(Schema.sqlCreateLUP_CommunityLoanSource_Table());

        db.execSQL(Schema.sqlCreateLUP_CommunityLeadPosition_Table());

        db.execSQL(Schema.sqlCreateRegNmemProgGrp_Table());

        db.execSQL(Schema.sqlCreateCommunityGroupCategoryes_Table());

        db.execSQL(Schema.sqlCreate_Gps_Location_Content_Table());

        db.execSQL(Schema.lastSyncTime());
        db.execSQL(Schema.createTableRegN_FFA());

        db.execSQL(Schema.sqlCreateDistNPlanBasic());
        db.execSQL(Schema.createTableLUP_RegNAddLookup());
        db.execSQL(Schema.createTableCommunityGrpDetail());
        db.execSQL(Schema.createTableProgOrgNRole());
        db.execSQL(Schema.createTableProgOrgN());

        db.execSQL(Schema.sqlCreateLUP_GpsList());


        db.execSQL(Schema.createTableDTATable());
        db.execSQL(Schema.createTableDTBasic());
        db.execSQL(Schema.createTableDTCategory());
        db.execSQL(Schema.createTableDTCountryProgram());
        db.execSQL(Schema.createTableDTGeoListLevel());
        db.execSQL(Schema.createTableDTQResMode());
        db.execSQL(Schema.createTableDTQTable());
        db.execSQL(Schema.createTableDTResponseTable());
        db.execSQL(Schema.createTableDTTableDefinition());
        db.execSQL(Schema.createTaleDTTableListCategory());


        Log.d(TAG, "  Create All Table ");

        //db.close();
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshDatabase();
    }

    public void refreshDatabase() {

        SQLiteDatabase db = this.getWritableDatabase();

        Log.d(TAG, "Dropping all table..");

        // Drop older table if existed
        try {
            db.execSQL(DROP_TABLE_IF_EXISTS + LOGIN_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VALID_DATE_RANGE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRICT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + UPAZILLA_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + UNIT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VILLAGE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REGISTRATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REGISTRATION_MEMBER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + RELATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_AWARD_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + ADM_DONOR_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_ASSIGN_PROG_SRV_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + OP_MONTH_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_PW_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CU2_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CA2_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_GEO_INFO_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + HOUSE_HOLD_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LIBERIA_REGISTRATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_LUP_GRADUATION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REPORT_TEMPLATE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + CARD_PRINT_REASON_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + MEMBER_CARD_PRINT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + UPLOAD_SYNTAX_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + FDP_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_FDP_ACCESS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_CT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_AGR_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_VUL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_CENTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LAYER_LABEL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_ITEM__MEAS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_EXTENDED_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DISTRIBUTION_EXTENDED_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SELECTED_VILLAGE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_SUB_GROUP_ATTRIBUTES_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_ATTRIBUTES_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + SERVICE_SPECIFIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_ANIMAL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_PROG_GROUP_CROP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LOAN_SOURCE_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_COMMUNITY_LEAD_POSITION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_MEM_PROG_GRP_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GROUP_CATEGORY_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + GPS_LOCATION_CONTENT_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + REG_N_FFA_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DIST_N_PLAN_BASIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + COMMUNITY_GRP_DETAIL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_NAME_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + PROGRAM_ORGANIZATION_ROLE_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + STAFF_MASTER_TABLE);

            db.execSQL(DROP_TABLE_IF_EXISTS + LUP_GPS_LIST_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_A_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_BASIC_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_CATEGORY_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_COUNTRY_PROGRAM_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_COL);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTGEO_LIST_LEVEL_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQRES_MODE_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTQ_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_RESPONSE_TABLE_COL);
            db.execSQL(DROP_TABLE_IF_EXISTS + DT_TABLE_DEFINITION_TABLE);
            db.execSQL(DROP_TABLE_IF_EXISTS + DTTABLE_LIST_CATEGORY_TABLE);


            Log.d(TAG, "All table Dropped.");
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        // Create tables again
        onCreate(db);
    }

    /**
     * Re crate database Delete all tables and create them again
     */
    public void deleteReferenceTable() {

        SQLiteDatabase db = this.getWritableDatabase();

        Log.d(TAG, "Deleting all user data..");

        try {

            db.delete(COUNTRY_TABLE, null, null);
            db.delete(VALID_DATE_RANGE, null, null);
            db.delete(DISTRICT_TABLE, null, null);
            db.delete(UPAZILLA_TABLE, null, null);
            db.delete(UNIT_TABLE, null, null);
            db.delete(VILLAGE_TABLE, null, null);
            db.delete(RELATION_TABLE, null, null);
            /**
             * todo do not delete AWARd Table program table Service Table
             */
            db.delete(ADM_AWARD_TABLE, null, null);
            db.delete(ADM_DONOR_TABLE, null, null);
            db.delete(PROGRAM_MASTER_TABLE, null, null);
            db.delete(SERVICE_MASTER_TABLE, null, null);
            db.delete(GPS_GROUP_TABLE, null, null);
            db.delete(GPS_SUB_GROUP_TABLE, null, null);
            // db.delete(GPS_LOCATION_TABLE, null, null);
            db.delete(OP_MONTH_TABLE, null, null);
            db.delete(COUNTRY_PROGRAM_TABLE, null, null);
            db.delete(SERVICE_CENTER_TABLE, null, null);
            db.delete(STAFF_GEO_INFO_ACCESS_TABLE, null, null);
            db.delete(HOUSE_HOLD_CATEGORY_TABLE, null, null);
            db.delete(REG_N_LUP_GRADUATION_TABLE, null, null);
            db.delete(LAYER_LABEL_TABLE, null, null);
            db.delete(REPORT_TEMPLATE_TABLE, null, null);
            db.delete(CARD_PRINT_REASON_TABLE, null, null);
            db.delete(FDP_MASTER_TABLE, null, null);
            db.delete(STAFF_FDP_ACCESS_TABLE, null, null);
            db.delete(LUP_SRV_OPTION_LIST_TABLE, null, null);
            db.delete(SERVICE_TABLE, null, null);
            db.delete(SERVICE_EXTENDED_TABLE, null, null);


            Log.d(TAG, "All Reference data Deleted.");
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        onCreate(db);
    }

    public void deleteUsersWithSelectedVillage() {
        deleteUsers();
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(SELECTED_VILLAGE_TABLE, null, null);/// Delete selected Village TABLE
        database.delete(SELECTED_FDP_TABLE, null, null);/// Delete selected Village TABLE
        database.delete(SELECTED_SERVICE_CENTER_TABLE, null, null);/// Delete selected Village TABLE

    }

    /**
     * Re crate database Delete all tables and create them again
     * todo optimize code
     */
    public void deleteUsers() {

        SQLiteDatabase db = this.getWritableDatabase();

        Log.d(TAG, "Deleting all user data..");

        try {
            // Delete All Rows
            db.delete(LOGIN_TABLE, null, null);
            db.delete(COUNTRY_TABLE, null, null);
            db.delete(VALID_DATE_RANGE, null, null);
            db.delete(DISTRICT_TABLE, null, null);
            db.delete(UPAZILLA_TABLE, null, null);
            db.delete(UNIT_TABLE, null, null);
            db.delete(VILLAGE_TABLE, null, null);
            db.delete(REGISTRATION_TABLE, null, null);
            db.delete(REGISTRATION_MEMBER_TABLE, null, null);
            db.delete(RELATION_TABLE, null, null);
            db.delete(SERVICE_TABLE, null, null);
            db.delete(ADM_AWARD_TABLE, null, null);
            db.delete(ADM_DONOR_TABLE, null, null);
            db.delete(PROGRAM_MASTER_TABLE, null, null);
            db.delete(SERVICE_MASTER_TABLE, null, null);
            db.delete(REG_N_ASSIGN_PROG_SRV_TABLE, null, null);
            db.delete(GPS_GROUP_TABLE, null, null);
            db.delete(GPS_SUB_GROUP_TABLE, null, null);
            db.delete(GPS_LOCATION_TABLE, null, null);
            db.delete(OP_MONTH_TABLE, null, null);
            db.delete(COUNTRY_PROGRAM_TABLE, null, null);

            db.delete(REG_N_LM_TABLE, null, null);
            db.delete(REG_N_PW_TABLE, null, null);
            db.delete(REG_N_CU2_TABLE, null, null);
            db.delete(REG_N_CA2_TABLE, null, null);

            db.delete(STAFF_GEO_INFO_ACCESS_TABLE, null, null);
            db.delete(HOUSE_HOLD_CATEGORY_TABLE, null, null);
            db.delete(LIBERIA_REGISTRATION_TABLE, null, null);
            db.delete(REG_N_LUP_GRADUATION_TABLE, null, null);
            db.delete(REPORT_TEMPLATE_TABLE, null, null);
            db.delete(CARD_PRINT_REASON_TABLE, null, null);
            db.delete(MEMBER_CARD_PRINT_TABLE, null, null);
            db.delete(UPLOAD_SYNTAX_TABLE, null, null);
            db.delete(FDP_MASTER_TABLE, null, null);
            db.delete(STAFF_FDP_ACCESS_TABLE, null, null);
            db.delete(REG_N_CT_TABLE, null, null);
            db.delete(DISTRIBUTION_TABLE, null, null);
            db.delete(REG_N_AGR_TABLE, null, null);
            db.delete(REG_N_VUL_TABLE, null, null);
            db.delete(SERVICE_CENTER_TABLE, null, null);
            db.delete(LAYER_LABEL_TABLE, null, null);
            db.delete(VOUCHER_ITEM_TABLE, null, null);
            db.delete(VOUCHER_ITEM__MEAS_TABLE, null, null);
            db.delete(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, null);

            db.delete(SERVICE_EXTENDED_TABLE, null, null);
            db.delete(DISTRIBUTION_EXTENDED_TABLE, null, null);

            db.delete(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, null);

            db.delete(LUP_GPS_TABLE, null, null);

            db.delete(GPS_LOCATION_ATTRIBUTES_TABLE, null, null);
            db.delete(SERVICE_SPECIFIC_TABLE, null, null);
            db.delete(COMMUNITY_GROUP_TABLE, null, null);
            db.delete(LUP_COMMUNITY_ANIMAL_TABLE, null, null);
            db.delete(LUP_PROG_GROUP_CROP_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, null);
            db.delete(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, null);

            db.delete(REG_N_MEM_PROG_GRP_TABLE, null, null);
            db.delete(COMMUNITY_GROUP_CATEGORY_TABLE, null, null);
            db.delete(GPS_LOCATION_CONTENT_TABLE, null, null);
            db.delete(REG_N_FFA_TABLE, null, null);
            db.delete(DIST_N_PLAN_BASIC_TABLE, null, null);
            db.delete(COMMUNITY_GRP_DETAIL_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_NAME_TABLE, null, null);
            db.delete(PROGRAM_ORGANIZATION_ROLE_TABLE, null, null);
            db.delete(STAFF_MASTER_TABLE, null, null);
            db.delete(LUP_GPS_LIST_TABLE, null, null);

            db.delete(DT_A_TABLE, null, null);
            db.delete(DT_BASIC_TABLE, null, null);
            db.delete(DT_CATEGORY_TABLE, null, null);
            db.delete(DT_COUNTRY_PROGRAM_TABLE, null, null);
            db.delete(DTGEO_LIST_LEVEL_TABLE, null, null);
            db.delete(DTQRES_MODE_TABLE, null, null);
            db.delete(DTQ_TABLE, null, null);
            db.delete(DT_RESPONSE_TABLE_COL, null, null);
            db.delete(DT_TABLE_DEFINITION_TABLE, null, null);
            db.delete(DTTABLE_LIST_CATEGORY_TABLE, null, null);


            Log.d(TAG, "All User data Deleted.");
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getMessage());
        }

        //db.close();

        onCreate(db);
    }

    /**
     * @param grpCode     Group Code
     * @param subGrpCode  Sub Group Code
     * @param locCode     Location  Code
     * @param contentCode content Code
     * @param imageFile   imge in byte array
     * @param remarks     remarks
     * @param entryBy     entryBy
     * @param entryDate   entryDate
     */


    public void insertIntoGPSLocationContentTable(String AdmCountryCode, String grpCode, String subGrpCode, String locCode
            , String contentCode, byte[] imageFile, String remarks, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, AdmCountryCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(LOCATION_CODE_COL, locCode);
        values.put(CONTENT_CODE_COL, contentCode);
        values.put(IMAGE_FILE_COL, imageFile);
        values.put(REMARKES_COL, remarks);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        long id = db.insert(GPS_LOCATION_CONTENT_TABLE, null, values);
        db.close();
        Log.d(TAG, "NEW Insert into GPSLocationContent Table: " + id);
    }

    public void insertIntoLupGpsList(String grpCode, String subGrpCode, String attbuteCode, String lup_valueCode, String lup_value_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(GROUP_CODE_COL, grpCode);
        values.put(SUB_GROUP_CODE_COL, subGrpCode);
        values.put(ATTRIBUTE_CODE_COL, attbuteCode);
        values.put(LUP_VALUE_CODE_COL, lup_valueCode);
        values.put(LUP_VALUE_TEXT_COL, lup_value_text);

        long id = db.insert(LUP_GPS_LIST_TABLE, null, values);
        db.close();
//        Log.d(TAG, "Insert into " + STAFF_MASTER_TABLE + " Table: " + id);


    }


    public void insertIntoStaffMasterTable(String staffId, String cCode, String staffName, String orgCode, String orgNDesgCode
            , String staffStatus, String staffCat, String userName, String userPass, String staffAdimRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(STAFF_ID_COL, staffId);
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(STAFF_NAME_COL, staffName);
        values.put(ORG_CODE_COL, orgCode);
        values.put(ORG_N_DESG_N_CODE_COL, orgNDesgCode);
        values.put(STAFF_STATUS_COL, staffStatus);
        values.put(STAFF_CATEGORY_COL, staffCat);
        values.put(USER_LOGIN_NAME, userName);
        values.put(USER_LOGIN_PW, userPass);
        values.put(STAFF_ADMIN_ROLE_COL, staffAdimRole);
        long id = db.insert(STAFF_MASTER_TABLE, null, values);
        db.close();
        Log.d(TAG, "Insert into " + STAFF_MASTER_TABLE + " Table: " + id);
    }

    public static final String TYPE_OF_GROUP = "TypeOfGroup";

    public void addIntoGroupDetails(String AdmCountryCode, String donorCode
            , String awardCode, String progCode, String grpCode
            , String ogrCode, String staffCode, String landSizeUnder, String iirigrationSysUsed, String fundSuppot
            , String active, String reapName, String reapPhone, String formation, String typeOfGrp
            , String status, String entryBy, String entryDate, String projecftNo, String projectTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, AdmCountryCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, grpCode);
        values.put(ORG_CODE_COL, ogrCode);
        values.put(STAFF_CODE_COL, staffCode);
        values.put(LAND_SIZE_UNDER_IRRIGATION_COL, landSizeUnder);
        values.put(IRRIGATION_SYSTEM_USED_COL, iirigrationSysUsed);
        values.put(FUND_SUPPORT_COL, fundSuppot);
        values.put(ACTIVE_COL, active);
        values.put(REP_NAME_COL, reapName);
        values.put(REP_PHONE_NUMBER_COL, reapPhone);
        values.put(FORMATION_DATE_COL, formation);
        values.put(TYPE_OF_GROUP, typeOfGrp);
        values.put(STATUS, status);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(PROJECT_NO_COL, projecftNo);
        values.put(PROJECT_TITLE, projectTitle);
        long id = db.insert(COMMUNITY_GRP_DETAIL_TABLE, null, values);
        db.close();
        Log.d(TAG, "NEW Insert into " + COMMUNITY_GRP_DETAIL_TABLE + " Table: " + id);
    }

    /**
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param progCode
     * @param groupCatCode
     * @param groupCatName
     * @param groupCatShortName
     */

    public void addCommunityGroupCategoryFromOnline(String cCode, String donorCode, String awardCode, String progCode, String groupCatCode, String groupCatName
            , String groupCatShortName) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);


        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);


        values.put(PROGRAM_CODE_COL, progCode);
        values.put(GROUP_CAT_CODE_COL, groupCatCode);
        values.put(GROUP_CAT_NAME_COL, groupCatName);

        values.put(GROUP_CAT_SHORT_NAME_COL, groupCatShortName);


        long id = db.insert(COMMUNITY_GROUP_CATEGORY_TABLE, null, values);
        db.close();
    }

    /**
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param disttCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhID
     * @param memID
     * @param progCode
     * @param srvCode
     * @param grpCode
     * @param active
     */

    public void addRegNmemProgGroupFromOnline(String cCode, String donorCode, String awardCode, String disttCode, String upCode
            , String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode, String grpCode, String active) {

        addRegNmemProgGroup(cCode, donorCode, awardCode, disttCode, upCode, unCode, vCode, hhID, memID, progCode, srvCode, grpCode, active, "", "");
    }

    // add LUP_AnimalType list

    /**
     * this method will need ed in Assigne Main Page also
     *
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param disttCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhID
     * @param memID
     * @param progCode
     * @param srvCode
     * @param grpCode
     * @param active
     * @param entryBy
     * @param entryDate
     */

    public void addRegNmemProgGroup(String cCode, String donorCode, String awardCode, String disttCode, String upCode
            , String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode, String grpCode, String active, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DISTRICT_CODE_COL, disttCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);

        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(HHID_COL, hhID);
        values.put(HH_MEM_ID, memID);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, srvCode);
        values.put(GROUP_CODE_COL, grpCode);

        values.put(ACTIVE_COL, active);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        long id = db.insert(REG_N_MEM_PROG_GRP_TABLE, null, values);
        db.close();


    }


    // add LUP_AnimalType list

    public void addLUP_AnimalTypeFromOnline(String cCode, String donorCode, String awardCode, String progCode, String animalCode, String animalType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(ANIMAL_TYPE_COL, animalType);


        long id = db.insert(LUP_COMMUNITY_ANIMAL_TABLE, null, values);
        db.close();


    }


    // add LUP_ProgramGroupCrop list

    public void addLUP_ProgramGroupCropFromOnLine(String cCode, String donorCode, String awardCode,
                                                  String progCode, String cropCode, String corpName, String cropCatCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(CROP_CODE_COL, cropCode);
        values.put(CROP_NAME_COL, corpName);
        values.put(CROP_CAT_COL, cropCatCode);


        long id = db.insert(LUP_PROG_GROUP_CROP_TABLE, null, values);
        db.close();


    }


    // add LUP_CommunityLoanSource list

    public void addLUP_CommunityLoanSource(String cCode, String donorCode, String awardCode,
                                           String progCode, String loanCode, String loanSource) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(LOAN_CODE_COL, loanCode);
        values.put(LOAN_SOURCE_COL, loanSource);


        long id = db.insert(LUP_COMMUNITY_LOAN_SOURCE_TABLE, null, values);
        db.close();


    }


    // add LUP_CommunityLoanSource list

    public void addLUP_CommunityLeadPostition(String cCode, String donorCode, String awardCode,
                                              String progCode, String leadCode, String leadPosition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(LEAD_POSITION_COL, leadPosition);


        long id = db.insert(LUP_COMMUNITY_LEAD_POSITION_TABLE, null, values);
        db.close();


    }


    // add service Center

    public void addServiceCenter(String cCode, String srvCenCode, String srvCenName, String fdpCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenCode);
        values.put(SERVICE_CENTER_NAME_COL, srvCenName);

        values.put(FDP_CODE_COL, fdpCode); //

        long id = db.insert(SERVICE_CENTER_TABLE, null, values);
        db.close();


    }

    public void insertAdmCountryProgram(String cCode, String donorCode, String awardCode, String programCode, String servCode, String food, String nonFood, String cash, String voucher,
                                        String defaultFoodDays, String defaultNoFoodDays, String defaultCashDays, String defaultVoucharDays, String srvSpecific) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(SERVICE_CODE_COL, servCode);
        values.put(FOOD_FLAG, food);
        values.put(NON_FOOD_FLAG, nonFood);
        values.put(CASH_FLAG, cash);
        values.put(VOUCHER_FLAG, voucher);
        values.put(DEFAULT_FOOD_DAYS_COL, defaultFoodDays);
        values.put(DEFAULT_NO_FOOD_DAYS_COL, defaultNoFoodDays);
        values.put(DEFAULT_CASH_DAYS_COL, defaultCashDays);
        values.put(DEFAULT_VOUCHAR_DAYS_COL, defaultVoucharDays);
        values.put(SERVICE_SPECIFIC_FLAG_COL, srvSpecific);
        // Inserting Row
        long id = db.insert(COUNTRY_PROGRAM_TABLE, null, values);
        db.close(); // Closing database connection

        //    Log.d(TAG, "New location  inserted into COUNTRY_PROGRAM_TABLE Table : " + id);
    }

    /**
     * **********************************************************************
     * INSERT OPERATION FROM HERE
     * ***********************************************************************
     */
     /* @ Faisal Mohammad
    * @date : 2015-09-30*/
    public void addGraduation(String programCode, String serviceCode, String grdCode, String grdTitle,
                              String defaultCatActive, String defaultCatExit) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PROGRAM_CODE_COL, programCode);
        values.put(SERVICE_CODE_COL, serviceCode);
        values.put(GRD_CODE_COL, grdCode);
        values.put(GRD_TITLE_COL, grdTitle);
        values.put(DEFAULT_CAT_ACTIVE_COL, defaultCatActive);
        values.put(DEFAULT_CAT_EXIT_COL, defaultCatExit);

        // many mort ot insert
        // insert
        long id = db.insert(REG_N_LUP_GRADUATION_TABLE, null, values);
        db.close();
        Log.d(TAG, "New REG_N_LUP_GRADUATION_TABLE  added: " + id);

    }


    public RegN_HH_libDataModel getPreviousLayeRListforHouseHold(int pID) {
        SQLiteDatabase db = this.getReadableDatabase();
        RegN_HH_libDataModel LayRList = new RegN_HH_libDataModel();

        String selectQuery = "SELECT " + COUNTRY_CODE + " , "
                + DISTRICT_NAME_COL + " , "
                + UPZILLA_NAME_COL + " , "
                + UNITE_NAME_COL + " , "
                + VILLAGE_NAME_COL +
                //  " , "      +PID_COL+
                "  FROM " + REGISTRATION_TABLE +
                " WHERE " + ID_COL + " = " + pID;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                LayRList.setCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE)));
                LayRList.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                LayRList.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                LayRList.setUnitCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                LayRList.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                // LayRList.setVillageCode(cursor.getString(cursor.getColumnIndex(PID_COL)));
            }
            cursor.close();
        }
        db.close();
        return LayRList;
    }


    public void editRegistrationRecordForLiberia(int pID, RegN_HH_libDataModel data) {

        String where = ID_COL + "=" + pID;


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DISTRICT_NAME_COL, data.getDistrictCode());
        values.put(UPZILLA_NAME_COL, data.getUpazillaCode());
        values.put(UNITE_NAME_COL, data.getUnitCode());
        values.put(VILLAGE_NAME_COL, data.getVillageCode());
        values.put(PID_COL, data.getHhId());
        values.put(REG_DATE_COL, data.getRegNDate());
        values.put(PNAME_COL, data.getHHHeadName());
        values.put(HOUSE_HOLD_TYPE_CODE_COL, data.getHHTypeCode());
        values.put(LT2YRS_M_COL, data.getLT2yrsM());
        values.put(LT2YRS_F_COL, data.getLT2yrsF());
        values.put(M_2TO5YRS_COL, data.getM2to5yers());
        values.put(F_2TO5YRS_COL, data.getF2to5yrs());
        values.put(M_6TO12YRS_COL, data.getM6to12yrs());
        values.put(F_6TO12YRS_COL, data.getF6to12yrs());
        values.put(M_13TO17YRS_COL, data.getM13to17yrs());
        values.put(F_13TO17YRS_COL, data.getF13to17yrs());
        values.put(ORPHN_LT18YRS_M_COL, data.getOrphn_LT18yrsM());
        values.put(ORPHN_LT18YRS_F_COL, data.getOrphn_LT18yrsF());
        values.put(ADLT_18TO59_M_COL, data.getAdlt_18to59M());
        values.put(ADLT_18TO59_F_COL, data.getAdlt_18to59F());
        values.put(ELD_60P_M_COL, data.getEld_60pM());
        values.put(ELD_60P_F_COL, data.getEld_60pF());
        values.put(PLW_NO_COL, data.getPLW());
        values.put(CHRO_ILL_NO_COL, data.getChronicallyIll());
        values.put(DECEASED_CONTRACT_EBOLA_COL, data.getLivingDeceasedContractEbola());
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, data.getExtraChildBecauseEbola());
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, data.getExtraelderlyPersonBecauseEbola());
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, data.getExtraChronicallyIllDisabledPersonBecauseEbola());
        values.put(BRF_COUNT_CATTLE_COL, data.getBrfCntCattle());
        values.put(BRF_VALUE_CATTLE_COL, data.getBrfValCattle());
        values.put(AFT_COUNT_CATTLE_COL, data.getAftCntCattle());
        values.put(AFT_VALUE_CATTLE_COL, data.getAftValCattle());
        values.put(BRF_COUNT_SGOATS_COL, data.getBrfCntSheepGoats());
        values.put(BRF_VALUE_SGOATS_COL, data.getBrfValSheepGoats());
        values.put(AFT_COUNT_SGOATS_COL, data.getAftCntSheepGoats());
        values.put(AFT_VALUE_SGOATS_COL, data.getAftValSheepGoats());
        values.put(BRF_COUNT_POULTRY_COL, data.getBrfCntPoultry());
        values.put(BRF_VALUE_POULTRY_COL, data.getBrfValPoultry());
        values.put(AFT_COUNT_POULTRY_COL, data.getAftCntPoultry());
        values.put(AFT_VALUE_POULTRY_COL, data.getAftValPoultry());
        values.put(BRF_COUNT_OTHER_COL, data.getBrfCntOther());
        values.put(BRF_VALUE_OTHER_COL, data.getBrfValOther());
        values.put(AFT_COUNT_OTHER_COL, data.getAftCntOther());
        values.put(AFT_VALUE_OTHER_COL, data.getAftValOther());
        values.put(BRF_ACRE_CULTIVABLE_COL, data.getBrfAcreCultivable());
        values.put(BRF_VALUE_CULTIVABLE_COL, data.getBrfValCultivable());
        values.put(AFT_ACRE_CULTIVABLE_COL, data.getAftAcreCultivable());
        values.put(AFT_VALUE_CULTIVABLE_COL, data.getAftValCultivable());
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, data.getBrfAcreNonCultivable());
        values.put(BRF_VAL_NON_CULTIVABLE_COL, data.getBrfValNonCultivable());
        values.put(AFT_ACRE_NON_CULTIVABLE, data.getAftAcreNonCultivable());
        values.put(AFT_VAL_NON_CULTIVABLE, data.getAftValNonCultivable());
        values.put(BRF_ACRE_ORCHARDS, data.getBrfAcreOrchards());
        values.put(BRF_VAL_ORCHARDS, data.getBrfValOrchards());
        values.put(AFT_ACRE_ORCHARDS, data.getAftAcreOrchards());
        values.put(AFT_VAL_ORCHARDS, data.getAftValOrchards());
        values.put(BRF_VAL_CROP, data.getBrfValCrop());
        values.put(AFT_VAL_CROP, data.getAftValCrop());
        values.put(BRF_VAL_LIVESTOCK, data.getBrfValLivestock());
        values.put(AFT_VAL_LIVESTOCK, data.getAftValLivestock());
        values.put(BRF_VAL_SMALL_BUSINESS, data.getBrfValSmallBusiness());
        values.put(AFT_VAL_SMALL_BUSINESS, data.getAftValSmallBusiness());
        values.put(BRF_VAL_EMPLOYMENT, data.getBrfValEmployment());
        values.put(AFT_VAL_EMPLOYMENT, data.getAftValEmployment());
        values.put(BRF_VAL_REMITTANCES, data.getBrfValRemittances());
        values.put(AFT_VAL_REMITTANCES, data.getAftValRemittances());
        values.put(BRF_CNT_WAGEENR, data.getBrfCntWageEnr());
        values.put(AFT_CNT_WAGEENR, data.getAftCntWageEnr());
        values.put(ENTRY_BY, data.getEntryDate());
        values.put(ENTRY_DATE, data.getEntryBy());

        // Inserting Row into local database
        db.update(REGISTRATION_TABLE, values, where, null);

        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false

        db.close(); // Closing database connection

        Log.d(TAG, "Registration data edited for: " + pID);
    }

    /* @ Faisal Mohammad
    * @date : 2015-09-19
    * todo: replace table name */
    public long addHHRegForLiberia(RegN_HH_libDataModel data) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, data.getCountryCode());
        values.put(DISTRICT_NAME_COL, data.getDistrictCode());
        values.put(UPZILLA_NAME_COL, data.getUpazillaCode());
        values.put(UNITE_NAME_COL, data.getUnitCode());
        values.put(VILLAGE_NAME_COL, data.getVillageCode());
        values.put(PID_COL, data.getHhId());
        values.put(REG_DATE_COL, data.getRegNDate());
        values.put(PNAME_COL, data.getHHHeadName());
        values.put(HOUSE_HOLD_TYPE_CODE_COL, data.getHHTypeCode());

        values.put(LT2YRS_M_COL, data.getLT2yrsM());
        values.put(LT2YRS_F_COL, data.getLT2yrsF());
        values.put(M_2TO5YRS_COL, data.getM2to5yers());
        values.put(F_2TO5YRS_COL, data.getF2to5yrs());
        values.put(M_6TO12YRS_COL, data.getM6to12yrs());
        values.put(F_6TO12YRS_COL, data.getF6to12yrs());
        values.put(M_13TO17YRS_COL, data.getM13to17yrs());
        values.put(F_13TO17YRS_COL, data.getF13to17yrs());
        values.put(ORPHN_LT18YRS_M_COL, data.getOrphn_LT18yrsM());
        values.put(ORPHN_LT18YRS_F_COL, data.getOrphn_LT18yrsF());
        values.put(ADLT_18TO59_M_COL, data.getAdlt_18to59M());
        values.put(ADLT_18TO59_F_COL, data.getAdlt_18to59F());
        values.put(ELD_60P_M_COL, data.getEld_60pM());
        values.put(ELD_60P_F_COL, data.getEld_60pF());
        values.put(PLW_NO_COL, data.getPLW());
        values.put(CHRO_ILL_NO_COL, data.getChronicallyIll());

        values.put(DECEASED_CONTRACT_EBOLA_COL, data.getLivingDeceasedContractEbola());
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, data.getExtraChildBecauseEbola());
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, data.getExtraelderlyPersonBecauseEbola());
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, data.getExtraChronicallyIllDisabledPersonBecauseEbola());


        values.put(BRF_COUNT_CATTLE_COL, data.getBrfCntCattle());
        values.put(BRF_VALUE_CATTLE_COL, data.getBrfValCattle());
        values.put(AFT_COUNT_CATTLE_COL, data.getAftCntCattle());
        values.put(AFT_VALUE_CATTLE_COL, data.getAftValCattle());
        values.put(BRF_COUNT_SGOATS_COL, data.getBrfCntSheepGoats());
        values.put(BRF_VALUE_SGOATS_COL, data.getBrfValSheepGoats());
        values.put(AFT_COUNT_SGOATS_COL, data.getAftCntSheepGoats());
        values.put(AFT_VALUE_SGOATS_COL, data.getAftValSheepGoats());
        values.put(BRF_COUNT_POULTRY_COL, data.getBrfCntPoultry());
        values.put(BRF_VALUE_POULTRY_COL, data.getBrfValPoultry());
        values.put(AFT_COUNT_POULTRY_COL, data.getAftCntPoultry());
        values.put(AFT_VALUE_POULTRY_COL, data.getAftValPoultry());
        values.put(BRF_COUNT_OTHER_COL, data.getBrfCntOther());
        values.put(BRF_VALUE_OTHER_COL, data.getBrfValOther());
        values.put(AFT_COUNT_OTHER_COL, data.getAftCntOther());
        values.put(AFT_VALUE_OTHER_COL, data.getAftValOther());
        values.put(BRF_ACRE_CULTIVABLE_COL, data.getBrfAcreCultivable());
        values.put(BRF_VALUE_CULTIVABLE_COL, data.getBrfValCultivable());
        values.put(AFT_ACRE_CULTIVABLE_COL, data.getAftAcreCultivable());
        values.put(AFT_VALUE_CULTIVABLE_COL, data.getAftValCultivable());
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, data.getBrfAcreNonCultivable());
        values.put(BRF_VAL_NON_CULTIVABLE_COL, data.getBrfValNonCultivable());
        values.put(AFT_ACRE_NON_CULTIVABLE, data.getAftAcreNonCultivable());
        values.put(AFT_VAL_NON_CULTIVABLE, data.getAftValNonCultivable());
        values.put(BRF_ACRE_ORCHARDS, data.getBrfAcreOrchards());
        values.put(BRF_VAL_ORCHARDS, data.getBrfValOrchards());
        values.put(AFT_ACRE_ORCHARDS, data.getAftAcreOrchards());
        values.put(AFT_VAL_ORCHARDS, data.getAftValOrchards());
        values.put(BRF_VAL_CROP, data.getBrfValCrop());
        values.put(AFT_VAL_CROP, data.getAftValCrop());
        values.put(BRF_VAL_LIVESTOCK, data.getBrfValLivestock());
        values.put(AFT_VAL_LIVESTOCK, data.getAftValLivestock());
        values.put(BRF_VAL_SMALL_BUSINESS, data.getBrfValSmallBusiness());
        values.put(AFT_VAL_SMALL_BUSINESS, data.getAftValSmallBusiness());
        values.put(BRF_VAL_EMPLOYMENT, data.getBrfValEmployment());
        values.put(AFT_VAL_EMPLOYMENT, data.getAftValEmployment());
        values.put(BRF_VAL_REMITTANCES, data.getBrfValRemittances());
        values.put(AFT_VAL_REMITTANCES, data.getAftValRemittances());
        values.put(BRF_CNT_WAGEENR, data.getBrfCntWageEnr());
        values.put(AFT_CNT_WAGEENR, data.getAftCntWageEnr());


        values.put(ENTRY_BY, data.getEntryBy());
        values.put(ENTRY_DATE, data.getEntryDate());
        // many mort ot insert
        // insert
        long id = db.insert(REGISTRATION_TABLE, null, values);
        db.close();
        Log.d(TAG, "New LIBERIA_REGISTRATION_TABLE  added: " + id);
        return id;
    }


    public void addServiceSpecificTableFromOnline(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String srvStatus
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode


    ) {

        String entryBy = "";
        String entryDate = "";


        addServiceSpecificTable(cCode, donorCode, awardCode
                , distCode, upCode, unCode, vCode, hhId, memId, programCode
                , srvCode, opCode, opMonthCode, srvCenterCode
                , fdpCode, srvStatus
                , babyStatus
                , gmpAttendence, weightStatus, nutAttendance, vitaUnder5, exclCurrentLybf
                , dateComFeed, camRef, camAdd, dateAncVisit
                , pncVisit2D, pncVisit1W, pncVisit6W
                , deliveryStaff_1, deliveryStaff_2, deliveryStaff_3
                , homeSupport24H_1D, homeSupport24H_2D, homeSupport24H_3D
                , homeSupport24H_8D, homeSupport24H_14D, homeSupport24H_21D
                , homeSupport24H_30D, homeSupport24H_60D, homeSupport24H_90D
                , homeSupport48H_1D, homeSupport48H_3D, homeSupport48H_8D
                , homeSupport48H_30D, homeSupport48H_60D, homeSupport48H_90D
                , maternal_bleeed, maternal_seizure, maternal_infection
                , maternal_proLongedLabor, maternal_obstructedLabor, maternal_pprm
                , nBorn_Aspyxia, nBorn_Sepsis, nBorn_HypoThermai
                , nBorn_HyperThermai, nBorn_noSuckling, nBorn_Jaundices
                , child_Diarrhea, child_Pneumonia, child_Fever, child_CerebralPalsy
                , immu_Polio, immu_BCG, immu_Measles
                , immu_DPT_HIB, immu_Lotta, immU_Other
                , fpCounsel_MaleCondom, fpCounsel_FemaleCondom
                , fpCounsel_Pill, fpCounsel_Depo
                , fpCounsel_LongParmanent, fpCounsel_NoMethod
                , cropCode, loanSource
                , loanAMT, animalCode
                , leadCode
                , entryBy
                , entryDate);
    }

    /**
     * This method insert into Service Specification Table
     *
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param distCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhId
     * @param memId
     * @param programCode
     * @param srvCode
     * @param opCode
     * @param opMonthCode
     * @param srvCenterCode
     * @param fdpCode
     * @param srvStatus
     * @param babyStatus
     * @param gmpAttendence
     * @param weightStatus
     * @param nutAttendance
     * @param vitaUnder5
     * @param exclCurrentLybf
     * @param dateComFeed
     * @param camRef
     * @param dateAncVisit
     * @param pncVisit2D
     * @param pncVisit1W
     * @param pncVisit6W
     * @param deliveryStaff_1
     * @param deliveryStaff_2
     * @param deliveryStaff_3
     * @param homeSupport24H_1D
     * @param homeSupport24H_2D
     * @param homeSupport24H_3D
     * @param homeSupport24H_8D
     * @param homeSupport24H_14D
     * @param homeSupport24H_21D
     * @param homeSupport24H_30D
     * @param homeSupport24H_60D
     * @param homeSupport24H_90D
     * @param homeSupport48H_1D
     * @param homeSupport48H_3D
     * @param homeSupport48H_8D
     * @param homeSupport48H_30D
     * @param homeSupport48H_60D
     * @param homeSupport48H_90D
     * @param maternal_bleeed
     * @param maternal_seizure
     * @param maternal_infection
     * @param maternal_proLongedLabor
     * @param maternal_obstructedLabor
     * @param maternal_pprm
     * @param nBorn_Aspyxia
     * @param nBorn_Sepsis
     * @param nBorn_HypoThermai
     * @param nBorn_HyperThermai
     * @param nBorn_noSuckling
     * @param nBorn_Jaundices
     * @param child_Diarrhea
     * @param child_Pneumonia
     * @param child_Fever
     * @param child_CerebralPalsy
     * @param immu_Polio
     * @param immu_BCG
     * @param immu_Measles
     * @param immu_DPT_HIB
     * @param immu_Lotta
     * @param immU_Other
     * @param fpCounsel_MaleCondom
     * @param fpCounsel_FemaleCondom
     * @param fpCounsel_Pill
     * @param fpCounsel_Depo
     * @param fpCounsel_LongParmanent
     * @param fpCounsel_NoMethod
     * @param cropCode
     * @param loanSource
     * @param loanAMT
     * @param animalCode
     * @param leadCode
     * @param entryBy
     * @param entryDate
     * @return
     */


    public long addServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String srvStatus
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode
            , String entryBy
            , String entryDate


    ) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(DISTRICT_CODE_COL, distCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);
        values.put(HHID_COL, hhId);
        values.put(HH_MEM_ID, memId);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(SERVICE_CODE_COL, srvCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);
        values.put(FDP_CODE_COL, fdpCode);
        values.put(SERVICE_STATUS_COL, srvStatus);

        values.put(BABY_STATUS_COL, babyStatus);
        values.put(GMP_ATTENDACE_COL, gmpAttendence);
        values.put(WEIGHT_STATUS_COL, weightStatus);
        values.put(NUT_ATTENDANCE_COL, nutAttendance);
        values.put(VITA_UNDER5_COL, vitaUnder5);
        values.put(EXCLUSIVE_CURRENTLYBF_COL, exclCurrentLybf);
        values.put(DATE_COMPFEEDING_COL, dateComFeed);
        values.put(CMAMREF_COL, camRef);
        values.put(CMAMADD_COL, camAdd);
        values.put(ANCVISIT_COL, dateAncVisit);

        values.put(PNCVISIT_2D_COL, pncVisit2D);
        values.put(PNCVISIT_1W_COL, pncVisit1W);
        values.put(PNCVISIT_6W_COL, pncVisit6W);

        values.put(DELIVERY_STAFF_1_COL, deliveryStaff_1);
        values.put(DELIVERY_STAFF_2_COL, deliveryStaff_2);
        values.put(DELIVERY_STAFF_3_COL, deliveryStaff_3);

        values.put(HOME_SUPPORT24H_1D_COL, homeSupport24H_1D);
        values.put(HOME_SUPPORT24H_2D_COL, homeSupport24H_2D);
        values.put(HOME_SUPPORT24H_3D_COL, homeSupport24H_3D);
        values.put(HOME_SUPPORT24H_8D_COL, homeSupport24H_8D);
        values.put(HOME_SUPPORT24H_14D_COL, homeSupport24H_14D);
        values.put(HOME_SUPPORT24H_21D_COL, homeSupport24H_21D);
        values.put(HOME_SUPPORT24H_30D_COL, homeSupport24H_30D);
        values.put(HOME_SUPPORT24H_60D_COL, homeSupport24H_60D);
        values.put(HOME_SUPPORT24H_90D_COL, homeSupport24H_90D);

        values.put(HOME_SUPPORT48H_1D_COL, homeSupport48H_1D);
        values.put(HOME_SUPPORT48H_3D_COL, homeSupport48H_3D);
        values.put(HOME_SUPPORT48H_8D_COL, homeSupport48H_8D);
        values.put(HOME_SUPPORT48H_30D_COL, homeSupport48H_30D);
        values.put(HOME_SUPPORT48H_60D_COL, homeSupport48H_60D);
        values.put(HOME_SUPPORT48H_90D_COL, homeSupport48H_90D);

        values.put(MATERNAL_BLEEDING_COL, maternal_bleeed);
        values.put(MATERNAL_SEIZURE_COL, maternal_seizure);
        values.put(MATERNAL_INFECTION_COL, maternal_infection);
        values.put(MATERNAL_PROLONGEDLABOR_COL, maternal_proLongedLabor);
        values.put(MATERNAL_OBSTRUCTEDLABOR_COL, maternal_obstructedLabor);
        values.put(MATERNAL_PPRM_COL, maternal_pprm);

        values.put(NBORN_ASPHYXIA_COL, nBorn_Aspyxia);
        values.put(NBORN_SEPSIS_COL, nBorn_Sepsis);
        values.put(NBORN_HYPOTHERMIA_COL, nBorn_HypoThermai);
        values.put(NBORN_HYPERTHERMIA_COL, nBorn_HyperThermai);
        values.put(NBORN_NOSUCKLING_COL, nBorn_noSuckling);
        values.put(NBORN_JAUNDICE_COL, nBorn_Jaundices);

        values.put(CHILD_DIARRHEA_COL, child_Diarrhea);
        values.put(CHILD_PNEUMONIA_COL, child_Pneumonia);
        values.put(CHILD_FEVER_COL, child_Fever);
        values.put(CHILD_CEREBRALPALSY_COL, child_CerebralPalsy);

        values.put(IMMU_POLIO_COL, immu_Polio);
        values.put(IMMU_BCG_COL, immu_BCG);
        values.put(IMMU_MEASLES_COL, immu_Measles);
        values.put(IMMU_DPT_HIB_COL, immu_DPT_HIB);
        values.put(IMMU_LOTTA_COL, immu_Lotta);
        values.put(IMMU_OTHER_COL, immU_Other);

        values.put(FPCOUNSEL_MALECONDOM_COL, fpCounsel_MaleCondom);
        values.put(FPCOUNSEL_FEMALECONDOM_COL, fpCounsel_FemaleCondom);
        values.put(FPCOUNSEL_PILL_COL, fpCounsel_Pill);
        values.put(FPCOUNSEL_DEPO_COL, fpCounsel_Depo);
        values.put(FPCOUNSEL_LONGPARMANENT_COL, fpCounsel_LongParmanent);
        values.put(FPCOUNSEL_NOMETHOD_COL, fpCounsel_NoMethod);

        values.put(CROP_CODE_COL, cropCode);
        values.put(LOAN_SOURCE_COL, loanSource);
        values.put(LOAN_AMT_COL, loanAMT);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // insert
        long id = db.insert(SERVICE_SPECIFIC_TABLE, null, values);
        db.close();
        Log.d(TAG, "New " + SERVICE_SPECIFIC_TABLE + "  added: " + id);
        return id;
    }

    /**
     * Tis method Update the Service Specific Table
     *
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param distCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhId
     * @param memId
     * @param programCode
     * @param srvCode
     * @param opCode
     * @param opMonthCode
     * @param srvCenterCode
     * @param fdpCode
     * @param babyStatus
     * @param gmpAttendence
     * @param weightStatus
     * @param nutAttendance
     * @param vitaUnder5
     * @param exclCurrentLybf
     * @param dateComFeed
     * @param camRef
     * @param camAdd
     * @param dateAncVisit
     * @param pncVisit2D
     * @param pncVisit1W
     * @param pncVisit6W
     * @param deliveryStaff_1
     * @param deliveryStaff_2
     * @param deliveryStaff_3
     * @param homeSupport24H_1D
     * @param homeSupport24H_2D
     * @param homeSupport24H_3D
     * @param homeSupport24H_8D
     * @param homeSupport24H_14D
     * @param homeSupport24H_21D
     * @param homeSupport24H_30D
     * @param homeSupport24H_60D
     * @param homeSupport24H_90D
     * @param homeSupport48H_1D
     * @param homeSupport48H_3D
     * @param homeSupport48H_8D
     * @param homeSupport48H_30D
     * @param homeSupport48H_60D
     * @param homeSupport48H_90D
     * @param maternal_bleeed
     * @param maternal_seizure
     * @param maternal_infection
     * @param maternal_proLongedLabor
     * @param maternal_obstructedLabor
     * @param maternal_pprm
     * @param nBorn_Aspyxia
     * @param nBorn_Sepsis
     * @param nBorn_HypoThermai
     * @param nBorn_HyperThermai
     * @param nBorn_noSuckling
     * @param nBorn_Jaundices
     * @param child_Diarrhea
     * @param child_Pneumonia
     * @param child_Fever
     * @param child_CerebralPalsy
     * @param immu_Polio
     * @param immu_BCG
     * @param immu_Measles
     * @param immu_DPT_HIB
     * @param immu_Lotta
     * @param immU_Other
     * @param fpCounsel_MaleCondom
     * @param fpCounsel_FemaleCondom
     * @param fpCounsel_Pill
     * @param fpCounsel_Depo
     * @param fpCounsel_LongParmanent
     * @param fpCounsel_NoMethod
     * @param cropCode
     * @param loanSource
     * @param loanAMT
     * @param animalCode
     * @param leadCode
     * @param entryBy
     * @param entryDate
     * @return
     */


    public int uploadIntoServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode
            , String babyStatus
            , String gmpAttendence, String weightStatus, String nutAttendance, String vitaUnder5, String exclCurrentLybf
            , String dateComFeed, String camRef, String camAdd, String dateAncVisit
            , String pncVisit2D, String pncVisit1W, String pncVisit6W
            , String deliveryStaff_1, String deliveryStaff_2, String deliveryStaff_3
            , String homeSupport24H_1D, String homeSupport24H_2D, String homeSupport24H_3D
            , String homeSupport24H_8D, String homeSupport24H_14D, String homeSupport24H_21D
            , String homeSupport24H_30D, String homeSupport24H_60D, String homeSupport24H_90D
            , String homeSupport48H_1D, String homeSupport48H_3D, String homeSupport48H_8D
            , String homeSupport48H_30D, String homeSupport48H_60D, String homeSupport48H_90D
            , String maternal_bleeed, String maternal_seizure, String maternal_infection
            , String maternal_proLongedLabor, String maternal_obstructedLabor, String maternal_pprm
            , String nBorn_Aspyxia, String nBorn_Sepsis, String nBorn_HypoThermai
            , String nBorn_HyperThermai, String nBorn_noSuckling, String nBorn_Jaundices
            , String child_Diarrhea, String child_Pneumonia, String child_Fever, String child_CerebralPalsy
            , String immu_Polio, String immu_BCG, String immu_Measles
            , String immu_DPT_HIB, String immu_Lotta, String immU_Other
            , String fpCounsel_MaleCondom, String fpCounsel_FemaleCondom
            , String fpCounsel_Pill, String fpCounsel_Depo
            , String fpCounsel_LongParmanent, String fpCounsel_NoMethod
            , String cropCode, String loanSource
            , String loanAMT, String animalCode
            , String leadCode
            , String entryBy
            , String entryDate


    ) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + DISTRICT_CODE_COL + " = '" + distCode + "' "
                + " AND " + UPCODE_COL + " = '" + upCode + "' "
                + " AND " + UCODE_COL + " = '" + unCode + "' "
                + " AND " + VCODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhId + "' "
                + " AND " + HH_MEM_ID + " = '" + memId + "' "
                + " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SERVICE_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' " //OPCODE 2 IS SERVICE MODE
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "' "
                + " AND " + FDP_CODE_COL + " = '" + fdpCode + "' ";


        values.put(BABY_STATUS_COL, babyStatus);
        values.put(GMP_ATTENDACE_COL, gmpAttendence);
        values.put(WEIGHT_STATUS_COL, weightStatus);
        values.put(NUT_ATTENDANCE_COL, nutAttendance);
        values.put(VITA_UNDER5_COL, vitaUnder5);
        values.put(EXCLUSIVE_CURRENTLYBF_COL, exclCurrentLybf);
        values.put(DATE_COMPFEEDING_COL, dateComFeed);
        values.put(CMAMREF_COL, camRef);
        values.put(CMAMADD_COL, camAdd);
        values.put(ANCVISIT_COL, dateAncVisit);

        values.put(PNCVISIT_2D_COL, pncVisit2D);
        values.put(PNCVISIT_1W_COL, pncVisit1W);
        values.put(PNCVISIT_6W_COL, pncVisit6W);

        values.put(DELIVERY_STAFF_1_COL, deliveryStaff_1);
        values.put(DELIVERY_STAFF_2_COL, deliveryStaff_2);
        values.put(DELIVERY_STAFF_3_COL, deliveryStaff_3);

        values.put(HOME_SUPPORT24H_1D_COL, homeSupport24H_1D);
        values.put(HOME_SUPPORT24H_2D_COL, homeSupport24H_2D);
        values.put(HOME_SUPPORT24H_3D_COL, homeSupport24H_3D);
        values.put(HOME_SUPPORT24H_8D_COL, homeSupport24H_8D);
        values.put(HOME_SUPPORT24H_14D_COL, homeSupport24H_14D);
        values.put(HOME_SUPPORT24H_21D_COL, homeSupport24H_21D);
        values.put(HOME_SUPPORT24H_30D_COL, homeSupport24H_30D);
        values.put(HOME_SUPPORT24H_60D_COL, homeSupport24H_60D);
        values.put(HOME_SUPPORT24H_90D_COL, homeSupport24H_90D);

        values.put(HOME_SUPPORT48H_1D_COL, homeSupport48H_1D);
        values.put(HOME_SUPPORT48H_3D_COL, homeSupport48H_3D);
        values.put(HOME_SUPPORT48H_8D_COL, homeSupport48H_8D);
        values.put(HOME_SUPPORT48H_30D_COL, homeSupport48H_30D);
        values.put(HOME_SUPPORT48H_60D_COL, homeSupport48H_60D);
        values.put(HOME_SUPPORT48H_90D_COL, homeSupport48H_90D);

        values.put(MATERNAL_BLEEDING_COL, maternal_bleeed);
        values.put(MATERNAL_SEIZURE_COL, maternal_seizure);
        values.put(MATERNAL_INFECTION_COL, maternal_infection);
        values.put(MATERNAL_PROLONGEDLABOR_COL, maternal_proLongedLabor);
        values.put(MATERNAL_OBSTRUCTEDLABOR_COL, maternal_obstructedLabor);
        values.put(MATERNAL_PPRM_COL, maternal_pprm);

        values.put(NBORN_ASPHYXIA_COL, nBorn_Aspyxia);
        values.put(NBORN_SEPSIS_COL, nBorn_Sepsis);
        values.put(NBORN_HYPOTHERMIA_COL, nBorn_HypoThermai);
        values.put(NBORN_HYPERTHERMIA_COL, nBorn_HyperThermai);
        values.put(NBORN_NOSUCKLING_COL, nBorn_noSuckling);
        values.put(NBORN_JAUNDICE_COL, nBorn_Jaundices);

        values.put(CHILD_DIARRHEA_COL, child_Diarrhea);
        values.put(CHILD_PNEUMONIA_COL, child_Pneumonia);
        values.put(CHILD_FEVER_COL, child_Fever);
        values.put(CHILD_CEREBRALPALSY_COL, child_CerebralPalsy);

        values.put(IMMU_POLIO_COL, immu_Polio);
        values.put(IMMU_BCG_COL, immu_BCG);
        values.put(IMMU_MEASLES_COL, immu_Measles);
        values.put(IMMU_DPT_HIB_COL, immu_DPT_HIB);
        values.put(IMMU_LOTTA_COL, immu_Lotta);
        values.put(IMMU_OTHER_COL, immU_Other);

        values.put(FPCOUNSEL_MALECONDOM_COL, fpCounsel_MaleCondom);
        values.put(FPCOUNSEL_FEMALECONDOM_COL, fpCounsel_FemaleCondom);
        values.put(FPCOUNSEL_PILL_COL, fpCounsel_Pill);
        values.put(FPCOUNSEL_DEPO_COL, fpCounsel_Depo);
        values.put(FPCOUNSEL_LONGPARMANENT_COL, fpCounsel_LongParmanent);
        values.put(FPCOUNSEL_NOMETHOD_COL, fpCounsel_NoMethod);

        values.put(CROP_CODE_COL, cropCode);
        values.put(LOAN_SOURCE_COL, loanSource);
        values.put(LOAN_AMT_COL, loanAMT);
        values.put(ANIMAL_CODE_COL, animalCode);
        values.put(LEAD_CODE_COL, leadCode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        // insert
        int affectedRow = db.update(SERVICE_SPECIFIC_TABLE, values, where, null);
        db.close();
        Log.d(TAG, "No of Row affected  " + affectedRow + " in " + SERVICE_SPECIFIC_TABLE);
        return affectedRow;
    }


    public void deleteFromServSpecificTable(String cCode, String donorCode, String awardCode
            , String distCode, String upCode, String unCode, String vCode, String hhId, String memId, String programCode
            , String srvCode, String opCode, String opMonthCode) {


        SQLiteDatabase db = this.getWritableDatabase();

        String where = COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + DISTRICT_CODE_COL + " = '" + distCode + "' "
                + " AND " + UPCODE_COL + " = '" + upCode + "' "
                + " AND " + UCODE_COL + " = '" + unCode + "' "
                + " AND " + VCODE_COL + " = '" + vCode + "' "
                + " AND " + HHID_COL + " = '" + hhId + "' "
                + " AND " + HH_MEM_ID + " = '" + memId + "' "
                + " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SERVICE_CODE_COL + " = '" + srvCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + opCode + "' " //OPCODE 2 IS SERVICE MODE
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";


        // delete
        int noOfDeletedRow = db.delete(SERVICE_SPECIFIC_TABLE, where, null);
        db.close();
        Log.d(TAG, "No of Row deleted  " + noOfDeletedRow + " in " + SERVICE_SPECIFIC_TABLE);

    }


    public void addInDistributionTableFormOnLine(DistributionSaveDataModel dist_data) {
        dist_data.setEntryBy("-");
        dist_data.setEntryDate("-");
        addInDistributionTable(dist_data);

    }

    public long addInDistributionTable(DistributionSaveDataModel distData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, distData.getCountryCode());
        values.put(DONOR_CODE_COL, distData.getAdmDonorCode());
        values.put(AWARD_CODE_COL, distData.getAdmAwardCode());
        values.put(DISTRICT_CODE_COL, distData.getDistrictCode());
        values.put(UPCODE_COL, distData.getUpCode());
        values.put(UCODE_COL, distData.getUniteCode());
        values.put(VCODE_COL, distData.getVillageCode());
        values.put(PROGRAM_CODE_COL, distData.getProgCode());
        values.put(SERVICE_CODE_COL, distData.getSrvCode());
        values.put(OP_MONTH_CODE_COL, distData.getOpMonthCode());
        values.put(FDP_CODE_COL, distData.getFDPCode());
        values.put(MEM_ID_15_D_COL, distData.getID());
        values.put(DISTRIBUTION_STATUS_COL, distData.getDistStatus());
        values.put(SRV_OP_MONTH_CODE_COL, distData.getSrvOpMonthCode());
        values.put(DIST_FLAG_COL, distData.getDistFlag());
        values.put(WORK_DAY_COL, distData.getWd());


        values.put(ENTRY_BY, distData.getEntryBy());
        values.put(ENTRY_DATE, distData.getEntryDate());
        long id = db.insert(DISTRIBUTION_TABLE, null, values);
        db.close();
        Log.d(TAG, "Distribution table   added: " + id);

        return id;
    }


    public long addInDistributionExtendedTable(String c_code, String donorCode, String awardCode, String districtCode,
                                               String upzellaCode, String uname, String vname, String program, String service, String opMonth,
                                               String fdp, String memID,
                                               String voItmSpec, String voItmUnit, String voRefNumber, String srvOpMonthCode, String distFlag, String entryBy, String entryDate, String is_online) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, c_code);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upzellaCode);
        values.put(UCODE_COL, uname);
        values.put(VCODE_COL, vname);
        values.put(PROGRAM_CODE_COL, program);
        values.put(SERVICE_CODE_COL, service);
        values.put(OP_MONTH_CODE_COL, opMonth);
        values.put(FDP_CODE_COL, fdp);
        values.put(MEM_ID_15_D_COL, memID);
        values.put(VOUCHER_ITEM_SPEC_COL, voItmSpec);
        values.put(VOUCHER_UNIT_COL, voItmUnit);
        values.put(VOUCHER_REFERENCE_NUMBER_COL, voRefNumber);

        values.put(SRV_OP_MONTH_CODE_COL, srvOpMonthCode);
        values.put(DIST_FLAG_COL, distFlag);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, is_online);
        long id = db.insert(DISTRIBUTION_EXTENDED_TABLE, null, values);
        db.close();
        Log.d(TAG, "Distribution Extended table   added: " + id);

        return id;
    }


    public boolean getDistIsprepare(String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String ProgCode,
                                    String OpCode, String SrvOpMonthCode, String DisOpMonthCode, String FDPCode) {


        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT *  FROM " + DIST_N_PLAN_BASIC_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + AdmDonorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + AdmAwardCode + "' "
                + " AND " + PROGRAM_CODE_COL + " = '" + ProgCode + "' "
                + " AND " + OPERATION_CODE_COL + " = '" + OpCode + "' "
                + " AND " + SRV_OP_MONTH_CODE_COL + " = '" + SrvOpMonthCode + "' "
                + " AND " + DIST_OP_MONTH_CODE_COL + " = '" + DisOpMonthCode + "' "
                + " AND " + FDP_CODE_COL + " = '" + FDPCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else
            return false;
    }

    public void addInDistributionNPlaneTable(String AdmCountryCode, String AdmDonorCode, String AdmAwardCode, String ProgCode,
                                             String OpCode, String SrvOpMonthCode, String DisOpMonthCode, String FDPCode
            , String DistFlag, String OrgCode, String Distributor, String DistributionDate, String DeliveryDate, String Status, String PreparedBy, String VerifiedBy, String ApproveBy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, AdmCountryCode);
        values.put(DONOR_CODE_COL, AdmDonorCode);
        values.put(AWARD_CODE_COL, AdmAwardCode);
        values.put(PROGRAM_CODE_COL, ProgCode);
        values.put(OPERATION_CODE_COL, OpCode);
        values.put(SRV_OP_MONTH_CODE_COL, SrvOpMonthCode);
        values.put(DIST_OP_MONTH_CODE_COL, DisOpMonthCode);
        values.put(FDP_CODE_COL, FDPCode);
        values.put(DIST_FLAG_COL, DistFlag);
        values.put(ORG_CODE_COL, OrgCode);
        values.put(DISTRIBUTOR_COL, Distributor);
        values.put(DISTRIBUTION_DATE_COL, DistributionDate);
        values.put(DELIVERY_DATE_COL, DeliveryDate);
        values.put(STATUS, Status);
        values.put(PREPARED_BY_COL, PreparedBy);
        values.put(VERIFIED_BY_COL, VerifiedBy);
        values.put(APPROVED_BY_COL, ApproveBy);

        long id = db.insert(DIST_N_PLAN_BASIC_TABLE, null, values);
        db.close();
        Log.d(TAG, "Distribution Extended table   added: " + id);


    }


    public String getMemberRegNDate(GraduationGridDataModel dtata) {
        AssignDataModel assignedMem = new AssignDataModel();
        assignedMem.setC_code(dtata.getCountryCode());
        assignedMem.setDistrictCode(dtata.getDistrictCode());
        assignedMem.setUpazillaCode(dtata.getUpazillaCode());
        assignedMem.setUnitCode(dtata.getUnitCode());
        assignedMem.setVillageCode(dtata.getVillageCode());
        assignedMem.setHh_id(dtata.getHh_id());
        assignedMem.setMemId(dtata.getMember_Id());
        assignedMem.setProgram_code(dtata.getProgram_code());
        assignedMem.setService_code(dtata.getService_code());
        return getMemberRegNDate(assignedMem);
    }


    /**
     * @param assignedMem
     * @return
     * @modified: 2015-11-07
     */
    public String getMemberRegNDate(AssignDataModel assignedMem) {
        String regDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT   " + REG_DATE_COL + "  " +
                " FROM " + REGISTRATION_MEMBER_TABLE + " WHERE " + COUNTRY_CODE_COL + " = '" + assignedMem.getCountryCode() + "' " +
                " AND " + DISTRICT_NAME_COL + " = '" + assignedMem.getDistrictCode() + "' " +
                " AND " + UPZILLA_NAME_COL + " = '" + assignedMem.getUpazillaCode() + "' " +
                " AND " + UNITE_NAME_COL + " = '" + assignedMem.getUnitCode() + "' " +
                " AND " + VILLAGE_NAME_COL + " = '" + assignedMem.getVillageCode() + "' " +
                " AND " + HHID_COL + " = '" + assignedMem.getHh_id() + "' " +
                " AND " + HH_MEM_ID + " = '" + assignedMem.getMemId() + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    regDate = cursor.getString(cursor.getColumnIndex(REG_DATE_COL));
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getMemberName() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();

        return regDate;
    }


    /* @ Faisal Mohammad
    * @date : 2015-09-18*/
    public void addHHCategory(String cCode, String hhCatCode, String hhCategoryName) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(CATEGORY_CODE_COL, hhCatCode);
        values.put(CATEGORY_NAME_COL, hhCategoryName);
        // Insert
        long id = db.insert(HOUSE_HOLD_CATEGORY_TABLE, null, values);
        db.close();
        Log.d(TAG, "New House Hold Category  added: " + id);


    }

    public String getUserId() {
        String userId = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQurey = "SELECT " + USER_ID + " FROM " + LOGIN_TABLE;
        Cursor cursor;
        cursor = db.rawQuery(selectQurey, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                userId = cursor.getString(cursor.getColumnIndex(USER_ID));
            }
            cursor.close();
        }

        db.close();
        return userId;
    }

    public boolean ifExistsInCU2Table(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + REG_N_CU2_TABLE + " WHERE    " + COUNTRY_CODE_COL + "=? AND " + DISTRICT_CODE_COL + "=? AND "
                        + UPCODE_COL + "=? AND " + UCODE_COL + "=? AND " + VCODE_COL + "=? AND " + HHID_COL + "=? AND " + HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});//*keyValue,keyvalue1*/});

        if (mCursor.getCount() > 0) {
            Log.d(TAG, " This data exists In CU2 table");
            return true;
/* record exist */
        } else {
            Log.d(TAG, " This data  didn't exists In cu2 table");
            return false;
/* record not exist */
        }

    }

    public int editMemberDataIn_CU2(AssignDataModel assMem, String dob) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(PROGRAM_CODE_COL, assMem.getProgram_code());
        values.put(SERVICE_CODE_COL, assMem.getService_code());
        values.put(REG_N_DAT_COL, assMem.getRegNDate());
        values.put(CU2DOB_DATE_COL, dob); // GDR Date
        values.put(GRD_CODE_COL, assMem.getGrdCode());
        values.put(CU2_GRD_DATE_COL, assMem.getGrdDate()); // GDR_Date
        values.put(ENTRY_BY, assMem.getEntryBy());
        values.put(ENTRY_DATE, assMem.getEntryDate());
        values.put(SYNC_COL, "0");

        Log.d(TAG, " in Cu2 :" + CU2DOB_DATE_COL + dob);

        Log.d(TAG, "asPeople.getCU2DOB_DATE_COL _Date():" + dob);

        String query = COUNTRY_CODE + " = '" + assMem.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assMem.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assMem.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assMem.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assMem.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assMem.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assMem.getMemId() + "'  ";


// updating row
        int id = db.update(REG_N_CU2_TABLE, values, query, null);
/* int id= db.update( REG_N_PW_TABLE, values, HH_MEM_ID + " = ? AND ",
                new String[]{asPeople.getMemberId()});*/

        //updateRegNPWStatus(assMem, 0);

        return id;
    }

    /*public long addMemberInto_CU2(AssignDataModel assingPerson, String dob) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, assingPerson.getCountryCode()); // country name

        values.put(DISTRICT_CODE_COL, assingPerson.getDistrictCode()); // district name
        values.put(UPCODE_COL, assingPerson.getUpazillaCode()); // upazilla name
        values.put(UCODE_COL, assingPerson.getUnitCode()); // Unit name
        values.put(VCODE_COL, assingPerson.getVillageCode()); // village  name


        values.put(HHID_COL, assingPerson.getHh_id()); // Hh id
        values.put(HH_MEM_ID, assingPerson.getMemId()); // member id
        values.put(PROGRAM_CODE_COL, assingPerson.getProgram_code()); // program Code
        values.put(SERVICE_CODE_COL, assingPerson.getService_code()); // service Code

        values.put(REG_N_DAT_COL, assingPerson.getRegNDate()); //
        values.put(CU2DOB_DATE_COL, dob);
        values.put(GRD_CODE_COL, assingPerson.getGrdCode());
        values.put(CU2_GRD_DATE_COL, assingPerson.getGrdDate()); // GDR_Date

        values.put(ENTRY_BY, assingPerson.getEntryBy());
        values.put(ENTRY_DATE, assingPerson.getEntryDate());

// values.put(GRD_CODE_COL,"00"); // GDR_CODE
        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(REG_N_CU2_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "add member to the CU2 table: " + id);
        return id;
    }*/


    /**
     * Todo : Save permission
     *
     * @param cCode
     * @param districtCode
     * @param unitCode
     * @param upzellaCode
     * @param vCode
     */

    public boolean getSavePermissionForHHEntries(String cCode, String districtCode, String upzellaCode, String unitCode, String vCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String savePermission = "";
        String selectQuery = SQLiteQuery.getSavePermissionSelectQuery(STAFF_GEO_INFO_ACCESS_TABLE, BTN_SAVE_COL, cCode, districtCode, upzellaCode, unitCode, vCode);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                savePermission = cursor.getString(cursor.getColumnIndex(BTN_SAVE_COL));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

      /*  if (savePermission.equals("1")) return true;
        else
            return  false;
            */
        // simplefli version
        return savePermission.equals("1");
    }

    /**
     * Todo : Save permission
     *
     * @param cCode
     * @param districtCode
     * @param unitCode
     * @param upzellaCode
     * @param vCode
     */

    public boolean getDeletePermissionForHHEntries(String cCode, String districtCode, String upzellaCode, String unitCode, String vCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String savePermission = "";
        String selectQuery = SQLiteQuery.getSavePermissionSelectQuery(STAFF_GEO_INFO_ACCESS_TABLE, BTN_DEL_COL, cCode, districtCode, upzellaCode, unitCode, vCode);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                savePermission = cursor.getString(cursor.getColumnIndex(BTN_DEL_COL));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

      /*  if (savePermission.equals("1")) return true;
        else
            return  false;
            */
        // simplefli version
        return savePermission.equals("1");
    }


    public void addStaffGeoAccessInfoFromOnline(String staffCode, String cCode, String donorCode, String awardCode, String layrListCode, String districtCode, String upzellaCode, String unitCode, String vCode, String btnNew, String btnSave, String btnDel, String btnpepr, String btnAprv, String btnRevw, String btnVrfy, String btnDtrain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(LAYR_LIST_CODE_COL, layrListCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upzellaCode);
        values.put(UCODE_COL, unitCode);
        values.put(VCODE_COL, vCode);

        // the permission of user action
        values.put(BTN_NEW_COL, btnNew);

        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);
        values.put(BTN_PEPR_COL, btnpepr);
        values.put(BTN_APRV_COL, btnAprv);
        values.put(BTN_REVW_COL, btnRevw);
        values.put(BTN_VRFY_COL, btnVrfy);
        values.put(BTN_DTRAN_COL, btnDtrain);


        // Inserting Row
        long id = db.insert(STAFF_GEO_INFO_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New location  inserted into STAFF_GEO_INFO_ACCESS_TABLE : " + id);
    }

    public void addOpMonthFromOnline(String cCode, String donorCode, String awardCode, String opCode, String opMonthCode, String mLable, String sDate, String eDate, String usasDate, String usaeDate, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(MONTH_LABEL, mLable);
        values.put(START_DATE, sDate);
        values.put(END_DATE, eDate);
        values.put(USA_START_DATE, usasDate);
        values.put(USA_END_DATE, usaeDate);
        values.put(STATUS, status);


        // Inserting Row
        long id = db.insert(OP_MONTH_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New location  inserted into OP_MONTH_TABLE Table : " + id);

    }

    // add location

    public void addGpsLocation(String cCode, String grpCode, String subGrpCode, String localCode, String localName, String lat, String lng) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode); // Country  code
        values.put(GROUP_CODE_COL, grpCode); // GROUP code
        values.put(SUB_GROUP_CODE_COL, subGrpCode); // sub GROUP code
        values.put(LOCATION_CODE_COL, localCode); // GROUP name
        values.put(LOCATION_NAME_COL, localName); // GROUP name
        values.put(LATITUDE_COL, lat); // ******************
        values.put(LONGITUDE_COL, lng); // ************


        // Inserting Row
        long id = db.insert(GPS_LOCATION_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New location  inserted into GPSLocation Table : " + id);

    }

    // add gps sub group
    public void addGpsSubGroup(String grpCode, String subGrpCode, String subGrpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode); // GROUP code
        values.put(SUB_GROUP_CODE_COL, subGrpCode); // GROUP name
        values.put(SUB_GROUP_NAME_COL, subGrpName); // GROUP name
        values.put(DESCRIPTION_COL, description); // GROUP description


        // Inserting Row
        long id = db.insert(GPS_SUB_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Group inserted into GPS_SUB_GROUP_TABLE: " + id);

    }

    public String getGroupNameFromDb(String groupCode) {
        String groupName = "";

        String selectQuery = "SELECT  " + GROUP_NAME_COL + " FROM " + GPS_GROUP_TABLE +
                " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                groupName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return groupName;
    }

    public String getSubGroupNameFromDb(String groupCode, String subGroupCode) {
        String sub_groupName = "";

        String selectQuery = "SELECT  " + SUB_GROUP_NAME_COL + " FROM " + GPS_SUB_GROUP_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                sub_groupName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return sub_groupName;
    }

    // add gps Fdp
    public void addStaffFDPAccess(String staffCode, String countryCode, String fdpCode, String btnNew, String btnSave, String btnDel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_CODE, staffCode);
        values.put(COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);
        values.put(BTN_NEW_COL, btnNew);
        values.put(BTN_SAVE_COL, btnSave);
        values.put(BTN_DEL_COL, btnDel);


        // Inserting Row
        long id = db.insert(STAFF_FDP_ACCESS_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Group inserted into " + STAFF_FDP_ACCESS_TABLE + ": " + id);

    }

    // add gps Fdp
    public void addFDPMaster(String countryCode, String fdpCode, String fdpName, String fdpCat, String whCode, String layer1, String layer2) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, countryCode);
        values.put(FDP_CODE_COL, fdpCode);

        values.put(FDP_NAME_COL, fdpName);
        values.put(FDA_CATEGORIES_CODE_COL, fdpName);
        values.put(WH_CODE_COL, whCode);
        values.put(DISTRICT_CODE_COL, layer1);
        values.put(UPCODE_COL, layer2);


        // Inserting Row
        long id = db.insert(FDP_MASTER_TABLE, null, values);
        db.close(); // Closing database connection

//        Log.d(TAG, "FDp master table inserted into " + FDP_MASTER_TABLE + ": " + id);

    }


    public ArrayList<GraduationGridDataModel> getMemberGraduationStatusList(String cCode, String donorCode, String awardCode, String programCode, String srvCode, String memCode) {

        ArrayList<GraduationGridDataModel> graduationGridList = new ArrayList<GraduationGridDataModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getMemberGraduationStatusList_sql(cCode, donorCode, awardCode, programCode, srvCode, memCode);

        Cursor cursor = db.rawQuery(selectQuery, null);
        String dateformat;
        if (cursor.moveToFirst()) {
            do {
                GraduationGridDataModel data = new GraduationGridDataModel();

                data.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                data.setMember_Id(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                data.setMember_name(cursor.getString(cursor.getColumnIndex("memName")));

                data.setCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                data.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_CODE_COL)));
                data.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPCODE_COL)));
                data.setUnitCode(cursor.getString(cursor.getColumnIndex(UCODE_COL)));
                data.setVillageCode(cursor.getString(cursor.getColumnIndex(VCODE_COL)));
                data.setProgram_code(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                data.setService_code(cursor.getString(cursor.getColumnIndex(SERVICE_CODE_COL)));

//                graduation.setGraduationDate(removeTimestamp(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL))));
                data.setGraduationDate(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL)));

                data.setVillageName(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                data.setGraduationTitle(cursor.getString(cursor.getColumnIndex("GRDTitle")));
                data.setnMemId(cursor.getString(cursor.getColumnIndex("nMemId")));

                //  Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));

                graduationGridList.add(data);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return graduationGridList;


    }

    public String getHHIDForLiberia(String c_code, String distID, String upID, String unit, String villID) {

        String registrationID = "";

        String next_id = getNextHHidForLiberia(c_code, distID, upID, unit, villID);

        if (!next_id.isEmpty()) {
            Integer temp_id = Integer.parseInt(next_id);
            temp_id++;
            next_id = temp_id.toString();
        } else {
            next_id = "1";
        }

        int id_len = next_id.length();


        registrationID = getPadding(id_len, next_id);

        return registrationID;
    }


    public String getNextHHidForLiberia(String c_code, String distID, String upID, String unit, String villID) {

        String query = "SELECT " + PID_COL + " AS max_rec FROM " + REGISTRATION_TABLE + " WHERE "
                + COUNTRY_CODE_COL + "='" + c_code + "' AND "
                + DISTRICT_NAME_COL + "='" + distID + "' AND "
                + UPZILLA_NAME_COL + "='" + upID + "' AND "
                + UNITE_NAME_COL + "='" + unit + "' AND "
                + VILLAGE_NAME_COL + "='" + villID + "'" +
                " ORDER BY " + PID_COL + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // EDITED BY POP
        if (cursor.moveToFirst()) {

            next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

        }

        cursor.close();
        db.close();

        return next_id;
    }

    /**
     * @param cCode
     * @param distCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhID
     * @param mmId
     * @return specific data exit or not
     * @see #checkDataExistInTable(String, String)
     */

    public boolean ifDataExistIn_RegN_AGR(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInRegN_ARG_TableSQL(cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_AGR_TABLE);
    }

    public boolean ifDataExistIn_RegN_FFA(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInRegN_FFA_TableSQL(cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_FFA_TABLE);
    }


    public ArrayList<ServiceSlDataModle> getServiceDetailsForMember(String cCode, String donorCode, String awardCord,
                                                                    String districCode, String upCode, String unCode,
                                                                    String vCode, String hhId, String mmId, String opCode,
                                                                    String opMCode, String prgCode, String srvCode) {
        ArrayList<ServiceSlDataModle> srvSlList = new ArrayList<ServiceSlDataModle>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT " + SERVICE_SL_COL + " , " +
                SERVICE_DT_COL + " , " +
                SERVICE_STATUS_COL +
                " FROM  " + SERVICE_TABLE +
                " WHERE  " + COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " And   " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " And   " + AWARD_CODE_COL + " = '" + awardCord + "' " +
                " And  " + DISTRICT_CODE_COL + " = '" + districCode + "' " +
                " And   " + UPCODE_COL + " = '" + upCode + "' " +
                " And   " + UCODE_COL + " = '" + unCode + "' " +
                " And   " + VCODE_COL + " = '" + vCode + "' " +
                "  And  " + HHID_COL + " = '" + hhId + "' " +
                "  And  " + HH_MEM_ID + " = '" + mmId + "' " +
                " And   " + PROGRAM_CODE_COL + " ='" + prgCode + "' " +
                "  And  " + SERVICE_CODE_COL + " ='" + srvCode + "' " +
                //   "  And  " + OPERATION_CODE_COL + " ='" + opCode + "' " +
                "  And  " + OPERATION_CODE_COL + " = '2' " +
                "  And  " + OP_MONTH_CODE_COL + "   ='" + opMCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ServiceSlDataModle srvDetails = new ServiceSlDataModle();

                srvDetails.setSrvSerial(cursor.getString(cursor.getColumnIndex(SERVICE_SL_COL)));

                /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                 * use mm-- dd--YYYY*/
                String temp = cursor.getString(cursor.getColumnIndex(SERVICE_DT_COL));
                // temp = temp.substring(0, 10);
                // / String dateformat = "";
                /**   MM--YYYY--DD*/
                //   dateformat = dateformat + temp.substring(5, 7) + "-" + temp.substring(8, 10) + "-" + temp.substring(0, 4);

                srvDetails.setServiceDate(cursor.getString(cursor.getColumnIndex(SERVICE_DT_COL)));


                srvDetails.setServiceStatus(cursor.getString(cursor.getColumnIndex(SERVICE_STATUS_COL)));

                //   Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor));

                srvSlList.add(srvDetails);

            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();

        return srvSlList;

    }


    public String getDOBDate_CU2(AssignDataModel asPeople) {
        String dobDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + CU2DOB_DATE_COL + " FROM " + REG_N_CU2_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);//*keyValue,keyvalue1*/});
        if (cursor != null && cursor.moveToFirst()) {
            // mCursor.moveToFirst();
//            dobDate = removeTimestamp(cursor.getString(cursor.getColumnIndex(CU2DOB_DATE_COL)));
            dobDate = cursor.getString(cursor.getColumnIndex(CU2DOB_DATE_COL));
            Log.d(TAG, "Dob date in REg Cuild under " + cursor.getString(0));
        }
        if (cursor != null)
            cursor.close();

        db.close();
        // mCursor.close();

        return dobDate;

    }

    public String getDOBDate_CA2(AssignDataModel asPeople) {
        String dobDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + CA2DOB_DATE_COL + " FROM " + REG_N_CA2_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);//*keyValue,keyvalue1*/});
        if (cursor != null && cursor.moveToFirst()) {
            // mCursor.moveToFirst();
            dobDate = cursor.getString(cursor.getColumnIndex(CA2DOB_DATE_COL));
            Log.d(TAG, "dob Date of CA2" + cursor.getString(0));
        }
        if (cursor != null)
            cursor.close();

        db.close();
        // mCursor.close();
        if (dobDate == null) {
            dobDate = "";
        }/* else {
            dobDate = removeTimestamp(dobDate);
        }*/

        return dobDate;

    }

    /**
     * get LMP date for pregnant women  from PW
     */
    public String getLMPDate_PW(AssignDataModel asPeople) {
        String lmpDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + LMP_DATE_COL + " FROM " + REG_N_PW_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {

//            lmpDate = removeTimestamp(cursor.getString(cursor.getColumnIndex(LMP_DATE_COL)));
            lmpDate = cursor.getString(cursor.getColumnIndex(LMP_DATE_COL));

            Log.d(TAG, "Lmp Date " + cursor.getString(0));
        }
        if (cursor != null)
            cursor.close();

        db.close();
        // mCursor.close();

        return lmpDate;

    }


    public String getLMDate_LM(AssignDataModel asPeople) {
        String lmDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + LM_DATE_COL + " FROM " + REG_N_LM_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null && cursor.moveToFirst()) {

//            lmDate = removeTimestamp(cursor.getString(cursor.getColumnIndex(LM_DATE_COL)));
            lmDate = cursor.getString(cursor.getColumnIndex(LM_DATE_COL));
            Log.d(TAG, "LM date" + cursor.getString(0));
        }
        if (cursor != null)
            cursor.close();

        db.close();


        return lmDate;

    }


    public String getRegDate_RegNAsgProgSrv(AssignDataModel asPeople) {
        String regDate = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + REG_N_DAT_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor != null && mCursor.moveToFirst()) {
//            regDate = removeTimestamp(mCursor.getString(mCursor.getColumnIndex(REG_N_DAT_COL)));
            regDate = mCursor.getString(mCursor.getColumnIndex(REG_N_DAT_COL));
            Log.d(TAG, "Reg Date:" + mCursor.getString(0));
        }
        if (mCursor != null) {
            mCursor.close();
        }

        db.close();

        return regDate;

    }


    public ArrayList<GPS_SubGroupAttributeDataModel> getGpsSubGroupAttributes(String groupCode, String subGroupCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<GPS_SubGroupAttributeDataModel> alist = new ArrayList<GPS_SubGroupAttributeDataModel>();

        String selectQuery = "SELECT "
                + ATTRIBUTE_CODE_COL
                + " , " + ATTRIBUTE_TITLE_COL
                + " , " + DATA_TYPE_COL
                + " , " + LOOK_UP_CODE_COL
                + " FROM " + GPS_SUB_GROUP_ATTRIBUTES_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                GPS_SubGroupAttributeDataModel data = new GPS_SubGroupAttributeDataModel();
                data.setGroupCode(groupCode);
                data.setSubGroupCode(subGroupCode);
                data.setAttributeCode(cursor.getString(0));
                data.setAttributeTitle(cursor.getString(1));
                data.setDataType(cursor.getString(2));
                data.setLookUpCode(cursor.getString(3));

                alist.add(data);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return alist;
    }


    public ArrayList<Lup_gpsListDataModel> getLupGPSList(String groupCode, String subGroupCode, String attCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Lup_gpsListDataModel> list = new ArrayList<Lup_gpsListDataModel>();

        String selectQuery = "SELECT * "

                + " FROM " + LUP_GPS_LIST_TABLE
                + " WHERE " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Lup_gpsListDataModel data = new Lup_gpsListDataModel();
                data.setGroupCode(cursor.getString(0));
                data.setSubGroupCode(cursor.getString(1));
                data.setAttributeCode(cursor.getString(2));
                data.setLupValueCode(cursor.getString(3));
                data.setLupValueText(cursor.getString(4));


                list.add(data);

            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return list;
    }


    public ServiceSpecificDataModel getSrvSpecificByMemberId(String cCode, String donorCode, String awardCode
            , String programCode, String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {


        SQLiteDatabase db = this.getReadableDatabase();
        ServiceSpecificDataModel data = new ServiceSpecificDataModel();

        String selectQuery = SQLiteQuery.getSrvSpecificByMemberId_SelectQuery(cCode, donorCode, awardCode
                , programCode, srvCode, opCode, opMonthCode, srvCenterCode, fdpCode, mem15Id);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                data.setBabyStatus(cursor.getString(cursor.getColumnIndex(BABY_STATUS_COL)));
                data.setGmpAttendence(cursor.getString(cursor.getColumnIndex(GMP_ATTENDACE_COL)));
                data.setWeightStatus(cursor.getString(cursor.getColumnIndex(WEIGHT_STATUS_COL)));
                data.setNutAttendance(cursor.getString(cursor.getColumnIndex(NUT_ATTENDANCE_COL)));


                data.setVitaUnder5(cursor.getString(cursor.getColumnIndex(VITA_UNDER5_COL)));


                data.setExclCurrentLybf(cursor.getString(cursor.getColumnIndex(EXCLUSIVE_CURRENTLYBF_COL)));
                data.setDateComFeed(cursor.getString(cursor.getColumnIndex(DATE_COMPFEEDING_COL)));
                data.setCamRef(cursor.getString(cursor.getColumnIndex(CMAMREF_COL)));
                data.setCamAdd(cursor.getString(cursor.getColumnIndex(CMAMADD_COL)));
                data.setDateAncVisit(cursor.getString(cursor.getColumnIndex(ANCVISIT_COL)));

                data.setPncVisit2D(cursor.getString(cursor.getColumnIndex(PNCVISIT_2D_COL)));
                data.setPncVisit1W(cursor.getString(cursor.getColumnIndex(PNCVISIT_1W_COL)));
                data.setPncVisit6W(cursor.getString(cursor.getColumnIndex(PNCVISIT_6W_COL)));
                data.setDeliveryStaff_1(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_1_COL)));
                data.setDeliveryStaff_2(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_2_COL)));
                data.setDeliveryStaff_3(cursor.getString(cursor.getColumnIndex(DELIVERY_STAFF_3_COL)));

                data.setHomeSupport24H_1D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_1D_COL)));
                data.setHomeSupport24H_2D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_2D_COL)));
                data.setHomeSupport24H_3D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_3D_COL)));
                data.setHomeSupport24H_8D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_8D_COL)));


                data.setHomeSupport24H_14D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_14D_COL)));
                data.setHomeSupport24H_21D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_21D_COL)));
                data.setHomeSupport24H_30D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_30D_COL)));
                data.setHomeSupport24H_60D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_60D_COL)));
                data.setHomeSupport24H_90D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT24H_90D_COL)));


                data.setHomeSupport48H_1D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_1D_COL)));
                data.setHomeSupport48H_3D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_3D_COL)));
                data.setHomeSupport48H_8D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_8D_COL)));
                data.setHomeSupport48H_30D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_30D_COL)));
                data.setHomeSupport48H_60D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_60D_COL)));
                data.setHomeSupport48H_90D(cursor.getString(cursor.getColumnIndex(HOME_SUPPORT48H_90D_COL)));


                data.setMaternal_bleeed(cursor.getString(cursor.getColumnIndex(MATERNAL_BLEEDING_COL)));
                data.setMaternal_seizure(cursor.getString(cursor.getColumnIndex(MATERNAL_SEIZURE_COL)));
                data.setMaternal_infection(cursor.getString(cursor.getColumnIndex(MATERNAL_INFECTION_COL)));
                data.setMaternal_proLongedLabor(cursor.getString(cursor.getColumnIndex(MATERNAL_PROLONGEDLABOR_COL)));
                data.setMaternal_obstructedLabor(cursor.getString(cursor.getColumnIndex(MATERNAL_OBSTRUCTEDLABOR_COL)));
                data.setMaternal_pprm(cursor.getString(cursor.getColumnIndex(MATERNAL_PPRM_COL)));


                data.setnBorn_Aspyxia(cursor.getString(cursor.getColumnIndex(NBORN_ASPHYXIA_COL)));
                data.setnBorn_Sepsis(cursor.getString(cursor.getColumnIndex(NBORN_SEPSIS_COL)));
                data.setnBorn_HypoThermai(cursor.getString(cursor.getColumnIndex(NBORN_HYPOTHERMIA_COL)));
                data.setnBorn_HyperThermai(cursor.getString(cursor.getColumnIndex(NBORN_HYPERTHERMIA_COL)));
                data.setnBorn_noSuckling(cursor.getString(cursor.getColumnIndex(NBORN_NOSUCKLING_COL)));
                data.setnBorn_Jaundices(cursor.getString(cursor.getColumnIndex(NBORN_JAUNDICE_COL)));


                data.setChild_Diarrhea(cursor.getString(cursor.getColumnIndex(CHILD_DIARRHEA_COL)));
                data.setChild_Pneumonia(cursor.getString(cursor.getColumnIndex(CHILD_PNEUMONIA_COL)));
                data.setChild_Fever(cursor.getString(cursor.getColumnIndex(CHILD_FEVER_COL)));
                data.setChild_CerebralPalsy(cursor.getString(cursor.getColumnIndex(CHILD_CEREBRALPALSY_COL)));

                data.setImmu_Polio(cursor.getString(cursor.getColumnIndex(IMMU_POLIO_COL)));
                data.setImmu_BCG(cursor.getString(cursor.getColumnIndex(IMMU_BCG_COL)));
                data.setImmu_Measles(cursor.getString(cursor.getColumnIndex(IMMU_MEASLES_COL)));
                data.setImmu_DPT_HIB(cursor.getString(cursor.getColumnIndex(IMMU_DPT_HIB_COL)));
                data.setImmu_Lotta(cursor.getString(cursor.getColumnIndex(IMMU_LOTTA_COL)));
                data.setImmU_Other(cursor.getString(cursor.getColumnIndex(IMMU_OTHER_COL)));

                data.setFpCounsel_MaleCondom(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_MALECONDOM_COL)));
                data.setFpCounsel_FemaleCondom(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_FEMALECONDOM_COL)));
                data.setFpCounsel_Pill(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_PILL_COL)));
                data.setFpCounsel_Depo(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_DEPO_COL)));
                data.setFpCounsel_LongParmanent(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_LONGPARMANENT_COL)));
                data.setFpCounsel_NoMethod(cursor.getString(cursor.getColumnIndex(FPCOUNSEL_NOMETHOD_COL)));


                data.setCropCode(cursor.getString(cursor.getColumnIndex(CROP_CODE_COL)));
                data.setLoanSource(cursor.getString(cursor.getColumnIndex(LOAN_SOURCE_COL)));
                data.setLoanAMT(cursor.getString(cursor.getColumnIndex(LOAN_AMT_COL)));
                data.setAnimalCode(cursor.getString(cursor.getColumnIndex(ANIMAL_CODE_COL)));
                data.setLeadCode(cursor.getString(cursor.getColumnIndex(LEAD_CODE_COL)));


            }


            cursor.close();
            db.close();
        }


        return data;


    }


    public boolean isDataExitsInServiceSpecificTable(String cCode, String donorCode, String awardCode
            , String programCode
            , String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {
        SQLiteDatabase db = this.getReadableDatabase();

        boolean dataExits = false;

        String selectQuery = "SELECT * FROM " + SERVICE_SPECIFIC_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + PROGRAM_CODE_COL + " = '" + programCode + "'"
                + " AND  " + SERVICE_CODE_COL + " = '" + srvCode + "'"
                + " AND  " + OPERATION_CODE_COL + " = '" + opCode + "'"
                + " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'"
                + " AND  " + SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "'"
                + " AND  " + FDP_CODE_COL + " = '" + fdpCode + "'"
                + " AND  " + DISTRICT_CODE_COL + " || '' || " + UPCODE_COL + " || '' || " + UCODE_COL
                + " || '' || " + VCODE_COL + " || '' || " + HHID_COL + " || '' || " + HH_MEM_ID + " = '" + mem15Id + "'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;
    }


    public boolean isDataExitsInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean dataExits = false;

        String selectQuery = "SELECT  * "
                + " FROM " + GPS_LOCATION_ATTRIBUTES_TABLE
                + " WHERE "
                + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " = '" + locationCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;

    }


    public String getAttributeValuesGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String attValues = "";

        String selectQuery = "SELECT  " + ATTRIBUTE_VALUE_COL
                + " FROM " + GPS_LOCATION_ATTRIBUTES_TABLE
                + " WHERE "
                + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " = '" + locationCode + "' "
                + " AND " + ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                attValues = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }


        return attValues;

    }


    /**
     * @param query
     * @return
     */
    public long insertIntoUploadTable(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQL_QUERY_SYNTAX, query);
        values.put(SYNC_COL, "0");
        long id = db.insert(UPLOAD_SYNTAX_TABLE, null, values);
        Log.d(TAG, "inserted into Upload Table id:" + id);
        return id;

    }

    /**
     * @date : 2016-01-12
     * @modified:
     * @author : Faisal Mohammad
     * @email: nirzon192@gmail.com
     * @caller: Every upload Class
     * @status
     * @description : it will Update Sync flag SQL SERvEr Query
     */
    public int uploadStatusFlagOfUploadTable(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SYNC_COL, 1);
        int updateId = db.update(UPLOAD_SYNTAX_TABLE, values, ID_COL + " = ? ", new String[]{String.valueOf(id)});
        Log.d(TAG, "inserted into Upload Table id:" + updateId);
        return updateId;

    }

    /**
     * Used in Synchronize data in MainActivity
     * <p/>
     * for sql Query data
     */
    public ArrayList<dataUploadDB> getUploadSyntaxData() {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<dataUploadDB> allData = new ArrayList<dataUploadDB>();


        String selectQuery = "SELECT  * FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + SYNC_COL + "=0 "
                + " ORDER BY " + ID_COL + " ASC ";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                dataUploadDB data = new dataUploadDB();
                data._id = cursor.getString(cursor.getColumnIndex(ID_COL));
                data._syntax = cursor.getString(cursor.getColumnIndex(SQL_QUERY_SYNTAX));
                allData.add(data);

            } while (cursor.moveToNext());
        }
        db.close();
        return allData;
    }

    /**
     * date : 2015-10-17
     * <p/>
     * Faisal Mohammad
     * <p/>
     * SummaryAssignBaseCriteria.class
     * <p/>
     * description : base on the criteria this method will list of member which are assigned in particular Criteria or Service
     */

    public List<SummaryAssignListModel> getTotalAssignSummary(String cCode, String distCode, String upCode, String unCode, String vCode,
                                                              String donorCode, String awardCord, String prgCode, String srvCode) {
        // final String OP_CODE_FOR_SERVICE="2";
        List<SummaryAssignListModel> assignList = new ArrayList<SummaryAssignListModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.get_DetailsAssignedMemberSummarySelectQuery(cCode, distCode, upCode, unCode, vCode, donorCode, awardCord, prgCode, srvCode);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryAssignListModel assignedPeople = new SummaryAssignListModel();
                //srvL.setCustomId(cursor.getString(cursor.getColumnIndex(SERVICE_TABLE + "." + HHID_COL)));
                assignedPeople.setCustomId(cursor.getString(cursor.getColumnIndex("NewID")));
                //  srvL.setMemberId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID ))); //
                assignedPeople.setMemberName(cursor.getString(cursor.getColumnIndex("memberName")));
                assignedPeople.setRegDate(cursor.getString(cursor.getColumnIndex("regDate")));
                /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                 * use mm-- dd--YYYY*/
               /* String temp = cursor.getString(cursor.getColumnIndex("regDate"));
                String dateformat = "";
                if (temp.length() > 5) {
                    temp = temp.substring(0, 10);
                    dateformat = "";
                    dateformat = dateformat + temp.substring(5, 7) + "-" + temp.substring(8, 10) + "-" + temp.substring(0, 4);
                } else {
                    dateformat = temp;
                }

                assignedPeople.setRegDate(dateformat);*/

                assignList.add(assignedPeople);
                //   Log.d(TAG, " Assigne summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return assignList;

    }

    /**
     * :
     * 2015-11-07
     * <p/>
     * :
     */
    public String getMemberName(String cCode, String disCode,
                                String upCode, String unCode,
                                String vCode, String hhID, String memID
    ) {
        String memberName = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT   " + MEM_NAME_COL + "  " +
                " FROM " + REGISTRATION_MEMBER_TABLE + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DISTRICT_NAME_COL + " = '" + disCode + "' " +
                " AND " + UPZILLA_NAME_COL + " = '" + upCode + "' " +
                " AND " + UNITE_NAME_COL + " = '" + unCode + "' " +
                " AND " + VILLAGE_NAME_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    memberName = cursor.getString(cursor.getColumnIndex(MEM_NAME_COL));
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getMemberName() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();

        return memberName;
    }


    public List<SummaryGroupListDataModel> getGroupSummaryList(String cCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<SummaryGroupListDataModel> list = new ArrayList<SummaryGroupListDataModel>();

        String sql = SQLiteQuery.getGroupSummaryList_sql(cCode);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SummaryGroupListDataModel data = new SummaryGroupListDataModel();
                data.setcCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                data.setDonorCode(cursor.getString(cursor.getColumnIndex(DONOR_CODE_COL)));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex(AWARD_CODE_COL)));
                data.setProgramCode(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));

                data.setGroupCatCode(cursor.getString(cursor.getColumnIndex(GROUP_CAT_CODE_COL)));
                data.setGroupCatShortName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_SHORT_NAME_COL)));
                data.setGroupCode(cursor.getString(cursor.getColumnIndex(GROUP_CODE_COL)));

                data.setGroupName(cursor.getString(cursor.getColumnIndex(GROUP_NAME_COL)));
                data.setSrvShortName(cursor.getString(cursor.getColumnIndex(SERVICE_SHORT_NAME_COL)));
                data.setCount(cursor.getString(cursor.getColumnIndex("c")));

                list.add(data);

            } while (cursor.moveToNext());
        }
        return list;
    }


    public List<SummaryIdListInGroupDataModel> getIdListInGroupInGroupSummary(String cCode, String donorCode, String awardCode, String prgCode, String grpCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<SummaryIdListInGroupDataModel> list = new ArrayList<SummaryIdListInGroupDataModel>();


        String sql = SQLiteQuery.getIdListInGroupInGroupSummary_sql(cCode, donorCode, awardCode, prgCode, grpCode);


        // SQLiteQuery.getGroupSummaryList_sql(cCode);

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SummaryIdListInGroupDataModel data = new SummaryIdListInGroupDataModel();
                data.setnMemId(cursor.getString(cursor.getColumnIndex("idMem")));
                data.setMemName(cursor.getString(cursor.getColumnIndex("memName")));
                data.setSrvName(cursor.getString(cursor.getColumnIndex(SERVICE_SHORT_NAME_COL)));


                list.add(data);

            } while (cursor.moveToNext());
        }
        return list;
    }


    public String getAwardGraduation(String cCode, String donorCode, String awardCode) {
        String grdDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + AWARD_END_DATE_COL +
                " FROM " + ADM_AWARD_TABLE +
                " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' ";


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grdDate = cursor.getString(0);
            }

            cursor.close();

            db.close();
        }


        return grdDate;
    }


    /**
     * @param cCode
     * @param disCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhID
     * @param memID
     * @param donorCode
     * @param awardCode
     * @param progCode
     * @return
     * @since 2015-11-07
     */

    public String getProgramGraduationDateOfMember(String cCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String donorCode, String awardCode, String progCode) {
        String grdDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getProgramGraduationDateOfMember_sql(cCode, disCode, upCode, unCode, vCode, hhID, memID, donorCode, awardCode, progCode);

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
                grdDate = cursor.getString(cursor.getColumnIndex("grdDate"));
                /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                 * use mm-- dd--YYYY*/
                String dateformat = "";
                if (grdDate.length() > 5) {
                    grdDate = grdDate.substring(0, 10);
                    dateformat = "";
                    dateformat = dateformat + grdDate.substring(5, 7) + "-" + grdDate.substring(8, 10) + "-" + grdDate.substring(0, 4);
                    grdDate = dateformat;
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getProgramGraduationDateOfMember get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


        db.close();


        return grdDate;
    }

    /**
     * @param assignDataModel
     * @param elderleyNy
     * @param regDate         TODO: AGR ELDERLEY
     */
    public int edtAssignAgerIn_Elderley(AssignDataModel assignDataModel, String elderleyNy, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(ELDERLY_YN_COL, elderleyNy);
        String query = COUNTRY_CODE + " = '" + assignDataModel.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";
        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        db.close();
        return id;
    }

    /**
     * @param asPeople AssignDataModel
     * @param lmpDate  lmpDate
     * @return no of affected rows
     */

    public int editMemberIn_PW(AssignDataModel asPeople, String lmpDate) {
        SQLiteDatabase db = this.getWritableDatabase();


        String sql = COUNTRY_CODE + " = '" + asPeople.getCountryCode() + "' "
                + " AND " + DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' "
                + " AND " + UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' "
                + " AND " + UCODE_COL + " = '" + asPeople.getUnitCode() + "' "
                + " AND " + VCODE_COL + " = '" + asPeople.getVillageCode() + "' "
                + " AND " + HHID_COL + " = '" + asPeople.getHh_id() + "'"
                + " AND " + HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, asPeople.getRegNDate());
        values.put(LMP_DATE_COL, lmpDate);
        values.put(GRD_CODE_COL, asPeople.getGrdCode());
        values.put(PW_GRD_DATE_COL, asPeople.getGrdDate());
        values.put(ENTRY_DATE, asPeople.getEntryDate());
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(SYNC_COL, "0");


        // updating row
        int id = db.update(REG_N_PW_TABLE, values, sql, null);
        db.close();
        return id;
    }


    /**
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param disttCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhID
     * @param memID
     * @param progCode
     * @param srvCode
     * @param grpCode
     * @param active
     * @param entryBy
     * @param entryDate
     * @return
     */

    public void editMemberIn_RegNmemProgGroup(String cCode, String donorCode, String awardCode, String disttCode, String upCode
            , String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode, String grpCode, String active, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();


        String sql = COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + "= '" + donorCode + "'"
                + " AND " + AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + DISTRICT_CODE_COL + "= '" + disttCode + "'"
                + " AND " + UPCODE_COL + "= '" + upCode + "'"
                + " AND " + UCODE_COL + "= '" + unCode + "'"
                + " AND " + VCODE_COL + "= '" + vCode + "'"
                + " AND " + HHID_COL + "= '" + hhID + "'"
                + " AND " + HH_MEM_ID + "=  '" + memID + "'"
                + " AND " + PROGRAM_CODE_COL + "=  '" + progCode + "'"
                + " AND " + SERVICE_CODE_COL + "=  '" + srvCode + "'";


        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode);
        values.put(ACTIVE_COL, active);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        // updating row
        int id = db.update(REG_N_MEM_PROG_GRP_TABLE, values, sql, null);
        Log.d(TAG, "row affected in " + REG_N_MEM_PROG_GRP_TABLE + ": " + id);
        db.close();

    }

    /**
     * @param assignDataModel
     * @param landSize
     * @param willingness
     * @param dependOnGruney
     * @param regDate         TODO: AGR-PG
     */
    public int edtAssignAgerIn_PG(AssignDataModel assignDataModel, String landSize, String willingness, String dependOnGruney, String regDate, String invc, String nasfm, String cu, String other, int goat, int chicken, int pigion, int other_sp) {
        SQLiteDatabase db = this.getWritableDatabase();


        String query = COUNTRY_CODE + " = '" + assignDataModel.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(DEPEND_ON_GANYU_COL, dependOnGruney);
        values.put(WILLINGNESS_COL, willingness);
        values.put(AG_INVC, invc);
        values.put(AG_NASFAM, nasfm);
        values.put(AG_CU, cu);
        values.put(AG_OTHER, other);

        values.put(AG_L_S_GOAT, goat);
        values.put(AG_L_S_CHICKEN, chicken);
        values.put(AG_L_S_PIGION, pigion);
        values.put(AG_L_S_OTHER, other_sp);

        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }

    /**
     * @param assignDataModel
     * @param landSize
     * @param willingness
     * @param winterCultivation
     * @param regDate           TODO: ELDERLEY-IG
     */
    public int edtAssignAgerIn_IG(AssignDataModel assignDataModel, String landSize, String willingness, String winterCultivation, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(WINTER_CULTIVATION_COL, winterCultivation);
        values.put(WILLINGNESS_COL, willingness);
        String query = COUNTRY_CODE + " = '" + assignDataModel.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";
        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }

    /**
     * @param assignDataModel
     * @param landSize
     * @param willingness
     * @param vurnarableHH
     * @param regDate         TODO: AGR-LG
     */
    public int edtAssignAgerIn_LG(AssignDataModel assignDataModel, String landSize, String willingness, String vurnarableHH, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(VULNERABLE_HH_COL, vurnarableHH);
        values.put(WILLINGNESS_COL, willingness);
        String query = COUNTRY_CODE + " = '" + assignDataModel.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";
        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }

    /**
     * @param assignDataModel
     * @param landSize
     * @param willingness
     * @param plantingVC
     * @param regDate         TODO: AGR-MG
     */
    public int edtAssignAgerIn_MG(AssignDataModel assignDataModel, String landSize, String willingness, String plantingVC, String regDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, regDate);
        values.put(LAND_SIZE_COL, landSize);
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, plantingVC);
        values.put(WILLINGNESS_COL, willingness);
        String query = COUNTRY_CODE + " = '" + assignDataModel.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + assignDataModel.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + assignDataModel.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + assignDataModel.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + assignDataModel.getVillageCode() + "' AND " +
                HHID_COL + " = '" + assignDataModel.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + assignDataModel.getMemId() + "'  ";
        int id = db.update(REG_N_AGR_TABLE, values, query, null);
        return id;
    }

    /**
     * @date :2015-11-06
     * @modified: 2015-11-07
     * @author : Faisal mohamad
     * @status
     * @description : get Serial no from MemberCardRequestTable
     */
    public String getMaxCardRequesSl(String cCode, String donorCode,
                                     String awardCode, String disCode,
                                     String upCode, String unCode,
                                     String vCode, String hhID,
                                     String memID, String rptGroup,
                                     String reportCode) {
        String lasSl = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT CASE WHEN MAX( " + CARD_REQUEST_SL_COL + " ) IS NULL THEN '0' ELSE " + CARD_REQUEST_SL_COL + " END AS SL" +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + disCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    lasSl = cursor.getString(cursor.getColumnIndex("SL"));
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "In getMaxCardRequesSl() method Exeption " + e);
        } finally {
            if (cursor != null)
                cursor.close();
        }


        db.close();

        if (lasSl.length() > 0) {
            int padd = 3 - lasSl.length();
            for (int i = 0; i < padd; i++) {
                lasSl = "0" + lasSl;
            }
        } else {
            lasSl = "000";
        }


        return lasSl;
    }

    /**
     * @param cCode    String
     * @param distCode String
     * @param upCode   String
     * @param unCode   String
     * @param vCode    String
     * @param hhID     String
     * @param mmId     String
     * @return AGR_DataModel
     * Data Check for AGR Table
     */
    public AGR_DataModel checkAssignCriteriaInAGR_TableForMalwai(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId, boolean impelmetedinMain) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.checkDataExitsQueryInAGR_TableAssignForMalwai(cCode, distCode, upCode, unCode, vCode, hhID, mmId, impelmetedinMain);
        Cursor cursor = db.rawQuery(selectQuery, null);
        AGR_DataModel agr_dataModel = new AGR_DataModel();
// default value
        agr_dataModel.setElderleyYN("N");
        agr_dataModel.setLandSize("00");
        agr_dataModel.setDepenonGanyu("N");
        agr_dataModel.setWillingness("N");
        agr_dataModel.setWinterCultivation("N");
        agr_dataModel.setVulnerableHh("N");
        agr_dataModel.setPlantingVcrop(null);
        Log.d(TAG, "In check AssignCriteria In AGR _ Table For Malwai");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Log.d(TAG, " data Exits In check AssignCriteria In AGR _ Table For Malwai");
                agr_dataModel.setElderleyYN(cursor.getString(cursor.getColumnIndex(ELDERLY_YN_COL)));
                //  Log.d(TAG, "AGR_DataModel getElderleyYN: " + agr_dataModel.getElderleyYN());
                agr_dataModel.setLandSize(cursor.getString(cursor.getColumnIndex(LAND_SIZE_COL)));
                //  Log.d(TAG, "AGR_DataModel getLandSize: " + agr_dataModel.getLandSize());
                agr_dataModel.setDepenonGanyu(cursor.getString(cursor.getColumnIndex(DEPEND_ON_GANYU_COL)));
                agr_dataModel.setWillingness(cursor.getString(cursor.getColumnIndex(WILLINGNESS_COL)));
                // Log.d(TAG, "AGR_DataModel getWillingness: " + agr_dataModel.getWillingness());
                agr_dataModel.setWinterCultivation(cursor.getString(cursor.getColumnIndex(WINTER_CULTIVATION_COL)));
                agr_dataModel.setVulnerableHh(cursor.getString(cursor.getColumnIndex(VULNERABLE_HH_COL)));
                agr_dataModel.setPlantingVcrop(cursor.getString(cursor.getColumnIndex(PLANTING_VALUE_CHAIN_CROP_COL)));
                agr_dataModel.setRegnDate(cursor.getString(cursor.getColumnIndex(REG_N_DAT_COL)));

                /** new */
                agr_dataModel.setAgInvc(cursor.getString(cursor.getColumnIndex(AG_INVC)));
                agr_dataModel.setAgNasfam(cursor.getString(cursor.getColumnIndex(AG_NASFAM)));
                agr_dataModel.setAgCu(cursor.getString(cursor.getColumnIndex(AG_CU)));
                agr_dataModel.setAgOrther(cursor.getString(cursor.getColumnIndex(AG_OTHER)));
                agr_dataModel.setIntGoat(cursor.getInt(cursor.getColumnIndex(AG_L_S_GOAT)));
                agr_dataModel.setIntChicken(cursor.getInt(cursor.getColumnIndex(AG_L_S_CHICKEN)));
                agr_dataModel.setIntPegion(cursor.getInt(cursor.getColumnIndex(AG_L_S_PIGION)));
                agr_dataModel.setIntOther(cursor.getInt(cursor.getColumnIndex(AG_L_S_OTHER)));
                Log.d("MOR1", "agr_dataModel.getAgInvc()=" + agr_dataModel.getAgInvc()
                        + agr_dataModel.getAgNasfam() + agr_dataModel.getAgCu()
                        + agr_dataModel.getAgOrther() + agr_dataModel.getIntGoat()
                        + agr_dataModel.getIntChicken() + agr_dataModel.getIntPegion()
                        + agr_dataModel.getIntOther());


            }
            cursor.close();
        }
        db.close();
        return agr_dataModel;
    }

    /**
     * @since :2015-11-09
     * <p/>
     * <p/>
     * <p/>
     * get DalivaryStatus no from MemberCardRequestTable
     */
    public String getCardDeliveryStatus(String cCode, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String rptGroup, String reportCode, String requestSl) {
        String cardDelivaryStatus = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN " + DELIVERY_STATUS_COL + " " +
                " IS NULL THEN 'N' ELSE " + DELIVERY_STATUS_COL + " END AS deliveryStatus " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + disCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardDelivaryStatus = cursor.getString(cursor.getColumnIndex("deliveryStatus"));

                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getCardDeliveryStatus() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardDelivaryStatus;
    }

    /**
     * @date :2015-11-07
     * @modified:
     * @author : Faisal mohamad
     * @status
     * @description : get Serial no from MemberCardRequestTable
     */
    public String getCardDeliveryDate(String cCode, String donorCode,
                                      String awardCode, String disCode,
                                      String upCode, String unCode,
                                      String vCode, String hhID,
                                      String memID, String rptGroup,
                                      String reportCode, String requestSl) {
        String cardPrintDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + DELIVERY_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + DELIVERY_DATE_COL + " END AS deliveryDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + disCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardPrintDate = cursor.getString(cursor.getColumnIndex("deliveryDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                    String dateformat = "";
                    if (cardPrintDate.length() > 5 && !cardPrintDate.equals("No Data found")) {
                        cardPrintDate = cardPrintDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardPrintDate.substring(5, 7) + "-" + cardPrintDate.substring(8, 10) + "-" + cardPrintDate.substring(0, 4);
                        cardPrintDate = dateformat;
                    }
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardPrintDate;
    }


    /**
     * @date :2015-11-07
     * @modified:
     * @author : Faisal mohamad
     * @status
     * @description : get Serial no from MemberCardRequestTable
     */
    public String getCardPrintDate(String cCode, String donorCode, String awardCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String rptGroup,
                                   String reportCode, String requestSl) {
        String cardPrintDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + PRINT_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + PRINT_DATE_COL + " END AS printDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + disCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardPrintDate = cursor.getString(cursor.getColumnIndex("printDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                   /* String dateformat = "";
                    if (cardPrintDate.length() > 5 && !cardPrintDate.equals("No Data found")) {
                        cardPrintDate = cardPrintDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardPrintDate.substring(5, 7) + "-" + cardPrintDate.substring(8, 10) + "-" + cardPrintDate.substring(0, 4);
                        cardPrintDate = dateformat;
                    }*/
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardPrintDate;
    }

    /**
     * @date :2015-11-09
     * @modified:
     * @author : Faisal mohamad
     * @status
     * @description :Update Card RequestDate from MemberCardRequestTable
     */

    public long addCardDelivaryDate(String c_code, String donorCode, String awardCode,
                                    String disCode, String upCode, String unCode,
                                    String vCode, String hhid, String memid,
                                    String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                    String deliveryDate, String deliveryBy) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(DELIVERY_DATE_COL, deliveryDate);
        values.put(DELIVERY_BY_COL, deliveryBy);
        values.put(DELIVERY_STATUS_COL, "Y");
        values.put(SYNC_COL, "0");


        String query = COUNTRY_CODE + " = '" + c_code + "' AND " +
                DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                DISTRICT_CODE_COL + " = '" + disCode + "' AND " +
                UPCODE_COL + " = '" + upCode + "' AND " +
                UCODE_COL + " = '" + unCode + "' AND " +
                VCODE_COL + " = '" + vCode + "' AND " +
                HHID_COL + " = '" + hhid + "' AND " +
                HH_MEM_ID + " = '" + memid + "'AND  " +
                REPORT_GROUP_COL + " = '" + reportGroupCode + "'AND  " +
                REPORT_CODE_COL + " = '" + reportCode + "'AND  " +
                CARD_REQUEST_SL_COL + " = '" + cRequestSl + "'AND  " +
                CARD_PRINT_REASON_CODE_COL + " = '" + cpReasonCode + "' ";


        // updating row
        int id = db.update(MEMBER_CARD_PRINT_TABLE, values, query, null);
        Log.d(TAG, "update Card Member Card Print's DelevaryDate  of MemberCardPrint Table: " + id);


        return id;


    }

    /**
     * @date :2015-11-09
     * @modified:
     * @author : Faisal mohamad
     * @status
     * @description : get Insert Card RequestDate from MemberCardRequestTable
     */

    public long addCardRequestDataFromOnline(String c_code, String donorCode, String awardCode,
                                             String disCode, String upCode, String unCode,
                                             String vCode, String hhid, String memid,
                                             String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                             String requestDate, String printDate, String printBy, String deliveryDate, String deliveryBy,
                                             String deliveryStatus, String entryBy,
                                             String entryDate) {


        // TODO :: photo
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(DISTRICT_CODE_COL, disCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);

        values.put(HHID_COL, hhid); // Registration id
        values.put(HH_MEM_ID, memid); // member id


        values.put(REPORT_GROUP_COL, reportGroupCode);
        values.put(REPORT_CODE_COL, reportCode);
        values.put(CARD_REQUEST_SL_COL, cRequestSl);
        values.put(CARD_PRINT_REASON_CODE_COL, cpReasonCode);
        values.put(REQUEST_DATE_COL, requestDate);
        values.put(PRINT_DATE_COL, printDate);
        values.put(PRINT_BY_COL, printBy);
        values.put(DELIVERY_DATE_COL, deliveryDate);
        values.put(DELIVERY_BY_COL, deliveryBy);

        values.put(DELIVERY_STATUS_COL, deliveryStatus);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, "1");

        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(MEMBER_CARD_PRINT_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "New Member Card Print data added into MemberCardPrint Table: " + id);
        return id;
    }


    /**
     * @date :2015-11-09
     * @modified:
     * @author : Faisal mohamad
     * @status
     * @description : get Insert Card RequestDate from MemberCardRequestTable
     */

    public long addCardRequestDate(String c_code, String donorCode, String awardCode,
                                   String disCode, String upCode, String unCode,
                                   String vCode, String hhid, String memid,
                                   String reportGroupCode, String reportCode, String cRequestSl, String cpReasonCode,
                                   String requestDate, String entryBy,
                                   String entryDate) {


        // TODO :: photo
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(DISTRICT_CODE_COL, disCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);

        values.put(HHID_COL, hhid); // Registration id
        values.put(HH_MEM_ID, memid); // member id


        values.put(REPORT_GROUP_COL, reportGroupCode);
        values.put(REPORT_CODE_COL, reportCode);
        values.put(CARD_REQUEST_SL_COL, cRequestSl);
        values.put(CARD_PRINT_REASON_CODE_COL, cpReasonCode);
        values.put(REQUEST_DATE_COL, requestDate);

        values.put(DELIVERY_STATUS_COL, "N");
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, "0");


        //  values.put(GRD_DATE_COL,"00"); // GDR_Date


        // Inserting Row
        long id = db.insert(MEMBER_CARD_PRINT_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "New Member Card Print data added into MemberCardPrint Table: " + id);
        return id;
    }


    /**
     * @date :2015-11-07
     * @modified: 2015-11-07
     * @author : Faisal mohamad
     * @status
     * @description : get Serial no from MemberCardRequestTable
     */
    public String getCardRequestDate(String cCode, String donorCode,
                                     String awardCode, String disCode,
                                     String upCode, String unCode,
                                     String vCode, String hhID,
                                     String memID, String rptGroup,
                                     String reportCode, String requestSl) {
        String cardRequestDate = "";
        //String temp="";

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  CASE WHEN MAX(" + REQUEST_DATE_COL + ")" +
                " IS NULL THEN '' ELSE " + REQUEST_DATE_COL + " END AS requestDate " +
                " FROM " + MEMBER_CARD_PRINT_TABLE + " WHERE " + CARD_REQUEST_SL_COL + " = '" + requestSl + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + disCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + HHID_COL + " = '" + hhID + "' " +
                " AND " + HH_MEM_ID + " = '" + memID + "' " +
                " AND " + REPORT_GROUP_COL + " = '" + rptGroup + "' " +
                " AND " + REPORT_CODE_COL + " = '" + reportCode + "' ";
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    cardRequestDate = cursor.getString(cursor.getColumnIndex("requestDate"));
                    /** @tips: For Removing timestamp 2015-06-06 00:00:00.00 to 2015-06-06
                     * use mm-- dd--YYYY*/
                    String dateformat = "";
                    if (cardRequestDate.length() > 5 && !cardRequestDate.equals("No Data found")) {
                        cardRequestDate = cardRequestDate.substring(0, 10);
                        dateformat = "";
                        dateformat = dateformat + cardRequestDate.substring(5, 7) + "-" + cardRequestDate.substring(8, 10) + "-" + cardRequestDate.substring(0, 4);
                        cardRequestDate = dateformat;
                    }
                }

            }
        } catch (NullPointerException e) {
            Log.e(TAG, "in getCardRequestDate() get Exception " + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        db.close();

        return cardRequestDate;
    }


    /**
     * @description : LIST OF ID THAT GET SERVICE  OF VOUCHER PROGRAM
     */

    public List<SummaryServiceListModel> getTotalSerDistItemizeAttendanceSummary(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec, String srvDistFlag) {
        // final String OP_CODE_FOR_SERVICE = "2";
        List<SummaryServiceListModel> srvRecvList = new ArrayList<SummaryServiceListModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        // String selectQuery = SQLiteQuery.getTotalServiceAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode);
        String selectQuery;

        if (srvDistFlag.equals(KEY.SRV_FLAG)) {

            selectQuery = SQLiteQuery.getTotal_Service_Itemize_AttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode/*, String srvCode*/, vouItSpec);

        } else {


            selectQuery = SQLiteQuery.getTotal_Distribution_Itemize_AttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode/*, String srvCode*/, vouItSpec);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryServiceListModel srvL = new SummaryServiceListModel();

                srvL.setCustomId(cursor.getString(0));
                srvL.setServiceCount(cursor.getString(1));
                srvL.setPer_unit_cost(cursor.getString(2));

                srvRecvList.add(srvL);
                //    Log.d(TAG, " Service summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return srvRecvList;

    }


    /**
     * @description : LIST OF ID THAT GET SERVICE
     */

    public List<SummaryServiceListModel> getTotalServiceNDistributionAttendanceSummary(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag, String srvORDistFlag) {
        final String OP_CODE_FOR_SERVICE = "2";
        List<SummaryServiceListModel> srvRecvList = new ArrayList<SummaryServiceListModel>();
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery;
        if (srvORDistFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.getTotalDistributionAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode, distFlag);
        } else {
            selectQuery = SQLiteQuery.getTotalServiceAttendanceSummary_SelectQuery(cCode, donorCode, awardCord, opMCode, prgCode, srvCode, distFlag);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryServiceListModel srvL = new SummaryServiceListModel();

                srvL.setCustomId(cursor.getString(cursor.getColumnIndex("NewID")));

                srvL.setServiceCount(cursor.getString(1));
                srvL.setMemberName(cursor.getString(2)); // member  name
                srvL.setStatus(cursor.getString(cursor.getColumnIndex("status"))); // member  name

                srvRecvList.add(srvL);
                //    Log.d(TAG, " Service summary List : " + cursor.getString(0) + " : " + cursor.getString(1) + " : " + cursor.getString(2) + " : " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return srvRecvList;

    }


    /**
     * @since : 2015-10-15 m:2015-10-19
     * <p/>
     * <p/>
     * this method load Assign  Criteria for Assigne Summary Criteria
     */

    public List<SummaryCriteriaModel> getAssignCriteriaList(String cCode, String distCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String progCode) {//, String opMCode) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();//List<SummaryCriteriaModel>();
        SQLiteDatabase db = this.getReadableDatabase();//Database();
        String selectQuery = SQLiteQuery.getAssignCriteriaListSelectQuery(cCode, donorCode, awardCode, progCode, distCode, upCode, unCode, vCode);

        Cursor cursor = db.rawQuery(selectQuery, null);
        //  SummaryCriteriaModel crite = new SummaryCriteriaModel();
        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("AssignCount"))));
                criteriaList.add(crite);
                Log.d(TAG, " Service summary Criteria : " + cursor.getString(0) + ",IdCriteria : " + cursor.getString(1) + ", AssignCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }

    /*// use in AGR Module
    public void insertDataInto_RegNAgrTable(String c_code, String d_code, String upname, String uname, String vname, String hhiD,
                                           String hhMemId, String regnDate, String elderleyYN, String landSize, String depenonGanyu,
                                           String willingness, String winterCultivation, String vulnerableHh, String plantingVcrop, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code);
        values.put(DISTRICT_CODE_COL, d_code);
        values.put(UPCODE_COL, upname);
        values.put(UCODE_COL, uname);
        values.put(VCODE_COL, vname);
        values.put(HHID_COL, hhiD);
        values.put(HH_MEM_ID, hhMemId);
        values.put(REG_N_DAT_COL, regnDate);
        values.put(ELDERLY_YN_COL, elderleyYN);
        values.put(LAND_SIZE_COL, landSize);
        values.put(DEPEND_ON_GANYU_COL, depenonGanyu);
        values.put(WILLINGNESS_COL, willingness);
        values.put(WINTER_CULTIVATION_COL, winterCultivation);
        values.put(VULNERABLE_HH_COL, vulnerableHh);
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, plantingVcrop);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        db.insert(REG_N_AGR_TABLE, null, values);

        db.close();
    }*//*REG_N_AGR_TABLE*/
    public void addInLupSrvOptionListFromOnline(String countryCode, String progCode, String srvCode, String optionCode, String optionName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, srvCode);
        values.put(LUP_OPTION_CODE_COL, optionCode);
        values.put(LUP_OPTION_NAME_COL, optionName);

        db.insert(LUP_SRV_OPTION_LIST_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemTableFromOnline(String catCode, String itemCode, String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(ITEM_NAME_COL, itemName);


        db.insert(VOUCHER_ITEM_TABLE, null, values);

        db.close();

    }

    public void addVoucherItemMeasFromOnline(String measRCode, String uniteMeas, String measeTitle) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAS_R_CODE_COL, measRCode);
        values.put(UNITE_MEAS_COL, uniteMeas);
        values.put(MEASE_TITLE_COL, measeTitle);


        db.insert(VOUCHER_ITEM__MEAS_TABLE, null, values);

        db.close();

    }


    public void addVoucherCountryProgItemFromOnline(String countryCode, String donorCode, String awardCode,
                                                    String progCode, String srvCode, String catCode,
                                                    String itemCode, String mearCode, String itemSpec,
                                                    String uniteCost, String active, String currency
    ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, srvCode);
        values.put(CATEGORY_CODE_COL, catCode);
        values.put(ITEM_CODE_COL, itemCode);
        values.put(MEAS_R_CODE_COL, mearCode);
        values.put(VOUCHER_ITEM_SPEC_COL, itemSpec);
        values.put(UNITE_COST_COL, uniteCost);
        values.put(ACTIVE_COL, active);
        values.put(CURRENCY_COL, currency);


        db.insert(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE, null, values);

        db.close();

    }


    public void addGPS_SubGroupAttributesFromOnline(String groupCode, String subGroupCode, String attributeCode, String attributeTitle, String dataType, String lupTableName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(ATTRIBUTE_TITLE_COL, attributeTitle);
        values.put(DATA_TYPE_COL, dataType);
        values.put(LOOK_UP_CODE_COL, lupTableName);

        db.insert(GPS_SUB_GROUP_ATTRIBUTES_TABLE, null, values);

        db.close();

    }


    public void addLUP_GPS_TableFromOnline(String groupCode, String subGroupCode, String attributeCode, String lookUpCode, String lookUpName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(LOOK_UP_CODE_COL, lookUpCode);
        values.put(LOOK_UP_NAME_COL, lookUpName);

        db.insert(LUP_GPS_TABLE, null, values);

        db.close();

    }

    /**
     * @param cCode
     * @param groupCode
     * @param subGroupCode
     * @param locationCode
     * @param attributeCode
     * @param attributeValue
     */
    public void addGPSLocationAttributesFromOnline(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo) {
        String entryBy = "";
        String entryDate = "";
        addGPSLocationAttributes(cCode, groupCode, subGroupCode, locationCode, attributeCode, attributeValue, photo, entryBy, entryDate);
    }


    /**
     * @param cCode
     * @param groupCode
     * @param subGroupCode
     * @param locationCode
     * @param attributeCode
     * @param attributeValue
     * @param entryBy
     * @param entryDate
     */
    public void addGPSLocationAttributes(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode, String attributeValue, String photo, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, cCode);
        values.put(GROUP_CODE_COL, groupCode);
        values.put(SUB_GROUP_CODE_COL, subGroupCode);
        values.put(LOCATION_CODE_COL, locationCode);
        values.put(ATTRIBUTE_CODE_COL, attributeCode);
        values.put(ATTRIBUTE_VALUE_COL, attributeValue);
        values.put(ATTRIBUTE_PHOTO_COL, photo);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        db.insert(GPS_LOCATION_ATTRIBUTES_TABLE, null, values);

        db.close();

    }

    public boolean isDataExistsInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + GPS_LOCATION_ATTRIBUTES_TABLE + " WHERE " +

                COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                GROUP_CODE_COL + " = '" + groupCode + "' AND " +
                SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' AND " +
                LOCATION_CODE_COL + " = '" + locationCode + "' AND " +
                ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else
            return false;


    }


    public GPS_LocationAttributeDataModel getDataFromInGpsLocationAttributesTable(String cCode, String groupCode, String subGroupCode, String locationCode, String attributeCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        GPS_LocationAttributeDataModel data = new GPS_LocationAttributeDataModel();
        String sql = "SELECT * FROM " + GPS_LOCATION_ATTRIBUTES_TABLE + " WHERE " +

                COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                GROUP_CODE_COL + " = '" + groupCode + "' AND " +
                SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' AND " +
                LOCATION_CODE_COL + " = '" + locationCode + "' AND " +
                ATTRIBUTE_CODE_COL + " = '" + attributeCode + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                data.setCountryCode(cursor.getString(0));
                data.setGroupCode(cursor.getString(1));
                data.setSubGroupCode(cursor.getString(2));
                data.setLocationCode(cursor.getString(3));
                data.setAttributeCode(cursor.getString(4));
                data.setAttributeValue(cursor.getString(5));


            }

        }
        return data;

    }


    public void addCommunityGroup(String cCode, String donorCode, String awardCode, String progCode, String groupCode,
                                  String groupName, String groupCatCode, String distCode, String upCode, String srvCenterCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, cCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, progCode);
        values.put(GROUP_CODE_COL, groupCode);
        values.put(GROUP_NAME_COL, groupName);
        values.put(GROUP_CAT_CODE_COL, groupCatCode);
        values.put(DISTRICT_CODE_COL, distCode);
        values.put(UPCODE_COL, upCode);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);

        db.insert(COMMUNITY_GROUP_TABLE, null, values);

        db.close();

    }


    public void addInRegNAgrTableFromOnline(AGR_DataModel onlineData, String invc, String nasfm, String cu, String other, int goat, int chicken, int pegion, int other_sp) {
        onlineData.setEntryBy("");
        onlineData.setEntryDate("");
        //TODO: FIX IT LATTER
        insertDataInto_RegNAgrTable(onlineData, invc, nasfm, cu, other, goat, chicken, pegion, other_sp);

    }

    public static final String AG_INVC = "AGOINVC";
    public static final String AG_NASFAM = "AGONASFAM";
    public static final String AG_CU = "AGOCU";
    public static final String AG_OTHER = "AGOOther";
    public static final String AG_L_S_GOAT = "LSGoat";
    public static final String AG_L_S_CHICKEN = "LSChicken";
    public static final String AG_L_S_PIGION = "LSPigeon";
    public static final String AG_L_S_OTHER = "LSOther";

    public void insertDataInto_RegNAgrTable(AGR_DataModel data, String invc, String nasfm, String cu, String other, int goat, int chicken, int pegion, int other_sp) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, data.getCountryCode());
        values.put(DISTRICT_CODE_COL, data.getDistrictCode());
        values.put(UPCODE_COL, data.getUpazillaCode());
        values.put(UCODE_COL, data.getUnitCode());
        values.put(VCODE_COL, data.getVillageCode());
        values.put(HHID_COL, data.getHhId());
        values.put(HH_MEM_ID, data.getHhMemId());
        values.put(REG_N_DAT_COL, data.getRegnDate());
        values.put(ELDERLY_YN_COL, data.getElderleyYN());
        values.put(LAND_SIZE_COL, data.getLandSize());
        values.put(DEPEND_ON_GANYU_COL, data.getDepenonGanyu());
        values.put(WILLINGNESS_COL, data.getWillingness());
        values.put(WINTER_CULTIVATION_COL, data.getWinterCultivation());
        values.put(VULNERABLE_HH_COL, data.getVulnerableHh());
        values.put(PLANTING_VALUE_CHAIN_CROP_COL, data.getPlantingVcrop());
        values.put(ENTRY_BY, data.getEntryBy());
        values.put(ENTRY_DATE, data.getEntryDate());
        values.put(AG_INVC, invc);
        values.put(AG_NASFAM, nasfm);
        values.put(AG_CU, cu);
        values.put(AG_OTHER, other);
        values.put(AG_L_S_GOAT, goat);
        values.put(AG_L_S_CHICKEN, chicken);
        values.put(AG_L_S_PIGION, pegion);
        values.put(AG_L_S_OTHER, other_sp);


        db.insert(REG_N_AGR_TABLE, null, values);

        db.close();
    }/*REG_N_AGR_TABLE*/

    /* this method load Service  Criteria for Service Summary Criteria  or Distribution */
    public List<SummaryCriteriaModel> getServiceSummaryCriteriaList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String distFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();//List<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();//Database();
        String selectQuery;
        // get the dist count
   /*     if (srvORDstFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.get_DistCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        } else*/
        selectQuery = SQLiteQuery.get_SrvCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Count"))));
                criteriaList.add(crite);
                Log.d(TAG, " Service summary Criteria : " + cursor.getString(0) + ",IdCriteria : " + cursor.getString(1) + ", ServiceCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }


    public List<SummaryCriteriaModel> getDistributionSummaryCriteriaList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String distFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;

        selectQuery = SQLiteQuery.get_DistCriteriaList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("Criteria")));
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("IdCriteria")));

                crite.setPlan(Integer.parseInt(cursor.getString(cursor.getColumnIndex("plan"))));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("receive"))));
                criteriaList.add(crite);
                Log.d(TAG, " Distribution summary Criteria : "
                        + cursor.getString(0) + ",IdCriteria : "
                        + cursor.getString(1)
                        + ", plan : " + cursor.getString(2)
                        + ", receive : " + cursor.getString(3)
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }


    /* this method load Service Extented item summary   */
    public List<SummaryCriteriaModel> getSrvORDistExtendedItemSummaryList(String cCode, String donorCode, String awardCord, String opMCode, String progCode, String srvDistFlag) {
        List<SummaryCriteriaModel> criteriaList = new ArrayList<SummaryCriteriaModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery;
        if (srvDistFlag.equals(KEY.DIST_FLAG)) {
            selectQuery = SQLiteQuery.getDistExtendedItemSummaryList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        } else {
            selectQuery = SQLiteQuery.getSrvExtendedItemSummaryList_SelectQuery(cCode, donorCode, awardCord, opMCode, progCode);
        }

        Cursor cursor = db.rawQuery(selectQuery, null);
        //  SummaryCriteriaModel crite = new SummaryCriteriaModel();
        if (cursor.moveToFirst()) {
            do {
                SummaryCriteriaModel crite = new SummaryCriteriaModel();
                crite.setCriteria_name(cursor.getString(cursor.getColumnIndex("item")));//cursor
                crite.setCriteria_id(cursor.getString(cursor.getColumnIndex("voucherID")));
                crite.setRecord(Integer.parseInt(cursor.getString(cursor.getColumnIndex("unitCount"))));
                criteriaList.add(crite);
                Log.d(TAG, " Service Item summary item : " + cursor.getString(0) + ",voucherID : " + cursor.getString(1) + ", unitCount : " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return criteriaList;
    }

    /**
     * added by Faisal Mohammad
     *
     */
    /**
     * get data from registration & memeber & reg Nass srv table
     *
     * @param cCode     Country
     * @param disCode   District Code LayR1
     * @param upCode    upCode
     * @param unCode    Unite Code
     * @param vCode     Village Code
     * @param donorCode Donor Code
     * @param awardCode awardCode
     * @param progCode  program Code
     * @param servCode  service Code
     * @param memberId  Sarch id
     * @return AssignDataModel list
     */
    public ArrayList<AssignDataModel> getSingleMemberForAssign(String cCode, String disCode, String upCode, String unCode, String vCode, String hhid, String memberId, String donorCode, String awardCode, String progCode, String servCode) {

        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getSingleMemberForAssign_sql(cCode, disCode, upCode, unCode, vCode, hhid, memberId, donorCode, awardCode, progCode, servCode);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setAssignYN(cursor.getString(cursor.getColumnIndex("Assign")));

                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(PNAME_COL)));


                assignPerson.setAssign_criteria(cursor.getString(cursor.getColumnIndex("AssignCriteria")));
                assignPerson.setGroupCode(cursor.getString(cursor.getColumnIndex("grpCode")));
                assignPerson.setGroupName(cursor.getString(cursor.getColumnIndex("grpName")));
                assignPerson.setGroupCatCode(cursor.getString(cursor.getColumnIndex("catCode")));
                assignPerson.setGroupCatName(cursor.getString(cursor.getColumnIndex("catName")));
                assignPerson.setActiveCode(cursor.getString(cursor.getColumnIndex("activeCode")));

                //   Log.d(TAG, " " + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(14) + " , " + cursor.getString(15));
                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }


    /**
     * added by Faisal Mohammad
     *
     */
    /**
     * get data from registration & memeber & reg Nass srv table
     *
     * @param cCode      Country
     * @param disCode    District Cod e
     * @param upCode     upCode
     * @param unCode     Unite Code
     * @param vCode      Village Code
     * @param donorCode  Donor Code
     * @param awardCode  awardCode
     * @param progCode   program Code
     * @param servCode   service Code
     * @param mmSearchId member Sarch id
     * @return AssignDataModel list
     */
    public ArrayList<AssignDataModel> getListForAssign(String cCode, String disCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String progCode, String servCode, String mmSearchId) {

        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getAssignListViewSelectQuery(cCode, disCode, upCode, unCode, vCode, donorCode, awardCode, progCode, servCode, mmSearchId);


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));
                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setAssignYN(cursor.getString(cursor.getColumnIndex("Assign")));

                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(PNAME_COL)));


                assignPerson.setAssign_criteria(cursor.getString(cursor.getColumnIndex("AssignCriteria")));

                //   Log.d(TAG, " " + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(14) + " , " + cursor.getString(15));
                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }


    public void updateIntoGroupDetails(String AdmCountryCode, String donorCode
            , String awardCode, String progCode, String grpCode
            , String orgCode, String staffCode, String landSizeUnder, String iirigrationSysUsed, String fundSuppot
            , String active, String reapName, String reapPhone, String formation, String typeOfGrp
            , String status, String entryBy, String entryDate, String projecftNo, String projectTitle) {
        String where = "";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        where = COUNTRY_CODE_COL + " = '" + AdmCountryCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND " + GROUP_CODE_COL + " = '" + grpCode + "' ";
        values.put(ORG_CODE_COL, orgCode);
        values.put(STAFF_CODE_COL, staffCode);
        values.put(LAND_SIZE_UNDER_IRRIGATION_COL, landSizeUnder);
        values.put(IRRIGATION_SYSTEM_USED_COL, iirigrationSysUsed);
        values.put(FUND_SUPPORT_COL, fundSuppot);
        values.put(ACTIVE_COL, active);
        values.put(REP_NAME_COL, reapName);
        values.put(REP_PHONE_NUMBER_COL, reapPhone);
        values.put(FORMATION_DATE_COL, formation);
        values.put(TYPE_OF_GROUP, typeOfGrp);
        values.put(STATUS, status);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(PROJECT_NO_COL, projecftNo);
        values.put(PROJECT_TITLE, projectTitle);
        db.update(COMMUNITY_GRP_DETAIL_TABLE, values, where, null);
    }


    private boolean isAlpha(String nameORid) {
        return nameORid.matches("[a-zA-Z]+");
    }

    /**
     * for Member Search Pag e
     *
     * @param cCode   Country Code
     * @param disCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return member list
     */

    public ArrayList<AssignDataModel> getMemberList(final String cCode, final String disCode, final String upCode, final String unCode, final String vCode, final String memberIdOrName) {

        ArrayList<AssignDataModel> listAsignPeople = new ArrayList<AssignDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        if (isAlpha(memberIdOrName)) {
            sql = SQLiteQuery.getMemberListView_searchBy_Name_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName);

        } else {
            sql = SQLiteQuery.getMemberListView_searchBy_ID_sql(cCode, disCode, upCode, unCode, vCode, memberIdOrName);
        }


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                AssignDataModel assignPerson = new AssignDataModel();

                assignPerson.setHh_id(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                assignPerson.setMemId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                assignPerson.setNewId(cursor.getString(cursor.getColumnIndex("newId")));
                assignPerson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));
                assignPerson.setMember_age(cursor.getString(cursor.getColumnIndex(MEM_AGE)));

                assignPerson.setMember_sex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                assignPerson.setHh_relation(cursor.getString(cursor.getColumnIndex("HHRelation")));
                assignPerson.setVillageName(cursor.getString(cursor.getColumnIndex("layR4Name")));
                assignPerson.setAddressName(cursor.getString(cursor.getColumnIndex("address")));


                assignPerson.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                assignPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                assignPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                assignPerson.setUnitCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                assignPerson.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                assignPerson.setHh_name(cursor.getString(cursor.getColumnIndex(PNAME_COL)));/** house hold name */


                listAsignPeople.add(assignPerson);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listAsignPeople;


    }

    public DynamicTableQuesDataModel getSingleDynamicQuestion(String dtBasicCode, int index) {
        DynamicTableQuesDataModel singleQus = new DynamicTableQuesDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'" +
                //  " AND " + DTQ_CODE_COL + "= '" + dtQuestionCode + "'"+
                " LIMIT 1 OFFSET " + String.valueOf(index);


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                singleQus.setDtBasicCode(cursor.getString(0));
                singleQus.setDtQCode(cursor.getString(1));
                singleQus.setqText(cursor.getString(2));
                singleQus.setqResModeCode(cursor.getString(3));
                singleQus.setqSeq(cursor.getString(4));
                singleQus.setAllowNullFlag(cursor.getString(5));
                singleQus.setLup_TableName(cursor.getString(6));
            }
            cursor.close();
            db.close();
        }
        return singleQus;
    }

    /**
     * invoking
     *
     * @param qResMode question Response Mode  ques's ans type
     * @return ans repose mode
     * @see com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DT_QuestionActivity#loadDT_QResMode(String)
     */

    public DTQResModeDataModel getDT_QResMode(String qResMode) {
        DTQResModeDataModel responseMode = new DTQResModeDataModel();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQRES_MODE_TABLE +
                " WHERE " + QRES_MODE_COL + "= '" + qResMode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                responseMode.setDtQResMode(cursor.getString(0));
                responseMode.setDtQResLupText(cursor.getString(1));
                responseMode.setDtDataType(cursor.getString(2).trim());
                responseMode.setDtLookUpUDFName(cursor.getString(3));
                responseMode.setDtResponseValueControl(cursor.getString(4).trim());

            }
            cursor.close();
            db.close();
        }
        Log.d("responseTest", " ResMode:" + responseMode.getDtQResMode()
                + " \tResponseValueControl" + responseMode.getDtResponseValueControl()
                + "  setDtQResLupText:"
                + responseMode.getDtQResLupText());
        return responseMode;
    }

    /**
     * <p>The DTA Table store the Default Value of All Dynamic View's default value </p>
     * invoke in {@link com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DT_QuestionActivity#displayQuestion(DynamicTableQuesDataModel)}
     *
     * @param dtBasic Basic Code
     * @param dtQCode Question Code
     * @return list of the default View's answer
     */

    public List<DT_ATableDataModel> getDTA_Table(String dtBasic, String dtQCode) {

        List<DT_ATableDataModel> listDTA = new ArrayList<DT_ATableDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DT_A_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasic + "'" +
                " AND " + DTQ_CODE_COL + "= '" + dtQCode + "'";


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                DT_ATableDataModel dta = new DT_ATableDataModel();

                dta.setDTBasic(cursor.getString(0));
                dta.setDt_QCode(cursor.getString(1));
                dta.setDt_ACode(cursor.getString(2));
                dta.setDt_ALabel(cursor.getString(3));
                dta.setDt_AValue(cursor.getString(4));
                dta.setDt_Seq(cursor.getString(5));
                dta.setDt_AShort(cursor.getString(6));
                dta.setDt_ScoreCode(cursor.getString(7));
                dta.setDt_SkipDTQCode(cursor.getString(8));
                dta.setDt_ACompareCode(cursor.getString(9));
                dta.setShowHide(cursor.getString(10));
                dta.setMaxValue(cursor.getInt(11));
                dta.setMinValue(cursor.getInt(12));
                dta.setDataType(cursor.getString(13));
                dta.setMarkOnGrid(cursor.getString(14));

                listDTA.add(dta);

          /*      Log.d("MOR_SQL",

                        "DTBasic() :" + dta.getDTBasic() +
                                "\n Dt_QCode()      :" + dta.getDt_QCode() +
                                "\n Dt_ACode()      :" + dta.getDt_ACode() +
                                "\n Dt_ALabel()     :" + dta.getDt_ALabel() +
                                "\n Dt_AValue()     :" + dta.getDt_AValue() +
                                "\n Dt_Seq()        :" + dta.getDt_Seq() +
                                "\n Dt_ScoreCode()  :" + dta.getDt_ScoreCode() +
                                "\n Dt_SkipDTQCode():" + dta.getDt_SkipDTQCode()

                );*/
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }


        return listDTA;

    }

    public ArrayList<DynamicTableQuesDataModel> getDynamicQuestionList(String dtBasicCode) {
        ArrayList<DynamicTableQuesDataModel> list = new ArrayList<DynamicTableQuesDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + DTQ_TABLE +
                " WHERE " + DT_BASIC_COL + "= '" + dtBasicCode + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                DynamicTableQuesDataModel data = new DynamicTableQuesDataModel();

                data.setDtBasicCode(cursor.getString(0));
                data.setDtQCode(cursor.getString(1));
                data.setqText(cursor.getString(2));
                data.setqResModeCode(cursor.getString(3));
                data.setqSeq(cursor.getString(4));
                data.setAllowNullFlag(cursor.getString(5));

                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;

    }

    /**
     * @param cCode         Country Code
     * @param dtTitleSearch Search Key
     * @return DT index list
     */

    public ArrayList<DynamicDataIndexDataModel> getDynamicTableIndexList(final String cCode, String dtTitleSearch) {

        ArrayList<DynamicDataIndexDataModel> list = new ArrayList<DynamicDataIndexDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT dtB." + DT_TITLE_COL + "  " +
                " , dtCPgr." + DT_BASIC_COL + " AS dtBasicCode  " +
                " , donor." + DONOR_NAME_COL + " || '-' || award." + AWARD_S_NAME_COL + " AS awardName  " +
                " , dtCPgr." + DONOR_CODE_COL + " || '' || dtCPgr." + AWARD_CODE_COL + " AS awardCode  " +
                " , prg." + PROGRAM_SHORT_NAME_COL + "  " +
                " , dtCPgr." + PROGRAM_CODE_COL + "  " +
                " , dtCPgr." + PROG_ACTIVITY_TITLE_COL +
                " , dtCPgr." + COUNTRY_CODE_COL +
                " , dtB." + DT_OP_MODE_COL +

                " , dtCPgr." + DONOR_CODE_COL +
                " , dtCPgr." + PROG_ACTIVITY_CODE_COL
                + "  FROM " +
                DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + DT_BASIC_TABLE + "  AS dtB  " +
                " ON dtB." + DT_BASIC_COL + " = dtCpgr." + DT_BASIC_COL + "   " +
                " LEFT JOIN " +
                ADM_AWARD_TABLE + " as award  " +
                " ON award." + COUNTRY_CODE_COL + " = dtCpgr." + COUNTRY_CODE_COL + "  " +
                " AND award." + DONOR_CODE_COL + " = dtCpgr." + DONOR_CODE_COL + "  " +
                " AND award." + AWARD_CODE_COL + "= dtCpgr." + AWARD_CODE_COL + "  " +
                " LEFT JOIN " +
                ADM_DONOR_TABLE + " AS donor  " +
                " ON donor." + DONOR_CODE_COL + " = dtCpgr." + DONOR_CODE_COL + "  " +
                " LEFT JOIN " +
                PROGRAM_MASTER_TABLE + " AS prg  " +
                " ON prg." + DONOR_CODE_COL + " = dtCpgr." + DONOR_CODE_COL + "  " +
                " AND prg." + AWARD_CODE_COL + " = dtCpgr." + AWARD_CODE_COL + "  " +
                " AND prg." + PROGRAM_CODE_COL + " = dtCpgr." + PROGRAM_CODE_COL + "  " +
                " WHERE dtCPgr." + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND dtB." + DT_TITLE_COL + " LIKE '%" + dtTitleSearch + "%'";


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                DynamicDataIndexDataModel data = new DynamicDataIndexDataModel();
                data.setDtTittle(cursor.getString(0));
                data.setDtBasicCode(cursor.getString(1));
                data.setAwardName(cursor.getString(2));
                data.setAwardCode(cursor.getString(3));
                data.setProgramName(cursor.getString(4));
                data.setProgramCode(cursor.getString(5));

                data.setPrgActivityTitle(cursor.getString(6));
                data.setcCode(cursor.getString(7));
                data.setOpMode(cursor.getString(8));
                data.setDonorCode(cursor.getString(9));
                data.setProgramActivityCode(cursor.getString(10));


                list.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;


    }


    public ArrayList<CommunityGroupDataModel> getCommunityGroupList(final String cCode, final String donorCode, final String awardCode, final String progCode, final String groupName) {

        ArrayList<CommunityGroupDataModel> listgroup = new ArrayList<CommunityGroupDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " +
                " cg." + SQLiteHandler.GROUP_NAME_COL

                + " , cg." + SQLiteHandler.GROUP_CODE_COL
                //  + " , cg." + SQLiteHandler.UPCODE_COL
                + " , up." + UPZILLA_NAME_COL
                + " , up." + UPCODE_COL

                + " , " + " cgc." + GROUP_CAT_CODE_COL
                + " , " + " cgc." + GROUP_CAT_NAME_COL
                + " , " + " cgc." + GROUP_CAT_SHORT_NAME_COL
                + " , " + " pm." + SQLiteHandler.PROGRAM_SHORT_NAME_COL
                + " , " + " pm." + SQLiteHandler.PROGRAM_CODE_COL
                + " , " + " pm." + SQLiteHandler.PROGRAM_NAME_COL
                + " , " + " don." + DONOR_NAME_COL + "|| '-' || awd." + SQLiteHandler.AWARD_S_NAME_COL + " AS awardName "
                + " , " + " cgc." + SQLiteHandler.AWARD_CODE_COL

                + " , " + " grpDetail." + ORG_CODE_COL
                + " , " + " org." + ORGANIZATION_NAME
                + " , " + " grpDetail." + STAFF_CODE_COL
                + " , " + " staff." + SQLiteHandler.STAFF_NAME_COL

                + " , " + " grpDetail." + SQLiteHandler.LAND_SIZE_UNDER_IRRIGATION_COL
                + " , " + " grpDetail." + SQLiteHandler.IRRIGATION_SYSTEM_USED_COL
                + " , " + " grpDetail." + SQLiteHandler.FUND_SUPPORT_COL
                + " , " + " grpDetail." + SQLiteHandler.ACTIVE_COL
                + " , " + " grpDetail." + SQLiteHandler.REP_NAME_COL
                + " , " + " grpDetail." + SQLiteHandler.REP_PHONE_NUMBER_COL
                + " , " + " grpDetail." + SQLiteHandler.FORMATION_DATE_COL
                + " , " + " grpDetail." + SQLiteHandler.TYPE_OF_GROUP
                + " , " + " grpDetail." + SQLiteHandler.STATUS
                + " , " + " grpDetail." + SQLiteHandler.PROJECT_NO_COL
                + " , " + " grpDetail." + SQLiteHandler.PROJECT_TITLE

                + " FROM " + COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc "

                + " INNER JOIN "
                + COMMUNITY_GROUP_TABLE + " AS cg "
                + " ON cgc." + SQLiteHandler.COUNTRY_CODE_COL + " = cg." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND cgc." + SQLiteHandler.DONOR_CODE_COL + " = cg." + SQLiteHandler.DONOR_CODE_COL
                + " AND cgc." + SQLiteHandler.AWARD_CODE_COL + " = cg." + SQLiteHandler.AWARD_CODE_COL
                + " AND cgc." + SQLiteHandler.PROGRAM_CODE_COL + " = cg." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND cgc." + SQLiteHandler.GROUP_CAT_CODE_COL + " = cg." + SQLiteHandler.GROUP_CAT_CODE_COL

                + " INNER JOIN " +
                SQLiteHandler.PROGRAM_MASTER_TABLE + " AS pm "
                + " ON cgc." + SQLiteHandler.DONOR_CODE_COL + " = pm." + SQLiteHandler.DONOR_CODE_COL
                + " AND cgc." + SQLiteHandler.AWARD_CODE_COL + " = pm." + SQLiteHandler.AWARD_CODE_COL
                + " AND cgc." + SQLiteHandler.PROGRAM_CODE_COL + " = pm." + SQLiteHandler.PROGRAM_CODE_COL
                + " INNER JOIN " +
                SQLiteHandler.UPAZILLA_TABLE + " AS up"
                + " ON up." + SQLiteHandler.COUNTRY_CODE_COL + " = cgc." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND up." + DISTRICT_CODE_COL + " = cg." + DISTRICT_CODE_COL
                + " AND up." + UPCODE_COL + " = cg." + UPCODE_COL
                + " INNER JOIN " +
                ADM_AWARD_TABLE + " AS awd "
                + " ON awd." + SQLiteHandler.COUNTRY_CODE_COL + " = cgc." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND awd." + SQLiteHandler.DONOR_CODE_COL + " = cgc." + SQLiteHandler.DONOR_CODE_COL
                + " AND awd." + SQLiteHandler.AWARD_CODE_COL + " = cgc." + SQLiteHandler.AWARD_CODE_COL

                + " INNER JOIN "
                + ADM_DONOR_TABLE + " AS don "
                + " ON "


                + "  don." + SQLiteHandler.DONOR_CODE_COL + " = cgc." + SQLiteHandler.DONOR_CODE_COL
                + " LEFT JOIN " +
                COMMUNITY_GRP_DETAIL_TABLE + " AS grpDetail "
                + " ON "
                + " grpDetail." + SQLiteHandler.COUNTRY_CODE_COL + " = cgc." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND grpDetail." + SQLiteHandler.DONOR_CODE_COL + " = cgc." + SQLiteHandler.DONOR_CODE_COL
                + " AND grpDetail." + SQLiteHandler.AWARD_CODE_COL + " = cgc." + SQLiteHandler.AWARD_CODE_COL
                + " AND grpDetail." + SQLiteHandler.PROGRAM_CODE_COL + " = pm." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND grpDetail." + SQLiteHandler.GROUP_CODE_COL + " = cg." + SQLiteHandler.GROUP_CODE_COL


                + " LEFT JOIN " + PROGRAM_ORGANIZATION_NAME_TABLE + " AS org "
                + " ON org." + SQLiteHandler.ORG_CODE_COL + " = grpDetail." + SQLiteHandler.ORG_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.STAFF_MASTER_TABLE + " AS staff "
                + " ON staff." + SQLiteHandler.STAFF_ID_COL + " = " + " grpDetail." + STAFF_CODE_COL
                + " AND staff." + SQLiteHandler.COUNTRY_CODE + " = " + " cgc." + COUNTRY_CODE_COL
                + " AND staff." + SQLiteHandler.ORG_CODE_COL + " = " + " grpDetail." + ORG_CODE_COL


                + "   WHERE cgc." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND cgc." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND cgc." + SQLiteHandler.AWARD_CODE_COL + " ='" + awardCode + "' "
                + " AND cgc." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND cg." + SQLiteHandler.GROUP_NAME_COL + " LIKE '%" + groupName + "%' ";


        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                CommunityGroupDataModel data = new CommunityGroupDataModel();

                data.setCommunityGroupName(cursor.getString(cursor.getColumnIndex(GROUP_NAME_COL)));
                data.setCommuCategoriesCode(cursor.getString(cursor.getColumnIndex(GROUP_CAT_CODE_COL)));
                data.setCommuCategoriesShortName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_SHORT_NAME_COL)));
                data.setProgramShortName(cursor.getString(cursor.getColumnIndex(PROGRAM_SHORT_NAME_COL)));
                data.setCommunityGroupCode(cursor.getString(cursor.getColumnIndex(GROUP_CODE_COL)));
                data.setCommuCategoriesName(cursor.getString(cursor.getColumnIndex(GROUP_CAT_NAME_COL)));
                data.setAwardName(cursor.getString(cursor.getColumnIndex("awardName")));
                data.setAwardCode(cursor.getString(cursor.getColumnIndex(AWARD_CODE_COL)));
                data.setProgramName(cursor.getString(cursor.getColumnIndex(PROGRAM_NAME_COL)));
                data.setProgramCode(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                data.setLayr2Code(cursor.getString(cursor.getColumnIndex(UPCODE_COL)));
                data.setLayr2Name(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                data.setOrgonizationCode(cursor.getString(cursor.getColumnIndex(ORG_CODE_COL)));
                data.setOrgonizationName(cursor.getString(cursor.getColumnIndex(ORGANIZATION_NAME)));

                data.setStaffCode(cursor.getString(cursor.getColumnIndex(STAFF_CODE_COL)));
                data.setStaffName(cursor.getString(cursor.getColumnIndex(STAFF_NAME_COL)));


                data.setLandSizeUnderIrrigation(cursor.getString(cursor.getColumnIndex(LAND_SIZE_UNDER_IRRIGATION_COL)));
                data.setIrrigationSystemUsed(cursor.getString(cursor.getColumnIndex(IRRIGATION_SYSTEM_USED_COL)));
                data.setFundSupport(cursor.getString(cursor.getColumnIndex(FUND_SUPPORT_COL)));
                data.setActive(cursor.getString(cursor.getColumnIndex(ACTIVE_COL)));

                data.setRepName(cursor.getString(cursor.getColumnIndex(REP_NAME_COL)));
                data.setRepPhoneNo(cursor.getString(cursor.getColumnIndex(REP_PHONE_NUMBER_COL)));
                data.setFormation(cursor.getString(cursor.getColumnIndex(FORMATION_DATE_COL)));
                data.setTypeOfGroup(cursor.getString(cursor.getColumnIndex(TYPE_OF_GROUP)));
                data.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
                data.setProjectNo(cursor.getString(cursor.getColumnIndex(PROJECT_NO_COL)));
                data.setProjectTitle(cursor.getString(cursor.getColumnIndex(PROJECT_TITLE)));


                //   Log.d(TAG, " " + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(14) + " , " + cursor.getString(15));
                listgroup.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return listgroup;


    }


    public long insertIntoProgOrgNRole(String countryCode, String donarCode, String awardCode, String organizationCode, String primeyn, String subyn, String techyn, String impyn, String logyn, String othyn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donarCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(ORG_CODE_COL, organizationCode);
        values.put(PRIME_Y_N, primeyn);
        values.put(SUB_Y_N, subyn);
        values.put(TECH_Y_N, techyn);
        values.put(IMP_Y_N, impyn);
        values.put(LONG_Y_N, logyn);
        values.put(OTH_Y_N, othyn);
        long id = db.insert(PROGRAM_ORGANIZATION_ROLE_TABLE, null, values);
        Log.d(TAG, "NEW Insert into " + PROGRAM_ORGANIZATION_ROLE_TABLE + " Table: " + id);
        return id;

    }


    public long insertIntoProgOrgN(String OrgNCode, String orgNName, String orgNShortName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ORG_CODE_COL, OrgNCode);
        values.put(ORGANIZATION_NAME, orgNName);
        values.put(ORGANIZATION_SHORT_NAME, orgNShortName);
        long id = db.insert(PROGRAM_ORGANIZATION_NAME_TABLE, null, values);
        Log.d(TAG, "NEW Insert into " + PROGRAM_ORGANIZATION_NAME_TABLE + " Table: " + id);
        return id;
    }


    /**
     * @param donorCode donorCode
     * @param awardCode awardCode
     * @param progCode  ProgramCode
     * @return
     */

    public String get_ProgramMultipleSrv(String donorCode, String awardCode, String progCode) {
        String programMultipleSrvFlag = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = SQLiteQuery.get_ProgramMultipleSrv_SelectQuery(donorCode, awardCode, progCode);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                programMultipleSrvFlag = cursor.getString(cursor.getColumnIndex(MULTIPLE_SERVICE_FLAG_COL));

            }
            cursor.close();
            db.close();
        }
        return programMultipleSrvFlag;

    }


    // update gps location
    public void updateGpsLocation(String cCode, String groupCode, String subGroupCode, String locationCode, String lat, String lng, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        String where = COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + GROUP_CODE_COL + " = '" + groupCode + "' " +
                " AND " + SUB_GROUP_CODE_COL + " = '" + subGroupCode + "' " +
                " AND " + LOCATION_CODE_COL + " = '" + locationCode + "'; ";
        ContentValues values = new ContentValues();
        values.put(LATITUDE_COL, lat);
        values.put(LONGITUDE_COL, lng);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);


        db.update(GPS_LOCATION_TABLE, values, where, null);

        //   Log.d(TAG + 195, "in update : query :");
        db.close();

    }

    /**
     *
     *
     */

    public boolean ifExistsInRegNAssProgSrv(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + REG_N_ASSIGN_PROG_SRV_TABLE + " WHERE    " + COUNTRY_CODE_COL + " = '" + asPeople.getCountryCode()
                + "' AND " + DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND "
                + UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " + UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " + VCODE_COL + " = '" + asPeople.getVillageCode()
                + "' AND " + HHID_COL + " = '" + asPeople.getHh_id() + "' AND " + HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ", null);

       /* Cursor mCursor = db.rawQuery("SELECT * FROM " + REG_N_ASSIGN_PROG_SRV_TABLE+ " WHERE    "+COUNTRY_CODE_COL+"=? AND "+DISTRICT_CODE_COL+"=? AND "
                        +UPCODE_COL+"=? AND "+UCODE_COL+"=? AND " +VCODE_COL+"=? AND "+HHID_COL +"=? AND "+ HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(),asPeople.getDistrictCode(),asPeople.getUpazillaCode(),asPeople.getUnitCode(),asPeople.getVillageCode(),
                        asPeople.getCustomId(),asPeople.getMemberId()});*///*keyValue,keyvalue1*/});

        if (mCursor.getCount() > 0) {

            mCursor.close();
            db.close();
            Log.d(TAG, " This data exists In Reg N Assinge prog service table");
            return true;
            /* record exist */
        } else {

            mCursor.close();
            db.close();
            Log.d(TAG, " This data  didn't exists In eg N Assinge prog service table");
            return false;
             /* record not exist */
        }


    }

    public int checkAssignCriteriaInCT_TableForLiberia(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.checkDataExitsQueryInCT_TableAssignForLiberia(cCode, distCode, upCode, unCode, vCode, hhID, mmId);
        Cursor cursor = db.rawQuery(selectQuery, null);
        String C11, C21, C31, C32, C33, C34, C35, C36, C37, C38;
        C11 = C21 = C31 = C32 = C33 = C34 = C35 = C36 = C37 = C38 = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                C11 = cursor.getString(cursor.getColumnIndex(C11_CT_PR));
                C21 = cursor.getString(cursor.getColumnIndex(C21_CT_PR));
                C31 = cursor.getString(cursor.getColumnIndex(C31_CT_PR));
                C32 = cursor.getString(cursor.getColumnIndex(C32_CT_PR));
                C33 = cursor.getString(cursor.getColumnIndex(C33_CT_PR));
                C34 = cursor.getString(cursor.getColumnIndex(C34_CT_PR));
                C35 = cursor.getString(cursor.getColumnIndex(C35_CT_PR));
                C36 = cursor.getString(cursor.getColumnIndex(C36_CT_PR));
                C37 = cursor.getString(cursor.getColumnIndex(C37_CT_PR));
                C38 = cursor.getString(cursor.getColumnIndex(C38_CT_PR));


            }
            cursor.close();
        }
        db.close();
        int radioButtonCheckValue = 0;
        if (C11.equals("Y")) {
            return radioButtonCheckValue = 1;
        } else {
            if (C21.equals("Y")) {
                return radioButtonCheckValue = 2;
            } else {
                if (C31.equals("Y")) {
                    return radioButtonCheckValue = 3;
                } else if (C32.equals("Y")) {
                    return radioButtonCheckValue = 4;
                } else if (C33.equals("Y")) {
                    return radioButtonCheckValue = 5;
                } else if (C34.equals("Y")) {
                    return radioButtonCheckValue = 6;
                } else if (C35.equals("Y")) {
                    return radioButtonCheckValue = 7;
                } else if (C36.equals("Y")) {
                    return radioButtonCheckValue = 8;
                } else if (C37.equals("Y")) {
                    return radioButtonCheckValue = 9;
                } else if (C38.equals("Y")) {
                    return radioButtonCheckValue = 10;
                }
            }
        }
        return radioButtonCheckValue;
    }

    /****/

    public boolean ifDataExiteInRegNCT(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        return checkDataExistInTable(SQLiteQuery.checkDataExitsQueryInCT_TableAssignForLiberia(cCode, distCode, upCode, unCode, vCode, hhID, mmId), REG_N_CT_TABLE);
    }

    /**
     * @date : 2016-02-07
     * check the data exists in Every Table
     */
    public boolean checkDataExistInTable(String query, String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        if (query == null)
            query = "";


        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            Log.d(TAG, "the data exists in Table :" + tableName);
            return true;
        }

        cursor.close();
        db.close();
        Log.d(TAG, "the data  did not exists in Table :" + tableName);

        return false;

    }

    public void addMemIntoCT_Table(CTDataModel memData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(ID_COL,idColoumn);
        values.put(COUNTRY_CODE_COL, memData.getCountryCode());
        values.put(DISTRICT_CODE_COL, memData.getDistrictCode());
        values.put(UPCODE_COL, memData.getUpazillaCode());
        values.put(UCODE_COL, memData.getUnitCode());
        values.put(VCODE_COL, memData.getVillageCode());
        values.put(HHID_COL, memData.getHhId());
        values.put(HH_MEM_ID, memData.getMemID());
        values.put(C11_CT_PR, memData.getC11CtPr());
        values.put(C21_CT_PR, memData.getC21CtPr());
        values.put(C31_CT_PR, memData.getC31CtPr());
        values.put(C32_CT_PR, memData.getC32CtPr());
        values.put(C33_CT_PR, memData.getC33CtPr());
        values.put(C34_CT_PR, memData.getC34CtPr());
        values.put(C35_CT_PR, memData.getC35CtPr());
        values.put(C36_CT_PR, memData.getC36CtPr());
        values.put(C37_CT_PR, memData.getC37CtPr());
        values.put(C38_CT_PR, memData.getC38CtPr());
        values.put(ENTRY_BY, memData.getEntryBy());
        values.put(ENTRY_DATE, memData.getEntryDate());
        long id = db.insert(REG_N_CT_TABLE, null, values);
        Log.i(TAG, "Insert into " + REG_N_CT_TABLE + " id : " + String.valueOf(id));
        db.close();
    }

    // todo: write query  TO SHOW SELECTED RADIO BUTTON
    public CTDataModel getCheckSelectItemInCTTable() {
        SQLiteDatabase db = this.getReadableDatabase();
      /*  String selectQuery="SELECT "+C11_CT_PR +" , "
                + C21_CT_PR +" , "
                + C31_CT_PR +" , "
                + C32_CT_PR +" , "
                + C33_CT_PR +" , "
                + C34_CT_PR +" , "
                + C35_CT_PR +" , "
                + C36_CT_PR +" , "
                + C37_CT_PR +" , "
                + C38_CT_PR +" , "
                + " WHERE "
                +COUNTRY_CODE_COL +" = '"        + countryCode+"' "
                +DISTRICT_CODE_COL +" = '"  + districtCode+"' "
                +UPCODE_COL +" = '"         + upCode+"' "
                +UCODE_COL +" = '"          ++"' "
                +VCODE_COL +" = '"          ++"' "
                +HHID_COL +" = '"           ++"' "
                +HH_MEM_ID +" = '"          ++"' "

*/
        return null;
    }


    public boolean doesMemberAssignedInDifferentService(RegNAssgProgSrv data) {
        SQLiteDatabase db = this.getReadableDatabase();
        String memId = "";
        String selectQuery = SQLiteQuery.doesMemberAssignedInDifferentServiceQuery(data);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                memId = cursor.getString(cursor.getColumnIndex(HH_MEM_ID));
            }
            cursor.close();
        }
        Log.d(TAG, " does does Member Assigned In Different Service member id : " + memId);
        db.close();
        if (memId.length() == 2) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @date: 2015-10-01
     * @author: Faisal Mohammad
     * @remark: use it generic in future ..
     * @discription: for graduation
     * todo : use checkDataExistInTable()
     */

    public boolean ifExistsInRegNAssProgSrv(GraduationGridDataModel grd) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = SQLiteQuery.getMemberDataFrom_RegNAssProgSrv_Query(grd);

       /* Cursor cursor = db.rawQuery("SELECT * FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE  " + COUNTRY_CODE_COL + " = '" + grd.getCountryCode()
                + "' AND " + DISTRICT_CODE_COL + " = '" + grd.getDistrictCode()
                + "' AND " + UPCODE_COL + " = '" + grd.getUpazillaCode()
                + "' AND " + UCODE_COL + " = '" + grd.getUnitCode()
                + "' AND " + VCODE_COL + " = '" + grd.getVillageCode()
                + "' AND " + HHID_COL + " = '" + grd.getHh_id()
                + "' AND " + HH_MEM_ID + " = '" + grd.getMember_Id()
                + "' AND " + DONOR_CODE_COL + " = '" + grd.getDonor_code()
                + "' AND " + AWARD_CODE_COL + " = '" + grd.getAward_code()
                + "' AND " + PROGRAM_CODE_COL + " = '" + grd.getProgram_code()
                + "' AND " + SERVICE_CODE_COL + " = '" + grd.getService_code() + "'  "
                , null);
*/

        Cursor cursor = db.rawQuery(sql, null);


        if (cursor.getCount() > 0) {

            cursor.close();
            db.close();
            Log.d(TAG, " This data exists In Reg N Assinge prog service table");
            return true;
            /* record exist */
        } else {

            cursor.close();
            db.close();
            Log.d(TAG, " This data  didn't exists In eg N Assinge prog service table");
            return false;
             /* record not exist */
        }


    }

    /**
     * * todo : use checkDataExistInTable()
     */

    public boolean ifExistsInCA2Table(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REG_N_CA2_TABLE + " WHERE    " + COUNTRY_CODE_COL + "=? AND " + DISTRICT_CODE_COL + "=? AND "
                        + UPCODE_COL + "=? AND " + UCODE_COL + "=? AND " + VCODE_COL + "=? AND " + HHID_COL + "=? AND " + HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});//*keyValue,keyvalue1*/});

        if (cursor.getCount() > 0) {
            Log.d(TAG, " This data exists In CA2 table");
            cursor.close();
            db.close();
            return true;
/* record exist */
        } else {
            Log.d(TAG, " This data  didn't exists In CA2 table");

            return false;
/* record not exist */
        }


    }

    /**
     * * todo : use checkDataExistInTable()
     */
    /**
     * This method return tru if data Exits in Pw Table
     *
     * @param asPeople ede
     * @return true
     */

    public boolean ifExistsInPWTable(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + REG_N_PW_TABLE + " WHERE    " + COUNTRY_CODE_COL + "=? AND " + DISTRICT_CODE_COL + "=? AND "
                        + UPCODE_COL + "=? AND " + UCODE_COL + "=? AND " + VCODE_COL + "=? AND " + HHID_COL + "=? AND " + HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});

        if (cursor.getCount() > 0) {
            Log.d(TAG, " This data exists In PW table");
            cursor.close();
            db.close();
            return true;
            /** record exist */
        } else {
            Log.d(TAG, " This data  didn't exists In PW table");
            return false;
            /* record does not exist */
        }

    }

    /**
     * @param cCode     Country Code
     * @param donorCode donorCode
     * @param awardCode awardCode
     * @param disttCode Layer1 Code
     * @param upCode    Layer2 Code
     * @param unCode    Layer3 Code
     * @param vCode     Layer4 Code
     * @param hhID      houshole id
     * @param memID     member id
     * @param progCode  program id
     * @param srvCode   Service Id
     * @return true/flase
     */


    public boolean ifExistsInRegNmemProgGroup(String cCode, String donorCode, String awardCode, String disttCode, String upCode
            , String unCode, String vCode, String hhID, String memID
            , String progCode, String srvCode) {
        SQLiteDatabase db = this.getReadableDatabase();


        String sql = "SELECT * FROM " + REG_N_MEM_PROG_GRP_TABLE
                + " WHERE    " + COUNTRY_CODE_COL + "= '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + "= '" + donorCode + "'"
                + " AND " + AWARD_CODE_COL + "= '" + awardCode + "'"
                + " AND " + DISTRICT_CODE_COL + "= '" + disttCode + "'"
                + " AND " + UPCODE_COL + "= '" + upCode + "'"
                + " AND " + UCODE_COL + "= '" + unCode + "'"
                + " AND " + VCODE_COL + "= '" + vCode + "'"
                + " AND " + HHID_COL + "= '" + hhID + "'"
                + " AND " + HH_MEM_ID + "=  '" + memID + "'"
                + " AND " + PROGRAM_CODE_COL + "=  '" + progCode + "'"
                + " AND " + SERVICE_CODE_COL + "=  '" + srvCode + "'";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() > 0) {
            Log.d(TAG, " This data exists In " + REG_N_MEM_PROG_GRP_TABLE + " table");
            cursor.close();
            db.close();
            return true;
            /** record exist */
        } else {
            Log.d(TAG, " This data  didn't exists In " + REG_N_MEM_PROG_GRP_TABLE + " table");
            return false;
            /* record does not exist */
        }

    }

    /**
     * Todo: use it in assgne  get_MemOthCriteriaLive
     *
     * @param cCode
     * @param distCode
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhId
     * @param mmId
     * @param donorCode
     * @param awardCode
     * @param progCode
     * @param srvCode
     * @return
     */

    public boolean get_MemOthCriteriaLive(String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId, String donorCode, String awardCode, String progCode, String srvCode) {
        boolean memHave = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + HH_MEM_ID +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + distCode + "' " +
                " AND " + UPCODE_COL + " = '" + upCode + "' " +
                " AND " + UCODE_COL + " = '" + unCode + "' " +
                " AND " + VCODE_COL + " = '" + vCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + HHID_COL + " = '" + hhId + "' " +
                " AND " + HH_MEM_ID + " = '" + mmId + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + progCode + "' " +
                " AND " + SERVICE_CODE_COL + " <> '" + srvCode + "' " +
                " AND " + GRD_CODE_COL + " = (" + SQLiteQuery.getGraduationDefaultActiveReason_Select_Query(progCode, srvCode) + ") ";

        //  Cursor cursor = db.rawQuery(sql, null);

        Cursor m = db.rawQuery(sql, null);


        if (m != null) {


            if (m.getCount() > 0) {

                memHave = true;

            } else {

                memHave = false;
            }
            m.close();
            db.close();

        }

        return memHave;

    }

    /**
     * * todo : use checkDataExistInTable()
     */

    public boolean ifExistsInLmdTable(AssignDataModel asPeople) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + REG_N_LM_TABLE + " WHERE    " + COUNTRY_CODE_COL + "=? AND " + DISTRICT_CODE_COL + "=? AND "
                        + UPCODE_COL + "=? AND " + UCODE_COL + "=? AND " + VCODE_COL + "=? AND " + HHID_COL + "=? AND " + HH_MEM_ID + "=?  ",
                new String[]{asPeople.getCountryCode(), asPeople.getDistrictCode(), asPeople.getUpazillaCode(), asPeople.getUnitCode(), asPeople.getVillageCode(),
                        asPeople.getHh_id(), asPeople.getMemId()});//*keyValue,keyvalue1*/});

        if (mCursor.getCount() > 0) {
            Log.d(TAG, " This data exists In LMD table");
            return true;
        /* record exist */
        } else {
            Log.d(TAG, " This data  didn't exists In LMD table");
            return false;
        /* record not exist */
        }

    }

    public String getGrdCodeForMember_RegNAssProgSrv(String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId,
                                                     String progCode, String srvCode, String donorCode, String awardCode) {

        String selectQuery = "SELECT " + GRD_CODE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE  " + COUNTRY_CODE_COL + " = '" + cCode
                + "' AND " + DISTRICT_CODE_COL + " = '" + distCode
                + "' AND " + UPCODE_COL + " = '" + upCode
                + "' AND " + UCODE_COL + " = '" + unCode
                + "' AND " + VCODE_COL + " = '" + vCode
                + "' AND " + HHID_COL + " = '" + hhId
                + "' AND " + HH_MEM_ID + " = '" + mmId
                + "' AND " + DONOR_CODE_COL + " = '" + donorCode
                + "' AND " + AWARD_CODE_COL + " = '" + awardCode
                + "' AND " + PROGRAM_CODE_COL + " = '" + progCode
                + "' AND " + SERVICE_CODE_COL + " = '" + srvCode + "'  ";

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
            Log.d(TAG, "Member Saved  GRD Code : " + grdCode);
        }
        mCursor.close();
        db.close();
        return grdCode;
    }

    /**
     * @since 2015-10-02
     */
    public String getGRDDefaultExitReason(String progCode, String srvCode) {


        String selectQuery = "SELECT " + GRD_CODE_COL + " FROM " + REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + PROGRAM_CODE_COL + " = '" + progCode
                + "' AND " + SERVICE_CODE_COL + " = '" + srvCode
                + "' AND " + DEFAULT_CAT_EXIT_COL + " = 'Y'  ";

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
        }
        mCursor.close();
        db.close();
        return grdCode;
    }


    /**
     * 2015-11-23
     * Faisal Mohammad
     * <p/>
     * get default Exit Reason for Graduation
     */
    public String getGRDDefaultActiveReason(String progCode, String srvCode) {


        String selectQuery = SQLiteQuery.getGraduationDefaultActiveReason_Select_Query(progCode, srvCode);
        /*"SELECT " + GRD_CODE_COL + " FROM " + REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + PROGRAM_CODE_COL + " = '" + progCode
                + "' AND " + SERVICE_CODE_COL + " = '" + srvCode
                + "' AND " + DEFAULT_CAT_ACTIVE_COL + " = 'Y'  ";*/

        SQLiteDatabase db = this.getReadableDatabase();
        String grdCode = "";
        Cursor mCursor = db.rawQuery(selectQuery, null);
        if (mCursor.moveToFirst()) {
            grdCode = mCursor.getString(mCursor.getColumnIndex(GRD_CODE_COL));
        }
        mCursor.close();
        db.close();
        return grdCode;
    }


    // add gps group
    public void addGpsGroup(String grpCode, String grpName, String description) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GROUP_CODE_COL, grpCode); // GROUP code
        values.put(GROUP_NAME_COL, grpName); // GROUP name
        values.put(DESCRIPTION_COL, description); // GROUP description


        // Inserting Row
        long id = db.insert(GPS_GROUP_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Group inserted into GPS_GROUP_TABLE: " + id);

    }

    // add program master record
    public void addServiceMaster(String pCode, String serviceCode, String serviceN, String srvShortN) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROGRAM_CODE_COL, pCode);
        values.put(SERVICE_CODE_COL, serviceCode);
        values.put(SERVICE_NAME_COL, serviceN);
        values.put(SERVICE_SHORT_NAME_COL, srvShortN);
        //values.put(PROGRAM_SHORT_NAME_COL, pShortN);


        // Inserting Row
        long id = db.insert(SERVICE_MASTER_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New AWARD inserted into SERVICE_MASTER_TABLE TABLE: " + id);

    }

    // add program master record
    public void addProgram(String pCode, String awardCode, String donorCode, String pName, String pShortN, String multipleSrv) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROGRAM_CODE_COL, pCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(PROGRAM_NAME_COL, pName);
        values.put(PROGRAM_SHORT_NAME_COL, pShortN);
        values.put(MULTIPLE_SERVICE_FLAG_COL, multipleSrv);


        // Inserting Row
        long id = db.insert(PROGRAM_MASTER_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Program  inserted into Program TABLE: " + id);

    }

    // add country award
    public void addCountryAward(String countryCode, String donorCode, String awardCode, String awardRef, String awardStartD, String awardEndD, String awardShortN, String awardStatus) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode); // country code
        values.put(DONOR_CODE_COL, donorCode); // donor code
        values.put(AWARD_CODE_COL, awardCode); //  award code
        values.put(AWARD_REF_N_COL, awardRef); // award refrence code
        values.put(AWARD_START_DATE_COL, awardStartD); // awardStartDate
        values.put(AWARD_END_DATE_COL, awardEndD); // awardEndDate
        values.put(AWARD_S_NAME_COL, awardShortN); // AwardShort Name
        values.put(AWARD_STATUS_COL, awardStatus); // AwardStatus

        // Inserting Row
        long id = db.insert(ADM_AWARD_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New AWARD inserted into COUNTRY AWARD_TABLE: " + id);
    }

    // add donor name

    /**
     * @param donorCode
     * @param donorName
     */
    public void addDonorName(String donorCode, String donorName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DONOR_CODE_COL, donorCode); // donor code
        values.put(DONOR_NAME_COL, donorName); // donor Name


        // Inserting Row
        long id = db.insert(ADM_DONOR_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New DONOR name  inserted into DONOR_TABLE: " + id);

    }

    /**
     * @param cCode
     * @param groupCode
     * @param subGroupCode
     * @param locationCode
     * @return GPSLocationLatLong
     */

    public GPSLocationLatLong getLocationSpecificLatLong(String cCode, String groupCode, String subGroupCode, String locationCode) {
        String selectQuery;


        selectQuery = " SELECT ( CASE WHEN " + LATITUDE_COL + "='ISNULL' Then "
                + LATITUDE_COL + "='' ElSE " + LATITUDE_COL + " END ) AS '" + LATITUDE_COL + "' ," +
                "(CASE WHEN " + LONGITUDE_COL + "='ISNULL'Then " + LONGITUDE_COL + " = '' ElSE " + LONGITUDE_COL + " END ) AS '" + LONGITUDE_COL + "'  " +
                " FROM " + GPS_LOCATION_TABLE +
                " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGroupCode + "' "
                + " AND " + LOCATION_CODE_COL + " ='" + locationCode + "'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        GPSLocationLatLong latLong;

        if (cursor.moveToFirst()) {

            latLong = new GPSLocationLatLong();
            latLong.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
            latLong.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE_COL)));


            Log.d(TAG, " Location " + cursor.getColumnName(0) + " :" + cursor.getString(0));


        } else {
            Log.d(TAG, "Location lat long cursor is null");
            latLong = new GPSLocationLatLong();
            latLong.setLatitude("");
            latLong.setLongitude("");

        }


        cursor.close();
        db.close();
        return latLong;
    }

    /**
     * @param cCode        Country Code
     * @param groupCode    Group  Code
     * @param subGroupCode Sub Group Code
     *                     <p>This Method return the GPS Coordinates , it's title  and Location Code, which latitude  and lonitude are not null</p>
     * @return An array of GPS Location
     */

    public ArrayList<GPS_LocationDataModel> getSubGroupSpecificLatLongCoordinates(String cCode, String groupCode, String subGroupCode) {

        ArrayList<GPS_LocationDataModel> gpsList = new ArrayList<GPS_LocationDataModel>();


        String selectQuery = " SELECT "
                + LOCATION_CODE_COL
                + " , " + LOCATION_NAME_COL
                + " , " + LATITUDE_COL

                + " , " + LONGITUDE_COL
                + " FROM " + GPS_LOCATION_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + GROUP_CODE_COL + " ='" + groupCode + "' "
                + " AND " + SUB_GROUP_CODE_COL + " ='" + subGroupCode + "' "
                + " AND (" + LATITUDE_COL + " != '' " + " OR  " + LONGITUDE_COL + " != '' )";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {

            do {
                GPS_LocationDataModel data = new GPS_LocationDataModel();
                data.setAdmCountryCode(cCode);
                data.setGroupCode(groupCode);
                data.setSubGroupCode(subGroupCode);
                data.setLocationCode(cursor.getString(0));
                data.setLocationName(cursor.getString(1));
                data.setLat(cursor.getString(2));
                data.setLng(cursor.getString(3));
                gpsList.add(data);


            } while (cursor.moveToNext());

            cursor.close();
            db.close();


        }


        return gpsList;
    }

    public String get_ProgSrvDefaultDays(String cCode, String donorCode, String awardCode, String progCode, String srvCode, String flag) {
        String getDefaultDays = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "";
        switch (flag) {
            case "FoodFlag":
                sql = "SELECT " + DEFAULT_FOOD_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "NFoodFlag":
                sql = "SELECT " + DEFAULT_NO_FOOD_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "CashFlag":
                sql = "SELECT " + DEFAULT_CASH_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' ";
                break;
            case "VOFlag":
                sql = "SELECT " + DEFAULT_VOUCHAR_DAYS_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                        + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                        + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                        + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                        + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                        + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' ";
                break;
        }


        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                getDefaultDays = cursor.getString(0);

            }
            cursor.close();
            db.close();
        }
        return getDefaultDays;
    }


    public boolean ifThisHHIDExitsInRegHHTable(String countryCode, String distCode, String upCode, String unCode, String villCode, String hh_id) {
        boolean isExits;
        String selectQuery = "SELECT * FROM " + REGISTRATION_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + DISTRICT_NAME_COL + " = '" + distCode + "' "
                + " AND " + UPZILLA_NAME_COL + " = '" + upCode + "' "
                + " AND " + UNITE_NAME_COL + " = '" + unCode + "' "
                + " AND " + VILLAGE_NAME_COL + " = '" + villCode + "' "
                + " AND " + PID_COL + " = '" + hh_id + "' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }

    /**
     * @param cCode    Country Code
     * @param dnorCode Donor Code
     * @param awdCode  award Code
     * @param progCode Program Code
     * @return VoucherFlag is exits than true or else  false
     */

    public boolean checkAdmCountryProgramsVoucherFlag(String cCode, String dnorCode, String awdCode, String progCode) {

        boolean voFlag = false;
        String tem = null;
        String selectQuery = SQLiteQuery.checkAdmCountryProgramsVoucherFlag_sql(cCode, dnorCode, awdCode, progCode);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(cursor.getColumnIndex(VOUCHER_FLAG));
            }

            cursor.close();
            db.close();
        }
        if (tem != null) {
            if (tem.equals("1")) // if volFag found 1 than it will sed the true
                voFlag = true;
        }
        return voFlag;

    }

    /**
     * @param cCode    Country Code
     * @param dnorCode Donor Code
     * @param awdCode  award Code
     * @param progCode Program Code
     * @param SrvCode  Service Code
     * @return nonFoodFlag is exits than true or else  false
     */

    public boolean checkAdmCountryProgramsNoneFoodFlag(String cCode, String dnorCode, String awdCode, String progCode, String SrvCode) {

        boolean nonFoodFlag = false;
        String tem = null;
        String selectQuery = SQLiteQuery.checkAdmCountryProgramsNoneFoodFlag_sql(cCode, dnorCode, awdCode, progCode, SrvCode);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }
        if (tem != null) {
            if (tem.equals("1")) // if nonFoodFlag found 1 than it will sed the true
                nonFoodFlag = true;
        }
        return nonFoodFlag;

    }


  /*  public long insertIntoDDR_RegN_FFATable(String countryCode, String districtCode, String upozillaCode,
                                            String unitCode, String villageCode, String houseHoldID,
                                            String houseHoldMemberId, String orphanChildren, String childHeaded,
                                            String elderlyHeaded, String chronicallyIll, String cropFailure,
                                            String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upozillaCode);
        values.put(UCODE_COL, unitCode);
        values.put(VCODE_COL, villageCode);
        values.put(HHID_COL, houseHoldID);
        values.put(HH_MEM_ID, houseHoldMemberId);
        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        long id = db.insert(REG_N_FFA_TABLE, null, values);
        return id;


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
                + " , " + SQLiteHandler.CROP_FAILURE_COL + " VARCHAR(1) "
                +"  , " + SQLiteHandler.CHILDREN_REC_SUPP_FEED_N_COL +" VARCHAR(1) "
                +"  , " + SQLiteHandler.WILLINGNESS_COL+ " VARCHAR(1) "
    }*/


    public void editIntoDDR_RegN_FFATable(String cCode, String districtCode, String upozillaCode,
                                          String unitCode, String villageCode, String houseHoldID,
                                          String houseHoldMemberId, String orphanChildren, String childHeaded,
                                          String elderlyHeaded, String chronicallyIll, String femaleHeaded, String cropFailure,
                                          String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String where = "" + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DISTRICT_CODE_COL + " = '" + districtCode + "' "
                + " AND " + UPCODE_COL + " = '" + upozillaCode + "' "
                + " AND " + UCODE_COL + " = '" + unitCode + "' "
                + " AND " + VCODE_COL + " = '" + villageCode + "' "
                + " AND " + HHID_COL + " = '" + houseHoldID + "' "
                + " AND " + HH_MEM_ID + " = '" + houseHoldMemberId + "' ";


        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(FEMALE_HEADED_COL, femaleHeaded);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.update(REG_N_FFA_TABLE, values, where, null);

    }


    // todo facthig data
    public void insertIntoDDR_RegN_FFATable(String countryCode, String districtCode, String upozillaCode,
                                            String unitCode, String villageCode, String houseHoldID,
                                            String houseHoldMemberId, String orphanChildren, String childHeaded,
                                            String elderlyHeaded, String chronicallyIll, String femaleHeaded, String cropFailure,
                                            String childrenRecSuppFeedN, String willingness, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upozillaCode);
        values.put(UCODE_COL, unitCode);
        values.put(VCODE_COL, villageCode);
        values.put(HHID_COL, houseHoldID);
        values.put(HH_MEM_ID, houseHoldMemberId);
        values.put(ORPHAN_CHILDREN_COL, orphanChildren);
        values.put(CHILD_HEADED_COL, childHeaded);
        values.put(ELDERLY_HEADED_COL, elderlyHeaded);
        values.put(CHRONICALLY_ILL_COL, chronicallyIll);
        values.put(FEMALE_HEADED_COL, femaleHeaded);
        values.put(CROP_FAILURE_COL, cropFailure);
        values.put(CHILDREN_REC_SUPP_FEED_N_COL, childrenRecSuppFeedN);
        values.put(WILLINGNESS_COL, willingness);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        db.insert(REG_N_FFA_TABLE, null, values);


    }


    public AssignDDR_FFA_DataModel getAssignDataIfExitsInRegNFFA_table(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID, String mmId) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectQuery = SQLiteQuery.getAssignDataIfExitsInRegNFFA_table_sql(cCode, distCode, upCode, unCode, vCode, hhID, mmId);


        Cursor cursor = db.rawQuery(selectQuery, null);
        AssignDDR_FFA_DataModel ffaData = new AssignDDR_FFA_DataModel();
// default value
        ffaData.setOrphanChildRb1("N");
        ffaData.setElderlyHeadedRb2("N");
        ffaData.setChronicallyIllRb3("N");
        ffaData.setFemaleHeadedRb4("N");
        ffaData.setCropFailureRb5("N");
        ffaData.setChildrenRecSuppFeedNRb6("N");
        ffaData.setWillingnessRb7("N");

        if (cursor != null) {
            if (cursor.moveToFirst()) {


                ffaData.setOrphanChildRb1(cursor.getString(cursor.getColumnIndex(CHILD_HEADED_COL)));
                ffaData.setElderlyHeadedRb2(cursor.getString(cursor.getColumnIndex(ELDERLY_HEADED_COL)));
                ffaData.setChronicallyIllRb3(cursor.getString(cursor.getColumnIndex(CHRONICALLY_ILL_COL)));
                ffaData.setFemaleHeadedRb4(cursor.getString(cursor.getColumnIndex(FEMALE_HEADED_COL)));
                ffaData.setCropFailureRb5(cursor.getString(cursor.getColumnIndex(CROP_FAILURE_COL)));
                ffaData.setChildrenRecSuppFeedNRb6(cursor.getString(cursor.getColumnIndex(CHILDREN_REC_SUPP_FEED_N_COL)));
                ffaData.setWillingnessRb7(cursor.getString(cursor.getColumnIndex(WILLINGNESS_COL)));

                Log.d("FFA-SQL", "agr_dataModel.=" + ffaData.getOrphanChildRb1()
                        + ffaData.getElderlyHeadedRb2() + ffaData.getChronicallyIllRb3()
                        + ffaData.getFemaleHeadedRb4() + ffaData.getCropFailureRb5()
                        + ffaData.getChildrenRecSuppFeedNRb6() + ffaData.getWillingnessRb7()
                );


            }
            cursor.close();
        }
        db.close();
        return ffaData;
    }


    public long insertIntoDDR_RegN_VUL(String countryCode, String districtCode, String upozillaCode,
                                       String unitCode, String villageCode, String houseHoldID,
                                       String houseHoldMemberId, String regnDate, String disable,
                                       String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upozillaCode);
        values.put(UCODE_COL, unitCode);
        values.put(VCODE_COL, villageCode);
        values.put(HHID_COL, houseHoldID);
        values.put(HH_MEM_ID, houseHoldMemberId);
        values.put(REG_N_DAT_COL, regnDate);
        values.put(Disabled_YN_COL, disable);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        long id = db.insert(REG_N_VUL_TABLE, null, values);
        return id;
    }


    public boolean checkCriteriaServiceSpecificFlag(String countryCode, String donarCode, String awardCode, String progCode, String srvCode) {

        boolean srvSpeceficFlag = false;
        String tem = null;
        String selectQuery = "SELECT " + SERVICE_SPECIFIC_FLAG_COL + " FROM " + COUNTRY_PROGRAM_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + DONOR_CODE_COL + " = '" + donarCode + "'"
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND " + SERVICE_CODE_COL + " = '" + srvCode + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                tem = cursor.getString(0);
            }

            cursor.close();
            db.close();
        }
        if (tem != null) {
            if (tem.equals("1")) // if srvSpeceficFlag found 1 than it will sed the true
                srvSpeceficFlag = true;
        }
        return srvSpeceficFlag;

    }

    public String getMinDate(String cCode) {
        return getDate(cCode, "Min");
    }

    public String getMaxDate(String cCode) {
        return getDate(cCode, "Max");
    }

    public String getDate(String cCode, String MinOrMax) {
        String date = "";
        String sql = "";
        // query to get the start date of the registration process.
        if (MinOrMax.equals("Min")) {
            sql = "SELECT MIN " + "(" + START_DATE + ")" + " FROM " + OP_MONTH_TABLE + " WHERE " + COUNTRY_CODE_COL
                    + " = '" + cCode + "'" + " AND " + STATUS + "= 'A'";
        } else {
            sql = "SELECT MAX " + "(" + END_DATE + ")" + " FROM " + OP_MONTH_TABLE + " WHERE " + COUNTRY_CODE_COL
                    + " = '" + cCode + "'" + " AND " + STATUS + "= 'A'";
        }

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                date = cursor.getString(0);     // Start Date
            }
            cursor.close();
            db.close();
        }


        return date;
    }


    public boolean ifThisNameExitsInRegHHTable(String hname) {
        boolean isExits;
        String selectQuery = "SELECT * FROM " + REGISTRATION_TABLE + " WHERE " + PNAME_COL + " = '" + hname + "' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;

    }


    // ToDo: get single member data

    /**
     * @param str_c_code
     */
    public RegN_HH_libDataModel getSingleLiberiaHouseHoldData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {
        RegN_HH_libDataModel hhData = new RegN_HH_libDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SQLiteQuery.getSingleHouseHoldDataForLiberiaQuery(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                // todo: change the columun inedx
                /*hhData.setCountryCode(idCountry);
                hhData.setDistrictCode(idDist);
                hhData.setUpazillaCode(idUP);
                hhData.setUnitCode(idUnion);
                hhData.setVillageCode(idVill);
                hhData.setHhId(strRegID);*/

                hhData.setRegNDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                hhData.setHHHeadName(cursor.getString(cursor.getColumnIndex(PNAME_COL)));
                hhData.setHHTypeCode(cursor.getString(cursor.getColumnIndex(HOUSE_HOLD_TYPE_CODE_COL)));
                hhData.setHHTypeString(cursor.getString(cursor.getColumnIndex("HHType")));

                // todo : get check box values
                hhData.setLT2yrsM(cursor.getString(cursor.getColumnIndex(LT2YRS_M_COL)));
                hhData.setLT2yrsF(cursor.getString(cursor.getColumnIndex(LT2YRS_F_COL)));
                hhData.setM2to5yers(cursor.getString(cursor.getColumnIndex(M_2TO5YRS_COL)));
                hhData.setF2to5yrs(cursor.getString(cursor.getColumnIndex(F_2TO5YRS_COL)));
                hhData.setM6to12yrs(cursor.getString(cursor.getColumnIndex(M_6TO12YRS_COL)));
                hhData.setF6to12yrs(cursor.getString(cursor.getColumnIndex(F_6TO12YRS_COL)));
                hhData.setM13to17yrs(cursor.getString(cursor.getColumnIndex(M_13TO17YRS_COL)));
                hhData.setF13to17yrs(cursor.getString(cursor.getColumnIndex(F_13TO17YRS_COL)));
                hhData.setOrphn_LT18yrsM(cursor.getString(cursor.getColumnIndex(ORPHN_LT18YRS_M_COL)));
                hhData.setOrphn_LT18yrsF(cursor.getString(cursor.getColumnIndex(ORPHN_LT18YRS_F_COL)));
                hhData.setAdlt_18to59M(cursor.getString(cursor.getColumnIndex(ADLT_18TO59_M_COL)));
                hhData.setAdlt_18to59F(cursor.getString(cursor.getColumnIndex(ADLT_18TO59_F_COL)));
                hhData.setEld_60pM(cursor.getString(cursor.getColumnIndex(ELD_60P_M_COL)));
                hhData.setEld_60pF(cursor.getString(cursor.getColumnIndex(ELD_60P_F_COL)));
                hhData.setPLW(cursor.getString(cursor.getColumnIndex(PLW_NO_COL)));
                hhData.setChronicallyIll(cursor.getString(cursor.getColumnIndex(CHRO_ILL_NO_COL)));


                hhData.setLivingDeceasedContractEbola(cursor.getString(cursor.getColumnIndex(DECEASED_CONTRACT_EBOLA_COL)));
                hhData.setExtraChildBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_CHILD_CAUSE_EBOLA_COL)));
                hhData.setExtraelderlyPersonBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_ELDERLY_CAUSE_EBOLA_COL)));
                hhData.setExtraChronicallyIllDisabledPersonBecauseEbola(cursor.getString(cursor.getColumnIndex(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL)));

                hhData.setBrfCntCattle(cursor.getString(cursor.getColumnIndex(BRF_COUNT_CATTLE_COL)));
                hhData.setBrfValCattle(cursor.getString(cursor.getColumnIndex(BRF_VALUE_CATTLE_COL)));
                hhData.setAftCntCattle(cursor.getString(cursor.getColumnIndex(AFT_COUNT_CATTLE_COL)));
                hhData.setAftValCattle(cursor.getString(cursor.getColumnIndex(AFT_VALUE_CATTLE_COL)));
                hhData.setBrfCntSheepGoats(cursor.getString(cursor.getColumnIndex(BRF_COUNT_SGOATS_COL)));
                hhData.setBrfValSheepGoats(cursor.getString(cursor.getColumnIndex(BRF_VALUE_SGOATS_COL)));
                hhData.setAftCntSheepGoats(cursor.getString(cursor.getColumnIndex(AFT_COUNT_SGOATS_COL)));
                hhData.setAftValSheepGoats(cursor.getString(cursor.getColumnIndex(AFT_VALUE_SGOATS_COL)));
                hhData.setBrfCntPoultry(cursor.getString(cursor.getColumnIndex(BRF_COUNT_POULTRY_COL)));
                hhData.setBrfValPoultry(cursor.getString(cursor.getColumnIndex(BRF_VALUE_POULTRY_COL)));
                hhData.setAftCntPoultry(cursor.getString(cursor.getColumnIndex(AFT_COUNT_POULTRY_COL)));
                hhData.setAftValPoultry(cursor.getString(cursor.getColumnIndex(AFT_VALUE_POULTRY_COL)));
                hhData.setBrfCntOther(cursor.getString(cursor.getColumnIndex(BRF_COUNT_OTHER_COL)));
                hhData.setBrfValOther(cursor.getString(cursor.getColumnIndex(BRF_VALUE_OTHER_COL)));
                hhData.setAftCntOther(cursor.getString(cursor.getColumnIndex(AFT_COUNT_OTHER_COL)));
                hhData.setAftValOther(cursor.getString(cursor.getColumnIndex(AFT_VALUE_OTHER_COL)));
                hhData.setBrfAcreCultivable(cursor.getString(cursor.getColumnIndex(BRF_ACRE_CULTIVABLE_COL)));
                hhData.setBrfValCultivable(cursor.getString(cursor.getColumnIndex(BRF_VALUE_CULTIVABLE_COL)));
                hhData.setAftAcreCultivable(cursor.getString(cursor.getColumnIndex(AFT_ACRE_CULTIVABLE_COL)));
                hhData.setAftValCultivable(cursor.getString(cursor.getColumnIndex(AFT_VALUE_CULTIVABLE_COL)));
                hhData.setBrfAcreNonCultivable(cursor.getString(cursor.getColumnIndex(BRF_ACRE_NON_CULTIVABLE_COL)));
                hhData.setBrfValNonCultivable(cursor.getString(cursor.getColumnIndex(BRF_VAL_NON_CULTIVABLE_COL)));
                hhData.setAftAcreNonCultivable(cursor.getString(cursor.getColumnIndex(AFT_ACRE_NON_CULTIVABLE)));
                hhData.setAftValNonCultivable(cursor.getString(cursor.getColumnIndex(AFT_VAL_NON_CULTIVABLE)));
                hhData.setBrfAcreOrchards(cursor.getString(cursor.getColumnIndex(BRF_ACRE_ORCHARDS)));
                hhData.setBrfValOrchards(cursor.getString(cursor.getColumnIndex(BRF_VAL_ORCHARDS)));
                hhData.setAftAcreOrchards(cursor.getString(cursor.getColumnIndex(AFT_ACRE_ORCHARDS)));
                hhData.setAftValOrchards(cursor.getString(cursor.getColumnIndex(AFT_VAL_ORCHARDS)));

                hhData.setBrfValCrop(cursor.getString(cursor.getColumnIndex(BRF_VAL_CROP)));
                hhData.setAftValCrop(cursor.getString(cursor.getColumnIndex(AFT_VAL_CROP)));
                hhData.setBrfValLivestock(cursor.getString(cursor.getColumnIndex(BRF_VAL_LIVESTOCK)));
                hhData.setAftValLivestock(cursor.getString(cursor.getColumnIndex(AFT_VAL_LIVESTOCK)));
                hhData.setBrfValSmallBusiness(cursor.getString(cursor.getColumnIndex(BRF_VAL_SMALL_BUSINESS)));
                hhData.setAftValSmallBusiness(cursor.getString(cursor.getColumnIndex(AFT_VAL_SMALL_BUSINESS)));
                hhData.setBrfValEmployment(cursor.getString(cursor.getColumnIndex(BRF_VAL_EMPLOYMENT)));
                hhData.setAftValEmployment(cursor.getString(cursor.getColumnIndex(AFT_VAL_EMPLOYMENT)));
                hhData.setBrfValRemittances(cursor.getString(cursor.getColumnIndex(BRF_VAL_REMITTANCES)));
                hhData.setAftValRemittances(cursor.getString(cursor.getColumnIndex(AFT_VAL_REMITTANCES)));
                hhData.setBrfCntWageEnr(cursor.getString(cursor.getColumnIndex(BRF_CNT_WAGEENR)));
                hhData.setAftCntWageEnr(cursor.getString(cursor.getColumnIndex(AFT_CNT_WAGEENR)));
                /**
                 hhData.setEntryBy(EntryBy);
                 hhData.setEntryDate(EntryDate);*/


            }
        }
        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return hhData;
    }


    // ToDo: get single member data

    /**
     * @param str_c_code
     */
    public RegN_MM_libDataModel getSingleLiberiaMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID, String memID) {
        RegN_MM_libDataModel memberData = new RegN_MM_libDataModel();

        SQLiteDatabase db = this.getReadableDatabase();

        String query = SQLiteQuery.getSingleLiberiaMemberDataQuery(str_c_code, str_district, str_upazilla, str_union, str_village, hhID, memID);

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {


                memberData.setStr_MemNameFirst(cursor.getString(cursor.getColumnIndex(MEM_NAME_COL)));
                memberData.setStr_entry_by(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                memberData.setStr_entry_date(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                memberData.setStr_MemBirthYear(cursor.getString(cursor.getColumnIndex(BIRTH_YEAR_COL)));
                memberData.setCodeMartial(cursor.getString(cursor.getColumnIndex(MARITAL_STATUS_COL)));
                memberData.setStr_MemContact(cursor.getString(cursor.getColumnIndex(CONTACT_NO_COL)));
                memberData.setStr_memOtherID(cursor.getString(cursor.getColumnIndex(MEMBER_OTHER_ID_COL)));
                memberData.setStr_MemNameFirst(cursor.getString(cursor.getColumnIndex(MEM_NAME_FIRST_COL)));
                memberData.setStr_MemNameMiddle(cursor.getString(cursor.getColumnIndex(MEM_NAME_MIDDLE_COL)));
                memberData.setStr_MemNameLast(cursor.getString(cursor.getColumnIndex(MEM_NAME_LAST_COL)));
                memberData.setMemberEncodedImage(cursor.getBlob(cursor.getColumnIndex(PHOTO_COL)));
                memberData.setCodeIDType(cursor.getString(cursor.getColumnIndex(TYPE_ID_COL)));
                memberData.setStrTypeIDNo(cursor.getString(cursor.getColumnIndex(ID_NO_COL)));
                memberData.setStr_V_bsc_Mem_1_NameFirst(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_FIRST_COL)));
                memberData.setStr_V_bsc_Mem_1_NameMiddle(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_MIDDLE_COL)));
                memberData.setStr_V_bsc_Mem_1_NameLast(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_NAME_LAST_COL)));

                // TODO :: Need to check relation in Member table and needs to save the Relation code
                // TODO :: Need to select relation according to code in Member Detail page  + V_BSC_MEM_1_TITLE_COL + " , "


                memberData.setCodeBscMem1Title(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_1_TITLE_COL)));
                memberData.setStr_V_bsc_Mem_2_NameFirst(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_FIRST_COL)));
                memberData.setStr_V_bsc_Mem_2_NameMiddle(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_MIDDLE_COL)));
                memberData.setStr_V_bsc_Mem_2_NameLast(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_NAME_LAST_COL)));
                memberData.setCodeBscMem2Title(cursor.getString(cursor.getColumnIndex(V_BSC_MEM_2_TITLE_COL)));
                memberData.setCodeDesignatedProxy(cursor.getString(cursor.getColumnIndex(PROXY_DESIGNATION_COL)));
                memberData.setStr_ProxyNameFirst(cursor.getString(cursor.getColumnIndex(PROXY_NAME_FIRST_COL)));
                memberData.setStr_ProxyNameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_NAME_MIDDLE_COL)));
                memberData.setStr_ProxyNameLast(cursor.getString(cursor.getColumnIndex(PROXY_NAME_LAST_COL)));
                memberData.setStr_ProxyBirthYear(cursor.getString(cursor.getColumnIndex(PROXY_BIRTH_YEAR_COL)));
                memberData.setProxyEncodedImage(cursor.getBlob(cursor.getColumnIndex(PROXY_PHOTO_COL)));
                memberData.setStr_ProxyTypedIDNo(cursor.getString(cursor.getColumnIndex(PROXY_ID_NO_COL)));
                memberData.setCodeIDTypeForProxy(cursor.getString(cursor.getColumnIndex(PROXY_TYPE_ID_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameFirst(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_FIRST_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_MIDDLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_1_NameLast(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_NAME_LAST_COL)));
                memberData.setCodeProxyBscMem1Title(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_1_TITLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameFirst(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_FIRST_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameMiddle(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_MIDDLE_COL)));
                memberData.setStr_Proxy_bsc_Mem_2_NameLast(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_NAME_LAST_COL)));
                memberData.setCodeProxyBscMem2Title(cursor.getString(cursor.getColumnIndex(PROXY_BSC_MEM_2_TITLE_COL)));


            }
        }
        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return memberData;
    }


    // Getting All Contacts

    /**
     * @param hhID
     * @param str_c_code
     * @param str_district
     * @param str_union
     * @param str_upazilla
     * @param str_village
     * @return List<MemberModel>
     */
    public List<MemberModel> getMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        List<MemberModel> memberList = new ArrayList<MemberModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";


        query = "SELECT DISTINCT rm.*, r.PersonName, r." + ID_COL + " AS PID, rel." + RELATION_NAME + " FROM "
                + REGISTRATION_MEMBER_TABLE + " AS rm " +
                " LEFT JOIN " + REGISTRATION_TABLE + " AS r " +
                " ON r.CountryCode=rm.CountryCode " +
                " AND r.DistrictName=rm.DistrictName " +
                " AND r.UpazillaName=rm.UpazillaName " +
                " AND r.UnitName=rm.UnitName " +
                " AND r.VillageName=rm.VillageName " +
                " AND r.RegistrationID=rm.RegisterID " +
                " LEFT JOIN " + RELATION_TABLE + " AS rel " +
                " ON rm." + RELATION_COL + "=rel." + RELATION_CODE +
                " WHERE rm." + COUNTRY_CODE + "='" + str_c_code + "' " +
                " AND rm." + DISTRICT_NAME_COL + "='" + str_district + "' " +
                " AND rm." + UPZILLA_NAME_COL + "='" + str_upazilla + "' " +
                " AND rm." + UNITE_NAME_COL + "='" + str_union + "' " +
                " AND rm." + VILLAGE_NAME_COL + "='" + str_village + "' " +
                " AND rm." + HHID_COL + "='" + hhID + "' " +
                " ORDER BY rm." + ID_COL + " DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                MemberModel member = new MemberModel();

                member.setPID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("PID"))));
                member.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));
                member.setRegID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                member.setName(cursor.getString(cursor.getColumnIndex(PNAME_COL)));

                member.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                member.setUpazilla(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                member.setUnitName(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                member.setVillage(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));

                member.setCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE)));
                member.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                member.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                member.setUnitNameCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                member.setVillageCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));


                member.setMemID(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                member.setMemberName(cursor.getString(cursor.getColumnIndex(MEM_NAME_COL)));
                member.setGender(cursor.getString(cursor.getColumnIndex(SEX_COL)));

                // TODO :: Need to check relation in Member table and needs to save the Relation code
                // TODO :: Need to select relation according to code in Member Detail page

                member.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                member.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));

                member.setRelation(cursor.getString(cursor.getColumnIndex(RELATION_COL)));
                member.setRelationCode(cursor.getString(cursor.getColumnIndex(RELATION_NAME)));


                member.setLMPDate(cursor.getString(cursor.getColumnIndex(LMP_DATE)));
                member.setChildDOB(cursor.getString(cursor.getColumnIndex(CHILD_DOB)));
                member.setElderly(cursor.getString(cursor.getColumnIndex(ELDERLY)));
                member.setDisabled(cursor.getString(cursor.getColumnIndex(DISABLE)));
                member.setMemberAge(cursor.getString(cursor.getColumnIndex(MEM_AGE)));


                // adding all into personList array
                memberList.add(member);

            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return memberList;
    }


    public ListDataModel getSingleRegisteredData(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String hhId) {

        //List<ListDataModel> personList = new ArrayList<ListDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";


        query = SQLiteQuery.getSingleRegisteredData_sql(cCode, layR1Code, layR2Code, layR3Code, layR4Code, hhId);
        ListDataModel person = new ListDataModel();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {


                person.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));


                person.setCountryName(cursor.getString(cursor.getColumnIndex(COUNTRY_NAME)));
                person.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                person.setUpazilla(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                person.setUnitName(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                person.setVillage(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));

                person.setCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                person.setDistrictCode(cursor.getString(cursor.getColumnIndex("R_District")));
                person.setUpazillaCode(cursor.getString(cursor.getColumnIndex("R_Upazilla")));
                person.setUnitNameCode(cursor.getString(cursor.getColumnIndex("R_Union")));
                person.setVillageCode(cursor.getString(cursor.getColumnIndex("R_Village")));
                person.setRegID(cursor.getString(cursor.getColumnIndex(PID_COL)));
                person.setRegDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                person.setName(cursor.getString(cursor.getColumnIndex(PNAME_COL)));
                person.setSex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                person.setHhSize(cursor.getString(cursor.getColumnIndex(HH_SIZE)));
                person.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
                person.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE_COL)));
                person.setAgLand(cursor.getString(cursor.getColumnIndex(AG_LAND)));
                person.setvStatus(cursor.getString(cursor.getColumnIndex(V_STATUS)));
                person.setmStatus(cursor.getString(cursor.getColumnIndex(M_STATUS)));
                person.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                person.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                person.setVSLAGroup(cursor.getString(cursor.getColumnIndex(VSLA_GROUP)));

                person.setAddressCode(cursor.getString(cursor.getColumnIndex("addcode")));
                person.setAddressName(cursor.getString(cursor.getColumnIndex("addname")));


                // adding all into personList array


            }
            cursor.close();
            db.close();
        }


        // return contact list
        return person;
    }

    /**
     * Getting All Contacts
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN VIEW PAGE
     * todo: address code & address Name
     */
    public List<ListDataModel> getRegisteredData(String ext_village, final String hhIdOrHHName) {

        List<ListDataModel> personList = new ArrayList<ListDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "";

        if (ext_village != null) {

            if (!hhIdOrHHName.equals("")) {
                if (isAlpha(hhIdOrHHName)) {
                    query = SQLiteQuery.getRegisteredData_ifVillageExt_SearchByName_sql(ext_village, hhIdOrHHName);
                } else {
                    query = SQLiteQuery.getRegisteredData_ifVillageExt_SelectQuery(ext_village, hhIdOrHHName);
                }
            } else {
                query = SQLiteQuery.getRegisteredData_ifVillageExt_SelectQuery(ext_village, hhIdOrHHName);
            }


        } else {
            query = SQLiteQuery.getRegisteredData_ifVillage_NOT_Ext_SelectQuery();
        }

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ListDataModel person = new ListDataModel();

                person.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_COL))));


                person.setCountryName(cursor.getString(cursor.getColumnIndex(COUNTRY_NAME)));
                person.setDistrict(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                person.setUpazilla(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                person.setUnitName(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                person.setVillage(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));

                person.setCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                person.setDistrictCode(cursor.getString(cursor.getColumnIndex("R_District")));
                person.setUpazillaCode(cursor.getString(cursor.getColumnIndex("R_Upazilla")));
                person.setUnitNameCode(cursor.getString(cursor.getColumnIndex("R_Union")));
                person.setVillageCode(cursor.getString(cursor.getColumnIndex("R_Village")));
                person.setRegID(cursor.getString(cursor.getColumnIndex(PID_COL)));
                person.setRegDate(cursor.getString(cursor.getColumnIndex(REG_DATE_COL)));
                person.setName(cursor.getString(cursor.getColumnIndex(PNAME_COL)));
                person.setSex(cursor.getString(cursor.getColumnIndex(SEX_COL)));
                person.setHhSize(cursor.getString(cursor.getColumnIndex(HH_SIZE)));
                person.setLatitude(cursor.getString(cursor.getColumnIndex(LATITUDE_COL)));
                person.setLongitude(cursor.getString(cursor.getColumnIndex(LONGITUDE_COL)));
                person.setAgLand(cursor.getString(cursor.getColumnIndex(AG_LAND)));
                person.setvStatus(cursor.getString(cursor.getColumnIndex(V_STATUS)));
                person.setmStatus(cursor.getString(cursor.getColumnIndex(M_STATUS)));
                person.setEntryBy(cursor.getString(cursor.getColumnIndex(ENTRY_BY)));
                person.setEntryDate(cursor.getString(cursor.getColumnIndex(ENTRY_DATE)));
                person.setVSLAGroup(cursor.getString(cursor.getColumnIndex(VSLA_GROUP)));

                person.setAddressCode(cursor.getString(cursor.getColumnIndex("addcode")));
                person.setAddressName(cursor.getString(cursor.getColumnIndex("addname")));

                person.setRank(cursor.getString(cursor.getColumnIndex("wRank")));


                // adding all into personList array
                personList.add(person);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return contact list
        return personList;
    }

    public String getDistributionStatusFromDistributionTable(String cCode, String donorCode, String awardCode, String districtCode, String upCode, String unCode, String vilCode, String progCode, String srvCode, String distMonthCode, String fdpCode, String distFlag, String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "-";

        String status = "";
        selectQuery = SQLiteQuery.getDistributionStatusFromDistributionTableQuery(cCode, donorCode, awardCode, districtCode, upCode, unCode, vilCode, progCode, srvCode, distMonthCode, fdpCode, distFlag, id);

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                status = cursor.getString(cursor.getColumnIndex(DISTRIBUTION_STATUS_COL));
            }
            cursor.close();
            db.close();
        }
        Log.d("All_4", "status:" + status + "\n length :" + status.length());
        if (!status.equals("null") && !(status.length() == 0)) {
            status = "R";
        } else {
            status = "-";
        }

        return status;
    }

    /**
     * Getting All Recodr from Service  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN Distribution Activity
     */
    public List<DistributionGridDataModel> getDistributionDataList(String cCode, String donorCode, String awardCode, String progCode, String srvOpMonthCode, String fdpCode, String searchMem) {

        Log.d(TAG, "In get data Distribution ");

        List<DistributionGridDataModel> distributedList = new ArrayList<DistributionGridDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = SQLiteQuery.getDistributionGridShowData(cCode, donorCode, awardCode, progCode, srvOpMonthCode, fdpCode, searchMem);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                DistributionGridDataModel distbutedPerson = new DistributionGridDataModel();

                distbutedPerson.setC_code(cursor.getString(cursor.getColumnIndex("country")));
                distbutedPerson.setDonorCode(cursor.getString(cursor.getColumnIndex("donor")));
                distbutedPerson.setAwardCode(cursor.getString(cursor.getColumnIndex("award")));
                distbutedPerson.setDistrictCode(cursor.getString(cursor.getColumnIndex("district")));
                distbutedPerson.setUpazillaCode(cursor.getString(cursor.getColumnIndex("upzella")));
                distbutedPerson.setUnitCode(cursor.getString(cursor.getColumnIndex("unite")));
                distbutedPerson.setVillageCode(cursor.getString(cursor.getColumnIndex("village")));

                if (donorCode.equals("01") && awardCode.equals("01") && progCode.equals("001")) {
                    if (i % 2 == 0) {
                        distbutedPerson.setRpt_id(cursor.getString(cursor.getColumnIndex("HHID")));
                        distbutedPerson.setRpt_name(cursor.getString(cursor.getColumnIndex("HhName")));
                        distbutedPerson.setServiceShortName("HHR");
                        distbutedPerson.setService_code("05");
                    } else {
                        distbutedPerson.setRpt_id(cursor.getString(cursor.getColumnIndex("MEMBERID")));
                        distbutedPerson.setRpt_name(cursor.getString(cursor.getColumnIndex("MemName")));
                        distbutedPerson.setServiceShortName(cursor.getString(cursor.getColumnIndex("srvName")));
                        distbutedPerson.setService_code(cursor.getString(cursor.getColumnIndex("service")));
                    }

                } else {


                    distbutedPerson.setRpt_id(cursor.getString(cursor.getColumnIndex("MEMBERID")));
                    distbutedPerson.setRpt_name(cursor.getString(cursor.getColumnIndex("MemName")));
                    distbutedPerson.setServiceShortName(cursor.getString(cursor.getColumnIndex("srvName")));
                    distbutedPerson.setService_code(cursor.getString(cursor.getColumnIndex("service")));
                }

              /*  distbutedPerson.setHh_id(cursor.getString(cursor.getColumnIndex("HHID")));
                distbutedPerson.setHh_name(cursor.getString(cursor.getColumnIndex("HhName")));
                distbutedPerson.setMem_id(cursor.getString(cursor.getColumnIndex("MEMBERID")));
                //final String  memberId=cursor_1.getString(cursor_1.getColumnIndex(HH_MEM_ID));
                distbutedPerson.setMem_name(cursor.getString(cursor.getColumnIndex("MemName")));
                distbutedPerson.setServiceShortName(cursor.getString(cursor.getColumnIndex("srvName")));*/
                distbutedPerson.setProgram_code(cursor.getString(cursor.getColumnIndex("program")));
                distbutedPerson.setWd(cursor.getString(cursor.getColumnIndex("wd")));


                // distbutedPerson.setMember_age(cursor.getString(5));


                // distbutedPerson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                // sperson.setMEMID(cursor_1.getString(cursor_1.getColumnIndex(HH_MEM_ID)));
                // distbutedPerson.setProgram_code(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                //  distbutedPerson.setService_code(cursor.getString(cursor.getColumnIndex(SERVICE_CODE_COL)));
                // distbutedPerson.setGetSrvMemCount(cursor.getString(cursor.getColumnIndex("SrvRecieved")));
                //   distbutedPerson.setNewID(cursor.getString(cursor.getColumnIndex("NewID")));
                //ToString()
                // Log.d(TAG, DatabaseUtils.dumpCursorToString(cursor_1));
                //  Log.d(TAG, cursor_1.getString(1) + " , " + cursor_1.getString(2) + " , " + cursor_1.getString(3) + " , " + cursor_1.getString(4) + " , " + cursor_1.getString(5) + " , " +
                //        cursor_1.getString(6) + " , " + cursor_1.getString(7) + " , " + cursor_1.getString(8) + " , " + cursor_1.getString(9));
                distributedList.add(distbutedPerson);
                i++;

            } while (cursor.moveToNext());
        }

        // Log.d("NIM", DatabaseUtils.dumpCursorToString(cursor));
        cursor.close();
        db.close();

        return distributedList;/// ther select per son to get service
    }

    public String getVoucherRefNoFromDistExted(String cCode, String discode, String upCode, String unCode, String vCode,
                                               String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {

        Log.d(TAG, "In get data Service ");

        String voiReference = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


// Grid data will load from DistExtended table
        selectQuery = "SELECT " + VOUCHER_REFERENCE_NUMBER_COL +
                " FROM  " + DISTRIBUTION_EXTENDED_TABLE +
                " WHERE  " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND  " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND  " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND  " + DISTRICT_CODE_COL + " = '" + discode + "' " +
                " AND  " + UPCODE_COL + " = '" + upCode + "' " +
                " AND  " + UCODE_COL + " = '" + unCode + "' " +
                " AND  " + VCODE_COL + " = '" + vCode + "' " +
                " AND  " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND  " + SERVICE_CODE_COL + " = '" + serviceCode + "' " +
                " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND  " + MEM_ID_15_D_COL + " = '" + memId + "' " +
                " AND  " + FDP_CODE_COL + " = '" + fdpCode + "' "
        ;


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                voiReference = cursor.getString(cursor.getColumnIndex(VOUCHER_REFERENCE_NUMBER_COL));
            }
            cursor.close();
            db.close();
        }


        return voiReference;/// ther select per son to get service
    }


    /**
     * Getting All Recodr from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     */
    public List<VouItemServiceExtDataModel> getDistExtedVoucherDataList(String cCode, String discode, String upCode, String unCode, String vCode,
                                                                        String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode, boolean dataExitstsInDistTable) {

        Log.d(TAG, "In get data Service ");

        List<VouItemServiceExtDataModel> srvExtListItem = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";

        if (dataExitstsInDistTable) {
// todo : testing
// Grid data will load from DistExtended table
            selectQuery = "SELECT " + COUNTRY_CODE_COL
                    + "  , " + DONOR_CODE_COL + " , " + AWARD_CODE_COL +
                    " , " + DISTRICT_CODE_COL + " , " + UPCODE_COL +
                    " , " + UCODE_COL + " , " + VCODE_COL +
                    " , " + PROGRAM_CODE_COL + " , " + SERVICE_CODE_COL +
                    " , " + OP_MONTH_CODE_COL +
                    " , (Select " + ITEM_NAME_COL + " from " + VOUCHER_ITEM_TABLE +
                    " where " + CATEGORY_CODE_COL + " || " + ITEM_CODE_COL + " = substr(" + VOUCHER_ITEM_SPEC_COL + ",0,8)) AS ItemName " +
                    " , (Select " + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " from " + VOUCHER_ITEM__MEAS_TABLE +
                    " where " + MEAS_R_CODE_COL + " = substr(" + VOUCHER_ITEM_SPEC_COL + ",8,3) ) as measerment " +
                    " , " + VOUCHER_REFERENCE_NUMBER_COL +
                    " , " + VOUCHER_UNIT_COL +
                    " , " + VOUCHER_ITEM_SPEC_COL +
                    " FROM  " + DISTRIBUTION_EXTENDED_TABLE +
                    " WHERE  " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                    " AND  " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                    " AND  " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                    " AND  " + DISTRICT_CODE_COL + " = '" + discode + "' " +
                    " AND  " + UPCODE_COL + " = '" + upCode + "' " +
                    " AND  " + UCODE_COL + " = '" + unCode + "' " +
                    " AND  " + VCODE_COL + " = '" + vCode + "' " +
                    " AND  " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                    " AND  " + SERVICE_CODE_COL + " = '" + serviceCode + "' " +
                    " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                    " AND  " + MEM_ID_15_D_COL + " = '" + memId + "' " +
                    " AND  " + FDP_CODE_COL + " = '" + fdpCode + "' "
            ;

        } else {
            // Grid data will load from SrvExtended table table


            selectQuery = "SELECT " + COUNTRY_CODE_COL
                    + "  , " + DONOR_CODE_COL + " , " + AWARD_CODE_COL +
                    " , " + DISTRICT_CODE_COL + " , " + UPCODE_COL +
                    " , " + UCODE_COL + " , " + VCODE_COL +
                    " , " + PROGRAM_CODE_COL + " , " + SERVICE_CODE_COL +
                    " , " + OP_MONTH_CODE_COL +
                    " , "
                    + VOUCHER_ITEM_TABLE + "." + ITEM_NAME_COL
                    + " AS ItemName " +
                    " , "
                    + VOUCHER_ITEM__MEAS_TABLE + "." + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL
                    + " AS measerment " +
                    " , " + VOUCHER_REFERENCE_NUMBER_COL +
                    " , " + VOUCHER_UNIT_COL +
                    " , " + VOUCHER_ITEM_SPEC_COL +
                    " FROM  " + SERVICE_EXTENDED_TABLE +
                    " INNER JOIN " + VOUCHER_ITEM_TABLE
                    + " ON " + VOUCHER_ITEM_TABLE + "." + CATEGORY_CODE_COL + " || " + VOUCHER_ITEM_TABLE + "." + ITEM_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",0,8)"
                    + " INNER JOIN " + VOUCHER_ITEM__MEAS_TABLE
                    + " ON " + VOUCHER_ITEM__MEAS_TABLE + "." + MEAS_R_CODE_COL + " = substr(" + SERVICE_EXTENDED_TABLE + "." + VOUCHER_ITEM_SPEC_COL + ",8,3)  "
                    + " WHERE  " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                    " AND  " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                    " AND  " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                    " AND  " + DISTRICT_CODE_COL + " = '" + discode + "' " +
                    " AND  " + UPCODE_COL + " = '" + upCode + "' " +
                    " AND  " + UCODE_COL + " = '" + unCode + "' " +
                    " AND  " + VCODE_COL + " = '" + vCode + "' " +
                    " AND  " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                    " AND  " + SERVICE_CODE_COL + " = '" + serviceCode + "' " +
                    " AND  " + OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                    " AND  " + DISTRICT_CODE_COL + " || " + UPCODE_COL + " || " + UCODE_COL + " || " + VCODE_COL + " || " + HHID_COL + " || " + HH_MEM_ID + " = '" + memId + "' ";
        }

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                items.setDonor_code(cursor.getString(cursor.getColumnIndex(DONOR_CODE_COL)));
                items.setAward_code(cursor.getString(cursor.getColumnIndex(AWARD_CODE_COL)));
                items.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_CODE_COL)));
                items.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPCODE_COL)));
                items.setUnitCode(cursor.getString(cursor.getColumnIndex(UCODE_COL)));
                items.setVillageCode(cursor.getString(cursor.getColumnIndex(VCODE_COL)));
                items.setProgram_code(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                items.setService_code(cursor.getString(cursor.getColumnIndex(SERVICE_CODE_COL)));
                items.setOpMontheCode(cursor.getString(cursor.getColumnIndex(OP_MONTH_CODE_COL)));
                items.setItemName(cursor.getString(cursor.getColumnIndex("ItemName")));
                items.setMeasurments(cursor.getString(cursor.getColumnIndex("measerment")));
//                items.setCheckBox(cursor.getString(cursor.getColumnIndex("checkedItem")).equals("False") ? false : true);
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_REFERENCE_NUMBER_COL)));
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_UNIT_COL)));
                items.setVoItmSpec(cursor.getString(cursor.getColumnIndex(VOUCHER_ITEM_SPEC_COL)));


                Log.d(TAG, " Dist Extended  list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));
                srvExtListItem.add(items);

            } while (cursor.moveToNext());


            cursor.close();
            db.close();
        }


        return srvExtListItem;/// ther select per son to get service
    }


    /**
     * Getting All Recodr from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     */
    public List<VouItemServiceExtDataModel> getSrvExtedVoucherDataList(String cCode, String discode, String upCode, String unCode, String vCode,
                                                                       String hhId, String memId
            , String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode) {

        //  Log.d(TAG, "In get data Service ");

        List<VouItemServiceExtDataModel> itemList = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = "select (Select " + ITEM_NAME_COL + " from " + VOUCHER_ITEM_TABLE +

                " where " + CATEGORY_CODE_COL + " || " + ITEM_CODE_COL + " = substr(VOCPI." + VOUCHER_ITEM_SPEC_COL + ",0,8))" +
                " ||'-'|| (Select " + UNITE_MEAS_COL + " ||' '|| " + MEASE_TITLE_COL + " from " + VOUCHER_ITEM__MEAS_TABLE +

                " where " + MEAS_R_CODE_COL + " = VOCPI." + MEAS_R_CODE_COL + " ) as item " +
                " ,   (case when SrET." + VOUCHER_ITEM_SPEC_COL + " is null then 'False' else 'True' end ) as checkedItem " +
                " , SrET." + VOUCHER_UNIT_COL + " AS  " + VOUCHER_UNIT_COL +
                " , VOCPI." + VOUCHER_ITEM_SPEC_COL + " AS " + VOUCHER_ITEM_SPEC_COL +
                " from " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + " as VOCPI" +
                " left join  " + SERVICE_EXTENDED_TABLE + " as  SrET" +
                "       on " +
                " SrET." + COUNTRY_CODE_COL + " = VOCPI." + COUNTRY_CODE_COL +

                " and  SrET." + AWARD_CODE_COL + " = VOCPI." + AWARD_CODE_COL
                + " and        SrET." + DONOR_CODE_COL + " = VOCPI." + DONOR_CODE_COL +
                " and        SrET." + VOUCHER_ITEM_SPEC_COL + " = VOCPI." + VOUCHER_ITEM_SPEC_COL +
                " and        SrET." + DISTRICT_CODE_COL + " = '" + discode + "'" +
                " and SrET." + UPCODE_COL + " = '" + upCode + "'" +
                " and SrET." + UCODE_COL + " = '" + unCode + "'" +
                " and SrET." + VCODE_COL + " = '" + vCode + "'" +
                " and SrET." + HHID_COL + " = '" + hhId + "'" +
                " and SrET." + HH_MEM_ID + " = '" + memId + "'" +
                " and SrET." + PROGRAM_CODE_COL + " = '" + programCode + "'" +
                " and SrET." + SERVICE_CODE_COL + " = '" + serviceCode + "'" +
                " and SrET." + OPERATION_CODE_COL + " = '2'" +      // opcode 2 mean s service
                " and SrET." + OP_MONTH_CODE_COL + " = '" + opMonthCode + "'" +
                " where VOCPI." + COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and VOCPI." + DONOR_CODE_COL + " = '" + donorCode + "'" +
                " and VOCPI." + AWARD_CODE_COL + " = '" + awardCode + "'" +
                " and VOCPI." + SERVICE_CODE_COL + " = '" + serviceCode + "'";


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setItemName(cursor.getString(cursor.getColumnIndex("item")));
                items.setCheckBox(cursor.getString(cursor.getColumnIndex("checkedItem")).equals("False") ? false : true);
                items.setVoItmUnit(cursor.getString(cursor.getColumnIndex(VOUCHER_UNIT_COL)));
                items.setVoItmSpec(cursor.getString(cursor.getColumnIndex(VOUCHER_ITEM_SPEC_COL)));


            /*    Log.d(TAG, " Voucher  list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                itemList.add(items);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemList;/// ther select per son to get service
    }

    public boolean isDataExitedDistExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                                 String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {

        boolean dataExits = false;

        String selectDelete = " Select * from " + DISTRIBUTION_EXTENDED_TABLE + " where " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.UCODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.VCODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.MEM_ID_15_D_COL + " = '" + memId + "' "
                + " AND " + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' ";
        // + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDelete, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;


    }


    public void deleteFromDistExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                            String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode, String fdpCode) {


        String selectDelete = " Delete from " + DISTRIBUTION_EXTENDED_TABLE + " where " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.UCODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.VCODE_COL + " = '" + vCode + "' "
                // + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.MEM_ID_15_D_COL + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' ";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectDelete);

        Log.d(TAG, " delete from Srv Extended table  row ");
        db.close();

    }

    public void deleteFromServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                               String hhId, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode/*, String voItmSpec*/) {


        String selectDelete = " Delete from " + SERVICE_EXTENDED_TABLE + " where " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.UCODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.VCODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";
        //  + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' "
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectDelete);

        Log.d(TAG, " delete from Srv Extended table  row ");
        db.close();

    }


    public boolean isDataExitedServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                                    String hhId, String memId, String donorCode, String awardCode, String programCode, String serviceCode, String opMonthCode/*, String voItmSpec*/) {

        boolean dataExits = false;

        String selectDelete = " Select * from " + SERVICE_EXTENDED_TABLE + " where " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + discode + "' "
                + " AND " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.UCODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.VCODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + serviceCode + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' ";
        // + " AND " + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + voItmSpec + "' ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectDelete, null);

        if (cursor.getCount() > 0) {
            dataExits = true;
        }
        cursor.close();
        db.close();


        return dataExits;


    }

    public List<VouItemServiceExtDataModel> getDefaultVoucherItemRespectToProgram(String cCode, String donorCode, String awardCode, String progCode, String srvCode) {
        List<VouItemServiceExtDataModel> itemList = new ArrayList<VouItemServiceExtDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();


        String sql = "SELECT ( " + SQLiteQuery.get_VOItmUnitMeas(VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + MEAS_R_CODE_COL) + " ) AS Unit " +
                " , " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + VOUCHER_ITEM_SPEC_COL
                + " , " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + UNITE_COST_COL
                + " FROM  " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND " + SERVICE_CODE_COL + " = '" + srvCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                VouItemServiceExtDataModel items = new VouItemServiceExtDataModel();

                items.setVoItmUnit(cursor.getString(0));
                items.setVoItmSpec(cursor.getString(1));
                items.setVoItemCost(cursor.getString(2));


                Log.d("FAll", " Voucher  list data" + cursor.getString(0) + " , " + cursor.getString(1));/* + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                itemList.add(items);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return itemList;/// ther select per son to get service

    }


    /* added by service */

    /**
     * @param cCode       country Code
     * @param discode     layer1 Code
     * @param upCode      layer2 Code
     * @param unCode      layer3 Code
     * @param vCode       layer4 Code
     * @param hhId        house hold id
     * @param memId       member id
     * @param donorCode   donor code
     * @param awardCode   awardCode
     * @param prgCode     programCode
     * @param srvCode     serviceCode
     * @param opCode      operation Code
     * @param opMonthCode operation Month Code
     * @param voItmSpec   voucher Item Specefic
     * @param voUnit      Voucher unite
     * @param voRefeNo    Voucher Reference No
     * @param voItmCost   Voucher Item Cost
     * @param entryBy     seasson manager
     * @param entryDate   seasson Date
     * @param is_online   is it come from online
     */
    public void addServiceExtendedTable(String cCode, String discode, String upCode, String unCode, String vCode,
                                        String hhId, String memId, String donorCode, String awardCode, String prgCode,
                                        String srvCode, String opCode, String opMonthCode, String voItmSpec, String voUnit, String voRefeNo,
                                        String voItmCost, String distFlag, String entryBy, String entryDate, String is_online) {


/*      for test  Log.d(TAG, "Service Extended Value befor SQLite :"
                + "cCode :" + cCode + " discode : " + discode + "upCode : " + upCode + "unCode : " + unCode +
                "vCode :" + vCode + "hhId :" + hhId + "memId : " + memId + "donorCode : " + donorCode + "awardCode : " + awardCode
                + "programCode :" + programCode + "serviceCode :" + serviceCode + "opCode :" + opCode + "opMonthCode:" + opMonthCode
                + "voItmSpec: " + voItmSpec + "voUnit :" + voUnit + "voRefeNo:" + voRefeNo
                + "voItmCost :" + voItmCost + "entryBy :" + entryBy + "entryDate: " + entryDate);*/

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE, cCode);
        values.put(DISTRICT_CODE_COL, discode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);
        values.put(HHID_COL, hhId);
        values.put(HH_MEM_ID, memId);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, prgCode);
        values.put(SERVICE_CODE_COL, srvCode);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(VOUCHER_ITEM_SPEC_COL, voItmSpec);
        values.put(VOUCHER_UNIT_COL, voUnit);
        values.put(VOUCHER_REFERENCE_NUMBER_COL, voRefeNo);
        values.put(VOUCHER_ITEM_COST_COL, voItmCost);
        values.put(DIST_FLAG_COL, distFlag);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        values.put(SYNC_COL, is_online); // Sync Status


        // Inserting Row
        long id = db.insert(SERVICE_EXTENDED_TABLE, null, values);
        db.close(); // Closing database connection

    /*    Log.d(TAG, "New Service Extended  data added  Service Extended Table: " + id);*/
    }


    public String getComunityGroupNameFromServiceTable(String cCode, String donorCode, String awardCode,
                                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {
        String groupName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "Select " + COMMUNITY_GROUP_TABLE + "." + GROUP_NAME_COL + " From " + SERVICE_TABLE
                + " " + " inner join " + COMMUNITY_GROUP_TABLE
                + " on " + SERVICE_TABLE + "." + COUNTRY_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + COUNTRY_CODE_COL
                + " and " + SERVICE_TABLE + "." + AWARD_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + AWARD_CODE_COL
                + " and " + SERVICE_TABLE + "." + DONOR_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + DONOR_CODE_COL
                + " and " + SERVICE_TABLE + "." + PROGRAM_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + PROGRAM_CODE_COL
                + " and  " + SERVICE_TABLE + "." + GROUP_CODE_COL + " = " + COMMUNITY_GROUP_TABLE + "." + GROUP_CODE_COL
                + " Where "
                + SERVICE_TABLE + "." + COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " and " + SERVICE_TABLE + "." + AWARD_CODE_COL + " = '" + awardCode + "'"
                + " and " + SERVICE_TABLE + "." + DONOR_CODE_COL + " = '" + donorCode + "'"
                + " and " + SERVICE_TABLE + "." + DISTRICT_CODE_COL + " = '" + distCode + "'"
                + " and " + SERVICE_TABLE + "." + UPCODE_COL + " = '" + upCode + "'"
                + " and " + SERVICE_TABLE + "." + UCODE_COL + " = '" + unCode + "'"
                + " and " + SERVICE_TABLE + "." + VCODE_COL + " = '" + vCode + "'"
                + " and " + SERVICE_TABLE + "." + HHID_COL + " = '" + hhId + "'"
                + " and " + SERVICE_TABLE + "." + HH_MEM_ID + " = '" + memId + "'"
                + " and " + SERVICE_TABLE + "." + PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " and " + SERVICE_TABLE + "." + SERVICE_CODE_COL + " = '" + srvCode + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                groupName = cursor.getString(0);
                if (groupName.equals("null"))
                    groupName = "";
            }
        }

        return groupName;

    }

    /**
     * This method get the short name Of the Program <p>
     *
     * @param awardCode Award Code
     * @param donorCode Donor Code
     * @param ProgCode  Program Code
     * @return program Short name
     */

    public String getProgramShortName(String awardCode, String donorCode, String ProgCode) {

        String progSName = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + PROGRAM_SHORT_NAME_COL + " FROM " + PROGRAM_MASTER_TABLE
                + " WHERE " + AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND  " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND  " + PROGRAM_CODE_COL + " = '" + ProgCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                progSName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return progSName;

    }

    /**
     * @param progCode Program Code
     * @param srvCode  Service Cod e
     * @return Service  Short name
     */

    public String getServiceShortName(String progCode, String srvCode) {
        String srvSName = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SERVICE_SHORT_NAME_COL
                + " FROM " + SERVICE_MASTER_TABLE
                + " WHERE  " + PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND " + SERVICE_CODE_COL + " = '" + srvCode + "' ";

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvSName = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvSName;
    }

    public String get_MemberMinSrvDate(String cCode, String donorCode, String awardCode,
                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {

        String srvMinDate = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SRV_MIN_DATE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.UCODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.VCODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvMinDate = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvMinDate;

    }

    public void updateSrvMinDate(String cCode, String donorCode, String awardCode, String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode, String srvMinDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.UCODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.VCODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'";
        ContentValues values = new ContentValues();
        values.put(SRV_MIN_DATE_COL, srvMinDate);
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, sql, null);
        Log.d("NI2", "id affected:" + id);
    }


    public String get_MemberMaxSrvDate(String cCode, String donorCode, String awardCode,
                                       String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode) {

        String srvMaxDate = "";
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + SRV_MAX_DATE_COL + " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.UCODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.VCODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                srvMaxDate = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }
        return srvMaxDate;

    }

    public void updateSrvMaxDate(String cCode, String donorCode, String awardCode, String distCode, String upCode, String unCode, String vCode
            , String hhId, String memId, String progCode, String srvCode, String srvMaxDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.DISTRICT_CODE_COL + " = '" + distCode + "'"
                + " AND  " + SQLiteHandler.UPCODE_COL + " = '" + upCode + "'"
                + " AND  " + SQLiteHandler.UCODE_COL + " = '" + unCode + "'"
                + " AND  " + SQLiteHandler.VCODE_COL + " = '" + vCode + "'"
                + " AND  " + SQLiteHandler.HHID_COL + " = '" + hhId + "'"
                + " AND  " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "'"
                + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'";
        ContentValues values = new ContentValues();
        values.put(SRV_MAX_DATE_COL, srvMaxDate);
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, sql, null);
        Log.d("NI2", "id affected:" + id);
    }


    // todo: impelements Graduatae Date to load data

    public List<ServiceDataModel> getFFAMemberListForService(String cCode, String donorCode,
                                                             String awardCode, String programCode,
                                                             String serviceCode, String mm_SearchId,
                                                             String opCode, String opMCode, String groupCode, String distFlag) {

//        Log.d(TAG, "In get data Service ");

        List<ServiceDataModel> sList = new ArrayList<ServiceDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = SQLiteQuery.getFFAMemberListForServiceSelectQuery(cCode, donorCode, awardCode,
                programCode, serviceCode, opCode, opMCode, mm_SearchId, groupCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ServiceDataModel sperson = new ServiceDataModel();

                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setHh_name(cursor.getString(cursor.getColumnIndex(PNAME_COL)));
                sperson.setMemberId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                sperson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));

                sperson.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                sperson.setDonor_code(cursor.getString(cursor.getColumnIndex(DONOR_CODE_COL)));
                sperson.setAward_code(cursor.getString(cursor.getColumnIndex(AWARD_CODE_COL)));
                sperson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_CODE_COL)));
                sperson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPCODE_COL)));
                sperson.setUnitCode(cursor.getString(cursor.getColumnIndex(UCODE_COL)));
                sperson.setVillageCode(cursor.getString(cursor.getColumnIndex(VCODE_COL)));
                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setProgram_code(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                sperson.setService_code(cursor.getString(cursor.getColumnIndex(SERVICE_CODE_COL)));
                sperson.setGetSrvMemCount(cursor.getString(cursor.getColumnIndex("SrvRecieved")));
                sperson.setNewID(cursor.getString(cursor.getColumnIndex("NewID")));
                sperson.setWorkingDay(cursor.getString(cursor.getColumnIndex("WD")));


/*                Log.d(TAG, " Service list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                sList.add(sperson);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sList;/// ther select per son to get service
    }


    /**
     * This method
     * Getting All Record from Registration member And Registration And ProgramAssngService  table
     * IT IS CALLED IN MW_RegisterViewRecord
     * IT SHOW ALL THE RECORD IN SERVICE Activity
     *
     * @param cCode       Country Code
     * @param donorCode   donor Code
     * @param awardCode   award Code
     * @param programCode programCode
     * @param serviceCode serviceCode
     * @param mm_SearchId mm_SearchId
     * @param opCode      Operation Code For Service The Op Code is 2
     * @param opMCode     OpmMonth Code For Service
     * @param groupCode   Community Group Code
     * @return member list
     */
    public List<ServiceDataModel> getMemberListForService(String cCode, String donorCode,
                                                          String awardCode, String programCode,
                                                          String serviceCode, String mm_SearchId,
                                                          String opCode, String opMCode, String groupCode, String distFlag) {

//        Log.d(TAG, "In get data Service ");

        List<ServiceDataModel> sList = new ArrayList<ServiceDataModel>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "";


        selectQuery = SQLiteQuery.getMemberListForServiceSelectQuery(cCode, donorCode, awardCode,
                programCode, serviceCode, opCode, opMCode, mm_SearchId, groupCode, distFlag);


        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ServiceDataModel sperson = new ServiceDataModel();

                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setHh_name(cursor.getString(cursor.getColumnIndex(PNAME_COL)));
                sperson.setMemberId(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
                sperson.setHh_mm_name(cursor.getString(cursor.getColumnIndex("memName")));

                sperson.setC_code(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                sperson.setDonor_code(cursor.getString(cursor.getColumnIndex(DONOR_CODE_COL)));
                sperson.setAward_code(cursor.getString(cursor.getColumnIndex(AWARD_CODE_COL)));
                sperson.setDistrictCode(cursor.getString(cursor.getColumnIndex(DISTRICT_CODE_COL)));
                sperson.setUpazillaCode(cursor.getString(cursor.getColumnIndex(UPCODE_COL)));
                sperson.setUnitCode(cursor.getString(cursor.getColumnIndex(UCODE_COL)));
                sperson.setVillageCode(cursor.getString(cursor.getColumnIndex(VCODE_COL)));
                sperson.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                sperson.setProgram_code(cursor.getString(cursor.getColumnIndex(PROGRAM_CODE_COL)));
                sperson.setService_code(cursor.getString(cursor.getColumnIndex(SERVICE_CODE_COL)));
                sperson.setGetSrvMemCount(cursor.getString(cursor.getColumnIndex("SrvRecieved")));
                sperson.setNewID(cursor.getString(cursor.getColumnIndex("NewID")));


           /*     Log.d(TAG, " Service list data" + cursor.getString(1) + " , " + cursor.getString(2) + " , " + cursor.getString(3) + " , "
                        + cursor.getString(4) + " , " + cursor.getString(5) + " , " +
                        cursor.getString(6) + " , " + cursor.getString(7) + " , " + cursor.getString(8) + " , " + cursor.getString(9));*/
                sList.add(sperson);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return sList;/// ther select per son to get service
    }

    /**
     * 2015-10-12
     * Faisal Mohammad
     * check Service date before insert the service
     */
    public String getLastServiceDate(ServiceDataModel si) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getLastServiceDateQuery(si);

        Cursor cursor = db.rawQuery(selectQuery, null);
        String lastdate = "";
        if (cursor.moveToFirst()) {
            lastdate = cursor.getString(cursor.getColumnIndex(SERVICE_DT_COL));
        }
        cursor.close();
        db.close();
        return lastdate;
    }


    public String get_VOUnitCost(String cCode, String donorCode, String awardCode, String progCode, String srvCode, String vOItmSpec) {
        SQLiteDatabase db = this.getReadableDatabase();
        String cost = "";
        String selectQuery = "Select " + UNITE_COST_COL + " from " + VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " where " + COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " and " + DONOR_CODE_COL + " = '" + donorCode + "'" +
                " and " + AWARD_CODE_COL + " = '" + awardCode + "'" +
                " and " + PROGRAM_CODE_COL + " = '" + progCode + "'" +
                " and " + SERVICE_CODE_COL + " = '" + srvCode + "'" +
                " and " + VOUCHER_ITEM_SPEC_COL + " = '" + vOItmSpec + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                cost = cursor.getString(cursor.getColumnIndex(UNITE_COST_COL));
            }
            cursor.close();
            db.close();
        }
        return cost;

    }

    public List<SummaryModel> getTotalRecords(String CountryId) {

        List<SummaryModel> listSummary = new ArrayList<SummaryModel>();

        int records = 0;
        String vill_name = "";

        //String query = "SELECT " + VILLAGE_NAME_COL + " AS Vill_Name, COUNT(*) AS records FROM " + REGISTRATION_TABLE + " GROUP BY " + VILLAGE_NAME_COL;
/*
        *****NOR VAI QUEY
        String query = "SELECT v." + VCODE_COL +" AS v_code,"+
                " v."+ VILLAGE_NAME_COL +" AS Vill_Name, COUNT(*) AS records FROM " + REGISTRATION_TABLE + " AS r LEFT JOIN " + VILLAGE_TABLE + " AS v ON r."+ VILLAGE_NAME_COL +"=v."+ VCODE_COL +"  GROUP BY v."+ VILLAGE_NAME_COL;

*/
        // POP CODE
        String query = "SELECT " + " v." + COUNTRY_CODE_COL + " || '' ||  v." + DISTRICT_CODE_COL + " || '' || v." + UPCODE_COL + " || '' || v." +
                UCODE_COL + " || '' || v." + VCODE_COL + " AS v_code," +
                " v." + VILLAGE_NAME_COL + " AS Vill_Name," +
                " COUNT(" + PID_COL + ") AS records FROM " + VILLAGE_TABLE + " AS v" +
                " LEFT JOIN " + REGISTRATION_TABLE + " AS r" +
                " ON r." + COUNTRY_CODE_COL + "=v." + COUNTRY_CODE_COL + " AND " +
                "r." + DISTRICT_NAME_COL + "=v." + DISTRICT_CODE_COL + " AND " +

                "r." + UPZILLA_NAME_COL + "=v." + UPCODE_COL + " AND " +
                "r." + UNITE_NAME_COL + "=v." + UCODE_COL + " AND " +
                "r." + VILLAGE_NAME_COL + "=v." + VCODE_COL +

                " WHERE v." + COUNTRY_CODE_COL + "='" + CountryId + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                "  GROUP BY v." + COUNTRY_CODE_COL + ",v." + DISTRICT_CODE_COL + ",v." + UPCODE_COL + ",v." + UCODE_COL + ",v." + VCODE_COL;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                SummaryModel summary = new SummaryModel();

                summary.setVillageCode(cursor.getString(cursor.getColumnIndex("v_code")));
                summary.setVillageName(cursor.getString(cursor.getColumnIndex("Vill_Name")));
                summary.setRecords(cursor.getInt(cursor.getColumnIndex("records")));

                //records = cursor.getInt(cursor.getColumnIndex("records"));
                String checkedId = cursor.getString(cursor.getColumnIndex("v_code"));
                int noOfRecords = cursor.getInt(cursor.getColumnIndex("records"));
                if ((checkedId != null) && (noOfRecords > 0)) { /// IF village RECORD IS NOT ZERRO THAN IT INSERT
                    listSummary.add(summary);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        return listSummary;
    }

    public String getNextMemberID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        String query = "SELECT " + HH_MEM_ID + " AS max_rec FROM " + REGISTRATION_MEMBER_TABLE + " WHERE "
                + COUNTRY_CODE + "='" + str_c_code + "' AND "
                + DISTRICT_NAME_COL + "='" + str_district + "' AND "
                + UPZILLA_NAME_COL + "='" + str_upazilla + "' AND "
                + UNITE_NAME_COL + "='" + str_union + "' AND "
                + VILLAGE_NAME_COL + "='" + str_village + "' AND "
                + HHID_COL + "='" + hhID + "'" +
                " ORDER BY " + HH_MEM_ID + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return next_id;
    }

    public String getNextHouseHoldID(String c_code, String distID, String upID, String unit, String villID) {

        //String query = "SELECT COUNT(*) AS max_rec FROM " + REGISTRATION_TABLE + " WHERE "
        String query = "SELECT " + PID_COL + " AS max_rec FROM " + REGISTRATION_TABLE + " WHERE "
                + COUNTRY_CODE_COL + "='" + c_code + "' AND "
                + DISTRICT_NAME_COL + "='" + distID + "' AND "
                + UPZILLA_NAME_COL + "='" + upID + "' AND "
                + UNITE_NAME_COL + "='" + unit + "' AND "
                + VILLAGE_NAME_COL + "='" + villID + "'" +
                " ORDER BY " + PID_COL + " DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // EDITED BY POP
        if (cursor.moveToFirst()) {

            next_id = cursor.getString(cursor.getColumnIndex("max_rec"));

        }


        cursor.close();
        db.close();

        return next_id;
    }

    public String getAutoIncrementID(String tableName) {

        String query = "SELECT * FROM " + SQLITE_SEQUENCE + " WHERE " + TABLE_NAME + "='" + tableName + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                next_id = cursor.getString(cursor.getColumnIndex("seq"));

            } while (cursor.moveToNext());
        }

        cursor.close();

        return next_id;
    }

    public String getMemberID(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String hhID) {

        String memberID = "";

        String next_id = getNextMemberID(str_c_code, str_district, str_upazilla, str_union, str_village, hhID);

        if (!next_id.isEmpty()) { // if next id is not empty
            Integer temp_id = Integer.parseInt(next_id); // convert it to int
            temp_id += 1;
            next_id = temp_id.toString();
        } else {
            next_id = "01";
        }

        int id_len = next_id.length();

        if (id_len < 2)
            memberID = "0" + next_id;
        else
            memberID = next_id;

        return "" + memberID;
    }

    public String getRegistrationID(String c_code, String distID, String upID, String unit, String villID) {

        String registrationID = "";

        String next_id = getNextHouseHoldID(c_code, distID, upID, unit, villID);

        if (!next_id.isEmpty()) {
            Integer temp_id = Integer.parseInt(next_id);
            temp_id++;
            next_id = temp_id.toString();
        } else {
            next_id = "1";
        }

        int id_len = next_id.length();


        registrationID = getPadding(id_len, next_id);

        return registrationID;
    }

    private String getPadding(int id_len, String next_id) {

        String padded_id = "";

        if (id_len > 0) {
            int pad = ID_LENGTH - id_len;

            for (int i = 0; i < pad; i++) {
                padded_id += "0";
            }
            padded_id = padded_id + next_id;
        } else {
            padded_id = "000001";
        }

        return padded_id;
    }

    public String getNextGroupId(String cCode, String donorCode, String awardCode, String progCode) {
        String grpCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + GROUP_CODE_COL + " FROM " + COMMUNITY_GROUP_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + PROGRAM_CODE_COL + " = '" + progCode + "'";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grpCode = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }


        if (!grpCode.isEmpty()) {
            Integer temp_id = Integer.parseInt(grpCode);
            temp_id++;
            grpCode = temp_id.toString();
        } else {
            grpCode = "1";
        }


        int grp_len = grpCode.length();
        String next_grp_id = grpCode;

        String padded_id = "";

        if (grp_len > 0) {
            int pad = 3 - grp_len;

            for (int i = 0; i < pad; i++) {
                padded_id += "0";
            }
            padded_id = padded_id + next_grp_id;
        } else {
            padded_id = "001";
        }

        return padded_id;

    }


    public String getVillageName(String criteria) {

        String selectQuery = "SELECT " + VILLAGE_NAME_COL + " FROM " + VILLAGE_TABLE + criteria;
        //selectLabel += getLayerLabel(cCode, "4");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        String villageName = "";

        if (cursor.moveToFirst()) {

            villageName = cursor.getString(0);
            // listItem.add(cursor.getString(0));


        }

        // closing connection
        cursor.close();
        db.close();
        return villageName;
    }

    /**
     * Getting list of any table with ID - Value pair
     * returns list of labels
     */
    public List<SpinnerHelper> getListAndID(String table_name, String criteria, String cCode, boolean countryLoad) {

        List<SpinnerHelper> listItem = new ArrayList<SpinnerHelper>();

        String selectQuery = "";
        String selectLabel = "Select ";

        switch (table_name) {


            case COUNTRY_TABLE:
                selectQuery = "SELECT DISTINCT " + COUNTRY_TABLE + "." + COUNTRY_CODE + ", " + COUNTRY_TABLE + "." + COUNTRY_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Country";

                break;

            case DISTRICT_TABLE:
                selectQuery = "SELECT " + table_name + "." + DISTRICT_CODE_COL + ", " + table_name + "." + DISTRICT_NAME_COL + " FROM " + table_name + criteria;
                //selectLabel += getLayerLabel(cCode, "1"); show select Country
                selectLabel = "Select " + getLayerLabel(cCode, "1");

                break;

            case UPAZILLA_TABLE:
                selectQuery = "SELECT " + table_name + "." + UPCODE_COL + ", " + table_name + "." + UPZILLA_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "2");

                break;

            case UNIT_TABLE:
                selectQuery = "SELECT " + table_name + "." + UCODE_COL + ", " + table_name + "." + UNITE_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "3");

                break;

            case VILLAGE_TABLE:
                selectQuery = "SELECT " + table_name + "." + VCODE_COL + ", " + table_name + "." + VILLAGE_NAME_COL + " FROM " + table_name + criteria;
                selectLabel += getLayerLabel(cCode, "4");

                break;

            case RELATION_TABLE:
                selectQuery = "SELECT " + RELATION_CODE + "," + RELATION_NAME + " FROM " + table_name + criteria;
                selectLabel = "Select Relation";
                //listItem.add("Select Village");
                break;
            case ADM_AWARD_TABLE:
                selectQuery = "SELECT " + ADM_AWARD_TABLE + "." + DONOR_CODE_COL + " || '' ||  " + ADM_AWARD_TABLE + "." + AWARD_CODE_COL + " AS AwardCode" + " , " +
                        ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_AWARD_TABLE + "." + AWARD_S_NAME_COL + " AS AwardName" +
                        " FROM " + table_name + " JOIN " + ADM_DONOR_TABLE + " ON " + ADM_AWARD_TABLE + "." + DONOR_CODE_COL + " = " + ADM_DONOR_TABLE + "." + DONOR_CODE_COL +
                        /* CHANGE*/    criteria + "GROUP BY " + ADM_DONOR_TABLE + "." + DONOR_NAME_COL + " || '-' ||  " + ADM_AWARD_TABLE + "." + AWARD_S_NAME_COL + " ORDER BY AwardName ";
                selectLabel = "Select Award";
                break;
            // Criteria for service
            case PROGRAM_MASTER_TABLE:
                selectQuery = "SELECT " + PROGRAM_MASTER_TABLE + "." + PROGRAM_CODE_COL + " || '' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_CODE_COL + " AS criteriaId" + " , " +
                        PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + table_name + " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_MASTER_TABLE + "." + PROGRAM_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + PROGRAM_CODE_COL +
                        criteria + " GROUP BY " + PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_SHORT_NAME_COL + " ORDER BY Criteria ";
                selectLabel = "Select Criteria";
                break;
            case GPS_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + GROUP_CODE_COL + "," + GROUP_NAME_COL + " FROM " + table_name + criteria;

                selectLabel = "Select Group";
                break;
            case GPS_SUB_GROUP_TABLE:
                selectQuery = "SELECT DISTINCT " + SUB_GROUP_CODE_COL + " , " + SUB_GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select sub Group";
                break;
            case GPS_LOCATION_TABLE:
                selectQuery = "SELECT " + LOCATION_CODE_COL + "," + LOCATION_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select location";
                break;
            // for Program spinner Assigne & gradution
            case COUNTRY_PROGRAM_TABLE:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL + " , " +
                        PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + table_name + " JOIN " + PROGRAM_MASTER_TABLE + " ON " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + PROGRAM_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + AWARD_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + AWARD_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + DONOR_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL;//+" GROUP BY "+PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;

            case SERVICE_MASTER_TABLE:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + SERVICE_CODE_COL + " AS criteriaId" + " , " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_NAME_COL + " || '-' ||  " + SERVICE_MASTER_TABLE + "." + SERVICE_SHORT_NAME_COL + " AS Criteria" +
                        " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + table_name +
                        " ON " + SERVICE_MASTER_TABLE + "." + PROGRAM_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL + " AND " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_CODE_COL + " = " + COUNTRY_PROGRAM_TABLE + "." + SERVICE_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + SERVICE_CODE_COL;
                      /*  "SELECT " + COUNTRY_PROGRAM_TABLE +"."+SERVICE_CODE_COL+" AS criteriaId" + " , "  +
                        SERVICE_MASTER_TABLE +"."+SERVICE_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL +" AS Criteria" +
                        " FROM " + COUNTRY_PROGRAM_TABLE  + " JOIN "+ table_name + " ON " +  SERVICE_MASTER_TABLE +"."+PROGRAM_CODE_COL +" = " +COUNTRY_PROGRAM_TABLE+"."+PROGRAM_CODE_COL +
                        criteria +" GROUP BY "+ COUNTRY_PROGRAM_TABLE +"."+SERVICE_CODE_COL;*/  // +" GROUP BY "+PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Criteria";
                break;

            case VILLAGE_TABLE_FOR_ASSIGN:
                selectQuery = "SELECT  v." +
                        COUNTRY_CODE_COL + " || '' || v." +
                        DISTRICT_CODE_COL + " || '' || v." + UPCODE_COL + " || '' || v." + UCODE_COL + " || '' || v." + VCODE_COL
                        + ", v." + VILLAGE_NAME_COL + " FROM " + VILLAGE_TABLE + criteria;
                selectLabel += getLayerLabel(cCode, "4");
                //listItem.add("Select Village");
                break;
            /** FOR SERVICE SUMMMARY OP_MONTH LOAD */

            case OP_MONTH_TABLE:
                selectQuery = "SELECT " + COUNTRY_CODE_COL + " || '' || " + DONOR_CODE_COL + " || '' || " + AWARD_CODE_COL + " || '' || " + OP_MONTH_CODE_COL + " AS OpMonthID, " + MONTH_LABEL + " FROM " + table_name + criteria;
                selectLabel = "Select Month";

                break;
            case SERVICE_SUMMARY_CRITERIA_QUERY:

                selectQuery = " SELECT " + SERVICE_MASTER_TABLE + "." + PROGRAM_CODE_COL + " || '' || " + SERVICE_MASTER_TABLE + "." + SERVICE_CODE_COL + " AS IdCriteria ,  " +
                        SERVICE_MASTER_TABLE + "." + SERVICE_SHORT_NAME_COL + " AS Criteria  " +

                        "FROM " + SERVICE_TABLE + " JOIN " + SERVICE_CENTER_TABLE
                        + " ON " + SERVICE_TABLE + "." + COUNTRY_CODE_COL + " = " + SERVICE_CENTER_TABLE + "." + COUNTRY_CODE_COL +

                        " JOIN " + OP_MONTH_TABLE
                        + " ON " + SERVICE_TABLE + "." + COUNTRY_CODE_COL + " = " + OP_MONTH_TABLE + "." + COUNTRY_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + OP_MONTH_CODE_COL + " = " + OP_MONTH_TABLE + "." + OP_MONTH_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + DONOR_CODE_COL + " = " + OP_MONTH_TABLE + "." + DONOR_CODE_COL + " AND " +
                        SERVICE_TABLE + "." + AWARD_CODE_COL + " = " + OP_MONTH_TABLE + "." + AWARD_CODE_COL + " " +
                        " JOIN " + SERVICE_MASTER_TABLE + " ON " + SERVICE_TABLE + "." + PROGRAM_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + PROGRAM_CODE_COL + " AND " +       // "\t   INNER JOIN AdmServiceMaster ON SrvTable.ProgCode = AdmServiceMaster.AdmProgCode AND \n" +
                        SERVICE_TABLE + "." + SERVICE_CODE_COL + " = " + SERVICE_MASTER_TABLE + "." + SERVICE_CODE_COL +
                        criteria +
                        " GROUP BY " + SERVICE_MASTER_TABLE + "." + SERVICE_SHORT_NAME_COL;
                selectLabel = "Select Criteria";

                break;
            case VILLAGE_TABLE_QUERY_FROM_REG:
                selectQuery = "SELECT " + STAFF_GEO_INFO_ACCESS_TABLE + "." + LAYR_LIST_CODE_COL + " , " + VILLAGE_NAME_COL + " FROM " + VILLAGE_TABLE
                        + " JOIN " + STAFF_GEO_INFO_ACCESS_TABLE +
                        " ON " + VILLAGE_TABLE + "." + COUNTRY_CODE_COL + " = " + STAFF_GEO_INFO_ACCESS_TABLE + "." + COUNTRY_CODE_COL +
                        " AND " + VILLAGE_TABLE + "." + DISTRICT_CODE_COL + " = " + STAFF_GEO_INFO_ACCESS_TABLE + "." + DISTRICT_CODE_COL +
                        " AND " + VILLAGE_TABLE + "." + UPCODE_COL + " = " + STAFF_GEO_INFO_ACCESS_TABLE + "." + UPCODE_COL +
                        " AND " + VILLAGE_TABLE + "." + UCODE_COL + " = " + STAFF_GEO_INFO_ACCESS_TABLE + "." + UCODE_COL +
                        " AND " + VILLAGE_TABLE + "." + VCODE_COL + " = " + STAFF_GEO_INFO_ACCESS_TABLE + "." + VCODE_COL +
                        criteria;
                selectLabel += getLayerLabel(cCode, "4");

                break;
            case VILLAGE_TABLE_QUERY_FOR_RECORDS:
                selectQuery = "SELECT " + " v." + COUNTRY_CODE_COL + " || '' ||  v." + DISTRICT_CODE_COL + " || '' || v." + UPCODE_COL + " || '' || v." +
                        UCODE_COL + " || '' || v." + VCODE_COL + " AS v_code," +
                        " v." + VILLAGE_NAME_COL + " AS Vill_Name " +
                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + VILLAGE_TABLE + " AS v" +
                        " LEFT JOIN " + REGISTRATION_TABLE + " AS r" +
                        " ON r." + COUNTRY_CODE_COL + "=v." + COUNTRY_CODE_COL + " AND " +
                        "r." + DISTRICT_NAME_COL + "=v." + DISTRICT_CODE_COL + " AND " +
                        "r." + UPZILLA_NAME_COL + "=v." + UPCODE_COL + " AND " +
                        "r." + UNITE_NAME_COL + "=v." + UCODE_COL + " AND " +
                        "r." + VILLAGE_NAME_COL + "=v." + VCODE_COL +

                        " WHERE v." + COUNTRY_CODE_COL + "='" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                        "  GROUP BY v." + COUNTRY_CODE_COL + ",v." + DISTRICT_CODE_COL + ",v." + UPCODE_COL + ",v." + UCODE_COL + ",v." + VCODE_COL;

                selectLabel += getLayerLabel(cCode, "4");
                break;
            case HOUSE_HOLD_CATEGORY_TABLE:
                selectQuery = "SELECT " + CATEGORY_CODE_COL + " , " + CATEGORY_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select House Hold Type";
                break;

            case REG_N_LUP_GRADUATION_TABLE:
                selectQuery = "SELECT " + GRD_CODE_COL + " , " + GRD_TITLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Reason";
                break;
            case SERVICE_CENTER_TABLE:
                selectQuery = "SELECT " + SERVICE_CENTER_CODE_COL + " , " +
                        SERVICE_CENTER_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Service Center ";
                break;
            case REPORT_TEMPLATE_TABLE: //@date:2015-11-04
                selectQuery = "SELECT " + REPORT_CODE_COL + " , " +
                        REPORT_LABLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Card Type ";
                break;
            case CARD_PRINT_REASON_TABLE: //@date:2015-11-04
                selectQuery = "SELECT " + CARD_PRINT_REASON_CODE_COL + " , " +
                        CARD_PRINT_REASON_TITLE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Reason";
                break;
            // to get Upzella Code & District Code
            case UPZELLA_TABLE_CUSTOM_QUERY:
                selectQuery = "SELECT " + UPAZILLA_TABLE + "." + DISTRICT_CODE_COL + " || " + UPAZILLA_TABLE + "." + UPCODE_COL + ", " + UPAZILLA_TABLE + "." + UPZILLA_NAME_COL + " FROM " + UPAZILLA_TABLE + criteria;
                selectLabel += getLayerLabel(cCode, "2");
                break;
            case STAFF_FDP_ACCESS_TABLE:
                selectQuery = "SELECT " + FDP_MASTER_TABLE + "." + FDP_CODE_COL + " AS " + FDP_CODE_COL + " , "
                        + FDP_MASTER_TABLE + "." + FDP_NAME_COL + " AS " + FDP_NAME_COL +
                        " FROM " + table_name + " INNER JOIN "
                        + FDP_MASTER_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + COUNTRY_CODE + " = " + FDP_MASTER_TABLE + "." + COUNTRY_CODE
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + COUNTRY_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL

                        + criteria;
                break;
            case LUP_SRV_OPTION_LIST_TABLE:
                selectQuery = " SELECT " + LUP_OPTION_CODE_COL + " , " + LUP_OPTION_NAME_COL
                        + " FROM " + LUP_SRV_OPTION_LIST_TABLE + " " + criteria;
                selectLabel = "Select Service";
                break;
            case ASSIGN_SUMMARY_PROGRAM_DETAILS:
                selectQuery = "SELECT " + COUNTRY_PROGRAM_TABLE + "." + DONOR_CODE_COL
                        + " || '' || " + COUNTRY_PROGRAM_TABLE + "." + AWARD_CODE_COL
                        + " || '' || " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL
                        + " , " +
                        PROGRAM_MASTER_TABLE + "." + PROGRAM_SHORT_NAME_COL +
                        " FROM " + COUNTRY_PROGRAM_TABLE + " JOIN " + PROGRAM_MASTER_TABLE + " ON " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + PROGRAM_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + AWARD_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + AWARD_CODE_COL +
                        " AND " + COUNTRY_PROGRAM_TABLE + "." + DONOR_CODE_COL + " = " + PROGRAM_MASTER_TABLE + "." + DONOR_CODE_COL + " " +
                        criteria + " GROUP BY " + COUNTRY_PROGRAM_TABLE + "." + PROGRAM_CODE_COL;//+" GROUP BY "+PROGRAM_MASTER_TABLE +"."+PROGRAM_SHORT_NAME_COL +" || '-' ||  " +SERVICE_MASTER_TABLE+"."+SERVICE_SHORT_NAME_COL+" ORDER BY Criteria ";
                selectLabel = "Select Program";
                break;
            case FDP_LAY_R2:
                selectQuery = " Select DISTINCT  " + UPAZILLA_TABLE + "." + DISTRICT_CODE_COL + " || " + UPAZILLA_TABLE + "." + UPCODE_COL + " AS code "
                        + " , " + UPAZILLA_TABLE + " ." + UPZILLA_NAME_COL + " AS Name "
                        + " FROM  " + STAFF_FDP_ACCESS_TABLE
                        + "  INNER JOIN         " + FDP_MASTER_TABLE
                        + "   ON         " + STAFF_FDP_ACCESS_TABLE + "." + COUNTRY_CODE_COL + " = " + FDP_MASTER_TABLE + "." + COUNTRY_CODE_COL
                        + "   AND         " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + FDP_MASTER_TABLE + "." + FDP_CODE_COL
                        + "   INNER JOIN    " + UPAZILLA_TABLE
                        + "   ON    " + STAFF_FDP_ACCESS_TABLE + "." + COUNTRY_CODE_COL + " = " + UPAZILLA_TABLE + "." + COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + COUNTRY_CODE_COL + " = " + UPAZILLA_TABLE + "." + COUNTRY_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + DISTRICT_CODE_COL + " = " + UPAZILLA_TABLE + "." + DISTRICT_CODE_COL
                        + "   AND   " + FDP_MASTER_TABLE + "." + UPCODE_COL + " = " + UPAZILLA_TABLE + "." + UPCODE_COL

                        + " INNER JOIN " + SELECTED_FDP_TABLE + " ON "
                        + STAFF_FDP_ACCESS_TABLE + "." + COUNTRY_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + COUNTRY_CODE_COL
                        + " AND " + STAFF_FDP_ACCESS_TABLE + "." + FDP_CODE_COL + " = " + SELECTED_FDP_TABLE + "." + FDP_CODE_COL


                        + criteria
                ;

                selectLabel = "Select ";
                break;
            case VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE:
                selectQuery = criteria;
                selectLabel = "Select Item";
                break;

            case CUSTOM_QUERY:
                selectQuery = criteria;
                selectLabel = "Select ";
                break;
            case LUP_COMMUNITY_ANIMAL_TABLE:
                selectQuery = "SELECT  " + ANIMAL_CODE_COL + " , " + ANIMAL_TYPE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Animal Type";

                break;
            case LUP_PROG_GROUP_CROP_TABLE:
                selectQuery = "SELECT  " + CROP_CODE_COL + " , " + CROP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Crop Type";

                break;


            case LUP_COMMUNITY_LOAN_SOURCE_TABLE:
                selectQuery = "SELECT  " + LOAN_CODE_COL + " , " + LOAN_SOURCE_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case LUP_COMMUNITY_LEAD_POSITION_TABLE:
                selectQuery = "SELECT  " + LEAD_CODE_COL + " , " + LEAD_POSITION_COL + " FROM " + table_name + criteria;
                selectLabel = "Select Loan Source";

                break;

            case COMMUNITY_GROUP_CATEGORY_TABLE:
                selectQuery = "SELECT  " + GROUP_CAT_CODE_COL + " , " + GROUP_CAT_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select ";

                break;

            case COMMUNITY_GROUP_TABLE:
                selectQuery = "SELECT  " + GROUP_CODE_COL + " , " + GROUP_NAME_COL + " FROM " + table_name + criteria;
                selectLabel = "Select";

                break;
            case LUP_REGN_ADDRESS_LOOKUP_TABLE:
                selectQuery = "SELECT " + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " , " + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + table_name + criteria;
                selectLabel = " Select";
                break;


        }

        //selectQuery = "SELECT * FROM " + table_name ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (selectLabel == null) selectLabel = "Select..";
        int position = 0;

        if (!countryLoad) { // all spinner show the select except load country of Main Activity
            listItem.add(new SpinnerHelper(position, "00", selectLabel));
            position++;
        }


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                listItem.add(new SpinnerHelper(position, cursor.getString(0), cursor.getString(1)));
//                Log.d(TAG, " table name :" + table_name + " :- " + cursor.getColumnName(0) + " : " + cursor.getString(0) + "  " + cursor.getColumnName(1) + " : " + cursor.getString(1));
                position++;
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning list item
        return listItem;
    }


    public List<LocationHelper> getLocationList(String cCode, String searchLocName) {
        int position = 0;
        List<LocationHelper> list = new ArrayList<LocationHelper>();
        String sql = "SELECT " + GPS_LOCATION_TABLE + "." + SQLiteHandler.GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SQLiteHandler.SUB_GROUP_CODE_COL + " || '' || " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_CODE_COL
                + " , " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_NAME_COL

                + ", CASE WHEN  ifnull(length(" + SQLiteHandler.GPS_LOCATION_TABLE + "." + SQLiteHandler.LATITUDE_COL + "), 0) = 0  THEN 'N' ELSE 'Y' END AS dataExit "
                + " , " + GPS_GROUP_TABLE + "." + GROUP_NAME_COL
                + " FROM " + SQLiteHandler.GPS_LOCATION_TABLE
                + " LEFT JOIN " + GPS_GROUP_TABLE
                + " ON " + GPS_GROUP_TABLE + "." + GROUP_CODE_COL + " = " + GPS_LOCATION_TABLE + "." + GROUP_CODE_COL
                + " WHERE " + GPS_LOCATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_NAME_COL + " LIKE '%" + searchLocName + "%' "
                + " ORDER BY " + GPS_LOCATION_TABLE + "." + SQLiteHandler.LOCATION_NAME_COL + " ASC ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                list.add(new LocationHelper(position, cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));

                position++;
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }

        return list;
    }

    public long addLUP_RegNAddLookup(String countryCode, String addressLookupCode, String addressLookup, String districtCode, String upozillaCode, String unitCode, String villageCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressLookupCode);
        values.put(REGN_ADDRESS_LOOKUP_NAME_COL, addressLookup);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upozillaCode);
        values.put(UCODE_COL, unitCode);
        values.put(VCODE_COL, villageCode);

        long id = db.insert(LUP_REGN_ADDRESS_LOOKUP_TABLE, null, values);
        db.close();
        return id;
    }


    public String getRelationString(String relationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String relationName = "";
        String selectQuery = "SELECT " + RELATION_NAME + " FROM " + RELATION_TABLE + " WHERE " +
                RELATION_CODE + " = '" + relationId + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                relationName = cursor.getString(cursor.getColumnIndex(RELATION_NAME));
            }
        }
        if (cursor != null)
            cursor.close();

        db.close();
        return relationName;
    }

    public void updateCardRequestStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + MEMBER_CARD_PRINT_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }

    public void updateRegistrationStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + REGISTRATION_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }

    public void updateLiberaiRegistrationStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + LIBERIA_REGISTRATION_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }

    /**************************************************************************************************
     * ************************************************************************************************
     * **********************************data upload query
     *********************************************/


    // after snyc data set it
    public void updateServiceStatus(String update_id, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + SERVICE_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }


    /**
     * Storing Country info into database
     */

    public void addCountry(String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, code); // Country code
        values.put(COUNTRY_NAME, name); // Country name

        // Inserting Row
        long id = db.insert(COUNTRY_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Country inserted: " + id);
    }


    /**
     * Storing Country info into database
     */

    public void addValidDateRange(String code, String sdate, String edate) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, code); // Country code
        values.put(DATE_START, sdate); // start date
        values.put(DATE_END, edate); // end date

        // Inserting Row
        long id = db.insert(VALID_DATE_RANGE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New date range inserted: " + id);
    }


    /**
     * Storing Layer Label info into database
     */

    public void addLayerLabel(String c_code, String l_code, String l_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code); // Country code
        values.put(LAYER_CODE_COL, l_code); // Layer code
        values.put(LAYER_NAME_COL, l_name); // Layer name

        // Inserting Row
        long id = db.insert(LAYER_LABEL_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "Layer Label data inserted: " + id);
    }


    /**
     * Storing District details into database
     */
    public void addDistrict(String country, String GeoLayRCode, String code, String name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(DISTRICT_CODE_COL, code); // district code
        values.put(DISTRICT_NAME_COL, name); // district name

        // Inserting Row
        long id = db.insert(DISTRICT_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New District inserted into District: " + id);
    }


    // Storing Card Reason  details into database
    // @date: 2015-11-05

    public void addCardPrintReason(String reason_code, String reason_title) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CARD_PRINT_REASON_CODE_COL, reason_code);
        values.put(CARD_PRINT_REASON_TITLE_COL, reason_title);

        // Inserting Row
        long id = db.insert(CARD_PRINT_REASON_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Card Reason inserted into Card Print Reason Table: " + id);
    }


    /**
     * Storing Upazilla details into database
     */
    public void addUpazilla(String country, String GeoLayRCode, String dcode, String upcode, String upname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(DISTRICT_CODE_COL, dcode); // district code
        values.put(UPCODE_COL, upcode); // upazilla code
        values.put(UPZILLA_NAME_COL, upname); // upazilla name

        // Inserting Row
        long id = db.insert(UPAZILLA_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New UPAZILLA_ inserted into Upazilla: " + id);
    }

    private String sqlCreateUnit() {
        return "CREATE TABLE IF NOT EXISTS " + UNIT_TABLE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COUNTRY_CODE_COL + " VARCHAR(50), " + LAYER_CODE_COL + " VARCHAR(2)," + DISTRICT_CODE_COL + " VARCHAR(50), " + UPCODE_COL + " VARCHAR(50), " + UCODE_COL + " VARCHAR(50), " + UNITE_NAME_COL + " VARCHAR(50))";
    }

    /**
     * Storing Unit details into database
     */
    public void addUnit(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String uname) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(DISTRICT_CODE_COL, dcode); //  district code
        values.put(UPCODE_COL, upcode); // upazilla code
        values.put(UCODE_COL, ucode); // unit code
        values.put(UNITE_NAME_COL, uname); // unit name

        // Inserting Row
        long id = db.insert(UNIT_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New UNIT inserted into Unit Table: " + id);
    }

    public void addSelectedVillage(String country, String dcode, String upcode, String ucode, String vcode, String layrCode, String vname, String addressCode) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code

        values.put(DISTRICT_CODE_COL, dcode); //  district code
        values.put(UPCODE_COL, upcode); // upazilla code
        values.put(UCODE_COL, ucode); // unit code
        values.put(VCODE_COL, vcode); // Village code
        values.put(LAYER_CODE_COL, layrCode); // whoe LaRCode code
        values.put(VILLAGE_NAME_COL, vname); // Village name
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode); // Village name


        // Inserting Row
        // long id =
        db.insert(SELECTED_VILLAGE_TABLE, null, values);
        db.close();

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }


    public void addSelectedFDP(String country, String fdpCode, String fdpName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(FDP_CODE_COL, fdpCode); //  fdp code
        values.put(FDP_NAME_COL, fdpName); // fdp name
        // Inserting Row
        long id = db.insert(SELECTED_FDP_TABLE, null, values);
        db.close();

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }


    public void addSelectedServiceCenter(String country, String fdpCode, String fdpName) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(SERVICE_CENTER_CODE_COL, fdpCode); //  fdp code
        values.put(SERVICE_CENTER_NAME_COL, fdpName); // fdp name
        // Inserting Row
        long id = db.insert(SELECTED_SERVICE_CENTER_TABLE, null, values);
        db.close();

//        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }

    /**
     * Storing Village details into database
     */
    public void addVillage(String country, String GeoLayRCode, String dcode, String upcode, String ucode, String vcode, String vname, String hhtarget) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE_COL, country); // country code
        values.put(LAYER_CODE_COL, GeoLayRCode); // Layer code
        values.put(DISTRICT_CODE_COL, dcode); //  district code
        values.put(UPCODE_COL, upcode); // upazilla code
        values.put(UCODE_COL, ucode); // unit code
        values.put(VCODE_COL, vcode); // Village code
        values.put(VILLAGE_NAME_COL, vname); // Village name
        values.put(HOUSE_HOLD_TARGET, hhtarget); // Village 's house hold targe

        // Inserting Row
        long id = db.insert(VILLAGE_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Village inserted into VILLAGE_TABLE: " + id);
    }

    // Storing Relation details into database

    public void addRelation(String rel_code, String rel_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RELATION_CODE, rel_code); // m code
        values.put(RELATION_NAME, rel_name); // m name

        // Inserting Row
        long id = db.insert(RELATION_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Relation inserted into Relation Table: " + id);
    }


    public void addCardType(String country_code, String cardType_lable, String cardType_code) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, country_code);
        values.put(REPORT_LABLE_COL, cardType_lable);
        values.put(REPORT_CODE_COL, cardType_code);

        // Inserting Row
        long id = db.insert(REPORT_TEMPLATE_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Report Card Type inserted into Report Template Table: " + id);
    }


    public long addRegNPWFromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,                                    //  String entryBy,
                                    //  String entryDate,
                                    String lmpDate, String pwGrdDate) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code); // country name

        values.put(DISTRICT_CODE_COL, dname); // district name
        values.put(UPCODE_COL, upname); // upazilla name
        values.put(UCODE_COL, uname); // Unit name
        values.put(VCODE_COL, vname); // village  name

        values.put(HHID_COL, hhid); // Registration id
        values.put(HH_MEM_ID, memid); // member id
        values.put(REG_N_DAT_COL, regNdate); //
        values.put(LMP_DATE_COL, lmpDate);
        values.put(PROGRAM_CODE_COL, program); //
        values.put(SERVICE_CODE_COL, service);
        values.put(GRDCODE_COL, grdCode);
        values.put(PW_GRD_DATE_COL, pwGrdDate);

        values.put(ENTRY_BY, "00");
        values.put(ENTRY_DATE, "00");

        values.put(SYNC_COL, "1");


        // Inserting Row
        long id = db.insert(REG_N_PW_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "New REG_N_PW_TABLE  data added from online into RegNPw Table: " + id);
        return id;
    }


    /**
     * this function insert member the into RegNAssProgSrv table
     *
     * @param assingPerson data
     * @return row id of inserted value
     * @since 2015-11-23
     */

    public long addMemberDataInto_RegNAsgProgSrv(AssignDataModel assingPerson) {

        return InsertInto_RegNAsgProgSrv(assingPerson.getCountryCode(), assingPerson.getDistrictCode()
                , assingPerson.getUpazillaCode(), assingPerson.getUnitCode()
                , assingPerson.getVillageCode(), assingPerson.getDonor_code()
                , assingPerson.getAward_code(), assingPerson.getHh_id()
                , assingPerson.getMemId(), assingPerson.getProgram_code()
                , assingPerson.getService_code(), assingPerson.getRegNDate()
                , assingPerson.getGrdCode(), assingPerson.getGrdDate()
                , assingPerson.getEntryBy(), assingPerson.getEntryDate(), "", "", "0");


    }

    /**
     * @param c_code     Country Code
     * @param dname      layer Code 1(District or County)
     * @param upname     layer Code 2
     * @param uname      layer Code 3
     * @param vname      layer Code 4 (Village or town)
     * @param donorCode  house hold id
     * @param awardCode  Member id
     * @param hhid       program code
     * @param memid      Service Code
     * @param progCode   Registration Date
     * @param srvCode    Graduation code
     * @param regNdate
     * @param grdCode
     * @param gdrDate
     * @param entryBy
     * @param entryDate
     * @param srvMinDate
     * @param srvMaxDate
     * @param onLine
     * @return
     */


    private long InsertInto_RegNAsgProgSrv(String c_code, String dname, String upname, String uname, String vname, String donorCode, String awardCode, String hhid, String memid, String progCode, String srvCode, String regNdate, String grdCode, String gdrDate, String entryBy, String entryDate, String srvMinDate, String srvMaxDate, String onLine) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code);

        values.put(DISTRICT_CODE_COL, dname);
        values.put(UPCODE_COL, upname);
        values.put(UCODE_COL, uname);
        values.put(VCODE_COL, vname);

        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(HHID_COL, hhid);
        values.put(HH_MEM_ID, memid);


        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, srvCode);

        values.put(REG_N_DAT_COL, regNdate);
        values.put(GRD_CODE_COL, grdCode);
        values.put(GRD_DATE_COL, gdrDate);

        values.put(SRV_MIN_DATE_COL, srvMinDate);
        values.put(SRV_MAX_DATE_COL, srvMaxDate);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, onLine);


        // Inserting Row
        long id = db.insert(REG_N_ASSIGN_PROG_SRV_TABLE, null, values);
        db.close(); // Closing database connection


        Log.d(TAG, "New " + REG_N_ASSIGN_PROG_SRV_TABLE + ": " + id);
        return id;
    }

    /**
     * added by Faisal Mohammad
     * if any thigoes wroing delete below code
     * Storing RegN assign Program Service  into database
     * REMARKS- ok it insert
     */

    public void addRegNassignProgServiceFromOnline(String c_code, String dname, String upname, String uname, String vname, String donor, String award, String hhid, String memid, String program, String service, String regNdate, String grdCode, String gdrDate, String srvMinDate, String srvMaxDate) {

        InsertInto_RegNAsgProgSrv(c_code, dname, upname, uname, vname, donor, award, hhid, memid, program, service, regNdate, grdCode, gdrDate, "", "", srvMinDate, srvMaxDate, "1");


    }


    public long addMotherInto_PW(AssignDataModel assingPerson, String lmpDate) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, assingPerson.getCountryCode()); // country name

        values.put(DISTRICT_CODE_COL, assingPerson.getDistrictCode()); // district name
        values.put(UPCODE_COL, assingPerson.getUpazillaCode()); // upazilla name
        values.put(UCODE_COL, assingPerson.getUnitCode()); // Unit name
        values.put(VCODE_COL, assingPerson.getVillageCode()); // village  name

        values.put(HHID_COL, assingPerson.getHh_id()); // Personal name
        values.put(HH_MEM_ID, assingPerson.getMemId()); // Registration name
        values.put(PROGRAM_CODE_COL, assingPerson.getProgram_code()); // Person name
        values.put(SERVICE_CODE_COL, assingPerson.getService_code()); // sex
        values.put(GRDCODE_COL, assingPerson.getGrdCode());
        values.put(PW_GRD_DATE_COL, assingPerson.getGrdDate()); //

        values.put(REG_N_DAT_COL, assingPerson.getRegNDate()); //
        values.put(LMP_DATE_COL, lmpDate);
        values.put(ENTRY_BY, assingPerson.getEntryBy());
        values.put(ENTRY_DATE, assingPerson.getEntryDate());
        values.put(SYNC_COL, "0");


        // Inserting Row
        long id = db.insert(REG_N_PW_TABLE, null, values);
        db.close(); // Closing database connection
        // updateRegNLMFStatus(assingPerson, 0);
        Log.d(TAG, "New RegN assign Program Service  data added from online into Service Table: " + id);
        return id;
    }


    /**
     * @param c_code    Country Code
     * @param dname     layer Code 1(District or County)
     * @param upname    layer Code 2
     * @param uname     layer Code 3
     * @param vname     layer Code 4 (Village or town)
     * @param hhid      house hold id
     * @param memId     Member id
     * @param program   program code
     * @param service   Service Code
     * @param regNdate  Registration Date
     * @param grdCode   Graduation code
     * @param lmDate    lactating Mother starting date
     * @param lmGrdDate lactating Mother Graduation Date
     * @param childName childName
     * @param childSex  childSex
     */
    public void addRegNLMFromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid,
                                    String memId, String program, String service, String regNdate, String grdCode, String lmDate, String lmGrdDate, String childName, String childSex) {

        addMemIntoRegN_LM(c_code, dname, upname, uname, vname, hhid, memId, program, service, regNdate, grdCode, lmDate, lmGrdDate, childName, childSex, "", "", "1");

    }

    /**
     * @param cCode     Country Code
     * @param distCode  layer Code 1(District or County)
     * @param upCode    layer Code 2
     * @param unCode    layer Code 3
     * @param vCode     layer Code 4 (Village or town)
     * @param hhID      house hold id
     * @param memId     Member id
     * @param progCode  program code
     * @param servCode  Service Code
     * @param regNdate  Registration Date
     * @param grdCode   Graduation code
     * @param lmDate    lactating Mother starting date
     * @param lmGrdDate lactating Mother Graduation Date
     * @param childName childName
     * @param childSex  childSex
     * @param entryBy   entry by
     * @param entryDate entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_LM(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                  String memId, String progCode, String servCode, String regNdate, String grdCode,
                                  String lmDate, String lmGrdDate, String childName, String childSex,
                                  String entryBy, String entryDate, String syn) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, cCode);
        values.put(DISTRICT_CODE_COL, distCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);
        values.put(HHID_COL, hhID);
        values.put(HH_MEM_ID, memId);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, servCode);

        values.put(GRD_CODE_COL, grdCode);
        values.put(LMGRDDATE_COL, lmGrdDate);
        values.put(REG_N_DAT_COL, regNdate);
        values.put(LM_DATE_COL, lmDate);

        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_LM_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New" + REG_N_LM_TABLE + " " + id);
        return id;
    }


    /**
     * @param cCode      Country Code
     * @param distCode   layer Code 1(District or County)
     * @param upCode     layer Code 2
     * @param unCode     layer Code 3
     * @param vCode      layer Code 4 (Village or town)
     * @param hhID       house hold id
     * @param memId      Member id
     * @param progCode   program code
     * @param servCode   Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param dob        date of Birth
     * @param cu2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     * @param entryBy    entry by
     * @param entryDate  entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_CU2(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                   String memId, String progCode, String servCode, String regNdate, String grdCode,
                                   String dob, String cu2GrdDate, String childName, String childSex,
                                   String entryBy, String entryDate, String syn
    ) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, cCode);
        values.put(DISTRICT_CODE_COL, distCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);
        values.put(HHID_COL, hhID);
        values.put(HH_MEM_ID, memId);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, servCode);

        values.put(REG_N_DAT_COL, regNdate);

        values.put(CU2DOB_DATE_COL, dob);


        values.put(GRD_CODE_COL, grdCode);
        values.put(CU2_GRD_DATE_COL, cu2GrdDate);


        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_CU2_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New" + REG_N_CU2_TABLE + " " + id);
        return id;
    }

    public void addRegNCU2_FromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,
                                      String cu2DOB, String cu2GrdDate, String childName, String childSex) {

        addMemIntoRegN_CU2(c_code, dname, upname, uname, vname, hhid, memid, program, service, regNdate, grdCode, cu2DOB, cu2GrdDate, childName, childSex, "", "", "1");

    }


    /**
     * @param c_code     Country Code
     * @param dname      layer Code 1(District or County)
     * @param upname     layer Code 2
     * @param uname      layer Code 3
     * @param vname      layer Code 4 (Village or town)
     * @param hhid       house hold id
     * @param memid      Member id
     * @param program    program code
     * @param service    Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param ca2DOB     date of Birth
     * @param ca2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     */


    public void addRegNCA2_FromOnLine(String c_code, String dname, String upname, String uname, String vname, String hhid, String memid, String program, String service, String regNdate, String grdCode,

                                      String ca2DOB, String ca2GrdDate, String childName, String childSex) {

        addMemIntoRegN_CA2(c_code, dname, upname, uname, vname, hhid, memid, program, service, regNdate, grdCode, ca2DOB, ca2GrdDate, childName, childSex, "", "", "1");

    }


    /**
     * @param cCode      Country Code
     * @param distCode   layer Code 1(District or County)
     * @param upCode     layer Code 2
     * @param unCode     layer Code 3
     * @param vCode      layer Code 4 (Village or town)
     * @param hhID       house hold id
     * @param memId      Member id
     * @param progCode   program code
     * @param servCode   Service Code
     * @param regNdate   Registration Date
     * @param grdCode    Graduation code
     * @param dob        date of Birth
     * @param ca2GrdDate Cu Graduation Date
     * @param childName  childName
     * @param childSex   childSex
     * @param entryBy    entry by
     * @param entryDate  entryDate
     * @return row of inserted data
     */

    public long addMemIntoRegN_CA2(String cCode, String distCode, String upCode, String unCode, String vCode, String hhID,
                                   String memId, String progCode, String servCode, String regNdate, String grdCode,
                                   String dob, String ca2GrdDate, String childName, String childSex,
                                   String entryBy, String entryDate, String syn
    ) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE, cCode);
        values.put(DISTRICT_CODE_COL, distCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);

        values.put(HHID_COL, hhID);
        values.put(HH_MEM_ID, memId);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, servCode);

        values.put(REG_N_DAT_COL, regNdate);
        values.put(CA2DOB_DATE_COL, dob);


        values.put(GRD_CODE_COL, grdCode);
        values.put(CA2_GRD_DATE_COL, ca2GrdDate);


        values.put(CHILD_NAME_COL, childName);
        values.put(CHILD_SEX_COL, childSex);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, syn);


        // Inserting Row
        long id = db.insert(REG_N_CA2_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New" + REG_N_CA2_TABLE + " " + id);
        return id;
    }


    public int editMemberDataIn_CA2(AssignDataModel asPeople) {


        SQLiteDatabase db = this.getWritableDatabase();

        String where = COUNTRY_CODE + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";


        ContentValues values = new ContentValues();


        values.put(PROGRAM_CODE_COL, asPeople.getProgram_code());
        values.put(SERVICE_CODE_COL, asPeople.getService_code());

        values.put(REG_N_DAT_COL, asPeople.getRegNDate());
        values.put(CA2DOB_DATE_COL, asPeople.getDobDate());
        values.put(GRDCODE_COL, asPeople.getGrdCode());
        values.put(CA2_GRD_DATE_COL, asPeople.getDobDate());
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());
        values.put(SYNC_COL, "0");

        // Log.d(TAG, "asPeople.getLmpDate() :" + asPeople.getLmpDate());


        // updating row
        int id = db.update(REG_N_CA2_TABLE, values, where, null);
       /* int id= db.update( REG_N_PW_TABLE, values, HH_MEM_ID + " = ? AND ",
                new String[]{asPeople.getMemberId()});*/

        //updateRegNCA2Status(asPeople, 0);

        return id;
    }


    public int upDateRegNLM(AssignDataModel asPeople, String lmDate) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(REG_N_DAT_COL, asPeople.getRegNDate()); // sex
        values.put(LM_DATE_COL, lmDate); // GDR_CODE
        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());


        Log.d(TAG, "asPeople.getLmpDate() :" + asPeople.getLmpDate());

        String query = COUNTRY_CODE + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        // updating row
        int id = db.update(REG_N_LM_TABLE, values, query, null);

        updateRegNLMFStatus(asPeople, 0);

        return id;
    }


    /**
     * @author: by Faisal Mohammad
     * @date: 2015-11-17
     * @discription: update the regestration value  RegN assign Program Service
     * into database For Graduation
     * PROBLEM: -
     * @remark-
     */


    public GraduationDateCode getGRDPeopleDetial(
            String cCode, String distCode, String upCode, String unCode,
            String vCode, String hhId, String mmId,
            String progCode, String srvCode, String donorCode, String awardCode) {


        SQLiteDatabase db = this.getReadableDatabase();

        GraduationDateCode data = new GraduationDateCode();


        String selectQuery = " SELECT " + GRD_CODE_COL + " , " + GRD_DATE_COL +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + COUNTRY_CODE + " = '" + cCode + "' AND " +
                DISTRICT_CODE_COL + " = '" + distCode + "' AND " +
                UPCODE_COL + " = '" + upCode + "' AND " +
                UCODE_COL + " = '" + unCode + "' AND " +
                VCODE_COL + " = '" + vCode + "' AND " +
                DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                HHID_COL + " = '" + hhId + "' AND " +
                HH_MEM_ID + " = '" + mmId + "' AND  " +
                PROGRAM_CODE_COL + " = '" + progCode + "' AND  " +
                SERVICE_CODE_COL + " = '" + srvCode + "'   ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                data.setGrdCode(cursor.getString(cursor.getColumnIndex(GRD_CODE_COL)));
                data.setGrdDate(cursor.getString(cursor.getColumnIndex(GRD_DATE_COL)));
            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

        return data;
    }


    /**
     * by Faisal Mohammad
     *
     * @since : 2015-11-17
     * update the regestration value  RegN assign Program Service
     * into database For Graduation
     */


    public String getRegDateFromRegNAssignProgSrv(String cCode, String distCode, String upCode, String unCode,
                                                  String vCode, String hhId, String mmId, String donorCode, String awardCode, String progCode, String srvCode) {


        SQLiteDatabase db = this.getReadableDatabase();

        String regDate = "";


        String selectQuery = " SELECT " + REG_N_DAT_COL +
                " FROM " + REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE " + COUNTRY_CODE + " = '" + cCode + "' AND " +
                DISTRICT_CODE_COL + " = '" + distCode + "' AND " +
                UPCODE_COL + " = '" + upCode + "' AND " +
                UCODE_COL + " = '" + unCode + "' AND " +
                VCODE_COL + " = '" + vCode + "' AND " +
                DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                HHID_COL + " = '" + hhId + "' AND " +
                HH_MEM_ID + " = '" + mmId + "' AND  " +
                PROGRAM_CODE_COL + " = '" + progCode + "' AND  " +
                SERVICE_CODE_COL + " = '" + srvCode + "'   ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                regDate = cursor.getString(cursor.getColumnIndex(REG_N_DAT_COL));

            }
        }
        if (cursor != null)
            cursor.close();
        db.close();

        return regDate;
    }

    /**
     * @author: by Faisal Mohammad
     * @date: 2015-10-01
     * @discription: update the regestration value  RegN assign Program Service
     * into database For Graduation
     * PROBLEM: -
     * @remark-
     */


    public int editMemberDataIn_RegNAsgProgSrv(String grdDate, String grdCode, String entryBy, String entryDate, String cCode, String distCode, String upCode, String unCode, String vCode, String hhId, String mmId, String progCode, String srvCode, String donorCode, String awardCode) {


        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();

        values.put(GRD_CODE_COL, grdCode); // GRD_CODE= Graduation Title Code
        values.put(GRD_DATE_COL, grdDate); // GDR_Date= Graduation Date Code
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);
        values.put(SYNC_COL, 0);

        Log.d(TAG, "update Graduation Date :" + grdDate);

        String criteria = " " + COUNTRY_CODE + " = '" + cCode + "' AND " +
                DISTRICT_CODE_COL + " = '" + distCode + "' AND " +
                UPCODE_COL + " = '" + upCode + "' AND " +
                UCODE_COL + " = '" + unCode + "' AND " +
                VCODE_COL + " = '" + vCode + "' AND " +
                DONOR_CODE_COL + " = '" + donorCode + "' AND " +
                AWARD_CODE_COL + " = '" + awardCode + "' AND " +
                HHID_COL + " = '" + hhId + "' AND " +
                HH_MEM_ID + " = '" + mmId + "' AND  " +
                PROGRAM_CODE_COL + " = '" + progCode + "' AND  " +
                SERVICE_CODE_COL + " = '" + srvCode + "'   ";

        // updating row
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, criteria,
                null);

        db.close();

        return id;
    }


    /**
     * added by Faisal Mohammad
     * <p/>
     * update the regestration value  RegN assign Program Service  into database
     *
     * @modify: 2015-11-25
     * REMARKS-
     */


    public int editMemberDataIn_RegNAsgProgSrv(AssignDataModel asPeople) {


        SQLiteDatabase db = this.getWritableDatabase();
        String whereQuery = " " + COUNTRY_CODE + " = '" + asPeople.getCountryCode() + "' AND " +
                DISTRICT_CODE_COL + " = '" + asPeople.getDistrictCode() + "' AND " +
                UPCODE_COL + " = '" + asPeople.getUpazillaCode() + "' AND " +
                UCODE_COL + " = '" + asPeople.getUnitCode() + "' AND " +
                VCODE_COL + " = '" + asPeople.getVillageCode() + "' AND " +
                DONOR_CODE_COL + " = '" + asPeople.getDonor_code() + "' AND " +
                AWARD_CODE_COL + " = '" + asPeople.getAward_code() + "' AND " +
                HHID_COL + " = '" + asPeople.getHh_id() + "' AND " +
                HH_MEM_ID + " = '" + asPeople.getMemId() + "'  ";

        ContentValues values = new ContentValues();
        values.put(REG_N_DAT_COL, asPeople.getRegNDate());

        values.put(GRD_DATE_COL, asPeople.getGrdDate());
        //  values.put(GRD_CODE_COL, asPeople.getGrdCode());
        //omite the code
        values.put(SYNC_COL, "0");

        values.put(ENTRY_BY, asPeople.getEntryBy());
        values.put(ENTRY_DATE, asPeople.getEntryDate());

        Log.d(TAG, "asPeople.getRegNDate() :" + asPeople.getRegNDate());


        // updating row
        int id = db.update(REG_N_ASSIGN_PROG_SRV_TABLE, values, whereQuery, null);
        Log.d(TAG, "id:" + id);

        db.close();
        return id;
    }


    /**
     * update regn Ass n ser prg tabel upload status
     */
    public void updateRegNLMFStatus(AssignDataModel aPeople, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + REG_N_LM_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " +
                COUNTRY_CODE_COL + "=" + aPeople.getCountryCode() + " AND " + DISTRICT_CODE_COL + "=" + aPeople.getDistrictCode() + " AND " +
                UPCODE_COL + "=" + aPeople.getUpazillaCode() + " AND " +
                UCODE_COL + "=" + aPeople.getUnitCode() + " AND " +
                VCODE_COL + "=" + aPeople.getVillageCode() + " AND " + HHID_COL + "=" + aPeople.getHh_id() + " AND " +
                HH_MEM_ID + "=" + aPeople.getMemId();

        //String selectQuery = "UPDATE " + REGISTRATION_TABLE + " SET " + SYNC_COL + "=" + status +" WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }


    /**
     * update regn Ass n ser prg tabel upload status
     */
    public void updateRegNAsgProgSrvStatus(AssignDataModel aPeople, int status) {

        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "UPDATE " + REG_N_ASSIGN_PROG_SRV_TABLE + " SET " + SYNC_COL + "=" + status + " WHERE " +
                COUNTRY_CODE_COL + "=" + aPeople.getCountryCode() + " AND " + DISTRICT_CODE_COL + "=" + aPeople.getDistrictCode() + " AND " +
                UPCODE_COL + "=" + aPeople.getUpazillaCode() + " AND " +
                UCODE_COL + "=" + aPeople.getUnitCode() + " AND " +
                VCODE_COL + "=" + aPeople.getVillageCode() + " AND " + HHID_COL + "=" + aPeople.getHh_id() + " AND " +
                HH_MEM_ID + "=" + aPeople.getMemId();

        //String selectQuery = "UPDATE " + REGISTRATION_TABLE + " SET " + SYNC_COL + "=" + status +" WHERE " + ID_COL + "=" + update_id;
        db.execSQL(selectQuery);
        db.close();
    }


    /**
     * @param c_code
     * @param donorCode
     * @param awardCode
     * @param districtCode
     * @param upzellaCode
     * @param uname
     * @param vname
     * @param hhid
     * @param memid
     * @param program
     * @param service
     * @param opCode
     * @param opMonth
     * @param serviceSl
     * @param srvCenterCode
     * @param serviceDt
     * @param SrvStatus
     * @param distStatus
     * @param distDate
     * @param is_online
     */

    public void addServiceFromOnline(String c_code, String donorCode, String awardCode, String districtCode,
                                     String upzellaCode, String uname, String vname, String hhid,
                                     String memid, String program, String service, String opCode,
                                     String opMonth, String serviceSl, String srvCenterCode,
                                     String serviceDt, String SrvStatus, String distStatus,
                                     String distDate, String fdpCode, String wd, String distFlag, String groupCode, String is_online) {


        String entryBy = "";
        String entryDate = "";
        addMemberServiceTable(c_code, donorCode, awardCode, districtCode, upzellaCode, uname, vname, hhid,
                memid, program, service, opCode, opMonth, serviceSl, srvCenterCode,
                serviceDt, SrvStatus, distStatus, distDate, fdpCode, wd, distFlag, groupCode, is_online, entryBy, entryDate);


    }

    /* added by service */

    /**
     * todo: Implements Service Center Code & wd
     * The
     *
     * @param srvData   Service Data Model
     * @param entryBy   entryBy Indecat who insert the data
     * @param entryDate EntryDate
     */
    public void addMemberIntoServiceTable(ServiceDataModel srvData, String entryBy, String entryDate) {
        String SrvStatus = "O";
        String distStatus = "";
        String distDate = "";
        // // TODO:  get Fdp Code For Service Center
        String fdpCode = srvData.getFPDCode();
        String wd = srvData.getWorkingDay();
        Log.d(TAG, "IN service insert method wd :" + wd);
        /**
         * there is no use of group cod e remove it late
         */
        String distFlag = srvData.getDistFlag();
        String groupCode = "";
        String srvCenterCode = srvData.getServiceCenterCode();
        String is_online = "0";
        addMemberServiceTable(srvData.getC_code(), srvData.getDonor_code(), srvData.getAward_code(), srvData.getDistrictCode(), srvData.getUpazillaCode()
                , srvData.getUnitCode(), srvData.getVillageCode(), srvData.getHHID(),
                srvData.getMemberId(), srvData.getProgram_code(), srvData.getService_code(), srvData.getOpCode(), srvData.getOpMontheCode(), srvData.getServiceSLCode()
                , srvCenterCode,
                srvData.getServiceDTCode(), SrvStatus, distStatus, distDate, fdpCode, wd, distFlag, groupCode, is_online, entryBy, entryDate);


    }


    /**
     * This method save the Service Data
     *
     * @param c_code             Country Code
     * @param donorCode
     * @param awardCode
     * @param districtCode       layR4Code
     * @param upCode
     * @param unCode
     * @param vCode
     * @param hhid
     * @param memId
     * @param progCode
     * @param srvCode
     * @param opCode
     * @param opMonthCode
     * @param srvSl
     * @param srvCenterCode
     * @param srvDate
     * @param srvStatus
     * @param distributionStatus
     * @param distributionDate
     * @param fdpCode            Food Distribution Code
     * @param wd                 working Day
     * @param groupCode
     * @param is_online
     * @param entryBy
     * @param entryDate          entryDate
     */

    public void addMemberServiceTable(String c_code, String donorCode, String awardCode, String districtCode,
                                      String upCode, String unCode, String vCode, String hhid,
                                      String memId, String progCode, String srvCode, String opCode,
                                      String opMonthCode, String srvSl, String srvCenterCode,
                                      String srvDate, String srvStatus, String distributionStatus,
                                      String distributionDate, String fdpCode, String wd, String distFlag,
                                      String groupCode, String is_online, String entryBy, String entryDate) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(COUNTRY_CODE_COL, c_code);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);

        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upCode);
        values.put(UCODE_COL, unCode);
        values.put(VCODE_COL, vCode);
        values.put(HHID_COL, hhid);
        values.put(HH_MEM_ID, memId);

        values.put(PROGRAM_CODE_COL, progCode);
        values.put(SERVICE_CODE_COL, srvCode);

        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);

        values.put(SERVICE_SL_COL, srvSl);
        values.put(SERVICE_CENTER_CODE_COL, srvCenterCode);

        values.put(SERVICE_DT_COL, srvDate);
        values.put(SERVICE_STATUS_COL, srvStatus);

        values.put(DISTRIBUTION_STATUS_COL, distributionStatus);
        values.put(DIST_DATE_COL, distributionDate);
        values.put(FDP_CODE_COL, fdpCode);

        values.put(WORK_DAY_COL, wd);
        values.put(DIST_FLAG_COL, distFlag);
        /**
         * Group Code is not necessary to insert by Mobile Device
         */
        values.put(GROUP_CODE_COL, groupCode);

        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        values.put(SYNC_COL, is_online); // Sync Status


        // Inserting Row
        long id = db.insert(SERVICE_TABLE, null, values);
        db.close(); // Closing database connection

        //  Log.d(TAG, "New Service data added  Service Table: " + id);


    }


    /**
     * @param c_code
     * @param donorCode
     * @param awardCode
     * @param districtCode
     * @param upzellaCode
     * @param unCode
     * @param vCode
     * @param hhid
     * @param memid
     * @param program
     * @param service
     * @param opCode
     * @param opMonthCode
     * @param voItmSpec
     * @param voItmUnit
     * @param voRefNumber
     * @param voItmCost
     * @param is_online
     */

    public void addServiceExtendedFromOnline(String c_code, String donorCode, String awardCode, String districtCode,
                                             String upzellaCode, String unCode, String vCode, String hhid,
                                             String memid, String program, String service, String opCode, String opMonthCode,
                                             String voItmSpec, String voItmUnit, String voRefNumber, String voItmCost, String is_online) {

        addServiceExtendedTable(c_code, districtCode, upzellaCode, unCode, vCode, hhid, memid, donorCode, awardCode, program,
                service, opCode, opMonthCode, voItmSpec, voItmUnit, voRefNumber,
                voItmCost, "", "", "", is_online);


        /*SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code); // country name
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(DISTRICT_CODE_COL, districtCode);
        values.put(UPCODE_COL, upzellaCode);
        values.put(UCODE_COL, uname);
        values.put(VCODE_COL, vname);
        values.put(HHID_COL, hhid);
        values.put(HH_MEM_ID, memid);
        values.put(PROGRAM_CODE_COL, program);
        values.put(SERVICE_CODE_COL, service);
        values.put(OPERATION_CODE_COL, opCode);
        values.put(OP_MONTH_CODE_COL, opMonth);


        values.put(VOUCHER_ITEM_SPEC_COL, voItmSpec);
        values.put(VOUCHER_UNIT_COL, voItmUnit);
        values.put(VOUCHER_REFERENCE_NUMBER_COL, voRefNumber);
        values.put(VOUCHER_ITEM_COST_COL, voItmCost);



        // Sync Status default 0

        // Inserting Row
        long id = db.insert(SERVICE_EXTENDED_TABLE, null, values);
        db.close(); // Closing database connection*/

//        Log.d(TAG, "New Service Extended data added from online into Service Table: " + id);
    }


    /** HERE REGISTRATION CRUDE OPERATION*/

    /**
     * Storing Registration Data into database
     */


    public void addRegistrationFromOnline(String c_code, String dname, String upname, String uname, String vname, String pid, String r_date, String pname, String sex, String HHSize, String latitude, String longitude, String AGLand, String VStatus, String MStatus, String EntryBy, String EntryDate, String VSLAGroup, String GpsLongSwap, String regNAddLookupCode,
                                          String HHCategories, String lessT2Ymale, String lessT2YFemale, String m2to5, String f2to5, String m6to12, String f6to12, String m13to17, String f13to17, String orpLT18m, String orpLT18f, String m18to59, String f18to59, String eld60m, String eld60f, String pwsNo, String chroIllNo, String livingDecasedContractE, String extraChildBecauseE,
                                          String extraElderlyBecauseE, String extraIllBecauseEbola, String BCntCattle, String BValCattle, String ACntCattle, String AValCattle, String BCntSGoats, String BValSGoats, String ACntSGoats, String AValSGoats, String BCntPoultry, String BValPoultry, String ACntPoultry, String AfValPoultry, String BCntOther, String BValOther, String ACntOther, String AValOther,
                                          String BAcreCultivable, String BValCultivable, String AAcreCultivable, String AValueCultivable, String BAcreNonCultivable, String BValNonCultivable, String AAcreNonCultivable, String AValNonCultivable, String BAcreOrchards, String BValOrchards, String AAcreOrchards, String AValOrchards, String BValCrop, String AValCrop, String BValLivestock, String AValLivestock,
                                          String BValSmallBusiness, String AValSmallBusiness, String BValEmployment, String AValEmployment, String BValRemittances, String AValRemittances, String BCntWageEnr, String ATCntWageEnr, String is_online, String wRank
            , String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code); // country name
        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(PID_COL, pid); // Personal name
        values.put(REG_DATE_COL, r_date); // Registration Date
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize); // House hold Size
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Agriculture land
        values.put(V_STATUS, VStatus); // verify Status
        values.put(M_STATUS, MStatus); // Marriage  Status
        values.put(GPS_LONG_SWAP, GpsLongSwap); //
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, regNAddLookupCode); //
        values.put(HOUSE_HOLD_TYPE_CODE_COL, HHCategories); // House Hold Categories
        values.put(LT2YRS_M_COL, lessT2Ymale); // Less than 2 Year Male
        values.put(LT2YRS_F_COL, lessT2YFemale); // Less than 2 Year Female
        values.put(M_2TO5YRS_COL, m2to5);
        values.put(F_2TO5YRS_COL, f2to5);
        values.put(M_6TO12YRS_COL, m6to12);
        values.put(F_6TO12YRS_COL, f6to12);
        values.put(M_13TO17YRS_COL, m13to17);
        values.put(F_13TO17YRS_COL, f13to17);
        values.put(ORPHN_LT18YRS_M_COL, orpLT18m);
        values.put(ORPHN_LT18YRS_F_COL, orpLT18f);
        values.put(ADLT_18TO59_M_COL, m18to59);
        values.put(ADLT_18TO59_F_COL, f18to59);
        values.put(ELD_60P_M_COL, eld60m);
        values.put(ELD_60P_F_COL, eld60f);
        values.put(PLW_NO_COL, pwsNo);
        values.put(CHRO_ILL_NO_COL, chroIllNo);
        values.put(DECEASED_CONTRACT_EBOLA_COL, livingDecasedContractE);
        values.put(EXTRA_CHILD_CAUSE_EBOLA_COL, extraChildBecauseE);
        values.put(EXTRA_ELDERLY_CAUSE_EBOLA_COL, extraElderlyBecauseE);
        values.put(EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL, extraIllBecauseEbola);//extraChronicallyIllBecauseEbola
        values.put(BRF_COUNT_CATTLE_COL, BCntCattle);
        values.put(BRF_VALUE_CATTLE_COL, BValCattle);
        values.put(AFT_COUNT_CATTLE_COL, ACntCattle);
        values.put(AFT_VALUE_CATTLE_COL, AValCattle);
        values.put(BRF_COUNT_SGOATS_COL, BCntSGoats);
        values.put(BRF_VALUE_SGOATS_COL, BValSGoats);
        values.put(AFT_COUNT_SGOATS_COL, ACntSGoats);
        values.put(AFT_VALUE_SGOATS_COL, AValSGoats);
        values.put(BRF_COUNT_POULTRY_COL, BCntPoultry);
        values.put(BRF_VALUE_POULTRY_COL, BValPoultry);
        values.put(AFT_COUNT_POULTRY_COL, ACntPoultry);
        values.put(AFT_VALUE_POULTRY_COL, AfValPoultry);
        values.put(BRF_COUNT_OTHER_COL, BCntOther);
        values.put(BRF_VALUE_OTHER_COL, BValOther);
        values.put(AFT_COUNT_OTHER_COL, ACntOther);
        values.put(AFT_VALUE_OTHER_COL, AValOther);
        values.put(BRF_ACRE_CULTIVABLE_COL, BAcreCultivable);
        values.put(BRF_VALUE_CULTIVABLE_COL, BValCultivable);
        values.put(AFT_ACRE_CULTIVABLE_COL, AAcreCultivable);
        values.put(AFT_VALUE_CULTIVABLE_COL, AValueCultivable);
        values.put(BRF_ACRE_NON_CULTIVABLE_COL, BAcreNonCultivable);
        values.put(BRF_VAL_NON_CULTIVABLE_COL, BValNonCultivable);
        values.put(AFT_ACRE_NON_CULTIVABLE, AAcreNonCultivable);
        values.put(AFT_VAL_NON_CULTIVABLE, AValNonCultivable);
        values.put(BRF_ACRE_ORCHARDS, BAcreOrchards);
        values.put(BRF_VAL_ORCHARDS, BValOrchards);
        values.put(AFT_ACRE_ORCHARDS, AAcreOrchards);
        values.put(AFT_VAL_ORCHARDS, AValOrchards);
        values.put(BRF_VAL_CROP, BValCrop);
        values.put(AFT_VAL_CROP, AValCrop);
        values.put(BRF_VAL_LIVESTOCK, BValLivestock);
        values.put(AFT_VAL_LIVESTOCK, AValLivestock);
        values.put(BRF_VAL_SMALL_BUSINESS, BValSmallBusiness);
        values.put(AFT_VAL_SMALL_BUSINESS, AValSmallBusiness);
        values.put(BRF_VAL_EMPLOYMENT, BValEmployment);
        values.put(AFT_VAL_EMPLOYMENT, AValEmployment);
        values.put(BRF_VAL_REMITTANCES, BValRemittances);
        values.put(AFT_VAL_REMITTANCES, AValRemittances);
        values.put(BRF_CNT_WAGEENR, BCntWageEnr);
        values.put(AFT_CNT_WAGEENR, ATCntWageEnr);
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation


        values.put(VSLA_GROUP, VSLAGroup); // Sync Status
        values.put(SYNC_COL, is_online); // Sync Status
        values.put(W_RANK_COL, wRank); // Sync Status


        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);


        // Inserting Row
        long id = db.insert(REGISTRATION_TABLE, null, values);
        db.close(); // Closing database connection

        //     Log.d(TAG, "New Registration data added into Registration Table: " + id);
    }


    public ContentValues getHouseHoldData(String c_code, String districtCode, String upazillaCode, String unionCode, String villageCode, String hhID) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor hCursor = null;

        ContentValues values = new ContentValues();


        String selectQuery = "SELECT "
                + REGISTRATION_TABLE + "." + ID_COL + ", "
                + REGISTRATION_TABLE + "." + REG_DATE_COL + ", "

                + REGISTRATION_TABLE + "." + COUNTRY_CODE + ", "
                + REGISTRATION_TABLE + "." + DISTRICT_NAME_COL + " AS R_District, "
                + REGISTRATION_TABLE + "." + UPZILLA_NAME_COL + " AS R_Upazilla, "
                + REGISTRATION_TABLE + "." + UNITE_NAME_COL + " AS R_Union, "
                + REGISTRATION_TABLE + "." + VILLAGE_NAME_COL + " AS R_Village, "

                + COUNTRY_TABLE + "." + COUNTRY_NAME + " AS country_name, "
                + DISTRICT_TABLE + "." + DISTRICT_NAME_COL + ", "
                + UPAZILLA_TABLE + "." + UPZILLA_NAME_COL + ","
                + UNIT_TABLE + "." + UNITE_NAME_COL + ", "
                + VILLAGE_TABLE + "." + VILLAGE_NAME_COL + ","

                + REGISTRATION_TABLE + "." + PID_COL + ","
                + REGISTRATION_TABLE + "." + PNAME_COL + ","
                + "(" + " CASE WHEN " + SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + REGISTRATION_TABLE + "." + HH_SIZE + ","
                + REGISTRATION_TABLE + "." + LATITUDE_COL + ","
                + REGISTRATION_TABLE + "." + LONGITUDE_COL + ","
                + REGISTRATION_TABLE + "." + AG_LAND + ","
                + "(" + " CASE WHEN " + V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + "(" + " CASE WHEN " + M_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS MStatus" + ","
                + REGISTRATION_TABLE + "." + ENTRY_BY + ","
                + REGISTRATION_TABLE + "." + ENTRY_DATE
                + " FROM " + REGISTRATION_TABLE
                + " LEFT JOIN " + DISTRICT_TABLE
                + " ON " + REGISTRATION_TABLE + "." + DISTRICT_NAME_COL + "=" + DISTRICT_TABLE + "." + DISTRICT_CODE_COL
                + " LEFT JOIN " + UPAZILLA_TABLE + " ON " + REGISTRATION_TABLE + "." + UPZILLA_NAME_COL + "=" + UPAZILLA_TABLE + "." + UPCODE_COL
                + " LEFT JOIN " + UNIT_TABLE + " ON " + REGISTRATION_TABLE + "." + UNITE_NAME_COL + "=" + UNIT_TABLE + "." + UCODE_COL
                + " LEFT JOIN " + VILLAGE_TABLE + " ON " + REGISTRATION_TABLE + "." + VILLAGE_NAME_COL + "=" + VILLAGE_TABLE + "." + VCODE_COL
                + " LEFT JOIN " + COUNTRY_TABLE + " ON " + REGISTRATION_TABLE + "." + COUNTRY_CODE + "=" + COUNTRY_TABLE + "." + COUNTRY_CODE

                + " WHERE " + PID_COL + "='" + hhID
                + "' AND " + REGISTRATION_TABLE + "." + COUNTRY_CODE + "='" + c_code
                + "' AND R_District='" + districtCode + "'"
                + " AND R_Upazilla='" + upazillaCode + "'"
                + " AND R_Union='" + unionCode + "'"
                + " AND R_Village='" + villageCode

                + "' ORDER BY " + REGISTRATION_TABLE + "." + ID_COL + " DESC";

        hCursor = db.rawQuery(selectQuery, null);

        if (hCursor != null) {
            if (hCursor.moveToFirst()) {
                do {


                    values.put("country_name", hCursor.getString(hCursor.getColumnIndex("country_name")));
                    values.put("str_district", hCursor.getString(hCursor.getColumnIndex(DISTRICT_NAME_COL)));
                    values.put("str_upazilla", hCursor.getString(hCursor.getColumnIndex(UPZILLA_NAME_COL)));
                    values.put("str_union", hCursor.getString(hCursor.getColumnIndex(UNITE_NAME_COL)));
                    values.put("str_village", hCursor.getString(hCursor.getColumnIndex(VILLAGE_NAME_COL)));

                    values.put("str_c_code", hCursor.getString(hCursor.getColumnIndex(COUNTRY_CODE_COL)));
                    values.put("str_districtCode", hCursor.getString(hCursor.getColumnIndex("R_District")));
                    values.put("str_upazillaCode", hCursor.getString(hCursor.getColumnIndex("R_Upazilla")));
                    values.put("str_unionCode", hCursor.getString(hCursor.getColumnIndex("R_Union")));
                    values.put("str_villageCode", hCursor.getString(hCursor.getColumnIndex("R_Village")));


                    values.put("str_reg_date", hCursor.getString(hCursor.getColumnIndex(REG_DATE_COL)));
                    values.put("str_hhName", hCursor.getString(hCursor.getColumnIndex(PNAME_COL)));
                    values.put("str_gender", hCursor.getString(hCursor.getColumnIndex(SEX_COL)));
                    values.put("str_hhsize", hCursor.getString(hCursor.getColumnIndex(HH_SIZE)));
                    values.put("str_latitude", hCursor.getString(hCursor.getColumnIndex(LATITUDE_COL)));
                    values.put("str_longitude", hCursor.getString(hCursor.getColumnIndex(LATITUDE_COL)));
                    values.put("str_agland", hCursor.getString(hCursor.getColumnIndex(AG_LAND)));
                    values.put("str_vstatus", hCursor.getString(hCursor.getColumnIndex(V_STATUS)));
                    values.put("str_mstatus", hCursor.getString(hCursor.getColumnIndex(M_STATUS)));
                    values.put("str_entry_by", hCursor.getString(hCursor.getColumnIndex(ENTRY_BY)));
                    values.put("str_entry_date", hCursor.getString(hCursor.getColumnIndex(ENTRY_DATE)));
                }
                while (hCursor.moveToNext());

            }
        }


        db.close(); // Closing database connection

        return values;
    }


    public void editMalawiMemberData(int mID, String str_MemName, String str_gender, String str_relation, String str_lmp_date, String str_child_dob, String str_elderly, String str_disabled, String str_age, int pID) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = "id='" + mID + "'";

        //String[] whereArgs = new String[] {String.valueOf(mID)};

        values.put(MEM_NAME_COL, str_MemName); // Member name
        values.put(SEX_COL, str_gender); // sex
        values.put(RELATION_COL, str_relation); // relation

        values.put(LMP_DATE, str_lmp_date);
        values.put(CHILD_DOB, str_child_dob);
        values.put(ELDERLY, str_elderly);
        values.put(DISABLE, str_disabled);
        values.put(MEM_AGE, str_age); // age

        // updating Row into local database
        db.update(REGISTRATION_MEMBER_TABLE, values, where, null);

        if (pID != 0)
            updateRegistrationStatus("" + pID, 0);    // Setting Update status to false to avail the Synchronization

        db.close(); // Closing database connection

        Log.d(TAG, "Updated Member data: " + mID);

    }

    public void editLiberiaMemberData(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID,
                                      String regNDate,
                                      String memOtherID, String memName_First,
                                      String memName_Middle, String memName_Last, String birthYear,
                                      String maritalStatus, String contactNo,
                                      String Photo,
                                      String type_ID, String id_NO,
                                      String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                                      String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                                      String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                                      String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                                      String Proxy_Photo,
                                      String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                                      String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                                      String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition,
                                      String str_entry_by, String str_entry_date) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = COUNTRY_CODE + " = '" + str_c_code + "' "
                + " AND " + DISTRICT_NAME_COL + " = '" + str_district + "' "
                + " AND " + UPZILLA_NAME_COL + " = '" + str_upazilla + "' "
                + " AND " + UNITE_NAME_COL + " = '" + str_union + "' "
                + " AND " + VILLAGE_NAME_COL + " = '" + str_village + "' "
                + " AND " + HHID_COL + " = '" + str_hhID + "' "
                + " AND " + HH_MEM_ID + " = '" + str_hhMemID + "' ";

        ContentValues values = new ContentValues();


        values.put(MEM_NAME_COL, memName_First); // Member name

        values.put(ENTRY_BY, str_entry_by); // entry by
        values.put(ENTRY_DATE, str_entry_date); // entry Date


        values.put(REG_DATE_COL, regNDate);


        values.put(BIRTH_YEAR_COL, birthYear);
        values.put(MARITAL_STATUS_COL, maritalStatus);
        values.put(CONTACT_NO_COL, contactNo);
        values.put(MEMBER_OTHER_ID_COL, memOtherID);
        values.put(MEM_NAME_FIRST_COL, memName_First);
        values.put(MEM_NAME_MIDDLE_COL, memName_Middle);
        values.put(MEM_NAME_LAST_COL, memName_Last);

        /** TODO : work this photo*/
        values.put(PHOTO_COL, Photo);

        values.put(TYPE_ID_COL, type_ID);
        values.put(ID_NO_COL, id_NO);
        values.put(V_BSC_MEM_1_NAME_FIRST_COL, v_BSCMemName1_First);
        values.put(V_BSC_MEM_1_NAME_MIDDLE_COL, v_BSCMemName1_Mid);
        values.put(V_BSC_MEM_1_NAME_LAST_COL, v_BSCMemName1_Last);
        values.put(V_BSC_MEM_1_TITLE_COL, v_BSCMem1_TitlePosition);

        values.put(V_BSC_MEM_2_NAME_FIRST_COL, v_BSCMemName2_First);
        values.put(V_BSC_MEM_2_NAME_MIDDLE_COL, v_BSCMemName2_Mid);
        values.put(V_BSC_MEM_2_NAME_LAST_COL, v_BSCMemName2_Last);
        values.put(V_BSC_MEM_2_TITLE_COL, v_BSCMem2_TitlePos);

        values.put(PROXY_DESIGNATION_COL, proxy_Desig);
        values.put(PROXY_NAME_FIRST_COL, proxy_Name_First);
        values.put(PROXY_NAME_MIDDLE_COL, proxy_Name_Mid);
        values.put(PROXY_NAME_LAST_COL, proxy_Name_Last);


        values.put(PROXY_BIRTH_YEAR_COL, proxy_BirthYear);

        values.put(PROXY_PHOTO_COL, Proxy_Photo);


        values.put(PROXY_TYPE_ID_COL, proxy_Type_ID);
        values.put(PROXY_ID_NO_COL, proxy_ID_NO);
        values.put(PROXY_BSC_MEM_1_NAME_FIRST_COL, p_BSCMemName1_First);
        values.put(PROXY_BSC_MEM_1_NAME_MIDDLE_COL, p_BSCMemName1_Middle);
        values.put(PROXY_BSC_MEM_1_NAME_LAST_COL, p_BSCMemName1_Last);
        values.put(PROXY_BSC_MEM_1_TITLE_COL, p_BSCMem1_TitlePosition);
        values.put(PROXY_BSC_MEM_2_NAME_FIRST_COL, p_BSCMemName2_First);
        values.put(PROXY_BSC_MEM_2_NAME_MIDDLE_COL, p_BSCMemName2_Middle);
        values.put(PROXY_BSC_MEM_2_NAME_LAST_COL, p_BSCMemName2_Last);
        values.put(PROXY_BSC_MEM_2_TITLE_COL, p_BSCMem2_TitlePosition);


        // Inserting Row into local database

        // updating Row into local database
        db.update(REGISTRATION_MEMBER_TABLE, values, where, null);

        // if (pID != 0)
        //  updateRegistrationStatus("" + pID, 0);    // Setting Update status to false to avail the Synchronization

        db.close(); // Closing database connection

        //    Log.d(TAG, "Updated Member data: " + mID);

    }

    /**
     * Storing Member Data into database for Malawi
     */

    public void addMemberDataForMalawi(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID, String str_MemName, String str_gender, String str_relation, String str_entry_by, String str_entry_date, String lmp_date, String child_dob, String str_elderly, String str_disabled, String str_age, int pID) {
        addMemberData(str_c_code, str_district, str_upazilla, str_union, str_village, str_hhID, str_hhMemID, str_MemName, str_gender, str_relation, str_entry_by, str_entry_date, lmp_date, child_dob, str_elderly, str_disabled, str_age
                , null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);


        if (pID != 0)
            updateRegistrationStatus("" + pID, 0);    // Setting Update status to false to avail the Synchronization


    }

    public long addMemberDataForLiberia(String str_c_code, String str_district, String str_upazilla, String str_union, String str_village, String str_hhID, String str_hhMemID,
                                        String regNDate,
                                        String memOtherID, String memName_First,
                                        String memName_Middle, String memName_Last, String birthYear,
                                        String maritalStatus, String contactNo,
                                        String Photo,
                                        String type_ID, String id_NO,
                                        String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                                        String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                                        String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                                        String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                                        String Proxy_Photo,
                                        String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                                        String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                                        String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition,
                                        String str_entry_by, String str_entry_date) {
        //    addMemberData(str_c_code, str_district,  str_upazilla,  str_union,  str_village,  str_hhID,  str_hhMemID,  str_MemName,  str_gender,  str_relation,  str_entry_by,  str_entry_date,  lmp_date,  child_dob,  str_elderly,  str_disabled,  str_age

        String str_gender = null, str_relation = null, lmp_date = null, child_dob = null, str_elderly = null, str_disabled = null;
        String str_age = null;
        String grp_code = null;

        long idRow = addMemberData(str_c_code, str_district, str_upazilla, str_union, str_village,
                str_hhID, str_hhMemID, memName_First, str_gender, str_relation, str_entry_by, str_entry_date,
                lmp_date, child_dob, str_elderly, str_disabled, str_age, regNDate, birthYear, maritalStatus
                , contactNo, memOtherID, memName_First, memName_Middle, memName_Last,
                Photo//.toString()
                ,
                type_ID, id_NO, v_BSCMemName1_First, v_BSCMemName1_Mid, v_BSCMemName1_Last, v_BSCMem1_TitlePosition,
                v_BSCMemName2_First, v_BSCMemName2_Mid, v_BSCMemName2_Last, v_BSCMem2_TitlePos,
                proxy_Desig, proxy_Name_First, proxy_Name_Mid, proxy_Name_Last, proxy_BirthYear,
                Proxy_Photo//.toString()
                ,
                proxy_Type_ID, proxy_ID_NO,
                p_BSCMemName1_First, p_BSCMemName1_Middle, p_BSCMemName1_Last, p_BSCMem1_TitlePosition,
                p_BSCMemName2_First, p_BSCMemName2_Middle, p_BSCMemName2_Last, p_BSCMem2_TitlePosition, grp_code);


        Log.d(TAG, " add member Liberia id: " + idRow);


        return idRow;


    }

    public long addMemberData(String c_code, String disCode, String upCode, String unCode, String vCode,
                              String str_hhID, String str_hhMemID, String str_MemName, String str_gender, String str_relation,
                              String str_entry_by, String str_entry_date, String lmp_date,
                              String child_dob, String elderly, String disabled,
                              String str_age, String regNDate, String birthYear,
                              String maritalStatus, String contactNo, String memOtherID, String memName_First,
                              String memName_Middle, String memName_Last,
                              String Photo,
                              String type_ID,
                              String id_NO, String v_BSCMemName1_First, String v_BSCMemName1_Mid, String v_BSCMemName1_Last,
                              String v_BSCMem1_TitlePosition, String v_BSCMemName2_First, String v_BSCMemName2_Mid,
                              String v_BSCMemName2_Last, String v_BSCMem2_TitlePos, String proxy_Desig, String proxy_Name_First,
                              String proxy_Name_Mid, String proxy_Name_Last, String proxy_BirthYear,
                              String Proxy_Photo,
                              String proxy_Type_ID, String proxy_ID_NO, String p_BSCMemName1_First, String p_BSCMemName1_Middle,
                              String p_BSCMemName1_Last, String p_BSCMem1_TitlePosition, String p_BSCMemName2_First, String p_BSCMemName2_Middle,
                              String p_BSCMemName2_Last, String p_BSCMem2_TitlePosition, String grpCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code); // country name
        values.put(DISTRICT_NAME_COL, disCode); // district name
        values.put(UPZILLA_NAME_COL, upCode); // upazilla name
        values.put(UNITE_NAME_COL, unCode); // Unit name
        values.put(VILLAGE_NAME_COL, vCode); // Village name
        values.put(HHID_COL, str_hhID); // Registered ID
        values.put(HH_MEM_ID, str_hhMemID); // Member ID
        values.put(MEM_NAME_COL, str_MemName); // Member name
        values.put(SEX_COL, str_gender); // sex
        values.put(RELATION_COL, str_relation); // relation
        values.put(ENTRY_BY, str_entry_by); // entry by
        values.put(ENTRY_DATE, str_entry_date); // entry Date
        values.put(LMP_DATE, lmp_date);
        values.put(CHILD_DOB, child_dob);
        values.put(ELDERLY, elderly);
        values.put(DISABLE, disabled);
        values.put(MEM_AGE, str_age);           // member age for malawi
        values.put(REG_DATE_COL, regNDate);
        values.put(BIRTH_YEAR_COL, birthYear);
        values.put(MARITAL_STATUS_COL, maritalStatus);
        values.put(CONTACT_NO_COL, contactNo);
        values.put(MEMBER_OTHER_ID_COL, memOtherID);
        values.put(MEM_NAME_FIRST_COL, memName_First);
        values.put(MEM_NAME_MIDDLE_COL, memName_Middle);
        values.put(MEM_NAME_LAST_COL, memName_Last);
        values.put(PHOTO_COL, Photo);
        values.put(TYPE_ID_COL, type_ID);
        values.put(ID_NO_COL, id_NO);
        values.put(V_BSC_MEM_1_NAME_FIRST_COL, v_BSCMemName1_First);
        values.put(V_BSC_MEM_1_NAME_MIDDLE_COL, v_BSCMemName1_Mid);
        values.put(V_BSC_MEM_1_NAME_LAST_COL, v_BSCMemName1_Last);
        values.put(V_BSC_MEM_1_TITLE_COL, v_BSCMem1_TitlePosition);
        values.put(V_BSC_MEM_2_NAME_FIRST_COL, v_BSCMemName2_First);
        values.put(V_BSC_MEM_2_NAME_MIDDLE_COL, v_BSCMemName2_Mid);
        values.put(V_BSC_MEM_2_NAME_LAST_COL, v_BSCMemName2_Last);
        values.put(V_BSC_MEM_2_TITLE_COL, v_BSCMem2_TitlePos);
        values.put(PROXY_DESIGNATION_COL, proxy_Desig);
        values.put(PROXY_NAME_FIRST_COL, proxy_Name_First);
        values.put(PROXY_NAME_MIDDLE_COL, proxy_Name_Mid);
        values.put(PROXY_NAME_LAST_COL, proxy_Name_Last);
        values.put(PROXY_BIRTH_YEAR_COL, proxy_BirthYear);
        values.put(PROXY_PHOTO_COL, Proxy_Photo);
        values.put(PROXY_TYPE_ID_COL, proxy_Type_ID);
        values.put(PROXY_ID_NO_COL, proxy_ID_NO);
        values.put(PROXY_BSC_MEM_1_NAME_FIRST_COL, p_BSCMemName1_First);
        values.put(PROXY_BSC_MEM_1_NAME_MIDDLE_COL, p_BSCMemName1_Middle);
        values.put(PROXY_BSC_MEM_1_NAME_LAST_COL, p_BSCMemName1_Last);
        values.put(PROXY_BSC_MEM_1_TITLE_COL, p_BSCMem1_TitlePosition);
        values.put(PROXY_BSC_MEM_2_NAME_FIRST_COL, p_BSCMemName2_First);
        values.put(PROXY_BSC_MEM_2_NAME_MIDDLE_COL, p_BSCMemName2_Middle);
        values.put(PROXY_BSC_MEM_2_NAME_LAST_COL, p_BSCMemName2_Last);
        values.put(PROXY_BSC_MEM_2_TITLE_COL, p_BSCMem2_TitlePosition);
        values.put(GROUP_CODE_COL, grpCode);


        // Inserting Row into local database
        long id = db.insert(REGISTRATION_MEMBER_TABLE, null, values);


        //   Log.d(TAG, "Member Table id: " + id);
        db.close(); // Closing database connection

        return id;
    }


    public String getHH_VerifiedStatus(String cCode, String dCode, String upCode, String unCode
            , String vCode, String personId) {

        SQLiteDatabase db = this.getReadableDatabase();
        String vStatus = "";// verifier status
        String selectQuery = " SELECT " + V_STATUS + " FROM " + REGISTRATION_TABLE + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + DISTRICT_NAME_COL + " = '" + dCode + "' " +
                " AND " + DISTRICT_NAME_COL + " = '" + dCode + "' " +
                " AND " + UPZILLA_NAME_COL + " = '" + upCode + "' " +
                " AND " + UNITE_NAME_COL + " = '" + unCode + "' " +
                " AND " + VILLAGE_NAME_COL + " = '" + vCode + "' " +
                " AND " + PID_COL + " = '" + personId + "' ";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            vStatus = cursor.getString(cursor.getColumnIndex(V_STATUS));
        }
        cursor.close();
        db.close();
        return vStatus;


    }

   /* public void updateRegistrationRecord(int pID, String dname, String upname, String uname,
                                         String vname, String pid, String r_date, String pname,
                                         String sex, String HHSize, String latitude, String longitude,
                                         String AGLand, String VStatus, String MStatus, String EntryBy,
                                         String EntryDate, String v_group) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String where = ID_COL + "=" + pID;

        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(PID_COL, pid); // Personal name
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize);
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group); // Date of creation


        // Inserting Row into local database
        db.update(REGISTRATION_TABLE, values, where, null);

        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false

        db.close(); // Closing database connection

        Log.d(TAG, "Registration data edited for: " + pID);
    }
*/


/*    public void updateRegistrationRecord(int pID, String dname, String upname, String uname,
                                         String vname, String addressCode, String pid, String r_date, String pname,
                                         String sex, String HHSize, String latitude, String longitude,
                                         String AGLand, String VStatus, String MStatus, String EntryBy,
                                         String EntryDate, String v_group) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String where = ID_COL + "=" + pID;

        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode);
        values.put(PID_COL, pid); // Personal name
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize);
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group); // Date of creation


        // Inserting Row into local database
        db.update(REGISTRATION_TABLE, values, where, null);

        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false

        db.close(); // Closing database connection

        Log.d(TAG, "Registration data edited for: " + pID);
    }*/

    public void updateRegistrationRecord(int pID, String dname, String upname, String uname,
                                         String vname, String addressCode, String pid, String r_date, String pname,
                                         String sex, String HHSize, String latitude, String longitude,
                                         String AGLand, String VStatus, String MStatus, String EntryBy,
                                         String EntryDate, String v_group, String wealthRank,
                                         String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String where = ID_COL + "=" + pID;
        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressCode);
        values.put(PID_COL, pid); // Personal name
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize);
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group);
        values.put(W_RANK_COL, wealthRank);// Date of creation
        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);
// Inserting Row into local database
        db.update(REGISTRATION_TABLE, values, where, null);
        updateRegistrationStatus("" + pID, 0);    // Setting Update status to false
        db.close(); // Closing database connection
        Log.d(TAG, "Registration data edited for: " + pID);
    }


   /* public long addRegistrationForMalawi(String c_code, String dname, String upname, String uname, String vname, String addressName, String pid, String r_date, String pname, String sex, String HHSize, String latitude, String longitude, String AGLand, String VStatus, String MStatus, String EntryBy, String EntryDate, String v_group, String wRankCode) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE, c_code); // country name
        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressName);
        values.put(PID_COL, pid); // Personal code
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize); // sex
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group); // VSLA_GROUP
        values.put(W_RANK_COL, wRankCode); // VSLA_GROUP

        // Inserting Row into local database
        long id = db.insert(REGISTRATION_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d(TAG, "New Registration data added into Registration Table: " + id);
        return id;
    }*/


    public long addRegistrationForMalawi(String c_code, String dname, String upname, String uname, String vname, String addressName, String pid, String r_date, String pname, String sex, String HHSize, String latitude, String longitude, String AGLand, String VStatus, String MStatus, String EntryBy, String EntryDate, String v_group, String wRankCode, String LTp2Hectres, String LT3mFoodStock, String NoMajorCommonLiveStock, String ReceiveNoFormalWages, String NoIGA, String RelyPiecework) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNTRY_CODE, c_code); // country name
        values.put(DISTRICT_NAME_COL, dname); // district name
        values.put(UPZILLA_NAME_COL, upname); // upazilla name
        values.put(UNITE_NAME_COL, uname); // Unit name
        values.put(VILLAGE_NAME_COL, vname); // Unit name
        values.put(REGN_ADDRESS_LOOKUP_CODE_COL, addressName);
        values.put(PID_COL, pid); // Personal code
        values.put(REG_DATE_COL, r_date); // Registration name
        values.put(PNAME_COL, pname); // Person name
        values.put(SEX_COL, sex); // sex
        values.put(HH_SIZE, HHSize); // sex
        values.put(LATITUDE_COL, latitude); // Latitude
        values.put(LONGITUDE_COL, longitude); // Longitude
        values.put(AG_LAND, AGLand); // Longitude
        values.put(V_STATUS, VStatus); // Longitude
        values.put(M_STATUS, MStatus); // Longitude
        values.put(ENTRY_BY, EntryBy); // Longitude
        values.put(ENTRY_DATE, EntryDate); // Date of creation
        values.put(VSLA_GROUP, v_group); // VSLA_GROUP
        values.put(W_RANK_COL, wRankCode); // WRANK
        values.put(LTP_2_HECTRES_COL, LTp2Hectres);
        values.put(LT_3_FOOD_STOCK_COL, LT3mFoodStock);
        values.put(NO_MAJOR_COMMON_LIVE_STOCK_COL, NoMajorCommonLiveStock);
        values.put(RECEIVE_NO_FORMAL_WAGES_COL, ReceiveNoFormalWages);
        values.put(NO_IGA_COL, NoIGA);
        values.put(RELY_PICE_EORK_COL, RelyPiecework);
// Inserting Row into local database
        long id = db.insert(REGISTRATION_TABLE, null, values);
        db.close(); // Closing database connection
        Log.d(TAG, "New Registration data added into Registration Table: " + id);
        return id;
    }

    public boolean getHouseHoldRegistrationIsChecked(String columnName, String c_code, String dname, String upname, String uname, String vname, String pid) {
        boolean isChecked;
        String str = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + columnName + " FROM " + REGISTRATION_TABLE
                + " WHERE " + COUNTRY_CODE + " = '" + c_code + "'" // country name
                + " AND " + DISTRICT_NAME_COL + " = '" + dname + "'" // district name
                + " AND " + UPZILLA_NAME_COL + " = '" + upname + "'" // upazilla name
                + " AND " + UNITE_NAME_COL + " = '" + uname + "'" // Unit name
                + " AND " + VILLAGE_NAME_COL + " = '" + vname + "'" // Unit name
                + " AND " + PID_COL + " = '" + pid + "'"; // Personal code
        Cursor cursor = db.rawQuery(sql, null);


        if (cursor != null) {
            if (cursor.moveToFirst()) {
                str = cursor.getString(0);
            }
            cursor.close();
            db.close();
        }

        if (str.equals("Y"))
            isChecked = true;
        else
            isChecked = false;


        return isChecked;


    }

    public ArrayList<VillageItem> getSelectedVillageList() {
        ArrayList<VillageItem> selectedVillage = new ArrayList<VillageItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + LAYER_CODE_COL
                + " , " + VILLAGE_NAME_COL + " FROM " + SELECTED_VILLAGE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                VillageItem vi = new VillageItem();
                vi.setLayRCode(cursor.getString(cursor.getColumnIndex(LAYER_CODE_COL)));
                vi.setLayR4ListName(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                Log.d(TAG, " setLayRCode :" + vi.getLayRCode());
                selectedVillage.add(vi);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return selectedVillage;
    }

    public ArrayList<FDPItem> getSelectedFDPList() {
        ArrayList<FDPItem> selectedFDP = new ArrayList<FDPItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COUNTRY_CODE_COL + " , " + FDP_CODE_COL
                + "  , " + FDP_NAME_COL + " FROM " + SELECTED_FDP_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                FDPItem fdp = new FDPItem();
                fdp.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                fdp.setFDPCode(cursor.getString(cursor.getColumnIndex(FDP_CODE_COL)));
                fdp.setFDPName(cursor.getString(cursor.getColumnIndex(FDP_NAME_COL)));
                Log.d(TAG, " setLayRCode :" + fdp.getFDPCode());
                selectedFDP.add(fdp);
            } while (cursor.moveToNext());
        }
        return selectedFDP;
    }


    public ArrayList<ServiceCenterItem> getSelectedServiceCenterList() {
        ArrayList<ServiceCenterItem> selectedSrvCenter = new ArrayList<ServiceCenterItem>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COUNTRY_CODE_COL + " , " + SERVICE_CENTER_CODE_COL
                + "  , " + SERVICE_CENTER_NAME_COL + " FROM " + SELECTED_SERVICE_CENTER_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ServiceCenterItem srvCenter = new ServiceCenterItem();
                srvCenter.setAdmCountryCode(cursor.getString(0));
                srvCenter.setServiceCenterCode(cursor.getString(1));
                srvCenter.setServiceCenterName(cursor.getString(2));
                Log.d(TAG, " ServiceCenterCode :" + srvCenter.getServiceCenterCode());
                selectedSrvCenter.add(srvCenter);
            } while (cursor.moveToNext());

            cursor.close();
            db.close();
        }
        return selectedSrvCenter;
    }


    // Check Local Login
    public boolean isValidLocalLogin(final String user, final String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + user + "' AND " + USER_LOGIN_PW + " = " + "'" + pass + "'";
        try {

            final Cursor c = db.rawQuery(selectQuery, null);
            if (c != null) {
                if (c.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {
            // close database connection
            db.close();
        }
        return false;
    }

    /**
     * this method check is the user is admin
     *
     * @param admin_user admin_user
     * @param admin_pass password
     * @return
     */

    public boolean isValidAdminLocalLogin(final String admin_user, final String admin_pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + STAFF_MASTER_TABLE
                + " WHERE " + USER_LOGIN_NAME + " = " + "'" + admin_user + "' "
                + " AND " + USER_LOGIN_PW + " = " + "'" + admin_pass + "' "
                + " AND " + STAFF_ADMIN_ROLE_COL + " IN ('A' ,'C') ";

        try {

            final Cursor c = db.rawQuery(selectQuery, null);
            if (c != null) {
                if (c.getCount() > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "isValidLocalLogin() Method: " + e.getMessage());

        } finally {
            // close database connection
            db.close();
        }
        return false;
    }


    /**
     * Storing user details in database
     *
     * @param user_id
     * @param country_code
     * @param login_name
     * @param login_pw
     * @param first_name
     * @param last_name
     * @param email
     * @param email_verification
     * @param user_status
     * @param entry_by
     * @param entry_date
     */
    public void addUser(String user_id, String country_code, String login_name, String login_pw, String first_name, String last_name, String email, String email_verification, String user_status, String entry_by, String entry_date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, user_id);
        values.put(COUNTRY_CODE, country_code);
        values.put(USER_LOGIN_NAME, login_name);
        values.put(USER_LOGIN_PW, login_pw);
        values.put(USER_FIRST_NAME, first_name);
        values.put(USER_LAST_NAME, last_name);
        values.put(USER_EMAIL, email);
        values.put(USER_EMAIL_VERIFICATION, email_verification);
        values.put(USER_STATUS, user_status);
        values.put(ENTRY_BY, entry_by);
        values.put(ENTRY_DATE, entry_date);

        // Inserting Row
        long id = db.insert(LOGIN_TABLE, null, values);
        db.close(); // Closing database connection

        Log.d("MOR_12", "New user inserted into User Login: " + id);
    }

    /**
     * Getting Layer Label
     */
    public String getLayerLabel(String c_code, String l_code) {
        String layerName = "";
        String selectQuery = "SELECT  " + LAYER_NAME_COL + " FROM " + LAYER_LABEL_TABLE + " WHERE " + COUNTRY_CODE + "='" + c_code + "' AND " + LAYER_CODE_COL + "='" + l_code + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            layerName = cursor.getString(0);
        }
        cursor.close();
        db.close();

        return layerName;
    }


    /**
     * Getting user data from database [for future use]
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();

        String selectQuery = "SELECT  * FROM " + LOGIN_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("code", cursor.getString(0));      // UsrID as StaffCode
            user.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            user.put("username", cursor.getString(2));    // userid
            user.put("password", cursor.getString(3));    // password

            user.put("name", cursor.getString(4));      // UsrFirstName as name
            user.put("status", cursor.getString(8));    // UsrStatus as user status
        }
        cursor.close();
        db.close();
        // return user
        Log.d(TAG, "Fetching user from Sqlite: " + user.toString());

        return user;
    }

/******************** check iop month for service
 *
 */
    /**
     * Getting date data  from om month for Service  [for future use]
     */
    public HashMap<String, String> getDateRangeForService(String countryCode, String srvOpMonthCode) {
        HashMap<String, String> dateRangeS = new HashMap<String, String>();


        String selectQuery = SQLiteQuery.getServiceDateRange_selectQuery(countryCode, srvOpMonthCode);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRangeS.put("opCode", cursor.getString(0));    // CountryCode as Country Code
            dateRangeS.put("opMCode", cursor.getString(1));
            dateRangeS.put("sdate", cursor.getString(2));      // Start Date
            dateRangeS.put("edate", cursor.getString(3));    // End Date
            dateRangeS.put("opMonthLable", cursor.getString(4));
        } else {
            dateRangeS.put("opCode", null);    // CountryCode as Country Code
            dateRangeS.put("opMCode", null);
            dateRangeS.put("sdate", null);      // Start Date
            dateRangeS.put("edate", null);    // End Date
            dateRangeS.put("opMonthLable", null);
        }
        cursor.close();
        db.close();

        return dateRangeS;
    }
    /* **********************************************************************************
     * *******************************Date Range Section*********************************
     * ***********************************************************************************/

    /**
     * @Discription: Getting Operation Startig date & End date data from database
     * [ For Graduation date]
     * @author: Faisal Mohammad
     * @date: 2015-10-01
     */
    public HashMap<String, String> getGRDDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();

        String selectQuery = "SELECT  " + START_DATE + " , " + END_DATE + " FROM " + OP_MONTH_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "' AND " +
                OPERATION_CODE_COL + " = '1' AND " + STATUS + " = 'A'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(1));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(2));      // Start Date
            dateRange.put("edate", cursor.getString(3));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }

    /* ******************************/
    public String getGraduatedDate(String countryCode, String donorCode, String awardCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + AWARD_END_DATE_COL + " FROM "
                + ADM_AWARD_TABLE +
                " WHERE " + COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + AWARD_CODE_COL + " = '" + awardCode + "' ";
        String grdDate = "";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                grdDate = cursor.getString(cursor.getColumnIndex(AWARD_END_DATE_COL));
            }
            cursor.close();
        }
        db.close();
        return grdDate;
    }

    /**
     * @author: Faisal Mohammad
     * @date: 2015-10-19
     */
    public HashMap<String, String> getRegistrationDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<>();
        String selectQuery = SQLiteQuery.get_RegNAssProgSrvRegistrationDateRangeSelectQuery(cCode);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getCount() > 0) {
                dateRange.put("sdate", cursor.getString(cursor.getColumnIndex(USA_START_DATE)));
                dateRange.put("edate", cursor.getString(cursor.getColumnIndex(USA_END_DATE)));
            } else {
                dateRange.put("sdate", null);
                dateRange.put("edate", null);
            }
            cursor.close();
        }

        db.close();
        return dateRange;


    }

    /**
     * This method invoking Form
     *
     * @param cCode   Country Code
     * @param opMonth Op Month Code
     * @return A  Hash Map of startDate & end Date
     * @see {@link com.siddiquinoor.restclient.activity.sub_activity.dynamic_table.DTResponseActivity.btn_goToQustion}
     * This method  return Date the Range of Dt
     */

    public HashMap<String, String> getDynamicTableDateRange(String cCode, String opMonth) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  "
                + "  " + USA_START_DATE
                + " , " + USA_END_DATE
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '5'"
                + " AND " + OP_MONTH_CODE_COL + " = '" + opMonth + "'";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            dateRange.put("sdate", cursor.getString(0));      // Start Date
            dateRange.put("edate", cursor.getString(1));    // End Date

            cursor.close();
            db.close();
        } else {

            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }


        return dateRange;
    }

    /**
     * Getting user data from database [for future use]
     * review the method
     */
    public HashMap<String, String> getDateRange(String cCode) {
        HashMap<String, String> dateRange = new HashMap<String, String>();


        String sql = "SELECT  " + COUNTRY_CODE_COL
                + " , " + START_DATE +
                " , " + END_DATE
                + " FROM " + OP_MONTH_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + STATUS + "= 'A'"
                + " AND " + OPERATION_CODE_COL + " = '1'" +
                "  ORDER BY " + OP_MONTH_CODE_COL + "   DESC   LIMIT 1 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            dateRange.put("c_code", cursor.getString(0));    // CountryCode as Country Code
            dateRange.put("sdate", cursor.getString(1));      // Start Date
            dateRange.put("edate", cursor.getString(2));    // End Date
        } else {
            dateRange.put("c_code", null);                  // CountryCode as Country Code
            dateRange.put("sdate", null);                   // Start Date
            dateRange.put("edate", null);                   // End Date
        }
        cursor.close();
        db.close();

        return dateRange;
    }


    public String getRelationName(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor qCursor = null;
        String result = null;

        String sql = "SELECT " + RELATION_NAME + " FROM " + RELATION_TABLE + " WHERE " + RELATION_CODE + "='" + code + "'";
        qCursor = db.rawQuery(sql, null);


        if (qCursor != null) {
            if (qCursor.moveToFirst()) {
                do {
                    result = qCursor.getString(qCursor.getColumnIndex(RELATION_NAME));
                } while (qCursor.moveToNext());
            }
        }
        db.close();

        return result;
    }


    public String getDataByCode(String col, String table, String condition) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor qCursor = null;
        ArrayList<String> results = null;
        String result = null;

        // TODO :: Need to modify where col=null means for all data of a row
        // to do so we need to modify the results.add function in 'do' loop

        if (col != null) {
            String sql = "SELECT " + col + " FROM " + table + " WHERE " + condition;
            qCursor = db.rawQuery(sql, null);
        } else {
            String sql = "SELECT * FROM " + table + " WHERE " + condition;
            qCursor = db.rawQuery(sql, null);
        }


        if (qCursor != null) {
            if (qCursor.moveToFirst()) {
                do {
                    result = qCursor.getString(qCursor.getColumnIndex(col));
                } while (qCursor.moveToNext());
            }
        }
        db.close();

        return result;
    }

    /*******************************************************************************************
     /*******************************************************************************************
     /***************************** DELETE QUERY *********************************************
     /*******************************************************************************************
     /*******************************************************************************************/

    /**
     * @author: Faisal Mohammad
     * @date: 2015-10-05
     * @discription: delete the recorde from service
     * @PROBLEM: -
     * @remark-
     */
    public int deleteService(String countryId, String donorId, String awardId, String distId, String upId, String unId, String villId, String hhId, String memId, String progId, String srvId, String opCodeId, String opMCodeId, String srvSerialNo) {


        SQLiteDatabase db = this.getWritableDatabase();
        String where = SQLiteQuery.getServiceDelete_WhereCondition(countryId, donorId, awardId, distId, upId, unId, villId, hhId, memId, progId, srvId, opMCodeId, srvSerialNo);

        int id = db.delete(SERVICE_TABLE, where, null);
        db.close();
        return id;

    }

    public int deleteDistribution(DistributionSaveDataModel distData) {


        SQLiteDatabase db = this.getWritableDatabase();
        String where = COUNTRY_CODE_COL + " = '" + distData.getCountryCode() + "'" +
                " AND " + DONOR_CODE_COL + " = '" + distData.getAdmDonorCode() + "' " +
                " AND " + AWARD_CODE_COL + " = '" + distData.getAdmAwardCode() + "' " +
                " AND " + DISTRICT_CODE_COL + " = '" + distData.getDistrictCode() + "' " +
                " AND " + UPCODE_COL + " = '" + distData.getUpCode() + "' " +
                " AND " + UCODE_COL + " = '" + distData.getUniteCode() + "' " +
                " AND " + VCODE_COL + " = '" + distData.getVillageCode() + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + distData.getProgCode() + "' " +
                " AND " + SERVICE_CODE_COL + " = '" + distData.getSrvCode() + "' " +
                " AND " + OP_MONTH_CODE_COL + " = '" + distData.getOpMonthCode() + "' " +

                " AND " + MEM_ID_15_D_COL + " = '" + distData.getID() + "' ";

        int id = db.delete(DISTRIBUTION_TABLE, where, null);

        Log.d(TAG, "DELETE Distribution data  id: " + distData.getID());
        db.close();
        return id;

    }


    public void deleteMember(int mID) {

        SQLiteDatabase db = this.getReadableDatabase();//
        String where = ID_COL + "=" + mID;
        db.delete(REGISTRATION_MEMBER_TABLE, where, null);
        db.close();
    }


    /**
     * To delete house  hold in live server send the
     */
    public SQLServerSyntaxGenerator getDeletedMemberID(int mID) { // mID= member Id
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getDeletedMemberIDQuery(mID);
        Cursor cursor = db.rawQuery(selectQuery, null);
        SQLServerSyntaxGenerator deletedMemberId = new SQLServerSyntaxGenerator();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                deletedMemberId.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                deletedMemberId.setLayR1ListCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                deletedMemberId.setLayR2ListCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                deletedMemberId.setLayR3ListCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                deletedMemberId.setLayR4ListCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                deletedMemberId.setHHID(cursor.getString(cursor.getColumnIndex(HHID_COL)));
                deletedMemberId.setMemID(cursor.getString(cursor.getColumnIndex(HH_MEM_ID)));
            }
            cursor.close();
        }
        db.close();
        return deletedMemberId;

    }

    /**
     * To delete house  hold in RegNHHTable live server db need to get HHID with layRList Codes
     */
    public SQLServerSyntaxGenerator getDeletedHouseHoldID(String pID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = SQLiteQuery.getDeletedHouseHoldIDQuery(pID);
        Cursor cursor = db.rawQuery(selectQuery, null);
        SQLServerSyntaxGenerator deletedHHid = new SQLServerSyntaxGenerator();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                deletedHHid.setAdmCountryCode(cursor.getString(cursor.getColumnIndex(COUNTRY_CODE_COL)));
                deletedHHid.setLayR1ListCode(cursor.getString(cursor.getColumnIndex(DISTRICT_NAME_COL)));
                deletedHHid.setLayR2ListCode(cursor.getString(cursor.getColumnIndex(UPZILLA_NAME_COL)));
                deletedHHid.setLayR3ListCode(cursor.getString(cursor.getColumnIndex(UNITE_NAME_COL)));
                deletedHHid.setLayR4ListCode(cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL)));
                deletedHHid.setHHID(cursor.getString(cursor.getColumnIndex(PID_COL)));
            }
            cursor.close();
        }
        db.close();
        return deletedHHid;

    }

    public void deleteHouseHold(String pID) {

        SQLiteDatabase db = this.getReadableDatabase();

        // Delete from Registration table
        String where = PID_COL + "='" + pID + "'";
        db.delete(REGISTRATION_TABLE, where, null);

        // deleting from Members table
        String mWhere = HHID_COL + "='" + pID + "'";
        db.delete(REGISTRATION_MEMBER_TABLE, mWhere, null);
        db.close();
    }


    public String selectCountryCode() {
        Cursor cursor;
        String countryCode = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " Select distinct " + SELECTED_VILLAGE_TABLE + "." +
                COUNTRY_CODE_COL + " from " + SELECTED_VILLAGE_TABLE +
                " Inner join " + COUNTRY_TABLE +
                " on " + SELECTED_VILLAGE_TABLE + "." + COUNTRY_CODE_COL + " = " + COUNTRY_TABLE + "." + COUNTRY_CODE_COL +
                " order by " + SELECTED_VILLAGE_TABLE + "." + COUNTRY_CODE_COL;
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                countryCode = cursor.getString(0);
            }
        } else {
        }
        return countryCode;
    }

    public int selectUploadSyntextRowCount() {
        Cursor cursor;
        String count = "0";

        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT( " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + ")" + " FROM " + UPLOAD_SYNTAX_TABLE + " WHERE " + UPLOAD_SYNTAX_TABLE + "." + SYNC_COL + " = " + 0;
        cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getString(0);
            }

        }
        Log.d(TAG + "-->Count-->", count);
        return Integer.valueOf(count);
    }

    public boolean checkDataAvailableOrNotInGpsLocationContentTable(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode) {
        Cursor cursor;
        String count = "0";
        SQLiteDatabase db = this.getReadableDatabase();

        String query = " SELECT * FROM " + GPS_LOCATION_CONTENT_TABLE
                + " WHERE " + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";
        Log.d(TAG, query);
        cursor = db.rawQuery(query, null);

        Log.d(TAG, " " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.close();
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    public void deleteRowFromGpsLocationContentTable(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode) {
        SQLiteDatabase db = this.getWritableDatabase();

        String where = COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";

        int deletedRowNo = db.delete(GPS_LOCATION_CONTENT_TABLE, where, null);
        db.close();
        Log.d(TAG, "" + deletedRowNo);
    }

    public void getImageFromDatabase(String AdmCountryCode, String GrpCode, String SubGrpCode, String LocationCode, String ContentCode, ImageView imageView) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + IMAGE_FILE_COL + " FROM "
                + GPS_LOCATION_CONTENT_TABLE + " WHERE "
                + COUNTRY_CODE_COL + " = '" + AdmCountryCode + "'"
                + " AND " + GROUP_CODE_COL + " = '" + GrpCode + "'"
                + " AND " + SUB_GROUP_CODE_COL + " = '" + SubGrpCode + "'"
                + " AND " + LOCATION_CODE_COL + " = '" + LocationCode + "'"
                + " AND " + CONTENT_CODE_COL + " = '" + ContentCode + "'";

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(cursor.getColumnIndex(IMAGE_FILE_COL));
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);
        }
        cursor.close();

    }

    //Login page Query, Added By REFAT
    public void deleteFromSelectedFDP() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_FDP_TABLE, null, null);
    }

    public void deleteFromSelectedVillage() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_VILLAGE_TABLE, null, null);

    }

    public void deleteFromSelectedServiceCenter() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SELECTED_SERVICE_CENTER_TABLE, null, null);
    }

    public List<String> selectGeoDataFDP() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        String query = "SELECT " + FDP_NAME_COL + " FROM " + SELECTED_FDP_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String fdpName = cursor.getString(cursor.getColumnIndex(FDP_NAME_COL));
                list.add(fdpName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }


    public List<String> selectGeoDataCenter() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        String query = "SELECT " + SERVICE_CENTER_NAME_COL + " FROM " + SELECTED_SERVICE_CENTER_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String centerName = cursor.getString(cursor.getColumnIndex(SERVICE_CENTER_NAME_COL));
                list.add(centerName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }


    public List<String> selectGeoDataVillage() {
        List<String> list;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        list = new ArrayList<String>();

        String query = "SELECT " + VILLAGE_NAME_COL + " FROM " + SELECTED_VILLAGE_TABLE;
        cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String villageName = cursor.getString(cursor.getColumnIndex(VILLAGE_NAME_COL));
                list.add(villageName);
                cursor.moveToNext();
            }
            cursor.close();
            db.close();
        }
        return list;
    }

    public long insertIntoLastSyncTraceStatus(String userId, String userName, String lastSyncTime) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USER_ID, userId);
        values.put(USER_LOGIN_NAME, userName);
        values.put(LAST_SYNC_TIME_COL, lastSyncTime);

        long id = db.insert(LAST_SYNC_TYRACE_TABLE, null, values);

        return id;
    }

    public String lastSyncStatus() {
        String query = "SELECT " + LAST_SYNC_TIME_COL + " FROM " + LAST_SYNC_TYRACE_TABLE + " ORDER BY " + ID_COL + " DESC LIMIT " + 1;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String date = "";
        if (cursor.moveToFirst()) {
            date = cursor.getString(cursor.getColumnIndex(LAST_SYNC_TIME_COL));
        }
        cursor.close();
        db.close();
        return date;
    }

    public void addIntoDTATable(String dtBasic, String dtqCode, String dtaCode, String dtaLabel, String dtaValue,
                                long dtSeq, String dtaShort, String dtScoreCode, String dtSkipDTQCode, String dtaCompareCode, String showHide,
                                long maxValue, long minValue, String dataType, String markOnGrid, String entryBy, String entryDate) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DTA_LABEL_COL, dtaLabel);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(DT_SEQ_COL, dtSeq);
        values.put(DTA_SHORT_COL, dtaShort);
        values.put(DT_SCORE_CODE_COL, dtScoreCode);
        values.put(DTSKIP_DTQ_CODE_COL, dtSkipDTQCode);
        values.put(DTA_COMPARE_CODE_COL, dtaCompareCode);
        values.put(SHOW_HIDE_COL, showHide);
        values.put(MAX_VALUE_COL, maxValue);
        values.put(MIN_VALUE_COL, minValue);
        values.put(DATA_TYPE_COL, dataType);
        values.put(MARK_ON_GRID_COL, markOnGrid);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_A_TABLE, null, values);
        db.close();
    }

    public void addIntoDTBasic(String dtBasic, String dtTitle, String dtSubTitle, String dtDescription, String dtAutoScroll,
                               String dtAutoScrollText, String dtActive, String dtCategory, String dtGeoListLevel,
                               String dtOpMode, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DT_TITLE_COL, dtTitle);
        values.put(DT_SUB_TITLE_COL, dtSubTitle);
        values.put(DT_DESCRIPTION_COL, dtDescription);
        values.put(DT_AUTO_SCROLL_COL, dtAutoScroll);
        values.put(DTAUTO_SCROLL_TEXT, dtAutoScrollText);
        values.put(DT_ACTIVE_COL, dtActive);
        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(DT_GEO_LIST_LEVEL_COL, dtGeoListLevel);
        values.put(DT_OP_MODE_COL, dtOpMode);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_BASIC_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCategory(String dtCategory, String categoryName, String frequency, String entryBy,
                                  String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_CATEGORY_COL, dtCategory);
        values.put(CATEGORY_NAME_COL, categoryName);
        values.put(FREQUENCY_COL, frequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_CATEGORY_TABLE, null, values);
        db.close();
    }

    public void addIntoDTCountryProgram(String countryCode, String donorCode, String awardCode, String programCode,
                                        String progActivityCode, String progActivityTitle, String dtBasic, String refIdentifier,
                                        String status, String rftFrequency, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(PROG_ACTIVITY_TITLE_COL, progActivityTitle);
        values.put(DT_BASIC_COL, dtBasic);
        values.put(REF_IDENTIFIER_COL, refIdentifier);
        values.put(STATUS, status);
        values.put(RPT_FREQUENCY_COL, rftFrequency);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_COUNTRY_PROGRAM_TABLE, null, values);
        db.close();
    }

    public void addIntoDTGeoListLevel(String geoLevel, String geoLevelName, String listUDFName, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(GEO_LEVEL_COL, geoLevel);
        values.put(GEO_LEVEL_NAME_COL, geoLevelName);
        values.put(LIST_UDF_NAME_COL, listUDFName);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DTGEO_LIST_LEVEL_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQResMode(String qResMode, String qResLupText, String dataType, String lookUpUDFName, String responseValueControl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(QRES_MODE_COL, qResMode);
        values.put(QRES_LUP_TEXT_COL, qResLupText);
        values.put(DATA_TYPE_COL, dataType);
        values.put(LOOK_UP_UDF_NAME_COL, lookUpUDFName);
        values.put(RESPONSE_VALUE_CONTROL_COL, responseValueControl);

        long id = db.insert(DTQRES_MODE_TABLE, null, values);
        db.close();
    }

    public void addIntoDTQTable(String dtBasic, String dtqCode, String qText, String qResMode, String qSeq, String allowNull, String lub_tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(QTEXT_COL, qText);
        values.put(QRES_MODE_COL, qResMode);
        values.put(QSEQ_SCOL, qSeq);
        values.put(ALLOW_NULL_COL, allowNull);
        values.put(LUP_TABLE_NAME, lub_tableName);

        long id = db.insert(DTQ_TABLE, null, values);
        db.close();
    }

    public void addIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                       String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                       String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DT_BASIC_COL, dtBasic);
        values.put(COUNTRY_CODE_COL, countryCode);
        values.put(DONOR_CODE_COL, donorCode);
        values.put(AWARD_CODE_COL, awardCode);
        values.put(PROGRAM_CODE_COL, programCode);
        values.put(DT_ENU_ID_COL, dtEnuId);
        values.put(DTQ_CODE_COL, dtqCode);
        values.put(DTA_CODE_COL, dtaCode);
        values.put(DT_RSEQ_COL, dtrSeq);
        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);

        long id = db.insert(DT_RESPONSE_TABLE_COL, null, values);
        db.close();
    }

    public void updateIntoDTResponseTable(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                          String dtEnuId, String dtqCode, String dtaCode, String dtrSeq, String dtaValue,
                                          String progActivityCode, String dttTimeString, String opMode, String opMonthCode, String dataType){

        SQLiteDatabase db = this.getWritableDatabase();

        String where =  DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_RSEQ_COL + " = " + dtrSeq;

        ContentValues values = new ContentValues();

        values.put(DTA_VALUE_COL, dtaValue);
        values.put(PROG_ACTIVITY_CODE_COL, progActivityCode);
        values.put(DTTIME_STRING_COL, dttTimeString);
        values.put(OP_MODE_COL, opMode);
        values.put(OP_MONTH_CODE_COL, opMonthCode);
        values.put(DATA_TYPE_COL, dataType);


        int id=db.update(DT_RESPONSE_TABLE_COL,values,where,null);

        Log.d("DT_UP"," no of row :"+id);

    }


    /**
     * this method check either data exits or not
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @param dtrSeq      - dynamic Response Sequence
     * @return either data exist or not
     */
    public boolean isDataExitsInDTAResponse_Table(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                  String dtEnuId, String dtqCode, String dtaCode) {

        DTResponseTableDataModel mDta = getDTResponseTableData(dtBasic, countryCode, donorCode, awardCode, programCode, dtEnuId, dtqCode, dtaCode);
        if (mDta != null)
            return true;
        else
            return false;

    }


    /**
     * @param dtBasic     - dynamic table  basic code
     * @param countryCode - country code
     * @param donorCode   - donor Code
     * @param awardCode   - award code
     * @param programCode - program code
     * @param dtEnuId     - staff id or entry by code
     * @param dtqCode     - dynamic table question
     * @param dtaCode     - dynamic table
     * @param dtrSeq      - dynamic Response Sequence
     * @return DTResponse Objec only need {@link DTResponseTableDataModel#getDtaValue()}
     */

    public DTResponseTableDataModel getDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                           String dtEnuId, String dtqCode, String dtaCode, int dtrSeq) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE_COL + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " +
                " AND " + DT_RSEQ_COL + " = " + dtrSeq;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();

                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));
               /* dtResponse.setCountryCode(cursor.getString(1));
                dtResponse.setDonorCode(cursor.getString(2));
                dtResponse.setAwardCode(cursor.getString(3));
                dtResponse.setProgramCode(cursor.getString(4));
                dtResponse.setDtEnuId(cursor.getString(5));
               */


            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }



    public DTResponseTableDataModel getDTResponseTableData(String dtBasic, String countryCode, String donorCode, String awardCode, String programCode,
                                                           String dtEnuId, String dtqCode, String dtaCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        DTResponseTableDataModel dtResponse = null;
        String sql = "SELECT * FROM " + DT_RESPONSE_TABLE_COL + "" +

                " WHERE " + DT_BASIC_COL + " = '" + dtBasic + "' " +
                " AND " + COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + PROGRAM_CODE_COL + " = '" + programCode + "' " +
                " AND " + DT_ENU_ID_COL + " = '" + dtEnuId + "' " +
                " AND " + DTQ_CODE_COL + " = '" + dtqCode + "' " +
                " AND " + DTA_CODE_COL + " = '" + dtaCode + "' " ;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {

                dtResponse = new DTResponseTableDataModel();

                dtResponse.setDtaValue(cursor.getString(9));
                dtResponse.setDtBasic(cursor.getString(cursor.getColumnIndex(DT_BASIC_COL)));
                dtResponse.setDtqCode(cursor.getString(cursor.getColumnIndex(DTQ_CODE_COL)));
                dtResponse.setDtaCode(cursor.getString(cursor.getColumnIndex(DTA_CODE_COL)));



            }
            cursor.close();
            db.close();
        }
        return dtResponse;
    }

    public void addIntoDTTableDefinition(String tableName, String fieldName, String fieldDefinition, String fieldShortName,
                                         String valueUdf, String lupTableName, String adminOnly, String entryBy, String entryDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(FIELD_NAME_COL, fieldName);
        values.put(FIELD_DEFINITION_COL, fieldDefinition);
        values.put(FIELD_SHORT_NAME_COL, fieldShortName);
        values.put(VALUE_UDF_COL, valueUdf);
        values.put(LUPTABLE_NAME_COL, lupTableName);
        values.put(ADMIN_ONLY_COL, adminOnly);
        values.put(ENTRY_BY, entryBy);
        values.put(ENTRY_DATE, entryDate);

        long id = db.insert(DT_TABLE_DEFINITION_TABLE, null, values);
        db.close();
    }

    public void addIntoDTTableListCategory(String tableName, String tableGroupCode, String useAdminOnly, String useReport,
                                           String useTransection, String useLup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_NAME_COL, tableName);
        values.put(TABLE_GROUP_CODE_COL, tableGroupCode);
        values.put(USE_ADMIN_ONLY_COL, useAdminOnly);
        values.put(USE_REPORT_COL, useReport);
        values.put(USE_TRANSACTION_COL, useTransection);
        values.put(USE_LUP_COL, useLup);

        long id = db.insert(DTTABLE_LIST_CATEGORY_TABLE, null, values);
        db.close();
    }

}
