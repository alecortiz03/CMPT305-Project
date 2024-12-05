package com.mycompany.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataManager {
    private static DataManager instance; // Singleton instance
    private BikeRoutes bikeRoutes; // Parsed bike routes

    // Private constructor to prevent direct instantiation
    private DataManager() {
        this.bikeRoutes = new BikeRoutes();
    }

    // Singleton access
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    // Load bike routes from CSV (only once)
    public void loadBikeRoutes(String filePath) {
        if (!bikeRoutes.getRoutes().isEmpty()) {
            return; // Avoid reloading if data already loaded
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Parse CSV values
                int id = Integer.parseInt(values[0].trim());
                String type = values[1].trim();
                String classification = values[2].trim();
                int duration = Integer.parseInt(values[3].trim());
                LocationRoutes routeList = parseLocationRoutes(values[4].trim());

                // Create BikeRoute and add to BikeRoutes
                BikeRoute bikeRoute = new BikeRoute(id, type, classification, duration, routeList);
                bikeRoutes.addRoute(bikeRoute);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading bike routes CSV: " + e.getMessage());
        }
    }

    // Getter for BikeRoutes
    public BikeRoutes getBikeRoutes() {
        return bikeRoutes;
    }

    // Parse location routes from a string (example: "lat1,lon1;lat2,lon2;...")
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
}
