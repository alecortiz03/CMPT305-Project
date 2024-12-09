package com.mycompany.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BikeDAO {
    private final DataManager dataManager;
    private final List<Observer> observers = new ArrayList<>();



    // Constructor
    public BikeDAO() throws IOException {
        this.dataManager = DataManager.getInstance();
        this.dataManager.loadPolygons("https://data.edmonton.ca/resource/65fr-66s6.geojson");
        this.dataManager.loadBikeRoutes("https://data.edmonton.ca/resource/vd4b-a4iv.csv");
        this.dataManager.loadPropertyAssessments("Property_Assessment_Data.csv");

    }

    // Add an observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers
    private void notifyObservers(TableValues tableValues) {
        for (Observer observer : observers) {
            observer.update(tableValues);
        }
    }

    // Get a bike route by index
    public BikeRoute getBikeRoute(int index) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        dataManager.loadBikeRoutes(bikeRackURL);

        // Retrieve the route at the specified index
        return dataManager.getBikeRoutes().getRoute(index);
    }

    // Get table values for a specific neighborhood and notify observers
    public TableValues getTableValues(String neighbourhoodName) {
        TableValues tableValues = dataManager.getTableInformation(neighbourhoodName);

        // Notify observers about the new table values
        notifyObservers(tableValues);

        return tableValues;
    }


}
