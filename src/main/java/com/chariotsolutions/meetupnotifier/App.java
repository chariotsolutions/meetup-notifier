package com.chariotsolutions.meetupnotifier;

import com.chariotsolutions.meetupnotifier.google.CalendarSample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class App {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static com.google.api.services.calendar.Calendar cal;

  /**
   * Currently being used for testing purposes.
   * @param args Commandline arguments
   */
  public static void main(String[] args) throws IOException, GeneralSecurityException {
    cal = CalendarSample.getCalendarService();
    logger.debug("Calendar access object retrieved");

    ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"context.xml"});
  }
}

