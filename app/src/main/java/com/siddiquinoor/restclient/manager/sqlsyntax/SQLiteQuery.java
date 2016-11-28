package com.siddiquinoor.restclient.manager.sqlsyntax;

import android.util.Log;

import com.siddiquinoor.restclient.data_model.RegNAssgProgSrv;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.views.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.adapters.ServiceDataModel;

/**
 * @author FAISAL MOHAMMAD on 1/17/2016.
 *         <p>
 *         This class provided  all the local queries
 *         </p>
 */
public class SQLiteQuery {

    private static final String YES = "Y";

    public static String getUpzillaJoinQuery(String countryCode, String districtCode) {
        return " JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE +
                " ON " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " AND " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " WHERE " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + "='" + districtCode + "' GROUP BY " +
                SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + ", " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL;
    }

    public static String getUnionJoinQuery(String countryCode, String districtCode, String upzellaCode) {
        return " JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE +
                " ON " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " AND " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " AND " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL +
                " WHERE " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + "='" + districtCode + "' AND " +
                SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + "='" + upzellaCode + "' " +
                " GROUP BY " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + ", " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL;
    }

    public static String getVillageJoinQuery(String countryCode, String districtCode, String upzellaCode, String unionCode) {
        return " JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE +
                " ON " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " AND " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " AND " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL +
                " AND " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL +

                " WHERE " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + countryCode + "' AND "
                + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + "='" + districtCode + "' AND " +
                SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + "='" + upzellaCode + "' AND " +
                SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + "='" + unionCode + "' " +
                " GROUP BY " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + ", " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL;
    }

    public static String getDistrictJoinQuery(String countryCode) {
        return " JOIN " + SQLiteHandler.SELECTED_VILLAGE_TABLE +
                " ON " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.SELECTED_VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " WHERE " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + countryCode + "' GROUP BY " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + ", " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL;

    }


    /**
     * <p>This method return the sql String of Assign </p>
     *
     * @param cCode     Country Code
     * @param dstCode   District Code =Layer1Code
     * @param upCode    Upzella Code=Layer2Code
     * @param unCode    Union Code= Layer3Code
     * @param vCode     Village Code =Layer4Code
     * @param donorCode Donor Code
     * @param awardCode Award Code
     * @param prgCode   program Code
     * @param srvCode   service Code
     * @return sql String
     */

    public static String getSingleMemberForAssign_sql(String cCode, String dstCode, String upCode, String unCode, String vCode, String hhid, String memberId, String donorCode, String awardCode, String prgCode, String srvCode) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        String sql = "SELECT " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL
                + ", " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_NAME + " AS HHRelation, " +
                " CASE WHEN LENGTH ( " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + ") > 0 " +
                " THEN 'Y' ELSE 'N' END  AS Assign "
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL
                + ", " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_NAME_COL + "  AS AssignCriteria "

                + " , " + " regMemGrp." + SQLiteHandler.GROUP_CODE_COL + " AS grpCode "
                + " , " + " grp." + SQLiteHandler.GROUP_NAME_COL + " AS grpName "
                + " , regMemGrp." + SQLiteHandler.ACTIVE_COL + " AS activeCode "


                + " , grpCat." + SQLiteHandler.GROUP_CAT_CODE_COL + " AS catCode "
                + " , grpCat." + SQLiteHandler.GROUP_CAT_NAME_COL + " AS catName "

                //", " +
               /* " ( SELECT " + SQLiteHandler.SERVICE_NAME_COL + " FROM " + SQLiteHandler.SERVICE_MASTER_TABLE + " WHERE " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + program + "' AND " +
                SQLiteHandler.SERVICE_CODE_COL + " = '" + service + "' GROUP BY " + SQLiteHandler.SERVICE_CODE_COL + " ) AS AssignCriteria " + // EXTRA FOR CRITERIA
*/

                + "FROM " + SQLiteHandler.REGISTRATION_TABLE +
                " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE +
                " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " " +
                " LEFT JOIN " + SQLiteHandler.RELATION_TABLE + " ON " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_CODE + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.RELATION_COL +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' " +
                " LEFT JOIN " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +

                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID +

                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' "
                + " LEFT JOIN " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " AS regMemGrp ON "

                + " regMemGrp." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND  regMemGrp." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND  regMemGrp." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND  regMemGrp." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND  regMemGrp." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND  regMemGrp." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  regMemGrp." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  regMemGrp." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND  regMemGrp." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND  regMemGrp." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'"
                + " AND  regMemGrp." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'"


                + " LEFT JOIN " + SQLiteHandler.COMMUNITY_GROUP_TABLE + " AS grp "
                + " ON "
                + " grp." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND grp." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND grp." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND grp." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'"
                + " AND grp. " + SQLiteHandler.GROUP_CODE_COL + " = " + " regMemGrp." + SQLiteHandler.GROUP_CODE_COL

                + " LEFT JOIN " + SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE + " AS grpCat "
                + " ON "
                + " grpCat." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND grpCat." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND grpCat." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND grpCat." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'"
                + " AND grpCat." + SQLiteHandler.GROUP_CAT_CODE_COL + " = " + " grp." + SQLiteHandler.GROUP_CAT_CODE_COL //+" regMemGrp." + SQLiteHandler.GROUP_CAT_CODE_COL
                + " WHERE " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " =  '" + cCode + "'" +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " =  '" + dstCode + "'" +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " =  '" + upCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " =  '" + unCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " =  '" + vCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " =  '" + hhid + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " =  '" + memberId + "' ";

        Log.d("MOR", sql);
        return sql;




/*
        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE + " ("


                + " grp. " + SQLiteHandler.COUNTRY_CODE_COL + " = "

                + " grp. " + SQLiteHandler.DONOR_CODE_COL + " = "
                + " grp. " + SQLiteHandler.AWARD_CODE_COL + " = "
                + " grp. " + SQLiteHandler.PROGRAM_CODE_COL + " = "
                + " grpCat." + SQLiteHandler.GROUP_CAT_CODE_COL + " = "
                + " grpCat." + SQLiteHandler.GROUP_CAT_NAME_COL + "  "
                + " , " + SQLiteHandler.GROUP_CAT_SHORT_NAME_COL + "  "


        return CREATE_TABLE_IF_NOT_EXISTS + SQLiteHandler.COMMUNITY_GROUP_TABLE + " ("


                + " grp." + SQLiteHandler.COUNTRY_CODE_COL + " = "
                + " grp." + SQLiteHandler.DONOR_CODE_COL + " = "
                + " grp." + SQLiteHandler.AWARD_CODE_COL + " = "
                + " grp." + SQLiteHandler.PROGRAM_CODE_COL + " = "
                + " grp. " + SQLiteHandler.GROUP_CODE_COL + " = "
                + " grp. " + SQLiteHandler.GROUP_NAME_COL + " VARCHAR(100) "
                + " grp. " + SQLiteHandler.GROUP_CAT_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " VARCHAR(4) "
                + " , " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " VARCHAR(4) "
                + " )";*/


        /** very good thinking
         * User can search by House hold & member Id also*/
        /*        SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " LIKE '%" + memberSearchId + "%' " +
                // +" GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL;*//*+
                " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " DESC "*;*/


    }

    /**
     * <p>This method return the sql String of Assign </p>
     *
     * @param cCode          Country Code
     * @param dstCode        District Code =Layer1Code
     * @param upCode         Upzella Code=Layer2Code
     * @param unCode         Union Code= Layer3Code
     * @param vCode          Village Code =Layer4Code
     * @param donorCode      Donor Code
     * @param awardCode      Award Code
     * @param prgCode        program Code
     * @param srvCode        service Code
     * @param memberSearchId search by member Id
     * @return sql String
     */

    public static String getAssignListViewSelectQuery(String cCode, String dstCode, String upCode, String unCode, String vCode, String donorCode, String awardCode, String prgCode, String srvCode, String memberSearchId) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL
                + ", " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_NAME + " AS HHRelation, " +
                " CASE WHEN LENGTH ( " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + ") > 0 " +
                " THEN 'Y' ELSE 'N' END  AS Assign "
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL
                + ", " +

                SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_NAME_COL + "  AS AssignCriteria " +
                //", " +
               /* " ( SELECT " + SQLiteHandler.SERVICE_NAME_COL + " FROM " + SQLiteHandler.SERVICE_MASTER_TABLE + " WHERE " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + program + "' AND " +
                SQLiteHandler.SERVICE_CODE_COL + " = '" + service + "' GROUP BY " + SQLiteHandler.SERVICE_CODE_COL + " ) AS AssignCriteria " + // EXTRA FOR CRITERIA
*/

                "FROM " + SQLiteHandler.REGISTRATION_TABLE +
                " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE +
                " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +
                " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " " +
                " LEFT JOIN " + SQLiteHandler.RELATION_TABLE + " ON " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_CODE + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.RELATION_COL +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' " +
                " LEFT JOIN " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +

                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID +

                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' " +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' " +


                " WHERE " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " =  '" + cCode + "'" +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " =  '" + dstCode + "'" +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " =  '" + upCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " =  '" + unCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " =  '" + vCode + "' "
                + " AND " +
                /** very good thinking
                 * User can search by House hold & member Id also*/
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " LIKE '%" + memberSearchId + "%' " +
                // +" GROUP BY " + REGISTRATION_MEMBER_TABLE + "." + HHID_COL;/*+
                " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " DESC ";


    }

    /**
     * @param cCode   Country Code
     * @param dstCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return sql String
     */

    public static String getMemberListView_searchBy_ID_sql(final String cCode, final String dstCode, final String upCode, final String unCode, final String vCode, final String memberSearchId) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS newId "

                + ", " + getMemName + " As memName "
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL
                + ", " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_NAME + " AS HHRelation "

                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + ", " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL
                + " , " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS layR4Name "
                + " , " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " As address "

