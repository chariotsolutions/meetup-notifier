package com.chariotsolutions.meetupnotifier.meetup.api;

import com.chariotsolutions.meetupnotifier.meetup.api.messages.Result;

@FunctionalInterface
public interface ResultConverter<T> {
  T convertTo(Result sourceResult) throws InvalidResultException;
}
