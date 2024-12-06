package com.mycompany.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;  // Import the File class
import java.util.Scanner;  // Import this class to handle errors
import java.util.regex.Matcher; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.mycompany.app.property.Location; // Package declaration

public class BikeRoutesCSV implements BikeRoutes{
    private List<BikeRoute> bikeRoutes = new ArrayList<>();
    
    public BikeRoutesCSV(String csvFilename) { // Parameterized constructor
        // Read the CSV file and initialize the instance variables
        try {
            File file = new File(csvFilename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                List<Location> routes = new ArrayList<>();
                Pattern pattern = Pattern.compile("(-?\\d+\\.\\d+) (-?\\d+\\.\\d+)");
                Matcher matcher = pattern.matcher(values[6]);
                while (matcher.find()) {
                    double longitude = Double.parseDouble(matcher.group(1));
                    double latitude = Double.parseDouble(matcher.group(2));
                    routes.add(new Location(latitude, longitude));
        }
                BikeRoute bikeRoute = new BikeRoute(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[4]), routes);
                this.bikeRoutes.add(bikeRoute);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    public int getRecordCount() { // Overriding the getRecordCount method
        return this.bikeRoutes.size(); // Returning the size of the BikeRoutes list
    }
    public List<BikeRoute> getOffRoad(){
        List<BikeRoute> offRoadRoutes = this.bikeRoutes.stream().filter(route -> route.getClassification().toLowerCase().equals("off road")).collect(Collectors.toList());
        return offRoadRoutes;
    }
    public List<BikeRoute> getOnRoad(){
        List<BikeRoute> onRoadRoutes = this.bikeRoutes.stream().filter(route -> route.getClassification().toLowerCase().equals("on road")).collect(Collectors.toList());
        return onRoadRoutes;
    }
    

    
    
    
}
