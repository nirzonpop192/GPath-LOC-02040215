package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexAdapter;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQuesDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQusDataModelAdapter;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DTResponseActivity extends BaseActivity {

    private Spinner spTableName;
    private Spinner spAward;
    private Spinner spProgram;
    private TextView tvActivityTitle;
    private TextView tvDate;
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private SQLiteHandler sqlH;
    private Context mContext = DTResponseActivity.this;
    private String idAward;
    private String strAward;
    private String idDonor;
    private String idTable;
    private String strTable;
    private String idProgram;
    private String strProgram;
    private ListView lv_DT_QList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dtresponse);
        inti();
        Intent intent = getIntent();
        DynamicDataIndexDataModel dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        idAward = dyIndex.getAwardCode();
        strAward = dyIndex.getAwardName();
        idTable = dyIndex.getDtBasicCode();
        strTable = dyIndex.getDtTittle();
        idProgram = dyIndex.getProgramCode();
        strProgram = dyIndex.getProgramName();
        tvActivityTitle.setText(dyIndex.getPrgActivityTitle());

        loadTable(dyIndex.getcCode());

        setListener();
    }

    private void inti() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
    }

    private void setListener() {
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate();
            }
        });

    }

    private void viewReference() {

        spTableName = (Spinner) findViewById(R.id.sp_dtResponse_table_name);
        spAward = (Spinner) findViewById(R.id.sp_dtResponse_award);
        spProgram = (Spinner) findViewById(R.id.sp_dtResponse_program);
        tvActivityTitle = (TextView) findViewById(R.id.tv_dtResponse_activity_title);
        tvDate = (TextView) findViewById(R.id.txt_dtResponse_date);
        lv_DT_QList = (ListView) findViewById(R.id.lv_DTQList);


    }

    public void setDate() {
        new DatePickerDialog(DTResponseActivity.this, datepickerD, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    DatePickerDialog.OnDateSetListener datepickerD = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };

    public void updateDate() {
        tvDate.setText(format.format(calendar.getTime()));
    }


    private void loadTable(final String cCode) {

        int position = 0;
        String sql = "SELECT " + "dtCPgr." + SQLiteHandler.DT_BASIC_COL + " AS dtBasicCode  " +
                " , " + "dtB." + SQLiteHandler.DT_TITLE_COL + "  " +
                "  FROM " + SQLiteHandler.DT_COUNTRY_PROGRAM_TABLE + " AS dtCPgr  " +
                " LEFT JOIN " + SQLiteHandler.DT_BASIC_TABLE + "  as dtB  " +
                " ON dtB." + SQLiteHandler.DT_BASIC_COL + " = dtCpgr." + SQLiteHandler.DT_BASIC_COL + "   " +
                " WHERE dtCPgr." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' ";


        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, sql, null, false);


        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listProgram);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

        spTableName.setAdapter(dataAdapter);


        if (idTable != null) {
            for (int i = 0; i < spTableName.getCount(); i++) {
                String table = spTableName.getItemAtPosition(i).toString();
                if (table.equals(strTable)) {
                    position = i;
                }
            }
            spTableName.setSelection(position);
        }


        spTableName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strTable = ((SpinnerHelper) spTableName.getSelectedItem()).getValue();
                idTable = ((SpinnerHelper) spTableName.getSelectedItem()).getId();
                loadAward(cCode);
                loadDT_questionView(idTable);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    /**
     * LOAD :: Award
     */
    private void loadAward(final String cCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.ADM_AWARD_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "'";

        List<SpinnerHelper> listAward = sqlH.getListAndID(SQLiteHandler.ADM_AWARD_TABLE, criteria, null, false);
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, listAward);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);

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

                    loadProgram(cCode, idDonor, idAward);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Award Spinner

    /**
     * LOAD :: Program
     */
    private void loadProgram(final String cCode, final String donorCode, final String awardCode) {

        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.AWARD_CODE_COL + "='" + awardCode + "'"
                + " AND " + SQLiteHandler.COUNTRY_PROGRAM_TABLE + "." + SQLiteHandler.DONOR_CODE_COL + "='" + donorCode + "'";

        List<SpinnerHelper> listProgram = sqlH.getListAndID(SQLiteHandler.COUNTRY_PROGRAM_TABLE, criteria, null, false);


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
        }


        spProgram.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getValue();
                idProgram = ((SpinnerHelper) spProgram.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // end Load Spinner


    public void loadDT_questionView(String dtBasicCode) {

        List<DynamicTableQuesDataModel> dataList = sqlH.getDynamicQuesionList(dtBasicCode);
        DynamicTableQusDataModelAdapter adapter=null;

        ArrayList<DynamicTableQuesDataModel> dataArray = new ArrayList<DynamicTableQuesDataModel>();
        if (dataList.size() != 0) {

            dataArray.clear();

            for (DynamicTableQuesDataModel data : dataList) {
                /**
                 * add contacts data in arrayList
                 */
                dataArray.add(data);
            }
/**
 * Assign the Adapter in list
 */
             adapter = new DynamicTableQusDataModelAdapter((Activity) mContext, dataArray);


        }

        if (adapter != null) {
            if (adapter.getCount() != 0) {
                adapter.notifyDataSetChanged();
                lv_DT_QList.setAdapter(adapter);
            } else {
                new ADNotificationManager().showInfromDialog(mContext, "NO Data", "No data Found");

            }

        }

    }

}
