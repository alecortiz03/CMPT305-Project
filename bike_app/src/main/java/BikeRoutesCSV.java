import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;  // Import the File class
import java.util.Scanner;  // Import this class to handle errors
import java.util.regex.Matcher; // Import the Scanner class to read text files
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                bikeRouteBuilder(values);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BikeRoutesCSV(List<String> fromURL) {
        for (int i = 1; i < fromURL.size(); i++) { // Skip header row
            try {
                System.out.println("Processing row: " + fromURL.get(i));
                String[] values = fromURL.get(i).replace("\"", "").split(",");

                if (values.length < 7) {
                    System.err.println("Skipping malformed row: " + fromURL.get(i));
                    continue;
                }

                bikeRouteBuilder(values);

            } catch (Exception e) {
                // Log and continue on error
                System.err.println("Error processing row: " + fromURL.get(i));
                e.printStackTrace();
            }
        }
    }

    private void bikeRouteBuilder(String[] values) {

        List<Location> routes = new ArrayList<>();
        Pattern pattern = Pattern.compile("(-?\\d+\\.\\d+) (-?\\d+\\.\\d+)");
        Matcher matcher = pattern.matcher(values[6]);
        while (matcher.find()) {
            double longitude = Double.parseDouble(matcher.group(1));
            double latitude = Double.parseDouble(matcher.group(2));
            routes.add(new Location(latitude, longitude));
        }

        int routeID = Integer.parseInt(values[0].trim());
        String routeName = values[1].trim();
        String classification = values[2].trim();

        // Added these lines because it would randomly crash.
        int usage;
        try {
            usage = !values[4].trim().isEmpty() ? Integer.parseInt(values[4].trim()) : 0;
        } catch (NumberFormatException e) {
            System.err.println("Invalid usage, setting to 0: " + values[4]);
            usage = 0;
        }

        this.bikeRoutes.add(new BikeRoute(routeID, routeName, classification, usage, routes));
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