//
                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " "
                + " LEFT JOIN " + SQLiteHandler.RELATION_TABLE
                + " ON " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_CODE + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.RELATION_COL
                + " left JOIN " + SQLiteHandler.VILLAGE_TABLE + " ON "

                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL

                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL


                + " WHERE " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " =  '" + cCode + "'"
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " =  '" + dstCode + "'"
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " =  '" + upCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " =  '" + unCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " =  '" + vCode + "' "
                + " AND "
                /** very good thinking
                 * User can search by House hold & member Id also*/
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " LIKE '%" + memberSearchId + "%' "

                // group by
                + " GROUP BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " "


                + " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " DESC "
                ;


    }


    /**
     * @param cCode   Country Code
     * @param dstCode District Code =Layer1Code
     * @param upCode  Upzella Code=Layer2Code
     * @param unCode  Union Code= Layer3Code
     * @param vCode   Village Code =Layer4Code
     * @return sql String
     */

    public static String getMemberListView_searchBy_Name_sql(final String cCode, final String dstCode, final String upCode, final String unCode, final String vCode, final String memberSearchName) {
        String getMemName;
        /**
         * 0004= Liberia's Country Code
         */
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }

        return "SELECT " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS newId "

                + " , " + getMemName + " As memName "
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL
                + " , " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_NAME + " AS HHRelation "

                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " , " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " , " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL
                + " , " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS layR4Name "
                + " , " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " As address "

