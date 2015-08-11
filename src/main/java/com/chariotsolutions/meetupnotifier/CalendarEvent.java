package com.chariotsolutions.meetupnotifier;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class CalendarEvent {
  private String googleId;
  private String meetupId;

  public CalendarEvent(String googleId, String meetupId) {
    checkNotNull(googleId);
    checkArgument(googleId.length() > 0, "The google ID should not be 0 length");
    checkNotNull(meetupId);
    checkArgument(meetupId.length() > 0, "The meetup ID should not be 0 length");
    this.googleId = googleId;
    this.meetupId = meetupId;
  }

  public String getGoogleId() {
    return googleId;
  }

  public String getMeetupId() {
    return meetupId;
  }

}
