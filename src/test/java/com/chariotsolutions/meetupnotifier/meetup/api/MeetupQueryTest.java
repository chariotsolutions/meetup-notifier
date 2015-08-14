package com.chariotsolutions.meetupnotifier.meetup.api;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MeetupQueryTest {
  private MeetupQuery meetupQuery;
  @Before

  public void setup() {
    meetupQuery = new MeetupQuery("group-name");
  }
  @Test
  public void testBuildQueryUrl() {
    String queryUrl = meetupQuery.buildQueryUrl("api-key");
    Assert.assertThat(queryUrl, is("https://api.meetup.com/2/events?&group_urlname=group-name&time=,2w&fields=timezone,venue_visibility&page=20&sign=true&key=api-key"));
  }
}