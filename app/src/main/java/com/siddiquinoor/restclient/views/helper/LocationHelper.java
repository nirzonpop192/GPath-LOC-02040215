package com.siddiquinoor.restclient.views.helper;

/**
 * Created by USER on 8/29/2016.
 */
public class LocationHelper {

    private int position;
    private String databaseId;
    private String databaseValue;
    private String locationExits;

    public LocationHelper ( int position, String databaseId , String databaseValue , String locationExits ) {
        this.position = position;
        this.databaseId = databaseId;
        this.databaseValue = databaseValue;
        this.locationExits = locationExits;
    }

    public String getLocationExits() {
        return locationExits;
    }

    public int getSpinnerPosition () {
        return position;
    }

    public String getId () {
        return databaseId;
    }

    public String getValue () {
        return databaseValue;
    }

    @Override
    public String toString () {
        return databaseValue;
    }


}


