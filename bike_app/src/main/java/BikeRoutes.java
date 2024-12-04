
import java.util.ArrayList;
import java.util.List;

public class BikeRoutes{
    private List<BikeRoute> routes = new ArrayList<>();

    public BikeRoutes(List<BikeRoute> routes) {
        this.routes = routes;
    }
    public BikeRoutes() {
        this.routes = new ArrayList<>();
    }
    public BikeRoute getRoute(int index) {
        return this.routes.get(index);
    }
    public void addRoute(BikeRoute route) {
        this.routes.add(route);
    }
    public List<BikeRoute> getRoutes() {
        return this.routes;
    }
    public void setRoutes(List<BikeRoute> routes) {
        this.routes = routes;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int indexCheck = 0;
        for (BikeRoute route : this.routes) {
            if (route != null) {
                if (indexCheck + 1 == this.routes.size()) {
                    result.append(route.toString());
                }
                else{
                    result.append(route.toString()).append(" | ");
                }
                indexCheck++;
            }
        }
        return result.toString();
    }

    
}
