package com.chariotsolutions.meetupnotifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalendarEventDaoHsqlDbImpl implements CalendarEventDao {

  @Autowired
  private JdbcTemplate template;

  public void insertEvent(String googleId, String meetupId) {
    template.update(
        "INSERT INTO calendar_event VALUES (?, ?)", googleId, meetupId);
  }

  public List<CalendarEvent> findEventsByMeetupId(String meetupId) {
    return template.query(
        "SELECT * from calendar_event where meetupID=?", eventMapper, meetupId);
  }

  private RowMapper<CalendarEvent> eventMapper = (resultSet, ii) -> new CalendarEvent(
      resultSet.getString("googleID"),
      resultSet.getString("meetupID"));


}
