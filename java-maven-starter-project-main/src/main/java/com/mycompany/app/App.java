/**
 * Copyright 2019 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private MapView mapView;

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
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);

        // Note: it is not best practice to store API keys in source code.
        // An API key is required to enable access to services, web maps, and web scenes hosted in ArcGIS Online.
        // If you haven't already, go to your developer dashboard to get your API key.
        // Please refer to https://developers.arcgis.com/java/get-started/ for more information
        String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGmI5rfxHaaL21jdPPXYpp9huX2_m2G4-XJYuUh1M54mnYNwiLHC0BMYosLxdaQpXPpo3_zr-XEV2ZhUBGyR3SLTbpUKRV4Xr_hE2MQ1RlY8eArscCn0SFWrG8kKPAestlAjUkUxYpfe5cTLZ9IHTrlOiAQyfUS0Gkopiv1V_R-YZ6TT7qVerIEbbRin1LBS_06j9qtuFb37OR-_gjn0AV_tAT1_MCnmWKIq";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);

        // create a MapView to display the map and add it to the stack pane
        mapView = new MapView();
        stackPane.getChildren().add(mapView);

        // create an ArcGISMap with an imagery basemap
        Portal portal = new Portal("https://www.arcgis.com", false);

        String itemId = "1ae528f03a75414fa574287f52e306c0";
        PortalItem portalItem = new PortalItem(portal, itemId);

        ArcGISMap map = new ArcGISMap(portalItem);

        // display the map by setting the map on the map view
        mapView.setMap(map);
        mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));






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
