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
 import javafx.scene.Scene;
 import javafx.scene.control.Button;
 import javafx.scene.layout.VBox;
 import javafx.stage.Stage;
 
 public class App extends Application {
 
     private MapView mapView;
     private ArcGISMap map1;
     private ArcGISMap map2;
     private boolean showingMap1 = true; // Flag to track the current map
 
     public static void main(String[] args) {
         Application.launch(args);
     }
 
     @Override
     public void start(Stage stage) {
 
         // set the title and size of the stage and show it
         stage.setTitle("My Map App");
         stage.setWidth(800);
         stage.setHeight(700);
         stage.show();
 
         // create a JavaFX scene with a stack pane as the root node and add it to the scene
         VBox vbox = new VBox();
         Scene scene = new Scene(vbox);
         stage.setScene(scene);
 
         // Note: it is not best practice to store API keys in source code.
         String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGmPNCNzdSFiVgLBvMIxAEco6KZVumKu7MdEw0hAqD_kE9dyGobK-enlgLuMR17SjMAJlvFEtVB_JAblbOQKOCv3sOPycS1WlG4_EuGd_cUI0X5mtknkjPxps_iv6rPBIJB74VWxNMjZt91JBLBR9DCB44ZAKIUI9gguPeEJC4Sfqs0BYXk9v9SST9JVjC9ZXbT7i6LgOz00qlaHrH13YfavAT1_MCnmWKIq";
         ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
 
         // Create the MapView to display the maps
         mapView = new MapView();
 
         // Create two maps with different basemaps or content
         Portal portal = new Portal("https://www.arcgis.com", false);
 
         String itemId1 = "f29c5e70b9d14febb35748dada45f312";
         PortalItem portalItem1 = new PortalItem(portal, itemId1);
         map1 = new ArcGISMap(portalItem1);
 
         String itemId2 = "4a120250e5b04daeb0a899762"; // Replace with your second map's itemId
         PortalItem portalItem2 = new PortalItem(portal, itemId2);
         map2 = new ArcGISMap(portalItem2);
 
         // Set the initial map to map1
         mapView.setMap(map1);
         mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));
 
         // Add a toggle button to switch between the maps
         Button toggleButton = new Button("Switch Map");
         toggleButton.setOnAction(event -> toggleMap());
 
         // Add the mapView and the toggle button to the layout
         vbox.getChildren().addAll(toggleButton, mapView);
     }
 
     // Toggle the map displayed in the MapView
     private void toggleMap() {
         if (showingMap1) {
             mapView.setMap(map2); // Switch to the second map
         } else {
             mapView.setMap(map1); // Switch back to the first map
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
 