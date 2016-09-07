package com.siddiquinoor.restclient.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 *
 * Created by pop
 * @since 8/12/2016.
 * @version 0.0.0
 * project: Device Measurement
 */
public class CalculationPadding {
    private static final String TAG = CalculationPadding.class.getSimpleName();
    int leftPaddSingleView;
    int rightPaddSingleView;

    private Context mContext;
    private Drawable mDrawable;

    public int getLeftPaddSingleView() {
        return leftPaddSingleView;
    }

    public void setLeftPaddSingleView(int leftPaddSingleView) {
        this.leftPaddSingleView = leftPaddSingleView;
    }

    public int getRightPaddSingleView() {
        return rightPaddSingleView;
    }

    public void setRightPaddSingleView(int rightPaddSingleView) {
        this.rightPaddSingleView = rightPaddSingleView;
    }


    public int calculateLeftPaddingSingle() {
        return calculateSidePaddingSingle();
    }

    public  int calculateRightPaddingSingle() {
        return calculateSidePaddingSingle();
    }

    public int calculateSidePaddingSingle() {
        DeviceSize deviceSize=  new DeviceSize(mContext);
        int deviceWidth = (int) deviceSize.getDpWidth();

        DrawableImageSize imageSize = new DrawableImageSize(mDrawable);
        int imageWidth = imageSize.getmWidth();

        int p = (int) (deviceWidth - imageWidth) / 2;
        Log.i(TAG, "padding :" + p);
        return p;
    }


    public void calculateSidePaddingDouble() {
        int width=calculateSidePaddingSingle();

    }
}
