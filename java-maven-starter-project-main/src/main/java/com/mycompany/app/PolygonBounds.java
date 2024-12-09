package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PolygonBounds {
    private String neighborhoodName;
    private Location maxLatLong = new Location(Double.MIN_VALUE, Double.MIN_VALUE); // Default to minimum values
    private Location minLatLong = new Location(Double.MAX_VALUE, Double.MAX_VALUE); // Default to maximum values
    private List<Integer> routeIds = new ArrayList<>(); // List to hold IDs of routes within bounds

    // Constructor
    public PolygonBounds(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    // Update bounds method using Location class
    public void updateBounds(Location location) {
        double latitude = location.getX(); // Get latitude (x) from Location
        double longitude = location.getY(); // Get longitude (y) from Location
        // Update minLatLong and maxLatLong based on the location
        if (latitude < minLatLong.getX() || (latitude == minLatLong.getX() && longitude < minLatLong.getY())) {
            minLatLong = location; // Update minLatLong if the current location is smaller
        }
        if (latitude > maxLatLong.getX() || (latitude == maxLatLong.getX() && longitude > maxLatLong.getY())) {
            maxLatLong = location; // Update maxLatLong if the current location is larger
        }
    }

    // Check if the route is within bounds and add the route ID to the list
    public void checkAndAddRoute(int routeId, List<Location> coordinates) {
        // Skip if route is already in the list
        if (routeIds.contains(routeId)) {
            return;
        }

        // Flag to track if the route has at least one coordinate within bounds
        boolean isWithinBounds = false;

        // Loop through each coordinate in the route to check if it's within bounds
        for (Location location : coordinates) {
            // Skip null locations
            if (location == null) {
                continue; // Skip to the next location if current location is null
            }

            double latitude = location.getX();  // Latitude (X)
            double longitude = location.getY(); // Longitude (Y)

            // Check if this location is within the polygon's bounds
            boolean isWithinLat = latitude >= minLatLong.getX() && latitude <= maxLatLong.getX();
            boolean isWithinLon = longitude >= minLatLong.getY() && longitude <= maxLatLong.getY();

            // If this coordinate is within bounds, mark the route as within bounds
            if (isWithinLat && isWithinLon) {
                isWithinBounds = true;
                break;  // Exit the loop early since we only need one coordinate within bounds
            }
        }

        // If at least one coordinate is within bounds, add the route ID to the list
        if (isWithinBounds) {
            routeIds.add(routeId);
        }
    }


    // Getter for routeIds
    public List<Integer> getRouteIds() {
        return routeIds;
    }

    // Getter for neighborhoodName
    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    // Getter for minLatLong and maxLatLong
    public Location getMinLatLong() {
        return minLatLong;
    }

    public Location getMaxLatLong() {
        return maxLatLong;
    }

    // Override toString to print out information about the PolygonBounds
    @Override
    public String toString() {
        return "PolygonBounds{" +
                "neighborhoodName='" + neighborhoodName + '\'' +
                ", minLatLong=" + minLatLong +
                ", maxLatLong=" + maxLatLong +
                ", routeIds=" + routeIds +
                '}';
    }

    // Override equals and hashCode to ensure proper comparison of PolygonBounds
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PolygonBounds that = (PolygonBounds) obj;
        return Objects.equals(neighborhoodName, that.neighborhoodName) &&
                Objects.equals(minLatLong, that.minLatLong) &&
                Objects.equals(maxLatLong, that.maxLatLong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neighborhoodName, minLatLong, maxLatLong);
    }
}
