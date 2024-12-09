package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class PolygonBoundsCollection {
    private List<PolygonBounds> polygonBoundsList;

    public PolygonBoundsCollection() {
        this.polygonBoundsList = new ArrayList<>();
    }

    // Add a PolygonBounds to the collection
    public void addPolygonBounds(PolygonBounds polygonBounds) {
        this.polygonBoundsList.add(polygonBounds);
    }

    // Method to update route counts for all polygons
    public void updateRouteCounts(BikeRoutes bikeRoutes) {
        // Iterate over all the bike routes
        for (BikeRoute bikeRoute : bikeRoutes.getRoutes()) {
            int routeId = bikeRoute.getId();  // Get the ID of the bike route
            LocationRoutes routeCoordinates = bikeRoute.getRouteList();  // Get the list of coordinates for the route

            // For each polygon, check if the route is within its bounds
            for (PolygonBounds polygon : polygonBoundsList) {
                // Check if the route is within the polygon's bounds
                polygon.checkAndAddRoute(routeId, routeCoordinates.getLocations());
            }
        }
    }

    public int getRouteCountForNeighborhood(String neighborhoodName) {
        for (PolygonBounds polygon : polygonBoundsList) {
            if (polygon.getNeighborhoodName().equalsIgnoreCase(neighborhoodName)) {
                return polygon.getRouteIds().size();
            }
        }
        return 0; // Return 0 if the neighborhood is not found
    }

    // Sort polygons by the number of routes they contain
    public void sortByRouteCount() {
        polygonBoundsList.sort(Comparator.comparingInt(polygon -> polygon.getRouteIds().size()));
    }

    // Sort polygons by neighborhood name
    public void sortByNeighborhoodName() {
        polygonBoundsList.sort(Comparator.comparing(PolygonBounds::getNeighborhoodName));
    }

    // Getter for polygonBoundsList
    public List<PolygonBounds> getPolygonBoundsList() {
        return polygonBoundsList;
    }

    // Override toString for debugging purposes
    @Override
    public String toString() {
        return "PolygonBoundsCollection{" +
                "polygonBoundsList=" + polygonBoundsList +
                '}';
    }
}
