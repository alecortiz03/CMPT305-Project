package com.mycompany.app;

/*
 * -----------------------------------------------------------
 * Alec Ortiz - CMPT 305 - 3114013
 * -----------------------------------------------------------
 */

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;


public class PropertyAssessments {
    // List to store property assessments
    private List<PropertyAssessment> assessments = new ArrayList<>();
    private List<String> assessmentClasses = new ArrayList<>();
    private List <String> neighborhoods = new ArrayList<>();

    // Constructor to initialize the assessments list
    public PropertyAssessments(){
        this.assessments = new ArrayList<>();
        this.assessmentClasses = new ArrayList<>();
    }
    public PropertyAssessments(List<PropertyAssessment> assessments){
        this.assessments = assessments;
    
    }

    // Method to get a specific assessment by index
    public PropertyAssessment getAssessment(int index){
        PropertyAssessment assessment = new PropertyAssessment();
        assessment = assessments.get(index);
        return assessment;
    }

    // Method to add a new assessment to the list
    public void addAssessment(PropertyAssessment assessment){
        assessments.add(assessment);
    }

    // Method to get the total number of records
    public int getRecordAmt(){
        return assessments.size();
    }

    public List<String> getAccounts(){
        List<String> accounts = new ArrayList<>();
        for (PropertyAssessment assessment: this.assessments){
            if (!accounts.contains(Integer.toString(assessment.getAccountNum()))){
                accounts.add(Integer.toString(assessment.getAccountNum()));
            }
        }
        return accounts;
    }

    public List<String> getNeighborhoods(){
        List<String> neighborhoods = new ArrayList<>();
        for (PropertyAssessment assessment: this.assessments){
            if (!this.neighborhoods.contains(assessment.getNeighborhood().getNeighborhood().toLowerCase())){
                this.neighborhoods.add(assessment.getNeighborhood().getNeighborhood().toLowerCase());
            }
        }
        neighborhoods = this.neighborhoods;
        return neighborhoods;
    }
    public List<String> getClasses(){
        List<String> classes = new ArrayList<>();
        for (PropertyAssessment assessment : this.assessments) {
            for(int i = 0; i < assessment.getWardClass().getRecordAmt(); i++){
                if (!this.assessmentClasses.contains(assessment.getWardClass().getClass(i).getClassName().toLowerCase())){
                    this.assessmentClasses.add(assessment.getWardClass().getClass(i).getClassName().toLowerCase());
                }
            }
    }
    classes = this.assessmentClasses;
    return classes;
}
    // Method to calculate the median assessed value
    public int calcMedian() {
        if (this.getRecordAmt() == 1) {
            return this.assessments.get(0).getAssessedValue(); // Return the only assessment if there is only one
        }
        Collections.sort(this.assessments); // Sort the assessments
        int middle = this.getRecordAmt(); // Get the middle index
        if (middle % 2 == 1) {
            return (this.assessments.get(middle / 2).getAssessedValue()); // Return the middle value if odd number of assessments
        } else {
            int middleSum = this.assessments.get(middle / 2).getAssessedValue() + this.assessments.get((middle / 2) + 1).getAssessedValue();
            return middleSum / 2; // Return the average of the two middle values if even number of assessments
        }
    }

    // Method to calculate the mean assessed value
    public long calcMean() {
        long sum = 0; // Initialize sum to 0
        for (PropertyAssessment assessment : this.assessments) {
            sum += assessment.getAssessedValue(); // Add each assessed value to the sum
        }
        long mean = sum / assessments.size(); // Calculate the mean
        return mean; // Return the mean value formatted as currency
    }

    // Method to search for an account by account number
    public PropertyAssessment searchAccount(int accountNum) {
        List<PropertyAssessment> accountList = this.assessments.parallelStream().filter(p -> p.getAccountNum() == accountNum).collect(Collectors.toList());
        return accountList.get(0);
    }

    // Method to get the minimum assessed value
    public int getMin() {
        int min = this.getAssessment(0).getAssessedValue(); // Initialize min to the maximum integer value
        for (PropertyAssessment assessment : this.assessments) {
            if (assessment.getAssessedValue() < min) {
                min = assessment.getAssessedValue(); // Update min if a smaller value is found
            }
        }
        return min; // Return the minimum value formatted as currency
    }

    // Method to get the maximum assessed value
    public int getMax(){
        int max = this.getAssessment(0).getAssessedValue(); // Initialize max to the minimum integer value
        for (PropertyAssessment assessment : this.assessments) {
            if (assessment.getAssessedValue() > max) {
                max = assessment.getAssessedValue(); // Update max if a larger value is found
            }
        }
        return max; // Return the maximum value formatted as currency
    }

