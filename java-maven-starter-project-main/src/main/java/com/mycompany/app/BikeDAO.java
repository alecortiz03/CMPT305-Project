package com.mycompany.app;

public class BikeDAO {
    private final DataManager dataManager;

    // Constructor
    public BikeDAO() {
        this.dataManager = DataManager.getInstance();
    }

    public BikeRoute getBikeRoute(int index) {
        // Load data if not already loaded
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        dataManager.loadBikeRoutes(bikeRackURL);

        // Retrieve the route at the specified index
        return dataManager.getBikeRoutes().getRoute(index);
    }
}
