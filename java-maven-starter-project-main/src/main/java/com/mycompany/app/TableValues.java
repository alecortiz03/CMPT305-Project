package com.mycompany.app;

public class TableValues {
    private String neighborhood;
    private int bikePaths;
    private int propertyCount;
    private long meanValue;
    private int medianValue;
    private int minValue;
    private int maxValue;

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

    public int getPropertyCount() {
        return propertyCount;
    }

    public long getMeanValue() {
        return meanValue;
    }
    public int getMedianValue() {
        return medianValue;
    }

    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue() {
        return maxValue;
    }

}
