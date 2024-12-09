package com.mycompany.app;

public class ExampleObserver implements Observer {
    @Override
    public void update(TableValues newValues) {
        System.out.println("TableValues have been updated:");
        System.out.println("Bike Count: " + newValues.getBikePaths());
        System.out.println("Properties: " + newValues.getPropertyCount());
        System.out.println("Mean Value: " + newValues.getMedianValue());
        System.out.println("Median Value: " + newValues.getMedianValue());
        System.out.println("Min Value: " + newValues.getMinValue());
        System.out.println("Max Value: " + newValues.getMaxValue());
    }
}