import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BikeRoutesCSV implements BikeRoutes{
    private List<BikeRoute> bikeRoutes = new ArrayList<>();
    
    public BikeRoutesCSV(String csvFilename) { // Parameterized constructor
        // Read the CSV file and initialize the instance variables
        
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
