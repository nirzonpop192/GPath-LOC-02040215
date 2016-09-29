package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.data_model.DTQResponseModeDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicTableQuesDataModel;

public class DT_QuestionActivity extends BaseActivity {

    public static final String TEXT = "Text";
    public static final String NUMBER = "Number";
    public static final String Date = "Date";
    public static final String DateTime = "Datetime";
    public static final String Textbox = "Textbox";
    private SQLiteHandler sqlH;
    private final Context mContext = DT_QuestionActivity.this;
    private DynamicDataIndexDataModel dyIndex;
    private int totalQuestion;
    private TextView tv_DtQuestion, tv_dtTimePicker;
    private Button btnNextQues;
    private DynamicTableQuesDataModel mQuestion;
    private Button btnPreviousQus;
    int mQusIndex;
    private EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    Log.d("MOR", "mQusIndex: " + mQusIndex);
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
                    Log.d("MOR", "mQusIndex: " + mQusIndex);
                }

            }
        });
    }

    private void displayQuestion(DynamicTableQuesDataModel qusObject) {
        tv_DtQuestion.setText(qusObject.getqText());
        loadAnswer(qusObject.getqResModeCode());
        // tv_DtQuestion.setText(qus);
    }

    private void inti() {
        viewReference();
        sqlH = new SQLiteHandler(mContext);
        Intent intent = getIntent();
        dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        totalQuestion = intent.getIntExtra(KEY.DYNAMIC_T_QUES_SIZE, 0);
        mQusIndex = 0;
        hideViews();

    }

    private void hideViews() {
        tv_dtTimePicker.setVisibility(View.GONE);
        edt.setVisibility(View.GONE);
    }

    private void viewReference() {
        tv_DtQuestion = (TextView) findViewById(R.id.tv_DtQuestion);
        tv_dtTimePicker = (TextView) findViewById(R.id.tv_dtTimePicker);
        edt = (EditText) findViewById(R.id.edt);

        btnNextQues = (Button) findViewById(R.id.btnHomeFooter);
        btnPreviousQus = (Button) findViewById(R.id.btnRegisterFooter);
        btnNextQues.setText("Next");
        btnPreviousQus.setText("Previous");
    }

    private DynamicTableQuesDataModel fistQuestion(final String dtBasic) {
        return loadQuestion(dtBasic, 0);
    }

    public DynamicTableQuesDataModel loadQuestion(final String dtBasic, final int qusIndex) {
        mQuestion = sqlH.getSingleDynamicQuestion(dtBasic, qusIndex);
        return mQuestion;
    }

    private DynamicTableQuesDataModel loadNextQuestion(final String dtBasic, final int qusIndex) {

        return loadQuestion(dtBasic, qusIndex);
    }

    private DynamicTableQuesDataModel loadPreviousQuestion(final String dtBasic, final int qusIndex) {

        return loadQuestion(dtBasic, qusIndex);
    }

    private void loadAnswer(String responsMode) {

        DTQResponseModeDataModel dtqResponseModeDataModel = sqlH.getAnswerResponse(responsMode);
        String dataType = dtqResponseModeDataModel.getDtDataType();
        String responseControl = dtqResponseModeDataModel.getDtResponseValueControl();
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

                    }
                    break;
                case DateTime:
                    tv_dtTimePicker.setVisibility(View.VISIBLE);
                    tv_dtTimePicker.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
            }
        }

    }

}
