package com.codegym.aurora.backOffice.service;

public interface SecurityService {

    boolean isAuthenticated();

    boolean isValidToken(String token);
}
