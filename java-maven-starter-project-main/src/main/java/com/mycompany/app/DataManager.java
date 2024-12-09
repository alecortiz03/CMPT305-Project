package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static DataManager instance; // Singleton instance
    private BikeRoutes bikeRoutes;
    private PropertyAssessments propertyAssessments;
    private PolygonBoundsCollection allPolygons;

    // Private constructor to initialize instances
    private DataManager() {
        this.bikeRoutes = new BikeRoutes();
        this.propertyAssessments = new PropertyAssessments();
        this.allPolygons = new PolygonBoundsCollection();
    }

    // Singleton access
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void loadBikeRoutes(String bikeRackURL) {
        if (!bikeRoutes.getRoutes().isEmpty()) {
            return;
        }
        BuildBikeRouteData buildBikeRouteData = new BuildBikeRouteData();
        this.bikeRoutes = buildBikeRouteData.BuildBikeRouteData(bikeRackURL);

        // Update route counts for polygons
        this.processRoutesInPolygons();
    }



    // Load property assessments from CSV
    public void loadPropertyAssessments(String filePath) {
        if (propertyAssessments.getRecordAmt() > 0) {
            return;
        }

        propertyAssessments = propertyAssessments.createAssessment(filePath); // Load data into property assessments
    }

    // Get polygons for route information
    public void loadPolygons(String geojsonUrl) throws IOException {
        if (propertyAssessments.getRecordAmt() > 0) {
            return;
        }
        GeoJSONParser parser = new GeoJSONParser();
        allPolygons = parser.parse(geojsonUrl);; // Load data into property assessments
    }
    public void processRoutesInPolygons() {

        allPolygons.updateRouteCounts(bikeRoutes);

    }



    public BikeRoutes getBikeRoutes() {
        return bikeRoutes;
    }

    public TableValues getTableInformation(String neighbourhoodName) {
        PropertyAssessments neighbourhood = this.propertyAssessments.searchNeighborhood(neighbourhoodName);

        int bikePaths = allPolygons.getRouteCountForNeighborhood(neighbourhoodName);


        // Property assessments stats
        int propertyCount = neighbourhood.getRecordAmt();
        int meanValue = (int) neighbourhood.calcMean();
        int medianValue = neighbourhood.calcMedian();
        int minValue = neighbourhood.getMin();
        int maxValue = neighbourhood.getMax();

        // Return or print table information
        return new TableValues(neighbourhoodName, bikePaths, propertyCount, meanValue, medianValue, minValue, maxValue);
    }



}