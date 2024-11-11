package app.bike;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/rter-xgyu.json";
        URLClass bikeRackLocations = new URLClass(bikeRackURL);
        System.out.println(bikeRackLocations.URLParse());
    }
}

