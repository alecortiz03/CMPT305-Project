package app.bike;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/rter-xgyu.csv";
        String bikeRouteURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        BikeURLClass bikeRackLocations = new BikeURLClass(bikeRackURL);
        BikeURLClass bikeRoutes = new BikeURLClass(bikeRouteURL);
        System.out.println(bikeRoutes);

    }
}

