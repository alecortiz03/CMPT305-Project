public class Main {
    public static void main(String[] args) {
        String propertiesAPI = "https://data.edmonton.ca/resource/q7d6-ambg.json";
        URLClass allProperties = new URLClass(propertiesAPI);
        System.out.println(allProperties.URLParse());
        // See End of API Call
        System.out.println("End");
    }
}

