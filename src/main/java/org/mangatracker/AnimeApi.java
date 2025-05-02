package org.mangatracker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class AnimeApi {
    private static final String API_URL = "https://api.jikan.moe/v4/random/anime";

    private static String httpConnection(){
        try{
            HttpClient client = HttpClient.newHttpClient(); //creating an instance of the httpclient

            HttpRequest request = HttpRequest.newBuilder()
                    .GET() //is a get request
                    .header("accept","application/json") // saying that the client accepts json requests
                    .uri(new URI(API_URL)) // specifying on which url to send to
                    .build(); // finally making it ready to use

            //gets the response in the form of String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static ArrayList<String> getRandomAnime(){
        String getAPIResponse = httpConnection();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root;

        try{
            root = objectMapper.readTree(getAPIResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String imageLink = root.path("data").path("images").path("jpg").path("image_url").asText();
        String titleDefault = root.path("data").path("title").asText();
        String titleInEnglish = root.path("data").path("title_english").asText();

        ArrayList<String> animeInfo = new ArrayList<>();
        animeInfo.add(imageLink);
        animeInfo.add(titleDefault);
        animeInfo.add(titleInEnglish);

        return animeInfo;

    }

}
