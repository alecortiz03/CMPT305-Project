package app.bike;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.json";
        BikeURLClassJson bikeJson = new BikeURLClassJson(bikeRackURL);
        System.out.println(bikeJson.URLParse());
    }
}

