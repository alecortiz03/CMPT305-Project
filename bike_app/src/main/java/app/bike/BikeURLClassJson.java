package app.bike;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class BikeURLClassJson {
    private final String url;
    public BikeURLClassJson(String urlLink) {
        this.url = urlLink;
    }

    public String URLParse() {
        try {
            // URL for the API endpoint
            URL rackURL = new URI(this.url).toURL();
            HttpURLConnection connection = (HttpURLConnection) rackURL.openConnection();

            // Set up the request
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close the connections
                in.close();
                connection.disconnect();

                ObjectMapper mapper = new ObjectMapper();
                // Print the response
                return content.toString();
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
