package com.mycompany.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BikeURLClass {
    String url;



    private List<String> urlInformation;

    public BikeURLClass(String url) {
        this.url = url;
        urlInformation = URLParse();
    }

    public List<String> getUrlInformation() {
        return urlInformation;
    }


    public List<String> URLParse() {
        List<String> allEntries = new ArrayList<>();
        int offset = 1;
        int limit = 10000; // The maximum is 50000
        int entriesCount = 100000;
        try {
            while (entriesCount < (limit)) {
                // Create URL with pagination parameters
                String paginatedUrl = this.url + "?$limit=" + limit + "&$offset=" + offset;
                URL url = new URL(paginatedUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;



                while ((line = reader.readLine()) != null) {
                    allEntries.add(line);
                }

                reader.close();
                connection.disconnect();
                entriesCount = allEntries.size();


                offset += limit;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEntries;
    }



    @Override
    public String toString() {
        return urlInformation.toString();
    }

}



//    public void splitLines(List<String> rackURL) {
//        List<String> allRacksUnsplit = URLParse();
//        List<BikeRack> allRacks = new ArrayList<>();
//        for (String s : allRacksUnsplit) {
//            this.racks.add(new BikeRack(s));
//        }
//
//    }

//public class URLClass {
//    private final String url;
//    public URLClass(String urlLink) {
//        this.url = urlLink;
//    }
//
//    public String URLParse() {
//        try {
//            // URL for the API endpoint
//            URL rackURL = new URI(this.url).toURL();
//            HttpURLConnection connection = (HttpURLConnection) rackURL.openConnection();
//
//            // Set up the request
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//
//            // Check the response code
//            int responseCode = connection.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                // Read the response
//                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String inputLine;
//                StringBuilder content = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    content.append(inputLine);
//                }
//
//                // Close the connections
//                in.close();
//                connection.disconnect();
//
//                ObjectMapper mapper = new ObjectMapper();
//                // Print the response
//                return content.toString();
//            } else {
//                System.out.println("GET request failed. Response Code: " + responseCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}
