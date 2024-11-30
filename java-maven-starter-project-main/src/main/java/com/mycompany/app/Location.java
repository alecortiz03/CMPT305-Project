package com.mycompany.app; // Declaring the package name    

import java.util.Objects; // Importing the Objects class for utility methods

/**
 * Alec Ortiz - CMPT 305 - 3114013
 * 
 * The Location class represents a point in a 2D space with x and y coordinates.
 * It implements the Comparable interface to allow comparison between Location objects.
 * @author Alec Ortiz 
 * @version 1.0
 */
public class Location implements Comparable<Location> { // Declaring the Location class which implements Comparable interface
    private double x; // Private variable x to store x-coordinate
    private double y; // Private variable y to store y-coordinate

    public Location(double x, double y) { // Constructor to initialize x and y
        this.x = x; // Assigning x to the instance variable
        this.y = y; // Assigning y to the instance variable
    }

    public Location() { // Default constructor
        this(0.0, 0.0); // Calling the parameterized constructor with default values 0, 0
    }

    public String toString() { // Overriding toString method
        return "( " + this.x + ", " + this.y + " )"; // Returning the string representation of the Location object
    }

    public double getX() { // Getter method for x
        return x; // Returning the value of x
    }

    public double getY() { // Getter method for y
        return y; // Returning the value of y
    }

    @Override
    public final boolean equals(Object o) { // Overriding equals method
        if (o == this) { // If the object is compared with itself
            return true; // Return true
        }
        if (!(o instanceof Location)) { // If the object is not an instance of Location
            return false; // Return false
        }
        Location location = (Location) o; // Typecast o to Location
        return x == location.getX() && y == location.getY(); // Compare x and y values
    }

    @Override
    public int hashCode() { // Overriding hashCode method
        return Objects.hash(x, y); // Returning hash code based on x and y
    }

    @Override
    public int compareTo(Location location) { // Overriding compareTo method
        if (location == null) { // If the location is null
            throw new NullPointerException("Location cannot be null"); // Throw NullPointerException
        }
        if (this.x == location.getX() && this.y == location.getY()) { // If both x and y are equal
            return 0; // Return 0
        } else if (this.x > location.getX() || (this.x == location.getX() && this.y > location.getY())) { // If this location is greater
            return 1; // Return 1
        } else { // If this location is smaller
            return -1; // Return -1
        }
    }
}
