package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.siddiquinoor.restclient.data_model.DTQResponseModeDataModel;
import com.siddiquinoor.restclient.data_model.DT_ATableDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQuesDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DT_QuestionActivity extends BaseActivity {

    public static final String TEXT = "Text";
    public static final String NUMBER = "Number";
    public static final String Date = "Date";
    public static final String DateTime = "Datetime";
    public static final String Textbox = "Textbox";
    public static final String COMBO_BOX = "Combobox";
    public static final String GEO_LAYER_3 = "Geo Layer 3";

    public static final String GEO_LAYER_2 = "Geo Layer 2";
    public static final String GEO_LAYER_1 = "Geo Layer 1";
    public static final String GEO_LAYER_4 = "Geo Layer 4";
    public static final String GEO_LAYER_ADDRESS = "Geo Layer Address";
    public static final String SERVICE_SITE = "Service Site";
    public static final String DISTRIBUTION_POINT = "Distribution Point";
    public static final String COMMUNITY_GROUP = "Community Group";

    private SQLiteHandler sqlH;
    private final Context mContext = DT_QuestionActivity.this;
    private DynamicDataIndexDataModel dyIndex;
    private int totalQuestion;
    private TextView tv_DtQuestion, tv_dtTimePicker;
    private Button btnNextQues;
    private DynamicTableQuesDataModel mQuestion;
    private Button btnPreviousQus;
    int mQusIndex;
    /**
     * For Date time picker
     */
    private SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    private Calendar calendar = Calendar.getInstance();
    /**
     * Dynamic view
     */
    private Spinner dt_spinner;
    private EditText edt;

    private String idSpinner;
    private String strSpinner;
    /**
     * #mDTATable is Dterminator of Check Box item &  value
     * it is assigned by {@link #displayQuestion(DynamicTableQuesDataModel)} method
     */
    List<DT_ATableDataModel> mDTATableList;

    /**
     * @param sIState savedInstanceState
     */

    @Override
    protected void onCreate(Bundle sIState) {
        super.onCreate(sIState);
        setContentView(R.layout.activity_dt__qustion);
        inti();
        DynamicTableQuesDataModel qus = fistQuestion(dyIndex.getDtBasicCode());
        displayQuestion(qus);

        setListener();


    }


    private void setListener() {
        btnNextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * TODO: SET VALIDATION
                 *
                 */
                if (mQuestion.getAllowNullFlag().equals("N")) {
                    if (tv_dtTimePicker.getVisibility() == View.VISIBLE) {
                        if (tv_dtTimePicker.getText().toString().equals("Click for Date")) {
                            Toast.makeText(DT_QuestionActivity.this, "Set Date First ", Toast.LENGTH_SHORT).show();
                        } else {
                            nextQuestion();
                        }
                    } else if (dt_spinner.getVisibility() == View.VISIBLE) {

                        if (idSpinner.equals("00")) {
                            Toast.makeText(mContext, "Select Item", Toast.LENGTH_SHORT).show();
                        }

                        Log.d("MOR", idSpinner);

                    }
                } else {


                    /**
                     *  TODO: 9/29/2016  save method & update method
                     */
                    saveData("");


                    /**
                     * NEXT QUESTION
                     */
                    nextQuestion();


                }// end of else where ans is not magneto


            }
        });
        btnPreviousQus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hideViews();
                --mQusIndex;
                /**
                 * to check does index exceed the max value
                 */
                if (mQusIndex >= 0) {
                    DynamicTableQuesDataModel nextQus = loadPreviousQuestion(dyIndex.getDtBasicCode(), mQusIndex);

                    displayQuestion(nextQus);
                } else if (mQusIndex < 0) {
                    mQusIndex = 0;
//                    Log.d("MOR", "mQusIndex: " + mQusIndex);
                }

            }
        });
    }

    private void saveData(String ansValue) {
        saveOnResponseTable(ansValue);
    }


    private void saveOnResponseTable(String ansValue) {
        /**
         * Todo: implemet the mDTATable
         */


        String DTBasic = dyIndex.getDtBasicCode();
        String AdmCountryCode = dyIndex.getcCode();
        String AdmDonorCode = dyIndex.getDonorCode();
        String AdmAwardCode = dyIndex.getAwardCode();
        String AdmProgCode = dyIndex.getProgramCode();
        String DTEnuID = getStaffID();

        String DTQCode = mQuestion.getDtQCode();
        String DTACode = null;
        String DTRSeq = null;
        String DTAValue = null;
        String ProgActivityCode = dyIndex.getProgramActivityCode();
        String DTTimeString = null;
        try {
            DTTimeString = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String OpMode = dyIndex.getOpMode();
        String OpMonthCode = dyIndex.getOpMonthCode();
        String DataType = null;


        Log.d("MOR", " for save process"
                + "\n DTBasic          : " + DTBasic
                + "\n AdmCountryCode   : " + AdmCountryCode
                + "\n AdmDonorCode     : " + AdmDonorCode
                + "\n AdmAwardCode     : " + AdmAwardCode
                + "\n AdmProgCode      : " + AdmProgCode
                + "\n DTEnuID          : " + DTEnuID
                + "\n DTQCode          : " + DTQCode
                + "\n DTACode          : " + DTACode
                + "\n DTRSeq           : " + DTRSeq
                + "\n DTAValue         : " + DTAValue
                + "\n ProgActivityCode : " + ProgActivityCode
                + "\n DTTimeString     : " + DTTimeString
                + "\n OpMode           : " + OpMode
                + "\n OpMonthCode      : " + OpMonthCode
                + "\n DataType         : " + DataType
        );

              /*  *//**
         * main Exiquation
         *//*

                sqlH.addIntoDTResponseTable(DTBasic, AdmCountryCode, AdmDonorCode, AdmAwardCode, AdmProgCode, DTEnuID, DTQCode, DTACode,
                        DTRSeq, DTAValue, ProgActivityCode, DTTimeString, OpMode, OpMonthCode, DataType);
*/
    }

    /**
     * Load the next Ques
     */

    private void nextQuestion() {
        ++mQusIndex;
        /**
         * to check does index exceed the max value
         */
        hideViews();
        if (mQusIndex < totalQuestion) {
            DynamicTableQuesDataModel nextQus = loadNextQuestion(dyIndex.getDtBasicCode(), mQusIndex);

            displayQuestion(nextQus);
        } else if (mQusIndex == totalQuestion) {
            mQusIndex = totalQuestion - 1;
//                    Log.d("MOR", "mQusIndex: " + mQusIndex);
        }
    }

    /**
     * @param qusObject DTQTable object
     *                  {@link #mDTATableList} must be assigned before invoking {@link #loadDT_QResMode(String)}
     */

    private void displayQuestion(DynamicTableQuesDataModel qusObject) {
        tv_DtQuestion.setText(qusObject.getqText());
        mDTATableList = sqlH.getDTA_Table(qusObject.getDtBasicCode(), qusObject.getDtQCode());

        loadDT_QResMode(qusObject.getqResModeCode());


    }

    /**
     * initial state
     * also views refer
     *
     * @see :{@link #viewReference()}
     */

    private void inti() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
        Intent intent = getIntent();
        dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        totalQuestion = intent.getIntExtra(KEY.DYNAMIC_T_QUES_SIZE, 0);
        mQusIndex = 0;
        hideViews();

    }

    /**
     * Hide the view
     */

    private void hideViews() {
        tv_dtTimePicker.setVisibility(View.GONE);
        edt.setVisibility(View.GONE);
        dt_spinner.setVisibility(View.GONE);
    }

    private void viewReference() {
        tv_DtQuestion = (TextView) findViewById(R.id.tv_DtQuestion);


        btnNextQues = (Button) findViewById(R.id.btnHomeFooter);
        btnPreviousQus = (Button) findViewById(R.id.btnRegisterFooter);
        btnNextQues.setText("Next");
        btnPreviousQus.setText("Previous");
        /**
         * dynamic view reference
         */

        tv_dtTimePicker = (TextView) findViewById(R.id.tv_dtTimePicker);
        edt = (EditText) findViewById(R.id.edt);
        dt_spinner = (Spinner) findViewById(R.id.dt_sp);


    }

    /**
     * the method invoked once  in {@link  #onCreate(Bundle)}
     *
     * @param dtBasic dtBasic code as primary key
     * @return Ques object  of first index
     */

    private DynamicTableQuesDataModel fistQuestion(final String dtBasic) {
        return loadQuestion(dtBasic, 0);
    }

    /**
     * invoking in {@link #btnNextQues}
     *
     * @param dtBasic  dtBasic code as primary key
     * @param qusIndex Qus  index
     * @return Ques object  of given index
     */
    private DynamicTableQuesDataModel loadNextQuestion(final String dtBasic, final int qusIndex) {

        return loadQuestion(dtBasic, qusIndex);
    }

    /**
     * invoking in {@link #btnPreviousQus}
     *
     * @param dtBasic  dtBasic code as primary key
     * @param qusIndex Qus  index
     * @return Ques object  of given index
     */

    private DynamicTableQuesDataModel loadPreviousQuestion(final String dtBasic, final int qusIndex) {

        return loadQuestion(dtBasic, qusIndex);
    }


    public DynamicTableQuesDataModel loadQuestion(final String dtBasic, final int qusIndex) {
        mQuestion = sqlH.getSingleDynamicQuestion(dtBasic, qusIndex);
        return mQuestion;
    }

    /**
     * loadDT_QResMode(String) is equivalent to ans view loader
     *
     * @param resMode repose Mode
     */
    private void loadDT_QResMode(String resMode) {

        DTQResponseModeDataModel dtqResponseModeDataModel = sqlH.getDT_QResMode(resMode);
        String dataType = dtqResponseModeDataModel.getDtDataType();
        String responseControl = dtqResponseModeDataModel.getDtResponseValueControl();
        String resLupText = dtqResponseModeDataModel.getDtQResLupText();
        if (dataType != null) {
            switch (responseControl) {
                case Textbox:

                    switch (dataType) {
                        case TEXT:
                            edt.setVisibility(View.VISIBLE);
                            edt.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        case NUMBER:
                            edt.setVisibility(View.VISIBLE);
                            edt.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;

                    }// end of switch

                    break;


                case DateTime:
                    /**
                     * use it latter {@link #getCurrentDate()}
                     *
                     * getCurrentDate();
                     */

                    tv_dtTimePicker.setVisibility(View.VISIBLE);
                    tv_dtTimePicker.setInputType(InputType.TYPE_CLASS_NUMBER);
                    tv_dtTimePicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setDate();
                        }
                    });

                    break;
                case COMBO_BOX:

                    dt_spinner.setVisibility(View.VISIBLE);
                    // // TODO: 9/30/2016 use switch for udf


                    loadSpinnerList(dyIndex.getcCode(), resLupText);


                    break;
                case "CheckBox":
                    mDTATableList.size()
                            // // TODO: 10/2/2016  worke Shubo vai
                    break;
            }// end of switch
        }// end of if

    }


    /**
     * Date & time Session
     */
    public void getCurrentDate() {
        Calendar calendar = Calendar.getInstance();

        String strDate = mFormat.format(calendar.getTime());
        displayDate(strDate);
    }

    private void displayDate(String strDate) {
        tv_dtTimePicker.setText(strDate);
    }

    public void setDate() {
        new DatePickerDialog(mContext, datepickerD, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * date Time picker Listener
     */

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
        displayDate(mFormat.format(calendar.getTime()));

    }


    /**
     * todo details in latter
     *
     * @param cCode      Country Code
     * @param resLupText res lup
     */
    private void loadSpinnerList(final String cCode, final String resLupText) {
        int position = 0;

        String udf;

        List<SpinnerHelper> list = new ArrayList<SpinnerHelper>();
        switch (resLupText) {

            case GEO_LAYER_3:

                udf = "SELECT " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UCODE_COL
                        + ", " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                        + " FROM " + SQLiteHandler.UNIT_TABLE
                        + " WHERE " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);

                break;
            case GEO_LAYER_2:
                udf = "SELECT " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPCODE_COL
                        + ", " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.UPZILLA_NAME_COL
                        + " FROM " + SQLiteHandler.UPAZILLA_TABLE
                        + " WHERE " + SQLiteHandler.UPAZILLA_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);

                break;

            case GEO_LAYER_1:

                udf = "SELECT " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_CODE_COL
                        + ", " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.DISTRICT_NAME_COL
                        + " FROM " + SQLiteHandler.DISTRICT_TABLE
                        + " WHERE " + SQLiteHandler.DISTRICT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            case GEO_LAYER_4:

                udf = "SELECT " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VCODE_COL
                        + ", " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.VILLAGE_NAME_COL
                        + " FROM " + SQLiteHandler.VILLAGE_TABLE
                        + " WHERE " + SQLiteHandler.VILLAGE_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            case GEO_LAYER_ADDRESS:

                udf = "SELECT " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_CODE_COL
                        + ", " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.REGN_ADDRESS_LOOKUP_NAME_COL
                        + " FROM " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE
                        + " WHERE " + SQLiteHandler.LUP_REGN_ADDRESS_LOOKUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            case SERVICE_SITE:

                udf = "SELECT " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.SERVICE_CENTER_CODE_COL
                        + ", " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.SERVICE_CENTER_NAME_COL
                        + " FROM " + SQLiteHandler.SERVICE_CENTER_TABLE
                        + " WHERE " + SQLiteHandler.SERVICE_CENTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            case DISTRIBUTION_POINT:

                udf = "SELECT " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_CODE_COL
                        + ", " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.FDP_NAME_COL
                        + " FROM " + SQLiteHandler.FDP_MASTER_TABLE
                        + " WHERE " + SQLiteHandler.FDP_MASTER_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            case COMMUNITY_GROUP:
                udf = "SELECT " + SQLiteHandler.COMMUNITY_GROUP_TABLE + "." + SQLiteHandler.GROUP_CODE_COL
                        + ", " + SQLiteHandler.COMMUNITY_GROUP_TABLE + "." + SQLiteHandler.GROUP_NAME_COL
                        + " FROM " + SQLiteHandler.COMMUNITY_GROUP_TABLE
                        + " WHERE " + SQLiteHandler.COMMUNITY_GROUP_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode + "'";

                list = sqlH.getListAndID(SQLiteHandler.CUSTOM_QUERY, udf, cCode, false);
                break;

            default:
                list.clear();
                break;
        }


        // Spinner Drop down elements for District


        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(this, R.layout.spinner_layout, list);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        dt_spinner.setAdapter(dataAdapter);
        /**
         * todo Retrieving Code for previous button
         *
         */


/*        if (idUnion != null) {
            for (int i = 0; i < dt_spinner.getCount(); i++) {
                String union = dt_spinner.getItemAtPosition(i).toString();
                if (union.equals(strUnion)) {
                    position = i;
                }
            }
            dt_spinner.setSelection(position);
        }*/


        dt_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                strSpinner = ((SpinnerHelper) dt_spinner.getSelectedItem()).getValue();
                idSpinner = ((SpinnerHelper) dt_spinner.getSelectedItem()).getId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
