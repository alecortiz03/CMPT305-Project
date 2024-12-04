public class getData {
    String bikeRouteURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
    public BikeRoutesCSV getRoutes() {
        BikeURLClass rawBikeRoutes = new BikeURLClass(this.bikeRouteURL);
        return new BikeRoutesCSV(rawBikeRoutes.getUrlInformation());
    }
}