    // Method to search for assessments in a specific neighborhood
    public PropertyAssessments searchNeighborhood(String usrInput){
        List<PropertyAssessment> neighborhoodAssessmentsList = this.assessments.parallelStream().filter(p -> p.getNeighborhood().getNeighborhood().toLowerCase().equals(usrInput)).collect(Collectors.toList());
        PropertyAssessments neighborhoodAssessments = new PropertyAssessments(neighborhoodAssessmentsList);
        return neighborhoodAssessments; // Return the list of assessments in the neighborhood   
    }
    public PropertyAssessments searchAssessmentClass(String usrInput){
        PropertyAssessments assessmentClass = new PropertyAssessments(); // Initialize a new list to store assessments in the neighborhood
        for (PropertyAssessment assessment : this.assessments) {
            for(int i = 0; i < assessment.getWardClass().getRecordAmt(); i++){
                if (assessment.getWardClass().getClass(i).getClassName().toLowerCase().equals(usrInput.toLowerCase())) {
                    assessmentClass.addAssessment(assessment); // Add the assessment to the list if it is in the neighborhood
                }

            }
        }
        return assessmentClass; // Return the list of assessments in the neighborhood     
    }
    // Method to create a list of PropertyAssessment objects from a CSV file
    public PropertyAssessments createAssessment(String csvFileName) {
        // Initialize an empty list to store assessments
        PropertyAssessments assessments = new PropertyAssessments();
        try {
            // Read data from the CSV file
            String[][] data = readData(csvFileName);
            
            // Iterate through each row of data
            for (String[] row : data) {
                AssessmentClasses wardClass = new AssessmentClasses();
                // Create a new PropertyAssessment object and add it to the list
                if (row[16].equals(" ") && row[17].equals(" ")){
                    Address address = new Address(row[2], row[3]);
                    Neighborhood neighborhood = new Neighborhood(row[6], row[7]);
                    AssessmentClass assessmentClass = new AssessmentClass(row[15],row[12]);
                    wardClass.addClass(assessmentClass);
                    PropertyAssessment assessment = new PropertyAssessment(Integer.parseInt(row[0]), address, Integer.parseInt(row[8]), wardClass, neighborhood, Double.parseDouble(row[10]), Double.parseDouble(row[9]));
                    assessments.addAssessment(assessment);
                }
                else if (row[17].equals(" ")){
                    Address address = new Address(row[2], row[3]);
                    Neighborhood neighborhood = new Neighborhood(row[6], row[7]);
                    AssessmentClass assessmentClass1 = new AssessmentClass(row[15],row[12]);
                    AssessmentClass assessmentClass2 = new AssessmentClass(row[16],row[13]);
                    wardClass.addClass(assessmentClass1);
                    wardClass.addClass(assessmentClass2);
                    PropertyAssessment assessment = new PropertyAssessment(Integer.parseInt(row[0]), address, Integer.parseInt(row[8]), wardClass, neighborhood, Double.parseDouble(row[10]), Double.parseDouble(row[9]));
                    assessments.addAssessment(assessment);
                }
                else{
                    Address address = new Address(row[2], row[3]);
                    Neighborhood neighborhood = new Neighborhood(row[6], row[7]);
                    AssessmentClass assessmentClass1 = new AssessmentClass(row[15],row[12]);
                    AssessmentClass assessmentClass2 = new AssessmentClass(row[16],row[13]);
                    AssessmentClass assessmentClass3 = new AssessmentClass(row[17],row[14]);
                    wardClass.addClass(assessmentClass1);
                    wardClass.addClass(assessmentClass2);
                    wardClass.addClass(assessmentClass3);
                    PropertyAssessment assessment = new PropertyAssessment(Integer.parseInt(row[0]), address, Integer.parseInt(row[8]), wardClass, neighborhood, Double.parseDouble(row[10]), Double.parseDouble(row[9]));
                    assessments.addAssessment(assessment);
                }
            }
            
            // Return the list of assessments
            return assessments;
        } catch (IOException e) {
            // Handle exception if reading the file fails
            System.out.println("Failed to read " + csvFileName);
            return assessments;
        }
    }

    // Method to read data from a CSV file and return it as a 2D array
    private static String[][] readData(String csvFileName) throws IOException {
        String[][] data; // 2D array to store data
        int index = 0; // Initialize index to 0
        
        // Try-with-resources statement to create a stream to read the CSV file. Automatically closes the resource.
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFileName))) {
            reader.readLine(); // Skip the header line
            
            int initialSize = 100; // Initial size of the data array
            data = new String[initialSize][]; // Initialize the data array
            
            String line; // Variable to store each line of the file
            
            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(","); // Split the line by commas
                
                // Handle cases where the number of values is less than expected
                if (values.length == 16) {
                    values = Arrays.copyOf(values, values.length + 2);
                    values[16] = " ";
                    values[17] = " ";
                }
                if (values.length == 17) {
                    values = Arrays.copyOf(values, values.length + 1);
                    values[17] = " ";
                }

                // Check if the array is full and resize if necessary
                if (index == data.length) {
                    data = Arrays.copyOf(data, data.length * 2);
                }
                data[index++] = values; // Add the values to the data array
            }
        }

        // Remove empty rows in the array and return it
        return Arrays.copyOf(data, index);
    }

}
