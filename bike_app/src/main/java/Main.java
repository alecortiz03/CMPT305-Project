

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        BuildBikeRouteData buildBikeRouteData = new BuildBikeRouteData();
        BikeRoutes bikeRoutes = buildBikeRouteData.BuildBikeRouteData(bikeRackURL);
        System.out.println(bikeRoutes.getRoute(1));
    

}
}




