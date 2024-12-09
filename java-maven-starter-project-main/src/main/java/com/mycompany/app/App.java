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
 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
 import javafx.geometry.Point2D;
 import javafx.geometry.Pos;
 import javafx.scene.Scene;
 import javafx.scene.control.*;
 import javafx.scene.control.cell.PropertyValueFactory;
 import javafx.scene.input.MouseEvent;
 import javafx.scene.layout.*;
 import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 import java.text.NumberFormat;
 import java.util.List;

import org.json.JSONObject;


public class App extends Application {
 
    private MapView mapView;
    private ArcGISMap map1;
    private ArcGISMap map2;
    private Label response;
    private ToggleButton toggleButton;
    private ToggleButton toggleButton2;
    private double latitude;
    private double longitude;
    private TableView<TableValues> table; //have a TableView object containing a class think of this as a row in the table
    private ObservableList<TableValues> values;
 
     public static void main(String[] args) {
         Application.launch(args);
     }
 
     @Override
     public void start(Stage stage) {
        
        
         // Set the title and size of the stage and show it
         stage.setTitle("City of Edmonton Bike Routes");
         stage.setWidth(1280);
         stage.setHeight(800);
         stage.show();

         //create the table
         createTable();


         // Create a BorderPane as the root layout
         BorderPane borderPane = new BorderPane();
         Scene scene = new Scene(borderPane);
         scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

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
         //mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));
         getCurrentLocation();
         mapView.setPrefWidth(700);

         
         
 
         
 
         borderPane.setLeft(mapView);






        // ------------------- City Name Label -------------------
        Label cityName = new Label("Map of the City of Edmonton");
        cityName.getStyleClass().add("custom-city-name-label");        
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
                String enteredText = addressField.getText().toLowerCase();  // Get the search term from the TextField
                response.setText("You searched for: " + enteredText);  // Display search text in response label

                // Create a BikeDAO instance
                BikeDAO bikeDAO = new BikeDAO();

                // Use BikeDAO to get data for the entered neighbourhood
                TableValues tableValues = bikeDAO.getTableValues(enteredText);

                // Add the table values to the ObservableList (which populates the TableView)
                //values.clear();  // Clear the existing rows
                values.add(tableValues);  // Add new data based on the search

            }
        });

        // ------------------- Right Panel -------------------
        VBox rightPanel = new VBox(10);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setAlignment(Pos.TOP_LEFT);

        rightPanel.setVgrow(table, Priority.ALWAYS);

        rightPanel.getChildren().addAll(table);



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
        //getCurrentLocation();        
     }


    private void createTable() {
        table = new TableView<>();
        values = FXCollections.observableArrayList();
        table.setItems(values);

        // Create the Neighborhood column first
        TableColumn<TableValues, String> neighborhoodCol = new TableColumn<>("Neighborhood");
        neighborhoodCol.setCellValueFactory(new PropertyValueFactory<>("neighborhood"));
        neighborhoodCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        table.getColumns().add(neighborhoodCol);

        TableColumn<TableValues, String> bikeCountCol = new TableColumn<>("Bike Paths");
        bikeCountCol.setCellValueFactory(new PropertyValueFactory<>("bikePaths"));
        bikeCountCol.prefWidthProperty().bind(table.widthProperty().multiply(0.175));
        table.getColumns().add(bikeCountCol);

        TableColumn<TableValues, String> propertyCountCol = new TableColumn<>("Property Num");
        propertyCountCol.setCellValueFactory(new PropertyValueFactory<>("propertyCount"));
        propertyCountCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        table.getColumns().add(propertyCountCol);

        TableColumn<TableValues, Integer> meanCol = new TableColumn<>("Mean");
        meanCol.setCellValueFactory(new PropertyValueFactory<>("meanValue"));
        meanCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        table.getColumns().add(meanCol);

        TableColumn<TableValues, Integer> medianCol = new TableColumn<>("Median");
        medianCol.setCellValueFactory(new PropertyValueFactory<>("medianValue"));
        medianCol.prefWidthProperty().bind(table.widthProperty().multiply(0.13));
        table.getColumns().add(medianCol);

        TableColumn<TableValues, Integer> minCol = new TableColumn<>("Min");
        minCol.setCellValueFactory(new PropertyValueFactory<>("minValue"));
        minCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        table.getColumns().add(minCol);

        TableColumn<TableValues, Integer> maxCol = new TableColumn<>("Max");
        maxCol.setCellValueFactory(new PropertyValueFactory<>("maxValue"));
        maxCol.setCellFactory(tc -> new TableCell<>() {


            @Override
            protected void updateItem(Integer value, boolean empty) {
                super.updateItem(value, empty);
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
                currencyFormat.setMaximumFractionDigits(0);
                setText(empty ? "" : currencyFormat.format(value));
            }

        });
        maxCol.prefWidthProperty().bind(table.widthProperty().subtract(
                neighborhoodCol.widthProperty().add(bikeCountCol.widthProperty())
                        .add(propertyCountCol.widthProperty()).add(meanCol.widthProperty())
                        .add(medianCol.widthProperty()).add(minCol.widthProperty())
        ));

        table.getColumns().add(maxCol);

        //maxCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));


        //table.getColumns().add(maxCol);
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
                latitude = json.getDouble("lat");
                longitude = json.getDouble("lon");

                Platform.runLater(() -> {
                    mapView.setViewpoint(new Viewpoint(latitude, longitude, 50000));
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
 
