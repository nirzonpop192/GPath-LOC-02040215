package com.siddiquinoor.restclient.activity.sub_activity.graduation_sub;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.siddiquinoor.restclient.activity.GraduationActivity;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.views.adapters.GraduationGridDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class GraduationUpdate extends BaseActivity implements View.OnClickListener {

    private final String TAG = GraduationUpdate.class.getName();


    private View removeBtn;// the remove
    private GraduationGridDataModel mGraduation;
    private TextView tv_award,tv_program;

    private TextView tv_criteria;
    private TextView tv_village;
    private TextView tv_hhId;
    private TextView tv_memberId;
    private TextView tv_memberName;
    private TextView tv_grdDate;
  //  private String srtGrdDate;
    private Spinner sp_grdReason;
    private SQLiteHandler sqlH;
    private Context mContext;
    private String idGRD;
    private String strGRDTitle;//Reason

    private Button btn_Save;// save Graduation
    private Button btn_Search;// save Graduation
    private Button btn_Delete;// save Graduation

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
  //  private SimpleDateFormat format_US = new SimpleDateFormat("MM-yyyy-dd", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    private Button btn_home;
    private String EntryBy;
    private String EntryDate;
    private ADNotificationManager dialog;

    private String srtGrdDate;
    private SimpleDateFormat formatUSA = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);


    public String getSrtGrdDate() {
        return srtGrdDate;
    }

    public void setSrtGrdDate(String srtGrdDate) {
        this.srtGrdDate = srtGrdDate;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduation_update);
        mContext = GraduationUpdate.this;
        dialog=new ADNotificationManager();
        viewReference();
        Intent intent = getIntent();
        mGraduation = intent.getExtras().getParcelable("GraduationDetails");//getParcelableExtra("GraduationDetails");

        setAllTextToTextView(mGraduation);
        loadReason(mGraduation.getCountryCode(), mGraduation.getProgram_code(), mGraduation.getService_code());



    }

    private void setAllTextToTextView( GraduationGridDataModel data) {
        tv_award.setText(data.getAward_name());
        tv_program.setText(data.getProgram_name());
        tv_criteria.setText(data.getCriteria_name());
        tv_village.setText(data.getVillageName());
        tv_hhId.setText(data.getHh_id());
        tv_memberId.setText(data.getMember_Id());
        tv_memberName.setText(data.getMember_name());
        tv_grdDate.setText(data.getGraduationDate());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_gradu_GRDDate:
                setGraduationDate();
                break;

            case R.id.btn_Graduation_Save:
                boolean invalid = false;

                try {
                    EntryBy = getStaffID();
                    EntryDate = getDateTime();
                    HashMap<String, String> dateRange = sqlH.getDateRange(mGraduation.getCountryCode());
                    String start_date = dateRange.get("sdate");
                    String end_date = dateRange.get("edate");
                    Log.d(TAG, " start_date :" + start_date + " end_date " + end_date);
                    if (tv_grdDate != null && start_date != null && end_date != null) {
                        if (!getValidDateRange(getSrtGrdDate(), start_date, end_date)) {
                            invalid = true;
                            Log.d(TAG, " invalid " + invalid+" cause grd date ");
                            dialog.showInvalidDialog(mContext, "Date", "Invalid date specified");
                          //  Toast.makeText(mContext, "Registration date is not a valid date! Please select a valid date within the range!!", Toast.LENGTH_LONG).show();

                        } else if(Integer.parseInt(idGRD)<0){
                            invalid = true;
                            Log.d(TAG, " invalid " + invalid);
                            Toast.makeText(mContext, "the reason is not Selected ! Please select Reason!!", Toast.LENGTH_LONG).show();
                        }
                        else if (invalid == false) {
                            Log.d(TAG, " invalid " + invalid);

                            if(sqlH.ifExistsInRegNAssProgSrv(mGraduation)){
                                /** the update operation  for local database*/
                                sqlH.editMemberDataIn_RegNAsgProgSrv(getSrtGrdDate(), idGRD, EntryBy, EntryDate, mGraduation.getCountryCode(), mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                                        mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                                        mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                                        mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
                                /** the update operation  for SQL Server or Web database*/
                                SQLServerSyntaxGenerator graduationQuery= new SQLServerSyntaxGenerator();
                                graduationQuery.setAdmCountryCode(mGraduation.getCountryCode());
                                graduationQuery.setLayR1ListCode(mGraduation.getDistrictCode());
                                graduationQuery.setLayR2ListCode(mGraduation.getUpazillaCode());
                                graduationQuery.setLayR3ListCode(mGraduation.getUnitCode());
                                graduationQuery.setLayR4ListCode(mGraduation.getVillageCode());
                                graduationQuery.setAdmAwardCode(mGraduation.getAward_code());
                                graduationQuery.setAdmDonorCode(mGraduation.getDonor_code());
                                graduationQuery.setHHID(mGraduation.getHh_id());
                                graduationQuery.setMemID(mGraduation.getMember_Id());
                                graduationQuery.setProgCode(mGraduation.getProgram_code());
                                graduationQuery.setSrvCode(mGraduation.getService_code());
                                graduationQuery.setGRDCode(idGRD);
                                graduationQuery.setGRDDate(getSrtGrdDate());

                                graduationQuery.setEntryBy(EntryBy);
                                graduationQuery.setEntryDate(EntryDate);
                                sqlH.insertIntoUploadTable(graduationQuery.updateGraduation());

                                Toast.makeText(mContext, "The data is saved", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(mContext, "This data is not exit in RegNAssSrvProg!", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {
                        Toast.makeText(mContext, "No valid date range found!", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException pe) {
                    pe.printStackTrace();
                    Toast.makeText(mContext, "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_Graduation_Search:
                finish();
                Intent ibackToGraduation = new Intent(mContext, GraduationActivity.class);
                ibackToGraduation.putExtra("ID_COUNTRY", mGraduation.getCountryCode());
                startActivity(ibackToGraduation);
                break;
            case R.id.btnHomeFooter:
                Intent iHome = new Intent(mContext, MainActivity.class);
                startActivity(iHome);

                break;
            /* delete operation */
            case R.id.btn_Graduation_Delete:
               String grdMemberReason=sqlH.getGrdCodeForMember_RegNAssProgSrv(mGraduation.getCountryCode(),
                       mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                       mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                       mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                       mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
                String grdDefaultExitReason=sqlH.getGRDDefaultExitReason(mGraduation.getProgram_code(),
                        mGraduation.getService_code());
                if (grdMemberReason.equals("")||grdDefaultExitReason.equals("")){
                    Log.d(TAG,"grdMemberReason ="+grdMemberReason+" , grdDefaultExitReason = "+grdDefaultExitReason);
                }
                else {
                    // If the grdCode is equal to Exit then
                    if (grdMemberReason.equals(grdDefaultExitReason)){
                        Toast.makeText(mContext,"You are not able to Delete this member",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            EntryBy = getStaffID();
                            EntryDate = getDateTime();
                            sqlH.editMemberDataIn_RegNAsgProgSrv(null,
                                    null,
                                    EntryBy, EntryDate, mGraduation.getCountryCode(),
                                    mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                                    mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                                    mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                                    mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());

                            SQLServerSyntaxGenerator graduationQuery= new SQLServerSyntaxGenerator();
                            graduationQuery.setAdmCountryCode(mGraduation.getCountryCode());
                            graduationQuery.setLayR1ListCode(mGraduation.getDistrictCode());
                            graduationQuery.setLayR2ListCode(mGraduation.getUpazillaCode());
                            graduationQuery.setLayR3ListCode(mGraduation.getUnitCode());
                            graduationQuery.setLayR4ListCode(mGraduation.getVillageCode());
                            graduationQuery.setAdmAwardCode(mGraduation.getAward_code());
                            graduationQuery.setAdmDonorCode(mGraduation.getDonor_code());
                            graduationQuery.setHHID(mGraduation.getHh_id());
                            graduationQuery.setMemID(mGraduation.getMember_Id());
                            graduationQuery.setProgCode(mGraduation.getProgram_code());
                            graduationQuery.setSrvCode(mGraduation.getService_code());
                            graduationQuery.setEntryBy(EntryBy);
                            graduationQuery.setEntryDate(EntryDate);

                            sqlH.insertIntoUploadTable(graduationQuery.updateGraduation());
                            //Toast.makeText(mContext, "The data is delete", Toast.LENGTH_SHORT).show();
                         /*  GraduationDateCode dateCode= sqlH.getGRDPeopleDetial(mGraduation.getCountryCode(),
                                    mGraduation.getDistrictCode(), mGraduation.getUpazillaCode(),
                                    mGraduation.getUnitCode(), mGraduation.getVillageCode(), mGraduation.getHh_id(),
                                    mGraduation.getMember_Id(), mGraduation.getProgram_code(),
                                    mGraduation.getService_code(), mGraduation.getDonor_code(), mGraduation.getAward_code());
                            tv_grdDate.setText(dateCode.getGrdDate());
                            idGRD=dateCode.getGrdCode();*/

                            tv_grdDate.setText("");

                            loadReason(mGraduation.getCountryCode(), mGraduation.getProgram_code(), mGraduation.getService_code());
                            Toast.makeText(mContext, "The data is delete", Toast.LENGTH_SHORT).show();
                        }catch (ParseException pe) {
                            pe.printStackTrace();
                            Toast.makeText(mContext, "Wrong Date Format, parse error!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                break;
        }
    }


    private void viewReference() {
        tv_award = (TextView) findViewById(R.id.tv_gradu_award);
        tv_program = (TextView) findViewById(R.id.tv_gradu_Program);
        tv_criteria = (TextView) findViewById(R.id.tv_gradu_Criteria);
        tv_village = (TextView) findViewById(R.id.tv_gradu_Village);
        tv_hhId = (TextView) findViewById(R.id.tv_gradu_hhID);
        tv_memberId = (TextView) findViewById(R.id.tv_gradu_MemberID);
        tv_memberName = (TextView) findViewById(R.id.tv_gradu_MemberName);
        tv_grdDate = (TextView) findViewById(R.id.tv_gradu_GRDDate);
        tv_grdDate.setOnClickListener(this);
        sp_grdReason = (Spinner) findViewById(R.id.spGraduationReason);
        removeBtn = findViewById(R.id.btnRegisterFooter);
        removeBtn.setVisibility(View.GONE);
        sqlH = new SQLiteHandler(mContext);
        btn_Save = (Button) findViewById(R.id.btn_Graduation_Save);
        btn_Save.setOnClickListener(this);

        btn_Search = (Button) findViewById(R.id.btn_Graduation_Search);
        btn_Search.setOnClickListener(this);

        btn_home = (Button) findViewById(R.id.btnHomeFooter);
        btn_home.setOnClickListener(this);
        btn_Delete=(Button) findViewById(R.id.btn_Graduation_Delete);
        btn_Delete.setOnClickListener(this);


    }

    /**
     * LOAD :: Reason
     */
    private void loadReason(String cCode, String pCode, String srvCode) { // Graduation Reason
        int position = 0;

        String criteria = " WHERE " + SQLiteHandler.PROGRAM_CODE_COL + " = '" + pCode + "' And " + SQLiteHandler.SERVICE_CODE_COL +
                " = '" + srvCode + "' And " + SQLiteHandler.DEFAULT_CAT_ACTIVE_COL + " = 'N' And " +
                SQLiteHandler.DEFAULT_CAT_EXIT_COL + " = 'N' ORDER BY " + SQLiteHandler.GRD_TITLE_COL;

        // Spinner Drop down elements for District
        List<SpinnerHelper> listDistrict = sqlH.getListAndID(SQLiteHandler.REG_N_LUP_GRADUATION_TABLE, criteria, cCode, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listDistrict);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        sp_grdReason.setAdapter(dataAdapter);


        if (idGRD != null) {
            for (int i = 0; i < sp_grdReason.getCount(); i++) {
                String district = sp_grdReason.getItemAtPosition(i).toString();
                if (district.equals(strGRDTitle)) {
                    position = i;
                }
            }
            sp_grdReason.setSelection(position);
        }


        sp_grdReason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //strDistrict =  ( (SpinnerHelper) sp_grdReason.getSelectedItem () ).getId();
                strGRDTitle = ((SpinnerHelper) sp_grdReason.getSelectedItem()).getValue();
                idGRD = ((SpinnerHelper) sp_grdReason.getSelectedItem()).getId();

                //loadUpazilla(idCountry);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner Reason


    /**
     * Time picker will appear
     */
    public void setGraduationDate() {
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

    public void updateRegDate() {


           /* srtGrdDate=format.format(calendar.getTime());

            tv_grdDate.setText(srtGrdDate);*/
        setSrtGrdDate(format.format(calendar.getTime()));
        tv_grdDate.setText(formatUSA.format(calendar.getTime()));
    }

    /**
     * to off Back press button
     */

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
