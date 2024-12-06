package com.mycompany.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.geometry.*;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.*;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.geometry.SpatialReferences;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.util.*;

import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import com.esri.arcgisruntime.geometry.GeometryEngine;


public class AppController{
    @FXML
    private HBox mainHBox;
    @FXML
    private StackPane mapBox;
    @FXML
    private Text text;
    @FXML
    private Button recenterButton;
    @FXML
    private TableView<Neighbourhood> neighbourhoodTable;
    @FXML
    private TableColumn<Neighbourhood, String> neighbourhoodNameColumn;
    @FXML
    private TableColumn<Neighbourhood, String> averageAssessmentColumn;
    @FXML
    private TableColumn<Neighbourhood, String> totalPropertiesColumn;
    @FXML
    private TableColumn<Neighbourhood, String> permits2024Column;

    static Portal portal = new Portal("https://braydenvt.maps.arcgis.com");
    private MapView mapView;
    private final ApiNeighbourhoodPolygonsDAO apiNP = new ApiNeighbourhoodPolygonsDAO();
    private final ApiPropertyAssessmentDAO apiPA = new ApiPropertyAssessmentDAO();
    private final ApiBuildingPermitsDAO apiBuilding = new ApiBuildingPermitsDAO();

    private Viewpoint  initialViewpoint;
    private boolean initialized = false;

    private void setMap() {
        // Set arcGIS apikey and license to use map view
        String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurPyb6EJwgq6JHwm9iBqtd-uqy1QfEqk2h1UVzjdqCp7ddHQ0SSO08Zj-NH-9J_Mmq98rJTfcNzbTXBPKGKYpFxRfDydGymraPTgY2Y0d0H51f7g5CMeudVleI7R8YuGP7Ayqgr8Hs_cMySYco0rEzmaUENbq0yRBUot2IWGMzytBWLU9xScmmbs5HOST60tCzXV4hyZg1xb4nyHtm0CLx_OcSbqKAnwXPY8-fkNEOT1lAT1_X7nimqZC";
        ArcGISRuntimeEnvironment.setApiKey(yourApiKey);
        // Set License
        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud7522764545,none,B5H93PJPXLK4LMZ59190");

        PortalItem edmontonWeb = new PortalItem(portal, "12e15ad9b88d400e8901ad3595505dd9");
        ArcGISMap edmontonMap = new ArcGISMap(edmontonWeb);

        // create a MapView
        mapView = new MapView();
        mapView.setMaxHeight(480);
        mapView.setMinWidth(300);

        // display the map by setting the map on the map view
        mapView.setMap(edmontonMap);

        // define and set the maps initial viewpoint
        Point initialLocation = new Point(-113.4938, 53.5261, SpatialReferences.getWgs84());
        initialViewpoint = new Viewpoint(initialLocation,500000);
        mapView.setViewpoint(initialViewpoint);

        // create a button to recenter
        Image recenterButtonImage = new Image("file:src/main/resources/images/target.png");
        javafx.scene.image.ImageView recenterButtonImageView = new ImageView(recenterButtonImage);
        recenterButtonImageView.setFitWidth(20); recenterButtonImageView.setFitHeight(20);
        recenterButton = new Button();
        recenterButton.setGraphic(recenterButtonImageView);

        // recenter map on button press
        recenterButton.setVisible(false);
        recenterButton.setOnAction( e -> recenterMap());

        // add button to pane
        mapBox.getChildren().addAll(mapView,recenterButton);
        StackPane.setAlignment(recenterButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(recenterButton, new Insets(0,0,20,4));
        recenterButton.setStyle("-fx-background-color: white; -fx-background-radius: 5em");

        mapView.addViewpointChangedListener(event -> {
            // Stops button from appearing on map initialization or reset
            if (!initialized) {
                initialized = true;
                return;
            }
            // If map has changed not because of a rest, show button
            recenterButton.setVisible(true);
        });

    }

    private void recenterMap() {
        // hide button
        recenterButton.setVisible(false);
        // reset map
        mapView.setViewpoint(initialViewpoint);
        // ensures button stays hidden on map reset
        initialized = false;
    }


    private Tooltip tooltip = new Tooltip();

    private void plotPolygons(List<Neighbourhood> neighbourhoods) {
        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        mapView.getGraphicsOverlays().add(graphicsOverlay);

        // break if no neighbourhoods are present
        if (neighbourhoods.isEmpty()) {
            return;
        }

        // get neighbourhood colours
        Map<String, Color> colours = getNeighbourhoodColours(neighbourhoods);

        // loop through each neighbourhood
        neighbourhoods.forEach((neighbourhood) -> {
            // fixes database issues with "horse hill neighbourhood 1A"
            if (neighbourhood.getNeighbourhoodPolygon() != null) {
                // create outline and fill colour
                SimpleLineSymbol blueOutlineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.DODGERBLUE , 1);
                //SimpleFillSymbol polygonFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, colours.get(neighbourhood.getNeighbourhoodName()), blueOutlineSymbol);
                SimpleFillSymbol polygonFillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.web(Color.RED.toString(),0.3),blueOutlineSymbol);

                // Create statistics overlay
                Graphic polygonGraphic = new Graphic(neighbourhood.getNeighbourhoodPolygon(), polygonFillSymbol);
                polygonGraphic.getAttributes().put("statistics", neighbourhood.getStatistics(2024));

                graphicsOverlay.getGraphics().add(polygonGraphic);
            }
        });
        //call hover tool**
        setupHoverTooltip(graphicsOverlay);
    }




    private void setupHoverTooltip(GraphicsOverlay graphicsOverlay) {
        mapView.setOnMouseMoved(event -> {
            Point screenPoint = mapView.screenToLocation(new javafx.geometry.Point2D(event.getX(), event.getY()));
            boolean found = false;

            for (Graphic graphic : graphicsOverlay.getGraphics()) {
                if (graphic.getGeometry() == null || graphic.getGeometry().getSpatialReference() == null) {
                    continue;
                }

                Point mapPoint = (Point) GeometryEngine.project(screenPoint, graphic.getGeometry().getSpatialReference());

                if (GeometryEngine.contains(graphic.getGeometry(), mapPoint)) {
                    String statistics = (String) graphic.getAttributes().get("statistics");
                    showTooltip(event, statistics);
                    found = true;
                    break;
                }
            }

            if (!found) hideTooltip();
        });
    }

    private void showTooltip(MouseEvent event, String text) {
        tooltip.setText(text);
        tooltip.show(mapView, event.getScreenX() + 10, event.getScreenY() + 10);
    }

    private void hideTooltip() {
        tooltip.hide();
    }





    @FXML
    private void initialize() {
        setMap();
        List<Neighbourhood> neighbourhoods = apiPA.buildNeighbourhoods();
        plotPolygons(neighbourhoods);
        apiPA.addHistoricalData(neighbourhoods);
        apiBuilding.updateAllNeighbourhoodPermits(neighbourhoods);
    }
}

