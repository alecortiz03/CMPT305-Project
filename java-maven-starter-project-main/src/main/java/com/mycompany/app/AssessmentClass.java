package com.mycompany.app;

public class AssessmentClass {
    // Private member variables to store class name and percentage
    private String className;
    private String percent;

    // Constructor to initialize className and percent with provided values
    public AssessmentClass(String className, String percent){
        this.className = className;
        this.percent = percent;
    }

    // Default constructor to initialize className and percent with "Unknown"
    public AssessmentClass(){
        this("Unknown", "Unknown");
    }

    // Getter method for className
    public String getClassName(){
        return className;
    }

    // Getter method for percent
    public String getPercent(){
        return percent;
    }

    // Overriding toString method to return a string representation of the object
    public String toString(){
        return this.className + " " + this.percent + "% ";
    }
}
