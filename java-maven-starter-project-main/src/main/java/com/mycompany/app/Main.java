package com.mycompany.app;

public class Main {
    public static void main(String[] args) {
        String bikeRackURL = "https://data.edmonton.ca/resource/vd4b-a4iv.csv";
        BuildBikeRouteData buildBikeRouteData = new BuildBikeRouteData();
        BikeRoutes bikeRoutes = buildBikeRouteData.BuildBikeRouteData(bikeRackURL);
//        System.out.println(bikeRoutes.getRoute(1));


        BikeDAO bikeDAO = new BikeDAO();

        ExampleObserver observer = new ExampleObserver();
        bikeDAO.addObserver(observer);

        // Fetch neighborhood table values, triggering notifications
        TableValues tableValues = bikeDAO.getNeighbourTableValues("cromdale");
        System.out.println(tableValues);

        //If you want a different one
        tableValues = bikeDAO.getNeighbourTableValues("lago lindo");
        System.out.println(tableValues);

}
}




