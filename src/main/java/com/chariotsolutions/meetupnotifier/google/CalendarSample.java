package com.chariotsolutions.meetupnotifier.google;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * hgk.
 * @author fda
 */
public class CalendarSample {


  private static final String APPLICATION_NAME = "ChariotSolutions-MeetupNotifier/0.1";

  private static final java.io.File DATA_STORE_DIR =
      new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

  /**
   * Build and return an authorized Calendar client service.
   * @return an authorized Calendar client service.
   */
  public static com.google.api.services.calendar.Calendar getCalendarService()
      throws IOException, GeneralSecurityException {
    // initialize the transport
    CalendarAuthorization.httpTransport = GoogleNetHttpTransport.newTrustedTransport();

    // initialize the data store factory
    CalendarAuthorization.dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

    // authorization
    Credential credential = CalendarAuthorization.authorize();

    // set up global Calendar instance
    return new com.google.api.services.calendar.Calendar.Builder(
        CalendarAuthorization.httpTransport, CalendarAuthorization.JSON_FACTORY, credential)
        .setApplicationName(APPLICATION_NAME)
        .build();
  }
}

