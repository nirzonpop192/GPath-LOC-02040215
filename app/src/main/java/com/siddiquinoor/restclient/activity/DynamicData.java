package com.siddiquinoor.restclient.activity;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.AssignDataModel;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexAdpater;
import com.siddiquinoor.restclient.views.adapters.DynamicDataIndexDataModel;
import com.siddiquinoor.restclient.views.adapters.MemberSearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class DynamicData extends BaseActivity {

    private ListView lvDynamicTableIndex;
    private Button btnHome;
    private final  Context mContext=DynamicData.this;
    private SQLiteHandler sqlH;
    private ArrayList<DynamicDataIndexDataModel> dataArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_data);
        inti();
        Intent intent = getIntent();
        String idCountry=intent.getStringExtra(KEY.COUNTRY_ID);
        loadDynamicIndex(idCountry);
        setListener();

    }

    private void setListener() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity((Activity)mContext);


            }
        });
    }

    private void inti() {
        viewReference();
        sqlH= new SQLiteHandler(mContext);
    }

    private void viewReference() {

        lvDynamicTableIndex = (ListView) findViewById(R.id.lvDynamicTableIndex);
        btnHome = (Button) findViewById(R.id.btnHomeFooter);
        Button btnSummary = (Button) findViewById(R.id.btnRegisterFooter);
        btnSummary.setVisibility(View.GONE);



    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addIconHomeButton() {


        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        setPaddingButton(mContext, imageHome, btnHome);


    }

    /**
     * calling getWidth() and getHeight() too early:
     * When  the UI has not been sized and laid out on the screen yet..
     *
     * @param hasFocus the value will be true when UI is focus
     */

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        addIconHomeButton();
    }

   private void loadDynamicIndex(String cCode){

       List<DynamicDataIndexDataModel> dataList = sqlH.getDynamicTableIndexList(cCode);
       DynamicDataIndexAdpater adapter=null;

      dataArray = new ArrayList<DynamicDataIndexDataModel>();
       if (dataList.size() != 0) {

           dataArray.clear();

           for (DynamicDataIndexDataModel data : dataList) {
               // add contacts data in arrayList
               dataArray.add(data);
           }

/**
 * Assign the Adapter in list
 */
            adapter = new DynamicDataIndexAdpater((Activity) mContext, dataArray);
           adapter.notifyDataSetChanged();

           lvDynamicTableIndex.setAdapter(adapter);

       }

   }
}
