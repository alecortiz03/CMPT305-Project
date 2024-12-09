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

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public String toString() {
        return "TableValues{" +
                "bikeCount=" + bikeCount +
                ", propertyCount=" + propertyCount +
                ", meanValue=" + meanValue +
                ", medianValue=" + medianValue +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", observers=" + observers +
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
    // Observer management
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public int getBikeCount() {
        return bikeCount;
    }

    public void setBikeCount(int bikeCount) {
        this.bikeCount = bikeCount;
        notifyObservers();
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public void setPropertyCount(int propertyCount) {
        this.propertyCount = propertyCount;
        notifyObservers();
    }

    public int getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(int meanValue) {
        this.meanValue = meanValue;
        notifyObservers();
    }

    public int getMedianValue() {
        return medianValue;
    }

    public void setMedianValue(int medianValue) {
        this.medianValue = medianValue;
        notifyObservers();
    }
      

    public int getMinValue() {
        return minValue;
    }
      
    public void setMinValue(int minValue) {
        this.minValue = minValue;
        notifyObservers();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        notifyObservers();
    }
}
