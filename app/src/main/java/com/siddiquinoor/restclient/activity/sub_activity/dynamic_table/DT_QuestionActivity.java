package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.DTQResponseModeDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLiteQuery;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQuesDataModel;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;

import java.util.ArrayList;
import java.util.List;

public class DT_QuestionActivity extends BaseActivity {

    public static final String TEXT = "Text";
    public static final String NUMBER = "Number";
    public static final String Date = "Date";
    public static final String DateTime = "Datetime";
    public static final String Textbox = "Textbox";
    public static final String COMBO_BOX = "Combobox";
    public static final String GEO_LAYER_3 = "Geo Layer 3";
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
     * Dynamic view
     */
    private Spinner dt_spinner;
    private EditText edt;

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
                 *  TODO: 9/29/2016  save method & update method
                 */
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

    private void displayQuestion(DynamicTableQuesDataModel qusObject) {
        tv_DtQuestion.setText(qusObject.getqText());
        loadAnswer(qusObject.getqResModeCode());

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


    private void loadAnswer(String respondMode) {

        DTQResponseModeDataModel dtqResponseModeDataModel = sqlH.getAnswerResponse(respondMode);
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
                    tv_dtTimePicker.setVisibility(View.VISIBLE);
                    tv_dtTimePicker.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case COMBO_BOX:

                    dt_spinner.setVisibility(View.VISIBLE);
                    // // TODO: 9/30/2016 use switch for udf

                    String cCode="0002";
                    loadSpinnerList(cCode,resLupText);


                    break;
            }// end of switch
        }// end of if

    }

    /**
     *  todo details in latter
     * @param cCode
     * @param resLupText
     */
    private void loadSpinnerList(final String cCode,final String resLupText) {
        int position = 0;

        String udf;

        List<SpinnerHelper> list=new ArrayList<SpinnerHelper>();
        switch (resLupText) {

            case GEO_LAYER_3:

                 udf = "SELECT " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UCODE_COL + ", " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.UNITE_NAME_COL
                         + " FROM " + SQLiteHandler.UNIT_TABLE
                        + " WHERE " + SQLiteHandler.UNIT_TABLE + "." + SQLiteHandler.COUNTRY_CODE_COL + "='" + cCode+"'";

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


                String strUnion = ((SpinnerHelper) dt_spinner.getSelectedItem()).getValue();
                String idUnion = ((SpinnerHelper) dt_spinner.getSelectedItem()).getId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
