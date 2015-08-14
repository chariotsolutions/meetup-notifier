package com.chariotsolutions.meetupnotifier.meetup.api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;

import java.util.Date;

public class GoogleConverter implements ResultConverter<Event> {

  private static final String MEMBERS_ONLY = "Location has been marked private, view the group on meetup.com for details.";
  private static final String NOT_SET = "The location for this meetup has not yet been set. View the group on meetup.com for updates.";

  @Override
  public Event convertTo(Result sourceResult) throws InvalidResultException {

    validate(sourceResult);

    Event evt = new Event()
        .setSummary(sourceResult.getName())
        .setDescription(sourceResult.getDescription());
    setTimes(evt, sourceResult);
    setLocation(evt, sourceResult);
    return evt;
  }

  private void setLocation(Event evt, Result result) {
    String location = result.getVenue().toString();
    if (location.equals("")) {
      if (result.getVenueVisibility().equals("members")) {
        location = MEMBERS_ONLY;
      } else {
        location = NOT_SET;
      }
    }

    evt.setLocation(location);
  }

  private void setTimes(Event evt, Result result) {
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

  private void validate(Result result) throws InvalidResultException {
    if (result.getVenueVisibility() == null) {
      throw new InvalidResultException("Venue visibility can not be null");
    }

    if (result.getDuration() < 0) {
      throw new InvalidResultException("A negative duration is invalid.");
    }

    if (result.getName() == null) {
      throw new InvalidResultException("Event name can not be null");
    }
//
//    if (new Date(result.getTime()).before(new Date())) {
//      throw new InvalidResultException("This event starts in the past");
//    }
  }
}
