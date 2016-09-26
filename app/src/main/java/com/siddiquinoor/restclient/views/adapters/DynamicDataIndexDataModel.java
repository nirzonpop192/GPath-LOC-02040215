package com.siddiquinoor.restclient.views.adapters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by USER on 9/26/2016.
 */
public class DynamicDataIndexDataModel implements Parcelable {
    private String dtTittle;
    private String dtBasicCode;
    private String awardCode;
    private String awardName;
    private String programCode;
    private String programName;
    private String prgActivityTitle;

    public DynamicDataIndexDataModel() {
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public DynamicDataIndexDataModel createFromParcel(Parcel in) {
            return new DynamicDataIndexDataModel(in);
        }

        public DynamicDataIndexDataModel[] newArray(int size) {
            return new DynamicDataIndexDataModel[size];
        }
    };

    public DynamicDataIndexDataModel(Parcel in) {
        readFromParcel(in);
    }


    private void readFromParcel(Parcel in) {
        dtTittle = in.readString();
        dtBasicCode = in.readString();
        awardCode = in.readString();
        awardName = in.readString();
        programCode = in.readString();
        programName = in.readString();
        prgActivityTitle = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dtTittle);
        dest.writeString(dtBasicCode);
        dest.writeString(awardCode);
        dest.writeString(awardName);
        dest.writeString(programCode);
        dest.writeString(programName);
        dest.writeString(prgActivityTitle);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public String getDtTittle() {
        return dtTittle;
    }

    public void setDtTittle(String dtTittle) {
        this.dtTittle = dtTittle;
    }

    public String getDtBasicCode() {
        return dtBasicCode;
    }

    public void setDtBasicCode(String dtBasicCode) {
        this.dtBasicCode = dtBasicCode;
    }

    public String getAwardCode() {
        return awardCode;
    }

    public void setAwardCode(String awardCode) {
        this.awardCode = awardCode;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getPrgActivityTitle() {
        return prgActivityTitle;
    }

    public void setPrgActivityTitle(String prgActivityTitle) {
        this.prgActivityTitle = prgActivityTitle;
    }
}
