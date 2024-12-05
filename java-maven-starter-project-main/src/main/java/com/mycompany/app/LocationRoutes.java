package com.mycompany.app;

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

    public Location getLocations(int index) {
        return this.routes.get(index);
    }
    public void addLocation(Location location) {
        this.routes.add(location);
        }

    @Override
    public String toString() {
    StringBuilder result = new StringBuilder();
    int indexCheck = 0;
    for (Location location : this.routes) {
        if (location != null) {
            if (indexCheck + 1 == this.routes.size()) {
                result.append(location.toString());
            }
            else{
        result.append(location.toString()).append(" | ");
            }
            indexCheck++;
        }
        
    }
    return result.toString();
}
    
}