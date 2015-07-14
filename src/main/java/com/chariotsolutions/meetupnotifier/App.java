package com.chariotsolutions.meetupnotifier;

import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQueryExecutor;
import com.chariotsolutions.meetupnotifier.meetup.api.GoogleConverter;
import com.google.api.services.calendar.model.Event;

import com.chariotsolutions.meetupnotifier.google.CalendarSample;
import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQuery;
import com.chariotsolutions.meetupnotifier.meetup.api.messages.Results;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  /**
   * Currently being used for testing purposes.
   * @param args Commandline arguments
   */
  public static void main(String[] args) throws IOException, GeneralSecurityException {
    Results resultsObject = new MeetupQueryExecutor("591a1341d1b5e50587c3b485afa34").executeQuery(new MeetupQuery("phillypug"));

    com.google.api.services.calendar.Calendar cal = CalendarSample.getCalendarService();
    GoogleConverter googleConverter = new GoogleConverter();

    List<Event> events =
        resultsObject.getResults().stream()
            .map(googleConverter::convertTo)
            .collect(Collectors.toList());
    for (Event event : events) {
      cal.events().insert("primary", event).execute();
    }
  }

}

