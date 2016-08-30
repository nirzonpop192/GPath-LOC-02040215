package com.siddiquinoor.restclient.activity.sub_activity.gps_sub;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import com.siddiquinoor.restclient.R;
import com.siddiquinoor.restclient.activity.MainActivity;
import com.siddiquinoor.restclient.activity.MapActivity;

import com.siddiquinoor.restclient.data_model.GPS_LocationDataModel;
import com.siddiquinoor.restclient.data_model.GPS_SubGroupAttributeDataModel;
import com.siddiquinoor.restclient.fragments.BaseActivity;
import com.siddiquinoor.restclient.manager.SQLiteHandler;
import com.siddiquinoor.restclient.manager.sqlsyntax.SQLServerSyntaxGenerator;
import com.siddiquinoor.restclient.utils.KEY;
import com.siddiquinoor.restclient.views.adapters.GPSLocationLatLong;
import com.siddiquinoor.restclient.views.helper.SpinnerHelper;
import com.siddiquinoor.restclient.views.notifications.ADNotificationManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PointAttributes extends BaseActivity {

    private static final String TAG = "GPSActivity";
    private static final String NO_LOOK_UP = "N";
    private static final String TEXT_TYPE = "B";
    private static final String NUMERIC = "N";
    private static final String IMAGE_TYPE = "I";

    private SQLiteHandler sqlH;


    private Spinner spGroup, spSubGroup, spLocation;
    private String idGroup, idSubGroup, idCountry, idLocation;
    private String strGroup, strSubGroup, strLocation;
    private LinearLayout llayout_Dynamic_Attribute;
    private Button btnHome;
    private Button btnSave;

    List<EditText> allEdt = new ArrayList<EditText>();
    List<CheckBox> allCheckBox = new ArrayList<CheckBox>();
    private Button btnMap;
    private boolean permissionForGoMap = false;
    private TextView lbel_lng, lbel_lat;
    private TextView tv_latitude, tv_longitude;
    Intent cameraIntent;


    /**
     * Fixed value for capture Image
     */
    private static final int CAMERA_REQUEST_1 = 10;
    private static final int CAMERA_REQUEST_2 = 20;
    private static final int CAMERA_REQUEST_3 = 30;
    private static final int CAMERA_REQUEST_4 = 40;
    private static final int CAMERA_REQUEST_5 = 50;


    private static final String IMG_CONTENT_CODE_1 = "01";
    private static final String IMG_CONTENT_CODE_2 = "02";
    private static final String IMG_CONTENT_CODE_3 = "03";
    private static final String IMG_CONTENT_CODE_4 = "04";
    private static final String IMG_CONTENT_CODE_5 = "05";

    private static final String REMARKS_1 = "1";
    private static final String REMARKS_2 = "2";
    private static final String REMARKS_3 = "3";
    private static final String REMARKS_4 = "4";
    private static final String REMARKS_5 = "5";

    private final Context CONTEXT = PointAttributes.this;

    HorizontalScrollView horizontalScrollView;

    ImageView mImageView1;
    ImageView mImageView2;
    ImageView mImageView3;
    ImageView mImageView4;
    ImageView mImageView5;

    // Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;

    public static final int MEDIA_TYPE_IMAGE = 1;


    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    private String imageName;

    public String getImageName() {
        return imageName;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        intili();

        Intent intent = getIntent();
        // get Country Code
        idCountry = intent.getExtras().getString(KEY.COUNTRY_ID);
        String dir = intent.getStringExtra(KEY.DIR_CLASS_NAME_KEY);
        /**
         * if intent come from map activity
         */
        if (dir.equals("MapActivity")) {


            idCountry = intent.getStringExtra(KEY.COUNTRY_ID);
            idGroup = intent.getStringExtra(KEY.GROUP_CODE);
            strGroup = intent.getStringExtra(KEY.GROUP_NAME);
            idSubGroup = intent.getStringExtra(KEY.SUB_GROUP_CODE);
            strSubGroup = intent.getStringExtra(KEY.SUB_GROUP_NAME);
            idLocation = intent.getStringExtra(KEY.LOCATION_CODE);
            strLocation = intent.getStringExtra(KEY.LOCATION_NAME);


            //idSubGroup

            String lat = intent.getStringExtra(KEY.LATITUDE);
            String lon = intent.getStringExtra(KEY.LONGITUDE);


    /*        Log.d(TAG, " From Map Sub directoirs \n"


                    + " idCountry : " + idCountry
                    + " idGroup : " + idGroup
                    + " strGroup : " + strGroup

                    + " idSubGroup : " + idSubGroup
                    + " strSubGroup : " + strSubGroup

                    + " idLocation : " + idLocation
                    + " strLocation : " + strLocation
            );*/


            setVisibletyLatLongViews(View.VISIBLE);
            tv_latitude.setText(lat);
            tv_longitude.setText(lon);
            implementImageCapture();


        }

        loadGroup(idCountry);

        getGPS();

        save();
        goHome();
        goToMapPage();
    }

    private void goToMapPage() {
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permissionForGoMap) {
                    Intent iGoToMap = new Intent(PointAttributes.this, MapActivity.class);
                    /**
                     * Send data to other activity
                     */
                    GPS_LocationDataModel gpsData = new GPS_LocationDataModel();


                    gpsData.setAdmCountryCode(idCountry);
                    gpsData.setGroupCode(idGroup);
                    gpsData.setSubGroupCode(idSubGroup);
                    gpsData.setLocationCode(idLocation);
                    gpsData.setGroupName(strGroup);
                    gpsData.setSubGroupName(strSubGroup);
                    gpsData.setLocationName(strLocation);
                    iGoToMap.putExtra(KEY.GPS_DATA_OBJECT_KEY, gpsData);

                    iGoToMap.putExtra(KEY.DIR_CLASS_NAME_KEY, TAG);
                    finish();
                    startActivity(iGoToMap);
                } else {
                    ADNotificationManager dialog = new ADNotificationManager();
                    dialog.showErrorDialog(CONTEXT, "Select Location");
                }


            }
        });
    }

    private void goHome() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iHome = new Intent(CONTEXT, MainActivity.class);
                startActivity(iHome);
            }
        });
    }

    private void save() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<GPS_SubGroupAttributeDataModel> attList = sqlH.getGpsSubGroupAttributes(idGroup, idSubGroup);
                /**
                 * count the total attribute size
                 */

                int size = attList.size();

                String entryBy = getStaffID();
                String entryDate = "";
                try {
                    entryDate = getDateTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                for (int j = 0; j < size; j++) {

                    GPS_SubGroupAttributeDataModel subAtt = attList.get(j);

                    /**
                     * if the attribute is not lookup
                     */


                    if (attList.get(j).getLookUpCode().equals(NO_LOOK_UP)) {
                        if (attList.get(j).getDataType().equals(IMAGE_TYPE)) {

                      /*      Log.d(TAG, " in save method \n "
                                    + "idCountry : " + idCountry + "idGroup : " + idGroup
                                    + "idSubGroup : " + idSubGroup + "idLocation : " + idLocation
                                    + "AttributeCode : " + subAtt.getAttributeCode() + "AttributeValue : photo"
                                    + "entryBy : " + entryBy + "entryDate : " + entryDate);*/

                            if (getmEncodedImageString() != null) {


                                SQLServerSyntaxGenerator sqlServer = new SQLServerSyntaxGenerator();
                                sqlServer.setAdmCountryCode(idCountry);
                                sqlServer.setGrpCode(idGroup);
                                sqlServer.setSubGrpCode(idSubGroup);
                                sqlServer.setLocationCode(idLocation);
                                sqlServer.setAttributeCode(subAtt.getAttributeCode());
                                //  sqlServer.setAttributeValue(string[i]);
                                sqlServer.setEntryBy(entryBy);
                                sqlServer.setEntryDate(entryDate);

                                sqlH.addGPSLocationAttributes(idCountry, idGroup, idSubGroup, idLocation, subAtt.getAttributeCode(), null, getmEncodedImageString(), entryBy, entryDate);

                                sqlH.insertIntoUploadTable(sqlServer.insertIntoGPSLocationAttributesTable());

                            }
                        } else {

                            String[] string = new String[allEdt.size()];

                            for (int i = 0; i < allEdt.size(); i++) {
                                string[i] = allEdt.get(i).getText().toString();
                         /*       Log.d(TAG, "Attributes " + string[i]);*/

/*
                                Log.d(TAG, " in save method \n "
                                        + "idCountry : " + idCountry + "idGroup : " + idGroup
                                        + "idSubGroup : " + idSubGroup + "idLocation : " + idLocation
                                        + "AttributeCode : " + subAtt.getAttributeCode() + "AttributeValue : " + string[i]
                                        + "entryBy : " + entryBy + "entryDate : " + entryDate);*/
                                SQLServerSyntaxGenerator sqlServer = new SQLServerSyntaxGenerator();
                                sqlServer.setAdmCountryCode(idCountry);
                                sqlServer.setGrpCode(idGroup);
                                sqlServer.setSubGrpCode(idSubGroup);
                                sqlServer.setLocationCode(idLocation);
                                sqlServer.setAttributeCode(subAtt.getAttributeCode());
                                sqlServer.setAttributeValue(string[i]);
                                sqlServer.setEntryBy(entryBy);
                                sqlServer.setEntryDate(entryDate);

                                sqlH.addGPSLocationAttributes(idCountry, idGroup, idSubGroup, idLocation, subAtt.getAttributeCode(), string[i], null, entryBy, entryDate);

                                sqlH.insertIntoUploadTable(sqlServer.insertIntoGPSLocationAttributesTable());
                            }


                        }

                    } else {

                        for (int i = 0; i < allCheckBox.size(); i++) {
                            if (allCheckBox.get(i).isChecked()) {


                             /*   Log.d(TAG, " in save method \n "
                                        + "idCountry : " + idCountry + "idGroup : " + idGroup
                                        + "idSubGroup : " + idSubGroup + "idLocation : " + idLocation
                                        + "AttributeCode : " + subAtt.getAttributeCode() + "AttributeValue : " + 1
                                        + "entryBy : " + entryBy + "entryDate : " + entryDate);*/

                                SQLServerSyntaxGenerator sqlServer = new SQLServerSyntaxGenerator();
                                sqlServer.setAdmCountryCode(idCountry);
                                sqlServer.setGrpCode(idGroup);
                                sqlServer.setSubGrpCode(idSubGroup);
                                sqlServer.setLocationCode(idLocation);
                                sqlServer.setAttributeCode(subAtt.getAttributeCode());
                                sqlServer.setAttributeValue("1");
                                sqlServer.setEntryBy(entryBy);
                                sqlServer.setEntryDate(entryDate);


                                sqlH.addGPSLocationAttributes(idCountry, idGroup, idSubGroup, idLocation, subAtt.getAttributeCode(), "1", null, entryBy, entryDate);
                                sqlH.insertIntoUploadTable(sqlServer.insertIntoGPSLocationAttributesTable());


                            }
                        }
                    }
                }

                Toast.makeText(CONTEXT, "Save successfully ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     */

    private void intili() {
        sqlH = new SQLiteHandler(this);
        viewReference();
        setUpGoButton();
        setVisibletyLatLongViews(View.GONE);
    }

    private void setVisibletyLatLongViews(int visible) {
        lbel_lat.setVisibility(visible);
        lbel_lng.setVisibility(visible);
        tv_latitude.setVisibility(visible);
        tv_longitude.setVisibility(visible);
    }

    private void viewReference() {

        spGroup = (Spinner) findViewById(R.id.spGroup);
        spSubGroup = (Spinner) findViewById(R.id.spGPS_SubGroup);
        spLocation = (Spinner) findViewById(R.id.spGPS_location);
        llayout_Dynamic_Attribute = (LinearLayout) findViewById(R.id.llayout_Dynamic_Attribute);

        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsvImageView_Controller);
        horizontalScrollView.setVisibility(View.GONE);

        mImageView1 = (ImageView) findViewById(R.id.iv_gpsAtt_1);
        mImageView2 = (ImageView) findViewById(R.id.iv_gpsAtt_2);
        mImageView3 = (ImageView) findViewById(R.id.iv_gpsAtt_3);
        mImageView4 = (ImageView) findViewById(R.id.iv_gpsAtt_4);
        mImageView5 = (ImageView) findViewById(R.id.iv_gpsAtt_5);


        btnHome = (Button) findViewById(R.id.btnRegisterFooter);
        btnSave = (Button) findViewById(R.id.btnHomeFooter);
        btnMap = (Button) findViewById(R.id.btnMap);

        setUpSaveButton();
        setUpHomeButton();

        lbel_lat = (TextView) findViewById(R.id.gps_tv_lat_label);
        lbel_lng = (TextView) findViewById(R.id.gps_tv_lng_label);

        tv_latitude = (TextView) findViewById(R.id.gps_tv_lat);
        tv_longitude = (TextView) findViewById(R.id.gps_tv_lng);


    }

    private void setUpGoButton() {
        btnMap.setText("");
        Drawable imageGoto = getResources().getDrawable(R.drawable.map_btn_icon);
        btnMap.setCompoundDrawablesRelativeWithIntrinsicBounds(imageGoto, null, null, null);
        btnMap.setPadding(380, 5, 380, 5);
    }

    private void implementImageCapture() {
        //image 1
        mImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureOrViewImageOption(CAMERA_REQUEST_1, idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_1);
            }
        });
        //image 2
        mImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureOrViewImageOption(CAMERA_REQUEST_2, idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_2);

            }
        });
        //image 3
        mImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureOrViewImageOption(CAMERA_REQUEST_3, idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_3);
            }
        });
        //image 4
        mImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureOrViewImageOption(CAMERA_REQUEST_4, idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_4);
            }
        });

        //image 5
        mImageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureOrViewImageOption(CAMERA_REQUEST_5, idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_5);
            }
        });

    }


    private void setUpSaveButton() {
        btnSave.setText("");
        Drawable saveImage = getResources().getDrawable(R.drawable.save_b);
        btnSave.setCompoundDrawablesRelativeWithIntrinsicBounds(saveImage, null, null, null);
        btnSave.setPadding(180, 20, 180, 20);
    }

    private void setUpHomeButton() {

        btnHome.setText("");
        Drawable imageHome = getResources().getDrawable(R.drawable.home_b);
        btnHome.setCompoundDrawablesRelativeWithIntrinsicBounds(imageHome, null, null, null);
        btnHome.setPadding(180, 20, 180, 20);
    }


    private void loadGroup(final String cCode) {
        int position = 0;

        String criteria = "";
        // Spinner Drop down elements for District
        List<SpinnerHelper> listGroup = sqlH.getListAndID(SQLiteHandler.GPS_GROUP_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(CONTEXT, R.layout.spinner_layout, listGroup);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spGroup.setAdapter(dataAdapter);


        if (idGroup != null) {
            for (int i = 1; i < spGroup.getCount(); i++) {
                String group = spGroup.getItemAtPosition(i).toString();
                if (group.equals(strGroup)) {
                    position = i;
                }
            }
            spGroup.setSelection(position);
        }


        spGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getValue();
                idGroup = ((SpinnerHelper) spGroup.getSelectedItem()).getId();
                if (idGroup.length() > 2)
                    loadSubGroup(cCode, idGroup);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    /**
     * LOAD :: SUb Group
     */

    private void loadSubGroup(final String cCode, final String idGroup) {
        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.GROUP_CODE_COL + "='" + idGroup + "'";

        // Spinner Drop down elements for District
        List<SpinnerHelper> listsubGroup = sqlH.getListAndID(SQLiteHandler.GPS_SUB_GROUP_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(CONTEXT, R.layout.spinner_layout, listsubGroup);
        // Drop down layout style
        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);
        // attaching data adapter to spinner
        spSubGroup.setAdapter(dataAdapter);

        // Set the string to the position
        if (idSubGroup != null) {
            for (int i = 0; i < spSubGroup.getCount(); i++) {
                String subGroup = spSubGroup.getItemAtPosition(i).toString();
                if (subGroup.equals(strSubGroup)) {
                    position = i;
                }
            }
            spSubGroup.setSelection(position);
        }


        spSubGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strSubGroup = ((SpinnerHelper) spSubGroup.getSelectedItem()).getValue();
                idSubGroup = ((SpinnerHelper) spSubGroup.getSelectedItem()).getId();
                if (idSubGroup.length() > 2) {
                    loadLocation(cCode, idGroup, idSubGroup);
                    createDynamicViews(idGroup, idSubGroup);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * LOAD :: Location to the Location Spinner
     */

    private void loadLocation(final String cCode, final String groupCode, final String subGroupCode) {
        int position = 0;
        String criteria = " WHERE " + SQLiteHandler.COUNTRY_CODE_COL + " = '" + cCode + "' AND " + sqlH.GROUP_CODE_COL + "='" + groupCode + "' AND " + sqlH.SUB_GROUP_CODE_COL + "='" + subGroupCode + "'";

        // Spinner Drop down elements for Location
        List<SpinnerHelper> listlocation = sqlH.getListAndID(SQLiteHandler.GPS_LOCATION_TABLE, criteria, null, false);

        // Creating adapter for spinner
        ArrayAdapter<SpinnerHelper> dataAdapter = new ArrayAdapter<SpinnerHelper>(CONTEXT, R.layout.spinner_layout, listlocation);

        dataAdapter.setDropDownViewResource(R.layout.spinner_layout);  // Set Drop down layout style

        spLocation.setAdapter(dataAdapter);  // attaching data adapter to spinner


        if (idLocation != null) {
            for (int i = 0; i < spLocation.getCount(); i++) {
                String location = spLocation.getItemAtPosition(i).toString();
                if (location.equals(strLocation)) {
                    position = i;
                }
            }
            spLocation.setSelection(position);
        }


        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strLocation = ((SpinnerHelper) spLocation.getSelectedItem()).getValue();
                idLocation = ((SpinnerHelper) spLocation.getSelectedItem()).getId();

              /*  Log.d(TAG, " strLocation :" + strLocation + " idLocation : " + idLocation);*/
                if (!idLocation.equals("00")) {
                    permissionForGoMap = true;
                    setVisibletyLatLongViews(View.VISIBLE);
                    GPSLocationLatLong latLong = sqlH.getLocationSpecificLatLong(idCountry, groupCode, subGroupCode, idLocation);
                    setLatLongInTextView(latLong);
                } else {
                    permissionForGoMap = false;
                    setVisibletyLatLongViews(View.GONE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setLatLongInTextView(GPSLocationLatLong latLong) {
        tv_latitude.setText(latLong.getLatitude());
        tv_longitude.setText(latLong.getLongitude());

    }

    Uri fileUri;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {

    }





    /*
 * Capturing Camera Image will lauch camera app requrest image capture
 */
 /*   private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }*/

    private void createDynamicViews(String groupCode, String subGroupCode) {

/**
 * clean the view  before generate new views
 */
        if (((LinearLayout) llayout_Dynamic_Attribute).getChildCount() > 0)
            ((LinearLayout) llayout_Dynamic_Attribute).removeAllViews();

        /**
         *  connect database */

        ArrayList<GPS_SubGroupAttributeDataModel> attList = sqlH.getGpsSubGroupAttributes(groupCode, subGroupCode);
        int size = attList.size();


        for (int i = 0; i < size; i++) {

            // add text view
            TextView tv = new TextView(this);
            String st = attList.get(i).getAttributeTitle();
            tv.setText(st);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextSize(15.0f);
            llayout_Dynamic_Attribute.addView(tv);

            if (attList.get(i).getLookUpCode().equals(NO_LOOK_UP)) {


                if (attList.get(i).getDataType().equals(IMAGE_TYPE)) {

                    LinearLayout parent = new LinearLayout(this);

                    parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    parent.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(this);
                    String imageName = idGroup + idSubGroup + attList.get(i).getAttributeCode() + idLocation;
                    textView.setText(imageName);
                    setImageName(imageName);

                    ImageButton bt = new ImageButton(this);

                    bt.setId(i);
                    bt.setImageResource(R.drawable.voucher_icon);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(CONTEXT, "Take photo ", Toast.LENGTH_SHORT).show();

                            if (!isDeviceSupportCamera()) {
                                Toast.makeText(getApplicationContext(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();

                            } else {
                                horizontalScrollView.setVisibility(View.VISIBLE);

                                viewImageFromDatabase(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_1, mImageView1);
                                viewImageFromDatabase(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_2, mImageView2);
                                viewImageFromDatabase(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_3, mImageView3);
                                viewImageFromDatabase(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_4, mImageView4);
                                viewImageFromDatabase(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_5, mImageView5);


                            }


                        }
                    });

                    parent.addView(textView);
                    parent.addView(bt);
                    llayout_Dynamic_Attribute.addView(parent);
                } else {

                    EditText et = new EditText(this);
                    allEdt.add(et);
                    et.setHint(st);
                    et.setId(i);
                    et.setPadding(15, 5, 0, 5);
                    et.setBackground(getResources().getDrawable(R.drawable.edit_box_background));
                    switch (attList.get(i).getDataType()) {
                        case TEXT_TYPE:
                            et.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        case NUMERIC:
                            et.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;
                    }
                    llayout_Dynamic_Attribute.addView(et);

                }


            } else {


                final CheckBox cb = new CheckBox(this);
                cb.setText(st);
                cb.setId(i);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                   /*     Log.d(TAG, "Check box " + cb.getId() + " is checked ");*/
                    }
                });
                allCheckBox.add(cb);
                llayout_Dynamic_Attribute.addView(cb);


            }
        }

    }


    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        String entryBy = getStaffID();

        String entryDate = "";
        try {
            entryDate = getDateTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SQLServerSyntaxGenerator sqlServer = new SQLServerSyntaxGenerator();
        sqlServer.setAdmCountryCode(idCountry);
        sqlServer.setGrpCode(idGroup);
        sqlServer.setSubGrpCode(idSubGroup);
        sqlServer.setLocationCode(idLocation);
        sqlServer.setEntryBy(entryBy);
        sqlServer.setEntryDate(entryDate);


        if (requestCode == CAMERA_REQUEST_1 && resultCode == RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            base64 = base64.trim();
            try {
                sqlH.insertIntoGPSLocationContentTable(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_1, byteArray, REMARKS_1, getStaffID(), getDateTime());
                sqlServer.setAdmCountryCode(idCountry);
                sqlServer.setContentCode(IMG_CONTENT_CODE_1);
                sqlServer.setImageFile(base64);
                sqlServer.setRemarks(REMARKS_1);
                sqlH.insertIntoUploadTable(sqlServer.InsertIntoGPSLocationContentTable());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data != null && resultCode == RESULT_OK) {
                mImageView1.setImageBitmap(photo);
            }
        } else if (requestCode == CAMERA_REQUEST_2 && resultCode == RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                sqlH.insertIntoGPSLocationContentTable(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_2, byteArray, REMARKS_2, getStaffID(), getDateTime());
                sqlServer.setAdmCountryCode(idCountry);
                sqlServer.setContentCode(IMG_CONTENT_CODE_2);
                sqlServer.setImageFile(base64);
                sqlServer.setRemarks(REMARKS_2);
                sqlH.insertIntoUploadTable(sqlServer.InsertIntoGPSLocationContentTable());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data != null && resultCode == RESULT_OK) {

                mImageView2.setImageBitmap(photo);
            }
        } else if (requestCode == CAMERA_REQUEST_3 && resultCode == RESULT_OK && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                sqlH.insertIntoGPSLocationContentTable(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_3, byteArray, REMARKS_3, getStaffID(), getDateTime());
                sqlServer.setAdmCountryCode(idCountry);
                sqlServer.setContentCode(IMG_CONTENT_CODE_3);
                sqlServer.setImageFile(base64);
                sqlServer.setRemarks(REMARKS_3);
                sqlH.insertIntoUploadTable(sqlServer.InsertIntoGPSLocationContentTable());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data != null && resultCode == RESULT_OK) {

                mImageView3.setImageBitmap(photo);
            }
        } else if (requestCode == CAMERA_REQUEST_4 && resultCode == RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                sqlH.insertIntoGPSLocationContentTable(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_4, byteArray, REMARKS_4, getStaffID(), getDateTime());
                sqlServer.setAdmCountryCode(idCountry);
                sqlServer.setContentCode(IMG_CONTENT_CODE_4);
                sqlServer.setImageFile(base64);
                sqlServer.setRemarks(REMARKS_4);
                sqlH.insertIntoUploadTable(sqlServer.InsertIntoGPSLocationContentTable());

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data != null && resultCode == RESULT_OK) {

                mImageView4.setImageBitmap(photo);
            }
        } else if (requestCode == CAMERA_REQUEST_5 && resultCode == RESULT_OK && data != null) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 99, stream);
            byte[] byteArray = stream.toByteArray();
            String base64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
            try {
                sqlH.insertIntoGPSLocationContentTable(idCountry, idGroup, idSubGroup, idLocation, IMG_CONTENT_CODE_5, byteArray, REMARKS_5, getStaffID(), getDateTime());
                sqlServer.setAdmCountryCode(idCountry);
                sqlServer.setContentCode(IMG_CONTENT_CODE_5);
                sqlServer.setImageFile(base64);
                sqlServer.setRemarks(REMARKS_5);
                sqlH.insertIntoUploadTable(sqlServer.InsertIntoGPSLocationContentTable());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (data != null && resultCode == RESULT_OK) {

                mImageView5.setImageBitmap(photo);
            }
        } else if (resultCode == RESULT_CANCELED && data == null) {
            finish();
        }
    }


    private String mEncodedImageString;

    public String getmEncodedImageString() {
        return mEncodedImageString;
    }

    public void setmEncodedImageString(String mEncodedImageString) {
        this.mEncodedImageString = mEncodedImageString;
    }

    /**
     * Display image from a path to ImageView
     */
    private void previewCapturedImage() {
        try {
            // hide video preview

            byte[] byteArray;
            String encodedImageString = null;


            mImageView1.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);

            // now it is set up to 170 x 196;
            Bitmap bitmapCompressed = Bitmap.createScaledBitmap(bitmap, 170, 196, true);

            // for passing the image to other activity
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            // convert the image into 100%
            bitmapCompressed.compress(Bitmap.CompressFormat.PNG, 50, stream);

            byteArray = stream.toByteArray();

           /*  convert in base 64*/
            encodedImageString = Base64.encodeToString(byteArray, Base64.DEFAULT);

            setmEncodedImageString(encodedImageString);

            mImageView1.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name

        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + getImageName() + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


    /**
     * @return
     */

    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }


    private double[] getGPS() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(false);

        /* Loop over the array backwards, and if you get an accurate location, then break out the loop*/
        Location l = null;

        for (int i = providers.size() - 1; i >= 0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) break;
        }

        double[] gps = new double[2];
        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
     /*   Log.d(TAG, "getLatitude : " + gps[0] + "getLatitude : " + gps[1]);*/
        return gps;
    }

    AlertDialog cameRaDialog;

    /**
     * @param REQUEST
     */
    private void captureOrViewImageOption(final int REQUEST, final String CountryCode, final String GrpCode, final String subGrpCode, final String LocationCode, final String ContentCode) {

       /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                GPSActivity.this);

        // set title
        alertDialogBuilder.setTitle("");

        // set dialog message
        alertDialogBuilder
                .setMessage("Active Camera ?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent,REQUEST);
                       // GPSActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });*/

        // create alert dialog
        //AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        //alertDialog.show();

        final CharSequence[] items = getResources().getStringArray(R.array.cameraOption);

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(PointAttributes.this, android.R.style.Theme_Holo_Light_Dialog));
        builder.setTitle("Photo:");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //startActivityForResult(cameraIntent,REQUEST);
                        captureImageAlert(REQUEST);
                        break;
                    case 1:
                        checkPhotoAvailability(CountryCode, GrpCode, subGrpCode, LocationCode, ContentCode);
                        break;
                    case 2:
                        cameRaDialog.dismiss();
                        break;
                }
                cameRaDialog.dismiss();
            }
        });
        cameRaDialog = builder.create();
        cameRaDialog.show();
    }


    /**
     * @param REQUEST
     */
    private void captureImageAlert(final int REQUEST) {

        // if this button is clicked, close
        // current activity
        cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, REQUEST);


    }

    private void checkPhotoAvailability(final String CountryCode, final String GrpCode, final String subGrpCode, final String LocationCode, final String ContentCode) {
        if (sqlH.checkDataAvailableOrNotInGpsLocationContentTable(CountryCode, GrpCode, subGrpCode, LocationCode, ContentCode) != true) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    PointAttributes.this);

            // set title
            alertDialogBuilder.setTitle("");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Failed To Delete.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            final SQLServerSyntaxGenerator sqlServer = new SQLServerSyntaxGenerator();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    PointAttributes.this);

            // set title
            alertDialogBuilder.setTitle("");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Delete Image?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            //TODO: DELETE QUERY
                            sqlH.deleteRowFromGpsLocationContentTable(CountryCode, GrpCode, subGrpCode, LocationCode, ContentCode);
                            if (ContentCode.equals(IMG_CONTENT_CODE_1)) {
                                mImageView1.setImageResource(R.drawable.cam);
                            } else if (ContentCode.equals(IMG_CONTENT_CODE_2)) {
                                mImageView2.setImageResource(R.drawable.cam);
                            } else if (ContentCode.equals(IMG_CONTENT_CODE_3)) {
                                mImageView3.setImageResource(R.drawable.cam);
                            } else if (ContentCode.equals(IMG_CONTENT_CODE_4)) {
                                mImageView4.setImageResource(R.drawable.cam);
                            } else if (ContentCode.equals(IMG_CONTENT_CODE_5)) {
                                mImageView5.setImageResource(R.drawable.cam);
                            }
                            sqlServer.setAdmCountryCode(idCountry);
                            sqlServer.setGrpCode(idGroup);
                            sqlServer.setSubGrpCode(idSubGroup);
                            sqlServer.setLocationCode(idLocation);
                            sqlServer.setContentCode(ContentCode);
                            sqlH.insertIntoUploadTable(sqlServer.deleteFromGPSLocationContentTable());

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            //show it
            alertDialog.show();
        }
    }

    private void viewImageFromDatabase(String CountryCode, String GrpCode, String subGrpCode, String LocationCode, String ContentCode, ImageView imageView) {
        sqlH.getImageFromDatabase(CountryCode, GrpCode, subGrpCode, LocationCode, ContentCode, imageView);

    }
}
