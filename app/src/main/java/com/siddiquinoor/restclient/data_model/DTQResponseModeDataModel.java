package com.siddiquinoor.restclient.data_model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shuvo on 29/09/2016.
 */
public class DTQResponseModeDataModel implements Parcelable {

    private String dtQResMode;
    private String dtQResLupText;
    private String dtDataType;
    private String dtLookUpUDFName;
    private String dtResponseValueControl;


    public static final Creator<DTQResponseModeDataModel> CREATOR = new Creator<DTQResponseModeDataModel>() {
        @Override
        public DTQResponseModeDataModel createFromParcel(Parcel in) {
            return new DTQResponseModeDataModel(in);
        }

        @Override
        public DTQResponseModeDataModel[] newArray(int size) {
            return new DTQResponseModeDataModel[size];
        }
    };

    public DTQResponseModeDataModel() {
    }

    protected DTQResponseModeDataModel(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {

        dtQResMode = in.readString();
        dtQResLupText = in.readString();
        dtDataType = in.readString();
        dtLookUpUDFName = in.readString();
        dtResponseValueControl = in.readString();


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(dtQResMode);
        parcel.writeString(dtQResLupText);
        parcel.writeString(dtDataType);
        parcel.writeString(dtLookUpUDFName);
        parcel.writeString(dtResponseValueControl);

    }

    public String getDtResponseValueControl() {
        return dtResponseValueControl;
    }

    public void setDtResponseValueControl(String dtResponseValueControl) {
        this.dtResponseValueControl = dtResponseValueControl;
    }

    public String getDtQResMode() {
        return dtQResMode;
    }

    public void setDtQResMode(String dtQResMode) {
        this.dtQResMode = dtQResMode;
    }

    public String getDtQResLupText() {
        return dtQResLupText;
    }

    public void setDtQResLupText(String dtQResLupText) {
        this.dtQResLupText = dtQResLupText;
    }

    public String getDtDataType() {
        return dtDataType;
    }

    public void setDtDataType(String dtDataType) {
        this.dtDataType = dtDataType;
    }

    public String getDtLookUpUDFName() {
        return dtLookUpUDFName;
    }

    public void setDtLookUpUDFName(String dtLookUpUDFName) {
        this.dtLookUpUDFName = dtLookUpUDFName;
    }
}
