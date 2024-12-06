package com.mycompany.app.property; // Package declaration


/*
 * -----------------------------------------------------------
 * Alec Ortiz - CMPT 305 - 3114013
 * -----------------------------------------------------------
 */

import java.util.Objects;

public class PropertyAssessment implements Comparable<PropertyAssessment> {    
    // Private member variables
    private int accountNum; // Account number of the property
    private Address address; // Address of the property
    private int assessedVal; // Assessed value of the property
    private AssessmentClasses wardClass; // Ward class of the property
    private Neighborhood neighborhood; // Neighborhood of the property
    private Location location; // Location object representing the coordinates of the property

    // Constructor with individual location coordinates
    public PropertyAssessment(int accountNum, Address address, int assessedVal, AssessmentClasses wardClass, Neighborhood neighborhood, double x, double y){
        this.accountNum = accountNum; // Initialize account number
        this.address = address; // Initialize address
        this.assessedVal = assessedVal; // Initialize assessed value
        this.wardClass = wardClass; // Initialize ward class
        this.neighborhood = neighborhood; // Initialize neighborhood
        this.location = new Location(x, y); // Initialize location with coordinates
    }

    // Constructor with Location object
    public PropertyAssessment(int accountNum, Address address, int assessedVal, AssessmentClasses wardClass, Neighborhood neighborhood, Location location){
        this.accountNum = accountNum; // Initialize account number
        this.address = address; // Initialize address
        this.assessedVal = assessedVal; // Initialize assessed value
        this.wardClass = wardClass; // Initialize ward class
        this.neighborhood = neighborhood; // Initialize neighborhood
        this.location = location; // Initialize location with Location object
    }

    // Default constructor
    public PropertyAssessment(){
        this.accountNum = 0; // Initialize account number
        this.address = new Address(); // Initialize address
        this.assessedVal = 0; // Initialize assessed value
        this.wardClass = new AssessmentClasses(); // Initialize ward class
        this.neighborhood = new Neighborhood(); // Initialize neighborhood
        this.location = new Location(); // Initialize location with default Location object
    }

    // Getter for account number
    public int getAccountNum(){
        return accountNum; // Return account number
    }

    // Getter for address
    public Address getAddress(){
        return new Address(address.getHouseNum(), address.getStreet()); // Return address
    }

    // Getter for assessed value
    public int getAssessedValue(){
        return assessedVal; // Return assessed value
    }

    // Getter for ward class
    public AssessmentClasses getWardClass(){
        AssessmentClasses newWardClass = new AssessmentClasses();
        newWardClass = wardClass;
        return newWardClass;
    }

    // Getter for neighborhood
    public Neighborhood getNeighborhood(){
        return new Neighborhood(neighborhood.getNeighborhood(), neighborhood.getWard()); // Return neighborhood
    }

    // Getter for location (returns a new Location object to prevent modification of the original)
    public Location getLocation(){
        return new Location(location.getX(), location.getY()); // Return a new Location object with the same coordinates
    }

    // Override equals method for object comparison
    @Override
    public final boolean equals(Object o){
        if (o == this){
            return true; // If the objects are the same instance
        }
        if (!(o instanceof PropertyAssessment propertyAssessment)){
            return false; // If the object is not an instance of PropertyAssessment
        }
        // Compare all fields for equality
        return propertyAssessment.getAccountNum() == accountNum && propertyAssessment.getAddress().equals(address) 
            && propertyAssessment.getAssessedValue() == assessedVal 
            && propertyAssessment.getWardClass().equals(wardClass) 
            && propertyAssessment.getNeighborhood().equals(neighborhood)
            && propertyAssessment.getLocation().equals(location);
    }

    // Override hashCode method for hashing
    @Override
    public int hashCode(){
        return Objects.hash(accountNum, address, assessedVal, wardClass, neighborhood, location); // Generate hash code based on all fields
    }

    // Override toString method for string representation
    @Override
    public String toString(){
        return "( " + this.accountNum + ", " + this.address + ", " + this.assessedVal + ", " + this.wardClass + ", " + this.neighborhood + ", " + this.location + " )"; // Return string representation of the object
    }

    // Override compareTo method for comparison based on account number
    @Override
    public int compareTo(PropertyAssessment propertyAssessment) {
        if (this.assessedVal == propertyAssessment.assessedVal) {
            return 0; // Return 0 if assessed values are equal
        } else if (this.assessedVal > propertyAssessment.assessedVal) {
            return 1; // Return 1 if this assessed value is greater
        } else {
            return -1; // Return -1 if this assessed value is lesser
        }
    }
}
