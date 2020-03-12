package com.madproject.spaceinvaders;

import android.content.res.Resources;

public class Rescaler {

    private double yRescaleFactor;
    private double xRescaleFactor;

    public Rescaler() {
        double height = (double) Resources.getSystem().getDisplayMetrics().heightPixels;
        double width = (double) Resources.getSystem().getDisplayMetrics().widthPixels;
        yRescaleFactor = height / 1812.0;
        xRescaleFactor = width / 1080.0;
    }

    public double getYRescaleFactor() {
        return yRescaleFactor;
    }

    public double getXRescaleFactor() {
        return xRescaleFactor;
    }
}
