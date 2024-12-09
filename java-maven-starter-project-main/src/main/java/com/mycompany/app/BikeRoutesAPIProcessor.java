package com.mycompany.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BikeRoutesAPIProcessor{
    private List<BikeRoute> bikeRoutes = new ArrayList<>();
    
    public BikeRoutesAPIProcessor(List<String> routesString) { // Parameterized constructor
        for (int i = 0; i < routesString.size(); i++) {

        }
        
    }
    public BikeRoutesAPIProcessor() { // Empty constructor
        this.bikeRoutes = new ArrayList<>();
    }
    public List<BikeRoute> creatAssessment(List<String> bike){
        List<String[]> finalData = new ArrayList<>();
        for (int i = 0; i < bike.size(); ++i) {
            String data = bike.get(i);
            String[] dataArr = data.split(",");
            finalData.add(dataArr);
        }
        
        
        
        //System.err.println(route.getLocations(1));
          for(int i = 1; i < finalData.size(); i++) {
            String[] test = finalData.get(i);
            LocationRoutes route = new LocationRoutes();
            int routeIndex = 6;
            while ((routeIndex >= 6) && (routeIndex < test.length)){
                Location location = extractCoordinates(test[routeIndex]);
                route.addLocation(location);
                routeIndex++;
            }
            if (test[0] == ""){
                test[0] = "0";
            }
            if (test[4] == ""){
                test[4] = "0";
            }
            BikeRoute testBikeRoute = new BikeRoute(Integer.parseInt(test[0].replace("\"", "")), test[1], test[2], Integer.parseInt(test[4].replace("\"", "")), route);
            bikeRoutes.add(testBikeRoute);
        }
        return bikeRoutes;
    }
    public static Location extractCoordinates(String input) {
        List<Double> coordinates = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            coordinates.add(Double.parseDouble(matcher.group()));
        }

        if (coordinates.size() >= 2) {
            return new Location(coordinates.get(1), coordinates.get(0));
        } else {
            return null; // or throw an exception if appropriate
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
