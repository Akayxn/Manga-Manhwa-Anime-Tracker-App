package org.mangatracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String password;
    // this api has all the dummy accounts for us to use
    final static private String API_URL = "https://dummyjson.com/users";

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    private static HttpResponse<String> getResponse() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header("accept", "application/json")
                    .uri(URI.create(API_URL))
                    .build(); // basically saying get request in json format from this url+pokemon_name of choice then finally building it

            return client.send(request, HttpResponse.BodyHandlers.ofString()); // sends request and gets the raw json into String.

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static  Map<String,String> fetchUsers() throws JsonProcessingException {
        HttpResponse<String> response = getResponse();

        if(response==null || response.body()==null){
            throw new RuntimeException("Failed to fetch User Data.");
        }

        Map<String,String> userMap= new HashMap<>(); // map to store all the username & password from the dummyapi


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(response.body());

        JsonNode users = root.get("users");

        for(JsonNode user : users){
            String username = user.path("username").asText();
            String password = user.path("password").asText();
            userMap.put(username,password);
        }

    return userMap;

    }







    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
















