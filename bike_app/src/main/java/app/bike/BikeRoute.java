package app.bike;

public class BikeRoute {
    private int id;
    private String type;
    private String classification;
    private int duration;
    private LocationRoutes routeList; // List of coordinates representing the route
    


    public BikeRoute(int id, String type, String classification, int duration, LocationRoutes routeList){
        this.id = id;
        this.type = type;
        this.classification = classification;
        this.duration = duration;
        this.routeList = routeList; // Initialize route list
        
    }
    public BikeRoute(){
        // Empty constructor
        this.id = 0;
        this.type = "";
        this.classification = "";
        this.duration = 0;
        this.routeList = new LocationRoutes(); // Initialize route list
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
        return this.duration;
    }
    public LocationRoutes getRouteList(){
        return this.routeList;
    }
    public String toString(){
        return "ID: " + this.id + " Type: " + this.type + " Classification: " + this.classification + " Duration: " + this.duration + " Route: " + this.routeList;
    }

}
