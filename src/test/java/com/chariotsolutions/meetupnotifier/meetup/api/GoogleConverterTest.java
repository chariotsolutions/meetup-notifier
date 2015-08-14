package com.chariotsolutions.meetupnotifier.meetup.api;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;
import com.google.api.services.calendar.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


public class GoogleConverterTest {
  private GoogleConverter googleConverter;


  @Before
  public void setup() {
    googleConverter = new GoogleConverter();
  }

  @Test
  public void testConversonOfSimpleResult() throws Exception {
    Result result = new Result();

    result.setName("name");
    result.setDescription("description");
    result.setVenueVisibility("");

    Event event = googleConverter.convertTo(result);

    assertThat(event.getSummary(), is("name"));
    assertThat(event.getDescription(), is("description"));

  }

  @Test(expected = InvalidResultException.class)
  public void testConversionWithMissingVenueVisibility() throws Exception {
    Result result = new Result();

    assertThat(result.getVenueVisibility(), is(nullValue()));

    Event evt = googleConverter.convertTo(result);
  }

  @Test(expected = InvalidResultException.class)
  public void testConversionWithMissingName() throws Exception{
    Result result = new Result();
    result.setDescription("A description");

    assertThat(result.getName(), is(nullValue()));
    Event evt = googleConverter.convertTo(result);

  }

  @Test(expected = InvalidResultException.class)
  public void testConversionWithMissingDescription() throws Exception{
    Result result = new Result();
    result.setName("A name");

    assertThat(result.getDescription(), is(nullValue()));
    Event evt = googleConverter.convertTo(result);


  }

}