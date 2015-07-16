package com.chariotsolutions.meetupnotifier.meetup.api;

public class MeetupQuery {

  private static final String queryString =
      "https://api.meetup.com/2/events?&group_urlname=%1$2s&time=,2w&fields=timezone,venue_visibility&page=20&sign=true&key=%2$2s";
  private String groupName;

  public MeetupQuery(String groupName) {
    this.groupName = groupName;
  }

  public String buildQueryUrl(String apiKey)  {
    return String.format(queryString, groupName, apiKey);
  }
}
