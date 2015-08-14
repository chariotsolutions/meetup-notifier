package com.chariotsolutions.meetupnotifier.meetup.api;

public class InvalidResultException extends Exception {

  public InvalidResultException(String message) {
    super(message);
  }

  public InvalidResultException(String message, Throwable cause) {
    super(message, cause);
  }

}
