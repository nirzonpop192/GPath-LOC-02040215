package com.siddiquinoor.restclient.activity.sub_activity.dynamic_table;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;

public class DT_QuestionActivity extends BaseActivity {

    private SQLiteHandler sql;
    private final Context mContext = DT_QuestionActivity.this;
    private DynamicDataIndexDataModel dyIndex;
    private int totalQuestion;
    private TextView tv_DtQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dt__qustion);
        inti();

    }

    private void inti() {

        sql = new SQLiteHandler(mContext);
        Intent intent = getIntent();
        dyIndex = intent.getParcelableExtra(KEY.DYNAMIC_INDEX_DATA_OBJECT_KEY);
        totalQuestion = intent.getIntExtra(KEY.DYNAMIC_T_QUES_SIZE, 0);

    }

    private void viewRefernce() {
        tv_DtQuestion = (TextView) findViewById(R.id.tv_DtQuestion);
    }

    private void fristQuestion() {
    }

    private void loadQuestion(final String dtBasic,final int qusIndex) {
        sql.getSingleDynamicQuestion(dtBasic,qusIndex);
    }

    private void loadNextQuestion() {
    }

    private void loadPreviousQuestion() {
    }

}
