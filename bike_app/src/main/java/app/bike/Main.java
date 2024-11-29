package app.bike;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        BikeURLClass bikeRackLocations = new BikeURLClass(bikeRackURL);
        List<String> bike = bikeRackLocations.URLParse();

        String commaSeparatedString = bike.get(1);
        String[] wordsArray = commaSeparatedString.split(",");
        List<String[]> finalData = new ArrayList<>();
        for (int i = 0; i < bike.size(); ++i) {
            String data = bike.get(i);
            String[] dataArr = data.split(",");
            finalData.add(dataArr);
        }

        String[] test = finalData.get(1);

        for(int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
        LocationRoutes route = new LocationRoutes();

    }

    public static Location extractCoordinates(String input) {
        List<Double> coordinates = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            coordinates.add(Double.parseDouble(matcher.group()));
        }

        if (coordinates.size() >= 2) {
            return new Location(coordinates.get(0), coordinates.get(1));
        } else {
            return null; // or throw an exception if appropriate
        }
    }
}




