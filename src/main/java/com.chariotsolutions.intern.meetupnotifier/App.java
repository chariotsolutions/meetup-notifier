package com.chariotsolutions.intern.meetupnotifier;


import com.chariotsolutions.intern.meetupnotifier.response.Results;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet("https://api.meetup.com/2/events?offset=0&format=json&limited_events=False&group_urlname=phillypug&photo-host=public&page=20&fields=timezone&order=time&desc=false&status=upcoming&sig_id=188504148&sig=3e8db7cb314f7e2b25f0cc7054c5dea549483eb2");
        getRequest.addHeader("accept", "application/json");
        HttpResponse response = client.execute(getRequest);

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(response.getEntity().getContent());

        Results ResultsObject = mapper.readValue(jsonParser, Results.class);

        String out = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ResultsObject);
        System.out.println(out);
    }
}

