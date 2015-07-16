package com.chariotsolutions.meetupnotifier.meetup.api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;

import java.util.Date;

public class GoogleConverter implements ResultConverter<Event> {

  @Override
  public Event convertTo(Result sourceResult) {
    Event evt = new Event()
        .setSummary(sourceResult.getName())
        .setDescription(sourceResult.getDescription());
    setTimes(evt, sourceResult);
    setLocation(evt, sourceResult);
    return evt;
  }

  private static void setLocation(Event evt, Result result) {
    String location = result.getVenue().toString();
    if (location.equals("")) {
      if (result.getVenueVisibility().equals("members")) {
        location = "Location has been marked private, view the group on meetup.com for details.";
      } else {
        location = "The location for this meetup has not yet been set."
                 + "View the group on meetup.com for updates.";
      }
    }

    evt.setLocation(location);
  }

  private static void setTimes(Event evt, Result result) {
    long startTime = result.getTime();
    long endTime = startTime + result.getDuration();
    String timezone = result.getTimezone();

    DateTime startDateTime = new DateTime(new Date(startTime));
    DateTime endDateTime = new DateTime(new Date(endTime));

    EventDateTime start = new EventDateTime()
        .setDateTime(startDateTime)
        .setTimeZone(timezone);

    EventDateTime end = new EventDateTime()
        .setDateTime(endDateTime)
        .setTimeZone(timezone);

    evt.setStart(start);
    evt.setEnd(end);
  }
}
