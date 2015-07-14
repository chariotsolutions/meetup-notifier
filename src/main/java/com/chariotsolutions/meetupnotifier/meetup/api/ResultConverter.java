package com.chariotsolutions.meetupnotifier.meetup.api;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;
import com.google.api.services.calendar.model.Event;

@FunctionalInterface
public interface ResultConverter<T> {
  T convertTo(Result sourceResult);
}
