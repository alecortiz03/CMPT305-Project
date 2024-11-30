package com.mycompany.app;

public class Neighborhood {
    private String neighborhood; // Neighborhood of the property
    private String ward; // Ward of the property

    // Constructor to initialize neighborhood and ward with given values
    public Neighborhood(String neighborhood, String ward) {
        this.neighborhood = neighborhood; // Initialize neighborhood
        this.ward = ward; // Initialize ward
    }

    // Default constructor to initialize neighborhood and ward with default values
    public Neighborhood() {
        this("Unknown", "Unknown"); // Initialize neighborhood and ward with default values
    }

    // Getter method for neighborhood
    public String getNeighborhood() {
        return neighborhood; // Return neighborhood
    }

    // Getter method for ward
    public String getWard() {
        return ward; // Return ward
    }

    // Override toString method to return a string representation of the object
    public String toString() {
        return this.neighborhood + " " + "(" + this.ward + ")"; // Return formatted string
    }
}
