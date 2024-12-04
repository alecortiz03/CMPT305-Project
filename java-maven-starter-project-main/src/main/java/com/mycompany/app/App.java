package com.mycompany.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.Viewpoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class App extends Application {

    private MapView mapView;
    private Label response;  // For showing feedback to the user

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Set the title and size of the stage
        stage.setTitle("My Map App");
        stage.setWidth(1024);
        stage.setHeight(720);
        stage.show();




        // Create the main BorderPane layout
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);

        // Set the API key for ArcGIS
        String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGlnDZ4ufU_qKFPagMXaJlIei9YH6M3I2VRE86ICIpw-zAOFnJAzYLHH0Mk85D0ldnDqT4y2CeD_oLna8Bgzkoo4zh3hFDmAC0GXvr1bLSCr6GVwBmXTFJF8j9FraOa3XuL_4W7u-t4KYMmowTY0dSKLy-kCdGK3yZapK_ziJhBFZMSrTeZIJVwh12vaaBu2gBo.AT1_j7NkVs9t";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // Create the map view
        mapView = new MapView();
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
        mapView.setMap(map);
        mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000)); // Edmonton, with appropriate scale
        mapView.setPrefWidth(600);  // Make the map view smaller
        // Allow map view to resize dynamically
        //mapView.setMaxWidth(Double.MAX_VALUE);
        //mapView.setMaxHeight(Double.MAX_VALUE);


        // Add map view to the left side of the BorderPane
        borderPane.setLeft(mapView);

        // ------------------- City Name Label -------------------
        Label cityName = new Label("Map of the City of Edmonton");
        cityName.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4A90E2;");

        // ------------------- Address Bar -------------------
        TextField addressField = new TextField();
        addressField.setPromptText("Enter address or search term");
        addressField.setPrefWidth(400);

        // ------------------- Response Label -------------------
        response = new Label("Push a button");
        response.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");



        // Create the Toggle button
        Button toggleButton = new Button("Toggle");
        toggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response.setText("Toggle button was pressed");
            }
        });

        // Create the Search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String enteredText = addressField.getText();
                response.setText("You searched for: " + enteredText);
            }
        });

        // ------------------- Statistics -------------------
        TableView<PropertyAssessment> tableView = new TableView<>();

        //Create columns for the table
        TableColumn<PropertyAssessment,String> nameCol = new TableColumn<>();
        TableColumn<PropertyAssessment,String> valueCol = new TableColumn<>();




        // Add the rows or columns to the TableView
        tableView.getColumns().addAll(nameCol, valueCol);


        // Set the data for the TableView
        //tableView.setItems();
        /*
        Label housePrices = new Label("House Prices");
        housePrices.setStyle("-fx-font-size: 14px;");

        Label mean = new Label("Mean: ");
        Label median = new Label("Median: ");
        Label max = new Label("Max: ");
        Label min = new Label("Min: ");
        Label range = new Label("Range: ");


         */

        //VBox statistics = new VBox(10);
        //statistics.setAlignment(Pos.CENTER);
        //statistics.getChildren().addAll(housePrices, mean, median, max, min, range);

        // ------------------- Right Panel -------------------
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.TOP_LEFT);
        rightPanel.getChildren().addAll(tableView, response);

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Create the HBox for buttons and place it at the top of the BorderPane
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(toggleButton, cityName, spacer, addressField, searchButton);

        // Add the HBox (buttons) at the top of the BorderPane
        borderPane.setTop(hbox);

        // Add right panel to the center of the BorderPane
        borderPane.setCenter(rightPanel); // Right section for the UI elements




    }
    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose(); // Dispose mapView resources when the app stops
        }
    }
}










///


///**
// * Copyright 2019 Esri
// *
// * Licensed under the Apache License, Version 2.0 (the "License"); you may not
// * use this file except in compliance with the License. You may obtain a copy
// * of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
// * License for the specific language governing permissions and limitations
// * under the License.
// */
//
//package com.mycompany.app;
//
//import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
//import com.esri.arcgisruntime.mapping.BasemapStyle;
//import com.esri.arcgisruntime.mapping.ArcGISMap;
//import com.esri.arcgisruntime.mapping.view.MapView;
//import com.esri.arcgisruntime.mapping.Viewpoint;
//import com.esri.arcgisruntime.portal.Portal;
//import com.esri.arcgisruntime.portal.PortalItem;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class App extends Application {
//
//    private MapView mapView;
//
//    public static void main(String[] args) {
//
//        Application.launch(args);
//    }
//
//    @Override
//    public void start(Stage stage) {
//        //FXMLloader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"))
//        // set the title and size of the stage and show it
//
//
//
//        stage.setTitle("My Map App");
//        stage.setWidth(800);
//        stage.setHeight(700);
//        stage.show();
//
//        // create a JavaFX scene with a stack pane as the root node and add it to the scene
//        StackPane stackPane = new StackPane();
//        Scene scene = new Scene(stackPane);
//        stage.setScene(scene);
//
//        // Note: it is not best practice to store API keys in source code.
//        // An API key is required to enable access to services, web maps, and web scenes hosted in ArcGIS Online.
//        // If you haven't already, go to your developer dashboard to get your API key.
//        // Please refer to https://developers.arcgis.com/java/get-started/ for more information
//        String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGnXlzPvOPgHLRsclEzGqk8WBrVOg_QU19RdNG4GJANQlmM6aMbNx43fPtHnKW87m8GgJQagx7OnyaR2FhL5Joqrg_-HrjYG7SX2DVED3ACtqUpz1m5aIEm7p-sjY_SvugJ2k7UuYod-VGP54X1Pnt5yHT3ZwIyRp1Fa8IDNwdKhH79_Iggz1sVWj3f7qZQuyeOHk4p2Dj9Rc2APgpW-WWWIAT1_MCnmWKIq";
//        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
//
//        // create a MapView to display the map and add it to the stack pane
//        mapView = new MapView();
//        stackPane.getChildren().add(mapView);
//
//        // create an ArcGISMap with an imagery basemap
//        Portal portal = new Portal("https://www.arcgis.com", false);
//
//        String itemId = "1ae528f03a75414fa574287f52e306c0";
//        PortalItem portalItem = new PortalItem(portal, itemId);
//
//        ArcGISMap map = new ArcGISMap(portalItem);
//
//        // display the map by setting the map on the map view
//        mapView.setMap(map);
//        mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));
//
//
//
//
//
//
//    }
//
//    /**
//     * Stops and releases all resources used in application.
//     */
//    @Override
//    public void stop() {
//
//        if (mapView != null) {
//            mapView.dispose();
//        }
//    }
//}