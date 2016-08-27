package com.siddiquinoor.restclient.activity;

/**
 * Activity for login validation and collect existing data from online
 *
 * @author Siddiqui Noor
 * @desc Technical Director, TechnoDhaka.
 * @link www.SiddiquiNoor.com
 * @version 1.3.0
 * @since 1.0
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.CommunityGroupDM;
import com.siddiquinoor.restclient.data_model.CountryNameItem;
import com.siddiquinoor.restclient.data_model.FDPItem;
import com.siddiquinoor.restclient.data_model.ProgramMasterDM;
import com.siddiquinoor.restclient.data_model.ServiceCenterItem;
import com.siddiquinoor.restclient.data_model.SrvTableReportDM;
import com.siddiquinoor.restclient.data_model.VillageItem;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.controller.AppConfig;
import com.siddiquinoor.restclient.controller.AppController;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.network.ConnectionDetector;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.notifications.AlertDialogManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.logging.Handler;
import android.content.Context;
import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends BaseActivity {
    // LogCat tag
    private static final String TAG = LoginActivity.class.getSimpleName();
    public static final String REG_HOUSE_HOLD_DATA = "reg_house_hold_data";
    public static final String REG_MEMBER_DATA = "reg_member_data";
    public static final String REG_MEMBER_PROG_GROUP_DATA = "reg_member_prog_grp_data";
    public static final String SERVICE_DATA = "service_data";

    // Login Button
    private Button btnLogin;
    // User hhName Input box
    private EditText inputUsername;
    //password input box
    private EditText inputPassword;
    //progress mdialog wigedt
    private ProgressDialog barPDialog; //Bar Progress Dialog
    //progress handler
    private Handler barPDialogHandler;
    //sqlLite Database handler
    private SQLiteHandler db;
    // connection Detector
    ConnectionDetector cd;
    // flag for connectivity
    Boolean isInternetAvailable = false;
    //alert Dialog Manager
    AlertDialogManager alert;
    //Application configuration
    private AppConfig ac;
    //progress mdialog
    private ProgressDialog pDialog;
    // size  = 0, int type
    private int size = 0;
    //mContext
    private final Context mContext = LoginActivity.this;
    //exit button
    private Button btnExit;
    SharedPreferences settings;
    SharedPreferences.Editor editor;
    private Button btnClean;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        barPDialogHandler = new Handler();

        /**
         * Initialize Button and Input Boxes
         */
        inputUsername = (EditText) findViewById(R.id.user_name);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnClean = (Button) findViewById(R.id.btnClean);

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE); //1
        editor = settings.edit(); //2

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Exit Application?");
                builder.setMessage("Click yes to exit!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog exitDailog = builder.create();
                exitDailog.show();


            }
        });


        // Progress mdialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // connectivity manager
        cd = new ConnectionDetector(getApplicationContext());


        setListener();


    }

    private void setListener() {
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.selectUploadSyntextRowCount() > 0) {
                    //Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
                    showAlert("There are records not yet Synced. Clean attempt denied");
                } else {


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete Database?");
                    builder.setMessage("Sure to delete database?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try {
                                dialog.dismiss();

                                db.deleteUsersWithSelectedVillage();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog exitDailog = builder.create();
                    exitDailog.show();


                }
            }
        });

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String user_name = "";
                String password = "";
                // in developments mode
                if (ac.DEV_ENVIRONMENT) {
                    user_name = "nkalam";
                    password = "p3";
                } else {
                    user_name = inputUsername.getText().toString().trim();
                    password = inputPassword.getText().toString().trim();
                }
                // Check for empty data in the form
                if (user_name.trim().length() > 0 && password.trim().length() > 0) {

                    if (db.isValidLocalLogin(user_name, password)) {
                        setLogin(true);        // login success

                        // Getting local User information
                        HashMap<String, String> user = db.getUserDetails();
                        setUserName(user.get("name"));  // Setting Username into session
                        setStaffID(user.get("code"));   // Setting StaffCode into session
                        setUserID(user.get("username"));
                        setUserPassword(user.get("password"));

                        setUserCountryCode(user.get("c_code")); // Setting Country Code into session

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        //startActivityForResult(intent,2);
                        finish();
                    } else {
                        /** is  inter net available */
                        isInternetAvailable = cd.isConnectingToInternet();
                        if (isInternetAvailable) {

                            if (db.selectUploadSyntextRowCount() > 0) {
//                                showAlert("Login with the same user for whom the device was configured.\nOtherwise, initiate to sync and login again.");
                                showAlert(getResources().getString(R.string.unsyn_msg));
                            } else {
                                pDialog = new ProgressDialog(mContext);
                                pDialog.setCancelable(false);
                                pDialog.setMessage("Loading..");
                                pDialog.show();
                                getOperationModeAlert(user_name, password);
                            }


                        } else
                            showAlert("Check your internet connectivity!!");
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    /**
     * function to verify login details & select 2 FDP
     */

    List<ServiceCenterItem> serviceCenterNameList = new ArrayList<ServiceCenterItem>();
    List<ProgramMasterDM> AdmProgramNameList = new ArrayList<ProgramMasterDM>();
    List<CommunityGroupDM> communityGroupList = new ArrayList<CommunityGroupDM>();
    List<SrvTableReportDM> srvTableReportList = new ArrayList<SrvTableReportDM>();
    ArrayList<ServiceCenterItem> selectedServiceCenterList = new ArrayList<ServiceCenterItem>();
    String[] serviceCenterNameStringArray;
    // todo : fix the service center

    public void checkServiceCenterSelection(final String user_name, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;
                        // count no countries assigne
                        if (!jObj.isNull("countrie_no")) {

                            JSONArray village = jObj.getJSONArray("countrie_no");

                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                CountryNo = vil.getString("CountryNo");

                            }
                        }


                        if (!jObj.isNull("countries")) {

                            JSONArray village = jObj.getJSONArray("countries");
                            countryNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                String AdmCountryCode = vil.getString("AdmCountryCode");
                                String AdmCountryName = vil.getString("AdmCountryName");
                                CountryNameItem countryNameItem = new CountryNameItem();
                                countryNameItem.setAdmCountryCode(AdmCountryCode);
                                countryNameItem.setAdmCountryName(AdmCountryName);
                                countryNameList.add(countryNameItem);

                            }
                        }


                        if (!jObj.isNull(MainActivity.DOB_SERVICE_CENTER_JSON_A)) {// this is not servie
                            JSONArray dob_service_centers = jObj.getJSONArray(MainActivity.DOB_SERVICE_CENTER_JSON_A);
                            size = dob_service_centers.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject dob_service_center = dob_service_centers.getJSONObject(i);

                                String AdmCountryCode = dob_service_center.getString(MainActivity.ADM_COUNTRY_CODE);
                                String SrvCenterCode = dob_service_center.getString(MainActivity.SRV_CENTER_CODE);
                                String SrvCenterName = dob_service_center.getString(MainActivity.SRV_CENTER_NAME);


                                ServiceCenterItem servCenterItem = new ServiceCenterItem();

                                servCenterItem.setAdmCountryCode(AdmCountryCode);
                                servCenterItem.setServiceCenterCode(SrvCenterCode);
                                servCenterItem.setServiceCenterName(SrvCenterName);
                                serviceCenterNameList.add(servCenterItem);


                            }
                        }


                        if (!jObj.isNull(MainActivity.ADM_PROGRAM_MASTER_JSON_A)) {
                            JSONArray adm_program_masters = jObj.getJSONArray(MainActivity.ADM_PROGRAM_MASTER_JSON_A);
                            size = adm_program_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                                String AdmCountryCode = adm_program_master.getString("AdmCountryCode");
                                String AdmProgCode = adm_program_master.getString(MainActivity.ADM_PROG_CODE);
                                String AdmAwardCode = adm_program_master.getString(MainActivity.ADM_AWARD_CODE);
                                String AdmDonorCode = adm_program_master.getString(MainActivity.ADM_DONOR_CODE);
                                String ProgName = adm_program_master.getString(MainActivity.PROG_NAME);
                                String ProgShortName = adm_program_master.getString(MainActivity.PROG_SHORT_NAME);

                                // db.addProgram(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);
                                ProgramMasterDM progData = new ProgramMasterDM();
                                progData.setAdmAwardCode(AdmCountryCode);
                                progData.setAdmProgCode(AdmProgCode);
                                progData.setAdmAwardCode(AdmAwardCode);
                                progData.setAdmDonorCode(AdmDonorCode);
                                progData.setProgName(ProgName);
                                progData.setProgShortName(ProgShortName);

                                AdmProgramNameList.add(progData);


                                Log.d(TAG, "In Program master Table- AdmProgCode :" + AdmProgCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " ProgName : " + ProgName + " ProgShortName  : " + ProgShortName);
                            }
                        }


                        if (!jObj.isNull("community_group")) {
                            JSONArray adm_program_masters = jObj.getJSONArray("community_group");
                            size = adm_program_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                                String AdmCountryCode = adm_program_master.getString("AdmCountryCode");
                                String AdmDonorCode = adm_program_master.getString("AdmDonorCode");
                                String AdmAwardCode = adm_program_master.getString("AdmAwardCode");
                                String AdmProgCode = adm_program_master.getString("AdmProgCode");
                                String GrpCode = adm_program_master.getString("GrpCode");
                                String SrvCenterCode = adm_program_master.getString("SrvCenterCode");
                                // db.addProgram(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);
                                CommunityGroupDM communityData = new CommunityGroupDM();
                                communityData.setAdmCountryCode(AdmCountryCode);
                                communityData.setAdmAwardCode(AdmAwardCode);
                                communityData.setAdmDonorCode(AdmDonorCode);
                                communityData.setAdmProgCode(AdmProgCode);
                                communityData.setGrpCode(GrpCode);
                                communityData.setSrvCenterCode(SrvCenterCode);

                                communityGroupList.add(communityData);
                                //     Log.d(TAG, "In Program master Table- AdmProgCode :" + AdmProgCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " ProgName : " + ProgName + " ProgShortName  : " + ProgShortName);
                            }
                        }


                        if (!jObj.isNull("srv_table_report")) {
                            JSONArray adm_program_masters = jObj.getJSONArray("srv_table_report");
                            size = adm_program_masters.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject adm_program_master = adm_program_masters.getJSONObject(i);

                                String AdmCountryCode = adm_program_master.getString("AdmCountryCode");
                                String AdmDonorCode = adm_program_master.getString("AdmDonorCode");
                                String AdmAwardCode = adm_program_master.getString("AdmAwardCode");
                                String ProgCode = adm_program_master.getString("ProgCode");

                                String SrvCenterCode = adm_program_master.getString("SrvCenterCode");
                                String OpCode = adm_program_master.getString("OpCode");
                                String OpMonthCode = adm_program_master.getString("OpMonthCode");
                                String PreparedBy = adm_program_master.getString("PreparedBy");
                                String VerifiedBy = adm_program_master.getString("VerifiedBy");
                                // db.addProgram(AdmProgCode, AdmAwardCode, AdmDonorCode, ProgName, ProgShortName, MultipleSrv);
                                SrvTableReportDM srvTableReportData = new SrvTableReportDM();
                                srvTableReportData.setAdmCountryCode(AdmCountryCode);
                                srvTableReportData.setAdmAwardCode(AdmAwardCode);
                                srvTableReportData.setAdmDonorCode(AdmDonorCode);
                                srvTableReportData.setProgCode(ProgCode);

                                srvTableReportData.setSrvCenterCode(SrvCenterCode);
                                srvTableReportData.setOpCode(OpCode);
                                srvTableReportData.setOpMonthCode(OpMonthCode);
                                srvTableReportData.setPreparedBy(PreparedBy);
                                srvTableReportData.setVerifiedBy(VerifiedBy);

                                srvTableReportList.add(srvTableReportData);
                                //     Log.d(TAG, "In Program master Table- AdmProgCode :" + AdmProgCode + " AdmDonorCode : " + AdmDonorCode + " AdmAwardCode : " + AdmAwardCode + " ProgName : " + ProgName + " ProgShortName  : " + ProgShortName);
                            }
                        }


                        hideDialog();
                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getServiceCenterAlert(user_name, password, false);
                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, 3);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_service_center_name");
                params.put("user_name", user_name);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details & select 2 FDP
     */

    List<FDPItem> fdpNameList = new ArrayList<FDPItem>();
    ArrayList<FDPItem> selectedFdpList = new ArrayList<FDPItem>();
    String[] fdpNameStringArray;

    public void checkFDPSelection(final String user_name, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;
                        // count no countries assigne
                        if (!jObj.isNull("countrie_no")) {

                            JSONArray village = jObj.getJSONArray("countrie_no");

                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                CountryNo = vil.getString("CountryNo");

                            }
                        }


                        if (!jObj.isNull("countries")) {

                            JSONArray village = jObj.getJSONArray("countries");
                            countryNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                String AdmCountryCode = vil.getString("AdmCountryCode");
                                String AdmCountryName = vil.getString("AdmCountryName");
                                CountryNameItem countryNameItem = new CountryNameItem();
                                countryNameItem.setAdmCountryCode(AdmCountryCode);
                                countryNameItem.setAdmCountryName(AdmCountryName);
                                countryNameList.add(countryNameItem);

                            }
                        }


                        if (!jObj.isNull(MainActivity.STAFF_FDP_ACCESS_JSON_A)) {

                            JSONArray village = jObj.getJSONArray(MainActivity.STAFF_FDP_ACCESS_JSON_A);
                            fdpNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject fdp_list = village.getJSONObject(i);

                                //String GeoLayRName = fdp_list.getString("GeoLayRName");
                                String AdmCountryCode = fdp_list.getString("AdmCountryCode");
                                String FDPCode = fdp_list.getString("FDPCode");
                                String FDPName = fdp_list.getString("FDPName");


                                FDPItem fdpItem = new FDPItem();

                                fdpItem.setAdmCountryCode(AdmCountryCode);
                                fdpItem.setFDPCode(FDPCode);
                                fdpItem.setFDPName(FDPName);
                                fdpNameList.add(fdpItem);


                            }
                        }
                        hideDialog();
                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getFDPAlert(user_name, password, false);
                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, 2);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_fdp_name");
                params.put("user_name", user_name);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    /**
     * function to verify login details & select 2 village
     */
    List<VillageItem> villageNameList = new ArrayList<VillageItem>();
    List<CountryNameItem> countryNameList = new ArrayList<CountryNameItem>();
    Dialog mdialog;
    ArrayList<VillageItem> aL_itemsSelected = new ArrayList<VillageItem>();
    ArrayList<CountryNameItem> aCountryL_itemsSelected = new ArrayList<CountryNameItem>();
    ArrayList<VillageItem> selectedVillageList = new ArrayList<VillageItem>();
    ArrayList<CountryNameItem> selectedCountryList = new ArrayList<CountryNameItem>();
    boolean[] itemChecked;
    boolean[] itemCheckedOpearationMode;

    String[] villageNameStringArray;
    String[] countryNameStringArray;
    private final String[] operationModeStringArray = {"Registration", "Distribution", "Service"};

    public void checkVillageSelection(final String user_name, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();

                String CountryNo = "0";
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        int size = 0;
                        // count no countries assigne
                        if (!jObj.isNull("countrie_no")) {

                            JSONArray village = jObj.getJSONArray("countrie_no");

                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                CountryNo = vil.getString("CountryNo");

                            }
                        }


                        if (!jObj.isNull("countries")) {

                            JSONArray village = jObj.getJSONArray("countries");
                            countryNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                String AdmCountryCode = vil.getString("AdmCountryCode");
                                String AdmCountryName = vil.getString("AdmCountryName");
                                CountryNameItem countryNameItem = new CountryNameItem();
                                countryNameItem.setAdmCountryCode(AdmCountryCode);
                                countryNameItem.setAdmCountryName(AdmCountryName);
                                countryNameList.add(countryNameItem);

                            }
                        }


                        if (!jObj.isNull(MainActivity.VILLAGE_JSON_A)) {

                            JSONArray village = jObj.getJSONArray(MainActivity.VILLAGE_JSON_A);
                            villageNameList.clear();
                            size = village.length();
                            for (int i = 0; i < size; i++) {
                                JSONObject vil = village.getJSONObject(i);

                                String GeoLayRName = vil.getString("GeoLayRName");
                                String AdmCountryCode = vil.getString("AdmCountryCode");
                                String LayRCode = vil.getString("LayRCode");
                                String LayR4ListName = vil.getString("LayR4ListName");


                                VillageItem villageItem = new VillageItem();
                                villageItem.setGeoLayRName(GeoLayRName);
                                villageItem.setAdmCountryCode(AdmCountryCode);
                                villageItem.setLayRCode(LayRCode);
                                villageItem.setLayR4ListName(LayR4ListName);
                                villageNameList.add(villageItem);


                            }
                        }
                        hideDialog();
                        // if user hsa 1 country assigned
                        if (CountryNo.equals("1")) {
                            getVillageAlert(user_name, password, false);
                        } else {
                            selectedCountryList.clear();
                            getCountryAlert(user_name, password, 1);
                        }


                    } else {
                        // Error in login. Invalid UserName or Password
                        hideDialog();
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                        // hideDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_village_name");
                params.put("user_name", user_name);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    public List<ServiceCenterItem> insertServiceCenterNameListToSArray(boolean countrySpec) {
        int i;
        if (countrySpec) {
            ArrayList<ServiceCenterItem> temCountySpecServiceCenterList = new ArrayList<ServiceCenterItem>();
            /**
             * check country Code
             */
            for (i = 0; i < serviceCenterNameList.size(); ++i) {
                if (selectedCountryList.get(0).getAdmCountryCode().equals(serviceCenterNameList.get(i).getAdmCountryCode())) {

                    temCountySpecServiceCenterList.add(serviceCenterNameList.get(i));
                    for (int j = 0; j < srvTableReportList.size(); j++) {
                        if (serviceCenterNameList.get(i).getServiceCenterCode().equals(srvTableReportList.get(j).getSrvCenterCode())) {

                            if (srvTableReportList.get(j).getPreparedBy().equals("null")) {
                                temCountySpecServiceCenterList.add(serviceCenterNameList.get(i));
                                Log.d("LOF", " serviceCenterNameList.get(i).getServiceCenterCode()=" + serviceCenterNameList.get(i).getServiceCenterCode());
                            }

                        } else {
                            temCountySpecServiceCenterList.add(serviceCenterNameList.get(i));
                        }
                    }


                }

            }
            serviceCenterNameList.clear();
            for (i = 0; i < temCountySpecServiceCenterList.size(); ++i) {

                serviceCenterNameList.add(temCountySpecServiceCenterList.get(i));


            }


            serviceCenterNameStringArray = new String[serviceCenterNameList.size()];

            for (i = 0; i < serviceCenterNameList.size(); ++i) {

                ServiceCenterItem serviceCenterItem = serviceCenterNameList.get(i);
                serviceCenterNameStringArray[i] = serviceCenterItem.getServiceCenterName();

            }


        } else {
            serviceCenterNameStringArray = new String[serviceCenterNameList.size()];

            for (i = 0; i < serviceCenterNameList.size(); ++i) {

                ServiceCenterItem serviceCenterItem = serviceCenterNameList.get(i);
                serviceCenterNameStringArray[i] = serviceCenterItem.getServiceCenterName();

            }


        }
        return serviceCenterNameList;

    }


    public List<FDPItem> insertFDPNameListToSArray(boolean countrySpec) {
        int i;
        if (countrySpec) {
            ArrayList<FDPItem> temCountySpecicFDPList = new ArrayList<FDPItem>();
            for (i = 0; i < fdpNameList.size(); ++i) {
                if (selectedCountryList.get(0).getAdmCountryCode().equals(fdpNameList.get(i).getAdmCountryCode())) {
                    temCountySpecicFDPList.add(fdpNameList.get(i));
                }

            }
            fdpNameList.clear();
            for (i = 0; i < temCountySpecicFDPList.size(); ++i) {

                fdpNameList.add(temCountySpecicFDPList.get(i));


            }


            fdpNameStringArray = new String[fdpNameList.size()];

            for (i = 0; i < fdpNameList.size(); ++i) {

                FDPItem fdpItem = fdpNameList.get(i);
                fdpNameStringArray[i] = fdpItem.getFDPName();

            }


            return fdpNameList;
        } else {
            fdpNameStringArray = new String[fdpNameList.size()];

            for (i = 0; i < fdpNameList.size(); ++i) {

                FDPItem fdpItem = fdpNameList.get(i);
                fdpNameStringArray[i] = fdpItem.getFDPName();

            }


            return fdpNameList;
        }

    }


    public List<VillageItem> insertVillageNameListToSArray(boolean countrySpec) {
        int i;
        if (countrySpec) {
            ArrayList<VillageItem> temCountySpecicVillageList = new ArrayList<VillageItem>();
            for (i = 0; i < villageNameList.size(); ++i) {
                if (selectedCountryList.get(0).getAdmCountryCode().equals(villageNameList.get(i).getAdmCountryCode())) {
                    temCountySpecicVillageList.add(villageNameList.get(i));
                }

            }
            villageNameList.clear();
            for (i = 0; i < temCountySpecicVillageList.size(); ++i) {

                villageNameList.add(temCountySpecicVillageList.get(i));


            }
/***
 * convert into array string
 */

            villageNameStringArray = new String[villageNameList.size()];

            for (i = 0; i < villageNameList.size(); ++i) {

                VillageItem villageItem = villageNameList.get(i);
                villageNameStringArray[i] = villageItem.getLayR4ListName();

            }


            return villageNameList;
        } else {
            villageNameStringArray = new String[villageNameList.size()];

            for (i = 0; i < villageNameList.size(); ++i) {

                VillageItem villageItem = villageNameList.get(i);
                villageNameStringArray[i] = villageItem.getLayR4ListName();

            }


            return villageNameList;
        }

    }


    public List<CountryNameItem> insertCountryNameListToSArray() {
        int i;
        countryNameStringArray = new String[countryNameList.size()];

        for (i = 0; i < countryNameList.size(); ++i) {

            CountryNameItem countryItem = countryNameList.get(i);
            countryNameStringArray[i] = countryItem.getAdmCountryName();

        }


        return countryNameList;

    }

    String strOperationMode = "";

    private void getOperationModeAlert(final String user_name, final String password) {
        aCountryL_itemsSelected = (ArrayList<CountryNameItem>) insertCountryNameListToSArray();
        itemCheckedOpearationMode = new boolean[operationModeStringArray.length];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Operation Mode");

        builder.setSingleChoiceItems(operationModeStringArray, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                strOperationMode = "";
                strOperationMode = operationModeStringArray[which];
            }
        });
        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //int selectItemCount = 0;
                if (!strOperationMode.equals("")) {

                    for (int i = 0; i < itemCheckedOpearationMode.length; i++) {
                        if (operationModeStringArray[i].equals(strOperationMode)) {


                            switch (i) {
                                case 0:

                                    checkVillageSelection(user_name, password);
                                    break;
                                case 1:

                                    checkFDPSelection(user_name, password);
                                    break;
                                case 2:

                                    checkServiceCenterSelection(user_name, password);
                                    break;

                                default:
                                    hideDialog();
                                    mdialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Select  any one", Toast.LENGTH_SHORT).show();
                                    break;

                            }
                        } else {
                              hideDialog();
                            mdialog.dismiss();
                        }

                    }
                } else {
                    mdialog.dismiss();
                    hideDialog();
                }


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                  hideDialog();
                //mdialog.dismiss();

                mdialog.dismiss();
            }
        });
        mdialog = builder.create();
        mdialog.show();
    }

    /**
     * @see {@link #getOperationModeAlert(String, String)}
     * @param user_name
     * @param password
     */
   /* private void getOperationModeAlert(final String user_name, final String password) {
//        int count = 1;
        aCountryL_itemsSelected = (ArrayList<CountryNameItem>) insertCountryNameListToSArray();

        itemCheckedOpearationMode = new boolean[operationModeStringArray.length];
//        if (countryNameStringArray.length > 0) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Operation Mode");
        builder.setMultiChoiceItems(operationModeStringArray, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int selectedItemId, boolean isSelected) {
                        if (isSelected) {

                            if (((AlertDialog) mdialog).getListView().getCheckedItemCount() <= 1) {
                                itemCheckedOpearationMode[selectedItemId] = isSelected;


                            } else {
                                Toast.makeText(LoginActivity.this, "Select Any One", Toast.LENGTH_SHORT).show();

                                ((AlertDialog) mdialog).getListView().setItemChecked(selectedItemId, false);
                            }
                        } else
                            itemCheckedOpearationMode[selectedItemId] = false;


                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int pos) {

                        int selectItemCount = 0;

                        for (int i = 0; i < itemCheckedOpearationMode.length; i++) {
                            if (itemCheckedOpearationMode[i] == true) {
                                selectItemCount++;
                            }
                        }

                        if (selectItemCount > 0) {
                            for (int i = 0; i < itemCheckedOpearationMode.length; i++) {
                                if (itemCheckedOpearationMode[i] == true) {
                                    //  selectedCountryList.add(aCountryL_itemsSelected.get(i));
                                    switch (i) {
                                        case 0:

//
                                            checkVillageSelection(user_name, password);
                                            break;
                                        case 1:
//
                                            checkFDPSelection(user_name, password);
                                            break;
                                        case 2:
//
                                            checkServiceCenterSelection(user_name, password);
                                            break;

                                        default:
                                            hideDialog();
                                            Toast.makeText(LoginActivity.this, "Select  any one", Toast.LENGTH_SHORT).show();
                                            break;

                                    }

                                }
                            }
                        } else {
                            hideDialog();
                            Toast.makeText(LoginActivity.this, "Select  any one", Toast.LENGTH_SHORT).show();
                        }


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface mdialog, int id) {

                         *//*   aL_itemsSelected.clear();
                            selectedCountryList.clear();*//*
                        hideDialog();
                        Toast.makeText(LoginActivity.this, "No Mode Selected." //+
                                //" Sync attempt denied.\n Select Country to Sync properly.\n Try to login again."
                                , Toast.LENGTH_SHORT).show();
                    }
                });
        mdialog = builder.create();
        mdialog.show();
//        } else {
        */
    /***//*
//        hideDialog();
//            Toast.makeText(LoginActivity.this, "No Mode Selected.", Toast.LENGTH_LONG).show();
//        }

    }*/

    String strCountryMode="";

    private void getCountryAlert(final String user_name, final String password, final int oparationFlag) {
        aCountryL_itemsSelected = (ArrayList<CountryNameItem>) insertCountryNameListToSArray();
        if (countryNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Country ");
            builder.setSingleChoiceItems(countryNameStringArray, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    strCountryMode="";
                    strCountryMode = countryNameStringArray[which];
                }
            });

            builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    selectedVillageList.clear();
                    selectedCountryList.clear();
                    selectedServiceCenterList.clear();
                    if(!strCountryMode.equals("")){

                        for (int i = 0; i < countryNameStringArray.length; i++) {
                            if (countryNameStringArray[i].equals(strCountryMode)) {
                                selectedCountryList.add(aCountryL_itemsSelected.get(i));
                            }
                        }
                        switch (oparationFlag) {
                            case UtilClass.REGISTRATION_OPERATION_MODE:
                                getVillageAlert(user_name, password, true);
                                break;
                            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                                getFDPAlert(user_name, password, true);
                                break;
                            case UtilClass.SERVICE_OPERATION_MODE:
                                getServiceCenterAlert(user_name, password, true);
                                break;
                        }
                    }else {
                        mdialog.dismiss();
                        hideDialog();
                    }

                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    aL_itemsSelected.clear();
                    selectedCountryList.clear();
                    dialog.dismiss();
                }
            });
            mdialog = builder.create();
            mdialog.show();
        }
    }

    /**
     * @see {@link #getCountryAlert(String, String, int)}
     * @param user_name
     * @param password
     * @param oparationFlag
     */
    /*private void getCountryAlert(final String user_name, final String password, final int oparationFlag) {
        int count = 1;
        aCountryL_itemsSelected = (ArrayList<CountryNameItem>) insertCountryNameListToSArray();

        itemChecked = new boolean[countryNameStringArray.length];
        if (countryNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select  A Country ");
            builder.setMultiChoiceItems(countryNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface mdialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) mdialog).getListView().getCheckedItemCount() <= 1) {
                                    itemChecked[selectedItemId] = isSelected;


                                } else {
                                    Toast.makeText(LoginActivity.this, "Select Country Maximum One", Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) mdialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aCountryL_itemsSelected.contains(selectedItemId)) {
                                aCountryL_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mdialog, int pos) {
                            //Your logic when OK button is clicked
                            // for safety
                            selectedVillageList.clear();
                            selectedCountryList.clear();
                            selectedServiceCenterList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i] == true) {
                                    selectedCountryList.add(aCountryL_itemsSelected.get(i));

                                }
                            }
                            switch (oparationFlag) {
                                case UtilClass.REGISTRATION_OPERATION_MODE:
                                    getVillageAlert(user_name, password, true);
                                    break;
                                case UtilClass.DISTRIBUTION_OPERATION_MODE:
                                    getFDPAlert(user_name, password, true);
                                    break;
                                case UtilClass.SERVICE_OPERATION_MODE:
                                    getServiceCenterAlert(user_name, password, true);
                                    break;

                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface mdialog, int id) {

                            aL_itemsSelected.clear();
                            selectedCountryList.clear();
                            Toast.makeText(LoginActivity.this, "No Country Selected. Sync attempt denied.\n Select Country to Sync properly.\n Try to login again.", Toast.LENGTH_SHORT).show();
                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            */
    /***//*
            Toast.makeText(LoginActivity.this, "No village assigned. Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }*/

    ArrayList<ServiceCenterItem> aLServiceCenter_itemsSelected = new ArrayList<ServiceCenterItem>();

    private void getServiceCenterAlert(final String user_name, final String password, boolean countrySpecificFlag) {

        aLServiceCenter_itemsSelected = (ArrayList<ServiceCenterItem>) insertServiceCenterNameListToSArray(countrySpecificFlag);

        itemChecked = new boolean[serviceCenterNameStringArray.length];
        if (serviceCenterNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Max TWO Service Centers ");
            builder.setMultiChoiceItems(serviceCenterNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;

                                    Log.d("REFAT----> position ", "" + selectedItemId);
                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two ServiceCenter ", Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aLServiceCenter_itemsSelected.contains(selectedItemId)) {
                                aLServiceCenter_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedServiceCenter();
                            selectedServiceCenterList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i] == true) {

                                    for (int j = 0; j < communityGroupList.size(); j++) {
                                        if (aLServiceCenter_itemsSelected.get(i).getServiceCenterCode().equals(communityGroupList.get(j).getSrvCenterCode())) {
                                            selectedServiceCenterList.add(aLServiceCenter_itemsSelected.get(i));
                                        }
                                    }


                                }
                            }

                            if (selectedServiceCenterList.size() > 0) {
                                JSONArray serviceCenterJSONarry = UtilClass.srvCenterCodeJSONConverter("LoginActivity", selectedServiceCenterList, db);
                                aLServiceCenter_itemsSelected.clear();
                                Log.d(TAG, " Service Center  jeson to string :" + serviceCenterJSONarry.toString());
                                /** 3 is operation code Service */
                                checkLogin(user_name, password, serviceCenterJSONarry, "3"); // checking online
                                editor.putInt(UtilClass.OPERATION_MODE, 3);
                                editor.commit();
                            } else {
                                hideDialog();
                                Toast.makeText(LoginActivity.this, "No Community Center in service center . Contact Admin.", Toast.LENGTH_LONG).show();
                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No Service Center assigned. Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }


    ArrayList<FDPItem> aLfdp_itemsSelected = new ArrayList<FDPItem>();

    private void getFDPAlert(final String user_name, final String password, boolean countrySpecificFlag) {

        aLfdp_itemsSelected = (ArrayList<FDPItem>) insertFDPNameListToSArray(countrySpecificFlag);

        itemChecked = new boolean[fdpNameStringArray.length];
        if (fdpNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Max TWO FDPs ");
            builder.setMultiChoiceItems(fdpNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;

                                    Log.d("REFAT----> position ", "" + selectedItemId);
                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two FDP", Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aLfdp_itemsSelected.contains(selectedItemId)) {
                                aLfdp_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedFDP();
                            selectedFdpList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i] == true) {
                                    selectedFdpList.add(aLfdp_itemsSelected.get(i));

                                }
                            }

                            JSONArray fdpJSONarry = UtilClass.fdpCodeJSONConverter("LoginActivity", selectedFdpList, db);
                            aLfdp_itemsSelected.clear();
                            Log.d(TAG, " FDp jeson to string :" + fdpJSONarry.toString());
                            /** 2 is operation code Distribution */
                            checkLogin(user_name, password, fdpJSONarry, "2"); // checking online
                            editor.putInt(UtilClass.OPERATION_MODE, 2);
                            editor.commit();

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                           /* Toast.makeText(LoginActivity.this, "No " + aL_itemsSelected.get(0).getGeoLayRName() + " Selected. Sync attempt denied." +
                                    "\n Select " + aL_itemsSelected.get(0).getGeoLayRName() + " to Sync properly.\n Try to login again.", Toast.LENGTH_SHORT).show();
                            aL_itemsSelected.clear();*/

                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No FDP assigned. Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }

