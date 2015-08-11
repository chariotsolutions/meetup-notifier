package com.chariotsolutions.meetupnotifier;


import java.util.List;

public interface CalendarEventDao {

  List<CalendarEvent> findEventsByMeetupId(String meetupId);

  void insertEvent(String googleId, String meetupId);
}
