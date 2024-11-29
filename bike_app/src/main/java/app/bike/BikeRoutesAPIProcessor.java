package app.bike;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BikeRoutesAPIProcessor implements BikeRoutes{
    private List<BikeRoute> bikeRoutes = new ArrayList<>();
    
    public BikeRoutesAPIProcessor(List<String> routesString) { // Parameterized constructor
                for (int i = 0; i < routesString.size(); i++) {
                    
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