/*  end aleart fro fdp */

    private void getVillageAlert(final String user_name, final String password, boolean countrySpecificFlag) {
//        int count = 1;
        aL_itemsSelected = (ArrayList<VillageItem>) insertVillageNameListToSArray(countrySpecificFlag);

        itemChecked = new boolean[villageNameStringArray.length];
        if (villageNameStringArray.length > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select " + aL_itemsSelected.get(0).getGeoLayRName() + " Maximum Two ");
            builder.setMultiChoiceItems(villageNameStringArray, null,
                    new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedItemId, boolean isSelected) {
                            if (isSelected) {

                                if (((AlertDialog) dialog).getListView().getCheckedItemCount() <= 2) {
                                    itemChecked[selectedItemId] = isSelected;

                                    Log.d("REFAT----> position ", "" + selectedItemId);
                                } else {
                                    Toast.makeText(LoginActivity.this, "You can not permitted to select more than Two " + aL_itemsSelected.get(0).getGeoLayRName(), Toast.LENGTH_SHORT).show();

                                    ((AlertDialog) dialog).getListView().setItemChecked(selectedItemId, false);
                                }
                            } else if (aL_itemsSelected.contains(selectedItemId)) {
                                aL_itemsSelected.remove(Integer.valueOf(selectedItemId));

                            }
                        }
                    })
                    .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int pos) {
                            //Your logic when OK button is clicked
                            db.deleteFromSelectedVillage();
                            selectedVillageList.clear();
                            for (int i = 0; i < itemChecked.length; i++) {
                                if (itemChecked[i] == true) {
                                    selectedVillageList.add(aL_itemsSelected.get(i));

                                }
                            }

                            JSONArray jaary = UtilClass.layR4CodeJSONConverter("LoginActivity", selectedVillageList, db);
                            aL_itemsSelected.clear();
                            Log.d(TAG, "jeson to string :" + jaary.toString());
                            /** for registration */
                            checkLogin(user_name, password, jaary, "1"); // checking online

                            editor.putInt(UtilClass.OPERATION_MODE, 1);
                            editor.commit();


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                            Toast.makeText(LoginActivity.this, "No " + aL_itemsSelected.get(0).getGeoLayRName() + " Selected. Sync attempt denied." +
                                    "\n Select " + aL_itemsSelected.get(0).getGeoLayRName() + " to Sync properly.\n Try to login again.", Toast.LENGTH_SHORT).show();
                            aL_itemsSelected.clear();

                        }
                    });
            mdialog = builder.create();
            mdialog.show();
        } else {
            hideDialog();
            Toast.makeText(LoginActivity.this, "No village assigned. Contact Admin.", Toast.LENGTH_LONG).show();
        }

    }


    /**
     * function to verify login details in mysql db
     */
    public void checkLogin(final String user_name, final String password, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Downloading  data .");
        pDialog.show();

        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, "all_data");

                Log.d(TAG, " After write data in txt \n"+selectedVilJArry);

                //  hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);


                boolean error = errorResult.equals("false") ? false : true;

                if (!error) {

                    // Experiments
                    downLoadRegNHouseHold(user_name, password, selectedVilJArry, operationMode);


                } else {
                    // Error in login. Invalid UserName or Password
                    hideDialog();
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_valid_user");
                params.put("user_name", user_name);
                params.put("password", password);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN house hold
     */
    public void downLoadRegNHouseHold(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg_hh";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_HOUSE_HOLD_DATA);

                Log.d(TAG, " After RegNHouseHold data in txt");
                //  hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);


                boolean error = errorResult.equals("false") ? false : true;

                if (!error) {

                    downLoadRegNMembers(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_house_hold");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN member data
     */
    public void downLoadRegNMembers(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_MEMBER_DATA);

                Log.d(TAG, " After wite data in txt");
                //  hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);


                boolean error = errorResult.equals("false") ? false : true;

                if (!error) {

                    downLoadRegNMemberProgGroup(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_member");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN member prog group code
     */
    public void downLoadRegNMemberProgGroup(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg_mem_grp";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, REG_MEMBER_PROG_GROUP_DATA);

                Log.d(TAG, " After wite data in txt");
                //  hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);


                boolean error = !errorResult.equals("false");

                if (!error) {

                    downLoadServiceData(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_mem_grp_data");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download Service
     */
    public void downLoadServiceData(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_reg";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, SERVICE_DATA);

                Log.d(TAG, " After write data in Service Data");
                //  hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

             /*   try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        downLoadAssignProgSrv(user_Name, pass_word, selectedVilJArry, operationMode);

                    } else {
                        // Error in login. Invalid UserName or Password
                        String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                        // hideDialog();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                String errorResult = response.substring(9, 14);


                boolean error = errorResult.equals("false") ? false : true;

                if (!error) {

                    downLoadAssignProgSrv(user_Name, pass_word, selectedVilJArry, operationMode);

                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_service_data");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    /**
     * function to verify login details download RegN AssProgSrv in mysql db
     */
    public void downLoadAssignProgSrv(final String user_Name, final String pass_word, final JSONArray selectedVilJArry, final String operationMode) {
        // Tag used to cancel the request
        String tag_string_req = "req_ass_prog";


        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.API_LINK, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                /***
                 * @deis: IN THIS STRING RESPONSE WRITE THE JSON DATA
                 *
                 */
                AppController.getInstance().getRequestQueue().getCache().clear();
                writeJSONToTextFile(response, "reg_ass_prog_srv_data");

                Log.d(TAG, " After wite data in txt");
                hideDialog();


                // DOING STRING OPERATION TO AVOID ALLOCATE CACHE MEMORY

                String errorResult = response.substring(9, 14);


                boolean error = errorResult.equals("false") ? false : true;

                if (!error) {


                    // @dec: IF GET NO ERROR  THAN GOTO THE MAIN ACTIVITY
                    setLogin(true);        // login success
                    // Launch main activity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    setUserID(user_Name);
                    setUserPassword(pass_word);
                    editor.putBoolean(IS_APP_FIRST_RUN, true);
                    editor.commit();

                    startActivity(intent);
                } else {
                    // Error in login. Invalid UserName or Password
                    String errorMsg = response.substring(response.indexOf("error_msg") + 11);
                    Toast.makeText(getApplicationContext(),
                            errorMsg, Toast.LENGTH_LONG).show();
                    // hideDialog();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error + " Stack Tracr = " + error.getStackTrace() + " Detail = " + error.getMessage());
                // hide the mdialog
                hideDialog();
                showAlert("Failed to retrieve data\r\nPlease try again checking your internet connectivity, Username and Password.");


            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("key", "PhEUT5R251");
                params.put("task", "is_down_load_reg_assn_prog");
                params.put("user_name", user_Name);
                params.put("password", pass_word);
                params.put("lay_r_code_j", selectedVilJArry.toString());
                params.put("operation_mode", operationMode);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
