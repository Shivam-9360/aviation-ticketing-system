package com.flight.booking.auth.exception;

import javax.naming.AuthenticationException;

public class CredentialsNotValidException extends AuthenticationException {
    public CredentialsNotValidException(String message){super(message);}
}
