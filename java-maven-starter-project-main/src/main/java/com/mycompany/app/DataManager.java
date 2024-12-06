package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataManager {
    private static DataManager instance; // Singleton instance
    private BikeRoutes bikeRoutes;
    private PropertyAssessments propertyAssessments;

    // Private constructor to initialize instances
    private DataManager() {
        this.bikeRoutes = new BikeRoutes();
        this.propertyAssessments = new PropertyAssessments();
    }

    // Singleton access
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Load bike routes from API call
    public void loadBikeRoutes(String bikeRackURL) {
        if (!bikeRoutes.getRoutes().isEmpty()) {
            return;
        }
        BuildBikeRouteData buildBikeRouteData = new BuildBikeRouteData();
        this.bikeRoutes = buildBikeRouteData.BuildBikeRouteData(bikeRackURL);
    }

    // Load property assessments from CSV
    public void loadPropertyAssessments(String filePath) {
        if (propertyAssessments.getRecordAmt() > 0) {
            return;
        }

        propertyAssessments = propertyAssessments.createAssessment(filePath); // Load data into property assessments
    }

    public BikeRoutes getBikeRoutes() {
        return bikeRoutes;
    }

    public PropertyAssessments getPropertyAssessments() {
        return propertyAssessments;
    }

    private LocationRoutes parseLocationRoutes(String routeString) {
        LocationRoutes locationRoutes = new LocationRoutes();
        String[] points = routeString.split(";");
        for (String point : points) {
            String[] coords = point.split(",");
            double latitude = Double.parseDouble(coords[0].trim());
            double longitude = Double.parseDouble(coords[1].trim());
            locationRoutes.addLocation(new Location(latitude, longitude));
        }
        return locationRoutes;
    }

    public TableValues getTableInformation(String neighbourhoodName) {
        PropertyAssessments neighbourhood = this.propertyAssessments.searchNeighborhood(neighbourhoodName);

        int bikeCount = bikeRoutes.getRoutes().size();



        // Property assessments stats
        int propertyCount = neighbourhood.getRecordAmt();
        int meanValue = (int) neighbourhood.calcMean();
        int medianValue = neighbourhood.calcMedian();
        int minValue = neighbourhood.getMin();
        int maxValue = neighbourhood.getMax();

        // Return or print table information
        return new TableValues(bikeCount, propertyCount, meanValue, medianValue, minValue, maxValue);
    }
}