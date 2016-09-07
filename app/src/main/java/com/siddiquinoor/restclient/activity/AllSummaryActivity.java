package com.siddiquinoor.restclient.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.GroupSummary;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.ServiceSummaryMenu;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.Summary;
import com.siddiquinoor.restclient.activity.sub_activity.summary_sub.SummaryAssignCriteria;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.utils.UtilClass;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;


public class AllSummaryActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton rbHouseHold, rbService, rbDistribution;
    private Button btnGo, btnHome;
    private String idCountry;
    private RadioButton rbAssign;
    private RadioButton rbtGroup;

    private ADNotificationManager dialog;
    private final Context CONTEXT = AllSummaryActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_all_summary);

        initial();

        Intent innt = getIntent();

        idCountry = innt.getStringExtra(KEY.COUNTRY_ID);

        viewAccessController();

        btnGo.setOnClickListener(this);
        btnHome.setOnClickListener(this);



    }

    private void initial() {
        reference();
        dialog = new ADNotificationManager();


        rbDistribution.setEnabled(false);
        rbHouseHold.setEnabled(false);
        rbService.setEnabled(false);
        rbAssign.setEnabled(false);
        rbtGroup.setEnabled(false);
    }


    private void viewAccessController( ) {

        SharedPreferences settings;

        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int operationMode = settings.getInt(UtilClass.OPERATION_MODE, 0);
        Log.d("NIR1", "operation mode : " + operationMode);
        switch (operationMode) {
            case UtilClass.REGISTRATION_OPERATION_MODE:
                rbHouseHold.setEnabled(true);
                rbAssign.setEnabled(true);
                rbtGroup.setEnabled(true);

                break;
            case UtilClass.DISTRIBUTION_OPERATION_MODE:
                rbDistribution.setEnabled(true);
                break;
            case UtilClass.SERVICE_OPERATION_MODE:
                rbService.setEnabled(true);

                break;
        }
    }

    private void reference() {
        rbHouseHold = (RadioButton) findViewById(R.id.rbtn_household_samm);
        rbService = (RadioButton) findViewById(R.id.rbtn_service_samm);
        rbDistribution = (RadioButton) findViewById(R.id.rbtn_distribution_samm);
        rbAssign = (RadioButton) findViewById(R.id.rbtn_Assign_summary);
        rbtGroup = (RadioButton) findViewById(R.id.rbtn_Group_summary);

        /**
         * view swipt views
         *
         */

        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnGo = (Button) findViewById(R.id.btnHomeFooter);

        setUpHomeButton();

        setUpGoButton();


    }

    private void setUpGoButton() {
        btnGo.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.goto_b);
        btnGo.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btnGo.setPadding(180, 10, 180, 10);
    }

    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 10, 180, 10);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // go button
            case R.id.btnHomeFooter:
                Intent intent = null;
                if (rbHouseHold.isChecked()) {
                    intent = new Intent(AllSummaryActivity.this, Summary.class);
                    intent.putExtra(KEY.COUNTRY_ID, idCountry);
                } else if (rbService.isChecked()) {
                    intent = new Intent(AllSummaryActivity.this, ServiceSummaryMenu.class);
                    intent.putExtra(KEY.COUNTRY_ID, idCountry);
                    intent.putExtra(KEY.FLAG, KEY.SRV_FLAG);
                    intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AllSummaryActivity");
                } else if (rbAssign.isChecked()) {
                    intent = new Intent(AllSummaryActivity.this, SummaryAssignCriteria.class);
                    intent.putExtra(KEY.COUNTRY_ID, idCountry);
                } else if (rbDistribution.isChecked()) {
                    intent = new Intent(AllSummaryActivity.this, ServiceSummaryMenu.class);
                    intent.putExtra(KEY.COUNTRY_ID, idCountry);
                    intent.putExtra(KEY.FLAG, KEY.DIST_FLAG);
                    intent.putExtra(KEY.DIR_CLASS_NAME_KEY, "AllSummaryActivity");
                }else if(rbtGroup.isChecked()){
                    intent = new Intent(AllSummaryActivity.this, GroupSummary.class);
                    intent.putExtra(KEY.COUNTRY_ID, idCountry);
                } else
                    dialog.showErrorDialog(CONTEXT, "No Menu is selected yet");


                if (intent != null) {

                    startActivity(intent);
                }


                break;
            // home button
            case R.id.btnRegisterFooter:
                finish();
                Intent iHome = new Intent(AllSummaryActivity.this, MainActivity.class);
                startActivity(iHome);

                break;
        }
    }


  /*  @Override
    public void onBackPressed() {
       *//* Intent noBackpressWork=new Intent(AllSummaryActivity.this,AllSummaryActivity.class);
        startActivity(noBackpressWork);*//*
    }*/
}
