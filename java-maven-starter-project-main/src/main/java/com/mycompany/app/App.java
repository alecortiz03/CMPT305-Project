/**
 * Copyright 2019 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package com.mycompany.app;

 import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
 import com.esri.arcgisruntime.concurrent.ListenableFuture;
 import com.esri.arcgisruntime.layers.FeatureCollectionLayer;
 import com.esri.arcgisruntime.layers.FeatureLayer;
 import com.esri.arcgisruntime.mapping.ArcGISMap;
 import com.esri.arcgisruntime.mapping.GeoElement;
 import com.esri.arcgisruntime.mapping.Viewpoint;
 import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
 import com.esri.arcgisruntime.mapping.view.MapView;
 import com.esri.arcgisruntime.portal.Portal;
 import com.esri.arcgisruntime.portal.PortalItem;
 
 import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
 import javafx.geometry.Point2D;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
 import javafx.scene.layout.*;
 import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONObject;


public class App extends Application {
 
     private MapView mapView;
     private ArcGISMap map1;
     private ArcGISMap map2;
     private Label response;
     private ToggleButton toggleButton;
    private ToggleButton toggleButton2;
     private boolean showingMap1 = true; // Flag to track the current map
 
     public static void main(String[] args) {
         Application.launch(args);
     }
 
     @Override
     public void start(Stage stage) {
 
         // Set the title and size of the stage and show it
         stage.setTitle("My Map App");
         stage.setWidth(1024);
         stage.setHeight(720);
         stage.show();
 
         // Create a BorderPane as the root layout
         BorderPane borderPane = new BorderPane();
         Scene scene = new Scene(borderPane);
         stage.setScene(scene);
 
         // Note: it is not best practice to store API keys in source code.
         String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGmU_I7A-_kGUQSnF7Q-oQyQRV-g4hD99UbwxWZmJdnlTR5H7BS9-dhS2ZV7l8VmblhYq1WMiweHSHAcQp-2GvfoJf_UySN5EQv_59SCJt-JQiWJgxQ9yMcLm0LMnP8DhaEhvQvXEZXql3fn5fQw7rBWJ2s7NhSJ1VTTROOO-o0B9I1jwtlzYyR1cY5y75eXu_3YZmVLr_WE828LszzoRIuvAT1_MCnmWKIq";
         ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
 
         // Create the MapView to display the maps
         mapView = new MapView();
 
         // Create two maps with different basemaps or content
         Portal portal = new Portal("https://www.arcgis.com", false);
 
         String itemId1 = "1ae528f03a75414fa574287f52e306c0";
         PortalItem portalItem1 = new PortalItem(portal, itemId1);
         map1 = new ArcGISMap(portalItem1);

         String itemId2= "1fa7f000f33f406eadf4123b9f426325";
         PortalItem portalItem2 = new PortalItem(portal, itemId2);
         FeatureLayer nLayer = new FeatureLayer(portalItem2);

         String itemId3 = "8a2f8b857f1647ba8c1f95974dfde427";
         PortalItem portalItem3 = new PortalItem(portal, itemId3);
         FeatureLayer bikeLayer = new FeatureLayer(portalItem3);




         // Set the initial map to map1
         mapView.setMap(map1);
         mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));
         mapView.setPrefWidth(600);

         
         
 
         
 
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
        toggleButton = new ToggleButton("Neighbourhood View");
        

        toggleButton2 = new ToggleButton("Bike Route View");

        toggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleLayer(nLayer, newValue);
        });

        toggleButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            toggleLayer(bikeLayer, newValue);
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
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Create the HBox for buttons and place it at the top of the BorderPane
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(toggleButton, toggleButton2, cityName, spacer, addressField, searchButton);

        // Add the HBox (buttons) at the top of the BorderPane
        borderPane.setTop(hbox);

        // Add right panel to the center of the BorderPane
        borderPane.setCenter(rightPanel); // Right section for the UI elements
        getCurrentLocation();        
     }


     private void toggleLayer(FeatureLayer layer, boolean addLayer) {
        if (addLayer) {
            map1.getOperationalLayers().add(layer);
        } else {
            map1.getOperationalLayers().remove(layer);
        }
    }
    private void getCurrentLocation() {
        new Thread(() -> {
            try {
                String url = "http://ip-api.com/json";
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();

                JSONObject json = new JSONObject(content.toString());
                double latitude = json.getDouble("lat");
                double longitude = json.getDouble("lon");

                Platform.runLater(() -> {
                    mapView.setViewpoint(new Viewpoint(latitude, longitude, 240000));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    






 
     /**
      * Stops and releases all resources used in application.
      */
     @Override
     public void stop() {
         if (mapView != null) {
             mapView.dispose();
         }
     }
 }
 