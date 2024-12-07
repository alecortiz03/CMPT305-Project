package com.mycompany.app;

public class TableValues {
    private int bikeCount;
    private int propertyCount;
    private int meanValue;
    private int medianValue;
    private int minValue;
    private int maxValue;

    // Constructor
    public TableValues(int bikeCount, int propertyCount, int meanValue, int medianValue, int minValue, int maxValue) {
        this.bikeCount = bikeCount;
        this.propertyCount = propertyCount;
        this.meanValue = meanValue;
        this.medianValue = medianValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}