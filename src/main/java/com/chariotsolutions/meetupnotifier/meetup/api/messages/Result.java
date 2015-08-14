package com.chariotsolutions.meetupnotifier.meetup.api.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
  private String name;
  private String description = "";
  @JsonProperty("event_url")
  private String eventUrl;
  private Venue venue = new Venue();
  @JsonProperty("venue_visibility")
  private String venueVisibility;
  @JsonProperty("how_to_find_us")
  private String howToFindUs;
  private long time;
  private String timezone;
  private long duration = 1000 * 60 * 60 * 3; // Three hours in millis. Default value
  private String id;
  private Group group;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEventUrl() {
    return eventUrl;
  }

  public void setEventUrl(String eventUrl) {
    this.eventUrl = eventUrl;
  }

  public Venue getVenue() {
    return venue;
  }

  public void setVenue(Venue venue) {
    this.venue = venue;
  }

  public String getVenueVisibility() {
    return venueVisibility;
  }

  public void setVenueVisibility(String venueVisibility) {
    this.venueVisibility = venueVisibility;
  }

  public String getHowToFindUs() {
    return howToFindUs;
  }

  public void setHowToFindUs(String howToFindUs) {
    this.howToFindUs = howToFindUs;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }
}
