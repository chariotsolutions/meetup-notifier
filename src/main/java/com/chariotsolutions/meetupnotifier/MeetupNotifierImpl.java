package com.chariotsolutions.meetupnotifier;


import com.google.api.services.calendar.model.Event;

import com.chariotsolutions.meetupnotifier.meetup.api.GoogleConverter;
import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQuery;
import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQueryExecutor;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class MeetupNotifierImpl {

  private static final Logger logger = LoggerFactory.getLogger(MeetupNotifierImpl.class);

  private String[] groups;

  public void queryAndNotify() throws IOException {
    for (String meetupGroupName : groups) {
      Results results = new MeetupQueryExecutor().executeQuery(new MeetupQuery(meetupGroupName));

      List<Event> events = results.getResults().stream()
          .map(new GoogleConverter()::convertTo)
          .collect(Collectors.toList());

      for (Event googleEvent : events) {
        App.cal.events().insert("primary", googleEvent).execute();
        logger.debug("An event was added to the calendar successfully");
      }
    }
  }

  @Value("${meetup.groups}")
  public void setGroups(String[] groups) {
    this.groups = groups;
  }
}