//
                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " "
                + " LEFT JOIN " + SQLiteHandler.RELATION_TABLE
                + " ON " + SQLiteHandler.RELATION_TABLE + "." + SQLiteHandler.RELATION_CODE + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.RELATION_COL
                + " left JOIN " + SQLiteHandler.VILLAGE_TABLE + " ON "

                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL

                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND  " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL

                + " AND " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL


                + " WHERE " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " =  '" + cCode + "'"
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " =  '" + dstCode + "'"
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " =  '" + upCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " =  '" + unCode + "' "
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " =  '" + vCode + "' "
                + " AND "
                /** very good thinking
                 * User can search by House hold & member Name also*/
                + getMemName + " LIKE '%" + memberSearchName + "%' "

                + " ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " DESC "
                ;


    }


    public static String getMemberGraduationStatusList_sql(String cCode, String donorCode, String awardCode, String programCode, String srvCode, String memberId) {
        String getMemberName;
        if (cCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID +

                " , " + getMemberName + " AS memName " +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.GRD_DATE_COL +
                " , " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +
                " , ( SELECT " + SQLiteHandler.GRD_TITLE_COL + " FROM " + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE +
                " WHERE " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'"
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'"
                + " AND " + SQLiteHandler.GRD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.GRD_CODE_COL
                + " ) AS GRDTitle "
                + " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS nMemId "
//                "
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                + " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " ON "
                + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " INNER JOIN " + SQLiteHandler.VILLAGE_TABLE + " ON "
                + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " WHERE " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " LIKE  '%" + memberId + "%' "
                + " GROUP BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL
                + " ORDER BY  " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;
    }

    public static String getSingleLiberiaMemberDataQuery(String country, String district, String upazilla, String union, String village, String household, String member) {

        return "SELECT  "
                + SQLiteHandler.MEM_NAME_COL
                + " , " + SQLiteHandler.ENTRY_BY
                + " , " + SQLiteHandler.ENTRY_DATE

                //  + REG_DATE_COL + " , " if it need latter
                + " , " + SQLiteHandler.BIRTH_YEAR_COL
                + " , " + SQLiteHandler.MARITAL_STATUS_COL
                + " , " + SQLiteHandler.CONTACT_NO_COL
                + " , " + SQLiteHandler.MEMBER_OTHER_ID_COL
                + " , " + SQLiteHandler.MEM_NAME_FIRST_COL
                + " , " + SQLiteHandler.MEM_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.MEM_NAME_LAST_COL
                + " , " + SQLiteHandler.PHOTO_COL
                + " , " + SQLiteHandler.TYPE_ID_COL
                + " , " + SQLiteHandler.ID_NO_COL
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_FIRST_COL
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.V_BSC_MEM_1_NAME_LAST_COL
                + " , " + SQLiteHandler.V_BSC_MEM_1_TITLE_COL
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_FIRST_COL
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.V_BSC_MEM_2_NAME_LAST_COL
                + " , " + SQLiteHandler.V_BSC_MEM_2_TITLE_COL
                + " , " + SQLiteHandler.PROXY_DESIGNATION_COL
                + " , " + SQLiteHandler.PROXY_NAME_FIRST_COL
                + " , " + SQLiteHandler.PROXY_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.PROXY_NAME_LAST_COL
                + " , " + SQLiteHandler.PROXY_BIRTH_YEAR_COL
                + " , " + SQLiteHandler.PROXY_PHOTO_COL
                + " , " + SQLiteHandler.PROXY_TYPE_ID_COL
                + " , " + SQLiteHandler.PROXY_ID_NO_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_FIRST_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_NAME_LAST_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_1_TITLE_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_FIRST_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_MIDDLE_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_NAME_LAST_COL
                + " , " + SQLiteHandler.PROXY_BSC_MEM_2_TITLE_COL
                +

                "   FROM "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "  " +
                " WHERE " + SQLiteHandler.COUNTRY_CODE + "='" + country + "' " +
                " AND " + SQLiteHandler.DISTRICT_NAME_COL + "='" + district + "' " +
                " AND " + SQLiteHandler.UPZILLA_NAME_COL + "='" + upazilla + "' " +
                " AND " + SQLiteHandler.UNITE_NAME_COL + "='" + union + "' " +
                " AND " + SQLiteHandler.VILLAGE_NAME_COL + "='" + village + "' " +
                " AND " + SQLiteHandler.HHID_COL + "='" + household + "' " +
                " AND " + SQLiteHandler.HH_MEM_ID + "='" + member + "' ";
    }

    public static String getDeletedHouseHoldIDQuery(String personId) {
        return "SELECT "
                + SQLiteHandler.COUNTRY_CODE_COL + " , "
                + SQLiteHandler.DISTRICT_NAME_COL + " , "
                + SQLiteHandler.UPZILLA_NAME_COL + " , "
                + SQLiteHandler.UNITE_NAME_COL + " , "
                + SQLiteHandler.VILLAGE_NAME_COL + " , "
                + SQLiteHandler.PID_COL + "  "
                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " WHERE " + SQLiteHandler.PID_COL + " = '" + personId + "' ";
    }

    public static String getDeletedMemberIDQuery(int memberId) {
        return "SELECT "
                + SQLiteHandler.COUNTRY_CODE_COL + " , "
                + SQLiteHandler.DISTRICT_NAME_COL + " , "
                + SQLiteHandler.UPZILLA_NAME_COL + " , "
                + SQLiteHandler.UNITE_NAME_COL + " , "
                + SQLiteHandler.VILLAGE_NAME_COL + " , "
                + SQLiteHandler.HHID_COL + " , "
                + SQLiteHandler.HH_MEM_ID + "  "
                + " FROM " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " WHERE " + SQLiteHandler.ID_COL + " = '" + memberId + "' ";
    }

    public static String getSavePermissionSelectQuery(String tableName, String columnName, String country, String district, String upzella, String unit, String village) {
        return " SELECT " + columnName +
                // " SELECT "+SQLiteHandler.BTN_SAVE_COL+
                //" FROM "+SQLiteHandler.STAFF_GEO_INFO_ACCESS_TABLE+
                " FROM " + tableName +
                " WHERE " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + country + "' " +
                " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + district + "' " +
                " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzella + "' " +
                " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unit + "' " +
                " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + village + "' ";
    }

    public static String getFFAMemberListForServiceSelectQuery(String country, String donor,
                                                               String award, String program, String service,
                                                               String opCode, String opMonthCode,
                                                               String memberId, String groupCode, String distFlag) {
        String getMemberName;
        if (country.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT  " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + "," + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL
                + "," + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + ", " + getMemberName + " AS memName "
                + " ," + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL
                + "," + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + "," + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + "," +

                "( SELECT  COUNT (*) " +
                " FROM " + SQLiteHandler.SERVICE_TABLE
                + " WHERE "
                + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "
                + " ) AS SrvRecieved "

                + " , " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " || \"\" || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " || \"\" || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS NewID "

                /** Add new string  id show ..*/


                + " , IFNULL(" + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.WORK_DAY_COL + ",'0') AS WD"

                + " FROM " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE +
                " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL

                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                    /*
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+LAY_R4_LIST_CODE_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+VILLAGE_NAME_COL+" AND "+
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" AND "+
                    */

                + " INNER JOIN " +
                SQLiteHandler.REGISTRATION_TABLE
                + " ON " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL

                + " INNER JOIN " +
                SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " ON " +

                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.GROUP_CODE_COL + " = '" + groupCode + "'"
                + " LEFT JOIN "
                + SQLiteHandler.SERVICE_TABLE + " ON "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " WHERE " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + country + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donor + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + award + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + program + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + service + "' AND " +
                /**  id Searching  with 15 digit*/
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " LIKE '%" + memberId + "%' " +
                " GROUP BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL +
                //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+
                "  ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " DESC, RegMembers.RegisterID DESC";
        //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+ "  ORDER BY "+ REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" DESC, RegMembers.RegisterID DESC";


    }

    /**
     * this too much wonge
     *
     * @param country     Country Code
     * @param donor       donor Code
     * @param award       award Code
     * @param program     program Code
     * @param srvCode     Service Code
     * @param opCode      operation Code
     * @param opMonthCode operation Month Code
     * @param memId       member Id
     * @param grpCode     Group Code
     * @return Sql String of member list for service
     */


    public static String getMemberListForServiceSelectQuery(String country, String donor,
                                                            String award, String program, String srvCode,
                                                            String opCode, String opMonthCode,
                                                            String memId, String grpCode, String distFlag) {
        String getMemberName;
        if (country.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT  " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + "," +
                SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + "," +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + ", " +
                getMemberName + " AS memName ," +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.SEX_COL + "," +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_AGE + "," +

                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + "," +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + "," +

                "( SELECT  COUNT (*) " +
                " FROM " + SQLiteHandler.SERVICE_TABLE +
                " WHERE "
                + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' "
                + " AND " + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "

                + " ) AS SrvRecieved ," +


                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS NewID " +
                /** Add new string  id show ..*/

                "FROM " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE +
                " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                +//" AND "+
                    /*
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+LAY_R4_LIST_CODE_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+VILLAGE_NAME_COL+" AND "+
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+" = "+REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" AND "+
                    REG_N_ASSIGN_PROG_SRV_TABLE+"."+HH_MEM_ID+" = "+REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+*/

                " INNER JOIN " +
                SQLiteHandler.REGISTRATION_TABLE + " ON " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL
               /*     REGISTRATION_MEMBER_TABLE+"."+VILLAGE_NAME_COL+" = "+REGISTRATION_TABLE+"."+VILLAGE_NAME_COL+" AND "+
                    REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" = "+REGISTRATION_TABLE+"."+PID_COL +*/

             /* for test */ + " INNER JOIN " +
                SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " ON " +

                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + "." + SQLiteHandler.GROUP_CODE_COL + " = '" + grpCode + "'"

                + " WHERE " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + country + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donor + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + award + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + program + "'  AND " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' AND " +
                /**  id Searching  with 15 digit*/
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || " +
                SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " LIKE '%" + memId + "%' " +
                " GROUP BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL +
                //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+
                "  ORDER BY " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " DESC, RegMembers.RegisterID DESC";
        //  " , "+ REGISTRATION_MEMBER_TABLE+"."+HH_MEM_ID+ "  ORDER BY "+ REGISTRATION_MEMBER_TABLE+"."+HHID_COL+" DESC, RegMembers.RegisterID DESC";


    }

    public static String getAddressQuery(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode) {
        return " WHERE "
                + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' AND "
                + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'";

        /**
         * " SELECT "+SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL+" , "+ SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
         +" FROM "+SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
         */
    }

    /**
     * To show distribution table
     */

    public static String getDistributionGridShowData(String countryCode, String donorCode, String awardCode, String progCode, String serviceOpMonthCode, String fdpCode, String memberId) {
        return "SELECT "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " AS country , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " AS donor , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " AS award , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " AS district , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " AS upzella , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " AS unite, "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " AS village, "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " AS program , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " AS service , "
                + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL + " AS srvName , "

                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " AS HHID , "

                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS MEMBERID , "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + " AS HhName , "
                + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL + " AS MemName , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " , "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CENTER_CODE_COL + " ,  "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.WORK_DAY_COL + " AS wd  "

                // can n't sub query or join because of where condition
                // + "  "+getDistributionStatusFromDistributionTableQuery()+ " )   AS DistStatus "
                // + " CASE WHEN " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DISTRIBUTION_STATUS_COL + " IS NULL THEN '-' ELSE 'R'  AS DistStatus"

                //     --,COUNT(Service.ServiceSL)

                + " FROM " + SQLiteHandler.SERVICE_TABLE + "  INNER JOIN " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + " ON  "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " INNER JOIN " + SQLiteHandler.REGISTRATION_TABLE + " ON "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " = "

                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL
                + " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " ON "
                + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " INNER JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE
                + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
               /* + " LEFT JOIN " + SQLiteHandler.DISTRIBUTION_TABLE + " ON "
                + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL    + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL           + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL            + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL            + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL     + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL     + " = " +SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL    + " = '" +distributionOpMonthCode +"' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.FDP_CODE_COL         + " = '" + fdpCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL + " = " +
*/
                + " WHERE (" + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.FOOD_FLAG + " = '1' OR "
                + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.NON_FOOD_FLAG + " = '1' OR "
                + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.CASH_FLAG + " = '1' OR "
                + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.VOUCHER_FLAG + " = '1'" +

                " ) "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + serviceOpMonthCode + "' "

                + " AND (" + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DISTRIBUTION_STATUS_COL + " IN ( 'S', 'P' )) "
                + " AND  " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DIST_DATE_COL + " = 'null' "

                + " AND (" + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CENTER_CODE_COL + " IN ( SELECT "
                + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.SERVICE_CENTER_CODE_COL + " FROM "
                + SQLiteHandler.SERVICE_CENTER_TABLE + " WHERE " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' ))"
                /**  id Searching  with 15 digit*/
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || \"\" || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " || \"\" || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID
                + " LIKE '%" + memberId + "%' "

                ;


    }

    public static String getDistributionStatusFromDistributionTableQuery(String countryCode, String donorCode, String awardCode, String districtCode, String upzillaCode, String uniteCode, String villageCode, String programCode, String srviceCode, String distMonthCode, String fdpCode, final String distFlag, String id) {
        String sql = "SELECT CASE WHEN " + SQLiteHandler.DISTRIBUTION_STATUS_COL + " IS NULL THEN '-' ELSE " + SQLiteHandler.DISTRIBUTION_STATUS_COL + " END   AS " + SQLiteHandler.DISTRIBUTION_STATUS_COL
                + " FROM " + SQLiteHandler.DISTRIBUTION_TABLE
                + " WHERE "
                + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzillaCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + uniteCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srviceCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + distMonthCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL + " = '" + id + "' ";
        Log.d("All_1", "sql:" + sql);
        return sql;
    }

    public static String getSingleHouseHoldDataForLiberiaQuery(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId) {
        return " SELECT " +
               /* SQLiteHandler.COUNTRY_CODE_COL + " , " +
                SQLiteHandler.DISTRICT_NAME_COL + " , " +
                SQLiteHandler.UPZILLA_NAME_COL + " , " +
                SQLiteHandler.UNITE_NAME_COL + " , " +
                SQLiteHandler.VILLAGE_NAME_COL + " , " +
                SQLiteHandler.PID_COL + " , " +*/
                SQLiteHandler.REG_DATE_COL + " , " +
                SQLiteHandler.PNAME_COL + " , " +
                SQLiteHandler.HOUSE_HOLD_TYPE_CODE_COL + " , " +// also retrives House hold catagories string
                SQLiteHandler.HOUSE_HOLD_CATEGORY_TABLE + "." + SQLiteHandler.CATEGORY_NAME_COL + " AS HHType, " +// also retrives House hold catagories string
                SQLiteHandler.LT2YRS_M_COL + " , " +
                SQLiteHandler.LT2YRS_F_COL + " , " +
                SQLiteHandler.M_2TO5YRS_COL + " , " +
                SQLiteHandler.F_2TO5YRS_COL + " , " +
                SQLiteHandler.M_6TO12YRS_COL + " , " +
                SQLiteHandler.F_6TO12YRS_COL + " , " +
                SQLiteHandler.M_13TO17YRS_COL + " , " +
                SQLiteHandler.F_13TO17YRS_COL + " , " +
                SQLiteHandler.ORPHN_LT18YRS_M_COL + " , " +
                SQLiteHandler.ORPHN_LT18YRS_F_COL + " , " +
                SQLiteHandler.ADLT_18TO59_M_COL + " , " +
                SQLiteHandler.ADLT_18TO59_F_COL + " , " +
                SQLiteHandler.ELD_60P_M_COL + " , " +
                SQLiteHandler.ELD_60P_F_COL + " , " +
                SQLiteHandler.PLW_NO_COL + " , " +
                SQLiteHandler.CHRO_ILL_NO_COL + " , " +

                SQLiteHandler.DECEASED_CONTRACT_EBOLA_COL + " , " +
                SQLiteHandler.EXTRA_CHILD_CAUSE_EBOLA_COL + " , " +
                SQLiteHandler.EXTRA_ELDERLY_CAUSE_EBOLA_COL + " , " +
                SQLiteHandler.EXTRA_CHRONICALLY_ILL_CAUSE_EBOLA_COL + " , " +


                /////////////////////
                SQLiteHandler.BRF_COUNT_CATTLE_COL + " , " +
                SQLiteHandler.BRF_VALUE_CATTLE_COL + " , " +
                SQLiteHandler.AFT_COUNT_CATTLE_COL + " , " +
                SQLiteHandler.AFT_VALUE_CATTLE_COL + " , " +
                SQLiteHandler.BRF_COUNT_SGOATS_COL + " , " +
                SQLiteHandler.BRF_VALUE_SGOATS_COL + " , " +
                SQLiteHandler.AFT_COUNT_SGOATS_COL + " , " +
                SQLiteHandler.AFT_VALUE_SGOATS_COL + " , " +
                SQLiteHandler.BRF_COUNT_POULTRY_COL + " , " +
                SQLiteHandler.BRF_VALUE_POULTRY_COL + " , " +
                SQLiteHandler.AFT_COUNT_POULTRY_COL + " , " +
                SQLiteHandler.AFT_VALUE_POULTRY_COL + " , " +
                SQLiteHandler.BRF_COUNT_OTHER_COL + " , " +
                SQLiteHandler.BRF_VALUE_OTHER_COL + " , " +
                SQLiteHandler.AFT_COUNT_OTHER_COL + " , " +
                SQLiteHandler.AFT_VALUE_OTHER_COL + " , " +
                SQLiteHandler.BRF_ACRE_CULTIVABLE_COL + " , " +
                SQLiteHandler.BRF_VALUE_CULTIVABLE_COL + " , " +
                SQLiteHandler.AFT_ACRE_CULTIVABLE_COL + " , " +
                SQLiteHandler.AFT_VALUE_CULTIVABLE_COL + " , " +
                SQLiteHandler.BRF_ACRE_NON_CULTIVABLE_COL + " , " +
                SQLiteHandler.BRF_VAL_NON_CULTIVABLE_COL + " , " +
                SQLiteHandler.AFT_ACRE_NON_CULTIVABLE + " , " +
                SQLiteHandler.AFT_VAL_NON_CULTIVABLE + " , " +
                SQLiteHandler.BRF_ACRE_ORCHARDS + " , " +
                SQLiteHandler.BRF_VAL_ORCHARDS + " , " +
                SQLiteHandler.AFT_ACRE_ORCHARDS + " , " +
                SQLiteHandler.AFT_VAL_ORCHARDS + " , " +
                SQLiteHandler.BRF_VAL_CROP + " , " +
                SQLiteHandler.AFT_VAL_CROP + " , " +
                SQLiteHandler.BRF_VAL_LIVESTOCK + " , " +
                SQLiteHandler.AFT_VAL_LIVESTOCK + " , " +
                SQLiteHandler.BRF_VAL_SMALL_BUSINESS + " , " +
                SQLiteHandler.AFT_VAL_SMALL_BUSINESS + " , " +
                SQLiteHandler.BRF_VAL_EMPLOYMENT + " , " +
                SQLiteHandler.AFT_VAL_EMPLOYMENT + " , " +
                SQLiteHandler.BRF_VAL_REMITTANCES + " , " +
                SQLiteHandler.AFT_VAL_REMITTANCES + " , " +
                SQLiteHandler.BRF_CNT_WAGEENR + " , " +
                SQLiteHandler.AFT_CNT_WAGEENR +
                " FROM  " + SQLiteHandler.REGISTRATION_TABLE +
                " LEFT JOIN " + SQLiteHandler.HOUSE_HOLD_CATEGORY_TABLE
                + " ON " + SQLiteHandler.HOUSE_HOLD_CATEGORY_TABLE + "." + SQLiteHandler.CATEGORY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.HOUSE_HOLD_TYPE_CODE_COL + " " +
                " WHERE " + SQLiteHandler.REGISTRATION_TABLE + "." +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' " +
                " AND " + SQLiteHandler.DISTRICT_NAME_COL + " = '" + districtCode + "' " +
                " AND " + SQLiteHandler.UPZILLA_NAME_COL + " = '" + upzellaCode + "' " +
                " AND " + SQLiteHandler.UNITE_NAME_COL + " = '" + unitCode + "'" +
                " AND " + SQLiteHandler.VILLAGE_NAME_COL + " = '" + villageCode + "' " +
                " AND " + SQLiteHandler.PID_COL + " = '" + houseHoldId + "' ";
    }

    public static String getVillageNameWHERECondition(String countryCode, String districtCode, String upazillaCode, String unitCode, String villageCode) {
        return " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' AND "
                + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' AND "
                + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upazillaCode + "' AND "
                + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' AND "
                + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'  ";
    }

    public static String getProgramsNames_WHERE_Condition(String awardCode, String donorCode) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'";

    }

    /**
     * getDistProgramsNames_WHERE_Condition() is For Distribution table
     */
    public static String getDistProgramsNames_WHERE_Condition(String awardCode, String donorCode, String columnName) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + columnName + "='" + 1 + "'";

    }

    public static String getServiceMonths_WHERE_Service_Open_Condition(String countryCode) {
        return " WHERE " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + SQLiteHandler.STATUS + " = '" + "A" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' ";
    }

    public static String getServiceMonths_WHERE_Service_Close_Condition(String countryCode) {
        return " WHERE " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + SQLiteHandler.STATUS + " = '" + "C" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' "
                + " ORDER BY OpMonthID   DESC   LIMIT 1"
                ;


    }


    public static String getDistributionMonths_WHERE_Condition(String countryCode) {
        return " WHERE " +
                SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + SQLiteHandler.STATUS + " = '" + "A" + "' "
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '3' "
                + " ORDER BY OpMonthID   DESC "
                + "        LIMIT 1";
    }

    public static String getCriteriaNames_WHERE_Condition(String awardCode, String donorCode, String programCode) {
        return " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + "='" + programCode + "'";

    }

    public static String getFDPNames_Where_Condition(String idCountry, String laryR2) {
        return " WHERE " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.COUNTRY_CODE + " = '" + idCountry + "'"
                + " AND " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + laryR2 + "' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_NEW_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_SAVE_COL + " = '1' "
                + " AND " + SQLiteHandler.STAFF_FDP_ACCESS_TABLE + "." + SQLiteHandler.BTN_DEL_COL + " = '1' "
                + "  GROUP BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL
                + "  ORDER BY " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL;
    }

    public static String checkDataExitsQueryInCT_TableAssignForLiberia(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_CT_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";

    }
   /* // havet use it

    public static String checkAssignedCriteriaQueryInCT_TableForLiberia(String columnName, String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT " + columnName + " FROM " + SQLiteHandler.REG_N_CT_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";

    }*/


    public static String getLastServiceDateQuery(ServiceDataModel service) {
        return " SELECT " + SQLiteHandler.SERVICE_DT_COL
                + " FROM " + SQLiteHandler.SERVICE_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE + " = '" + service.getC_code() + "'"
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + service.getDonor_code() + "'"
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + service.getAward_code() + "'"
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + service.getDistrictCode() + "'"
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + service.getUpazillaCode() + "'"
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + service.getUnitCode() + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + service.getVillageCode() + "'"
                + " AND " + SQLiteHandler.HHID_COL + " = '" + service.getHHID() + "'"
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + service.getMemberId() + "'"
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + service.getProgram_code() + "'"
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + service.getService_code() + "'"
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '" + service.getOpCode() + "'"
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + service.getOpMontheCode() + "'" +
                "ORDER BY " + SQLiteHandler.SERVICE_SL_COL + " desc limit 1";
    }

    // todo: Analysis the Query to replace the Sub Query
    public static String getAssignCriteriaListSelectQuery(String countryCode, String donorCode, String awardCode, String programCode, String districtCode, String upzellaCode, String unitCode, String villageCode) {
        return "SELECT " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL + " AS Criteria , "
                + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " || '' || " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " AS IdCriteria ,  "
                + "( SELECT COUNT (*) FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE "
                + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' ) AS AssignCount "
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                //" JOIN "+SERVICE_CENTER_TABLE +" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+COUNTRY_CODE_COL +" = "+ SERVICE_CENTER_TABLE+"."+COUNTRY_CODE_COL+
                + " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON "
                + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " WHERE " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "' "
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'"
                + " GROUP BY " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL;

    }

    public static String doesMemberAssignedInDifferentServiceQuery(RegNAssgProgSrv regData) {
        return "SELECT " + SQLiteHandler.HH_MEM_ID
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + regData.getCountryCode() + "'"
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + regData.getDistrictCode() + "'"
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + regData.getUpazillaCode() + "'"
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + regData.getUnitCode() + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + regData.getVillageCode() + "'"
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + regData.getDonorCode() + "'"
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + regData.getAwardCode() + "'"
                + " AND " + SQLiteHandler.HHID_COL + " = '" + regData.getHhId() + "'"
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + regData.getMemberId() + "'"
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + regData.getProgramCode() + "'"
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " != '" + regData.getServiceCode() + "'"
                + " AND (" + SQLiteHandler.GRD_CODE_COL + " = ( " + getGraduationDefaultActiveReason_Select_Query(regData.getProgramCode(), regData.getServiceCode()) + " ) )";
    }

    public static String getGraduationDefaultActiveReason_Select_Query(String prrogramCode, String serviceCode) {
        return "SELECT " + SQLiteHandler.GRD_CODE_COL + " FROM " + SQLiteHandler.REG_N_LUP_GRADUATION_TABLE +
                " WHERE  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prrogramCode + "'"
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + serviceCode + "'"
                + " AND " + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " = '" + YES + "'  ";
    }

    public static String getMemberDataFrom_RegNAssProgSrv_Query(GraduationGridDataModel member) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE +
                " WHERE  " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + member.getCountryCode() + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + member.getDistrictCode() + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + member.getUpazillaCode() + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + member.getUnitCode() + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + member.getVillageCode() + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + member.getHh_id() + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + member.getMember_Id() + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + member.getDonor_code() + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + member.getAward_code() + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + member.getProgram_code() + "' "
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + member.getService_code() + "'  ";
    }

    /**
     * AGR Table Check
     */
   /* public static String checkDataExitsQueryInAGR_TableAssignForMalwai(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_AGR_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
    }*/
    public static String checkDataExitsQueryInRegN_ARG_TableSQL(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_AGR_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
    }


    public static String checkDataExitsQueryInRegN_FFA_TableSQL(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return "SELECT * FROM " + SQLiteHandler.REG_N_FFA_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
    }
