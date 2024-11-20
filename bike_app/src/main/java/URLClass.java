

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class URLClass {
    private final String url;
    public URLClass(String urlLink) {
        this.url = urlLink;

    }

    public List<JSONObject> URLParse() {
        // Define the API endpoint
        int limit = 50000;
        int offset = 0;

        // Create the HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // List of JSONArrays to return
        List<JSONObject> allEntries = new ArrayList<>();

        try {
            int size = limit;
            while (size == limit) {
                offset += limit;
                String modifiedUrl = this.url + "?$limit=" + limit + "&$offset=" + offset;

                // Build the HTTP GET request
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(modifiedUrl))
                        .GET()
                        .build();


                // Send the request and get the response
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                // Check if the response was successful
                if (response.statusCode() == 200) {
                    // Parse the JSON response as an array
                    JSONArray jsonArray = new JSONArray(response.body());

                    // Iterate through the array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        allEntries.add(jsonArray.getJSONObject(i));
                    }
                    size = jsonArray.length();
                    System.out.println("Total Account Number: " + size);


                } else {
                    System.out.println("Error: Received status code " + response.statusCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allEntries;


    }
    }