package com.mycompany.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoJSONParser {

    public PolygonBoundsCollection parse(String geojsonUrl) {
        PolygonBoundsCollection boundsCollection = new PolygonBoundsCollection();

        try {
            // Create a URL object from the string
            URL url = new URL(geojsonUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Parse the GeoJSON data from the URL
            JsonNode rootNode = objectMapper.readTree(connection.getInputStream());

            // Access features array
            JsonNode featuresNode = rootNode.path("features");

            // Loop through the features
            for (JsonNode featureNode : featuresNode) {
                // Access properties and geometry data
                JsonNode propertiesNode = featureNode.path("properties");
                JsonNode geometryNode = featureNode.path("geometry");

                // Extract name from properties
                String name = propertiesNode.path("name").asText();

                // Extract coordinates from geometry
                JsonNode coordinatesNode = geometryNode.path("coordinates");

                // Create a PolygonBounds object for this neighborhood
                PolygonBounds bounds = new PolygonBounds(name);

                // If the geometry type is a polygon (or multi-polygon), handle appropriately
                if (geometryNode.path("type").asText().equals("Polygon")) {
                    // Handle single polygon
                    handlePolygonCoordinates(coordinatesNode, bounds);
                } else if (geometryNode.path("type").asText().equals("MultiPolygon")) {
                    // Handle multi-polygon
                    for (JsonNode polygon : coordinatesNode) {
                        handlePolygonCoordinates(polygon, bounds);
                    }
                }

                // Add the bounds to the collection
                boundsCollection.addPolygonBounds(bounds);
            }

            // Close the connection
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return boundsCollection;
    }

    // Method to handle extracting and updating bounds for a polygon
    private void handlePolygonCoordinates(JsonNode coordinatesNode, PolygonBounds bounds) {
        if (coordinatesNode.isArray()) {
            for (JsonNode coordinateArray : coordinatesNode) {
                if (coordinateArray.isArray()) {
                    for (JsonNode coordinate : coordinateArray) {
                        if (coordinate.isArray() && coordinate.size() == 2) {
                            // Extract longitude and latitude from each coordinate pair
                            double longitude = coordinate.get(0).asDouble();
                            double latitude = coordinate.get(1).asDouble();
                            // Update the bounds
                            bounds.updateBounds(new Location(latitude, longitude));
                        } else {
                            System.out.println("Invalid coordinate format: " + coordinate);
                        }
                    }
                } else {
                    System.out.println("Invalid polygon format: " + coordinateArray);
                }
            }
        } else {
            System.out.println("Coordinates node is not an array: " + coordinatesNode);
        }
    }
}
