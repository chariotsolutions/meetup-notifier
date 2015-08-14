package com.chariotsolutions.meetupnotifier;


import com.chariotsolutions.meetupnotifier.meetup.api.InvalidResultException;
import com.google.api.services.calendar.model.Event;

import com.chariotsolutions.meetupnotifier.meetup.api.GoogleConverter;
import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQuery;
import com.chariotsolutions.meetupnotifier.meetup.api.MeetupQueryExecutor;
import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@EnableScheduling
public class MeetupNotifierImpl {

  @Autowired
  CalendarEventDao dao;

  private String[] groups;

  public void queryAndNotify() throws IOException, InvalidResultException {
    for (String meetupGroupName : groups) {
      List<Result> results = new MeetupQueryExecutor()
          .executeQuery(new MeetupQuery(meetupGroupName)).getResults();

      for (Result result : results) {
        Event gev = new GoogleConverter().convertTo(result);
        List<CalendarEvent> tab = dao.findEventsByMeetupId(result.getId());

        if (tab.size() == 0) {
          Event insertedEvent = App.cal.events().insert("primary", gev).execute();
          dao.insertEvent(insertedEvent.getId(), result.getId());
        } else {
          App.cal.events().update("primary", tab.get(0).getGoogleId(), gev).execute();
        }
      }
    }
  }


  @Value("${meetup.groups}")
  public void setGroups(String[] groups) {
    this.groups = groups;
  }
}
