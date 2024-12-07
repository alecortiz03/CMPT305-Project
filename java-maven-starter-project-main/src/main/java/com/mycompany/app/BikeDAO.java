package com.mycompany.app;

import java.io.File;
import java.nio.file.Paths;

public class BikeDAO {
    private final DataManager dataManager;

    // Constructor
    public BikeDAO() {
        this.dataManager = DataManager.getInstance();
        this.dataManager.loadPropertyAssessments("Property_Assessment_Data.csv");
        this.dataManager.loadBikeRoutes("https://data.edmonton.ca/resource/vd4b-a4iv.csv");

    }

    public BikeRoute getBikeRoute(int index) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        dataManager.loadBikeRoutes(bikeRackURL);

        // Retrieve the route at the specified index
        return dataManager.getBikeRoutes().getRoute(index);
    }

    public TableValues getTableValues(String neighbourhoodName) {
        String propertiesFile = "Property_Assessment_Data.csv";
        dataManager.loadPropertyAssessments(propertiesFile);
        return dataManager.getTableInformation(neighbourhoodName);

    }
}
