package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;

public class TableValues {
    private String neighborhood;
    private int bikePaths;
    private int propertyCount;
    private long meanValue;
    private int medianValue;
    private int minValue;
    private int maxValue;


    @Override
    public String toString() {
        return "TableValues{" +
                "bikeCount=" + bikePaths +
                ", propertyCount=" + propertyCount +
                ", meanValue=" + meanValue +
                ", medianValue=" + medianValue +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }

    // Constructor
    public TableValues() {
        this.neighborhood = "";
        this.bikePaths = 0;
        this.propertyCount = 0;
        this.meanValue = 0;
        this.medianValue = 0;
        this.minValue = 0;
        this.maxValue = 0;
    }


    public TableValues(String neighborhood, int bikePaths, int propertyCount, long meanValue, int medianValue, int minValue, int maxValue) {
        this.neighborhood = neighborhood;
        this.bikePaths = bikePaths;
        this.propertyCount = propertyCount;
        this.meanValue = meanValue;
        this.medianValue = medianValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public int getBikePaths() {
        return bikePaths;
    }
    // Observer management




    public int getPropertyCount() {
        return propertyCount;
    }

    public int getMedianValue() {
        return medianValue;
    }

      

    public int getMinValue() {
        return minValue;
    }


    public long getMeanValue() {
        return meanValue;
    }
    public int getMaxValue() {
        return maxValue;
    }


}
