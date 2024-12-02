package app.bike;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        BikeURLClass bikeRackLocations = new BikeURLClass(bikeRackURL);
        List<String> bike = bikeRackLocations.URLParse();

        BikeRoutesAPIProcessor bikeRoutesAPIProcessor = new BikeRoutesAPIProcessor();
        List<BikeRoute> bikeRoutes = bikeRoutesAPIProcessor.creatAssessment(bike);
        System.out.println(bikeRoutes.get(0));

        
    }
    

}




