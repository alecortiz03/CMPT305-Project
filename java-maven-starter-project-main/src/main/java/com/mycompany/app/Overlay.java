package com.mycompany.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.data.FeatureCollection;
import com.esri.arcgisruntime.layers.FeatureCollectionLayer;


public class Overlay {
    public static FeatureCollection data = new FeatureCollection();

    public static void getData() {
        String endpoint = "https://data.edmonton.ca/resource/65fr-66s6.geojson";
        String url = endpoint;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            FeatureCollection featureCollection = FeatureCollection.fromJson(client.send(request, HttpResponse.BodyHandlers.ofString()).body());
            data = featureCollection;
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }


    }
    public static void addOverlay(ArcGISMap map) {
        FeatureCollectionLayer layer = new FeatureCollectionLayer(data);
        map.getOperationalLayers().add(layer);
        System.out.println(map.toString());


    }

}

