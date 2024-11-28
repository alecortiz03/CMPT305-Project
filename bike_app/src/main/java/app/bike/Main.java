package app.bike;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.json";
        URLClass bikeRackLocations = new URLClass(bikeRackURL);
        System.out.println(bikeRackLocations.URLParse());
    }
}

