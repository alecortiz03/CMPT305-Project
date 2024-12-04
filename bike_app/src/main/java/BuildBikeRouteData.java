

import java.util.List;

public class BuildBikeRouteData {

    public BuildBikeRouteData() {
    }

    public BikeRoutes BuildBikeRouteData(String url) {
        BikeRoutesAPIProcessor bikeRoutesAPIProcessor = new BikeRoutesAPIProcessor();
        BikeURLClass bikeRackLocations = new BikeURLClass(url);
        List<String> bike = bikeRackLocations.URLParse();
        BikeRoutes bikeRoutes = new BikeRoutes();
        bikeRoutes.setRoutes(bikeRoutesAPIProcessor.creatAssessment(bike));
        return bikeRoutes;

       
    }
    
}
