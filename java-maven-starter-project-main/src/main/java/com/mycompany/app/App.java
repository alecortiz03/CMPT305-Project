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
 import com.esri.arcgisruntime.mapping.ArcGISMap;
 import com.esri.arcgisruntime.mapping.Viewpoint;
 import com.esri.arcgisruntime.mapping.view.MapView;
 import com.esri.arcgisruntime.portal.Portal;
 import com.esri.arcgisruntime.portal.PortalItem;
 
 import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



 
 public class App extends Application {
 
     private MapView mapView;
     private ArcGISMap map1;
     private ArcGISMap map2;
     private Label response;
     private boolean showingMap1 = true; // Flag to track the current map
 
     public static void main(String[] args) {
         Application.launch(args);
     }
 
     @Override
     public void start(Stage stage) {
 
         // Set the title and size of the stage and show it
         stage.setTitle("My Map App");
         stage.setWidth(800);
         stage.setHeight(700);
         stage.show();
 
         // Create a BorderPane as the root layout
         BorderPane borderPane = new BorderPane();
         Scene scene = new Scene(borderPane);
         stage.setScene(scene);
 
         // Note: it is not best practice to store API keys in source code.
         String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGmPNCNzdSFiVgLBvMIxAEco6KZVumKu7MdEw0hAqD_kE9dyGobK-enlgLuMR17SjMAJlvFEtVB_JAblbOQKOCv3sOPycS1WlG4_EuGd_cUI0X5mtknkjPxps_iv6rPBIJB74VWxNMjZt91JBLBR9DCB44ZAKIUI9gguPeEJC4Sfqs0BYXk9v9SST9JVjC9ZXbT7i6LgOz00qlaHrH13YfavAT1_MCnmWKIq";
         ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
 
         // Create the MapView to display the maps
         mapView = new MapView();
 
         // Create two maps with different basemaps or content
         Portal portal = new Portal("https://www.arcgis.com", false);
 
         String itemId1 = "4a120250e5b04daeb0a899762";
         PortalItem portalItem1 = new PortalItem(portal, itemId1);
         map1 = new ArcGISMap(portalItem1);
 
         String itemId2 = "f29c5e70b9d14febb35748dada45f312"; // Replace with your second map's itemId
         PortalItem portalItem2 = new PortalItem(portal, itemId2);
         map2 = new ArcGISMap(portalItem2);
 
         // Set the initial map to map1
         mapView.setMap(map1);
         mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));
 
         
         
 
         
 
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
        toggleButton.setOnAction(event -> toggleMap());

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
 
     // Toggle the map displayed in the MapView
     private void toggleMap() {
        if (showingMap1) {
            // Set map2 and adjust its viewpoint after it's loaded
            mapView.setMap(map2);
            map2.addDoneLoadingListener(() -> mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000)));
        } else {
            // Set map1 and adjust its viewpoint after it's loaded
            mapView.setMap(map1);
            map1.addDoneLoadingListener(() -> mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000)));
        }
        showingMap1 = !showingMap1; // Toggle the flag
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
 