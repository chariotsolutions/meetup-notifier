package com.chariotsolutions.meetupnotifier.meetup.api;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Results;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.validator.ValidateWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MeetupQueryExecutor {

  private static String apiKey;

  @Value("${meetup.key}")
  public void setApiKey(String apiKey) {
    MeetupQueryExecutor.apiKey = apiKey;
  }

  public Results executeQuery(MeetupQuery meetupQuery) throws IOException {
    String queryString = meetupQuery.buildQueryUrl(apiKey);

    CloseableHttpClient client = HttpClientBuilder.create().build();
    HttpGet getRequest = new HttpGet(queryString);
    getRequest.addHeader("accept", "application/json");
    HttpResponse response =  client.execute(getRequest);

    ObjectMapper mapper = new ObjectMapper();
    JsonFactory jsonFactory = new JsonFactory();
    JsonParser jsonParser = jsonFactory.createParser(response.getEntity().getContent());

    return mapper.readValue(jsonParser, Results.class);
  }
}
