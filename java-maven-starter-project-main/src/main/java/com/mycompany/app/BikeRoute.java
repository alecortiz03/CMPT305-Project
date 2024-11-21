import java.util.ArrayList;
import java.util.List;

public class BikeRoute {
    private int id;
    private String type;
    private String classification;
    private int durattion;
    private List<Location> routeList = new ArrayList<>(); // List of coordinates representing the route
    


    public BikeRoute(int id, String type, String classification, int durattion, List<Location> routeList){
        this.id = id;
        this.type = type;
        this.classification = classification;
        this.durattion = durattion;
        this.routeList = routeList; // Initialize route list
        
    }
    public BikeRoute(){
        // Empty constructor
        this.id = 0;
        this.type = "";
        this.classification = "";
        this.durattion = 0;
        this.routeList = new ArrayList<>(); // Initialize route list
    }
    public int getId(){
        return this.id;
    }
    public String getType(){
        return this.type;
    }
    public String getClassification(){
        return this.classification;
    }
    public int getDuration(){
        return this.durattion;
    }
    public List<Location> getRouteList(){
        return this.routeList;
    }
    

}
