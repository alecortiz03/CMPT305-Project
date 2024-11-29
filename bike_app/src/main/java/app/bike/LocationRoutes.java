package app.bike;
import java.util.ArrayList;
import java.util.List;

public class LocationRoutes {
    List<Location> routes = new ArrayList<>();

    public LocationRoutes(List<Location> routes) {
        this.routes = routes;
    }
    public LocationRoutes() {
        this.routes = new ArrayList<>();
    }

    public void getLocations(int index) {
        this.routes.get(index);
    }
    public void addLocation(Location location) {
        this.routes.add(location);
    }
    
}