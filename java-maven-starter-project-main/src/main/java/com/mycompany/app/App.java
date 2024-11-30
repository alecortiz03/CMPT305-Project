package com.mycompany.app;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.Viewpoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class App extends Application {
    private Label response;
    private MapView mapView;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

            // Set the API key for ArcGIS Runtime
            String yourApiKey = "AAPTxy8BH1VEsoebNVZXo8HurKi7aeFYVUcn_aZ7X7LtCGlnDZ4ufU_qKFPagMXaJlIei9YH6M3I2VRE86ICIpw-zAOFnJAzYLHH0Mk85D0ldnDqT4y2CeD_oLna8Bgzkoo4zh3hFDmAC0GXvr1bLSCr6GVwBmXTFJF8j9FraOa3XuL_4W7u-t4KYMmowTY0dSKLy-kCdGK3yZapK_ziJhBFZMSrTeZIJVwh12vaaBu2gBo.AT1_j7NkVs9t"; // Make sure to replace this with your actual API key
            ArcGISRuntimeEnvironment.setApiKey(yourApiKey);


                //create the vBox
                VBox vBox = new VBox();
                vBox.setPadding(new Insets(10, 10, 10, 10));
                vBox.setAlignment(Pos.TOP_CENTER);

                // City name label at the top
                Label cityName = new Label("Map of the City of Edmonton");
                cityName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

                // Create the address bar (TextField)
                TextField addressField = new TextField();
                addressField.setPromptText("Enter address or search term");
                addressField.setPrefWidth(400);

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

                //This will be replaced when the map is implemented
                // Create the Map Area
                Label cityMap = new Label("City");
                cityMap.setStyle("-fx-font-size: 18px;");

                //This will be replaced when the statistics column is implemented
                // Create the statistics area
//        Label statistics = new Label("Statistics");
//        statistics.setStyle("-fx-font-size: 18px;");


                // Create an HBox for the city name, address bar, search button, and toggle button
                HBox topBar = new HBox(30);  // 10px spacing between elements in the HBox
                topBar.setAlignment(Pos.CENTER);  // Align elements in the center
                topBar.getChildren().addAll(cityName, toggleButton, addressField, searchButton);

                // Create the response label (to display search or toggle messages)
                response = new Label("Push a button");
                response.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");


                Label housePrices = new Label("House Prices");
                housePrices.setStyle("-fx-font-size: 14px;");
                housePrices.setAlignment(Pos.CENTER);

                Label mean = new Label("Mean: ");
                mean.setStyle("-fx-font-size: 14px;");

                Label median = new Label("Median: ");
                median.setStyle("-fx-font-size: 14px;");

                Label max = new Label("Max: ");
                max.setStyle("-fx-font-size: 14px;");

                Label min = new Label("Min: ");
                min.setStyle("-fx-font-size: 14px;");

                Label range = new Label("Range: ");
                range.setStyle("-fx-font-size: 14px;");

                Label bikePaths = new Label("Number of Bike Paths: ");
                bikePaths.setStyle("-fx-font-size: 14px;");

                VBox statistics = new VBox(10);
                statistics.setAlignment(Pos.CENTER);
                statistics.getChildren().addAll(housePrices, mean, median, max, min, range);


                // Create an HBox for the Map and Statistics columns
                HBox centreBar = new HBox(10);
                centreBar.setAlignment(Pos.CENTER);
                centreBar.getChildren().addAll(cityMap, statistics);



                // Add the anchorPane to the vBox
                AnchorPane anchorPane = new AnchorPane();

                anchorPane.setBottomAnchor(response, 10.0);
                anchorPane.setRightAnchor(response, 10.0);

                anchorPane.getChildren().add(response);

                // Add the topBar to the VBox
                vBox.getChildren().addAll(topBar, centreBar, anchorPane);

                // Create the Scene and set it on the Stage
                Scene scene = new Scene(vBox, 1024, 720);
                stage.setScene(scene);

                // Show the stage
                stage.show();









                // Create the VBox layout to stack elements vertically
                //VBox rootNode = new VBox(10); // 10px spacing between elements
                //rootNode.setAlignment(Pos.TOP_CENTER); // Center-align the content

                //AnchorPane rootNode = new AnchorPane();

                //Scene scene = new Scene(rootNode, 1024,720); //This is the aspect ratio in pixels


                //primaryStage.setScene(scene);


                //try to find a geoJSON dataset which you pretty much have to upload it to the map, then you get all the
                //boundaries of the neighbourhood
                //


                //Creating a label

                //Label label = new Label("City of Edmonton Map Goes Here");
                //label.setLayoutX(200);
                //label.setLayoutY(200);


                //adding a label to the layout
//        response = new Label("push me");
//        response.setLayoutX(800);
//        response.setLayoutY(700);


//        rootNode.getChildren().add(label);
//        rootNode.getChildren().add(response);

                //FlowPane rootNode = new FlowPane(10,10); //Horizontal and vertical spacing of elements just set to 10


//        TextField addressField = new TextField();
//        addressField.setPromptText("Enter address or search term"); // Placeholder text
//        addressField.setLayoutX(660); // Position the text field
//        addressField.setLayoutY(0); // Position the text field
//        addressField.setPrefWidth(300); // Set preferred width

                // Add the address field to the layout
                //rootNode.getChildren().add(addressField);


//        //Create the toggle button
//        Button toggleButton = new Button("Toggle");
//
//        toggleButton.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {
//                response.setText("Toggle button was pressed");
//            }
//        });
//        AnchorPane.setTopAnchor(toggleButton, 0.0);
//        AnchorPane.setLeftAnchor(toggleButton, 100.0);
//
//        rootNode.getChildren().add(toggleButton);
//
//
//        //Create the search button
//        Button searchButton = new Button("Search");
//
//        searchButton.setOnAction(new EventHandler<ActionEvent>(){
//            @Override
//            public void handle(ActionEvent event) {
//                String enteredText = addressField.getText();
//                response.setText("You searched for: " + enteredText);
//            }
//        });
//        AnchorPane.setTopAnchor(searchButton, 0.0);
//        AnchorPane.setRightAnchor(searchButton, 10.0);
//
//
//        //adds the button to the layout
//        rootNode.getChildren().add(searchButton);







                //shows the screen
                //primaryStage.show();



                //
                //       searchButton.setOnAction(actionEvent -> response.setText("search was pressed"));
//        rootNode.getChildren().addAll(searchButton,response);




            }

        }



        /*
        // Set the title for the window
            stage.setTitle("Bike Routes in Edmonton");

            // Create the VBox layout
            VBox vBox = new VBox();
            vBox.setPadding(new Insets(10, 10, 10, 10));
            vBox.setAlignment(Pos.TOP_CENTER);

            // City name label at the top
            Label cityName = new Label("Map of the City of Edmonton");
            cityName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            // Create the address bar (TextField)
            TextField addressField = new TextField();
            addressField.setPromptText("Enter address or search term");
            addressField.setPrefWidth(400);

            // Create the Toggle button
            Button toggleButton = new Button("Toggle");
            toggleButton.setOnAction(event -> response.setText("Toggle button was pressed"));

            // Create the Search button
            Button searchButton = new Button("Search");
            searchButton.setOnAction(event -> {
                String enteredText = addressField.getText();
                response.setText("You searched for: " + enteredText);
            });

            // Create the response label
            Label response = new Label("Push a button");
            response.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");

            // Create the HBox for the top bar
            HBox topBar = new HBox(30);
            topBar.setAlignment(Pos.CENTER);
            topBar.getChildren().addAll(cityName, toggleButton, addressField, searchButton);


            /*
            // Create the MapView and set the map
            MapView mapView = new MapView();
            mapView.setPrefWidth(800);
            mapView.setPrefHeight(600);

            // Create an ArcGISMap with an imagery basema
            ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_IMAGERY);
            mapView.setMap(map);
            mapView.setViewpoint(new Viewpoint(53.5381, -113.4937, 240000));



            Label mapView = new Label("Map");

            // Create an HBox for the Map and Statistics columns
            VBox statistics = new VBox(10);
            statistics.setAlignment(Pos.CENTER);
            Label housePrices = new Label("House Prices");
            housePrices.setStyle("-fx-font-size: 14px;");
            statistics.getChildren().addAll(housePrices);

            // Create the main center layout and add the map and statistics
            HBox centreBar = new HBox(10);
            centreBar.setAlignment(Pos.CENTER);
            centreBar.getChildren().addAll(mapView, statistics);

            // Add the top bar and center bar to the VBox
            vBox.getChildren().addAll(topBar, centreBar);

            // Create the Scene and set it on the Stage
            Scene scene = new Scene(vBox, 1024, 720);
            stage.setScene(scene);

            // Show the stage
            stage.show();
        }
    }
*/