// havet use it


    public static String getAssignDataIfExitsInRegNFFA_table_sql(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID) {
        return " SELECT " + SQLiteHandler.CHILD_HEADED_COL + " , "
                + SQLiteHandler.ELDERLY_HEADED_COL + " , "
                + SQLiteHandler.CHRONICALLY_ILL_COL + " , "

                + SQLiteHandler.FEMALE_HEADED_COL + " , "

                + SQLiteHandler.CROP_FAILURE_COL + " , "
                + SQLiteHandler.CHILDREN_REC_SUPP_FEED_N_COL + " , "

                + SQLiteHandler.WILLINGNESS_COL + "  "

                + " FROM " + SQLiteHandler.REG_N_FFA_TABLE

                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
    }

    /**
     * AGR Table Check
     */
    public static String checkDataExitsQueryInAGR_TableAssignForMalwai(String countryCode, String districtCode, String upzellaCode, String unitCode, String villageCode, String houseHoldId, String memberID, boolean impelmetedinMain) {
        String sql = " SELECT " + SQLiteHandler.ELDERLY_YN_COL + " , "
                + SQLiteHandler.LAND_SIZE_COL + " , "
                + SQLiteHandler.DEPEND_ON_GANYU_COL + " , "
                + SQLiteHandler.WILLINGNESS_COL + " , "
                + SQLiteHandler.WINTER_CULTIVATION_COL + " , "
                + SQLiteHandler.VULNERABLE_HH_COL + " , "
                + SQLiteHandler.PLANTING_VALUE_CHAIN_CROP_COL + " , "
                + SQLiteHandler.REG_N_DAT_COL + " , "
                + SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE + "." + SQLiteHandler.LUP_OPTION_NAME_COL + " AS vcCropStr ,"

                + SQLiteHandler.AG_INVC_COL + " , "
                + SQLiteHandler.AG_NASFAM_COL + " , "
                + SQLiteHandler.AG_CU_COL + ", "
                + SQLiteHandler.AG_OTHER_COL + " , "
                + SQLiteHandler.AG_L_S_GOAT_COL + " , "
                + SQLiteHandler.AG_L_S_CHICKEN_COL + " , "
                + SQLiteHandler.AG_L_S_PIGION_COL + " , "
                + SQLiteHandler.AG_L_S_OTHER_COL + "  "

                + " FROM " + SQLiteHandler.REG_N_AGR_TABLE
                + " LEFT JOIN " + SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE
                + " ON " + SQLiteHandler.REG_N_AGR_TABLE + " . " + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.LUP_SRV_OPTION_LIST_TABLE + " . " + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.PLANTING_VALUE_CHAIN_CROP_COL + " = " + SQLiteHandler.LUP_OPTION_CODE_COL
                + " WHERE " + SQLiteHandler.REG_N_AGR_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + countryCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unitCode + "'"
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + houseHoldId + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memberID + "' ";
        Log.d("MOR1", sql);
        return sql;
    }

    public static String get_RegNAssProgSrvRegistrationDateRangeSelectQuery(String cCode) {
        return "SELECT " + SQLiteHandler.USA_START_DATE + " , " + SQLiteHandler.USA_END_DATE + " FROM " + SQLiteHandler.OP_MONTH_TABLE +
                " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '" + SQLiteHandler.REGISTRATION_OP_CODE + "' " +
                " AND " + SQLiteHandler.STATUS + " = '" + SQLiteHandler.ACTIVE + "' ";

    }

    public static String get_DetailsAssignedMemberSummarySelectQuery(String countryCode, String districtCode, String upzellaCode, String unionCode, String villageCode, String donorCode, String awardCord, String programCode, String srvCode) {


        String getMemberName;
        if (countryCode.equals("0004")) {
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;


        return "SELECT " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL
                + " || '' || " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS NewID "
                + ", " + getMemberName + "  AS memberName"
                + " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.REG_N_DAT_COL + " AS regDate"
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + " JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " " +
                      /*   " JOIN "+SERVICE_MASTER_TABLE+" ON "+REG_N_ASSIGN_PROG_SRV_TABLE+"."+PROGRAM_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+PROGRAM_CODE_COL+
                 " AND " +REG_N_ASSIGN_PROG_SRV_TABLE+"."+SERVICE_CODE_COL+" = "+SERVICE_MASTER_TABLE+"."+SERVICE_CODE_COL+*/
                " WHERE " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "= '" + countryCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + districtCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upzellaCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unionCode + "'" +
                " AND " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villageCode + "'" +
                //  +
                // " GROUP BY "+ REG_N_ASSIGN_PROG_SRV_TABLE+"."+HHID_COL+
                " ORDER BY " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HHID_COL +
                " , " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + "." + SQLiteHandler.HH_MEM_ID + "  ASC ";


    }

    public static String get_ProgramMultipleSrv_SelectQuery(String donorCode, String awardCode, String programCode) {

        return "SELECT " + SQLiteHandler.MULTIPLE_SERVICE_FLAG_COL + " FROM " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE
                + " WHERE " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' ";
    }

    public static String get_SrvCriteriaList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode, String distFlag) {
        return " SELECT " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL + " AS Criteria , " +
                SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " || '' || " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " AS IdCriteria ,  " +
                "( SELECT COUNT (*) FROM " + SQLiteHandler.SERVICE_TABLE +
                " WHERE " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_STATUS_COL + " = 'O'"

                + " ) AS Count " +

                " FROM " + SQLiteHandler.SERVICE_TABLE + " JOIN " + SQLiteHandler.SERVICE_CENTER_TABLE
                + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +

                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " " +

                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " AND " +

                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL +

                " WHERE  " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "
                + " GROUP BY " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL;
    }


    public static String get_DistCriteriaList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode, String distFlag) {


        return " SELECT " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL + " AS Criteria , " +
                SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " || '' || " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " AS IdCriteria"
       /* ,( Select Count(*) From Service  WHERE Service.ProgramCode = ServiceMaster.ProgramCode
        AND Service.ServiceCode = ServiceMaster.ServiceCode
        AND Service.ServiceStatus='C'
        and Service.DistributionStatus in('S','P') ) as plane*/

                + " ,  ( SELECT COUNT (*) FROM  " + SQLiteHandler.SERVICE_TABLE
                + " WHERE " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL

                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_STATUS_COL + " = 'C' "
                + " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DISTRIBUTION_STATUS_COL + " IN ('S','P') ) AS plan "


                + " ,  ( SELECT COUNT (*) FROM " + SQLiteHandler.DISTRIBUTION_TABLE
                + " WHERE " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DISTRIBUTION_STATUS_COL + " = 'R'"

                + " ) AS receive " +

                " FROM " + SQLiteHandler.DISTRIBUTION_TABLE
                //+ " JOIN " + SQLiteHandler.SERVICE_CENTER_TABLE
                // + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +

                + " JOIN " + SQLiteHandler.OP_MONTH_TABLE
                + " ON " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " "
                + " AND " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = '3' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL

                + " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE
                + " ON " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL +

                " WHERE  " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode +
                "' AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode +
                "' AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'  " +
                " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "'  " +
                " GROUP BY " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_SHORT_NAME_COL;
    }


    public static String getSrvExtendedItemSummaryList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode) {


        return " SELECT " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_NAME_COL

                + " || '-' || " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.UNITE_MEAS_COL
                + " ||' '||  " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEASE_TITLE_COL + " AS item , "
                + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " AS voucherID ,  " +
                " sum( " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_UNIT_COL + ") AS unitCount " +
                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.CATEGORY_CODE_COL + " || " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SQLiteHandler.SERVICE_EXTENDED_TABLE
                + " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL;

    }

    public static String getDistExtendedItemSummaryList_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String programCode) {
        return " SELECT " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_NAME_COL

                + " || '-' || " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.UNITE_MEAS_COL
                + " ||' '||  " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEASE_TITLE_COL + " AS item , "
                + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " AS voucherID ,  " +
                " sum( " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_UNIT_COL + ") AS unitCount " +
                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.CATEGORY_CODE_COL + " || " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE
                + " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'"
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY "
                + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL;


    }

    public static String getTotalServiceAttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag) {
        String getMemName;
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }


        return " SELECT " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || '' || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || '' || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || '' || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || '' || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " || '' || " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS NewID , " +
                /** HERE COUNT THE SERVICE */


                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_SL_COL
                + " , " + getMemName + " AS memberName "
                + " , " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_STATUS_COL + " AS status " +
                //   SERVICE_TABLE + "." + SERVICE_DT_COL +
                " FROM " + SQLiteHandler.SERVICE_TABLE +
                " JOIN " + SQLiteHandler.SERVICE_CENTER_TABLE + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +

                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "  " +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + "  " +
                " INNER JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE +
                " ON " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL +
                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL +


                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL +

                " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID +

                " WHERE " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'" +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "'  " +
                " AND " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_STATUS_COL + " = 'O'  " +
                " GROUP BY " + SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HHID_COL + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.HH_MEM_ID + " , " +
                SQLiteHandler.SERVICE_TABLE + "." + SQLiteHandler.SERVICE_SL_COL + "  ";

    }

    public static String getTotalDistributionAttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode, String srvCode, String distFlag) {
        String getMemName;
        if (cCode.equals("0004")) {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_FIRST_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_MIDDLE_COL + " || ' ' || " +
                    SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else {
            getMemName = SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.MEM_NAME_COL;

        }
/**
 * todo: check status
 */

        return " SELECT " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL + " AS NewID "
                + " , " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DISTRIBUTION_STATUS_COL + " AS status "
                + " , CASE   WHEN length(" + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL + ") >=15 "
                + " THEN " + getMemName
                + " ELSE " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + " END AS rptName "

                + " FROM " + SQLiteHandler.DISTRIBUTION_TABLE
                + " JOIN " + SQLiteHandler.OP_MONTH_TABLE
                + " ON " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = '3' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HHID_COL
                + " || " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + "." + SQLiteHandler.HH_MEM_ID + " = " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL


                + " LEFT JOIN " + SQLiteHandler.REGISTRATION_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                + " || " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL
                + " = " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL

                + " WHERE " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "' "
                + " AND " + SQLiteHandler.DISTRIBUTION_TABLE + "." + SQLiteHandler.DIST_FLAG_COL + " = '" + distFlag + "' "
                ;
    }

    public static String getTotal_Service_Itemize_AttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec) {
        return " SELECT " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || '' || "
                + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || '' || "
                + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " || '' || "
                + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || '' || "
                + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.HHID_COL + " || '' || "
                + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.HH_MEM_ID + " AS NewID , "
                +
                /** HERE COUNT THE SERVICE */
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_UNIT_COL + "  "
                + " ," + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.UNITE_COST_COL + " AS cost "
                + " FROM " + SQLiteHandler.SERVICE_EXTENDED_TABLE +
                " JOIN " + SQLiteHandler.SERVICE_CENTER_TABLE + " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OPERATION_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "  " +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + "  " +
                " INNER JOIN " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND  " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL +
                " AND  " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL +
                " AND  " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND  " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL +
                " AND  " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL +
                " WHERE " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'" +
                //** use it latter    " AND " + SERVICE_EXTENDED_TABLE + "." + SERVICE_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + vouItSpec + "'  " +
                " GROUP BY " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " , " +
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.HHID_COL + " , " +
                SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.HH_MEM_ID;
//                + " , " +               SERVICE_EXTENDED_TABLE + "." + SERVICE_SL_COL + "  ";

    }

    public static String getTotal_Distribution_Itemize_AttendanceSummary_SelectQuery(String cCode, String donorCode, String awardCord, String opMCode, String prgCode/*, String srvCode*/, String vouItSpec) {
        return " SELECT "
                + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL + " AS NewID , " +
                /** HERE Unit  */
                SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_UNIT_COL + "  " +
                " ," + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.UNITE_COST_COL + " AS cost " +
                " FROM " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE +
                " JOIN " + SQLiteHandler.SERVICE_CENTER_TABLE + " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " JOIN " + SQLiteHandler.OP_MONTH_TABLE + " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                //" AND " + SERVICE_EXTENDED_TABLE + "." + OPERATION_CODE_COL + " = " + OP_MONTH_TABLE + "." + OPERATION_CODE_COL +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.DONOR_CODE_COL +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.OP_MONTH_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "  " +
                " JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.SERVICE_MASTER_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + "  " +
                " INNER JOIN " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND  " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL +
                " AND  " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL +
                " AND  " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND  " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL +
                " AND  " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL +
                " WHERE " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCord + "'" +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "'" +
                //** use it latter    " AND " + SERVICE_EXTENDED_TABLE + "." + SERVICE_CODE_COL + " = '" + srvCode + "'" +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  " +
                " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = '" + vouItSpec + "'  " +
                " GROUP BY " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.MEM_ID_15_D_COL;
//


    }

    public static String getServiceDelete_WhereCondition(String cCode, String donorCode, String awardCode, String distId, String upId, String unId, String villId, String hhId, String memId, String progCode, String srvCode, String opMonthCode, String slNo) {
        return SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + distId + "' " +
                " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upId + "' " +
                " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unId + "' " +
                " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + villId + "' " +
                " AND " + SQLiteHandler.HHID_COL + " = '" + hhId + "' " +
                " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memId + "' " +
                " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' " +
                " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "' " +
                " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2' " +
                " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "' " +
                " AND " + SQLiteHandler.SERVICE_SL_COL + " = '" + slNo + "' ";
    }

    public static String getRegisteredData_ifVillageExt_SelectQuery(String ext_village, String hhId) {


        String sql = "SELECT "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " AS R_District, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " AS R_Upazilla, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " AS R_Union, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS R_Village, "
                + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_NAME + ", "
                + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + ", "
                + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + ","
                + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + ", "
                + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + ","
                + "(" + " CASE WHEN " + SQLiteHandler.SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LATITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LONGITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + " " + SQLiteHandler.M_STATUS + " AS MStatus " + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_DATE + " , "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.W_RANK_COL + " as wRank ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.M_STATUS + " as status "//26
                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.DISTRICT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UPAZILLA_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UNIT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.VILLAGE_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +// + LAY_R4_LIST_CODE_COL + "='" + ext_village +" AND "+
                SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = '" + ext_village + "' "
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " LIKE '%" + hhId + "%' "
                + " GROUP BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + " DESC";
        Log.d("MAR", sql);
        return sql;

    }


    public static String getSingleRegisteredData_sql(String cCode, String layR1Code, String layR2Code, String layR3Code, String layR4Code, final String hhId) {


        return "SELECT "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " AS R_District, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " AS R_Upazilla, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " AS R_Union, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS R_Village, "
                + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_NAME + ", "
                + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + ", "
                + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + ","
                + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + ", "
                + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + ","
                + "(" + " CASE WHEN " + SQLiteHandler.SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LATITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LONGITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + "(" + " CASE WHEN " + SQLiteHandler.M_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS MStatus" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_DATE + " , "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode "//26
                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.DISTRICT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UPAZILLA_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UNIT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.VILLAGE_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +
                SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' AND "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " = '" + layR1Code + "' AND "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " = '" + layR2Code + "' AND "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " = '" + layR3Code + "' AND "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = '" + layR4Code + "'   "
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + " = '" + hhId + "' ";
        // + " GROUP BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
        //   + " ORDER BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + " DESC";
        //   Log.d("MAR",sql);
        // return sql;

    }


    public static String getRegisteredData_ifVillageExt_SearchByName_sql(String ext_village, String hhName) {


        return "SELECT "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " AS R_District, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " AS R_Upazilla, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " AS R_Union, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS R_Village, "
                + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_NAME + ", "
                + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + ", "
                + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + ","
                + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + ", "
                + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + ","
                + "(" + " CASE WHEN " + SQLiteHandler.SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ")  AS Sex" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LATITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LONGITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + " " + SQLiteHandler.M_STATUS + " AS MStatus" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_DATE + " , "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.W_RANK_COL + " as wRank ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.M_STATUS + " as status "//26

                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.DISTRICT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UPAZILLA_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UNIT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.VILLAGE_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " WHERE " +// + LAY_R4_LIST_CODE_COL + "='" + ext_village +" AND "+
                SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " || '' || "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " = '" + ext_village + "' "
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + " LIKE '%" + hhName + "%' "
                + " GROUP BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + " DESC";
        //   Log.d("MAR",sql);
        // return sql;

    }


    public static String getRegisteredData_ifVillage_NOT_Ext_SelectQuery() {
        return "SELECT "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REG_DATE_COL + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE + ", "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + " AS R_District, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + " AS R_Upazilla, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + " AS R_Union, "
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + " AS R_Village, "

                + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_NAME + ", "
                + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + ", "
                + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + ","
                + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + ", "
                + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + ","

                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PID_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.PNAME_COL + ","
                + "(" + " CASE WHEN " + SQLiteHandler.SEX_COL + "==" + "'F'" + " THEN " + "'Female'" + " ELSE " + "'Male'" + " END " + ") AS Sex" + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.HH_SIZE + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LATITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.LONGITUDE_COL + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.AG_LAND + ","
                + "(" + " CASE WHEN " + SQLiteHandler.V_STATUS + "==" + "'Y'" + " THEN " + "'Yes'" + " ELSE " + "'No'" + " END " + ") AS VStatus" + ","
                + "  " + SQLiteHandler.M_STATUS + " AS MStatus " + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_BY + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ENTRY_DATE + ","
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VSLA_GROUP + " , "
                + " , "
                + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL + " as addname , "//25
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " as addcode ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.W_RANK_COL + " as wRank ,"//26
                + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.M_STATUS + " as status "//26

                + " FROM " + SQLiteHandler.REGISTRATION_TABLE
                + " LEFT JOIN " + SQLiteHandler.COUNTRY_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.COUNTRY_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " LEFT JOIN " + SQLiteHandler.DISTRICT_TABLE

                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UPAZILLA_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.UNIT_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.VILLAGE_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " LEFT JOIN " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                + " ON " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.UNITE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL + "=" + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " AND " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL + " = " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL

                + " GROUP BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL  /// this GROUP BY PREVENT TO SHOW DUPLICATED VALUES // Faisal Mohammad  @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.com @email:nirzon192@gmail.commodify
                + " ORDER BY " + SQLiteHandler.REGISTRATION_TABLE + "." + SQLiteHandler.ID_COL + " DESC";
    }

    /**
     * @param cCode
     * @param donorCode
     * @param awardCode
     * @param programCode
     * @param srvCode
     * @param opCode
     * @param opMonthCode
     * @param srvCenterCode
     * @param fdpCode
     * @param mem15Id
     * @return
     */

    public static String getSrvSpecificByMemberId_SelectQuery(String cCode, String donorCode, String awardCode
            , String programCode, String srvCode, String opCode, String opMonthCode, String srvCenterCode
            , String fdpCode, String mem15Id) {
        return "SELECT "
                + SQLiteHandler.BABY_STATUS_COL
                + " , " + SQLiteHandler.GMP_ATTENDACE_COL
                + " , " + SQLiteHandler.WEIGHT_STATUS_COL
                + " , " + SQLiteHandler.NUT_ATTENDANCE_COL
                + " , " + SQLiteHandler.VITA_UNDER5_COL
                + " , " + SQLiteHandler.EXCLUSIVE_CURRENTLYBF_COL
                + " , " + SQLiteHandler.DATE_COMPFEEDING_COL
                + " , " + SQLiteHandler.CMAMREF_COL
                + " , " + SQLiteHandler.CMAMADD_COL
                + " , " + SQLiteHandler.ANCVISIT_COL
                + " , " + SQLiteHandler.PNCVISIT_2D_COL
                + " , " + SQLiteHandler.PNCVISIT_1W_COL
                + " , " + SQLiteHandler.PNCVISIT_6W_COL
                + " , " + SQLiteHandler.DELIVERY_STAFF_1_COL
                + " , " + SQLiteHandler.DELIVERY_STAFF_2_COL
                + " , " + SQLiteHandler.DELIVERY_STAFF_3_COL

                + " , " + SQLiteHandler.HOME_SUPPORT24H_1D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_2D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_3D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_8D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_14D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_21D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_30D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_60D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT24H_90D_COL

                + " , " + SQLiteHandler.HOME_SUPPORT48H_1D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT48H_3D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT48H_8D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT48H_30D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT48H_60D_COL
                + " , " + SQLiteHandler.HOME_SUPPORT48H_90D_COL

                + " , " + SQLiteHandler.MATERNAL_BLEEDING_COL
                + " , " + SQLiteHandler.MATERNAL_SEIZURE_COL
                + " , " + SQLiteHandler.MATERNAL_INFECTION_COL
                + " , " + SQLiteHandler.MATERNAL_PROLONGEDLABOR_COL
                + " , " + SQLiteHandler.MATERNAL_OBSTRUCTEDLABOR_COL
                + " , " + SQLiteHandler.MATERNAL_PPRM_COL


                + " , " + SQLiteHandler.NBORN_ASPHYXIA_COL
                + " , " + SQLiteHandler.NBORN_SEPSIS_COL
                + " , " + SQLiteHandler.NBORN_HYPOTHERMIA_COL
                + " , " + SQLiteHandler.NBORN_HYPERTHERMIA_COL
                + " , " + SQLiteHandler.NBORN_NOSUCKLING_COL
                + " , " + SQLiteHandler.NBORN_JAUNDICE_COL

                + " , " + SQLiteHandler.CHILD_DIARRHEA_COL
                + " , " + SQLiteHandler.CHILD_PNEUMONIA_COL
                + " , " + SQLiteHandler.CHILD_FEVER_COL
                + " , " + SQLiteHandler.CHILD_CEREBRALPALSY_COL

                + " , " + SQLiteHandler.IMMU_POLIO_COL
                + " , " + SQLiteHandler.IMMU_BCG_COL
                + " , " + SQLiteHandler.IMMU_MEASLES_COL
                + " , " + SQLiteHandler.IMMU_DPT_HIB_COL
                + " , " + SQLiteHandler.IMMU_LOTTA_COL
                + " , " + SQLiteHandler.IMMU_OTHER_COL

                + " , " + SQLiteHandler.FPCOUNSEL_MALECONDOM_COL
                + " , " + SQLiteHandler.FPCOUNSEL_FEMALECONDOM_COL
                + " , " + SQLiteHandler.FPCOUNSEL_PILL_COL
                + " , " + SQLiteHandler.FPCOUNSEL_DEPO_COL
                + " , " + SQLiteHandler.FPCOUNSEL_LONGPARMANENT_COL
                + " , " + SQLiteHandler.FPCOUNSEL_NOMETHOD_COL

                + " , " + SQLiteHandler.CROP_CODE_COL
                + " , " + SQLiteHandler.LOAN_SOURCE_COL
                + " , " + SQLiteHandler.LOAN_AMT_COL
                + " , " + SQLiteHandler.ANIMAL_CODE_COL
                + " , " + SQLiteHandler.LEAD_CODE_COL

                + " FROM " + SQLiteHandler.SERVICE_SPECIFIC_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND  " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND  " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND  " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'"
                + " AND  " + SQLiteHandler.OPERATION_CODE_COL + " = '" + opCode + "'"
                + " AND  " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMonthCode + "'"
                + " AND  " + SQLiteHandler.SERVICE_CENTER_CODE_COL + " = '" + srvCenterCode + "'"
                + " AND  " + SQLiteHandler.FDP_CODE_COL + " = '" + fdpCode + "'"
                + " AND  " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || '' || " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || '' || " + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || '' || " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " || '' || " + SQLiteHandler.HHID_COL + " || '' || " + SQLiteHandler.HH_MEM_ID + " = '" + mem15Id + "'";

    }

    public static String getServiceDateRange_selectQuery(String cCode, String srvOpMonthCode) {
        return "SELECT  " + SQLiteHandler.OPERATION_CODE_COL
                + " , " + SQLiteHandler.OP_MONTH_CODE_COL
                + " , " + SQLiteHandler.USA_START_DATE
                + " , " + SQLiteHandler.USA_END_DATE
                + " , " + SQLiteHandler.MONTH_LABEL
                + " FROM " + SQLiteHandler.OP_MONTH_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.STATUS + "= 'A'"
                + " AND " + SQLiteHandler.OPERATION_CODE_COL + " = '2'"
                + " AND " + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + srvOpMonthCode + "'";
    }


    public static String getProgramGraduationDateOfMember_sql(String cCode, String disCode, String upCode, String unCode, String vCode, String hhID, String memID, String donorCode, String awardCode, String progCode) {
        return "SELECT  MAX( " + SQLiteHandler.GRD_DATE_COL + " )  AS grdDate"
                + " FROM " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = '" + disCode + "' "
                + " AND " + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = '" + upCode + "' "
                + " AND " + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = '" + unCode + "' "
                + " AND " + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = '" + vCode + "' "
                + " AND " + SQLiteHandler.HHID_COL + " = '" + hhID + "' "
                + " AND " + SQLiteHandler.HH_MEM_ID + " = '" + memID + "' "
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' "
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "' ";
    }

    public static String checkAdmCountryProgramsVoucherFlag_sql(String cCode, String donorCode, String awardCode, String progCode) {
        return "SELECT " + SQLiteHandler.VOUCHER_FLAG + " FROM " + SQLiteHandler.COUNTRY_PROGRAM_TABLE +
                " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'" +
                " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'" +
                " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'" +
                " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'";
    }


    public static String checkAdmCountryProgramsNoneFoodFlag_sql(String cCode, String donorCode, String awardCode, String progCode, String srvCode) {
        return "SELECT " + SQLiteHandler.NON_FOOD_FLAG + " FROM " + SQLiteHandler.COUNTRY_PROGRAM_TABLE
                + " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'"
                + " AND " + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "'"
                + " AND " + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + progCode + "'"
                + " AND " + SQLiteHandler.SERVICE_CODE_COL + " = '" + srvCode + "'"
                ;
    }

    public static String get_VOItmUnitMeas(String measRCode) {
        return " SELECT " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.UNITE_MEAS_COL
                + " FROM " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE
                + " WHERE " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEAS_R_CODE_COL + " = " + measRCode;

    }


    public static String getServExtentedItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode) {
        return " SELECT " +
                SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " AS voucherID" +

                " , " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_NAME_COL

                + " || '-' || " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.UNITE_MEAS_COL
                + " ||' '||  " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEASE_TITLE_COL + " AS item" +

                " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.CATEGORY_CODE_COL + " || " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",0,8) " +
                "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE
                + " ON " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL


                + " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY "
                + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " , " + SQLiteHandler.DISTRIBUTION_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL;

    }


    public static String getDistExtentedItemName(final String cCode, final String donorCode, final String awardCode, final String opMCode, final String programCode) {
        return " SELECT " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " AS voucherID "
                + " , " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_NAME_COL
                + " || '-' || " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.UNITE_MEAS_COL
                + " ||' '||  " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEASE_TITLE_COL + " AS item "
                + " FROM " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE
                + "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.CATEGORY_CODE_COL + " || " + SQLiteHandler.VOUCHER_ITEM_TABLE + "." + SQLiteHandler.ITEM_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",0,8) "
                + "  INNER JOIN " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE
                + " ON " + SQLiteHandler.VOUCHER_ITEM__MEAS_TABLE + "." + SQLiteHandler.MEAS_R_CODE_COL + " = SUBSTR(" + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + ",8) " +
                "  INNER JOIN " + SQLiteHandler.SERVICE_EXTENDED_TABLE
                + " ON " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.SERVICE_CODE_COL
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL + " = " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL +
                " WHERE  " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "'"
                + " AND " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.OP_MONTH_CODE_COL + " = '" + opMCode + "'  "
                + " AND " + SQLiteHandler.VOUCHER_COUNTRY_PROGRAM_ITEM_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + programCode + "'  "
                + " GROUP BY " + SQLiteHandler.SERVICE_EXTENDED_TABLE + "." + SQLiteHandler.VOUCHER_ITEM_SPEC_COL;
    }

    public static String getGroupSummaryList_sql(final String cCode) {
        return "SELECT " +

                "   cg." + SQLiteHandler.COUNTRY_CODE_COL + " " +
                " , cg." + SQLiteHandler.DONOR_CODE_COL +
                " , cg." + SQLiteHandler.AWARD_CODE_COL +
                " , cg." + SQLiteHandler.PROGRAM_CODE_COL +
                " , cgc." + SQLiteHandler.GROUP_CAT_CODE_COL +
                " , cgc." + SQLiteHandler.GROUP_CAT_SHORT_NAME_COL +
                " , cg." + SQLiteHandler.GROUP_CODE_COL +
                " , cg." + SQLiteHandler.GROUP_NAME_COL +
                " , srv." + SQLiteHandler.SERVICE_SHORT_NAME_COL +

                " , ( select Count(*) from " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " AS regNgrp "
                + " WHERE  regNgrp." + SQLiteHandler.COUNTRY_CODE_COL + " = cg." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND  " + "regNgrp." + SQLiteHandler.DONOR_CODE_COL + " = cg." + SQLiteHandler.DONOR_CODE_COL
                + " AND  " + "regNgrp." + SQLiteHandler.AWARD_CODE_COL + " = cg." + SQLiteHandler.AWARD_CODE_COL
                + " AND regNgrp." + SQLiteHandler.PROGRAM_CODE_COL + " = cg." + SQLiteHandler.PROGRAM_CODE_COL
                + " AND  " + "regNgrp." + SQLiteHandler.GROUP_CODE_COL + " = cg." + SQLiteHandler.GROUP_CODE_COL


                + " )  AS c " +

                " FROM " + SQLiteHandler.COMMUNITY_GROUP_TABLE + "  AS cg " +

                " LEFT JOIN " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " AS regG " +
                " ON regG." + SQLiteHandler.COUNTRY_CODE_COL + " = cg." + SQLiteHandler.COUNTRY_CODE_COL + " " +
                " AND regG." + SQLiteHandler.DONOR_CODE_COL + " = cg." + SQLiteHandler.DONOR_CODE_COL +
                " AND regG." + SQLiteHandler.AWARD_CODE_COL + " = cg." + SQLiteHandler.AWARD_CODE_COL +
                " AND regG." + SQLiteHandler.PROGRAM_CODE_COL + " = cg." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND regG." + SQLiteHandler.GROUP_CODE_COL + " = cg." + SQLiteHandler.GROUP_CODE_COL +
                " LEFT JOIN " + SQLiteHandler.COMMUNITY_GROUP_CATEGORY_TABLE + " AS cgc " +
                " ON cg." + SQLiteHandler.COUNTRY_CODE_COL + " = cgc." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND cg." + SQLiteHandler.DONOR_CODE_COL + " = cgc." + SQLiteHandler.DONOR_CODE_COL +
                " AND cg." + SQLiteHandler.AWARD_CODE_COL + " = cgc." + SQLiteHandler.AWARD_CODE_COL +
                " AND cg." + SQLiteHandler.PROGRAM_CODE_COL + " = cgc." + SQLiteHandler.PROGRAM_CODE_COL +
                " AND cg." + SQLiteHandler.GROUP_CAT_CODE_COL + " = cgc." + SQLiteHandler.GROUP_CAT_CODE_COL +
                " LEFT JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " AS srv " +
                " ON cg." + SQLiteHandler.PROGRAM_CODE_COL + " = srv. " + SQLiteHandler.PROGRAM_CODE_COL +
                " AND regG." + SQLiteHandler.SERVICE_CODE_COL + " = srv." + SQLiteHandler.SERVICE_CODE_COL +
                " WHERE cg." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " GROUP BY cgc." + SQLiteHandler.GROUP_CAT_CODE_COL + ", cg." + SQLiteHandler.GROUP_CODE_COL;
    }

    public static String loadVillageInAssignSummary_sql(String cCode) {
        return "SELECT " + " v." + SQLiteHandler.COUNTRY_CODE_COL + " || '' ||  v." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " || '' || v." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " || '' || v." +
                SQLiteHandler.LAY_R3_LIST_CODE_COL + " || '' || v." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " AS v_code," +
                " v." + SQLiteHandler.VILLAGE_NAME_COL + " AS Vill_Name " +
                     /*   " COUNT("+PID_COL+") AS records"*/" FROM " + SQLiteHandler.VILLAGE_TABLE + " AS v" +
                " LEFT JOIN " + SQLiteHandler.REGISTRATION_TABLE + " AS r" +
                " ON r." + SQLiteHandler.COUNTRY_CODE_COL + "= v." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + "r." + SQLiteHandler.DISTRICT_NAME_COL + "= v." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + "r." + SQLiteHandler.UPZILLA_NAME_COL + "= v." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + "r." + SQLiteHandler.UNITE_NAME_COL + "= v." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + "r." + SQLiteHandler.VILLAGE_NAME_COL + "= v." + SQLiteHandler.LAY_R4_LIST_CODE_COL +
                " Inner join " + SQLiteHandler.SELECTED_VILLAGE_TABLE + " AS s"
                + " on " + " s." + SQLiteHandler.COUNTRY_CODE_COL + "= v." + SQLiteHandler.COUNTRY_CODE_COL
                + " AND " + "s." + SQLiteHandler.LAY_R1_LIST_CODE_COL + "= v." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " AND " + "s." + SQLiteHandler.LAY_R2_LIST_CODE_COL + "= v." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " AND " + "s." + SQLiteHandler.LAY_R3_LIST_CODE_COL + "= v." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " AND " + "s." + SQLiteHandler.LAY_R4_LIST_CODE_COL + "= v." + SQLiteHandler.LAY_R4_LIST_CODE_COL +

                " WHERE v." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'" + /** send the no of village for selected country added by Faisal Mohammad*/
                "  GROUP BY v." + SQLiteHandler.COUNTRY_CODE_COL + ",v." + SQLiteHandler.LAY_R1_LIST_CODE_COL + ",v." + SQLiteHandler.LAY_R2_LIST_CODE_COL + ",v." + SQLiteHandler.LAY_R3_LIST_CODE_COL + ",v." + SQLiteHandler.LAY_R4_LIST_CODE_COL;

    }


    public static String getIdListInGroupInGroupSummary_sql(String cCode, String donorCode, String awardCode, String prgCode, String grpCode) {


        String getMemberName;
        if (cCode.equals("0004")) {
            getMemberName = " regMem." + SQLiteHandler.MEM_NAME_FIRST_COL +
                    "|| ' ' || " + " regMem." + SQLiteHandler.MEM_NAME_MIDDLE_COL +
                    "|| ' ' || " + " regMem." + SQLiteHandler.MEM_NAME_LAST_COL;

        } else
            getMemberName = " regMem." + SQLiteHandler.MEM_NAME_COL;
        return "SELECT " +


                "  regG." + SQLiteHandler.LAY_R1_LIST_CODE_COL +
                " || '' || regG." + SQLiteHandler.LAY_R2_LIST_CODE_COL +
                " || '' || regG." + SQLiteHandler.LAY_R3_LIST_CODE_COL +
                " || '' || regG." + SQLiteHandler.LAY_R4_LIST_CODE_COL +
                " || '' || regG." + SQLiteHandler.HHID_COL +
                " || '' || regG." + SQLiteHandler.HH_MEM_ID + " AS idMem " +

                " , srv." + SQLiteHandler.SERVICE_SHORT_NAME_COL +
                " , " + getMemberName + " AS memName " +
                " FROM " + SQLiteHandler.REG_N_MEM_PROG_GRP_TABLE + " AS regG " +


                " LEFT JOIN " + SQLiteHandler.SERVICE_MASTER_TABLE + " AS srv " +
                " ON regG." + SQLiteHandler.PROGRAM_CODE_COL + " = srv. " + SQLiteHandler.PROGRAM_CODE_COL +
                " AND regG." + SQLiteHandler.SERVICE_CODE_COL + " = srv." + SQLiteHandler.SERVICE_CODE_COL +

                " LEFT JOIN " + SQLiteHandler.REGISTRATION_MEMBER_TABLE + " AS regMem " +

                " ON " + " regG." + SQLiteHandler.COUNTRY_CODE_COL + " = " + " regMem." + SQLiteHandler.COUNTRY_CODE_COL +
                " AND " + " regG." + SQLiteHandler.LAY_R1_LIST_CODE_COL + " = " + " regMem." + SQLiteHandler.DISTRICT_NAME_COL +
                " AND " + " regG." + SQLiteHandler.LAY_R2_LIST_CODE_COL + " = " + " regMem." + SQLiteHandler.UPZILLA_NAME_COL +
                " AND " + " regG." + SQLiteHandler.LAY_R3_LIST_CODE_COL + " = " + " regMem." + SQLiteHandler.UNITE_NAME_COL +
                " AND " + " regG." + SQLiteHandler.LAY_R4_LIST_CODE_COL + " = " + " regMem." + SQLiteHandler.VILLAGE_NAME_COL +
                " AND " + " regG." + SQLiteHandler.HHID_COL + " = " + " regMem." + SQLiteHandler.HHID_COL +
                " AND " + " regG." + SQLiteHandler.HH_MEM_ID + " = " + " regMem." + SQLiteHandler.HH_MEM_ID + " " +


                " WHERE regG." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' " +
                " AND regG." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "' " +
                " AND regG." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "' " +
                " AND regG." + SQLiteHandler.PROGRAM_CODE_COL + " = '" + prgCode + "' " +
                " AND regG." + SQLiteHandler.GROUP_CODE_COL + " = '" + grpCode + "' ";

    }
    public static String loadProgramWhereMemberAreAssigned_sql(final String idcCode, final String donorCode, final String awardCode, final String memId){
        return "SELECT " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " , " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_SHORT_NAME_COL
                + " FROM " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE
                + " INNER JOIN " + SQLiteHandler.ADM_AWARD_TABLE
                + " ON " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + " = " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.DONOR_CODE_COL
                + " AND " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + " = " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.AWARD_CODE_COL
                + " INNER JOIN " + SQLiteHandler.REG_N_ASSIGN_PROG_SRV_TABLE + " AS regAss "
                + " ON regAss." + SQLiteHandler.PROGRAM_CODE_COL + " = " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.PROGRAM_CODE_COL
                + " WHERE " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.ADM_PROGRAM_MASTER_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'"
                + " AND regAss." + SQLiteHandler.LAY_R1_LIST_CODE_COL
                + " || '' || regAss." + SQLiteHandler.LAY_R2_LIST_CODE_COL
                + " || '' || regAss." + SQLiteHandler.LAY_R3_LIST_CODE_COL
                + " || '' || regAss." + SQLiteHandler.LAY_R4_LIST_CODE_COL
                + " || '' || regAss." + SQLiteHandler.HHID_COL
                + " || '' || regAss." + SQLiteHandler.HH_MEM_ID + " = '" + memId + "'";

    }

    public static String loadOrganization_sql(final String cCode, final String donorCode, final String awardCode){
        return "SELECT progOR." + SQLiteHandler.ORG_CODE_COL +
                " ,  pOrg." + SQLiteHandler.ORGANIZATION_NAME + " " +
                "                                FROM " + SQLiteHandler.PROGRAM_ORGANIZATION_ROLE_TABLE + " AS progOR "
                + "                               INNER JOIN " +
                "                                " + SQLiteHandler.PROGRAM_ORGANIZATION_NAME_TABLE + " AS pOrg " +
                "                               ON progOR." + SQLiteHandler.ORG_CODE_COL + " = pOrg." + SQLiteHandler.ORG_CODE_COL + "  " +
                "                                WHERE (progOR." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "')" +
                "                                AND (progOR." + SQLiteHandler.DONOR_CODE_COL + " = '" + donorCode + "') " +
                "                                AND (progOR." + SQLiteHandler.AWARD_CODE_COL + " = '" + awardCode + "') " +
                "                                AND (progOR." + SQLiteHandler.IMP_Y_N_COL + " = 'Y')" +
                "                                ORDER BY pOrg." + SQLiteHandler.ORGANIZATION_NAME;

    }
}//end of class
